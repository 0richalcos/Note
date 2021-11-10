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





## 1.5、JS 对象

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



## 1.6、JS 字符串搜索

**String.search()**

`search()` 方法在字符串中搜索指定值并返回匹配的位置：

```javascript
let str = "Please locate where 'locate' occurs!";
str.search("locate")     // 返回 7
```

关于 `indexOf()` 和 `search()` ：

1. 都可以进行字符串的查找
2. serach() 支持正则，indexOf() 不支持
3. indexOf() 性能更高



**String.match()**

`match()` 方法根据正则表达式在字符串中搜索匹配项，并将匹配项作为 Array 对象返回。

在字符串中搜索 "ain"：

```javascript
let text = "The rain in SPAIN stays mainly in the plain";
text.match(/ain/g)    // 返回数组 [ain,ain,ain]
```



**String.includes()**

如果字符串包含指定值，`includes()` 方法返回 true。

`includes()` 方法可以选择填写第二个参数，该参数默认为 0，表示开始搜索的位置。

```javascript
let text = "Hello world, welcome to the universe.";
text.includes("world")    // 返回 true
```

`includes()` 方法还能检测数组中是否包含某一个元素，它和 `indexOf` 方法的区别是：

1. indexOf() 返回的是数值类型，而 includes() 返回的是布尔类型
2. 数组中的 indexOf() 不能判断数组中是否有 NaN，而 includes() 可以做到



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

`parseFloat()` 解析一段字符串并返回数值。允许空格。只返回首个数字：

```javascript
parseFloat("10");        // 返回 10
parseFloat("10.33");     // 返回 10.33
parseFloat("10 20 30");  // 返回 10
parseFloat("10 years");  // 返回 10
parseFloat("years 10");  // 返回 NaN
```

如果无法转换为数值，则返回 `NaN (Not a Number)`。



## 2.0、JS 数组方法

**把数组转换为字符串**

JavaScript 方法 `toString()` 把数组转换为数组值（逗号分隔）的字符串。

```javascript
var fruits = ["Banana", "Orange", "Apple", "Mango"];
document.getElementById("demo").innerHTML = fruits.toString(); 
//结果 Banana,Orange,Apple,Mango
```



**Popping 和 Pushing**

在处理数组时，删除元素和添加新元素是很简单的。

Popping 和 Pushing 指的是：从数组弹出项目，或向数组推入项目。

`pop()` 方法从数组中删除最后一个元素并返回被删除的值：：

```javascript
var fruits = ["Banana", "Orange", "Apple", "Mango"];
var x = fruits.pop();              // 从 fruits 删除最后一个元素（"Mango"），x 的值是 "Mango"
```

`push()` 方法（在数组结尾处）向数组添加一个新的元素并返回新数组的长度：：

```javascript
var fruits = ["Banana", "Orange", "Apple", "Mango"];
var x = fruits.push("Kiwi");       //  向 fruits 添加一个新元素，x 的值是 5
```



**位移元素**

位移与弹出等同，但处理首个元素而不是最后一个。

`shift()` 方法会删除首个数组元素，并把所有其他元素“位移”到更低的索引。`shift()` 方法还会返回被“位移出”的字符串：

```javascript
var fruits = ["Banana", "Orange", "Apple", "Mango"];
var x = fruits.shift();            // 从 fruits 删除第一个元素 "Banana"，x 的值是 "Banana"
```

`unshift()` 方法（在开头）向数组添加新元素，并“反向位移”旧元素。`unshift()` 方法还会返回新数组的长度：

```javascript
var fruits = ["Banana", "Orange", "Apple", "Mango"];
var x = fruits.unshift("Lemon");    // 向 fruits 添加新元素 "Lemon"，x 的值是 5
```



**拼接数组**

`splice()` 方法可用于向数组添加新项，`splice()` 方法还会返回一个包含已删除项的数组：：

