---
typora-copy-images-to: upload
---

# 1、简介

达梦数据库管理系统是达梦公司推出的具有完全自主知识产权的高性能数据库管理系统，简称 DM。达梦数据库管理系统的最新版本是 8.0 版本，简称 DM8。
DM8 采用全新的体系架构，在保证大型通用的基础上，针对可靠性、高性能、海量数据处理和安全性做了大量的研发和改进工作，极大提升了达梦数据库产品的性能、可靠性、可扩展性，能同时兼顾 OLTP 和 OLAP 请求，从根本上提升了 DM8 产品的品质。



# 2、安装

## 2.1、Linux 安装

### 2.1.1、安装前准备

用户在安装 DM 数据库之前需要检查或修改操作系统的配置，以保证 DM 数据库能够正确安装和运行。

本文以演示环境如下：

| 操作系统 | CPU         | 数据库                  |
| :------- | :---------- | :---------------------- |
| CentOS7  | x86_64 架构 | dm8_rh7_64_ent_8.1.1.87 |



**新建 dmdba 用户**

> 安装前必须创建 dmdba 用户，禁止使用 root 用户安装数据库。

1. 创建用户所在的组，命令如下：

   ```shell
   groupadd dinstall
   ```

2. 创建用户，命令如下：

   ```shell
   useradd -g dinstall -m -d /home/dmdba -s /bin/bash dmdba
   ```

3. 修改用户密码，命令如下：

   ```shell
   passwd dmdba
   ```



**修改文件打开最大数**

- 重启服务器后永久生效

  使用 vi 编辑器打开 `/etc/security/limits.conf` 文件，命令如下：

  ```shell
  vi /etc/security/limits.conf
  ```

  在最后添加四条语句，需添加的语句如下：

  ```
  dmdba hard nofile 65536
  dmdba soft nofile 65536
  dmdba hard stack 32768
  dmdba soft stack 16384
  ```

  切换到 dmdba 用户，查看是否生效，命令如下：

  ```shell
  su - dmdba
  
  ulimit -a
  ```

  ![查看设置是否生效](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/check-openfiles.png)

- 设置参数临时生效

  可使用 dmdba 用户执行如下命令，使设置临时生效：

  ```shell
  ulimit -n 65536
  ```



**挂载镜像**

切换到 root 用户，将 DM 数据库的 iso 安装包保存在任意位置，例如 `/opt` 目录下，执行如下命令挂载镜像：

```shel
mount -o loop /opt/dm8_setup_rh7_64_ent_8.1.1.45_20191121.iso /mnt
```



**新建安装目录**

在根目录下创建 `/dm8` 文件夹，用来安装 DM 数据库。命令如下：

```shell
mkdir /dm8
```

> 使用 root 用户建立文件夹，待 dmdba 用户建立完成后需将文件所有者更改为 dmdba 用户，否则无法安装到该目录下



**修改安装目录权限**

将新建的安装路径目录权限的用户修改为 dmdba，用户组修改为 dinstall。命令如下：

```shel
chown dmdba:dinstall -R /dm8/
```

给安装路径下的文件设置 755 权限。命令如下：

```shell
chmod -R 755 /dm8
```



### 2.1.2、数据库安装

DM 数据库在 Linux 环境下支持命令行安装和图形化安装。



**命令行安装**

切换至 dmdba 用户下，在 `/mnt` 目录下使用命令行安装数据库程序，依次执行以下命令安装 DM 数据库：

```shell
su - dmdba

cd /mnt/

./DMInstall.bin -i
```

按需求选择安装语言，默认为中文。本地安装选择【不输入 Key 文件】，选择【默认时区 21】。

![选项1](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/choose-lang-time.png)

选择【1-典型安装】，按已规划的安装目录 `/dm8` 完成数据库软件安装，不建议使用默认安装目录。

![选项2](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/choose-type-path.png)

数据库安装大概 1~2 分钟，数据库安装完成后，显示如下界面。

![安装完成](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/install-success.png)

数据库安装完成后，需要切换至 root 用户执行上图中的命令 `/dm8/script/root/root_installer.sh` 创建 DmAPService，否则会影响数据库备份。



**图形化安装**

切换到 dmdba 用户，进入 `/mnt` 目录下，执行命令开始图形化安装：

