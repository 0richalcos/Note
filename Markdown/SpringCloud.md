# 1、微服务

微服务就是由一系列围绕自己业务开发的微小服务构成，他们独立部署运行在自己的进程里，基于分布式进行管理。

微服务是一种架构，这种架构是将单个的整体应用程序分割成更小的项目关联的独立的服务。一个服务通常实现一组独立的特性或功能，包含自己的业务逻辑和适配器。各个微服务之间的关联通过暴露 API 来实现。这些独立的微服务不需要部署在同一个虚拟机、同一个系统和同一个应用服务器中。

<br>

## 1.1、单体应用和微服务结构应用

**单体应用**

![image-20200708224716035](../Images/SpringCloud/image-20200708224716035.png)

优点：

- 单一架构模式在项目初期很小的时候开发方便，测试方便，部署方便，运行良好

缺点：

- 应用随着时间的推进，加入的功能越来越多，最终会变得巨大，一个项目中很有可能数百万行的代码，互相之间繁琐的 jar 包
- 久而久之，开发效率低，代码维护困难
- 如果想整体应用采用新的技术、新的框架或者语言会很困难
- 任意模块的漏洞或者错误都会影响整个应用，降低系统的可靠性

<br>

**微服务结构应用**

![image-20200723155352063](../Images/SpringCloud/image-20200723155352063.png)

优点：

- 将服务拆分成多个单一职责的小的服务，进行单独部署，服务之间通过网络进行通信
- 每个服务应该有自己单独的管理团队，高度自治
- 服务各自有自己单独的职责，服务之间松耦合，避免因一个模块的问题导致服务崩溃

缺点：

- 开发人员要处理分布式系统的复杂性
- 多服务运维难度，随着服务的增加，运维的压力也在增大
- 服务治理 和 服务监控 关键

<br>

## 1.2、架构的演变

`[单一应用架构] `==> ` [垂直应用架构] ` ==> ` [分布式服务架构] ` ==>` [流动计算架构]||[微服务架构]` 

<br>

**All in One Application		单一架构**

起初当网站流量很小时，将所有功能都写在一个应用里面，对整个应用进行部署，以减少部署节点和成本。对于这个架构简化增删改查的工作量的数据访问框架（ORM）是关键。

<br>

**Vertical Application		垂直架构**

当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，提升效率的方法之一是将应用拆成互不相干的几个应用，以提升效率。此时，用于加速前端页面开发的Web框架（MVC）是关键。

<br>

**Distributed Service		分布式服务架构**

当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。此时，用于提高业务复用及整合的分布式服务框架（RPC）是关键。

<br>

**Elastic Computing		流动计算架构即微服务架构**

当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。此时，用于提高机器利用率的资源调度和治理中心（SOA）是关键。

<br>

# 2、SpringCloud

SpringCloud 为开发人员提供了在分布式系统中快速构建一些通用模式的工具，例如配置管理、服务发现、断路器、智能路由、微代理、控制总线。分布式系统的协调导致了锅炉板模式，开发人员使用 SpringCloud 可以快速地建立实现这些模式的服务和应用程序。

通俗理解：SpringCloud 是一个含概多个子项目的开发工具集，集合了众多的开源框架，它利用了 SpringBoot 开发的便利性实现了很多功能，如服务注册、服务注册发现、负载均衡等。SpringCloud 在整合过程中主要是针对 Netflix 开源组件的封装。SpringCloud 的出现真正的简化了分布式架构的开发。

> NetFlix 是美国的一个在线视频网站、微服务业的翘楚，它是公认的大规模生产级微服务的杰出实践者，NetFlix 的开源组件已经在它大规模分布式微服务环境中经过多年的生产实战验证，因此 SpringCloud 中很多组件都是基于NetFlix。

<br>

## 2.1、核心组件说明

- EurekaServer、Consul、Nacos	  服务注册中心组件
- Rabbion & OpenFeign				  	服务负载均衡 和 服务调用组件
- Hystrix & Hystrix Dashboard		   服务断路器  和  服务监控组件
- Zuul、Gateway    				 			 服务网关组件
- Config   					 						  统一配置中心组件
- Bus  													消息总线组件
- ...

![image-20200724161314786](../Images/SpringCloud/image-20200724161314786.png)

<br>

## 2.2、命名和版本选择

**SpringCloud 的命名**

SpringCloud 是一个由众多独立子项目组成的大型综合项目，原则每个子项目上有不同的发布节奏，都维护自己发布版本号。为了更好的管理SpringCloud 的版本，通过一个资源清单BOM(Bill of Materials)，为避免与子项目的发布号混淆，所以没有采用版本号的方式，而是通过命名的方式。

这些名字是按字母顺序排列的，如伦敦地铁站的名称（“天使” 是第一个版本，“布里斯顿” 是第二个版本，”卡姆登“ 是第三个版本）。当单个项目的点发布累积到一个临界量，或者其中一个项目中有一个关键缺陷需要每个人都可以使用时，发布序列将推出名称以 “.SRX” 结尾的 “服务发布”，其中 “X” 是一个数字。

<br>

**SpringCloud 的版本选择**

- Angel 										版本基于 SpringBoot1.2.x 版本构建与 1.3 版本不兼容
- Brixton									  版本基于 SpringBoot1.3.x 版本构建与 1.2 版本不兼容
	                                                   2017 年 Brixton and Angel release 官方宣布报废
- Camden      							  版本基于 SpringBoot1.4.x 版本构建并在 1.5 版本通过测试
	                                                   2018 年 Camden release 官方宣布报废
- Dalston、Edgware 				 版本基于 SpringBoot1.5.x 版本构建，目前不能在 SpringBoot2.0.x 版本中使用
	                                                   Dalston（达尔斯顿）将于 2018 年 12 月官方宣布报废
	                                                   Edgware 将遵循 Spring Boot 1.5.x 的生命周期结束
- Finchley 			         			   版本基于 SpringBoot2.0.x 版本进行构建，不能兼容 1.x 版本
- Greenwich								版本基于 SpringBoot2.1.x 版本进行构建，不能兼容 1.x 版本
- Hoxton									  版本基于 SpringBoot2.2.x 版本进行构建

![image-20200709112427684](../Images/SpringCloud/image-20200709112427684.png)

<br>

# 2、环境搭建

说明：

- SpringBoot 2.2.5.RELEASE
- SpringCloud Hoxten.SR6
- Java 11
- Maven 3.8.1
- IDEA 2021.1

<br>

