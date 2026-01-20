# SSO

## 1、引言与基础

### 1.1、什么是单点登录？

单点登录（Single Sign-On） 是一种身份认证的集成方案。它允许用户在多个独立的软件系统中，仅使用一组身份凭证（如用户名和密码）登录一次，便能无缝访问所有相互信任的应用系统，而无需在每个系统上重复进行登录操作。其核心价值在于提升用户体验、简化密码管理和增强系统安全性。

<br>

### 1.2、SSO 的核心角色

*   用户（User）：需要访问系统的最终使用者。
*   身份提供商（Identity Provider, IdP）：系统的 “认证中心”，负责验证用户身份、管理用户凭证。
*   服务提供商（Service Provider, SP）：用户希望访问的业务应用系统，它信任 IdP 的认证结果。

<br>

### 1.3、主流 SSO 协议

协议是实现 SSO 的 “通用语言”，它定义了 IdP 和 SP 之间如何安全地通信和交换认证信息。 

<br>

**OpenID Connect (OIDC) 1.0**

*   定位：现代 Web/App 身份认证的事实标准。
*   基础：构建在 OAuth 2.0 授权框架之上的一层身份认证协议。
*   核心：通过引入 ID Token（一个 JWT 格式的令牌）来传递用户的身份信息，解决了 OAuth 2.0 只管 “授权” 不管 “认证” 的问题。
*   特点：基于 REST/JSON，轻量级，安全性高，生态完善。

<br>

**SAML (Security Assertion Markup Language) 2.0**

*   定位：企业级和联邦认证的成熟标准。
*   基础：基于 XML 的标记语言，用于交换认证和授权 “断言（Assertions）”。
*   核心：IdP 将一个经过数字签名的 XML 文档（SAML Response）发送给 SP，SP 验证该文档以确认用户身份。
*   特点：功能强大但配置繁琐，在企业级集成中广泛应用，对移动端不友好。

<br>

**CAS (Central Authentication Service)**

*   定位：一个具体的协议，也是一个开源项目，专注于 SSO 场景。
*   基础：自成体系的协议，通过浏览器重定向和 “票据（Ticket）” 机制工作。
*   核心：IdP 颁发一次性的服务票据（ST），SP 必须通过后端服务调用来验证此票据的有效性。
*   特点：架构清晰，安全性高，但在通用性上不如 OIDC 和 SAML。

<br>

## 2、主流方案与协议应用

### 2.1、基于共享存储

这种模式不遵循任何标准 SSO 协议，是一种自定义的、内部的实现方式，依赖于系统间的强耦合。

<br>

#### 2.1.1、同主域共享 Cookie

**关联协议**

无。这是一种纯粹的、利用浏览器特性的技巧。

<br>

**工作原理**

1.  用户在统一的登录域名（如 `login.example.com`）下登录。
2.  IdP 在认证成功后，向浏览器写入一个 Cookie，并将其 `Domain` 属性设置为主域名 `.example.com`。
3.  当用户访问同一主域名下的其他业务系统时（如 `app1.example.com`），浏览器会自动携带这个主域 Cookie。
4.  业务系统的后端读取并验证这个共享 Cookie，从而确认用户身份。

<br>

**优点**

*   实现简单：无需复杂的协议，逻辑直观，开发快速。

<br>

**缺点**

*   强依赖主域名：致命缺陷，无法支持不同主域名的系统。
*   高耦合：所有应用必须约定并理解同一个 Cookie 的格式和内容。
*   安全性较低：Cookie 直接暴露在浏览器，易受 CSRF、XSS 攻击。
*   非浏览器环境不友好：无法用于原生 App、小程序等场景。

<br>

**适用场景**

*   仅限于全部署在同一主域名下的、简单的、非核心的内部系统。在现代架构中已基本被淘汰。

<br>

#### 2.1.2、后端共享 Session

**关联协议**

无。是一种自定义方案。

<br>

**工作原理**

1.  用户在 IdP 登录后，IdP 创建一个 Session，并将 Session 数据存入一个中心化存储介质（如 Redis）。
2.  IdP 将与该 Session 关联的唯一 ID 通过 URL 重定向等方式传递给目标 SP。
3.  SP 接收到 ID 后，以后端服务访问 Redis，获取完整的用户信息，并创建自己的本地会话。

<br>

**优点**:

*   解决跨域问题：通过后端传递 ID，不再受浏览器同源策略限制。
*   相对安全：浏览器中的凭证只是一个无意义的 ID，敏感信息存储在后端。

<br>

