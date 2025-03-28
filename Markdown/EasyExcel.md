# 1、EasyExcel

Java 解析、生成 Excel 比较有名的框架有 Apache POI、jxl。但他们都存在一个严重的问题就是非常的耗内存，POI 有一套 SAX 模式的 API 可以一定程度的解决一些内存溢出的问题，但 POI 还是有一些缺陷，比如 07 版 Excel 解压缩以及解压后存储都是在内存中完成的，内存消耗依然很大。EasyExcel 重写了 POI 对 07 版 Excel 的解析，能够原本一个 3M 的 Excel 用 POI SAX 依然需要100M 左右内存降低到几 M，并且再大的 Excel 不会出现内存溢出，03 版依赖 POI 的 SAX 模式。在上层做了模型转换的封装，让使用者更加简单方便



**导入依赖**

```xml
<!--easyExcel-->
<!-- https://mvnrepository.com/artifact/com.alibaba/easyexcel -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>easyexcel</artifactId>
    <version>2.2.6</version>
</dependency>
```

点进 `com.alibaba` 可以看到其实 EasyExcel 也是使用了 Apache POI，所以需要注释掉自己导入的 POI 依赖，以防止依赖冲突。

这里只介绍简单的读写，详情参考 alibaba 开源项目 [EasyExcel](https://github.com/alibaba/easyexcel)。



# 2、写 Excel

## 2.1、最简单的写

1. 需要一个实体类，一个实体类对象代表一行数据：

   ```java
   @Data
   public class DemoData {
       @ExcelProperty("字符串标题")
       private String string;
       @ExcelProperty("日期标题")
       private Date date;
       @ExcelProperty("数字标题")
       private Double doubleDate;
       //忽略这个字段
       @ExcelIgnore
       private String ignore;
   }
   ```

2. 测试：

   ```java
   public class easyExcelTest {
       String PATH = "C:\\Users\\Orichalcos\\Desktop\\";
   
       private List<DataDemo> data(){
           List<DataDemo> list = new ArrayList<DataDemo>();
           for (int i = 0;i<10;i++){
               DataDemo data = new DataDemo();
               data.setString("字符串"+i);
               data.setDate(new Date());
               data.setDoubleDate(0.56);
               list.add(data);
           }
           return list;
       }
   
       //根据List，写入 excel
       @Test
       public void simpleWrite(){
           String fileName = PATH+"easyExcel.xls";
   
           //写法一
           //这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
           EasyExcel.write(fileName,DemoData.class).sheet("模板").doWrite(data());
   
           //写法二
           //这里 需要指定写用哪个class去写
           ExcelWriter excelWriter = EasyExcel.write(fileName, DataDemo.class).build();
           WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
           excelWriter.write(data(),writeSheet);
           //千万别忘记finish，会帮助故关闭流
           excelWriter.finish();
       }
   }
   ```

 

## 2.2、web 中的写

```java
/**
  * 文件下载（失败了会返回一个有部分数据的Excel）
  * <p>
  * 1. 创建excel对应的实体对象 参照{@link DownloadData}
  * <p>
  * 2. 设置返回的 参数
  * <p>
  * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
  */
@GetMapping("download")
public void download(HttpServletResponse response) throws IOException {
    // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("utf-8");
    // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
    String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
    response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    EasyExcel.write(response.getOutputStream(), DownloadData.class).sheet("模板").doWrite(data());
}
```

 

## 2.3、复杂头写出

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @ExcelIgnore
    private long productID;

    @ExcelProperty({"商品信息", "商品名称"})
    private String productName;

    @ExcelProperty({"商品信息", "商品重量"})
    private double productWeight;

    @ExcelProperty({"商品信息", "商品价格"})
    private double productPrice;

    @ExcelProperty({"商品信息", "商品分类"})
    private String productClassify;

    @ExcelProperty({"其他信息", "商品图片"})
    private String productImage;

    @ExcelProperty({"其他信息", "备注"})
    private String productRemark;
}
```

<img src="!assets/EasyExcel/image-20201016151516010.png" alt="image-20201016151516010" style="width:40%" />



## 2.4、写到单个或者多个 Sheet

```java
/**
  * 重复多次写入
  * <p>
  * 1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
  * <p>
  * 2. 使用{@link ExcelProperty}注解指定复杂的头
  * <p>
  * 3. 直接调用二次写入即可
  */
