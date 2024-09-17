---
typora-copy-images-to: upload
---

# 1、简介

XXL-JOB 是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用。



**XXL-JOB 的设计思想**

- 将调度行为抽象形成 “调度中心” 公共平台，而平台自身并不承担业务逻辑，“调度中心” 负责发起调度请求。
- 将任务抽象成分散的 JobHandler，交由 “执行器” 统一管理。
- “执行器” 负责接收调度请求并执行对应的 JobHandler 中业务逻辑。
- 因此，“调度” 和 “任务” 两部分可以相互解耦，提高系统整体稳定性和扩展性。



**特性**

1. 简单：支持通过 Web 页面对任务进行 CRUD 操作，操作简单，一分钟上手。
2. 动态：支持动态修改任务状态、启动/停止任务，以及终止运行中任务，即时生效。
3. 调度中心 HA（中心式）：调度采用中心式设计，“调度中心” 自研调度组件并支持集群部署，可保证调度中心 HA。
4. 执行器 HA（分布式）：任务分布式执行，任务 ”执行器” 支持集群部署，可保证任务执行 HA。
5. 注册中心：执行器会周期性自动注册任务, 调度中心将会自动发现注册的任务并触发执行。同时，也支持手动录入执行器地址。
6. 弹性扩容缩容：一旦有新执行器机器上线或者下线，下次调度时将会重新分配任务。
7. 触发策略：提供丰富的任务触发策略，包括：Cron 触发、固定间隔触发、固定延时触发、API（事件）触发、人工触发、父子任务触发。
8. 调度过期策略：调度中心错过调度时间的补偿处理策略，包括：忽略、立即补偿触发一次等。
9. 阻塞处理策略：调度过于密集执行器来不及处理时的处理策略，策略包括：单机串行（默认）、丢弃后续调度、覆盖之前调度。
10. 任务超时控制：支持自定义任务超时时间，任务运行超时将会主动中断任务。
11. 任务失败重试：支持自定义任务失败重试次数，当任务失败时将会按照预设的失败重试次数主动进行重试。其中分片任务支持分片粒度的失败重试。
12. 任务失败告警：默认提供邮件方式失败告警，同时预留扩展接口，可方便的扩展短信、钉钉等告警方式。
13. 路由策略：执行器集群部署时提供丰富的路由策略，包括：第一个、最后一个、轮询、随机、一致性HASH、最不经常使用、最近最久未使用、故障转移、忙碌转移等。
14. 分片广播任务：执行器集群部署时，任务路由策略选择”分片广播”情况下，一次任务调度将会广播触发集群中所有执行器执行一次任务，可根据分片参数开发分片任务。
15. 动态分片：分片广播任务以执行器为维度进行分片，支持动态扩容执行器集群从而动态增加分片数量，协同进行业务处理。在进行大数据量业务操作时可显著提升任务处理能力和速度。
16. 故障转移：任务路由策略选择”故障转移”情况下，如果执行器集群中某一台机器故障，将会自动Failover切换到一台正常的执行器发送调度请求。
17. 任务进度监控：支持实时监控任务进度。
18. Rolling 实时日志：支持在线查看调度结果，并且支持以 Rolling 方式实时查看执行器输出的完整的执行日志。
19. GLUE：提供 Web IDE，支持在线开发任务逻辑代码，动态发布，实时编译生效，省略部署上线的过程。支持 30 个版本的历史版本回溯。
20. 脚本任务：支持以 GLUE 模式开发和运行脚本任务，包括 Shell、Python、NodeJS、PHP、PowerShell 等类型脚本。
21. 命令行任务：原生提供通用命令行任务 Handler（Bean 任务，”CommandJobHandler”）。业务方只需要提供命令行即可。
22. 任务依赖：支持配置子任务依赖，当父任务执行结束且执行成功后将会主动触发一次子任务的执行, 多个子任务用逗号分隔。
23. 一致性：“调度中心” 通过 DB 锁保证集群分布式调度的一致性, 一次任务调度只会触发一次执行。
24. 自定义任务参数：支持在线配置调度任务入参，即时生效。
25. 调度线程池：调度系统多线程触发调度运行，确保调度精确执行，不被堵塞。
26. 数据加密：调度中心和执行器之间的通讯进行数据加密，提升调度信息安全性。
27. 邮件报警：任务失败时支持邮件报警，支持配置多邮件地址群发报警邮件。
28. 推送 Maven 中央仓库：将会把最新稳定版推送到 Maven 中央仓库，方便用户接入和使用。
29. 运行报表：支持实时查看运行数据，如任务数量、调度次数、执行器数量等。以及调度报表，如调度日期分布图，调度成功分布图等。
30. 全异步：任务调度流程全异步化设计实现，如异步调度、异步运行、异步回调等，有效对密集调度进行流量削峰，理论上支持任意时长任务的运行。
31. 跨语言：调度中心与执行器提供语言无关的 RESTful API 服务，第三方任意语言可据此对接调度中心或者实现执行器。除此之外，还提供了 “多任务模式” 和 “httpJobHandler” 等其他跨语言方案。
32. 国际化：调度中心支持国际化设置，提供中文、英文两种可选语言，默认为中文。
33. 容器化：提供官方 Docker 镜像，并实时更新推送 DockerHub，进一步实现产品开箱即用。
34. 线程池隔离：调度线程池进行隔离拆分，慢任务自动降级进入 ”Slow” 线程池，避免耗尽调度线程，提高系统稳定性。
35. 用户管理：支持在线管理系统用户，存在管理员、普通用户两种角色。
36. 权限控制：执行器维度进行权限控制，管理员拥有全量权限，普通用户需要分配执行器权限后才允许相关操作。



