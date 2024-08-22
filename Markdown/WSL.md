# 1、概述

适用于 Linux 的 Windows 子系统（WSL）可让开发人员直接在 Windows 上按原样运行 GNU/Linux 环境（包括大多数命令行工具、实用工具和应用程序），且不会产生传统虚拟机或双启动设置开销。



## 1.1、什么是 WSL

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



## 1.2、比较 WSL 版本

### 1.2.1、比较 WSL 1 和 WSL 2

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



### 1.2.2、WSL 2 中的新增功能

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



### 1.2.3、例外情况

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



# 2、安装

开发人员可以在 Windows 计算机上同时访问 Windows 和 Linux 的强大功能。 通过适用于 Linux 的 Windows 子系统 (WSL)，开发人员可以安装 Linux 发行版（例如 Ubuntu、OpenSUSE、Kali、Debian、Arch Linux 等），并直接在 Windows 上使用 Linux 应用程序、实用程序和 Bash 命令行工具，不用进行任何修改，也无需承担传统虚拟机或双启动设置的费用。



## 2.1、Windows

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



## 2.2、Windows Server

Windows Server 2022 现在使用命令支持简单的 WSL 安装：

```bash
wsl --install
```

现在，可以在管理员 PowerShell 或 Windows 命令提示符中输入此命令，然后重启计算机来安装在 Windows Server 2022 上运行 WSL 所需的全部内容。

此命令将启用所需的可选组件，下载最新的 Linux 内核，将 WSL 2 设置为默认值，并安装 Linux 发行版（默认安装 Ubuntu）。



# 3、概念

## 3.1、基本 WSL 命令

以下 WSL 命令以 PowerShell 或 Windows 命令提示符支持的格式列出。 若要通过 Bash/Linux 发行版命令行运行这些命令，必须将 `wsl` 替换为 `wsl.exe`。

若要获取发行版名称的有效列表，可以使用 `help` 命令。



**Help 命令**

```powershell
wsl --help
```

查看 WSL 中可用的选项和命令列表。



### 3.1.1、安装、更新、卸载

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



### 3.1.2、启动、关闭

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



### 3.1.3、设置

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



### 3.1.4、查询

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



### 3.1.5、导入、导出

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



## 3.2、跨文件系统工作

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



## 3.3、高级设置配置



## 3.4、网络注意事项



## 3.5、使用 SystemD 管理服务



# 4、其他

## 4.1、设置中文

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


