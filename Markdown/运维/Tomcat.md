# Tomcat

## 1、简介

Tomcat 容器实现了 Jakarta Servlet、Jakarta Expression Language 与 WebSocket 技术规范，统一管理 Web 应用的生命周期与请求分发。

Tomcat 既可作为独立 HTTP 服务器，也可通过 Coyote Connector 与 Apache HTTPD、IIS 等集成，满足多种部署需求。



**核心特点**

- 轻量级：纯 Java 实现，无需完整 Java EE 容器即可运行 Servlet/JSP 应用。
- 高可扩展：支持多实例（CATALINA_BASE）、集群、负载均衡与热部署等高级特性。
- 灵活配置：通过 `server.xml`、`context.xml`、`web.xml` 等文件全局或应用级配置，便于自定义连接器、资源与安全策略。



**版本兼容性**

| Tomcat 版本 | 最低 Java 版本 |
| ----------- | -------------- |
| 11.0.x      | 17 或更高      |
| 10.1.x      | 11 或更高      |
| 10.0.x      | 8 或更高       |
| 9.0.x       | 8 或更高       |
| 8.5.x       | 7 或更高       |





## 2、安装与目录结构

### 2.1、安装

1. 下载 Tomcat 安装包：

   ```shell
   wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.105/bin/apache-tomcat-9.0.105.tar.gz
   ```

2. 解压到目标目录：

   ```shell
   tar zxvf apache-tomcat-9.0.105.tar.gz -C /opt
   ```

3. 重命名目录以简化路径：

   ```shell
   mv /opt/apache-tomcat-9.0.105 /opt/tomcat
   ```

4. 编辑系统环境变量：

   ```shell
   sudo vim /etc/profile
   ```

   添加如下内容：
   ```shell
   export CATALINA_HOME=/opt/tomcat
   export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
   export PATH=$JAVA_HOME/bin:$CATALINA_HOME/bin:$PATH
   ```

5. 执行以下命令使配置生效：
   ```shell
   source /etc/profile
   ```



### 2.2、目录结构

```perl
/opt/tomcat
├── bin/          # 启动/停止脚本
├── conf/         # server.xml, web.xml, context.xml, tomcat-users.xml, catalina.properties, logging.properties
├── lib/          # Tomcat 核心库
├── logs/         # 日志文件
├── webapps/      # 默认部署目录
├── work/         # JSP 编译临时文件
├── temp/         # JVM 临时文件
└── endorsed/     #（旧版）Java 扩展 JAR
```



## 3、部署项目

将 `myapp.war` 复制至 `webapps/`，Tomcat 自动解压部署。



## 4、配置文件

### 4.1、server.xml  

全局核心配置，定义 `<Server>`、`<Service>`、`<Connector>`、`<Engine>`、`<Host>` 等组件及其关系。

其结构为：

```perl
<Server>
  └── <Service>
        ├── <Connector>
        └── <Engine>
              └── <Host>
```

示例：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Server port="8005" shutdown="SHUTDOWN">
  <!-- Listener 用于在 Tomcat 启动/关闭时执行相应操作 -->
  <Listener className="org.apache.catalina.core.JasperListener" />
  <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
  <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
  <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
  <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />

  <!-- 全局 JNDI 资源 -->
  <GlobalNamingResources>
    <Resource name="UserDatabase" auth="Container"
              type="org.apache.catalina.UserDatabase"
              description="User database that can be updated and saved"
              factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
              pathname="conf/tomcat-users.xml" />
  </GlobalNamingResources>

  <!-- 一个 Service 示例，包含两个 Connector（HTTP 和 AJP）以及一个 Engine -->
  <Service name="Catalina">
    <!-- HTTP 连接器 -->
    <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
    <!-- AJP 连接器 -->
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />

    <!-- Engine 核心 -->
    <Engine name="Catalina" defaultHost="localhost">
      <!-- Security Realm，用于用户鉴权 -->
      <Realm className="org.apache.catalina.realm.LockOutRealm">
        <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
               resourceName="UserDatabase" />
      </Realm>

      <!-- 虚拟主机 Host -->
      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
        <!-- 访问日志配置 -->
        <Valve className="org.apache.catalina.valves.AccessLogValve"
               directory="logs"
               prefix="localhost_access_log." suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
      </Host>
    </Engine>
  </Service>
