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

实例中，boot 文件的第一个属性用 **d** 表示。**d** 在 Linux 中代表该文件是一个目录文件。

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

- `-R`：递归更改文件属组，就是在更改某个目录文件的属组时，如果加上 `-R` 的参数，那么该目录下的所有文件的属组都会更改。



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

- `xyz` : 就是刚刚提到的数字类型的权限属性，为 rwx 属性数值的相加。
- `-R` : 进行递归（recursive）的持续变更，亦即连同次目录下的所有文件都会变更。



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
	路径的写法，由根目录 `/` 写起，例如：` /usr/share/doc` 这个目录。
- **相对路径：**
	路径的写法，不是由 `/` 写起，例如由 `/usr/share/doc` 要到 `/usr/share/man` 底下时，可以写成： `cd ../man` 这就是相对路径的写法。



## 5.1、处理目录的常用命令

- `ls`（英文全拼：list files）: 列出目录及文件名
- `cd`（英文全拼：change directory）：切换目录
- `pwd`（英文全拼：print work directory）：显示目前的目录
- `mkdir`（英文全拼：make directory）：创建一个新的目录
- `rmdir`（英文全拼：remove directory）：删除一个空的目录
- `cp`（英文全拼：copy file）: 复制文件或目录
- `rm`（英文全拼：remove）: 删除文件或目录
- `mv`（英文全拼：move file）: 移动文件与目录，或修改文件与目录的名称

可以使用 `man [命令]` 来查看各个命令的使用文档，如 ：`man cp`。



**ls（列出目录）**

在Linux系统当中，`ls` 命令可能是最常被运行的。

语法：

```
[root@www ~]# ls [-aAdfFhilnrRSt] 目录名称
[root@www ~]# ls [--color={never,auto,always}] 目录名称
[root@www ~]# ls [--full-time] 目录名称
```

选项与参数：

- `-a` ：全部的文件，连同隐藏文件（开头为 . 的文件）一起列出来（常用）
- `-d` ：仅列出目录本身，而不是列出目录内的文件数据（常用）
- `-l` ：长数据串列出，包含文件的属性与权限等等数据（常用）

将家目录下的所有文件列出来（含属性与隐藏档）

```shell
ls -al ~
```



**cd（切换目录）**

`cd` 是 Change Directory 的缩写，这是用来变换工作目录的命令。

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

`pwd` 是 Print Working Directory 的缩写，也就是显示目前所在目录的命令。

语法：

```shell
pwd [-P]
```

选项与参数：

- `-P` ：显示出确实的路径，而非使用连结（link）路径

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

- `-p` ：从该目录起，一次删除多级空目录

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

`cp` 即拷贝文件和目录。

语法:

```
[root@www ~]# cp [-adfilprsu] 来源档(source) 目标档(destination)
[root@www ~]# cp [options] source1 source2 source3 .... directory
```

选项与参数：

- `-a`：相当於 -pdr 的意思，至于 pdr 请参考下列说明（常用）
- `-d`：若来源档为连结档的属性（link file），则复制连结档属性而非文件本身
- `-f`：为强制（force）的意思，若目标文件已经存在且无法开启，则移除后再尝试一次
- `-i`：若目标档（destination）已经存在时，在覆盖时会先询问动作的进行（常用）
- `-l`：进行硬式连结（hard link）的连结档创建，而非复制文件本身
- `-p`：连同文件的属性一起复制过去，而非使用默认属性（备份常用）
- `-r`：递归持续复制，用於目录的复制行为（常用）
- `-s`：复制成为符号连结档（symbolic link），亦即『捷径』文件
- `-u`：若 destination 比 source 旧才升级 destination ！



**rm（移除文件或目录）**

语法：

```shell
 rm [-fir] 文件或目录
```

选项与参数：

- `-f` ：就是 force 的意思，忽略不存在的文件，不会出现警告信息
- `-i` ：互动模式，在删除前会询问使用者是否动作
- `-r` ：递归删除啊！最常用在目录的删除了！这是非常危险的选项！！！



**mv（移动文件与目录，或修改名称）**

语法：

```
[root@www ~]# mv [-fiu] source destination
[root@www ~]# mv [options] source1 source2 source3 .... directory
```

选项与参数：

- `-f `：force 强制的意思，如果目标文件已经存在，不会询问而直接覆盖
- `-i` ：若目标文件（destination）已经存在时，就会询问是否覆盖
- `-u` ：若目标文件已经存在，且 source 比较新，才会升级（update）



## 5.2、文件内容查看

Linux 系统中使用以下命令来查看文件的内容：

