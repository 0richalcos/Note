# Dameng

## 1、简介

达梦数据库管理系统是达梦公司推出的具有完全自主知识产权的高性能数据库管理系统，简称 DM。达梦数据库管理系统的最新版本是 8.0 版本，简称 DM8。
DM8 采用全新的体系架构，在保证大型通用的基础上，针对可靠性、高性能、海量数据处理和安全性做了大量的研发和改进工作，极大提升了达梦数据库产品的性能、可靠性、可扩展性，能同时兼顾 OLTP 和 OLAP 请求，从根本上提升了 DM8 产品的品质。

<br>

## 2、安装及卸载

### 2.1、Linux

#### 2.1.1、安装前准备

用户在安装 DM 数据库之前需要检查或修改操作系统的配置，以保证 DM 数据库能够正确安装和运行。

本文以演示环境如下：

| 操作系统 | CPU         | 数据库                  |
| :------- | :---------- | :---------------------- |
| CentOS7  | x86_64 架构 | dm8_rh7_64_ent_8.1.1.87 |

<br>

**新建 dmdba 用户**

> [!CAUTION]
>
> 安装前必须创建 dmdba 用户，禁止使用 root 用户安装数据库。

1. 创建用户所在的组，命令如下：

   ```shell
   groupadd dinstall -g 2001
   ```

2. 创建用户，命令如下：

   ```shell
   useradd -G dinstall -m -d /home/dmdba -s /bin/bash -u 2001 dmdba
   ```

3. 修改用户密码，命令如下：

   ```shell
   passwd dmdba
   ```

<br>

**修改文件打开最大数**

在 Linux、Solaris、AIX 和 HP-UNIX 等系统中，操作系统默认会对程序使用资源进行限制。如果不取消对应的限制，则数据库的性能将会受到影响。

- 重启服务器后永久生效

  使用 root 用户打开 `/etc/security/limits.conf` 文件进行修改，命令如下：

  ```shell
  vi /etc/security/limits.conf
  ```

  在最后需要添加如下配置：

  ```
  dmdba  soft      nice       0
  dmdba  hard      nice       0
  dmdba  soft      as         unlimited
  dmdba  hard      as         unlimited
  dmdba  soft      fsize      unlimited
  dmdba  hard      fsize      unlimited
  dmdba  soft      nproc      65536
  dmdba  hard      nproc      65536
  dmdba  soft      nofile     65536
  dmdba  hard      nofile     65536
  dmdba  soft      core       unlimited
  dmdba  hard      core       unlimited
  dmdba  soft      data       unlimited
  dmdba  hard      data       unlimited
  ```

  切换到 dmdba 用户，查看是否生效，命令如下：

  ```shell
  su - dmdba
  
  ulimit -a
  ```

  可以看到参数配置已生效：

  <img src="./!assets/DM/202401240959260UW7J0C11OXTBOXWK9.png" alt="image.png" style="zoom:67%;" />

- 设置参数临时生效

  可使用 dmdba 用户执行如下命令，使设置临时生效：

  ```shell
  ulimit -n 65536
  ulimit -u 65536
  ```

<br>

**创建数据目录**

创建实例保存目录、归档保存目录、备份保存目录：

```shell
##实例保存目录
mkdir -p /dmdata/data 
##归档保存目录
mkdir -p /dmdata/arch
##备份保存目录
mkdir -p /dmdata/dmbak
```

> [!CAUTION]
>
> 使用 root 用户建立文件夹，待 dmdba 用户建立完成后需将文件所有者更改为 dmdba 用户，否则无法安装到该目录下。

<br>

**修改目录权限**

将新建的路径目录权限的用户修改为 dmdba，用户组修改为 dinstall。命令如下：

```shel
chown -R dmdba:dinstall /dmdata
```

给路径下的文件设置 755 权限。命令如下：

```shell
chmod -R 755 /dmdata
```

<br>

**挂载镜像**

切换到 root 用户，将 DM 数据库的 iso 安装包保存在任意位置，例如 `/opt` 目录下，执行如下命令挂载镜像：

```shell
cd  /opt
mount -o loop dm8_20240116_x86_rh7_64.iso /mnt
```

<br>

#### 2.1.2、数据库安装

DM 数据库在 Linux 环境下支持命令行安装和图形化安装。

<br>

**命令行安装**

1. 切换至 dmdba 用户下，在 `/mnt` 目录下使用命令行安装数据库程序，依次执行以下命令安装 DM 数据库：

   ```shell
   su - dmdba
   
   cd /mnt/
   
   ./DMInstall.bin -i
   ```

2. 按需求选择安装语言，没有 key 文件选择 "n"，时区按需求选择一般选择 “21”，安装类型选择 “1”，安装目录按实际情况配置，这里示例使用默认安装位置：

   <img src="./!assets/DM/20240124112650T19KGAG7IFVF7RE1JH.png" alt="image.png" style="zoom:67%;" />

4. 数据库安装大概 1~2 分钟，数据库安装完成后，显示如下界面：

   <img src="./!assets/DM/2024012411300992W9AYDO7OL5LIEXUO.png" alt="image.png" style="zoom:67%;" />

4. 数据库安装完成后，需要切换至 root 用户执行上图中的命令 `/home/dmdba/dmdbms/script/root/root_installer.sh` 创建 DmAPService，否则会影响数据库备份。

   数据库安装完成后还需注册实例才能使用数据库，注册实例后面会讲到。

<br>

**图形化安装**

1. 切换到 dmdba 用户，进入 `/mnt` 目录下，执行命令开始图形化安装：

   ```shell
   su - dmdba
   
   cd /mnt/
   
   ./DMInstall.bin
   ```

   若出现：`初始化图形界面失败，如果当前监视器窗口不支持图形界面，请进入安装文件所在文件夹并使用"./DMInstall.bin -i"进行命令行安装。` 错误提示，可按以下两种方式操作解决：

   - 方法一：注销当前用户，登陆 dmdba 用户，执行 `./DMInstall.bin` 命令。
   - 方法二：用当前用户执行 `xhost +`，切换到 dmdba 用户，执行 `export DISPLAY=:0`，再执行 `xhost +` 命令。

2. 图形化界面启动成功后，将弹出【选择语言与时区】页面，默认为简体中文和中国标准时间：

   <img src="!assets/DM/ui-lang-time.png" alt="选择语言与时区" style="zoom:67%;" />

3. 点击【确定】后，弹出 DM 数据库安装程序：

   <img src="!assets/DM/ui-install-xd.png" alt="安装向导" style="zoom:67%;" />

4. 点击【下一步】后，为许可证协议页面，选择【接受】：

   <img src="!assets/DM/ui-install-xkxy.png" alt="许可证协议" style="zoom:67%;" />

5. 点击【下一步】后，弹出 key 文件页面，点击【浏览】选择【key 文件】，若没有 key 文件可以直接点击【下一步】，跳过该步骤：

   <img src="!assets/DM/ui-install-key.png" alt="key文件" style="zoom:67%;" />

6. 点击【下一步】后，弹出选择组件页面，建议选择典型安装，也可根据需要，选择服务器安装、客户端安装和自定义安装：

   <img src="!assets/DM/ui-install-chosezj.png" alt="选择组件" style="zoom:67%;" />

7. 点击【下一步】后，弹出选择安装位置页面，可点击【浏览】选择安装位置，也可安装在默认路径下：

   <img src="!assets/DM/ui-install-path.png" alt="选择安装位置" style="zoom: 33%;" />

8. 点击【下一步】后，弹出确认安装信息页面，检查安装信息是否准确，确认无误后点击【安装】：

   <img src="!assets/DM/ui-install-message.png" alt="确认安装信息" style="zoom:67%;" />

9. 点击【安装】后，等待 1~2 分钟即可安装完成，安装完成后弹出执行配置脚本页面，按照页面要求执行该脚本即可：

   <img src="!assets/DM/ui-install-runshell.png" alt="执行配置脚本" style="zoom: 67%;" />

10. 重新打开一个终端，切换到 root 用户，执行弹出页面中的脚本：

    <img src="!assets/DM/ui-install-zxjb.png" alt="执行脚本" style="zoom:67%;" />

11. 脚本执行完成后，点击执行配置脚本页面中的【完成】，弹出提示框，提示是否关闭窗口，选择是，提示数据库安装完成，再点击【完成】按钮，完成数据库安装：

    <img src="!assets/DM/ui-install-success.png" alt="完成安装" style="zoom:67%;" />

<br>

#### 2.1.3、配置环境变量

1. 切换到 root 用户进入 dmdba 用户的根目录下，配置对应的环境变量。`DM_HOME` 变量和动态链接库文件的加载路径在程序安装成功后会自动导入。命令如下：

   ```shell
   export PATH=$PATH:$DM_HOME/bin:$DM_HOME/tool
   ```

2. 编辑 `.bash_profile`，使其最终效果如下图所示：

   ```shell
   cd /home/dmdba/
   
   vim .bash_profile
   ```

   <img src="!assets/DM/dm-home-path.png" alt="环境变量" style="zoom: 80%;" />