新建一个空项目，在空项目中新建一个 Maven 子项目用于版本管理（可以删除 src 目录只留 pom.xml）:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.orichalcos</groupId>
    <artifactId>springcloud_parent</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--继承SpringBoot的父项目-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.5.RELEASE</version>
    </parent>

    <!--定义SpringCloud使用版本号-->
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <spring-cloud.version>Hoxton.SR6</spring-cloud.version>
    </properties>

    <!--维护版本-->
    <dependencyManagement>
        <dependencies>
            <!--维护SpringCloud版本依赖-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

<br>

# 3、服务注册中心

所谓服务注册中心就是在整个的微服务架构中单独提出一个服务，这个服务不完成系统的任何的业务功能，仅仅用来完成对整个微服务系统的服务注册和服务发现，以及对服务健康状态的监控和管理功能。

![image-20200709124952525](../Images/SpringCloud/image-20200709124952525.png)

服务注册中心：

- 可以对所有的微服务的信息进行存储，如微服务的名称、IP、端口等
- 可以在进行服务调用时通过服务发现查询可用的微服务列表及网络地址进行服务调用
- 可以对所有的微服务进行心跳检测，如发现某实例长时间无法访问，就会从服务注册表移除该实例

SpringCloud 支持多种注册中心：Eureka、Consul、Zookeeper、以及阿里巴巴推出 Nacos。这些注册中心在本质上都是用来管理服务的注册和发现以及服务状态的检查的。

<br>

## 3.1、Eureka

Eureka 是 Netflix 开发的服务发现框架，本身是一个基于 REST 的服务。SpringCloud 将它集成在其子项目 spring-cloud-netflix 中，以实现SpringCloud 的服务注册和发现功能。

Eureka 包含两个组件：Eureka Server 和 Eureka Client。

<br>

### 3.1.1、开发 Eureka Server

1. 创建一个 Maven 项目并引入 Eureka Server 依赖

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
       </dependency>
   </dependencies>
   ```

2. 编写配置文件 application.properties

	```properties
	#执行服务端口
	server.port=8761
	#指定服务名称 唯一标识
	spring.application.name=eurekaserver
	#指定服务注册中心的地址
	eureka.client.service-url.defaultZone=http://localhost:8761/eureka
	```

3. 编写入口类 EurekaServer8761Application.java 并添加开启 Eureka Server 注解

	```java
	@SpringBootApplication
	@EnableEurekaServer
	public class EurekaServer8761Application {
	    public static void main(String[] args) {
	        SpringApplication.run(EurekaServer8761Application.class, args);
	    }
	}
	```

4. 启动项目，访问 Eureka 的服务注册页面：http://localhost:8761

	![image-20210713122030329](../Images/SpringCloud/image-20210713122030329.png)

5. 同时在项目启动的时候控制台会报错

	![image-20210713122150531](../Images/SpringCloud/image-20210713122150531.png)

	出现上述问题原因：EurekaServer 依赖内部包含了 EurekaClient

	<img src="../Images/SpringCloud/image-20210714002253735.png" alt="image-20210714002253735"  />

	Server 是一个服务注册中心，用来接受客户端的注册。Client 的特性会让当前启动的服务把自己作为 Eureka 的客户端进行服务中心的注册，当项目启动时服务注册中心还没有创建好，所以找不到服务的客户端组件就直接报错了，当启动成功服务注册中心创建好了，Client 就能进行注册并且不再报错啦！

6. 关闭 Eureka 自己注册自己

	```properties
	#不再将自己同时作为客户端注册
	eureka.client.register-with-eureka=false
	#关闭作为客户端时从Eureka Server获取服务信息
	eureka.client.fetch-registry=false
	```

7. 再次启动，当前应用就是一个单纯 Eureka Server，控制器也不再报错

<br>

### 3.1.2、开发 Eureka Client

1. 创建一个 Maven 子项目并引入 Spring Web 和 Eureka Client 依赖

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
       </dependency>
   </dependencies>
   ```

2. 编写配置文件 application.properties

   ```properties
   #执行服务端口
   server.port=8888
   #指定服务名称 唯一标识
   spring.application.name=eurekaclient
   #eureka注册中心地址
   eureka.client.service-url.defaultZone=http://localhost:8761/eureka
   ```

3. 编写入口类 EurekaClient8888Application.java 并添加开启 Eureka Client 注解

   ```java
   @SpringBootApplication
   @EnableEurekaClient
   public class EurekaClient8888Application {
       public static void main(String[] args) {
           SpringApplication.run(EurekaClient8888Application.class, args);
       }
   }
   ```

4. 启动之前的 Eureka Server，再启动 Eureka Client

   ![image-20210714001817434](../Images/SpringCloud/image-20210714001817434.png)

5. 查看 Eureka Server 的服务注册情况

   ![image-20210714001846314](../Images/SpringCloud/image-20210714001846314.png)

<br>

### 3.1.3、Eureka Server 集群

1. 首先在本地 hosts 文件中配置如下映射

	```text
	127.0.0.1 peer1
	127.0.0.1 peer2
	127.0.0.1 peer3
	```

2. 将 Eureka Server 的配置文件转为 application.yml，增加三个 `profile`，分别对应三个 Eureka Server 的配置

	```yaml
	spring:
	  application:
	    name: eruekaserver
	eureka:
	  client:
	    register-with-eureka: false
	    fetch-registry: true
	
	---
	spring:
	  profiles: peer1
	server:
	  port: 8761
	eureka:
	  instance:
	    hostname: peer1
	  client:
	    service-url:
	      defaultZone: http://peer2:8762/eureka,http://peer3:8763/eureka
	
	---
	spring:
	  profiles: peer2
	server:
	  port: 8762
	eureka:
	  instance:
	    hostname: peer2
	  client:
	    service-url:
	      defaultZone: http://peer1:8761/eureka,http://peer3:8763/eureka
	
	---
	spring:
	  profiles: peer3
	server:
	  port: 8763
	eureka:
	  instance:
	    hostname: peer3
	  client:
	    service-url:
	      defaultZone: http://peer1:8761/eureka,http://peer2:8762/eureka
	```

