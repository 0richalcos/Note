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



**配置选项**

通常来说，一般的简单工程，通过以上配置即可正常使用 MyBatis-Plus，同时 MyBatis-Plus 提供了大量的个性化配置来满足不同复杂度的工程：

```yaml
mybatis-plus:
  # MyBatis 配置文件位置
  configLocation:
  # MyBatis Mapper 所对应的 XML 文件位置，Maven 多模块项目的扫描路径需以 classpath*: 开头（即加载多个 jar 包下的 XML 文件）
  mapperLocations: classpath*:/mapper/**/*.xml
  # MyBaits 别名包扫描路径，通过该属性可以给包中的类注册别名
  # 注册后在 Mapper 对应的 XML 文件中可以直接使用类名，而不用使用全限定的类名（即 XML 中调用的时候不用包含包名）
  typeAliasesPackage:
  # 该配置需要和 typeAliasesPackage 一起使用，如果配置了该属性，则仅仅会扫描路径下以该类作为父类的域对象
  typeAliasesSuperType:
  # TypeHandler 扫描路径，如果配置了该属性，SqlSessionFactoryBean 会把该包下面的类注册为对应的 TypeHandler
  typeHandlersPackage:
  # 启动时是否检查 MyBatis XML 文件的存在
  checkConfigLocation: false
  # 通过该属性可指定 MyBatis 的执行器，MyBatis 的执行器总共有三种：
  # - ExecutorType.SIMPLE：该执行器类型不做特殊的事情，为每个语句的执行创建一个新的预处理语句（PreparedStatement）
  # - ExecutorType.REUSE：该执行器类型会复用预处理语句（PreparedStatement）
  # - ExecutorType.BATCH：该执行器类型会批量执行所有的更新语句
  executorType: simple
  # 指定外部化 MyBatis Properties 配置，通过该配置可以抽离配置，实现不同环境的配置部署
  configurationProperties:
  # 原生 MyBatis 所支持的配置，也可以通过 MyBatis XML 配置文件的形式进行配置
  configuration:
    # 是否开启自动驼峰命名规则（camel case）映射
    # 即从经典数据库列名 A_COLUMN（下划线命名）到经典 Java 属性名 aColumn（驼峰命名）的类似映射
    # 此属性在 MyBatis 中原默认值为 false，在 MyBatis-Plus 中此属性也将用于生成最终的 SQL 的 select body
    # 如果你的数据库命名符合规则无需使用 @TableField 注解指定数据库字段名
    mapUnderscoreToCamelCase: true
    # 默认枚举处理类，如果配置了该属性，枚举将统一使用指定处理器进行处理
    defaultEnumTypeHandler: org.apache.ibatis.type.EnumTypeHandler
    # 当设置为 true 的时候，懒加载的对象可能被任何懒属性全部加载，否则，每个属性都按需加载，需要和 lazyLoadingEnabled 一起使用
    aggressiveLazyLoading: true
    # MyBatis 自动映射策略通过该配置可指定 MyBatis 是否并且如何来自动映射数据表字段与对象的属性，总共有 3 种可选值：
    # - AutoMappingBehavior.NONE：不启用自动映射
    # - AutoMappingBehavior.PARTIAL：只对非嵌套的 resultMap 进行自动映射
    # - AutoMappingBehavior.FULL：对所有的 resultMap 都进行自动映射
    autoMappingBehavior: partial
    # MyBatis 自动映射时未知列或未知属性处理策略
    # 通过该配置可指定 MyBatis 在自动映射过程中遇到未知列或者未知属性时如何处理，总共有 3 种可选值：
    # - AutoMappingUnknownColumnBehavior.NONE：不做任何处理
    # - AutoMappingUnknownColumnBehavior.WARNING：以日志的形式打印相关警告信息
    # - AutoMappingUnknownColumnBehavior.FAILING：当作映射失败处理，并抛出异常和详细信息
    autoMappingUnknownColumnBehavior: NONE
    # Mybatis 一级缓存
    # - SESSION：session 级别缓存，同一个 session 相同查询语句不会再次查询数据库
    # - STATEMENT：关闭一级缓存
    # 单服务架构中（有且仅有只有一个程序提供相同服务），一级缓存开启不会影响业务，只会提高性能
    # 微服务架构中需要关闭一级缓存，原因：Service1 先查询数据，若之后 Service2 修改了数据，
    # 之后 Service1 又再次以同样的查询条件查询数据，因走缓存会出现查处的数据不是最新数据
    localCacheScope: SESSION
    # 开启 Mybatis 二级缓存
    cacheEnabled: true
    # 指定当结果集中值为 null 的时候是否调用映射对象的 Setter（Map 对象时为 put）方法，
    # 通常运用于有 Map.keySet() 依赖或 null 值初始化的情况
    # 通俗的讲，即 MyBatis 在使用 resultMap 来映射查询结果中的列，如果查询结果中包含空值的列，
    # 则 MyBatis 在映射的时候，不会映射这个字段，这就导致在调用到该字段的时候由于没有映射，取不到而报空指针异常
    callSettersOnNulls: false
    # 指定一个提供 Configuration 实例的工厂类，该工厂生产的实例将用来加载已经被反序列化对象的懒加载属性值，
    # 其必须包含一个签名方法 static Configuration getConfiguration()
    configurationFactory:
  # MyBatis-Plus 全局策略配置
  globalConfig:
    # 是否控制台打印 mybatis-plus 的 LOGO
    banner: true
    # 是否初始化 SqlRunner（com.baomidou.mybatisplus.extension.toolkit.SqlRunner）
    enableSqlRunner: false
    # MyBatis-Plus 全局策略中的 DB 策略配置
    dbConfig:
      # 全局默认主键类型
      idType: ASSIGN_ID
      # 表名前缀
      tablePrefix:
      # schema
      schema:
      # 字段 format（对主键无效），例: %s
      columnFormat:
      # 字段 format（3.5.3.2 +），例: %s
      tableFormat:
      # entity 的字段（property）的 format，只有在 column as property 这种情况下生效（对主键无效），例: %s
      propertyFormat:
      # 表名是否使用驼峰转下划线命名，只对表名生效
      tableUnderline: true
      # 大写命名，对表名和字段名均生效
      capitalMode: false
      # 全局的 entity 的逻辑删除字段属性名（逻辑删除下有效）
      logicDeleteField:
      # 逻辑已删除值（逻辑删除下有效）
      logicDeleteValue: 1
      # 逻辑未删除值（逻辑删除下有效）
      logicNotDeleteValue: 0
      # 字段验证策略之 insert，在 insert 的时候的字段验证策略
      insertStrategy: NOT_NULL
      # 字段验证策略之 update，在 update 的时候的字段验证策略
      updateStrategy: NOT_NULL
      # 字段验证策略之 select，在 select 的时候的字段验证策略，即 wrapper 根据内部 entity 生成的 where 条件
      whereStrategy: NOT_NULL
```

