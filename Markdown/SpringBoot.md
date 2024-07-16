---
typora-copy-images-to: upload
---

# 1、SpringBoot

## 1.1、简介

SpringBoot 基于 Spring 开发，SpringBoot 本身并不提供 Spring 框架的核心特性以及扩展功能，只是用于快速、敏捷地开发新一代基于 Spring 框架的应用程序。也就是说，它并不是用来替代 Spring 的解决方案，而是和 Spring 框架紧密结合用于提升 Spring 开发者体验的工具。

SpringBoot 以约定大于配置的核心思想，默认帮我们进行了很多设置，多数 SpringBoot 应用只需要很少的 Spring 配置。Spring Boot 内嵌 Servlet 容器，降低了对环境的要求，可以命令执行语句。同时它集成了大量常用的第三方库配置（例如Redis、MongoDB、Jpa、RabbitMQ、Quartz等等），SpringBoot 应用中这些第三方库几乎可以零配置的开箱即用。

简单的来说 SpringBoot 并不是什么新的框架，它默认配置了很多框架的使用方式，就像 maven 整合了所有的 jar 包，SpringBoot 整合了所有的框架。

Spring Boot 解决的问题：

- 使编码变得简单；
- 使配置变得简单；
- 使部署变得简单；
- 使监控变得简单。

SpringBoot 的核心功能：

- 独自运行 Spring 项目：SpringBoot 可以以 jar 包的形式进行独立运行，使用 `java -jar XX.jar` 就可以成功运行；
- 内嵌 Servlet 容器：内嵌容器，使得我们可以直接执行运行项目的 main 函数，使得项目快速运行；
- 提供 starter 简化 maven 配置：SpringBoot 提供了一系列的 start-XXX 来简化 maven 依赖；
- 自动配置 Spring：SpringBoot 会根据我们项目中的类路径的 jar 包，为 jar 包的类进行自动装配 bean；
- 应用监控：SpringBoot 提供了基于 HTTP、ssh、Telnet 对运行的项目进行监控。

SpringBoot 的缺点：

- 高度集成，使用的时候不知道底层的实现；
- 由于不了解底层，导致项目出现了问题会很难排查。

四大核心：

- 自动配置：针对很多 Spring 应用程序和常见的应用功能，SpringBoot 能自动提供相关配置；
- 起步依赖：告诉 SpringBoot 需要什么功能，它就能引入相关的依赖库；
- Actuator：提供在运行时检视应用程序内部情况的能力，让你能够深入运行应用程序，一探 SpringBoot 的内部信息；
- 命令行界面：这是 SpringBoot 的可选特性，主要针对于 Groovy 语言使用。



## 1.2、微服务

微服务架构是 “新标准”，是一种架构风格。构建小型，独立且可运行的应用程序可以带来极大的灵活性，并为您的代码增加弹性。 SpringBoot 的许多专用功能使在大规模生产中构建和运行微服务变得容易。 而且不要忘记，没有 SpringCloud 简化管理并提高容错能力，就不会有完整的微服务架构。



**单体应用架构**

所谓单体应用架构（all in one）是指：我们将一个应用中的所有服务都封装在一个应用中。

无论是 ERP、CRM 或是其他什么系统，都把数据库访问、Web 访问等等各个功能放到一个 war 包内。

- 这样做的好处是：易于开发和测试；也十分方便部署；当需要扩展时，只需要将 war 复制多份，然后放到多个服务器上，再做个负载均衡就可以了。
- 单体应用架构的缺点是：哪怕要修改一个非常小的地方，都需要停掉整个服务，重新打包、部署这个应用 war 包。特别是对于一个大型应用，我们不可能把所有内容都放在一个应用内，我们如何维护、如何分工合作都是问题。



**微服务架构**

all in one 的架构方式，我们把所有的功能单元放在一个应用里面。然后我们把整个应用部署到服务器上。如果负载能力不行，我们将整个应用进行水平复制，进行扩展，然后再负载均衡。

所谓微服务架构，就是打破之前 all in one 的架构方式，把每个功能元素独立出来。把独立出来的功能元素动态组合，需要的功能元素才拿去组合。所以微服务架构是对功能元素进行复制，而没有对整个应用进行复制。

这样做的好处是：

1. 节省了调用资源。
2. 每个功能元素的服务都是一个可替换的、可独立升级的软件代码。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200402163100834.png" alt="image-20200402163100834" style="zoom: 50%;" />



# 2、第一个 SpringBoot 程序

1. 打开 IDEA，点击【新建项目】，选择 【Spring Initializr】，点确认：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200402181821619.png" alt="image-20200402181821619" />

2. Spring Web 依赖的主要作用是提供 Web 开发场景所需的底层所有依赖，引入后就可以实现 Web 场景开发，而不需要额外导入Tomcat 服务器以及其他 Web 依赖文件等：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200402181913472.png" alt="image-20200402181913472" />

3. 在 Application.java 同级目录下创建 controller 包，编写 UserController.java：

	```java
	@Controller
	public class HelloController {
	    @RequestMapping("/hello")
	    @ResponseBody
	    public String hello() {
	        return "Hello,SpringBoot!";
	    }
	}
	```

4. 运行 Application.java



> 更改端口号：在 `application.properties` 添加
>
> ```properties
> #更改项目端口号
> server.port=8080
> ```



# 3、配置文件

当我们构建完 SpringBoot 项目后，会在 resources 目录下给我们一个默认的全局配置文件 `application.properties`，这是一个空文件，因为 SpringBoot 在底层已经把配置都给我们自动配置好了，当在配置文件进行配置时，会修改 SpringBoot 自动配置的默认值。

SpringBoot 有两种类型的配置文件，application 和 bootstrap 文件，SpringBoot 会自动加载 classpath 目录下的这两个文件，文件格式为 `properties` 或 `yml` 格式。

- `*.properties` 文件是 key=value 的形式；
- `*.yml` 是 key: value 的形式，通过空格来确定层级关系，使配置文件结构更清晰。它的加载的属性是有顺序的，一般推荐用 yml 文件，看起来更加形象；但是 yml 不支持 `@PropertySource` 注解来导入配置。

bootstrap 配置文件是系统级别的，用来加载外部配置，如配置中心的配置信息，也可以用来定义系统不会变化的属性；`*.bootstatp` 文件的加载先于application文件。

application 配置文件是应用级别的，是当前应用的配置文件。



## 3.1、YAML

YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）的递归缩写。在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。

YAML 的语法和其他高级语言类似，并且可以简单表达清单、散列表，标量等数据形态。它使用空白符号缩进和大量依赖外观的特色，特别适合用来表达或编辑数据结构、各种配置文件、倾印调试内容、文件大纲（例如：许多电子邮件标题格式和YAML非常接近）。

YAML 的配置文件后缀为 **.yml**，如：**application.yml** 。



**基本语法**

- 大小写敏感；
- 使用缩进表示层级关系；
- 缩进不允许使用 tab，只允许空格（一般是两个空格）；
- 缩进的空格数不重要，只要相同层级的元素左对齐即可；
- `#` 表示注释。



**数据类型**

YAML 支持以下几种数据类型：

- 对象：键值对的集合，又称为映射（mapping）/ 哈希（hashes） / 字典（dictionary）；
- 数组：一组按次序排列的值，又称为序列（sequence） / 列表（list）；
- 纯量（scalars）：单个的、不可再分的值。



### 3.1.1、YAML 对象

对象键值对使用冒号结构表示（冒号后面要加一个空格）： 

```yaml
key: value
```

转为 json 如下：

```json
{ key: 'value' }
```



Yaml 也允许另一种写法，将所有键值对写成一个行内对象：

```yaml
key: { key1: value1, key2: value2 }
```

转为 json 如下：

```json
{ key: { key1: 'value1', key2: 'value2' } }
```

 还可以使用缩进表示层级关系：

```yaml
key: 
  child-key: value
  child-key2: value2
```



### 3.1.2、YAML 数组

以 `-` 开头的行表示构成一个数组：

```yaml
- A
- B
- C
```

转为 json 如下：

```json
['A', 'B', 'C']
```



数据结构的子成员是一个数组，则可以在该项下面缩进一个空格：

```yaml
-
  - A
  - B
  - C
```

转为 json 如下：

```json
[['A', 'B', 'C']]
```



数组也可以采用行内表示法：

```yaml
key: [ value1, value2 ]
```

转为 json 如下：

```json
{key: ['value1', 'value2']}
```



一个相对复杂的例子：

```yaml
companies:
  - id: 1
    name: company1
    price: 200W
  - id: 2
    name: company2
    price: 500W
```

意思是 `companies` 属性是一个数组，每一个数组元素又是由 `id`、`name`、`price` 三个属性构成。

数组也可以使用流式（flow）的方式表示：