# 2、快速开始

## 2.1、搭建调度中心

**下载源码**

下载源码导入 IDEA，源码地址：https://gitee.com/xuxueli0323/xxl-job.git：

- doc：xxl-job 的文档资料，包括了[数据库](https://cloud.tencent.com/solution/database?from_column=20065&from=20065)的脚本（后面要用到）。
- xxl-job-core：公共 jar 包依赖。
- xxl-job-admin：调度中心，项目源码，是 SpringBoot 项目，可以直接启动。
- xxl-job-executor-samples：执行器，是 Sample 实例项目，里面的 SpringBoot 工程可以直接启动，也可以在该项目的基础上进行开发，也可以将现有的项目改造成为执行器项目。



**数据库**

数据库文件在源码 doc/db 目录下，XXL_JOB 的数据库里有如下几个表：

- xxl_job_group：执行器信息表，用于维护任务执行器的信息。
- xxl_job_info：调度扩展信息表，主要是用于保存 XXL_JOB 的调度任务的扩展信息，比如说像任务分组、任务名、机器的地址等等。
- xxl_job_lock：任务调度锁表。
- xxl_job_log：日志表，主要是用在保存 XXL_JOB 任务调度历史信息，像调度结果、执行结果、调度入参等等。
- xxl_job_log_report：日志报表，会存储 XXL_JOB 任务调度的日志报表，会在调度中心里的报表功能里使用到。
- xxl_job_logglue：任务的 GLUE 日志，用于保存 GLUE 日志的更新历史变化，支持 GLUE 版本的回溯功能。
- xxl_job_registry：执行器的注册表，用在维护在线的执行器与调度中心的地址信息。
- xxl_job_user：系统的用户表。



**调度中心配置**

调度中心配置文件地址：`/xxl-job/xxl-job-admin/src/main/resources/application.properties`：

调度中心配置内容说明：

```properties
### 调度中心JDBC链接：链接地址请保持和 2.1章节 所创建的调度数据库的地址一致
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=root_pwd
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

### 报警邮箱
spring.mail.host=smtp.qq.com
spring.mail.port=25
spring.mail.username=xxx@qq.com
spring.mail.password=xxx
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory

### 调度中心通讯TOKEN [选填]：非空时启用；
xxl.job.accessToken=Orichalcos

### 调度中心国际化配置 [必填]： 默认为 "zh_CN"/中文简体, 可选范围为 "zh_CN"/中文简体, "zh_TC"/中文繁体 and "en"/英文；
xxl.job.i18n=zh_CN

## 调度线程池最大线程配置【必填】
xxl.job.triggerpool.fast.max=200
xxl.job.triggerpool.slow.max=100

### 调度中心日志表数据保存天数 [必填]：过期日志自动清理；限制大于等于7时生效，否则, 如-1，关闭自动清理功能；
xxl.job.logretentiondays=30
```



**启动项目**

调度中心访问地址：http://localhost:8080/xxl-job-admin（该地址执行器将会使用到，作为回调地址）

默认登录账号密码：admin/123456，登录后运行界面如下图所示：

![image-20240621154025166](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621154025166.png)



## 2.2、调度中心界面介绍

### 2.2.1、运行报表

以图形化来展示了整体的任务执行情况：

![image-20240621155943274](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621155943274.png)

- 任务数量：能够看到调度中心运行的任务数量。
- 调度次数：调度中心所触发的调度次数。
- 执行器数量：在整个调度中心中，在线的执行器数量有多少。



### 2.2.2、任务管理

显示执行器下的所有执行任务：

![image-20240621160005625](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621160005625.png)

点击 “新增” 即可开始配置执行任务：

![image-20240621160422064](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621160422064.png)

- 示例执行器：所用到的执行器。
- 任务描述：概述该任务是做什么的。
- 负责人：填写该任务调度的负责人。
- 报警邮件：出现报警，则发送邮件。
- Cron：执行规则。
- JobHandler：定义执行器的名字（如果 “运行模式” 选择 Bean 模式，那么这里对应 Bean 的名称）。
- 路由策略：
  - 第一个：选择第一个机器。
  - 最后一个：选择最后一个机器。
  - 轮询：依次选择执行。
  - 随机：随机选择在线的机器。
  - 一致性 HASH：每个任务按照 Hash 算法固定选择某一台机器，并且所有的任务均匀散列在不同的机器上。
  - 最不经常使用：使用频率最低的机器优先被使用。
  - 最近最久未使用：最久未使用的机器优先被选举。
  - 故障转移：按照顺序依次进行心跳检测，第一个心跳检测成功的机器选定为目标的执行器并且会发起任务调度。
  - 忙碌转移：按照顺序来依次进行空闲检测，第一个空闲检测成功的机器会被选定为目标群机器，并且会发起任务调度。
  - 分片广播：广播触发对于集群中的所有机器执行任务，同时会系统会自动传递分片的参数。
- 子任务ID：输入子任务的任务 ID，可填写多个。
- 调度过期策略：调度中心错过调度时间的补偿处理策略，包括：忽略、立即补偿触发一次等。
- 阻塞处理策略：
  - 单机串行：新的调度任务在进入到执行器之后，该调度任务进入 FIFO 队列，并以串行的方式去进行。
  - 丢弃后续调度：新的调度任务在进入到执行器之后，如果存在相同的且正在运行的调度任务，本次的调度任务请求就会被丢弃掉，并且标记为失败。
  - 覆盖之前的调度：新的调度任务在进入到执行器之后，如果存在相同的且正在运行的调度任务，就会终止掉当前正在运行的调度任务，并且清空队列，运行新的调度任务。
- 任务超时时间：添加任务超时的时候，单位 s，设置时间大于 0 的时候就会生效。
- 失败重试次数：设置失败重试的次数，设置时间大于 0 的时候就会生效。



### 2.2.3、调度日志

这里是查看调度的日志，根据日志来查看任务具体的执行情况是怎样的：

![image-20240621160611361](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621160611361.png)



### 2.2.4、执行器管理

这里是配置执行器，等待执行器启动的时候都会被调度中心监听加入到地址列表：

![image-20240621161257121](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621161257121.png)



### 2.2.5、用户管理

可以对用户的一些操作：

![image-20240621160844422](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621160844422.png)



## 2.3、整合 XXL_JOB

### 2.3.1、项目搭建

**引入 XXL_JOB 依赖**

```xml
<!-- http://repo1.maven.org/maven2/com/xuxueli/xxl-job-core/ -->
<dependency>
    <groupId>com.xuxueli</groupId>
    <artifactId>xxl-job-core</artifactId>
    <version>2.3.1</version>
</dependency>
```



**配置 properties**

```properties
server.port=8081

### 调度中心部署根地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调"；为空则关闭自动注册；
xxl.job.admin.addresses=http://127.0.0.1:8080/xxl-job-admin

### 执行器通讯TOKEN [选填]：非空时启用；
xxl.job.accessToken=Orichalcos

### 执行器AppName [选填]：执行器心跳注册分组依据；为空则关闭自动注册
xxl.job.executor.appname=xxl-job-test
### 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 ”IP:PORT“ 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。
xxl.job.executor.address=
### 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"；
xxl.job.executor.ip=
### 执行器端口号 [选填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；
xxl.job.executor.port=9999
### 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；
xxl.job.executor.logpath=/data/applogs/xxl-job/jobhandler
### 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；
xxl.job.executor.logretentiondays=30
```



**编写配置类**

```java
@Configuration
@Slf4j
public class XxlJobConfig {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appName);
		//xxlJobSpringExecutor.setIp(ip);
		//xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
		//xxlJobSpringExecutor.setLogPath(logPath);
		//xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }
}
```



### 2.3.2、添加分布式调度任务

**代码配置 JobHandler**

```java
@Slf4j
@Component
public class MyXxlJobHandler {

    /**
     * 简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> execute(String param){
        for (int i = 0; i < 5; i++) {
            log.info("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        log.info("========================定时任务执行");
    }
}
```



**新增执行器**

在 “执行器管理” 界面新增一个执行器：

![image-20240621173423002](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621173423002.png)



**新增任务**

在 “任务管理” 界面新增一个任务：

![image-20240621174414866](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621174414866.png)



**重新启动项目**

重新启动项目，等待执行器上线后，在 “任务管理” 界面选择执行一次任务：

![image-20240621175803016](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240621175803016.png)
