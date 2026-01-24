# WSL

## 1、概述

适用于 Linux 的 Windows 子系统（WSL）可让开发人员直接在 Windows 上按原样运行 GNU/Linux 环境（包括大多数命令行工具、实用工具和应用程序），且不会产生传统虚拟机或双启动设置开销。



### 1.1、什么是 WSL

**什么是适用于 Linux 的 Windows 子系统？**

适用于 Linux 的 Windows 子系统（WSL）是 Windows 的一项功能，可用于在 Windows 计算机上运行 Linux 环境，而无需单独的虚拟机或双引导。 WSL 旨在为希望同时使用 Windows 和 Linux 的开发人员提供无缝高效的体验。

- 使用 WSL 安装和运行各种 Linux 发行版，例如 Ubuntu、Debian、Kali 等。 安装 Linux 发行版并从 Microsoft Store 接收自动更新、导入 Microsoft Store 中没有的 Linux 发行版，或构建你自己的定制 Linux 发行版。
- 将文件存储在独立的 Linux 文件系统中，具体取决于安装的发行版。
- 运行命令行工具，例如 BASH。
- 运行常用的 BASH 命令行工具（例如 `grep`、`sed`、`awk`）或其他 ELF-64 二进制文件。
- 运行 Bash 脚本和 GNU/Linux 命令行应用程序，包括：
	- 工具：vim、emacs、tmux
	- 语言：NodeJS、JavaScript、Python、Ruby、C/C++、C# 和 F#、Rust、Go 等。
	- 服务：SSHD、MySQL、Apache、lighttpd、MongoDB、PostgreSQL。
- 使用自己的 GNU/Linux 分发包管理器安装其他软件。
- 使用类似于 Unix 的命令行 shell 调用 Windows 应用程序。
- 在 Windows 上调用 GNU/Linux 应用程序。
- 运行直接集成到 Windows 桌面的 GNU/Linux 图形应用程序
- 使用你的设备 GPU 加速 Linux 上运行的机器学习工作负载。



**什么是 WSL 2？**

安装 Linux 发行版时，WSL 2 是默认发行版类型。 WSL 2 使用虚拟化技术在轻量级实用工具虚拟机（VM）中运行 Linux 内核。 Linux 发行版作为独立的容器在 WSL 2 托管 VM 内运行。 通过 WSL 2 运行的 Linux 发行版将共享同一网络命名空间、设备树（而非 `/dev/pts`）、CPU/内核/内存/交换空间、`/init` 二进制文件，但有自己的 PID 命名空间、装载命名空间、用户命名空间、Cgroup 命名空间和 `init` 进程。

WSL 2 提高了文件系统性能，并且与 WSL 1 体系结构相比增加了完整的系统调用兼容性。

单个 Linux 分发版可以在 WSL 1 或 WSL 2 体系结构中运行。 每个分发版可随时升级或降级，并且你可以并行运行 WSL 1 和 WSL 2 分发版。



### 1.2、比较 WSL 版本

#### 1.2.1、比较 WSL 1 和 WSL 2

WSL 1 和 WSL 2 之间的主要区别在于，在托管 VM 内使用实际的 Linux 内核、支持完整的系统调用兼容性以及跨 Linux 和 Windows 操作系统的性能。 WSL 2 是安装 Linux 发行版时的当前默认版本，它使用最新最好的虚拟化技术在轻量级实用工具虚拟机（VM）内运行 Linux 内核。 WSL2 将 Linux 发行版作为托管 VM 内的隔离容器运行。

| 功能                                           | WSL 1 | WSL 2 |
| :--------------------------------------------- | :---- | :---- |
| Windows 和 Linux 之间的集成                    | ✅     | ✅     |
| 启动时间短                                     | ✅     | ✅     |
| 与传统虚拟机相比，占用的资源量少               | ✅     | ✅     |
| 可以与当前版本的 VMware 和 VirtualBox 一起运行 | ✅     | ❌     |
| 托管 VM                                        | ❌     | ✅     |
| 完整的 Linux 内核                              | ❌     | ✅     |
| 完全的系统调用兼容性                           | ❌     | ✅     |
| 跨 OS 文件系统的性能                           | ✅     | ❌     |
| systemd 支持                                   | ❌     | ✅     |
| IPv6 支持                                      | ✅     | ✅     |

从上面的比较表中可以看出，WSL 2 架构在几个方面优于 WSL 1，但跨 OS 文件系统的性能除外，对于这种情况，可通过将项目文件存储在与处理项目时运行的工具相同的操作系统上进行处理。

WSL 2 仅在 Windows 11 或 Windows 10 版本 1903、内部版本 18362 或更高版本中可用。 通过按 Windows 徽标键 + R，检查你的 Windows 版本，然后键入 winver，选择 “确定”。 （或者在 Windows 命令提示符下输入 `ver` 命令）。 你可能需要更新到最新的 Windows 版本。 低于 14393 的版本完全不支持 WSL。



#### 1.2.2、WSL 2 中的新增功能

