# WebSocket

在构建网络应用中，客户端（一般为浏览器）需要经常与服务端进行持续的通讯一保持双方信息的同步。出于人性化的考虑，减少用户操作，通常这种持久通讯在不刷新页面的情况下进行。

但是 HTTP 协议有一个缺陷：通信只能由客户端发起，服务器返回查询结果。HTTP 协议做不到服务器主动向客户端推送信息。这种单向请求的特点，注定了如果服务器有连续的状态变化，客户端要获知就非常麻烦。我们只能使用轮询：每隔一段时候，就发出一个询问，了解服务器有没有新的信息。



## 1、传统轮询

为了定时获取并刷新页面上的数据，客户端定时向服务器发送 Ajax 请求，服务器接到请求后马上返回响应信息并关闭连接。

结合Ajax客户端实现如下：

```javascript
setInterval(function() {
    $.get("/path/to/server", function(data, status) {
        console.log(data);
    });
}, 10000);
```

上面的程序会每隔 10 秒向服务器请求一次数据，并在数据到达后存储。这个实现方法通常可以满足简单的需求，然而同时也存在着很大的缺陷：在网络情况不稳定的情况下，服务器从接收请求、发送请求到客户端接收请求的总时间有可能超过 10 秒，而请求是以 10 秒间隔发送的，这样会导致接收的数据到达先后顺序与发送顺序不一致。于是出现了采用 setTimeout 的轮询方式：

```javascript
function poll() {
    setTimeout(function() {
        $.get("/path/to/server", function(data, status) {
            console.log(data);
            // 发起下一次请求
            poll();
        });
    }, 10000);
}
```

程序首先设置 10 秒后发起请求，当数据返回后，调用请求函数再隔10 秒发起第二次请求，以此类推。这样的话虽然无法保证两次请求之间的时间间隔为固定值，但是可以保证到达数据的顺序。

> - 优点：后端程序编写比较容易（几乎不用做什么特殊处理）
> - 缺点：因为是不断请求，服务端有没有更新数据都会返回。这就造成了请求中有大半是无用，浪费带宽和服务器资源
> - 实例：适用于小型应用



## 2、长轮询 

上面所说的传统轮询方式都存在一个严重缺陷：程序每发出一次请求就要新建一个 Http 请求。因为发起 Http 请求时会有很多头部信息，真正的请求信息几乎很少，这样就会造成资源浪费，频繁的轮询使得 Web 服务器遭受 "凌迟" 之苦。

在长轮询机制中，客户端像传统轮询一样从服务器请求数据，如果服务器没有可以立即返回给客户端的数据，则不会立刻返回一个空结果，其连接的服务器会 “hold” 住此次连接，保持这个请求等待数据到来（或者恰当的超时：小于 ajax 的超时时间），直到有新消息才返回响应信息并关闭连接，客户端处理完响应信息后再向服务器发送新的 Http 请求,以此类推。

<img src="!assets/WebSocket/20190708144449764.png" alt="img" style="" />

长轮询可能在以下 3 种情况时终止：

- 有新数据推送 。当服务器向浏览器推送信息后，应该主动结束程序运行从而让连接断开，这样浏览器才能及时收到数据
- 没有新数据推送 。应该设定一个最长时限，避免 Web 服务器超时（Timeout），若一直没有新信息，服务器应主动向浏览器发送本次轮询无新信息的正常响应，并断开连接，这也被称为 “心跳” 信息
- 网络故障或异常 。由于网络故障等因素造成的请求超时或出错也可能导致轮询的意外中断，此时浏览器将收到错误信息

> - 优点：在无消息的情况下不会频繁的请求，耗费资源小
> - 缺点：服务器 hold 住连接会消耗资源，返回数据顺序无保证，难于管理维护
> - 实例：WebQQ、Hi网页版、Facebook IM



**Web 客户端代码如下**

```javascript
//向后台长轮询消息
function longPolling(){
    $.ajax({
        async : true,//异步
        url : 'longPollingAction!getMessages.action', 
        type : 'post',
        dataType : 'json',
        data :{},
        timeout : 30000,//超时时间设定30秒
        error : function(xhr, textStatus, thrownError) {
            longPolling();//发生异常错误后再次发起请求
        },
        success : function(response) {
            message = response.data.message;
            if(message!="timeout"){
                broadcast();//收到消息后发布消息
            }
            longPolling();
        }
    });
}
```



**Web 服务端代码如下：**

```java
public class LongPollingAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private LongPollingService longPollingService;
    private static final long TIMEOUT = 20000;// 超时时间设置为20秒
 
    public String getMessages() {
        long requestTime = System.currentTimeMillis();
        result.clear();
        try {
            String msg = null;
 			// 如果超时则停止循环
            while ((System.currentTimeMillis() - requestTime) < TIMEOUT) {
                msg = longPollingService.getMessages();
                if (msg != null) {
                    break; // 跳出循环，返回数据
                } else {
                    Thread.sleep(1000);// 休眠1秒
                }
            }
            if (msg == null) {
                result.addData("message", "timeout");// 超时
            } else {
                result.addData("message", msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return SUCCESS;
    }
    
    public LongPollingService getLongPollingService() {
        return longPollingService;
    }
 
    public void setLongPollingService(LongPollingService longPollingService) {
        this.longPollingService = longPollingService;
    }
 
}
```



## 3、WebSocket

轮询的效率低，非常浪费资源（因为必须不停连接，或者 HTTP 连接始终打开）。因此，工程师们一直在思考，有没有更好的方法。WebSocket 就是这样发明的。

WebSocket 是 HTML5 开始提供的一种在单个 TCP 连接上进行全双工通讯的协议。WebSocket 通讯协议于 2011 年被 IETF 定为标准 RFC 6455，WebSocketAPI 被 W3C 定为标准。 在 WebSocket API 中，浏览器和服务器只需要做一个握手的动作，然后，浏览器和服务器之间就形成了一条快速通道。两者之间就直接可以数据互相传送。

WebSocket API 最伟大之处在于服务器和客户端可以在给定的时间范围内的任意时刻，相互推送信息。WebSocket 并不限于以 Ajax（或 XHR）方式通信，因为 Ajax 技术需要客户端发起请求，而 WebSocket 服务器和客户端可以彼此相互推送信息。

<img src="!assets/WebSocket/bg2017051502.png" alt="img" style="" />

其他特点包括：

- 建立在 TCP 协议之上，服务器端的实现比较容易

- 与 HTTP 协议有着良好的兼容性。默认端口也是 80 和 443，并且握手阶段采用 HTTP 协议，因此握手时不容易屏蔽，能通过各种 HTTP 代理服务器

- 数据格式比较轻量，性能开销小，通信高效

- 可以发送文本，也可以发送二进制数据

- 没有同源限制，客户端可以与任意服务器通信

- 协议标识符是 ws（如果加密，则为 wss），服务器网址就是 URL

	```
	ws://example.com:80/some/path
	```

<img src="!assets/WebSocket/bg2017051503.jpg" alt="img" style="" />



