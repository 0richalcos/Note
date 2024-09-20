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

你设备上的不同项目可能使用不同版本的 Node.js。对这些不同的项目仅使用一个版本（由 npm 安装的版本）可能无法为你提供准确的执行结果。

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



# 3、包管理工具

在 Node.js 生态系统中，包管理工具（Package Manager）是专门用于管理项目中的第三方依赖库和模块的工具。它帮助开发者在项目中自动化管理库的下载、更新、安装、移除，并解决不同库之间的依赖关系。

包管理工具通常依赖一个远程包仓库（例如 npm Registry）来存储和分发库。开发者通过命令行工具从远程仓库下载和安装库，并且包管理工具会自动更新项目的依赖文件，如 package.json 和 lock 文件，确保项目的一致性



**包管理工具的功能**

1. 依赖管理：在 Node.js 项目中，经常会使用第三方库来简化开发工作。包管理工具自动处理这些库之间的依赖，确保所需的依赖被正确安装。
2. 安装包：通过包管理工具，开发者只需通过命令行指定库的名称，工具会自动从远程仓库下载并安装库，减少了手动查找和安装的步骤。例如：
   - `npm install express`
   - `yarn add lodash`
   - `pnpm add axios`
3. 版本控制：包管理工具确保项目中的库的版本保持一致，避免因为库版本不一致而导致的项目问题。例如，`package-lock.json` 或 `yarn.lock` 文件会记录依赖的具体版本，以确保在不同的环境下安装相同版本的包。
4. 依赖更新：包管理工具可以自动检查项目中的依赖是否有新版本，并提供更新机制。例如，`npm update` 或 `yarn upgrade`。
5. 发布和共享：开发者可以将自己的代码库打包发布到公共仓库中，供其他开发者使用。npm、Yarn、pnpm 都允许发布自己的包到 npm Registry。



**常见的包管理工具**

1. npm（Node Package Manager）

   默认工具：随着 Node.js 一起安装，几乎所有 Node.js 项目都默认使用 npm 进行包管理。

   npm 的特点：简单、成熟、支持大量第三方库，生态系统非常庞大。

2. Yarn

   替代工具：由 Facebook 开发，旨在改进 npm 在早期版本中的性能和稳定性问题。

   Yarn 的特点：速度快、并行下载、离线安装、稳定的依赖管理。

3. pnpm

   高效工具：通过符号链接机制提高磁盘使用效率，并加速包安装速度。

   pnpm 的特点：节省磁盘空间、速度快、适合大型项目和 monorepo 环境。



## 3.1、npm



## 3.2、Yarn

### 3.2.1、简介

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



### 3.2.2、安装

#### 使用  Corepack 安装

管理 Yarn 的首选方法是通过 Corepack，这是一个从 16.10 开始的所有 Node.js 版本附带的新二进制文件。它充当您和 Yarn 之间的中介，并允许您在多个项目中使用不同的包管理器版本，而无需再签入 Yarn 二进制文件。

1. **安装 Corepack**

   用户根据自己的 Node.js 版本执行以下命令：

   - Node.js >= 16.10

     默认情况下，Corepack 包含在所有 Node.js 安装中，但目前可以选择加入。若要启用它，请运行以下命令：

     ```shell
     corepack enable
     ```

   - Node.js < 16.10

     在 16.10 之前的版本中，Node.js 不包含 Corepack；要解决此问题，请运行：

     ```shell
     npm i -g corepack
     ```

