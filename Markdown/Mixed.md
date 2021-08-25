# Windows

## 【1】Windows默认管理员权限

1. 按下 Win+R 快捷键唤出 “运行” 窗口，输入 gpedit.msc
2. 这时打开了 组策略编辑器，在左边找到【计算机配置】 =>【Windows设置】，再进入右边【安全设置】
3.  进入【本地策略】
4. 进入【安全选项】
5. 向下滑，找到
	-  “用户账户控制：以管理员批准模式运行所有管理员”
	- “用户账户控制：用于内置管理员账户的管理员批准模式”
6. 分别选中并点击鼠标右键，再点击【属性】，进入配置窗口，将这两项都分别设置为 “已禁用”，再点击 【确定】
7. 重启电脑，操作完成！



## 【2】重装系统时将硬盘格式转换为GPT

1. 按 shift 键和 F10 键，启动 cmd 命令行模式
2. 在命令行窗口中输入 `diskpart` 命令并回车，进入 Diskpart 模式
3. 输入 `list disk` 命令并回车，确认电脑硬盘数量
4. 继续输入 `select disk 0` 命令并回车，选择 disk0 的硬盘（C盘为disk0，视情况而定）
5.  继续输入 `clean` 命令并回车，清除 disk0 硬盘中所有的分区和数据
6. 继续 `convert GPT` 并回车，将硬盘格式从 MBR 转换成 GPT
7. 最后输入 `exit` 命令并回车，退出 Diskpart 模式
8. 再输入 `exit` 命令并回车，退出 cmd 命令行模式



## 【3】修改系统引导

1. win+R
2. 输入 `msconfig`



## 【4】查看后台运行的程序的详细信息

cmd 命令窗口输入 `netstat –ano`，回车。

可以解决端口占用



## 【5】Compact 压缩功能命令

它可以对所有的系统文件进行压缩，实现磁盘压缩，节约硬盘空间。

开启：cmd命令窗口输入 `compact /compactos:always`，回车。

关闭：`compact /compactos:never`



## 【6】删除右键菜单新建中不要选项

1. 按下 Win+R，运行 `regedit`
2. 展开 HKEY_CLASSES_ROOT，找到需要删除的文件后缀名，然后展开文件夹找到 shellnew 选项，直接删除即可



## 【7】取消开机密码

使用微软账户登录：

1. 确保 账户 => 登录选项 => 其他设置中 “为了提高安全性，仅允许...” 是关闭的：
	![image-20210813000252683](../Images/Mixed/image-20210813000252683.png)
2. 按 Win + R，弹出 ”运行“，输入`netplwiz`，点【确定】
3. 取消 “要使用本计算机，用户必须输入用户名和密码”，点【确定 】
4. 会弹出一个窗口，在窗口中输入 微软账户的用户名和密码，点【确定】

<img src="../Images/Mixed/image-20210513005900008.png" alt="image-20210513005900008" style="zoom: 67%;" />



## 【8】微软输入法快速输入时间

1. 按下快捷键 Win + I，打开【设置】

2. 依次进入：【时间和语言】 => 【语言 】

3. 找到【首选语言】，点击首选语言（中文简体，中国）的【选项】

4. 找到【键盘】，点击首选键盘（微软输入法）的【选项】

5. 选择【词库和自学习】，打开【用户定义的短语】，点击【添加用户定义的短语】

6. 添加：

   ![image-20210624233318170](../Images/Mixed/image-20210624233318170.png)

   ```
   # 2020-11-29 16:21:29
   %yyyy%-%MM%-%dd% %HH%:%mm%:%ss%
   
   # 2020年11月29日 16:21:29
   %yyyy%年%MM%月%dd%日 %HH%:%mm%:%ss%
   ```



## 【9】Windows Terminal SSH 保持连接

Windows 10 全新的 Powershell 内置了 ssh.exe 因此可以直接连接远程服务器，在 Windows Terminal 中连接 SSH 中若一段时间没有操作会导致连接断开，终端卡死。

常见的终端工具（比如：Xshell）等都会内置 keepalive 功能，自动会发送心跳包来保持连接，但是 Windows Terminal 没有自带此功能。

