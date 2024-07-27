# 1、简介

## 1.1、概览

**frp 是什么？**

frp 是一款高性能的反向代理应用，专注于内网穿透。它支持多种协议，包括 TCP、UDP、HTTP、HTTPS 等，并且具备 P2P 通信功能。使用 frp，您可以安全、便捷地将内网服务暴露到公网，通过拥有公网 IP 的节点进行中转。



**为什么选择 frp？**

通过在具有公网 IP 的节点上部署 frp 服务端，您可以轻松地将内网服务穿透到公网，并享受以下专业特性：

- 多种协议支持：客户端服务端通信支持 TCP、QUIC、KCP 和 Websocket 等多种协议。
- TCP 连接流式复用：在单个连接上承载多个请求，减少连接建立时间，降低请求延迟。
- 代理组间的负载均衡。
- 端口复用：多个服务可以通过同一个服务端端口暴露。
- P2P 通信：流量不必经过服务器中转，充分利用带宽资源。
- 客户端插件：提供多个原生支持的客户端插件，如静态文件查看、HTTPS/HTTP 协议转换、HTTP、SOCKS5 代理等，以便满足各种需求。
- 服务端插件系统：高度可扩展的服务端插件系统，便于根据自身需求进行功能扩展。
- 用户友好的 UI 页面：提供服务端和客户端的用户界面，使配置和监控变得更加方便。



**工作原理**

frp 主要由两个组件组成：客户端（frpc） 服务端（frps）。通常情况下，服务端部署在具有公网 IP 地址的机器上，而客户端部署在需要穿透的内网服务所在的机器上。

由于内网服务缺乏公网 IP 地址，因此无法直接被非局域网内的用户访问。用户通过访问服务端的 frps，frp 负责根据请求的端口或其他信息将请求路由到相应的内网机器，从而实现通信。



**代理**

在 frp 中，一个代理对应一个需要公开访问的内网服务。一个客户端可以同时配置多个代理，以满足不同的需求。

frp 支持多种代理类型，以适应不同的使用场景。以下是一些常见的代理类型：

- TCP：提供纯粹的 TCP 端口映射，使服务端能够根据不同的端口将请求路由到不同的内网服务。
- UDP：提供纯粹的 UDP 端口映射，与 TCP 代理类似，但用于 UDP 流量。
- HTTP：专为 HTTP 应用设计，支持修改 Host Header 和增加鉴权等额外功能。
- HTTPS：类似于 HTTP 代理，但专门用于处理 HTTPS 流量。
- STCP：提供安全的 TCP 内网代理，要求在被访问者和访问者的机器上都部署 frpc，不需要在服务端暴露端口。
- SUDP：提供安全的 UDP 内网代理，与 STCP 类似，需要在被访问者和访问者的机器上都部署 frpc，不需要在服务端暴露端口。
- XTCP：点对点内网穿透代理，与 STCP 类似，但流量不需要经过服务器中转。
- TCPMUX：支持服务端 TCP 端口的多路复用，允许通过同一端口访问不同的内网服务。



## 1.2、安装