3. 分别启动三个注册中心，环境变量 `spring.profiles.active` 激活对应的集群配置

	![image-20210714153603951](../Images/SpringCloud/image-20210714153603951.png)

	启动之后访问 `http://peer1:8761/` 进入 `peer1` 这个注册中心，就可以看到另外两个分片 `peer2、peer3`，说明集群中有3个节点了

	![image-20210714154331476](../Images/SpringCloud/image-20210714154331476.png)

	再去访问其他两个注册中也能看到另外两个分片

	![image-20210714154403887](../Images/SpringCloud/image-20210714154403887.png)

	![image-20210714154423775](../Images/SpringCloud/image-20210714154423775.png)

	并且可以看到虽然 Eureka Client 之注册到了第一个 Eureka Server 上，但是可以看到 Eureka Server 节点之间的实例会相互同步

<br>

### 3.1.4、Eureka 自我保护机制

首先 Eureka 注册中心各个节点都是平等的，没有 ZK 中角色的概念， 即使 N-1 个节点挂掉也不会影响其他节点的正常运行。

默认情况下，如果 Eureka Server 在一定时间内（默认90秒）没有接收到某个微服务实例的心跳，Eureka Server 将会移除该实例。但是当网络分区故障发生时，微服务与 Eureka Server 之间无法正常通信，而微服务本身是正常运行的，此时不应该移除这个微服务，所以引入了自我保护机制。

> **Eureka心跳机制**
>
> 在应用启动后，节点们将会向 Eureka Server 发送心跳，默认周期为 30 秒，如果 Eureka Server 在多个心跳周期内没有接收到某个节点的心跳，Eureka Server 将会从服务注册表中把这个服务节点移除（默认90秒）。

如果在 Eureka Server 的首页看到以下这段提示，则说明 Eureka 已经进入了保护模式。 

![image-20210714185558794](../Images/SpringCloud/image-20210714185558794.png)

Eureka Server 自动进入自我保护机制，此时会出现以下几种情况：

1. Eureka Server 不再从注册列表中移除因为长时间没收到心跳而应该过期的服务
2. Eureka Server 仍然能够接受新服务的注册和查询请求，但是不会被同步到其它节点上，保证当前节点依然可用
3. 当网络稳定时，当前 Eureka Server 新的注册信息会被同步到其它节点中

因此 Eureka Server 可以很好的应对因网络故障导致部分节点失联的情况，而不会像 ZK 那样如果有一半不可用的情况会导致整个集群不可用而变成瘫痪。

<br>

**关闭自我保护**

1. Eureka Server 端：配置关闭自我保护，并按需配置 Eureka Server 清理无效节点的时间间隔

	```yaml
	eureka:
	  server: 
	    #设为false，关闭自我保护 
	    enable-self-preservation: false 
	    #清理间隔（单位毫秒，默认是60*1000） 
	    eviction-interval-timer-in-ms: 3000
	```

2.  Eureka Client 端：配置开启健康检查，并按需配置续约更新时间和到期时间

	```yaml
	eureka:
	  instance:
	    #续约更新时间间隔（默认30秒
	    lease-renewal-interval-in-seconds: 3
	    #续约到期时间（默认90秒） 
	    lease-expiration-duration-in-seconds: 10
	```

注意：更改 Eureka 更新频率将打破服务器的自我保护功能，生产环境下不建议自定义这些配置：

![image-20210714192047475](../Images/SpringCloud/image-20210714192047475.png)

> 关于 Eureka 2.x 的开源工作已经停止。作为 2.x 分支上现有工作存储库的一部分发布的代码库和工件被视为使用风险自负，在 1.x 版本项目还是活跃的。

<br>

## 3.2、Consul

Consul 是 HashiCorp 公司推出的开源工具，用于实现分布式系统的服务发现与配置。与其它分布式服务注册与发现的方案，Consul 的方案更 “一站式”，内置了服务注册与发现框架、分布一致性协议实现、健康检查、Key/Value 存储、多数据中心方案，不再需要依赖其它工具（比如 ZooKeeper 等）。使用起来也较为简单。Consul 使用 Go 语言编写，因此具有天然可移植性（支持Linux、windows和Mac OS X）；安装包仅包含一个可执行文件，方便部署，与 Docker 等轻量级容器可无缝配合。

<br>

### 3.2.1、安装 Consul

**Windows 下安装 Consul**

1. 前往 https://www.consul.io/downloads

   ![image-20210714233845045](../Images/SpringCloud/image-20210714233845045.png)

   选择自己电脑对应的版本下载

2. 解压完后只有一个脚本文件

   ![image-20210714234043504](../Images/SpringCloud/image-20210714234043504.png)

3. 使用终端切换到 consul.exe 目录并执行以下命令启动 Consul

   ![image-20210714235520532](../Images/SpringCloud/image-20210714235520532.png)

4. 访问 Consul 的 WEB 服务端口：http://localhost:8500

   ![image-20210714235639426](../Images/SpringCloud/image-20210714235639426.png)

   左上角 Consul logo 旁边的 dc1 为数据中心，可以通过 `-datacenter` 进行设置：

   ```bash
   consul agent -dev -datacenter=aa
   ```

可以通过设置环境变量，不用更改终端路径，直接执行 Consul 命令：

1. 新建一个环境变量，路径指向 consul.exe  的文件夹

   ![image-20210715000050208](../Images/SpringCloud/image-20210715000050208.png)

2. 在系统变量 Path 中将刚刚添加的变量加上

   ![image-20210715000501565](../Images/SpringCloud/image-20210715000501565.png)

3. 赶紧试试~

   ![image-20210715000842740](../Images/SpringCloud/image-20210715000842740.png)

> 如果前面使用的是 Windows CMD 开启了 Consul，记得按 Ctrl + C 关闭，然后还需重新启动 CMD

<br>

### 3.2.2、开发 Consul Client

1. 创建项目并引入 Spring Web 和 Consul 客户端依赖

	```xml
	<dependencies>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-web</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.cloud</groupId>
	        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
	    </dependency>
	</dependencies>
	```

2. 编写配置文件 application.properties

	```properties
	server.port=8889
	#注册consul服务的主机
	spring.application.name=consulclient
	spring.cloud.consul.host=localhost
	#注册consul服务的端口号
	spring.cloud.consul.port=8500
	```

3. 编辑 ConsulClient8889Application.class 入口类并添加开启客户端发现注解

	```java
	@SpringBootApplication
	@EnableDiscoveryClient
	public class ConsulClient8889Application {
	    public static void main(String[] args) {
	        SpringApplication.run(ConsulClient8889Application.class, args);
	
	    }
	}
	```

4. 启动服务查看 Consul 界面服务信息

	![image-20210715173835645](../Images/SpringCloud/image-20210715173835645.png)

