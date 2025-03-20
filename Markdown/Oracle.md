---
typora-copy-images-to: upload
---

# 1、Oracle 简介

## 1.1、Oracle 简介

Oracle Database，又名 Oracle RDBMS，简称 Oracle。Oracle 数据库系统是美国 Oracle 公司（甲骨文）提供的以分布式数据库为核心的一组软件产品，是目前最流行的客户/服务器（client/server）或B/S体系结构的数据库之一，比如 SilverStream 就是基于数据库的一种中间件。Oracle 数据库是目前世界上使用最为广泛的数据库管理系统，作为一个通用的数据库系统，它具有完整的数据管理功能；作为一个关系型数据库，它是一个完备关系的产品；作为分布式数据库它实现了分布式处理功能。



**Oracle 体系结构**

Oracle 数据库实际上是一个数据的物理储存系统，这其中包括数据文件（ora/dbf）、参数文件、控制文件、联机日志等。

**实例：**一个操作系统只有一个 Oracle 数据库，但是可以安装多 个Oracle 实例，一个 Oracle 实例对应着一系列的后台进程（Backguound Processes)和内存结构（Memory Structures)。

**数据文件：**Oracle 数据文件是数据存储的物理单位，数据库的数据是存储在表空间中的。而一个表空间可以由一个或多个数据文件组成，一个数据文件只能属于一个表空间，一旦数据文件被加入到某个表空间后，就不能删除这个文件，如果要删除某个数据文件，只能删除其所属于的表空间才行。

**表空间：**表空间是 Oracle 对物理数据库数据文件（ora/dbf）的逻辑映射。一个数据库在逻辑上被划分成一到若干个表空间，每个表空间由同一磁盘上的一个或多个数据文件（datafile）组成，一个数据文件只能属于一个表空间。

**Oracle用户：**表当中的数据是由 Oracle 用户放入到表空间当中的，而这些表空间会随机的把数据放入到一个或者多个数据文件当中。Oracle 对表数据的管理是通过用户对表的管理去查询，而不是直接对数据文件或表空间进行查询。因为不同用户可以在同一个表空间上面建立相同的表名。但是通过不同的用户管理自己的表数据。



**数据结构逻辑关系如下图：**

<img src="!assets/Oracle/viewImages.png" alt="数据结构逻辑关系" style="" />



**Oracle体系概要图如下：**

<img src="!assets/Oracle/viewImages-16358235683753.png" alt="Oracle体系概要图" style="zoom: 67%;" />



## 1.2、Oracle 19c 安装

**安装前注意退出杀毒软件！！！**