```yaml
companies: [ { id: 1,name: company1,price: 200W },{ id: 2,name: company2,price: 500W } ]
```



### 3.1.3、复合结构

数组和对象可以构成复合结构，例：

```yaml
languages:
  - Ruby
  - Perl
  - Python
websites:
  YAML: yaml.org
  Ruby: ruby-lang.org
  Python: python.org
  Perl: use.perl.org
```

转换为 json 为：

```json
{ 
  languages: [ 'Ruby', 'Perl', 'Python'],
  websites: {
    YAML: 'yaml.org',
    Ruby: 'ruby-lang.org',
    Python: 'python.org',
    Perl: 'use.perl.org' 
  } 
}
```



### 3.1.4、纯量

纯量是最基本的，不可再分的值，包括：

- 字符串
- 布尔值
- 整数
- 浮点数
- Null
- 时间
- 日期

使用一个例子来快速了解纯量的基本使用：

```yaml
boolean:
  - TRUE   # true,True 都可以
  - FALSE  # false，False 都可以
float:
  - 3.14
  - 6.8523015e+5  # 可以使用科学计数法
int:
  - 123
  - 0b1010_0111_0100_1010_1110    # 二进制表示
null:
  nodeName: 'node'
  parent: ~         # 使用 ~ 表示 null
string:
  - 哈哈             # 字符串默认不使用引号表示
  - 'Hello world'   # 可以使用双引号或者单引号包裹特殊字符
  - '内容： 字符串'   # 如果字符串之中包含空格或特殊字符，需要放在引号之中。
  - newline
    newline2        # 字符串可以写成多行，从第二行开始，必须有一个单空格缩进。换行符会被转为空格。
date:
  - 2018-02-17      # 日期必须使用ISO 8601格式，即yyyy-MM-dd
datetime:
  - 2018-02-17T15:02:31+08:00    #时间使用ISO 8601格式，时间和日期之间使用T连接，最后使用+代表时区
```



### 3.1.5、引用

`&` 用来建立锚点（defaults），`<<` 表示合并到当前数据，`*` 用来引用锚点：

```yaml
defaults: &defaults
  adapter: postgres
  host: localhost

development:
  database: myapp_development
  <<: *defaults

test:
  database: myapp_test
  <<: *defaults
```

相当于：

```yaml
defaults:
  adapter: postgres
  host: localhost

development:
  database: myapp_development
  adapter: postgres
  host: localhost

test:
  database: myapp_test
  adapter: postgres
  host: localhost
```



## 3.2、获取配置文件属性

### 3.2.1、@Value

`@Value` 可修饰到任一变量获取，使用较灵活。

优点：

- 使用简单，且使用关联的链路较短
- 支持 SpEL

缺点：

- 配置名不能被有效枚举到
- 每一个配置的使用都需重新定义，使用较为麻烦
- 项目强依赖配置的定义，配置不存在则会导致项目无法启动

使用场景：

- 项目强依赖该配置的加载，想要从源头避免因配置缺失导致的未知问题
- 只想使用少数几个配置

示例：

```properties
server.port=8080
```

```java
@Configuration
public class ConfigByValueAnnotation {

    @Value("${server.port}")
    private String serverPort;

    public String getServerPort() {
        return serverPort;
    }
}
```



### 3.2.2、@ConfigurationProperties

`@ConfigurationProperties` 告诉 SpringBoot 将本类中的所有属性和配置文件中相关的配置进行绑定，参数 `prefix = "xxx"`：将配置文件中 `xxx` 下面的属性一一对应。

优点：

- 使用配置只需确定 key 的前缀即能使用，有利于批量获取场景的使用
- 因采用前缀匹配，所以在使用新的相同前缀 key 的配置时无需改动代码
- 支持 JSR303 数据校验
- 支持复杂类型封装
- 松散绑定：比如 yaml 中写的是 `last-name`，那么这个和 `lastName` 是一样的，`-` 后面跟着的字母默认是大写的

缺点：

- 使用复杂，需定义配置类或者手动创建 Bean 后引入使用
- 增加新的前缀相同 key 时可能会引入不稳定因素

使用场景：

- 需要同时使用多前缀相同 key 的配置
- 期望增加新配置但不修改代码的 properties 注入



#### 简单使用

**自定义的 Bean 使用 `@ConfigurationProperties`**

1. 编写 Dog 类：

   ```java
   public class Dog {
       private String name;
       private Integer age
   }
   ```

     Person 类使用 `@ConfigurationProperties` 注解将配置中的属性值关联到实体类上：

   ```java
   @Component
   @Data
   @ConfigurationProperties(prefix = "person")
   public class Person {
       private String name;
       private Integer age;
       private Boolean happy;
       private Date birthday;
       private Map<String, Object> map;
       private List<Object> list;
       private Dog dog;
   }
   ```

   > 实体类注意要提供属性对应的 setter 方法

   如果加入 `@ConfigurationProperties` 后爆红可以在 pom.xml 中加入下面的依赖解决：

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-configuration-processor</artifactId>
       <optional>true</optional>
   </dependency>
   ```

2. 编写 `application.yaml`：

   ```yaml
   person:
     name: qinjiang
     age: 3
     happy: false
     birthday: 2019/11/02
     map: {k1: v1, k2: v2}
     list:
       - code
       - music
       - girl
     dog:
       name: 旺财
       age: 3
   ```

3. 测试：

  ```java
  @SpringBootTest
  class Springboot02ConfigApplicationTests {
      @Autowired
      private Person person;
  
      @Test
      void contextLoads() {
          System.out.println(person);
      }
  }
  ```



**第三方 Bean 使用 `@ConfigurationProperties`**

自定义 Bean 的 `@ConfigurationProperties` 注解是写在类定义的上方，而第三方开发的 Bean 源代码不是我们自己写的，我们也不可能到源代码中去添加 `@ConfigurationProperties` 注解，所以这里需要换种方法处理。

1. 首先使用 `@Bean` 注解定义第三方 Bean：

   ```java
   @Bean
   public DruidDataSource datasource(){
       DruidDataSource ds = new DruidDataSource();
       return ds;
   }
   ```

2. 然后在 yml中 定义要绑定的属性，注意 `datasource` 此时全小写：

   ```yaml
   datasource:
     driverClassName: com.mysql.cj.jdbc.Driver
   ```

3. 最后使用 `@ConfigurationProperties` 注解为第三方 Bean 进行属性绑定，注意前缀是全小写的 `datasource`：

   ```java
   @Bean
   @ConfigurationProperties(prefix = "datasource")
   public DruidDataSource datasource(){
       DruidDataSource ds = new DruidDataSource();
       return ds;
   }
   ```

操作方式跟刚才一样，不同的是 `@ConfigurationProperties` 注解不仅能添加到类上，还可以添加到方法上，添加到类上是为 Spring 容器管理的当前类的对象绑定属性，添加到方法上是为 Spring 容器管理的当前方法的返回值对象绑定属性，本质上都一样。



**`@EnableConfigurationProperties`**

`@EnableConfigurationProperties` 将标注了 `@ConfigurationProperties` 注解的类注入到 Spring 容器中。

该注解是用来开启对 `@ConfigurationProperties` 注解的支持，也就是 `@EnableConfigurationProperties` 注解告诉 Spring 容器能支持 `@ConfigurationProperties` 注解。

一般情况下会定义两个文件，一个用于绑定 application.yml 中的配置信息，一个用于定义配置类：

1. 定义一个属性类用于绑定配置信息：

   ```java
   @Data
   @ConfigurationProperties(prefix = "spring.drools")
   public class DroolsProperties {
       // 规则文件和决策表的路径(多个目录使用逗号分割)
       private String path;
       // 更新缓存的轮询周期 - 单位：秒(默认30秒)
       private Long update;
       // 模式: stream 或 cloud(默认stream模式)
       private String mode;
       // 是否开启监听器：true = 开, false = 关闭(默认开启)
       private boolean listener;
       // 是否自动更新：true = 开, false = 关闭(默认开启)
       private boolean autoUpdate;
       // 是否开启DRL的语法检查: true = 开, false = 关闭(默认开启)
       private boolean verify;
       // 是否开启REDIS的缓存: true = 开, false = 关闭(默认开启)
       private boolean useRedis;
   }
   ```

2. 定义一个配置类用于开启文件属性的绑定功能：

   ```java
   // 配置类
   @Configuration
   // 开启属性文件绑定功能
   @EnableConfigurationProperties(DroolsProperties.class)
   public class DroolsConfig {
       @Bean
       @ConditionalOnMissingBean(name = "kieTemplate")
       public KieTemplate kieTemplate(DroolsProperties droolsProperties) {
           KieTemplate kieTemplate = new KieTemplate();
           kieTemplate.setPath(droolsProperties.getPath());
           kieTemplate.setMode(droolsProperties.getMode());
           if (droolsProperties.isAutoUpdate()) {
               // 启用自动更新
               kieTemplate.setUpdate(droolsProperties.getUpdate());
           } else {
               // 关闭自动更新
               kieTemplate.setUpdate(999999L);
           }
           kieTemplate.setListener(droolsProperties.isListener());
           kieTemplate.setVerify(droolsProperties.isVerify());
           kieTemplate.setUseRedis(droolsProperties.isUseRedis());
           return kieTemplate;
       }
   }
   ```



### 3.2.3、@PropertySource

`@PropertySource` 是 Spring 的注解，用于加载指定的属性文件的配置到 Spring 的 Environment 中，可以配合 `@Value` 和 `@ConfigurationProperties` 使用。

1. 创建一个 `application.properties`

	```properties
	name=orichalcos
	```

2. 在Person中引用

	```java
	@PropertySource(value = "classpath:application.properties")
	```

3. 给属性赋值（在属性上添加注解）

	```java
	@Value("${name}");
	```



可以组合注解使用` @PropertySource`+`@ConfigurationProperties`，`@PropertySource` 指定加载哪个文件，`@ConfigurationProperties` 指定加载文件中的哪一类属性。`@PropertySource`+`@ConfigurationProperties` 在一起解决了 `@ConfigurationProperties` 只能加载主文件内属性问题：

```java
@Configuration
@PropertySource("classpath:hellword.properties")
@ConfigurationProperties(prefix = "my")
public class HelloWorldConfig {
  private String name;
}
```



## 3.3、多环境配置

很多时候，我们项目在开发环境和生成环境的环境配置是不一样的，例如，数据库配置，在开发的时候，我们一般用测试数据库，而在生产环境的时候，我们是用正式的数据，这时候，我们可以利用 profile 在不同的环境下配置用不同的配置文件或者不同的配置。

配置文件可以放于以下四个位置，多个配置文件存在时，不同位置的文件优先级不同：

1. `file:./config/`
2. `file:./`
3. `classpath:/config`
4. `classpath:/`

如果多个配置文件含有相同的变量名，并且在使用 `${}` 进行引用时没有指定文件名，那么 Spring Boot 会按照特定的顺序查找配置文件，并使用第一个找到的变量值。这个查找顺序为： `application-{suffix}.properties` 或 `application-{suffix}.yml > application.properties` 或 `application.yml` > 其他自定义的配置文件。其中 `，{suffix}` 指的是 Spring Profiles 中的激活配置 `profile`。



### 3.3.1、active

SpringBoot 允许你按照命名约定的格式（`application-{profile}.properties`）来定义多个配置文件，然后在`application.properties` 中通过 `spring.profiles.active` 来具体激活一个或者多个配置文件，如果没有没有指定任何 profile 的配置文件的话，SpringBoot 默认会启动`application.properties`。

日常开发中一般有三个环境，分别是开发环境（dev），测试环境（test），生产环境（prod）。



**properties**

`application.properties`：

```properties
spring.profiles.active=test
```

`application-test.properties`：

```properties
server.port=8082
```

`application-dev.properties`：

```properties
server.port=8083
```



**yml 多文件配置**

`application.yml`：

```yaml
# 需要使用的配置文件
spring:
  profiles:
    active: test
