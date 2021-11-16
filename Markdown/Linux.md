# 1、Linux 简介

Linux 英文解释为 **Linux is not Unix**。

Linux 内核最初只是由芬兰人林纳斯·托瓦兹（Linus Torvalds）在赫尔辛基大学上学时出于个人爱好而编写的。

Linux 是一套免费使用和自由传播的类 Unix 操作系统，是一个基于 POSIX 和 UNIX 的多用户、多任务、支持多线程和多 CPU 的操作系统。

Linux 能运行主要的 UNIX 工具软件、应用程序和网络协议。它支持 32 位和 64 位硬件。Linux 继承了 Unix 以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。



**Linux 的发行版**

Linux 的发行版说简单点就是将 Linux 内核与应用软件做一个打包。

![img](../Images/Linux/1511849829609658.jpg)

目前市面上较知名的发行版有：Ubuntu、RedHat、CentOS、Debian、Fedora、SuSE、OpenSUSE、Arch Linux、SolusOS 等。

![img](../Images/Linux/wKioL1bvVPWAu7hqAAEyirVUn3c446.jpg-wh_651x-s_3197843091.jpg)

这里用的 Linux 版本是 Ubuntu 20.04 64位。



# 2、系统目录结构

![preview](../Images/Linux/7ebc125005bebe6a6c0a2daa6c4d7f6e_r.jpg)





# 3、远程登录

Linux 一般作为服务器使用，而服务器一般放在机房，你不可能在机房操作你的 Linux 服务器。这时我们就需要远程登录到 Linux 服务器来管理维护系统。Linux 系统中是通过 ssh 服务实现的远程登录功能，默认 ssh 服务端口号为 22。

现在 Windows 10 大都内置了 OpenSSH，如果没有可以去：设置 ==> 应用和功能 ==> 可选功能 中找到 OpenSSH 客户端安装。

打开 CMD 或者 PowerShell 连接 Linux：

```shell
ssh 用户名@IP地址
```



# 4、文件基本属性

Linux 系统是一种典型的多用户系统，不同的用户处于不同的地位，拥有不同的权限。为了保护系统的安全性，Linux 系统对不同的用户访问同一文件（包括目录文件）的权限做了不同的规定。

在 Linux 中我们通常使用以下两个命令来修改文件或目录的所属用户与权限：

- chown (change owner) ： 修改所属用户与组
- chmod (change mode) ： 修改用户的权限

在 Linux 中我们可以使用 `ll` 或者 `ls –l` 命令来显示一个文件的属性以及文件所属的用户和组，如：

```
root@Orichalcos:/# ls -l
total 970036
lrwxrwxrwx   1 root root         7 Sep 22 10:02 bin -> usr/bin
drwxr-xr-x   3 root root      4096 Nov 12 09:27 boot
...
```

实例中，boot 文件的第二个属性用 **d** 表示。**d** 在 Linux 中代表该文件是一个目录文件。

在 Linux 中第一个字符代表这个文件是目录、文件或链接文件等等。

- 当为 `d` 则是目录
- 当为 `-` 则是文件
- 若是 `l` 则表示为链接文档（link file）
- 若是 `b` 则表示为装置文件里面的可供储存的接口设备（可随机存取装置）
- 若是 `c` 则表示为装置文件里面的串行端口设备，例如键盘、鼠标（一次性读取装置）

接下来的字符中，以三个为一组，且均为 `rwx` 的三个参数的组合。其中， `r` 代表可读(read)、 `w` 代表可写(write)、 `x` 代表可执行(execute)。 要注意的是，这三个权限的位置不会改变，如果没有权限，就会出现减号 `-` 而已。

![img](../Images/Linux/file-llls22.jpg)

每个文件的属性由左边第一部分的 10 个字符来确定（如下图）。

![363003_1227493859FdXT](../Images/Linux/363003_1227493859FdXT.png)

从左至右用 **0-9** 这些数字来表示。

第 **0** 位确定文件类型，第 **1-3** 位确定属主（该文件的所有者）拥有该文件的权限；第4-6位确定属组（所有者的同组用户）拥有该文件的权限；第7-9位确定其他用户拥有该文件的权限。

