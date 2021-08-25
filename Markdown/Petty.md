# 【1】JS 实现 html 页面点击下载文件

**使用 <a> 标签来完成**

```html
<a href="/user/test/xxxx.txt" download="文件名.txt">点击下载</a>
```

这样当用户打开浏览器点击链接的时候就会直接下载文件。但是有个情况，比如 txt,png,jpg 等这些浏览器支持直接打开的文件是不会执行下载任务的，而是会直接打开文件，**这个时候就需要给 a 标签添加一个属性 “download”;**

其中 download 后面的属性是下载后文件的文件名字

- 如果 url 指向同源资源，是正常的。
- 如果 url 指向第三方资源，download 会失效，表现和不使用 download 时一致——浏览器能打开的文件，浏览器会直接打开，不能打开的文件，会直接下载。浏览器打开的文件，可以手动下载。

解决方案一：将文件打包为 .zip/.rar 等浏览器不能打开的文件下载。

解决方案二：通过后端转发，后端请求第三方资源，返回给前端，前端使用 file-saver 等工具保存文件。



**使用表单提交**

```javascript
//方法二：通过form
$eleBtn2.click(function(){
    var $eleForm = $("<form method='get'></form>");
    $eleForm.attr("action","https://codeload.github.com/douban/douban-client/legacy.zip/master");
    $(document.body).append($eleForm);
    //提交表单，实现下载
    $eleForm.submit();
});
```



# 【2】transferto 遇到的问题 java.io.FileNotFoundException

解决前的代码：重点关注代码片段 image.transferTo(file)

```java
 public void saveOrUpdateImageFile(Info bean, MultipartFile image, HttpServletRequest req)
            throws Exception {
     String filepath="E:/image/info";//指定图片上传到哪个文件夹的路径
     File imageFolder = new File(filepath);
     System.out.println(imageFolder);
     File file = new File(imageFolder, bean.getId() + ".jpg");
     System.out.println(file);
     if (!file.getParentFile().exists())
         file.getParentFile().mkdirs();
     image.transferTo(file)；//出错地方

     //以下片段用来判断文件是否jpg格式，不需要看
     BufferedImage img = ImageUtil.change2jpg(file);
     System.out.println(img);
     ImageIO.write(img, "jpg", file);
 }
```

解决后的代码：引入commons-io的Maven依赖包，将`image.transferTo(file)`改为`FileUtils.copyInputStreamToFile(image.getInputStream(), file)`

```java
public void saveOrUpdateImageFile(Info bean, MultipartFile image, HttpServletRequest req)
            throws Exception {
    String filepath="E:/image/info";//指定图片上传到哪个文件夹的路径
    File imageFolder = new File(filepath);
    System.out.println(imageFolder);
    File file = new File(imageFolder, bean.getId() + ".jpg");
    System.out.println(file);
    if (!file.getParentFile().exists())
        file.getParentFile().mkdirs();
    FileUtils.copyInputStreamToFile(image.getInputStream(), file);

    //以下片段用来判断文件是否jpg格式，不需要看
    BufferedImage img = ImageUtil.change2jpg(file);
    System.out.println(img);
    ImageIO.write(img, "jpg", file);
}
```



# 【3】Java 将多个文件压缩打包成zip下载

步骤如下：

1. 设置下载文件名编码
2. 创建zip输出流ZipOutputStream
3. 将需要下载的文件流循环写入ZipOutputStream
4. 关闭各个流