1. 去官网下载 [Oracle 19c](https://www.oracle.com/database/technologies/oracle-database-software-downloads.html#19c)：

	<img src="!assets/Oracle/image-20211102113310795.png" alt="image-20211102113310795" style="" />

2. 安装前需要把文件包解压，解压的位置为需要安装的位置，所以要提前确定好解压路径，解压后的文件大概需要 6G 的空间，确保空间足够大。

   > 解压的目录不要含有中文和空格！

3. 以管理员身份运行 `setup.exe`：

	<img src="!assets/Oracle/123.png" alt="img" style="" />

4. 打开安装程序后，跟着安装向导，初学者建议选默认选择 “创建并配置单实例数据库”：

	<img src="!assets/Oracle/12dlaXhp20.png" alt="img" style="" />
	
5. 选中【桌面类】，点击下一步：

	<img src="!assets/Oracle/watermark,type_ZmFuZ3poZW5.png" alt="img" style="" />

6. Oracle主目录用户，如下图所示：

	- 使用虚拟账户：用于 Oracle 数据库单实例安装的 Oracle 主目录用户。
	- 使用现有 Windows 用户：如果选择该项，则需要指定没有管理权限的用户。
	- 创建新 Windows 用户：创建一个新用户，输入用户名和密码，这个新建的用户没有 Windows 登录权限。
	- 使用 Windows 内置账户：微软在开 Windows 时预先为用户设置的能够登录系统的账户。

	此处本人选择虚拟账户，也是 Oracle 的官方建议之一。

	<img src="!assets/Oracle/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nL.png" alt="img" style="" />

7. 更改【Oracle 基目录】，取消勾选【创建为容器数据库】，填写【全局数据库名】及口令，点击下一步：

	<img src="!assets/Oracle/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0c.png" alt="在这里插入图片描述" style="" />

8. 先决条件检查：

	<img src="!assets/Oracle/watermark,type_ZmFuZ3poZW5na4ubmV0L3FxXzE3MDU4OTkz,size_16,color_FFFFFF,t_70.png" alt="img" style="" />

9. 概要：

	<img src="!assets/Oracle/watermark,type_ZmFuZ3poZW5nNzZG4ubmV0L3dlaXhpbl80Mzc5MjQwMQ==,size_16,color_FFFFFF,t_70.png" alt="在这里插入图片描述" style="" />

10. 安装等待，这里会等待得比较久一点：

	<img src="!assets/Oracle/watermark,type_ZmFuZ3poZW5naGVpdGk,2zZG4ubmV0L3dlaXhpbl80Mzc5MjQwMQ==,size_16,color_FFFFFF,t_70.png" alt="在这里插入图片描述" style="" />

11. 几十分钟后，显示成功的窗口，点击关闭：

	<img src="!assets/Oracle/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly92LmNzZG4ubmV0L3dlaXhpbl80Mzc5MjQwMQ==,size_16,color_FFFFFF,t_70.png" alt="在这里插入图片描述" style="" />



### 1.2.1、安装问题

**Oracle19c 安装中的无法将 Windows 用户或 Windows 组 “XXX“ 添加到 Windows 组 “XXX“ 中**

看看自己的计算机名称和自己用户名是不是相同，相同的话更改计算机名称为其他的。并不一定适用所有，可进行尝试。



## 1.3、Oracle 19c 卸载

要在 Windows 上卸载 Oracle 数据库，我们必须手动删除所有相关的 `Ora*` 注册表项、文件和文件夹。

1. 停止Oracle *服务：
	
	<img src="!assets/Oracle/image-20230419152744833.png" alt="image-20230419152744833" style="zoom:50%;" />
	
2. 删除 Oracle *注册表项：

	-   `计算机\HKEY_LOCAL_MACHINE\SOFTWARE\Oracle*`

	-   `计算机\HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Oracle*`

	-   `计算机\HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Oracle*`

		重新启动Windows。

3. 删除以下 Oracle * 文件夹和文件（如果存在）:

	- `C:\Oracle` 或 `ORACLE_BASE`
	- `C:\Program Files\Oracle`
	- `C:\Program Files (x86)\Oracle`
	- `C:\ProgramData\Microsoft\Windows\Start Menu\Programs\` 与 Oracle 相关的文件夹。
	- `C:\Users` 与 Oracle 相关的文件夹。

4. 清空 `C:\temp ` 和回收站。



# 2、连接和配置

## 2.1、连接 Oracle

### 2.1.1、SQL*Plus

SQL*Plus 是 Oracle 提供的命令行工具，用于与 Oracle 数据库交互。它支持：

- 运行 SQL 语句（如 `SELECT`、`INSERT`、`UPDATE`、`DELETE`）。
- 运行 PL/SQL 代码块（如 `BEGIN ... END;`）。
- 进行数据库管理（如 `ALTER DATABASE`、`SHUTDOWN`、`STARTUP`）。
- 执行 SQL 脚本（如 `.sql` 文件）。

SQL*Plus 可以用于本地或远程连接 Oracle 数据库。



**以 `SYSDBA` 权限连接**

如果你的操作系统用户属于 `dba` 组（Linux）或 `ORA_DBA` 组（Windows），可以使用：

```shell
sqlplus / as sysdba
```

这将以 `SYSDBA` 权限连接到 Oracle 数据库。



**使用普通用户连接**

如果你要使用普通用户连接，可以提供用户名和密码：

```shell
sqlplus <用户名>/<密码>@[<主机IP>:<端口>/]<数据库服务名>
```

如果不想在命令行直接输入密码，可以只输入：

```shell
sqlplus <用户名>@[<主机IP>:<端口>/]<数据库服务名>
```

然后 SQL*Plus 会提示输入密码。



### 2.1.2、Navicat 

<img src="!assets/Oracle/image-20230523200124483.png" alt="image-20230523200124483" style="" />

基本上直接连就行，这里主要记录下遇到的问题：



#### ORA-12514

ORA-12514:TNS:监听程序当前无法识别连接述符中请求的服务

1. 【工具】 ==> 【选项】 ==> 【环境】：

	<img src="!assets/Oracle/image-20211102124021098.png" alt="image-20211102124021098" style="" />

2. 在 Oracle 安装目录下找到 `oci.dll`， 这是我的路径：`E:\Database\Oracle\Oracle_WINDOWS.X64_193000_db_home\bin\oci.dll`，完成之后重新启动 Navicat。



#### ORA-12516

ORA-12516:TNS:监听程序找不到符合协议堆栈要求的可用处理程序

出现这个问题是连接数过大导致的，也正是因为这个连接数过大，即使使用 `sqlplus / as sysdba` 也是登录不上。所以要解决这个问题，首先就要先断开当前连着的 process，然后使用 `sqlplus` 进行登录修改 process 和 session 的最大值：

1. 运行以下命令启动 SQL*Plus 并直接以 `SYSDBA` 权限连接到数据库：

   ```shell
   sqlplus / as sysdba
   ```

2. 查看 process 的参数值和占有值：

   ```sql
   show parameter processes;
   
   select count(*) from v$process;
   ```

   会发现 `processes` 的参数值不太大，一般默认是 150 或者 300。

3. 修改 process 和 session 的最大值：

   ```sql
   alter system set processes=2000 scope=spfile;
   
   alter system set sessions=3005 scope=spfile;
   ```

   > `processes` 的值和 `sessions` 的值 Oracle 官方文档中要求 sessions = processes * 1.5 + 5

4. 重启服务：

   ```sql
   -- 关闭数据库
   shutdown immediate
   
   -- 启动数据库使配置生效
   startup;
   ```



#### ORA-12638

ORA-12638: 身份证明检索失败

这个一般出现在远程连接 Oracle 时，找到 sqlnet.ora 文件，如果存在 `SQLNET.AUTHENTICATION_SERVICES= (NTS)` 设置，则修改为：`SQLNET.AUTHENTICATION_SERVICES= (NONE)`，如果不存在，则直接添加 `SQLNET.AUTHENTICATION_SERVICES= (NONE)`。



#### ORA-28547

ORA-28547:connection to server failed, probable Oracle Net admin error

因为 Navicat 是通过 Oracle 客户端连接 Oracle 服务器的，Oracle 的客户端分为两种，一种是标准版，一种是简洁版，即 Oracle Install Client。而我们用 Navicat 时通常会在自己的安装路径下包含多个版本的 OCI，如果使用 Navicat 连接 Oracle 服务器出现 ORA-28547 错误时，多数是因为 Navicat 本地的 OCI 版本与 Oracle 服务器服务器不符造成的。所以我们要做的就是下载 OCI 使之与我们所安装的 Oracle 服务器相符合。

OCI 下载地址：https://www.oracle.com/database/technologies/instant-client/downloads.html

1. 选择自己的运行环境：

   <img src="!assets/Oracle/image-20230803121856769.png" alt="image-20230803121856769" style="" />

2. 选择自己的 Oracle 版本：

   <img src="!assets/Oracle/image-20230803122123579.png" alt="image-20230803122123579" style="" />

   > 还有一点要注意，Oracle9i 或以上版本的，要安装 Install Client11 或以下；Oracle8 或 8i 服务器，需要安装 Install Client10 或以下。这个问题不大，因为我们现在的 Oracle 都是 10 或 11 了，注意一下就好。

3.  然后在 Navicat 中配置一下，选择【工具】 ==> 【选项】 ==> 【环境】：

   <img src="!assets/Oracle/image-20230803122833129.png" alt="image-20230803122833129" style="zoom: 67%;" />

   在 OCI library 中找到刚刚下载的文件夹中的 oci.dll。

4. 这样就完成了 Navicat 配置，也就使得 Navicat 中的 oci.dll 版本和 Oracle 中的版本一致了，必须重启 Navicat 才能生效。



### 2.1.3、SQL Developer

Oracle SQL Developer 是一个免费的图形化工具，可以提高生产力并简化数据库开发任务。使用 SQL Developer 可以浏览数据库对象、运行 SQL 语句和 SQL 脚本、编辑和调试 PL/SQL 语句、操作和导出数据以及查看和创建报告。您可以连接到 Oracle 数据库，也可以连接到选定的第三方（非 Oracle）数据库，查看元数据和数据，并将这些数据库迁移到 Oracle。

SQL Developer 还将接口集成到一些相关技术中，包括 Oracle Data Miner、Oracle OLAP、Oracle TimesTen In Memory Database 和 SQL Developer Data Modeler（只读）。

前往 [Oracle SQL Developer - Oracle SQL Developer Releases](https://docs.oracle.com/en/database/oracle/sql-developer/index.html) 可选择版本下载。



以下为使用 SQL Developer 新建表空间以及用户的操作步骤：

1. 连接到 Oracle 数据库后，选中 Oracle 连接然后点击【新建】：

   <img src="!assets/Oracle/image-20230523201401549.png" alt="image-20230523201401549" style="" />

   在如下界面中可以看到新建表空间的选项：

   <img src="!assets/Oracle/image-20230523201523278.png" alt="image-20230523201523278" style="" />

2. 在【其他用户】里，可以右键添加用户：

   <img src="!assets/Oracle/image-20230523202652586.png" alt="image-20230523202652586" style="" />

3. 填写用户名和密码，并分配表空间：

   <img src="!assets/Oracle/image-20230523204127324.png" alt="image-20230523204127324" style="" />

4. 授权里面选中 CONNECT、DBA、RESOURE 三个权限，保存即可：

   <img src="!assets/Oracle/image-20230523203347868.png" alt="image-20230523203347868" style="" />



## 2.2、Oracle 配置

listener.ora、tnsnames.ora 和 sqlnet.ora 这 3 个文件是关系 Oracle 网络配置的 3 个主要文件，都是放在  `$ORACLE_HOME/network/admin/` 目录下。

如果不知道 `$ORACLE_HOME` 在哪可以打开图形工具 Oracle Net Manager 查看：

<img src="!assets/Oracle/image-20230523183048714.png" alt="image-20230523183048714" style="" />

同时上面说到的三个文件都可以通过这个图形的配置工具来完成配置，这些都是 Oracle 自带的配置工具：

- Net Manager：管理已经新建好的实例和监听器。
- Database Configuration Assistant：数据库实例管理。
- Net Configuration Assistant ：监听器管理。

所有 Oracle 客户端都有这三个文件，其中 listener.ora 是和服务器端相关，而 tnsnames.ora 和 sqlnet.ora 主要的还是和客户端关系紧密。



### 2.2.1、开放远程连接

与连接远程数据库有关的只有远程数据库上的 listener.ora 文件，listener.ora 文件 是 Listener 监听器进程的配置文件。Listener 进程存在于服务器上，负责接受远程对数据库的接入申请并转交给 Oracle 的服务器进程。所以如果不是使用的远程的连接，Listener 进程就不是必需的，同样的如果关闭 Listener 进程并不会影响已经存在的数据库连接。

Listener 即监听器，原则上，一个监听器对应一个数据库实例。

一个典型的文件如下，由数据库自己生成：

```shell
# 这个的后缀对应着listener，它是listener监听器对应的实例列表
SID_LIST_LISTENER =
  (SID_LIST =
    (SID_DESC =
      (SID_NAME = CLRExtProc)
      (ORACLE_HOME = C:\Oracle\Oracle_19c\Oracle_193000_db_home)
      (PROGRAM = extproc)
      (ENVS = "EXTPROC_DLLS=ONLY:C:\Oracle\Oracle_19c\Oracle_193000_db_home\bin\oraclr19.dll")
    )
  )

# 定义一个叫listener的监听器，这里名称可变
LISTENER =
  (DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))
  )