**缺点**:

*   有状态服务：所有系统都强依赖后端的共享存储，增加了系统复杂性和潜在的性能瓶颈。
*   重度耦合：所有 SP 都必须能连接并访问该共享存储，违反了微服务的高内聚低耦合原则。

<br>

**适用场景**

*   应用数量不多的中小型项目，在微服务架构普及前较为常见。不推荐在新项目中使用。

<br>

### 2.2、基于重定向的票据/断言

这种模式是 SSO 的经典实现，通过浏览器重定向来协调 IdP 和 SP，并在后端安全地验证身份。

<br>

#### 2.2.1、票据验证

**关联协议**

CAS 协议

<br>

**工作原理**

1.  用户访问 SP，SP 发现未登录，将其重定向到 IdP，并附带 `service`（回调地址）参数。
2.  用户在 IdP 登录。IdP 创建全局会话（TGT），生成一次性服务票据（ST），然后将用户重定向回 SP 的 `service` 地址，并附上 `ticket=ST-xxxx`。
3.  SP 的后端收到 ST 后，立即向 IdP 的 `/serviceValidate` 端点发起一个安全的后台 HTTP/HTTPS 请求，验证 ST 的有效性。
4.  IdP 验证 ST 成功后，返回用户信息，并使 ST 失效。SP 创建本地会话。

<br>

**优点**

*   高安全性：用户凭证不离开 IdP，票据一次性使用且在后端验证，对前端透明。
*   架构清晰解耦：认证逻辑与业务逻辑完全分离，SP 职责单一。

<br>

**缺点**

*   性能开销：每次登录新应用都需要一次额外的后端 HTTP 调用来验证票据。
*   协议特定：主要用于支持 CAS 协议的系统，通用性不如 SAML/OIDC。

<br>

**适用场景**

*   企业级内部应用、高校系统、政府项目等对安全性、稳定性和架构清晰度有高要求的场景。特别是在已有 CAS 生态的环境中。

<br>

#### 2.2.2、断言消费

**关联协议**

SAML 2.0 协议。

<br>

**工作原理**

1.  用户访问 SP，SP 发现未登录，生成一个 XML 格式的认证请求（AuthnRequest），通过浏览器重定向发送给 IdP。
2.  用户在 IdP 登录。IdP 创建一个包含用户身份信息的 XML 文档（SAML Response），并使用其私钥进行数字签名。
3.  IdP 将包含 SAML Response 的表单自动提交回 SP 的断言消费服务（ACS）URL。
4.  SP 使用预先获取的 IdP 公钥来验证 SAML Response 的数字签名。验证通过后，解析 XML 获取用户信息，创建本地会话。

<br>

**优点**

*   功能强大：支持复杂的属性交换和联邦信任关系，是实现不同组织间 SSO 的利器。
*   企业级标准：在 B2B 场景和大型企业集成（如微软 ADFS, Salesforce）中是首选。

<br>

**缺点**

*   配置复杂：XML 和元数据交换（Metadata Exchange）的配置非常繁琐，调试困难。
*   体积庞大：XML 格式相比 JSON 更重，不适合性能敏感或移动应用。

<br>

**适用场景**

*   B2B 应用集成：当你的应用需要作为服务卖给其他公司，并与对方公司的身份系统打通时。
*   联邦认证：在多个独立的组织之间建立信任关系，实现跨组织的 SSO。

<br>

### 2.3、基于令牌

这是当前最主流的模式，其核心是 IdP 颁发一个自包含的、可被 SP 在本地验证的令牌。

典型代表就是基于 JWT 的身份认证。

<br>

**关联协议**

OpenID Connect (OIDC) 1.0。

<br>

**工作原理**

1.  用户访问 SP，SP 发现未登录，将用户重定向到 IdP 的授权端点，请求 `scope=openid` 并提供 `response_type=code`。
2.  用户在 IdP 登录并授权后，IdP 将浏览器重定向回 SP，并附带一个一次性的授权码（Code）。
3.  SP 的后端使用此授权码，向 IdP 的令牌端点发起请求。
4.  IdP 验证授权码后，返回 ID Token（JWT）和 Access Token。
5.  SP 在本地使用 IdP 的公钥验证 ID Token 的签名和内容。验证通过后，创建本地会话。
6.  客户端携带 Access Token 去访问受保护的 API。

<br>

**优点**

*   完全无状态与高性能：SP 可在本地验证令牌，无需额外网络请求，极利于微服务扩展。
*   标准且灵活：OIDC 流程清晰，同时支持 Web、移动端等多种客户端类型。
*   轻量级：基于 JSON，对网络友好，易于解析。
*   生态极其繁荣：几乎所有现代语言、框架和云服务都提供完善的支持。