5. 默认情况 Consul 监控健康是开启的，但是必须依赖健康监控依赖才能正确监控健康状态，所以直接启动会显示错误，引入健康监控依赖之后服务正常

	```xml
	<!-- 这个包是用做健康度监控的-->
	<dependency>
	  <groupId>org.springframework.boot</groupId>
	  <artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>
	```

6. 引入成功后重启项目，刷新 Consul 监控页面：

	![image-20210715175633678](../Images/SpringCloud/image-20210715175633678.png)

<br>

# 4、服务调用组件

接下来在整个微服务架构中，我们比较关心的就是服务间的服务改如何调用，有哪些调用方式？

![image-20200713095528763](../Images/SpringCloud/image-20200713095528763.png)

在 SpringCloud 中服务间调用方式主要是使用 HTTP RESTful 方式进行服务间调用

<br>

## 4.1、RestTemplate

Spring 框架提供的 RestTemplate 类可用于在应用中调用 REST 服务，它简化了与 HTTP 服务的通信方式，统一了 RESTful 的标准，封装了 HTTP 链接， 我们只需要传入url 及返回值类型即可。相较于之前常用的 HttpClient，RestTemplate 是一种更优雅的调用 RESTful 服务的方式。

<br>

**发送简单的请求**

1. 创建两个服务并注册到 Consul 注册中心中

	- users	  	代表用户服务，端口为 9999
	- order 		 代表订单服务，端口为 9998

	 注意：这里服务仅仅用来测试，没有实际业务意义

	![image-20210801002648820](../Images/SpringCloud/image-20210801002648820.png)

	![image-20210801002754756](../Images/SpringCloud/image-20210801002754756.png)

2. 创建一个 OrderController 提供服务：

	```java
	@RestController
	@RequestMapping("/order")
	public class OrderController {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	    @GetMapping
	    public String demo() {
	        LOGGER.info("order demo...");
	        return "order demo OK!!";
	    }
	}
	```

3. 创建一个 UserController 调用订单服务：

	```java
	@RestController
	@RequestMapping("/user")
	public class UserController {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	    @GetMapping
	    public String invokeDemo() {
	        LOGGER.info("user demo...");
	
	        //调用订单服务 服务地址： http://localhost:9999/order 必须GET方式 接收返回值 String 类型
	        RestTemplate restTemplate = new RestTemplate();
	        String orderResult = restTemplate.getForObject("http://localhost:9998/order", String.class);
	        
	        LOGGER.info("调用订单服务成功:{}", orderResult);
	        return "调用订单服务成功,结果为:" + orderResult;
	    }
	}
	```

4. 先启动 Consul，再启动 User 服务和 Order 服务，然后 http://localhost:9999/user 测试：

	![image-20210804004640357](../Images/SpringCloud/image-20210804004640357.png)

	![image-20210804004717424](../Images/SpringCloud/image-20210804004717424.png)

<br>

**添加请求头 Headers 和请求体 Body**

```java
RestTemplate restTemplate = new RestTemplate();
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
headers.add("Authorization", token);
// MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();
HashMap<String, String> map = new HashMap<>();
map.put("username", "Orichalcos");
map.put("password", "OriPass");
HttpEntity<HashMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
JSONObject jsonObject = restTemplate.postForObject("url", httpEntity, JSONObject.class);
```

> 当没有请求头信息时，用 `MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();`
>
> 当有请求头信息时，用 `HashMap<String, Object> map = new HashMap<>();`

上述代码设置了 `headers` 里面的 `Content-Type` 为 `application/json` ，添加了一个 `Authorization` 属性，`body` 里设置了用户名和密码。如果想要设置更复杂的 `Content_Type` 可以使用以下方法：

```java
MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
httpHeaders.setContentType(type);
```

> 如果想要获取更为完整的响应，可以使用 `postForEntity()`。
>
> getForObject 函数实际上是对 getForEntity 函数的进一步封装，如果你只关注返回的消息体的内容，对其他信息都不关注，此时可以使用getForObject。

<br>

**发送其他类型的 HTTP 请求**

```java
exchange(String url, HttpMethod method,HttpEntity requestEntity, Class responseType, Object... uriVariables)
```

参数说明：

- url：请求路径
- method：请求的方法（GET、POST、PUT等）
- requestEntity：HttpEntity 对象，封装了请求头和请求体
- responseType：返回数据类型
- uriVariables：支持 PathVariable 类型的数据。

<br>

## 4.2、OpenFeign

Feign 是一个声明式的伪 HTTP 客户端，它使得写 HTTP 客户端变得更简单。使用 Feign，只需要创建一个接口并添加注解。它具有可插拔的注解特性，可以使用 SpringMVC 的注解，可使用 Feign 注解和 JAX-RS 注解。Feign 支持可插拔的编码器和解码器。Feign 默认集成了 Ribbon，默认实现了负载均衡的效果并且 SpringCloud 为 Feign 添加了 SpringMVC 注解的支持。

<br>

### 4.2.1、OpenFeign 服务调用

1. 新建两个服务 Category、Product，并将其注册到 Consul 中（Product 可根据启动文件启动两个，用于测试负载均衡）

	![image-20210822234802589](../Images/SpringCloud/image-20210822234802589.png)

2. 在 Product 服务中提供一个被调用接口：

	```java
	@RestController
	public class ProductController {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	    @Value("${server.port}")
	    private String port;
	
	    @GetMapping("/product")
	    public String product() {
	        LOGGER.info("进入商品服务.....");
	        return "product ok，当前提供服务窗口：" + port;
	    }
	}
	```

3.  在服务调用方 Category 中添加 OpenFeign 依赖：

	```xml
	<!--OpenFeign依赖-->
	<dependency>
	    <groupId>org.springframework.cloud</groupId>
	    <artifactId>spring-cloud-starter-openfeign</artifactId>
	</dependency>
	```

4. 在 Category 启动类上添加注解，开启 OpenFeign 客户端调用：

	```java
	@SpringBootApplication
	@EnableDiscoveryClient
	//开启OpenFeign客户端调用
	@EnableFeignClients
	public class Category9995Application {
	    public static void main(String[] args) {
	        SpringApplication.run(Category9995Application.class, args);
	    }
	}
	```

5. 在 Category 服务中添加一个客户端调用接口：

	```java
	//调用商品服务接口 value：用来书写被调用服务的服务Id
	@FeignClient("product")
	public interface ProductClient {
	
	    //调用商品服务
	    @GetMapping("/product")
	    String product();
	}
	```

