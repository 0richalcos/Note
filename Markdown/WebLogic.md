---
typora-copy-images-to: upload
---

# 1、简介

WebLogic 是美国 Oracle 公司出品的一个 application server，确切的说是一个基于 JAVAEE 架构的中间件，WebLogic 是用于开发、集成、部署和管理大型分布式 Web 应用、网络应用和数据库应用的 Java 应用服务器。将 Java 的动态功能和 Java Enterprise 标准的安全性引入大型网络应用的开发、集成、部署和管理之中。



## 1.1、下载介质

访问：https://www.oracle.com/cn/middleware/technologies/weblogic-server-downloads.html，在页面中找到 `Oracle WebLogic Server 12.2.1.4`，点击 `Generic` 下载。

![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095506916-1584814676.png)



## 1.2、安装

1. 安装前请安装 JDK1.8，安装路径不能有空格

2. 将下载后的 ZIP 包解压

   解压后为安装包和 readme，readme 中有版本说明、安装手册等

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095517481-334808979.png)

3. 解压安装包（jar 包）

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095539807-1391224409.png)

4. 进入 `Disk1\install`

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095552529-1487676700.png)

5. 找到文件 `ng.cmd`，鼠标右键，点击以管理员身份运行，弹出控制台，稍后则会出现安装界面

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095620711-406017220.png)

6. 安装

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095629573-1539117900.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095634576-659549188.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095640776-1308382125.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095645436-1147065726.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095650819-1257919969.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095658330-1520671133.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095705310-537446202.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095710158-1886335348.png)

7. 配置

   如果在安装最后一步，没有勾选自动启动配置项向导，则需要在安装目录一次找到 `Oracle\Middleware\Oracle_Home\oracle_common\common\bin\config.cmd`，双击即可打开配置向导。

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095825359-1267580425.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095832141-1098880837.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095841393-940125565.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095847137-302705788.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095915953-260996187.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095920519-1131564734.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095925940-723661005.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095944242-114285994.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095952319-1556892709.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805095957959-1388745340.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100002564-1551700284.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100006944-1873116894.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100014428-465651627.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100020165-667155737.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100026565-460038184.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100031798-2001912021.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100037444-2012661630.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100042679-575953920.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100050624-1448488874.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100056454-697515979.png)



## 1.3、启动

在安装目录依次找到 `Oracle\Middleware\Oracle_Home\user_projects\domains\base_domain\bin\startWebLogic.cmd`，双击启动。

![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100137637-723411073.png)

浏览器输入：http://127.0.0.1:7001/console

![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100224073-1606451984.png)

![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805100148244-952036088.png)



## 1.4、卸载

在开始中找到 `Uninstall OracleHome1` 快捷方式，或者在 C 盘搜索到 `Uninstall OracleHome1` 快捷方式位置，点击即可卸载。

![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805101038077-310325990.png)

![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1119097-20210805101045461-1913711451.png)



# 2、部署项目

## 2.1、部署 web 项目（war包）

1. 启动并访问 Weblogic，进入登录页面：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306162126594-39703724.png)

2. 进入主页面，开始部署项目：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306162231545-698664955.png)

3. 上载项目 war 包：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306162337244-1709328388.png)

   选择需要上载的本地 war 包：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306162450722-952168594.png)

4. 开始项目配置：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306162650116-1150502665.png)

   继续【下一步】：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306162750298-2077098431.png)

   选择红色标记的配置：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306162929219-210649233.png)

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163010472-1944122109.png)

5. 完成配置：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163130861-286537828.png)

6. 激活配置：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163220376-548157725.png)

7. 开始配置系统环境：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163343368-2147326009.png)

8. 进入【AdminServer（管理）】：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163505382-510409227.png)

9. 选择【协议】，然后选择【HTTP】：

   ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163613423-725849867.png)