<br>

**缺点**

*   令牌吊销复杂：JWT 的无状态性使得吊销成为一个挑战，通常需要引入黑名单等机制，这会增加系统的复杂性。
*   流程相对复杂：相比简单的票据模式，OIDC 的流程涉及更多步骤，但现代库已将其封装得很好。

<br>

**适用场景**

*   所有新项目：无论是 Web 应用、前后端分离的 SPA、微服务后端，还是移动 App。
*   构建开放平台：当你需要向第三方开发者提供 API 时，OIDC（OAuth 2.0）是最佳选择。

<br>

## 3、方案实现

### 3.1、非对称加密的认证

#### 3.1.1、方案概述

本方案旨在实现用户从法务系统到我方系统的安全、免登录跳转。为确保传输数据的机密性、完整性以及抵御重放攻击，本方案采用 `RSA非对称加密` + `时间戳` + `随机数(Nonce)` 的组合策略。

<br>

**核心流程**

法务系统将包含用户工号、当前时间戳和唯一随机数的 JSON 数据，使用我方提供的公钥加密后传递；我方后端使用私钥解密，并对时间戳和随机数进行严格校验，通过后为用户创建会话（Token），实现安全登录。

<br>

**方案优势**

1.  机密性：只有我方后端持有私钥，能解密用户身份信息。
2.  防篡改：RSA 签名特性保证了数据在传输中未被修改（虽然本方案主要用其加密性）。
3.  防重放 (Anti-Replay):
    *   时间戳（Timestamp）：确保请求在指定时间窗口内有效，过期请求将被拒绝。
    *   随机数（Nonce）：确保每个请求只能被成功处理一次，用过的请求将被记录并拒绝再次使用。

<br>

#### 3.1.2、核心流程

1.  准备阶段：我方系统生成一对 RSA 公钥和私钥。
    *   私钥 (Private Key)：由我方后端秘密保管，并配置在应用中。
    *   公钥 (Public Key)：提供给法务系统。

2.  跳转发起（法务系统）：
    * 用户在法务系统点击跳转链接。

    *   法务系统后端准备待加密的数据，格式为 JSON 字符串，包含三个字段：
        *   `employeeId`：用户工号（String）。
        *   `timestamp`：当前时间的毫秒级时间戳（long）。
        *   `nonce`：每次请求生成的唯一、不可预测的随机字符串（String）。
        
        示例：`{"employeeId":"user001","timestamp":1678886400000,"nonce":"a1b2c3d4e5f6g7h8"}`
        
    * 使用我方提供的公钥对上述 JSON 字符串进行 RSA 加密。

    * 将加密后的二进制密文进行 Base64 URL-Safe 编码。

    * 拼接跳转 URL：`https://our-system.com/sso-redirect?data=<encoded_encrypted_data>`

    *   前端重定向到此 URL。

3.  请求接收与处理（我方系统）：
    *   前端页面加载后，从 URL 中获取 `data` 参数。
    *   调用后端单点登录接口 `POST /api/sso/login`，将 `data` 作为请求体发送。
    *   后端接收到 `data` 密文，进行 Base64 URL-Safe 解码。
    *   使用私钥进行 RSA 解密，得到原始的 JSON 字符串。
    *   将 JSON 字符串解析为数据对象。
    *   安全校验：
        1. 校验时间戳：检查 `timestamp` 与当前服务器时间差是否在允许的范围内（例如 60 秒内）。若超时，则拒绝。
        2. 校验随机数：使用 Redis 检查 `nonce` 是否已被使用过。若已使用，则为重放攻击，拒绝。若未使用，则将该 `nonce` 存入 Redis 并设置一个稍长于时间戳窗口的过期时间（例如 120 秒）。
    *   安全校验通过后，根据 `employeeId` 查询用户信息，确认用户合法性。
    *   生成 JWT Token，并返回给前端。
    *   前端接收并存储 Token，完成登录，跳转到系统首页。

<br>

#### 3.1.3、实现步骤

##### 生成公钥和私钥

使用 openssl 工具来生成，这是最标准的方式。

1. 安装 OpenSSL：

   - Linux/macOS：通常自带。
   - Windows：可安装 Git for Windows，其自带的 Git Bash 中包含 openssl。

2. 打开终端/Git Bash。