</Server>

```



#### 4.1.1、Server

下面示例展示了 `<Server>` 的常见配置，包括关闭端口、关闭口令及周期性任务线程数的设置：

```xml
<Server port="8005" address="0.0.0.0" shutdown="SHUTDOWN" portOffset="100"
        utilityThreads="0" utilityThreadsAsDaemon="true" periodicEventDelay="5"
        className="org.apache.catalina.core.StandardServer">
  <!-- 此处可嵌套 GlobalNamingResources 与 Service 等元素 -->
</Server>
```

常用属性及详解：

| 属性名                   | 默认值                                    | 描述                                                         |
| ------------------------ | ----------------------------------------- | ------------------------------------------------------------ |
| `className`              | `org.apache.catalina.core.StandardServer` | 指定实现 `org.apache.catalina.Server` 接口的类名；若不显式设置，则默认使用上表中的标准实现。 |
| `port`                   | `8005`                                    | 监听关闭命令的端口号。客户端可通过 `telnet localhost 8005` 发送 `shutdown` 指令来优雅关闭 Tomcat。 若设置为 `-1` 则禁用关闭端口，需使用其他方式停止进程。 |
| `address`                | `localhost`                               | 接收关闭命令的 IP 地址。默认为 `localhost`，仅本地可发送关闭指令；若设置为 `0.0.0.0` 则监听所有网络接口。 |
| `shutdown`               | `SHUTDOWN`                                | 关闭口令，可通过 TCP 发送此字符串触发容器关闭。默认值为 `"SHUTDOWN"`，需与客户端发送指令一致。 |
| `portOffset`             | `0`                                       | 对 `port` 以及任意嵌套的 `<Connector>` 端口进行统一偏移。若设为 `100`，则所有端口值（如 8005、8080 等）会自动加上 100。常用于同一台服务器部署多个 Tomcat 实例避免端口冲突。 |
| `utilityThreads`         | `1`                                       | 用于执行周期性维护任务的线程数；`0` 表示使用 `availableProcessors()`，负值表示 `availableProcessors()+value`（最小不低于 1）。 |
| `utilityThreadsAsDaemon` | `false`                                   | 指定上述 `utilityThreads` 是否以守护线程方式创建；`true` 表示守护线程，JVM 退出时不会阻止进程结束。 |
| `periodicEventDelay`     | `10`                                      | 生命周期监听器（`LifecycleListener`）周期性事件的间隔（秒），如内存泄漏保护、JNDI 全局资源检查等；设为 `0` 或负值时禁用该周期性调用。 |



#### 4.1.2、Service

下面示例展示了包含两个 `<Connector>`（HTTP 和 AJP）以及一个 `<Engine>` 的典型 `<Service>` 配置：

```xml
<Service name="Catalina" className="org.apache.catalina.core.StandardService"
         gracefulStopAwaitMillis="5000">
  <!-- HTTP 连接器 -->
  <Connector port="8080" protocol="HTTP/1.1"
             connectionTimeout="20000"
             redirectPort="8443" />
  <!-- AJP 连接器 -->
  <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />

  <!-- 引擎 -->
  <Engine name="Catalina" defaultHost="localhost">
    <!-- Realm、Host 等子元素见下文 -->
  </Engine>
