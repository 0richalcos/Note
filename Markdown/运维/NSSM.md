# NSSM

## 1、什么是 NSSM？

NSSM 是一个开源的服务封装工具，全称是 "Non-Sucking Service Manager"（意为 “不那么蹩脚的服务管理器”）。它的主要目的是将普通的、非设计为 Windows 服务的可执行程序（`.exe`、脚本等）封装成标准的 Windows 服务。这样，这些程序就可以像其他系统服务一样，在后台运行、随系统启动、自动重启、并由 Windows 服务管理器（`services.msc`）进行管理。



## 2、为什么需要 NSSM？

Windows 自带了一个命令行工具 `sc.exe`（Service Control），也可以用来创建和管理服务。那么为什么还需要 NSSM 呢？

当你需要将一个不是为 Windows 服务设计的应用程序（例如一个 Node.js 服务器、一个 Python 脚本、一个 Java.jar 程序、一个 Go 应用、甚至一个批处理脚本）作为后台服务可靠地运行时，NSSM 是一个比 `sc.exe` 更简单、更健壮、功能更丰富的选择。它极大地简化了配置过程，并提供了关键的进程监控和自动重启能力。



**`sc.exe` 的局限性**

1.  主要面向原生服务： `sc.exe` 主要设计用于管理那些本身就按照 Windows 服务规范（实现了 ServiceMain 函数等 SCM API）编写的程序。对于普通的控制台应用或脚本，直接用 `sc.exe` 创建服务往往无法正常工作或难以管理其生命周期。
2.  缺乏进程监控和自动重启： 如果一个普通程序（非原生服务）通过 `sc.exe` 启动后意外崩溃或退出了，`sc.exe` 创建的服务状态会变为 “已停止”，但它不会自动尝试重启这个程序。你需要依赖 Windows 服务本身的恢复选项，但这对于非原生服务可能不够可靠。
3.  复杂的配置： 使用 `sc.exe` 创建服务时，配置项（如工作目录、参数、环境变量等）需要通过命令行参数设置，语法相对繁琐且容易出错。
4.  I/O 重定向困难： 普通程序运行时的标准输出（stdout）和标准错误（stderr）通常会显示在控制台窗口。当它们作为服务在后台运行时，这些输出默认会丢失。使用 `sc.exe` 很难方便地将这些输出重定向到日志文件。
5.  优雅关闭问题： 当服务停止时，`sc.exe` 默认可能直接终止进程（`TerminateProcess`），这可能导致数据丢失或状态不一致。它缺乏简单的方法来发送更温和的关闭信号（如 `Ctrl+C`）。



**NSSM 的优势**

1.  专为封装普通程序设计： NSSM 本身作为一个轻量级的服务程序运行，它的核心任务是启动、监控和管理你指定的那个目标应用程序。它充当了目标程序和 Windows 服务管理器之间的桥梁。
2.  强大的进程监控和自动重启： 这是 NSSM 的核心优势。NSSM 会持续监控它所启动的目标程序。如果目标程序意外退出（崩溃、被杀死等），NSSM 会检测到，并根据你的配置自动尝试重新启动它，大大提高了应用程序的可用性。
3.  简单易用的 GUI 和 CLI： NSSM 提供了图形用户界面（GUI），使得创建和编辑服务配置变得非常直观。同时，它也支持完整的命令行接口（CLI），方便自动化和脚本操作。
4.  方便的 I/O 重定向： NSSM 可以轻松地将目标程序的标准输出和标准错误重定向到指定的日志文件，并支持日志轮换（Rotation），防止日志文件无限增大。
5.  更优雅的关闭机制： NSSM 允许你配置多种方式来尝试关闭目标应用程序（发送 `Ctrl+C`、发送 `WM_CLOSE` 消息、发送 `WM_QUIT` 消息），在强制终止进程之前给应用程序一个“优雅退出”的机会。
6.  环境管理： 可以方便地为服务设置自定义的环境变量。
7.  依赖管理： 可以设置服务的依赖关系。
8.  其他高级功能： 如设置进程优先级、CPU 亲和性、应用程序目录等。



## 3、获取和准备 NSSM