```java
@Override
public void downloadFile(String ids, HttpServletResponse response) throws IOException {
    //获取文件对象
    List<SysFileInfo> sysFileInfos = selectSysFileInfoListByIds(ids);
    response.reset();
    response.setContentType("bin");
    String localPath = Global.getProfile();
    if (sysFileInfos.size() > 1) {
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("附件.zip", "UTF-8"));
        //获取响应中的输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //构建Zip流对象
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        for (SysFileInfo sysFileInfo : sysFileInfos) {
            String filePath = sysFileInfo.getFilePath();
            //获取本地文件
            File file = new File(localPath + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX));
            if (file.exists()) {
                //定义ZipEntry对象
                ZipEntry zipEntry = new ZipEntry(file.getName());
                //赋予Zip流对象属性
                zipOutputStream.putNextEntry(zipEntry);
                int let;
                //缓冲
                byte[] bytes = new byte[100];
                 //构建FileInputStream流对象
                FileInputStream fileInputStream = new FileInputStream(file);
                while ((let = fileInputStream.read(bytes)) > 0) {
                    zipOutputStream.write(bytes, 0, let);
                    zipOutputStream.flush();
                }
                //关闭closeEntry
                zipOutputStream.closeEntry();
                //关闭FileInputStream
                fileInputStream.close();
            }
        }
        //最后关闭ZIP流
        zipOutputStream.close();
    } else {
        ...
    }
}
```



# 【4】JS 获取 Model 的数据

```java
@Controller
public class ChartController {
    @Autowired
    private IReportService reportService;
    @GetMapping("/report/index")
    public String index(Model model){
        //查询 课程平均分数据
        List<ReportVo> reportVos = reportService.courseAvgReport();
        //存入model
        model.addAttribute("courseAvgs",reportVos);
        //返回页面
        return "views/report/report_list";
    }
}
```

```html
<script th:inline="javascript">
    let courseAvgs = [[${courseAvgs}]]
    console.log(courseAvgs)
</script>
```



# 【5】JS 判断 NaN 和保留两位小数

**window.isNaN()**

```javascript
(1)  window.isNaN(NaN)   // true
(2)  window.isNaN(123)   // false
//注意: window.isNaN 只对数值有效，如果传入其他值，会被先转成数值。比如，传入字符串的时候，字符串会被先转成NaN，所以最后返回true，这点要特别引起注意。也就是说，isNaN为true的值，有可能不是NaN，而是一个字符串。(不是数值会先调用 Number 方法转化为数值)

window.isNaN('Hello')    // true
//相当于
window.isNaN(Number('Hello'))   // true
```



**先判断是不是数字,然后再使用 window.isNaN()**

```javascript
function judgeNaN (value) {
	return (typeof value) === 'number' && window.isNaN(value);
}

judgeNaN(1)             //false
judgeNaN(NaN)          //true
judgeNaN("我是字符串")	//false
judgeNaN([])           //false
judgeNaN({})           //false
```



**Number.isNaN(value) ( 1. 首先判断 value 类型是不是 number; 2. 然后判断 value 是不是 NaN)**

```javascript
Number.isNaN(NaN);                 // true
Number.isNaN(Number.NaN);          // true
Number.isNaN(0/0);                 // true

// 下面这些使用 window.isNaN() 将会返回 true ,Number.isNaN() 返回 false,
// 因为 window.isNaN 会先把参数转化为数字类型,再判断是不是 NaN; 而 Number.isNaN 会先判断参数是不是数字类型,不是就返回 false, 是数字类型再进入判断是不是 NaN.
Number.isNaN('NaN');                      // false
Number.isNaN(undefined);                  // false
Number.isNaN({});                         // false
Number.isNaN('blabla');                   // false

// 下面这些 window.isNaN() 和 Number.isNaN() 都返回 false
Number.isNaN(true);
Number.isNaN(null);
Number.isNaN(37);
Number.isNaN('37');
Number.isNaN('37.37');
Number.isNaN('');
Number.isNaN(' ')
```



**两数字相除 保留两位小数**

```javascript
$('#aa').value=(a/b).toFixed(2);
```



# 【6】JS 图片预览

```html
<div>
    <img height="100" width="100" src="https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547_960_720.jpg" class="pic"/>
    <img height="100" width="100" src="https://cdn.pixabay.com/photo/2011/12/14/12/21/orion-nebula-11107_960_720.jpg" class="pic"/>
    <img height="100" width="100" src="https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg" class="pic"/>
</div>
<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
    <div id="innerdiv" style="position:absolute;">
        <img id="bigimg" style="border:5px solid #fff;" src="" />
     </div>
</div>
```

