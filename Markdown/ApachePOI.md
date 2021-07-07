# 1、Apache POI

Apache POI 是 Apache 软件基金会的开放源码函数库，POI 提供 API 给 Java 程序对 Microsoft Office 格式档案读和写的功能

基本功能：

- HSSF - 提供读写 Microsoft Excel 格式档案的功能（03）
- XSSF - 提供读写 Microsoft Excel OOXML 格式档案的功能（07）
- HWPF - 提供读写 Microsoft Word 格式档案的功能
- HSLF - 提供读写 Microsoft PowerPoint 格式档案的功能
- HDGF - 提供读写 Microsoft Visio 格式档案



**导入依赖**

```xml
<!--导入依赖-->
<dependencies>
    <!--xls(03)-->
    <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>4.1.2</version>
    </dependency>

    <!--xlsx(7)-->
    <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>4.1.2</version>
    </dependency>

    <!--日期格式化工具-->
    <!-- https://mvnrepository.com/artifact/joda-time/joda-time -->
    <dependency>
        <groupId>joda-time</groupId>
        <artifactId>joda-time</artifactId>
        <version>2.10.6</version>
    </dependency>

    <!--test-->
    <!-- https://mvnrepository.com/artifact/junit/junit -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```



# 2、POI Excel 写

## 2.1、基础操作

```java
public class ExcelWriteTest {
    String PATH="C:\\Users\\Orichalcos\\Desktop\\";

    @Test
    public void testWrite03(){
        //1.创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        //2.创建一个工作表
        Sheet sheet = workbook.createSheet("Orichalcos");
        //3.创建一个行
        Row row1 = sheet.createRow(0);
        //4.创建一个单元格
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今日新增观众");
        //(1,2)
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue(666);

        //第二行
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //(2,2)
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        //生成一张表（IO流）
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH + "Orichalcos03.xls");

            workbook.write(fileOutputStream);

            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Orichalcos03.xls 生成完毕");
    }

    @Test
    public void testWrite07(){
        //1.创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        //2.创建一个工作表
        Sheet sheet = workbook.createSheet("Orichalcos");
        //3.创建一个行
        Row row1 = sheet.createRow(0);
        //4.创建一个单元格
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今日新增观众");
        //(1,2)
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue(666);

        //第二行
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //(2,2)
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        //生成一张表（IO流）
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH + "Orichalcos07.xlsx");

            workbook.write(fileOutputStream);

            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Orichalcos07.xls 生成完毕");
    }
}
```



## 2.2、数据批量导入

- 大文件写 HSSF

	> 缺点：最多只能处理 65536 行，否则会抛出异常
	>
	> 优点：过程中写入缓存，不操作磁盘，最后一次写入磁盘，速度快

- 大文件写 XSSF

	> 缺点：写数据时非常慢，非常耗内存，也会发生内存溢出，如 100 万条
	>
	> 优点：可以写较大的数据量，如 20 万条

- 大文件写 SXSSF

	> 优点：可以写非常大的数据量，如 100 万条甚至更多条，写数据速度快，占用更少的内存
	>
	> 注意：过程中会产生临时文件，需要清理临时文件，默认 100 条记录被保存在内存中，如果超过这数量，则前面的数据被写入临时文件，如果想自定义内存中数据的数量，可以使用`new SXSSFWorkbook(数量)`，SXSSF 仍然可能会消耗大量内存



**大文件写 HSSF**

```java
@Test
public void testWrite03BigData() {
    //时间
    long begin = System.currentTimeMillis();

    //创建一个簿
    Workbook workbook = new HSSFWorkbook();
    //创建表
    Sheet sheet = workbook.createSheet();
    //写入数据
    for (int rowNum = 0; rowNum < 65536; rowNum++) {
        Row row = sheet.createRow(rowNum);
        for (int cellNum = 0; cellNum < 10; cellNum++) {
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(cellNum);
        }
    }
    System.out.println("over");
    try {
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "OrichalcosBig03.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    long end = System.currentTimeMillis();
    System.out.println((double) (end - begin) / 1000);
}
```



