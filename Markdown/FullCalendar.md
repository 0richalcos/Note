# 1、介绍

如何获取 FullCalendar 的代码，初始化日历和其他基本原则。

<br>

## 1.1、加载代码

在初始化日历之前，必须首先让 FullCalendar 的代码加载到页面上。你可以编写自己的 `<script>` 标签，也可以使用 Webpack 这样的构建系统。

<br>

**`<scritp>` 标签**

首先，从 [发布页面](https://github.com/fullcalendar/fullcalendar/releases) 下载一个 ZIP 文件。解压后，找到文件 `fullcalendar.js` 和 `fullcalendar.css`。

然后，下载 FullCalendar 所依赖的两个 JavaScript 文件：[jQuery](https://jquery.com/) 和 [Moment](https://momentjs.com/)。

然后，在页面的 `<head>` 中写下如下内容：

```html
<link rel='stylesheet' href='fullcalendar/fullcalendar.css' />
<script src='lib/jquery.min.js'></script>
<script src='lib/moment.min.js'></script>
<script src='fullcalendar/fullcalendar.js'></script>
```

在加载 FullCalendar 的 JS 文件之前，要先加载 jQuery 和 Moment 的 JS 文件。

<br>

**作为 NPM 模块（Webpack / Browserify）**

使用 NPM 安装FullCalendar：

```shell
npm install jquery moment fullcalendar
```

然后，你必须安装一个构建系统，如 Webpack 或 Browserify，它将自动捆绑所有代码。[查看使用 WebPack 的示例»](https://github.com/fullcalendar/webpack-example/tree/v3)

然后，编写一个模块，同时导入 jQuery 和 FullCalendar：

```shell
import $ from 'jquery';
import 'fullcalendar';
```

导入的 `fullcalendar` 不需要被命名。它将作为一个插件附在 jQuery 中。

<br>

## 1.2、初始化选项

将 FullCalendar 及其依赖项加载到页面上后，就可以编写初始化日历的 JS 代码。该代码必须在初始化之后执行。最好的方法是使用 jQuery 的 `$(document).ready`，例如：

```javascript
$(function() {

  // 页面已准备就绪，初始化日历...

  $('#calendar').fullCalendar({
    // 将您的选择和回调放在这里
  })

});
```

上面的代码应在页面头的 `<script>` 标签中。该代码依赖于页面正文中具有 "calendar" ID 的元素。日历将放置在此 `div` 内：

```html
<div id='calendar'></div>
```

就这样，应该可以在页面上看到一个基于月份的日历，上面没有任何事件。

<br>

**Options**

FullCalendar 的大多数文档都描述了影响日历外观或行为的选项。选项通常在初始化日历时设置，如下所示：

```javascript
$('#calendar').fullCalendar({
  weekends: false // 会隐藏周六和周日
});
```

一个重要的选项是 `defaultView`，它确定加载日历时首先显示哪个日历视图。

<br>

## 1.3、处理程序（Handlers）

处理程序（有时称为 “callbacks” ）有点像选项，但它们是在发生特殊情况时被调用的函数。在以下示例中，每当用户单击某一天时，都会出现一个警报框：

```javascript
$('#calendar').fullCalendar({
  dayClick: function() {
    alert('a day has been clicked!');
  }
});
```

<br>

**动态添加处理程序**

可以在日历初始化后使用 `on` 和 `off` 方法附加处理程序。此功能自2.4.0版起可用。

下面的示例动态绑定 `dayClick` 处理程序：

```javascript
// 获取日历对象的方便实用程序。
// 你可以直接调用日历对象上的方法。
var calendar = $('#calendar').fullCalendar('getCalendar');

calendar.on('dayClick', function(date, jsEvent, view) {
  console.log('clicked on ' + date.format());
});
```

<br>

## 1.4、方法（Methods）

方法提供了从 JavaScript 代码操纵日历的方法。方法对已初始化的日历的 jQuery 对象进行操作，使用熟悉的 `fullCalendar` 命令，但方式完全不同：

```javascript
$('#calendar').fullCalendar('next');
```

这将调用 `next` 方法，并将迫使日历转移到下个 月/周/天。

如果想在每次调用方法时都避免使用 jQuery，则可以做这样的事情：

```javascript
// 获取日历对象的方便实用程序
// 你可以直接调用日历对象上的方法。
var calendar = $('#calendar').fullCalendar('getCalendar');

calendar.next();
```

<br>

# 2、日期库

FullCalendar 将 `MomentJS` 作为其日期库。Moment 和 Duration 对象可用于很多设置，并且在整个 API 中使用它们。

<br>

## 2.1、Moment 对象

Moment 对象代表一个时间点，就像本地的 Date 对象一样，但要比它优越得多。

这个功能是由 MomentJS 提供的，这是一个第三方的开源库。FullCalendar 对这个功能做了一些扩展，以适应时间不明确和区域不明确的时刻。

[查看MomentJS文档»](https://momentjs.com/docs/)

在API中，大多数接受 Moment 的选项也会方便地接受 `moment()` 构造函数所接受的任何东西，包括：

- 日期字符串（强烈建议使用 ISO8601 标准）
- unix偏移量（自Unix Epoch以来的毫秒数）
- 本地日期对象

<br>

**从头开始创建 Moments**

大多数情况下，不必担心实例化你自己的 Moments。例如，当指定事件数组数据时，可以只写 ISO8601 字符串，让 FullCalendar 来处理解析工作。

然而，从头开始创建 Moments 有时是必要的。由于 Moment 是 FullCalendar 的一个附属品，全局的 Moment 构造器很可能对你有用。你应该能够像这样从头开始创建新的 Moments：

```javascript
var m = moment();
```

要创建一个具有 FullCalendar 扩展格式和 "模糊" 功能的时刻（见下文），请使用 FullCalendar 的 `moment`、`moment.utc` 和 moment.parseZone 构造函数的版本：

```javascript
var noTime = $.fullCalendar.moment('2014-05-01');
var local = $.fullCalendar.moment('2014-05-01T12:00:00');
var utc = $.fullCalendar.moment.utc('2014-05-01T12:00:00');
var noTZ = $.fullCalendar.moment.parseZone('2014-05-01T12:00:00');
```

要创建一个具有扩展功能的时刻，它已经在一个给定的日历的时区和地区设置范围内，使用 `Calendar` 对象的 `moment` 构造函数版本：

```javascript
var calendar = $('#calendar').fullCalendar('getCalendar');
var m = calendar.moment();
```

<br>

**模糊时间的时刻**

对于 FullCalendar，Moment 对象已经被扩展，以表示没有时间的时刻，或 "模糊时间的时刻"。在引擎盖下，这些时刻以 UTC 模式表示，时间为 `00:00:00`。

要创建一个，可以使用 FullCalendar 的 `moment`、`moment.utc` 或 `moment.parseZone` 构造函数，同时使用一个没有时间部分的 ISO8601 字符串：

```javascript
var m = $.fullCalendar.moment('2014-01-22');
m.hasTime();
=> false
```

正如你所看到的，可以通过使用 `hasTime` 方法来查询一个时刻是否有模糊的时间。这个方法只适用于通过 FullCalendar 的某个时刻构造函数创建的时刻。

也可以通过使用 `stripTime` 方法将一个有时间的时刻转换为模糊的。这个方法只适用于通过 FullCalendar 的某个时刻构造函数创建的时刻：

```javascript
var m = $.fullCalendar.moment('2014-01-22T05:00:00');
m.stripTime();
m.hasTime();
=> false
```

`format` 和 `toISOString` 方法已经被修改，这样，时间不明确的时刻就不会返回字符串中的时间部分：

```javascript
m.format();
=> "2013-01-22"
```

<br>

**模糊时区的时刻**

Moment 对象也被扩展为代表一个没有指定时区的日期。在引擎盖下，这些时刻以  UTC 模式表示。

要创建一个，可以使用 FullCalendar 的 `moment.parseZone` 构造函数的版本，同时使用一个没有时区偏移部分的 ISO8601 字符串。

```javascript
var m = $.fullCalendar.moment.parseZone('2014-01-22T06:00:00');
m.hasZone();
=> false
```

正如你所看到的，可以通过使用 `hasZone` 方法来查询一个时刻是否有模糊的区域。这个方法只适用于通过 FullCalendar 的某个时刻构造函数创建的时刻。

也可以通过使用 `stripZone` 方法将一个有时区的时刻转换成模糊的  。这个方法只适用于通过 FullCalendar 的某个时刻构造函数创建的时刻：

```javascript
var m = $.fullCalendar.moment('2014-01-22T05:00:00-07:00');
m.stripZone();
m.hasZone();
=> false
```

`format` 和 `toISOString` 方法已被修改，使模糊时区的时刻不返回字符串中的时区偏移部分：

```javascript
m.format();
=> "2014-01-22T05:00:00"
```

<br>

# 3、