</Service>
```

常用属性及详解：

| 属性名                    | 默认值                                     | 描述                                                         |
| ------------------------- | ------------------------------------------ | ------------------------------------------------------------ |
| `className`               | `org.apache.catalina.core.StandardService` | 指定实现 `org.apache.catalina.Service` 接口的类名；若不指定，则使用标准实现。 |
| `name`                    | `Catalina`                                 | 服务名称，用于日志与管理工具（如 JMX）标识；在同一 `<Server>` 中应唯一。若需要多个 Service，可使用不同名称以区分。 |
| `gracefulStopAwaitMillis` | `0`                                        | 在关闭 Service 时等待客户端连接优雅关闭的最大毫秒数，仅在连接器启用了 `bindOnInit="false"`（即延迟绑定）时有效。设为 `0` 则不等待，立即关闭。 |



#### 4.1.3、Connector

`<Connector>` 是 Tomcat 用来处理网络请求的关键组件，可配置多种协议（HTTP、HTTPS、AJP），并对线程数、超时、压缩等进行精细控制。

HTTP/1.1 连接器示例：

```xml
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443"
           maxThreads="200" minSpareThreads="10" maxSpareThreads="100"
           acceptCount="100"
           URIEncoding="UTF-8"
           compression="on" compressionMinSize="2048"
           noCompressionUserAgents="gozilla, traviata"
           compressableMimeType="text/html,text/xml,text/plain,text/css,application/json,application/javascript"
           maxHttpHeaderSize="8192"
           enableLookups="false" />
```

 HTTPS (NIO2) 连接器示例：

```xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11Nio2Protocol"
           maxThreads="200" SSLEnabled="true" scheme="https" secure="true"
           clientAuth="false" sslProtocol="TLS"
           keystoreFile="conf/keystore.jks" keystorePass="changeit"
           maxHttpHeaderSize="8192"
           URIEncoding="UTF-8" />
```

AJP 连接器示例：

```xml
<Connector port="8009" protocol="AJP/1.3"
           redirectPort="8443"
           enableLookups="false"
           connectionTimeout="20000"
           URIEncoding="UTF-8" />