```

`application-test.yml`：

```yaml
server:
  port: 8082
```

`application-dev.yml`：

```yaml
server:
  port: 8083
```



**yml 单文件配置**

yml 可以不需要创建多个文件来区分，可以直接以 `---` 来当做一个配置文件环境。

`application.yaml`:

```yaml
spring:
  profiles:
    active: test
server:
  port: 8081
  
---
spring:
  config:
    activate:
      on-profile: test
server:
  port: 8082
  
---
spring:
  config:
    activate:
      on-profile: dev
server:
  port: 8083
```

> 如果 Spring Boot 版本为 2.4 以下，请使用以下方法配置：

`application.yaml`：

```yaml
spring:
  profiles:
    active: test
server:
  port: 8081
  
---
spring:
  profiles: test
server:
  port: 8082
  
---
spring:
  profiles: dev
server:
  port: 8083
```



### 3.3.2、include

我们可以将一些公共的配置单独拿出来，然后其他文件都把这个配置给包含进去。



**yml 多文件配置**

`application.yml`：

```yaml
spring:
  profiles:
    # 导入其他配置（本处以eureka，feign为例）
    include: eureka,feign
  application:
    name: order
```

`application-eureka.yml `：

```yaml
eureka:
  client:
    service-Url:
      defaultZone: http://localhost:7001/eureka
```

`application-feign.yml `：

```yaml
feign:
  hystrix:
    enabled: true
```



**yml 单文件配置**

`application.yml`：

```yaml
spring:
  profiles:
    # 导入其他配置（本处以eureka，feign为例）
    include: eureka,feign
  application:
    name: order

---
# eureka配置
spring:
  config:
    activate:
      on-profile:: eureka
eureka:
  client:
    service-Url:
      defaultZone: http://localhost:7001/eureka
      
---
# feign配置
spring:
  config:
    activate:
      on-profile:: feign
feign:
  hystrix:
    enabled: true
```

> 如果 Spring Boot 版本为 2.4 以下，请使用以下方法配置：

`application.yml`：

```yaml
spring:
  profiles:
    # 导入其他配置（本处以eureka，feign为例）
    include: eureka,feign
  application:
    name: order

---
# eureka配置
spring:
  profiles: eureka
eureka:
  client:
    service-Url:
      defaultZone: http://localhost:7001/eureka
      
---
# feign配置
spring:
  profiles: feign
feign:
  hystrix:
    enabled: true
```



**active 和 include 的区别**

我认为主要是语意上的区别，实际使用效果基本相同。假设，项目有 2 种环境：dev、prod，我们选择激活其中一种；其中涉及到 3 种组件：https、mysql、log，我们根据环境选择包含一个或多个。active 的构件被认为是与环境有关的，include 的构件被认为是与环境无关的。

实际使用，只有下边这一处区别：

The properties from spring.profile.include override default properties. The properties from active profiles override spring.profile.include and default properties.

即：`spring.profile.include` 的属性会覆盖默认属性，`spring.profiles.active` 会覆盖 `spring.profile.include` 和默认属性。



### 3.3.3、group

Spring Boot 2.4 之后增加了 Profile 不能同时使用 `spring.profiles.active` 和 `spring.profiles.include` 的限制，但有个常用的场景，就是可能需要同时使用两个 Profile 配置， 比如线上配置了 MySQL 以及 RabbitMQ：

```yaml
spring:
  profiles:
    active: "dev"
    
---
spring:
  profiles: "dev"
  include: "mysql-dev,rabbitmq-dev"
  
---
spring:
  profiles: "mysql-dev"
  datasource:
    url: "jdbc:mysql://localhost/test"
    username: "dbuser"
    password: "dbpass"
    
---
spring:
  profiles: "rabbitmq-dev"
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "admin"
    password: "secret"
```

其中：

1. 第一个`spring.profiles.active: dev`，代表默认激活 `dev` 配置。
2. 第二段 `dev` 配置中使用了 `spring.profiles.include` 来引入其他配置信息，一个是 `dev` 的 mysql 配置，一个是 `dev` 的 rabbitmq 配置。

在 2.3 和之前版本的时候，我们通常就是这样来分组配置不同中间件的。

所以 Spring Boot 2.4 之后引入了 “组” 的概念，方便创建相应的便捷操作：

```yaml
spring:
  profiles:
    active: "dev"
    group:
      dev: "mysql-dev,rabbitmq-dev"
      prod: "mysql-prod,rabbitmq-prod"

---
spring:
  config:
    activate:
      on-profile: "mysql-dev"
  datasource:
    url: "jdbc:mysql://localhost/test"
    username: "dbuser"
    password: "dbpass"
    
---
spring:
  config:
    activate:
      on-profile: "rabbitmq-dev"
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "admin"
    password: "secret"
    
---
spring:
  config:
    activate:
      on-profile: "mysql-prod"
  datasource:
    url: "jdbc:mysql://host/test"
    username: "dbuser"
    password: "dbpass"
    
---
spring:
  config:
    activate:
      on-profile: "rabbitmq-prod"
  rabbitmq:
    host: "host"
    port: 5672
    username: "admin"
    password: "secret"