6.  在 Category 服务中添加 Controller，使用 FeignClient 客户端对象调用服务：

	```java
	@RestController
	public class CategoryController {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	    @Autowired
	    private ProductClient productClient;
	
	   	@GetMapping("/category")
	    public String category() {
	        String result = productClient.product();
	        LOGGER.info("category service....." + result);
	        return "category ok...." + result;
	    }
	}
	```

	![image-20210822233948054](../Images/SpringCloud/image-20210822233948054.png)

	![image-20210822235320696](../Images/SpringCloud/image-20210822235320696.png)

<br>

### 4.2.2、调用服务并传参

服务和服务之间通信，不仅仅是调用，往往在调用过程中还伴随着参数传递，接下来重点来看看 OpenFeign 在调用服务时如何传递参数。

<br>

**GET 方式调用服务传递参数**

1. 在 Product 服务中添加如下方法：

	```java
	//定义一个接收零散类型参数接口 queryString
	@GetMapping("/test1")
	public String test1(String name, Integer age) {
	    LOGGER.info("name:{} age:{}", name, age);
	    return "test1 ok，当前服务端口为：" + port;
	}
	```
	
2. 在 Category 服务 ProductClient.java 中添加接口：

	```java
	@GetMapping("/test1")
	String test1(@RequestParam("name") String name, @RequestParam("age") Integer age);
	```

3. 在 Category 服务中调用：

	```java
	@GetMapping("/test1")
	public String test1(String name, Integer age) {
	    String result = productClient.test1(name, age);
	    return "category ok....." + result;
	}
	```

4. 访问：http://localhost:9995/test1?name=Orichalcos&age=18

	![image-20210901000521366](../Images/SpringCloud/image-20210901000521366.png)

	![image-20210831235811079](../Images/SpringCloud/image-20210831235811079.png)

<br>

**POST 方式调用服务传递参数**

1. 在 Product 服务中添加如下方法：

	```java
	//定义一个接收零散类型参数接口 路径传递参数
	@GetMapping("/test2/{id}/{name}")
	public String test2(@PathVariable("id") String id, @PathVariable("name") String name) {
	    LOGGER.info("id:{} name{}", id, name);
	    return "test2 ok，当前服务端口为：" + port;
	}
	```

2. 在 Category 服务 ProductClient.java 中添加接口：

	```java
	@GetMapping("/test2/{id}/{name}")
	String test2(@PathVariable("id") String id, @PathVariable("name") String name);
	```

3. 在 Category 服务中调用：

	```java
	@GetMapping("/test2/{id}/{name}")
	public String test2(@PathVariable("id") String id, @PathVariable("name") String name) {
	    String result = productClient.test2(id, name);
	    return "category ok....." + result;
	}
	```

4. 访问：http://localhost:9995/test2/1/Orichalcos

	![image-20210901001120701](../Images/SpringCloud/image-20210901001120701.png)

	![image-20210901001139833](../Images/SpringCloud/image-20210901001139833.png)

<br>

**传递对象参数 application/json 格式**

1. 在 Product 和 Category 服务中创建一个 Product 实体类，并增加 get/set、有参无参构造函数：

	```java
	public class Product {
	    private Integer id;
	    private String name;
	    private Double price;
	    private Date bir;
	    ...
	}
	```

2. 在 Product 服务中添加如下方法：

	```java
	//定义一个接收对象类型参数接口 application/json
	@PostMapping("/test3")
	public String test3(@RequestBody Product product) {
	    LOGGER.info("product:{}", product);
	    return "test3 ok，当前服务端口为：" + port;
	}
	```

3. 在 Category 服务 ProductClient.java 中添加接口：

	```java
	@PostMapping("/test3")
	String test3(@RequestBody Product product);
	```

4. 在 Category 服务中调用：

	```java
	@GetMapping("/test3")
	public String test3() {
	    String result = productClient.test3(new Product(1, "Orichalcos", 19.9, new Date()));
	    return "category ok....." + result;
	}
	```

5. 访问：http://localhost:9995/test3

	![image-20210901003708149](../Images/SpringCloud/image-20210901003708149.png)

	![image-20210901003732017](../Images/SpringCloud/image-20210901003732017.png)

<br>

### 4.2.3、OpenFeign 超时设置

默认情况下，OpenFiegn 在进行服务调用时，要求服务提供方处理业务逻辑时间必须在 1S 内返回，如果超过 1S 没有返回则 OpenFeign 会直接报错，不会等待服务执行，但是往往在处理复杂业务逻辑是可能会超过 1S，因此需要修改 OpenFeign 的默认服务调用超时时间。

<br>

调用超时会出现如下错误

1. 在服务提供方加入线程等待阻塞模拟超时：

	```java
	@GetMapping("/product")
	public String product() throws InterruptedException {
	    LOGGER.info("进入商品服务.....");
	    Thread.sleep(2000);
	    return "product ok，当前提供服务窗口：" + port;
	}
	```

2. 进行客户端调用：

	![image-20211102000512159](../Images/SpringCloud/image-20211102000512159.png)



修改 OpenFeign 默认超时时间：

```properties
feign.client.config.PRODUCTS.connectTimeout=5000  		#配置指定服务连接超时
feign.client.config.PRODUCTS.readTimeout=5000		  	#配置指定服务等待超时
#feign.client.config.default.connectTimeout=5000  		#配置所有服务连接超时
#feign.client.config.default.readTimeout=5000			#配置所有服务等待超时
```

<br>

### 4.2.4、日志展示

往往在服务调用时我们需要详细展示 Feign 的日志，默认 Feign 在调用是并不是最详细日志输出，因此在调试程序时应该开启 Feign 的详细日志展示。Feign 对日志的处理非常灵活，可为每个 Feign 客户端指定日志记录策略，每个客户端都会创建一个 logger，默认情况下 logger 的名称是Feign 的全限定名需要注意的是，Feign 日志的打印只会DEBUG级别做出响应。

我们可以为 Feign 客户端配置各自的 logger.lever 对象，告诉 Feign 记录那些日志 logger.lever 有以下的几种值：

- NONE  不记录任何日志
- BASIC 仅仅记录请求方法、url、响应状态代码及执行时间
- HEADERS 记录Basic级别的基础上，记录请求和响应的header
- FULL 记录请求和响应的header、body和元数据

<br>

1. 开启日志展示

	```properties
	feign.client.config.PRODUCTS.loggerLevel=full 	  #开启指定服务日志展示
	#feign.client.config.default.loggerLevel=full  	  #全局开启服务日志展示
	logging.level.com.orichalcos.feignclient=debug    #指定feign调用客户端对象所在包,必须是debug级别
	```

