---
typora-copy-images-to: upload
---

# 1、基础

## 1.1、使用

在 HTML 中，JavaScript 代码必须位于 `<script>` 与 `</script>` 标签之间。

> 旧的 JavaScript 例子也许会使用 **type** 属性：`<script type="text/javascript">`。
>
> type 属性不是必需的。JavaScript 是 HTML 中的默认脚本语言。

脚本可被放置与 HTML 页面的 `<body>` 或 `<head>` 部分中，或兼而有之。

> 把脚本置于 `<body>` 元素的底部，可改善显示速度，因为脚本编译会拖慢显示。

如果相同的脚本被用于许多不同的网页，可将脚本放置于外部文件中。

在外部文件中放置脚本有如下优势：

- 分离了 HTML 和代码
- 使 HTML 和 JavaScript 更易于阅读和维护
- 已缓存的 JavaScript 文件可加速页面加载



**JS 文件中引入另一个JS文件**

1. 在调用文件的顶部加入下例代码：
   ```javascript
   document.write("<script language=javascript src="+url+"></script>");
   ```

   > 注：有时引用的文件还可能需要引用其他的 JS，我们需要将需要的那个 JS 文件也以同样的方法引用进来。

2. 在 JS 中写如下代码：
   ```javascript
   function addScript(url){
   	var script = document.createElement('script');
   	script.setAttribute('type','text/javascript');
   	script.setAttribute('src',url);
   	document.getElementsByTagName('head')[0].appendChild(script);
   }
   ```

   利用 `document.createElement("script")` 生成了一个 `script` 的标签，设置其 `type` 属性为 `text/javascript`。

3. 利用 ES6 中 `export` 和 `import` 实现模块化：

   一个 JS 文件代表一个 JS 模块 。ES6 引入外部模块分两种情况：

   1. 导入外部的变量或函数等：

      ```javascript
      import {firstName, lastName} from './test';
      ```

   2. 导入外部的模块，并立即执行：
      ```javascript
      import './test'
      //执行test.js，但不导入任何变量
      ```



## 1.2、Let 和 Const

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



### 1.2.4、Const

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



## 1.2、数据类型

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





## 1.3、字符串

### 1.3.1、字符串搜索

**String.search()**

`search()` 方法在字符串中搜索指定值并返回匹配的位置：

```javascript
let str = "Please locate where 'locate' occurs!";
str.search("locate")     // 返回 7
```

关于 `indexOf()` 和 `search()` ：

1. 都可以进行字符串的查找
2. `serach()` 支持正则，`indexOf()` 不支持
3. `indexOf()` 性能更高



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



### 1.3.2、模板字符串

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



## 1.4、数字

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



### 1.4.1、数字方法

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



## 1.5、循环

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



## 1.6、数组

### 1.6.1、数组方法

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



### 1.6.2、数组排序

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



### 1.6.3、数组迭代

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
var numbers = [45, 4, 9, 16, 25];
var over18 = numbers.filter(myFunction); // [45,25]

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
//求数组中所有数字的总和：
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



# 2、（Object）对象

## 2.1、对象

JavaScript 中有八种数据类型。有七种原始类型，因为它们的值只包含一种东西（字符串，数字或者其他）。相反，对象则用来存储键值对和更复杂的实体。

我们可以通过使用带有可选属性列表的花括号 `{…}` 来创建对象。一个属性就是一个键值对（“key: value”），其中键（key）是一个字符串（也叫做属性名），值（value）可以是任何值。

两种语法中的任一种可以用来创建一个空的对象：

```javascript
let user = new Object(); // “构造函数” 的语法
let user = {};  // “字面量” 的语法
```

通常，我们用花括号。这种方式我们叫做 **字面量**。



### 2.1.1、文本和属性

在创建对象的时候，可以立即将一些属性以键值对的形式放到 `{...}` 中：

```javascript
let user = {     // 一个对象
  name: "John",  // 键 "name"，值 "John"
  age: 30        // 键 "age"，值 30
};
```

属性有键（或者也可以叫做 “名字” 或 “标识符”），位于冒号 `:` 的前面，值在冒号的右边。

在 `user` 对象中，有两个属性：

1. 第一个的键是 `name`，值是 `"John"`。
2. 第二个的键是 `age`，值是 `30`。

可以使用点符号访问属性值：

```javascript
// 读取文件的属性：
alert( user.name ); // John
alert( user.age ); // 30
```

可以用 `delete` 操作符移除属性：

```javascript
delete user.age;
```

也可以用多字词语来作为属性名，但必须给它们加上引号：

```javascript
let user = {
  name: "John",
  age: 30,
  "likes birds": true  // 多词属性名必须加引号
};
```



### 2.1.2、方括号

对于多词属性，点操作就不能用了：

```javascript
// 这将提示有语法错误
user.likes birds = true
```

JavaScript 理解不了。它认为我们在处理 `user.likes`，然后在遇到意外的 `birds` 时给出了语法错误。

点符号要求 `key` 是有效的变量标识符。这意味着：不包含空格，不以数字开头，也不包含特殊字符（允许使用 `$` 和 `_`）。

有另一种方法，就是使用方括号，可用于任何字符串：

```javascript
let user = {};

// 设置
user["likes birds"] = true;

// 读取
alert(user["likes birds"]); // true

// 删除
delete user["likes birds"];
```

方括号同样提供了一种可以通过任意表达式来获取属性名的方式 —— 与文本字符串不同 —— 例如下面的变量：

```javascript
let key = "likes birds";

// 跟 user["likes birds"] = true; 一样
user[key] = true;
```

点符号不能以类似的方式使用：

```javascript
let user = {
  name: "John",
  age: 30
};

let key = "name";
alert( user.key ) // undefined
```



### 2.1.3、属性值简写

在实际开发中，我们通常用已存在的变量当做属性名，例如：

```javascript
function makeUser(name, age) {
  return {
    name: name,
    age: age,
    // ……其他的属性
  };
}

let user = makeUser("John", 30);
alert(user.name); // John
```

在上面的例子中，属性名跟变量名一样。这种通过变量生成属性的应用场景很常见，在这有一种特殊的 **属性值缩写** 方法，使属性名变得更短。

可以用 `name` 来代替 `name:name` 像下面那样：

```javascript
function makeUser(name, age) {
  return {
    name, // 与 name: name 相同
    age,  // 与 age: age 相同
    // ...
  };
}
```

我们可以把属性名简写方式和正常方式混用：

```javascript
let user = {
  name,  // 与 name:name 相同
  age: 30
};
```



### 2.1.4、属性存在性测试

相比于其他语言，JavaScript 的对象有一个需要注意的特性：能够被访问任何属性。即使属性不存在也不会报错！

读取不存在的属性只会得到 `undefined`。所以我们可以很容易地判断一个属性是否存在：

```javascript
let user = {};

alert( user.noSuchProperty === undefined ); // true 意思是没有这个属性
```

这里还有一个特别的，检查属性是否存在的操作符 `in`，语法为：

```javascript
"key" in object
```

例如：

```javascript
let user = { name: "John", age: 30 };

alert( "age" in user ); // true，user.age 存在
alert( "blabla" in user ); // false，user.blabla 不存在。
```

`in` 的左边必须是 **属性名**，通常是一个带引号的字符串。如果我们省略引号，就意味着左边是一个变量，它应该包含要判断的实际属性名。例如：

```javascript
let user = { age: 30 };

let key = "age";
alert( key in user ); // true，属性 "age" 存在
```

为何会有 `in` 运算符呢？与 `undefined` 进行比较来判断还不够吗？

