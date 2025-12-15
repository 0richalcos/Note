# 1、什么是 VNC？

VNC（Virtual Network Computing） 是一种基于远程帧缓冲（RFB，Remote FrameBuffer）协议的远程桌面共享系统。它允许用户通过网络远程控制另一台计算机的桌面界面。

通俗理解：就像远程操作别人的显示器、键盘、鼠标，把远程桌面传输到本地电脑上操作。

> [!NOTE]
>
> 默认 VNC 协议不加密传输内容，使用 SSH 隧道或搭配 VPN 使用。

<br>

**VNC 使用场景**

- 远程办公、远程技术支持。
- 无人值守服务器图形界面维护。
- 实验室/课堂多机控制。
- 异地远程协助操作。

<br>

**VNC 的特点**

- 跨平台：支持 Linux、Windows、macOS、Unix 等系统互相远程操作。
- 图形化界面：支持图形界面远程控制（与 SSH 相比）
- 不依赖登录用户：可设置为服务进程，在登录前就能远程连接
- 可搭配加密通道：通常结合 SSH、OpenVPN 使用来提升安全性

<br>

# 2、VNC 的工作原理

**架构组成**

```
+-------------------+                  +--------------------+
|    VNC Viewer     |  <--------->     |    VNC Server      |
| (客户端/本地控制端)  |    RFB协议       |   (远程桌面提供者)    |
+-------------------+                  +--------------------+
```

- VNC Viewer（客户端）：接收远程桌面的图像，捕捉鼠标/键盘事件并发送给 Server。
- VNC Server（服务端）：采集屏幕图像并传输给 Viewer，同时接收并执行 Viewer 发来的输入操作。
- 通常监听 TCP 5900 + display number（如 :1 则监听 5901）

<br>

**通信流程（RFB协议流程）**

1. 握手阶段：协商协议版本与安全机制。
2. 认证阶段：常见为口令认证（也支持 TLS、UNIX login 等方式）。
3. 初始化阶段：客户端请求共享桌面。
4. 图像传输：
   - Server 以增量方式传输屏幕图像（仅变化部分）。
   - 支持多种压缩编码（Raw、Hextile、Tight 等）。
5. 事件传输：
   - Client 发送鼠标移动、点击、键盘按键等事件。

<br>

# 3、VNC 与其他远程协议对比

| 协议/工具   | 图形界面 | 默认加密    | 跨平台          | 典型用途               | 带宽效率           |
| ----------- | -------- | ----------- | --------------- | ---------------------- | ------------------ |
| VNC         | ✅        | ❌（可加密） | ✅               | 远程图形管理服务器     | 中等（取决于编码） |
| SSH + X11   | ✅        | ✅           | ✅               | Linux GUI 程序远程运行 | 低                 |
| RDP（微软） | ✅        | ✅           | ❌（主 Windows） | Windows远程桌面        | 高（智能压缩）     |
| TeamViewer  | ✅        | ✅           | ✅               | 远程协助、远程办公     | 高                 |
| AnyDesk     | ✅        | ✅           | ✅               | 高性能远程连接         | 非常高             |

<br>

# 4、环境搭建

## 4.1、Linux 通用

通用 Linux 远程桌面搭建方案：TigerVNC（服务端） + XFCE4（桌面环境）。

适用于主流 Linux 发行版，包括：

- Debian、Ubuntu、UOS、Deepin 等 Debian 系。
- CentOS、Rocky Linux、AlmaLinux、RHEL 等 RedHat 系。

搭建步骤：

1. 安装桌面环境和 VNC 服务端：

   - Debian 系系统：

     ```shell
     apt update
     apt install -y xfce4 xfce4-goodies tigervnc-standalone-server
     ```

   - RedHat 系系统：

     ```shell
     yum groupinstall -y "Xfce"
     yum install -y tigervnc-server
     ```

   > [!NOTE]
   >
   > 不同发行版桌面环境包名略有差异，请根据实际发行版调整。

2. 设置 VNC 访问密码：

   ```shell
   vncpasswd
   ```

   密码将保存在用户目录下的 `~/.vnc/passwd`。

3. 初始化启动 VNC 会话以生成配置文件：

   ```shell
   vncserver :1
   ```

   这条命令会启动一个新的 VNC 会话，监听端口为 `5900 + 显示号`，即 5901端口（后续会改）。

   它会自动生成默认的 `~/.vnc/xstartup` 启动脚本（后续需要修改）。