```

可以看到，在 2.4 版本的配置中，不同环境的配置定义都在第一段默认配置中了，所有的环境定义都转移到了 `spring.profiles.group` 的 key 字段（上面配置了 `dev` 和 `prod`），value 字段则代表了每个环境需要加载的不同配置分组。



### 3.3.4、Maven

Maven 本身也提供了对多环境的支持，不仅仅支持 Spring Boot 项目，只要是基于 Maven 的项目都可以配置。

Maven 对于多环境的支持在功能方面更加强大，支持 JDK 版本、资源文件、操作系统等等因素来选择环境。

创建不同环境的配置文件，分别是 `application-dev.properties`、`application-test.properties`、`application-prod.properties`。

加上默认的配置文件 `application.properties` 同样是四个配置文件。



**创建多环境配置文件**

需要将 Maven 激活的环境作用于 Spring Boot，实际还是利用了 `spring.profiles.active` 这个属性，只是现在这个属性的取值将是取值于 Maven。配置如下：

```ini
spring.profiles.active=@profile.active@
```

> `profile.active` 实际上就是一个变量，在 Maven 打包的时候指定的 `-P test` 传入的就是值

为啥 SpringBoot 中的占位符就变成 `@@` 了呢？可以从 pom 文件中看到：

1. 自己 pom 文件中的 `<parent>` 标签：

   ```xml
   <parent>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-parent</artifactId>
       <version>2.0.3.RELEASE</version>
   </parent>
   ```

2. 点击 `<version>` 标签中的 “2.0.3.RELEASE” 后，进入 spring-boot-starter-parent-2.0.3.RELEASE.pom 文件。在该 pom 文件中，`<properties>` 定义了占位符为 `@`，如下：

   ```xml
   <properties>
       <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
       <java.version>1.8</java.version>
       <resource.delimiter>@</resource.delimiter>
       <maven.compiler.source>${java.version}</maven.compiler.source>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <maven.compiler.target>${java.version}</maven.compiler.target>
   </properties>
   ```

   同时，还配置了 maven-resources-plugin 插件，该插件禁用了默认的占位符，替换为 `@`。如下：

   ```xml
   <plugin>
       <artifactId>maven-resources-plugin</artifactId>
       <configuration>
           <delimiters>
               <!-- 声明自己的占位符 -->
               <delimiter>${resource.delimiter}</delimiter>
           </delimiters>
           <!-- 禁用默认占位符 -->
           <useDefaultDelimiters>false</useDefaultDelimiters>
       </configuration>
   </plugin>
   ```

但是这个只有继承了 spring-boot-starter-parent 的 SpringBoot 项目才会默认使用 `@@` 占位符，否则 SpringBoot 配置文件中的默认占位符 `${}` 可能会与 Maven 的默认占位符 `${}` 冲突，可以使用以下插件将 SpringBoot 配置文件中的 Maven 占位符改为 `@@`：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <configuration>
                <delimiters>
                    <!--将maven占位符替换为 @ @ -->
                    <delimiter>@</delimiter>
                </delimiters>
                <!--不使用默认的变量分割符即${}-->
                <useDefaultDelimiters>false</useDefaultDelimiters>
            </configuration>
        </plugin>
    </plugins>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
             <!--maven会自动读取includes配置文件，然后解析其中的占位符（占位符是${变量名称}这样的形式）-->
            <filtering>true</filtering>
            <includes>
                <include>**/application*.yml</include>
                <include>**/application*.yaml</include>
                <include>**/application*.properties</include>
            </includes>
        </resource>
    </resources>
</build>
```



**定义激活的变量**

需要在 Maven 的 `pom.xml` 文件中定义不同环境的 `profile`，如下：

```xml
<!--定义三种开发环境-->
<profiles>
    <profile>
        <!--不同环境的唯一id-->
        <id>dev</id>
        <activation>
            <!--默认激活开发环境-->
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <!--profile.active对应application.yml中的@profile.active@-->
            <profile.active>dev</profile.active>
        </properties>
    </profile>

    <!--测试环境-->
    <profile>
        <id>test</id>
        <properties>
            <profile.active>test</profile.active>
        </properties>
    </profile>

    <!--生产环境-->
    <profile>
        <id>prod</id>
        <properties>
            <profile.active>prod</profile.active>
        </properties>
    </profile>
</profiles>
```

标签 `<profile.active>` 正是对应着配置文件中的 `@profile.active@`。

`<activeByDefault>` 标签指定了默认激活的环境，则是打包的时候不指定 `-P` 选项默认选择的环境。

以上配置完成后，将会在 IDEA 的右侧 Maven 选项卡中出现以下内容：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1.png" alt="1" />

可以选择打包的环境，然后点击 `package` 即可。

或者在项目的根目录下用命令打包，不过需要使用 `-P` 指定环境，如下：

```shell
mvn clean package package -P test
```

Maven 中的 `profile` 的激活条件还可以根据 JDK、操作系统、文件存在或者缺失来激活。这些内容都是在 `<activation>` 标签中配置，如下：

```xml
<!--activation用来指定激活方式，可以根据jdk环境，环境变量，文件的存在或缺失-->
<activation>
   <!--配置默认激活-->
  <activeByDefault>true</activeByDefault>

  <!--通过jdk版本-->
  <!--当jdk环境版本为1.8时，此profile被激活-->
  <jdk>1.8</jdk>
  <!--当jdk环境版本1.8或以上时，此profile被激活-->
  <jdk>[1.8,)</jdk>

  <!--根据当前操作系统-->
  <os>
    <name>Windows XP</name>
    <family>Windows</family>
    <arch>x86</arch>
    <version>5.1.2600</version>
  </os>
</activation>
```



**资源过滤**

如果你不配置这一步，将会在任何环境下打包都会带上全部的配置文件，但是我们可以配置只保留对应环境下的配置文件，这样安全性更高。

这一步配置很简单，只需要在 `pom.xml` 文件中指定 `<resource>` 过滤的条件即可，如下：

```xml
<build>
  <resources>
  <!--排除配置文件-->
    <resource>
      <directory>src/main/resources</directory>
      <!--先排除所有的配置文件-->
        <excludes>
          <!--使用通配符，当然可以定义多个exclude标签进行排除-->
          <exclude>application*.properties</exclude>
        </excludes>
    </resource>

    <!--根据激活条件引入打包所需的配置和文件-->
    <resource>
      <directory>src/main/resources</directory>
      <filtering>true</filtering>
      <includes>
        <include>application.yml</include>
          <!--根据maven选择环境导入配置文件-->
        <include>application-${profile.active}.yml</include>
      </includes>
    </resource>
  </resources>
</build>
```

上述配置主要分为两个方面，第一是先排除所有配置文件，第二是根据 `profile.active` 动态的引入配置文件。



# 4、JSR303 数据校验

数据的校验的重要性就不用说了，即使在前端对数据进行校验的情况下，我们还是要对传入后端的数据再进行一遍校验，避免用户绕过浏览器直接通过一些 HTTP 工具直接向后端请求一些违法数据。

最普通的做法就像下面这样。我们通过 `if/else` 语句对请求的每一个参数一一校验。

```java
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @PostMapping
    public ResponseEntity<PersonRequest> save(@RequestBody PersonRequest personRequest) {
        if (personRequest.getClassId() == null
                || personRequest.getName() == null
                || !Pattern.matches("(^Man$|^Woman$|^UGM$)", personRequest.getSex())) {

        }
        return ResponseEntity.ok().body(personRequest);
    }
}
```

但是，不太建议这样来写，这样的代码明显违背了单一职责原则。大量的非业务代码混杂在业务代码中，非常难以维护，还会导致业务层代码冗杂！

实际上，我们是可以通过一些简单的手段对上面的代码进行改进的，比如使用 JSR303~



## 4.1、简介

JSR-303 是 JAVA EE 6 中的一项子规范，叫做 Bean Validation，官方参考实现是 Hibernate Validator。

Hibernate Validator 官网介绍：

验证数据是一项常见任务，它发生在从表示层到持久层的所有应用程序层中。通常在每一层都实现相同的验证逻辑，这既耗时又容易出错。为了避免重复这些验证，开发人员经常将验证逻辑直接捆绑到域模型中，将域类与验证代码混在一起，而验证代码实际上是关于类本身的元数据。

![application layers](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/application-layers.png)

Jakarta Bean Validation 2.0 - 为实体和方法验证定义了元数据模型和 API。默认元数据源是注释，能够通过使用 XML 覆盖和扩展元数据。API 不依赖于特定的应用程序层或编程模型。它特别不依赖于 Web 或持久层，并且可用于服务器端应用程序编程以及富客户端 Swing 应用程序开发人员。

![application layers2](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/application-layers2.png)



## 4.2、快速开始

导入依赖：

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

在Spring Boot 2.3 1 之前，`spring-boot-starter-validation` 包括在了 `spring-boot-starter-web` 中，但如果使用的 Spring Boot 版本大于2.3.1，比如我当前使用的是 2.7.1，那么就必须手动添加依赖 `spring-boot-starter-validation`。



### 4.2.1、验证 Controller 的输入

**验证请求体**

验证请求体即是验证被 `@RequestBody` 注解标记的方法参数。