```javascript
var fruits = ["Banana", "Orange", "Apple", "Mango"];
var x = fruits.splice(2, 1, "Lemon", "Kiwi");
//fruits 为 ["Banana", "Orange", "Lemon", "Kiwi", "Mango"]，x 的值是 ["Apple"]
```

第一个参数（2）定义了应添加新元素的位置（拼接）。

第二个参数（1）定义应删除多少元素。

其余参数（“Lemon”，“Kiwi”）定义要添加的新元素。



**合并（连接）数组**

`concat()` 方法通过合并（连接）现有数组来创建一个新数组：

```javascript
var arr1 = ["Cecilie", "Lone"];
var arr2 = ["Emil", "Tobias", "Linus"];
var arr3 = ["Robin", "Morgan"];
var myChildren = arr1.concat(arr2, arr3);   // 将arr1、arr2 与 arr3 连接在一起
```

`concat()` 方法不会更改现有数组。它总是返回一个新数组。

`concat()` 方法可以使用任意数量的数组参数。

`concat()` 方法也可以将值作为参数：

```javascript
var arr1 = ["Cecilie", "Lone"];
var myChildren = arr1.concat(["Emil", "Tobias", "Linus"]); 
```



**裁剪数组**

`slice()` 方法用数组的某个片段切出新数组。

`slice()` 方法不会从源数组中删除任何元素，它总是返回一个新数组。

`slice()` 可接受两个参数，比如 (1, 3)。该方法会从开始参数选取元素，直到结束参数（不包括）为止。如果结束参数被省略，则 `slice()` 会切出数组的剩余部分。

```javascript
var fruits = ["Banana", "Orange", "Lemon", "Apple", "Mango"];
var citrus = fruits.slice(1, 3); //["Orange", "Lemon"]
```



## 2.1、JS 数组排序

**数组排序**

`sort()` 方法以字母顺序对数组进行排序：

```javascript
var fruits = ["Banana", "Orange", "Apple", "Mango"];
fruits.sort();            // 对 fruits 中的元素进行排序
```



**反转数组**

`reverse()` 方法反转数组中的元素（是反转，不是对数组进行降序排序！）。

```javascript
var fruits = ["Banana", "Orange", "Apple", "Mango"];
fruits.sort();            // 对 fruits 中的元素进行排序
fruits.reverse();         // 反转元素顺序
```



**数字排序**

默认地，`sort()` 函数按照字符串顺序对值进行排序，该函数很适合字符串（"Apple" 会排在 "Banana" 之前）。

不过，如果数字按照字符串来排序，则 "25" 大于 "100"，因为 "2" 大于 "1"。

正因如此，`sort()` 方法在对数值排序时会产生不正确的结果。

可以通过一个比值函数来修正此问题：

```javascript
var points = [40, 100, 1, 5, 25, 10];
points.sort(function(a, b){return a - b}); 
```



**比值函数**

比较函数的目的是定义另一种排序顺序，它应该返回一个负，零或正值，这取决于参数：

```javascript
function(a, b){return a - b}
```

当 `sort()` 函数比较两个值时，会将值发送到比较函数，并根据所返回的值（负、零或正值）对这些值进行排序。

比如说：当比较 40 和 100 时，`sort()` 方法会调用比较函数 function(40,100)，该函数计算 40-100，然后返回 -60（负值），排序函数将把 40 排序为比 100 更低的值。

所以如果想要降序排序，可以这么写：

```javascript
function(a, b){return b - a})
```

随机排序：

```javascript
var points = [40, 100, 1, 5, 25, 10];
points.sort(function(a, b){return 0.5 - Math.random()}); 
```

排序对象数组:

即使对象拥有不同数据类型的属性，`sort()` 方法仍可用于对数组进行排序。解决方法是通过比较函数来对比属性值：

```javascript
var cars = [
    {type:"Volvo", year:2016},
    {type:"Saab", year:2001},
    {type:"BMW", year:2010}
];
cars.sort(function(a, b){return a.year - b.year});
```