3. 生成 2048 位 RSA 私钥 (PKCS#1)：

   ```shell
   openssl genrsa -out rsa_private_key_pkcs1.pem 2048
   ```

4. 从私钥中提取公钥：

   ```shell
   openssl rsa -in rsa_private_key_pkcs1.pem -pubout -out rsa_public_key.pem
   ```

5. 将 PKCS#1 私钥转换为 Java 更易于使用的 PKCS#8 格式

   ```shell
   openssl pkcs8 -topk8 -inform PEM -in rsa_private_key_pkcs1.pem -outform PEM -nocrypt -out rsa_private_key_pkcs8.pem
   ```

6. 获取密钥内容：

   *   提供给法务系统：`rsa_public_key.pem` 的内容。
   *   我方系统使用：`rsa_private_key_pkcs8.pem` 的内容。

<br>

##### 我方后端实现

1.  添加 pom 依赖（没有需要加上）：

   ```xml
   <!-- Web, Lombok(可选), Jackson(Web自带) -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   <!-- Redis for Nonce Caching -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>
   <!-- Lombok for cleaner code (optional) -->
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <optional>true</optional>
   </dependency>
   <!-- JWT Token Generation -->
   <dependency>
       <groupId>io.jsonwebtoken</groupId>
       <artifactId>jjwt-api</artifactId>
       <version>0.11.5</version>
   </dependency>
   <dependency>
       <groupId>io.jsonwebtoken</groupId>
       <artifactId>jjwt-impl</artifactId>
       <version>0.11.5</version>
       <scope>runtime</scope>
   </dependency>
   <dependency>
       <groupId>io.jsonwebtoken</groupId>
       <artifactId>jjwt-jackson</artifactId>
       <version>0.11.5</version>
       <scope>runtime</scope>
   </dependency>
   ```

2. 配置 application.yml：

   ```yaml
   spring:
     redis:
       host: localhost
       port: 6379
       # password: your-redis-password
   
   # 单点登录配置
   sso:
     # 公钥
     public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw...
     # 私钥
     private-key: MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDA...
     # 时间戳容忍窗口，单位：秒
     timestamp-tolerance-seconds: 60
   ```

3. 创建 SsoProperties 属性类：

   ```java 
   import lombok.Data;
   import org.springframework.boot.context.properties.ConfigurationProperties;
   import org.springframework.stereotype.Component;
   
   @Data
   @Component
   @ConfigurationProperties(prefix = "sso")
   public class SsoProperties {
       
       /**
        * RSA 公钥 (Base64编码的字符串)
        * 在测试环境中用于模拟加密方。
        */
       private String publicKey;
       
       /**
        * RSA 私钥 (Base64编码的PKCS#8格式字符串)
        * 这是启用SSO功能的关键配置。
        */
       private String privateKey;
   
       /**
        * 时间戳容忍窗口，单位：秒。
        * 用于防止重放攻击。
        */
       private long timestampToleranceSeconds = 60L;
   }
   ```

4. 创建 RSA 解密工具类 RsaUtils：

   ```java
   import lombok.RequiredArgsConstructor;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
   import org.springframework.stereotype.Component;
   
   import javax.crypto.Cipher;
   import java.nio.charset.StandardCharsets;
   import java.security.KeyFactory;
   import java.security.PrivateKey;
   import java.security.spec.PKCS8EncodedKeySpec;
   import java.util.Base64;
   
   @Component
   @RequiredArgsConstructor
   @ConditionalOnProperty(prefix = "sso", name = "private-key")
   public class RsaUtils {
   
       private final String privateKeyString;
       private PrivateKey privateKey;
   
       @Autowired
       public RsaUtils(SsoProperties ssoProperties) {
           this.privateKeyString = ssoProperties.getPrivateKey();
       }
   
       /**
        * 从字符串加载私钥
        *
        * @return PrivateKey
        * @throws Exception 异常
        */
       private PrivateKey getPrivateKey() throws Exception {
           if (privateKey == null) {
               byte[] keyBytes = Base64.getDecoder().decode(privateKeyString);
               PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
               KeyFactory kf = KeyFactory.getInstance("RSA");
               this.privateKey = kf.generatePrivate(spec);
           }
           return this.privateKey;
       }
   
       /**
        * RSA解密
        *
        * @param encryptedData 经过Base64编码的加密数据
        * @return 解密后的原始字符串
        * @throws Exception 异常
        */
       public String decrypt(String encryptedData) throws Exception {
           // Base64解码 -> RSA解密
           byte[] encryptedBytes = Base64.getUrlDecoder().decode(encryptedData); // 注意使用URL-safe的解码器
   
           Cipher cipher = Cipher.getInstance("RSA");
           cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
           byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
   
           return new String(decryptedBytes, StandardCharsets.UTF_8);
       }
   }
   ```

5. 创建 SsoPayload 数据传输对象：

   ```java
   import lombok.Data;
   
   @Data
   public class SsoPayload {
       /**
        * 用户ID
        */
       private String employeeId;
   
       /**
        * 时间戳
        */
       private long timestamp;
   
       /**
        * 随机数
        */
       private String nonce;
   }
   ```

6. 创建 Nonce 校验服务 NonceService：

   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
   import org.springframework.data.redis.core.StringRedisTemplate;
   import org.springframework.stereotype.Service;
   
   import java.util.concurrent.TimeUnit;
   
   @Service
   @ConditionalOnProperty(prefix = "sso", name = "private-key")
   public class NonceService {
   
       private static final String NONCE_PREFIX = "sso:nonce:";
       private final StringRedisTemplate redisTemplate;
       private final long toleranceSeconds;
   
       @Autowired
       public NonceService(StringRedisTemplate redisTemplate, SsoProperties ssoProperties) {
           this.redisTemplate = redisTemplate;
           this.toleranceSeconds = ssoProperties.getTimestampToleranceSeconds();
       }
   
       /**
        * 检查 Nonce 是否可用，如果可用，则立即占用它。
        * 这是一个原子操作。
        *
        * @param nonce 随机数
        * @return true 如果 nonce 是新的且被成功占用；false 如果 nonce 已存在
        */
       @Override
       public boolean useNonce(String nonce) {
           // 使用 setIfAbsent 实现原子性的 "检查并设置"
           // 将 nonce 存入 Redis，并设置一个比时间戳容错窗口稍长的过期时间，防止 Redis 内存被占满
           return Boolean.TRUE.equals(
                   redisTemplate.opsForValue().setIfAbsent(
                           NONCE_PREFIX + nonce,
                           "1",
                           toleranceSeconds + 60, // 设置 2 分钟的过期时间
                           TimeUnit.SECONDS
                   )
           );
       }
   }
   
   ```

7. 创建 SsoController 接口：

   ```java
   import com.fasterxml.jackson.databind.ObjectMapper;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.http.HttpStatus;
   import org.springframework.http.ResponseEntity;
   import org.springframework.web.bind.annotation.PostMapping;
   import org.springframework.web.bind.annotation.RequestBody;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   import java.time.Instant;
   import java.util.Collections;
   import java.util.Map;
   
   @RestController
   @RequestMapping("/api/sso")
   public class SsoController {
   
       @Autowired(required = false)
       private RsaUtils rsaUtils;
   
       @Autowired(required = false)
       private NonceService nonceService;
   
       @Autowired(required = false)
       private SsoProperties ssoProperties;
   
       @Autowired
       private ObjectMapper objectMapper;
   
       @PostMapping("/login")
       public ResponseEntity<Map<String, String>> ssoLogin(@RequestBody String encryptedData) {
           if (rsaUtils == null) {
               return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                       .body(Collections.singletonMap("error", "SSO 属性未配置"));
           }
   
           try {
               // 1. 解密
               String jsonPayload = rsaUtils.decrypt(encryptedData);
   
               // 2. 解析 Payload
               SsoPayload payload = objectMapper.readValue(jsonPayload, SsoPayload.class);
   
               // 3. 校验时间戳
               long now = Instant.now().toEpochMilli();
               if (Math.abs(now - payload.getTimestamp()) > ssoProperties.getTimestampToleranceSeconds() * 1000) {
                   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "请求超时或时间戳无效"));
               }
   
               // 4. 校验 Nonce (防重放)
               if (!nonceService.useNonce(payload.getNonce())) {
                   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "请求已被处理，请勿重放"));
               }
   
               // 5. [核心业务] 根据工号进行登录处理
               String employeeId = payload.getEmployeeId();
               // User user = userService.findByEmployeeId(employeeId);
               // if (user == null || !user.isActive()) {
               //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "用户不存在或状态异常"));
               // }
   
               System.out.println("SSO 校验成功，用户工号: " + employeeId);
   
               // 6. 生成 Token
               // String token = jwtService.generateToken(user);
               String token = "jwt-token-for-" + employeeId + "-nonce-" + payload.getNonce(); // 伪造 Token
   
               return ResponseEntity.ok(Collections.singletonMap("token", token));
           } catch (Exception e) {
               // 记录详细日志
               e.printStackTrace();
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "SSO 登录失败，请联系管理员"));
           }
       }
   }
   
   ```

<br>

##### 对接方系统说明

1. 将 `rsa_public_key.pem` 的内容（一长串 Base64 字符串，不含头尾标记和换行符）保存到法务系统。

2. 数据加密规则：

   *   加密内容：必须是一个包含 `employeeId`、`timestamp`、`nonce` 三个字段的 JSON 字符串。
   *   加密算法：RSA（标准填充模式 `RSA/ECB/PKCS1Padding`）。
   *   编码：加密后的二进制数据需进行 Base64 URL-Safe 编码。
   *   跳转 URL：`https://our-system.com/sso-redirect?data=<编码后的密文>`