- `cat` 由第一行开始显示文件内容
- `tac` 从最后一行开始显示，可以看出 tac 是 cat 的倒着写！
- `nl`  显示的时候，顺道输出行号！
- `more` 一页一页的显示文件内容
- `less` 与 `more` 类似，但是比 `more` 更好的是，他可以往前翻页！
- `head` 只看头几行
- `tail` 只看尾巴几行

可以使用 `man [命令]`来查看各个命令的使用文档，如 ：`man cp`。



**cat**

由第一行开始显示文件内容。

语法：

```shell
cat [-AbEnTv] 文件
```

选项与参数：

- `-A `：相当於 -vET 的整合选项，可列出一些特殊字符而不是空白而已
- `-b` ：列出行号，仅针对非空白行做行号显示，空白行不标行号！
- `-E` ：将结尾的断行字节 $ 显示出来
- `-n` ：列出行号，连同空白行也会有行号，与 `-b` 的选项不同
- `-T` ：将 [tab] 按键以 ^I 显示出来
- `-v `：列出一些看不出来的特殊字符



**tac**

`tac` 与 `cat` 命令刚好相反，文件内容从最后一行开始显示，可以看出 `tac` 是 `cat` 的倒着写！

 

**nl**

显示行号。

语法：

```shell
nl [-bnw] 文件
```

选项与参数：

- `-b` ：指定行号指定的方式，主要有两种：
	- `-b a` ：表示不论是否为空行，也同样列出行号（类似 `cat -n`）
	- `-b t` ：如果有空行，空的那一行不要列出行号(默认值)
- `-n` ：列出行号表示的方法，主要有三种：
	- `-n ln` ：行号在荧幕的最左方显示
	- `-n rn` ：行号在自己栏位的最右方显示，且不加 0 
	- `-n rz` ：行号在自己栏位的最右方显示，且加 0 
- `-w` ：行号栏位的占用的位数



**more**

一页一页翻动。

语法：

```shell
more 文件
```

在 `more` 这个程序的运行过程中，你有几个按键可以按的：

- 空白键（space）：代表向下翻一页
- Enter     ：代表向下翻『一行』
- /字串     ：代表在这个显示的内容当中，向下搜寻『字串』这个关键字
- :f      ：立刻显示出档名以及目前显示的行数
- q       ：代表立刻离开 more ，不再显示该文件内容
- b 或 [ctrl]-b ：代表往回翻页，不过这动作只对文件有用，对管线无用



**less**

一页一页翻动。

`less` 运行时可以输入的命令有：

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

- `-n` ：后面接数字，代表显示几行的意思



**tail**

取出文件后面几行。默认的情况中，显示最后的十行！

语法：

```shell
tail [-n number] 文件 
```

选项与参数：

- `-n`：后面接数字，代表显示几行的意思
- `-f`：表示持续侦测后面所接的档名，要等到按下 [ctrl]-c 才会结束 tail 的侦测



# 6、用户和用户组管理

Linux 系统是一个多用户多任务的分时操作系统，任何一个要使用系统资源的用户，都必须首先向系统管理员申请一个账号，然后以这个账号的身份进入系统。用户的账号一方面可以帮助系统管理员对使用系统的用户进行跟踪，并控制他们对系统资源的访问；另一方面也可以帮助用户组织文件，并为用户提供安全性保护。

每个用户账号都拥有一个唯一的用户名和各自的口令。用户在登录时键入正确的用户名和口令后，就能够进入系统和自己的主目录。

实现用户账号的管理，要完成的工作主要有如下几个方面：

- 用户账号的添加、删除与修改
- 用户口令的管理
- 用户组的管理



## 6.1、系统用户账号的管理

用户账号的管理工作主要涉及到用户账号的添加、修改和删除。



### 6.1.1、添加帐号

添加用户账号就是在系统中创建一个新账号，然后为新账号分配用户号、用户组、主目录和登录Shell等资源。刚添加的账号是被锁定的，无法使用。

添加新的用户账号使用 `useradd` 命令，其语法如下：

```shell
useradd 选项 用户名
```

参数说明：

- 选项：

	- `-c comment`：指定一段注释性描述
	- `-d 目录`：指定用户主目录，如果此目录不存在，则同时使用 -m 选项，可以创建主目录
	- `-g 用户组`：指定用户所属的用户组
	- `-G 用户组,用户组`：指定用户所属的附加组
	- `-s Shell文件`：指定用户的登录 Shell
	- `-u 用户号`：指定用户的用户号，如果同时有 `-o` 选项，则可以重复使用其他用户的标识号