```shell
su - dmdba

cd /mnt/

./DMInstall.bin
```

若出现：`初始化图形界面失败，如果当前监视器窗口不支持图形界面，请进入安装文件所在文件夹并使用"./DMInstall.bin -i"进行命令行安装。` 错误提示，可按以下两种方式操作解决：

- 方法一：注销当前用户，登陆 dmdba 用户，执行 `./DMInstall.bin` 命令。
- 方法二：用当前用户执行 `xhost +`，切换到 dmdba 用户，执行 `export DISPLAY=:0`，再执行 `xhost +` 命令。

图形化界面启动成功后，将弹出【选择语言与时区】页面，默认为简体中文和中国标准时间。

![选择语言与时区](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-lang-time.png)

点击【确定】后，弹出 DM 数据库安装程序。

![安装向导](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-xd.png)

点击【下一步】后，为许可证协议页面，选择【接受】。

![许可证协议](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-xkxy.png)

点击【下一步】后，弹出 key 文件页面，点击【浏览】选择【key 文件】，若没有 key 文件可以直接点击【下一步】，跳过该步骤。

![key文件](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-key.png)

点击【浏览】后，找到相应的 key 文件，点击【确定】即可。

点击【下一步】后，弹出选择组件页面，建议选择典型安装，也可根据需要，选择服务器安装、客户端安装和自定义安装。

![选择组件](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-chosezj.png)

点击【下一步】后，弹出选择安装位置页面，可点击【浏览】选择安装位置，也可安装在默认路径下。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-path.png" alt="选择安装位置" style="zoom: 50%;" />

点击【下一步】后，弹出确认安装信息页面，检查安装信息是否准确，确认无误后点击【安装】。

![确认安装信息](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-message.png)

点击【安装】后，等待 1~2 分钟即可安装完成，安装完成后弹出执行配置脚本页面，按照页面要求执行该脚本即可。

![执行配置脚本](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-runshell.png)

重新打开一个终端，切换到 root 用户，执行弹出页面中的脚本。

![执行脚本](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-zxjb.png)

脚本执行完成后，点击执行配置脚本页面中的【完成】，弹出提示框，提示是否关闭窗口，选择是，提示数据库安装完成，再点击【完成】按钮，完成数据库安装。

![完成安装](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-install-success.png)



**配置环境变量**

切换到 root 用户进入 dmdba 用户的根目录下，配置对应的环境变量。DM_HOME 变量和动态链接库文件的加载路径在程序安装成功后会自动导入。命令如下：

```shell
export PATH=$PATH:$DM_HOME/bin:$DM_HOME/tool
```

编辑 `.bash_profile`，使其最终效果如下图所示：

```shell
cd /home/dmdba/

vim .bash_profile
```

![环境变量](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/dm-home-path.png)

切换至 dmdba 用户下，执行以下命令，使环境变量生效：

```shell
su - dmdba

source .bash_profile
```



### 2.1.3、配置实例

DM 数据库在 Linux 环境支持命令行配置实例以及图形化配置实例。



**命令行配置实例**

使用 dmdba 用户配置实例，进入到 DM 数据库安装目录下的 bin 目录中，使用 `dminit` 命令初始化实例。

`dminit` 命令可设置多种参数，可执行如下命令查看可配置参数：

```help
./dminit help
```

![dminit 帮助](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ml-licence-dminithelp.png)

需要注意的是页大小 (*page_size*)、簇大小 (*extent_size*)、大小写敏感 (*case_sensitive*)、字符集 (*charset*) 这四个参数，一旦确定无法修改，需谨慎设置。

- *extent_size*：指数据文件使用的簇大小，即每次分配新的段空间时连续的页数。只能是 16 页或 32 页或 64 页之一，缺省使用 16 页。
- *page_size*：数据文件使用的页大小，可以为 4 KB、8 KB、16 KB 或 32 KB 之一，选择的页大小越大，则 DM 支持的元组长度也越大，但同时空间利用率可能下降，缺省使用 8 KB。
- *case_sensitive*：标识符大小写敏感，默认值为 Y 。当大小写敏感时，小写的标识符应用双引号括起，否则被转换为大写；当大小写不敏感时，系统不自动转换标识符的大小写，在标识符比较时也不区分大小写，只能是 Y、y、N、n、1、0 之一。
- *charset*：字符集选项。0 代表 GB18030；1 代表 UTF-8；2 代表韩文字符集 EUC-KR；取值 0、1 或 2 之一。默认值为 0。