3. Java 加密示例代码：

   ```java
   import javax.crypto.Cipher;
   import java.nio.charset.StandardCharsets;
   import java.security.KeyFactory;
   import java.security.PublicKey;
   import java.security.spec.X509EncodedKeySpec;
   import java.util.Base64;
   import java.util.UUID;
   
   /**
    * 单点登录（SSO）加密示例程序
    * <p>
    * 使用说明:
    * 1. 将此文件复制到您的项目中。
    * 2. 在 main 方法中，将 `OUR_SYSTEM_PUBLIC_KEY` 的值替换为我方系统提供的真实公钥。
    * 3. 运行 main 方法，即可在控制台看到生成的加密数据和最终的SSO URL。
    * 4. 将此文件中的加密逻辑集成到您的业务代码中。
    */
   public class SsoEncryptor {
   
       public static void main(String[] args) {
           try {
               // ========================= 配置区 START =========================
   
               // TODO: 请将此处的示例公钥替换为我方系统提供的真实公钥字符串
               // (公钥字符串应为一行，不包含"-----BEGIN..."头和"-----END..."尾，也不含换行符)
               final String OUR_SYSTEM_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw...<此处粘贴真实公钥>";
   
               // TODO: 此处应替换为法务系统当前登录用户的真实工号
               String currentUserEmployeeId = "legal_user_007";
   
               // TODO: 此处为我方系统的SSO接收地址
               final String SSO_TARGET_URL_BASE = "https://our-system.com/sso-redirect";
   
               // ========================= 配置区 END =========================
   
   
               // 1. 从字符串加载公钥对象
               byte[] keyBytes = Base64.getDecoder().decode(OUR_SYSTEM_PUBLIC_KEY);
               X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
               KeyFactory keyFactory = KeyFactory.getInstance("RSA");
               PublicKey publicKey = keyFactory.generatePublic(spec);
   
               // 2. 准备包含工号、时间戳和随机数的JSON数据载体
               long timestamp = System.currentTimeMillis();
               String nonce = UUID.randomUUID().toString().replace("-", "");
               // 注意：手动拼接JSON，请确保工号不含特殊字符，或进行转义
               String jsonPayload = String.format("{\"employeeId\":\"%s\",\"timestamp\":%d,\"nonce\":\"%s\"}",
                       currentUserEmployeeId, timestamp, nonce);
   
               System.out.println("Step 1: 待加密的原始数据 (JSON):");
               System.out.println(jsonPayload);
   
               // 3. 使用公钥进行RSA加密
               Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
               cipher.init(Cipher.ENCRYPT_MODE, publicKey);
               byte[] encryptedBytes = cipher.doFinal(jsonPayload.getBytes(StandardCharsets.UTF_8));
   
               // 4. 将加密后的二进制数据进行Base64 URL-Safe编码
               String encryptedData = Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedBytes);
   
               System.out.println("\nStep 2: 加密并编码后的数据:");
               System.out.println(encryptedData);
   
               // 5. 拼接最终的SSO跳转URL
               String finalSsoUrl = SSO_TARGET_URL_BASE + "?data=" + encryptedData;
   
               System.out.println("\nStep 3: 最终生成的SSO跳转链接:");
               System.out.println(finalSsoUrl);
   
           } catch (Exception e) {
               System.err.println("\n[ERROR] SSO 加密过程中发生错误!");
               System.err.println("请检查以下几点:");
               System.err.println("1. 公钥字符串是否正确、完整，且为单行。");
               System.err.println("2. 您的Java环境是否支持所需的加密算法 (通常都支持)。");
               e.printStackTrace();
           }
       }
   }
   ```

