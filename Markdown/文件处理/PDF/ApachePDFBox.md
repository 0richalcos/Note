# 1、Apache PDFbox

Apache PDFbox 是一个开源的、基于 Java 的、支持 PDF 文档生成的工具库，它可以用于创建新的 PDF 文档，修改现有的 PDF 文档，还可以从 PDF 文档中提取所需的内容。Apache PDFBox 还包含了数个命令行工具。

导入依赖：

```xml
<!--pdfBox-->
<dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>pdfbox</artifactId>
    <version>2.0.21</version>
</dependency>
```

编写代码：

```java
public class PDFTest {
    public static String getTextFromPDF(String pdfFilePath)
    {
        String result = null;
        PDDocument document = null;
        File file = new File(pdfFilePath);
        try {
            PDFParser parser = new PDFParser(new RandomAccessFile(file,"rw"));
            //如果是web解析可以使用下面这种方法
            //PDFParser parser = new PDFParser(new RandomAccessBuffer(input));
            parser.parse();
            document = parser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            result = stripper.getText(document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    public  static void main(String[] args)
    {
        String str=PDFTest.getTextFromPDF("D:\\pdf.pdf");
        System.out.println(str);
 
    }
}
```



## 1.1、PDF 转 HTML

pdfbox 自带的转换 html 的方法效果不是太好，pdfdom 是基于 pdfbox 的，在此之上加强了转换 html 的能力。

导入依赖：

```xml
<dependency>
    <groupId>net.sf.cssbox</groupId>
    <artifactId>pdf2dom</artifactId>
    <version>1.9</version>
</dependency>
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.13.1</version>
</dependency>
```

```java
public static String pdfToHtml(File file) {
    try (PDDocument pdDocument = PDDocument.load(file);
         //PrintWriter writer = new PrintWriter("E://upload//123.html", StandardCharsets.UTF_8);
         Writer writer = new CharArrayWriter()
        ) {
        new PDFDomTree().writeText(pdDocument, writer);
        logger.info("pdf转html成功，文件名：" + file.getName());
        return writer.toString();
    } catch (IOException | ParserConfigurationException e) {
        logger.error("pdf转html失败，文件名：" + file.getName());
        return null;
    }
}
```

