---
typora-copy-images-to: upload
---

此库允许在页面上创建可编辑元素。它可以与任何引擎（Bootstrap，jQuery-ui，jQuery）一起使用，包括弹出和内联模式。



# 1、快速开始

## 1.1、前端

1. 确定要使用的核心库：

   - **Bootstrap**
   - **jQuery UI**
   - **only jQuery (+ Poshytip)**

   将其引用在网页上。以下示例适用于 Bootstrap:

   ```html
   <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script> 
   <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
   ```

2. 下载相应的 X-editable 版本并将其引用在页面上：

   ```html
   <link href="bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet">
   <script src="bootstrap-editable/js/bootstrap-editable.js"></script>
   ```

   > 注意：在核心库（Bootstrap，jQuery-ui）的引用之后引用 X-ededible！

3. 标记元素应该是可编辑的。通常它是标签 `<a></a>` 并具有附加 `data-*` 属性：

   ```html
   <a href="#" id="username" data-type="text" data-pk="1" data-url="/post" data-title="Enter username">Oricha</a>
   ```

   主要属性为：

   - `data-type`：输入类型（text、textarea、select 等）
   - `data-url`：服务器端的 URL，用来处理提交的值（`/post`、`post.php` 等)
   - `data-pk`：要更新的记录的主键（数据中的 ID）
   - `id` 或 `data-name` ：要更新的字段名称（数据中的列）。
   - `value`：初始值，用于 select，其中 value 是要显示的文本的整数键。如果为空将从元素 HTML 内容中获取

4. 设置可编辑模式 **inline** 或 **popup**（默认）：

   ```javascript
   //转到内联模式
   $.fn.editable.defaults.mode = 'inline';
   ```

5. 将 `editable()` 方法应用于这些元素：

   ```html
   <a href="#" id="username">superuser</a>
   ```

   ```javascript
   $('#username').editable({
       type: 'text',
       pk: 1,
       url: '/post',
       title: 'Enter username'
   });
   ```

6. 前端准备好了！

   ![image-20220516195314317](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20220516195314317.png)

   打开页面并单击元素。输入新值并提交表单。它将发送带有新值的 AJAX 请求 `/post`，下面为请求的数据：

   ```javascript
   {
       name:  'username',  //字段名称 (数据库中的列)
       pk:    1            //主键 (记录 id)
       value: 'superuser!' //新值
   }
   ```



## 1.2、后端

X-editable 对后端部分没有限制，例如，要在服务器上验证提交的值：

- 如果值有效，则应返回 **HTTP status 200 OK**。页面上的元素将自动更新，不需要响应主体。
- 如果值无效，则应返回 **HTTP status != 200**（例如 400 Bad Rquest），并在响应正文中显示错误消息。页面上的元素不会更新，可编辑的表单将显示错误消息。

默认请求方法是 **POST**，可以通过默认配置更改它：

```javascript
$.fn.editable.defaults.ajaxOptions = {type: "PUT"};
```



**JSON 响应**

如果服务器返回 JSON，可以在响应正文中发送带有错误标志的 **HTTP status 200**，然后使用 `success()`  处理程序：

```javascript
//假设服务器响应：200 Ok {status: 'error', msg: 'field cannot be empty!'}
 
$('#username').editable({
    ...
    success: function(response, newValue) {
        if(response.status == 'error') return response.msg; //msg will be shown in editable form
    }
});
```



**在本地工作**

如果不想在服务器上发送值，只需保留空 `URL`  选项，并且在 `success()` 处理程序中处理值：

```javascript
$('#username').editable({
    type: 'text',
    title: 'Enter username',
    success: function(response, newValue) {
        userModel.set('username', newValue); //更新后台模型
    }
});
```



# 2、$().editable(options)

## 2.1、options

Options 可以通过以下方式定义：

- javascript ：`$().editable({...})`
- HTML 元素属性：`data-*`



**ajaxOptions**

- 类型：object
- 默认：null

提交 AJAX 请求的其他选项：

```javascript
ajaxOptions: {
    type: 'put',
    dataType: 'json'
}
```



**autotext**

- 类型：string
- 默认：'auto'

