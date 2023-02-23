# 1、简介

Yarn 是代码的包管理器。它允许您使用来自世界各地的其他开发人员并与之共享代码。Yarn 可以快速、安全、可靠地做到这一点，因此您不必担心。

代码通过称为 **package** 的东西共享。包包含共享的所有代码以及描述包的文件（称为 **manifest**）。`package.json`



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