3. 切换至 dmdba 用户下，执行以下命令，使环境变量生效：

   ```shell
   su - dmdba
   
   source .bash_profile
   ```

<br>


#### 2.1.4、配置实例

DM 数据库在 Linux 环境支持命令行配置实例以及图形化配置实例。

<br>

**命令行配置实例**

1. 使用 dmdba 用户配置实例，进入到 DM 数据库安装目录下的 bin 目录中：

   ```shell
   su - dmdba
   cd /home/dmdba/dmdbms/bin
   ```

2. 使用 `dminit` 命令初始化实例，`dminit` 命令可设置多种参数，可执行如下命令查看可配置参数：

   ```shell
   ./dminit help
   ```

   <img src="./!assets/DM/20250508101244TDXKI0J2TY6HFMXE15.png" alt="image.png" style="zoom:67%;" />

   需要注意的是页大小（*PAGE_SIZE*）、簇大小（*EXTENT_SIZE*）、大小写敏感（*CASE_SENSITIVE*）、字符集（*CHARSET*）、空格填充模式（*BLANK_PAD_MODE*）、页检查模式（*PAGE_CHECK*）等部分参数，一旦确定无法修改，在初始化实例时确认需求后谨慎设置。

   部分参数解释如下：

   - *PAGE_SIZE*：数据文件使用的页大小。取值范围 4、8、16、32，单位：KB。缺省值为 8。选择的页大小越大，则 DM 支持的元组长度也越大，但同时空间利用率可能下降。

     可选参数。数据库创建成功后无法再修改页大小，可通过系统函数 `SF_GET_PAGE_SIZE()` 获取系统的页大小。

   - *EXTENT_SIZE*：数据文件使用的簇大小，即每次分配新的段空间时连续的页数。取值范围 16、32、64。单位：页数。缺省值为 16。

     可选参数。数据库创建成功后无法再修改簇大小，可通过系统函数 `SF_GET_EXTENT_SIZE()` 获取系统的簇大小。

   - *CASE_SENSITIVE*：标识符大小写敏感。当大小写敏感时，小写的标识符应用 `""` 括起，否则被系统自动转换为大写；当大小写不敏感时，系统不会转换标识符的大小写，系统比较函数会将大写字母全部转为小写字母再进行比较。取值：Y、y、1 表示敏感；N、n、0 表示不敏感。缺省值为 Y。

     可选参数。此参数在数据库创建成功后无法修改，可通过系统函数 `SF_GET_CASE_SENSITIVE_FLAG()` 或 `CASE_SENSITIVE()` 查询设置的参数值。

   - *CHARSET*：字符集选项。取值范围 0、1、2。0 代表 GB18030，1 代表 UTF-8，2 代表韩文字符集 EUC-KR。缺省值为 0。

     可选参数。此参数在数据库创建成功后无法修改，可通过系统函数 `SF_GET_UNICODE_FLAG()` 或 `UNICODE()` 查询设置的参数值。

   - *BLANK_PAD_MODE*：设置字符串比较时，结尾空格填充模式是否兼容 ORACLE。1：兼容；0：不兼容。缺省值为 0。

     可选参数。此参数在数据库创建成功后无法修改，可通过查询 `V$PARAMETER` 中的 `BLANK_PAD_MODE` 参数名查看此参数的设置值。

   - *PAGE_CHECK*：*PAGE_CHECK* 为页检查模式。取值范围 0、1、2、3。0：禁用页校验；1：开启页校验并使用 CRC 校验；2：开启页校验并使用指定的 HASH 算法进行校验；3：开启页校验并使用快速 CRC 校验。缺省值为 3。

     可选参数。在数据库创建成功后无法修改。

3. 如果需要附加实例存放路径。此处以初始化实例到 `/dmdata/data` 目录下为例（执行初始化命令前，需要使用 root 用户授予 `/dmdata/data` 目录相应权限），初始化命令如下：

   ```shell
   ./dminit path=/dmdata/data SYSDBA_PWD=****** SYSAUDITOR_PWD=******
   ```

   也可以自定义初始化实例的参数，参考如下示例：

   ```shell
   ./dminit path=/dmdata/data PAGE_SIZE=32 EXTENT_SIZE=32 CASE_SENSITIVE=y CHARSET=1 DB_NAME=DAMENG INSTANCE_NAME=DMSERVER PORT_NUM=5236 SYSDBA_PWD=123  SYSAUDITOR_PWD=321
   ```

   上面命令设置页大小为 32 KB，簇大小为 32 KB，大小写敏感，字符集为 utf_8，数据库名为 DAMENG，实例名为 DMSERVER，端口为 5236，SYSDBA 用户密码为 123，SYSAUDITOR 用户密码为 321。

   > [!NOTE]
   >
   > 如果此处自定义了初始化参数，在后面的注册服务和启动数据库等步骤中，请按实际的自定义参数进行操作。

<br>

**图形化配置实例**

1. 使用图形化界面安装数据库安装完成后，会弹出选择是否初始化数据库页面，选择【初始化】：

   <img src="!assets/DM/ui-licence-csh.png" alt="初始化实例" style="zoom:67%;" />

2. 点击初始化后会弹出数据库配置助手，通过数据库配置助手便可以配置数据库：

   <img src="!assets/DM/ui-licence-ipzzs.png" alt="初始化实例" style="zoom:67%;" />

3. 若需要主动打开配置助手，可使用 dmdba 用户配置实例，进入到 DM 数据库安装目录下的 tool 目录中，使用 `./dbca.sh` 命令打开数据库配置助手：

   <img src="!assets/DM/ui-licence-stdbca.png" alt="启动配置助手" style="zoom:67%;" />

4. 选择创建数据库实例，点击【开始】，进入创建数据库页面的创建数据库模版页签，选择【一般用途】，如下图所示：

   <img src="!assets/DM/ui-instance-cremod.png" alt="创建数据库模版" style="zoom:67%;" />

5. 点击【下一步】，选择数据库实例安装目录，如下图所示：

   <img src="!assets/DM/ui-instance-cpath.png" alt="指定数据库目录" style="zoom:67%;" />

6. 确定好数据库安装目录后，点击【下一步】，用户可根据需要设置对应的数据库参数，如下图所示：

   <img src="!assets/DM/ui-instance-settag.png" alt="指定数据库目录" style="zoom:67%;" />

7. 点击【下一步】，配置数据库文件路径，选择【默认路径】即可，如下图所示：

   <img src="!assets/DM/ui-licence-datafilepath.png" alt="指定数据库文件目录" style="zoom:67%;" />

8. 点击【下一步】，配置初始化参数，注意簇大小、页大小、字符集以及大小写敏感确定后不可修改，如下图所示：

   <img src="!assets/DM/ui-licence-setcs.png" alt="指定数据库文件目录" style="zoom:67%;" />

9. 点击【下一步】，配置数据库口令，默认配置即可，如下图所示：

   <img src="!assets/DM/ui-licence-pswm.png" alt="口令管理" style="zoom:67%;" />

10. 点击【下一步】，配置示例库，建议勾选  `BOOKSHOP` 或 `DMHR`，作为演示环境，如下图所示：

    <img src="!assets/DM/ui-licence-exdatabase.png" alt="创建示例库" style="zoom:67%;" />

11. 点击【下一步】，用户可检查创建参数，若有需要修改之处可点击【上一步】回到需要修改的位置进行修改，如下图所示：

    <img src="!assets/DM/ui-licence-crezy.png" alt="创建摘要" style="zoom:67%;" />

12. 点击【完成】，创建完成数据库实例后，按下图按提示执行脚本即可完成实例配置：

    <img src="!assets/DM/ui-licence-cresuee.png" alt="创建完成" style="zoom:67%;" />

    <img src="!assets/DM/zxml.png" alt="执行命令" style="zoom:67%;" />

<br>

#### 2.1.5、注册服务

**命令行注册服务**

1. 注册服务需使用 root 用户进行注册。使用 root 用户进入数据库安装目录的 `/script/root` 下，如下所示：

   ```shell
   cd /home/dmdba/dmdbms/script/root/
   ```

2. 注册服务，如下所示：

   ```shell
   ./dm_service_installer.sh -t dmserver -dm_ini /dmdata/data/DAMENG/dm.ini -p DMSERVER
   ```

   部分参数说明：

   | 标志      | 参数         | 说明                                                         |
   | :-------- | :----------- | :----------------------------------------------------------- |
   | `-t`      | 服务类型     | 注册服务类型，支持以下服务类型：dmap、dmamon、dmserver、<br />dmwatcher、dmmonitor、dmasmsvr、dmasmsvrm、dmcss、dmcssm。 |
   | `-dm_ini` | INI 文件路径 | 指定服务所需要的 dm.ini 文件路径。                           |
   | `-p`      | 服务名后缀   | 指定服务名后缀，生成的操作系统服务名为 “服务脚本模板名称 + 服务名后缀”。<br />此参数只针对 dmserver、dmwatcher、dmmonitor、dmasmsvr、dmasmsvrm、<br />dmcss、dmcssm 服务脚本生效。 |