```html
<script src="./jquery.min.js"></script>
<script type="text/javascript">	
	$(function(){  
        $(".pic").click(function(){  
            var _this = $(this);//将当前的pimg元素作为_this传入函数  
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);  
        });  
    });  
 
    function imgShow(outerdiv, innerdiv, bigimg, _this){  
        var src = _this.attr("src");//获取当前点击的pimg元素中的src属性  
        $(bigimg).attr("src", src);//设置#bigimg元素的src属性  
      
        /*获取当前点击图片的真实大小，并显示弹出层及大图*/  
        $("<img/>").attr("src", src).load(function(){  
            var windowW = $(window).width();//获取当前窗口宽度  
            var windowH = $(window).height();//获取当前窗口高度  
            var realWidth = this.width;//获取图片真实宽度  
            var realHeight = this.height;//获取图片真实高度  
            var imgWidth, imgHeight;  
            var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
              
            if(realHeight>windowH*scale) {//判断图片高度  
                imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
                imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
                if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
                    imgWidth = windowW*scale;//再对宽度进行缩放  
                }  
            } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
                imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
                imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
            } else {//如果图片真实高度和宽度都符合要求，高宽不变  
                imgWidth = realWidth;  
                imgHeight = realHeight;  
            }  
            $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
              
            var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
            var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
            $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
            $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
        });  
          
        $(outerdiv).click(function(){//再次点击淡出消失弹出层  
            $(this).fadeOut("fast");  
        });  
    }	
</script>
```



# 【7】Ajax 上传/下载文件

**上传**

HTML 上传按钮：

```html
<div class="modal-body">
    <div class="form-group">
        <label for="upload">选择文件</label><span>&nbsp;&nbsp;*.xlsx&nbsp;&nbsp;*.docx&nbsp;&nbsp;*.pdf</span>
        <input type="file" class="form-control-file" id="upload">
    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
    <button type="button" class="btn btn-primary" onclick="imp()">导入</button>
</div>
```

Ajax 上传：

```javascript
function imp() {
    let name = $('#upload').val();
    if (name != "") {
        let formData = new FormData();
        formData.append("file", $("#upload")[0].files[0])
        formData.append("name", name);
        $.ajax({
            url: '/import',
            type: 'POST',
            data: formData,
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要设置Content-Type请求头
            contentType: false,
            success: function (response) {
                $('#table').bootstrapTable('refresh');
                $('#imp').modal('hide');
                $('#upload').val('');
                alert("导入成功 ^ ^");
            }
        })
    } else {
        alert("请选择导入文件！")
    }
}
```

Controller：

```java
/**
 * 导入商品
 * @param file 文件
 * @return 结果
 */
@PostMapping("/import")
public String importProduct(MultipartFile file){
    return productService.importProduct(file);
}
```

通过`file.getOriginalFilename()`可以获取到文件名，通过`file.getInputStream()`可以获取文件流。



**下载（Excel）**

Controller：

```java
/**
  * 导出商品
  *
  * @param response
  */
@GetMapping("/export")
public void export(HttpServletResponse response) {
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("utf-8");
    try {
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
        String fileName = URLEncoder.encode("商品", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    productService.export(response);
}
```

通过`response.getOutputStream()`可以获取输出流。

Ajax 下载:

jQuery 的 ajax 函数的返回类型只有 xml、text、json、html 等类型，没有“流”类型，所以要实现 ajax 下载，不能够使用相应的 ajax 函数进行文件下载。但可以用 js 生成一个 form，用这个 form 提交参数，并返回“流”类型的数据。在实现过程中，页面也没有进行刷新。

```javascript
// 导出
function exp() {
    $.ajax({
        url: '/export',
        type: 'GET',
        success: function (data) {
            let form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "GET");
            form.attr("action", "/export");
            let input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "exportData");
            input1.attr("value", (new Date()).getMilliseconds());
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
        }
    })
}
```