我们在需要验证的参数上加上 `@Valid` 注解，如果验证失败，它将抛出 `MethodArgumentNotValidException`。默认情况下，Spring 会将此异常转换为 HTTP Status 400（错误请求）：

```java
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @PostMapping
    public ResponseEntity<PersonRequest> save(@RequestBody @Valid PersonRequest personRequest) {
        return ResponseEntity.ok().body(personRequest);
    }
}
```

> 注意：这里开启 Spring 数据校验使用 `@Validated` 也可以。

使用校验注解对请求的参数进行校验：

```java
@Data
public class PersonRequest {

    @NotNull(message = "classId 不能为空")
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name 不能为空")
    private String name;

    @Pattern(regexp = "(^Man$|^Woman$|^UGM$)", message = "sex 值不在可选范围")
    @NotNull(message = "sex 不能为空")
    private String sex;

}
```

自定义异常处理器可以帮助我们捕获异常，并进行一些简单的处理：

```java
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理参数校验失败异常
     * @param exception 异常类
     * @return 响应
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean exceptionHandler(MethodArgumentNotValidException exception){
      //我们主要获取这个接口BindingResult的数据，它就包含了我们使用@RequestBody绑定的参数的所有信息，无论是校验异常错误信息还是JavaBean参数的属性信息
      BindingResult bindingResult = exception.getBindingResult();
      
      Map<String, String> errorMap = new HashMap<>();
      StringBuffer buffer = new StringBuffer();
      if(bindingResult.getFieldErrors() != null){
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
          String field = fieldError.getField();
          Object rejectedValue = fieldError.getRejectedValue();
          String defaultMessage = fieldError.getDefaultMessage();
          errorMap.put(field, defaultMessage);
          String msg = String.format("错误字段：%s, 错误值：%s, 原因：%s", field, rejectedValue, defaultMessage);
          buffer.append(msg);
          log.warn("错误字段：[{}], 错误值：[{}], 原因：[{}]", field, rejectedValue, defaultMessage);
        }
      }
      return ResultBean.error(buffer.toString(), errorMap, 400);
    }
}
```



**验证请求参数**

这些参数通常被 `@PathVariable` 以及 `@RequestParam` 标记，并且相对于 JavaBean 的参数，我们往往将其称为平铺参数。

我们在需要验证的控制器上加上 `@Validated` 注解，如果验证失败，那么会抛出 `ConstraintViolationException` 异常：

```java
@RestController
@RequestMapping("/api/person")
@Validated
public class PersonController {
    @GetMapping("/{id}")
    public ResponseEntity<Integer> getPersonByID(@PathVariable("id") @Max(value = 5, message = "超过 id 的范围了") Integer id) {
        return ResponseEntity.ok().body(id);
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> getPersonByName(@RequestParam("name") @Size(max = 6, message = "超过 name 的范围了") String name) {
        return ResponseEntity.ok().body(name);
    }
}
```

> 注意：这里用 `@Valid` 注解是不行的，因为它要求待校验的入参是 JavaBean，所以如果需要校验平铺参数，请使用 `@Validated` 开启 Spring 自动参数校验。

处理平铺参数校验失败：

```java
/**
  * 处理平铺参数校验失败异常
  * @param exception 异常类
  * @return 响应
  */
@ExceptionHandler(ConstraintViolationException.class)
public ResultBean exceptionHandler(ConstraintViolationException exception){
    log.warn(exception.getMessage());
    return ResultBean.error(exception.getMessage(), 400);
}
```



### 4.2.2、验证 Service 中的方法

我们不仅可以使用 `@Validated` 和 `@Valid` 验证 Controller 组件，也可以验证其他 Spring 管理的组件，比如 Service，不过 Controller 一般不提供接口，而 Service 一般是面向接口编程，所以需要额外注意一些情况。

在实现类中重定义接口方法的参数校验配置会失败且会报错：`javax.validation.ConstraintDeclarationException: HV000151: A method overriding another method must not redefine the parameter constraint configuration`，这个异常信息也告诉我们：参数的校验配置应该写在接口方法中，并且实现类不能修改配置，要么保持一样，要么可以不用写参数校验配置。

在非 Controller 组件中，像 Service，必须组合使用 `@Validated` 和 `@Valid`，其中 `@Validated` 作为类注解、`@Valid` 作为方法参数注解 JavaBean，这样参数校验才会生效，并且它产生的异常是 `ConstraintViolationException`，这个跟之前 Controller 中的平铺参数校验产生的异常是相同的，这个异常没有继承 `BindException` 接口，相对而言它的错误不好像 `BindException` 和 `MethodArgumentNotValidException` 那样处理：

```java
@Validated
public interface PersonService {
    PersonRequest insertPerson(@Valid PersonRequest person);
}
```

> 注意：`@Validated` 可以放在接口中，也可以放在实现类中，不过我一般放在接口中

如果方法参数是平铺参数，那么只要加 `@Validated` 就行了：

```java 
@Service
public class PersonServiceImpl implements PersonService {
    @Override 
    public PersonRequest insertPerson(@NotNull @Min(10) Integer id, @NotNull String name) { 
        return null; 
    } 
}
```

处理参数校验失败：

```java
@ExceptionHandler(ConstraintViolationException.class)
public ResultBean exceptionHandler(ConstraintViolationException exception){
    log.warn(exception.getMessage());
    return ResultBean.error(exception.getMessage(), 400);
}
```



## 4.3、级联校验和手动校验

**级联校验**

级联校验关键点在于 `@Valid`，级联校验的意思是 JavaBean 内部有其他 JavaBean 需要验证，那么这个 JavaBean 就需要加`@Valid` 注解，并且只能用 `@Valid`，因为它可以标记字段，`@Validatd` 不行：

```java
@Data
public class PersonRequest {

  @NotNull(message = "classId 不能为空")
  private String classId;

  @Pattern(regexp = "(^Man$|^Woman$|^UGM$)", message = "sex 值不在可选范围")
  @NotNull(message = "sex 不能为空")
  private String sex;

  @Valid //让InnerChild的属性也参与校验
  @NotNull
  private InnerChild child;     //内部的JavaBean

  @Getter
  @Setter
  @ToString
  public static class InnerChild {
    @Size(max = 33)
    @NotNull(message = "name 不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Positive(message = "年龄只能为正数")
    private Integer age;
  }
}
```



**手动校验**

某些场景下可能会需要我们手动校验并获得校验结果。

可以通过 `Validator` 工厂类可以获得的 `Validator` 示例，如果是在 Spring Bean 中的话，还可以通过 `@Autowired` 直接注入的方式：

```java
@Autowired
private Validator validate;
```

具体使用情况如下：

```java
/**
 * 手动校验对象
 */
@Test
public void check_person_manually() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    PersonRequest personRequest = PersonRequest.builder().sex("Man22")
            .classId("82938390").build();
    Set<ConstraintViolation<PersonRequest>> violations = validator.validate(personRequest);
    violations.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
}
```

输出结果如下：

```
sex 值不在可选范围
name 不能为空
```



## 4.4、自定义 Validator

虽然在 Spring Boot 中已经提供了非常多的预置注解，用以解决在日常开发工作中的各类内容，但是在特定情况仍然存在某些场景，无法满足需求，需要自行定义相关的 Validator。

比如我们现在多了这样一个需求：`PersonRequest` 类多了一个 `Region` 字段，`Region` 字段只能是 China、China-Taiwan、China-HongKong 这三个中的一个。

首先需要创建一个注解 `Region`：

