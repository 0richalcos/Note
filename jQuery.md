# 1、jQuery 教程

## 1.1、jQuery 简介

jQuery 库可以通过一行简单的标记被添加到网页中。

jQuery 是一个 JavaScript 函数库。

jQuery 库包含以下特性：

- HTML 元素选取
- HTML 元素操作
- CSS 操作
- HTML 事件函数
- JavaScript 特效和动画
- HTML DOM 遍历和修改
- AJAX
- Utilities

jQuery 库位于一个 JavaScript 文件中，其中包含了所有的 jQuery 函数，可以通过下面的标记把 jQuery 添加到网页中：

```html
<head>
	<script src="jquery.js"></script>
</head>
```

请注意，<script> 标签应该位于页面的 <head> 部分。JavaScript 是 HTML5 以及所有现代浏览器中的默认脚本语言！所以不需要在 <script> 标签中使用 type="text/javascript" 。



下载 jQuery
共有两个版本的 jQuery 可供下载：一份是精简过的，另一份是未压缩的（供调试或阅读）；这两个版本都可从  [jQuery.com](http://docs.jquery.com/Downloading_jQuery#Download_jQuery)  下载。



如果不愿意在自己的计算机上存放 jQuery 库，那么可以从 Google 或 Microsoft 加载 CDN jQuery 核心文件。

```html
<!--使用 Google 的 CDN-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>

<!--使用 Microsoft 的 CDN-->
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
```

使用谷歌或微软的 jQuery，有一个很大的优势：许多用户在访问其他站点时，已经从谷歌或微软加载过 jQuery。当他们访问您的站点时，会从缓存中加载 jQuery，这样可以减少加载时间。同时，大多数 CDN 都可以确保当用户向其请求文件时，会从离用户最近的服务器上返回响应，这样也可以提高加载速度。



## 1.2、jQuery 语法

通过 jQuery，您可以选取（查询，query） HTML 元素，并对它们执行“操作”（actions）。



基础语法是：*$(selector).action()*

- 美元符号定义 jQuery
- 选择符（selector）“查询”和“查找” HTML 元素
- jQuery 的 action() 执行对元素的操作

示例：

`$(this).hide()` - 隐藏当前元素

`$("p").hide()` - 隐藏所有段落

`$(".test").hide()` - 隐藏所有 class="test" 的所有元素

`$("#test").hide()` - 隐藏所有 id="test" 的元素



**文档就绪函数**

实例中的所有 jQuery 函数位于一个 document ready 函数中：

```javascript
$(document).ready(function(){
	--- jQuery functions go here ----
});
//可以简写为
$(function(){
    --- jQuery functions go here ----
})
```

这是为了防止文档在完全加载（就绪）之前运行 jQuery 代码。

如果在文档没有完全加载之前就运行函数，操作可能失败。下面是两个具体的例子：

- 试图隐藏一个不存在的元素
- 获得未完全加载的图像的大小



## 1.3、jQuery 选择器

jQuery 元素选择器和属性选择器允许您通过标签名、属性名或内容对 HTML 元素进行选择。

选择器允许您对 HTML 元素组或单个元素进行操作。

在 HTML DOM 术语中：选择器允许您对 DOM 元素组或单个 DOM 节点进行操作。



| 选择器               | 实例                         | 选取                                       |
| -------------------- | ---------------------------- | ------------------------------------------ |
| `*`                  | `$("*")`                     | 所有元素                                   |
| `#id`                | `$("#lastname")`             | id="lastname" 的元素                       |
| `.class`             | `$(".intro")`                | 所有 class="intro" 的元素                  |
| `element`            | `$("p")`                     | 所有 <p> 元素                              |
| `.class.class`       | `$(".intro.demo")`           | 所有 class="intro" 且 class="demo" 的元素  |
|                      |                              |                                            |
| `:first`             | `$("p:first")`               | 第一个 <p> 元素                            |
| `:last`              | `$("p:last")`                | 最后一个 <p> 元素                          |
| `:even`              | `$("tr:even")`               | 所有偶数 <tr> 元素                         |
| `:odd`               | `$("tr:odd")`                | 所有奇数 <tr> 元素                         |
|                      |                              |                                            |
| `:eq(index)`         | `$("ul li:eq(3)")`           | 列表中的第四个元素（index 从 0 开始）      |
| `gt(no)`             | `$("ul li:gt(3)")`           | 列出 index 大于 3 的元素                   |
| `lt(no)`             | `$("ul li:lt(3)")`           | 列出 index 小于 3 的元素                   |
| `:not(*selector*)`   | `$("input:not(:empty)")`     | 所有不为空的 input 元素                    |
|                      |                              |                                            |
| `:header`            | `$(":header")`               | 所有标题元素 <h1> - <h6>                   |
| `:animated`          |                              | 所有动画元素                               |
|                      |                              |                                            |
| `:contains(text)`    | `$(":contains('W3School')")` | 包含指定字符串的所有元素                   |
| `:empty`             | `$(":empty")`                | 无子（元素）节点的所有元素                 |
| `:hidden`            | `$("p:hidden")`              | 所有隐藏的 <p> 元素                        |
| `:visible`           | `$("table:visible")`         | 所有可见的表格                             |
|                      |                              |                                            |
| `s1,s2,s3`           | `$("th,td,.intro")`          | 所有带有匹配选择的元素                     |
|                      |                              |                                            |
| `[attribute]`        | `$("[href]")`                | 所有带有 href 属性的元素                   |
| `[attribute=value]`  | `$("[href='#']")`            | 所有 href 属性的值等于 "#" 的元素          |
| `[attribute!=value]` | `$("[href!='#']")`           | 所有 href 属性的值不等于 "#" 的元素        |
| `[attribute$=value]` | `$("[href$='.jpg']")`        | 所有 href 属性的值包含以 ".jpg" 结尾的元素 |
|                      |                              |                                            |
| `:input`             | `$(":input")`                | 所有 <input> 元素                          |
| `:text`              | `$(":text")`                 | 所有 type="text" 的 <input> 元素           |
| `:password`          | `$(":password")`             | 所有 type="password" 的 <input> 元素       |
| `:radio`             | `$(":radio")`                | 所有 type="radio" 的 <input> 元素          |
| `:checkbox`          | `$(":checkbox")`             | 所有 type="checkbox" 的 <input> 元素       |
| `:submint`           | `$(":submit")`               | 所有 type="submit" 的 <input> 元素         |
| `:reset`             | `$(":reset")`                | 所有 type="reset" 的 <input> 元素          |
| `:button`            | `$(":button")`               | 所有 type="button" 的 <input> 元素         |
| `:image`             | `$(":image")`                | 所有 type="image" 的 <input> 元素          |
| `:file`              | `$(":file")`                 | 所有 type="file" 的 <input> 元素           |
|                      |                              |                                            |
| `:enabled`           | `$(":enabled")`              | 所有激活的 input 元素                      |
| `:disabled`          | `$(":disabled")`             | 所有禁用的 input 元素                      |
| `:selected`          | `$(":selected")`             | 所有被选取的 input 元素                    |
| `:checked`           | `$(":checked")`              | 所有被选中的 input 元素                    |



**更多的选择器实例**

| 语法                   | 描述                                                 |
| ---------------------- | ---------------------------------------------------- |
| `$(this)`              | 当前 HTML 元素                                       |
| `$("ul li:first")`     | 每个 <ul> 的第一个 <li> 元素                         |
| `$("div#intro .head")` | id="intro" 的 <div> 元素中的所有 class="head" 的元素 |



## 1.4、jQuery 事件

jQuery 是为事件处理特别设计的。



**jQuery 事件函数**

jQuery 事件处理方法是 jQuery 中的核心函数。

事件处理程序指的是当 HTML 中发生某些事件时所调用的方法。术语由事件“触发”（或“激发”）经常会被使用。

```html
<body>
    <h2>This is a heading</h2>
    <p>This is a paragraph.</p>
    <p>This is another paragraph.</p>
    <button type="button">Click me</button>
    <script>
        $(document).ready(function () {
            $("button").click(function () {
                $("p").hide();
            })
        })
    </script>
</body>
```

在上面的例子中，当按钮的点击事件被触发时会调用一个函数：

```javascript
$("button").click(function() {..some code... } )
```

该方法隐藏所有 <p> 元素：

```javascript
$("p").hide();
```



**jQuery 名称冲突**

jQuery 使用 $ 符号作为 jQuery 的简写方式。

某些其他 JavaScript 库中的函数（比如 Prototype）同样使用 $ 符号；jQuery 使用名为 noConflict() 的方法来解决该问题。

*var jq=jQuery.noConflict()*，帮助您使用自己的名称（比如 jq）来代替 $ 符号。

```html
<body>
    <p>这是一个段落。</p>
    <button>测试 jQuery</button>
    <script>
        //$ => jQuery
        $.noConflict();
        //$ => jQ
        //var jQ = jQuery.noConflict();
        jQuery(document).ready(function () {
            jQuery("button").click(function () {
                jQuery("p").text("jQuery 仍在运行！");
            });
        });
    </script>
</body>
```



**jQuery 事件**

下面是 jQuery 中事件方法的一些例子：

| Event 函数                        | 绑定函数至                                     |
| --------------------------------- | ---------------------------------------------- |
| `$(document).ready(function)`     | 将函数绑定到文档的就绪事件（当文档完成加载时） |
| `$(selector).click(function)`     | 触发或将函数绑定到被选元素的点击事件           |
| `$(selector).dblclick(function)`  | 触发或将函数绑定到被选元素的双击事件           |
| `$(selector).focus(function)`     | 触发或将函数绑定到被选元素的获得焦点事件       |
| `$(selector).mouseover(function)` | 触发或将函数绑定到被选元素的鼠标悬停事件       |



# 2、jQuery 效果 

## 2.1、jQuery 隐藏/显示

**jQuery hide() 和 show()**

通过 jQuery，可以使用 hide() 和 show() 方法来隐藏和显示 HTML 元素：

```javascript
$("#hide").click(function(){
  $("p").hide();
});

$("#show").click(function(){
  $("p").show();
});
```

语法：

```javascript
$(selector).hide(speed,callback);
$(selector).show(speed,callback);
```

可选的 speed 参数规定隐藏/显示的速度，可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是隐藏或显示完成后所执行的函数名称。



**jQuery toggle()**

通过 jQuery，您可以使用 toggle() 方法来切换 hide() 和 show() 方法。

显示被隐藏的元素，并隐藏已显示的元素：

```javascript
$("button").click(function(){
  $("p").toggle();
});
```

语法：

```javascript
$(selector).toggle(speed,callback);
```

可选的 speed 参数规定隐藏/显示的速度，可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是 toggle() 方法完成后所执行的函数名称。



## 2.2、jQuery 淡入淡出

通过 jQuery 可以实现元素的淡入淡出效果。

jQuery 拥有下面四种 fade 方法：

- fadeIn()
- fadeOut()
- fadeToggle()
- fadeTo()



**jQuery fadeIn() 方法**

jQuery fadeIn() 用于淡入已隐藏的元素。

```javascript
$("button").click(function(){
  $("#div1").fadeIn();
  $("#div2").fadeIn("slow");
  $("#div3").fadeIn(3000);
});
```

语法：

```javascript
$(selector).fadeIn(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是 fading 完成后所执行的函数名称。



**jQuery fadeOut() 方法**

jQuery fadeOut() 方法用于淡出可见元素。

```javascript
$("button").click(function(){
  $("#div1").fadeOut();
  $("#div2").fadeOut("slow");
  $("#div3").fadeOut(3000);
});
```

语法：

```javascript
$(selector).fadeOut(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是 fading 完成后所执行的函数名称。



**jQuery fadeToggle() 方法**

jQuery fadeToggle() 方法可以在 fadeIn() 与 fadeOut() 方法之间进行切换。

如果元素已淡出，则 fadeToggle() 会向元素添加淡入效果。如果元素已淡入，则 fadeToggle() 会向元素添加淡出效果。

```javascript
$("button").click(function(){
  $("#div1").fadeToggle();
  $("#div2").fadeToggle("slow");
  $("#div3").fadeToggle(3000);
});
```

语法：

```javascript
$(selector).fadeToggle(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是 fading 完成后所执行的函数名称。



**jQuery fadeTo() 方法**

jQuery fadeTo() 方法允许渐变为给定的不透明度（值介于 0 与 1 之间）。

```javascript
$("button").click(function(){
  $("#div1").fadeTo("slow",0.15);
  $("#div2").fadeTo("slow",0.4);
  $("#div3").fadeTo("slow",0.7);
});
```

语法：

```javascript
$(selector).fadeTo(speed,opacity,callback);
```

必需的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

fadeTo() 方法中必需的 opacity 参数将淡入淡出效果设置为给定的不透明度（值介于 0 与 1 之间）。

可选的 callback 参数是该函数完成后所执行的函数名称。



## 2.3、jQuery 滑动

jQuery 滑动方法可使元素上下滑动。

jQuery 拥有以下滑动方法：

- slideDown()
- slideUp()
- slideToggle()



**jQuery slideDown() 方法**

jQuery slideDown() 方法用于向下滑动元素。

```javascript
$("#flip").click(function(){
  $("#panel").slideDown();
});
```

语法：

```javascript
$(selector).slideDown(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是滑动完成后所执行的函数名称。



**jQuery slideUp() 方法**

jQuery slideUp() 方法用于向上滑动元素。

```javascript
$("#flip").click(function(){
  $("#panel").slideUp();
});
```

语法：

```javascript
$(selector).slideUp(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是滑动完成后所执行的函数名称。



**jQuery slideToggle() 方法**

jQuery slideToggle() 方法可以在 slideDown() 与 slideUp() 方法之间进行切换。

如果元素向下滑动，则 slideToggle() 可向上滑动它们。

如果元素向上滑动，则 slideToggle() 可向下滑动它们。

```javascript
$("#flip").click(function(){
  $("#panel").slideToggle();
});
```

语法：

```javascript
$(selector).slideToggle(speed,callback);
```

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是滑动完成后所执行的函数名称。



## 2.4、jQuery 动画

jQuery animate() 方法允许创建自定义的动画。



**jQuery 动画 - animate() 方法**

jQuery animate() 方法用于创建自定义动画。

```javascript
//把 <div> 元素移动到左边，直到 left 属性等于 250 像素为止
$("button").click(function(){
  $("div").animate({left:'250px'});
}); 
```

语法：

```javascript
$(selector).animate({params},speed,callback);
```

必需的 params 参数定义形成动画的 CSS 属性。

可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。

可选的 callback 参数是动画完成后所执行的函数名称。

**提示：**默认地，所有 HTML 元素都有一个静态位置，且无法移动。如需对位置进行操作，要记得首先把元素的 CSS position 属性设置为 relative、fixed 或 absolute！



**jQuery animate() - 操作多个属性**

注意，生成动画的过程中可同时使用多个属性：

```javascript
$("button").click(function(){
  $("div").animate({
    left:'250px',
    opacity:'0.5',
    height:'150px',
    width:'150px'
  });
});
```

==几乎==可以用 animate() 方法来操作所有 CSS 属性！不过，需要记住一件重要的事情：当使用 animate() 时，必须使用 Camel 标记法（驼峰）书写所有的属性名，比如，必须使用 paddingLeft 而不是 padding-left，使用 marginRight 而不是 margin-right，等等。

同时，色彩动画并不包含在核心 jQuery 库中；如果需要生成颜色动画，需要从 jQuery.com 下载 Color Animations 插件。



**jQuery animate() - 使用相对值**

也可以定义相对值（该值相对于元素的当前值）。需要在值的前面加上 += 或 -=：

```javascript
$("button").click(function(){
  $("div").animate({
    left:'250px',
    height:'+=150px',
    width:'+=150px'
  });
});
```



**jQuery animate() - 使用预定义的值**

甚至可以把属性的动画值设置为 "show"、"hide" 或 "toggle"：

```javascript
$("button").click(function(){
  $("div").animate({
    height:'toggle'
  });
});
```



**jQuery animate() - 使用队列功能**

默认地，jQuery 提供针对动画的队列功能。

这意味着如果在彼此之后编写多个 animate() 调用，jQuery 会创建包含这些方法调用的“内部”队列。然后逐一运行这些 animate 调用。

```javascript
//隐藏，如果希望在彼此之后执行不同的动画，那么需要利用队列功能
$("button").click(function(){
  var div=$("div");
  div.animate({height:'300px',opacity:'0.4'},"slow");
  div.animate({width:'300px',opacity:'0.8'},"slow");
  div.animate({height:'100px',opacity:'0.4'},"slow");
  div.animate({width:'100px',opacity:'0.8'},"slow");
});
```

```javascript
// 将<div> 元素移动到右边，然后增加文本的字号：
$("button").click(function(){
  var div=$("div");
  div.animate({left:'100px'},"slow");
  div.animate({fontSize:'3em'},"slow");
});
```



## 2.5、jQuery stop()

jQuery stop() 方法用于在动画或效果完成前对它们进行停止。

stop() 方法适用于所有 jQuery 效果函数，包括滑动、淡入淡出和自定义动画。

```javascript
$("#stop").click(function(){
  $("#panel").stop();
});
```

语法：

```javascript
$(selector).stop(stopAll,goToEnd);
```

可选的 stopAll 参数规定是否应该清除动画队列。默认是 false，即仅停止活动的动画，允许任何排入队列的动画向后执行。

可选的 goToEnd 参数规定是否立即完成当前动画。默认是 false。

因此，默认地，stop() 会清除在被选元素上指定的当前动画。



## 2.6、jQuery Callback

**jQuery 动画的问题**

许多 jQuery 函数涉及动画。这些函数也许会将 *speed* 或 *duration* 作为可选参数。

例子：**$("p").hide("slow")**

*speed* 或 *duration* 参数可以设置许多不同的值，比如 "slow", "fast", "normal" 或毫秒。

```javascript
$("button").click(function(){
  $("p").hide(1000);
});
```

由于 JavaScript 语句（指令）是逐一执行的 - 按照次序，动画之后的语句可能会产生错误或页面冲突，因为动画还没有完成。

为了避免这个情况，可以以参数的形式添加 Callback 函数。



**jQuery Callback 函数**

当动画 100% 完成后，即调用 Callback 函数。

典型的语法：

```javascript
$(selector).hide(speed,callback)
```

*callback* 参数是一个在 hide 操作完成后被执行的函数。

错误（没有 callback）

```javascript
$("p").hide(1000);
alert("The paragraph is now hidden");
```

正确（有 callback）

```javascript
$("p").hide(1000,function(){
alert("The paragraph is now hidden");
});
```



## 2.7、jQuery Chaining

通过 jQuery 可以把动作/方法链接起来。Chaining 允许在一条语句中运行多个 jQuery 方法（在相同的元素上）。



**jQuery 方法链接**

直到现在，我们都是一次写一条 jQuery 语句（一条接着另一条）；不过，有一种名为链接（chaining）的技术，允许在相同的元素上运行多条 jQuery 命令，一条接着另一条。这样的话，浏览器就不必多次查找相同的元素。

如需链接一个动作，只需简单地把该动作追加到之前的动作上。

下面的例子把 css(), slideUp(), and slideDown() 链接在一起。"p1" 元素首先会变为红色，然后向上滑动，然后向下滑动：

```javascript
$("#p1").css("color","red").slideUp(2000).slideDown(2000);
```

如果需要，我们也可以添加多个方法调用。

**提示：**当进行链接时，代码行会变得很差。不过，jQuery 在语法上不是很严格；可以按照希望的格式来写，包含折行和缩进。

```javascript
$("#p1").css("color","red")
  .slideUp(2000)
  .slideDown(2000);
```

jQuery 会抛掉多余的空格，并按照一行长代码来执行上面的代码行。



# 3、jQuery HTML

## 3.1、jQuery 获取

jQuery 拥有可操作 HTML 元素和属性的强大方法。



**获得内容 - text()、html() 以及 val()**

三个简单实用的用于 DOM 操作的 jQuery 方法：

- text() - 设置或返回所选元素的文本内容
- html() - 设置或返回所选元素的内容（包括 HTML 标记）
- val() - 设置或返回表单字段的值

```javascript
$("#btn1").click(function(){
  alert("Text: " + $("#test").text());
});
$("#btn2").click(function(){
  alert("HTML: " + $("#test").html());
});
```

```javascript
$("#btn1").click(function(){
  alert("Value: " + $("#test").val());
});
```



**获取属性 - attr()**

jQuery attr() 方法用于获取属性值。

```javascript
$("button").click(function(){
  alert($("#w3s").attr("href"));
});
```



## 3.2、jQuery 设置

**设置内容 - text()、html() 以及 val()**

- text() - 设置或返回所选元素的文本内容
- html() - 设置或返回所选元素的内容（包括 HTML 标记）
- val() - 设置或返回表单字段的值

```javascript
$("#btn1").click(function(){
  $("#test1").text("Hello world!");
});
$("#btn2").click(function(){
  $("#test2").html("<b>Hello world!</b>");
});
$("#btn3").click(function(){
  $("#test3").val("Dolly Duck");
});
```



**text()、html() 以及 val() 的回调函数**

上面的三个 jQuery 方法：text()、html() 以及 val()，同样拥有回调函数。回调函数由两个参数：被选元素列表中当前元素的下标，以及原始（旧的）值。然后以函数新值返回您希望使用的字符串。

```javascript
$("#btn1").click(function(){
  $("#test1").text(function(i,origText){
    return "Old text: " + origText + " New text: Hello world!
    (index: " + i + ")";
  });
});

$("#btn2").click(function(){
  $("#test2").html(function(i,origText){
    return "Old html: " + origText + " New html: Hello <b>world!</b>
    (index: " + i + ")";
  });
});
```



**设置属性 - attr()**

jQuery attr() 方法也用于设置/改变属性值。

```javascript
$("button").click(function(){
  $("#w3s").attr("href","http://www.w3school.com.cn/jquery");
});
```

attr() 方法也允许同时设置多个属性。

```javascript
$("button").click(function(){
  $("#w3s").attr({
    "href" : "http://www.w3school.com.cn/jquery",
    "title" : "W3School jQuery Tutorial"
  });
});
```



**attr() 的回调函数**

jQuery 方法 attr()，也提供回调函数。回调函数由两个参数：被选元素列表中当前元素的下标，以及原始（旧的）值。然后以函数新值返回您希望使用的字符串。

```javascript
$("button").click(function(){
  $("#w3s").attr("href", function(i,origValue){
    return origValue + "/jquery";
  });
});
```



## 3.3、jQuery 添加

通过 jQuery，可以很容易地添加新元素/内容。

- append() - 在被选元素的结尾插入内容
- prepend() - 在被选元素的开头插入内容
- after() - 在被选元素之后插入内容
- before() - 在被选元素之前插入内容



**jQuery append() 方法**

jQuery append() 方法在被选元素的结尾插入内容。

```javascript
$("p").append("Some appended text.");
```



**jQuery prepend() 方法**

jQuery prepend() 方法在被选元素的开头插入内容。

```javascript
$("p").prepend("Some prepended text.");
```



**通过 append() 和 prepend() 方法添加若干新元素**

append() 和 prepend() 方法能够通过参数接收无限数量的新元素。可以通过 jQuery 来生成文本/HTML（就像上面的例子那样），或者通过 JavaScript 代码和 DOM 元素。

在下面的例子中，创建若干个新元素。这些元素可以通过 text/HTML、jQuery 或者 JavaScript/DOM 来创建。然后通过 append() 方法把这些新元素追加到文本中（对 prepend() 同样有效）：

```javascript
function appendText(){
  var txt1="<p>Text.</p>";               // 以 HTML 创建新元素
  var txt2=$("<p></p>").text("Text.");   // 以 jQuery 创建新元素
  var txt3=document.createElement("p");  // 以 DOM 创建新元素
  txt3.innerHTML="Text.";
  $("p").append(txt1,txt2,txt3);         // 追加新元素
}
```



**jQuery after() 和 before() 方法**

jQuery after() 方法在被选元素之后插入内容。

jQuery before() 方法在被选元素之前插入内容。

```javascript
$("img").after("Some text after");

$("img").before("Some text before");
```



**通过 after() 和 before() 方法添加若干新元素**

after() 和 before() 方法能够通过参数接收无限数量的新元素。可以通过 text/HTML、jQuery 或者 JavaScript/DOM 来创建新元素。

在下面的例子中，创建若干个新元素。这些元素可以通过 text/HTML、jQuery 或者 JavaScript/DOM 来创建。然后通过 after() 方法把这些新元素插到文本中（对 before() 同样有效）：

```javascript
function afterText(){
  var txt1="<b>I </b>";                    // 以 HTML 创建新元素
  var txt2=$("<i></i>").text("love ");     // 通过 jQuery 创建新元素
  var txt3=document.createElement("big");  // 通过 DOM 创建新元素
  txt3.innerHTML="jQuery!";
  $("img").after(txt1,txt2,txt3);          // 在 img 之后插入新元素
}
```



## 3.4、jQuery 删除

通过 jQuery，可以很容易地删除已有的 HTML 元素。

- remove() - 删除被选元素（及其子元素）
- empty() - 从被选元素中删除子元素



**jQuery remove() 方法**

jQuery remove() 方法删除被选元素及其子元素。

```javascript
$("#div1").remove();
```



**jQuery empty() 方法**

jQuery empty() 方法删除被选元素的子元素。

```javascript
$("#div1").empty();
```



**过滤被删除的元素**

jQuery remove() 方法也可接受一个参数，允许对被删元素进行过滤；该参数可以是任何 jQuery 选择器的语法。

```javascript
//删除 class="italic" 的所有 <p> 元素：
$("p").remove(".italic");
```



## 3.5、jQuery CSS 类

jQuery 拥有若干进行 CSS 操作的方法。

- addClass() - 向被选元素添加一个或多个类
- removeClass() - 从被选元素删除一个或多个类
- toggleClass() - 对被选元素进行添加/删除类的切换操作
- css() - 设置或返回样式属性

下面的样式表将用于 3.5 的所有例子：

```css
.important{
  font-weight:bold;
  font-size:xx-large;
}

.blue{
  color:blue;
}
```



**jQuery addClass() 方法**

在添加类时，也可以选取多个元素：

```javascript
$("button").click(function(){
  $("h1,h2,p").addClass("blue");
  $("div").addClass("important");
});
```

也可以在 addClass() 方法中规定多个类：

```javascript
$("button").click(function(){
  $("#div1").addClass("important blue");
});
```



**jQuery removeClass() 方法**

```javascript
$("button").click(function(){
  $("h1,h2,p").removeClass("blue");
});
```



**jQuery toggleClass() 方法**

该方法对被选元素进行添加/删除类的切换操作：

```javascript
$("button").click(function(){
  $("h1,h2,p").toggleClass("blue");
});
```



## 3.6、jQuery css()

css() 方法设置或返回被选元素的一个或多个样式属性。



**返回 CSS 属性**

如需返回指定的 CSS 属性的值，请使用如下语法：

```javascript
css("propertyname");
```

```javascript
//返回首个匹配元素的 background-color 值：
$("p").css("background-color");
```



**设置 CSS 属性**

如需设置指定的 CSS 属性，请使用如下语法：

```javascript
css("propertyname","value");
```

```javascript
//为所有匹配元素设置 background-color 值：
$("p").css("background-color","yellow");
```



**设置多个 CSS 属性**

如需设置多个 CSS 属性，使用如下语法：

```javascript
css({"propertyname":"value","propertyname":"value",...});
```

为所有匹配元素设置 background-color 和 font-size：

```javascript
$("p").css({"background-color":"yellow","font-size":"200%"});
```



## 3.7、jQuery 尺寸

通过 jQuery，很容易处理元素和浏览器窗口的尺寸。

jQuery 提供多个处理尺寸的重要方法：

- width()
- height()
- innerWidth()
- innerHeight()
- outerWidth()
- outerHeight()



**jQuery width() 和 height() 方法**

width() 方法设置或返回元素的宽度（不包括内边距、边框或外边距）。

height() 方法设置或返回元素的高度（不包括内边距、边框或外边距）。

```javascript
//返回指定的 <div> 元素的宽度和高度：
$("button").click(function(){
  var txt="";
  txt+="Width: " + $("#div1").width() + "</br>";
  txt+="Height: " + $("#div1").height();
  $("#div1").html(txt);
});
```



**jQuery innerWidth() 和 innerHeight() 方法**

innerWidth() 方法返回元素的宽度（包括内边距）。

innerHeight() 方法返回元素的高度（包括内边距）。

```javascript
//返回指定的 <div> 元素的 inner-width/height：
$("button").click(function(){
  var txt="";
  txt+="Inner width: " + $("#div1").innerWidth() + "</br>";
  txt+="Inner height: " + $("#div1").innerHeight();
  $("#div1").html(txt);
});
```



**jQuery outerWidth() 和 outerHeight() 方法**

outerWidth() 方法返回元素的宽度（包括内边距和边框）。

outerHeight() 方法返回元素的高度（包括内边距和边框）。

```javascript
//返回指定的 <div> 元素的 outer-width/height：
$("button").click(function(){
  var txt="";
  txt+="Outer width: " + $("#div1").outerWidth() + "</br>";
  txt+="Outer height: " + $("#div1").outerHeight();
  $("#div1").html(txt);
});
```

outerWidth(true) 方法返回元素的宽度（包括内边距、边框和外边距）。

outerHeight(true) 方法返回元素的高度（包括内边距、边框和外边距）。

```javascript
$("button").click(function(){
  var txt="";
  txt+="Outer width (+margin): " + $("#div1").outerWidth(true) + "</br>";
  txt+="Outer height (+margin): " + $("#div1").outerHeight(true);
  $("#div1").html(txt);
});
```



**jQuery - 更多的 width() 和 height()**

```javascript
//返回文档（HTML 文档）和窗口（浏览器视口）的宽度和高度：
$("button").click(function(){
  var txt="";
  txt+="Document width/height: " + $(document).width();
  txt+="x" + $(document).height() + "\n";
  txt+="Window width/height: " + $(window).width();
  txt+="x" + $(window).height();
  alert(txt);
});
```

```javascript
//设置指定的 <div> 元素的宽度和高度：
$("button").click(function(){
  $("#div1").width(500).height(500);
});
```



# 4、jQuery 遍历

## 4.1、jQuery 祖先

这些 jQuery 方法用于向上遍历 DOM 树：

- parent()
- parents()
- parentsUntil()



**jQuery parent() 方法**

parent() 方法返回被选元素的直接父元素。该方法只会向上一级对 DOM 树进行遍历。

```javascript
//返回每个 <span> 元素的的直接父元素：
$(document).ready(function(){
  $("span").parent();
});
```



**jQuery parents() 方法**

parents() 方法返回被选元素的所有祖先元素，它一路向上直到文档的根元素 (<html>)。

```javascript
$(document).ready(function(){
  $("span").parents();
});
```

也可以使用可选参数来过滤对祖先元素的搜索。

```javascript
//返回所有 <span> 元素的所有祖先，并且它是 <ul> 元素：
$(document).ready(function(){
  $("span").parents("ul");
});
```



**jQuery parentsUntil() 方法**

parentsUntil() 方法返回介于两个给定元素之间的所有祖先元素。

```javascript
//返回介于 <span> 与 <div> 元素之间的所有祖先元素：
$(document).ready(function(){
  $("span").parentsUntil("div");
});
```



## 4.2、jQuery 后代

下面两个是用于向下遍历 DOM 树的 jQuery 方法：

- children()
- find()



**jQuery children() 方法**

children() 方法返回被选元素的所有直接子元素。该方法只会向下一级对 DOM 树进行遍历。

```javascript
//返回每个 <div> 元素的所有直接子元素：
$(document).ready(function(){
  $("div").children();
});
```

也可以使用可选参数来过滤对子元素的搜索。

```javascript
//下面的例子返回类名为 "1" 的所有 <p> 元素，并且它们是 <div> 的直接子元素：
$(document).ready(function(){
  $("div").children("p.1");
});
```



**jQuery find() 方法**

find() 方法返回被选元素的后代元素，一路向下直到最后一个后代。

```javascript
//返回属于 <div> 后代的所有 <span> 元素：
$(document).ready(function(){
  $("div").find("span");
});
```

```javascript
//返回 <div> 的所有后代：
$(document).ready(function(){
  $("div").find("*");
});
```



## 4.3、jQuery 同胞

同胞拥有相同的父元素。

jQuery 有许多有用的方法在 DOM 树进行水平遍历：

- siblings()
- next()
- nextAll()
- nextUntil()
- prev()
- prevAll()
- prevUntil()



**jQuery siblings() 方法**

siblings() 方法返回被选元素的所有同胞元素。

```javascript
//返回 <h2> 的所有同胞元素：
$(document).ready(function(){
  $("h2").siblings();
});
```

也可以使用可选参数来过滤对同胞元素的搜索。

```javascript
//返回属于 <h2> 的同胞元素的所有 <p> 元素：
$(document).ready(function(){
  $("h2").siblings("p");
});
```



**jQuery next() 方法**

next() 方法返回被选元素的下一个同胞元素。该方法只返回一个元素。

```javascript
//返回 <h2> 的下一个同胞元素：
$(document).ready(function(){
  $("h2").next();
})
```



**jQuery nextAll() 方法**

nextAll() 方法返回被选元素的所有跟随的同胞元素。

```javascript
//返回 <h2> 的所有跟随的同胞元素：
$(document).ready(function(){
  $("h2").nextAll();
});
```



**jQuery nextUntil() 方法**

nextUntil() 方法返回介于两个给定参数之间的所有跟随的同胞元素。

```javascript
//返回介于 <h2> 与 <h6> 元素之间的所有同胞元素：
$(document).ready(function(){
  $("h2").nextUntil("h6");
});
```



**jQuery prev(), prevAll() & prevUntil() 方法**

prev(), prevAll() 以及 prevUntil() 方法的工作方式与上面的方法类似，只不过方向相反而已：它们返回的是前面的同胞元素（在 DOM 树中沿着同胞元素向后遍历，而不是向前）。



## 4.4、jQuery 过滤

三个最基本的过滤方法是：first(), last() 和 eq()，它们允许基于其在一组元素中的位置来选择一个特定的元素。

其他过滤方法，比如 filter() 和 not() 允许选取匹配或不匹配某项指定标准的元素。



**jQuery first() 方法**

first() 方法返回被选元素的首个元素。

```javascript
//选取首个 <div> 元素内部的第一个 <p> 元素：
$(document).ready(function(){
  $("div p").first();
});
```



**jQuery last() 方法**

last() 方法返回被选元素的最后一个元素。

```javascript
//选择最后一个 <div> 元素中的最后一个 <p> 元素：
$(document).ready(function(){
  $("div p").last();
});
```



**jQuery eq() 方法**

eq() 方法返回被选元素中带有指定索引号的元素。索引号从 0 开始，因此首个元素的索引号是 0 而不是 1。

```javascript
//选取第二个 <p> 元素（索引号 1）：
$(document).ready(function(){
  $("p").eq(1);
});
```



**jQuery filter() 方法**

filter() 方法允许规定一个标准。不匹配这个标准的元素会被从集合中删除，匹配的元素会被返回。

```javascript
//返回带有类名 "intro" 的所有 <p> 元素：
$(document).ready(function(){
  $("p").filter(".intro");
});
```



**jQuery not() 方法**

not() 方法返回不匹配标准的所有元素。not() 方法与 filter() 相反。

```javascript
//返回不带有类名 "intro" 的
$(document).ready(function(){
  $("p").not(".intro");
});
```



# 5、jQuery AJAX

## 5.1、jQuery 加载

**jQuery load() 方法**

jQuery load() 方法是简单但强大的 AJAX 方法。从服务器加载数据，并把返回的数据放入被选元素中。



**语法：**

```javascript
$(selector).load(URL,data,callback);
```

必需的 *URL* 参数规定您希望加载的 URL。

可选的 *data* 参数规定与请求一同发送的查询字符串键/值对集合。

可选的 *callback* 参数是 load() 方法完成后所执行的函数名称。



**示例：**

这是示例文件（"demo_test.txt"）的内容：

```txt
<h2>jQuery and AJAX is FUN!!!</h2>
<p id="p1">This is some text in a paragraph.</p>
```

把文件 "demo_test.txt" 的内容加载到指定的 <div> 元素中：

```javascript
$("#div1").load("demo_test.txt");
```

也可以把 jQuery 选择器添加到 URL 参数。

```javascript
//把 "demo_test.txt" 文件中 id="p1" 的元素的内容，加载到指定的 <div> 元素中：
$("#div1").load("demo_test.txt #p1");
```

可选的 callback 参数规定当 load() 方法完成后所要允许的回调函数。回调函数可以设置不同的参数：

- *responseTxt* - 包含调用成功时的结果内容
- *statusTXT* - 包含调用的状态
- *xhr* - 包含 XMLHttpRequest 对象

```javascript
//load() 方法完成后显示一个提示框。如果 load() 方法已成功，则显示“外部内容加载成功！”，而如果失败，则显示错误消息：
$("button").click(function(){
  $("#div1").load("demo_test.txt",function(responseTxt,statusTxt,xhr){
    if(statusTxt=="success")
      alert("外部内容加载成功！");
    if(statusTxt=="error")
      alert("Error: "+xhr.status+": "+xhr.statusText);
  });
});
```



## 5.2、jQuery Get/Post

两种在客户端和服务器端进行请求-响应的常用方法是：GET 和 POST。

- *GET* - 从指定的资源请求数据
- *POST* - 向指定的资源提交要处理的数据

GET 基本上用于从服务器获得（取回）数据。注释：GET 方法可能返回缓存数据。

POST 也可用于从服务器获取数据。不过，POST 方法不会缓存数据，并且常用于连同请求一起发送数据。



**jQuery $.get() 方法**

$.get() 方法通过 HTTP GET 请求从服务器上请求数据。

**语法：**

```javascript
$.get(URL,callback);
```

必需的 *URL* 参数规定您希望请求的 URL。

可选的 *callback* 参数是请求成功后所执行的函数名。

**实例：**

```javascript
$("button").click(function(){
  $.get("demo_test.asp",function(data,status){
    alert("Data: " + data + "\nStatus: " + status);
  });
});
```

$.get() 的第一个参数是希望请求的 URL（"demo_test.asp"）。

第二个参数是回调函数。第一个回调参数存有被请求页面的内容，第二个回调参数存有请求的状态。



**jQuery $.post() 方法**

$.post() 方法通过 HTTP POST 请求从服务器上请求数据。

**语法：**

```javascript
$.post(URL,data,callback);
```

必需的 *URL* 参数规定您希望请求的 URL。

可选的 *data* 参数规定连同请求发送的数据。

可选的 *callback* 参数是请求成功后所执行的函数名。

**实例：**

```javascript
$("button").click(function(){
  $.post("demo_test_post.asp",
  {
    name:"Donald Duck",
    city:"Duckburg"
  },
  function(data,status){
    alert("Data: " + data + "\nStatus: " + status);
  });
});
```

$.post() 的第一个参数是希望请求的 URL ("demo_test_post.asp")。

然后连同请求（name 和 city）一起发送数据。

"demo_test_post.asp" 中的 ASP 脚本读取这些参数，对它们进行处理，然后返回结果。

第三个参数是回调函数。第一个回调参数存有被请求页面的内容，而第二个参数存有请求的状态。