其中，第 **1、4、7** 位表示读权限，如果用 `r` 字符表示，则有读权限，如果用 `-` 字符表示，则没有读权限；第 **2、5、8** 位表示写权限，如果用 `w` 字符表示，则有写权限，如果用 `-` 字符表示没有写权限；第 **3、6、9** 位表示可执行权限，如果用 `x` 字符表示，则有执行权限，如果用 `-` 字符表示，则没有执行权限。



## 4.1、Linux 文件属主和属组

```
[root@www /]# ls -l
total 64
drwxr-xr-x 2 root  root  4096 Feb 15 14:46 cron
drwxr-xr-x 3 mysql mysql 4096 Apr 21  2014 mysql
……
```

对于文件来说，它都有一个特定的所有者，也就是对该文件具有所有权的用户。同时，在 Linux 系统中，用户是按组分类的，一个用户属于一个或多个组。文件所有者以外的用户又可以分为文件所有者的同组用户和其他用户。因此，Linux 系统按文件所有者、文件所有者同组用户和其他用户来规定了不同的文件访问权限。

- 属主：所属的用户，文档所有者，这是一个账户，这是一个人
- 属组：所属的用户组，这是一个组

在以上实例中，mysql 文件是一个目录文件，属主和属组都为 mysql，属主有可读、可写、可执行的权限；与属主同组的其他用户有可读和可执行的权限；其他用户也有可读和可执行的权限。

对于 root 用户来说，一般情况下，文件的权限对其不起作用。



## 4.2、更改文件属性

**chgrp：更改文件属组**

语法：

```shell
chgrp [-R] 属组名 文件名
```

参数选项

- -R：递归更改文件属组，就是在更改某个目录文件的属组时，如果加上 -R 的参数，那么该目录下的所有文件的属组都会更改。



**chown：更改文件属主，也可以同时更改文件属组**

语法：

```shell
chown [–R] 属主名 文件名
chown [-R] 属主名：属组名 文件名
```



**chmod：更改文件9个属性**

Linux文件属性有两种设置方法，一种是数字，一种是符号。

Linux 文件的基本权限就有九个，分别是 **owner/group/others(拥有者/组/其他)** 三种身份各有自己的 **read/write/execute** 权限。

文件的权限字符为： `-rwxrwxrwx` ， 这九个权限是三个三个一组的！其中，我们可以使用数字来代表各个权限，各权限的分数对照表如下：

- r:4
- w:2
- x:1

每种身份（owner/group/others）各自的三个权限（r/w/x）分数是需要累加的，例如当权限为： `-rwxrwx---` 分数则是：

- owner = rwx = 4+2+1 = 7
- group = rwx = 4+2+1 = 7
- others= --- = 0+0+0 = 0

所以等一下设定权限的变更时，该文件的权限数字就是 **770**。变更权限的指令 chmod 的语法是这样的：

```shell
chmod [-R] xyz 文件或目录
```

选项与参数：

- xyz : 就是刚刚提到的数字类型的权限属性，为 rwx 属性数值的相加。
- -R : 进行递归（recursive）的持续变更，亦即连同次目录下的所有文件都会变更。



**符号类型改变文件权限**

还有一个改变权限的方法，从之前的介绍中我们可以发现，基本上就九个权限分别是：

- user：用户
- group：组
- others：其他

那么就可以使用 **u, g, o** 来代表三种身份的权限。此外， **a** 则代表 **all**，即全部的身份。读写的权限可以写成 `r, w, x`，也就是可以使用下表的方式来看：

![image-20211114155622930](../Images/Linux/image-20211114155622930.png)

如果需要将文件权限设置为 **-rwxr-xr--** ，可以使用 `chmod u=rwx,g=rx,o=r 文件名` 来设定:

```
#  touch test1    // 创建 test1 文件
# ls -al test1    // 查看 test1 默认权限
-rw-r--r-- 1 root root 0 Nov 15 10:32 test1
# chmod u=rwx,g=rx,o=r  test1    // 修改 test1 权限
# ls -al test1
-rwxr-xr-- 1 root root 0 Nov 15 10:32 test1
```



# 5、文件与目录管理