比较字符串属性会稍复杂：

```javascript
cars.sort(function(a, b){
	  var x = a.type.toLowerCase();
	  var y = b.type.toLowerCase();
	  if (x < y) {return -1;}
	  if (x > y) {return 1;}
	  return 0;
});
```



**对数组使用 Math.max()**

可以使用 `Math.max.apply` 来查找数组中的最高值：

```javascript
function myArrayMin(arr) {
    return Math.min.apply(null, arr);
}
```

可以使用 `Math.min.apply` 来查找数组中的最低值。



## 2.2、JS 数组迭代

**Array.forEach()**

`forEach()` 方法为每个数组元素调用一次函数（回调函数）。

该函数接受 3 个参数：

- 项目值
- 项目索引
- 数组本身

```javascript
var txt = "";
var numbers = [45, 4, 9, 16, 25];
numbers.forEach(myFunction);

function myFunction(value, index, array) {
  txt = txt + value + "<br>"; 
}
```



**Array.map()**

`map()` 方法通过对每个数组元素执行函数来创建新数组。

`map()` 方法不会对没有值的数组元素执行函数。

`map()` 方法不会更改原始数组。

该函数有 3 个参数：

- 项目值
- 项目索引
- 数组本身

```javascript
var numbers1 = [45, 4, 9, 16, 25];
var numbers2 = numbers1.map(myFunction); // [90,8,18,32,50]

function myFunction(value, index, array) {
  return value * 2;
}
```



**Array.filter()**

`filter()` 方法创建一个包含通过测试的数组元素的新数组。

此函数接受 3 个参数：

- 项目值
- 项目索引
- 数组本身

```javascript
var numbers = [45, 4, 9, 16, 25]; // [45,25]
var over18 = numbers.filter(myFunction);

function myFunction(value, index, array) {
  return value > 18;
}
```



**Array.reduce()**

`reduce()` 方法在每个数组元素上运行函数，以生成（减少它）单个值。

`reduce()` 方法在数组中从左到右工作。`reduceRight()` 方法从右到左工作。

`reduce()` 方法不会减少原始数组。

此函数接受 4 个参数：

- 总数（初始值/先前返回的值）
- 项目值
- 项目索引
- 数组本身

```javascript
总和是：99//求数组中所有数字的总和：
var numbers1 = [45, 4, 9, 16, 25];
var sum = numbers1.reduce(myFunction);	//总和是：99

function myFunction(total, value, index, array) {
  return total + value;
}
```

`reduce()` 方法能够接受一个初始值：

```javascript
var numbers1 = [45, 4, 9, 16, 25];
var sum = numbers1.reduce(myFunction, 100);	//总和是：199

function myFunction(total, value) {
  return total + value;
}
```



**Array.every()**

`every()` 方法检查所有数组值是否通过测试。

此函数接受 3 个参数：

- 项目值
- 项目索引
- 数组本身

```javascript
var numbers = [45, 4, 9, 16, 25];
var allOver18 = numbers.every(myFunction); //false

function myFunction(value, index, array) {
  return value > 18;
}
```



**Array.some()**

`some()` 方法检查某些数组值是否通过了测试。

此函数接受 3 个参数：

- 项目值
- 项目索引
- 数组本身

```javascript
var numbers = [45, 4, 9, 16, 25];
var someOver18 = numbers.some(myFunction); //true

function myFunction(value, index, array) {
  return value > 18;
}
```



**Array.find()**

`find()` 方法返回通过测试函数的第一个数组元素的值。

此函数接受 3 个参数：

- 项目值
- 项目索引
- 数组本身

```javascript
25var numbers = [4, 9, 16, 25, 29];
var first = numbers.find(myFunction); //25

function myFunction(value, index, array) {
  return value > 18;
}
```



**Array.findIndex()**

`findIndex()` 方法返回通过测试函数的第一个数组元素的索引。

此函数接受 3 个参数：