**大文件写 XSSF**

```java
@Test
public void testWrite07BigData() {
    //时间
    long begin = System.currentTimeMillis();

    //创建一个簿
    Workbook workbook = new XSSFWorkbook();
    //创建表
    Sheet sheet = workbook.createSheet();
    //写入数据
    for (int rowNum = 0; rowNum < 100000; rowNum++) {
        Row row = sheet.createRow(rowNum);
        for (int cellNum = 0; cellNum < 10; cellNum++) {
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(cellNum);
        }
    }
    System.out.println("over");
    try {
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "OrichalcosBig07.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    long end = System.currentTimeMillis();
    System.out.println((double) (end - begin) / 1000);
}
```



**大文件写 SXSSF**

```java
@Test
public void testWrite07BigDataS() {
    //时间
    long begin = System.currentTimeMillis();

    //创建一个簿
    Workbook workbook = new SXSSFWorkbook();
    //创建表
    Sheet sheet = workbook.createSheet();
    //写入数据
    for (int rowNum = 0; rowNum < 100000; rowNum++) {
        Row row = sheet.createRow(rowNum);
        for (int cellNum = 0; cellNum < 10; cellNum++) {
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(cellNum);
        }
    }
    System.out.println("over");
    try {
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "OrichalcosBig07S.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    //清除临时文件
    ((SXSSFWorkbook)workbook).dispose();
    long end = System.currentTimeMillis();
    System.out.println((double) (end - begin) / 1000);
}
```



## 2.3、合并单元格

```java
 public class Excel2 {
	public static void main(String[] args) throws IOException {
        FileOutputStream fos=new FileOutputStream("D:\\hebing.xls");
        HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		/*
		 * 设定合并单元格区域范围
		 * 	firstRow  0-based
		 * 	lastRow   0-based
		 * 	firstCol  0-based
		 * 	lastCol   0-based
		 */
		CellRangeAddress cra=new CellRangeAddress(0, 2, 3, 6);		
		//在sheet里增加合并单元格
		sheet.addMergedRegion(cra);
		
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell_1 = row.createCell(3);
		cell_1.setCellValue("this is merge unit ");
		//cell 位置3-6被合并成一个单元格，不管你怎样创建第4个cell还是第5个cell…然后在写数据。都是无法写入的。
		HSSFCell cell_2 = row.createCell(7);
		cell_2.setCellValue("what's up ! ");
		wb.write(fos);
		fos.close();
	}
}
```

<img src="POI & easyExcel.assets/20180720094625254" alt="img" style="zoom:80%;float:left" />

**合并单元格的关键代码：**

```java
//创建合并单元格区域
CellRangeAddress cra=new CellRangeAddress(0, 2, 3, 6);        
//在sheet里增加合并单元格
sheet.addMergedRegion(cra);
```

创建合并单元格的方法 CellRangeAdress(int firstRow, int lastRow, int fitstCol, int lastCol); 中的参数四个参数分别表示，合并区域的第一行，最后一行，第一列，最后一列。并且合并区域的单元格数目必须大于2，否则出错。



# 3、POI Excel 读

## 3.1、基础操作

```java
public class ExcelReadTest {
    String PATH = "C:\\Users\\Orichalcos\\Desktop\\";

    @Test
    public void testRead03() throws IOException {
        //获取文件流
        FileInputStream fileInputStream = new FileInputStream(PATH + "Orichalcos.xls");

        //1.创建一个工作簿，使用 Excel 能能操作的这边他都可以操作
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        //2.得到表
        Sheet sheet = workbook.getSheetAt(0);
        //3.得到行
        Row row = sheet.getRow(1);
        //4.得到列
        Cell cell = row.getCell(3);

        //读取值的时候,一定要注意类型
        //getStringCellValue 字符串类型
        //System.out.println(cell.getStringCellValue());
        System.out.println(cell.getDateCellValue());
        fileInputStream.close();
    }

    @Test
    public void testRead07() throws IOException {
        //获取文件流
        FileInputStream fileInputStream = new FileInputStream(PATH + "Orichalcos.xlsx");

        //1.创建一个工作簿，使用 Excel 能能操作的这边他都可以操作
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        //2.得到表
        Sheet sheet = workbook.getSheetAt(0);
        //3.得到行
        Row row = sheet.getRow(1);
        //4.得到列
        Cell cell = row.getCell(3);

        //读取值的时候,一定要注意类型
        //getStringCellValue 字符串类型
        //System.out.println(cell.getStringCellValue());
        System.out.println(cell.getDateCellValue());
        fileInputStream.close();
    }
}
```