Linux的目录结构为树状结构，最顶级的目录为根目录 `/`。其他目录通过挂载可以将它们添加到树中，通过解除挂载可以移除它们。

绝对路径与相对路径。

- **绝对路径：**
	路径的写法，由根目录 `/` 写起，例如： /usr/share/doc 这个目录。
- **相对路径：**
	路径的写法，不是由 `/` 写起，例如由 /usr/share/doc 要到 /usr/share/man 底下时，可以写成： **cd ../man** 这就是相对路径的写法。



## 5.1、处理目录的常用命令

- ls（英文全拼：list files）: 列出目录及文件名
- cd（英文全拼：change directory）：切换目录
- pwd（英文全拼：print work directory）：显示目前的目录
- mkdir（英文全拼：make directory）：创建一个新的目录
- rmdir（英文全拼：remove directory）：删除一个空的目录
- cp（英文全拼：copy file）: 复制文件或目录
- rm（英文全拼：remove）: 删除文件或目录
- mv（英文全拼：move file）: 移动文件与目录，或修改文件与目录的名称

可以使用 `man [命令]` 来查看各个命令的使用文档，如 ：man cp。



**ls（列出目录）**

在Linux系统当中， ls 命令可能是最常被运行的。

语法：

```
[root@www ~]# ls [-aAdfFhilnrRSt] 目录名称
[root@www ~]# ls [--color={never,auto,always}] 目录名称
[root@www ~]# ls [--full-time] 目录名称
```

选项与参数：

- -a ：全部的文件，连同隐藏文件（开头为 . 的文件）一起列出来（常用）
- -d ：仅列出目录本身，而不是列出目录内的文件数据（常用）
- -l ：长数据串列出，包含文件的属性与权限等等数据（常用）

将家目录下的所有文件列出来（含属性与隐藏档）

```shell
ls -al ~
```



**cd（切换目录）**

cd 是 Change Directory 的缩写，这是用来变换工作目录的命令。

语法：

```shell
 cd [相对路径或绝对路径]
```

```
#使用 mkdir 命令创建 runoob 目录
[root@www ~]# mkdir runoob

#使用绝对路径切换到 runoob 目录
[root@www ~]# cd /root/runoob/

#使用相对路径切换到 runoob 目录
[root@www ~]# cd ./runoob/

# 表示回到自己的家目录，亦即是 /root 这个目录
[root@www runoob]# cd ~

# 表示去到目前的上一级目录，亦即是 /root 的上一级目录的意思；
[root@www ~]# cd ..
```



**pwd（显示目前所在的目录）**

pwd 是 Print Working Directory 的缩写，也就是显示目前所在目录的命令。

语法：

```shell
pwd [-P]
```

选项与参数：

- -P ：显示出确实的路径，而非使用连结（link）路径

单纯显示出目前的工作目录：

```
[root@www ~]# pwd
/root   <== 显示出目录啦～
```

显示出实际的工作目录，而非连结档本身的目录名：

```
[root@www ~]# cd /var/mail   <==注意，/var/mail是一个连结档
[root@www mail]# pwd
/var/mail         <==列出目前的工作目录
[root@www mail]# pwd -P
/var/spool/mail   <==怎么回事？有没有加 -P 差很多～
[root@www mail]# ls -ld /var/mail
lrwxrwxrwx 1 root root 10 Sep  4 17:54 /var/mail -> spool/mail
# 看到这里应该知道为啥了吧？因为 /var/mail 是连结档，连结到 /var/spool/mail 
# 所以，加上 pwd -P 的选项后，会不以连结档的数据显示，而是显示正确的完整路径啊！
```



**rmdir（删除空的目录）**

语法：

```shell
 rmdir [-p] 目录名称
```

选项与参数：

- -p ：从该目录起，一次删除多级空目录

将 test/test1/test2 删除掉：

```
root@Orichalcos:~# ls -ld test/test1/test2
drwxr-xr-x 2 root root 4096 Nov 15 16:27 test/test1/test2
root@Orichalcos:~# rmdir test
rmdir: failed to remove 'test': Directory not empty
root@Orichalcos:~# rmdir -p test/test1/test2
root@Orichalcos:~# ls -ld test/test1/test2
ls: cannot access 'test/test1/test2': No such file or directory
root@Orichalcos:~#
```



