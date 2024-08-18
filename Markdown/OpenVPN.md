---
typora-copy-images-to: upload
---

# 1、简介

OpenVPN 是一个用于创建虚拟私人网络加密通道的软件包，最早由 James Yonan 编写。OpenVPN 允许建立的 VPN 使用公开密钥、电子证书、或者用户名/密码来进行身份验证。

它大量使用了 OpenSSL 加密库中的 SSL/TLS 协议函数库。

目前 OpenVPN 能在 Solaris、Linux、OpenBSD、FreeBSD、NetBSD、Mac OS X 与 Microsoft Windows 以及 Android 和 iOS 上运行，并包含了许多安全性的功能。它不与 IPsec 兼容。



# 2、搭建 OpenVPN

准备三台电脑，一台具有公网 IP 的服务器，用来安装 OpenVPN 服务端，一台内网服务器和一台家用电脑，用来安装 OpenVPN 客户端。



## 2.1、OpenVPN 下载

安装包可以在 [GitHub](https://github.com/OpenVPN/openvpn) 下载，或者去[官网](https://openvpn.net/community-downloads/)下载。



## 2.2、服务端安装

OpenVPN 软件服务端和客户端都是同一个安装包，本次使用的 OpenVPN 安装包为 2.6.12 版本。

1. 安装的时候要选择 Customize，勾选 OpenVPN Service 和 EasyRSA3 安装，用于服务端配置和证书生成使用：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817110655401.png" alt="image-20240817110655401" style="zoom: 67%;" />

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817110817655.png" alt="image-20240817110817655" style="zoom:67%;" />

2. 安装完成后软件默认位置 `C:\Program Files\OpenVPN` 目录：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817111105533.png" alt="image-20240817111105533" style="zoom:67%;" />



## 2.3、证书生成

上一步已经安装了证书生成工具 EasyRSA3，使用此工具即可生成所需证书：

1. **准备 CA 签发机构环境**

   进入 `C:\Program Files\OpenVPN\easy-rsa` 目录，将 vars.example 文件复制一份，改为 vars，然后编辑内容：

   ```ini
   # 生成证书使用完整DN结构
   set_var EASYRSA_DN	"org"
   # 设置请求国家字段为中国
   set_var EASYRSA_REQ_COUNTRY	"CN"
   # 设置请求省份字段为上海
   set_var EASYRSA_REQ_PROVINCE	"Shanghai"
   # 设置请求城市字段为上海
   set_var EASYRSA_REQ_CITY	"Shanghai"
   # 设置请求组织字段为Orichalcos_ORG
   set_var EASYRSA_REQ_ORG	"Orichalcos_ORG"
   # 设置CA证书有效期为10年
   set_var EASYRSA_CA_EXPIRE	36500
   # 设置服务器证书为10年
   set_var EASYRSA_CERT_EXPIRE	3650
   ```

2. **进入 EasyRSA shell 环境 DOS 窗口**

   双击 EasyRSA-Start.bat 进入 EasyRSA shell 环境 DOS 窗口中：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817111452752.png" alt="image-20240817111452752" style="zoom:67%;" />

3. **清理原有证书和私钥并初始化**

   弹出的 DOS 窗口中输入 `./easyrsa clean-all` 清理原有证书和私钥并初始化，初始化成功后会在 `C:\Program Files\OpenVPN\easy-rsa` 目录下新建文件夹 pki：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817175141535.png" alt="image-20240817175141535" style="zoom:67%;" />

4. **生成 CA 根证书**

   在 DOS 窗口中输入 `./easyrsa build-ca` 生成 CA 证书，生成过程中会要求输入名称、密码等信息，比如我这里名称（Common Name）写的 Orichalcos：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817211225042.png" alt="image-20240817211225042" style="zoom:67%;" />

   CA 根证书位置： `C:\Program Files\OpenVPN\easy-rsa\pki\ca.crt`。

   CA 密钥位置： `C:\Program Files\OpenVPN\easy-rsa\pki\private\ca.key`。

5. **生成服务端证书**

   输入 `./easyrsa build-server-full server nopass` 生成无密码服务端证书：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817212039908.png" alt="image-20240817212039908" style="zoom:67%;" />

   服务端证书路径：`C:\Program Files\OpenVPN\easy-rsa\pki\issued\server.crt` 。

   服务端私钥路径：`C:\Program Files\OpenVPN\easy-rsa\pki\private\server.key` 。

   > [!NOTE]
   >
   > 这里使用 `nopass` 参数设置不需要密码，那么在启动 OpenVPN 服务的时候就不提示输入密码。

6. **生成 Diffie-Hellman 算法需要的密钥文件**

   输入 `./easyrsa gen-dh` 生成 DH 密钥交换协议文件，生成文件在 `C:\Program Files\OpenVPN\easy-rsa\pki` 目录下：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817113407298.png" alt="image-20240817113407298" style="zoom:67%;" />

7. **生成 tls-auth Key 用于防止 DDOS 和 TLS 攻击**

   输入 `openvpn --genkey --secret ta.key`：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817213609767.png" alt="image-20240817213609767" style="zoom:67%;" />

   ta.key 路径：`C:\Program Files\OpenVPN\easy-rsa\ta.key`。



## 2.4、服务器配置

服务端配置文件模板为 server.ovpn ，在 `C:\Program Files\OpenVPN\sample-config` 目录下：

1. **修改服务端配置文件**

   复制 server.ovpn 文件至 `C:\Program Files\OpenVPN\config` 目录，修改如下选项：

   ```ini
   # 客户端连接端口
   port 1194
   # 指定OpenVPN创建的通信隧道类型，tun创建一个路由的IP隧道，tap创建一个以太网隧道
   # TCP还是UDP协议方式连接服务器
   proto tcp
   dev tun
   # 证书配置，因证书生成已经复制到本目录
   ca ca.crt
   # 证书配置，因证书生成已经复制到本目录
   cert server.crt
   # 证书配置，因证书生成已经复制到本目录
   key server.key
   # 证书配置，因证书生成已经复制到本目录
   dh dh.pem
   # 开启TLS-auth，使用ta.key防御攻击，服务端的第二个参数为0，客户端的为1
   tls-auth ta.key 0
   # VPN所用网段，不能和内网冲突，推荐默认
   server 10.8.0.0 255.255.255.0
   # 默认是禁止客户端之间互联的，将其开启
   client-to-client
   ```

2. **证书复制配置**

   将服务证书、服务 Key、CA 证书、DH 文件复制到文件夹 `C:\Program Files\OpenVPN\config` 下：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817220414043.png" alt="image-20240817220414043" style="zoom:67%;" />

   > [!IMPORTANT]
   >
   > 需要拷贝的文件包括 ca.crt、ca.key、server.crt、server.key、dh.pem、ta.key。

3. **连接**

   右键点击任务栏带锁小电脑图标，点击连接，连接成功后会变绿，系统提示分配 IP：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817115630422.png" alt="image-20240817115630422" style="zoom: 33%;" /><img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817115605785.png" alt="image-20240817115605785" style="zoom: 67%;" />



## 2.5、客户端配置

1. **安装**

   双击 OpenVPN-2.6.12-I001-amd64.msi，点击 Install Now 安装客户端。

2. **生成客户端的证书和私钥**

   在服务端 DOS 窗口输入 `./easyrsa build-client-full client nopass` 生成客户端的证书和私钥，每多一个客户端就要多生成一份：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817222424285.png" alt="image-20240817222424285" style="zoom:67%;" />

   > [!NOTE]
   >
   > 命令 `./easyrsa build-client-full client nopass` 中的 client  为客户端证书和密钥的名字，可以自己取名，但注意需要和客户端配置文件里 `cert`、`key`的名字相同。

   客户端证书路径：`C:\Program Files\OpenVPN\easy-rsa\pki\issued\client.crt`。
   客户端私钥路径：`C:\Program Files\OpenVPN\easy-rsa\pki\private\client.key`。

   将生成的客户端证书（client.crt)、私钥（client.key）、服务端根证书（ca.crt）、ta.key 打包发送到客户端的 `C:\Program Files\OpenVPN\config`。