2. 测试服务调用查看日志

	![image-20211102001811337](../Images/SpringCloud/image-20211102001811337.png)

<br>

# 5、服务负载均衡

## 5.1、Ribbon

Spring Cloud Ribbon 是一个基于 HTTP 和 TCP 的客户端负载均衡工具，它基于 Netflix Ribbon 实现。通过 Spring Cloud 的封装，可以让我们轻松地将面向服务的 REST 模版请求自动转换成客户端负载均衡的服务调用。

<br>

### 5.1.1、Ribbon 服务调用

**准备工作**

1. 项目中引入依赖：

   - 如果使用的是 Eureka Client 和 Consul Client，无须引入依赖，因为在 Eureka、Consul 中默认集成了 Ribbon 组件

   - 如果使用的 Client 中没有 Ribbon 依赖需要显式引入如下依赖

     ```xml
     <!--引入ribbon依赖-->
     <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
     </dependency>
     ```

2. 修改 Order 服务的控制器

   ```java
   @RestController
   @RequestMapping("/order")
   public class OrderController {
   
       private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
   
       @Value("${server.port}")
       private String port;
   
       @GetMapping
       public String demo() {
           LOGGER.info("order被调用，服务端口为：{}", port);
           return "order demo OK!!,服务端口为：" + port;
       }
   }
   ```

3. 修改 Order 服务的配置文件，，增加三个 `profile`

   ```yaml
   spring:
     application:
       name: order
     cloud:
       consul:
         host: localhost
         port: 8500
   
   ---
   spring:
     profiles: order9998
   server:
     port: 9998
   
   ---
   spring:
     profiles: order9997
   server:
     port: 9997
   
   ---
   spring:
     profiles: order9996
   server:
     port: 9996
   ```

4. 分别启动三个注册中心，环境变量 `spring.profiles.active` 激活对应的集群配置

   ![image-20210805002959560](../Images/SpringCloud/image-20210805002959560.png)

<br>

**RestTemplate + Ribbon 的调用方式：**

- 使用 discovery client  	进行客户端调用
- 使用 loadBalanceClient 进行客户端调用
- 使用 @loadBalanced      进行客户端调用

<br>

**DiscoveryClient**

修改 User 服务的控制器，增加以下内容

```java
@Autowired
private DiscoveryClient discoveryClient;

@GetMapping("/discoveryClient")
public String discoveryClient(){
    //获取服务列表
    List<ServiceInstance> orders = discoveryClient.getInstances("order");
    orders.forEach(order->{
        LOGGER.info("服务主机：【{}】",order.getHost());
        LOGGER.info("服务端口：【{}】",order.getPort());
        LOGGER.info("服务地址：【{}】", order.getUri());
    });
    //从服务列表中随机调取一个服务
    ServiceInstance order = orders.get(new Random().nextInt(orders.size()));
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(order.getUri() + "/order", String.class);
    return "User服务调用OK，" + result;
}
```

访问 http://localhost:9999/user/discoveryClient 查看，可重复刷新查看是否切换不同服务

<br>

**LoadBalance Client**

修改 User 服务的控制器，增加以下内容

```java
@Autowired
private LoadBalancerClient loadBalancerClient;

@GetMapping("/loadBalancerClient")
public String loadBalancerClient() {
    //根据负载均衡策略选取某一个服务调用
    ServiceInstance order = loadBalancerClient.choose("order");
    LOGGER.info("服务主机：【{}】", order.getHost());
    LOGGER.info("服务端口：【{}】", order.getPort());
    LOGGER.info("服务地址：【{}】", order.getUri());
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(order.getUri() + "/order", String.class);
    return "User服务调用OK，" + result;
}
```

访问 http://localhost:9999/user/loadBalancerClient 查看，可重复刷新查看是否切换不同服务

<br>

**@LoadBalanced**

在 User 服务中新建一个 BeansConfig.java 来提供 RestTemplate

```java
@Configuration
public class BeansConfig {
    //整合restTemplate + ribbon
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

修改 User 服务的控制器，增加以下内容

```java
//调用服务位置注入 RestTemplate
@Autowired
private RestTemplate restTemplate;

