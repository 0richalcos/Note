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



## 1.4、JS 数据类型

**String，Number，Boolean，Undefined，Null，Object。**

JavaScript 拥有动态类型，这意味着相同变量可用作不同类型：

```javascript
var x;               // 现在 x 是 undefined
var x = 7;           // 现在 x 是数值
var x = "Bill";      // 现在 x 是字符串值
```



**String**

字符串（或文本字符串）是一串字符（比如 "Bill Gates"），字符串被引号包围，可使用单引号或双引号：

```javascript
var carName = "Porsche 911";   // 使用双引号
var carName = 'Porsche 911';   // 使用单引号
```



**Number**

JavaScript 只有一种数值类型，写数值时用不用小数点均可：

```javascript
var x1 = 34.00;     // 带小数点
var x2 = 34;        // 不带小数点
```



**Boolean**

布尔值只有两个值：`true` 或 `false`。

```javascript
var x = true;
var y = false;
```



**Undefined**

在 JavaScript 中，没有值的变量，其值是 `undefined`。typeof 也返回 `undefined`。

```javascript
var person;                  // 值是 undefined，类型是 undefined
```

任何变量均可通过设置值为 `undefined` 进行清空。其类型也将是 `undefined`。



**Null**

在 JavaScript 中，`null` 是 "nothing"。它被看做不存在的事物。 `null` 的数据类型是对象。

可以通过设置值为 null 清空对象：

```javascript
var person = null;           // 值是 null，但是类型仍然是对象
```

`Undefined` 与 `null` 的值相等，但类型不相等。



**Object**

JavaScript 对象用花括号来书写。对象属性是 `name:value` 对，由逗号分隔。

```javascript
var person = {firstName:"Bill", lastName:"Gates", age:62, eyeColor:"blue"};
```

上例中的对象（person）有四个属性：firstName、lastName、age 以及 eyeColor。



**typeof 运算符**

`typeof` 运算符返回变量或表达式的类型：

```javascript
typeof ""                  // 返回 "string"
typeof "Bill"              // 返回 "string"
typeof "Bill Gates"        // 返回 "string"
```

typeof 运算符对数组返回 "object"，因为在 JavaScript 中数组属于对象。



## 1.5、JS 函数

**JavaScript 函数是被设计为执行特定任务的代码块。JavaScript 函数会在某代码调用它时被执行。**

```javascript
function myFunction(p1, p2) {
    return p1 * p2;              // 该函数返回 p1 和 p2 的乘积
}
```



**调用函数**

```javascript
function toCelsius(fahrenheit) {
    return (5/9) * (fahrenheit-32);
}

document.getElementById("demo").innerHTML = toCelsius(77);
```

**() 运算符调用函数**，使用上面的例子，`toCelsius` 引用的是函数对象，而 `toCelsius()` 引用的是函数结果。



## 1.6、JS 对象

**访问对象属性**

可以通过以两种方式访问属性：

```javascript
objectName.propertyName
```

或者

```javascript
objectName["propertyName"]
```



**访问对象方法**

能够通过如下语法访问对象方法：

```javascript
objectName.methodName()
```

如果不使用 `()` 访问 fullName 方法，则将返回**函数定义**：

```javascript
name = person.fullName;
```



## 1.7、JS 字符串模板

**Back-Tics 语法**

模板字面量使用反引号 (``) 而不是引号 ("") 来定义字符串：

```javascript
let text = `Hello World!`;
```

通过使用模板字面量，可以在字符串中同时使用单引号和双引号：

```javascript
let text = `He's often called "Johnny"`;
```

模板字面量允许多行字符串：

```javascript
let text =
`The quick
brown fox
jumps over
the lazy dog`;
```



**插值**

模板字面量提供了一种将变量和表达式插入字符串的简单方法。

该方法称为字符串插值（string interpolation）。语法是：

```javascript
${...}
```

模板字面量允许字符串中的变量：

```javascript
let firstName = "John";
let lastName = "Doe";

let text = `Welcome ${firstName}, ${lastName}!`;
```

模板字面量允许字符串中的表达式：

```javascript
let price = 10;
let VAT = 0.25;

let total = `Total: ${(price * (1 + VAT)).toFixed(2)}`;
```



**HTML 模板**

```javascript
let header = "Templates Literals";
let tags = ["template literals", "javascript", "es6"];

let html = `<h2>${header}</h2><ul>`;
for (const x of tags) {
  html += `<li>${x}</li>`;
}