3. **配置客户端文件**

   客户端配置文件 client.ovpn，模板在 `C:\Program Files\OpenVPN\sample-config`，复制该文件到 `C:\Program Files\OpenVPN\config` 目录下，修改客户端配置如下：

   ```ini
   # 服务端IP地址和端口号
   remote <公网服务器IP> 1194
   # TCP还是UDP协议方式连接服务器
   proto tcp
   # 开启TLS-auth，使用ta.key防御攻击，服务端的第二个参数为0，客户端的为1
   tls-auth ta.key 1
   ```

4. **测试连接服务器**

   右键点击任务栏带锁小电脑图标，点击连接，连接成功后系统分配 IP，小电脑变绿。

   服务器端终端中输入 `ipconfig -all`，查看服务器端地址：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817232449865.png" alt="image-20240817232449865" style="zoom:67%;" />

   可以看到服务器端 IP 地址为 10.8.0.1，所以此时我们在本机访问 10.8.0.1 地址就可以访问到服务器：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240817232615756.png" alt="image-20240817232615756" style="zoom:67%;" />

5. **另外一台客户端执行同样的操作**

6. **测试能否互连**

   一台服务端和两台客户端启动 OpenVPN 服务后，被访问的客户端防火墙开通 ICMPv4 协议，互相 ping 测试能否互连：

   ![image-20240818022548260](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/image-20240818022548260.png)

   > [!NOTE]
   >
   > 后续可以根据自己需要开通被访问方防火墙 TCP 1194 端口。

   

# 3、使用 OpenVPN

## 3.1、查看在线用户

OpenVPN 启动之后会在 `C:\Program Files\OpenVPN\config` 位置生成 ipp.txt 和 openvpn-status.log 文件：

- ipp.txt：记录了每个客户端的 IP 信息。
- openvpn-status.log：记录了 OpenVPN 的客户端、路由等一些信息。



## 3.2、客户端设置静态 IP

有多个内网及公网的机器需要打通然后部署了 OpenVPN 服务，但是一旦有机器重启就好导致 IP 发生变化，因此需要想办法固定 IP：

1. 在 OpenVPN 的配置文件夹中创建一个 ccd 文件夹，完整路径为：`C:\Program Files\OpenVPN\config`。

2. 编辑 VPN 服务器配置文件，添加以下内容：

   ```ini
   client-config-dir ccd
   ```

   这样配置将告诉 OpenVPN 使用 `./ccd` 目录中的配置文件为每个客户端分配固定的 IP 地址。

3. 对于每个客户端，在 ccd 文件夹下创建一个与其名称对应的配置文件，并指定需要分配给该客户端的固定 IP 地址。

   例如，如果有一个名为 client 的客户端，可以创建一个名为 client 的文件，并在其中写入以下内容：

   ```ini
   ifconfig-push 10.8.0.2 255.255.255.0
   ```

   这将分配 IP 地址 10.8.0.2 给 client。你可以为每个客户端创建类似的配置文件，只需更改 IP 地址即可。

4. 重启 OpenVPN 服务，使配置生效。