确实，大部分情况下与 `undefined` 进行比较来判断就可以了。但有一个例外情况，这种比对方式会有问题，但 `in` 运算符的判断结果仍是对的。

那就是属性存在，但存储的值是 `undefined` 的时候：

```javascript
let obj = {
  test: undefined
};

alert( obj.test ); // 显示 undefined，所以属性不存在？

alert( "test" in obj ); // true，属性存在！
```



## 2.2、对象引用和复制

对象与原始类型的根本区别之一是，对象是 “通过引用” 存储和复制的，而原始类型：字符串、数字、布尔值等 —— 总是 “作为一个整体” 复制。

例如一个字符串，这里我们将 `message` 复制到 `phrase`：

```javascript
let message = "Hello!";
let phrase = message;
```

结果我们就有了两个独立的变量，每个都存储着字符串 `"Hello!"`。

但是对象不是这样的。赋值了对象的变量存储的不是对象本身，而是该对象 “在内存中的地址”，换句话说就是对该对象的 “引用”。当一个对象变量被复制（引用被复制），而该对象自身并没有被复制。例如：

```javascript
let user = { name: "John" };

let admin = user; // 复制引用
```

现在我们有了两个变量，它们保存的都是对同一个对象的引用，所以我们可以通过其中任意一个变量来访问该对象并修改它的内容：

```javascript
let user = { name: 'John' };

let admin = user;

admin.name = 'Pete'; // 通过 "admin" 引用来修改

alert(user.name); // 'Pete'，修改能通过 "user" 引用看到
```



### 2.2.1、通过引用来比较

仅当两个对象为同一对象时，两者才相等。

例如，这里 `a` 和 `b` 两个变量都引用同一个对象，所以它们相等：

```javascript
let a = {};
let b = a; // 复制引用

alert( a == b ); // true，都引用同一对象
alert( a === b ); // true
```

而这里两个独立的对象则并不相等，即使它们看起来很像（都为空）：

```javascript
let a = {};
let b = {}; // 两个独立的对象

alert( a == b ); // false
```



### 2.2.2、克隆与合并

我们可以使用 `Object.assign` 方法来达到克隆的效果。

```javascript
Object.assign(dest, [src1, src2, src3...])
```

- 第一个参数 *dest* 是指目标对象。
- 更后面的参数 *src1, ..., srcN*（可按需传递多个参数）是源对象。
- 该方法将所有源对象的属性拷贝到目标对象 *dest* 中。换句话说，从第二个开始的所有参数的属性都被拷贝到第一个参数的对象中。
- 调用结果返回 *dest*。

例如，将对象中的所有属性拷贝到了一个空对象中，并返回这个新的对象：

```javascript
let user = {
  name: "John",
  age: 30
};

let clone = Object.assign({}, user);
```

可以用它来合并多个对象：

```javascript
let user = { name: "John" };

let permissions1 = { canView: true };
let permissions2 = { canEdit: true };

// 将 permissions1 和 permissions2 中的所有属性都拷贝到 user 中
Object.assign(user, permissions1, permissions2);

// 现在 user = { name: "John", canView: true, canEdit: true }
```

如果被拷贝的属性的属性名已经存在，那么它会被覆盖：

```javascript
let user = { name: "John" };

Object.assign(user, { name: "Pete" });

alert(user.name); // 现在 user = { name: "Pete" }
```



### 2.2.3、深层克隆

到现在为止，我们都假设 `user` 的所有属性均为原始类型。但属性可以是对其他对象的引用：

```javascript
let user = {
  name: "John",
  sizes: {
    height: 182,
    width: 50
  }
};

alert( user.sizes.height ); // 182
```

现在这样拷贝 `clone.sizes = user.sizes` 已经不足够了，因为 `user.sizes` 是个对象，它会以引用形式被拷贝。因此 `clone` 和 `user` 会共用一个 `sizes`：

```javascript
let user = {
  name: "John",
  sizes: {
    height: 182,
    width: 50
  }
};

let clone = Object.assign({}, user);

alert( user.sizes === clone.sizes ); // true，同一个对象

// user 和 clone 分享同一个 sizes
user.sizes.width++;       // 通过其中一个改变属性值
alert(clone.sizes.width); // 51，能从另外一个获取到变更后的结果
```

为了解决这个问题，并让 `user` 和 `clone` 成为两个真正独立的对象，应该使用一个拷贝循环来检查 `user[key]` 的每个值，如果它是一个对象，那么也复制它的结构。这就是所谓的 “深拷贝”。