**cp（复制文件或目录）**

cp 即拷贝文件和目录。

语法:

```
[root@www ~]# cp [-adfilprsu] 来源档(source) 目标档(destination)
[root@www ~]# cp [options] source1 source2 source3 .... directory
```

选项与参数：

- -a：相当於 -pdr 的意思，至于 pdr 请参考下列说明（常用）
- -d：若来源档为连结档的属性（link file），则复制连结档属性而非文件本身
- -f：为强制（force）的意思，若目标文件已经存在且无法开启，则移除后再尝试一次
- -i：若目标档（destination）已经存在时，在覆盖时会先询问动作的进行（常用）
- -l：进行硬式连结（hard link）的连结档创建，而非复制文件本身
- -p：连同文件的属性一起复制过去，而非使用默认属性（备份常用）
- -r：递归持续复制，用於目录的复制行为（常用）
- -s：复制成为符号连结档（symbolic link），亦即『捷径』文件
- -u：若 destination 比 source 旧才升级 destination ！



**rm（移除文件或目录）**

语法：

```shell
 rm [-fir] 文件或目录
```

选项与参数：

- -f ：就是 force 的意思，忽略不存在的文件，不会出现警告信息
- -i ：互动模式，在删除前会询问使用者是否动作
- -r ：递归删除啊！最常用在目录的删除了！这是非常危险的选项！！！



**mv（移动文件与目录，或修改名称）**

语法：

```
[root@www ~]# mv [-fiu] source destination
[root@www ~]# mv [options] source1 source2 source3 .... directory
```

选项与参数：

- -f ：force 强制的意思，如果目标文件已经存在，不会询问而直接覆盖
- -i ：若目标文件（destination）已经存在时，就会询问是否覆盖
- -u ：若目标文件已经存在，且 source 比较新，才会升级（update）



## 5.2、文件内容查看

Linux 系统中使用以下命令来查看文件的内容：

- cat 由第一行开始显示文件内容
- tac 从最后一行开始显示，可以看出 tac 是 cat 的倒着写！
- nl  显示的时候，顺道输出行号！
- more 一页一页的显示文件内容
- less 与 more 类似，但是比 more 更好的是，他可以往前翻页！
- head 只看头几行
- tail 只看尾巴几行

可以使用 `man [命令]`来查看各个命令的使用文档，如 ：man cp。



**cat**

由第一行开始显示文件内容。

语法：

```shell
cat [-AbEnTv] 文件
```

选项与参数：

- -A ：相当於 -vET 的整合选项，可列出一些特殊字符而不是空白而已
- -b ：列出行号，仅针对非空白行做行号显示，空白行不标行号！
- -E ：将结尾的断行字节 $ 显示出来
- -n ：列印出行号，连同空白行也会有行号，与 -b 的选项不同
- -T ：将 [tab] 按键以 ^I 显示出来
- -v ：列出一些看不出来的特殊字符



**tac**

tac 与 cat 命令刚好相反，文件内容从最后一行开始显示，可以看出 tac 是 cat 的倒着写！



**nl**

显示行号。

语法：

```shell
nl [-bnw] 文件
```

选项与参数：

- -b ：指定行号指定的方式，主要有两种：
	- -b a ：表示不论是否为空行，也同样列出行号（类似 cat -n）
	- -b t ：如果有空行，空的那一行不要列出行号(默认值)
- -n ：列出行号表示的方法，主要有三种：
	- -n ln ：行号在荧幕的最左方显示
	- -n rn ：行号在自己栏位的最右方显示，且不加 0 
	- -n rz ：行号在自己栏位的最右方显示，且加 0 
- -w ：行号栏位的占用的位数



**more**

一页一页翻动。

语法：

```shell
more 文件
```

在 more 这个程序的运行过程中，你有几个按键可以按的：

- 空白键（space）：代表向下翻一页
- Enter     ：代表向下翻『一行』
- /字串     ：代表在这个显示的内容当中，向下搜寻『字串』这个关键字
- :f      ：立刻显示出档名以及目前显示的行数
- q       ：代表立刻离开 more ，不再显示该文件内容
- b 或 [ctrl]-b ：代表往回翻页，不过这动作只对文件有用，对管线无用