WSL 2 是对基础体系结构的一次重大改造，它使用虚拟化技术和 Linux 内核来实现其新功能。 此更新的主要目标是提高文件系统性能和添加完全的系统调用兼容性。



**WSL 2 体系结构**

传统的 VM 体验可能启动速度慢，是独立的，消耗大量资源，需要你花费时间进行管理。 WSL 2 没有这些属性。

WSL 2 有 WSL 1 的优点，包括 Windows 和 Linux 之间的无缝集成，启动时间短，资源占用量少，并且无需 VM 配置或管理。 虽然 WSL 2 确实使用 VM，但 VM 是在幕后管理和运行的，因此你将具有与 WSL 1 相同的用户体验。



**完整的 Linux 内核**

WSL 2 中的 Linux 内核是 Microsoft 根据最新的稳定版分支（基于 kernel.org 上提供的源代码）构建的。此内核已专门针对 WSL 2 进行了调整，针对大小和性能进行了优化，以便在 Windows 上提供良好的 Linux 体验。 内核将由 Windows 更新提供服务，这意味着你将获得最新的安全修补程序和内核改进功能，而无需自行管理它。



**提升了文件 IO 性能**

如果使用 WSL 2，文件密集型操作（如 git 克隆、npm 安装、apt 更新、apt 升级等）的速度都明显更快。

实际的速度提升将取决于你运行的应用以及它与文件系统的交互方式。 在对压缩的 tarball 进行解包时，WSL 2 的初始版本的运行速度比 WSL 1 快达 20 倍，在各种项目上使用 git 克隆、npm 安装和 cmake 时，大约快 2-5 倍。



**完全的系统调用兼容性**

Linux 二进制文件使用系统调用来执行访问文件、请求内存、创建进程等功能。 虽然 WSL 1 使用的是由 WSL 团队构建的转换层，但 WSL 2 包括了自己的 Linux 内核，具有完全的系统调用兼容性。 优点包括：

- 可以在 WSL 内部运行的一组全新应用，例如 Docker 等。
- 对 Linux 内核的任何更新都立即可供使用。



#### 1.2.3、例外情况

我们建议使用 WSL 2，因为它提供更快的性能和100% 的系统调用兼容性。 但是，在某些特定情况下，你可能会更倾向于使用 WSL 1。 在以下情况下，请考虑使用 WSL 1：

- 你的项目文件必须存储在 Windows 文件系统中。 WSL 1 可以更快地访问从 Windows 装载的文件。

	如果你将使用 WSL Linux 分发版来访问 Windows 文件系统上的项目文件，并且这些文件无法存储在 Linux 文件系统上，那么，通过使用 WSL 1，你将跨 OS 文件系统实现更快的性能。

- 一个项目要求对相同的文件使用 Windows 和 Linux 工具进行交叉编译。

	在 WSL 1 中，跨 Windows 和 Linux 操作系统的文件性能比 WSL 2 中更快，因此如果要使用 Windows 应用程序来访问 Linux 文件，则目前通过 WSL 1 可实现更快的性能。

- 你的项目需要访问串行端口或 USB 设备。 但是，现在可通过 USBIPD-WIN 项目为 WSL 2 提供 USB 设备支持。

- WSL 2 不支持访问串行端口。

- 有严格的内存要求

	WSL 2 的内存使用量会随使用而缩放。 当进程释放内存时，这会自动返回到 Windows。 但从现在开始，在关闭 WSL 实例前，WSL 2 还不会将内存中缓存的页面释放回 Windows。 如果你有长时间运行的 WSL 会话或访问非常大量的文件，此缓存可能会耗尽 Windows 内存。

- 如果依赖 Linux 发行版在与主机相同的网络中拥有 IP 地址，则可能需要设置一种替代方法来运行 WSL 2。 WSL 2 作为 hyper-v 虚拟机运行。 这是对 WSL 1 中使用的桥接网络适配器的更改，这意味着 WSL 2 使用网络地址转换 (NAT) 服务作为其虚拟网络，而不是将其桥接到主机网络接口卡 (NIC)，从而生成唯一的将在重启时更改的 IP 地址。



## 2、安装

开发人员可以在 Windows 计算机上同时访问 Windows 和 Linux 的强大功能。 通过适用于 Linux 的 Windows 子系统 (WSL)，开发人员可以安装 Linux 发行版（例如 Ubuntu、OpenSUSE、Kali、Debian、Arch Linux 等），并直接在 Windows 上使用 Linux 应用程序、实用程序和 Bash 命令行工具，不用进行任何修改，也无需承担传统虚拟机或双启动设置的费用。



### 2.1、Windows

必须运行 Windows 10 版本 2004 及更高版本（内部版本 19041 及更高版本）或 Windows 11 才能使用以下命令。



**安装 WSL 命令**

在管理员模式下打开 PowerShell 或 Windows 命令提示符，输入 `wsl --install` 命令，然后重启计算机。

```powershell
wsl --install
```

此命令将启用运行 WSL 并安装 Linux 的 Ubuntu 发行版所需的功能。 