<br>

##### 我方前端实现

1. 创建登录请求函数 `ssoLogin()`：

   ```js
   /**
    * 执行单点登录（SSO）
    * @param {string} encryptedData 从 URL 获取的加密数据
    * @returns {Promise<object>} 返回一个 Promise，成功时解析为后端返回的数据，例如 { token: '...' }
    */
   export function ssoLogin(encryptedData) {
     return request({
       url: '/api/sso/login',
       method: 'post',
       headers: {
         'Content-Type': 'text/plain;charset=UTF-8'
       },
       data: encryptedData
     })
   }
   ```

2. 修改 Vuex Action，增加 `ssoLogin()` 实现：

   ```js
   /**
     * 单点登录 (SSO)
     * 接收加密数据，调用API完成登录，并更新状态。
     * @param {object} context - Vuex action context { commit }
     * @param {string} encryptedData - 从URL获取的加密字符串
     */
   ssoLogin({commit}, encryptedData) {
       return new Promise((resolve, reject) => {
           ssoLogin(encryptedData).then(res => {
               setToken(res.token)
               commit('SET_TOKEN', res.token)
               resolve()
           }).catch(error => {
               reject(error)
           })
       })
   },
   ```

3. 创建 SSO 处理组件 SsoRedirect：

   ```vue
   <template>
     <div class="sso-container">
       <div class="loading-wrapper">
         <i class="el-icon-loading"></i>
         <p class="loading-text">正在安全登录中，请稍候...</p>
       </div>
     </div>
   </template>
   
   <script>
   
   export default {
     name: 'SsoRedirect',
     created() {
       this.handleSsoLogin()
     },
     methods: {
       handleSsoLogin() {
         const encryptedData = this.$route.query.data
   
         if (!encryptedData) {
           this.$message.error('无效的单点登录请求，缺少必要参数。')
           this.$router.push({path: '/login'})
           return
         }
   
         this.$store.dispatch('ssoLogin', encryptedData)
           .then(() => {
             console.log('SSO 登录成功，正在跳转...')
             const redirect = this.$route.query.redirect || '/'
             this.$router.push({path: redirect})
           })
           .catch(error => {
             console.error('SSO 登录失败:', error)
             const errorMessage = (error.response && error.response.data && error.response.data.msg)
               || error.message
               || '单点登录认证失败，请重试。'
             this.$message.error(errorMessage)
             this.$router.push({path: '/login'})
           })
       }
     }
   }
   </script>
   
   <style scoped>
   .sso-container {
     display: flex;
     justify-content: center;
     align-items: center;
     height: 100vh;
     background-color: #f0f2f5;
   }
   
   .loading-wrapper {
     text-align: center;
     color: #606266;
   }
   
   .el-icon-loading {
     font-size: 50px;
   }
   
   .loading-text {
     margin-top: 20px;
     font-size: 16px;
   }
   </style>
   ```

