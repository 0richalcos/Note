---
typora-copy-images-to: upload
---

# 1、快速入门

## 1.1、简介

MyBatis-Plus (opens new window)（简称 MP）是一个 MyBatis（opens new window）的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。



**特性**

- 无侵入：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑。
- 损耗小：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作。
- 强大的 CRUD 操作：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求。
- 支持 Lambda 形式调用：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错。
- 支持主键自动生成：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- 支持 ActiveRecord 模式：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作。
- 支持自定义全局通用操作：支持全局通用方法注入（Write once, use anywhere）。
- 内置代码生成器：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用。
- 内置分页插件：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询。
- 分页插件支持多种数据库：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库。
- 内置性能分析插件：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询。
- 内置全局拦截插件：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作。



**支持数据库**

任何能使用 MyBatis 进行 CRUD，并且支持标准 SQL 的数据库，具体支持情况如下：

- MySQL，Oracle，DB2，H2，HSQL，SQLite，PostgreSQL，SQLServer，Phoenix，Gauss ，ClickHouse，Sybase，OceanBase，Firebird，Cubrid，Goldilocks，csiidb，informix，TDengine，redshift。
- 达梦数据库，虚谷数据库，人大金仓数据库，南大通用(华库)数据库，南大通用数据库，神通数据库，瀚高数据库，优炫数据库，星瑞格数据库。



**框架结构**

![framework](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/mybatis-plus-framework.jpg)



## 1.2、安装

全新的 MyBatis-Plus 3.0 版本基于 JDK8，提供了 lambda 形式的调用，所以安装集成 MP3.0 要求如下：

- JDK 8+
- Maven or Gradle



**Spring Boot**

Maven：

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>最新版本</version>
</dependency>
```

> 引入 MyBatis-Plus 之后请不要再次引入 MyBatis 以及 `mybatis-spring-boot-starter` 和 `MyBatis-Spring`，以避免因版本差异导致的问题。



## 1.3、配置

MyBatis-Plus 的配置异常的简单，仅需要一些简单的配置即可使用 MyBatis-Plus 的强大功能！



**Spring Boot**

配置 MapperScan 注解：

```java
@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