**下载**

 GitHub 的 [Release](https://github.com/fatedier/frp/releases) 页面中下载最新版本的客户端和服务器二进制文件。所有文件都打包在一个压缩包中，还包含了一份完整的配置参数说明。



**部署**

1. 解压下载的压缩包。
2. 将 frpc 复制到内网服务所在的机器上。
3. 将 frps 复制到拥有公网 IP 地址的机器上，并将它们放在任意目录。



**开始使用**

1. 编写配置文件，目前支持的文件格式包括 TOML/YAML/JSON，旧的 INI 格式仍然支持，但已经不再推荐。
2. 使用以下命令启动服务器：`./frps -c ./frps.toml`。
3. 使用以下命令启动客户端：`./frpc -c ./frpc.toml`。
4. 如果需要在后台长期运行，建议结合其他工具，如 systemd 和 supervisor。



# 2、功能特性

## 2.1、配置文件

从 v0.52.0 版本开始，frp 开始支持 TOML、YAML 和 JSON 作为配置文件格式。INI 已被弃用，并将在未来的发布中移除。新功能只能在 TOML、YAML 或 JSON 中使用。



**格式**

可使用 TOML/YAML/JSON 任何一个格式来编写配置文件，frp 会自动适配进行解析。

文档示例主要通过 YAML 编写，如下的示例配置将本地 SSH 服务穿透到公网。

frps 配置：

```yaml
bindPort: 7000
```

frpc 配置：

```yaml
serverAddr: x.x.x.x
serverPort: 7000

proxies:
  - name: ssh
    type: tcp
    localIP: 127.0.0.1
    localPort: 22
    remotePort: 6000
```

同一个客户端可以配置多个代理，但是 `name` 必须确保唯一。不同的客户端之间，可以通过配置不同的 `user` 来确保代理名称唯一。



**配置校验**

通过执行 `frpc verify -c ./frpc.yaml` 或 `frps verify -c ./frps.yaml` 可以对配置文件中的参数进行预先校验：

```
frpc: the configuration file ./frpc.toml syntax is ok
```

如果出现此结果，则说明新的配置文件没有错误，否则会输出具体的错误信息。



# 3、配置说明

## 3.1、通用配置

**<span id='LogConfig'>LogConfig</span>**

| Field             | Type   | Description                                                  | Required |
| :---------------- | :----- | :----------------------------------------------------------- | :------- |
| to                | string | 日志输出文件路径，如果为 console，则会将日志打印在标准输出中。 | No       |
| level             | string | 日志级别，可选值为 trace、debug、info、warn、error，默认级别为 info。 | No       |
| maxDays           | int    | 日志文件最多保留天数，默认为 3 天。                          | No       |
| disablePrintColor | bool   | 禁用标准输出中的日志颜色。                                   | No       |



**<span id='WebServerConfig'>WebServerConfig</span>**

| Field       | Type      | Description                                                  | Required |
| :---------- | :-------- | :----------------------------------------------------------- | :------- |
| addr        | string    | webServer 监听地址，默认为 `127.0.0.1`。                     | No       |
| port        | int       | webServer 监听端口。                                         | Yes      |
| user        | string    | HTTP BasicAuth 用户名。                                      | No       |
| password    | string    | HTTP BasicAuth 密码。                                        | No       |
| assetsDir   | string    | 静态资源目录，Dashboard 使用的资源默认打包在二进制文件中，通过指定此参数使用自定义的静态资源。 | No       |
| pprofEnable | bool      | 启动 Go HTTP pprof，用于应用调试。                           | No       |
| tls         | TLSConfig | Dashboard 启用 HTTPS 的 TLS 相关配置。                       | No       |



## 3.2、服务端配置

**ServerConfig**

| Field                           | Type                                      | Description                                                  | Required |
| :------------------------------ | :---------------------------------------- | :----------------------------------------------------------- | :------- |
| auth                            | [AuthServerConfig](#FWD-AuthServerConfig) | 鉴权配置。                                                   | No       |
| bindAddr                        | string                                    | 服务端监听地址，用于接收 frpc 的连接，默认监听 `0.0.0.0`。   | No       |
| bindPort                        | int                                       | 服务端监听端口，默认值为 7000。                              | No       |
| kcpBindPort                     | int                                       | 服务端监听 KCP 协议端口，用于接收配置了使用 KCP 协议的 frpc 连接。 | No       |
| quicBindPort                    | int                                       | 服务端监听 QUIC 协议端口，用于接收配置了使用 QUIC 协议的 frpc 连接。 | No       |
| proxyBindAddr                   | string                                    | 代理监听地址，可以使代理监听在不同的网卡地址，默认情况下同 `bindAddr`。 | No       |
| vhostHTTPPort                   | int                                       | HTTP 类型代理监听的端口，启用后才能支持 HTTP 类型的代理。    | No       |
| vhostHTTPTimeout                | int                                       | HTTP 类型代理在服务端的 ResponseHeader 超时时间，单位秒，默认为 60 秒。 | No       |
| vhostHTTPSPort                  | int                                       | HTTPS 类型代理监听的端口，启用后才能支持 HTTPS 类型的代理。  | No       |
| tcpmuxHTTPConnectPort           | int                                       | tcpmux 类型且复用器为 httpconnect 的代理监听的端口。         | No       |
| tcpmuxPassthrough               | bool                                      | 对于 tcpmux 类型的代理是否透传 CONNECT 请求。                | No       |
| subDomainHost                   | string                                    | 二级域名后缀。                                               | No       |
| custom404Page                   | string                                    | 自定义 404 错误页面地址。                                    | No       |
| sshTunnelGateway                | SSHTunnelGateway                          | ssh 隧道网关配置。                                           | No       |
| webServer                       | [WebServerConfig](#WebServerConfig)       | 服务端 Dashboard 配置。                                      | No       |
| enablePrometheus                | bool                                      | 是否提供 Prometheus 监控接口，需要同时启用了 `webServer` 后才会生效。 | No       |
| log                             | [LogConfig](#LogConfig)                   | 日志配置。                                                   | No       |
| transport                       | ServerTransportConfig                     | 网络层配置。                                                 | No       |
| detailedErrorsToClient          | bool                                      | 服务端返回详细错误信息给客户端，默认为 true。                | No       |
| maxPortsPerClient               | int                                       | 限制单个客户端最大同时存在的代理数，默认无限制。             | No       |
| userConnTimeout                 | int                                       | 用户建立连接后等待客户端响应的超时时间，单位秒，默认为 10 秒。 | No       |
| udpPacketSize                   | int                                       | 代理 UDP 服务时支持的最大包长度，默认为 1500，服务端和客户端的值需要一致。 | No       |
| natholeAnalysisDataReserveHours | int                                       | 打洞策略数据的保留时间，默认为 168 小时，即 7 天。           | No       |
| allowPorts                      | []PortsRange                              | 允许代理绑定的服务端端口。                                   | No       |
| httpPlugins                     | []HTTPPluginOptions                       | 服务端 HTTP 插件配置。                                       | No       |



**<span id='FWD-AuthServerConfig'>AuthServerConfig</span>**

| Field            | Type                 | Description                                                  | Required |
| :--------------- | :------------------- | :----------------------------------------------------------- | :------- |
| method           | string               | 鉴权方式，可选值为 token 或 oidc，默认为 token。             | No       |
| additionalScopes | []string             | 鉴权信息附加范围，可选值为 HeartBeats 和 NewWorkConns        | No       |
| token            | string               | 在 `method` 为 token 时生效，客户端需要设置一样的值才能鉴权通过。 | No       |
| oidc             | AuthOIDCServerConfig | oidc 鉴权配置。                                              | No       |



## 3.3、客户端配置

**ClientConfig**

| Field             | Type                                      | Description                                                  | Required |
| :---------------- | :---------------------------------------- | :----------------------------------------------------------- | :------- |
| auth              | [AuthServerConfig](#KHD-AuthServerConfig) | 客户端鉴权配置。                                             | No       |
| user              | string                                    | 用户名，设置此参数后，代理名称会被修改为 `{user}.{proxyName}`，避免代理名称和其他用户冲突。 | No       |
| serverAddr        | string                                    | 连接服务端的地址。                                           | No       |
| serverPort        | int                                       | 连接服务端的端口，默认为 7000。                              | No       |
| natHoleStunServer | string                                    | xtcp 打洞所需的 stun 服务器地址，默认为 `stun.easyvoip.com:3478`。 | No       |
| dnsServer         | string                                    | 使用 DNS 服务器地址，默认使用系统配置的 DNS 服务器，指定此参数可以强制替换为自定义的 DNS 服务器地址。 | No       |
| loginFailExit     | bool                                      | 第一次登陆失败后是否退出，默认为 true。                      | No       |
| start             | []string                                  | 指定启用部分代理，当配置了较多代理，但是只希望启用其中部分时可以通过此参数指定，默认为全部启用。 | No       |
| log               | [LogConfig](#LogConfig)                   | 日志配置。                                                   | No       |
| webServer         | [WebServerConfig](#WebServerConfig)       | 客户端 AdminServer 配置。                                    | No       |
| transport         | ClientTransportConfig                     | 客户端网络层配置。                                           | No       |
| udpPacketSize     | int                                       | 代理 UDP 服务时支持的最大包长度，默认为 1500，服务端和客户端需要保持配置一致。 | No       |
| metadatas         | map[string]string                         | 附加元数据，会传递给服务端插件，提供附加能力。               | No       |
| includes          | []string                                  | 指定额外的配置文件目录，其中的 proxy 和 visitor 配置会被读取加载。 | No       |
| proxies           | []ProxyConfig                             | 代理配置，不同的代理类型对应不同的配置，例如 TCPProxyConfig 或 HTTPProxyConfig。 | No       |
| visitors          | []VisitorConfig                           | 访问者配置，不同的访问者类型对应不同的配置，例如 STCPVisitorConfig。 | No       |



**AuthServerConfig**

| Field            | Type                 | Description                                                  | Required |
| :--------------- | :------------------- | :----------------------------------------------------------- | :------- |
| method           | string               | 鉴权方式，可选值为 `token` 或 oidc，默认为 token。           | No       |
| additionalScopes | []string             | 鉴权信息附加范围，可选值为 HeartBeats 和 NewWorkConns        | No       |
| token            | string               | 在 `method` 为 token 时生效，客户端需要设置一样的值才能鉴权通过。 | No       |
| oidc             | AuthOIDCServerConfig | oidc 鉴权配置。                                              | No       |



## 3.4、代理配置

**ProxyConfig**

| Field        | Type                | Description                                                  | Required |
| :----------- | :------------------ | :----------------------------------------------------------- | :------- |
| name         | string              | 代理名称。                                                   | Yes      |
| type         | string              | 代理类型，可选值为 tcp、udp、http、https、tcpmux、stcp、sudp、xtcp。 | Yes      |
| annotations  | map[string]string   | 代理的注释信息，会被展示在 server 的 dashboard 中。          | No       |
| transport    | ProxyTransport      | 代理网络层配置。                                             | No       |
| metadatas    | map[string]string   | 附加元数据，会传递给服务端插件，提供附加能力。               | No       |
| loadBalancer | LoadBalancerConfig  | 负载均衡配置。                                               | No       |
| healthCheck  | HealthCheckConfig   | 健康检查配置。                                               | No       |
| localIP      | string              | 被代理的本地服务 IP，默认为 `127.0.0.1`。                    | Yes      |
| localPort    | int                 | 被代理的本地服务端口。                                       | Yes      |
| plugin       | ClientPluginOptions | 客户端插件配置，如果启用了客户端插件，则不需要配置 `localIP` 和 `localPort`，流量会由客户端插件接管。不同的插件类型对应不同的配置，例如 HTTPProxyPluginOptions。 | No       |



# 4、示例

## 4.1、通过 SSH 访问内网机器

通过简单配置 TCP 类型的代理，使用户能够访问内网服务器：

1. **在具有公网 IP 的机器上部署 frps**

   部署 frps 并编辑 frps.yaml 文件。以下是简化的配置，其中设置了 frp 服务器用于接收客户端连接的端口：

   ```yaml
   bindAddr: 0.0.0.0
   bindPort: 7000
   
   auth:
     method: token
     token: Orichalcos
   
   webServer:
     addr: 0.0.0.0
     port: 8080
     user: root
     password: root
   
   log:
     to: console
     level: info
     maxDays: 3
   ```

2. **在需要被访问的内网机器上部署 frpc**

   部署 frpc 并编辑 frpc.yaml 文件，假设 frps 所在服务器的公网 IP 地址为 `x.x.x.x`。以下是示例配置：

   ```yaml
   serverAddr: x.x.x.x
   serverPort: 7000
   
   auth:
     method: token
     token: Orichalcos
   
   log:
     to: console
     level: info
     maxDays: 3
   
   proxies:
     - name: ssh
       type: tcp
       localIP: 127.0.0.1
       localPort: 22
       remotePort: 6000
       annotations:
         ssh: SSH访问
   ```

   - `localIP` 和 `localPort` 配置为需要从公网访问的内网服务的地址和端口。
   - `remotePort` 表示在 frp 服务端监听的端口，访问此端口的流量将被转发到本地服务的相应端口。

3. **启动 frps 和 frpc**

4. **通过 SSH 访问内网机器**

   使用以下命令通过 SSH 访问内网机器，假设用户名为 test：

   ```shell
   ssh -o Port=6000 test@x.x.x.x
   ```

   frp 将请求发送到 `x.x.x.x:6000` 的流量转发到内网机器的 22 端口。

