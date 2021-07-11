# 1、微服务

微服务就是由一系列围绕自己业务开发的微小服务构成，他们独立部署运行在自己的进程里，基于分布式进行管理。

微服务是一种架构，这种架构是将单个的整体应用程序分割成更小的项目关联的独立的服务。一个服务通常实现一组独立的特性或功能，包含自己的业务逻辑和适配器。各个微服务之间的关联通过暴露 API 来实现。这些独立的微服务不需要部署在同一个虚拟机、同一个系统和同一个应用服务器中。



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



## 1.2、架构的演变

`[单一应用架构] `==> ` [垂直应用架构] ` ==> ` [分布式服务架构] ` ==>` [流动计算架构]||[微服务架构]` 



**All in One Application		单一架构**

起初当网站流量很小时，将所有功能都写在一个应用里面，对整个应用进行部署，以减少部署节点和成本。对于这个架构简化增删改查的工作量的数据访问框架（ORM）是关键。



**Vertical Application		垂直架构**

当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，提升效率的方法之一是将应用拆成互不相干的几个应用，以提升效率。此时，用于加速前端页面开发的Web框架（MVC）是关键。



**Distributed Service		分布式服务架构**

当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。此时，用于提高业务复用及整合的分布式服务框架（RPC）是关键。



**Elastic Computing		流动计算架构即微服务架构**

当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。此时，用于提高机器利用率的资源调度和治理中心（SOA）是关键。



# 2、SpringCloud

SpringCloud 为开发人员提供了在分布式系统中快速构建一些通用模式的工具，例如配置管理、服务发现、断路器、智能路由、微代理、控制总线。分布式系统的协调导致了锅炉板模式，开发人员使用 SpringCloud 可以快速地建立实现这些模式的服务和应用程序。

通俗理解：SpringCloud 是一个含概多个子项目的开发工具集，集合了众多的开源框架，它利用了 SpringBoot 开发的便利性实现了很多功能，如服务注册、服务注册发现、负载均衡等。SpringCloud 在整合过程中主要是针对 Netflix 开源组件的封装。SpringCloud 的出现真正的简化了分布式架构的开发。

> NetFlix 是美国的一个在线视频网站、微服务业的翘楚，它是公认的大规模生产级微服务的杰出实践者，NetFlix 的开源组件已经在它大规模分布式微服务环境中经过多年的生产实战验证，因此 SpringCloud 中很多组件都是基于NetFlix。



## 2.1、核心组件说明

- EurekaServer、Console、Nacos	服务注册中心组件
- Rabbion & OpenFeign				  	服务负载均衡 和 服务调用组件
- Hystrix & Hystrix Dashboard		   服务断路器  和  服务监控组件
- Zuul、Gateway    				 			 服务网关组件
- Config   					 						  统一配置中心组件
- Bus  													消息总线组件
- ...

![image-20200724161314786](../Images/SpringCloud/image-20200724161314786.png)



## 2.2、命名和版本选择

**SpringCloud 的命名**

SpringCloud 是一个由众多独立子项目组成的大型综合项目，原则每个子项目上有不同的发布节奏，都维护自己发布版本号。为了更好的管理SpringCloud 的版本，通过一个资源清单BOM(Bill of Materials)，为避免与子项目的发布号混淆，所以没有采用版本号的方式，而是通过命名的方式。

这些名字是按字母顺序排列的，如伦敦地铁站的名称（“天使” 是第一个版本，“布里斯顿” 是第二个版本，”卡姆登“ 是第三个版本）。当单个项目的点发布累积到一个临界量，或者其中一个项目中有一个关键缺陷需要每个人都可以使用时，发布序列将推出名称以 “.SRX” 结尾的 “服务发布”，其中 “X” 是一个数字。



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



# 2、环境搭建

说明：

- SpringBoot 2.2.5
- SpringCloud Hoxten.SR6
- Java 11
- Maven 3.8.1
- IDEA 2021.1



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
        <version>2.5.2</version>
    </parent>

    <!--定义SpringCloud使用版本号-->
    <properties>
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