3. 进入数据安装目录下 bin 目录中可以看到已经注册好的服务 DmServiceDMSERVER：

   ```shell
   cd /home/dmdba/dmdbms/bin
   ls | grep DmServiceDMSERVER
   ```

<br>

**图形化注册服务**

1. 打开运行 dbca 工具，选择【注册数据库服务】，如下图所示：

   <img src="!assets/DM/ui-service-dbca.png" alt="打开dbca" style="zoom:67%;" />

2. 单击【开始】，弹出注册数据库服务页面，如下图所示：

   <img src="!assets/DM/ui-service-zccs.png" alt="注册参数" style="zoom:67%;" />

3. 点击【完成】后，弹出执行配置脚本页面，按页面要求执行脚本即可，如下图所示：

   <img src="!assets/DM/ui-service-shell.png" alt="执行配置脚本" style="zoom:67%;" />

4. 执行脚本成功后，该实例已启动，如下图所示：

   <img src="!assets/DM/ui-service-zd.png" alt="终端" style="zoom:67%;" />

<br>

#### 2.1.6、启动、停止数据库

**命令行启停数据库**

服务注册成功后，启动数据库，如下所示：

```shell
systemctl start DmServiceDMSERVER.service
```

停止数据库，如下所示：

```shell
systemctl stop DmServiceDMSERVER.service
```

重启数据库，如下所示：

```shell
systemctl restart DmServiceDMSERVER.service
```

查看数据库服务状态，如下所示：

```shell
systemctl status DmServiceDMSERVER.service
```

可前台启动，进入 DM 安装目录下的 `bin` 目录下，命令如下：

```shell
./dmserver /dm/data/DAMENG/dm.ini
```

该启动方式为前台启动，若想关闭数据库，则输入 `exit` 即可。

也可进入 DM 安装目录下的 `bin` 目录下，启动/停止/重启数据库，如下所示：

```shell
./DmServiceDMSERVER start/stop/restart
```

查看数据库状态，如下所示：

```shell
./DmServiceDMSERVER status
```

<br>

**图形化启停数据库**

进入 DM 安装目录下的 `tool` 目录，使用如下命令打开 DM 服务查看器，如下所示：

```shell
./dmservice.sh
```

<img src="!assets/DM/ui-service-fwck.png" alt="安装完成" style="zoom:67%;" />

<img src="!assets/DM/ui-service-czfw.png" alt="安装完成" style="zoom:67%;" />

<br>

#### 2.1.7、卸载数据库

**命令行卸载**

用户在 DM 安装目录下，找到卸载程序 `uninstall.sh` 来卸载数据库：

1. 进入 DM 安装目录：

   ```shell
   cd /DM_INSTALL_PATH
   ```

2. 执行卸载脚本，命令行卸载需要添加参数 `-i`：

   ```shell
   ./uninstall.sh -i
   ```

3. 终端窗口将提示确认是否卸载程序，输入 “y/Y” 开始卸载 DM，输入 “n/N” 退出卸载程序：

   <img src="!assets/DM/图2.53-运行卸载程序.png" alt="删除目录" style="zoom:67%;" />

4. 在 Linux（Unix）系统下，使用非 root 用户卸载完成时，终端提示 “请以 root 系统用户执行命令”。用户需要手动执行相关命令：

   ```shell
   /home/dmdba/dmdbms/root_all_service_uninstaller.sh
   
   rm -rf /etc/dm_svc.conf
   ```

<br>

**图形化卸载**

用户在 DM 安装目录下，找到卸载程序 `uninstall.sh` 来执行卸载数据库，启动图形化卸载程序将会弹出提示框确认是否卸载程序。点击 “确定” 进入卸载小结页面，点击 “取消” 退出卸载程序。

卸载流程和命令行卸载一样。

<br>

### 2.2、Windows

Windows 安装和 Linux 环境图形化安装操作基本一致。

<br>

### 2.3、数据库目录结构

**数据库安装目录**

下图展示为 DM8 数据库目录。

<img src="!assets/DM/dm-dictionary.png" alt="目录" style="" />

- `/dm8/bin` 目录存放 DM 数据库的可执行文件，例如 `disql` 命令、`dminit` 命令、`dmrman` 工具等。
- `/dm8/desktop` 存放 DM 数据库各个工具的桌面图标。
- `/dm8/doc` 存放 DM 数据库用户手册。
- `/dm8/drivers` 存放连接 DM 数据库的驱动文件。
- `/dm8/log` 存放 DM 数据库日志，包括工具的日志、数据库日志、服务日志等。
- `/dm8/samples` 存放 DM 数据库各类配置文件的示例文件。
- `/dm8/script` 存放注册、注销 DM 数据库服务的工具，例如 dm_service_installer.sh 等。
- `/dm8/tool` 存放 DM 数据库的各个工具，例如 manager 管理工具、dbca 数据库配置助手等。
- `/dm8/uninstall` 目录存放卸载 DM 数据库的脚本。
- `/dm8/web` 目录存放 DM 数据库 dem 工具的 web 环境。

<br>

**数据库实例目录**

`/dm8/data` 为数据库实例目录，该目录存放各个实例的文件。

<img src="!assets/DM/dictionary-instance.png" alt="目录" style="" />

以实例 DAMENG 为例，该目录下存放 DAMENG 实例的配置文件（`*.ini`）、控制文件（`dm.ctl`）、数据文件（`*.DBF`）、日志文件（`*.log`） 等。

<img src="!assets/DM/dictionary-instance-DAMENG1.png" alt="目录" style="" />

<br>

## 3、SQL 交互式查询工具

disql 是一款命令行客户端工具，用于进行 SQL 交互式查询，disql 工具一般用于没有图形界面时的操作，或者使用的连接工具为命令行形式，如 Xshell、SCRT 等工具。

<br>

### 3.1、disql 登录数据库

#### 3.1.1、Linux 登录 disql

Linux 登录，进入数据库软件安装目录的 bin 目录下。登录方式主要有两种，分别如下：

<br>

**方式一**

```shell
./disql username/password@IP:PORT
```

以 `/home/dmdba/dmdbms/bin` 为例，如下所示：

```shell
./disql SYSDBA/SYSDBA@LOCALHOST:5236
```

<img src="!assets/DM/202407231508331967I025JMXMVX2VQK.png" alt="202407231508331967I025JMXMVX2VQK" style="zoom:80%;" />

如果密码含有特殊字符的情况下，需要使用双引号将密码包含进来，同时外层再使用单引号进行转义。以用户名 TEST，密码 `TEST@111#2024` 为例，如下所示：

```shell
./disql TEST/'"TEST@111#2024"'@127.0.0.1:5236
```

<img src="!assets/DM/20240723150931L5ZOIWO60GJRIMAXKS.png" alt="20240723150931L5ZOIWO60GJRIMAXKS" style="zoom:80%;" />

如果用户名、密码、端口号均为默认的情况下（用户名：SYSDBA ，密码：SYSDBA ，端口：5236 ），可直接输入 `./disql`，敲击回车按键，即可登录数据库。如下所示：

<img src="!assets/DM/QQ_1726074490724.png" alt="QQ_1726074490724" style="zoom: 25%;" />

<br>

**方式二**

```shell
## 进入disql操作终端界面，然后执行下面的操作
./disql /nolog

## 使用conn命令连接
conn 用户名/密码@IP:PORT
## 或者使用connet
connect 用户名/密码@IP:PORT
## 或者输入LOGIN命令
login
```

以 `/home/dmdba/dmdbms/bin` 为例，disql 中通过 `conn` 或者 `connect` 命令连接数据库，操作如下所示：

```shell
./disql /nolog

CONNECT TEST/'"TEST@111#2024"'@127.0.0.1:5236
```

<img src="!assets/DM/QQ_1726074907877.png" alt="QQ_1726074907877" style="zoom: 25%;" />

`login` 命令操作如下：

- 服务名：`IP:PORT` 或者 dm_svc.conf 文件中配置的服务名。
- 用户名：输入登录的数据库用户名。
- 密码：输入用户密码（密码无需加转义符）。

其余回车即可：

<img src="!assets/DM/QQ_1726074954932.png" alt="QQ_1726074954932" style="zoom: 25%;" />

<br>

### 3.2、disql 登出数据库

登出命令在 Windows、Linux 均相同，主要分为两类，一类是 `logout`、`disconnect`；另一类是 `exit`、`quit`。

其中 `logout`、`disconnect` 只退出或者断开当前登录的会话连接，不退出 disql；`exit`、`quit` 表示退出当前登录会话连接并且退出 disql 操作界面。如下所示：

<img src="!assets/DM/QQ_1726075327422.png" alt="QQ_1726075327422" style="zoom:25%;" />

<img src="!assets/DM/QQ_1726075343639.png" alt="QQ_1726075343639" style="zoom:25%;" />

<img src="!assets/DM/QQ_1726075360897.png" alt="QQ_1726075360897" style="zoom:25%;" />

<br>

### 3.3、disql 的使用

#### 3.3.1、脚本使用

