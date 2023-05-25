---
typora-copy-images-to: upload
---

# 1、OnlyOffice

## 1.1、基本概念和安装

ONLYOFFICE Document Server API 用于使开发人员将 ONLYOFFICE 文档/电子表格/演示文稿 编辑器集成到他们自己的网站中，并设置和管理编辑器。

这里使用 [Docker](https://www.docker.com/) 进行集成，避免了出现服务器系统的不同而重新适配的问题。Docker的思想来自于集装箱，集装箱解决了什么问题？在一艘大船上，可以把货物规整的摆放起来。并且各种各样的货物被集装箱标准化了，集装箱和集装箱之间不会互相影响。那么我就不需要专门运送水果的船和专门运送化学品的船了。只要这些货物在集装箱里封装的好好的，那我就可以用一艘大船把他们都运走。

**需要了解一下几个概念：镜像，容器，仓库：**

> 镜像（image）：Docker 镜像就是一个只读的模板，镜像可以用来创建 Docker 容器。Docker 提供了一个很简单的机制来创建镜像或者更新现有的镜像，用户甚至可以直接从其他人那里下载一个已经做好的镜像来直接使用。镜像是一种文件结构。Dockerfile中的每条命令都会在文件系统中创建一个新的层次结构，文件系统在这些层次上构建起来，镜像就构建于这些联合的文件系统之上。Docker官方网站专门有一个页面来存储所有可用的镜像，网址是：[index.docker.io](http://index.docker.io/)。

> 容器（ Container）：容器是从镜像创建的运行实例。它可以被启动、开始、停止、删除。每个容器都是相互隔离的、保证安全的平台。可以把容器看做是一个简易版的 Linux 环境，Docker 利用容器来运行应用。

> 仓库：仓库是集中存放镜像文件的场所，仓库注册服务器（Registry）上往往存放着多个仓库，每个仓库中又包含了多个镜像，每个镜像有不同的标签（tag）。目前，最大的公开仓库是 Docker Hub，存放了数量庞大的镜像供用户下载。

Docker 并非是一个通用的容器工具，它依赖于已存在并运行的 Linux 内核环境。它实质上是在已经运行的 Linux 下制造了一个隔离的文件环境，因此它执行的效率几乎等同于所部署的 Linux 主机。因此，Docker 必须部署在 Linux 内核的系统上。如果其他系统想部署 Docker 就必须安装一个虚拟 Linux 环境。



**Windows 平台上安装 Docker：**

Windows10 中内置了 Linux（WSL），如何打开可以去微软官网 **[查看教程](https://docs.microsoft.com/zh-cn/windows/wsl/install-win10)**。

首先去 Docker 官网下载 **[Docker Desktop](https://desktop.docker.com/win/stable/Docker%20Desktop%20Installer.exe)**，下载完了直接安装。安装好了需要配置一下 Docker 的镜像源，替换为国内源。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20210303001708558.png" alt="image-20210303001708558" style="width:80%;" />


推荐的几个国内源：

```
Docker中国区官方镜像
https://registry.docker-cn.com

网易
http://hub-mirror.c.163.com

ustc 
https://docker.mirrors.ustc.edu.cn

中国科技大学
https://docker.mirrors.ustc.edu.cn

阿里云容器服务(上图所使用的)，去阿里云搜索 容器镜像服务
```

运行测试（dos，powershell，windows terminal都可以，后面的 shell 命令同样）：

```shell
docker -v
```



Docker 安装好了拉取 Onlyoffice 镜像，创建容器：

```shell
#拉取onlyoffice镜像
docker pull onlyoffice/documentserver

#查看镜像
docker images

#运行镜像（创建容器），并绑定端口，这里绑定 9000
docker run -itd --name office -p 9000:80 onlyoffice/documentserver

#查看正在运行的容器，后面加上 -a 查看所有容器
docker ps

#停止运行
docker stop office

#开始运行
docker start office

#删除镜像（如果有镜像创建的容器需要先删除容器，删除容器需要先停止运行才可以删除）
docker rm office
docker rmi onlyoffice/documentserver
```

查看服务是否已经启动（需要等待一分钟左右）：

```
http://电脑ip:绑定端口
```

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20210303003314432.png" alt="image-20210303003314432" style="width:100%;" />


通常可以在以下编辑器文件夹中找到 API JavaScript 文件：

```
http://documentserver/web-apps/apps/api/documents/api.js
```

其中`documentserver`是安装了 ONLYOFFICE Document Server 的服务器的名称。

要嵌入编辑器的目标HTML文件需要有一个占位符div标记，其中将传递有关编辑器参数的所有信息：

```html
<div id="placeholder"></div>
<script type="text/javascript" src="https://documentserver/web-apps/apps/api/documents/api.js"></script>
```

包含可变参数的页面代码是这样的:

```javascript
var docEditor = new DocsAPI.DocEditor("placeholder", config);
```

其中config是一个对象：

```javascript
config = {
    "document": {
        "fileType": "docx",
        "key": "Khirz6zTPdfd7",
        "title": "Example Document Title.docx",
        "url": "https://example.com/url-to-example-document.docx"
    },
    "documentType": "word",
    "editorConfig": {
        "callbackUrl": "https://example.com/url-to-callback.ashx"
    }
};
```

一个简单的例子：

```html
<!DOCTYPE html>
<html style="height: 100%;">
<head>
    <title>ONLYOFFICE Api Documentation</title>
</head>
<body style="height: 100%; margin: 0;">
    <div id="placeholder" style="height: 100%"></div>
    <!--将 documentserver 替换为 安装了 ONLYOFFICE Document Server 的服务器-->
    <script type="text/javascript" src="https://documentserver/web-apps/apps/api/documents/api.js"></script>
    <script type="text/javascript">
        window.docEditor = new DocsAPI.DocEditor("placeholder",
         	{
                "document": {
                    "fileType": "docx",
                    "key": "E7FAFC9C22A8",
                    "title": "Example Document Title.docx",
                    "url": "https://example.com/url-to-example-document.docx" //访问文档的url，自行替换
                },
                "documentType": "word",
                "height": "100%",
                "width": "100%"
            });
    </script>
</body>
</html>
```



# 2、工作原理

## 2.1、打开文件

参考图和以下步骤说明了在ONLYOFFICE Document Server中打开文档的过程：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20210304001457116.png" alt="image-20210304001457116" style="width:50%;" />


1. 用户使用文档管理器（在他/她的浏览器中找到）打开文档进行查看或编辑。

	> 浏览器中的文档管理器从文档存储服务接收用户可用的所有文档的列表。

2. 使用 JavaScript API 将文档标识符及其在文档存储服务处的链接发送到文档编辑器。

3. 文档编辑器向文档编辑服务发出打开文档的请求。文档编辑器使用从文档管理器接收的文档标识符及其链接（在步骤2）。

4. 文档编辑服务使用提供的ID和链接从文档存储服务下载文档文件。 在此步骤中，还执行了将文件转换为Office Open XML 格式的操作，以使文档编辑器具有更好的性能和格式兼容性。

5. 准备就绪后，文档编辑服务会将文档文件传输到基于浏览器的文档编辑器。

6. 文档编辑器显示文档文件和/或（如果提供了适当的权限）允许对其进行编辑。

编辑完成后，将进行文档保存过程。



**实践**

1. 创建一个空的 html 文件。

2. 如下所示添加 div 元素。

	```html
	<div id="placeholder"></div>
	```

3. 使用将用于您的网站的 JavaScript API指定的 ONLYOFFICE Document Server 链接。 

  ```html
<script type="text/javascript" src="https://documentserver/web-apps/apps/api/documents/api.js"></script>
  ```

  > 其中 documentserver 是安装了 ONLYOFFICE Document Server 的服务器的名称。

4. 添加初始化 div 元素的文档编辑器的脚本，该脚本带有要打开的文档的配置。

  ```javascript
new DocsAPI.DocEditor("placeholder", {
    "document": {
        "fileType": "docx",
        "key": "Khirz6zTPdfd7",
        "title": "Example Document Title.docx",
        "url": "https://example.com/url-to-example-document.docx"
    },
    "documentType": "word",
    "height": "100%",
    "width": "100%"
});
  ```

  > 其中 example.com 是安装了文档管理器和文档存储服务的服务器的名称（说白了就是访问文档的 URL）。

5. 在浏览器中打开html文件。

> 如果出现只有工具栏、看不到文章主体的问题。原因是没有给 <body></body> 设置高度，试着加上
>
> ```css
> html, body {
>  margin: 0;
>  height: 100%
> }
> ```



## 2.2、保存文件

参考图和以下步骤说明了将文档保存在ONLYOFFICE Document Server中的过程。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20210307232148928.png" alt="image-20210307232148928" style="width:50%;" />


1. 用户在文档编辑器中编辑文档。
2. 文档编辑器将所做的更改发送到文档编辑服务。
3. 用户关闭文档编辑器。
4. 文档编辑服务监视文档工作的结束，并将从文档编辑器发送的更改收集到一个文档中。
5. 文档编辑服务使用 JavaScript API 中的 callbackUrl 通知文档存储服务有关文档编辑结束的信息，并返回到修改后的文档的链接。
6. 文档存储服务从文档编辑服务下载带有所有已保存更改的文档文件并进行存储。



**实践**

1. 创建一个回调处理程序，以从文档编辑服务中保存文档。

	```java
	public void saveFile(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
	    PrintWriter writer = response.getWriter();
	    Scanner scanner = new Scanner(request.getInputStream()).useDelimiter("\\A");
	    String body = scanner.hasNext() ? scanner.next() : "";
	    JSONObject jsonObj = JSONObject.parseObject(body);
	    if ((int) jsonObj.get("status") == 2) {
	        String downloadUri = (String) jsonObj.get("url");
	        URL url = new URL(downloadUri);
	        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
	        InputStream stream = connection.getInputStream();
	        // pathForSave 是保存文件的计算机文件夹的绝对路径，包括文件名。
	        File savedFile = new File(pathForSave);
	        try (FileOutputStream out = new FileOutputStream(savedFile)) {
	            int read;
	            final byte[] bytes = new byte[1024];
	            while ((read = stream.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	            out.flush();
	        }
	        connection.disconnect();
	    }
	    writer.write("{\"error\":0}");
	}
	```

2. 创建一个html文件以打开文档。

3. 在文档编辑器初始化的配置脚本中，使用参数行中的回调处理程序指定文件的URL。

	```javascript
	new DocsAPI.DocEditor("placeholder", {
	    "document": {
	        "fileType": "docx",
	        "key": "Khirz6zTPdfd7",
	        "title": "Example Document Title.docx",
	        "url": "https://example.com/url-to-example-document.docx"
	    },
	    "documentType": "word",
	    "editorConfig": {
	        "callbackUrl": "https://example.com/url-to-callback.ashx" //回调程序接口
	    }
	});
	```

	> 其中example.com是安装文档管理器和文档存储服务的服务器的名称。

4. 在浏览器中打开html文件并编辑文档。

5. 关闭文档编辑器。大约10秒钟后查看您的文档。应保存所有更改，这意味着配置正确。



**保存延时**

文档编辑完成后，文档编辑服务会将此通知文档存储服务。完成此操作之前的时间是使用已编辑文件到Office Open XML格式的转换时间（取决于文件大小、复杂性和计算机功率，可以执行相当长的时间）和转换开始延迟时间（默认情况下等于5秒）计算的。在大多数情况下，时间大约是编辑完成后的10秒。

转换开始延迟是必要的，以允许在不保存文件的情况下返回到文件编辑会话，例如，在打开文件进行编辑的情况下重新加载浏览器页面时。默认转换开始延迟时间在文档服务器配置文件中定义，可以在以下路径中找到：

```
/etc/onlyoffice/documentserver/default.json.
```

如果你想改变它，你可以使用 *local.json* 文件，所有编辑过的参数都应该存储在这里。此文件与 *default.json* 位于同一个目录中。

| 参数                                         | 说明                                                     | 类型    | 例子 |
| -------------------------------------------- | -------------------------------------------------------- | ------- | ---- |
| services.CoAuthoring.server.savetimeoutdelay | 定义已编辑文件关闭后的转换开始延迟时间（以毫秒为单位）。 | integer | 5000 |

*local.json* 配置示例

```json
{
    "services": {
        "CoAuthoring": {
            "server": {
                "savetimeoutdelay": 5000
            }
        }
    }
}
```



## 3.3、审阅

“审阅” 选项允许您审阅文档、更改句子、短语和其他页面元素、更正拼写等，而无需实际编辑文档。所有更改都将被记录并显示给创建文档的用户。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/review.png" alt="img" style="width:40%;" />


要启用“审阅”选项，必须将文档初始化的`permissions `部分中的`review`参数设置为`true`。文档状态栏将包含“审阅”菜单选项。

如果`edit`参数设置为`true`，`review`参数也设置为`true`，则用户将能够编辑文档、接受或拒绝更改并切换到审阅模式。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/accept_reject.png" alt="Reviewing" style="width:50%;" />


如果`edit`参数设置为`false`，而`review`参数设置为`true`，则文档将仅可用于审阅。

```javascript
var docEditor = new DocsAPI.DocEditor("placeholder", {
    "document": {
        "permissions": {
            "edit": false,
            "review": true
        },
        ...
    },
    ...
});
```

> 请注意，如果`mode`参数设置为`edit`，文档审阅将仅对文档编辑器可用。



# 3、文档

## 3.1、高级参数

可以为 ONLYOFFICE Document Server 更改的参数可细分为以下主要部分：

- config -- 允许更改使用的平台类型、文档显示大小（宽度和高度）以及打开的文档类型
	- document -- 包含与文档相关的所有参数（标题、url、文件类型等）
		- info -- 包含文档的其他参数（文档所有者、存储文档的文件夹、上载日期、共享设置）
		- permissions -- 定义文档是否可以编辑和下载
	- editorConfig -- 定义与编辑器界面相关的参数：打开模式（查看器或编辑器）、界面语言、附加按钮等）
		- customization  -- 允许自定义编辑器界面，并改变是否存在其他按钮、链接、更改徽标和编辑器所有者详细信息
		- embedded -- 仅用于嵌入式文档类型，允许更改用于控制嵌入式模式的按钮的行为
		- plugins -- 用于将必要的插件连接到文档服务器，以便所有文档编辑器用户都能看到它们
	- events -- 对文档应用某个操作（加载、修改等）时调用的特殊事件列表

包含所有附加参数的完整配置如下所示:

```javascript
config = {
    "document": {
        "fileType": "docx",
        "info": {
            "folder": "Example Files",
            "owner": "John Smith",
            "sharingSettings": [
                {
                    "permissions": "Full Access",
                    "user": "John Smith"
                },
                {
                    "isLink": true,
                    "permissions": "Read Only",
                    "user": "External link"
                },
                ...
            ],
            "uploaded": "2010-07-07 3:46 PM"
        },
        "key": "Khirz6zTPdfd7",
        "permissions": {
            "comment": true,
            "copy": true,
            "download": true,
            "edit": true,
            "fillForms": true,
            "modifyContentControl": true,
            "modifyFilter": true,
            "print": true,
            "review": true
        },
        "title": "Example Document Title.docx",
        "url": "https://example.com/url-to-example-document.docx"
    },
    "documentType": "word",
    "editorConfig": {
        "actionLink": ACTION_DATA,
        "callbackUrl": "https://example.com/url-to-callback.ashx",
        "createUrl": "https://example.com/url-to-create-document/",
        "customization": {
            "autosave": true,
            "chat": true,
            "commentAuthorOnly": false,
            "comments": true,
            "compactHeader": false,
            "compactToolbar": false,
            "compatibleFeatures": false,
            "customer": {
                "address": "My City, 123a-45",
                "info": "Some additional information",
                "logo": "https://example.com/logo-big.png",
                "mail": "john@example.com",
                "name": "John Smith and Co.",
                "www": "example.com"
            },
            "feedback": {
                "url": "https://example.com",
                "visible": true
            },
            "forcesave": false,
            "goback": {
                "blank": true,
                "requestClose": false,
                "text": "Open file location",
                "url": "https://example.com"
            },
            "help": true,
            "hideRightMenu": false,
            "logo": {
                "image": "https://example.com/logo.png",
                "imageEmbedded": "https://example.com/logo_em.png",
                "url": "https://example.com"
            },
            "macros": true,
            "macrosMode": "warn",
            "mentionShare": true,
            "plugins": true,
            "reviewDisplay": "original",
            "showReviewChanges": false,
            "spellcheck": true,
            "toolbarHideFileName": false,
            "toolbarNoTabs": false,
            "trackChanges": false,
            "unit": "cm",
            "zoom": 100
        },
        "embedded": {
            "embedUrl": "https://example.com/embedded?doc=exampledocument1.docx",
            "fullscreenUrl": "https://example.com/embedded?doc=exampledocument1.docx#fullscreen",
            "saveUrl": "https://example.com/download?doc=exampledocument1.docx",
            "shareUrl": "https://example.com/view?doc=exampledocument1.docx",
            "toolbarDocked": "top"
        },
        "lang": "en",
        "location": "us",
        "mode": "edit",
        "plugins": {
             "autostart": [
                 "asc.{0616AE85-5DBE-4B6B-A0A9-455C4F1503AD}",
                 "asc.{FFE1F462-1EA2-4391-990D-4CC84940B754}",
                 ...
             ],
             "pluginsData": [
                 "https://example.com/plugin1/config.json",
                 "https://example.com/plugin2/config.json",
                 ...
             ]
        },
        "recent": [
            {
                "folder": "Example Files",
                "title": "exampledocument1.docx",
                "url": "https://example.com/exampledocument1.docx"
            },
            {
                "folder": "Example Files",
                "title": "exampledocument2.docx",
                "url": "https://example.com/exampledocument2.docx"
            },
            ...
        ],
        "region": "en-US",
        "templates": [
            {
                "image": "https://example.com/exampletemplate1.png",
                "title": "exampletemplate1.docx",
                "url": "https://example.com/url-to-create-template1"
            },
            {
                "image": "https://example.com/exampletemplate2.png",
                "title": "exampletemplate2.docx",
                "url": "https://example.com/url-to-create-template2"
            },
            ...
        ],
        "user": {
            "id": "78e1e841",
            "name": "John Smith"
        }
    },
    "events": {
        "onAppReady": onAppReady,
        "onCollaborativeChanges": onCollaborativeChanges,
        "onDocumentReady": onDocumentReady,
        "onDocumentStateChange": onDocumentStateChange,
        "onDownloadAs": onDownloadAs,
        "onError": onError,
        "onInfo": onInfo,
        "onMetaChange": onMetaChange,
        "onOutdatedVersion": onOutdatedVersion,
        "onRequestClose": onRequestClose,
        "onRequestCompareFile": onRequestCompareFile,
        "onRequestCreateNew": onRequestCreateNew,
        "onRequestEditRights": onRequestEditRights,
        "onRequestHistory": onRequestHistory,
        "onRequestHistoryClose": onRequestHistoryClose,
        "onRequestHistoryData": onRequestHistoryData,
        "onRequestInsertImage": onRequestInsertImage,
        "onRequestMailMergeRecipients": onRequestMailMergeRecipients,
        "onRequestRename": onRequestRename,
        "onRequestRestore": onRequestRestore,
        "onRequestSaveAs": onRequestSaveAs,
        "onRequestSendNotify": onRequestSendNotify,
        "onRequestSharingSettings": onRequestSaveAs,
        "onRequestUsers": onRequestUsers,
        "onWarning": onWarning
    },
    "height": "100%",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.e30.t-IDcSemACt8x4iTMCda8Yhe3iZaWbvV5XKSTbuAn0M",
    "type": "desktop",
    "width": "100%"
};
```

> 其中example.com是安装了文档管理器和文档存储服务的服务器的名称。



## 3.2、Config

config base 部分允许更改使用的平台类型、文档显示大小（宽度和高度）以及打开的文档类型。

| 名字         | 说明                                                         | 类型   | 示例      |
| ------------ | ------------------------------------------------------------ | ------ | --------- |
| documentType | 定义要打开的文档类型：office三件套（Word、Excel、PowerPoint）分别对应（word、cell、slide） | string | "cell"    |
| height       | 在浏览器窗口中定义文档高度（默认为100%）。                   | string | "100%"    |
| token        | 定义以令牌的形式添加到文档服务器配置中的加密签名。           | string |           |
| type         | 定义用于访问文档的平台类型：desktop（电脑）、mobile（手机平板）、embedded（网页嵌入），默认为desktop | string | "desktop" |
| width        | 在浏览器窗口中定义文档宽度（默认为100%）。                   | string | "100%"    |



**示例**

```javascript
var docEditor = new DocsAPI.DocEditor("placeholder", {
    "documentType": "word",
    "height": "100%",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.e30.t-IDcSemACt8x4iTMCda8Yhe3iZaWbvV5XKSTbuAn0M",
    "type": "desktop",
    "width": "100%",
    ...
});
```



### Document

文档部分允许更改与文档相关的所有参数（标题、url、文件类型等），这些参数为必填字段。

| 名字     | 说明                                                         | 类型   | 示例            |
| -------- | ------------------------------------------------------------ | ------ | --------------- |
| fileType | 定义查看或编辑的源文档的文件类型。必须小写。                 | string | "docx"          |
| key      | 定义服务用于识别文档的唯一文档标识符。如果发送了已知密钥，文档将从缓存中获取。每次编辑和保存文档时，都必须重新生成密钥。文档 `url` 可用作键，但不包含特殊字符，长度限制为128个符号。 | string | "Khirz6zTPdfd7" |
| title    | 为已浏览或编辑的文档定义所需的文件名，该文件名也将在下载文档时用作文件名。长度限制为128个符号。 | string | "测试.docx"     |
| url      | 定义存储已查看或编辑的源文档的绝对URL。                      | string |                 |



**示例**

```javascript
var docEditor = new DocsAPI.DocEditor("placeholder", {
    "document": {
        "fileType": "docx",
        "key": "Khirz6zTPdfd7",
        "title": "Example Document Title.docx",
        "url": "https://example.com/url-to-example-document.docx",
    },
    ...
});
```

> key可以使用：0-9、a-z、a-z、-._=。最大密钥长度为20个字符。



#### Info

文档信息部分允许更改文档的附加参数(文档所有者、文档存储的文件夹、上传日期、共享设置)。

| 名字            | 说明                                                         | 类型     | 示例            |
| --------------- | ------------------------------------------------------------ | -------- | --------------- |
| folder          | 定义用于存储文档的文件夹（如果文档存储在根文件夹中，则可以为空）。 | string   | "Example Files" |
| owner           | 定义文档所有者/创建者的名称。                                | string   | "John Smith"    |
| sharingSettings | 显示有关允许与其他用户共享文档的设置的信息：<br>**isLink**--将用户图标更改为链接图标，**type**：boolean，**example**：false。<br>**permissions**--具有上述名称的用户的访问权限。可以是 **Full Access**, **Read Only** 或者 **Deny Access**。**type**：string，**example**："Full Access"。<br>**user**--将与之共享文档的用户的名称。**type**：string，**example**：”John Smith“。 | 对象数组 |                 |

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/sharing_settings.png" alt="img" style="width:60%;" />




| 名字     | 说明               | 类型   | 示例                 |
| -------- | ------------------ | ------ | -------------------- |
| uploaded | 定义文档上传日期。 | string | "2010-07-07 3:46 PM" |

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/info.png" alt="img" style="width:60%;" />



**示例**

```javascript
var docEditor = new DocsAPI.DocEditor("placeholder", {
    "document": {
        "info": {
            "folder": "Example Files",
            "owner": "John Smith",
            "sharingSettings": [
                {
                    "permissions": "Full Access",
                    "user": "John Smith"
                },
                {
                    "isLink": true,
                    "permissions": "Read Only",
                    "user": "External link"
                },
                ...
            ],
            "uploaded": "2010-07-07 3:46 PM"
        },
        ...
    },
    ...
});
```



#### Permissions

文档权限部分允许更改是否要编辑和下载文档的权限。

| 名字    | 说明                                                         | 类型    | 示例 |
| ------- | ------------------------------------------------------------ | ------- | ---- |
| comment | 定义是否可以对文档进行注释。 如果注释权限设置为`true`，则文档侧栏将包含“注释”菜单选项； 只有将`mode`参数设置为`edit`时，文档注释才可用于文档编辑器。 默认值与`edit`参数的值一致。 | boolean | true |

> 如果`edit`设置为`true`，`comment`也设置为`true`，用户将能够编辑文档和注释。如果`edit`设置为`true`，`comment`设置为`false`，用户只能编辑，相应的评论功能只能查看，评论的添加和编辑将不可用。如果`edit`设置为`false`，而`comment`设置为`true`，则文档仅可用于注释。如果`edit`设置为`false`，`review`设置为`false`，`comment`设置为`true`，则不考虑`fillForms`值，表格填写不可用。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/comment.png" alt="img" style="width:35%;" />




| 名字     | 说明                                                         | 类型    | 示例 |
| -------- | ------------------------------------------------------------ | ------- | ---- |
| copy     | 允许您将内容复制到剪贴板。 默认值是true。                    | boolean | true |
| download | 定义是否可以下载文档或仅在线查看或编辑文档。 如果将下载权限设置为“ false”，则“文件”菜单中将不存在“下载为...”菜单选项。 默认值是true。 | boolean | true |
| edit     | 定义文档是可以编辑还是只能查看。 如果将编辑权限设置为“ true”，则“文件”菜单将包含“编辑文档”菜单选项； 请注意，如果将编辑权限设置为“ false”，则将在查看器中打开该文档，即使将`mode`参数设置为`edit`，也将无法将其切换到编辑器。 默认值是true。 | boolean | true |
| print    | 定义是否可以打印文档。 如果将打印许可设置为“ false”，则“文件”菜单中将不存在“打印”菜单选项。 默认值是true。 | boolean | true |

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/permissions.png" alt="img" style="width:60%;" />




| 名字      | 说明                                                         | 类型    | 示例 |
| --------- | ------------------------------------------------------------ | ------- | ---- |
| fillForms | 定义是否可以填写表格。 如果将mode参数设置为edit，则填写表单仅对文档编辑器可用。 默认值与edit或review参数的值一致。 | boolean | true |

> 如果将edit设置为“ true”或将review设置为“ true”，则不考虑fillForms值，并且可以进行表单填充。 如果将edit设置为“ false”，将review设置为“ false”，并且fillForms也设置为“ true”，则用户只能在文档中填写表单。 如果edit设置为“ false”且审阅设置为“ false”且fillForms设置为“ true”，则不考虑注释值，并且注释不可用。 当前仅表单填写模式仅适用于“文档编辑器”。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/fill-forms.png" alt="img" style="width:40%;" />




| 名字                 | 说明                                                         | 类型    | 示例 |
| -------------------- | ------------------------------------------------------------ | ------- | ---- |
| modifyContentControl | 定义是否可以更改内容控件设置。 仅当mode参数设置为edit时，内容控件修改才可用于文档编辑器。 默认值是true。 | boolean | true |
| modifyFilter         | 定义过滤器是否可以全局应用（true）影响所有其他用户，或局部应用（false），即仅适用于当前用户。 如果将mode参数设置为edit，则只能对电子表格编辑器进行过滤器修改。 默认值是true。 |         |      |

> 如果文档由具有完全访问权限的用户编辑，则由该用户应用的过滤器将对所有其他用户可见，而不管其本地设置如何。



| 名字   | 说明                                                         | 类型    | 示例 |
| ------ | ------------------------------------------------------------ | ------- | ---- |
| review | 定义是否可以查看文档。 如果审阅权限设置为true，则文档状态栏将包含“审阅”菜单选项； 如果将mode参数设置为edit，则文档审阅仅对文档编辑器可用。 默认值与edit参数的值一致。 | boolean | true |

> 如果将编辑设置为“ true”，并且审阅也设置为“ true”，则用户将能够编辑文档，接受/拒绝所做的更改并自己切换到审阅模式。 如果编辑设置为“ true”，而审阅设置为“ false”，则用户将只能进行编辑。 如果将编辑设置为“ false”，将审阅设置为“ true”，则该文档仅在审阅模式下可用。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/review.png" alt="img" style="width:33%;" />




**审阅**

```javascript
var docEditor = new DocsAPI.DocEditor("placeholder", {
    "document": {
        "permissions": {
            "comment": true,
            "copy": true,
            "download": true,
            "edit": true,
            "fillForms": true,
            "modifyContentControl": true,
            "modifyFilter": true,
            "print": true,
            "review": true
        },
        ...
    },
    ...
});
```



# 4、故障排除

**下载失败**

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/e-download.png" alt="Download failed" style="width:33%;" />


编辑器加载过程中将显示“下载失败”消息。

文档编辑服务无法上传文件进行编辑。

检查到 `document.url` 中指定的文件的链接是否正确。 必须可以从文档编辑服务访问该链接。



**文件版本变更**

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/e-key.png" alt="The file version has been changed" style="width:50%;" />


编辑器加载“The file version has been changed. The page will be reloaded（文件版本已被更改”。页面将被重新加载）”的消息。

文档编辑服务无法打开以前编辑和保存的文件进行编辑。

每次编辑和保存文档时，必须重新生成 `document.key`。