10. 配置 HTTP 协议中的信息，没用红框标记的保持默认就行了，配置完成后再次点击左上角的【激活更改】：

    ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163720539-688081214.png)

11. 最后，再次点击【部署】：

    ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163834622-1380453145.png)

    ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306163949387-1806740500.png)

12. 启动部署项目：

    ![img](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/1303698-20180306164039223-1396985426.png)

13. 然后就可以访问项目了，链接格式为：http://IP:PORT/ProjectName

    例：http://172.150.13.24:7001/jenkins_webdemo



## 2.2、删除自动部署项目

1. 从域的 autodeploy 目录下删除相应的文件夹或者 war 文件。

   如我的相应文件夹是 `D:\Oracle\Middleware\user_projects\domains\base_domain\autodeploy`。

2. 从 WebLogic 配置文件中删除相应的元素，否则在管理员页面中还将看到那些你已经删除了的项目：

   `D:\Oracle\Middleware\user_projects\domains\base_domain\config\config.xml` 中的对应的 `<app-deployment>` 元素。

3. 从 `D:\Oracle\Middleware\user_projects\domains\base_domain\servers\AdminServer\stage` 目录下删除相应的项目。



# 3、WebLogic 配置

## 3.1、修改 JVM 堆大小

您可以更改默认的 JVM 堆大小以适应部署的需要。

WebLogic 的默认 JVM 堆大小为 3GB。该大小是在 Linux 的 `setDomainEnv.sh` 文件或 Windows 的 `setDomainEnv.cmd` 文件中设置的，该文件位于 `$DOMAIN_HOME/bin` 目录中。堆大小是用 `-Xmx` 选项设置的。

更改 WebLogic JVM 堆大小：

1. 在文本编辑器中打开 setDomainEnv 文件。

2. 搜索这个注释行：

   对于 Linux：

   ```sh
   # IF USER_MEM_ARGS the environment variable is set, use it to override ALL MEM_ARGS values
   ```

   对于 Windows：

   ```sh
   @REM IF USER_MEM_ARGS the environment variable is set, use it to override ALL MEM_ARGS values
   ```

3. 在注释行之后，添加以下一行：

   对于 Linux：

   ```sh
   export USER_MEM_ARGS="-Xms128m -Xmx3072m ${MEM_DEV_ARGS} ${MEM_MAX_PERM_SIZE}"
   ```

   对于 Windows：

   ```sh
   set USER_MEM_ARGS=-Xms128m -Xmx3072m %MEM_DEV_ARGS% %MEM_MAX_PERM_SIZE%
   ```

4. 保存文件。

5. 重新启动 WebLogic 服务器。



## 3.2、修改的端口 

两种方法可以修改，第一种方法是后台管理界面修改，第二种是配置文件修改。



**后台修改**

1. 进入 WebLogic 登陆界面：

   ![image-20230829172847335](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230829172847335.png)

2. 登录之后找到【环境】=>【服务器】：

   ![image-20230829173017666](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230829173017666.png)

3. 点击管理项目的服务器超链接：

   ![image-20230829173137105](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230829173137105.png)

4. 点击【锁定并编辑】，修改监听端口后保存即可：

   ![image-20230829173236833](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230829173236833.png)

   此种方式不需要重启。



**配置文件修改方式**

修改 `%weblogic%\user_projects\domains\base_domain\config\config.xml` 的 `listen-port` 节点，大概在 55 行：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230829173614635.png" alt="image-20230829173614635" style="zoom: 67%;" />

此种方式需要重启。



## 3.3、修改控制台路径

**配置文件修改方式**

修改 `%weblogic%\user_projects\domains\base_domain\config\config.xml` 的 `console-context-path` 节点 ，大概在 46 行：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/QQ_1734293261850.png" alt="QQ_1734293261850" style="zoom:80%;" />

此种方式需要重启。

> [!WARNING]
>
> 如果是多级目录，不要使用 `/` 开头，不然会无法登录控制台！！！