disql 登录成功后，通过反引号 `` ` 和 `start` 命令加上脚本位置执行脚本，以 Linux 上脚本位置 `/home/dmdba/test.sql`、Windows 上脚本位置 `C:\dm8_326_p6\sel.sql` 为例，如下所示：

```shell
./disql TEST/'"TEST@111#2024"'@127.0.0.1:5236

start /home/dmdba/test.sql
`/home/dmdba/test.sql
```



<img src="!assets/DM/202407231529233K13R37GG5RDKDV4G0.png" alt="202407231529233K13R37GG5RDKDV4G0" style="zoom:80%;" />

也可在登录时直接同时进行脚本的执行，如下所示：

```shell
./disql TEST/'"TEST@111#2024"'@127.0.0.1:5236 \`/home/dmdba/test.sql
```

<img src="!assets/DM/20240723152653MTEBSP0Z1BUXKW2KVL.png" alt="20240723152653MTEBSP0Z1BUXKW2KVL" style="zoom:80%;" />

<img src="!assets/DM/20240723152719ICBB8YK83P07CQY2OU.png" alt="20240723152719ICBB8YK83P07CQY2OU" style="zoom:80%;" />

> [!IMPORTANT]
>
> Windows 环境下不需要对反引号 `` ` 进行转义，而 Linux 环境下需要对其进行转义。

<br>

#### 3.3.2、环境变量参数设置

可通过设置 disql 的参数，来调整交互界面的显示效果，以达成输出的显示结果更加直观。

通过 `SET` 命令语法进行使用，off 表示该参数关闭，on 表示该参数开启。

可以同时 SET 多个环境变量，如：`set heading on timing on` 。需要注意的是，SET 之后某个环境变量出错，那么该变量之后的环境变量参数将不再起作用。

disql 常用部分参数如下所示：

```SQL
## 设置一页有多少行数
SET PAGESIZE 1000

## 显示每个 SQL 语句花费的执行时间
SET TIMING ON

## 显示系统的当前时间
SET TIME ON

## 设置屏幕上一行显示宽度
SET LINESIZE 1000

## 关闭显示行号
SET LINESHOW OFF

## 设置查看执行计划
SET AUTOTRACE <OFF(缺省值) | NL | INDEX | ON | TRACE | TRACEONLY>

## 在块中有打印信息时，是否打印，以及打印的格式
SET SERVEROUTPUT ON

## 设置 SQL 语句的编码方式 GBK | GB18030 | UTF8 | DEFAULT
SET CHAR_CODE DEFAULT

## 设置兼容 MySQL 的打印格式
SET ISQL_MODE 3

## DISQL 中使用 INSERT 语句且当插入的值包含 & 符号时，需要将 DEFINE 环境变量关闭或者设置成其他值。
## DEFINE 环境变量默认为 & 前缀，即 SQL 语句中包含 & 符号时，& 符号后面的字符会识别为 SQL 语句中变量名，
## 如后面字符中包含 $ 符号，则从 & 符号与 $ 符号中间的字符识别为 SQL 语句中的变量名。
## 关闭 DEFINE 功能
SET DEFINE OFF

## 输出到文件
SPOOL /home/dmdba/dbchk20200609.txt 
## 结束输出文件
SPOOL OFF; 
```

<br>

## 4、基本操作

### 4.1、创建表空间

**新建表空间**

使用命令创建表空间 TEST， 指定数据库文件为 `/data/dmdata/DAMENG/TEST.DBF`，初始大小为 128 M，其它默认，示例如下：

```SQL
create tablespace "TEST" datafile '/data/dmdata/DAMENG/TEST.DBF' size 128;
```

<br>

**修改表空间参数**

修改表空间，打开自动扩展，每次自动扩展 100 M ，扩展上限 10240 M，参考示例如下：

```SQL
alter tablespace "TEST" datafile '/data/dmdata/DAMENG/TEST.DBF' autoextend on next 100 maxsize 10240;
```

<br>

**新建表空间设置加密算法、密码**

创建表空间 TEST， 指定数据库文件为 `/data/dmdata/DAMENG/TEST.DBF`，初始大小为 128 M，打开自动扩展，每次自动扩展 100 M ，扩展上限 10240 M，使用 RC4 加密算法，参考示例如下：

```sql
create tablespace "TEST" datafile '/data/dmdata/DAMENG/TEST.DBF' size 128 autoextend on next 100 maxsize 10240 CACHE = NORMAL encrypt with RC4;
```

<br>

### 4.2、创建用户

**新建用户**

使用命令行方式创建用户 TEST ，密码 Dameng@123，使用散列算法 SHA512 ，使用存储加密密钥为 123456，指定表空间为 TEST，索引表空间为 TEST，示例参考如下：

```SQL
create user "TEST" identified by "Dameng@123" hash with SHA512 salt
encrypt by "123456"
default tablespace "TEST"
default index tablespace "TEST";
```

<br>

**授权**

TEST 用户授予 PUBLIC 和 SOI 权限，示例参考如下：

```sql
grant "PUBLIC","SOI" to "TEST";
```

<br>

### 4.3、查询数据库信息

**查看授权**

```sql
select * from v$license;
```

<br>

**查看数据库表数量**

```sql
-- 查询整个数据库所有用户的表数量
SELECT COUNT(*) FROM ALL_TABLES;