首次启动新安装的 Linux 发行版时，将打开一个控制台窗口，要求你等待将文件解压缩并存储到计算机上。 未来的所有启动时间应不到一秒。

> [!NOTE]
>
> 仅当根本没有安装 WSL 时，上述命令才有效。



**更改默认安装的 Linux 发行版**

默认情况下，安装的 Linux 分发版为 Ubuntu。 可以使用 `-d` 标志进行更改。

- 若要更改安装的发行版，请输入：`wsl --install -d <Distribution Name>`。 将 `<Distribution Name>` 替换为要安装的发行版的名称。
- 若要查看可通过在线商店下载的可用 Linux 发行版列表，请输入：`wsl --list --online` 或 `wsl -l -o`。
- 若要在初始安装后安装其他 Linux 发行版，还可使用命令：`wsl --install -d <Distribution Name>`。



### 2.2、Windows Server

Windows Server 2022 现在使用命令支持简单的 WSL 安装：

```bash
wsl --install
```

现在，可以在管理员 PowerShell 或 Windows 命令提示符中输入此命令，然后重启计算机来安装在 Windows Server 2022 上运行 WSL 所需的全部内容。

此命令将启用所需的可选组件，下载最新的 Linux 内核，将 WSL 2 设置为默认值，并安装 Linux 发行版（默认安装 Ubuntu）。



## 3、概念

### 3.1、基本 WSL 命令

以下 WSL 命令以 PowerShell 或 Windows 命令提示符支持的格式列出。 若要通过 Bash/Linux 发行版命令行运行这些命令，必须将 `wsl` 替换为 `wsl.exe`。

若要获取发行版名称的有效列表，可以使用 `help` 命令。



**Help 命令**

```powershell
wsl --help
```

查看 WSL 中可用的选项和命令列表。



#### 3.1.1、安装、更新、卸载

**安装**

```powershell
wsl --install
```

安装 WSL 和 Linux 的默认 Ubuntu 发行版。 

选项包括：

- `--distribution`：指定要安装的 Linux 发行版（`wsl --install --distribution <distribution name>`）。 
- `--no-launch`：安装 Linux 发行版，但不自动启动它。
- `--web-download`：通过联机渠道安装，而不是使用 Microsoft Store 安装。

未安装 WSL 时，选项包括：

- `--inbox`：使用 Windows 组件（而不是 Microsoft Store）安装 WSL。 （WSL 更新将通过 Windows 更新接收，而不是通过 Microsoft Store 中推送的可用更新来接收）。
- `--enable-wsl1`：在安装 Microsoft Store 版本的 WSL 的过程中也启用 “适用于 Linux 的 Windows 子系统” 可选组件，从而启用 WSL 1。
- `--no-distribution`：安装 WSL 时不安装发行版。

> [!TIP] 
>
> 如果在 Windows 10 或更低版本上运行 WSL，可能需要在 `--install` 命令中包含 `-d` 标志以指定发行版：`wsl --install -d <distribution name>`。



**更新 WSL**

```powershell
wsl --update
```

将 WSL 版本更新到最新版本。

选项包括：

- `--web-download`：从 GitHub 而不是 Microsoft Store 下载最新更新。



**注销或卸载 Linux 发行版**

```powershell
wsl --unregister <DistributionName>
```

如果将 `<DistributionName>` 替换为目标 Linux 发行版的名称，则将从 WSL 取消注册该发行版，以便可以重新安装或清理它。

> [!CAUTION] 
>
> 取消注册后，与该分发版关联的所有数据、设置和软件将永久丢失。 从 Store 重新安装会安装分发版的干净副本。 例如：`wsl --unregister Ubuntu` 将从可用于 WSL 的发行版中删除 Ubuntu。 运行 `wsl --list` 将会显示它不再列出。

还可以像卸载任何其他应用商店应用程序一样卸载 Windows 计算机上的 Linux 发行版应用。 若要重新安装，请在 Microsoft Store 中找到该发行版，然后选择【启动】。



#### 3.1.2、启动、关闭

**将目录更改为主页**

```powershell
wsl ~
```

`~` 可与 wsl 一起使用，以在用户的主目录中启动。 若要在 WSL 命令提示符中从任何目录跳回到主目录，可使用命令 `cd ~`。



**通过 PowerShell 或 CMD 运行特定的 Linux 发行版**

```powershell
wsl --distribution <Distribution Name> --user <User Name>
```

若要通过特定用户运行特定 Linux 发行版，请将 `<Distribution Name>` 替换为你首选的 Linux 发行版的名称（例如 Debian），将 `<User Name>` 替换为现有用户的名称（例如 root）。 如果 WSL 发行版中不存在该用户，你将会收到一个错误。 若要输出当前用户名，请使用 `whoami` 命令。



**以特定用户的身份运行**

```powershell
wsl --user <Username>
```

若要以指定用户身份运行 WSL，请将 `<Username>` 替换为 WSL 发行版中存在的用户名。



**关闭**