```java
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = RegionValidator.class)
@Documented
public @interface Region {

    String message() default "Region 值不在可选范围内";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

自定义约束注解需要 `@Constraint` 修饰，必须包含 `message`、`groups`、`payload` 三个属性：

- `@Constraint`：设置自定义验证器
- `message`：定制化的提示信息，主要是从 `ValidationMessages.properties` 里提取，也可以依据实际情况进行定制
- `groups`：这里主要进行将 Validator 进行分类，不同的类 group 中会执行不同的 Validator 操作
- `payload`：主要是针对 Bean 的，使用不多。

编写自定义验证器 `RegionValidator` 实现 `ConstraintValidator` 接口，并重写 `isValid` 方法：

```java
public class RegionValidator implements ConstraintValidator<Region, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        HashSet<Object> regions = new HashSet<>();
        regions.add("China");
        regions.add("China-Taiwan");
        regions.add("China-HongKong");
        return regions.contains(value);
    }
}
```

现在你就可以使用这个注解：

```java
@Region
private String region;
```



## 4.5、常用注解总结

**常用校验注解**

JSR303 定义了 Bean Validation（校验）的标准 validation-api，并没有提供实现。Hibernate Validation 是对这个规范的实现，并且增加了 `@Email`、`@Length`、`@Range` 等注解。Spring Validation 底层依赖的就是 Hibernate Validation。

JSR 提供的校验注解:

- `@Null`：被注释的元素必须为 `null`
- `@NotNull`：被注释的元素必须不为 `null`
- `@AssertTrue`：被注释的元素必须为 `true`
- `@AssertFalse`：被注释的元素必须为 `false`
- `@Min(value)`：被注释的元素必须是一个数字，其值必须大于等于指定的最小值
- `@Max(value)`：被注释的元素必须是一个数字，其值必须小于等于指定的最大值
- `@DecimalMin(value)`：被注释的元素必须是一个数字，其值必须大于等于指定的最小值
- `@DecimalMax(value)`：被注释的元素必须是一个数字，其值必须小于等于指定的最大值
- `@Size(max=, min=)`：被注释的元素的大小必须在指定的范围内
- `@Digits (integer, fraction)`：被注释的元素必须是一个数字，其值必须在可接受的范围内
- `@Past`：被注释的元素必须是一个过去的日期
- `@Future`：被注释的元素必须是一个将来的日期
- `@Pattern(regex=,flag=)`：被注释的元素必须符合指定的正则表达式

Hibernate Validator 提供的校验注解：

- `@NotBlank(message =)`：验证字符串非 `null`，且长度必须大于 0
- `@Email`：被注释的元素必须是电子邮箱地址
- `@Length(min=,max=)`：被注释的字符串的大小必须在指定的范围内
- `@NotEmpty`：被注释的字符串的必须非空
- `@Range(min=,max=,message=)`：被注释的元素必须在合适的范围内



**`@Validated` 和 `@Valid` 的区别**

1. `@Valid`：标准 JSR-303 规范的标记型注解，用来标记验证属性和方法返回值，进行级联和递归校验

   `@Validated`：Spring 的注解，是标准 JSR-303 的一个变种（补充），提供了一个分组功能，可以在入参验证时，根据不同的分组采用不同的验证机制

2. 在 Controller 中校验方法参数时，使用 `@Valid` 和 `@Validated` 并无特殊差异（若不需要分组校验的话）

   在非 Controller 组件中校验方法参数时，`@Valid` 和 `@Validated` 必须配合使用，其中 `@Validated` 标记组件类，`@Valid` 标记方法参数，如果方法参数是平铺参数，那么只需要用 `@Validated` 标记类组件就行了

3. 相比于 `@Validated`，`@Valid` 可以用在字段级别约束，用来表示级联校验

   相比与 `@Valid`，`@Validated` 可以用于提供分组功能

4. `@Valid` 和 `@Validated` 作为类注解都有一个共同作用：开启 Spring 自动参数校验；但 `@Valid` 作为类注解只能标记 Controller 组件，而 `@Validated` 可以标记除 Controller 组件的其他组件比如 Service



**特别注意**

`@NotNull(message = “您还未上传任何图像”) MultipartFile multipartFile`：校验 MultipartFile 是否为空，因为 `@NotNull` 直接对它进行标记，某种意义上它应该算平铺参数，所以最终的异常信息是 `ConstraintViolationException`，所以应该使用 `@Validated`。



# 5、数据源

##  5.1、JDBC

首先新建一个 SpringBoot 项目

需要导入的组件：Web（必须）、JDBC API（整合所需）、MySQL Driver（连接数据库）



虽然JDBC已经能够满足大部分用户需求，但是在使用JDBC时，必须自己来管理数据库资源，如：获取PreparedStatment、设置SQL语句参数、关闭连接等步骤。

JdbcTemplate是Spring对JDBC的封装，目的是使JDBC更加易于使用。==JdbcTemplate是Spring的一部分。==Spring对数据库的操作在JDBC上面做了深层次的封装，使用Spring的注入功能，可以把DataSource注册到JdbcTemplate中。



**JdbcTemplate主要提供以下五类方法：**

- execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；
- update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；
- query方法及queryForXXX方法：用于执行查询相关语句；
- call方法：用于执行存储过程、函数相关语句。



**例：**

使用Spring默认的数据源 Hikari（后面提），所以直接配置数据库参数即可

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/newer?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT
    username: root
    password: root
```

JDBCController.java

```java
@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    //没有实体类，数据库中的东西怎么获取？
    @GetMapping("/userList")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> list_map = jdbcTemplate.queryForList(sql);
        //List里面放置的是User对象，Map里面放置的是User的详细信息，key为字段名（列名），value为值。
        return list_map;
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        String sql = "update user set u_name=?,u_password=? where id=" + id;
        //封装
        Object[] objects = new Object[2];
        objects[0] = "小明";
        objects[1] = "zzzzzz";
        jdbcTemplate.update(sql, objects);
        return "updateUser-OK";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from user where id=?";
        jdbcTemplate.update(sql, id);
        return "deleteUser-OK";
    }
}
```



**Hikari**

SpringBoot2默认的数据源从Tomcat换成了HikariCP。

HikariCP是数据库连接池的后起之秀，号称性能最好，可以完美PK掉其他的连接池，是一个高i性能的连接池，基于BoneCP做了不少的改进和优化。

那么，这么好的P是怎么做到的呢？官网详细地说明了HikariCP所做的一些优化，总结如下：

- **字节码精简**：优化代码，直到编译后的字节码最少，这样，CPU缓存可以加载更多的程序代码；
- **优化代理和拦截器**：减少代码，例如HikariCP的Statement proxy只有100行代码，只有BoneCP的十分之一；
- **自定义数组类型（FastStatementList）代替ArrayList**：避免每次get()调用都要进行range check，避免调用remove()时的从头到尾的扫描；
- **自定义集合类型（ConcurrentBag）**：提高并发读写的效率；
- **其他针对BoneCP缺陷的优化**，比如对于耗时超过一个CPU时间片的方法调用的研究（但没说具体怎么优化）。



由于Spring Boot的自动化配置机制，大部分对于数据源的配置都可以通过配置参数的方式去改变。只有一些特殊情况，比如：更换默认数据源，多数据源共存等情况才需要去修改覆盖初始化的Bean内容。

在Spring Boot自动化配置中，对于数据源的配置可以分为两类：

- 通用配置：以`spring.datasource.*`的形式存在，主要是对一些即使使用不同数据源也都需要配置的一些常规内容。比如：数据库链接地址、用户名、密码等。

	```properties
	spring.datasource.url=jdbc:mysql://localhost:3306/test
	spring.datasource.username=root
	spring.datasource.password=123456
	spring.datasource.driver-class-name=com.mysql.jdbc.Driver
	```

	

- 数据源连接池配置：以`spring.datasource.<数据源名称>.*`的形式存在，比如：Hikari的配置参数就是`spring.datasource.hikari.*`形式。

	```properties
	spring.datasource.hikari.minimum-idle=10
	spring.datasource.hikari.maximum-pool-size=20
	spring.datasource.hikari.idle-timeout=500000
	spring.datasource.hikari.max-lifetime=540000
	spring.datasource.hikari.connection-timeout=60000
	spring.datasource.hikari.connection-test-query=SELECT 1
	```

	这些配置的含义：

	- `spring.datasource.hikari.minimum-idle`: 最小空闲连接，默认值10，小于0或大于maximum-pool-size，都会重置为maximum-pool-size
	- `spring.datasource.hikari.maximum-pool-size`: 最大连接数，小于等于0会被重置为默认值10；大于零小于1会被重置为minimum-idle的值
	- `spring.datasource.hikari.idle-timeout`: 空闲连接超时时间，默认值600000（10分钟），大于等于max-lifetime且max-lifetime>0，会被重置为0；不等于0且小于10秒，会被重置为10秒。
	- `spring.datasource.hikari.max-lifetime`: 连接最大存活时间，不等于0且小于30秒，会被重置为默认值30分钟.设置应该比mysql设置的超时时间短
	- `spring.datasource.hikari.connection-timeout`: 连接超时时间：毫秒，小于250毫秒，否则被重置为默认值30秒
	- `spring.datasource.hikari.connection-test-query`: 用于测试连接是否可用的查询语句



# 6、打包

## 6.1、war

Spring Boot 提供了内置的 Tomcat、Undertow、Jetty 三种 Servlet Web 容器让我们开箱即用，可以迅速以 JAR 启动一个 Web 应用。但是在某些场景中可能还需要将我们的 Spring Boot 容器以 War 的形式进行传统的部署。这时就需要通过借助于 `SpringBootServletInitializer` 来实现。

`SpringBootServletInitializer` 是 `WebApplicationInitializer` 的实现，它从部署在 Web 容器上的传统 War 包运行 Spring Boot 应用。该类将 Servlet、Filter 和 ServletContextInitializer Bean 从应用程序上下文绑定到服务器。`SpringBootServletInitializer` 类还允许我们通过覆盖 `SpringApplicationBuilder configure(SpringApplicationBuilder application)` 方法来配置由 Servlet 容器运行的应用程序。



 Spring Boot War 部署步骤：

