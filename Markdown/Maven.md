---
typora-copy-images-to: upload
---

# 1、简介

Apache Maven 是一个软件项目管理和理解工具。基于项目对象模型（POM）的概念，Maven 可以从中心信息中管理项目的构建、报告和文档。



# 2、多环境配置与应用

Maven 提供配置多种环境的设定，帮助开发者使用过程中快速切换环境。

![image-20230721203143302](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230721203143302.png)



## 2.1、多环境配置

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



# 3、资源过滤

`nonFilteredFileExtensions` 是 Apache Maven 的一个 POM 构建选项，用于通过指定一组文件后缀名，告诉 Maven 在执行资源过滤时不需要对这些文件进行处理。

在项目中，Maven 会将资源文件（如文本文件、配置文件、XML 文件等）拷贝到目标目录，并在拷贝时对其中的变量进行替换，例如将代码中某个环境变量的值替换为具体的值，这个过程叫做资源过滤。

有些文件，例如图片、音频和视频等二进制文件，它们没有需要替换的内容，因此不需要进行资源过滤，这时候就可以使用 `nonFilteredFileExtensions` 配置来告诉 Maven 不需要对这些文件进行处理，以加快构建速度。

下面是一个示例配置：

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <configuration>
        <nonFilteredFileExtensions>
            <!-- 避免font文件的二进制文件格式压缩破坏 -->
            <nonFilteredFileExtension>woff</nonFilteredFileExtension>
            <nonFilteredFileExtension>woff2</nonFilteredFileExtension>
            <nonFilteredFileExtension>eot</nonFilteredFileExtension>
            <nonFilteredFileExtension>ttf</nonFilteredFileExtension>
            <nonFilteredFileExtension>svg</nonFilteredFileExtension>
            <nonFilteredFileExtension>lic</nonFilteredFileExtension>
            <!--避免xls文件打包损坏-->
            <nonFilteredFileExtension>xls</nonFilteredFileExtension>
        </nonFilteredFileExtensions>
    </configuration>
</plugin>
```