@Test
public void repeatedWrite() {
    // 方法1: 如果写到同一个sheet
    String fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 需要指定写用哪个class去写
    try (ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build()) {
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
        for (int i = 0; i < 5; i++) {
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DemoData> data = data();
            excelWriter.write(data, writeSheet);
        }
    }

    // 方法2: 如果写到不同的sheet 同一个对象
    fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 指定文件
    try (ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build()) {
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
            WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DemoData> data = data();
            excelWriter.write(data, writeSheet);
        }
    }

    // 方法3 如果写到不同的sheet 不同的对象
    fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 指定文件
    try (ExcelWriter excelWriter = EasyExcel.write(fileName).build()) {
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。
            // 这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class，实际上可以一直变
            WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(DemoData.class).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DemoData> data = data();
            excelWriter.write(data, writeSheet);
        }
    }

}
```



## 2.5、自定义格式转换

格式转换，一般用的最多的就是时间格式，时间类型一般有：`Date` 和 `LocalDateTime`，如果使用的是 `Date` 可以用注解格式化，如果使用 `LocalDateTime` 需要自己写转换器：

1. 转换器：

   ```java
   /**
    * 自定义 LocalDateTime 日期转换器
    */
   public class LocalDateTimeConverter implements Converter<LocalDateTime> {
   
       @Override
       public Class<LocalDateTime> supportJavaTypeKey() {
           return LocalDateTime.class;
       }
   
       @Override
       public CellDataTypeEnum supportExcelTypeKey() {
           return CellDataTypeEnum.STRING;
       }
   
       @Override
       public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                              GlobalConfiguration globalConfiguration) {
           return LocalDateTime.parse(cellData.getStringValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
       }
   
       @Override
       public CellData<String> convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty,
                                                  GlobalConfiguration globalConfiguration) {
           return new CellData<>(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
       }
   
   }
   ```

   > [!NOTE]
   >
   > 自定义转换器，只需实现 `Converter<T>` 接口并重写 `convertToJavaData() ` 和 `convertToExcelData()` 即可。
   >
   > `convertToJavaData()` 这个方法是从 Excel 转到 Java 类型，反之 `convertToExcelData()` 是 Java 到 Excel。

2. 实体类：

   ```java
   /**
    * ColumnWidth 定义列宽
    */
   @ColumnWidth(25)
   @Data
   public class UserDto {
   
       @ExcelProperty("用户名称")
       private String username;
   
       @ExcelProperty("年龄")
       private Integer age;
   
       @ExcelProperty("部门")
       @DropDownFields(source = {"财务部","人事部","研发部","商务部"})
       private String department;
   
       @ExcelProperty("职业")
       private String occupation;
   
       /**
        * 自定义日期转换器LocalDateTimeConverter
        */
       @ColumnWidth(50)
       @ExcelProperty(value = "注册时间",converter = LocalDateTimeConverter.class)
       private LocalDateTime createTime;
   
       /**
        * 使用注解@DateTimeFormat
        */
       @ExcelProperty(value = "发财时间")
       @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
       @ColumnWidth(50)
       private Date startTime;
       
        /**
        * 我想写到excel 用百分比表示
        */
       @NumberFormat("#.##%")
       @ExcelProperty(value = "数字标题")
       private Double doubleData;
   }
   ```




# 3、读 Excel

## 3.1、最简单的读

1. 实体类：

   ```java
   @Data
   public class DemoData {
       private String string;
       private Date date;
       private Double doubleDate;
   }
   ```

2. DAO 持久层：

   ```java
   /**
    * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
    *
    * @author Jiaju Zhuang
    **/
   public class DemoDAO {
       public void save(List<DemoData> list) {
           // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
       }
   }
   ```

3. 监听器：

   ```java
   /**
    * 模板的读取类
    */
   // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
   public class DemoDataListener extends AnalysisEventListener<DemoData> {
       private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
       /**
        * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
        */
       private static final int BATCH_COUNT = 5;
       List<DemoData> list = new ArrayList<DemoData>();
       /**
        * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
        */
       private DemoDAO demoDAO;
   
       public DemoDataListener() {
           // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
           demoDAO = new DemoDAO();
       }
   
       /**
        * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
        *
        * @param demoDAO
        */
       public DemoDataListener(DemoDAO demoDAO) {
           this.demoDAO = demoDAO;
       }
   
       /**
        * 这个每一条数据解析都会来调用
        *
        * @param data
        *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
        * @param context
        */
       @Override
       public void invoke(DemoData data, AnalysisContext context) {
           LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
           list.add(data);
           // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
           if (list.size() >= BATCH_COUNT) {
               saveData();
               // 存储完成清理 list
               list.clear();
           }
       }
   
       /**
        * 所有数据解析完成了 都会来调用
        *
        * @param context
        */
       @Override
       public void doAfterAllAnalysed(AnalysisContext context) {
           // 这里也要保存数据，确保最后遗留的数据也存储到数据库
           saveData();
           LOGGER.info("所有数据解析完成！");
       }
   
       /**
        * 加上存储数据库
        */
       private void saveData() {
           LOGGER.info("{}条数据，开始存储数据库！", list.size());
           demoDAO.save(list);
           LOGGER.info("存储数据库成功！");
       }
   }
   ```

4. 测试：

   ```java
   public class ReadTest {
       private static final Logger LOGGER = LoggerFactory.getLogger(ReadTest.class);
   
       /**
        * 最简单的读
        * <p>
        * 1. 创建excel对应的实体对象 参照{@link DemoData}
        * <p>
        * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
        * <p>
        * 3. 直接读即可
        */
       @Test
       public void simpleRead() {
           // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
           // 写法1：
           String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
           // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
           EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
   
           // 写法2：
           fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
           ExcelReader excelReader = null;
           try {
               excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
               ReadSheet readSheet = EasyExcel.readSheet(0).build();
               excelReader.read(readSheet);
           } finally {
               if (excelReader != null) {
                   // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                   excelReader.finish();
               }
           }
       }
   }
   ```




**记一次 EasyExce 读取 xlsx 文件实体 Bean 数据全部为 null 的问题**

可能和 Lombok 的 `@Accessors` 注解有关，去掉就好了：

```java
@Data
@EqualsAndHashCode()
//@Accessors(chain = true)
```



## 3.2、web 中的读

```java
/**
  * 文件上传
  * <p>
  * 1. 创建excel对应的实体对象 参照{@link UploadData}
  * <p>
  * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
  * <p>
  * 3. 直接读即可
  */