这里可以使用递归来实现它。或者为了不重复造轮子，采用现有的实现，例如 [lodash](https://lodash.com/) 库的 [_.cloneDeep(obj)](https://lodash.com/docs#cloneDeep)。



# 3、（Function）函数

## 3.1、函数

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



**默认参数**

设置函数的默认参数是在 JavaScript 中比较常见的需求。

在 ES6 以前，我们可以使用一些技巧来模拟函数默认参数值的功能，比如利用 JavaScript 中逻辑运算符 `||` 的特性，只要第一个表达式为 `false`，则返回第二个表达式的值。利用这个特性，我们就可以设置函数的默认参数：

```javascript
function sayHello(name) {
  name = name || 'guest'; // 如果 name 为 false（例如 undefined、null、false、0），则把 name 赋值为 'guest'
  console.log('Hello, ' + name);
}

sayHello('Bob'); // 输出：Hello, Bob
sayHello(); // 输出：Hello, guest
```

在 ES6 中，可以很方便地设置函数的默认参数，只需要在函数参数中直接给出默认值即可。如下面的代码所示：

```javascript
function sayHello(name = 'guest') { // 如果 name 没有传入值，则默认为 'guest'
  console.log('Hello, ' + name);
}

sayHello('Bob'); // 输出：Hello, Bob
sayHello(); // 输出：Hello, guest
```



## 3.2、箭头函数

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



**this 怎么办？**

与常规函数相比，箭头函数对 `this` 的处理也有所不同。

简而言之，箭头函数没有自己的 `this` 对象，内部的 `this` 就是定义时上层作用域中的 `this`。

```javascript
var hello;

hello = () => {
  document.getElementById("demo").innerHTML += this;
}

//window 对象调用函数：
window.addEventListener("load", hello);	//[object Window]

//button 对象调用函数：
document.getElementById("btn").addEventListener("click", hello);	//[object Window]
```



## 3.3、函数 Call

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



## 3.4、函数 Apply

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



## 3.5、闭包

一个函数和对其周围状态（**lexical environment，词法环境**）的引用捆绑在一起（或者说函数被引用包围），这样的组合就是**闭包**（**closure**）。也就是说，闭包让你可以在一个内层函数中访问到其外层函数的作用域。在 JavaScript 中，每当创建一个函数，闭包就会在函数创建的同时被创建出来。



**词法作用域**

```javascript
function init() {
    var name = "Mozilla"; // name 是一个被 init 创建的局部变量
    function displayName() { // displayName() 是内部函数，一个闭包
        alert(name); // 使用了父函数中声明的变量
    }
    displayName();
}
init();
```

`init()` 创建了一个局部变量 `name` 和一个名为 `displayName()` 的函数。`displayName()` 是定义在 `init()` 里的内部函数，并且仅在 `init()` 函数体内可用。请注意，`displayName()` 没有自己的局部变量。然而，因为它可以访问到外部函数的变量，所以 `displayName()` 可以使用父函数 `init()` 中声明的变量 `name `。

运行该代码后发现， `displayName()` 函数内的 `alert()` 语句成功显示出了变量 `name` 的值（该变量在其父函数中声明）。这个词法作用域的例子描述了分析器如何在函数嵌套的情况下解析变量名。词法（lexical）一词指的是，词法作用域根据源代码中声明变量的位置来确定该变量在何处可用。嵌套函数可访问声明于它们外部作用域的变量。



**闭包**

```javascript
function makeFunc() {
    var name = "Mozilla";
    function displayName() {
        alert(name);
    }
    return displayName;
}

var myFunc = makeFunc();
myFunc();
```

运行这段代码的效果和之前 `init()` 函数的示例完全一样。其中不同的地方（也是有意思的地方）在于内部函数 `displayName()` 在执行前，从外部函数返回。

第一眼看上去，也许不能直观地看出这段代码能够正常运行。在一些编程语言中，一个函数中的局部变量仅存在于此函数的执行期间。一旦 `makeFunc()` 执行完毕，你可能会认为 `name` 变量将不能再被访问。然而，因为代码仍按预期运行，所以在 JavaScript 中情况显然与此不同。

原因在于，JavaScript 中的函数会形成了闭包。 闭包是由函数以及声明该函数的词法环境组合而成的。该环境包含了这个闭包创建时作用域内的任何局部变量。在本例子中，`myFunc` 是执行 `makeFunc` 时创建的 `displayName` 函数实例的引用。`displayName` 的实例维持了一个对它的词法环境（变量 `name` 存在于其中）的引用。因此，当 `myFunc` 被调用时，变量 `name` 仍然可用，其值 `Mozilla` 就被传递到 `alert` 中。

下面是一个更有意思的示例 — 一个 `makeAdder` 函数：

```javascript
function makeAdder(x) {
  return function(y) {
    return x + y;
  };
}

var add5 = makeAdder(5);
var add10 = makeAdder(10);

console.log(add5(2));  // 7
console.log(add10(2)); // 12
```

在这个示例中，我们定义了 `makeAdder(x)` 函数，它接受一个参数 `x` ，并返回一个新的函数。返回的函数接受一个参数 `y`，并返回`x+y`的值。

从本质上讲，`makeAdder` 是一个函数工厂 — 他创建了将指定的值和它的参数相加求和的函数。在上面的示例中，我们使用函数工厂创建了两个新函数 — 一个将其参数和 5 求和，另一个和 10 求和。

`add5` 和 `add10` 都是闭包。它们共享相同的函数定义，但是保存了不同的词法环境。在 `add5` 的环境中，`x` 为 5。而在 `add10` 中，`x` 则为 10。



# 4、Browser 对象

## 4.1、存储对象

Web 存储 API 提供了 sessionStorage （会话存储） 和 localStorage（本地存储）两个存储对象来对网页的数据进行添加、删除、修改、查询操作。

- localStorage 用于长久保存整个网站的数据，保存的数据没有过期时间，直到手动去除。
- sessionStorage 用于临时保存同一窗口（或标签页）的数据，在关闭窗口或标签页之后将会删除这些数据。



**存储对象属性**

| 属性   | 描述                           |
| ------ | ------------------------------ |
| length | 返回存储对象中包含多少条数据。 |



**存储对象方法**

| 方法                    | 描述                                               |
| ----------------------- | -------------------------------------------------- |
| key(n)                  | 返回存储对象中第 *n* 个键的名称                    |
| getItem(keyname)        | 返回指定键的值                                     |
| setItem(keyname, value) | 添加键和值，如果对应的值存在，则更新该键对应的值。 |
| removeItem(keyname)     | 移除键                                             |
| clear()                 | 清除存储对象中所有的键                             |



### 4.1.1、localStorage

localStorage 和 sessionStorage 属性允许在浏览器中存储 key/value 对的数据。localStorage 用于长久保存整个网站的数据，保存的数据没有过期时间，直到手动去删除。

localStorage 属性是只读的。



**localStorage 的优势**

1. localStorage 拓展了 cookie 的 4K 限制。
2.  localStorage 会可以将第一次请求的数据直接存储到本地，这个相当于一个 5M 大小的针对于前端页面的数据库，相比于 cookie 可以节约带宽，但是这个却是只有在高版本的浏览器中才支持的。



**localStorage 的局限**

1. 浏览器的大小不统一，并且在 IE8 以上的 IE 版本才支持 localStorage 这个属性。
2. 目前所有的浏览器中都会把 localStorage 的值类型限定为 string 类型，这个在对我们日常比较常见的 JSON 对象类型需要一些转换。
3. localStorage 在浏览器的隐私模式下面是不可读取的。
4. localStorage 本质上是对字符串的读取，如果存储内容多的话会消耗内存空间，会导致页面变卡。
5. localStorage 不能被爬虫抓取到。



**localStorage 使用**

首先在使用 localStorage 的时候，我们需要判断浏览器是否支持 localStorage 这个属性：

```javascript
if(!window.localStorage){
    alert("浏览器不支持localstorage");
    return false;
}else{
    //主逻辑业务
}
```

localStorage 的写入有三种方法：

```javascript
if(!window.localStorage){
    alert("浏览器不支持localstorage");
    return false;
}else{
    var storage=window.localStorage;
    //写入a字段
    storage["a"]=1;
    //写入b字段
    storage.b=1;
    //写入c字段
    storage.setItem("c",3);
    console.log(typeof storage["a"]); //string
    console.log(typeof storage["b"]); //string
    console.log(typeof storage["c"]); //string
}
```

这里面是三种对 localStorage 的读取，其中官方推荐的是 `getItem\setItem` 这两种方法对其进行存取，这里要特别说明一下 localStorage 的使用也是遵循同源策略的，所以不同的网站直接是不能共用相同的 localStorage。



### 4.1.2、sessionStorage 

localStorage 和 sessionStorage 属性允许在浏览器中存储 key/value 对的数据。sessionStorage 用于临时保存同一窗口(或标签页)的数据，在关闭窗口或标签页之后将会删除这些数据。

其余和 localStorage  相似。



## 4.2、Window 对象

Window 对象表示浏览器中打开的窗口。

如果文档包含框架（`<frame>` 或 `<iframe>` 标签），浏览器会为 HTML 文档创建一个 window 对象，并为每个框架创建一个额外的 window 对象。

> 注意： 没有应用于 window 对象的公开标准，不过所有浏览器都支持该对象。



### 4.2.1、Window 对象属性

| 属性           | 描述                                                         |
| -------------- | ------------------------------------------------------------ |
| parent         | 返回父窗口。                                                 |
| localStorage   | 在浏览器中存储 key/value 对。没有过期时间。                  |
| sessionStorage | 在浏览器中存储 key/value 对。 在关闭窗口或标签页之后将会删除这些数据。 |



### 4.2.2、Window 对象方法

**setInterval()**

`setInterval()` 方法可按照指定的周期（以毫秒计）来调用函数或计算表达式。

`setInterval()` 方法会不停地调用函数，直到 `clearInterval()` 被调用或窗口被关闭。由 `setInterval()` 返回的 ID 值可用作 `clearInterval()` 方法的参数。

语法：

```javascript
setInterval(code, milliseconds);
setInterval(function, milliseconds, param1, param2, ...);
```

| 参数                | 描述                                                         |
| :------------------ | :----------------------------------------------------------- |
| code/function       | 必需。要调用一个代码串，也可以是一个函数。                   |
| milliseconds        | 必须。周期性执行或调用 code/function 之间的时间间隔，以毫秒计。 |
| param1, param2, ... | 可选。 传给执行函数的其他参数（IE9 及其更早版本不支持该参数）。 |

`setInterval()` 会返回一个 ID（数字），可以将这个 ID 传递给 `clearInterval()`  以取消执行。



**clearTimeout()**

`clearTimeout()` 方法可取消由 `setTimeout()` 方法设置的定时操作。

`clearTimeout()` 方法的参数必须是由 `setTimeout()` 返回的 ID 值。

> 注意：使用 `clearTimeout()` 方法，在创建执行定时操作时要使用全局变量：
>
> ```javascript
> myVar = setTimeout("javascript function", milliseconds);
> ```
>
> 如果方法还未被执行，可以使用 `clearTimeout()` 来阻止它。

语法：

```javascript
clearTimeout(id_of_settimeout)
```

| 参数              | 描述                                                         |
| :---------------- | :----------------------------------------------------------- |
| id_of_setinterval | 调用 `setTimeout()` 函数时所获得的返回值，使用该返回标识符作为参数，可以取消该 `setTimeout()` 所设定的定时执行操作。 |



# 5、关键字和运算符

## 5.1、this

**this 是什么？**

JavaScript this 关键词指的是它所属的对象。

它拥有不同的值，具体取决于它的使用位置：

- 在方法中，this 指的是所有者对象。
- 单独的情况下，this 指的是全局对象。
- 在函数中，this 指的是全局对象。
- 在函数中，严格模式下，this 是 undefined。
- 在事件中，this 指的是接收事件的元素。

像 `call()` 和 `apply()` 这样的方法可以将 this 引用到任何对象。



**单独的 this**

在单独使用时，拥有者是全局对象，因此 `this` 指的是全局对象。

在浏览器窗口中，全局对象是 `[object Window]`：

```javascript
var x = this;	//[object Window]
```

在严格模式中，如果单独使用，那么 `this` 指的是全局对象 `[object Window]`：

```javascript
"use strict";
var x = this;	//[object Window]
```



**函数中的 this**

在 JavaScript 函数中，函数的拥有者默认绑定 `this`。

因此，在函数中，`this` 指的是全局对象 `[object Window]`。

```javascript
function myFunction() {
  return this;	//[object Window]
}
```

JavaScript 严格模式不允许默认绑定。

因此，在函数中使用时，在严格模式下，`this` 是未定义的（`undefined`）。

```javascript
"use strict";
function myFunction() {
  return this;	//undefined
}
```



**事件处理程序中的 this**

在 HTML 事件处理程序中，`this` 指的是接收此事件的 HTML 元素：

```html
<button onclick="this.style.display='none'">单击来删除我！</button>
```



**对象方法绑定**

在此例中，`this` 是 person 对象（person 对象是该函数的“拥有者”）：

```javascript
var person = {
  firstName  : "Bill",
  lastName   : "Gates",
  id         : 678,
  myFunction : function() {
    return this;	//[object Object]
  }
};
```

换句话说，*this.firstName* 意味着 *this*（person）对象的 *firstName* 属性。



**显式函数绑定**

`call()` 和 `apply()` 方法是预定义的 JavaScript 方法。它们都可以用于将另一个对象作为参数调用对象方法。

在下面的例子中，当使用 *person2* 作为参数调用 *person1.fullName* 时，`this` 将引用 *person2*，即使它是 *person1* 的方法：

```javascript
var person1 = {
  fullName: function() {
    return this.firstName + " " + this.lastName;
  }
}
var person2 = {
  firstName:"Bill",
  lastName: "Gates",
}
person1.fullName.call(person2);  // 会返回 "Bill Gates"
```



## 5.2、void

众所周知，`undefined` 是 JS 语言中的 7 大基本类型之一，表示未定义，它的值只有一个，就是 `undefined`。任何变量在赋值前都是 `undefined`。

而在一些框架源码中，会出现一些这样的表达式：

```javascript
function foo() {
    var a  = arguments[0] !== (void 0 ) ? arguments[0] : 2;
    return a; 
}
```

这是 《你不知道的JavaScript》的一段代码，用于实现 ES6 中函数参数默认值的 `transpiling` 处理，为了让不支持 ES6 这个新特性的浏览器可以正常使用。

正如你可以看到的，它会检查 `arguments[0]` 的值是否为 `void 0`（也就是 `undefined`），如果是的话就提供默认值 `2`；否则就使用传入值。



**什么是 void？**

MDN 中对 void 有这么一段说明：

```js
The void operator evaluates the given expression and then returns undefined.
```

翻译过来意思是：`void` 操作符对给定的表达式求值，然后返回 `undefined`。

在 JavaScript 中 `void` 是一元运算符，出现在操作数的左边，操作数可以是任意类型的值，`void` 右边的表达式可以是带括号形式（例如：`void(0)`），也可以是不带括号的形式（例如：`void 0`）。



**为什么用 void 0 代替 undefined?**

`void 0` 返回 `undefined`，我们都知道的，但是为什么不直接 `arguments[0] !== undefined`?

1. `undefined` 可以被重写：

   在 ES5 的全局环境中，`undefined` 是只读的，但是在部分低级别的浏览器（IE7,IE8）中可以被修改。而在局部作用域中，`undefined` 也是可变的。这个是 JS 语言公认的设计失误之一。

   ```javascript
   (function() {
     var undefined = 10;
    
     // 10 -- chrome
     alert(undefined);
   })();
    
   (function() {
     undefined = 10;
    
     // undefined -- chrome
     alert(undefined);
   })();
   ```

2. 从性能方面： `void 0` 比 `undefined` 少了三个字节：

   当源码中有大量用 `undefined` 判断的时候，这个优化还是值得关注的。

   ```javascript
   >"undefined".length
   9
   >"void 0".length
   6
   ```

3. 保证结果不变性：

   `undefined` 并不是 Javascript中的保留字，我们可以使用 `undefined` 作为变量名字，然后给它赋值。`void 0` 输出唯一的结果 `undefined`，保证了不变性。



**void 0 应用场景**

1. 立即调用的函数表达式：

   在使用**立即执行的函数表达式**时，可以利用 `void` 运算符让 JavaScript 引擎把一个 `function` 关键字识别成函数表达式而不是函数声明（语句）。

   ```javascript
   void function iife() {
       var bar = function () {};
       var baz = function () {};
       var foo = function () {
           bar();
           baz();
        };
       var biz = function () {};
   
       foo();
       biz();
   }();
   ```

2. `javascript URIs`：

   当用户点击一个以 `javascript:` 开头的 URI 时，它会执行 URI 中的代码，然后用返回的值替换页面内容，除非返回的值是 `undefined`。`void` 运算符可用于返回 `undefined`。例如：

   ```html
   <a href="javascript:void(0);">
     这个链接点击之后不会做任何事情，如果去掉 void()，
     点击之后整个页面会被替换成一个字符 0。
   </a>
   <p> chrome中即使<a href="javascript:0;">也没变化，firefox中会变成一个字符串0 </p>
   <a href="javascript:void(document.body.style.backgroundColor='green');">
     点击这个链接会让页面背景变成绿色。
   </a>
   ```

   注意，虽然这么做是可行的，但利用 `javascript:` 伪协议来执行 JavaScript 代码是不推荐的，推荐的做法是为链接元素绑定事件。

3. 箭头函数中避免泄漏：

   箭头函数标准中，允许在函数体不使用括号来直接返回值。 如果右侧调用了一个原本没有返回值的函数，其返回值改变后，则会导致非预期的副作用。 安全起见，当函数返回值是一个不会被使用到的时候，应该使用 `void` 运算符，来确保返回 `undefined`（如下方示例），这样，当 API 改变时，并不会影响箭头函数的行为。

   ```javascript
   button.onclick = () => void doSomething();
   ```



## 5.3、…

扩展操作符 `…` 是 ES6 中引入的，将可迭代对象展开到其单独的元素中，所谓的可迭代对象就是任何能用 `for of` 循环进行遍历的对象，例如：数组、字符串、Map、Set 、DOM 节点等。



**拷贝数组对象**

使用扩展符拷贝数组是 ES6 中常用的操作：

```javascript
const years = [2018, 2019, 2020, 2021];
const copyYears = [...years];

console.log(copyYears); // [ 2018, 2019, 2020, 2021 ]
```

拷贝对象，代码如下：

```javascript
const time = {
    year: 2021,
    month: 7,
    day: {
        value: 1,
    },
};
const copyTime = { ...time };
console.log(copyTime); // { year: 2021, month: 7, day: { value: 1 } }
```



**合并操作**

数组的合并，如下：

```javascript
const halfMonths1 = [1, 2, 3, 4, 5, 6];
const halfMonths2 = [7, 8, 9, 10, 11, 12];

const allMonths = [...halfMonths1, ...halfMonths2];
console.log(allMonths); // [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ]
```

合并对象，在合并对象时，如果一个键已经存在，它会被具有相同键的最后一个对象给替换。

```javascript
const time1 = {
    month: 7,
    day: {
        value: 1,
    },
};
const time2 = {
    year: 2021,
    month: 8,
    day: {
        value: 10,
    },
};
const time = { ...time1, ...time2 };
console.log(time); // { month: 8, day: { value: 10 }, year: 2021 }
```



**参数传递**

```javascript
const sum = (num1, num2) => num1 + num2;

console.log(sum(...[6, 7])); // 13
console.log(sum(...[6, 7, 8])); // 13
```

从上面的代码看，函数定义了多少个参数，扩展运算符传入的值就是多少个。

和 `math` 函数一起使用，如下：

```javascript
const arrayNumbers = [1, 5, 9, 3, 5, 7, 10];
const min = Math.min(...arrayNumbers);
const max = Math.max(...arrayNumbers);
console.log(min); // 1
console.log(max); // 10
```



**数组去重**

与 `Set` 一起使用消除数组的重复项，如下：

```javascript
const arrayNumbers = [1, 5, 9, 3, 5, 7, 10, 4, 5, 2, 5];
const newNumbers = [...new Set(arrayNumbers)];
console.log(newNumbers); // [ 1,  5, 9, 3, 7, 10, 4, 2 ]
```



**字符串转字符数组**

`String` 也是一个可迭代对象，所以也可以使用扩展运算符 `...` 将其转为字符数组，如下：

```javascript
const title = "china";
const charts = [...title];
console.log(charts); // [ 'c', 'h', 'i', 'n', 'a' ]
```



# 6、模块

## 6.1、模块 (Module) 简介

很长一段时间，JavaScript 都没有语言级（language-level）的模块语法。这不是一个问题，因为最初的脚本又小又简单，所以没必要将其模块化。

但是最终脚本变得越来越复杂，因此社区发明了许多种方法来将代码组织到模块中，使用特殊的库按需加载模块。

语言级的模块系统在 2015 年的时候出现在了标准（ES6）中，此后逐渐发展，现在已经得到了所有主流浏览器和 Node.js 的支持。





### 6.1.1、什么是模块？

一个模块（module）就是一个文件。一个脚本就是一个模块。就这么简单。

模块可以相互加载，并可以使用特殊的指令 `export` 和 `import` 来交换功能，从另一个模块调用一个模块的函数：

- `export` 关键字标记了可以从当前模块外部访问的变量和函数。
- `import` 关键字允许从其他模块导入功能。

例如，我们有一个 `sayHi.js` 文件导出了一个函数：

```javascript
// 📁 sayHi.js
export function sayHi(user) {
  alert(`Hello, ${user}!`);
}
```

然后另一个文件可能导入并使用了这个函数：

```javascript
// 📁 main.js
import { sayHi } from './sayHi.js';

alert(sayHi); // function...
sayHi('John'); // Hello, John!
```

`import` 指令通过相对于当前文件的路径 `./sayHi.js` 加载模块，并将导入的函数 `sayHi` 分配（assign）给相应的变量。

由于模块支持特殊的关键字和功能，因此我们必须通过使用 `<script type="module">` 特性（attribute）来告诉浏览器，此脚本应该被当作模块（module）来对待。

```html
<!doctype html>
<script type="module">
  import {sayHi} from './say.js';

  document.body.innerHTML = sayHi('John');
</script>
```

> **模块只通过 HTTP(s) 工作，而非本地**
>
> 如果你尝试通过 `file://` 协议在本地打开一个网页，你会发现 `import/export` 指令不起作用。你可以使用本地 Web 服务器，例如 static-server，或者使用编辑器的“实时服务器”功能，例如 VS Code 的 Live Server Extension 来测试模块。



### 6.1.2、模块核心功能

**始终使用 “use strict”**

模块始终在严格模式下运行。例如，对一个未声明的变量赋值将产生错误（译注：在浏览器控制台可以看到 error 信息）。

```html
<script type="module">
  a = 5; // error
</script>
```



**模块级作用域**

每个模块都有自己的顶级作用域（top-level scope）。换句话说，一个模块中的顶级作用域变量和函数在其他脚本中是不可见的。

在下面这个例子中，`hello.js` 尝试使用在 `user.js` 中声明的变量 `user`。它失败了，因为它是一个单独的模块（你在控制台中可以看到报错）：

```javascript
// 📁 user.js
let user = "John";
```

```javascript
// 📁 hello.js
alert(user); // no such variable (each module has independent variables)
```

模块应该 `export` 它们想要被外部访问的内容，并 `import` 它们所需要的内容。

- `user.js` 应该导出 `user` 变量。
- `hello.js` 应该从 `user.js` 模块中导入它。

换句话说，对于模块，我们使用导入/导出而不是依赖全局变量。

这是正确的变体：

```javascript
// 📁 user.js
export let user = "John";
```

```javascript
// 📁 hello.js
import {user} from './user.js';

document.body.innerHTML = user; // John
```

在浏览器中，对于 HTML 页面，每个 `<script type="module">` 都存在独立的顶级作用域。

下面是同一页面上的两个脚本，都是 `type="module"`。它们看不到彼此的顶级变量：

```html
<script type="module">
  // 变量仅在这个 module script 内可见
  let user = "John";
</script>

<script type="module">
  alert(user); // Error: user is not defined
</script>
```

> 在浏览器中，我们可以通过将变量显式地分配给 `window` 的一个属性，使其成为窗口级别的全局变量。例如 `window.user = "John"`。
>
> 这样所有脚本都会看到它，无论脚本是否带有 `type="module"`。
>
> 也就是说，创建这种全局变量并不是一个好的方式。请尽量避免这样做。



**模块代码仅在第一次导入时被解析**

如果同一个模块被导入到多个其他位置，那么它的代码只会执行一次，即在第一次被导入时。然后将其导出（export）的内容提供给进一步的导入（importer）。

如果执行一个模块中的代码会带来副作用（side-effect），例如显示一条消息，那么多次导入它只会触发一次显示 —— 即第一次：

```javascript
// 📁 alert.js
alert("Module is evaluated!");
```

```javascript
// 在不同的文件中导入相同的模块

// 📁 1.js
import `./alert.js`; // Module is evaluated!

// 📁 2.js
import `./alert.js`; // (什么都不显示)
```

假设一个模块导出了一个对象：

```javascript
// 📁 admin.js
export let admin = {
  name: "John"
};
```

如果这个模块被导入到多个文件中，模块仅在第一次被导入时被解析，并创建 `admin` 对象，然后将其传入到所有的导入。

所有的导入都只获得了一个唯一的 `admin` 对象：

```javascript
// 📁 1.js
import { admin } from './admin.js';
admin.name = "Pete";

// 📁 2.js
import { admin } from './admin.js';
alert(admin.name); // Pete

// 1.js 和 2.js 引用的是同一个 admin 对象
// 在 1.js 中对对象做的更改，在 2.js 中也是可见的
```

因为该模块只执行了一次。生成导出，然后这些导出在导入之间共享，因此如果更改了 `admin` 对象，在其他导入中也会看到。



**import.meta**

`import.meta` 对象包含关于当前模块的信息。

它的内容取决于其所在的环境。在浏览器环境中，它包含当前脚本的 URL，或者如果它是在 HTML 中的话，则包含当前页面的 URL。

```html
<script type="module">
  alert(import.meta.url); // 脚本的 URL
  // 对于内联脚本来说，则是当前 HTML 页面的 URL
</script>
```



**在一个模块中，“this” 是 undefined**

在一个模块中，顶级 `this` 是 undefined。

将其与非模块脚本进行比较会发现，非模块脚本的顶级 `this` 是全局对象：

```html
<script>
  alert(this); // window
</script>

<script type="module">
  alert(this); // undefined
</script>
```



### 6.1.3、浏览器特定功能

与常规脚本相比，拥有 `type="module"` 标识的脚本有一些特定于浏览器的差异。



**模块脚本是延迟的**

模块脚本总是被延迟的，与 `defer` 特性对外部脚本和内联脚本（inline script）的影响相同。

也就是说：

- 下载外部模块脚本 `<script type="module" src="...">` 不会阻塞 HTML 的处理，它们会与其他资源并行加载。
- 模块脚本会等到 HTML 文档完全准备就绪（即使它们很小并且比 HTML 加载速度更快），然后才会运行。
- 保持脚本的相对顺序：在文档中排在前面的脚本先执行。

它的一个副作用是，模块脚本总是会 “看到” 已完全加载的 HTML 页面，包括在它们下方的 HTML 元素。

```html
<script type="module">
  alert(typeof button); // object：脚本可以“看见”下面的 button
  // 因为模块是被延迟的（deferred，所以模块脚本会在整个页面加载完成后才运行
</script>

相较于下面这个常规脚本：

<script>
  alert(typeof button); // button 为 undefined，脚本看不到下面的元素
  // 常规脚本会立即运行，常规脚本的运行是在在处理页面的其余部分之前进行的
</script>

<button id="button">Button</button>
```

上面的第二个脚本实际上要先于前一个脚本运行！所以我们会先看到 `undefined`，然后才是 `object`。

这是因为模块脚本是被延迟的，所以要等到 HTML 文档被处理完成才会执行它。而常规脚本则会立即运行，所以我们会先看到常规脚本的输出。

当使用模块脚本时，我们应该知道 HTML 页面在加载时就会显示出来，在 HTML 页面加载完成后才会执行 JavaScript 模块，因此用户可能会在 JavaScript 应用程序准备好之前看到该页面。某些功能那时可能还无法正使用。我们应该放置 “加载指示器（loading indicator）”，否则，请确保不会使用户感到困惑。



**Async 适用于内联脚本（inline script）**

对于非模块脚本，`async` 特性（attribute）仅适用于外部脚本。异步脚本会在准备好后立即运行，独立于其他脚本或 HTML 文档。

对于模块脚本，它也适用于内联脚本。

例如，下面的内联脚本具有 `async` 特性，因此它不会等待任何东西。

它执行导入（fetch `./analytics.js`），并在导入完成时运行，即使 HTML 文档还未完成，或者其他脚本仍在等待处理中。

这对于不依赖任何其他东西的功能来说是非常棒的，例如计数器，广告，文档级事件监听器。

```html
<!-- 所有依赖都获取完成（analytics.js）然后脚本开始运行 -->
<!-- 不会等待 HTML 文档或者其他 <script> 标签 -->
<script async type="module">
  import {counter} from './analytics.js';

  counter.count();
</script>
```



**外部脚本**

具有 `type="module"` 的外部脚本（external script）在两个方面有所不同：

1. 具有相同 `src` 的外部脚本仅运行一次：

   ```html
   <!-- 脚本 my.js 被加载完成（fetched）并只被运行一次 -->
   <script type="module" src="my.js"></script>
   <script type="module" src="my.js"></script>
   ```

2. 从另一个源（例如另一个网站）获取的外部脚本需要 CORS header。换句话说，如果一个模块脚本是从另一个源获取的，则远程服务器必须提供表示允许获取的 header `Access-Control-Allow-Origin`。

   ```html
   <!-- another-site.com 必须提供 Access-Control-Allow-Origin -->
   <!-- 否则，脚本将无法执行 -->
   <script type="module" src="http://another-site.com/their.js"></script>
   ```

   默认这样做可以确保更好的安全性。



**不允许裸模块（“bare” module）**

在浏览器中，`import` 必须给出相对或绝对的 URL 路径。没有任何路径的模块被称为 “裸（bare）” 模块。在 `import` 中不允许这种模块。

例如，下面这个 `import` 是无效的：

```javascript
import {sayHi} from 'sayHi'; // Error，“裸”模块
// 模块必须有一个路径，例如 './sayHi.js' 或者其他任何路径
```

某些环境，像 Node.js 或者打包工具（bundle tool）允许没有任何路径的裸模块，因为它们有自己的查找模块的方法和钩子（hook）来对它们进行微调。但是浏览器尚不支持裸模块。



**兼容性，“nomodule”**

旧时的浏览器不理解 `type="module"`。未知类型的脚本会被忽略。对此，我们可以使用 `nomodule` 特性来提供一个后备：

```html
<script type="module">
  alert("在现代浏览器中运行");
</script>

<script nomodule>
  alert("现代浏览器都知道 type=module 和 nomodule，所以跳过这个")
  alert("旧浏览器忽略 type=module 的未知脚本，但执行它。");
</script>
```



## 6.2、导出和导入

导出（export）和导入（import）指令有几种语法变体。



### 6.2.1、导出与声明

**在声明前导出**

我们可以通过在声明之前放置 `export` 来标记任意声明为导出，无论声明的是变量，函数还是类都可以。

```javascript
// 导出数组
export let months = ['Jan', 'Feb', 'Mar','Apr', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

// 导出 const 声明的变量
export const MODULES_BECAME_STANDARD_YEAR = 2015;

// 导出类
export class User {
  constructor(name) {
    this.name = name;
  }
}
```

> **导出 class/function 后没有分号**
>
> 注意，在类或者函数前的 `export` 不会让它们变成 函数表达式。尽管被导出了，但它仍然是一个函数声明。
>
> 大部分 JavaScript 样式指南都不建议在函数和类声明后使用分号。
>
> 这就是为什么在 `export class` 和 `export function` 的末尾不需要加分号：
>
> ```javascript
> export function sayHi(user) {
>   alert(`Hello, ${user}!`);
> }  // 在这里没有分号 ;
> ```



**导出与声明分开**

另外，我们还可以将 `export` 分开放置。

```javascript
// 📁 say.js
function sayHi(user) {
  alert(`Hello, ${user}!`);
}

function sayBye(user) {
  alert(`Bye, ${user}!`);
}

export {sayHi, sayBye}; // 导出变量列表
```

……从技术上讲，我们也可以把 `export` 放在函数上面。



### 6.2.2、Import *

通常，我们把要导入的东西列在花括号 `import {...}` 中，就像这样：

```javascript
// 📁 main.js
import {sayHi, sayBye} from './say.js';

sayHi('John'); // Hello, John!
sayBye('John'); // Bye, John!
```

但是如果有很多要导入的内容，我们可以使用 `import * as <obj>` 将所有内容导入为一个对象，例如：

```javascript
// 📁 main.js
import * as say from './say.js';

say.sayHi('John');
say.sayBye('John');
```

乍一看，“通通导入” 看起来很酷，写起来也很短，但是我们通常为什么要明确列出我们需要导入的内容？

1. 现代的构建工具（webpack 和其他工具）将模块打包到一起并对其进行优化，以加快加载速度并删除未使用的代码。

   比如说，我们向我们的项目里添加一个第三方库 `say.js`，它具有许多函数：

   ```javascript
   // 📁 say.js
   export function sayHi() { ... }
   export function sayBye() { ... }
   export function becomeSilent() { ... }
   ```

   现在，如果我们只在我们的项目里使用了 `say.js` 中的一个函数：

   ```javascript
   // 📁 main.js
   import {sayHi} from './say.js';
   ```

   那么，优化器（optimizer）就会检测到它，并从打包好的代码中删除那些未被使用的函数，从而使构建更小。这就是所谓的 “摇树（tree-shaking）”。

2. 明确列出要导入的内容会使得名称较短：`sayHi()` 而不是 `say.sayHi()`。

3. 导入的显式列表可以更好地概述代码结构：使用的内容和位置。它使得代码支持重构，并且重构起来更容易。



### 6.2.3、Import/Export “as”

**Import “as”**

我们也可以使用 `as` 让导入具有不同的名字。

例如，简洁起见，我们将 `sayHi` 导入到局部变量 `hi`，将 `sayBye` 导入到 `bye`：

```javascript
// 📁 main.js
import {sayHi as hi, sayBye as bye} from './say.js';

hi('John'); // Hello, John!
bye('John'); // Bye, John!
```



**Export “as”**

导出也具有类似的语法。

我们将函数导出为 `hi` 和 `bye`：

```javascript
// 📁 say.js
...
export {sayHi as hi, sayBye as bye};
```

现在 `hi` 和 `bye` 是在外面使用时的正式名称：

```javascript
// 📁 main.js
import * as say from './say.js';

say.hi('John'); // Hello, John!
say.bye('John'); // Bye, John!
```



### 6.2.4、Export default

在实际中，主要有两种模块。

- 包含库或函数包的模块，像上面的 `say.js`。
- 声明单个实体的模块，例如模块 `user.js` 仅导出 `class User`。

大部分情况下，开发者倾向于使用第二种方式，以便每个 “东西” 都存在于它自己的模块中。

当然，这需要大量文件，因为每个东西都需要自己的模块，但这根本不是问题。实际上，如果文件具有良好的命名，并且文件夹结构得当，那么代码导航（navigation）会变得更容易。

模块提供了一个特殊的默认导出 `export default` 语法，以使 “一个模块只做一件事” 的方式看起来更好。

将 `export default` 放在要导出的实体前：

```javascript
// 📁 user.js
export default class User { // 只需要添加 "default" 即可
  constructor(name) {
    this.name = name;
  }
}
```

每个文件应该只有一个 `export default` 并且将其导入而不需要花括号：

```javascript
// 📁 main.js
import User from './user.js'; // 不需要花括号 {User}，只需要写成 User 即可

new User('John');
```

`import` 命名的导出时需要花括号，而 `import` 默认的导出时不需要花括号：

| 命名的导出                | 默认的导出                        |
| :------------------------ | :-------------------------------- |
| `export class User {...}` | `export default class User {...}` |
| `import {User} from ...`  | `import User from ...`            |

导出的实体可能没有名称。因为每个文件只有一个 `export default`，因此不带花括号的 `import` 知道要导入的内容是什么。

如果没有 `default`，这样的导出将会出错：

```javascript
export class { // Error!（非默认的导出需要名称）
  constructor() {}
}
```



**“default” 名称**

在某些情况下，`default` 关键词被用于引用默认的导出。

例如，要将函数与其定义分开导出：

```javascript
function sayHi(user) {
  alert(`Hello, ${user}!`);
}

// 就像我们在函数之前添加了 "export default" 一样
export {sayHi as default};
```

或者，另一种情况，假设模块 `user.js` 导出了一个主要的默认的导出和一些命名的导出（这种情况很少见，但确实会发生）：

```javascript
// 📁 user.js
export default class User {
  constructor(name) {
    this.name = name;
  }
}

export function sayHi(user) {
  alert(`Hello, ${user}!`);
}
```

这是导入默认的导出以及命名的导出的方法：

```javascript
// 📁 main.js
import {default as User, sayHi} from './user.js';

new User('John');
```

如果我们将所有东西 `*` 作为一个对象导入，那么 `default` 属性正是默认的导出：

```javascript
// 📁 main.js
import * as user from './user.js';

let User = user.default; // 默认的导出
new User('John');
```



**我应该使用默认的导出吗？**

命名的导出是明确的。它们确切地命名了它们要导出的内容，因此我们能从它们获得这些信息，这是一件好事。

命名的导出会强制我们使用正确的名称进行导入：

```javascript
import {User} from './user.js';
// 导入 {MyUser} 不起作用，导入名字必须为 {User}
```

对于默认的导出，我们总是在导入时选择名称：

```javascript
import User from './user.js'; // 有效
import MyUser from './user.js'; // 也有效
// 使用任何名称导入都没有问题
```

通常，为了避免这种情况并使代码保持一致，可以遵从这条规则，即导入的变量应与文件名相对应，例如：

```javascript
import User from './user.js';
import LoginForm from './loginForm.js';
import func from '/path/to/func.js';
...
```



### 6.2.5、重新导出

“重新导出（Re-export）”语法 `export ... from ...` 允许导入内容，并立即将其导出（可能是用的是其他的名字），就像这样：

```javascript
export {sayHi} from './say.js'; // 重新导出 sayHi

export {default as User} from './user.js'; // 重新导出 default
```

为什么要这样做？我们看一个实际开发中的用例。

想象一下，我们正在编写一个 “package”：一个包含大量模块的文件夹，其中一些功能是导出到外部的（像 NPM 这样的工具允许我们发布和分发这样的 package，但我们不是必须要去使用它们），并且其中一些模块仅仅是供其他 package 中的模块内部使用的 “helpers”。

文件结构可能是这样的：

```
auth/
    index.js
    user.js
    helpers.js
    tests/
        login.js
    providers/
        github.js
        facebook.js
        ...
```

我们希望通过单个入口暴露包的功能。

换句话说，想要使用我们的包的人，应该只从 “主文件” `auth/index.js` 导入。

像这样：

```javascript
import {login, logout} from 'auth/index.js'
```

“主文件”，`auth/index.js` 导出了我们希望在包中提供的所有功能。

这样做是因为，其他使用我们包的开发者不应该干预其内部结构，不应该搜索我们包的文件夹中的文件。我们只在 `auth/index.js` 中导出必要的部分，并保持其他内容 “不可见”。

由于实际导出的功能分散在 package 中，所以我们可以将它们导入到 `auth/index.js`，然后再从中导出它们：

```javascript
// 📁 auth/index.js

// 导入 login/logout 然后立即导出它们
import {login, logout} from './helpers.js';
export {login, logout};

// 将默认导出导入为 User，然后导出它
import User from './user.js';
export {User};
...
```

现在使用我们 package 的人可以 `import {login} from "auth/index.js"`。

语法 `export ... from ...` 只是下面这种导入-导出的简写：

```javascript
// 📁 auth/index.js
// 重新导出 login/logout
export {login, logout} from './helpers.js';

// 将默认导出重新导出为 User
export {default as User} from './user.js';
...
```

`export ... from` 与 `import/export` 相比的显着区别是重新导出的模块在当前文件中不可用。所以在上面的 `auth/index.js` 示例中，我们不能使用重新导出的 `login/logout` 函数。



**重新导出默认导出**

重新导出时，默认导出需要单独处理。

假设我们有一个 `user.js` 脚本，其中写了 `export default class User`，并且我们想重新导出类 `User`：

```javascript
// 📁 user.js
export default class User {
  // ...
}
```

我们可能会遇到两个问题：

1. `export User from './user.js'` 无效。这会导致一个语法错误。

   要重新导出默认导出，我们必须明确写出 `export {default as User}`，就像上面的例子中那样。

2. `export * from './user.js'` 重新导出只导出了命名的导出，但是忽略了默认的导出。

   如果我们想将命名的导出和默认的导出都重新导出，那么需要两条语句：

   ```javascript
   export * from './user.js'; // 重新导出命名的导出
   export {default} from './user.js'; // 重新导出默认的导出
   ```

重新导出一个默认导出的这种奇怪现象，是某些开发者不喜欢默认导出，而是喜欢命名的导出的原因之一。



# 7、杂项

## 7.1、脚本：async，defer

现代的网站中，脚本往往比 HTML 更 “重”：它们的大小通常更大，处理时间也更长。

当浏览器加载 HTML 时遇到 `<script>...</script>` 标签，浏览器就不能继续构建 DOM。它必须立刻执行此脚本。对于外部脚本 `<script src="..."></script>` 也是一样的：浏览器必须等脚本下载完，并执行结束，之后才能继续处理剩余的页面。

这会导致两个重要的问题：

1. 脚本不能访问到位于它们下面的 DOM 元素，因此，脚本无法给它们添加处理程序等。

2. 如果页面顶部有一个笨重的脚本，它会 “阻塞页面”。在该脚本下载并执行结束前，用户都不能看到页面内容：

   ```html
   <p>...content before script...</p>
   
   <script src="https://javascript.info/article/script-async-defer/long.js?speed=1"></script>
   
   <!-- This isn't visible until the script loads -->
   <p>...content after script...</p>
   ```

这里有一些解决办法。例如，我们可以把脚本放在页面底部。此时，它可以访问到它上面的元素，并且不会阻塞页面显示内容：

```html
<body>
  ...all content is above the script...

  <script src="https://javascript.info/article/script-async-defer/long.js?speed=1"></script>
</body>
```

但是这种解决方案远非完美。例如，浏览器只有在下载了完整的 HTML 文档之后才会注意到该脚本（并且可以开始下载它）。对于长的 HTML 文档来说，这样可能会造成明显的延迟。

幸运的是，这里有两个 `<script>` 特性（attribute）可以为我们解决这个问题：`defer` 和 `async`。



### 7.1.1、defer

`defer` 特性告诉浏览器不要等待脚本。相反，浏览器将继续处理 HTML，构建 DOM。脚本会 “在后台” 下载，然后等 DOM 构建完成后，脚本才会执行。

这是与上面那个相同的示例，但是带有 `defer` 特性：

```html
<p>...content before script...</p>

<script defer src="https://javascript.info/article/script-async-defer/long.js?speed=1"></script>

<!-- 立即可见 -->
<p>...content after script...</p>
```

换句话说：

- 具有 `defer` 特性的脚本不会阻塞页面。
- 具有 `defer` 特性的脚本总是要等到 DOM 解析完毕，但在 `DOMContentLoaded` 事件之前执行。

```html
<p>...content before scripts...</p>

<script>
  document.addEventListener('DOMContentLoaded', () => alert("DOM ready after defer!"));
</script>

<script defer src="https://javascript.info/article/script-async-defer/long.js?speed=1"></script>

<p>...content after scripts...</p>
```

1. 页面内容立即显示。
2. `DOMContentLoaded` 事件处理程序等待具有 `defer` 特性的脚本执行完成。它仅在脚本下载且执行结束后才会被触发。



**具有 `defer` 特性的脚本保持其相对顺序，就像常规脚本一样。**

假设，我们有两个具有 `defer` 特性的脚本：`long.js` 在前，`small.js` 在后。

```html
<script defer src="https://javascript.info/article/script-async-defer/long.js"></script>
<script defer src="https://javascript.info/article/script-async-defer/small.js"></script>
```

浏览器扫描页面寻找脚本，然后并行下载它们，以提高性能。因此，在上面的示例中，两个脚本是并行下载的。`small.js` 可能会先下载完成。

但是，`defer` 特性除了告诉浏览器 “不要阻塞页面” 之外，还可以确保脚本执行的相对顺序。因此，即使 `small.js` 先加载完成，它也需要等到 `long.js` 执行结束才会被执行。

当我们需要先加载 JavaScript 库，然后再加载依赖于它的脚本时，这可能会很有用。

> **`defer` 特性仅适用于外部脚本**
>
> 如果 `<script>` 脚本没有 `src`，则会忽略 `defer` 特性。



### 7.1.2、async

`async` 特性与 `defer` 有些类似。它也能够让脚本不阻塞页面。但是，在行为上二者有着重要的区别。

`async` 特性意味着脚本是完全独立的：

- 浏览器不会因 `async` 脚本而阻塞（与 `defer` 类似）。
- 其他脚本不会等待 `async` 脚本加载完成，同样，`async` 脚本也不会等待其他脚本。
- `DOMContentLoaded` 和异步脚本不会彼此等待：
  - `DOMContentLoaded` 可能会发生在异步脚本之前（如果异步脚本在页面完成后才加载完成）
  - `DOMContentLoaded` 也可能发生在异步脚本之后（如果异步脚本很短，或者是从 HTTP 缓存中加载的）

换句话说，`async` 脚本会在后台加载，并在加载就绪时运行。DOM 和其他脚本不会等待它们，它们也不会等待其它的东西。`async` 脚本就是一个会在加载完成时执行的完全独立的脚本。

下面是一个类似于我们在讲 `defer` 时所看到的例子：`long.js` 和 `small.js` 两个脚本，只是现在 `defer` 变成了 `async`。

它们不会等待对方。先加载完成的（可能是 `small.js`）—— 先执行：

```html
<p>...content before scripts...</p>

<script>
  document.addEventListener('DOMContentLoaded', () => alert("DOM ready!"));
</script>

<script async src="https://javascript.info/article/script-async-defer/long.js"></script>
<script async src="https://javascript.info/article/script-async-defer/small.js"></script>

<p>...content after scripts...</p>
```

- 页面内容立刻显示出来：加载写有 `async` 的脚本不会阻塞页面渲染。
- `DOMContentLoaded` 可能在 `async` 之前或之后触发，不能保证谁先谁后。
- 较小的脚本 `small.js` 排在第二位，但可能会比 `long.js` 这个长脚本先加载完成，所以 `small.js` 会先执行。虽然，可能是 `long.js` 先加载完成，如果它被缓存了的话，那么它就会先执行。换句话说，异步脚本以 “加载优先” 的顺序执行。

当我们将独立的第三方脚本集成到页面时，此时采用异步加载方式是非常棒的：计数器，广告等，因为它们不依赖于我们的脚本，我们的脚本也不应该等待它们：

```html
<!-- Google Analytics 脚本通常是这样嵌入页面的 -->
<script async src="https://google-analytics.com/analytics.js"></script>
```

> **`async` 特性仅适用于外部脚本**
>
> 就像 `defer` 一样，如果 `<script>` 标签没有 `src` 特性（attribute），那么 `async` 特性会被忽



## 7.2、正则表达式

### 7.2.1、RegExp 对象

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



### 7.2.2、RegExp 方法

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