4. 路由配置：

   ```js
   {
       path: '/sso-redirect',
       component: () => import('@/views/ssoRedirect'),
       hidden: true
   },
   ```

<br>

##### 测试

编写集成测试。在自己的系统中同时模拟 “发起方（法务系统）” 和 “接收方（我方系统）”，可以确保整个加解密链路的正确性。

1. 在 SsoProperties 类中添加一个 `publicKey` 字段：

   ```java
   import lombok.Data;
   import org.springframework.boot.context.properties.ConfigurationProperties;
   import org.springframework.stereotype.Component;
   
   @Data
   @Component
   @ConfigurationProperties(prefix = "sso")
   public class SsoProperties {
   
       /**
        * RSA 私钥 (Base64编码的PKCS#8格式字符串)
        * 这是启用SSO功能的关键配置，用于解密。
        */
       private String privateKey;
   
       /**
        * RSA 公钥 (Base64编码的字符串)
        * 在测试环境中用于模拟加密方。
        */
       private String publicKey; // <-- 新增此字段
   
       /**
        * 时间戳容忍窗口，单位：秒。
        * 用于防止重放攻击。
        */
       private long timestampToleranceSeconds = 60L;
   }
   ```

2. 在 application.yml 文件中，同时配置公钥和私钥：

   ```yaml
   sso:
     # 用于测试加密的公钥
     public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw...<你的公钥字符串>
     # 用于解密的私钥
     private-key: MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDA...<你的私钥字符串>
     timestamp-tolerance-seconds: 60
   ```

   > [!IMPORTANT]
   >
   > 将 .pem 文件中的 `-----BEGIN...-----` 和 `-----END...-----` 标记以及所有换行符都去掉。