```

通用属性（HTTP/HTTPS/NIO/APR/AJP 均支持）：

| 属性名                    | 默认值                          | 描述                                                         |
| ------------------------- | ------------------------------- | ------------------------------------------------------------ |
| `className`               | 协议默认实现                    | 指定实现 `org.apache.coyote.ProtocolHandler` 接口的类名：[HTTP/1.1] 默认 `org.apache.coyote.http11.Http11Protocol`；[NIO] `org.apache.coyote.http11.Http11NioProtocol`；[NIO2] `org.apache.coyote.http11.Http11Nio2Protocol`；[APR] `org.apache.coyote.http11.Http11AprProtocol`；[AJP] `org.apache.coyote.ajp.AjpProtocol`。 |
| `port`                    | 无                              | 要监听的 TCP 端口号：HTTP 常用 `8080`；HTTPS 常用 `8443`；AJP 常用 `8009`。必须显式指定。 |
| `protocol`                | `HTTP/1.1` 或 `AJP/1.3` 等      | 简洁写法与 `className` 相对应，例如 `protocol="HTTP/1.1"` 等同于 `className="org.apache.coyote.http11.Http11Protocol"`。对于 NIO/NIO2/APR，需要填写完整类名。 |
| `maxThreads`              | `200`                           | 最大处理线程数；并发请求数超过该值时，新请求会排队等待。适当调整可提升并发性能。 |
| `minSpareThreads`         | `10`                            | 保持的最小空闲线程数；当空闲线程低于该值时，自动创建新线程；避免峰值时线程来不及创建。 |
| `maxSpareThreads`         | `100`                           | 保持的最大空闲线程数；超过该值的空闲线程将被销毁，防止线程过多占用内存。 |
| `acceptCount`             | `100`                           | 当所有工作线程都在忙时，允许排队等待的最大连接数；超过后则拒绝新连接。 |
| `connectionTimeout`       | `20000`                         | 等待客户端发送请求头的最长时间（毫秒）。超过该时间未接收完整数据时关闭连接。 |
| `keepAliveTimeout`        | `-1`                            | 在持久连接（Keep-Alive）模式下，等待下一个请求的超时时间（毫秒）。若为 `-1` 则使用 `connectionTimeout`；若为 `0` 表示永不超时。 |
| `maxKeepAliveRequests`    | `100`                           | 同一持久连接上允许请求的最大次数；达到后关闭连接；若为 `-1` 表示不限制。 |
| `URIEncoding`             | `UTF-8`（推荐）                 | 请求中 URI（路径及查询字符串）的字符编码，尤其用于处理中文等非 ASCII 字符，否则会导致乱码。若不指定，默认使用 JVM 的 `file.encoding`。 |
| `useBodyEncodingForURI`   | `false`                         | 若设为 `true`，则使用请求体（如 POST）字符编码来解码 URI；适用于表单提交时参数编码与 URL 编码一致的场景；默认仅对查询字符串有效。 |
| `maxHttpHeaderSize`       | `8192`                          | HTTP 请求头的最大字节数；超过该值将拒绝请求。                |
| `maxSwallowSize`          | `2097152`                       | 对于请求体超过 `maxPostSize` 时允许吞掉的最大字节数；默认 `2MB`；若设置为 `-1`，表示无限制；若请求过大超过该值，会抛异常。 |
| `enableLookups`           | `false`                         | 是否进行反向 DNS 查询，将客户端 IP 逆解析为主机名；开启会影响性能，建议生产环境关闭；若需要基于主机名做访问控制或日志记录可启用。 |
| `compression`             | `off`                           | 是否启用 GZIP 压缩，值为 `"on"` 或 `"off"`；若启用需配合下列属性：`compressionMinSize`、`noCompressionUserAgents`、`compressableMimeType`。 |
| `compressionMinSize`      | `2048`                          | HTTP 响应体大于该值（字节）时才进行压缩；小于该值将不压缩，以避免过度压缩带来的性能损耗。 |
| `noCompressionUserAgents` | `gozilla, traviata`             | 对特定 User-Agent 不启用压缩列表（多个用逗号分隔），常针对不支持或性能不佳的客户端。 |
| `compressableMimeType`    | `text/html,text/xml,text/plain` | 指定允许压缩的 MIME 类型；逗号分隔。                         |
| `scheme`                  | `http`（HTTP）/`https`（HTTPS） | 当启用 SSL 时需设置为 `"https"`；否则可省略。                |
| `secure`                  | `false`                         | 明确标识该 Connector 是否为安全连接；若启用 SSL，需设为 `true`；否则预设为 `false`。 |
| `SSLEnabled`              | `false`                         | 对于 HTTPS/NIO2/APR 协议，是否启用 SSL；若为 `true` 则需要配置 `keystoreFile`、`keystorePass` 等。 |
| `keystoreFile`            | 无                              | 指定包含密钥对的 Java KeyStore 文件路径（相对或绝对）；仅当 `SSLEnabled="true"` 时有效。 |
| `keystorePass`            | 无                              | KeyStore 的访问密码；仅在启用 SSL 时需要。                   |
| `keyAlias`                | 无                              | KeyStore 中要使用的密钥对别名；若不指定，则使用 KeyStore 默认别名。 |
| `clientAuth`              | `false`                         | 是否需要客户端证书校验；仅当启用 SSL 时生效；`true` 表示双向验证；默认 `false`。 |
| `sslProtocol`             | `TLS`                           | SSL/TLS 协议版本；常用 `TLS`、`TLSv1.2` 等；仅在启用 SSL 时有效。 |
| `maxPostSize`             | `2097152`                       | POST 请求体最大大小（字节），超过该值请求将拒绝；默认 `2MB`；若为 `-1` 表示不限制。 |
| `bindOnInit`              | `true`                          | 是否在启动时就绑定监听端口，若设为 `false`，则延迟到第一次请求时才绑定。辅助 `gracefulStopAwaitMillis` 使用。 |
| `disableUploadTimeout`    | `true`                          | 当接收大文件上传时，是否禁用超时；若设为 `false`，则上传时仍会触发 `connectionTimeout`。 |
| `maxConnections`          | `10000`                         | 服务器允许的最大 TCP 连接数。                                |

上述属性均可在 `<Connector>` 中混合使用，根据实际需求（HTTP/HTTPS/AJP）选择适配。



#### 4.1.4、Engine

`<Engine>` 是 Servlet 引擎的核心组件，负责将 `<Connector>` 接收到的请求分发给相应的 `<Host>` 中的 `<Context>`（Web 应用）。一个 `<Service>` 只能包含一个 `<Engine>`。

标准示例：

```xml
<Engine name="Catalina" defaultHost="localhost" jvmRoute="node01" backgroundProcessorDelay="10">
  <!-- 安全 Realm 示例 -->
  <Realm className="org.apache.catalina.realm.LockOutRealm">
    <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
           resourceName="UserDatabase" />
  </Realm>

  <!-- 虚拟主机 Host -->
  <Host name="localhost" appBase="webapps" unpackWARs="true" autoDeploy="true">
    <!-- Host 下可嵌套多个 Context 或 Valve -->
  </Host>
  <!-- 其他虚拟主机可继续在此处定义 -->
