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