```powershell
wsl --shutdown
```

立即终止所有正在运行的发行版和 WSL 2 轻量级实用工具虚拟机。 



**Terminate**

```powershell
wsl --terminate <Distribution Name>
```

若要终止指定的发行版或阻止其运行，请将 `<Distribution Name>` 替换为目标发行版的名称。



#### 3.1.3、设置

**将 WSL 版本设置为 1 或 2**

```powershell
wsl --set-version <distribution name> <versionNumber>
```

若要指定运行 Linux 发行版的 WSL 版本（1 或 2），请将 `<distribution name>` 替换为发行版的名称，并将 `<versionNumber>` 替换为 1 或 2。

> [!TIP]
>
> 在 WSL 1 和 WSL 2 之间切换可能非常耗时，并且可能会由于两种体系结构之间的差异而导致失败。 对于包含大型项目的分发，建议在尝试转换之前备份文件。



**设置默认 WSL 版本**

```powershell
wsl --set-default-version <Version>
```

若要设置 WSL 1 或 WSL 2 的默认版本，请将 `<Version>` 替换为数字 1 或 2。 该数字表示新 Linux 发行版安装默认使用的 WSL 版本。



**设置默认 Linux 发行版**

```powershell
wsl --set-default <Distribution Name>
```

若要设置 WSL 命令将用于运行的默认 Linux 发行版，请将 `<Distribution Name>` 替换为你首选的 Linux 发行版的名称。



**更改发行版的默认用户**

```powershell
<DistributionName> config --default-user <Username>
```

更改用于发行版登录的默认用户。 用户必须已经存在于发行版中才能成为默认用户。

例如：`ubuntu config --default-user johndoe` 会将 Ubuntu 发行版的默认用户更改为 johndoe 用户。



#### 3.1.4、查询

**列出可用的 Linux 发行版**

```powershell
wsl --list --online
```

查看可通过在线商店获得的 Linux 发行版列表。 此命令也可输入为：`wsl -l -o`。



**列出已安装的 Linux 发行版**

```powershell
wsl --list --verbose
```

查看安装在 Windows 计算机上的 Linux 发行版列表，其中包括状态（发行版是正在运行还是已停止）和运行发行版的 WSL 版本（WSL 1 或 WSL 2）。  此命令也可输入为：`wsl -l -v`。

选项包括：

- `--all`：列出所有发行版。
- `--running`：仅列出当前正在运行的发行版。
- `--quiet`：仅显示发行版名称。



**检查 WSL 状态**

```powershell
wsl --status
```

查看有关 WSL 配置的常规信息，例如默认发行版类型、默认发行版和内核版本。



**检查 WSL 版本**

```powershell
wsl --version
```

检查有关 WSL 及其组件的版本信息。



**标识 IP 地址**

- `wsl hostname -I`：返回通过 WSL 2 安装的 Linux 发行版 IP 地址（WSL 2 VM 地址）
- `ip route show | grep -i default | awk '{ print $3}'`：返回从 WSL 2（WSL 2 VM）看到的 Windows 计算机的 IP 地址



#### 3.1.5、导入、导出

**导出分发版**

```powershell
wsl --export <Distribution Name> <FileName>
```

将指定分发版的快照导出为新的分发文件。 默认为 tar 格式。 在标准输入中，文件名可以是 `-`。 

选项包括：

- `--vhd`：指定导出分发版应为 .vhdx 文件而不是 tar 文件（这仅在使用 WSL 2 的情况下受支持）



**导入分发版**

```powershell
wsl --import <Distribution Name> <InstallLocation> <FileName>
```

导入指定的 tar 文件作为新的分发版。 在标准输入中，文件名可以是 `-`。

选项包括：

- `--vhd`：指定导入分发版应为 .vhdx 文件而不是 tar 文件（这仅在使用 WSL 2 的情况下受支持）
- `--version <1/2>`：指定将分发版导入为 WSL 1 还是 WSL 2 分发版



**就地导入发行版**

```powershell
wsl --import-in-place <Distribution Name> <FileName>
```

将指定的 .vhdx 文件导入为新的发行版。 虚拟硬盘必须采用 ext4 文件系统类型格式。



### 3.2、跨文件系统工作

**跨文件系统的文件存储和性能**

建议不要跨操作系统使用文件，除非有这么做的特定原因。 在 Linux 命令行（Ubuntu、OpenSUSE 等）工作中想获得最快的性能速度，请将文件存储在 WSL 文件系统中。 如果使用 Windows 命令行（PowerShell、命令提示符）工作，请将文件存储在 Windows 文件系统中。

例如，在存储 WSL 项目文件时：

- 使用 Linux 文件系统根目录：`/home/<user name>/Project`。
- 而不使用 Windows 文件系统根目录：`/mnt/c/Users/<user name>/Project$` 或 `C:\Users\<user name>\Project`。

在 WSL 命令行的文件路径中看到 `/mnt/` 时，表示你正在使用已装载的驱动器。 因此，Windows 文件系统 `C:\Users\<user name>\Project` 在 WSL 命令行中装载时将如下所示：`/mnt/c/Users/<user name>/Project$`。