</Engine>
```

常用属性及详解：

| 属性名                     | 默认值                                    | 描述                                                         |
| -------------------------- | ----------------------------------------- | ------------------------------------------------------------ |
| `className`                | `org.apache.catalina.core.StandardEngine` | 指定实现 `org.apache.catalina.Engine` 接口的类名；若不设置，则使用标准实现。 |
| `name`                     | `Catalina`                                | 引擎名称，用于日志与管理工具标识，不同 `<Service>`  下可重复。 |
| `defaultHost`              | 无                                        | 指定默认虚拟主机名称，如果请求的 Host 头无法匹配任何 `<Host name="...">`，则会将请求路由到此默认主机。建议设为 `localhost`。 |
| `jvmRoute`                 | 无                                        | 用于集群环境中标识节点的路由标识（Session 复制/粘滞会话），一般在 Load Balancer 配合 `<Valve className="org.apache.catalina.ha.session.JvmRouteBinderValve" />` 使用。 |
| `backgroundProcessorDelay` | `10`                                      | 用于设置后台维护线程（如会话过期检查）运行的间隔时间（秒），若设为 `-1` 则禁用该定时任务；若为 `0` 则使用默认值 (`10`)。 |



#### 4.1.5、Host

`<Host>` 用于定义虚拟主机（Virtual Host），每个 `<Engine>` 可包含多个 `<Host>`。每个虚拟主机拥有独立的应用部署目录及日志配置。

下面示例展示了两个虚拟主机的配置：一个主机 `localhost`，另一个主机 `example.com`：

```xml
<Host name="localhost"  appBase="webapps"
      unpackWARs="true" autoDeploy="true"
      deployOnStartup="true"
      xmlValidation="false" xmlNamespaceAware="false"
      backgroundProcessorDelay="30">
  <!-- 访问日志 -->
  <Valve className="org.apache.catalina.valves.AccessLogValve"
         directory="logs"
         prefix="localhost_access_log." suffix=".txt"
         pattern="%h %l %u %t &quot;%r&quot; %s %b" />
</Host>

<Host name="example.com" appBase="/var/www/webapps"
      unpackWARs="false" autoDeploy="false"
      deployOnStartup="false"
      xmlValidation="true" xmlNamespaceAware="true"
      backgroundProcessorDelay="-1">
  <!-- 自定义 404 页面 -->
  <Context path="/" docBase="/var/www/webapps/ROOT" reloadable="false" />
