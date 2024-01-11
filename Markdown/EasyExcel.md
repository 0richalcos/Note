---
typora-copy-images-to: upload
---

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

需要一个实体类，一个实体类对象代表一行数据

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

测试：

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

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20201016151516010.png" alt="image-20201016151516010" style="width:40%" />



# 3、读 Excel

## 3.1、最简单的读

实体类：

```java
@Data
public class DemoData {
    private String string;
    private Date date;
    private Double doubleDate;
}
```

DAO 持久层：

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

监听器：

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

测试：

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

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20231214232714486.png" alt="image-20231214232714486" style="zoom:80%;" />



**最终效果**

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20231214232949483.png" alt="image-20231214232949483" style="zoom:80%;" />



**代码**

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