1. 修改打包方式为 War。

   修改 Spring Boot 项目的 `pom.xml` 文件将打包方式修改为 `war` 。

   默认打 `jar` 包：

   ```xml
   <packaging>jar</packaging>
   ```

   改为打 `war` 包：

   ```xml
   <packaging>war</packaging>
   ```

2. 排除内嵌的 Web 容器。

   默认使用内嵌 Tomcat Web 容器。如果此前使用了内嵌的 Jetty、Undertow ，请务必清除相关的 Starter 依赖。然后我们可以使用两种方式来处理：

   - 方法一

     Spring Boot 内嵌的 Tomcat 默认已经集成在 `spring-boot-starter-web` 包里，所以要排除掉它。

     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
         <exclusions>
             <exclusion>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-tomcat</artifactId>
             </exclusion>
         </exclusions>
     </dependency>
     ```

     此方式我们把 Servlet Api 依赖也排除掉了，`SpringBootServletInitializer` 需要依赖 Servlet Api ，因此要加上它（务必注意  versionNumber 版本要跟外置的 Tomcat 版本兼容）。

     ```xml
     <dependency>
          <groupId>javax.servlet</groupId>
          <artifactId>javax.servlet-api</artifactId>
          <version>${versionNumber}</version>
          <scope>provided</scope>
     </dependency>
     ```

   - 方法二

     通过引入 `spring-boot-starter-tomcat` 覆盖掉默认的内置 Tomcat 并设置作用范围（scope）为 `provided`(编译、测试)。

     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-tomcat</artifactId>
         <scope>provided</scope>
     </dependency>                      
     ```

3. 添加 War 包打包插件。

   如果用的是继承 `spring-boot-starter-parent` 的形式使用 Spring Boot，那可以跳过，因为它已经帮你配置好了。如果使用的依赖 `spring-boot-dependencies` 形式，你需要添加以下插件：

   ```xml
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-war-plugin</artifactId>
       <configuration>
           <failOnMissingWebXml>false</failOnMissingWebXml>
       </configuration>
   </plugin>
   ```

4. 实现 SpringBootServletInitializer 接口。

   启动类继承 `SpringBootServletInitializer` 类，并覆盖 `configure`。

   ```java
   @SpringBootApplication
   public class Application extends SpringBootServletInitializer {
   
       @Override
       protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
           return application.sources(Application.class);
       }
   
       public static void main(String[] args) throws Exception {
           SpringApplication.run(Application.class, args);
       }
   
   }
   ```

5. 打包。

   打 War 包方式和打 Jar 包方式一样，没有区别。

   - 在 Maven 中使用 `mvn clean package` 命令即可打包。

   - 在 Idea 中可以这样设置打包：

     ![image-20230626153638578](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230626153638578.png)



## 6.2、jar

在项目根目录下找到 pom.xml 文件，确保其中包含了 spring-boot-maven-plugin 插件，如果没有的话需要添加：

```xml 
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

在命令行中进入项目根目录，执行以下命令先清理再进行打包：

```shell
mvn clean package
```

或者使用以下命令跳过测试：

```shell
mvn clean package -DskipTests
```

执行完毕后，在项目根目录的 target 目录下会生成一个可执行的 JAR 文件，生成的 JAR 文件可以通过以下命令来运行项目：

```shell
java -jar your-project-name.jar
```



### 6.2.1、打 jar 包后乱码

**在 pom 文件中指定编码**

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <encoding>UTF-8</encoding>
    </configuration>
</plugin>
```



**在 IDE 中设置项目的编码格式（这里使用的IDEA）**

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/QQ_1720978751626.png" alt="QQ_1720978751626" style="zoom: 67%;" />



**运行 jar 时指定文件编码**

```shell
java -Dfile.encoding=utf-8 -jar your-project-name.jar
```

Windows 控制台中默认显示的编码是 GBK，此时控制台打印的中文会乱码，切换下就好了。

切换 UTF-8：

```shell
chcp 65001
```

切换回 GBK：

```shell
chcp 936
```



# 7、日志

```yaml
logging:
  #日志文件
  config: classpath:log4g2.xml
  level:
    com.alibaba.nacos.client.config.impl: WARN
    cn.jay.repository: trace
  file:
    #${file.name} 后期可以改成${spring.application.name}
    path: /log/${file.name}
```

```xml
<!-- ${sys:LOG_PATH} 读取的就是 application.yml 中的 logging.file.path 的值 -->
<Property name="LOG_HOME" value="${sys:LOG_PATH}"/>
```



# 10、发送邮件

1. **开启 SMTP**
	这里以 QQ 邮箱为例。登录 QQ 邮箱之后，点击设置，点击账户，**选择开启 IMAP/SMTP 服务**，记住 QQ 邮箱提示的授权码，后面会用到。

2. **引入依赖**
	在 SpringBoot 的 pom 文件中导入以下依赖

	```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-mail</artifactId>
	</dependency>
	```

3. **配置文件**
	在 SpringBoor 项目中引入依赖之后，即可在其配置文件中配置邮箱的参数：

	```properties
	spring.mail.host=smtp.qq.com
	spring.mail.username=happyjava@foxmail.com
	spring.mail.password=xxxxxxxx
	spring.mail.protocol=smtp
	spring.mail.default-encoding=UTF-8
	```

	因为是QQ邮箱，所以host需要使用 smtp.qq.com。如果是其它邮箱，搜索下即可找到。

	username 为邮箱账号，password 为上面步骤中提到的授权码，protocol 为使用的协议。

4. **注入JavaMailSender实例**
	邮箱参数配置完之后，即可直接注入 JavaMailSender 实例。

	```java
	@Autowired
	private JavaMailSender javaMailSender;
	```

5. **发送普通邮件**

	```java
	@Test
	public void testSend() throws MessagingException {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom("happyjava@foxmail.com");
	    message.setTo("1015030682@qq.com");
	    message.setSubject("这是标题");
	    message.setText("这是内容");
	    javaMailSender.send(message);
	}
	```

	- From 需要和配置文件中的 username 一致，否则会报错；
	- To 为邮件接收者；
	- Subject 为邮件的标题；
	- Text 为邮件的内容。

	运行方法，即可收到邮件。

6. **发送HTML邮件**
	很多时候，我们需要邮件带有美观的样式。这时候，可以使用 HTML 的样式。我们需要使用 javaMailSender 的 createMimeMessage方法，构建一个 MimeMessage，然后使用 MimeMessage 实例创建出 MimeMessageHelper。如下：

	```java
	@Test
	public void testSend() throws MessagingException {
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	    messageHelper.setSubject("标题");
	    messageHelper.setFrom("happyjava@foxmail.com");
	    messageHelper.setTo("1015030682@qq.com");
	    messageHelper.setText("<h1>标题</h1><br/><p>这是内容</p>", true);
	    javaMailSender.send(messageHelper.getMimeMessage());
	}
	```

	这里需要注意的是，setText 的时候需要传一个布尔值进去，表名需要使用HTML样式。



**项目部署到服务器之后无法发送邮件的问题**

原因：我使用的是阿里云服务器，由于阿里云因为安全考虑不开发邮件默认发送端口25，所以导致本地可以发送邮件，服务器上不能发送邮件。

解决方法：使用端口465以及 SSL 加密方式发送邮件，我的项目是 SpringBoot，所以直接在配置文件中加入下面的配置：

```properties
# Use port 465 instead of the default port 25  
spring.mail.properties.mail.smtp.socketFactory.port=465
spring.mail.properties.mail.smtp.ssl.enable=true
```



# 11、处理静态资源

静态资源，一般是网页端的：HTML文件、JavaScript 文件和图片。

虽然真实项目里，图片可以直接存储在**对象存储的存储桶内**或者直接用 **Nginx进行反代**，但是一些小的静态资源，直接 SpringBoot 规划静态资源，也是个不错的选择。

SpringBoot 内设置静态资源，或者说静态资源文件夹，主要有两种方法（均为 SpringMVC 实现）：

- 在 `application.yml`/`application.properties` 内配置。
- 设置 `Configuration 配置类`。

以上两种方法，均可实现用户访问网址，不走 Controller 层的拦截，直接进行静态文件访问：

![简单解释一下](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/f1630117a2b8420a90a8a46dcfa68f2atplv-k3u1fbpfcp-zoom-in-crop-mark1304000.webp)



## 11.1、application.yml 配置

设置 application 方法很简单，主要涉及两个配置项：

