---
typora-copy-images-to: upload
---

# 1、简介

Nginx 是 lgor Sysoev 为俄罗斯访问量第二的 rambler.ru 站点设计开发的。从 2004 年发布至今，凭借开源的力量，已经接近成熟与完善。

Nginx 功能丰富，可作为HTTP服务器，也可作为反向代理服务器，邮件服务器。支持 FastCGI、SSL、Virtual Host、URL Rewrite、Gzip 等功能。并且支持很多第三方的模块扩展。

Nginx 的稳定性、功能集、示例配置文件和低系统资源的消耗让他后来居上，在全球活跃的网站中有 12.18% 的使用比率，大约为 2220 万个网站。



# 2、安装

## 2.1、Linux 安装

### 2.1.1、离线安装

**安装**

1. 去 [官网](http://nginx.org/en/download.html) 下载压缩包：

   ![image-20230614234051811](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230614234051811.png)

2. 将下载好的包上传至服务器，解压至 `/usr/local`：

   ```shell
   tar -xzf nginx-1.24.0.tar.gz -C /usr/local
   ```

3. 将解压后的文件夹改名为 `nginx` 并进入：

   ```shell
   # 改名
   mv /usr/local/nginx-1.24.0/ /usr/local/nginx
   
   # 进入
   cd /usr/local/nginx
   ```

4. 执行配置文件：

   ```shell
   ./configure
   ```

   如无问题，则直接进入第 5 步，如像以下一样缺少库，则先安装库再继续。

   ![image-20230615001606389](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230615001606389.png)

   这里显示缺少 PCRE 库，可以从 [官网](http://www.pcre.org/) 下载，然后上传至服务器、解压、执行配置文件、安装：

   ```shell
   tar -xzf pcre2-10.42.tar.gz -C /usr/local
   
   mv /usr/local/pcre2-10.42.tar.gz/ /usr/local/pcre2
   
   cd /usr/local/pcre2
   
   ./configure
   
   make
   
   sudo make install
   ```

   安装完成后再前往 `/usr/local/nginx` 目录执行 `./configure`：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230615003550134.png" alt="image-20230615003550134" style="zoom: 33%;" />

   这里显示缺少 zlib 库，可以从 [官网](http://www.zlib.net) 下载，然后上传至服务器、解压、执行配置文件、安装：

   ```shell
   tar -xzf zlib-1.2.13.tar.gz -C /usr/local
   
   mv /usr/local/zlib-1.2.13.tar.gz/ /usr/local/zlib
   
   cd /usr/local/zlib
   
   ./configure
   
   make
   
   sudo make install
   ```

   之后再前往 `/usr/local/nginx` 目录执行 `./configure`，缺少库就去安装，直到 `./configure` 不显示错误。

   > Nginx 对 OpenSSL 不做硬性要求，在不使用 SSL 模块的情况下没有 OpenSSL 也可以安装通过，如果需要此功能，请先安装 OpenSSL 然后执行以下配置命令：
   >
   > ```shell
   > ./configure --with-http_stub_status_module --with-http_ssl_module --with-openssl=/usr/local/openssl
   > ```

5. 编译并安装：

   ```shell
   make
   
   sudo make install
   ```

6. 运行（默认运行在服务 80 端口）：

   ```shell
   # 进入 sbin/ 目录
   cd sbin/
   
   # 运行
   ./nginx
   ```



**卸载**

1. 停止 Nginx 软件：
   ```shell
   /usr/local/nginx/sbin/nginx -s stop
   ```

   如果不知道 Nginx 的安装路径，可以通过 `ps` 查看 Nginx 的 PID，然后 `kill`  其 PID。

2. 查找根下所有名字包含 Nginx 的文件：

   ```shell
   find / -name nginx
   ```

3. 执行命令 `rm -rf *` 删除 Nignx 安装的相关文件：

   ```shell
   rm -rf /usr/local/sbin/nginx
   ...
   ```



**常用命令**

```shell
cd /usr/local/nginx/sbin/     //进入目录

./nginx                       //启动
./nginx -s stop               //停止
./nginx -s quit               //安全退出
./nginx -s reload             //重载配置文件（修改了配置文件需要执行此命令 比较常用）
ps aux | grep nginx           //查看ngnix进程

cd /usr/local/nginx/conf      //进入配置目录
vim nginx.conf                //编辑配置文件
```



## 2.2、Windows 安装

**安装**

去 [官网](http://nginx.org/en/download.html) 下载压缩包：

![image-20230810003412376](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230810003412376.png)

下载好，找个目录解压缩一下，其中最主要的配置文件 `nginx.conf` 在 conf 文件夹中。



**常用命令**

```shell
cd C:\Nginx					//进入目录

start nginx					//启动nginx，或者直接双击nginx.exe

nginx.exe -s stop			//快速停止nginx
nginx.exe -s quit			//完整有序的停止nginx，并保存相关信息

nginx.exe -s reload			//重新载入nginx配置信息
nginx.exe -s reopen			//重新打开nginx的日志文件
nginx.exe -v				//查看nginx版本信息
```

