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

<br>

下载 jQuery
共有两个版本的 jQuery 可供下载：一份是精简过的，另一份是未压缩的（供调试或阅读）；这两个版本都可从  [jQuery.com](http://docs.jquery.com/Downloading_jQuery#Download_jQuery)  下载。

<br>

如果不愿意在自己的计算机上存放 jQuery 库，那么可以从 Google 或 Microsoft 加载 CDN jQuery 核心文件。

```html
<!--使用 Google 的 CDN-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>

<!--使用 Microsoft 的 CDN-->
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
```

使用谷歌或微软的 jQuery，有一个很大的优势：许多用户在访问其他站点时，已经从谷歌或微软加载过 jQuery。当他们访问您的站点时，会从缓存中加载 jQuery，这样可以减少加载时间。同时，大多数 CDN 都可以确保当用户向其请求文件时，会从离用户最近的服务器上返回响应，这样也可以提高加载速度。

<br>

## 1.2、jQuery 语法

通过 jQuery，您可以选取（查询，query） HTML 元素，并对它们执行“操作”（actions）。

<br>

基础语法是：*$(selector).action()*

- 美元符号定义 jQuery
- 选择符（selector）“查询”和“查找” HTML 元素
- jQuery 的 action() 执行对元素的操作

示例：

`$(this).hide()` - 隐藏当前元素

`$("p").hide()` - 隐藏所有段落

`$(".test").hide()` - 隐藏所有 class="test" 的所有元素

`$("#test").hide()` - 隐藏所有 id="test" 的元素

<br>

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



| 选择器               | 实例                         | 选取                                          |
| -------------------- | ---------------------------- | --------------------------------------------- |
| `*`                  | `$("*")`                     | 所有元素                                      |
| `#id`                | `$("#lastname")`             | `id="lastname"` 的元素                        |
| `.class`             | `$(".intro")`                | 所有 `class="intro"` 的元素                   |
| `element`            | `$("p")`                     | 所有 `<p>` 元素                               |
| `.class.class`       | `$(".intro.demo")`           | 所有 `class="intro"` 且 `class="demo"` 的元素 |
|                      |                              |                                               |
| `:first`             | `$("p:first")`               | 第一个 `<p>` 元素                             |
| `:last`              | `$("p:last")`                | 最后一个 `<p>` 元素                           |
| `:even`              | `$("tr:even")`               | 所有偶数 `<tr>` 元素                          |
| `:odd`               | `$("tr:odd")`                | 所有奇数 `<tr>` 元素                          |
|                      |                              |                                               |
| `:eq(index)`         | `$("ul li:eq(3)")`           | 列表中的第四个元素（index 从 0 开始）         |
| `gt(no)`             | `$("ul li:gt(3)")`           | 列出 index 大于 3 的元素                      |
| `lt(no)`             | `$("ul li:lt(3)")`           | 列出 index 小于 3 的元素                      |
| `:not(*selector*)`   | `$("input:not(:empty)")`     | 所有不为空的 `<input>` 元素                   |
|                      |                              |                                               |
| `:header`            | `$(":header")`               | 所有标题元素 `<h1>` - `<h6>`                  |
| `:animated`          |                              | 所有动画元素                                  |
|                      |                              |                                               |
| `:contains(text)`    | `$(":contains('W3School')")` | 包含指定字符串的所有元素                      |
| `:empty`             | `$(":empty")`                | 无子（元素）节点的所有元素                    |
| `:hidden`            | `$("p:hidden")`              | 所有隐藏的 `<p>` 元素                         |
| `:visible`           | `$("table:visible")`         | 所有可见的表格                                |
|                      |                              |                                               |
| `s1,s2,s3`           | `$("th,td,.intro")`          | 所有带有匹配选择的元素                        |
|                      |                              |                                               |
| `[attribute]`        | `$("[href]")`                | 所有带有 href 属性的元素                      |
| `[attribute=value]`  | `$("[href='#']")`            | 所有 href 属性的值等于 "#" 的元素             |
| `[attribute!=value]` | `$("[href!='#']")`           | 所有 href 属性的值不等于 "#" 的元素           |
| `[attribute^=value]` | `$("[href^='code']")`        | 所有 href 属性的值包含以 "code" 开始的元素    |
| `[attribute$=value]` | `$("[href$='.jpg']")`        | 所有 href 属性的值包含以 ".jpg" 结尾的元素    |
| `[attribute*=value]` | `$("[href*='code']")`        | 所有 href 属性的值包含 "code" 的元素          |
|                      |                              |                                               |
| `:input`             | `$(":input")`                | 所有 `<input>` 元素                           |
| `:text`              | `$(":text")`                 | 所有 `type="text"` 的 `<input>` 元素          |
| `:password`          | `$(":password")`             | 所有 `type="password"` 的 `<input>` 元素      |
| `:radio`             | `$(":radio")`                | 所有 `type="radio"` 的 `<input>` 元素         |
| `:checkbox`          | `$(":checkbox")`             | 所有 `type="checkbox"` 的 `<input>` 元素      |
| `:submint`           | `$(":submit")`               | 所有 `type="submit"` 的 `<input>` 元素        |
| `:reset`             | `$(":reset")`                | 所有 `type="reset"` 的 `<input>` 元素         |
| `:button`            | `$(":button")`               | 所有 `type="button"` 的 `<input>` 元素        |
| `:image`             | `$(":image")`                | 所有 `type="image"` 的 `<input>` 元素         |
| `:file`              | `$(":file")`                 | 所有 `type="file"` 的 `<input>` 元素          |
|                      |                              |                                               |
| `:enabled`           | `$(":enabled")`              | 所有激活的 `<input>` 元素                     |
| `:disabled`          | `$(":disabled")`             | 所有禁用的 `<input>` 元素                     |
| `:selected`          | `$(":selected")`             | 所有被选取的 `<input>` 元素                   |
| `:checked`           | `$(":checked")`              | 所有被选中的 `<input>` 元素                   |



**更多的选择器实例**

| 语法                   | 描述                                                       |
| ---------------------- | ---------------------------------------------------------- |
| `$(this)`              | 当前 HTML 元素                                             |
| `$("ul li:first")`     | 每个 `<ul>` 的第一个 `<li>` 元素                           |
| `$("div#intro .head")` | `id="intro"` 的 `<div>` 元素中的所有 `class="head"` 的元素 |



## 1.4、jQuery 事件

jQuery 是为事件处理特别设计的。

<br>

**jQuery 事件函数**

jQuery 事件处理方法是 jQuery 中的核心函数。

事件处理程序指的是当 HTML 中发生某些事件时所调用的方法。术语由事件 “触发”（或“激发”）经常会被使用。

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

该方法隐藏所有 `<p>` 元素：

```javascript
$("p").hide();
```

<br>

**jQuery 名称冲突**

jQuery 使用 `$` 符号作为 jQuery 的简写方式。

某些其他 JavaScript 库中的函数（比如 Prototype）同样使用 `$` 符号；jQuery 使用名为 `noConflict()` 的方法来解决该问题。

`var jq=jQuery.noConflict()`，帮助您使用自己的名称（比如 `jq`）来代替 `$` 符号。

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

<br>

**jQuery 事件**

下面是 jQuery 中事件方法的一些例子：

| Event 函数                        | 绑定函数至                                     |
| --------------------------------- | ---------------------------------------------- |
| `$(document).ready(function)`     | 将函数绑定到文档的就绪事件（当文档完成加载时） |
| `$(selector).click(function)`     | 触发或将函数绑定到被选元素的点击事件           |
| `$(selector).dblclick(function)`  | 触发或将函数绑定到被选元素的双击事件           |
| `$(selector).focus(function)`     | 触发或将函数绑定到被选元素的获得焦点事件       |
| `$(selector).mouseover(function)` | 触发或将函数绑定到被选元素的鼠标悬停事件       |

<br>

**jQuery 获取当前事件对象**

有两种常用方法：

- 通过参数传入：

	```html
	<button onclick="clickEvent(this)"></button>
	...
	<script>
	    function clickEvent(event) {
	        $(event);
	    }
	</script>
	```

- 在事件方法体中通过 `$(this)` 获取：

	```html
	<button id="btn"></button>
	...
	<script>
	    $('#btn').click(function () {
	        $(this);
	    })
	</script>
	```

可以通过上面方法获取获取 `<li></li>` 的索引：

```javascript
$("ul li").click(function () {
    var index = $("ul li").index(this);
    alert(index);
 });
```



**手动触发事件**

jQuery 的 `trigger()` 可以触发被选元素的指定事件类型：

```javascript
$('obj').trigger('event');
$('btn').trigger('click');
$('input').trigger('onblur');
```



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

<br>

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

<br>

## 2.2、jQuery 淡入淡出

通过 jQuery 可以实现元素的淡入淡出效果。

jQuery 拥有下面四种 fade 方法：

- fadeIn()
- fadeOut()
- fadeToggle()
- fadeTo()

<br>

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

<br>

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

<br>

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

<br>

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

<br>

## 2.3、jQuery 滑动

jQuery 滑动方法可使元素上下滑动。

jQuery 拥有以下滑动方法：

- slideDown()
- slideUp()
- slideToggle()

<br>

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

<br>

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

<br>

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

<br>

## 2.4、jQuery 动画

jQuery animate() 方法允许创建自定义的动画。

<br>

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

<br>

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

几乎可以用 animate() 方法来操作所有 CSS 属性！不过，需要记住一件重要的事情：当使用 animate() 时，必须使用 Camel 标记法（驼峰）书写所有的属性名，比如，必须使用 paddingLeft 而不是 padding-left，使用 marginRight 而不是 margin-right，等等。

同时，色彩动画并不包含在核心 jQuery 库中；如果需要生成颜色动画，需要从 jQuery.com 下载 Color Animations 插件。

<br>

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

<br>

**jQuery animate() - 使用预定义的值**

甚至可以把属性的动画值设置为 "show"、"hide" 或 "toggle"：

```javascript
$("button").click(function(){
  $("div").animate({
    height:'toggle'
  });
});
```

<br>

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

<br>

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

<br>

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

<br>

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

<br>

## 2.7、jQuery Chaining

通过 jQuery 可以把动作/方法链接起来。Chaining 允许在一条语句中运行多个 jQuery 方法（在相同的元素上）。

<br>

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

<br>

# 3、jQuery HTML

## 3.1、jQuery 获取

jQuery 拥有可操作 HTML 元素和属性的强大方法。

<br>

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

<br>

**获取属性 - attr()**

jQuery attr() 方法用于获取属性值。

```javascript
$("button").click(function(){
  alert($("#w3s").attr("href"));
});
```

<br>

**获取属性 - prop()**

```javascript
$("button").click(function(){
    alert($("div").attr("color"));
});
```

<br>

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

<br>

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

jQuery 方法 attr()，也提供回调函数。回调函数由两个参数：被选元素列表中当前元素的下标，以及原始（旧的）值。然后以函数新值返回您希望使用的字符串。

```javascript
$("button").click(function(){
  $("#w3s").attr("href", function(i,origValue){
    return origValue + "/jquery";
  });
});
```

<br>

**设置属性 - prop()**

prop() 方法设置或返回被选元素的属性和值。功能和 attr() 基本相似，但是用法上有所不同。

在 1.9.0 的版本中：

```html
<input type="checkbox" />
<script>
    $(function() {
        $('input').click(function() {
            $(this).attr('checked');
        });
    });
</script>
```

点击 checkbox，**结果都是 undefined**，而在 1.8.3 的版本中，**结果是 checked 和 undefined**。

根据官方的建议：**要检索和更改 DOM 属性，例如表单元素的选中、选中或禁用状态，请使用 .prop（）方法。**

> property 和 attribute 的 区别：
>
> - `property` 是 DOM 中的属性，是 JavaScript 里的对象；它是与生俱来的，并不是后天赋予的。比如说，某些对象在定义时就具有某一些属性。
> - `attribute `是 HTML 标签上的特性（属性），它的值只能够是字符串；它本身没有的，后天赋予的。比如说，某些对象在创建后，自定义赋予的一些属性。
>
> 对应到 jQuery 中就是：
>
> - 对于 HTML 元素本身就带有的固有属性，或者说 W3C 标准里就包含有这些属性，更直观的说法就是，编辑器里面可以智能提示出来的一些属性，如：`src`、`href`、`value`、`class`、`name`、`id` 等。在处理时，使用 `prop()` 方法。
> - 对于 HTML 元素我们自定义的 DOM 属性，即元素本身是没有这个属性的，如：`data-*`。在处理时，使用 `attr()` 方法。

<br>

## 3.3、jQuery 添加

通过 jQuery，可以很容易地添加新元素/内容。

- append() - 在被选元素的结尾插入内容
- prepend() - 在被选元素的开头插入内容
- after() - 在被选元素之后插入内容
- before() - 在被选元素之前插入内容

<br>

**jQuery append() 方法**

jQuery append() 方法在被选元素的结尾插入内容。

```javascript
$("p").append("Some appended text.");
```

<br>

**jQuery prepend() 方法**

jQuery prepend() 方法在被选元素的开头插入内容。

```javascript
$("p").prepend("Some prepended text.");
```

<br>

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

<br>

**jQuery after() 和 before() 方法**

jQuery after() 方法在被选元素之后插入内容。

jQuery before() 方法在被选元素之前插入内容。

```javascript
$("img").after("Some text after");

$("img").before("Some text before");
```

<br>

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

<br>

## 3.4、jQuery 删除

通过 jQuery，可以很容易地删除已有的 HTML 元素。

- remove() - 删除被选元素（及其子元素）
- empty() - 从被选元素中删除子元素

<br>

**jQuery remove() 方法**

jQuery remove() 方法删除被选元素及其子元素。

```javascript
$("#div1").remove();
```

<br>

**jQuery empty() 方法**

jQuery empty() 方法删除被选元素的子元素。

```javascript
$("#div1").empty();
```

<br>

**过滤被删除的元素**

jQuery remove() 方法也可接受一个参数，允许对被删元素进行过滤；该参数可以是任何 jQuery 选择器的语法。

```javascript
//删除 class="italic" 的所有 <p> 元素：
$("p").remove(".italic");
```

<br>

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

<br>

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

<br>

**jQuery removeClass() 方法**

```javascript
$("button").click(function(){
  $("h1,h2,p").removeClass("blue");
});
```

<br>

**jQuery toggleClass() 方法**

该方法对被选元素进行添加/删除类的切换操作：

```javascript
$("button").click(function(){
  $("h1,h2,p").toggleClass("blue");
});
```

<br>

## 3.6、jQuery css()

css() 方法设置或返回被选元素的一个或多个样式属性。

<br>

**返回 CSS 属性**

如需返回指定的 CSS 属性的值，请使用如下语法：

```javascript
css("propertyname");
```

```javascript
//返回首个匹配元素的 background-color 值：
$("p").css("background-color");
```

<br>

**设置 CSS 属性**

如需设置指定的 CSS 属性，请使用如下语法：

```javascript
css("propertyname","value");
```

```javascript
//为所有匹配元素设置 background-color 值：
$("p").css("background-color","yellow");
```

<br>

**设置多个 CSS 属性**

如需设置多个 CSS 属性，使用如下语法：

```javascript
css({"propertyname":"value","propertyname":"value",...});
```

为所有匹配元素设置 background-color 和 font-size：

```javascript
$("p").css({"background-color":"yellow","font-size":"200%"});
```

<br>

## 3.7、jQuery 尺寸

通过 jQuery，很容易处理元素和浏览器窗口的尺寸。

jQuery 提供多个处理尺寸的重要方法：

- width()
- height()
- innerWidth()
- innerHeight()
- outerWidth()
- outerHeight()

<br>

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

<br>

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

<br>

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

<br>

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

<br>

# 4、jQuery 遍历

## 4.1、jQuery 祖先

这些 jQuery 方法用于向上遍历 DOM 树：

- parent()
- parents()
- parentsUntil()

<br>

**jQuery parent() 方法**

parent() 方法返回被选元素的直接父元素。该方法只会向上一级对 DOM 树进行遍历。

```javascript
//返回每个 <span> 元素的的直接父元素：
$(document).ready(function(){
  $("span").parent();
});
```

<br>

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

<br>

**jQuery parentsUntil() 方法**

parentsUntil() 方法返回介于两个给定元素之间的所有祖先元素。

```javascript
//返回介于 <span> 与 <div> 元素之间的所有祖先元素：
$(document).ready(function(){
  $("span").parentsUntil("div");
});
```

<br>

## 4.2、jQuery 后代

下面两个是用于向下遍历 DOM 树的 jQuery 方法：

- children()
- find()

<br>

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

<br>

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

<br>

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

<br>

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

<br>

**jQuery next() 方法**

next() 方法返回被选元素的下一个同胞元素。该方法只返回一个元素。

```javascript
//返回 <h2> 的下一个同胞元素：
$(document).ready(function(){
  $("h2").next();
})
```

<br>

**jQuery nextAll() 方法**

nextAll() 方法返回被选元素的所有跟随的同胞元素。

```javascript
//返回 <h2> 的所有跟随的同胞元素：
$(document).ready(function(){
  $("h2").nextAll();
});
```

<br>

**jQuery nextUntil() 方法**

nextUntil() 方法返回介于两个给定参数之间的所有跟随的同胞元素。

```javascript
//返回介于 <h2> 与 <h6> 元素之间的所有同胞元素：
$(document).ready(function(){
  $("h2").nextUntil("h6");
});
```

<br>

**jQuery prev(), prevAll() & prevUntil() 方法**

`prev()`、`prevAll()` 以及 `prevUntil()` 方法的工作方式与上面的方法类似，只不过方向相反而已：它们返回的是前面的同胞元素（在 DOM 树中沿着同胞元素向后遍历，而不是向前）。

<br>

## 4.4、jQuery 过滤

三个最基本的过滤方法是：`first()`、`last()` 和 `eq()`，它们允许基于其在一组元素中的位置来选择一个特定的元素。

其他过滤方法，比如 `filter()` 和 `not()` 允许选取匹配或不匹配某项指定标准的元素。

<br>

**jQuery first() 方法**

first() 方法返回被选元素的首个元素。

```javascript
//选取首个 <div> 元素内部的第一个 <p> 元素：
$(document).ready(function(){
  $("div p").first();
});
```

<br>

**jQuery last() 方法**

last() 方法返回被选元素的最后一个元素。

```javascript
//选择最后一个 <div> 元素中的最后一个 <p> 元素：
$(document).ready(function(){
  $("div p").last();
});
```

<br>

**jQuery eq() 方法**

eq() 方法返回被选元素中带有指定索引号的元素。索引号从 0 开始，因此首个元素的索引号是 0 而不是 1。

```javascript
//选取第二个 <p> 元素（索引号 1）：
$(document).ready(function(){
  $("p").eq(1);
});
```

<br>

**jQuery filter() 方法**

filter() 方法允许规定一个标准。不匹配这个标准的元素会被从集合中删除，匹配的元素会被返回。

```javascript
//返回带有类名 "intro" 的所有 <p> 元素：
$(document).ready(function(){
  $("p").filter(".intro");
});
```

<br>

**jQuery not() 方法**

not() 方法返回不匹配标准的所有元素。not() 方法与 filter() 相反。

```javascript
//返回不带有类名 "intro" 的
$(document).ready(function(){
  $("p").not(".intro");
});
```

<br>

## 4.5、each()/map()

**each()**

each() 方法规定为每个匹配元素规定运行的函数。

> 返回 false 可用于及早停止循环。

**语法：**

```javascript
$(selector).each(function(index,element))
```

| 参数                        | 描述                                                         |
| --------------------------- | ------------------------------------------------------------ |
| function ( index, element ) | 必需。为每个匹配元素规定运行的函数。<br> - *index*：选择器的 index 的位置<br> - *element*：当前的元素（也可用 `this` 选择器） |

**实例：**

```html

<body>
<button>输出每个列表项的值</button>
<ul>
    <li>Coffee</li>
    <li>Milk</li>
    <li>Soda</li>
</ul>
<script type="text/javascript">
$(document).ready(function(){
  $("button").click(function(){
    $("li").each(function(){
      alert($(this).text())
    });
  });
});
</script>
</body>
```

```
Coffee
Milk
Soda
```

<br>

**map()**

map() 方法用于使用指定函数处理数组中的每个元素(或对象的每个属性)，并将处理结果封装为新的数组返回。

> 在jQuery 1.6 之前，该函数只支持遍历数组；从 1.6 开始，该函数也支持遍历对象。
> map() 还会为函数传入两个参数：其一是当前迭代的元素或属性值，其二是当前迭代项的数组索引或对象属性名。
> 该函数返回值将作为结果数组中的一个元素，如果返回值为 null 或 undefined，则不会被添加到结果数组中。

**语法：**

```javascript
$.map( object, callback )
```

| 参数                           | 描述                                        |
| ------------------------------ | ------------------------------------------- |
| object                         | Array/Object类型 指定的需要处理的数组或对象 |
| callback ( domElement, index ) | 对当前集合中的每个元素调用的函数对象。      |

**实例：**

```html
<body>
<div></div>
<p></p>
<span></span>
<script>
$(function () { 
	var arr = [ "a", "b", "c", "d", "e" ];
	$("div").text(arr.join(", "));
	arr = $.map(arr, function(n, i){
		return (n.toUpperCase() + i);
	});
	$("p").text(arr.join(", "));
	arr = $.map(arr, function (a) {
		return a + a;
	});
	$("span").text(arr.join(", "));
})
</script>
</body>
```

```
a, b, c, d, e
A0, B1, C2, D3, E4
A0A0, B1B1, C2C2, D3D3, E4E4
```

<br>

# 5、jQuery AJAX

## 5.1、jQuery Load

**jQuery load() 方法**

jQuery `load()` 方法是简单但强大的 AJAX 方法。从服务器加载数据，并把返回的数据放入被选元素中。

<br>

**语法：**

```javascript
$(selector).load(URL,data,callback);
```

必需的 *URL* 参数规定您希望加载的 URL。

可选的 *data* 参数规定与请求一同发送的查询字符串键/值对集合。

可选的 *callback* 参数是 `load()` 方法完成后所执行的函数名称。

<br>

**示例：**

这是示例文件（"demo_test.txt"）的内容：

```txt
<h2>jQuery and AJAX is FUN!!!</h2>
<p id="p1">This is some text in a paragraph.</p>
```

把文件 "demo_test.txt" 的内容加载到指定的 `<div>` 元素中：

```javascript
$("#div1").load("demo_test.txt");
```

也可以把 jQuery 选择器添加到 URL 参数。

```javascript
//把 "demo_test.txt" 文件中 id="p1" 的元素的内容，加载到指定的 <div> 元素中：
$("#div1").load("demo_test.txt #p1");
```

可选的 *callback* 参数规定当 `load()` 方法完成后所要允许的回调函数。回调函数可以设置不同的参数：

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

<br>

## 5.2、jQuery Get/Post

两种在客户端和服务器端进行请求-响应的常用方法是：GET 和 POST。

- *GET* - 从指定的资源请求数据
- *POST* - 向指定的资源提交要处理的数据

GET 基本上用于从服务器获得（取回）数据。注释：GET 方法可能返回缓存数据。

POST 也可用于从服务器获取数据。不过，POST 方法不会缓存数据，并且常用于连同请求一起发送数据。

<br>

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

<br>

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

<br>

## 5.3、.serialize()

serialize() 方法通过序列化表单值，创建 URL 编码文本字符串。

可以选择一个或多个表单元素（比如 input 及/或 文本框），或者 form 元素本身。

序列化的值可在生成 AJAX 请求时用于 URL 查询字符串中。

**语法：**

```javascript
$(selector).serialize()
```

**实例：**

```html
<form>
    <div><input type="text" name="a" value="1" id="a"/></div>
    <div><input type="text" name="b" value="2" id="b"/></div>
    <div><input type="hidden" name="c" value="3" id="c"/></div>
    <div>
        <textarea name="d" rows="8" cols="40">4</textarea>
    </div>
    <div>
        <select name="e">
            <option value="5" selected="selected">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
        </select>
    </div>
    <div>
        <input type="checkbox" name="f" value="8" id="f"/>
    </div>
    <div>
        <input type="submit" name="g" value="Submit" id="g"/>
    </div>
</form>
<script>
    $(function () {
        $('form').submit(function() {
            alert($(this).serialize());
            return false;
        });
    });
</script>
```

输出标准的查询字符串：

```
a=1&b=2&c=3&d=4&e=5
```

> 只会将 ”成功的控件“ 序列化为字符串。如果不使用按钮来提交表单，则不对提交按钮的值序列化。如果要表单元素的值包含到序列字符串中，元素必须使用 name 属性。

<br>

## 5.4、.serializeArray()

serializeArray() 方法通过序列化表单值来创建对象数组（名称和值）。

可以选择一个或多个表单元素（比如 input 及/或 textarea），或者 form 元素本身。

**语法：**

```javascript
$(selector).serializeArray()
```

**实例：**

```html
<form>
    <div><input type="text" name="a" value="1" id="a"/></div>
    <div><input type="text" name="b" value="2" id="b"/></div>
    <div><input type="hidden" name="c" value="3" id="c"/></div>
    <div>
        <textarea name="d" rows="8" cols="40">4</textarea>
    </div>
    <div>
        <select name="e">
            <option value="5" selected="selected">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
        </select>
    </div>
    <div>
        <input type="checkbox" name="f" value="8" id="f"/>
    </div>
    <div>
        <input type="submit" name="g" value="Submit" id="g"/>
    </div>
</form>
<script>
    $(function () {
        $('form').submit(function() {
            console.log($(this).serializeArray());
            return false;
        });
    });
</script>
```

此方法返回的是 JSON 对象而非 JSON 字符串，需要使用插件或者第三方库进行字符串化操作。返回的 JSON 对象是由一个对象数组组成的，其中每个对象包含一个或两个名值对：name 参数和 value 参数（如果 value 不为空的话）。

上面的代码产生下面的数据结构（假设浏览器支持 console.log）：

```
[
  {
    name: a
    value: 1
  },
  {
    name: b
    value: 2
  },
  {
    name: c
    value: 3
  },
  {
    name: d
    value: 4
  },
  {
    name: e
    value: 5
  }
]
```

<br>

## 5.5、jQuery Param

有关 URL 操作的工具函数，暂时也只有一种，那就是`$.param()`方法。在 jQuery 中，我们可以使用`$.param()`方法将数组或对象转化为字符串序列，以便用于 URL 查询字符串或 Ajax 请求。

<br>

**语法：**

```javascript
$.param(obj或array)
```

**实例：**

```html
<head>
    <meta charset="utf-8" />
    <title></title>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script>
        $(function () {
            var person = {
                name:"Orichalcos",
                age:25
            }
            var str = $.param(person);
            console.log(str);
        })
    </script>
</head>
```

控制台输出结果：

```
name=Orichalcos&age=25
```

<br>

## 5.6、jQuery When

### 5.6.1、什么是deferred对象？

开发网站的过程中，我们经常遇到某些耗时很长的 javascript 操作。其中，既有异步的操作（比如 ajax 读取服务器数据），也有同步的操作（比如遍历一个大型数组），它们都不是立即能得到结果的。

通常的做法是，为它们指定回调函数（callback）。即事先规定，一旦它们运行结束，应该调用哪些函数。

但是，在回调函数方面，jQuery 的功能非常弱。为了改变这一点，jQuery 开发团队就设计了 deferred 对象。

简单说，deferred 对象就是 jQuery 的回调函数解决方案。在英语中，defer 的意思是"延迟"，所以 deferred 对象的含义就是 "延迟" 到未来某个点再执行。

<br>

### 5.6.2、ajax 操作的链式写法

jQuery 的 ajax 操作的传统写法：

```javascript
$.ajax({
    url: "test.html",
    success: function(){
        alert("哈哈，成功了！");
    },
    error:function(){
        alert("出错啦！");
    }
});
```

在上面的代码中，`$.ajax()` 接受一个对象参数，这个对象包含两个方法：`success()` 方法指定操作成功后的回调函数，`error()` 方法指定操作失败后的回调函数。

`$.ajax()` 操作完成后，如果使用的是低于 1.5.0 版本的 jQuery，返回的是 XHR 对象，你没法进行链式操作；如果高于 1.5.0 版本，返回的是 deferred 对象，可以进行链式操作。

现在，新的写法是这样的：

```javascript
$.ajax("test.html")
    .done(function(){ alert("哈哈，成功了！"); })
    .fail(function(){ alert("出错啦！"); });
```

可以看到，`done()` 相当于 `success()` 方法，`fail()` 相当于 `error()` 方法。采用链式写法以后，代码的可读性大大提高。

有时为了省事，可以把 `done()` 和 `fail()` 合在一起写，这就是 `then()` 方法。

```javascript
$.when($.ajax( "/main.php" ))
    .then(successFunc, failureFunc );
```

如果 `then()` 有两个参数，那么第一个参数是 `done()` 方法的回调函数，第二个参数是 `fail()` 方法的回调方法。如果 `then()` 只有一个参数，那么等同于 `done()`。

另外，还有个 `always()` 方法，不管调用的是 `deferred.resolve()` 还是 `deferred.reject()`，最后总是执行。

<br>

### 5.6.3、为操作指定多个回调函数

deferred 对象的一大好处，就是它允许自由添加多个回调函数。

还是以上面的代码为例，如果 ajax 操作成功后，除了原来的回调函数，我还想再运行一个回调函数，怎么办？

很简单，直接把它加在后面就行了。

```javascript
$.ajax("test.html")
    .done(function(){ alert("哈哈，成功了！");} )
    .fail(function(){ alert("出错啦！"); } )
    .done(function(){ alert("第二个回调函数！");} );
```

回调函数可以添加任意多个，它们按照添加顺序执行。

<br>

### 5.6.4、为多个操作指定回调函数

deferred 对象的另一大好处，就是它允许你为多个事件指定一个回调函数，这是传统写法做不到的。

请看下面的代码，它用到了一个新的方法 $.when()：

```javascript
$.when($.ajax("test1.html"), $.ajax("test2.html"))
    .done(function(){ alert("哈哈，成功了！"); })
    .fail(function(){ alert("出错啦！"); });
```

这段代码的意思是，先执行两个操作 ` $.ajax("test1.html")` 和 `$.ajax("test2.html")`，如果都成功了，就运行 done() 指定的回调函数；如果有一个失败或都失败了，就执行 fail() 指定的回调函数。

<br>

### 5.6.5、普通操作的回调函数接口

deferred 对象的最大优点，就是它把这一套回调函数接口，从 ajax 操作扩展到了所有操作。也就是说，任何一个操作，不管是 ajax 操作还是本地操作，也不管是异步操作还是同步操作，都可以使用 deferred 对象的各种方法，指定回调函数。

假定有一个很耗时的操作wait：

```javascript
var wait = function(){
    var tasks = function(){
        alert("执行完毕！");
    };
    setTimeout(tasks,5000);
};
```

我们为它指定回调函数，应该怎么做呢？

很自然的，你会想到，可以使用 $.when()：

```javascript
$.when(wait())
    .done(function(){ alert("哈哈，成功了！"); })
    .fail(function(){ alert("出错啦！"); });
```

但是这样写的话 done() 方法会立即执行，起不到回调函数的作用。原因在于 `$.when()` 的参数只能是 deferred 对象，所以必须对 wait() 进行改写：

```javascript
var wait = function(){
    var dtd = $.Deferred(); // 新建一个deferred对象
    var tasks = function(){
        alert("执行完毕！");
        dtd.resolve(); // 改变deferred对象的执行状态
    };
    setTimeout(tasks,5000);
    return dtd.promise(); // 返回promise对象
};
```

现在，wait() 函数返回的是 deferred 对象，这就可以加上链式操作了。

```javascript
$.when(wait(dtd))
    .done(function(){ alert("哈哈，成功了！"); })
    .fail(function(){ alert("出错啦！"); });
```

> jQuery 规定，deferred 对象有三种执行状态：未完成，已完成和已失败。如果执行状态是 "已完成"（resolved），deferred 对象立刻调用 done() 方法指定的回调函数；如果执行状态是 "已失败"，调用 fail() 方法指定的回调函数；如果执行状态是 "未完成"，则继续等待，或者调用progress() 方法指定的回调函数（jQuery1.7 版本添加）。

类似的，还存在一个 `deferred.reject()` 方法，作用是将 dtd 对象的执行状态从 "未完成" 改为 "已失败"，从而触发 fail() 方法。

<br>

# 7、jQuery.extend()

`jQuery.extend()` 函数用于将一个或多个对象的内容合并到目标对象。

语法：

```javascript
$.extend( target [, object1 ] [, objectN ] )
```

指示是否深度合并：

```javascript
$.extend( [deep ], target, object1 [, objectN ] )
```

| 参数      | 描述                                                         |
| :-------- | :----------------------------------------------------------- |
| *deep*    | 可选。 Boolean 类型，指示是否深度合并对象，默认为 `false`。如果该值为 `true`，且多个对象的某个同名属性也都是对象，则该 "属性对象" 的属性也将进行合并。 |
| *target*  | Object 类型，目标对象，其他对象的成员属性将被附加到该对象上。 |
| *object1* | 可选。 Object类型，第一个被合并的对象。                      |
| *objectN* | 可选。 Object类型，第 *N* 个被合并的对象。                   |

> 1. 如果只为 `$.extend()` 指定了一个参数，则意味着参数 *target* 被省略。此时，*target* 就是 jQuery 对象本身。通过这种方式，我们可以为全局对象 jQuery 添加新的函数。
> 2. 如果多个对象具有相同的属性，则后者会覆盖前者的属性值。

<br>

**示例：**

遍历数组元素，并修改第一个对象：

```javascript
var object1 = {
    apple: 0,
    banana: {weight: 52, price: 100},
    cherry: 97
};
var object2 = {
    banana: {price: 200},
    durian: 100
};
/* object2 合并到 object1 中 */
$.extend(object1, object2);
var printObj = typeof JSON != "undefined" ? JSON.stringify : function(obj) {
    var arr = [];
    $.each(obj, function(key, val) {
        var next = key + ": ";
        next += $.isPlainObject(val) ? printObj(val) : val;
        arr.push( next );
    });
    return "{ " +  arr.join(", ") + " }";
};
$("#log").append( printObj(object1) ); //{"apple":0,"banana":{"price":200},"cherry":97,"durian":100}
```

