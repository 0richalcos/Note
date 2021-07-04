# 1、概述

**导语**

相信无论是前端还是后端开发，都或多或少地被接口文档折磨过。前端经常抱怨后端给的接口文档与实际情况不一致。后端又觉得编写及维护接口文档会耗费不少精力，经常来不及更新。其实无论是前端调用后端，还是后端调用后端，都期望有一个好的接口文档。但是这个接口文档对于程序员来说，就跟注释一样，经常会抱怨别人写的代码没有写注释，然而自己写起代码起来，最讨厌的，也是写注释。所以仅仅只通过强制来规范大家是不够的，随着时间推移，版本迭代，接口文档往往很容易就跟不上代码了。



**什么是 Swagger？**

发现了痛点就要去找解决方案。解决方案用的人多了，就成了标准的规范，这就是 Swagger 的由来。

Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。总体目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法、参数和模型紧密集成到服务器端的代码，允许 API 来始终保持同步。Swagger 让部署管理和使用功能强大的 API 从未如此简单。

这个解释简单点来讲就是说，Swagger 是一款可以根据 RESTful 风格生成接口开发文档，并且支持做测试的一款中间软件。



**为什么要使用 Swagger？**

- 对于后端开发人员来说：
  不用再手写 WiKi 接口拼大量的参数，避免手写错误
  对代码侵入性低，采用全注解的方式，开发简单
  方法参数名修改、增加、减少参数都可以直接生效，不用手动维护
  缺点：增加了开发成本，写接口还得再写一套参数配置
- 对于前端开发来说
  后端只需要定义好接口，会自动生成文档，接口功能、参数一目了然
  联调方便，如果出问题，直接测试接口，实时检查参数和返回值，就可以快速定位是前端还是后端的问题
- 对于测试
  对于某些没有前端界面UI的功能，可以用它来测试接口
  操作简单，不用了解具体代码就可以操作