@GetMapping("/loadBalanced")
public String loadBalanced() {
    //调用：使用被调用服务的Id 替代 被调用服务的域名和端口号（下面第一个order为订单服务的端口号）
    String result = restTemplate.getForObject("http://order/order", String.class);
    return "User服务调用OK，" + result;
}
```

访问 http://localhost:9999/user/loadBalanced 查看，可重复刷新查看是否切换不同服务

<br>

### 5.1.2、Ribbon 的负载均衡策略

这里可以点进自动注入的 `LoadBalancerClient `查看其源码：

![image-20210807001718136](../Images/SpringCloud/image-20210807001718136.png)

发现并没有我们调用的 `choose()` 方法，那直接从  `choose()` 方法中点进查看，发现该方法来源自 `LoadBalancerClient` 的父接口 `ServiceInstanceChooser`：

![image-20210807001930520](../Images/SpringCloud/image-20210807001930520.png)

点击选中 `LoadBalancerClient` 按 F4 查看其实现，`ServiceInstanceChooser` 的 `choose()` 方法默认实现为 `RiibonLoadBalancerClient`：

![image-20210807002537050](../Images/SpringCloud/image-20210807002537050.png)

在 `RiibonLoadBalancerClient` 的 `choose()` 方法中可以看到它又调用了自己的 `choose()` 方法，由方法中的 `getServer()` 方法获取服务：

![image-20210807002831290](../Images/SpringCloud/image-20210807002831290.png)

继续追下去，发现 `getServer()` 又调用了 `chooseServer()`：

![image-20210807003135718](../Images/SpringCloud/image-20210807003135718.png)

再点进去就发现进入了 `ILoadBalancer` 接口了，查看方法实现，发现有三个实现：

![image-20210807003744210](../Images/SpringCloud/image-20210807003744210.png)

这里可以打个断点，然后使用 Step Into 追踪，会发现会调用 `ZoneAwareLoadBalancer` 的实现，而 `ZoneAwareLoadBalancer` 会调用父类的 `chooseServer()`：

![image-20210807004001423](../Images/SpringCloud/image-20210807004001423.png)

继续追下去，发现到了 `BaseLoadBalancer` 中，这里有许多判断，用 dbug 可以清晰地看到判断一路走到了 `this.rule.choose(key)`：

![image-20210807004405986](../Images/SpringCloud/image-20210807004405986.png)

再点下去就到了关键的接口 `IRule`:

![image-20210807005034400](../Images/SpringCloud/image-20210807005034400.png)

这个接口定义 LoadBalancer 的 “规则”，规则可以被看作是负载均衡的策略。众所周知的负载均衡策略包括轮询、基于响应时间等。而 `choose()` 上面注解解释了：通过 `key` 从 `lb.allServers` 中选择一个活的服务器。

到了这里已经很明朗了，`IRule` 就是所有负载均衡的父接口，点击这里的 `rule` 变量可以看到默认的负载均衡策略为轮询：

![image-20210807010401158](../Images/SpringCloud/image-20210807010401158.png)

可以通过 IDEA 查看 `IRule` 的所有实现：

![image-20210807010639929](../Images/SpringCloud/image-20210807010639929.png)

- RoundRobinRule 
  轮训策略：按顺序循环选择 Server
- RandomRule
  随机策略：随机选择 Server
- AvailabilityFilteringRule
  可用过滤策略：会先过滤由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问

- WeightedResponseTimeRule 
	响应时间加权策略：根据平均响应的时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高，刚启动时如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够会切换到
	
- RetryRule
	重试策略：先按照RoundRobinRule的策略获取服务，如果获取失败则在制定时间内进行重试，获取可用的服务
	
- BestAviableRule
	最低并发策略：会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务  

<br>

### 5.1.3、修改默认负载均衡策略

被调用的服务id.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule

```properties
#修改用户服务调用订单服务默认负载均衡策略，使用随机策略
order.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
```

<br>

# 6、服务断路器

## 6.1、Hystrix

 在分布式环境中，许多服务依赖项不可避免地会失败。Hystrix 是一个库，它通过添加延迟容忍和容错逻辑来帮助您控制这些分布式服务之间的交互。Hystrix 通过隔离服务之间的访问点、停止它们之间的级联故障以及提供后备选项来实现这一点，所有这些都可以提高系统的整体弹性。

<br>

### 6.1.1、服务雪崩、熔断、降级

**服务雪崩**

在微服务之间进行服务调用是由于某一个服务故障，导致级联服务故障的现象，称为雪崩效应。雪崩效应描述的是提供方不可用，导致消费方不可用并将不可用逐渐放大的过程。

如存在如下调用链路：

![image-20211103234624097](../Images/SpringCloud/image-20211103234624097.png)

而此时，Service A 的流量波动很大，流量经常会突然性增加！那么在这种情况下，就算 Service A 能扛得住请求，Service B 和Service C 未必能扛得住这突发的请求。此时，如果 Service C 因为抗不住请求，变得不可用。那么 Service B 的请求也会阻塞，慢慢耗尽 Service B 的线程资源，Service B 就会变得不可用。紧接着，Service A 也会不可用，这一过程如下图所示：

![image-20211103234721085](../Images/SpringCloud/image-20211103234721085.png)

<br>

**服务熔断**

“熔断器” 本身是一种开关装置，当某个服务单元发生故障之后，通过断路器（hystrix）的故障监控，某个异常条件被触发，直接熔断整个服务。向调用方法返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方法无法处理的异常，就保证了服务调用方的线程不会被长时间占用，避免故障在分布式系统中蔓延，乃至雪崩。如果目标服务情况好转则恢复调用。服务熔断是解决服务雪崩的重要手段。

服务熔断图示：

![image-20211103234923258](../Images/SpringCloud/image-20211103234923258.png)

<br>

**服务降级**

服务压力剧增的时候根据当前的业务情况及流量对一些服务和页面有策略的降级，以此缓解服务器的压力，以保证核心任务的进行。同时保证部分甚至大部分任务客户能得到正确的响应。也就是当前的请求处理不了或者出错了，给一个默认的返回。

服务降级图示：

![image-20211103235406735](../Images/SpringCloud/image-20211103235406735.png)

<br>

**降级和熔断总结**

共同点：

- 目的很一致，都是从可用性可靠性着想，为防止系统的整体缓慢甚至崩溃，采用的技术手段。
- 最终表现类似，对于两者来说，最终让用户体验到的是某些功能暂时不可达或不可用。
- 粒度一般都是服务级别，当然，业界也有不少更细粒度的做法，比如做到数据持久层（允许查询，不允许增删改）。
- 自治性要求很高，熔断模式一般都是服务基于策略的自动触发，降级虽说可人工干预，但在微服务架构下，完全靠人显然不可能，开关预置、配置中心都是必要手段。

不同点：

- 触发原因不太一样，服务熔断一般是某个服务（下游服务）故障引起，而服务降级一般是从整体负荷考虑；
- 管理目标的层次不太一样，熔断其实是一个框架级的处理，每个微服务都需要（无层级之分），而降级一般需要对业务有层级之分（比如降级一般是从最外围服务边缘服务开始）

熔断必会触发降级，所以熔断也是降级一种，区别在于熔断是对调用链路的保护，而降级是对系统过载的一种保护处理

<br>

### 6.2.2、服务熔断的实现

1. 添加一个微服务 springcloud_hystrix，并引入相关依赖：

	```xml
	<!--引入hystrix-->
	<dependency>
	    <groupId>org.springframework.cloud</groupId>
	    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
	</dependency>
	```

2. 配置下 application.properties 文件：

	```properties
	server.port=8990
	spring.application.name=hystrix
	#注册到consul server上
	spring.cloud.consul.host=localhost
	spring.cloud.consul.port=8500
	```

3. 在 SpringApplication 入口类上加入开启断路器的注解：

	```java
	@SpringBootApplication
	@EnableDiscoveryClient
	//开启Hystrix服务熔断
	@EnableHystrix
	public class Hystrix8990Application {
	    public static void main(String[] args) {
	        SpringApplication.run(Hystrix8990Application.class, args);
	    }
	}
	```

4. 编写一个  Controller，使用 HystrixCommand 注解实现断路

	```java
	@RestController
	public class DemoController {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
	
	    @GetMapping("/demo")
	    //指定熔断时快速返回方法
	    @HystrixCommand(fallbackMethod = "testBreakFall")
	    public String testBreak(int id) {
	        LOGGER.info("接收的Id为：{}", id);
	        if (id < 0) {
	            throw new RuntimeException("数据不合法");
	        }
	        return "接收的Id为：" + id;
	    }
	
	    public String testBreakFall(int id) {
	        return "当前数据不合法：" + id;
	    }
	}
	```

5. 测试，访问 [localhost:8990/demo?id=-1](http://localhost:8990/demo?id=-1) 和 [localhost:8990/demo?id=1](http://localhost:8990/demo?id=1)

	![image-20211107231830649](../Images/SpringCloud/image-20211107231830649.png)

<br>

**断路器的开启条件**

当多次调用错误参数后，断路器会自动打开，此时正常调用也会出发断路器：

![image-20211107232033660](../Images/SpringCloud/image-20211107232033660.png)

因为当达成 Hystrix 的打开条件后，断路器就会自动打开：

1. 当满足一定的阀值的时候（默认10秒内超过20个请求次数）
2. 当失败率达到一定的时候（默认10秒内超过50%的请求失败）

当开启的时候，所有请求都不会进行转发。一段时间之后（默认是5秒），这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。

<br>

**断路器流程**

![image-20211107232403809](../Images/SpringCloud/image-20211107232403809.png)

<br>

**默认的服务 FallBack 处理方法**

如果为每一个服务方法开发一个降级，对于我们来说可能会出现大量的代码的冗余，不利于维护，这个时候就需要加入默认服务降级处理方法

```java
@GetMapping("/default")
@HystrixCommand(defaultFallback = "defaultFallback")
public String defaultTest() {
    throw new RuntimeException("异常啦！");
}