- `spring.mvc.static-path-pattern`：根据官网的描述和实际效果，可以理解为**静态文件 URL 匹配头**，也就是静态文件的 URL 地址开头。SpringBoot 默认为：`/**`。
- `spring.web.resources.static-locations`：根据官网的描述和实际效果，可以理解为**实际静态文件地址**，也就是静态文件 URL 后，匹配的实际静态文件。SpringBoot 默认为：`classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/`。

如何运作的？，这里画个简单的图：

![简单演示](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/f3d35e5cc4ca417b80f12680de0ad194tplv-k3u1fbpfcp-zoom-in-crop-mark1304000.webp)

需要注意：

- `spring.web.resources.static-locations` 是后续配置，旧版 SpringBoot 的配置项为：`spring.resources.static-locations`；在2.2.5 版本之后，旧版本配置已经失效。
- `spring.web.resources.static-locations` 有多个配置项，在 SpringBoot 编译后，会合并为一个文件。多个配置文件，使用 `,` 进行分割。
- `spring.web.resources.static-location` 仅仅允许一个配置，无法使用 `,` 进行分割，如果需要多个静态资源文件，可以使用下文的配置类方法。
- `spring.web.resources.static-locations` 可以使用 `classpath`、`file` 进行匹配。如果使用 `file`，这个时候的相对路径为项目地址（打包为 .jar 后，相对路径就是 .jar 运行地址）。



现在来写一个示例，最终效果为浏览器输入：`http://localhost:8088/SystemData/UserData/Avatar/Mintimate.jpeg` 可以直接访问项目文件下的：`/SystemData/UserData/Avatar/Mintimate.jpeg`

![就是这个文件了嗷](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/502059a0bef4412a8b184ff1a2c9c989tplv-k3u1fbpfcp-zoom-in-crop-mark1304000.webp)

配置文件为：

```yaml
spring:
  mvc:
  	# URL响应地址（Springboot默认为/**)
    static-path-pattern: /SystemData/**
  web:
    resources:
      # 静态文件地址，保留官方内容后，进行追加
      static-locations: classpath:/static,classpath:/public,classpath:/resources,classpath:/META-INF/resources,file:SystemData
```

其中，`file:SystemData` 就是映射本地文件了。

这样运行项目就可以直接访问静态资源了：

![直接访问静态资源成功](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/d952a35ea07f42be900efcb0af474c70tplv-k3u1fbpfcp-zoom-in-crop-mark1304000.webp)

这样的配置，可以说最简单且粗暴，但是灵活性差一点点：URL 响应地址只能为一项，也就是 `spring.mvc.static-path-pattern` 配置只能写一项。

这意味着，按上文设置了 `/SystemData/**` 为 URL 匹配，就不能设置第二个 `/resources/**` 这样的配置为第二静态目录。如果需要设置多个地址为静态资源目录，可以参考下文的 **设置配置类方法** 方法。



## 11.2、Configuration 配置类

写一个配置类，实现静态资源的文件夹方法很多。比如：

- 继承于 `WebMvcConfigurationSupport` 父类，并实现 `addResourceHandlers` 方法。
- 引用 `WebMvcConfigurer` 接口，并实现 `addInterceptors` 方法。

这里使用 `WebMvcConfigurationSupport` 进行实现 `addResourceHandlers`：

```java
@Override
protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    
}
```

这里的 `registry` 使用链式编程，方法为：

- `addResourceHandler`：添加 URL 响应地址目录。
- `addResourceLocations`：添加实际资源目录。

和 `application.yml` 里设置一样，支持 `classpath` 和 `file` 等关键词。



现在就来配置，最终效果为（两组同时）:

- 浏览器输入：`http://localhost:8088/SystemData/UserData/Avatar/Mintimate.jpeg` 可以直接访问项目文件下的：`/SystemData/UserData/Avatar/Mintimate.jpeg`
- 浏览器输入：`http://localhost:8088/SystemDataTest/UserData/Avatar/Mintimate.jpeg` 可以直接访问项目文件下的：`/Test/UserData/Avatar/Demo.jpeg`

![本地资源目录文件夹](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/651815b7ad4346fda348bfdaf0b866dctplv-k3u1fbpfcp-zoom-in-crop-mark1304000.webp)

添加一个配置类，并继承 `WebMvcConfigurationSupport`，实现 `addResourceHandlers` 方法，并打上 `@Configuration` 注解，使其成为配置类：

```java
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }
}
```

之后，重写内容：

```java
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    //定位到项目文件夹下的SystemData文件夹，作为个人静态资源目录
    static final String IMG_PATH = System.getProperty("user.dir") + "SystemData/";
    static final String IMG_PATH_TWO = System.getProperty("user.dir") + "/Test/";

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源银蛇
        registry.addResourceHandler("/SystemData/**")
                .addResourceLocations("file:" + IMG_PATH);
        registry.addResourceHandler("/SystemDataTest/**")
                .addResourceLocations("file:" + IMG_PATH_TWO);
        super.addResourceHandlers(registry);
    }
}
```

之后，浏览器就可以访问了：

![静态资源一](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/776d165ad82d4247b201ab7f29ff06eetplv-k3u1fbpfcp-zoom-in-crop-mark1304000.webp)

![静态资源二](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/dba2e1b5b0bb4a7289f39e7d15f91e73tplv-k3u1fbpfcp-zoom-in-crop-mark1304000.webp)



# 12、devtools 热部署

devtools 是 boot 的一个热部署工具，会监听 classpath 下的文件变动（包括类文件、属性文件、页面等），并且会立即重启应用（发生在保存时机），会重新启动应用（由于其采用的双类加载器机制，这个启动会非常快，如果发现这个启动比较慢，可以选择使用 JRebel）

双类加载器机制：将划分应用程序的类路径并分配给两个不同的类加载器来实现重启机制：

- base 类加载器：用于加载不会改变的 jar（第三方依赖的 jar）
- restart 类加载器：用于加载我们正在开发的 jar（整个项目里我们自己编写的类）。

当应用重启后，原先的 restart 类加载器被丢掉、重新 new 一个 restart 类加载器来加载这些修改过的东西，而 base 类加载器却不需要动一下。这种方法意味着应用程序的重启通常比 “冷启动” 要快得多，因为 bc 没有受到影响并且一直存在着。

devtools 可以

- 实现页面热部署，即 JSP 页面修改后会立即生效。

  在 Spring Boot 中，模板引擎的页面默认是开启缓存的，如果修改了页面的内容，则刷新页面是得不到修改后的页面的，因此如果只是想实现页面热部署可以在 application.properties 中关闭模版引擎的缓存，如下：

  - Thymeleaf 的配置：

    ```properties
    spring.thymeleaf.cache=false
    ```

  - FreeMarker 的配置：

    ```properties
    spring.freemarker.cache=false
    ```

  - Groovy 的配置：

    ```properties
    spring.groovy.template.cache=false
    ```

  - Velocity 的配置：

    ```properties
    spring.velocity.cache=false
    ```

  devtools 默认关闭了模版缓存，如果使用则不用单独配置关闭模版缓存。

- 实现类文件热部署（Java 类文件修改后不会立即生效）。Java 类热部署前提条件：类的结构不发生变化（即类方法结构不变、类属性不变）

- 实现对属性文件的热部署。



**使用**

1. 引入 spring-boot-devtools 依赖：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1676221-20200430155320785-521416484.png" alt="img" style="zoom: 80%;" />

2. 在 application.yml 中配置一下 devtools：

   ```yaml
   spring:
       devtools:
           restart:
               enabled: true  #设置开启热部署
               additional-paths: src/main/java #重启目录
               exclude: WEB-INF/**
   ```

配置了后再修改 Java 文件后也就支持了热启动，不过这种方式是属于项目重启（速度比较快的项目重启），会清空 session 中的值，也就是如果有用户登陆的话，项目重启后需要重新登陆。

默认情况下 `/META-INF/maven`、`/META-INF/resources`、`/resources`、`/static`、`/templates`、`/public` 这些文件夹下的文件修改不会使应用重启，但是会重新加载（devtools 内嵌了一个 LiveReload server，当资源发生改变时，浏览器刷新）

- 如果想改变默认的设置，可以自己设置不重启的目录：

  ```properties
  spring.devtools.restart.exclude=static/**,public/**
  ```

  这样的话，就只有这两个目录下的文件修改不会导致 restart 操作了。

- 如果要在保留默认设置的基础上还要添加其他的排除目录：

  ```properties
  spring.devtools.restart.additional-exclude=[目录]
  ```

IDEA：当我们修改了 Java 类后，IDEA 默认是不自动编译的，而 spring-boot-devtools 又是监测 classpath 下的文件发生变化才会重启应用，所以需要设置 IDEA 的自动编译：

1. File ==> Settings ==> Build, execute, deploy ==> Compiler  ==> Build Project automatically
2. ctrl + shift + alt + / ==> 选择 Registry ==> 勾上 Compiler autoMake allow when app running