- 用户名：

	指定新账号的登录名。

**实例1：**

```shell
useradd –d /home/sam -m sam
```

此命令创建了一个用户 sam，其中 `-d` 和 `-m` 选项用来为登录名 sam 产生一个主目录 /home/sam（/home 为默认的用户主目录所在的父目录）。

**实例2：**

```shell
useradd -s /bin/sh -g group –G adm,root gem
```

此命令新建了一个用户 gem，该用户的登录 Shell 是 /bin/sh，它属于 group 用户组，同时又属于 adm 和 root 用户组，其中 group 用户组是其主组。这里可能新建组：`#groupadd group 及 groupadd adm`

增加用户账号就是在 /etc/passwd 文件中为新用户增加一条记录，同时更新其他系统文件如 /etc/shadow, /etc/group 等。

Linux 提供了集成的系统管理工具 userconf，它可以用来对用户账号进行统一管理。



### 6.1.2、删除帐号

如果一个用户的账号不再使用，可以从系统中删除。删除用户账号就是要将 /etc/passwd 等系统文件中的该用户记录删除，必要时还删除用户的主目录。

删除一个已有的用户账号使用`userdel`命令，其格式如下：

```shell
userdel 选项 用户名
```

常用的选项是 `-r`，它的作用是把用户的主目录一起删除。

**实例：**

```shell
userdel -r sam
```

此命令删除用户 sam 在系统文件中（主要是 /etc/passwd, /etc/shadow, /etc/group 等）的记录，同时删除用户的主目录。



### 6.1.3、修改帐号

修改用户账号就是根据实际情况更改用户的有关属性，如用户号、主目录、用户组、登录 Shell 等。

修改已有用户的信息使用`usermod`命令，其格式如下：

```shell
usermod 选项 用户名
```

常用的选项包括 `-c`, `-d`, `-m`, `-g`, `-G`, `-s`, `-u` 以及 `-o` 等，这些选项的意义与`useradd`命令中的选项一样，可以为用户指定新的资源值。

另外，有些系统可以使用选项：`-l 新用户名`

这个选项指定一个新的账号，即将原来的用户名改为新的用户名。

例如：

```shell
usermod -s /bin/ksh -d /home/z –g developer sam
```

此命令将用户 sam 的登录 Shell 修改为 ksh，主目录改为 /home/z，用户组改为 developer。



### 6.1.4、用户口令的管理

用户管理的一项重要内容是用户口令的管理。用户账号刚创建时没有口令，但是被系统锁定，无法使用，必须为其指定口令后才可以使用，即使是指定空口令。

指定和修改用户口令的Shell命令是`passwd`。超级用户可以为自己和其他用户指定口令，普通用户只能用它修改自己的口令。命令的格式为：

```shell
passwd 选项 用户名
```

可使用的选项：

- `-l`：锁定口令，即禁用账号
- `-u`：口令解锁
- `-d`：使账号无口令
- `-f`：强迫用户下次登录时修改口令

如果默认用户名，则修改当前用户的口令。

例如，假设当前用户是sam，则下面的命令修改该用户自己的口令：

```
$ passwd 
Old password:****** 
New password:******* 
Re-enter new password:*******
```

如果是超级用户，可以用下列形式指定任何用户的口令：

```
# passwd sam 
New password:******* 
Re-enter new password:*******
```

普通用户修改自己的口令时，`passwd` 命令会先询问原口令，验证后再要求用户输入两遍新口令，如果两次输入的口令一致，则将这个口令指定给用户；而超级用户为用户指定口令时，就不需要知道原口令。

为了系统安全起见，用户应该选择比较复杂的口令，例如最好使用 8 位长的口令，口令中包含有大写、小写字母和数字，并且应该与姓名、生日等不相同。

为用户指定空口令时，执行下列形式的命令：

```shell
passwd -d sam
```

此命令将用户 sam 的口令删除，这样用户 sam 下一次登录时，系统就不再允许该用户登录了。

passwd 命令还可以用 `-l`（lock）选项锁定某一用户，使其不能登录，例如：

```shell
passwd -l sam
```



## 6.2、系统用户组的管理

每个用户都有一个用户组，系统可以对一个用户组中的所有用户进行集中管理。不同 Linux 系统对用户组的规定有所不同，如 Linux 下的用户属于与它同名的用户组，这个用户组在创建用户时同时创建。

用户组的管理涉及用户组的添加、删除和修改。组的增加、删除和修改实际上就是对 /etc/group 文件的更新。



**添加组**