允许根据元素的值自动设置元素的文本。值可以为： auto、always、never：

- `auto`：仅当元素为空时才会自动设置文本
- `always`：总是从不尝试设置元素的文本
- `never`：从不尝试设置元素的文本

适用于 select 和 date。例如，如果下拉列表是 `{1: 'a', 2: 'b'}` 并且元素的 `value` 设置为 `1`，则它的 HTML 将自动设置为 `'a'`。



**display**

- 类型：function|boolean
- 默认：null

回调以执行元素文本中值的自定义显示。

如果定义为 `boolean`：

-  `null`：使用默认输入的显示。
-  `false`：不会调用任何显示方法，元素的文本永远不会更改。

如果定义为 `function` ：

`function` 参数为：

- *value*：要显示的当前值
- *response*：服务器响应（如果在 AJAX 提交后调用显示）

对于带有源（select、checklist）的输入，参数是不同的：

- *value*：要显示的当前值
- *sourceData*：当前输入的项目数组（例如下拉项）
- *response*：服务器响应（如果在 AJAX 提交后调用显示）

使用 `$.fn.editableutils.itemsByValue(value, sourceData)` 获取当前选中项：

```javascript
display: function(value, sourceData) {
   //display checklist as comma-separated values
   var html = [],
       checked = $.fn.editableutils.itemsByValue(value, sourceData);
       
   if(checked.length) {
       $.each(checked, function(i, v) { html.push($.fn.editableutils.escape(v.text)); });
       $(this).html(html.join(', '));
   } else {
       $(this).empty(); 
   }
}
```



**error**

- 类型：function
- 默认：null

错误回调，请求失败时调用（response status != 200）。 

当要解析错误响应并显示自定义消息时有用，必须返回 string（要在错误块中显示的消息）。

```javascript
error: function(response, newValue) {
    if(response.status === 500) {
        return 'Service unavailable. Please try later.';
    } else {
        return response.responseText;
    }
}
```



**params**

- 类型：object|function
- 默认：null

提交的其他参数。

- 如果定义为 `object`，它将 appended 到原始 AJAX 数据（pk、name 和 values）

- 如果定义为 `function`，返回的对象将 overwrites 原始的 AJAX 数据

```javascript
params: function(params) {
    //最初参数包含 pk, name and value
    params.a = 1;
    return params;
}
```



**selector**

- 类型：string
- 默认：null

如果提供了选择器，X-editable 将被委托给指定的目标。

适用于动态生成的 DOM 元素。 

> 请注意：委派的目标无法使用 `emptytext` 和 `autotext` 选项进行初始化，因为它们实际上只有在首次单击后才可编辑。 

应该手动将给这些元素设置`class="editable-click"`。 

另外，如果元素最初为空，则应添加 class `editable-empty`，设置 `data-value=""` 并将 emptytext 写入元素：

```html
<div id="user">
  <!-- empty -->
  <a href="#" data-name="username" data-type="text" class="editable-click editable-empty" data-value="" title="Username">Empty</a>
  <!-- non-empty -->
  <a href="#" data-name="group" data-type="select" data-source="/groups" data-value="1" class="editable-click" title="Group">Operator</a>
</div>     

<script>
$('#user').editable({
    selector: 'a',
    url: '/post',
    pk: 1
});
</script>
```



**success**

- 类型：function
- 默认：null

成功回调。在服务器上成功发送值并且 `response status = 200` 时调用。

用于处理 JSON 响应。例如，如果后端响应可以是 `{success: true}` 或者 `{success: false, msg: "server error"}` ，可以在此回调中检查它。如果它返回 string，意味着发生错误，字符串显示为错误消息。如果它返回 objec*`{newValue: <something>}`，它会覆盖用户提交的值。
否则 *newValue* 只是渲染到元素中：

```javascript
success: function(response, newValue) {
    if(!response.success) return response.msg;
}
```



**toggle**

- 类型：string
- 默认：'click'

如何切换可编辑。可以为：click、dblclick、mouseenter、manual。

设置为 `manual` 时需要手动调用 X-editable 的 `show/hide` 的方法。