可以使用默认参数初始化实例，需要附加实例存放路径。此处以初始化实例到 `/dm/data` 目录下为例（执行初始化命令前，需要使用 root 用户授予 `/dm/data` 目录相应权限，可以参考修改目录权限），初始化命令如下：

```shell
./dminit path=/dm/data
```

![dminit 默认参数](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ml-licence-mrcs.png)

也可以自定义初始化实例的参数，参考如下示例：

以下命令设置页大小为 32 KB，簇大小为 32 KB，大小写敏感，字符集为 utf_8，数据库名为 DMDB，实例名为 DBSERVER，端口为 5237。

```shell
./dminit path=/dm/data PAGE_SIZE=32 EXTENT_SIZE=32 CASE_SENSITIVE=y
CHARSET=1 DB_NAME=DMDB INSTANCE_NAME=DBSERVER PORT_NUM=5237
```

![dminit 设置参数](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ml-licence-szcs.png)

> 如果此处自定义了初始化参数，在后面的注册服务和启动数据库等步骤中，请按实际的自定义参数进行操作。



**图形化配置实例**

使用图形化界面安装数据库安装完成后，会弹出选择是否初始化数据库页面，选择【初始化】。

![初始化实例](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-csh.png)

点击初始化后会弹出数据库配置助手，通过数据库配置助手便可以配置数据库。

![初始化实例](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-ipzzs.png)

若需要主动打开配置助手，可使用 dmdba 用户配置实例，进入到 DM 数据库安装目录下的 tool 目录中，使用 `./dbca.sh` 命令打开数据库配置助手。

![启动配置助手](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-stdbca.png)

选择创建数据库实例，点击【开始】，进入创建数据库页面的创建数据库模版页签，选择【一般用途】，如下图所示：

![创建数据库模版](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-instance-cremod.png)

点击【下一步】，选择数据库实例安装目录，如下图所示：

![指定数据库目录](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-instance-cpath.png)

确定好数据库安装目录后，点击【下一步】，用户可根据需要设置对应的数据库参数，如下图所示：

![指定数据库目录](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-instance-settag.png)

点击【下一步】，配置数据库文件路径，选择【默认路径】即可，如下图所示：

![指定数据库文件目录](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-datafilepath.png)

点击【下一步】，配置初始化参数，注意簇大小、页大小、字符集以及大小写敏感确定后不可修改，默认配置即可，如下图所示：

![指定数据库文件目录](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-setcs.png)

点击【下一步】，配置数据库口令，默认配置即可，如下图所示：

![口令管理](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-pswm.png)

点击【下一步】，配置示例库，建议勾选  `BOOKSHOP` 或 `DMHR`，作为演示环境，如下图所示：

![创建示例库](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-exdatabase.png)

点击【下一步】，用户可检查创建参数，若有需要修改之处可点击【上一步】回到需要修改的位置进行修改，如下图所示：

![创建摘要](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-crezy.png)

点击【完成】，创建完成数据库实例后，按下图按提示执行脚本即可完成实例配置：

![创建完成](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-licence-cresuee.png)

![执行命令](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/zxml.png)





### 2.1.4、注册服务

**命令行注册服务**

注册服务需使用 root 用户进行注册。使用 root 用户进入数据库安装目录的 `/script/root` 下，如下所示：

```shell
cd /dm8/script/root
```

注册服务，如下所示：

```shell
./dm_service_installer.sh -t dmserver -dm_ini /dm8/data/DAMENG/dm.ini -p DMSERVER
```

用户可根据自己的环境更改 dm.ini 文件的路径以及服务名，如下所示：

```shell
./dm_service_installer.sh -h
```

如需为其他实例注册服务，需打开 dbca 工具，进行注册服务，如下所示：

```shell
cd /dm8/tool

./dbca.sh
```



**图形化注册服务**

打开运行 dbca 工具，选择【注册数据库服务】，如下图所示：

![打开dbca](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-service-dbca.png)

单击【开始】，弹出注册数据库服务页面，如下图所示：

![注册参数](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-service-zccs.png)

点击【完成】后，弹出执行配置脚本页面，按页面要求执行脚本即可，如下图所示：