增加一个新的用户组使用 `groupadd `命令。其格式如下

```shell
groupadd 选项 用户组
```

可以使用的选项有：

- `-g GID` ：指定新用户组的组标识号（GID），如果未指定，新组的组标识号在当前已有的最大组标识号的基础上加 1
- `-o` ：一般与 `-g` 选项同时使用，表示新用户组的 GID 可以与系统已有用户组的 GID 相同



**删除组**

如果要删除一个已有的用户组，使用 `groupdel` 命令，其格式如下：

```shell
groupdel 用户组
```



**修改组**

修改用户组的属性使用 `groupmod` 命令。其语法如下：

```shell
groupmod 选项 用户组
```

常用的选项有：

- `-g GID` ：为用户组指定新的组标识号
- `-o` ：与 `-g` 选项同时使用，用户组的新 GID可 以与系统已有用户组的 GID 相同
- `-n 新用户组` ：将用户组的名字改为新名字



**用户切换组**

如果一个用户同时属于多个用户组，那么用户可以在用户组之间切换，以便具有其他用户组的权限。

用户可以在登录后，使用命令 `newgrp` 切换到其他用户组，这个命令的参数就是目的用户组。例如：

```shell
newgrp root
```

这条命令将当前用户切换到 root 用户组，前提条件是 root 用户组确实是该用户的主组或附加组。类似于用户账号的管理，用户组的管理也可以通过集成的系统管理工具来完成。



## 6.3、与用户账号有关的系统文件

完成用户管理的工作有许多种方法，但是每一种方法实际上都是对有关的系统文件进行修改。与用户和用户组相关的信息都存放在一些系统文件中，这些文件包括 /etc/passwd, /etc/shadow, /etc/group 等。



### 6.3.1、/etc/passwd

/etc/passwd 文件是用户管理工作涉及的最重要的一个文件。Linux 系统中的每个用户都在 /etc/passwd 文件中有一个对应的记录行，它记录了这个用户的一些基本属性。

这个文件对所有用户都是可读的。它的内容类似下面的例子：

```
＃ cat /etc/passwd
root:x:0:0:Superuser:/:
daemon:x:1:1:System daemons:/etc:
bin:x:2:2:Owner of system commands:/bin:
sys:x:3:3:Owner of system files:/usr/sys:
adm:x:4:4:System accounting:/usr/adm:
uucp:x:5:5:UUCP administrator:/usr/lib/uucp:
auth:x:7:21:Authentication administrator:/tcb/files/auth:
cron:x:9:16:Cron daemon:/usr/spool/cron:
listen:x:37:4:Network daemon:/usr/net/nls:
lp:x:71:18:Printer administrator:/usr/spool/lp:
sam:x:200:50:Sam san:/home/sam:/bin/sh
```

从上面的例子可以看到，/etc/passwd 中一行记录对应着一个用户，每行记录又被冒号 `:` 分隔为7个字段，其格式和具体含义如下：

```
用户名:口令:用户标识号:组标识号:注释性描述:主目录:登录Shell
```



**“用户名” 是代表用户账号的字符串**

通常长度不超过8个字符，并且由大小写字母和 `/` 或数字组成。登录名中不能有冒号`:`，因为冒号在这里是分隔符。

为了兼容起见，登录名中最好不要包含点字符 `.`，并且不使用连字符 `-` 和加号 `+` 打头。



**“口令” 一些系统中，存放着加密后的用户口令字**

虽然这个字段存放的只是用户口令的加密串，不是明文，但是由于 /etc/passwd 文件对所有用户都可读，所以这仍是一个安全隐患。因此，现在许多Linux 系统（如 SVR4）都使用了shadow 技术，把真正的加密后的用户口令字存放到 /etc/shadow 文件中，而在 /etc/passwd 文件的口令字段中只存放一个特殊的字符，例如 “x” 或者 “*”。



**“用户标识号” 是一个整数，系统内部用它来标识用户**

一般情况下它与用户名是一一对应的。如果几个用户名对应的用户标识号是一样的，系统内部将把它们视为同一个用户，但是它们可以有不同的口令、不同的主目录以及不同的登录 Shell 等。

通常用户标识号的取值范围是 0～65 535。0 是超级用户 root 的标识号，1～99 由系统保留，作为管理账号，普通用户的标识号从 100 开始。在 Linux系统中，这个界限是 500。



**“组标识号” 字段记录的是用户所属的用户组**

它对应着 /etc/group 文件中的一条记录。



**“注释性描述” 字段记录着用户的一些个人情况**