ADR_BASE_LISTENER = C:\Oracle\Oracle_19c\Oracle_193000_db_home\log
```

第二段定义了一个监听器，监听的 `HOST` 为本机名，可以是服务器名，也可以是`127.0.0.1` 或 `localhost`，因为这个监听器的作用就是监听本地请求，也就是在服务器上直接登录的情况。如果某个连接请求到了本地 ip，端口又为 1521，监听器就向实例列表中请求实例名称，以完成数据库连接。

这样定义好以后，只是本地能访问了，也就是说如果不这样配好本地监听，连服务器自己都上不去 Oracle。所以这段代码一般在数据库安装好，新建实例后就会初始化好。

接下来我们可以在 `LISTENER` 这个监听器的 `DESCRIPTION` 中添加一个局域网的地址，来监听在这个局域网地址上的访问。例如：

```shell
LISTENER =
  (DESCRIPTION_LIST =
    (DESCRIPTION =
      (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))
      (ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.20.101)(PORT = 1521)) # 新增局域网地址
    )
  )
```

这样配置好后，监听器就会监听本机的局域网 ip，任何对这个 ip 的 1521 端口的请求都会被其捕获，并尝试进行数据库连接。

> [!IMPORTANT]
>
> 将 `HOST` 设置为 `0.0.0.0` 表示不限制任何 IP 访问，配置完一定要记得重启 Listener 服务！



### 2.2.2、修改端口号

Oracle 默认监听端口 1521，一众扫描器通常通过探测 1521 端口是否开启来探测是否存在 Oracle 服务，如果修改默认监听端口在一定程度上可以提升数据库和主机的安全性。

比如这里我们修改成 2521 为例：

1. 查看当前监听状态：

   ```shell
   lsnrctl status
   ```

2. 停止监听：

   ```shell
   lsnrctl stop
   ```

3. 修改监听配置文件：

   打开 listener.ora 文件：

   ```shell
   vi $ORACLE_HOME/network/admin/listener.ora
   ```

   在 listener.ora 文件中，找到 `LISTENER` 条目，如下所示：

   ```
   LISTENER =
     (DESCRIPTION =
       (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))
     )
   ```

   将 `PORT` 值从 1521 更改为你希望使用的新端口号。例如，如果你想将端口更改为 2521，则应将其更改为：

   ```
   (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 2521))
   ```

4. 修改连接配置文件：

   打开 tnsnames.ora 文件：

   ```shell
   vi $ORACLE_HOME/network/admin/tnsnames.ora
   ```

   找到数据库服务别名（通常为 `ORCL`），并将 `PORT` 值更改为与 listener.ora 文件中相同的新端口号。例如：

   ```
   ORCL =
     (DESCRIPTION =
       (ADDRESS_LIST =
         (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 2521))
       )
       (CONNECT_DATA =
         (SERVICE_NAME = ORCL)
       )
     )
   ```

5. 运行以下命令启动 SQL*Plus 并直接以 `SYSDBA` 权限连接到数据库：

   ```shell
   sqlplus / as sysdba
   ```

6. 查看 `local_listener` 参数：

   ```sql
   show parameter local_listener
   ```

   如果之前没修改端口使用的是默认配置，则此时参数 `VALUE` 应为空值。

7. 修改 `local_listener` 参数：

   如果 `LOCAL_LISTENER` 参数已设置，请确保其值与在 `listener.ora` 文件中设置的新端口号一致。

   ```sql
   alter system set local_listener="(address = (PROTOCOL = TCP)(HOST = localhost) (PORT = 2521))";
   ```

8. 退出 SQL*Plus：

   ```sql
   exit
   ```

9. 重新启动监听：

   ```shell
   lsnrctl start
   ```

> [!NOTE]
>
> Windows 下监听服务为 Oracle 开头 TNSListener 结尾的服务，比如安装的 Oracle19C，服务名为：OracleOraDB19Home1TNSListener。



### 2.2.3、sqlnet.ora

通过这个文件来决定怎样找一个连接中出现的连接字符串，示例文件：

```
SQLNET.AUTHENTICATION_SERVICES= (NTS)
NAMES.DIRECTORY_PATH= (TNSNAMES, HOSTNAME, ONAMES，EZCONNECT)
#NAMES.DEFAULT_DOMAIN = oracle.com123
```

内容说明：

- `SQLNET.AUTHENTICATION_SERVICES= (NTS)`

  表明用户连接数据库用哪种验证方式，主要两种：

  - `NTS`：表示系统身份验证 — 用户名和口令可输可不输 `conn / as sysdba`；
  - `NONE`：Oracle 数据库身份验证 — 必须输入用户名和口令 `conn system/oracle as sysdba`；

  在 Unix 环境下可能会有问题，一般在 Unix 下可以去掉这个配置。



### 2.2.4、修改字符集

1. 运行以下命令启动 SQL*Plus 并直接以 `SYSDBA` 权限连接到数据库：

   ```sql
   sqlplus / as sysdba
   ```

3. 立即关闭数据库，以确保数据库不处于运行状态，从而允许后续的数据库修改操作：

   ```sql
   shutdown immediate;
   ```

4. 启动数据库实例，并将其保持在MOUNT（已装载）状态：

   ```sql
   startup mount
   ```

5. 为了确保只有授权用户能够在字符集更改期间访问数据库，启用 RESTRICTED SESSION 模式，只允许具有 RESTRICTED SESSION 特权的用户连接：

   ```sql
   -- 检查 RESTRICTED SESSION 模式，如果 logins 列的值为 RESTRICTED，则表示 RESTRICTED SESSION 模式已打开。如果值为 ALLOWED，则表示模式未打开。
   SELECT logins FROM v$instance;
   
   ALTER SYSTEM ENABLE RESTRICTED SESSION;
   ```

6. 为了防止后台作业和队列在字符集更改期间对数据库产生干扰，禁用作业队列和高级队列：

   ```sql
   -- 查看 JOB_QUEUE_PROCESSES 参数。
   SELECT name, value FROM v$parameter WHERE name = 'job_queue_processes';
   
   ALTER SYSTEM SET JOB_QUEUE_PROCESSES=0;
   
   -- 查看 AQ_TM_PROCESSES 参数。
   SELECT name, value FROM v$parameter WHERE name = 'aq_tm_processes';
   
   ALTER SYSTEM SET AQ_TM_PROCESSES=0;
   ```

7. 在 MOUNT 状态下，数据库不能正常工作，需要先打开数据库：

   ```sql
   alter database open;
   ```

8. 修改数据库字符集为 ZHS16GBK，修改内部使用的数据库字符集为 ZHS16GBK：

   ```sql
   ALTER DATABASE CHARACTER SET ZHS16GBK;
   ALTER DATABASE CHARACTER SET INTERNAL_USE ZHS16GBK;
   ```

9. 查询 NLS 参数以验证字符集更改是否生效：

   ```sql
   -- 在内容中找下NLS_CHARACTERSET，这个值：ZHS16GBK
   select * from v$nls_parameters;
   ```

10. 关闭之前还原修改的参数：

    ```sql
    ALTER SYSTEM ENABLE <参数> SESSION;
    ALTER SYSTEM SET JOB_QUEUE_PROCESSES=<参数>;
    ALTER SYSTEM SET AQ_TM_PROCESSES=<参数>;
    ```

11. 关闭数据库以进行最终的清理工作：

    ```sql
    shutdown immediate;
    ```

12. 再次启动数据库以确保所有更改生效：

    ```sql
    startup
    ```

13. 查查询 NLS 参数以验证字符集更改是否持久生效：

    ```sql
    select * from v$nls_parameters;
    ```



### 2.2.5、修改占用内存

Oracle 安装好之后，也可以调整内存的分配。

首先需要运行以下命令启动 SQL*Plus 并直接以 `SYSDBA` 权限连接到数据库（后续的操作需要借助此工具完成）：

```shell
sqlplus / as sysdba
```

查看相关配置：

```sql
show parameter target
```

```
NAME                                 TYPE        VALUE
------------------------------------ ----------- ------------------------------
archive_lag_target                   integer     0
db_big_table_cache_percent_target    string      0
db_flashback_retention_target        integer     1440
fast_start_io_target                 integer     0
fast_start_mttr_target               integer     0
memory_max_target                    big integer 6000m
memory_target                        big integer 6000m
parallel_servers_target              integer     400
pga_aggregate_target                 big integer 1500M
sga_target                           big integer 4500M
target_pdbs                          integer     9
```

> [!CAUTION]
>
> 后续内存调整中，必须 memory_target >= sga_target + pga_aggregate_target，否则会启动报错！

主要有两种方法来修改 Oracle 的内存设置：



**调整 `MEMORY_TARGET` 和 `MEMORY_MAX_TARGET` 参数**

如果你启用了自动内存管理（AMM），可以通过修改以下两个参数来调整内存分配：

- `MEMORY_TARGET`：Oracle 实例启动后使用的内存大小。
- `MEMORY_MAX_TARGET`：Oracle 实例可以使用的最大内存大小。

> [!NOTE]
>
> 在 Oracle 19C，如果你设置占用内存超过 4 G，那么会禁用 AMM。

修改方法：

```sql
ALTER SYSTEM SET MEMORY_TARGET=2G SCOPE=SPFILE;
ALTER SYSTEM SET MEMORY_MAX_TARGET=3G SCOPE=SPFILE;