- 项目值
- 项目索引
- 数组本身

```javascript
var numbers = [4, 9, 16, 25, 29];
var first = numbers.findIndex(myFunction); //3

function myFunction(value, index, array) {
  return value > 18;
}
```



## 2.3、JS 循环

**For/In 循环**

JavaScript `for/in` 语句遍历对象的属性：

```javascript
var person = {fname:"Bill", lname:"Gates", age:62}; 

var text = ""; //Bill Gates 62
var x;
for (x in person) {
    text += person[x];
}
```

for in 循环遍历 person 对象，每次迭代返回一个键 (x)，键用于访问键的值，键的值为 person[x]



**For Of 循环**

JavaScript `for of` 语句循环遍历可迭代对象的值。

它允许循环遍历可迭代的数据结构，例如数组、字符串、映射、节点列表等：

```javascript
for (variable of iterable) {
  // code block to be executed
}
```

*variable* - 对于每次迭代，下一个属性的值都会分配给变量。变量可以用 const、let 或 var 声明。

*iterable* - 具有可迭代属性的对象。

```javascript
const cars = ["BMW", "Volvo", "Mini"];

let text = ""; //BMWVolvoMini
for (let x of cars) {
    text += x;
}
```



## 2.4、JS 正则表达式

### 2.4.1、RegExp 对象

RegExp 对象表示正则表达式，它是对字符串执行模式匹配的强大工具。

直接量语法：

```javascript
/pattern/attributes;
```

创建 RegExp 对象的语法：

```javascript
new RegExp(pattern, attributes);
```

参数 *pattern* 是一个字符串，指定了正则表达式的模式或其他正则表达式。

参数 *attributes* 是一个可选的字符串，包含属性 "g"、"i" 和 "m"，分别用于指定全局匹配、区分大小写的匹配和多行匹配。ECMAScript 标准化之前，不支持 m 属性。如果 *pattern* 是正则表达式，而不是字符串，则必须省略该参数。



**修饰符**

修饰符可用于大小写不敏感的更全局的搜素：

| 修饰符 | 描述                                                     |
| ------ | -------------------------------------------------------- |
| i      | 执行对大小写不敏感的匹配。                               |
| g      | 执行全局匹配（查找所有匹配而非在找到第一个匹配后停止）。 |
| m      | 执行多行匹配。                                           |



**方括号**

方括号用于查找某个范围内的字符：

| 表达式             | 描述                               |
| ------------------ | ---------------------------------- |
| [abc\]             | 查找方括号之间的任何字符。         |
| [^abc\]            | 查找任何不在方括号之间的字符。     |
| [0-9]              | 查找任何从 0 至 9 的数字。         |
| [a-z]              | 查找任何从小写 a 到小写 z 的字符。 |
| [A-Z]              | 查找任何从大写 A 到大写 Z 的字符。 |
| [A-z]              | 查找任何从大写 A 到小写 z 的字符。 |
| (red\|blue\|green) | 查找任何指定的选项。               |



**元字符**

元字符（Metacharacter）是拥有特殊含义的字符：

| 元字符 | 描述                                        |
| ------ | ------------------------------------------- |
| .      | 查找单个字符，除了换行和行结束符。          |
| \w     | 查找单词字符。                              |
| \W     | 查找非单词字符。                            |
| \d     | 查找数字。                                  |
| \D     | 查找非数字字符。                            |
| \s     | 查找空白字符。                              |
| \S     | 查找非空白字符。                            |
| \b     | 匹配单词边界。                              |
| \B     | 匹配非单词边界。                            |
| \0     | 查找 NUL 字符。                             |
| \n     | 查找换行符。                                |
| \f     | 查找换页符。                                |
| \r     | 查找回车符。                                |
| \t     | 查找制表符。                                |
| \v     | 查找垂直制表符。                            |
| \xxx   | 查找以八进制数 xxx 规定的字符。             |
| \xdd   | 查找以十六进制数 dd 规定的字符。            |
| \uxxxx | 查找以十六进制数 xxxx 规定的 Unicode 字符。 |