可以将项目文件存储在装载的驱动器上，但如果将其直接存储在 `\\wsl$` 驱动器上，性能速度会提高。

> [!TIP]
>
> 若要在 Windows 文件资源管理器中查看所有可用的 Linux 发行版及其根文件系统，请在地址栏中输入：`\\wsl$`。



**从 Windows 命令行运行 Linux 工具**

使用 `wsl <command>`（或 `wsl.exe <command>`）从 Windows 命令提示符（CMD）或 PowerShell 运行 Linux 二进制文件。

例如：

```powershell
wsl ls -la
```

以这种方式调用二进制文件：

- 使用当前 CMD 或 PowerShell 提示符中提到的同一工作目录。
- 以 WSL 默认用户的身份运行。
- 拥有与调用方进程和终端相同的 Windows 管理权限。

`wsl`（或 `wsl.exe`）后面的 Linux 命令的处理方式与 WSL 中运行的任何命令的处理方式类似。 可以执行 `sudo`、管道处理和文件重定向等操作。

使用 `sudo` 更新默认 Linux 分发版的示例：

```powershell
wsl sudo apt-get update
```

运行此命令后，将会列出默认的 Linux 分发版用户名，并将要求你输入密码。 正确输入密码后，分发版将下载更新。



**混合 Linux 和 Windows 命令**

下面是几个使用 PowerShell 混合 Linux 和 Windows 命令的示例。

若要使用 Linux 命令 `ls -la` 列出文件，并使用 PowerShell 命令 `findstr` 来筛选包含 git 的单词的结果，请组合这些命令：

```powershell
wsl ls -la | findstr "git"
```

若要使用 PowerShell 命令 `dir` 列出文件，并使用 Linux 命令 `grep` 来筛选包含 git 的单词的结果，请组合这些命令：

```powershell
dir | wsl grep git
```

若要使用 Linux 命令 `ls -la` 列出文件，并使用 PowerShell 命令 `> out.txt` 将该列表输出到名为 out.txt 的文本文件，请组合这些命令：

```powershell
wsl ls -la > out.txt
```

若要使用 Linux 命令 `ls -la` 列出 `/proc/cpuinfo` Linux 文件系统路径中的文件，请使用 PowerShell：

```powershell
wsl ls -la /proc/cpuinfo
```

若要使用 Linux 命令 `ls -la` 列出 `C:\Program Files` Windows 文件系统路径中的文件，请使用 PowerShell：

```powershell
wsl ls -la "/mnt/c/Program Files"
```



### 3.3、高级设置配置

wsl.conf 和 .wslconfig 文件用于针对每个发行版（`wsl.conf`）和全局跨所有 WSL 2 发行版 (`.wslconfig`) 配置高级设置选项。 



**wsl.conf 和 .wslconfig 之间有什么差别？**

可以为已安装的 Linux 发行版配置设置，使它们在你每次启动 WSL 时自动应用，有两种方法：

- .wslconfig 用于在 WSL 2 上运行的所有已安装发行版中配置全局设置。
- wsl.conf 用于为在 WSL 1 或 WSL 2 上运行的每个 Linux 发行版按各个发行版配置本地设置。

这两种文件类型都用于配置 WSL 设置，但存储文件的位置、配置的范围、可配置的选项类型以及运行发行版的 WSL 版本都会影响应选择的文件类型。



**配置更改的 8 秒规则**

必须等到运行你的 Linux 发行版的子系统完全停止运行并重启，配置设置更新才会显示。 这通常需要关闭发行版 shell 的所有实例后大约 8 秒。

例如需要修改分发版 Ubuntu 配置，请修改配置文件，关闭分发版，然后重启它。你可能会假设配置更改已立即生效，但当前情况并非如此，因为子系统可能仍在运行。 在重启之前，必须等子系统停止，给它足够的时间来获取你的更改。 可以通过使用 PowerShell 和以下命令来检查关闭 Linux 发行版（shell）后其是否仍在运行：`wsl --list --running`。 如果分发版未运行，则会收到响应：“没有正在运行的分发版” 。现在可以重启分发版，以查看应用的配置更新。



#### 3.3.1、wsl.conf

使用 wsl.conf 为 WSL 1 或 WSL 2 上运行的每个 Linux 发行版按各个发行版配置本地设置。

- 作为 unix 文件存储在发行版的 `/etc` 目录中。
- 用于针对每个发行版配置设置。 在此文件中配置的设置将仅应用于包含存储此文件的目录的特定 Linux 发行版。
- 可用于由 WSL 1 或 WSL 2 版本运行的发行版。

> [!NOTE]
>
> 使用 wsl.conf 文件调整每个发行版设置的功能仅适用于 Windows 版本 17093 及更高版本。



**网络设置**

wsl.conf 节标签：`[network]`