解决办法：在当前用户目录下创建 .ssh 文件夹，或者可以尝试连接 SSH 随意连接一个服务器即可自动创建此文件夹。在其中创建配置文件 config ，写入以下两行：

```
Host *
    ServerAliveInterval 40
```



## 【10】删除桌面右键 Open in Terminal

按 win 键+ R 键，打开 “运行” 窗口，输入  `regedit`，按回车键，弹出 注册表编辑器。

在注册表`HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Shell Extensions\`处右键创建名为 `Blocked` 的**项**。

然后在其中创建字**符串值**，**名称**为 `{9F156763-7844-4DC4-B2B1-901F640F5155}`，**数值**为 `WindowsTerminal`。

![image-20210806112305321](../Images/Mixed/image-20210806112305321.png)



# Chrome

## 【1】移动Chrome的数据文件

Chrome 默认的数据文件地址是：C:\Users\Orichalcos\AppData\Local\Google

移动前需要关闭 Google，将文件移动到想要移动的地方，然后在 cmd（需要管理员权限）输入：mklink 旧地址 新地址，回车。



# IDEA

## 【1】Maven 部分文件无法导出

maven 由于他的约定大于配置，之后可能遇到写的配置文件，无法被导出或者生效的问题

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```



## 【2】Maven 框架 web-app 中 web.xml 版本过低

1. 找到 maven-archetype-webapp 的 jar 包位置：maven位置\repository\org\apache\maven\archetypes\maven-archetype-webapp\1.4。
2. 用压缩包形式打开，不要解压！
3. 然后依次点，archetype-resources\src\main\webapp\WEB-INF\web.xml，打开，将其修改成最新的模板。

PS：上边的web-app标签中有一个**metadata-complete="true"**，这是个大坑，因为web-app标签3.0以上版本是可以使用servlet的注解的，如下图：

<img src="../Images/Mixed/1247983-20190604213809759-1772243544.png" alt="img" style="zoom:67%;float:left" />

再也不用去web.xml写那servlet的那一堆映射了（下图就是那一堆映射）

<img src="../Images/Mixed/1247983-20190604214818782-1161183865.png" alt="img" style="zoom:67%;float:left" />

如果忘记了改这个web-app标签里的metadata-complete的这个属性，所写的注解都将失效！！！

该属性默认为true，表示容器在部署时只依赖部署描述文件，忽略所有标注，如果不配置该属性，或者将其设置为false，则表示启动注解支持。当metadata-complete="false"时，web.xml和注解对于Servlet的影响同时起作用，两种方法定义的url-partten都可以访问到该Servlet，但是当通过web.xml定义的url-partten访问时，注解定义的属性将失效。



## 【3】修改内存大小

首先在IDEA中显示内存：在窗口下方右键，选中 Memory Indicator

<img src="../Images/Mixed/image-20200428211739758.png" alt="image-20200428211739758" style="zoom:67%;float:left" />

帮助 => 编辑自定义VM选项。

在弹出来的文件中修改Xms和Xmx：

<img src="../Images/Mixed/image-20200428212406493.png" alt="image-20200428212406493" style="zoom:67%;float:left" />



## 【4】SpringBoot 实现热部署

![img](../Images/Mixed/1676221-20200430155320785-521416484.png)

在application.yml中配置一下devtools

```yaml
spring:
    devtools:
        restart:
            enabled: true  #设置开启热部署
            additional-paths: src/main/java #重启目录
            exclude: WEB-INF/**
    freemarker:
        cache: false    #页面不加载缓存，修改即时生效

```