例如用户的真实姓名、电话、地址等，这个字段并没有什么实际的用途。在不同的 Linux 系统中，这个字段的格式并没有统一。在许多 Linux 系统中，这个字段存放的是一段任意的注释性描述文字，用做 `finger` 命令的输出。



**“主目录” 也就是用户的起始工作目录**

它是用户在登录到系统之后所处的目录。在大多数系统中，各用户的主目录都被组织在同一个特定的目录下，而用户主目录的名称就是该用户的登录名。各用户对自己的主目录有读、写、执行（搜索）权限，其他用户对此目录的访问权限则根据具体情况设置。



**用户登录后，要启动一个进程，负责将用户的操作传给内核，这个进程是用户登录到系统后运行的命令解释器或某个特定的程序，即 Shell**

Shell 是用户与 Linux 系统之间的接口。Linux 的 Shell 有许多种，每种都有不同的特点。常用的有 sh（Bourne Shell），csh（C Shell），ksh（Korn Shell）， tcsh（TENEX/TOPS-20 type C Shell）， bash（Bourne Again Shell）等。

系统管理员可以根据系统情况和用户习惯为用户指定某个 Shell。如果不指定 Shell，那么系统使用 sh 为默认的登录 Shell，即这个字段的值为 /bin/sh。

用户的登录 Shell 也可以指定为某个特定的程序（此程序不是一个命令解释器）。利用这一特点，我们可以限制用户只能运行指定的应用程序，在该应用程序运行结束后，用户就自动退出了系统。有些 Linux 系统要求只有那些在系统中登记了的程序才能出现在这个字段中。



**系统中有一类用户称为伪用户（pseudo users）**

这些用户在 /etc/passwd 文件中也占有一条记录，但是不能登录，因为它们的登录 Shell 为空。它们的存在主要是方便系统管理，满足相应的系统进程对文件属主的要求。

常见的伪用户如下所示：

```
伪 用 户 含 义 
bin 拥有可执行的用户命令文件 
sys 拥有系统文件 
adm 拥有帐户文件 
uucp UUCP使用 
lp lp或lpd子系统使用 
nobody NFS使用
```

除了上面列出的伪用户外，还有许多标准的伪用户，例如：audit, cron, mail, usenet 等，它们也都各自为相关的进程和文件所需要。



### 6.3.2、/etc/shadow

由于 /etc/passwd 文件是所有用户都可读的，如果用户的密码太简单或规律比较明显的话，一台普通的计算机就能够很容易地将它破解，因此对安全性要求较高的 Linux 系统都把加密后的口令字分离出来，单独存放在一个文件中，这个文件是 /etc/shadow 文件。 有超级用户才拥有该文件读权限，这就保证了用户密码的安全性。

/etc/shadow 中的记录行与 /etc/passwd 中的一一对应，它由 pwconv 命令根据 /etc/passwd 中的数据自动产生。它的文件格式与 /etc/passwd 类似，由若干个字段组成，字段之间用 `:` 隔开。这些字段是：

```
登录名:加密口令:最后一次修改时间:最小时间间隔:最大时间间隔:警告时间:不活动时间:失效时间:标志
```

**“登录名” 是与 /etc/passwd 文件中的登录名相一致的用户账号**

**”口令“ 字段存放的是加密后的用户口令字，长度为 13 个字符。如果为空，则对应用户没有口令，登录时不需要口令；如果含有不属于集合 { ./0-9A-Za-z }中的字符，则对应的用户不能登录。**

**“最后一次修改时间” 表示的是从某个时刻起，到用户最后一次修改口令时的天数。时间起点对不同的系统可能不一样。例如在 SCO Linux 中，这个时间起点是1970年1月1日。**

**”最小时间间隔“ 指的是两次修改口令之间所需的最小天数。**

**“最大时间间隔” 指的是口令保持有效的最大天数。**

**“警告时间” 字段表示的是从系统开始警告用户到用户密码正式失效之间的天数。**

**“不活动时间” 表示的是用户没有登录活动但账号仍能保持有效的最大天数。**

**“失效时间” 字段给出的是一个绝对的天数，如果使用了这个字段，那么就给出相应账号的生存期。期满后，该账号就不再是一个合法的账号，也就不能再用来登录了。**

下面是 /etc/shadow 的一个例子：

```
＃ cat /etc/shadow

root:Dnakfw28zf38w:8764:0:168:7:::
daemon:*::0:0::::
bin:*::0:0::::
sys:*::0:0::::
adm:*::0:0::::
uucp:*::0:0::::
nuucp:*::0:0::::
auth:*::0:0::::
cron:*::0:0::::
listen:*::0:0::::
lp:*::0:0::::
sam:EkdiSECLWPdSa:9740:0:0::::
```