-- 查询多个用户（Schema）的表数量
SELECT OWNER, COUNT(*) AS TABLE_COUNT
FROM DBA_TABLES
WHERE OWNER IN ('CRRC', 'CRRC_WF', 'CRRC_XXL_JOB')
GROUP BY OWNER;
```

<br>

## 5、数据库配置

### 5.1、数据库状态和模式

DM 数据库包含以下几种状态：

1. 配置状态（MOUNT）：不允许访问数据库对象，只能进行控制文件维护、归档配置、数据库模式修改等操作。
2. 打开状态（OPEN）：不能进行控制文件维护、归档配置等操作，可以访问数据库对象，对外提供正常的数据库服务。
3. 挂起状态（SUSPEND）：与 OPEN 状态的唯一区别就是，限制磁盘写入功能；一旦修改了数据页，触发 REDO 日志、数据页刷盘，当前用户将被挂起。

OPEN 状态与 MOUNT 和 SUSPEND 能相互转换，但是 MOUNT 和 SUSPEND 之间不能相互转换。

DM 数据库包含以下几种模式：

1. 普通模式（NORMAL）：用户可以正常访问数据库，操作没有限制。
2. 主库模式（PRIMARY）：用户可以正常访问数据库，所有对数据库对象的修改强制生成 REDO 日志，在归档有效时，发送 REDO 日志到备库。
3. 备库模式（STANDBY）：接收主库发送过来的 REDO 日志并重做。数据对用户只读。

三种模式只能在 MOUNT 状态下设置，模式之间可以相互转换。

对于新初始化的库，首次启动不允许使用 MOUNT 方式，需要先正常启动并正常退出，然后才允许 MOUNT 方式启动。

一般情况下，数据库为 NORMAL 模式，如果不指定 MOUNT 状态启动，则自动启动到 OPEN 状态。

在需要对数据库配置时（如配置数据守护、数据复制），服务器需要指定 MOUNT 状态启动。当数据库模式为非 NORMAL 模式（PRIMARY、STANDBY 模式），无论是否指定启动状态，服务器启动时自动启动到 MOUNT 状态。

<br>

#### 5.1.1、状态切换

**命令行方式**

以 SYSDBA 角色连接数据库后，可执行命令切换数据库状态。

将数据库转为 MOUNT 配置状态，可读取数据库配置文件，不可对数据文件读写：

```sql
alter database mount;
```

将数据库转为 OPEN 打开状态，可读取数据库配置文件，可对数据文件读写：

```sql
alter database open;
```

<br>

**图形化界面配置**

1. 打开 DM管理工具 => 连接数据库 => 右键选择【管理服务器】：

   <img src="!assets/DM/image-20230922004306995.png" alt="image-20230922004306995" style="zoom: 67%;" />

2. 切换到【系统管理】页面，选择需要切换的状态，最后点击【转换】：

   <img src="!assets/DM/image-20230922004422477.png" alt="image-20230922004422477" style="zoom:67%;" />

   左侧目录刷新，即可看到数据库对象信息。

<br>

### 5.2、启动和停止

#### 5.2.1、Windows 系统

**菜单方式**

安装 DM 数据库后默认情况下 DM 服务会自动启动，在 Windows 的开始菜单中选择【DM 服务查看器】可以启动或停止 DM 数据库：

<img src="!assets/DM/image-20250326004256779.png" alt="image-20250326004256779" style="zoom: 67%;" />

<br>

**Windows 服务**

安装 DM 数据库并且新建一个 DM 实例后，Windows 的服务中会自动增加一项和该实例名对应的服务。例如新建一个实例名为 DMSERVER 的 DM 数据库， Windows 的服务中会增加一项名称为【DmServiceDMSERVER】 的服务。

打开 Windows 服务，右键选择【DmServiceDMSERVER】启动或者停止 DM 数据库：

<img src="!assets/DM/image-20250326004644792.png" alt="image-20250326004644792" style="zoom: 50%;" />

<br>

#### 5.2.2、Linux 系统

**菜单方式**

安装 DM 数据库后默认情况下 DM 服务会自动启动，在 Linux 的开始菜单选项中选择【DM 服务查看器】可以启动或停止 DM 数据库。启动方式类似 Windows。

<br>

**Linux 服务**

安装 DM 数据库后，在 `/etc/rc.d/init.d` 中有名称为 DmService 开头的文件，文件全名为 `DmService+实例名`。

在数据库的安装目录 bin 下输入命令可以启动或停止 DM 数据库：

```shell
## 启动
./DmServiceDMSERVER start 
## 停止
./DmServiceDMSERVER stop
```

或者直接使用 `service` 命令：

```shell
## 启动
service DmServiceDMSERVER start
## 停止
service DmServiceDMSERVER stop
```

<br>

### 5.3、数据库的兼容性

达梦数据库可以通过修改实例的配置文件 dm.ini 中的参数 `COMPATIBLE_MODE` 用来兼容不同的数据库，此参数为静态参数，修改后只有重启数据库服务才能生效。

`COMPATIBLE_MODE` 有以下值：

- 0：不兼容。
- 1：兼容 SQL92 标准。
- 2：部分兼容 ORACLE。
- 3：部分兼容 MS SQL SERVER。
- 4：部分兼容 MYSQL。
- 5：兼容 DM6。
- 6：部分兼容 TERADATA。

可通过以下 SQL 查询 `COMPATIBLE_MODE` 值：

```sql
SELECT para_name, para_type, para_value FROM V$DM_INI WHERE PARA_NAME ='COMPATIBLE_MODE';
```

<br>

**通过 disql 修改此参数**

修改此参数为 2，兼容 Oracle 数据库：

```sql
sp_set_para_value(2,'compatible_mode',2);
```

重启数据库服务，查看此参数已修改。

<br>

**通过 dm.ini 配置文件修改此参数**

通过 `sed` 命令直接修改 dm.ini 配置文件中的 `COMPATIBLE_MODE` 参数为 0，不兼容：

```shell
sed -i 's/^COMPATIBLE_MODE *= *.*/COMPATIBLE_MODE = 0/' dm.ini
```

重启数据库服务，查看此参数已修改。

<br>

## 6、备份和迁移

### 6.1、DTS 工具迁移

DM 数据迁移工具 DM DTS 提供了主流大型数据库迁移到 DM、DM 到 DM、文件迁移到 DM 以及 DM 迁移到文件等功能。

得益于 DM 数据库对目前主流大型关系型数据库系统有着业界领先的兼容性，在存储层面、语法层面、接口层面和它们保持高度兼容，借助于 DM 图形界面且采用向导方式引导各个迁移步骤的 DTS 工具，移植工作可以变得非常的简单。

1. 新建工程：

   <img src="!assets/DM/20210202164357901.png" alt="img" style="" />

2. 新建迁移：

   <img src="!assets/DM/20210202164528157.png" alt="img" style="" />

3. 双击打开 dm-dm 进行迁移配置：

   <img src="!assets/DM/20210202164622570.png" alt="img" style="" />

4. 选择相应的迁移模式（此处选【dm ==> dm】迁移）：

   <img src="!assets/DM/20210202164743774.png" alt="img" style="" />

5. 填写相应的源数据库和目标数据库的连接信息：

   <img src="!assets/DM/2021020216493840.png" alt="img" style="" />

6. 选择要迁移的源模式，并指定目的模式，其中 "目录"、"公共同义词"、"上下文" 取消勾选：

   <img src="!assets/DM/20210202165336811.png" alt="img" style="" />

7. 勾选相应的对象，点击【转换】选择迁移策略，可以根据实际情况选择只迁移表结构、数据、约束，或删除原有数据再迁移等具体情况，大字段表选项改小 256 即可

   <img src="!assets/DM/20210202165939365.png" alt="img" style="" />

8. 确认无误后完成即可：

   <img src="!assets/DM/20210202170509591.png" alt="img" style="" />

<br>

### 6.2、物理备份还原

物理备份是找出那些已经分配、使用的数据页，拷贝并保存到备份集中。物理还原是物理备份的逆过程，物理还原一般通过 DMRMAN 工具（或者 SQL 语句），把备份集中的数据内容（数据文件、数据页、归档文件）重新拷贝、写入目标文件。

- 联机备份还原：联机备份还原指数据库处于运行状态时，并正常提供数据库服务情况下进行的备份还原操作，称为联机备份还原。
- 脱机备份还原：脱机还原指数据库处于关闭状态时执行的还原操作。库备份、表空间备份和归档备份，可以执行脱机还原。脱机还原操作的目标库必须处于关闭状态。
- 备份集：备份集用来存放备份过程中产生的备份数据及备份信息。一个备份集对应了一次完整的备份。一般情况下，一个备份集就是一个目录，备份集包含一个或多个备份片文件，以及一个备份元数据文件。

<br>

#### 6.2.1、准备工作

联机备份数据库必须要配置归档。联机备份时，大量的事务处于活动状态，为确保备份数据的一致性，需要同时备份一段日志（备份期间产生的 REDO 日志），因此要求数据库必须配置本地归档且归档处于开启状态。

脱机备份数据库可配置归档也可以不配置。正常退出的库的备份不需要考虑本地归档日志的完整性，可以不配置归档；但对于故障退出的库的备份要求因故障未刷盘的日志也必须存在于本地归档中，因此必须配置归档。

归档配置有两种方式：一是联机归档配置，数据库实例启动情况下，使用 SQL 语句完成 dmarch.ini 和 ARCH_INI 配置；二是手动配置归档，数据库实例未启动的情况下，手动编写 dmarch.ini 文件和设置参数 ARCH_INI。

<br>

##### 联机归档配置

**命令行配置**

1. 修改数据库为 Mount 状态：

   ```sql
   ALTER DATABASE MOUNT;
   ```

2. 开启归档模式：

   ```sql
   ALTER DATABASE ARCHIVELOG;
   ```

3. 配置本地归档：

   ```sql
   ALTER DATABASE ADD ARCHIVELOG 'DEST = /data/dm_arch/arch, TYPE = local, FILE_SIZE = 1024, SPACE_LIMIT = 2048';
   ```

4. 修改数据库为 Open 状态：

   ```sql
   ALTER DATABASE OPEN;
   ```

<br>

**图形化配置**

1. 右键数据库连接，选择【管理服务器】，切换到【系统管理】，将状态转换为【配置】：

   <img src="!assets/DM/image-20240812180155686.png" alt="image-20240812180155686" style="zoom: 67%;" />

2. 切换到【归档配置】，将归档模式设置为【归档】，并添加一个本地的归档目标：

   <img src="!assets/DM/image-20240812180342701.png" alt="image-20240812180342701" style="zoom:67%;" />

   > [!WARNING]
   >
   > 此处为归档日志目录，并且添加了之后无法删除！！！
   
3. 点击确定后，再打开【管理服务器】回到【系统管理】，将状态切换为【打开】：

   <img src="!assets/DM/image-20240812180502625.png" alt="image-20240812180502625" style="zoom:67%;" />

<br>

#### 6.2.2、联机备份还原

联机方式支持数据库、用户表空间、用户表和归档的备份以及用户表的还原。在进行联机库级备份、归档备份和表空间备份时，必须保证系统处于归档模式，否则联机备份不能进行。

<br>

##### 数据备份

这里仅讲述数据库备份使用场景。

<br>

**命令行备份**

在 disql 工具或图形化管理工具 SQL 编辑区中使用 `BACKUP` 语句可以备份整个数据库，执行以下命令：

```sql
BACKUP DATABASE FULL BACKUPSET '/data/dm_bak/bak_name';
```

> [!CAUTION]
>
> 确保 dmdba 用户拥有备份目录文件夹读写的权限，否则会备份失败！

设置数据库备份选项：

- 设置联机数据库备份集路径：

  ```sql
  # 指定备份集路径为 /home/dm_bak/db_bak_3_01
  BACKUP DATABASE BACKUPSET '/home/dm_bak/db_bak_3_01';
  ```

- 设置备份名：

  ```sql
  # 创建备份集，备份名设置为“WEEKLY_FULL_BAK”
  BACKUP DATABASE TO WEEKLY_FULL_BAK BACKUPSET '/home/dm_bak/db_bak_3_02';
  ```

  > [!CAUTION]
  >
  > 备份名的设置不可以使用特殊格式，例如 `%NAME`。

- 添加备份描述：
  ```sql
  # 创建备份为备份集添加描述信息为“完全备份”。
  BACKUP DATABASE BACKUPSET '/home/dm_bak/db_bak_3_04' BACKUPINFO  '完全备份';
  ```

- 限制备份片大小：

  ```sql
  # 创建备份限制备份片大小为300M
  BACKUP DATABASE BACKUPSET '/home/dm_bak/db_bak_3_05' MAXPIECESIZE 300;
  ```

  > [!CAUTION]
  >
  > `MAXPIECESIZE` 不能大于磁盘剩余空间大小，否则报错磁盘空间不足。

- 备份压缩：

  压缩选项有不同的压缩级别可以选择，取值范围为 0~9，应根据存储空间、数据文件大小等确定合适地压缩级别。

  ```sql
  # 执行备份压缩，压缩级别设置为5。
  BACKUP DATABASE BACKUPSET '/home/dm_bak/db_bak_3_06' COMPRESSED LEVEL 5;
  ```

- 设置并行备份：

  ```sql
  # 创建并行备份，指定并行数为8
  BACKUP DATABASE BACKUPSET '/home/dm_bak/db_bak_3_07' PARALLEL 8;
  ```

<br>

**图形化备份**

在连接里找到【备份】，右键【库备份】选择【新增备份】，点击确定就好：

<img src="!assets/DM/image-20240812180633492.png" alt="image-20240812180633492" style="zoom:67%;" />

备份文件在：`<数据目录>/bak/` 文件夹里。

<br>

##### 数据还原

达梦数据库仅支持表的联机还原，数据库、表空间和归档日志的还原必须通过脱机工具 DMRMAN 执行，详细内容见[脱机备份还原](###4.2.3、脱机备份还原)。

<br>

#### 6.2.3、脱机备份还原

DMRMAN（DM RECOVERY MANAGER）是脱机备份还原命令行工具，无需额外安装，由它来统一负责库级脱机备份、脱机还原、脱机恢复等相关操作，该工具支持命令行指定参数方式和控制台交互方式执行，降低用户的操作难度。

启动和退出 DMRMAN：

```shell
## 进入数据库安装目录的 bin 目录下，例如 /dm8/bin
cd /dm8/bin
## 启动DMRMAN
./dmrman

