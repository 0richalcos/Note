---
typora-copy-images-to: upload
---

# 1、Node.js

Node.js 是一个基于 Chrome V8 引擎的 JavaScript 运行环境。Node.js 使用了一个事件驱动、非阻塞式 I/O 的模型，使其轻量又高效。Node.js 的包管理器 npm，是全球最大的开源库生态系统。



npm 是随同 Node.js 一起安装的包管理工具，能解决 Node.js 代码部署上的很多问题，常见的使用场景有以下几种：

- 允许用户从 npm 服务器下载别人编写的第三方包到本地使用。
- 允许用户从 npm 服务器下载并安装别人编写的命令行程序到本地使用。
- 允许用户将自己编写的包或命令行程序上传到 npm 服务器供别人使用。



# 2、搭建 Node 环境

## 2.1、安装 Node.js

1. 打开官网[下载链接](https://nodejs.org/en/download/): 

   ![image-20221118103127016](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221118103127016.png)

2. 下载完成后直接安装，可以不用修改，直接默认选项安装，如有需要可以修改安装目录。

3. 安装完成后打开命令行查看版本：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221118103806893.png" alt="image-20221118103806893" style="zoom:50%;" />

   `node -v` 显示 Node.js 的版本说明已安装成功，`npm -v` 显示 npm 的版本说明自带的 npm 也已经安装成功。

4. 安装完成后的目录如下所示：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221118104255998.png" alt="image-20221118104255998" style="zoom: 50%;" />



## 2.2、环境配置

这里的环境配置主要配置的是 npm 安装的全局模块所在的路径，以及缓存 cache 的路径，之所以要配置，是因为以后在执行类似：`npm install express [-g]` （后面的可选参数 `-g`，g 代表 global 全局安装的意思）的安装语句时，会将安装的模块安装到【C:\Users\用户名\AppData\Roaming\npm】路径中，占 C 盘空间。

例如：我希望将全模块所在路径和缓存路径放在我 Node.js 安装的文件夹中。

1. 在我安装的文件夹【D:\Develop\nodejsC:\Program Files\nodejs】下创建两个文件夹【node_global】及【node_cache】：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221118104639599.png" alt="image-20221118104639599" style="zoom: 50%;" />

2. 创建完两个空文件夹之后，打开命令行窗口，输入：

   ```shell
   npm config set prefix "C:\Program Files\nodejs\node_global"
   npm config set cache "C:\Program Files\nodejs\node_cache"
   ```

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221118105137769.png" alt="image-20221118105137769" style="zoom:67%;" />

3. 接下来设置环境变量：

   进入环境变量对话框，新建系统变量，变量名：`NODE_HOME`，变量值：`C:\Program Files\nodejs`：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221202142134655.png" alt="image-20221202142134655" style="zoom:50%;" />

   将用户变量下的 Path 的 `C:\Users\Orichalcos\AppData\Roaming\npm` 删除，在系统变量下的 Path 下新建 `%NODE_HOME%\node_global\node_modules`：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221202142647572.png" alt="image-20221202142647572" style="zoom:50%;" />

4. 配置完后，安装个 module 测试下，我们就安装最常用的 express 模块。

   在命令行输入如下命令进行模块的全局安装：

   ```shell
   npm install express -g
   ```

