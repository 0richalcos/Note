## 	Shell命令

**软件的安装与卸载**

```shell
# 更新软件库的索引
apt update

# 安装软件：tree jdk11 nginx mysql-server mongodb redis
apt install tree

# 升级
apt upgrade

# 自动删除不需要的包
apt autoremove

# 搜索
apt search xxx

# 查看
apt info xxx

# 卸载软件
apt remove xxx
```



## 管理命令

**service：管理服务状态**

```shell
# 显示状态
service nginx status

# 关闭服务
service nginx stop

# 启动服务
service nginx start 

# 重启服务
service nginx restart

# 重新加载配置
service nginx reload
```

| 命令      | 描述             | 参数 |
| --------- | ---------------- | ---- |
| top       | 任务管理器，动态 |      |
| ps        | 进程快照，静态   | aux  |
| netstat   | 网络状态         | tap  |
| service   | 服务管理         |      |
| systemctl | 服务管理         |      |

> `service` 和 `systemctl` 的区别：
>
> service nginx stop = systemctl stop nginx

 

**基本 Linux 命令**

| 命令   | 描述                                 | 参数                                      |
| ------ | ------------------------------------ | ----------------------------------------- |
| pwd    | 打印当前位置                         |                                           |
| ls     | 列表显示目录内容                     | `l`(详细)、`a`(所有)、`h`(文件大小格式化) |
| cd     | 切换目录                             | `.`(当前)、`..`(上一级)、`~`(home)        |
| mkdir  | 新建文件夹                           |                                           |
| touch  | 新建文件、修改时间戳                 |                                           |
| rm     | 删除文件、非空文件夹                 | `r`(递归)                                 |
| rmdir  | 删除空文件夹                         |                                           |
| mv     | 移动或重命名                         |                                           |
| cp     | 拷贝                                 | `r`(递归)                                 |
| tree   | 树形方式显示目录结构（需要安装tree） | `a`(所有内容)、`d`(目录)、`L`(深度)       |
| man    | 手册                                 | `XXX`（想知道的命令，比如`ls`）           |
| clear  | 清除页面内容                         |                                           |
| uname  | 查看系统                             | `a`(查看详细信息)                         |
| reboot | 重新启动系统                         |                                           |
| nano   | 开启 nano 编辑器                     | `XXX`(目标文件名)                         |



## 配置 nginx

1. 编辑配置文件：`/etc/nginx/nginx.conf`

	- nano
	- vi
	- vs code `Remote`(扩展)
	- 下载、编辑、上传 [scp]

2. 在 `http{}` 中增加以下内容，其中 `[ip]` 为 ip 地址或域名：

	```shell
	server{
		listen 80;
		server_name [ip];
		root /opt/www;
		index index.html;
	}
	```

3. 在`/opt/www`中写入`index.html`等资源

4. 重启服务 / 重新加载服务

	```shell
	service nginx restart
	service nginx reload
	```

5. 发布（上传）资料（程序）

	```shell
	# 安全复制，上传、下载
	# scp [-r] [源] [目标]
	
	# 上传
	scp c:/path/index.html root@47.106.190.209:/opt/www
	
	# 下载
	scp root@47.106.190.209:/etc/nginx/nginx.conf d:/
	```



## 安装 MySQL

从官方源安装 MySQL 数据库

1. 下载`wget`官方软件资料库

	```shell
	wget https://dev.mysql.com/get/mysql-apt-config_0.8.15-1_all.deb
	```

2. 安装资料库`mysql-apt-config_0.8.15-1_all.deb`

	```shell
	dpkg -i mysql-apt-config_0.8.15-1_all.deb
	```

	> `dpkg`中的`d`对应的是文件后缀名`deb`

3. 更新软件仓库的索引

	```shell
	apt update
	apt upgrade
	```

4. 安装数据库

	```shell
	apt install mysql-server
	```

5. 查看服务器状态

	```shell
	# netstat \ ss
	netstat -tap | grep mysql
	ss -tap | grep mysql
	
	# ps
	ps aux | grep mysql
	
	# service
	service mysql status
	
	# systemctl
	systemctl status mysql
	```

6. 本地连接

	```shell
	mysql -u root -p
	
	# \s 查看数据库连接状态
	```

7. 远程连接

	```shell
	mysql -u root -p
	
	use mysql
	
	select host,user from user;
	
	# 允许 root 用户从远程连接数据库服务器
	update user set host='%' where user='root';
	
	exit
	service mysql restart
	
	# 使用你的（图形）客户端（使用 root）远程连接
	# 创建用户
	
	# 关闭 root 用户的远程连接
	update user set host='localhost' where user='root';
	exit
	serivce mysql restart
	
	# 使用 mysql 命令行客户端远程连接
	mysql -h ip -u test -p
	```