Quantifiers 定义量词：

| 量词   | 描述                                        |
| ------ | ------------------------------------------- |
| n+     | 匹配任何包含至少一个 n 的字符串。           |
| n*     | 匹配任何包含零个或多个 n 的字符串。         |
| n?     | 匹配任何包含零个或一个 n 的字符串。         |
| n{X}   | 匹配包含 X 个 n 的序列的字符串。            |
| n{X,Y} | 匹配包含 X 至 Y 个 n 的序列的字符串。       |
| n{X,}  | 匹配包含至少 X 个 n 的序列的字符串。        |
| n$     | 匹配任何结尾为 n 的字符串。                 |
| ^n     | 匹配任何开头为 n 的字符串。                 |
| ?=n    | 匹配任何其后紧接指定字符串 n 的字符串。     |
| ?!n    | 匹配任何其后没有紧接指定字符串 n 的字符串。 |



### 2.4.2、RegExp 方法

 **test()**

`test()` 是一个正则表达式方法，它通过模式来搜索字符串，然后根据结果返回 true 或 false。

下面的例子搜索字符串中的字符 "e"：

```javascript
var patt = /e/;
patt.test("The best things in life are free!");  //true
```



**exec()**

`exec()` 方法是一个正则表达式方法。

它通过指定的模式（pattern）搜索字符串，返回一个数组，其中存放匹配的结果。如果未找到匹配，则返回值为 null。

exec() 方法的功能非常强大，它是一个通用的方法，而且使用起来也比 test() 方法以及支持正则表达式的 String 对象的方法更为复杂。

如果 exec() 找到了匹配的文本，则返回一个结果数组。否则，返回 null。此数组的第 0 个元素是与正则表达式相匹配的文本，第 1 个元素是与 RegExpObject 的第 1 个子表达式相匹配的文本（如果有的话），第 2 个元素是与 RegExpObject 的第 2 个子表达式相匹配的文本（如果有的话），以此类推。除了数组元素和 length 属性之外，exec() 方法还返回两个属性。index 属性声明的是匹配文本的第一个字符的位置。input 属性则存放的是被检索的字符串 string。我们可以看得出，在调用非全局的 RegExp 对象的 exec() 方法时，返回的数组与调用方法 String.match() 返回的数组是相同的。

但是，当 RegExpObject 是一个全局正则表达式时，exec() 的行为就稍微复杂一些。它会在 RegExpObject 的 lastIndex 属性指定的字符处开始检索字符串 string。当 exec() 找到了与表达式相匹配的文本时，在匹配后，它将把 RegExpObject 的 lastIndex 属性设置为匹配文本的最后一个字符的下一个位置。这就是说，您可以通过反复调用 exec() 方法来遍历字符串中的所有匹配文本。当 exec() 再也找不到匹配的文本时，它将返回 null，并把 lastIndex 属性重置为 0。

示例：

```javascript
var str = "Visit W3School, W3School is a place to study web technology."; 
var patt = new RegExp("W3School","g");
var result;

while ((result = patt.exec(str)) != null)  {
    document.write(result);
    document.write("<br />");
    document.write(patt.lastIndex);
    document.write("<br />");
}
```

输出：

```
W3School
14
W3School
24
```



## 2.5、JS this 关键字

**this 是什么？**

JavaScript this 关键词指的是它所属的对象。

它拥有不同的值，具体取决于它的使用位置：

- 在方法中，this 指的是所有者对象。
- 单独的情况下，this 指的是全局对象。
- 在函数中，this 指的是全局对象。
- 在函数中，严格模式下，this 是 undefined。
- 在事件中，this 指的是接收事件的元素。

像 `call()` 和 `apply()` 这样的方法可以将 this 引用到任何对象。



# 2、JS 函数

## 2.1、JS 函数

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



## 2.2、JS 箭头函数