| 设置名称           | 值      | 默认值         | 说明                                                         |
| :----------------- | :------ | :------------- | :----------------------------------------------------------- |
| generateHosts      | boolean | true           | true 将 WSL 设置为生成 `/etc/hosts`。 hosts 文件包含主机名对应的 IP 地址的静态映射。 |
| generateResolvConf | boolean | true           | true 将 WSL 设置为生成 `/etc/resolv.conf`。 resolv.conf 包含能够将给定主机名解析为其 IP 地址的 DNS 列表。 |
| hostname           | string  | Windows 主机名 | 设置要用于 WSL 发行版的主机名。                              |



**互操作设置**

wsl.conf 节标签：`[interop]`

| 设置名称          | 值      | 默认值 | 说明                                                         |
| :---------------- | :------ | :----- | :----------------------------------------------------------- |
| enabled           | boolean | true   | 设置此键可确定 WSL 是否支持启动 Windows 进程。               |
| appendWindowsPath | boolean | true   | 设置此键可确定 WSL 是否会将 Windows 路径元素添加到 $PATH 环境变量。 |



**用户设置**

wsl.conf 节标签：`[user]`

| 设置名称 | 值     | 默认值                     | 说明                                                  |
| :------- | :----- | :------------------------- | :---------------------------------------------------- |
| default  | string | 首次运行时创建的初始用户名 | 设置此键指定在首次启动 WSL 会话时以哪个用户身份运行。 |



**启动设置**

wsl.conf 节标签：`[boot]`

| 设置名称 | 值      | 默认值 | 说明                                                         |
| :------- | :------ | :----- | :----------------------------------------------------------- |
| systemd  | boolean | false  | true 将启用 systemd。                                        |
| command  | string  | ""     | 你希望在 WSL 实例启动时运行的命令字符串。 <br />此命令以根用户身份运行。 例如 `service docker start`。 |

> [!NOTE]
>
> 启动设置仅在 Windows 11 和 Server 2022 上可用。



**wsl.conf 文件示例**

```bash
## 当启动该发行版时自动挂载Windows驱动器
[automount]
## 设置为true时，将在上面设置的根目录下自动挂载固定驱动器（如C:/或D:/）到DrvFs。
## 设置为false时，驱动器将不会自动挂载，需要手动挂载或使用fstab文件挂载。
enabled = true
## 设置固定驱动器自动挂载的目录。这个例子更改了挂载位置，因此你的C盘会挂载为/c，而不是默认的/mnt/c。
root = /
## 可以指定DrvFs的特定选项。
options = "metadata,uid=1003,gid=1003,umask=077,fmask=11,case=off"
## 设置WSL发行版启动时是否处理`/etc/fstab`文件。
mountFsTab = true

## 网络主机设置，用于启用WSL2使用的DNS服务器。
## 这个例子更改了主机名，设置generateHosts为false，防止WSL自动生成/etc/hosts，
## 并设置generateResolvConf为false，防止WSL自动生成/etc/resolv.conf，以便你可以创建自己的配置（例如：nameserver 1.1.1.1）。
[network]
hostname = DemoHost
generateHosts = false
generateResolvConf = false

## 设置WSL是否支持启动Windows应用程序和添加路径变量。将这些设置为false将阻止启动Windows进程，并阻止添加$PATH环境变量。
[interop]
enabled = false
appendWindowsPath = false

## 设置使用WSL启动发行版时的默认用户。
[user]
default = DemoUser

## 设置启动新的WSL实例时要运行的命令。这个例子启动了Docker容器服务。
[boot]
## 启用 systemd
systemd = true
command = service docker start
```



#### 3.3.2、.wslconfig

使用 .wslconfig 为 WSL 上运行的所有已安装的发行版配置全局设置。

- 默认情况下，.wslconfig 文件不存在。 它必须创建并存储在 `C:\Users\<UserName>` 目录中才能应用这些配置设置。
- 用于在作为 WSL 2 版本运行的所有已安装的 Linux 发行版中全局配置设置。
- 只能用于 WSL 2 运行的发行版。 作为 WSL 1 运行的发行版不受此配置的影响，因为它们不作为虚拟机运行。

WSL 将检测这些文件是否存在，读取内容，并在每次启动 WSL 时自动应用配置设置。 如果文件缺失或格式错误（标记格式不正确），则 WSL 将继续正常启动，而不应用配置设置。



**主要 WSL 设置**

.wslconfig 节标签：`[wsl2]`

