# 1、简介

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





# 2、安装与目录结构

## 2.1、安装

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



## 2.2、目录结构

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



# 3、部署项目

将 `myapp.war` 复制至 `webapps/`，Tomcat 自动解压部署。



# 4、配置文件

## 4.1、server.xml  

全局核心配置，定义 `<Server>`、`<Service>`、`<Connector>`、`<Engine>`、`<Host>` 等组件及其关系。

示例：

```xml
<Server port="8005" shutdown="SHUTDOWN">
  <Service name="Catalina">
    <!-- HTTP 连接器 -->
    <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
               compression="on"
               compressableMimeType="text/html,text/xml,text/plain,text/css,application/json,application/javascript"/>
    <!-- HTTPS 示例（需配置 keystoreFile, keystorePass） -->
    <!--
    <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               SSLEnabled="true"
               scheme="https" secure="true"
               keystoreFile="conf/keystore.jks"
               keystorePass="changeit" />
    -->
    <Engine name="Catalina" defaultHost="localhost">
      <Host name="localhost" appBase="webapps"
            unpackWARs="true" autoDeploy="true">
        <!-- Host 级别的 <Context> 可放在此处 -->
      </Host>
    </Engine>
  </Service>
</Server>
```

说明：

- `<Connector>`：定义协议、端口、超时、压缩等。
- `<Engine>`/`<Host>`：配置虚拟主机及部署策略，可针对多域名部署多站点。 



## 4.2、web.xml

全局 Web 应用默认配置，所有部署的 WAR 都会继承此配置 。

示例：

```xml
<welcome-file-list>
  <welcome-file>index.jsp</welcome-file>
  <welcome-file>index.html</welcome-file>
</welcome-file-list>
```



## 4.3、context.xml

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



### 4.3.1、静态资源的缓存行为

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



## 4.4、tomcat-users.xml

配置访问 Manager、Host Manager 应用的用户与角色。

示例：

```xml
<tomcat-users>
  <role rolename="manager-gui"/>
  <role rolename="admin-gui"/>
  <user username="admin" password="s3cret" roles="manager-gui,admin-gui"/>
</tomcat-users>
```



## 4.5、catalina.properties

定义 Catalina 系统属性、共享类加载器白名单、包隔离等。



### 4.5.1、日志编码

示例：

```properties
java.util.logging.ConsoleHandler.encoding = UTF-8
```

> [!NOTE]
>
> Windows 使用 Tomcat 控制台会出现乱码，是因为控制台窗口的编码与输出内容不一致。输出内容有两个主要来源，一个是 Tomcat 自身的输出，另一个是 Tomcat 的应用输出，只要保证这三者编码一致就没有问题。



## 4.6、logging.properties

配置 JULI 日志框架，包括日志级别、输出格式、文件轮转、日志目录等。