### 6.3.3、/etc/group

将用户分组是Linux 系统中对用户进行管理及控制访问权限的一种手段。

每个用户都属于某个用户组；一个组中可以有多个用户，一个用户也可以属于不同的组。当一个用户同时是多个组中的成员时，在 /etc/passwd 文件中记录的是用户所属的主组，也就是登录时所属的默认组，而其他组称为附加组。

用户要访问属于附加组的文件时，必须首先使用 `newgrp` 命令使自己成为所要访问的组中的成员。

用户组的所有信息都存放在 /etc/group 文件中。此文件的格式也类似于 /etc/passwd 文件，由冒号 `:` 隔开若干个字段，这些字段有：

```
组名:口令:组标识号:组内用户列表
```

**“组名” 是用户组的名称，由字母或数字构成。与 /etc/passwd 中的登录名一样，组名不应重复。**

**“口令” 字段存放的是用户组加密后的口令字。一般 Linux 系统的用户组都没有口令，即这个字段一般为空，或者是 *。**

**“组标识号” 与用户标识号类似，也是一个整数，被系统内部用来标识组。**

**“组内用户列表” 是属于这个组的所有用户的列表，不同用户之间用逗号 `,`分隔。这个用户组可能是用户的主组，也可能是附加组。**

/etc/group 文件的一个例子如下：

```
root::0:root
bin::2:root,bin
sys::3:root,uucp
adm::4:root,adm
daemon::5:root,daemon
lp::7:root,lp
users::20:root,sam
```



### 6.3.4、添加批量用户

添加和删除用户对每位 Linux 系统管理员都是轻而易举的事，比较棘手的是如果要添加几十个、上百个甚至上千个用户时，我们不太可能还使用`useradd` 一个一个地添加，必然要找一种简便的创建大量用户的方法。Linux 系统提供了创建大量用户的工具，可以立即创建大量用户，方法如下：

1. 先编辑一个文本用户文件，每一列按照 /etc/passwd 密码文件的格式书写，要注意每个用户的用户名、UID、宿主目录都不可以相同，其中密码栏可以留做空白或输入 x 号。一个范例文件 user.txt 内容如下：

	```
	user001::600:100:user:/home/user001:/bin/bash
	user002::601:100:user:/home/user002:/bin/bash
	user003::602:100:user:/home/user003:/bin/bash
	user004::603:100:user:/home/user004:/bin/bash
	user005::604:100:user:/home/user005:/bin/bash
	user006::605:100:user:/home/user006:/bin/bash
	```

2. 以 root 身份执行命令 `/usr/sbin/newusers`，从刚创建的用户文件 user.txt 中导入数据，创建用户：

	```shell
	newusers < user.txt
	```

	然后可以执行命令 `vipw` 或 `vi /etc/passwd` 检查 /etc/passwd 文件是否已经出现这些用户的数据，并且用户的宿主目录是否已经创建。

3. 执行命令 `/usr/sbin/pwunconv` 将 /etc/shadow 产生的 shadow 密码解码，然后回写到 /etc/passwd 中，并将 /etc/shadow 的 shadow 密码栏删掉。这是为了方便下一步的密码转换工作，即先取消 shadow password 功能。

	```shell
	pwunconv
	```

4. 编辑每个用户的密码对照文件，格式为：

	```
	用户名:密码
	```

	实例文件 passwd.txt 内容如下：

	```
	user001:123456
	user002:123456
	user003:123456
	user004:123456
	user005:123456
	user006:123456
	```

5. 以 root 身份执行命令 `/usr/sbin/chpasswd` 创建用户密码，`chpasswd` 会将经过 `/usr/bin/passwd` 命令编码过的密码写入 /etc/passwd 的密码栏：

	```shell
	chpasswd < passwd.txt
	```

6. 确定密码经编码写入 /etc/passwd 的密码栏后执行命令 `/usr/sbin/pwconv` 将密码编码为 shadow password，并将结果写入 /etc/shadow：

	```shell
	pwconv
	```

	这样就完成了大量用户的创建了，之后您可以到 /home 下检查这些用户宿主目录的权限设置是否都正确，并登录验证用户密码是否正确。



# 7、磁盘管理

Linux 磁盘管理好坏直接关系到整个系统的性能问题。

Linux 磁盘管理常用三个命令为 `df`、`du` 和 `fdisk`。