## 3.2、读取不同类型数据

```java
@Test
public void testCellType() throws IOException {
    //获取文件流
    FileInputStream fileInputStream = new FileInputStream(PATH + "Orichalcos.xls");

    //创建一个工作簿
    Workbook workbook = new HSSFWorkbook(fileInputStream);
    Sheet sheet = workbook.getSheetAt(0);
    
    //获取标题内容
    Row rowTitle = sheet.getRow(0);
    if (rowTitle != null) {
        int cellCount = rowTitle.getPhysicalNumberOfCells();
        for (int cellNum = 0; cellNum < cellCount; cellNum++) {
            Cell cell = rowTitle.getCell(cellNum);
            if (cell != null) {
                String cellValue = cell.getStringCellValue();
                System.out.print(cellValue + "|");
            }
        }
        System.out.println();
    }

    //获取表中的日期
    int rowCount = sheet.getPhysicalNumberOfRows();
    for (int rowNum = 1; rowNum < rowCount; rowNum++) {
        Row rowData = sheet.getRow(rowNum);
        if (rowData != null) {
            //读取列
            int cellCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                System.out.print("[" + (rowNum + 1) + "-" + (cellNum + 1) + "]");

                Cell cell = rowData.getCell(cellNum);
                //匹配列的数据类型
                CellType cellType = cell.getCellType();
                String cellValue = "";

                switch (cellType) {
                    case STRING://字符串
                        System.out.print("【String】");
                        cellValue = cell.getStringCellValue();
                        break;
                    case BOOLEAN://布尔
                        System.out.print("【Boolean】");
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case BLANK://空
                        System.out.print("【Blank】");
                        break;
                    case NUMERIC://数字(日期、普通数字)
                        System.out.print("【Numeric】");
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期
                            System.out.print("【日期】");
                            Date date = cell.getDateCellValue();
                            cellValue = new DateTime(date).toString("yyyy-MM-dd");
                        } else {
                            //不是日期格式，防止数字过长
                            System.out.print("【转换为字符串输出】");
                            cell.setCellType(CellType.STRING);
                            cellValue = cell.toString();
                        }
                        break;
                    case ERROR:
                        System.out.print("【数据类型错误】");
                        break;
                }
                System.out.println(cellValue);
            }
        }
    }
    fileInputStream.close();
}
```



## 3.3、计算公式

```java
@Test
public void testFormula() throws IOException {
    FileInputStream inputStream = new FileInputStream(PATH + "公式.xls");
    Workbook workbook = new HSSFWorkbook(inputStream);
    Sheet sheet = workbook.getSheetAt(0);

    Row row = sheet.getRow(3);
    Cell cell =row.getCell(0);
    System.out.println(cell.getNumericCellValue());

    //拿到计算公式
    FormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);

    //输出单元格的内容
    CellType cellType = cell.getCellType();
    switch (cellType){
        case FORMULA://公式
            String formula = cell.getCellFormula();
            System.out.println(formula);

            //计算
            CellValue evaluate = formulaEvaluator.evaluate(cell);
            String string = evaluate.formatAsString();
            System.out.println(string);
            break;
    }
}
```



# 4、POI Word 读

使用poi读取文档中的表格，当有多个表格时可以指定需要读取的表格，同时支持读取 docx 和 doc 格式。需要添加 poi 的 jar 包