public String defaultFallback() {
    return "此为默认响应";
}
```

<br>

### 6.2.3、服务降级的实现

1. 创建一个新的微服务：openfeignHystrix，并引入相关依赖：

	```xml
	<dependencies>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-web</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.cloud</groupId>
	        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-actuator</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.cloud</groupId>
	        <artifactId>spring-cloud-starter-openfeign</artifactId>
	    </dependency>
	</dependencies>
	```

	因为 OpfenFeign 中包含了 Hystrix 依赖，所以无需再次引入：

	![image-20211107235244640](../Images/SpringCloud/image-20211107235244640.png)

2. 配置一下 application.properties，并开启openfeign支持服务降级：

	```properties
	server.port=8991
	spring.application.name=openfeignHystrix
	#注册到consul server
	spring.cloud.consul.port=8500
	spring.cloud.consul.host=localhost
	#开启openfeign支持服务降级
	feign.hystrix.enabled=true
	```

3. 编写入口类：

	```java
	@SpringBootApplication
	@EnableDiscoveryClient
	//开启OpenFeign调用
	@EnableFeignClients
	public class OpenfeignHystrix8991Application {
	    public static void main(String[] args) {
	        SpringApplication.run(OpenfeignHystrix8991Application.class, args);
	    }
	}
	```

4. 编写 OpenFeign 客户端：

	```java
	@FeignClient("hystrix")
	public interface HystrixClient {
	    @GetMapping("/demo")
	    String demo(@RequestParam("id") int id);
	}
	```

5. 开发 fallback 处理类：实现 HystrixClient，重写的方法就是对应的 fallback 处理

	```java
	@Configuration
	public class HystrixFallBack implements HystrixClient {
	    @Override
	    public String demo(int id) {
	        return "当前服务不可用,请稍后再试,id:" + id;
	    }
	}
	```

6. 在 OpenFeign 客户端中加入 Hystrix：

	```java
	@FeignClient(value = "hystrix", fallback = HystrixFallBack.class)
	```

7. 编写一个 Controller 测试：

	```java
	@RestController
	public class DemoController {
	
	    @Autowired
	    private HystrixClient hystrixClient;
	
	    @GetMapping("/test")
	    public String test() {
	        String result = hystrixClient.demo(-1);
	        return "feignHystrix调用成功：" + result;
	    }
	}
	```

	![image-20211108000721009](../Images/SpringCloud/image-20211108000721009.png)

<br>

# 7、服务网关组件

**什么是服务网关？**

网关统一服务入口，可方便实现对平台众多服务接口进行管控，对访问服务的身份认证、防报文重放与防数据篡改、功能调用的业务鉴权、响应数据的脱敏、流量与并发控制，甚至基于 API 调用的计量或者计费等等。

网关 =  路由转发 + 过滤器。路由转发：接收一切外界请求，转发到后端的微服务上去；在服务网关中可以完成一系列的横切功能，例如权限校验、限流以及监控等，这些都可以通过过滤器完成。

<br>

**为什么需要网关？**

- 网关可以实现服务的统一管理
- 网关可以解决微服务中通用代码的冗余问题（如权限控制、流量监控、限流等）

<br>

**网关组件在微服务中架构**

![image-20211221221738529](../Images/SpringCloud/image-20211221221738529.png)

<br>

## 7.1、Gateway

这个项目提供了一个在 SpringMVC 之上构建 API 网关的库。SpringCloud Gateway 旨在提供一种简单而有效的方法来路由到 API，并为 API 提供横切关注点，比如：安全性、监控、度量和弹性。

<br>

**特性**

- 基于 SpringBoot 2.x 和 Spring WebFlux 和 Reactor 构建，响应式异步非阻塞 IO 模型
- 动态路由
- 请求过滤

<br>

### 7.1.1、开发网关动态路由

网关配置有两种方式：一种是快捷方式（Java 代码编写网关），一种是完全展开方式（配置文件方式）[推荐]。

1. 创建项目引入网关依赖：

	```xml
	<dependencies>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-web</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.cloud</groupId>
	        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-actuator</artifactId>
	    </dependency>
	    <!--引入gateway网关依赖-->
	    <dependency>
	        <groupId>org.springframework.cloud</groupId>
	        <artifactId>spring-cloud-starter-gateway</artifactId>
	    </dependency>
	</dependencies>
	```

	```java
	@SpringBootApplication
	@EnableDiscoveryClient
	public class Gateway8989Application {
	    public static void main(String[] args) {
	        SpringApplication.run(Gateway8989Application.class, args);
	    }
	}
	```

2. 编写 application.yml 配置文件：

	```yaml
	server:
	  port: 8989
	spring:
	  application:
	    name: gateway
	  cloud:
	    consul:
	      port: 8500
	      host: localhost
	    gateway:
	      routes:
	        - id: user_route                # 指定路由唯一标识
	          uri: http://localhost:9999/   # 指定路由服务的地址
	          predicates:
	            - Path=/user/**             # 指定路由规则
	
	        - id: product_route
	          uri: http://localhost:9998/
	          predicates:
	            - Path=/product/**
	```

	