- `df`（英文全称：disk full）：列出文件系统的整体磁盘使用量
- `du`（英文全称：disk used）：检查磁盘空间使用量
- `fdisk`：用于磁盘分区



## 7.1、df

`df` 命令参数功能：检查文件系统的磁盘空间占用情况。可以利用该命令来获取硬盘被占用了多少空间，目前还剩下多少空间等信息。

语法：

```
df [-ahikHTm] [目录或文件名]
```

选项与参数：

- `-a` ：列出所有的文件系统，包括系统特有的 /proc 等文件系统
- `-k` ：以 KBytes 的容量显示各文件系统
- `-m` ：以 MBytes 的容量显示各文件系统
- `-h` ：以人们较易阅读的 GBytes, MBytes, KBytes 等格式自行显示
- `-H` ：以 M=1000K 取代 M=1024K 的进位方式
- `-T` ：显示文件系统类型, 连同该 partition 的 filesystem 名称（例如 ext3）也列出
- `-i` ：不用硬盘容量，而以 inode 的数量来显示

**实例 1**

将系统内所有的文件系统列出来：

```
[root@www ~]# df
Filesystem      1K-blocks      Used Available Use% Mounted on
/dev/hdc2         9920624   3823112   5585444  41% /
/dev/hdc3         4956316    141376   4559108   4% /home
/dev/hdc1          101086     11126     84741  12% /boot
tmpfs              371332         0    371332   0% /dev/shm
```

在 Linux 底下如果 `df` 没有加任何选项，那么默认会将系统内所有的（不含特殊内存内的文件系统与 swap）都以 1 Kbytes 的容量来列出来！

**实例 2**

将容量结果以易读的容量格式显示出来：

```
[root@www ~]# df -h
Filesystem            Size  Used Avail Use% Mounted on
/dev/hdc2             9.5G  3.7G  5.4G  41% /
/dev/hdc3             4.8G  139M  4.4G   4% /home
/dev/hdc1              99M   11M   83M  12% /boot
tmpfs                 363M     0  363M   0% /dev/shm
```

**实例 3**

将系统内的所有特殊文件格式及名称都列出来：

```
[root@www ~]# df -aT
Filesystem    Type 1K-blocks    Used Available Use% Mounted on
/dev/hdc2     ext3   9920624 3823112   5585444  41% /
proc          proc         0       0         0   -  /proc
sysfs        sysfs         0       0         0   -  /sys
devpts      devpts         0       0         0   -  /dev/pts
/dev/hdc3     ext3   4956316  141376   4559108   4% /home
/dev/hdc1     ext3    101086   11126     84741  12% /boot
tmpfs        tmpfs    371332       0    371332   0% /dev/shm
none   binfmt_misc         0       0         0   -  /proc/sys/fs/binfmt_misc
sunrpc  rpc_pipefs         0       0         0   -  /var/lib/nfs/rpc_pipefs
```

**实例 4**

将 /etc 底下的可用的磁盘容量以易读的容量格式显示：

```
[root@www ~]# df -h /etc
Filesystem            Size  Used Avail Use% Mounted on
/dev/hdc2             9.5G  3.7G  5.4G  41% /
```



## 7.2、du

Linux `du` 命令也是查看使用空间的，但是与 `df` 命令不同的是 Linux `du` 命令是对文件和目录磁盘使用的空间的查看，还是和 `df` 命令有一些区别的，这里介绍 Linux `du` 命令。

语法：

```shell
du [-ahskm] 文件或目录名称
```

选项与参数：

- `-a` ：列出所有的文件与目录容量，因为默认仅统计目录底下的文件量而已。
- `-h` ：以人们较易读的容量格式（G/M）显示；
- `-s ` ：列出总量而已，而不列出每个各别的目录占用容量；
- `-S` ：不包括子目录下的总计，与 `-s` 有点差别。
- `-k` ：以 KBytes 列出容量显示；
- `-m` ：以 MBytes 列出容量显示；

**实例 1**

只列出当前目录下的所有文件夹容量（包括隐藏文件夹）:

```
[root@www ~]# du
8       ./test4     <==每个目录都会列出来
8       ./test2
....中间省略....
12      ./.gconfd   <==包括隐藏文件的目录
220     .           <==这个目录(.)所占用的总量
```

直接输入 `du` 没有加任何选项时，则 `du` 会分析当前所在目录里的子目录所占用的硬盘空间。

**实例 2**

将文件的容量也列出来：

```
[root@www ~]# du -a
12      ./install.log.syslog   <==有文件的列表了
8       ./.bash_logout
8       ./test4
8       ./test2
....中间省略....
12      ./.gconfd
220     .
```