</Host>
```

常用属性及详解：

| 属性名                     | 默认值                                  | 描述                                                         |
| -------------------------- | --------------------------------------- | ------------------------------------------------------------ |
| `className`                | `org.apache.catalina.core.StandardHost` | 指定实现 `org.apache.catalina.Host` 接口的类名；若未设置，则使用标准实现。 |
| `name`                     | 无                                      | 虚拟主机名称，需与请求的 Host 头部匹配；例如 `localhost`、`example.com`。 |
| `appBase`                  | `webapps`                               | 默认部署目录，相对路径是相对于 `$CATALINA_BASE`，可设置为绝对路径。Tomcat 会在此目录下查找 WAR 包或应用目录并自动部署。 |
| `unpackWARs`               | `true`                                  | 是否自动解压 WAR 包为目录；`true` 表示解压，适合开发和生产环境；设为 `false` 可节省磁盘空间，但部署变更需手动重启。 |
| `autoDeploy`               | `true`                                  | 在运行时监测 `appBase` 目录下新增或更新的 WAR 包，若为 `false`，则需要手动部署/重启才能生效。建议生产环境设为 `false` 并通过 CI/CD 工具部署。 |
| `deployOnStartup`          | `true`                                  | 是否在 Tomcat 启动时自动部署 `appBase` 下所有应用，若设为 `false` 则需手动部署。 |
| `autoDeployInterval`       | `1`（秒）                               | 当 `autoDeploy="true"` 时，监测 `appBase` 目录的时间间隔（秒）。 |
| `xmlValidation`            | `true`                                  | 是否对部署描述符（`context.xml`、`web.xml`）进行 XML 验证；生产环境可设为 `false` 以加快部署速度。 |
| `xmlNamespaceAware`        | `false`                                 | 是否在解析部署描述符时启用 XML 命名空间；若为 `true`，可更严格地解析符合命名空间的 XML 文件。 |
| `backgroundProcessorDelay` | `30`（秒）                              | 用于 Host 级别后台任务（如应用过期清理）的延迟时间；若设为 `-1` 则禁用。 |
| `managerContextName`       | 无                                      | 若同时配置了 Manager 应用，此属性指定 Manager 的上下文名称；常见值为 `manager`。仅在 Host 上启用 Manager Web 应用时生效。 |



#### 4.1.6、Context

`<Context>` 元素可以嵌套在 `<Host>` 中，也可以通过独立 XML 文件放置在 `conf/[enginename]/[hostname]/` 路径下，例如 `conf/Catalina/localhost/myapp.xml`。

下述示例定义了一个路径为 `/myapp` 的应用，热部署开启，Session 失效时间为 60 分钟，并捕获 System.out/err 到日志：

```xml
<Context path="/myapp" docBase="myapp.war" reloadable="true"
         sessionTimeout="60" cookies="true" useHttpOnly="true"
         antiJARLocking="false" antiResourceLocking="false"
         swallowOutput="true" privileged="false">
  <!-- 可在此定义 Context 特定的 Resource、Environment、Valve 等 -->
