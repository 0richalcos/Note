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

切换至 dmdba 用户下，在 `/mnt` 目录下使用命令行安装数据库程序，依次执行以下命令安装 DM 数据库。

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

切换到 dmdba 用户，进入 `/mnt` 目录下，执行命令开始图形化安装。

若出现：`初始化图形界面失败，如果当前监视器窗口不支持图形界面，请进入安装文件所在文件夹并使用"./DMInstall.bin -i"进行命令行安装。` 错误提示，可按以下两种方式操作解决：

- 方法一：注销当前用户，登陆 dmdba 用户，执行 ./DMInstall.bin 命令。
- 方法二：用当前用户执行 `xhost +`，切换到 dmdba 用户，执行 `export DISPLAY=:0`，再执行 `xhost +`命令。