```xml
<!--
devtools工具:（说白了就是:例如在使用用myeclipse工具，服务在运行时，修改了java类内的内容，直接就生效了，不需要重启服务（当然改变了类的结构是需要重启服务的））
（1） devtools可以实现页面热部署（即jsp页面修改后会立即生效，这个可以直接在application.properties文件中配置spring.thymeleaf.cache=false来实现），
      实现类文件热部署（java类文件修改后不会立即生效），实现对属性文件的热部署。(java类热部署前提条件：类的结构不发生变化（1.类方法结构不变 2.类属性不变）)
即devtools会监听classpath下的文件变动，并且会立即重启应用（发生在保存时机），注意：因为其采用的虚拟机机制，该项重启是很快的
（2）配置了后在修改java文件后也就支持了热启动，不过这种方式是属于项目重启（速度比较快的项目重启），会清空session中的值，也就是如果有用户登陆的话，项目重启后需要重新登陆。
默认情况下，/META-INF/maven，/META-INF/resources，/resources，/static，/templates，/public这些文件夹下的文件修改不会使应用重启，但是会重新加载（devtools内嵌了一个LiveReload server，当资源发生改变时，浏览器刷新）

IDEA：当我们修改了Java类后，IDEA默认是不自动编译的，而spring-boot-devtools又是监测classpath下的文件发生变化才会重启应用，所以需要设置IDEA的自动编译：
（1）File-Settings-Build, execute, deploy-Compiler-Build Project automatically
（2）ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running
-->
```



## 【5】Maven 项目没有被识别

在 pom.xml 上右键、点击 Add as Maven Project 



## 【6】插件：camelCase

下划线 <=> 驼峰，你是怎么转换的，一个一个字母的修改吗？

camelCase 可以用 一个快捷键 就搞定，去 IDEA 的插件库中搜索 camelCase 插件，安装上。

使用方式：先选中要要格式转换的代码， 再用快捷键 Shift + Alt + u 进行转换，多个格式切换，就按多次快捷键。

camelCase 包含 6 种格式的切换，可能某些格式是平时不用的，可以把不用的格式取消，这样在格式切换时，就不会包含已取消的格式了：

![图片描述](../Images/Mixed/5e7827dc0001c7ea11720724.png)



## 【7】IDEA  jar 包自动导入及优化

加快开发效率，除去没用的包，洁癖者必用!   这样设置，就可以自动导入包以及除去没有用到的包！

![img](../Images/Mixed/1100499-20180530104314466-1984750519.png)

第一个是 自动导入需要的 jar 包，若有多个同名 jar 包，需要开发者自己选择导入

第二个是 优化导入，也就是除去没有用到的 jar 包，这个设置只会对当前的项目有效！每个项目都需要单独设置 此选项！



# STS

## 【1】修改内存大小

显示内存：首选项 => 常规 => 显示堆状态 。

在安装目录找到SpringToolSuite4.ini，修改Xms Xmx。

然后在Java => 已安装的JRE中，修改缺省VM参数，添加Xms和Xmx，中间用空格隔开。

<img src="../Images/Mixed/image-20200428213849950.png" alt="image-20200428213849950" style="zoom: 50%;float:left" />



## 【2】汉化

<img src="../Images/Mixed/image-20200622113126300.png" alt="image-20200622113126300" style="zoom:80%;float:left" />

打开 https://www.eclipse.org/babel/downloads.php 

<img src="../Images/Mixed/image-20200622113248801.png" alt="image-20200622113248801" style="zoom: 50%;float:left" />

选择一个复制，在弹出的窗口中点击添加

<img src="../Images/Mixed/image-20200622113354699.png" alt="image-20200622113354699" style="zoom: 50%;float:left" />

找到中文简体安装就行了。



## 【3】SpringBoot 打 jar 包

SpringBoot是使用内置的tomcat的，所以不用打包成war文件，当然也可以打包成war文件进行部署，只是个人觉得没有那个必要，通过maven可将SpringBoot项目打包成jar文件运行。

1. pom.xml文件中添加所需插件

	```xml
	<plugins>
	    <!-- 要使生成的jar可运行，需要加入此插件 -->
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-surefire-plugin</artifactId>
			<configuration>
			<skip>true</skip>
			</configuration>
		</plugin>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
	```

2. 项目运行环境选择java jdk
	<img src="../Images/Mixed/20191105110853305.png" alt="img" style="zoom:67%;" />

3. 执行maven clean
	**右键项目 run as 选择maven clean**

4. 执行maven install
	**右键项目 run as 选择maven install**

5. 运行jar
	**命令行运行： java -jar xxx.jar**