**实例 3**

检查根目录底下每个目录所占用的容量：

```
[root@www ~]# du -sm /*
7       /bin
6       /boot
.....中间省略....
0       /proc
.....中间省略....
1       /tmp
3859    /usr     <==系统初期最大就是他了啦！
77      /var
```

通配符 * 来代表每个目录。与 `df` 不一样的是，`du` 这个命令其实会直接到文件系统内去搜寻所有的文件数据。



## 7.3、fdisk

fdisk 是 Linux 的磁盘分区表操作工具。

语法：

```shell
fdisk [-l] 装置名称
```

选项与参数：

- `-l` ：输出后面接的装置所有的分区内容。若仅有 `fdisk -l` 时， 则系统将会把整个系统内能够搜寻到的装置的分区均列出来。

**实例 1**

列出所有分区信息：

```
[root@AY120919111755c246621 tmp]# fdisk -l

Disk /dev/xvda: 21.5 GB, 21474836480 bytes
255 heads, 63 sectors/track, 2610 cylinders
Units = cylinders of 16065 * 512 = 8225280 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disk identifier: 0x00000000

    Device Boot      Start         End      Blocks   Id  System
/dev/xvda1   *           1        2550    20480000   83  Linux
/dev/xvda2            2550        2611      490496   82  Linux swap / Solaris

Disk /dev/xvdb: 21.5 GB, 21474836480 bytes
255 heads, 63 sectors/track, 2610 cylinders
Units = cylinders of 16065 * 512 = 8225280 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disk identifier: 0x56f40944

    Device Boot      Start         End      Blocks   Id  System
/dev/xvdb2               1        2610    20964793+  83  Linux
```

**实例 2**

找出你系统中的根目录所在磁盘，并查阅该硬盘内的相关信息：

```
[root@www ~]# df /            <==注意：重点在找出磁盘文件名而已
Filesystem           1K-blocks      Used Available Use% Mounted on
/dev/hdc2              9920624   3823168   5585388  41% /

[root@www ~]# fdisk /dev/hdc  <==仔细看，不要加上数字喔！
The number of cylinders for this disk is set to 5005.
There is nothing wrong with that, but this is larger than 1024,
and could in certain setups cause problems with:
1) software that runs at boot time (e.g., old versions of LILO)
2) booting and partitioning software from other OSs
   (e.g., DOS FDISK, OS/2 FDISK)

Command (m for help):     <==等待你的输入！
```

输入 `m` 后，就会看到底下这些命令介绍

```
Command (m for help): m   <== 输入 m 后，就会看到底下这些命令介绍
Command action
   a   toggle a bootable flag
   b   edit bsd disklabel
   c   toggle the dos compatibility flag
   d   delete a partition            <==删除一个partition
   l   list known partition types
   m   print this menu
   n   add a new partition           <==新增一个partition
   o   create a new empty DOS partition table
   p   print the partition table     <==在屏幕上显示分割表
   q   quit without saving changes   <==不储存离开fdisk程序
   s   create a new empty Sun disklabel
   t   change a partition's system id
   u   change display/entry units
   v   verify the partition table
   w   write table to disk and exit  <==将刚刚的动作写入分割表
   x   extra functionality (experts only)
```

离开 `fdisk` 时按下 `q`，那么所有的动作都不会生效！相反的， 按下`w`就是动作生效的意思。

```
Command (m for help): p  <== 这里可以输出目前磁盘的状态

Disk /dev/hdc: 41.1 GB, 41174138880 bytes        <==这个磁盘的文件名与容量
255 heads, 63 sectors/track, 5005 cylinders      <==磁头、扇区与磁柱大小
Units = cylinders of 16065 * 512 = 8225280 bytes <==每个磁柱的大小

   Device Boot      Start         End      Blocks   Id  System
/dev/hdc1   *           1          13      104391   83  Linux
/dev/hdc2              14        1288    10241437+  83  Linux
/dev/hdc3            1289        1925     5116702+  83  Linux
/dev/hdc4            1926        5005    24740100    5  Extended
/dev/hdc5            1926        2052     1020096   82  Linux swap / Solaris
# 装置文件名 启动区否 开始磁柱    结束磁柱  1K大小容量 磁盘分区槽内的系统

Command (m for help): q
```

想要不储存离开吗？按下 `q` 就对了！不要随便按 `w` 啊！使用 `p` 可以列出目前这颗磁盘的分割表信息，这个信息的上半部在显示整体磁盘的状态。



# 8、vi/vim