ES6 中引入了箭头函数，箭头函数允许我们编写更短的函数。

之前：

```
 hello = function() {
     return "Hello World!";
 }
```

用了箭头函数之后：

```
 hello = () => {
     return "Hello World!";
 }
```

如果函数只有一个语句，并且该语句返回一个值，则可以去掉括号和 `return` 关键字：

```
 hello = () => "Hello World!";
```

如果有参数，则将它们传递到括号内：

```
 hello = (val) => "Hello " + val;
```

事实上，如果只有一个参数，也可以略过括号：

```
 hello = val => "Hello " + val;
```



## 2.3、JS 函数 Call

**方法重用**

使用 `call()` 方法可以编写能够在不同对象上使用的方法。



**函数是对象方法**

在 JavaScript 中，函数是对象的方法。如果一个函数不是 JavaScript 对象的方法，那么它就是全局对象的函数。

下面的例子创建了带有三个属性的对象（firstName、lastName、fullName）。

```javascript
var person = {
    firstName:"Bill",
    lastName: "Gates",
    fullName: function () {
        return this.firstName + " " + this.lastName;
    }
}
person.fullName();		// 将返回 "Bill Gates"
```

fullName 属性是一个方法。person 对象是该方法的拥有者。fullName 属性属于 person 对象的方法。



**JavaScript call() 方法**

`call()` 方法是预定义的 JavaScript 方法，它可以用来调用所有者对象作为参数的方法。

通过 `call()` 能够使用属于另一个对象的方法。

本例调用 person 的 fullName 方法，并用于 person1：

```javascript
var person = {
    fullName: function() {
        return this.firstName + " " + this.lastName;
    }
}
var person1 = {
    firstName:"Bill",
    lastName: "Gates",
}
var person2 = {
    firstName:"Steve",
    lastName: "Jobs",
}
person.fullName.call(person1);  // 将返回 "Bill Gates"
```



**带参数的 call() 方法**

call() 方法可接受参数：

```javascript
var person = {
    fullName: function(city, country) {
        return this.firstName + " " + this.lastName + "," + city + "," + country;
    }
}
var person1 = {
    firstName:"Bill",
    lastName: "Gates"
}
person.fullName.call(person1, "Seattle", "USA"); // 将返回 "Bill Gates,Seatle,USA"
```



## 2.4、JS 函数 Apply

**方法重用**

通过 apply() 方法能够编写用于不同对象的方法。



**JavaScript apply() 方法**

`apply()` 方法与 `call() `方法非常相似：

在本例中，person 的 fullName 方法被应用到 person1：

```javascript
var person = {
    fullName: function() {
        return this.firstName + " " + this.lastName;
    }
}
var person1 = {
    firstName: "Bill",
    lastName: "Gates",
}
person.fullName.apply(person1);  // 将返回 "Bill Gates"
```



**call() 和 apply() 之间的区别**

不同之处是：

- `call() `方法分别接受参数。
- `apply()` 方法接受数组形式的参数。

如果要使用数组而不是参数列表，则 `apply()` 方法非常方便。



**带参数的 apply() 方法**

`apply()` 方法接受数组中的参数：

```javascript
var person = {
  fullName: function(city, country) {
    return this.firstName + " " + this.lastName + "," + city + "," + country;
  }
}
var person1 = {
  firstName:"John",
  lastName: "Doe"
}
person.fullName.apply(person1, ["Oslo", "Norway"]); // 将返回 "Bill Gates,Seatle,USA"
```



**在数组上模拟 max 方法**

可以使用 `Math.max()` 方法找到（数字列表中的）最大数字：

```javascript
Math.max(1,2,3);  // 会返回 3
```

由于 JavaScript 数组没有 `max()` 方法，因此可以应用 `Math.max()` 方法。

```javascript
Math.max.apply(null, [1,2,3]); // 也会返回 3
```

第一个参数（null）无关紧要。在本例中未使用它。