1.  访问 NSSM [官方网站](https://nssm.cc/)。
2.  通常下载最新的稳定版本（stable release），下载完成你会得到一个 zip 压缩包。
3.  解压 zip 文件你会看到包含 `nssm.exe` 的文件夹，通常有 win32 和 win64 两个子目录。根据你的 Windows 系统是 32 位还是 64 位，选择对应的 `nssm.exe`。
4.  将 `nssm.exe` 复制到一个系统 `PATH` 环境变量包含的目录中，例如 `C:\Windows\System32`（不推荐直接放这里，可能会与系统文件混淆）或者创建一个专门的工具目录（如 `C:\Tools`）并将这个目录添加到系统 `PATH` 中。这样你可以在任何路径下直接运行 `nssm` 命令。

> [!IMPORTANT]
>
> 所有 NSSM 的操作（安装、删除、编辑、启动、停止服务）都需要管理员权限。请确保你使用 “以管理员身份运行” 的命令提示符 (cmd) 或 PowerShell 窗口。



## 4、使用 NSSM

### 4.1、使用 GUI

运行 `nssm install <ServiceName>` 会打开配置界面，`nssm edit <ServiceName>` 会打开已存在服务的配置界面。

配置完成所需信息后，点击 `Install service` 或 `Edit service` 保存。



#### 4.1.1、Application

Application（应用程序）- 核心配置

- Path（路径）：

	用途：指定要运行的主程序（`.exe` 或解释器）。

	建议：必需配置。使用绝对路径，避免依赖 PATH 环境变量可能导致的问题。点击...选择。

	示例：`C:\Program Files\Java\jdk-17\bin\java.exe`、`C:\MyApp\myapp.exe`、`C:\Python310\python.exe`

- Startup directory（启动目录）：

	用途：程序的工作目录。

	建议：强烈推荐配置。设置为你的应用程序根目录或需要查找配置/资源文件的目录，确保路径正确，否则可能导致 “找不到文件” 错误。

	示例：`C:\MyApp\`、`C:\MyJavaService\`

- Arguments（参数）：

	用途：传递给 `Path` 中程序的命令行参数。

	建议：根据需要配置。对于 Java JAR，通常是 JVM 参数 + `-jar myapp.jar` + 应用参数；对于脚本，是脚本文件名 + 脚本参数。

	示例：

	- Java：`-Xmx512m -Dfile.encoding=UTF-8 -jar my-service.jar --server.port=8080`
	- Python：`main.py --config /etc/myapp.conf`
	- Batch：`/C run_job.bat`（当 `Path` 是 `cmd.exe` 时）



#### 4.1.2、Details

Details（详细信息）- 服务标识

- Display name（显示名称）：

	用途：services.msc 中显示的名称。

	建议：强烈推荐配置。使用清晰、易懂的名称。

	示例：`My Production Web Service，Data Processing Job Runner`

- Description（描述）：

	用途：服务的详细说明。

	建议：推荐配置。简要说明服务的功能，方便他人理解。

	示例：`Handles user authentication and profile management APIs，Runs daily data aggregation script`

- Startup type（启动类型）：

	用途：通过下拉框设置服务的启动方式。

	建议：根据服务需求选择：

	- Automatic（自动）：强烈推荐用于需要随系统启动的关键服务。
	- Automatic (Delayed Start)（自动（延迟启动））：推荐用于非启动关键路径的服务。
	- Manual（手动）：按需配置。用于手动启动或依赖触发。
	- Disabled（禁用）：按需配置。临时禁止服务。



#### 4.1.3、Log on

Log on（登录）- 运行身份

指定服务运行账户：

- Local System account（本地系统账户）：适用于大多数不需要特殊权限（如访问特定用户目录或网络共享）的本地服务。通常保持默认即可。
	- Allow service to interact with desktop：强烈不推荐勾选。
- This account（此账户）：仅在服务需要访问特定网络资源（需域用户权限）或需要以特定用户身份运行时配置。需要提供用户名和密码。谨慎使用。



#### 4.1.4、Dependencies

Dependencies（依赖项）- 服务启动顺序

用途：定义此服务启动前必须先运行的其他 Windows 服务。

建议：按需配置。如果你的应用程序依赖于数据库服务（如 MSSQLSERVER、MySQL）或消息队列服务等，在此处填入它们的服务名称（是内部服务名，不是显示名称），每行一个。Windows 会确保依赖项先启动。如果无特定依赖，保持为空。

示例：

```
MSSQLSERVER
RabbitMQ
```



#### 4.1.5、Process

Process（进程）- 性能与资源

- Priority（优先级）：

	用途：设置进程调度优先级。

	建议：通常保持默认 `Normal`。除非有明确的性能调优需求，否则不建议修改。

- Affinity（亲和性）：

	用途：绑定 CPU 核心。

	建议：通常保持默认 `All processors`。仅在特殊性能场景或需要隔离时配置。

- Console window：

	用途：显示控制台窗口。

	建议：强烈不推荐勾选，仅用于临时调试时勾选。



#### 4.1.6、Shutdown

Shutdown（关闭）- 优雅退出

- Generate Control-C：

	用途：停止服务时，向程序发送 `CTRL+C` 信号。

	建议：推荐勾选。对多数控制台应用有效。

- Send WM_CLOSE message：

	用途：向程序主窗口（如果有）发送关闭消息。

	建议：按需勾选。对 GUI 应用可能有效。

- Send WM_QUIT message：

	用途：向程序线程发送退出消息。

	建议：按需勾选。

- Termination timeout（终止超时）：

	用途：为上述每种方法设置超时时间（毫秒）。 NSSM 会按顺序尝试勾选的方法，在超时后尝试下一种或最终强制终止。

	建议：通常保持默认（如 1500 ms），可按需增加。



#### 4.1.7、Exit actions

Exit actions（退出操作）- 可靠性核心

- Delay restart if application runs for less than ... ms（延迟重启）：

	用途：程序退出后的等待时间。

	建议：强烈推荐配置（如 1500 到 5000 毫秒）。

- Action to take when application exits other than in response to a contolled service shutdown（退出时的操作）：

	用途：应用程序退出时采取的操作（响应受控服务关闭除外）。

	建议：推荐勾选 Restart application。

	- Delay restart by ... ms：延迟重启时间。



#### 4.1.8、I/O

I/O（输入/输出）- 日志捕获

- Input (stdin)：

	用途：提供标准输入。

	建议：通常无需配置，保持为空。

- Output (stdout)（标准输出）：

	用途：捕获程序的标准输出。

	建议：强烈推荐配置。指向一个日志文件，用于记录程序正常运行信息。确保日志目录存在且服务账户有写入权限。

	示例：`C:\MyApp\logs\stdout.log`

- Error (stderr)（标准错误）：

	用途：捕获程序的标准错误输出。

	建议：强烈推荐配置。这是调试服务启动失败或运行错误的关键。指向一个单独的日志文件。确保日志目录存在且服务账户有写入权限。

	示例：`C:\MyApp\logs\stderr.log`



#### 4.1.9、File rotation

ile rotation（文件轮换）- 日志管理

- Replace existing Output and/or Error files：不推荐勾选。在启动时覆盖现有输出文件服务。

- Rotate files（轮换文件）：

	用途：启用日志轮换。

	建议：强烈推荐勾选，以防止日志文件无限增长。

	- Rote while servie is running：

		用途：在服务运行时轮转已达到指定大小限制的文件。

		建议：推荐勾选。

	- Rotate files older than ... seconds：

		用途：基于时间的轮换。

		建议：按需配置。如果日志量不大，按天轮换更合适，可以设置（如 86400 秒 = 1 天）。

	- Rotate files larger than ... bytes：

		用途：基于大小的轮换。

		建议：推荐配置。这是最常用的方式。根据预期日志量设置一个合理的大小（如 10485760 = 10MB，52428800 = 50MB）。



#### 4.1.10、Environment

Environment（环境）- 运行环境

- Environment variables：

	用途：设置服务特定的环境变量。

	建议：根据需要配置。非常适合设置应用环境标识（如 `NODE_ENV=production`、`SPRING_PROFILES_ACTIVE=prod`）、配置路径、Java 选项（JAVA_OPTS）等，而无需修改系统全局变量。格式 `Key=Value`，每行一个。

	示例：

	```
	JAVA_OPTS=-Xms256m -Xmx1024m
	APP_CONFIG_PATH=C:\MyApp\config\production.properties
	```

- Replace default environment：

	用途：不继承系统环境变量。

	建议：强烈不推荐勾选，除非你完全清楚这样做的后果。



### 4.2、使用 CLI

CLI 适合脚本自动化或熟悉命令行的用户。



#### 4.2.1、安装服务

安装命令为：

```shell
nssm install <ServiceName> <ApplicationPath> [Arguments]
```

示例：安装一个 Node.js 应用

```shell
nssm install MyNodeApp "C:\Program Files\nodejs\node.exe" "C:\path\to\my\app\server.js"
```



#### 4.2.2、设置服务参数

配置命令为（安装后配置）：

```sehll
nssm set <ServiceName> <Parameter> <Value>
```

常用参数：

- `AppDirectory`：设置工作目录

	```shell
	nssm set MyNodeApp AppDirectory "C:\path\to\my\app"
	```

- `DisplayName`：设置显示名称

	```shell
	nssm set MyNodeApp DisplayName "My Node.js Web Application"
	```

- `Description`：设置描述

	```shell
	nssm set MyNodeApp Description "Runs the main web application server."
	```

- `AppStdout`：设置标准输出日志文件：

	```shell
	nssm set MyNodeApp AppStdout "C:\path\to\my\app\logs\stdout.log"
	```

- `AppStderr`：设置标准错误日志文件

	```shell
	nssm set MyNodeApp AppStderr "C:\path\to\my\app\logs\stderr.log"
	```

- `AppRotateFiles`：启用日志轮换 (1 表示启用)

	```shell
	nssm set MyNodeApp AppRotateFiles 1
	```

- `AppRotateBytes`：设置日志轮换大小（单位：字节）

	```shell
	# 10MB
	nssm set MyNodeApp AppRotateBytes 10485760
	```

- `AppRestartDelay`：设置重启延迟（单位：毫秒）

	```shell
	# 2 seconds
	nssm set MyNodeApp AppRestartDelay 2000 
	```

- `Start`：设置服务启动类型（`SERVICE_AUTO_START`、`SERVICE_DEMAND_START`、`SERVICE_DISABLED`）

	```shell
	# 设置为自动启动
	nssm set MyNodeApp Start SERVICE_AUTO_START 
	```

- `DependOnService`：添加服务依赖（可以多次使用添加多个依赖）

	```shell
	nssm set MyNodeApp DependOnService AnotherService
	```

- `Environment`：设置环境变量（格式为 `Key=Value` 或 `Key=Value\nKey2=Value2`，`\n` 为换行）

	```shell
	nssm set MyNodeApp Environment "NODE_ENV=production\nPORT=80"
	```



#### 4.2.3、管理服务状态

启动服务：

```shell
nssm start <ServiceName>
```

停止服务：

```shell
nssm stop <ServiceName>
```

重启服务：

```shell
nssm restart <ServiceName>
```

查看服务状态：

```shell
nssm status <ServiceName>
```



#### 4.2.4、获取服务参数

获取服务参数命令为：

```shell
nssm get <ServiceName> <Parameter>
```

示例：获取工作目录

```shell
nssm get MyNodeApp AppDirectory
```



#### 4.2.5、删除服务

删除服务命令为：

```shell
nssm remove <ServiceName> confirm
```

需要加上 `confirm` 参数以避免误删。



### 4.3、使用 Windows 服务管理器 

一旦使用 NSSM 创建了服务，它就会出现在标准的 Windows 服务列表中（按 Win+R，输入 `services.msc` 并回车）。你可以像管理其他任何 Windows 服务一样，在这里：

- 启动、停止、重启 服务。
- 查看服务的状态（正在运行、已停止）。
- 修改服务的启动类型（自动、手动、禁用）。
- 配置服务的恢复选项（虽然 NSSM 自身的重启机制通常更适合）。
- 查看服务的依赖关系。

> [!CAUTION]
>
> 在 `services.msc` 中修改服务的 “可执行文件路径” 等核心属性并不会更新 NSSM 的配置。要修改程序的路径、参数、工作目录等，必须使用 `nssm edit <ServiceName>` 或 `nssm set ...` 命令。