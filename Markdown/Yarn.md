---
typora-copy-images-to: upload
---

# 1、入门

## 1.1、简介

Yarn 是由 Facebook、Google、Exponent 和 Tilde 联合推出了一个新的 JS 包管理工具 ， 你可以通过它使用全世界开发者的代码，或者分享自己的代码给全世界的开发者。

代码通过 **软件包（package）** 的方式被共享。一个软件包里包含了所有需要共享的代码，以及一个描述软件包信息的文件 `package.json` （叫做 **清单**）。

正如官方文档中写的，Yarn 是为了弥补 npm 的一些缺陷而出现的。



**Yarn的优点？**

- **速度快** 。速度快主要来自以下两个方面：
  1. 并行安装：无论 npm 还是 Yarn 在执行包的安装时，都会执行一系列任务。npm 是按照队列执行每个 package，也就是说必须要等到当前 package 安装完成之后，才能继续后面的安装。而 Yarn 是同步执行所有任务，提高了性能。
  2. 离线模式：如果之前已经安装过一个软件包，用 Yarn再次安装时之间从缓存中获取，就不用像npm那样再从网络下载了。

- 安装 **版本统一**：为了防止拉取到不同的版本，Yarn 有一个锁定文件（lock file）记录了被确切安装上的模块的版本号。每次只要新增了一个模块，Yarn 就会创建（或更新）yarn.lock 这个文件。这么做就保证了，每一次拉取同一个项目依赖时，使用的都是一样的模块版本。

  npm 其实也有办法实现处处使用相同版本的 packages，但需要开发者执行 `npm shrinkwrap` 命令。这个命令将会生成一个锁定文件，在执行 `npm install` 的时候，该锁定文件会先被读取，和 Yarn 读取 yarn.lock 文件一个道理。

  npm 和 Yarn 两者的不同之处在于，Yarn 默认会生成这样的锁定文件，而 npm 要通过 `shrinkwrap` 命令生成 npm-shrinkwrap.json 文件，只有当这个文件存在的时候，packages 版本信息才会被记录和更新。
- **更简洁的输出**：npm 的输出信息比较冗长。在执行 `npm install` 的时候，命令行里会不断地打印出所有被安装上的依赖。相比之下，Yarn 简洁太多：默认情况下，结合了 emoji 直观且直接地打印出必要的信息，也提供了一些命令供开发者查询额外的安装信息。
- **多注册来源处理：**所有的依赖包，不管他被不同的库间接关联引用多少次，安装这个包时，只会从一个注册来源去装，要么是 npm 要么是 bower，防止出现混乱不一致。
- **更好的语义化**： Yarn 改变了一些 npm 命令的名称，比如 `yarn add/remove`，感觉上比 npm 原本的 `install/uninstall` 要更清晰。



## 1.2、安装

### 1.2.1、通过  Corepack 安装

**安装 Corepack**

管理 Yarn 的首选方法是通过 Corepack，这是一个从 16.10 开始的所有 Node.js 版本附带的新二进制文件。它充当您和 Yarn 之间的中介，并允许您在多个项目中使用不同的包管理器版本，而无需再签入 Yarn 二进制文件。



用户根据自己的 Node.js 版本执行以下命令：

- Node.js >=16.10

  默认情况下，Corepack 包含在所有 Node.js 安装中，但目前可以选择加入。若要启用它，请运行以下命令：

  ```shell
  corepack enable
  ```

- Node.js <16.10

  在 16.10 之前的版本中，Node.js 不包含 Corepack；要解决此问题，请运行：

  ```shell
  npm i -g corepack
  ```



**更新全局 Yarn 版本**

用户根据自己的 Node.js 版本执行以下 corepack 命令：

- Node.js ^16.17 or >=18.6

  ```shell
  corepack prepare yarn@stable --activate
  ```

- Node.js <16.17 or <18.6

  查看[最新的 Yarn](https://github.com/yarnpkg/berry/releases/latest) 版本，记下版本号，然后运行：

  ```shell
  corepack prepare yarn@<version> --activate
  ```

  示例：

  ```shell
  corepack prepare yarn@3.4.1 --activate
  ```



### 1.2.2、直接安装

也可以直接通过 npm 安装：

```shell
npm i -g yarn
```

查看版本：

```shell
yarn -v
```



**更新到最新版本**

如果以后要将 Yarn 更新到最新版本，请运行：

```shell
yarn set version stable
```

也可以指定版本：

```shell
yarn set version 3.4.1
```



## 1.3、环境配置

和安装 Node.js 一样，这里也要统一修改 Yarn 的全局模块所在路径和缓存路径：

1. 首先查看 Yarn 全局模块所在路径和缓存路径：

   ```shell
   # 全局模块所在路径
   yarn config get globalFolder
   
   # 缓存路径
   yarn config get cacheFolder
   ```
   
   附：可以通过以下命令查看 Yarn 配置列表：
   
   ```shell
   yarn config
   ```
   
   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20231213225852215.png" alt="image-20231213225852215" />
   
2. 首先清除以上文件数据，然后创建新的全局模块所在路径和缓存路径：

   ![image-20231213231639081](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20231213231639081.png)

3. 修改 Yarn 的全局模块所在路径和缓存路径：

   ```shell
   yarn config set globalFolder "D:\Nodejs\yarn_global"
   
   yarn config set cacheFolder "D:\Nodejs\yarn_cache"
   ```

   

**设置代理**

```shell
yarn config set httpProxy "http://127.0.0.1:7890"

yarn config set httpsProxy "http://127.0.0.1:7890"
```



# 2、使用问题

## 2.1、Yarn3 安装依赖没生成 node_modules

将 Yarn 从 v1 升级到 v3 后，使用 Yarn 3 执行 `yarn install` 安装项目依赖后提示成功，也没有报错，但是没有 node_modules 文件夹，是因为 `nodeLinker` 这个配置，下面是这个配置详细的说明：

```
Defines what linker should be used for installing Node packages (useful to enable the node-modules plugin), one of: pnp, pnpm and node-modules.

nodeLinker: "pnp"
```

这个配置目前的默认值是 `pnp`，取值范围是：`[ pnp | pnpm | node-modules ]`

pnp 是类似 pnpm 的方式，内置在 Yarn v3 中，但当前报错的项目不支持，所以需要降级。



**解决方案**

将 `nodeLinker ` 降级为 `node-modules`：

```shell
yarn config set nodeLinker node-modules
```

然后重新安装依赖：

```shell
yarn install
```



**关于 pnp**

在我们执行 `npm install` 时，npm 会做出如下操作：

1. 向 registry 查询获取模块的地址
2. 根据 package.json 中的配置确定需要安装的模块版本
3. 下载对应的压缩包，存放在 `~/.npm` 目录
4. 解压压缩包到当前项目的 `node_modules` 目录

而在 Node.js 中，我们提供给 require 方法的参数如果不是一个路径，也不是 node 的核心模块， node 将试图去当前目录的 node_modules 文件夹里搜索。如果当前目录的 node_modules 里没有找到， node 会继续试图在父目录的 node_modules 里搜索，这样递归下去直到根目录。

这些过程都需要进行大量的文件 I/O 操作，这无疑是非常低效的。为了解决这些问题，Facebook 提出了 Plug’n’Play(PnP) 方案。

在 Yarn 中，当我们开启 PnP 后，Yarn 会生成一个 `.png.js` 文件来描述项目的依赖信息和所需模块的查找路径。同时，项目目录下不再需要一个 node_modules 目录，取而代之的是一个全局的缓存目录，项目所需依赖都可以从这个目录中获取。