| 设置名称              | 值      | 默认值                                         | 说明                                                         |
| :-------------------- | :------ | :--------------------------------------------- | :----------------------------------------------------------- |
| kernel                | path    | Microsoft 内置内核提供的收件箱                 | 自定义 Linux 内核的绝对 Windows 路径。                       |
| memory                | size    | Windows 上总内存的 50%                         | 要分配给 WSL 2 VM 的内存量。                                 |
| processors            | number  | Windows 上相同数量的逻辑处理器                 | 要分配给 WSL 2 VM 的逻辑处理器数量。                         |
| localhostForwarding   | boolean | true                                           | 一个布尔值，用于指定绑定到 WSL 2 VM 中的通配符或 localhost 的端口是否应可通过 `localhost:port` 从主机连接。 |
| kernelCommandLine     | string  | 空白                                           | 其他内核命令行参数。                                         |
| safeMode              | boolean | false                                          | 在 “安全模式” 中运行 WSL，这会禁用许多功能，应用于恢复处于错误状态的发行版。 仅适用于 Windows 11 和 WSL 版本 0.66.2+。 |
| swap                  | size    | Windows 上 25% 的内存大小四舍五入到最接近的 GB | 要向 WSL 2 VM 添加的交换空间量，0 表示无交换文件。 交换存储是当内存需求超过硬件设备上的限制时使用的基于磁盘的 RAM。 |
| pageReporting         | boolean | true                                           | 默认的 true 设置使 Windows 能够回收分配给 WSL 2 虚拟机的未使用内存。 |
| guiApplications       | boolean | true                                           | 一个布尔值，用于在 WSL 中打开或关闭对 GUI 应用程序（WSLg）的支持。 |
| debugConsole*         | boolean | false                                          | 一个布尔值，用于在 WSL 2 发行版实例启动时打开显示 `dmesg` 内容的输出控制台窗口。 仅适用于 Windows 11。 |
| nestedVirtualization* | boolean | true                                           | 用于打开或关闭嵌套虚拟化的布尔值，使其他嵌套 VM 能够在 WSL 2 中运行。 仅适用于 Windows 11。 |
| vmIdleTimeout*        | number  | 60000                                          | VM 在关闭之前处于空闲状态的毫秒数。 仅适用于 Windows 11。    |
| dnsProxy              | boolean | true                                           | 仅适用于 networkingMode = NAT。 布尔值，通知 WSL 将 Linux 中的 DNS 服务器配置为主机上的 NAT。 设置为 false 会将 DNS 服务器从 Windows 镜像到 Linux。 |
| networkingMode**      | string  | NAT                                            | 如果值为 mirrored，则会启用镜像网络模式。 默认或无法识别的字符串会生成 NAT 网络。 |
| firewall**            | boolean | true                                           | 如果设置为 true，则 Windows 防火墙规则以及特定于 Hyper-V 流量的规则可以筛选 WSL 网络流量。 |
| dnsTunneling**        | boolean | true                                           | 更改将 DNS 请求从 WSL 代理到 Windows 的方式                  |
| autoProxy*            | boolean | true                                           | 强制 WSL 使用 Windows 的 HTTP 代理信息                       |
| defaultVhdSize        | siize   | 1TB                                            | 设置存储 Linux 发行版（例如 Ubuntu）文件系统的虚拟硬盘（VHD）大小。 可用于限制分发文件系统允许占用的最大大小。 |

- 具有 `path` 值的条目必须是带有转义反斜杠的 Windows 路径，例如：`C:\\Temp\\myCustomKernel`。
- 具有 `size` 值的条目后面必须跟上大小的单位，例如 `8GB` 或 `512MB`。
- 值类型后带有 `*` 的条目仅在 Windows 11 中可用。
- 值类型后带有 `**` 的条目需要 Windows 11 版本 22H2 或更高版本。



**实验性设置**

.wslconfig 节标签：`[experimental]`

| 设置名称                 | 值      | 默认值         | 说明                                                         |
| :----------------------- | :------ | :------------- | :----------------------------------------------------------- |
| autoMemoryReclaim        | string  | disabled       | 检测空闲 CPU 使用率后，自动释放缓存的内存。 设置为 gradual 以慢速释放，设置为 dropcache 以立即释放缓存的内存。 |
| sparseVhd                | boolean | false          | 如果设置为 true，则任何新创建的 VHD 将自动设置为稀疏。       |
| useWindowsDnsCache**     | boolean | false          | 仅当 `wsl2.dnsTunneling` 设置为 true 时才适用。 如果此选项设置为 false，则从 Linux 隧道传输的 DNS 请求将绕过 Windows 中的缓存名称，以始终将请求放在网络上。 |
| bestEffortDnsParsing**   | boolean | false          | 仅当 `wsl2.dnsTunneling` 设置为 true 时才适用。 如果设置为 true，Windows 将从 DNS 请求中提取问题并尝试解决该问题，从而忽略未知记录。 |
| dnsTunnelingIpAddress**  | string  | 10.255.255.254 | 仅当 `wsl2.dnsTunneling` 设置为 true 时才适用。 指定启用 DNS 隧道的情况下将在 Linux resolv.conf 文件中配置的 nameserver。 |
| initialAutoProxyTimeout* | string  | 1000           | 仅当 `wsl2.autoProxy` 设置为 true 时才适用。 配置启动 WSL 容器时，WSL 等待检索 HTTP 代理信息的时长（以毫秒为单位）。 如果代理设置在此时间之后解析，则必须重启 WSL 实例才能使用检索到的代理设置。 |
| ignoredPorts**           | string  | Null           | 仅当 `wsl2.networkingMode` 设置为 mirrored 时才适用。 指定 Linux 应用程序可以绑定到哪些端口（即使该端口已在 Windows 中使用）。 通过此设置，应用程序能够仅侦听 Linux 中的流量端口，因此即使该端口在 Windows 上用于其他用途，这些应用程序也不会被阻止。 例如，WSL 将允许绑定到 Linux for Docker Desktop 中的端口 53，因为它只侦听来自 Linux 容器中的请求。 应在逗号分隔列表中设置格式，例如：`3000,9000,9090`。 |
| hostAddressLoopback**    | boolean | false          | 仅当 `wsl2.networkingMode` 设置为 mirrored 时才适用。 如果设置为 true，将会允许容器通过分配给主机的 IP 地址连接到主机，或允许主机通过此方式连接到容器。 始终可以使用 127.0.0.1 环回地址，此选项也允许使用所有额外分配的本地 IP 地址。 仅支持分配给主机的 IPv4 地址。 |

