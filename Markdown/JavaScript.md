# 1、JS 教程

## 1.1、JS 使用

在 HTML 中，JavaScript 代码必须位于 `<script>` 与 `</script>` 标签之间。

> 旧的 JavaScript 例子也许会使用 **type** 属性：`<script type="text/javascript">`。
>
> type 属性不是必需的。JavaScript 是 HTML 中的默认脚本语言。

脚本可被放置与 HTML 页面的 `<body>` 或 `<head>` 部分中，或兼而有之。

> 把脚本置于 `<body>` 元素的底部，可改善显示速度，因为脚本编译会拖慢显示。

外部脚本很实用，如果相同的脚本被用于许多不同的网页。

在外部文件中放置脚本有如下优势：

- 分离了 HTML 和代码
- 使 HTML 和 JavaScript 更易于阅读和维护
- 已缓存的 JavaScript 文件可加速页面加载



## 1.2、JS Let

**ECMAScript 2015**

ES2015 引入了两个重要的 JavaScript 新关键词：`let` 和 `const`。

这两个关键字在 JavaScript 中提供了**块作用域（Block Scope）变量**和**常量**。

在 ES2015 之前，JavaScript 只有两种类型的作用域：**全局作用域**和**函数作用域**。



### 1.2.1、作用域

**全局作用域**

**全局**（在函数之外）声明的变量拥有**全局作用域**。

```javascript
var carName = "porsche";
// 此处的代码可以使用 carName
function myFunction() {
    // 此处的代码也可以使用 carName
}
```

**全局**变量可以在 JavaScript 程序中的任何位置访问。



**函数作用域**

**局部**（函数内）声明的变量拥有**函数作用域**。

```javascript
// 此处的代码不可以使用 carName
function myFunction() {
    var carName = "porsche";
    // code here CAN use carName
}
// 此处的代码不可以使用 carName
```

**局部**变量只能在它们被声明的函数内访问。



**JavaScript 块作用域**

通过 `var` 关键词声明的变量没有**块作用域**。

在块 `{}` 内声明的变量可以从块之外进行访问。

```javascript
{ 
    var x = 10; 
}
// 此处可以使用 x
```

在 ES2015 之前，JavaScript 是没有块作用域的。

可以使用 `let` 关键词声明拥有块作用域的变量。

在块 `{}` 内声明的变量无法从块外访问：

```javascript
{ 
    let x = 10;
}
// 此处不可以使用 x
```



### 1.2.2、var 和 let 的不同

**重新声明变量**

使用 `var` 关键字重新声明变量会带来问题。

在块中重新声明变量也将重新声明块外的变量：

```javascript
var x = 10;
// 此处 x 为 10
{ 
    var x = 6;
    // 此处 x 为 6
}
// 此处 x 为 6
```

使用 `let` 关键字重新声明变量可以解决这个问题。

在块中重新声明变量不会重新声明块外的变量：

```javascript
var x = 10;
// 此处 x 为 10
{ 
    let x = 6;
    // 此处 x 为 6
}
// 此处 x 为 10
```



**循环作用域**

在循环中使用 `var`：

```javascript
var i = 7;
for (var i = 0; i < 10; i++) {
    // 一些语句
}
// 此处，i 为 10
```

在循环中使用 `let`：

```javascript
let i = 7;
for (let i = 0; i < 10; i++) {
    // 一些语句
}
// 此处 i 为 7
```



**HTML 中的全局变量**

使用 JavaScript 的情况下，全局作用域是 JavaScript 环境。

在 HTML 中，全局作用域是 window 对象。

通过 `var` 关键词定义的全局变量属于 window 对象：

```javascript
var carName = "porsche";
// 此处的代码可使用 window.carName
```

通过 `let` 关键词定义的全局变量不属于 window 对象：

```javascript
let carName = "porsche";
// 此处的代码不可使用 window.carName
```



**重新声明**

允许在程序的任何位置使用 `var` 重新声明 JavaScript 变量：

```javascript
var x = 10;
// 现在，x 为 10

var x = 6;
// 现在，x 为 6
```

在相同的作用域，或在相同的块中，通过 `let` 重新声明一个 `var` 变量是不允许的，通过 `var` 重新声明一个 `let` 变量也是不允许的：：

```javascript
var x = 10;       // 允许
let x = 6;       // 不允许
{
    var x = 10;   // 允许
    let x = 6;   // 不允许
}
```

在不同的作用域或块中，通过 `let` 重新声明变量是允许的：

```javascript
let x = 6;       // 允许
{
    let x = 7;   // 允许
}
{
    let x = 8;   // 允许
}
```



**提升**

通过 `var` 声明的变量会*提升*到顶端。

```javascript
// 在此处，您可以使用 carName
var carName;
```

通过 `let` 定义的变量不会被提升到顶端。

在声明 `let` 变量之前就使用它会导致 ReferenceError。

变量从块的开头一直处于“暂时死区”，直到声明为止：

```javascript
// 在此处，您不可以使用 carName
let carName;
```



### 1.2.3、var 和 let 的相同点

**函数作用域**

在函数内声明变量时，使用 `var`和 `let` 很相似。

它们都有**函数作用域**：

```javascript
function myFunction() {
    var carName = "porsche";   // 函数作用域
}
function myFunction() {
    let carName = "porsche";   // 函数作用域
}
```



**全局作用域**

如果在块外声明声明，那么 `var` 和 `let` 也很相似。

它们都拥有**全局作用域**：

```javascript
var x = 10;       // 全局作用域
let y = 6;       // 全局作用域
```



## 1.3、JS Const

**ECMAScript 2015**

ES2015 引入了两个重要的 JavaScript 新关键词：`let` 和 `const`。

通过 `const` 定义的变量与 `let` 变量类似，但不能重新赋值：

```javascript
const PI = 3.141592653589793;
PI = 3.14;      // 会出错
PI = PI + 10;   // 也会出错
```

JavaScript `const` 变量必须在声明时赋值。



**不是真正的常数**

关键字 `const` 有一定的误导性。它没有定义常量值。它定义了对值的常量引用。

因此，我们不能更改常量原始值，但我们可以更改常量对象的属性。

```javascript
const PI = 3.141592653589793;
PI = 3.14;      // 会出错
PI = PI + 10;   // 也会出错

// 您可以创建 const 对象：
const car = {type:"porsche", model:"911", color:"Black"};
// 您可以更改属性：
car.color = "White";
// 您可以添加属性：
car.owner = "Bill";
//但是您无法重新为常量对象赋值
car = {type:"Volvo", model:"XC60", color:"White"};    // ERROR

// 您可以创建常量数组：
const cars = ["Audi", "BMW", "porsche"];
// 您可以更改元素：
cars[0] = "Honda";
// 您可以添加元素：
cars.push("Volvo");
//但是您无法重新为常量数组赋值
cars = ["Honda", "Toyota", "Volvo"];    // ERROR
```