</Context>
```

常用属性及详解：

| 属性名                | 默认值                                     | 描述                                                         |
| --------------------- | ------------------------------------------ | ------------------------------------------------------------ |
| `className`           | `org.apache.catalina.core.StandardContext` | 指定实现 `org.apache.catalina.Context` 接口的类名；若不设置则使用标准实现。 |
| `path`                | 无                                         | 应用在 URL 中的访问路径，以斜杠 `/` 开头；例如 `path="/myapp"` 对应 URL `http://host:port/myapp`。若定义在 `webapps/ROOT` 下，则 `path=""` 或省略表示根上下文。 |
| `docBase`             | 无                                         | 应用实际物理路径或 WAR 文件路径；可以是相对路径（相对于 `appBase`），也可以是绝对路径。若省略，Tomcat 会根据 `path` 自动在 `appBase` 下查找同名目录或 WAR。 |
| `reloadable`          | `false`                                    | 是否允许在运行时监测 `/WEB-INF/classes` 和 `/WEB-INF/lib` 的变化并热部署；仅推荐在开发环境中使用，生产环境应设为 `false`。 |
| `antiJARLocking`      | `false`                                    | Windows 系统中防止锁定 JAR 文件；若为 `true`，Tomcat 会将 JAR 解压到临时目录运行，从而避免因锁文件而无法替换 WAR。会有额外磁盘开销。 |
| `antiResourceLocking` | `false`                                    | 是否在 Windows 平台防止资源文件（如 JSP、XML）锁定；若为 `true`，Tomcat 会将资源复制到临时目录。仅在开发环境必要。( |
| `sessionTimeout`      | `30`（分钟）                               | 会话（Session）过期时间（分钟），超过该时间未访问将失效。可在应用级别覆盖。 |
| `cookies`             | `true`                                     | 是否通过 Cookie 存储 Session ID，设为 `false` 则启用 URL 重写。 |
| `useHttpOnly`         | `true`                                     | 是否在 Set-Cookie 时加上 `HttpOnly` 属性，防止客户端脚本访问 Cookie。 |
| `swallowOutput`       | `false`                                    | 是否将应用的标准输出/错误（System.out/err）捕获并重定向到日志记录器；设为 `true` 可便于调试。 |
| `enableWelcomeFiles`  | `true`                                     | 是否启用欢迎页（`welcome-file-list` 中定义）。               |
| `reloadable`          | `false`                                    | （重复，已列出）                                             |
| `privileged`          | `false`                                    | 是否允许该 Context 调用容器内的内部 API；例如启用 JNDI、JMX 等。仅当安全需求允许时才设为 `true`。 |

> [!NOTE]
>
> 多数属性默认适合生产环境，开发环境可根据需求启用热部署等特性。



### 4.2、web.xml

全局 Web 应用默认配置，所有部署的 WAR 都会继承此配置 。

示例：

```xml
<welcome-file-list>
  <welcome-file>index.jsp</welcome-file>
  <welcome-file>index.html</welcome-file>
</welcome-file-list>
```



### 4.3、context.xml

全局应用上下文配置，定义所有 Web 应用的默认 JNDI 资源、Session 持久化、Cookie 策略等

示例：

```xml
<Context path="" docBase="" reloadable="true">
  <!-- 静态资源的缓存行为-->
  <Resources cachingAllowed="true" cacheMaxSize="512000" />
  <!-- 全局 DataSource 资源 -->
  <Resource name="jdbc/MyDB" auth="Container"
            type="javax.sql.DataSource"
            factory="org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory"
            maxTotal="100" maxIdle="30" maxWaitMillis="10000"
            driverClassName="com.mysql.cj.jdbc.Driver"
            url="jdbc:mysql://localhost:3306/mydb"
            username="dbuser" password="dbpass"/>
</Context>
```



#### 4.3.1、静态资源的缓存行为

示例：

```xml
<Context path="" docBase="" reloadable="true">
  <!-- 静态资源的缓存行为-->
  <Resources cachingAllowed="true" cacheMaxSize="512000" />    
</Context>
```

参数详解：

- `cachingAllowed`：是否允许 Tomcat 缓存静态资源（如 HTML、CSS、JS、图片等）。
- `cacheMaxSize`：最大缓存大小（以字节为单位），超出后会逐出旧资源。

> [!NOTE]
>
> 如果 `cacheMaxSize` 设置过小，启动 Tomcat 会提示：“无法将位于-的资源添加到Web应用程序-的缓存中，因为在清除过期缓存条目后可用空间仍不足 - 请考虑增加缓存的最大空间”。

使用此配置可以提升 Tomcat 性能：

- 减少对磁盘 I/O 的访问，提升静态资源读取速度。
- 减轻文件系统压力。
- 提高并发访问效率。

注意事项：

- 只对静态资源有效（不是 JSP/Servlet）。
- 若设置过小，缓存命中率低，效果有限。
- 若部署频繁，建议关闭或设置合理时间，以避免旧缓存影响部署。



### 4.4、tomcat-users.xml

配置访问 Manager、Host Manager 应用的用户与角色。

示例：

```xml
<tomcat-users>
  <role rolename="manager-gui"/>
  <role rolename="admin-gui"/>
  <user username="admin" password="s3cret" roles="manager-gui,admin-gui"/>
</tomcat-users>
```



### 4.5、catalina.properties

定义 Catalina 系统属性、共享类加载器白名单、包隔离等。



#### 4.5.1、日志编码

示例：

```properties
java.util.logging.ConsoleHandler.encoding = UTF-8
```

> [!NOTE]
>
> Windows 使用 Tomcat 控制台会出现乱码，是因为控制台窗口的编码与输出内容不一致。输出内容有两个主要来源，一个是 Tomcat 自身的输出，另一个是 Tomcat 的应用输出，只要保证这三者编码一致就没有问题。



### 4.6、logging.properties

配置 JULI 日志框架，包括日志级别、输出格式、文件轮转、日志目录等。