**less**

一页一页翻动。

less 运行时可以输入的命令有：

- 空白键  ：向下翻动一页
- [pagedown]：向下翻动一页
- [pageup] ：向上翻动一页
- /字串   ：向下搜寻『字串』的功能
- ?字串   ：向上搜寻『字串』的功能
- n     ：重复前一个搜寻（与 / 或 ? 有关！）
- N     ：反向的重复前一个搜寻（与 / 或 ? 有关！）
- q     ：离开 less 这个程序



**head**

取出文件前面几行。默认的情况中，显示前面 10 行！

语法：

```shell
head [-n number] 文件 
```

选项与参数：

- -n ：后面接数字，代表显示几行的意思



**tail**

取出文件后面几行。默认的情况中，显示最后的十行！

语法：

```shell
tail [-n number] 文件 
```

选项与参数：

- -n：后面接数字，代表显示几行的意思
- -f：表示持续侦测后面所接的档名，要等到按下 [ctrl]-c 才会结束 tail 的侦测



# 6、用户和用户组管理

Linux 系统是一个多用户多任务的分时操作系统，任何一个要使用系统资源的用户，都必须首先向系统管理员申请一个账号，然后以这个账号的身份进入系统。用户的账号一方面可以帮助系统管理员对使用系统的用户进行跟踪，并控制他们对系统资源的访问；另一方面也可以帮助用户组织文件，并为用户提供安全性保护。

每个用户账号都拥有一个唯一的用户名和各自的口令。用户在登录时键入正确的用户名和口令后，就能够进入系统和自己的主目录。

实现用户账号的管理，要完成的工作主要有如下几个方面：

- 用户账号的添加、删除与修改
- 用户口令的管理
- 用户组的管理



## 6.1、系统用户账号的管理

用户账号的管理工作主要涉及到用户账号的添加、修改和删除。



### 6.1.1、添加账户

添加用户账号就是在系统中创建一个新账号，然后为新账号分配用户号、用户组、主目录和登录Shell等资源。刚添加的账号是被锁定的，无法使用。

添加新的用户账号使用 `useradd` 命令，其语法如下：

```shell
useradd 选项 用户名
```

参数说明：

- 选项：

	- -c comment：指定一段注释性描述
	- -d 目录：指定用户主目录，如果此目录不存在，则同时使用 -m 选项，可以创建主目录
	- -g 用户组：指定用户所属的用户组
	- -G 用户组,用户组：指定用户所属的附加组
	- -s Shell文件：指定用户的登录 Shell
	- -u 用户号：指定用户的用户号，如果同时有 -o 选项，则可以重复使用其他用户的标识号

- 用户名：

	指定新账号的登录名。

**实例1：**

```shell
useradd –d /home/sam -m sam
```

此命令创建了一个用户 sam，其中 -d 和 -m 选项用来为登录名 sam 产生一个主目录 /home/sam（/home 为默认的用户主目录所在的父目录）。

**实例2：**

```shell
useradd -s /bin/sh -g group –G adm,root gem
```

此命令新建了一个用户 gem，该用户的登录Shell是 /bin/sh，它属于 group 用户组，同时又属于 adm 和 root 用户组，其中 group 用户组是其主组。这里可能新建组：`#groupadd group 及 groupadd adm`

增加用户账号就是在 /etc/passwd 文件中为新用户增加一条记录，同时更新其他系统文件如 /etc/shadow, /etc/group 等。

Linux 提供了集成的系统管理工具 userconf，它可以用来对用户账号进行统一管理。



### 6.1.2、删除账户

如果一个用户的账号不再使用，可以从系统中删除。删除用户账号就是要将 /etc/passwd 等系统文件中的该用户记录删除，必要时还删除用户的主目录。

删除一个已有的用户账号使用`userdel`命令，其格式如下：

```shell
userdel 选项 用户名
```

常用的选项是 -r，它的作用是把用户的主目录一起删除。

**实例：**

```shell
userdel -r sam
```

此命令删除用户 sam 在系统文件中（主要是/etc/passwd, /etc/shadow, /etc/group等）的记录，同时删除用户的主目录。
