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

如果有多 Node.js 版本需求，请直接前往 [2.2、版本管理器 NVM](#2.2、版本管理器 NVM)

1. 打开官网[下载链接](https://nodejs.org/en/download/): 

   ![image-20221118103127016](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221118103127016.png)

2. 下载完成后直接安装，可以不用修改，直接默认选项安装，如有需要可以修改安装目录。

3. 安装完成后打开命令行查看版本：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221118103806893.png" alt="image-20221118103806893" style="zoom:50%;" />

   `node -v` 显示 Node.js 的版本说明已安装成功，`npm -v` 显示 npm 的版本说明自带的 npm 也已经安装成功。

4. 安装完成后的目录如下所示：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20221118104255998.png" alt="image-20221118104255998" style="zoom: 50%;" />



## 2.2、版本管理器 NVM

**什么是 NVM？**

顾名思义，NVM 是一种用于管理设备上的 Node 版本的工具。

你设备上的不同项目可能使用不同版本的 Node.js。对这些不同的项目仅使用一个版本（由 `npm` 安装的版本）可能无法为你提供准确的执行结果。

例如，如果你将 10.0.0 的 Node 版本用于使用 12.0.0 的项目，则可能会出现一些错误。如果你用 npm 将 Node 版本更新到 12.0.0，并且你将它用于使用 10.0.0 的项目，你可能无法获得预期的体验。

NVM 允许你安装不同版本的 Node，并根据你正在通过命令行处理的项目在这些版本之间切换。

在继续之前，如果你已经安装了 Node.js，我还建议你卸载它，这样就不会有 Node.js 和 NVM 之间的任何冲突。



**在 Windows 上安装 NVM**

NVM 主要在 Linux 和 Mac 上得到支持。它不支持 Windows。但是 coreybutler 创建了一个类似的工具，用于在 Windows 中提供 NVM 体验，叫作 [nvm-windows](https://github.com/coreybutler/nvm-windows)。

以下是它的安装方法：

1. 点击 “立即下载”：

   在 nvm-windows 仓库的 Readme 文件中，单击 “立即下载”：

   ![image-338](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-338.png)

   这将打开一个显示不同 NVM 版本的页面。

2. 安装最新版本的 .exe 文件：

   在最新版本中，单击 nvm-setup.exe 资源：

   ![](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20231214000940061.png)

3. 完成安装向导：

   打开你下载的文件，然后完成安装向导。完成后，你可以通过运行以下命令确认 NVM 已安装：

   ```shell
   nvm -v
   ```

   如果 NVM 安装正确，此命令将显示已安装的 NVM 版本。



**使用**

安装了 NVM 后，你现在可以在你的 Windows、Linux 或 Mac 设备中安装、卸载和切换不同的 Node 版本。

你可以像这样安装 Node 版本：

- 安装最新版本的 Node：

  ```shell
  nvm install latest
  ```

- 安装 `X.Y.Z` Node 版本：

  ```shell
  nvm install vX.Y.Z
  ```

如果你想在任何时候使用特定版本，你可以在终端中运行以下命令：

```shell
nvm use vA.B.C
```



## 2.3、环境配置

这里的环境配置主要配置的是 npm 安装的全局模块所在的路径，以及缓存 cache 的路径。



**node_global**

npm 全局下载依赖时，会默认下载到当前使用的 Nodejs 版本的路径文件夹。这就导致了如果我切换另外一个 Nodejs 时，之前全局安装的依赖都无法使用。为了解决这个问题，我们需要把全局默认下载依赖的统一放到一个文件夹中，我们一般会命名这个文件夹名为 node_global。



**node_cache**

在运行 npm 命令执行操作的时候，会默认在 `C:\Users\{用户}\AppData\Local\npm-cache` 中生成一些缓存数据，如果不想增加到 C 盘，通常会会把缓存数据统一放置，命名 npm 的缓存文件为 node_cache 或者 npm_cache。我们一般会把 npm 的缓存与 npm 全局安装的依赖这两个文件夹放到同级目录下。



例如：我希望将全局模块所在路径和缓存路径放在 `D:\Nodejs` 文件夹下。

1. 在文件夹 `D:\Nodejs` 下创建两个文件夹 node_global 及 node_cache：

   ![image-20231214002338556](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20231214002338556.png)

2. 创建完两个空文件夹之后，打开命令行窗口，输入：

   ```shell
   # 修改npm全局预设
   npm config set prefix "D:\Nodejs\node_global"
   
   # 修改npm缓存
   npm config set cache "D:\Nodejs\node_cache"
   ```

3. 查看确认全局预设与缓存配置：

   ```shell
   # 查看npm全局预设
   npm prefix -g
   
   # 查看npm缓存
   npm config get cache
   ```

4. 前面配置 prefix 预设只是告诉了 npm 安装依赖到全局预设路径下，我们也需要告诉电脑，当我们输入cli 命令时，去到全局预设路径下查找。

   进入环境变量对话框，新建系统变量：

   - 变量名：`NODE_GLOBAL`，变量值：`D:\Nodejs\node_global`
   - 变量名：`NODE_PATH`，变量值：`%NODE_GLOBAL%\node_modules`

   将新建的两个环境变量配置到 Path 中，请注意顺序（未装 NVM 请忽视 NVM 相关环境变量）：

   ![image-20231214004741293](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20231214004741293.png)

   以上涉及的变量：

   - `NVM_HOME`：NVM 安装路径
   - `NVM_SYMLINK`：NVM 切换使用 Node 版本后，生成快捷方式指向的，快捷方式路径
   - `NODE_GLOBAL`：Node 全局安装依赖的路径
   - `NODE_PATH`：Node 全局安装的依赖包路径

5. 配置完后，安装个 module 测试下，我们就安装最常用的 vue-cli 模块。

   在命令行输入如下命令进行模块的全局安装：

   ```shell
   npm install -g @vue/cli
   ```

   查看 vue 版本：

   ```shell
   vue --version
   ```