## 安装 Redis

从源码编译、安装程序 `make`

```shell
# 下载源码 
$ wget http://download.redis.io/releases/redis-6.0.5.tar.gz

# 解压
$ tar xzf redis-6.0.5.tar.gz

#
进入目录
$ cd redis-6.0.5

# C/C++ 构建，类似 mvn install，npm build
$ make

# 进入源码目录
cd src

# 启动服务器程序
./redis-server &

# 启动客户端 
./redis-cli
```



## 用户管理

`/etc/passwd`用户信息

`/etc/shadow`	密码（影子文件）

| 命令   | 描述             | 参数          |
| ------ | ---------------- | ------------- |
| su     | 切换用户         | `XXX`(用户名) |
| whoami | 我是谁？         |               |
| sudo   | 以管理员权限运行 |               |
| passwd | 修改密码         |               |



> **查看文件内容**
>
> `cat`
> `echo`
> `less`	文件内容（向下）	less is more
> `more`	文件内容（向下）
> `head -n 3`	前 3 行
> `tail -n 3`	倒数 3 行



```shell
# 创建、删除用户
adduser bob
deluser bob

# 创建组，创建用户在指定的组
addgroup j1102
adduser --gid 1000 bob
```



`rwx`	`r--`	`r--`

`111`	`101`	`101`

| 数字 | 意义       |
| ---- | ---------- |
| 000  | --- （0）  |
| 001  | --x（1）   |
| 010  | -w-（2）   |
| 011  | -wx（3）   |
| 100  | r--  （4） |
| 101  | r-x （5）  |
| 110  | rw-（6）   |
| 111  | rwx（7）   |



## Docker

应用程序容器（打包、标准化、软件）引擎

### Docker安装

方案一：从软件的系统库

```shell
apt install docker.io
```

方案二：从官方包

```shell
# containerd.io_1.2.13-2_amd64.deb
# docker-ce_19.03.12~3-0~ubuntu-focal_amd64.deb
# docker-ce-cli_19.03.12~3-0~ubuntu-focal_amd64.deb

dpkg -i *.deb

# 检查服务状态
docker info

service docker status
```



**运行第一个容器**

```shell
docker run hello-world
```



### 镜像：容器的模板

```shell
# 显示镜像
docker images

# 搜索镜像
docker search nginx

# 下载镜像
docker pull nginx

# 删除镜像
docker rmi nginx
```

> 如果使用镜像创建了一个容器，那么必须删掉容器才可以删除镜像：`docker rm nginx`



### 镜像加速器

在阿里云控制台搜索 ”镜像加速器“

编辑`/etc/docker/daemon.json`加入以下内容：

```json
{
  "registry-mirrors": ["https://lk6269kx.mirror.aliyuncs.com"]
}
```

重启服务

```shell
systemctl restart docker
```



### 容器

可以基于一个镜像启动多个容器（独立程序）

```shell
# 基于 ubuntu 镜像创建一个 u1，i 交互式 t 终端 d daemon(后台)
docker run --name u1 -itd ubuntu

# 查看状态
docker ps

# 关闭
docker stop u1 / id

# 启动
docker start u1 / id

# 连接到服务器 u1
docker exec -it u1 bash
```

```shell
# 创建容器时，指定端口映射
docker run --name web -itd -p 80:80 ubuntu

docker exec -it web bash

apt update

apt install nginx 

service nginx status

service nginx start

service nginx status
```



**在宿主和容器之间直接复制文件**

```shell
# 上传
docker cp /opt/test/file.txt container:/opt/testnew/
```



**容器导入、导出**

```shell
# 导出
docker export ng > ng.tar

# 导入，nweb 是一个镜像
docker import ng.tar nweb

# 查看容器的 command（启动的被导入的容器需要）
docker ps -a --no-trunc

# 基于镜像启动容器
docker run -itd --name n1 -p 80:80 nweb -g 'command'

docker exec -it nweb bash
```

'/app/ds/run-document-server.sh'

### 容器之间的通信

- u1 MySQL
- u2 Redis
- u3 MongoDB
- u4 App(Java / Pathon)



**创建网络**

创建容器时，指定容器所在的网络，多个容器直接相互通信。

```shell
# 创建网络 mynet
docker network create mynet

# 创建容器时指定所在网络
docker run --name u3 --network mynet -itd ubuntu
docker run --name u4 --network mynet -itd ubuntu

docker exec -it u4 bash

apt install iputils-ping

# 查看网络是否连通
ping u3
```



