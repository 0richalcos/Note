---
typora-copy-images-to: upload
---

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
   mv /usr/local/nginx-1.24.0 /usr/local/nginx
   
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
   
   mv /usr/local/pcre2-10.42.tar.gz /usr/local/pcre2
   
   cd /usr/local/pcre2
   
   ./configure
   
   make && make install
   ```

   安装完成后再前往 `/usr/local/nginx` 目录执行 `./configure`：

   ![image-20230615003550134](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230615003550134.png)

   这里显示缺少 zlib 库，可以从 [官网](http://www.zlib.net) 下载，然后上传至服务器、解压、执行配置文件、安装：

   ```shell
   tar -xzf zlib-1.2.13.tar.gz -C /usr/local
   
   mv /usr/local/zlib-1.2.13.tar.gz /usr/local/zlib
   
   cd /usr/local/zlib
   
   ./configure
   
   make && make install
   ```

   之后再前往 `/usr/local/nginx` 目录执行 `./configure`，缺少库就去安装，直到 `./configure` 不显示错误。

5. 编译并安装：

   ```shell
   make && make install
   ```

6. 运行（默认运行在服务 80 端口）：

   ```shell
   # 进入 sbin/ 目录
   cd sbin/
   
   # 运行
   ./nginx
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