4. 立即停止这个临时的 VNC server：

   ```shell
   vncserver -kill :1
   ```

   因为我们最终要用 systemd 来管理它，所以这个只是用来生成文件的，用完就扔。

5. 配置 xstartup 启动桌面环境，编辑生成的 `~/.vnc/xstartup` 文件：

   ```shell
   vim ~/.vnc/xstartup
   ```

   内容示例：

   ```
   #!/bin/sh
   
   unset SESSION_MANAGER
   unset DBUS_SESSION_BUS_ADDRESS
   
   [ -r /etc/X11/Xresources ] && xrdb /etc/X11/Xresources
   
   startxfce4 &
   ```

   保存并赋予执行权限：

   ```shell
   chmod +x ~/.vnc/xstartup
   ```

6. 以 `:1` 显示号，即以监听 5901 端口为例（这里才是最终监听的端口），创建 systemd 服务文件：

   ```shell
   vim /etc/systemd/system/vncserver@:1.service
   ```

   内容示例：

   ```
   [Unit]
   Description=TigerVNC server for %i display
   After=syslog.target network.target
   
   [Service]
   Type=forking
   User=<YOUR_USERNAME>
   PAMName=login
   PIDFile=/home/<YOUR_USERNAME>/.vnc/%H:%i.pid
   ExecStart=/usr/bin/vncserver %i -geometry 1280x800 -depth 24
   ExecStop=/usr/bin/vncserver -kill %i
   
   [Install]
   WantedBy=multi-user.target
   ```

   - 将 `<YOUR_USERNAME>` 替换为实际使用的用户名。
   - `ExecStart` 中的路径可能根据发行版不同有所差异，可以用 `which vncserver` 确认路径。

7. 启用并启动服务：

   ```shell
   systemctl daemon-reload
   systemctl enable vncserver@:1
   systemctl start vncserver@:1
   ```

8. 查看服务状态：

   ```shell
   systemctl status vncserver@:1
   ```

9. 查看运行中的回话列表：

   ```shell
   vncserver -list
   ```

<br>

## 4.2、统信 UOS

在统信服务器操作系统中安装 VNC 工具，用于实现从 Windows 侧系统上使用 VNC Viewer 等软件远程登录桌面访问 UOS 服务器操作系统。

前置条件：

1. 确认系统已安装有图形化，可接入显示器或通过虚拟控制台确认。
2. root 用户执行以下所有命令。

搭建步骤：

1. 关闭本机防火墙：

   ```shell
   systemctl stop firewalld
   systemctl disable firewalld
   systemctl mask firewalld
   
   systemctl stop ebtables
   systemctl disable ebtables
   systemctl mask ebtables
   
   systemctl stop iptables
   systemctl disable iptables
   systemctl mask iptables
   
   systemctl stop ip6tables
   systemctl disable ip6tables
   systemctl mask ip6tables
   
   systemctl stop nftables
   systemctl disable nftables
   systemctl mask nftables
   ```

   上述指令会关闭并停用相关防火墙服务，同时将屏蔽防火墙服务。

2. 安装 x11vnc 软件包：

   - Debian 系系统：

     ```shell
     apt update
     apt install -y x11vnc
     ```

   - RedHat 系系统：

     ```shell
     yum install x11vnc
     ```

3. 修改配置文件：

   ```shell
   vim /lib/systemd/system/x11vnc.service
   ```

   注释掉文件所有的原内容后，粘贴以下内容：

   ```shell
   [Unit]
   Description=Start x11vnc at startup.
   After=multi-user.target
   
   [Service]
   Type=simple
   ExecStart=/usr/bin/x11vnc -auth /var/run/lightdm/root/:0 -forever -loop -noxdamage -repeat -rfbauth /etc/x11vnc.pwd -rfbport 5900 -shared
   ExecStop=/bin/kill ${MAINPID}
   RemainAfterExit=yes
   
   [Install] 
   WantedBy=multi-user.target
   ```

   > [!NOTE]
   >
   > 以上配置内容中 VNC 使用的端口是 5900，网络防火墙需要注意提前放行。

4. 配置 VNC 密码：

   ```shell
   x11vnc -storepasswd
   mv ~/.vnc/passwd /etc/x11vnc.pwd
   ```

5. 启动服务：

   ```shell
   systemctl daemon-reload
   systemctl enable x11vnc
   systemctl restart x11vnc
   ```

