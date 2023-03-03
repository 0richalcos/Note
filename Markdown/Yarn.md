# 1、简介

Yarn 是由 Facebook、Google、Exponent 和 Tilde 联合推出了一个新的 JS 包管理工具 ，正如官方文档中写的，Yarn 是为了弥补 npm 的一些缺陷而出现的。



**Yarn的优点？**

- **速度快** 。速度快主要来自以下两个方面：
  1. 并行安装：无论 npm 还是 Yarn 在执行包的安装时，都会执行一系列任务。npm 是按照队列执行每个 package，也就是说必须要等到当前 package 安装完成之后，才能继续后面的安装。而 Yarn 是同步执行所有任务，提高了性能。
  2. 离线模式：如果之前已经安装过一个软件包，用 Yarn再次安装时之间从缓存中获取，就不用像npm那样再从网络下载了。

- 安装**版本统一**：为了防止拉取到不同的版本，Yarn 有一个锁定文件（lock file）记录了被确切安装上的模块的版本号。每次只要新增了一个模块，Yarn 就会创建（或更新）yarn.lock 这个文件。这么做就保证了，每一次拉取同一个项目依赖时，使用的都是一样的模块版本。npm 其实也有办法实现处处使用相同版本的 packages，但需要开发者执行 `npm shrinkwrap` 命令。这个命令将会生成一个锁定文件，在执行 `npm install` 的时候，该锁定文件会先被读取，和 Yarn 读取 yarn.lock 文件一个道理。npm 和 Yarn 两者的不同之处在于，Yarn 默认会生成这样的锁定文件，而 npm 要通过 shrinkwrap 命令生成 npm-shrinkwrap.json 文件，只有当这个文件存在的时候，packages 版本信息才会被记录和更新。
- **更简洁的输出**：npm 的输出信息比较冗长。在执行 `npm install` 的时候，命令行里会不断地打印出所有被安装上的依赖。相比之下，Yarn 简洁太多：默认情况下，结合了 emoji 直观且直接地打印出必要的信息，也提供了一些命令供开发者查询额外的安装信息。
- **多注册来源处理：**所有的依赖包，不管他被不同的库间接关联引用多少次，安装这个包时，只会从一个注册来源去装，要么是 npm 要么是 bower，防止出现混乱不一致。
- **更好的语义化**： Yarn 改变了一些 npm 命令的名称，比如 `yarn add/remove`，感觉上比 npm 原本的 `install/uninstall` 要更清晰。



# 2、安装

## 2.1、安装 Corepack

管理 Yarn 的首选方法是通过 Corepack，这是一个从 16.10 开始的所有 Node.js 版本附带的新二进制文件。它充当您和 Yarn 之间的中介，并允许您在多个项目中使用不同的包管理器版本，而无需再签入 Yarn 二进制文件。



用户根据自己的 Node.js 版本执行以下命令：

- **Node.js >=16.10**

  默认情况下，Corepack 包含在所有 Node.js 安装中，但目前可以选择加入。若要启用它，请运行以下命令：

  ```shell
  corepack enable
  ```

- **Node.js <16.10**

  在 16.10 之前的版本中，Node.js 不包含 Corepack;要解决此问题，请运行：

  ```shell
  npm i -g corepack
  ```



## 2.2、更新全局 Yarn 版本

用户根据自己的 Node.js 版本执行以下命令：

- **Node.js ^16.17 or >=18.6**

  ```shell
  corepack prepare yarn@stable --activate
  ```

- **Node.js <16.17 or <18.6**

  查看[最新的 Yarn](https://github.com/yarnpkg/berry/releases/latest) 版本，记下版本号，然后运行：

  ```shell
  corepack prepare yarn@<version> --activate
  ```

  示例：

  ```shell
  corepack prepare yarn@3.4.1 --activate
  ```