> 注意：如果调用 `show` 或 `toggle` 在某些 DOM 元素的 `click` 处理程序内部，则需要应用， `e.stopPropagation()` 因为在文档上的任何单击时都会关闭容器。

```javascript
$('#edit-button').click(function(e) {
    e.stopPropagation();
    $('#username').editable('toggle');
});
```



**url**

- 类型：string|function
- 默认：null

用于提交的 URL，例如：`'/post'`。

如果定义为 `function` ，那么将将调用它而不是使用 AJAX。函数应返回延迟对象以运行失败/完成回调。

```javascript
url: function(params) {
    var d = new $.Deferred();
    if(params.value === 'abc') {
        return d.reject('error message'); //通过延迟对象返回错误 
    } else {
         //在js模型中异步保存数据
        someModel.asyncSaveMethod({
           ..., 
           success: function(){
              d.resolve();
           }
        }); 
        return d.promise();
    }
}
```



**validate**

- 类型：function
- 默认：null

用于客户端验证的函数。

如果返回字符串则表示验证未通过，字符串显示为错误。可以通过以下方式返回对象来修改提交的值`validate`: `{newValue: '...'}` 或 `{newValue: '...', msg: '...'}`

```javascript
validate: function(value) {
    if($.trim(value) == '') {
        return 'This field is required';
    }
}
```



**value**

- 类型：mixed
- 默认：element's text

输入的初始值。如果未设置，则取自元素的文本。

> 注意，如果元素的文本为空 - 文本是从值自动生成的，可以自定义（参阅 `autotext` 选项）。

例如，要显示货币符号：

```html
<a id="price" data-type="text" data-value="100"></a>
<script>
$('#price').editable({
    ...
    display: function(value) {
      $(this).text(value + '%%%~COMPRESS~PRE~20~%%%#x27;);
    } 
}) 
</script>
```



| 名称          | 类型                     | 默认             | 描述                                                         |
| :------------ | :----------------------- | :--------------- | :----------------------------------------------------------- |
| anim          | string                   | false            | 动画速度（仅限内联模式）                                     |
| defaultValue  | string\|object           | null             | 当原始字段值为空（null、undefined、''）时在输入中显示的值。  |
| disabled      | boolean                  | false            | 设置可编辑的禁用状态                                         |
| emptyclasss   | string                   | editable-empty   | 当可编辑文本为空时应用的 Css Class                           |
| emptytext     | string                   | 'Empty'          | 元素为空时显示的文本                                         |
| highlight     | string\|boolean          | #FFFF80          | 用于在更新后突出显示元素的颜色。通过 CSS3 过渡实现，适用于现代浏览器。 |
| mode          | string                   | 'popup'          | 可编辑的模式，可以是 `popup` 或 `inline`                     |
| name          | string                   | null             | 字段名称。将在服务器上提交。可以从 `id` 属性中获取           |
| onblur        | string                   | 'cancel'         | 用户在容器外部单击时的操作。可以为： cancel、submit、ignore。<br>设置 `ignore` 允许打开多个容器。 |
| pk            | string\|object\|function | null             | 可编辑对象的主键（例如，数据库中的 ID）。<br>对于复合键，使用对象，例如 `{id: 1, lang: 'en'}`。<br>可以通过函数动态计算。 |
| placement     | string                   | 'top'            | 容器相对于元素的放置。可以为：top、right、bottom、left。<br>不用于内联容器。 |
| savenochange  | boolean                  | false            | 未更改值但已提交表单时是否保存或取消值                       |
| send          | string                   | 'auto'           | 在服务器上发送数据的策略。可以为：auto、always、never。仅当定义了 pk 和 URL 时，才会在服务器上发送 'auto' 数据，否则新值将存储在本地。 |
| showbutton    | boolean\|string          | true             | 显示按钮的位置：left（true）、bottom、false。<br>不带按钮的表单自动提交。 |
| type          | string                   | 'text'           | 输入类型。可以为：text、textarea、select、date、checklist    |
| unsavedclasss | string                   | editable-unsaved | 存储值但未发送到服务器时应用的Css类（`pk` 为空或 `send = 'never'`）。如果在本地使用可编辑项并将它们一起提交，则可以将其设置为 `null` |