html += `</ul>`;
```



## 1.8、JS 数字

**JavaScript 只有一种数值类型。书写数值时带不带小数点均可。**

超大或超小的数可通过科学计数法来写：

```javascript
var x = 123e5;    // 12300000
var y = 123e-5;   // 0.00123
```

与许多其他编程语言不同，JavaScript 不会定义不同类型的数，比如整数、短的、长的、浮点的等等。

JavaScript 数值始终以双精度浮点数来存储，根据国际 IEEE 754 标准。

此格式用 64 位存储数值，其中 0 到 51 存储数字（片段），52 到 62 存储指数，63 位存储符号：

| 值(aka Fraction/Mantissa) | 指数              | 符号       |
| ------------------------- | ----------------- | ---------- |
| 52 bits(0 - 51)           | 11 bits (52 - 62) | 1 bit (63) |



**精度**

整数（不使用指数或科学计数法）会被精确到 15 位。

```javascript
var x = 999999999999999;   // x 将是 999999999999999
var y = 9999999999999999;  // y 将是 10000000000000000
```

小数的最大数是 17 位，但是浮点的算数并不总是 100% 精准：

```javascript
var x = 0.2 + 0.1;         // x 将是 0.30000000000000004
```

使用乘除法有助于解决上面的问题：

```javascript
var x = (0.2 * 10 + 0.1 * 10) / 10;       // x 将是 0.3
```



**NaN - 非数值**

`NaN` 属于 JavaScript 保留词，指示某个数不是合法数。

尝试用一个非数字字符串进行除法会得到 NaN（Not a Number）：

```javascript
var x = 100 / "Apple";  // x 将是 NaN（Not a Number）
```

可使用全局 JavaScript 函数 `isNaN()` 来确定某个值是否是数：

```javascript
var x = 100 / "Apple";
isNaN(x);               // 返回 true，因为 x 不是数
```

要小心 `NaN`。假如在数学运算中使用了 `NaN`，则结果也将是 `NaN`：

```javascript
var x = NaN;
var y = 5;
var z = x + y;         // z 将是 NaN
```

`NaN` 是数，`typeof NaN` 返回 `number`：

```javascript
typeof NaN;             // 返回 "number"
```



**Infinity**

`Infinity` （或 `-Infinity`）是 JavaScript 在计算数时超出最大可能数范围时返回的值。

```javascript
var myNumber = 2;

while (myNumber != Infinity) {          // 执行直到 Infinity
    myNumber = myNumber * myNumber;
}
```

除以 0（零）也会生成 `Infinity`：

```javascript
var x =  2 / 0;          // x 将是 Infinity
var y = -2 / 0;          // y 将是 -Infinity
```

`Infinity` 是数：`typeOf Infinity` 返回 `number`。

```javascript
typeof Infinity;        // 返回 "number"
```



**十六进制**

JavaScript 会把前缀为 `0x` 的数值常量解释为十六进制。

```javascript
var x = 0xFF;             // x 将是 255
```

绝不要用前导零写数字（比如 07）。

一些 JavaScript 版本会把带有前导零的数解释为八进制。

默认地，Javascript 把数显示为十进制小数。

但是可以使用 `toString()` 方法把数输出为十六进制、八进制或二进制。

```javascript
var myNumber = 128;
myNumber.toString(16);     // 返回 80
myNumber.toString(8);      // 返回 200
myNumber.toString(2);      // 返回 10000000
```



## 1.9、JS 数字方法

**Number 方法和属性**

原始值（比如 3.14 或 2016），无法拥有属性和方法（因为它们不是对象）。

但是通过 JavaScript，方法和属性也可用于原始值，因为 JavaScript 在执行方法和属性时将原始值视作对象。



**toExponential() 方法**

`toExponential()` 返回字符串值，它包含已被四舍五入并使用指数计数法的数字。

参数定义小数点后的字符数：

```javascript
var x = 9.656;
x.toExponential(2);     // 返回 9.66e+0
x.toExponential(4);     // 返回 9.6560e+0
x.toExponential(6);     // 返回 9.656000e+0
```



**toFixed() 方法**

`toFixed()` 返回字符串值，它包含了指定位数小数的数字：

```javascript
var x = 9.656;
x.toFixed(0);           // 返回 10
x.toFixed(2);           // 返回 9.66
x.toFixed(4);           // 返回 9.6560
x.toFixed(6);           // 返回 9.656000
```

`toFixed(2) ` 非常适合处理金钱。



**toPrecision() 方法**

`toPrecision()` 返回字符串值，它包含了指定长度的数字：

```javascript
var x = 9.656;
x.toPrecision();        // 返回 9.656
x.toPrecision(2);       // 返回 9.7
x.toPrecision(4);       // 返回 9.656
x.toPrecision(6);       // 返回 9.65600
```



**Number() 方法**

`Number()` 可用于把 JavaScript 变量转换为数值：

```javascript
x = true;
Number(x);        // 返回 1
x = false;     
Number(x);        // 返回 0
x = new Date();
Number(x);        // 返回 1404568027739
x = "10"
Number(x);        // 返回 10
x = "10 20"
Number(x);        // 返回 NaN
```

如果无法转换数字，则返回 `NaN`。	

`Number()` 还可以把日期转换为数字：

```javascript
Number(new Date("2019-04-15"));    // 返回 1506729600000
```

上面的 `Number()` 方法返回 1970 年 1 月 1 日至今的毫秒数。



**parseInt() 方法**

`parseInt()` 解析一段字符串并返回数值。允许空格。只返回首个数字：

```javascript
parseInt("10");         // 返回 10
parseInt("10.33");      // 返回 10
parseInt("10 20 30");   // 返回 10
parseInt("10 years");   // 返回 10
parseInt("years 10");   // 返回 NaN
```

如果无法转换为数值，则返回 `NaN` (Not a Number)。



**parseFloat() 方法**