- 值类型后带有 `*` 的条目仅在 Windows 11 中可用。
- 值类型后带有 `**` 的条目需要 Windows 11 版本 22H2 或更高版本。



**.wslconfig 文件示例**

```bash
## 设置适用于在WSL2上运行的所有Linux发行版
[wsl2]
## 限制虚拟机内存使用不超过4GB，这个值可以设置为整数，使用GB或MB作为单位
memory=4GB 
## 设置虚拟机使用两个虚拟处理器
processors=2
## 指定一个自定义的Linux内核供已安装的发行版使用。默认使用的内核可以在 https://github.com/microsoft/WSL2-Linux-Kernel 找到
kernel=C:\\temp\\myCustomKernel
## 设置额外的内核参数，在这个例子中启用了对较旧的Linux基础镜像（如CentOS 6）的支持
kernelCommandLine = vsyscall=emulate
## 设置交换空间大小为8GB，默认值为可用内存的25%
swap=8GB
## 设置交换文件路径位置，默认路径为 %USERPROFILE%\AppData\Local\Temp\swap.vhdx
swapfile=C:\\temp\\wsl-swap.vhdx
## 禁用页面报告，使WSL保留从Windows中分配的所有内存，不在空闲时释放回去
pageReporting=false
## 启用默认连接，将WSL2的localhost绑定到Windows的localhost。此设置在networkingMode=mirrored时被忽略
localhostforwarding=true
## 禁用嵌套虚拟化
nestedVirtualization=false
## 打开输出控制台，在打开WSL2发行版时显示dmesg的内容以进行调试
debugConsole=true

## 启用实验性功能
[experimental]
sparseVhd=true
```



### 3.4、网络注意事项

#### 3.4.1、默认网络模式：NAT

默认情况下，WSL 使用基于 NAT（网络地址转换）的网络体系结构。 



**从 Windows (localhost) 访问 Linux 网络应用**

如果要在 Linux 分发版中构建网络应用（例如，在 NodeJS 或 SQL server 上运行的应用），可以使用 `localhost` 从 Windows 应用（如 Microsoft Edge 或 Chrome Internet 浏览器）访问它（就像往常一样）。



**从 Linux（主机 IP）访问 Windows 网络应用**

如果要从 Linux 分发版（即 Ubuntu）访问 Windows 上运行的网络应用（例如在 NodeJS 或 SQL 服务器上运行的应用），则需要使用主机的 IP 地址。 虽然这不是一种常见方案，但你可以执行以下步骤来使其可行。

1. 通过在 Linux 分发版中运行以下命令来获取主机的 IP 地址：`ip route show | grep -i default | awk '{ print $3}'`。
2. 使用复制的 IP 地址连接到任何 Windows 服务器。



#### 3.4.2、镜像模式网络

在运行 Windows 11 22H2 及更高版本的计算机上，可以在 .wslconfig 文件中的 `[wsl2]` 下设置 `networkingMode=mirrored` 以启用镜像模式网络。 启用此功能会将 WSL 更改为全新的网络体系结构，其目标是将 Windows 上的网络接口 “镜像” 到 Linux 中，以添加新的网络功能并提高兼容性。

以下是启用此模式的当前优势：

- IPv6 支持。
- 使用 localhost 地址 127.0.0.1 从 Linux 内部连接到 Windows 服务器。 不支持 IPv6 localhost 地址 `::1`。
- 改进了 VPN 的网络兼容性。
- 多播支持。
- 直接从局域网（LAN）连接到 WSL。

这种新模式解决了使用基于 NAT（网络地址转换）的体系结构时出现的网络问题。 



## 4、其他

### 4.1、设置中文

1. 安装中文语言包：

	```shell
	sudo apt install language-pack-zh-hans
	```

2. 设置中文为默认语言：

	```shell
	sudo dpkg-reconfigure locales
	```

	选择 `en_US.UTF-8` 和 `zh_CN.UTF-8`，选择 `zh_CN.UTF-8` 为默认语言。

2. 重启 WSL Ubuntu 终端即可显示中文。

