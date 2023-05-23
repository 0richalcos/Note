---
typora-copy-images-to: upload
---

# 1、简介

X6 是 AntV 旗下的图编辑引擎，提供了一系列开箱即用的交互组件和简单易用的节点定制能力，方便我们快速搭建流程图、DAG 图、ER 图等图应用。



**特性**

- 🌱　极易定制：支持使用 SVG/HTML/React/Vue 定制节点样式和交互；
- 🚀　开箱即用：内置 10+ 图编辑配套扩展，如框选、对齐线、小地图等；
- 🧲　数据驱动：基于 MVC 架构，用户更加专注于数据逻辑和业务逻辑；
- 💯　事件驱动：可以监听图表内发生的任何事件。



# 2、快速上手

## 2.1、安装

通过 npm 或 yarn 命令安装 X6：

```shell
# npm
$ npm install @antv/x6 --save

# yarn
$ yarn add @antv/x6
```

安装完成之后，使用 `import` 或 `require` 进行引用：

```ts
import { Graph } from '@antv/x6';
```

如果是直接通过 `script` 标签引入，可以使用下面三个 CDN 中的任何一个，默认返回 X6 的最新版：

- https://unpkg.com/@antv/x6/dist/x6.js
- https://cdn.jsdelivr.net/npm/@antv/x6/dist/x6.js
- https://cdnjs.cloudflare.com/ajax/libs/antv-x6/1.3.20/x6.js (不支持获取最新版)

```html
<script src="https://unpkg.com/@antv/x6/dist/x6.js"></script>
```

在生产环境推荐使用一个明确的版本号，以避免新版本造成的不可预期的破坏：

- https://unpkg.com/@antv/x6@1.1.1/dist/x6.js
- https://cdn.jsdelivr.net/npm/@antv/x6@1.1.1/dist/x6.js
- https://cdnjs.cloudflare.com/ajax/libs/antv-x6/1.1.1/x6.js

```html
<script src="https://unpkg.com/@antv/x6@1.1.1/dist/x6.js"></script>
```



## 2.2、开始使用

创建一个最简单的 `Hello --> World` 应用。

1. **创建容器**

   在页面中创建一个用于容纳 X6 绘图的容器，可以是一个 `div` 标签：

   ```html
   <div id="container"></div>
   ```

2. **准备数据**

   X6 支持 JSON 格式数据，该对象中需要有节点 `nodes` 和边 `edges` 字段，分别用数组表示：

   ```ts
   const data = {
     // 节点
     nodes: [
       {
         id: 'node1', // String，可选，节点的唯一标识
         x: 40,       // Number，必选，节点位置的 x 值
         y: 40,       // Number，必选，节点位置的 y 值
         width: 80,   // Number，可选，节点大小的 width 值
         height: 40,  // Number，可选，节点大小的 height 值
         label: 'hello', // String，节点标签
       },
       {
         id: 'node2', // String，节点的唯一标识
         x: 160,      // Number，必选，节点位置的 x 值
         y: 180,      // Number，必选，节点位置的 y 值
         width: 80,   // Number，可选，节点大小的 width 值
         height: 40,  // Number，可选，节点大小的 height 值
         label: 'world', // String，节点标签
       },
     ],
     // 边
     edges: [
       {
         source: 'node1', // String，必须，起始节点 id
         target: 'node2', // String，必须，目标节点 id
       },
     ],
   };
   ```

3. **渲染画布**

   首先，需要创建一个 `Graph` 对象，并为其指定一个页面上的绘图容器，通常也会指定画布的大小：

   ```ts
   import { Graph } from '@antv/x6';
   
   // 使用 CDN 引入时暴露了 X6 全局变量
   // const { Graph } = X6
   
   const graph = new Graph({
     container: document.getElementById('container'),
     width: 800,
     height: 600,
   });
   ```

   如果是通过 `script` 标签引入方式， `Graph` 对象是挂载在 `X6` 这个全局变量下面：

   ```html
   <script src="https://unpkg.com/@antv/x6/dist/x6.js"></script>
   <script>
     const graph = new X6.Graph({
       container: document.getElementById('container'),
       width: 800,
       height: 600,
     });
   </script>
   ```

   然后就可以使用刚刚创建的 `graph` 来渲染节点和边：
   ```ts
   graph.fromJSON(data)
   ```

   到此，我们就得到一个最简单的 `Hello --> World` 示例：

   <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20220505005938460.png" alt="image-20220505005938460" style="width:70%;" />



## 2.3、画布

**背景和网格**

接下来给画布设置一个背景颜色和网格，另外还支持背景图片、网格类型等配置：

