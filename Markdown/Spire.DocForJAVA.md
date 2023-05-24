---
typora-copy-images-to: upload
---

# 1、Spire.DocForJAVA

Spire.Doc for Java 是一款专业的 Java Word 组件，开发人员使用它可以轻松地将 Word 文档创建、读取、编辑、转换和打印等功能集成到自己的 Java 应用程序中。作为一款完全独立的组件，Spire.Doc for Java 的运行环境无需安装 Microsoft Office。

Spire.Doc for Java 能执行多种 Word 文档处理任务，包括生成、读取、转换和打印 Word 文档，插入图片，添加页眉和页脚，创建表格，添加表单域和邮件合并域，添加书签，添加文本和图片水印，设置背景颜色和背景图片，添加脚注和尾注，添加超链接，加密和解密 Word 文档，添加批注，添加形状等。



**引用 Spire.DocForJAVA**

首先，在 pom.xml 文件中配置 Maven 仓库路径：

```xml
<repositories>
    <repository>
        <id>com.e-iceblue</id>
        <name>e-iceblue</name>
        <url>https://repo.e-iceblue.cn/repository/maven-public/</url>
    </repository>
</repositories>
```

然后，在 pom.xml 文件中指定 Spire 产品的 Maven 依赖：

```xml
<dependencies>
    <dependency>
        <groupId>e-iceblue</groupId>
        <artifactId>spire.doc.free</artifactId>
        <version>5.2.0</version>
    </dependency>
</dependencies>
```



## 1.1、比较两个 Word 文档的内容

自版本 3.8.8 开始，Spire.Doc for Java 支持比较两个Word文档的内容，并以修订模式生成结果文档。

以下为两个示例文档，标注内容是它们的内容差异：

![Java 比较两个 Word 文档的内容](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/Compare-two-Word-documents-in-Java-1-16504706498001.png)

代码示例：

```java
import com.spire.doc.Document;

public class Comparison {
    public static void main(String[] args) {
        //创建Document实例
        Document doc1 = new Document();
        //加载第一个Word示例文档
        doc1.loadFromFile("C:\\Users\\Test1\\Desktop\\Sample1.docx");
        //创建Document实例
        Document doc2 = new Document();
        //加载第二个Word示例文档
        doc2.loadFromFile("C:\\Users\\Test1\\Desktop\\Sample2.docx");
        //比较两个示例文档的内容差异
        doc1.compare(doc2, "e-iceblue");
        //保存结果文档
        doc1.saveToFile("output/Result.docx");
    }
}
```

比较结果：

![Java 比较两个 Word 文档的内容](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/Compare-two-Word-documents-in-Java-2.png)
