# Poi-tl

## 1、简介

poi-tl（poi template language）是一个基于 Apache POI 的 Word 模板引擎，也是一个免费开源的Java类库，它可以使用 Word 模板和数据创建很棒的 Word 文档。



### 1.1、为什么选择 poi-tl

| 方案            | 移植性                       | 功能性                                                     | 易用性                                                       |
| :-------------- | :--------------------------- | :--------------------------------------------------------- | :----------------------------------------------------------- |
| **Poi-tl**      | Java 跨平台                  | Word 模板引擎，基于 Apache POI，提供更友好的 API           | 低代码，准备文档模板和数据即可                               |
| Apache POI      | Java 跨平台                  | Apache 项目，封装了常见的文档操作，也可以操作底层 XML 结构 | 文档不全，这里有一个教程：[Apache POI Word快速入门](http://deepoove.com/poi-tl/apache-poi-guide.html) |
| Freemarker      | XML 跨平台                   | 仅支持文本，很大的局限性                                   | 不推荐，XML 结构的代码几乎无法维护                           |
| OpenOffice      | 部署 OpenOffice，移植性较差  | -                                                          | 需要了解 OpenOffice 的 API                                   |
| HTML 浏览器导出 | 依赖浏览器的实现，移植性较差 | HTML 不能很好的兼容 Word 的格式，样式糟糕                  | -                                                            |
| Jacob、winlib   | Windows 平台                 | -                                                          | 复杂，完全不推荐使用                                         |

| Word模板引擎功能     | 描述                                                         |
| :------------------- | :----------------------------------------------------------- |
| 文本                 | 将标签渲染为文本                                             |
| 图片                 | 将标签渲染为图片                                             |
| 表格                 | 将标签渲染为表格                                             |
| 列表                 | 将标签渲染为列表                                             |
| 图表                 | 条形图（3D 条形图）、柱形图（3D 柱形图）、面积图（3D 面积图）、折线图（3D 折线图）、雷达图、饼图（3D 饼图）、散点图等图表渲染 |
| If Condition 判断    | 根据条件隐藏或者显示某些文档内容（包括文本、段落、图片、表格、列表、图表等） |
| Foreach Loop 循环    | 根据集合循环某些文档内容（包括文本、段落、图片、表格、列表、图表等） |
| Loop 表格行          | 循环复制渲染表格的某一行                                     |
| Loop 表格列          | 循环复制渲染表格的某一列                                     |
| Loop 有序列表        | 支持有序列表的循环，同时支持多级列表                         |
| Highlight 代码高亮   | Word 中代码块高亮展示，支持 26 种语言和上百种着色样式        |
| Markdown             | 将 Markdown 渲染为 Word 文档                                 |
| Word 批注            | 完整的批注功能，创建批注、修改批注等                         |
| Word 附件            | Word 中插入附件                                              |
| SDT 内容控件         | 内容控件内标签支持                                           |
| Textbox 文本框       | 文本框内标签支持                                             |
| 图片替换             | 将原有图片替换成另一张图片                                   |
| 书签、锚点、超链接   | 支持设置书签，文档内锚点和超链接功能                         |
| Expression Language  | 完全支持 SpringEL 表达式，可以扩展更多的表达式：OGNL、MVEL…  |
| 样式                 | 模板即样式，同时代码也可以设置样式                           |
| 模板嵌套             | 模板包含子模板，子模板再包含子模板                           |
| 合并                 | Word 合并 Merge，也可以在指定位置进行合并                    |
| 用户自定义函数(插件) | 插件化设计，在文档任何位置执行函数                           |



### 1.2、版本

Apache POI 已经进入 5.0.0+ 时代，如果你仍希望使用低版本的 Apache POI，请查阅历史版本。

-  当前版本 1.12.1 Documentation，Apache POI5.2.2+，JDK1.8+
- 1.11.x Documentation，Apache POI5.1.0+，JDK1.8+
- 1.10.x Documentation，Apache POI4.1.2，JDK1.8+
- 1.9.x Documentation，Apache POI4.1.2，JDK1.8+
- 1.8.x Documentation，Apache POI4.1.2，JDK1.8+
- 1.7.x Documentation，Apache POI4.0.0+，JDK1.8+
- 1.6.x Documentation，Apache POI4.0.0+，JDK1.8+
- 1.5.x Documentation，Apache POI3.16+，JDK1.6+

V1.12.x 版本作了一个不兼容的改动，升级的时候需要注意：

- 重构了 PictureRenderData，改为抽象类，建议使用 Pictures 工厂方法来创建图片数据



### 1.3、快速开始

**Maven**

```xml
<dependency>
  <groupId>com.deepoove</groupId>
  <artifactId>poi-tl</artifactId>
  <version>1.12.1</version>
</dependency>
```



**示例**

新建 Word 文档 template.docx，包含标签 `{{title}}`：

```
{{title}}
```

代码示例：

```java
XWPFTemplate template = XWPFTemplate.compile("template.docx").render(
  new HashMap<String, Object>(){{
    put("title", "Hi, poi-tl Word模板引擎");
}});  
template.writeAndClose(new FileOutputStream("output.docx")); 
```

1. compile 编译模板
2. render 渲染数据
3. write 输出到流

> TDO 模式：Template + data-model = output

输出 output.docx：

```
Hi, poi-tl Word模板引擎
```



**Template：模板**

模板是 Docx 格式的 Word 文档，你可以使用 Microsoft office、WPS Office、Pages 等任何你喜欢的软件制作模板，也可以使用 Apache POI 代码来生成模板。

所有的标签都是以 `{{` 开头，以 `}}` 结尾，标签可以出现在任何位置，包括页眉，页脚，表格内部，文本框等，表格布局可以设计出很多优秀专业的文档，推荐使用表格布局。

poi-tl 模板遵循 “所见即所得” 的设计，模板和标签的样式会被完全保留。



**Data-model：数据**

数据类似于哈希或者字典，可以是 Map 结构（key 是标签名称）：

```java
Map<String, Object> data = new HashMap<>();
data.put("name", "Sayi");
data.put("start_time", "2019-08-04");
```

可以是对象（属性名是标签名称）：

```java
public class Data {
  private String name;
  private String startTime;
  private Author author;
}
```

> 数据可以是树结构，每级之间用点来分隔开，比如 `{{author.name}}` 标签对应的数据是 author 对象的 name 属性值。

FreeMarker、Velocity 文本模板中可以通过三个标签设置图片路径、宽和高：

```html
<img src="{{path}}" width="{{width}}" height="{{height}}">
```

但是Word模板不是由简单的文本表示，所以在渲染图片、表格等元素时提供了数据模型，它们都实现了接口 RenderData，比如图片数据模型 PictureRenderData 包含图片路径、宽、高三个属性。



**Output：输出**

以流的方式进行输出：

```java
template.write(OutputStream stream);
```

可以写到任意输出流中，比如文件流：

```java
template.write(new FileOutputStream("output.docx"));
```

比如网络流：

```java
response.setContentType("application/octet-stream");
response.setHeader("Content-disposition","attachment;filename=\""+"out_template.docx"+"\"");

// HttpServletResponse response
OutputStream out = response.getOutputStream();
BufferedOutputStream bos = new BufferedOutputStream(out);
template.write(bos);
bos.flush();
out.flush();
PoitlIOUtils.closeQuietlyMulti(template, bos, out);
```

> 最后不要忘记关闭这些流。