```ts
import { Graph } from '@antv/x6';

const graph = new Graph({
  container: document.getElementById('container'),
  width: 800,
  height: 600,
  background: {
    color: '#fffbe6', // 设置画布背景颜色
  },
  grid: {
    size: 10,      // 网格大小 10px
    visible: true, // 渲染网格背景
  },
});
```

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20220505010235847.png" alt="image-20220505010235847" style="width:75%;" />




**缩放和平移**

创建画布后，可以调用 `graph.zoom(factor: number)` 和 `graph.translate(tx: number, ty: number)` 来缩放和平移画布：

```ts
graph.zoom(-0.5)
graph.translate(80, 40)
```

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20220505010314434.png" alt="image-20220505010314434" style="width:100%;" />




## 2.4、节点

**使用图形**

在上面示例中使用了默认图形 `rect` 来渲染节点，除此之外， X6 中也内置了 `circle`、`ellipse`、`polygon` 等基础图形，可以通过 `shape` 属性为节点指定渲染的图形：

```ts
const data = {
  nodes: [
    {
      id: 'node1',
      shape: 'rect', // 使用 rect 渲染
      x: 100,
      y: 200,
      width: 80,
      height: 40,
      label: 'hello',
    },
    {
      id: 'node2',
      shape: 'ellipse', // 使用 ellipse 渲染
      x: 300,
      y: 200,
      width: 80,
      height: 40,
      label: 'world',
    },
  ],
  edges: [
    {
      source: 'node1',
      target: 'node2',
    },
  ],
};
```

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20220505010457515.png" alt="image-20220505010457515" style="width:70%;" />




**定制样式**

在创建节点或准备节点数据时可以通过 `attrs` 对象来配置节点样式，该对象的 Key 是节点 SVG 元素的选择器（Selector），对应的值是应用到该 SVG 元素的 SVG 属性值（如 fill 和 stroke）。

`'rect'` 图形中定义了 `'body'`（代表 `<rect>` 元素）和 `'label'`（代表 `<text>` 元素）两个选择器（Selector），每种图形都有属于自己的选择器定义。

```ts
const data = {
  nodes: [
    {
      id: 'node1',
      x: 40,
      y: 40,
      width: 100,
      height: 40,
      attrs: {
        body: {
          fill: '#2ECC71',
          stroke: '#000',
          strokeDasharray: '10,2',
        },
        label: {
          text: 'Hello',
          fill: '#333',
          fontSize: 13,
        }
      }
    },
    {
      id: 'node2',
      x: 180,
      y: 240,
      width: 100,
      height: 40,
      attrs: {
        body: {
          fill: '#F39C12',
          stroke: '#000',
          rx: 16,
          ry: 16,
        },
        label: {
          text: 'World',
          fill: '#333',
          fontSize: 18,
          fontWeight: 'bold',
          fontVariant: 'small-caps',
        },
      },
    },
  ],
  edges: [
    {
      source: 'node1',
      target: 'node2',
    },
  ],
};
```

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20220505010914905.png" alt="image-20220505010914905" style="width:80%;" />




## 2.5、边

**使用图形**

在上面示例中使用了默认图形 `edge` 来渲染边，除此之外，在 X6 中还内置了 `double-edge` 和 `shadow-edge` 两种图形，可以通过 `shape` 属性为边指定渲染的图形，例如：

```ts
const data = {
  nodes: [
    {
      id: 'node1',
      shape: 'rect',
      x: 100,
      y: 100,
      width: 80,
      height: 40,
      label: 'hello',
    },
    {
      id: 'node2',
      shape: 'ellipse',
      x: 240,
      y: 300,
      width: 80,
      height: 40,
      label: 'world',
    },
  ],
  edges: [
    {
      source: 'node1',
      target: 'node2',
      shape: 'double-edge',
    },
  ],
}
```

 <img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20220505011011412.png" alt="image-20220505011011412" style="width:80%;" />




**定制样式**

与定制节点样式一样，使用 `attrs` 对象来配置边的样式，默认的 `edge` 图形定义了 `'line'`（`<path>` 元素）和 `'wrap'`（透明的 `<path>` 元素，更宽但不可见，为了方便交互）两个选择器（Selector）。

```ts
const data = {
  nodes: [
    {
      id: 'node1',
      shape: 'rect',
      x: 100,
      y: 100,
      width: 80,
      height: 40,
      label: 'hello',
    },
    {
      id: 'node2',
      shape: 'ellipse',
      x: 240,
      y: 300,
      width: 80,
      height: 40,
      label: 'world',
    },
  ],
  edges: [
    {
      source: 'node1',
      target: 'node2',
      attrs: {
        line: {
          stroke: 'orange',
        },
      },
    },
  ],
}
```

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20220505011203840.png" alt="image-20220505011203840" style="width:80%;" />




# 3、基础教程