2. **安装全局 Yarn 版本**

   用户根据自己的 Node.js 版本执行以下 Corepack 命令：

   - Node.js ^16.17 or >= 18.6

     ```shell
     corepack prepare yarn@stable --activate
     ```

   - Node.js < 16.17 or < 18.6

     查看[最新的 Yarn](https://github.com/yarnpkg/berry/releases/latest) 版本，记下版本号，然后运行：

     ```shell
     corepack prepare yarn@<version> --activate
     ```

     示例：

     ```shell
     corepack prepare yarn@3.4.1 --activate
     ```




#### 直接安装

1. **通过 npm 安装**

   ```shell
   npm i -g yarn
   ```

2. **验证安装**

   安装完成后，可以使用以下命令检查 yarn 是否已正确安装：

   ```shell
   yarn -v
   ```

3. **更新 Yarn 版本**

   如果以后要将 Yarn 更新到最新版本，请运行：

   ```shell
   yarn set version stable
   ```

   也可以指定版本：

   ```shell
   yarn set version 3.4.1
   ```




### 3.2.3、环境配置

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

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20231213231639081.png" alt="image-20231213231639081" />

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



### 3.2.4、使用问题

#### Yarn3 安装依赖没生成 node_modules

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



#### 快速删除 node_modules

当安装了较多模块后，node_modules 目录下的文件会很多，直接删除整个目录会很慢。可以全局安装 rimraf  模块，然后通过其命令来快速删除 node_modules 目录。

全局安装 rimraf：

```shell
npm install rimraf -g
```

删除 node_modules：

```shell
rimraf node_modules
```



## 3.3、pnpm

### 3.3.1、简介

**节省磁盘空间**

使用 npm 时，依赖每次被不同的项目使用，都会重复安装一次。  而在使用 pnpm 时，依赖会被存储在内容可寻址的存储中，所以：

1. 如果你用到了某个依赖项的不同版本，只需将不同版本间存在差异的文件添加到仓库。

   例如，如果有 100 个文件，而新版本仅更改了其中一个文件， `pnpm update` 只会向存储添加 1 个新文件，而不是仅仅为了单个更改而克隆整个依赖项。

2. 所有文件都会存储在硬盘上的某一位置。 

   当软件包被被安装时，包里的文件会硬链接到这一位置，而不会占用额外的磁盘空间。 这允许你跨项目地共享同一版本的依赖。

因此，您在磁盘上节省了大量空间，这与项目和依赖项的数量成正比，并且安装速度要快得多！



**提高安装速度**

pnpm 分三个阶段执行安装：

1. 依赖解析：仓库中没有的依赖都被识别并获取到仓库。
2. 目录结构计算：node_modules 目录结构是基于依赖关系计算的。
3. 链接依赖项：所有剩余的依赖项都会从存储中获取并硬链接到 node_modules，这种方法比传统的三阶段安装过程（解析、获取和将所有依赖项写入 node_modules）快得多。



**创建一个非扁平的 node_modules 目录**

使用 npm 或 Yarn Classic 安装依赖项时，所有的包都被提升到模块目录的根目录。 这样就导致了一个问题，源码可以直接访问和修改依赖，而不是作为只读的项目依赖。

默认情况下，pnpm 使用符号链接将项目的直接依赖项添加到模块目录的根目录中：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/QQ_1726745555326.png" alt="QQ_1726745555326" style="zoom:50%;" />

> [!TIP]
>
> 如果您的工具不适用于符号链接，您仍然可以使用 pnpm 并将 `node-linker` 设置设置为 hoisted。 这样 pnpm 就会创建一个类似于 npm 和 Yarn Classic 创建的 node_modules 目录。



### 3.3.2、安装

#### 使用 Corepack 安装

Corepack 是 Node.js 16.10.0 引入的一种工具，用于管理 Node.js 包管理工具（如 pnpm、Yarn）的版本，并简化它们的安装过程。通过 Corepack，你可以无需手动安装 pnpm，而是让 Corepack 来自动管理。

1. **启用 Corepack**

   从 v16.10 开始，Node.js 发布了 [Corepack](https://nodejs.org/api/corepack.html) 来管理包管理器。 这是一项实验性功能，因此您需要通过运行如下脚本来启用它：

   ```shell
   corepack enable
   ```

2. **安装并激活**

   然后使用以下 Corepack 命令会安装最新版本的 pnpm 并将其激活，使其在全局可用：

   ```shell
   corepack prepare pnpm@latest --activate
   ```

   如果需要使用特定版本的 pnpm，可以指定版本号，例如：

   ```shell
   corepack prepare pnpm@7.0.0 --activate
   ```

3. **验证安装**

   安装完成后，可以使用以下命令检查 pnpm 是否已正确安装：

   ```shell
   pnpm --version
   ```

你可以通过下列命令固定项目所用的 pnpm 版本：

```shell
corepack use pnpm@latest
```

这会添加一个 `packageManager` 字段到您本地的 package.json，指示 Corepack 始终在该项目上使用特定的版本。 如果您想要可复现性，这可能很有用，因为所有使用 Corepack 的开发人员都将使用与您相同的版本。 当一个新版本的 pnpm 发布时，您可以重新运行上述命令。

> [!IMPORTANT]
>
> `corepack use pnpm@latest` 命令需要 Corepack 在高版本时才可用，可以使用 `corepack -h` 查看是否支持此命令。
>
> Corepack 在后面的版本中，`corepack prepare pnpm@latest --activate` 被替换为 `corepack install --global pnpm@latest`。



#### 直接安装

1. **通过 npm 安装**

   ```shell
   npm i -g pnpm@latest
   ```

2. **验证安装**

   安装完成后，可以使用以下命令检查 pnpm 是否已正确安装：

   ```shell
   pnpm -v
   ```



### 3.3.3、环境配置

1. 首先查看 pnpm 全局存储路径、缓存存储路径、状态存储路径：

   ```shell
   # 全局存储路径
   pnpm config get store-dir
   
   # 缓存存储路径
   pnpm config get cache-dir
   
   # 状态存储路径
   pnpm config get state-dir
   ```

   附：可以通过以下命令查看 pnpm 配置列表：

   ```shell
   pnpm config list
   ```

2. 修改 pnpm 全局存储路径、缓存存储路径、状态存储路径：

   ```shell
   # 全局存储路径
   pnpm config set store-dir "D:\Nodejs\pnpm_global"
   
   # 缓存存储路径
   pnpm config set cache-dir "D:\Nodejs\pnpm_cache"
   
   # 状态存储路径
   pnpm config set state-dir "D:\Nodejs\pnpm_state"
   ```



# 4、package.json

## 4.1、版本号

当我们查看 package.json 中已安装的库的时候，会发现他们的版本号之前都会加一个符号，有的是插入符号（`^`），有的是波浪符号（`~`）：

```json
"dependencies": {
    "bluebird": "^3.3.4",
    "body-parser": "~1.15.2"
}
```



**波浪符号（~）**

他会更新到当前 minor version（也就是中间的那位数字）中最新的版本。

放到我们的例子中就是：`body-parser:~1.15.2`，这个库会去匹配更新到 1.15.x 的最新版本，如果出了一个新的版本为 1.16.0，则不会自动升级。波浪符号是曾经 npm 安装时候的默认符号，现在已经变为了插入符号。



**插入符号（^）**

这个符号就显得非常的灵活了，他将会把当前库的版本更新到当前 major version（也就是第一位数字）中最新的版本。

放到我们的例子中就是：`bluebird:^3.3.4`，这个库会去匹配 3.x.x 中最新的版本，但是他不会自动更新到 4.0.0。



**minor verision 和 major version**

版本号对应就是 `MAJOR.MINOR.PATCH`：

- MAJOR：这个版本号变化了表示有了一个不可以和上个版本兼容的大更改。
- MINOR：这个版本号变化了表示有了增加了新的功能，并且可以向后兼容。
- PATCH：这个版本号变化了表示修复了 bug，并且可以向后兼容。

比如 1.15.2，则代表 1 是 marjor version，15 是 minor version，2 是 patch version。

因为 major version 变化表示可能会影响之前版本的兼容性，所以无论是波浪符号还是插入符号都不会自动去修改 major version，因为这可能导致程序 crush，可能需要手动修改代码。



## 4.2、process.env.NODE_ENV

`process.env.NODE_ENV` 应该是我们最熟悉的环境变量了，它经常出现在使用框架或者类库的时候，被用来区分不同的环境（开发，测试，生产等），以便我们进行相对应的项目配置，比如是否开启 sourceMap，api 地址切换等。



**process**

`process` 对象是一个 全局变量 （global），提供有关信息，控制当前 Node.js 进程。作为一个对象，它对于 Node.js 应用程序始终是可用的，故无需使用 `require()`。



**process.env**

`process.env` 属性返回一个包含用户环境信息的对象。



**process.env.NODE_ENV**

在 node  环境中，当我们打印 `process.env` 时，发现它并没有 `NODE_ENV` 这一个属性。实际上，`process.env.NODE_ENV` 是在package.json 的 `scripts` 命令中注入的，也就是 `NODE_ENV` 并不是 node 自带的，而是由用户定义的，至于为什么叫 `NODE_ENV`，应该是约定成俗的吧。

```json
{
  "scripts": {
    "dev": "NODE_ENV=development webpack --config webpack.dev.config.js"
  }
}
```

可以看到 `NODE_ENV` 被赋值为 `development`，当执行 `npm run dev` 时，我们就可以在 `webpack.dev.config.js` 脚本中以及它所引入的脚本中访问到 `process.env.NODE_ENV`，而无法在其它脚本中访问。