ALTER SYSTEM SET MEMORY_TARGET=6000m SCOPE=SPFILE;
ALTER SYSTEM SET MEMORY_MAX_TARGET=6000m SCOPE=SPFILE;
```

然后重启数据库实例使其生效：

```sql
SHUTDOWN IMMEDIATE;
STARTUP;
```

> [!IMPORTANT]
>
> `SCOPE` 参数决定了更改的生效时间：
>
> - `SCOPE=SPFILE`：在下次数据库启动时生效。
> - `SCOPE=MEMORY`：立即生效，但不会保存在配置文件中。
> - `SCOPE=BOTH`：立即生效，并保存到配置文件中。



**手动调整 SGA 和 PGA**

如果你没有启用AMM，可以手动调整SGA（System Global Area）和PGA（Program Global Area）的大小。主要参数有：

- `SGA_TARGET`：控制 SGA 的大小。
- `PGA_AGGREGATE_TARGET`：控制 PGA 的大小。

修改方法：

```sql
ALTER SYSTEM SET SGA_TARGET=4500m SCOPE=SPFILE;
ALTER SYSTEM SET PGA_AGGREGATE_TARGET=1500m SCOPE=SPFILE;
```

同样需要重启数据库实例：

```sql
SHUTDOWN IMMEDIATE;
STARTUP;
```



**调错内存导致无法启动**

在数据库不能更改 spfile 的情况下，我们可以更改 pfile，再用 pfile 启动：

1. 以 spfile 为副本创建 pfile 文件：

   ```sql
   create pfile = 'C:\Oracle\Oracle19C\WINDOWS.X64_193000_db_home\INIT.ORA' from spfile;
   ```

2. 将 pfile 文件里面的内存设置改掉（改成需要的大小）：

   ```
   *.memory_max_target=8192m
   *.memory_target=8192m
   ```

3.  用 pfile 启动：

   ```sql
   STARTUP PFILE='C:\Oracle\Oracle19C\WINDOWS.X64_193000_db_home\INIT.ORA';
   ```

4. 启动成功后将文件改回 spfile：

   ```sql
   create spfile from pfile = 'C:\Oracle\Oracle19C\WINDOWS.X64_193000_db_home\INIT.ORA';
   ```

5. 然后再启动一次：

   ```sql
   SHUTDOWN IMMEDIATE;
   STARTUP;
   ```



# 3、用户及表空间

## 3.1、用户管理

运行以下命令启动 SQL*Plus 并直接以 `SYSDBA` 权限连接到数据库：

```shell
sqlplus / as sysdba
```



**账户解锁**

```sql
alter user <用户名> account unlock;
```



**修改密码**

```sql
alter user <用户名> identified by <密码>;
```



**取消密码180 天限制**

1. 查看用户的 `proifle` 是哪个，一般是 DEFAULT：

   ```sql
   SELECT username,PROFILE FROM dba_users;
   ```

2. 查看指定概要文件（如 DEFAULT）的密码有效期设置：

   ```sql
   SELECT * FROM dba_profiles WHERE profile='DEFAULT' AND resource_name='PASSWORD_LIFE_TIME';
   ```

3. 将密码有效期由默认的 180 天修改成无限制：

   ```sql
   ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;
   ```



## 3.2、表空间

**删除表空间**

删除空的表空间，但是不包含物理文件：

```sql
DROP TABLESPACE tablespace_name;
```

如果表空间非空，就要加上 `INCLUDING CONTENTS` 选项：

```sql
DROP TABLESPACE tablespace_name INCLUDING CONTENTS;
```

如果还希望删除该表空间的相关数据文件，可以使用 `INCLUDING CONTENTS AND DATAFILES` 选项：

```sql
DROP TABLESPACE tablespace_name INCLUDING CONTENTS AND DATAFILES;
```

如果其他表空间中的表有外键等约束关联到了本表空间中的表的字段，就要加上 `CASCADE CONSTRAINTS`：

```sql
DROP TABLESPACE tablespace_name INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;
```



# 4、数据结构

## 4.1、VARCHAR 和 VARCHAR2

1. varchar 是标准 SQL 里面的； varchar2 是 Oracle 提供的独有的数据类型。
2. varchar 对于汉字占两个字节，对于英文是一个字节，占的内存小；varchar2 都是占两个字节。
3. varchar 对空串不处理；varchar2 将空串当做 `null` 来处理。
4. varchar 存放固定长度的字符串，最大长度是2000；varchar2 是存放可变长度的字符串，最大长度是4000。
5. 如果是要跟换不同的数据库，例如 MySQL，那么就用 varchar，如果就用 Oracle，那么用 varchar2 比较好一点。



# 5、查询

我初学的数据库是 MySQL，由于 Oracle 也是使用 SQL 标准，这里用于记录工作中使用 Oracle 所遇到的查询问题。



## 5.1、对 CLOB 进行模糊查询

在 Oracle 中大文本数据我们没有办法使用 `LIKE` 进行查询，所以只能使用 Oracle 中的函数：

```sql
SELECT * FROM <表名> WHERE dbms_lob.instr(<字段名（clod类型）>, '查询条件', 1, 1) > 0
```

在 Oracle 中，可以使用 `instr()` 函数对某个字符串进行判断，判断其是否含有指定的字符。其语法为：

```sql
instr(sourceString, destString, start, appearPosition)
```

参数：

- *sourceString*：源字符串。
- *destString*：想从源字符串中查找的子串。
- *start*：查找的开始位置，该参数是可选的，默认为 1，如果 *start* 的值为负数，那么代表从右往左进行查找。
- *appearPosition*：想从源字符中查找出第几次出现的 *destString*，该参数也是可选的，默认为 1。

返回值：查找到的字符串的位置。



## 5.2、树形结构层级查询

通常，在查询树形结构的数据时，需要使用 `START WITH...CONNECT BY PRIOR` 的方式查询。

`START WITH...CONNECT BY PRIOR` 的语法为：

```sql
SELECT <字段>
FROM <表名>
WHERE condition1
START WITH condition2
CONNECT BY PRIOR condition3
```

参数：

- *condition1*：过滤条件
- *condition2*：起始的查询条件，指定根节点，当然可以放宽限定条件，以取得多个根结点，实际就是多棵树
- *condition3*：指定父节点和子节点直接的关系，`PRIOR` 指定父节点



**示例**

假设现有部门表 `DEPARTMENT`，部门表中字段包括 `DEPID`（部门 ID），`PARENTDEPID`（父部门 ID），`DEPNAME`（部门名称）

1、我们要查询部门 `ID="1110"` 的部门的所有父部门的 ID 和名称（包含部门 `ID="1110"` 的部门，不包含可通过 `WHERE` 条件过滤）

```sql
SELECT DEPID, DEPNAME
FROM FW_DEPARTMENT
-- WHERE DEPID <> '1110'
START WITH DEPID = '1110'
CONNECT BY PRIOR PARENTDEPID = DEPID 
```

2、我们要查询部门 `ID="1110"` 的部门的所有子部门的 ID 和名称（包含部门 `ID="1110"` 的部门，不包含可通过 `WHERE` 条件过滤）

```sql
SELECT DEPID, DEPNAME
FROM FW_DEPARTMENT
-- WHERE DEPID <> '1110'
START WITH DEPID = '1110'
CONNECT BY PRIOR DEPID = PARENTDEPID
```

从上面 2 个 SQL 可以发现：

- 查询当前节点的所有父节点时，需要将 `PRIOR` 放在父节点左侧
- 查询当前节点的所有子节点时，需要将 `PRIOR` 放在子节点左侧



**排序**

在层次查询中，如果想让 “亲兄弟” 按规矩进行升序排序就不得不借助 `ORDERSIBLINGS BY` 这个特定的排序语句而非 `ORDER BY` 子句，若要降序输出可以在其后添加 `DESC` 关键字。



## 5.3、关于 Oracle 中的 AS

在 Oracle 中 `AS` 关键字不能用于指定表的别名，在 Oracle 中指定表的别名时只需在原有表名和表的别名之间用空格分隔即可，指定列的别名的用法和 MySQL 相同，但在存储过程中如果列的别名与原有列名相同，在运行时会报错（编译时不会出错），其他情况下列的别名可以与列名本身相同。



## 5.4、限制查询条数

在 MySQL 数据库中，`LIMIT` 关键字用于限制查询结果的返回行数。它通常用于分页查询或限制结果集的大小。

但是 Oracle 中并没有 `LIMIMT`，在 Oracle 数据库中，`ROWNUM` 是一个伪列（Pseudocolumn），它用于标识查询结果集中的行号。每当从查询结果集中检索出一行时，`ROWNUM` 就会递增。这使得它成为限制返回行数、分页查询的一种方式。



**限制返回行数**

可以使用 `ROWNUM` 限制查询结果的返回行数，例如：

```sql
SELECT column1, column2
FROM your_table
WHERE conditions
AND ROWNUM <= 10;
```

这将返回满足条件的前 10 行。



**分页查询**

对于这种形式的查询，Oracle 不像 MySQL 那么方便，它必须使用子查询或者是集合操作来实现。

因为当你查询 `ROWNUM` 大于 2 的记录时会得到一个空的结果集，由于 `ROWNUM` 是根据查询的结果集来对记录进行编号，当 Oracle 查询得到第 1 条记录时，发现 `ROWNUM` 为 1 不满足条件，然后就继续查询第 2 条记录，但此时第 2 条记录又被编号为 1（也即 `ROWNUM` 变为 1），所以查询得到的始终是 `rownum=1`，因此无法满足约束，最终查询的结果集为空。

使用 `ROWNUM` 结合子查询实现分页查询，例如：

```sql
SELECT column1, column2
FROM (
    SELECT column1, column2, ROWNUM AS rnum
    FROM your_table
    WHERE conditions
)
WHERE rnum >= 11 AND rnum <= 20;
```

这将返回满足条件的第 11 到 20 行，用于实现分页。



## 5.5、不等于空字符串

之前的应用一直是连接 MySQL 数据库，在 MySQL 中 `''` 和 `NULL` 是不相等的，如：

```sql
SELECT CASE WHEN '' IS NULL THEN 'Yes' ELSE 'No' END AS result FROM dual;
-- 结果：NO
```

在 Oracle 中，`''` 在存储时会自动转换为 `NULL`，因此它们是等价的，即 `''` 等同于 `NULL`：

```sql
SELECT CASE WHEN '' IS NULL THEN 'Yes' ELSE 'No' END AS result FROM dual;
-- 结果：YES
```

所以在 Oracle 中要小心使用 `''`，因为它实际上是 `NULL`，而在 MySQL 中它们是不同的！

> [!IMPORTANT]
>
> 不管是 MySQL 还是 Oracle，判断 `NULL` 都应该使用 `IS` 和 `IS NOT` 而不是 `=` 和 `!=`！



## 5.6、字段名与 SQL 关键字重名

Oracle 中，如果表中的字段名正好跟 SQL 中关键字重名，写 SQL 语句时要注意：

1. 要将该字段名大写

2. 字段名两边要加双引号 `""`（注：必须是双引号，单引号将无效）

   > 在 MySQL 数据库中是使用反引号（数字 1 按键左边）

比如要创建的表中有 `group` 字段：

```sql
CREATE TABLE address(
   "group" char(30)
)
```



# 6、函数

## 6.1、时间日期

### 6.1.1、SYSDATE

获取当前日期和时间：

```sql
SYSDATE()
```



### 6.1.2、ADD_MONTHS

日期/时间增减

增减一小时：
```sql
dateField + 1/24;
dateField - 1/24;
```

增减一天：

```sql
dateField + 1;
dateField - 1;
```

增减一月：

```sql
ADD_MONTHS(dateField, 1);
ADD_MONTHS(dateField, -1);
```

增减一季度：

```sql
ADD_MONTHS(dateField, 3);
ADD_MONTHS(dateField, -3);
```

增减一年：

```sql
ADD_MONTHS(dateField, 12);
ADD_MONTHS(dateField, -12;
```



### 6.1.3、MONTHS_BETWEEN

Oracle `MONTHS_BETWEEN` 函数用于计算两个日期之间的月份差。它返回一个浮点数，表示两个日期之间相差的月份数量。这个函数可以用于计算例如年龄差、账单周期等涉及月份差异的情况。

```sql
MONTHS_BETWEEN(date1, date2)
```

*date1* 和 *date2* 是要比较的两个日期。函数返回一个浮点数，表示从 *date1* 到 *date2* 的月份差。

> `MONTHS_BETWEEN` 函数需要的是实际的日期值，而不是字符串。需要使用 `TO_DATE` 函数将字符串转换为日期，然后再计算月份差。



`MONTHS_BETWEEN` 函数的返回值包括了小数部分，表示不足一个月的部分。如果要得到整数的月份差，可以使用取整函数（如 `ROUND`、`FLOOR`、`CEIL` 等）对结果进行处理：

```sql
SELECT ROUND(MONTHS_BETWEEN(TO_DATE('2023-08-31', 'YYYY-MM-DD'),
                            TO_DATE('2022-01-15', 'YYYY-MM-DD'))) AS months_difference
FROM dual;
-- 输出：20
```



## 6.2、正则表达式

**匹配机制**

如果有一个字符串 aabcd，并指定了一个 `a(b|c)d` 搜索，则将搜索后跟 b 或 c，接着是 d 的 a。

<img src="!assets/Oracle/image-20221108142342799.png" alt="image-20221108142342799" style="zoom: 50%;" />

`a(b|c)d` 不匹配给定的字符串 aabcd。



**Oracle 数据库正则表达式中的 POSIX 元字符**

元字符是具有特殊意义的字符，如通配符字符、重复字符、非匹配字符或一个字符范围。

可以在与函数匹配的模式中使用多个预定义的元字符符号。

| **符号**    | **说明**                                                     |
| ----------- | ------------------------------------------------------------ |
| `*`         | 匹配零个或多个匹配项                                         |
| `|`         | 用于指定选择性匹配项的选择性运算符                           |
| `^/$     `  | 匹配行的开头和结尾                                           |
| `[]`        | 用于匹配列表（匹配该列表中的任何表达式）的方括号表达式       |
| `[^exp]`    | 如果脱字符位于方括号内部，则对表达式取非。                   |
| `{m}`       | 精确匹配 *m* 次                                              |
| `{m,n}`     | 至少匹配 *m* 次，但不超过 *n* 次                             |
| `[: :]`     | 指定一个字符类并匹配该类中的任何字符                         |
| `\`         | 可以有四种不同的含义：(1) 表示其自身；(2) 引用下一个字符；(3) 引入一个运算符；(4) 不执行任何操作 |
| `+`         | 匹配一个或多个匹配项                                         |
| ` `         | 匹配零个或一个匹配项                                         |
| `.`         | 匹配所支持字符集中的任何字符（NULL 除外）                    |
| `()`        | 对表达式进行分组（视作一个子表达式）                         |
| `\n`        | 向后引用表达式                                               |
| `[==]     ` | 指定等价类                                                   |
| `[..]     ` | 指定一个对照元素（如多字符元素）                             |



**Perl 正则表达式扩展**

除了 POSIX 标准以外，Oracle 还支持受 Perl 影响的常见元字符。如果您是一位生命科学开发人员，并使用 Perl 对大型 DNA 和蛋白质数据库中存储的生物信息数据进行模式分析，则可以使用 SQL 正则表达式直接支持数据，而无需中间层。这可以提供更高效的解决方案。为 Perl 兼容性而添加的元字符包括：

| **运算符** | **说明**                             |
| ---------- | ------------------------------------ |
| `\d`       | 匹配数字字符                         |
| `\D`       | 匹配非数字字符                       |
| `\w`       | 匹配单词字符                         |
| `\W`       | 匹配非单词字符                       |
| `\s`       | 匹配空白字符                         |
| `\S`       | 匹配非空白字符                       |
| `\A`       | 仅匹配字符串的开头                   |
| `\Z`       | 仅匹配字符串的结尾或者行结尾之前     |
| `\z`       | 仅匹配字符串的结尾                   |
| `* `       | 匹配 0 次或更多次（非贪婪）          |
| `+ `       | 匹配 1 次或更多次（非贪婪）          |
| `? `       | 匹配 0 次或 1 次（非贪婪）           |
| `{n} `     | 精确匹配 n 次（非贪婪）              |
| `{n,} `    | 至少匹配 n 次（非贪婪）              |
| `{n,m} `   | 至少匹配 n 次，但不超过 m 次（贪婪） |



Oracle 中的支持正则表达式的函数主要有以下五个：

- `REGEXP_LIKE` ：与 `LIKE` 的功能相似，可以支持按正则表达式与文本进行匹配。
- `REGEXP_INSTR` ：返回指定字符串中与正则表达式匹配部分第一次出现的位置。
- `REGEXP_COUNT` ：返回指定字符串中与正则表达式匹配部分出现的次数。
- `REGEXP_SUBSTR` ：截取指定字符串中与正则表达式匹配的部分。
- `REGEXP_REPLACE` ：替换指定字符串中与正则表达式匹配的部分。



### 6.2.1、REGEXP_LIKE

`REGEXP_LIKE()` 与 `LIKE` 的功能相似，可以支持按正则表达式与文本进行匹配。

```sql
REGEXP_LIKE(STRING, REGEX, MODIFIER)
```

- *STRING*：需要进行正则处理的字符串
- *REGEX*：进行匹配的正则表达式
- *MODIFIER*：模式，默认为 `c`：
  - `i`：不区分大小写进行检索。
  - `c`：区分大小写进行检索。



**示例：**

```sql
SELECT ENAME, JOB FROM EMP WHERE REGEXP_LIKE(JOB, '(clerk|analyst)', 'i');
```

结果：

```
+-------+---------+
| ENAME | JOB     |
+-------+---------+
| SMITH | CLERK   |
| JAMES | CLERK   |
| FORD  | ANALYST |
| SCOTT | ANALYST |
+-------+---------+
```



### 6.2.2、REGEXP_INSTR

`REGEXP_INSTR()` 返回指定字符串中与正则表达式匹配部分第一次出现的位置。

```sql
REGEXP_INSTR(STRING, REGEX[, START_POSITION[, OCCURRENCE[, RETURN_OPTION[, MODIFIER]]]])
```

- *STRING*：需要进行正则处理的字符串。
- *REGEX*：进行匹配的正则表达式。
- *START_POSITION*：起始位置，从字符串的第几个字符开始正则表达式匹配（默认从第一个字符开始，值为 1）。
- *OCCURRENCE*：获取分割出来的第几组子串（分割后最初的字符串会按分割的顺序排列成数组）。
- *RETURN_OPTION*：指定返回值的类型。为 0，则返回匹配值第一次出现的第一个字符的角标，非 0，则返回匹配值第一次出现的最后一个字符的角标 +1。
- *MODIFIER*：模式，默认为 `c`：
  - `c`：区分大小写进行检索。
  - `i`：不区分大小写进行检索。
  - `n`：允许句点 `.` 作为通配符去匹配换行符。如果省略该参数，句点将不匹配换行符。
  - `m`：将源串视为多行。即 Oracle 中将 `^` 和 `$` 分别看作源串中任意位置任何行的开始和结束，而不是仅仅看作整个源串的开始或结束。如果省略该参数，则 Oracle 将源串看作一行。
  - `x`：忽略源串中的空格字符。默认情况下，空格字符与自身相匹配。
- 返回值：返回满足正则表达式的字符或字符串第一次出现的角标，如果没有找到结果，则返回 0。



**示例：**

```sql
SELECT REGEXP_INSTR('11a22A33a', 'a') AS STR FROM DUAL;
-- 结果：3
```

```sql
SELECT REGEXP_INSTR('11a22A33a11a22A33a', '2A', 1, 1, 0, 'c') AS STR FROM DUAL;
-- 结果：5
```

```sql
SELECT REGEXP_INSTR('11a22A33a11a22A33a', '2A', 1, 1, 1, 'c') AS STR FROM DUAL;
-- 结果：7
```



### 6.3.3、REGEXP_COUNT

`REGEXP_COUNT()` 返回指定字符串中与正则表达式匹配部分出现的次数。

```sql
REGEXP_COUNT(STRING, REGEX[, START_POSITION[, MODIFIER]])
```

- *STRING*：需要进行正则处理的字符串。
- *REGEX*：进行匹配的正则表达式。
- *START_POSITION*：起始位置，从字符串的第几个字符开始正则表达式匹配（默认从第一个字符开始，值为 1）。
- *MODIFIER*：模式，默认为 `c`：
  - `c`：区分大小写进行检索。
  - `i`：不区分大小写进行检索。
  - `n`：允许句点 `.` 作为通配符去匹配换行符。如果省略该参数，句点将不匹配换行符。
  - `m`：将源串视为多行。即 Oracle 中将 `^` 和 `$` 分别看作源串中任意位置任何行的开始和结束，而不是仅仅看作整个源串的开始或结束。如果省略该参数，则 Oracle 将源串看作一行。
  - `x`：忽略源串中的空格字符。默认情况下，空格字符与自身相匹配。
- 返回值：返回满足正则表达式的字符或字符串出现的次数。



**示例：**

```sql
SELECT REGEXP_COUNT('11a22A33a11a22A33a', '2A', 1, 'c') AS STR FROM DUAL;
-- 结果：2
```



### 6.2.4、REGEXP_SUBSTR

`REGEXP_SUBSTR()` 截取指定字符串中与正则表达式匹配的部分。

```sql
REGEXP_SUBSTR(STRING, REGEX[, START_POSITION[, OCCURRENCE[, MODIFIER]]])
```

- *STRING*：需要进行正则处理的字符串。
- *REGEX*：进行匹配的正则表达式。
- *START_POSITION*：起始位置，从字符串的第几个字符开始正则表达式匹配（默认从第一个字符开始，值为 1）。
- *OCCURRENCE*：获取分割出来的第几组子串（分割后最初的字符串会按分割的顺序排列成数组）。
- *MODIFIER*：模式，默认为 `c`：
  - `c`：区分大小写进行检索。
  - `i`：不区分大小写进行检索。
  - `n`：允许句点 `.` 作为通配符去匹配换行符。如果省略该参数，句点将不匹配换行符。
  - `m`：将源串视为多行。即 Oracle 中将 `^` 和 `$` 分别看作源串中任意位置任何行的开始和结束，而不是仅仅看作整个源串的开始或结束。如果省略该参数，则 Oracle 将源串看作一行。
  - `x`：忽略源串中的空格字符。默认情况下，空格字符与自身相匹配。



**示例：**

```sql
SELECT REGEXP_SUBSTR('11a22A33a', '[^A]+', 1, 1, 'i') AS STR FROM DUAL;
-- 结果：11
```

分析：正则表达式是以 A 为标识进行分割，而 `i` 标识不区分大小写，从第一个字符开始，取第一组截取结果，所以结果是 11，而不是 11a22。

```sql
SELECT REGEXP_SUBSTR('11a22A33a', '[^A]+', 1, 1, 'c') AS STR FROM DUAL;
-- 结果：11a22
```

分析：正则表达式是以 A 为标识进行分割，而 `c` 标识区分大小写，从第一个字符开始，取第一组截取结果，所以结果是 11a22，而不是 11。

```sql
SELECT REGEXP_SUBSTR('11a22A33a', '[^A]+', 4, 1, 'i') AS STR FROM DUAL;
-- 结果：22
```

分析：正则表达式是以 A 为标识进行分割，而 `i` 标识不区分大小写，从第 4 个字符开始，取第一组截取结果，所以结果是 22，而不是 11。

```sql
SELECT REGEXP_SUBSTR('11a22A33a', '[^A]+', 4, 1, 'c') AS STR FROM DUAL;
-- 结果：22
```

分析：正则表达式是以 A 为标识进行分割，而 `c` 标识区分大小写，从第 4 个字符开始，取第一组截取结果，所以结果是 22，而不是 11a22。



### 6.2.5、REGEXP_REPLACE

`REGEXP_REPLACE()` 替换指定字符串中与正则表达式匹配的部分。

```sql
REGEXP_REPLACE(STRING, REGEX, REPLACE_STRING)
```

- *STRING*：需要进行正则处理的字符串。
- *REGEX*：进行匹配的正则表达式。
- *REPLACE_STRING*：要替换成的字符串。



**示例：**

```sql
SELECT REGEXP_REPLACE('11a22A33a', 'a', '') AS STR FROM DUAL;
-- 结果：1122A33
```

```sql
SELECT REGEXP_REPLACE('11a22A33a11a22A33a', '[^A]+', '#') AS STR FROM DUAL;
-- 结果：#A#A#
```



## 6.3、聚合函数

### 6.3.1、WM_CONCAT

Oracle 的 `WM_CONCAT` 函数是一个已废弃的函数，用于将多个行的值合并成一个单一字符串。它类似于 `LISTAGG` 函数，但不像 `LISTAGG` 那样强大和灵活。`WM_CONCAT` 函数在较早版本的 Oracle 数据库中存在，但在 Oracle 11g 之后的版本中被弃用。

```sql
WM_CONCAT(separator, colname)
```

用指定的 *separator* 做分隔符，连接 *colname* 中的值。

参数：

- *separator*：必填。STRING 类型常量，分隔符。
- *colname*：必填。STRING 类型。如果输入为 BIGINT、DOUBLE 或 DATETIME 类型，会隐式转换为 STRING 类型后参与运算。

返回 STRING 类型。返回规则如下：

- *separator* 非 STRING 类型常量时，返回报错。
- *colname* 非 STRING、BIGINT、DOUBLE 或 DATETIME 类型时，返回报错。
- *colname* 值为 NULL 时，该行不会参与计算。

> `select wm_concat(',', name) from table_name;` 语句中，如果 `table_name` 为空集合，该语句返回 NULL 值。



示例 1：对所有职工的姓名（ename）进行合并。命令示例如下：

```sql
SELECT WM_CONCAT(',', ename) FROM emp;
```

返回结果如下：

```
+------------+
| _c0        |
+------------+
| SMITH,ALLEN,WARD,JONES,MARTIN,BLAKE,CLARK,SCOTT,KING,TURNER,ADAMS,JAMES,FORD,MILLER,JACCKA,WELAN,TEBAGE |
+------------+
```

示例 2：与 `GROUP BY` 配合使用，对所有职工按照部门（deptno）进行分组，并将同组的职工姓名（ename）进行合并。命令示例如下：

```sql
SELECT deptno, WM_CONCAT(',', ename) FROM emp GROUP BY deptno ORDER BY deptno;
```

返回结果如下：

```
+------------+------------+
| deptno     | _c1        |
+------------+------------+
| 10         | CLARK,KING,MILLER,JACCKA,WELAN,TEBAGE |
| 20         | SMITH,JONES,SCOTT,ADAMS,FORD |
| 30         | ALLEN,WARD,MARTIN,BLAKE,TURNER,JAMES |
+------------+------------+
```

示例 3：与 `GROUP BY` 配合使用，对所有职工按照部门（deptno）进行分组，并将同组的薪资（sal）去重后进行合并。命令示例如下：

```sql
SELECT deptno, WM_CONCAT(DISTINCT ',', sal) FROM emp GROUP BY deptno ORDER BY deptno;
```

返回结果如下：

```
+------------+------------+
| deptno     | _c1        |
+------------+------------+
| 10         | 1300,2450,5000 |
| 20         | 1100,2975,3000,800 |
| 30         | 1250,1500,1600,2850,950 |
+------------+------------+
```



### 6.3.2、LISTAGG

Oracle 的 `LISTAGG` 函数是一个用于将多个行的值合并成一个单一字符串的聚合函数。它可以用于将查询结果中的多个行的某一列的值连接在一起，并以指定的分隔符分隔它们。

```sql
LISTAGG(column_name, delimiter) WITHIN GROUP (ORDER BY order_column)
```

- *column_name*：要连接的列的名称。
- *delimiter*：用于分隔连接值的字符串。
- *ORDER BY order_column*：用于指定连接值的顺序。



假设我们有一个名为 `employees` 的表，其中包含员工的姓名和部门：

```sql
SELECT department, LISTAGG(employee_name, ', ') WITHIN GROUP (ORDER BY employee_name) AS employees_list
FROM employees
GROUP BY department;
```

这个查询将会按部门分组，并将每个部门中的员工姓名连接成一个以逗号和空格分隔的字符串。



## 6.4、判空

### 6.4.1、NVL

Oracle `NVL` 函数的格式如下：

```sql
NVL(expr1,expr2)
```

如果 *expr1* 为空那么显示 *expr2* 的值，如果 *expr1* 的值不为空，则显示 *expr1*本来的值。



## 6.5、类型转换

### 6.5.1、TO_CHAR

Oracle `TO_CHAR` 函数用于将特定数据类型的值，通常是日期或数字，转换为格式化的字符串表示。

```sql
TO_CHAR(value, format)
```

- *value*：要转换的值，可以是日期、数字或其他支持的数据类型。
- *format*：一个字符串，指定了希望将值转换成的格式。对于日期和数字，这个格式字符串可以包含不同的占位符来表示年、月、日、小时、分钟、秒等等。



日期/时间转字符串：

```sql
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') FROM DUAL;
-- 输出类似：'2023-08-31 15:30:00'
```

将数字转换为带有千位分隔符的字符串：

```sql
SELECT TO_CHAR(1234567.89, '9,999,999.99') FROM DUAL;
-- 输出：'1,234,567.89'
```

格式化货币金额：

```sql
SELECT TO_CHAR(1234.56, '$9,999.99') FROM DUAL;
-- 输出：'$1,234.56'
```



### 6.5.2、TO_DATE

Oracle `TO_DATE` 函数用于将一个字符串值转换为日期类型。

```sql
TO_DATE(string, format)
```

- *string*：要转换为日期的字符串。
- *format*：一个字符串，指定了输入字符串的格式，以便正确解析为日期。



将字符串日期转换为日期类型：

```sql
SELECT TO_DATE('2023-08-31', 'YYYY-MM-DD') FROM DUAL;
-- 输出：2023-08-31
```

指定小时、分钟和秒的时间戳转换：

```sql
SELECT TO_DATE('2023-08-31 15:30:00', 'YYYY-MM-DD HH24:MI:SS') FROM DUAL;
-- 输出：2023-08-31 15:30:00
```



### 6.5.3、CAST

Oracle `CAST()` 是一个内置函数，它将给定的参数从一种类型转换为另一种类型。此函数支持基本数据类型和集合类型。

```sql
CAST({expr AS type_name [ DEFAULT return_value ON CONVERSION ERROR ] [, fmt [, 'nlsparam' ] ])
```

- *expr*：要转换为不同数据类型的值或表达式，可以是内置数据类型、集合类型或 ANYDATA 类型的实例。
- *type_name*：要将表达式转换为的目标数据类型。
- *DEFAULT return_value ON CONVERSION ERROR*：可选的。它允许您指定在转换发生错误时返回的值。
- *fmt*：可选的。格式化字符串。
- *'nlsparam'* ：可选的。你可以使用这个 `'NLS_DATE_LANGUAGE = language'` 形式设置此参数，其中 `language` 是语言名称。



字符串日期转为 TIMESTAMP 类型：

```sql
ALTER SESSION SET NLS_TIMESTAMP_FORMAT = 'YYYY-MM-DD HH24:MI:SSXFF';
SELECT
    CAST('2023-02-28' AS TIMESTAMP, 'YYYY-MM-DD') Result
FROM dual;
-- 输出：2023-02-28 00:00:00.000000000
```

指定语言以按照指定的语言进行转换：

```sql
SELECT CAST(
        '28-Feb-23' AS TIMESTAMP,
        'DD-MON-YY',
        'NLS_DATE_LANGUAGE = American'
    ) Result
FROM dual;
-- 输出：2023-02-28 00:00:00.000000000

SELECT CAST(
        '20-2月 -23' AS TIMESTAMP,
        'DD-MON-YY',
        'NLS_DATE_LANGUAGE = ''Simplified Chinese'''
    ) Result
FROM dual;
-- 输出：20-2月 -23 12.00.00.000000000 上午
```



# 7、Spring Boot 集成

## 7.1、集成步骤

**Spring Boot 配置 Oracle19c 数据库**

1. pom.xml 文件添加依赖包：

   ```xml
   <!-- Oracle数据库驱动 -->
   <dependency>
   	<groupId>com.oracle.ojdbc</groupId>
   	<artifactId>ojdbc8</artifactId>
   	<version>19.3.0.0</version>
   </dependency>
   
   <!-- 这里使用的是JDBC包，也可使用JPA包进行 -->
   <dependency>
   	<groupId>org.springframework.boot</groupId>
   	<artifactId>spring-boot-starter-jdbc</artifactId>
   </dependency>
   
   <!--        <dependency>-->
   <!--            <groupId>org.springframework.boot</groupId>-->
   <!--            <artifactId>spring-boot-starter-data-jpa</artifactId>-->
   <!--        </dependency>-->
   
   <!-- druid -->
   <dependency>
   	<groupId>com.alibaba</groupId>
   	<artifactId>druid</artifactId>
   	<version>1.1.16</version>
   </dependency>
   ```

2. application.yml 配置文件加入 Oracle 数据库信息：

   ```yaml
   spring:
     application:
       name: cloud-payment-service # 服务名称
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource # 当前数据源操作类型
       driver-class-name: oracle.jdbc.OracleDriver # Oracle数据库驱动包
       url: jdbc:oracle:thin:@//localhost:1521/ORCL
       username: yma
       password: 123456
   ```

   

## 7.2、遇到的问题

### 7.2.1、ORA-28040: 没有匹配的验证协议

可能的原因有：



**连接数据库驱动和 Oracle 版本不一致**

可以通过下载新的驱动解决，也可以使用修改配置的方式。

1. 在 Oracle 的安装路径下找到 sqlnet.ora 文件，在文件的最后添加如下配置：

   ```
   SQLNET.ALLOWED_LOGON_VERSION=8
    
   SQLNET.ALLOWED_LOGON_VERSION_SERVER=8
    
   SQLNET.ALLOWED_LOGON_VERSION_CLIENT=8
   ```

2. 重启 Oracle 数据库服务，主要是监听，不过最好都重启一下。

3. 此时通过 Java 连接出现 `java.sql.SQLException: ORA-01017: 用户名/口令无效; 登录被拒绝 `，但是通过 SQL Developer 又能正常连接。解决办法就是重新修改用户的密码即可：

   ```sql
   sqlplus / as sysdba;
   
   alter user <用户名> identified by <密码>;
   ```

4. 此时再次连接即可正常连接了。



**加密机制问题**

默认情况下 Oracle 19c 将使用新的加密机制（SHA-2）而非以前版本的（SHA-1）。你需要禁用 SHA-2 并强制使用 SHA-1 加密机制，可以通过在 JDBC URL 参数中添加 `?oracle.net.disableOob=true` 来实现。

例如，你可以将 dbURL 修改成以下格式：

```
jdbc:oracle:thin:@//localhost:1521/ORCL?oracle.net.disableOob=true
```



### 7.2.2、NL Exception was generated

出现这个错误，主要是数据库配置文件中 url 字符串写错导致的。仔细检查 url 字符串，问题基本就解决了。
