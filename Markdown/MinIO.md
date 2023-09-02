---
typora-copy-images-to: upload
---

# 1、简介

## 1.1、MinIO 是什么？

官方解释：MinIO 是一个基于 Apache License v2.0 开源协议的对象存储服务。它兼容亚马逊 S3 云存储服务接口，非常适合于存储大容量非结构化的数据，例如图片、视频、日志文件、备份数据和容器/虚拟机镜像等，而一个对象文件可以是任意大小，从几 kb 到最大 5T 不等。

MinIO 是一个非常轻量的服务，可以很简单的和其他应用的结合，类似 NodeJS、Redis 或者 MySQL。



## 1.2、MinIO 有哪些优势？

**开发文档全面**

MinIO 作为一款基于 Golang 编程语言开发的一款高性能的分布式式存储方案的开源项目，有十分完善的官方文档。

官网文档地址：https://docs.min.io/cn/



**高性能**

MinIO 号称是目前速度最快的对象存储服务器。在标准硬件上，对象存储的读/写速度最高可以高达183 GB/s 和 171 GB/s。对象存储可以作为主存储层，用来处理 Spark、Presto、TensorFlow、H2O.ai 等各种复杂工作负载以及成为 Hadoop HDFS 的替代品。

MinIO 用作云原生应用程序的主要存储，和传统对象存储相比，云原生应用程序需要更高的吞吐量和更低的延迟。而这些都是 MinIO 能够达成的性能指标。

![aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy82T3hxU3FXQnFzSWhZYTFReDR5WUozbklOdEZkVVI1T2xpYUJLeGhHRzRsZDBVaWJFZVl4aDJKOTVJU1JJYnM1T0d4Y1NzdllORTYwNW9xN3BqMHN2bGlidy82NDA](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy82T3hxU3FXQnFzSWhZYTFReDR5WUozbklOdEZkVVI1T2xpYUJLeGhHRzRsZDBVaWJFZVl4aDJKOTVJU1JJYnM1T0d4Y1NzdllORTYwNW9xN3BqMHN2bGlidy82NDA.png)



**支持全面**

目前 MinIO 支持市面主流的开发语言并且可以通过 SDK 快速集成快速集成使用。

![image-20230613214138567](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230613214138567.png)



**AWS S3标准兼容**

亚马逊云的 S3 API（接口协议） 是在全球范围内达到共识的对象存储的协议，是全世界内大家都认可的标准。MinIO 在很早的时候就采用了 S3 兼容协议，并且 MinIO 是第一个支持 S3 Select 的产品。MinIO 对其兼容性的全面性感到自豪， 并且得到了 750 多个组织的认同，包括 Microsoft Azure 使用 MinIO 的 S3 网关 - 这一指标超过其他同类产品的总和。

![aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy82T3hxU3FXQnFzSWhZYTFReDR5WUozbklOdEZkVVI1T0k2N0t6aWI3clVlbTA4VzFWOGdMckN3VjdHSXY2bW42NjhtVmd5cTlGWFdDaWM2T0RLUWVzSU53LzY0MA](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy82T3hxU3FXQnFzSWhZYTFReDR5WUozbklOdEZkVVI1T0k2N0t6aWI3clVlbTA4VzFWOGdMckN3VjdHSXY2bW42NjhtVmd5cTlGWFdDaWM2T0RLUWVzSU53LzY0MA.png)



**安装部署非常简单**

MinIO 安装部署非常简单。MinIO 简单特性减少了出错的机会，节约了安装部署的时间，提供了可靠性，同时简单性又是性能的基础。Linux 环境下只需下载一个二进制文件然后执行，即可在几分钟内完成安装和配置 MinIO。配置选项和变体的数量保持在最低限度，这样让失败的配置概率降低到几乎接近于 0 的水平。MinIO 升级是通过一个简单命令完成的，这个命令可以无中断的完成 MinIO 的升级工作，并且不需要停机即可完成升级操作，大大降低总使用和运维成本。



**开放全部源代码 + 企业级支持**

MinIO 基于 Apache V2 license 100% 开放源代码 。这就意味着 MinIO 的用户能够自动的、无限制、自由免费使用和集成 MinIO、自由的创新和创造、 自由的去修改、自由的再次发行新的版本和软件。确实，MinIO 强有力的支持和驱动了很多世界 500 强的企业。此外，其部署的多样性和专业性提供了其他软件无法比拟的优势。



**容器化集成方便**

MinIO 提供了与 k8s、etcd、Docker 等主流容器化技术深度集成方案。



**管理界面的支持**

MinIO 服务安装后，可以直接通过浏览器登录系统，完成文件夹、文件的管理。非常方便使用。



## 1.3、MinIO 安装

**MinIO 安装**

1. 进入 `/opt` 目录，创建 `minio` 文件夹并进入：

   ```shell 
   cd /opt
   mkdir minio
   cd minio
   ```

2. 下载安装包：

   这里需要根据自己系统的 Architecture 去下载对应的版本，可以通过 `hostnamectl` 命令查看 Architecture 。

   ![image-20230613214942697](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230613214942697.png)

   [点击进入下载地址](https://min.io/download#/linux) 或者直接通过 wget 下载：

   ```shell
   wget https://dl.minio.org.cn/server/minio/release/linux-amd64/minio
   ```

3. 创建 `data` 文件夹用于存放静态文件：

   ```shell
   mkdir data
   ```

4. 赋予 `minio` 文件执行权限，最高权限：

   ```shell
   chmod 777 minio
   ```

5. 启动 MinIO：

   ```shell
   MINIO_ROOT_USER=admin MINIO_ROOT_PASSWORD=password ./minio server /opt/minio/data --console-address ":9001"
   ```

   - *MINIO_ROOT_USER*：设置用户名；
   - *MINIO_ROOT_PASSWORD*：设置密码；
   - */opt/minio/data*：存放静态文件的目录；
   - *--console-address*：设置 console 的端口（设置的话每次启动该端口都会变动）；
   - *--address*：设置 API 端口，该端口重新启动是不变的，但是可以通过 `--address ":9000"` 手动改变。

   上面的启动方式，当我们关闭 shell 连接时，MinIO 也就关闭了，可以通过 `nohup` 命令进行后台启动。

   由于 `nohup` 命令后无法使用 `MINIO_ROOT_USER/MINIO_ROOT_PASSWORD` 参数设置 root 用户名和密码，所以需要提前在环境变量设置（如果没有自定义密码的需求可以跳过这一步，默认用户名和密码都是 `minioadmin`）：

   ```shell
   vim ~/.profile
   
   # 在最后一行加上
   export MINIO_ACCESS_KEY=minioxx
   export MINIO_SECRET_KEY=minioxxx
   
   source ~/.profile
   ```

   后台启动 MinIO：

   ```shell
   nohup ./minio server /opt/minio/data --console-address ":9001" --address ":9000" &
   ```



**关闭 MinIO**

查看端口占用，9000 为 MinIO 占用端口号，`kill` 杀死进程。

```shell
netstat -nlp | grep 9000
```

![image-20230613221015197](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230613221015197.png)

```shell
kill -9 2524
```