## 启动后控制台中输入 exit 命令即可退出
exit;
```

<br>

##### 数据备份

<br>

##### 数据还原

这里仅讲述数据库备份使用场景。

> [!CAUTION]
>
> 在数据还原时，要使用 dmdba 账号进行操作，并且确保 dmdba 账户拥有备份目录的操作权限！！！

1. 数据库还原：

   使用 `RESTORE` 命令完成脱机还原操作，在还原语句中指定库级备份集，可以是脱机库级备份集，也可以是联机库级备份集。

   > [!CAUTION]
   >
   > 通过 `RESTORE` 命令还原后的数据库不可用，需进一步执行 `RECOVER` 命令，将数据库恢复到备份结束时的状态。

   以联机数据库备份说明使用 DMRMAN 如何执行数据库还原操作：

   ```sql
   # 校验备份，校验待还原备份集的合法性。校验备份有两种方式，联机和脱机，此处使用脱机校验。
   CHECK BACKUPSET '/home/dm_bak/db_full_bak_for_restore';
   
   #还原数据库。
   RESTORE DATABASE '/opt/dmdbms/data/DAMENG_FOR_RESTORE/dm.ini' FROM BACKUPSET '/home/dm_bak/db_full_bak_for_restore';
   ```

2. 数据库恢复：

   使用 `RECOVER` 命令完成数据库恢复工作，可以是基于备份集的恢复工作，也可以是使用本地归档日志的恢复工作。

   从备份集恢复，即重做备份集中的 REDO 日志：

   ```sql
   # 执行还原数据库的命令之后，可以直接执行恢复数据库的命令从备份集恢复。
   RECOVER DATABASE '/opt/dmdbms/data/DAMENG_FOR_RESTORE/dm.ini' FROM  BACKUPSET '/home/dm_bak/db_full_bak_for_recover_backupset';
   ```

   或从归档恢复，即重做归档中的 REDO 日志：

   ```sql
   # 通过使用 WITH ARCHIVEDIR 关键字进行归档恢复，如下：
   RECOVER DATABASE '/opt/dmdbms/data/DAMENG_FOR_RESTORE/dm.ini' WITH ARCHIVEDIR'/home/dm_arch/arch' 
   ```

3. 数据库更新：

   数据库更新是指更新数据库的 DB_MAGIC，并将数据库调整为可正常工作状态，与数据库恢复一样使用 `RECOVER` 命令完成。数据库更新发生在重做 REDO 日志恢复数据库后。

   ```sql
   RECOVER DATABASE '/opt/dmdbms/data/DAMENG_FOR_RESTORE/dm.ini' UPDATE DB_MAGIC;
   ```

<br>

### 6.3、逻辑备份还原

逻辑导出（dexp）和逻辑导入（dimp）是 DM 数据库的两个命令行工具，分别用来实现对 DM 数据库的逻辑备份和逻辑还原。逻辑备份和逻辑还原都是在联机方式下完成，联机方式是指数据库服务器正常运行过程中进行的备份和还原 dexp 和 dimp 是 DM 数据库自带的工具，只要安装了 DM 数据库，就可以在安装目录 `/dmdbms/bin` 中找到。

逻辑导出和逻辑导入数据库对象分为四种级别：数据库级、用户级、模式级和表级。四种级别独立互斥，不能同时存在。四种级别所提供的功能：

- 数据库级（FULL）：导出或导入整个数据库中的所有对象。
- 用户级（OWNER）：导出或导入一个或多个用户所拥有的所有对象。
- 模式级（SCHEMAS）：导出或导入一个或多个模式下的所有对象。
- 表级（TABLES）：导出或导入一个或多个指定的表或表分区。

<br>

#### 6.3.1、逻辑导出

dexp 工具可以对本地或者远程数据库进行数据库级、用户级、模式级和表级的逻辑备份。备份的内容非常灵活，可以选择是否备份索引、数据行和权限，是否忽略除主键和非空约束外的其他约束，在备份前还可以选择生成日志文件，记录备份的过程以供查看。

dexp 工具名称有两种写法 dexp 和 dexpdp，两者区别在于：dexp 导出的文件必须存放在客户端，dexpdp 导出的文件必须存放在服务器端。另外由于 dexpdp 是将客户端命令发给服务器启动 dexp 执行，如果客户端连接使用 dm_svc.conf 配置，服务器端也应该跟客户端配置一致。

<br>

**使用 dexp 工具**

dexp 工具需要从命令行启动。在 cmd 命令行工具中找到 dexp 所在安装目录 `/dmdbms/bin`，输入 dexp 和参数后回车。

语法如下：

```shell
dexp PARAMETER=value { PARAMETER=value }