```xml
<!--apache-POI-->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.14</version>
</dependency>
<!--POI Word(03)转为HTML工具-->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-scratchpad</artifactId>
    <version>3.14</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.14</version>
</dependency>
<!--POI Word(07)转为HTML工具-->
<dependency>
    <groupId>fr.opensagres.xdocreport</groupId>
    <artifactId>fr.opensagres.poi.xwpf.converter.xhtml</artifactId>
    <version>2.0.1</version>
</dependency>
<!--如果遇见jar包冲突可以替换为这个jar包-->
<!--<dependency>-->
<!--    <groupId>fr.opensagres.xdocreport</groupId>-->
<!--    <artifactId>org.apache.poi.xwpf.converter.xhtml</artifactId>-->
<!--    <version>1.0.6</version>-->
<!--</dependency>-->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml-schemas</artifactId>
    <version>3.14</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>ooxml-schemas</artifactId>
    <version>1.3</version>
</dependency>
```

<img src="../Images/ApachePOI/20180914173007117" alt="img" style="zoom:80%; float:left" />

```java
 /**
 * 
 * 读取word文档中表格数据，支持doc、docx
 * @author Fise19
 * 
 */
public class ExportDoc {
	public static void main(String[] args) {
		ExportDoc test = new ExportDoc();
		String filePath = "D:\\new\\测试.docx";
		//String filePath = "D:\\new\\测试.doc";
		test.testWord(filePath);
	}
	/**
	 * 读取文档中表格
	 * @param filePath
	 */
	public void testWord(String filePath){
		try{
			FileInputStream in = new FileInputStream(filePath);//载入文档
			// 处理docx格式 即office2007以后版本
			if(filePath.toLowerCase().endsWith("docx")){
				//word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后   
				XWPFDocument xwpf = new XWPFDocument(in);//得到word文档的信息
				Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格
				// 设置需要读取的表格  set是设置需要读取的第几个表格，total是文件中表格的总数
				int set = 2, total = 4;
				int num = set;
				// 过滤前面不需要的表格
				for (int i = 0; i < set-1; i++) {
					it.hasNext();
					it.next();
				}
				while(it.hasNext()){
					XWPFTable table = it.next();  
					System.out.println("这是第" + num + "个表的数据");
					List<XWPFTableRow> rows = table.getRows(); 
					//读取每一行数据
					for (int i = 0; i < rows.size(); i++) {
						XWPFTableRow  row = rows.get(i);
						//读取每一列数据
						List<XWPFTableCell> cells = row.getTableCells(); 
						for (int j = 0; j < cells.size(); j++) {
							XWPFTableCell cell = cells.get(j);
							//输出当前的单元格的数据
							System.out.print(cell.getText() + "\t");
						}
						System.out.println();
					}
					// 过滤多余的表格
					while (num < total) {
						it.hasNext();
						it.next();
						num += 1;
					}
				}
			}else{
				// 处理doc格式 即office2003版本
				POIFSFileSystem pfs = new POIFSFileSystem(in);   
				HWPFDocument hwpf = new HWPFDocument(pfs);   
				Range range = hwpf.getRange();//得到文档的读取范围
				TableIterator it = new TableIterator(range);
				// 迭代文档中的表格
				// 如果有多个表格只读取需要的一个 set是设置需要读取的第几个表格，total是文件中表格的总数
				int set = 1, total = 4;
				int num = set;
				for (int i = 0; i < set-1; i++) {
					it.hasNext();
					it.next();
				}
				while (it.hasNext()) {   
					Table tb = (Table) it.next();   
					System.out.println("这是第" + num + "个表的数据");
					//迭代行，默认从0开始,可以依据需要设置i的值,改变起始行数，也可设置读取到那行，只需修改循环的判断条件即可
					for (int i = 0; i < tb.numRows(); i++) {   
						TableRow tr = tb.getRow(i);   
						//迭代列，默认从0开始
						for (int j = 0; j < tr.numCells(); j++) {   
							TableCell td = tr.getCell(j);//取得单元格
							//取得单元格的内容
							for(int k = 0; k < td.numParagraphs(); k++){   
								Paragraph para = td.getParagraph(k); 
								String s = para.text();
								//去除后面的特殊符号
								if(null != s && !"".equals(s)){
									s = s.substring(0, s.length()-1);
								}
								System.out.print(s + "\t");
							}
						}
						System.out.println();
					} 
					// 过滤多余的表格
					while (num < total) {
						it.hasNext();
						it.next();
						num += 1;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
```