![执行配置脚本](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-service-shell.png)

执行脚本成功后，该实例已启动，如下图所示：

![终端](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-service-zd.png)



### 2.1.5、启动、停止数据库

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



**图形化启停数据库**

进入 DM 安装目录下的 `tool` 目录，使用如下命令打开 DM 服务查看器，如下所示：

```shell
./dmservice.sh
```

![安装完成](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-service-fwck.png)

![安装完成](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/ui-service-czfw.png)



## 2.2、数据库目录结构

**数据库安装目录**

下图展示为 DM8 数据库目录。

![目录](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/dm-dictionary.png)

- `/dm8/bin` 目录存放 DM 数据库的可执行文件，例如 `disql` 命令、`dminit` 命令、`dmrman` 工具等。
- `/dm8/desktop` 存放 DM 数据库各个工具的桌面图标。
- `/dm8/doc` 存放 DM 数据库用户手册。
- `/dm8/drivers` 存放连接 DM 数据库的驱动文件。
- `/dm8/log` 存放 DM 数据库日志，包括工具的日志、数据库日志、服务日志等。
- `/dm8/samples` 存放 DM 数据库各类配置文件的示例文件。
- `/dm8/script` 存放注册、注销 DM 数据库服务的工具，例如 `dm_service_installer.sh` 等。
- `/dm8/tool` 存放 DM 数据库的各个工具，例如 manager 管理工具、dbca 数据库配置助手等。
- `/dm8/uninstall` 目录存放卸载 DM 数据库的脚本。
- `/dm8/web` 目录存放 DM 数据库 dem 工具的 web 环境。



**数据库实例目录**

`/dm8/data` 为数据库实例目录，该目录存放各个实例的文件。

![目录](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/dictionary-instance.png)

以实例 DAMENG 为例，该目录下存放 DAMENG 实例的配置文件（`*.ini`）、控制文件（`dm.ctl`）、数据文件（`*.DBF`）、日志文件（`*.log`） 等。

![目录](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/dictionary-instance-DAMENG1.png)



# 3、备份和迁移

## 3.1、DTS 工具迁移

1. 新建工程：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/20210202164357901.png)

2. 新建迁移：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/20210202164528157.png)

3. 双击打开 dm-dm 进行迁移配置：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/20210202164622570.png)

4. 选择相应的迁移模式（此处选【dm ==> dm】迁移）：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/20210202164743774.png)

5. 填写相应的源数据库和目标数据库的连接信息：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/2021020216493840.png)

6. 选择要迁移的源模式，并指定目的模式，其中 "目录"、"公共同义词"、"上下文" 取消勾选：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/20210202165336811.png)

7. 勾选相应的对象，点击【转换】选择迁移策略，可以根据实际情况选择只迁移表结构、数据、约束，或删除原有数据再迁移等具体情况，大字段表选项改小 256 即可

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/20210202165939365.png)

8. 确认无误后完成即可：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/20210202170509591.png)



## 3.2、物理备份

1. 右键数据库连接，选择【管理服务器】，切换到【系统管理】，将状态转换为【配置】：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240812180155686.png" alt="image-20240812180155686" style="zoom: 67%;" />

2. 切换到【归档配置】，将归档模式设置为【归档】，并添加一个本地的归档目标：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240812180342701.png" alt="image-20240812180342701" style="zoom:67%;" />

3. 点击确定后，再打开【管理服务器】回到【系统管理】，将状态切换为【打开】：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240812180502625.png" alt="image-20240812180502625" style="zoom:67%;" />

4. 最后在连接里找到【备份】，右键【库备份】选择【新增备份】，点击确定就好：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20240812180633492.png" alt="image-20240812180633492" style="zoom:67%;" />

5. 备份文件在：`<数据目录>/bak/` 文件夹里。



# 4、数据库状态和模式

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



## 4.1、状态切换

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



**图形化界面配置**

1. 打开 DM管理工具 => 连接数据库 => 右键选择【管理服务器】：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230922004306995.png" alt="image-20230922004306995" style="zoom: 67%;" />

2. 切换到【系统管理】页面，选择需要切换的状态，最后点击【转换】：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230922004422477.png" alt="image-20230922004422477" style="zoom:67%;" />

   左侧目录刷新，即可看到数据库对象信息。