dexpdp PARAMETER=value { PARAMETER=value }
```

- *PARAMETER*：dexp 参数。参数 USERID 必须置于首位，此外的多个参数之间排列顺序无影响，参数之间使用空格间隔。
- *`<value>`*：参数取值。

以用户名为 SYSDBA、密码 “Dmsys_123” 为例，IP 地址为 192.168.0.248，端口号为 8888 的数据库采用 FULL 方式完全导出，导出文件名为 db_str.dmp，导出的日志文件名为 db_str.log，导出文件的路径为 `/mnt/dexp/data`：

```shell
./dexp USERID=SYSDBA/Dmsys_123@192.168.0.248:8888 FILE=db_str.dmp DIRECTORY=/mnt/dexp/data LOG=db_str.log FULL=Y
```

> [!NOTE]
>
> 如果密码含有特殊字符的情况下，需要使用双引号将密码包含进来，同时外层再使用单引号进行转义。

<br>

**dexp 参数一览表**

> [!NOTE]
>
> 每个参数含义后面的括号内为（N）则表示该参数缺省为否，为（Y）表示缺省为是；`()` 内的值表示的是参数的缺省值。

| 参数                         | 含义                                                         | 备注                                                         |
| ---------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| USERID                       | 数据库的连接信息                                             | 必选                                                         |
| FILE                         | 明确指定导出文件名称                                         | 可选                                                         |
| LOG                          | 明确指定日志文件名称                                         | 可选                                                         |
| DIRECTORY                    | 导出文件所在目录                                             | 可选                                                         |
| FULL、OWNER、SCHEMAS、TABLES | FULL：导出整个数据库（N）<br />OWNER：用户名列表，导出一个或多个用户所拥有的所有对象<br />SCHEMAS：模式列表，导出一个或多个模式下的所有对象<br />TABLES：表名列表，导出一个或多个指定的表或表分区 | 可选，四者中选其一。<br />缺省为 SCHEMAS                     |
| FUZZY_MATCH                  | TABLES选项是否启用模糊匹配（N）                              | 可选                                                         |
| QUERY                        | 用于指定对导出表的数据进行过滤的条件                         | 可选                                                         |
| PARALLEL                     | 用于指定导出的过程中所使用的线程数目（16）                   | 可选                                                         |
| TABLE_PARALLEL               | 用于指定导出每张表所使用的线程数（8）；在 MPP 模式下会转换成单线程 | 可选                                                         |
| TABLE_POOL                   | 用于设置导出过程中存储表的缓冲区个数（8）                    | 可选                                                         |
| EXCLUDE                      | 批量设置导出内容中忽略的对象。<br /> 1. `EXCLUDE=[(]<对象种类名>{,<对象种类名>}[)]` <br />对象种类可为：CONSTRAINTS、INDEXES、ROWS、TRIGGERS、GRANTS、VIEWS、PROCEDURE、PACKAGE、SEQUENCE、TABLES。<br />2. `EXCLUDE=TYPE:name1,name2` <br />TYPE 可为：SCHEMAS、TABLES、VIEWS、PROCEDURE、PACKAGE、SEQUENCE。<br />3. `EXCLUDE=TYPE:cond{,TYPE:cond}` <br />TYPE 可取值同上，cond 为 IN 或 LIKE 过滤条件 | 可选                                                         |
| INCLUDE                      | 批量设置导出时只导出指定的对象种类或某个具体对象。 <br />1. `INCLUDE=[(]<对象种类名>{,<对象种类名>}[)]` <br />对象种类可为：CONSTRAINTS、INDEXES、ROWS、TRIGGERS、GRANTS、VIEWS、PROCEDURE、PACKAGE、SEQUENCE、TABLES。<br />2. `INCLUDE=TYPE:name1,name2` <br />TYPE 可为： SCHEMAS、TABLES、VIEWS、PROCEDURE、PACKAGE、SEQUENCE。 <br />3. `INCLUDE=TYPE:cond{,TYPE:cond}` <br />TYPE 可取值同上，cond 为 IN 或 LIKE 过滤条件 | 可选                                                         |
| TABLESPACE                   | 导出表空间/表空间组和表空间/表空间组存储选项（N）            | 可选                                                         |
| CONSTRAINTS                  | 导出约束（Y）                                                | 可选                                                         |
| GRANTS                       | 导出权限（Y）                                                |                                                              |
| INDEXES                      | 导出索引（Y）                                                |                                                              |
| TRIGGERS                     | 导出触发器（Y）                                              |                                                              |
| ROWS                         | 导出数据行（Y）                                              |                                                              |
| NOLOGFILE                    | 不使用日志文件（N）                                          | 可选                                                         |
| NOLOG                        | 屏幕上不显示日志信息（N）                                    | 可选                                                         |
| LOG_WRITE                    | 日志信息实时写入文件 （N）                                   | 可选                                                         |
| DUMMY                        | 设置交互信息处理方式（P：打印）                              | 可选                                                         |
| PARFILE                      | 参数文件名，如果 dexp 的参数很多，可以存成参数文件           | 可选                                                         |
| FEEDBACK                     | 每 x 行显示进度 （0）                                        | 可选                                                         |
| COMPRESS                     | 是否压缩导出数据文件（N）                                    | 可选                                                         |
| COMPRESS_LEVEL               | 导出数据压缩等级（1）。取值范围 0~9                          | 可选                                                         |
| ENCRYPT                      | 导出数据是否加密 （N）                                       | 可选，                                                       |
| ENCRYPT_PASSWORD             | 导出数据的加密密钥                                           | 和 ENCRYPT 同时使用                                          |
| ENCRYPT_NAME                 | 导出数据的加密算法                                           | 可选。和 ENCRYPT、ENCRYPT_PASSWORD<br />同时使用。缺省为 RC4 |
| FILESIZE                     | 用于指定单个导出文件大小的上限。可以按字节[B]、K[B]、M[B]、G[B] 的方式指定大小 | 可选                                                         |
| FILENUM                      | 多文件导出时，一个模板可以生成的文件数（99）。取值范围 1~99  | 可选                                                         |
| DROP                         | 导出后删除原表，但不级联删除 （N）                           | 可选                                                         |
| DESCRIBE                     | 导出数据文件的描述信息，记录在数据文件中                     | 可选                                                         |
| FLASHBACK_SCN                | 用于指定导出表数据的闪回 LSN，和 FLASHBACK_TIME 一起使用时只有一个能生效，参数位置靠后的生效 | 可选                                                         |
| FLASHBACK_TIME               | 用于指定导出表数据的闪回时间，和 FLASHBACK_SCN 一起使用时只有一个能生效，参数位置靠后的生效 | 可选                                                         |
| COL_DEFAULT_SEPARATE         | 是否单独导出列（Y）                                          | 可选                                                         |
| WITH_UR                      | 导出表数据是否允许脏读（N）                                  | 可选                                                         |
| SIMPLE_LOG                   | 导出日志是否使用简要日志（N）                                | 可选                                                         |
| CTRL_INFO                    | 控制信息用来控制一些特殊情况导出（0）                        | 可选                                                         |
| CONFIG_FILE                  | 配置文件路径，配置默认连接串和密码信息                       | 可选                                                         |
| FILE_VERSION                 | 用于指定导出的 dmp 文件的逻辑版本，有效范围 9~28             | 可选                                                         |
| HELP                         | 显示帮助信息                                                 | 可选                                                         |

<br>

#### 6.3.2、逻辑导入

dimp 逻辑导入工具利用 dexp 工具生成的备份文件对本地或远程的数据库进行联机逻辑还原。dimp 导入是 dexp 导出的相反过程。还原的方式可以灵活选择，如是否忽略对象存在而导致的创建错误、是否导入约束、是否导入索引、导入时是否需要编译、是否生成日志等。

dimp 工具名称有两种写法 dimp 和 dimpdp，两者区别在于：dimp 导入的文件必须存放在客户端，dimpdp 导入的文件必须存放在服务器端。另外由于 dimpdp 是将客户端命令发给服务器启动 dimp 执行，如果客户端连接使用 dm_svc.conf 配置，服务器端也应该跟客户端配置一致。

<br>

**使用 dimp 工具**

dimp 工具需要从命令行启动。在 cmd 命令行工具中找到 dimp 所在安装目录/dmdbms/bin，输入 dimp 和参数后回车。参数在下一节详细介绍。

**语法如下：**

```shell
dimp PARAMETER=value { PARAMETER=value }