3. 创建加密服务 SsoEncryptorService：

   ```java
   import com.fasterxml.jackson.core.JsonProcessingException;
   import com.fasterxml.jackson.databind.ObjectMapper;
   import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
   import org.springframework.stereotype.Service;
   
   import javax.crypto.Cipher;
   import java.nio.charset.StandardCharsets;
   import java.security.KeyFactory;
   import java.security.PublicKey;
   import java.security.spec.X509EncodedKeySpec;
   import java.util.Base64;
   import java.util.UUID;
   
   @Service
   @ConditionalOnProperty(prefix = "sso", name = "public-key")
   public class SsoEncryptorService {
   
       private final PublicKey publicKey;
       private final ObjectMapper objectMapper;
   
       public SsoEncryptorService(SsoProperties ssoProperties, ObjectMapper objectMapper) throws Exception {
           this.objectMapper = objectMapper;
           
           // 从配置中加载公钥
           byte[] keyBytes = Base64.getDecoder().decode(ssoProperties.getPublicKey());
           X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
           KeyFactory kf = KeyFactory.getInstance("RSA");
           this.publicKey = kf.generatePublic(spec);
       }
   
       /**
        * 加密一个包含工号的SSO载体
        * @param employeeId 要加密的工号
        * @return 经过Base64 URL-Safe编码的加密字符串
        * @throws Exception 加密异常
        */
       public String encrypt(String employeeId) throws Exception {
           // 创建Payload
           SsoPayload payload = new SsoPayload();
           payload.setEmployeeId(employeeId);
           payload.setTimestamp(System.currentTimeMillis());
           payload.setNonce(UUID.randomUUID().toString().replace("-", ""));
   
           // 将Payload转为JSON字符串
           String jsonPayload = objectMapper.writeValueAsString(payload);
   
           // 使用公钥加密
           Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
           cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
           byte[] encryptedBytes = cipher.doFinal(jsonPayload.getBytes(StandardCharsets.UTF_8));
   
           // 返回URL-safe的Base64编码字符串
           return Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedBytes);
       }
   }
   ```

4. 编写集成测试类 SsoControllerTest：

   ```java
   import org.junit.jupiter.api.Test;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
   import org.springframework.boot.test.context.SpringBootTest;
   import org.springframework.http.MediaType;
   import org.springframework.test.web.servlet.MockMvc;
   
   import static org.hamcrest.Matchers.containsString;
   import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
   import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
   import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
   
   @SpringBootTest
   @AutoConfigureMockMvc
   class SsoControllerTest {
   
       @Autowired
       private MockMvc mockMvc;
   
       @Autowired
       private SsoEncryptorService encryptorService;
   
       @Test
       void testSsoLogin_Success() throws Exception {
           // 准备测试数据
           String employeeId = "test_user_001";
   
           // 使用加密服务生成加密后的数据，模拟法务系统的行为
           String encryptedData = encryptorService.encrypt(employeeId);
           System.out.println("Generated Encrypted Data for test: " + encryptedData);
   
           // 使用 MockMvc 模拟向 /api/sso/login 发送POST请求
           mockMvc.perform(post("/api/sso/login")
                   .contentType(MediaType.TEXT_PLAIN) // 注意，我们发送的是纯文本
                   .content(encryptedData)) // 将加密数据作为请求体
               // 断言（Assert）期望的结果
               .andExpect(status().isOk()) // 期望HTTP状态码为 200 OK
               .andExpect(jsonPath("$.token").exists()) // 期望返回的JSON中包含 "token" 字段
               .andExpect(jsonPath("$.token").isNotEmpty()) // 期望 "token" 的值不为空
               .andExpect(jsonPath("$.token", containsString(employeeId))); // 期望生成的token中包含工号信息
       }
       
       @Test
       void testSsoLogin_ReplayAttack() throws Exception {
           // 加密一次数据
           String encryptedData = encryptorService.encrypt("replay_user_002");
           
           // 第一次请求，应该成功
           mockMvc.perform(post("/api/sso/login")
                   .contentType(MediaType.TEXT_PLAIN)
                   .content(encryptedData))
               .andExpect(status().isOk());
               
           // 立即使用完全相同的数据再次请求，模拟了=重放攻击
           System.out.println("Performing replay attack with the same data...");
           mockMvc.perform(post("/api/sso/login")
                   .contentType(MediaType.TEXT_PLAIN)
                   .content(encryptedData))
               // 4.期望请求被拒绝，例如返回 401 Unauthorized
               .andExpect(status().isUnauthorized())
               .andExpect(jsonPath("$.error", containsString("请勿重放"))); // 检查错误信息
       }
   }
   ```