```
这是第2个表的数据
123	1	2	3	
表2-1	1	2	3	
表2-2	1	2	3	
```



## 4.1、Word 转 HTML

**07 版 Word 转 Html**

```java
public static String docxToHtml(File file) {
    //获取word文件名（去掉扩展名）
    //String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
    //存放图片的路径（如果不存在创建）
    String imagePath = "E:\\upload";
    File imageFolder = new File(imagePath);
    if (!imageFolder.exists()) {
        imageFolder.mkdirs();
    }
    //生成的html文件的名字
    //String htmlFileName = imagePath + File.separator + fileName + ".html";

    //加载word文档生成XWPFDocument对象
    try (XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(file));
         //创建文件数据流输出转换的html文件
         //OutputStream outputStream = new FileOutputStream(htmlFileName);
         //也可以使用字符数组流获取解析的内容
         OutputStream outputStream = new ByteArrayOutputStream()
    ) {
        //解析XHTML配置（这里设置URIResolver来设置图片存放的目录）
        XHTMLOptions xhtmlOptions = XHTMLOptions.create();
        //存放图片的文件夹
        xhtmlOptions.setExtractor(new FileImageExtractor(imageFolder));
        //html中图片的路径
        xhtmlOptions.URIResolver(new FileURIResolver(imageFolder));
        //不忽略未使用的样式
        xhtmlOptions.setIgnoreStylesIfUnused(false);
        
        xhtmlOptions.setFragment(true);
        //将XWPFDocument转换为XHTML
        XHTMLConverter.getInstance().convert(xwpfDocument, outputStream, xhtmlOptions);
        logger.info("docx转html成功,文件名：" + file.getName());
        return outputStream.toString();
        //return htmlFileName;
    } catch (Exception e) {
        logger.error("docx转html失败，文件名：" + file.getName());
        return null;
    }
}
```

在 Web 中转化后的 html  页面无法访问图片的问题：因为 ` xhtmlOptions.URIResolver(new FileURIResolver(imageFolder));`生成的 url 是绝对路径，可以将其改为`xhtmlOptions.URIResolver(new BasicURIResolver(""));`，同时需要对 `E:\\upload`路径进行映射：

```yaml
web:
  upload-path: E:/upload
spring:
  mvc:
    static-path-pattern: /**
  resources:
    # 对上传的地址E:/进行映射
    static-locations: classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/,file:${web.upload-path}
```



**03版word转html**

```java
public static String docToHtml(File file) {
    //获取word的文件名（去掉扩展名）
    String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
    //图片存储路径（如果不存在则创建）
    String imagePath = "E:\\upload";
    //生成的html文件名
    //String htmlFileName = imagePath + File.separator + fileName + ".html";
    try (//使用文件输出流获取解析内容
        //OutputStream outputStream = new FileOutputStream(htmlFileName);
        //也可以使用字符数组流获取解析的内容
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
    ) {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream(file));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            File folder = new File(imagePath + File.separator + fileName);
            //图片目录不存在则创建
            if (!folder.exists()) {
                folder.mkdirs();
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(folder + File.separator + name)) {
                fileOutputStream.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return folder + File.separator + name;
        });
        //解析word文档
        wordToHtmlConverter.processDocument(hwpfDocument);
        Document documentHtml = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(documentHtml);

        StreamResult streamResult = new StreamResult(outputStream);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        transformer.transform(domSource, streamResult);
        logger.info("doc转html成功，文件名：" + file.getName());
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
        //return htmlFileName;
    } catch (IOException | ParserConfigurationException | TransformerException e) {
        logger.error("doc转html失败，文件名：" + file.getName());
        return null;
    }
}
```

如果显示缺少 class，看情况导入以下依赖：

```xml
<!--缺少的jar包-->
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-impl</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-core</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>javax.activation</groupId>
    <artifactId>activation</artifactId>
    <version>1.1.1</version>
</dependency>
```