dimpdp PARAMETER=value { PARAMETER=value }
```

- *PARAMETER*：dimp 参数。其中 USERID 必须为第一个参数，其余参数之间排列顺序无影响，参数之间使用空格间隔。
- *value*：参数取值。

以逻辑备份采用 FULL 方式完全导入到用户名为 SYSDBA、密码 “Dmsys_123” 为例，实际运行中需要用户自行替换为数据库初始化时设定的密码。IP 地址为 192.168.0.248，端口号为 8888 的数据库。导入文件名为 db_str.dmp，导入的日志文件名为 db_str.log，路径为 `/mnt/data/dexp`：

```shell
./dimp USERID=SYSDBA/Dmsys_123@192.168.0.248:8888 FILE=db_str.dmp DIRECTORY=/mnt/data/dexp LOG=db_str.log FULL=Y
```

<br>

**dimp 参数一览表**

> [!NOTE]
>
> 每个参数含义后面的括号内为（N）则表示该参数缺省为否，为（Y）表示缺省为是；`()` 内的值表示的是参数的缺省值。

| 参数                         | 含义                                                         | 备注                                                 |
| ---------------------------- | ------------------------------------------------------------ | ---------------------------------------------------- |
| USERID                       | 数据库的连接信息                                             | 必选                                                 |
| FILE                         | 输入文件，即 dexp 或 dexpdp 导出的文件                       | 可选                                                 |
| LOG                          | 日志文件                                                     | 可选                                                 |
| DIRECTORY                    | 导入文件所在目录                                             | 可选                                                 |
| FULL、OWNER、SCHEMAS、TABLES | FULL：整库导入（N）<br />OWNER：以用户方式导入<br />SCHEMAS：以模式方式导入<br />TABLES：以表名方式导入，指定导入的tables名称。不支持对外部表进行导入 | 可选，四者中选其一。缺省为导入的文件导出时所用的方式 |
| PARALLEL                     | 用于指定导入的过程中所使用的线程数目（16）                   | 可选                                                 |
| TABLE_PARALLEL               | 用于指定导入的过程中每个表所使用的子线程数目（8）            | 可选。在FAST_LOAD为Y时有效                           |
| IGNORE                       | 忽略创建错误 （N）。如果表已经存在则向表中插入数据，否则报错表已经存在。 | 可选                                                 |
| TABLE_EXISTS_ACTION          | 需要的导入表在目标库中存在时采取的操作<br />[SKIP \| APPEND \| TRUNCATE \| REPLACE \| TRUNCATE_CASCADE] | 可选                                                 |
| FAST_LOAD                    | 是否使用dmfldr进行数据导入（N）                              | 可选                                                 |
| FLDR_ORDER                   | 使用dmfldr是否需要严格按顺序来导数据（Y）                    | 可选                                                 |
| COMMIT_ROWS                  | 批量提交的行数（5000）                                       | 可选                                                 |
| EXCLUDE                      | 批量设置导入时忽略的对象种类。 <br />1. `EXCLUDE=[(]<对象种类名>{,<对象种类名>}[)]` <br />对象种类可为：CONSTRAINTS、INDEXES、ROWS、TRIGGERS、GRANTS、VIEWS、PROCEDURE、PACKAGE、SEQUENCE、TABLES 、JOB。<br />2. `EXCLUDE=TYPE:name1,name2` <br />TYPE 可为： SCHEMAS、TABLES、VIEWS、PROCEDURE、PACKAGE、SEQUENCE。 | 可选                                                 |
| INCLUDE                      | 批量设置导入时只导入指定的对象种类或某个具体对象。 <br />1. `INCLUDE=[(]<对象种类名>{,<对象种类名>}[)]` <br />对象种类可为：CONSTRAINTS、INDEXES、ROWS、TRIGGERS、GRANTS、VIEWS、PROCEDURE、PACKAGE、SEQUENCE、TABLES 、JOB。<br />2. `INCLUDE=TYPE:name1,name2` <br />TYPE 可为：SCHEMAS、TABLES、VIEWS、PROCEDURE、PACKAGE、SEQUENCE。 | 可选                                                 |
| GRANTS                       | 导入权限 （Y）                                               | 可选                                                 |
| CONSTRAINTS                  | 导入约束 （Y）                                               | 可选                                                 |
| INDEXES                      | 导入索引 （Y）                                               | 可选                                                 |
| TRIGGERS                     | 导入触发器（Y）                                              | 可选                                                 |
| ROWS                         | 导入数据行 （Y）                                             | 可选                                                 |
| NOLOGFILE                    | 不使用日志文件（N）                                          | 可选                                                 |
| NOLOG                        | 屏幕上不显示日志信息（N）                                    | 可选                                                 |
| DUMMY                        | 设置交互信息处理（P：打印）                                  | 可选                                                 |
| LOG_WRITE                    | 日志信息实时写入文件（N）                                    | 可选                                                 |
| PARFILE                      | 参数文件名，如果 dimp 的参数很多，可以存成参数文件           | 可选                                                 |
| FEEDBACK                     | 显示每 x 行（0）的进度                                       | 可选                                                 |
| COMPILE                      | 编译过程, 程序包和函数 （Y）                                 | 可选                                                 |
| INDEXFILE                    | 将表的索引/约束信息写入指定的文件                            | 可选                                                 |
| INDEXFIRST                   | 导入时先建索引（N）                                          | 可选                                                 |
| REMAP_SCHEMA                 | 格式：`SOURCE_SCHEMA:TARGET_SCHEMA` <br />将 SOURCE_SCHEMA 中的数据导入到 TARGET_SCHEMA 中 | 可选                                                 |
| ENCRYPT_PASSWORD             | 数据的加密密钥                                               | 可选。和 dexp 中的ENCRYPT_PASSWORD 设置的密钥一样    |
| ENCRYPT_NAME                 | 数据的加密算法的名称                                         | 可选。和 dexp 中的ENCRYPT_NAME 设置的加密算法一样    |
| SHOW/ DESCRIBE               | 打印出指定文件的信息 （N）                                   | 可选                                                 |
| TASK_THREAD_NUMBER           | 用于设置 dmfldr 处理用户数据的线程数目                       | 可选                                                 |
| BUFFER_NODE_SIZE             | 用于设置 dmfldr 读入文件缓冲区大小                           | 可选                                                 |
| TASK_SEND_NODE_NUMBER        | 用于设置 dmfldr 发送节点个数，取值范围 16~65535              | 可选                                                 |
| LOB_NOT_FAST_LOAD            | 如果一个表含有大字段，则不使用 dmfldr（N）                   | 可选                                                 |
| PRIMARY_CONFLICT             | 主键冲突的处理方式，默认报错                                 | 可选                                                 |
| TABLE_FIRST                  | 是否强制先导入表（N）。Y表示先导入表，N正常导入              | 可选                                                 |
| SHOW_SERVER_INFO             | 是否显示服务器信息 （N）<br />Y 表示显示导出文件对应服务器信息，实际不导入<br />N 表示不显示导出文件对应服务器信息，正常导入 | 可选                                                 |
| IGNORE_INIT_PARA             | 指定源库和目标库之间忽略差异的建库参数（0）。<br />0：不忽略建库参数差异<br />1：忽略CASE_SENSITIVE<br />2：忽略LENGTH_IN_CHAR<br />3：忽略 CASE_SENSITIVE 和 LENGTH_IN_CHAR | 可选                                                 |
| AUTO_FREE_KEY                | 导入数据完成后, 是否释放密钥（N）。Y：是，N：否              | 可选                                                 |
| REMAP_TABLE                  | 格式：`SOURCE_TABLE:TARGET_TABLE`<br />将 SOURCE_TABLE 中的数据导入到 TARGET_TABLE 中 | 可选                                                 |
| REMAP_TABLESPACE             | 格式：`SOURCE_TABLESPACE:TARGET_TABLESPACE` <br />将 SOURCE_TABLESPACE 表空间映射到 TARGET_TABLESPACE 表空间中 | 可选                                                 |
| SIMPLE_LOG                   | 导入日志是否使用简要日志（N）                                | 可选                                                 |
| DATA_ONLY                    | 是否只导入表数据（N）                                        | 可选                                                 |
| INDEX_OPTION                 | 使用快速装载时索引的设置选项（2）。<br />1：不刷新二级索引，数据按照索引先排序，装载完后再将排序的数据插入索引<br />2：不刷新二级索引，数据装载完成重建后所有二级索引；刷新二级索引，数据装载的同时将数据插入二级索引 | 可选                                                 |
| CTRL_INFO                    | 控制信息用来控制一些特殊情况导入（0）。<br />0：不导入表空间定义<br />1：导入表空间定义<br />2：校验导入文件的 MD5 值，并执行导入<br />4：校验导入文件的 MD5 值，但不执行导入<br />8：导入对象时忽略 OR REPLACE 条件 | 可选                                                 |
| CONFIG_FILE                  | 配置文件路径，配置默认连接串和密码信息                       | 可选                                                 |
| FILE_VERSION                 | 用于指定将 dmp 文件降级后生成的新 dmp 文件的逻辑版本，有效范围 9~27 | 可选                                                 |
| HELP                         | 显示帮助信息                                                 | 可选                                                 |

<br>

### 6.4、自动备份和清理

准备工作：创建代理环境。

<br>

**图形操作方式**

<img src="!assets/DM/image-20250924172502361.png" alt="image-20250924172502361" style="zoom:50%;" />

<br>

**命令行操作方式**

创建代理环境：

```sql
SP_INIT_JOB_SYS(1);
```

删除代理环境（谨慎操作）：

```sql
SP_INIT_JOB_SYS(0); 
```

<br>

#### 6.4.1、自动数据备份

> [!CAUTION]
>
> 备份之前需要开启归档，并进行一次联机备份测试，归档配置可见 [联机归档配置](#联机归档配置)。

<br>

**图形操作方式**

1. 新建定时自动备份作业：

   ![image-20250924173031415](!assets/DM/image-20250924173031415.png)

2. 填写作业信息：

   <img src="!assets/DM/image-20250924173504505.png" alt="image-20250924173504505" style="zoom:67%;" />

3. 创建备份的作业步骤：

   <img src="!assets/DM/image-20250924173544861.png" alt="image-20250924173544861" style="zoom:67%;" />

4. 创建作业调度信息：

   <img src="!assets/DM/image-20250924173640511.png" alt="image-20250924173640511" style="zoom:67%;" />

5. 全部创建成功后，保存即可。

<br>

#### 6.4.2、自动清理备份

自动备份如果不及时清理，会占用大量磁盘空间，最好在配置自动备份后也同时配置自动清理：

1. 打开原来自动备份的作业调度。

2. 创建一个新的作业步骤即可：

   <img src="!assets/DM/image-20250924174312003.png" alt="image-20250924174312003" style="zoom:67%;" />

   脚本语句为（末尾的 `-8` 意思是删除 8 天之前的历史备份，具体保存多久的历史备份，根据磁盘剩余空间大小，与项目现场的要求配置）：

   ```sql
   CALL SP_DB_BAKSET_REMOVE_BATCH('DISK', NOW()-8);
   ```

<br>

## 7、升级

### 7.1、数据库升级

如果是小版本升级，直接替换 bin 目录就行。

1. 首先去官网下载最新的安装包。

2. 备份数据库。

3. 停止达梦所有相关服务：

   <img src="!assets/DM/image-20250324204744243.png" alt="image-20250324204744243" style="zoom: 67%;" />

4. 将下载的安装包手动解压，将 source 文件夹里的 bin 文件夹替换安装目录里的 bin 文件夹：

   <img src="!assets/DM/image-20250324210316217.png" alt="image-20250324210316217" style="zoom:80%;" />

5. 重启达梦数据库实例服务和达梦 AP 服务。

<br>

### 7.2、数据库工具升级

1. 首先去官网下载最新的安装包。

2. 备份自己数据库工具的配置文件，存储在自己安装目录里的 tool/tool 文件夹下：

   <img src="!assets/DM/image-20250324210902645.png" alt="image-20250324210902645" style="zoom:80%;" />

3. 将下载的安装包手动解压，将 source 文件夹里的 tool 文件夹替换安装目录里的 tool  文件夹：

   <img src="!assets/DM/image-20250324211021741.png" alt="image-20250324211021741" style="zoom:80%;" />

4. 最后将备份的 tool 文件夹还原到安装目录里的 tool  文件夹里：

   ![image-20250324211153247](!assets/DM/image-20250324211153247.png)

<br>
