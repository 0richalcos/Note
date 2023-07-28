---
typora-copy-images-to: upload
---

# 1、简介

Apache Maven 是一个软件项目管理和理解工具。基于项目对象模型（POM）的概念，Maven 可以从中心信息中管理项目的构建、报告和文档。



# 2、多环境配置与应用

Maven 提供配置多种环境的设定，帮助开发者使用过程中快速切换环境。

![image-20230721203143302](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230721203143302.png)



## 2.1、 多环境配置

```xml
<!--定义多环境-->
<profiles>
    <!--定义具体的环境：生产环境-->
    <profile>
        <!--定义环境对应的唯一名称-->
        <id>env_dep</id>
        <!--定义环境中专用的属性值-->
        <properties>
            <jdbc.url>jdbc:mysql://127.0.0.1:3306/ssm_db</jdbc.url>
        </properties>
        <!--设置默认启动-->
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <!--当此环境激活时添加下面依赖-->
        <dependencies>
            <dependency>
                <groupId>com.github.xiaoymin</groupId>
                <artifactId>knife4j-spring-boot-starter</artifactId>
                <version>3.0.3</version>
            </dependency>
        </dependencies>
    </profile>
    <!--定义具体的环境：开发环境-->
    <profile>
        <id>env_pro</id>
        ……
    </profile>
</profiles>
```



## 2.2、使用多环境

命令：

```shell
mvn <指令> –P <环境定义id>
```

示例：

```shell
mvn install –P pro_env
```