@PostMapping("upload")
@ResponseBody
public String upload(MultipartFile file) throws IOException {
    EasyExcel.read(file.getInputStream(), UploadData.class, new UploadDataListener(uploadDAO)).sheet().doRead();
    return "success";
}
```

 

## 3.3、模板校验

只需要重写 `AnalysisEventListener` 抽象类的 `invokeHeadMap()` 方法即可：

```java
/**
  * 将表头作为map返回，重写当前方法以接收标头数据。
  *
  * @param headMap
  * @param context
  */
public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {}
```

下面是一个完整的示例：

1. 创建导入时数据对应的实体类，并使用 `@ExcelProperty` 注解配置模板表头：

   ```java
   @Data
   public class EasyExcelData {
       @ExcelProperty(value = "学号", index = 0)
       private String no;
       @ExcelProperty(value = "姓名", index = 1)
       private String name;
       @ExcelProperty(value = "性别", index = 2)
       private String gender;
   }
   ```

2. 创建监听类并继承 `AnalysisEventListener` 类，重写 `invokeHeadMap()` 方法，在该方法中判断是否符合模板：

   ```java
   public class EasyExcelDemoListener extends AnalysisEventListener<EasyExcelData> {
   
       /**
        * 在这里进行模板的判断
        * @param headMap 存放着导入表格的表头，键是索引，值是名称
        * @param context
        */
       @Override
       public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
           /*
           count 记录模板表头有几个，用以判断用户导入的表格是否和模板完全一致
           如果用户导入表格较模板的表头多，但其余符合模板，这样不影响则不需要
            */
           int count = 0;
           // 获取数据实体的字段列表
           Field[] fields = EasyExcelData.class.getDeclaredFields();
           // 遍历字段进行判断
           for (Field field : fields) {
               // 获取当前字段上的ExcelProperty注解信息
               ExcelProperty fieldAnnotation = field.getAnnotation(ExcelProperty.class);
               // 判断当前字段上是否存在ExcelProperty注解
               if (fieldAnnotation != null) {
                   ++count;
                   // 存在ExcelProperty注解则根据注解的index索引到表头中获取对应的表头名
                   String headName = headMap.get(fieldAnnotation.index());
                   // 判断表头是否为空或是否和当前字段设置的表头名不相同
                   if (StringUtils.isEmpty(headName) || !headName.equals(fieldAnnotation.value()[0])) {
                       // 如果为空或不相同，则抛出异常不再往下执行
                       throw new RuntimeException("模板错误，请检查导入模板");
                   }
               }
           }
   
           // 判断用户导入表格的标题头是否完全符合模板
           if (count != headMap.size()) {
               throw new RuntimeException("模板错误，请检查导入模板");
           }
       }
   }
   ```



# 4、填充 Excel

## 4.1、最简单的填充

**模板**

<img src="!assets/EasyExcel/image-20231214232714486.png" alt="image-20231214232714486" style="zoom: 50%;" />



**最终效果**

<img src="!assets/EasyExcel/image-20231214232949483.png" alt="image-20231214232949483" style="zoom: 50%;" />



**代码**

1. 实体类：

   ```java
   @Getter
   @Setter
   @EqualsAndHashCode
   public class FillData {
       private String name;
       private double number;
       private Date date;
   }
   ```

2. 测试：

   ```java
    /**
     * 最简单的填充
     *
     * @since 2.1.1
     */
   @Test
   public void simpleFill() {
       // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
       String templateFileName =
           TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "simple.xlsx";
   
       // 方案1 根据对象填充
       String fileName = TestFileUtil.getPath() + "simpleFill" + System.currentTimeMillis() + ".xlsx";
       // 这里 会填充到第一个sheet， 然后文件流会自动关闭
       FillData fillData = new FillData();
       fillData.setName("张三");
       fillData.setNumber(5.2);
       EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillData);
   
       // 方案2 根据Map填充
       fileName = TestFileUtil.getPath() + "simpleFill" + System.currentTimeMillis() + ".xlsx";
       // 这里 会填充到第一个sheet， 然后文件流会自动关闭
       Map<String, Object> map = MapUtils.newHashMap();
       map.put("name", "张三");
       map.put("number", 5.2);
       EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
   }
   ```



## 4.2、复杂的填充

**模板**

![2025-01-01 052633](!assets/EasyExcel/2025-01-01 052633.png)



**最终效果**

![2025-01-01 052654](!assets/EasyExcel/2025-01-01 052654.png)



**代码**

1. 实体类：

   ```java
   @Getter
   @Setter
   @EqualsAndHashCode
   public class FillData {
       private String name;
       private double number;
       private Date date;
   }
   ```

2. 测试：

   ```java
   /**
     * 数据量大的复杂填充
     * <p>
     * 这里的解决方案是 确保模板list为最后一行，然后再拼接table.还有03版没救，只能刚正面加内存。
     *
     * @since 2.1.1
     */
   @Test
   public void complexFillWithTable() {
       // 模板注意用 {} 来表示你要用的变量 如果本来就有 "{" "}" 特殊字符 用 "\{" "\}" 代替
       // {} 代表普通变量 
       // {.} 代表是 list 的变量
       String templateFileName = TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "complexFillWithTable.xlsx";
   
       String fileName = TestFileUtil.getPath() + "complexFillWithTable" + System.currentTimeMillis() + ".xlsx";
   
       try (ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build()) {
           WriteSheet writeSheet = EasyExcel.writerSheet().build();
           // 直接写入数据
           excelWriter.fill(data(), writeSheet);
   
           // 写入list之前的数据
           Map<String, Object> map = new HashMap<String, Object>();
           map.put("date", "2019年10月9日13:28:28");
           excelWriter.fill(map, writeSheet);
       }
   }
   ```
   
   