> **注意:** 可以修改 `$.fn.editable.defaults` 为页面上的所有可编辑元素设置默认选项。
>
> 例如，如果要强制所有元素通过 `PUT` 方法提交：`$.fn.editable.defaults.ajaxOptions = {type: "put"}`



# 3、Inputs

可以通过以下方式定义：

- javascript ：`$().editable({...})`
- HTML 元素属性：`data-*`

目前支持：

- text
- textarea
- select
- date
- datetime
- dateui
- combodate
- html5types
- checklist
- wysihtml5
- typeahead
- typeaheadjs
- select2



## 3.1、text

| 名称        | 类型    | 默认                  | 描述                                                         |
| :---------- | :------ | :-------------------- | :----------------------------------------------------------- |
| clear       | boolean | true                  | 是否显示`clear` 按钮                                         |
| escapes     | boolean | true                  | 如果为 `true`，HTML 将通过 `$.text()` 方法在元素的内容中进行转义。 <br/>如果为 `false` HTML 不会被转义，则使用 `$.html()`。 <br/>当使用自己的 `display` 功能时，此选项显然没有效果。 |
| inputclass  | string  | null                  | CSS  class 自动应用于输入                                    |
| placeholder | string  | null                  | 输入的占位符属性，输入为空时显示                             |
| tpl         | string  | `<input type="text">` | HTML 输入模板，通常不应该改变它                              |



## 3.2、textarea

| 名称        | 类型    | 默认                    | 描述                                                         |
| :---------- | :------ | :---------------------- | :----------------------------------------------------------- |
| escape      | boolean | true                    | 如果为 `true`，HTML 将通过 `$.text()` 方法在元素的内容中进行转义。 <br>如果为 `false` HTML 不会被转义，则使用 `$.html()`。 <br>当使用自己的 `display` 功能时，此选项显然没有效果。 |
| inputclass  | string  | input-large             | CSS class 自动应用于输入                                     |
| placeholder | string  | null                    | 输入的占位符属性，输入为空时显示                             |
| rows        | integer | 7                       | textarea 中的行数                                            |
| tpl         | string  | `<textarea></textarea>` | HTML 输入模板，通常不应该改变它                              |



## 3.3、select

**source**

- 类型：string | array | object | function
- 默认：null

列表的源数据。

- 如果定义为 `array`，应该以格式： `[{value: 1, text: "text1"}, {value: 2, text: "text2"}, ...]`。为了兼容性，还支持对象格式：`{"1": "text1", "2": "text2" ...}`，但它不保证元素顺序。
- 如果定义为 `string`，认为被认为是加载项目的 AJAX URL。在这种情况下，将为具有相同源和名称的字段缓存结果。另见 `sourceCache` 选项。
- 如果定义为 `function`，它应该以上面的格式返回数据。

| 名称         | 类型                                  | 默认                    | 描述                                                         |
| :----------- | :------------------------------------ | :---------------------- | :----------------------------------------------------------- |
| escape       | boolean                               | true                    | 如果为 `true`，HTML 将通过 `$.text()` 方法在元素的内容中进行转义。 <br>如果为 `false`，HTML 不会被转义，则使用 `$.html()`。<br>当使用自己的 `display` 功能时，此选项显然没有效果。 |
| inputclass   | string                                | null                    | CSS class 自动应用于输入                                     |
| prepend      | string \| array \| object \| function | false                   | 数据自动添加到下拉列表的开头                                 |
| sourceCache  | boolean                               | true                    | 如果为 `true` 并且 source **string url**，将为具有相同源的字段缓存结果。 可用于网格中的可编辑列以防止额外请求 |
| sourceError  | string                                | Error when loading list | 无法加载列表时出现错误消息（例如 AJAX 错误）                 |
| sourceOption | object\|function                      | null                    | 从服务器加载列表时，`$.ajax()` 中使用的其他 AJAX 选项。用于发送额外参数（`data` 键）或更改请求方法（`type` 键） |
| tpl          | string                                | `<select></select>`     | HTML 输入模板，通常不应该改变它                              |