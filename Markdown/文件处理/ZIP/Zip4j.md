# 1、JDK 内置操作 ZIP 文件

## 1.1、ZipInputStream

`ZipInputStream` 是 Java 类，实现用于读取 ZIP 文件格式的文件的输入流过滤器。 它支持压缩和未压缩的条目。

`ZipInputStream` 具有以下构造函数：

```java
ZipInputStream(InputStream in)
ZipInputStream(InputStream in, Charset charset)
```

`ZipInputStream.getNextEntry()` 读取下一个 ZIP 文件条目，并将流定位在条目数据的开头。



**Java 读取 ZIP 示例**

下面的示例读取一个 ZIP 文件的内容：

```java
public static Map<String, String> readZipFile(String file) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        Charset gbk = Charset.forName("GBK");
        try (InputStream in = new BufferedInputStream(new FileInputStream(file));
             ZipInputStream zin = new ZipInputStream(in, gbk);
        ) {
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                if (!ze.isDirectory()) {
                    // 文件的大小
                    long size = ze.getSize();
                    // 获取文件名称
                    String name = ze.getName();
                    // 具体对其中每个文件的操作和获取信息，可以参考JDK API
                    if (size > 0) {
                        // ……	业务逻辑
                    }
                }
                zin.closeEntry();
            }
        }
        return resultMap;
    }
```

从文件创建一个 `FileInputStream`。 `FileInputStream` 用于读取原始字节流，为了获得更好的性能，将 `FileInputStream` 传递到`BufferedInputStream` 中。

```java
InputStream in = new BufferedInputStream(new FileInputStream(file));
```

> 一般小的字节（byte）文件的读取和写入数据，使用 `FileInputStream` 和 `FileOutputStream` 类就可以实现了，但是对于大的字节（byte）文件的读取和写入数据，性能就会有很大的问题，一般选择 `BufferedInputStream` 和 `BufferedOutputStream` 来处理，也就是说 `BufferedInputStream` 和 `BufferedOutputStream` 主要的目的是提高字节（byte）文件的内容读取和写入的性能。
>
> ```java
> //创建一个有32字节的缓存区
> BufferedInputStream(InputStream in)
> //创建一个有size大小字节的缓存区
> BufferedInputStream(InputStream in, int size)
> ```

`ZipInputStream` 是从缓冲的 `FileInputStream` 创建的。 当资源不再需要时，try-with-resources 将关闭流。

```java
ZipInputStream zin = new ZipInputStream(in);
```

在 while 循环中，使用 `getNextEntry()` 方法浏览 ZIP 文件的条目。 如果没有更多条目，则返回 `null`。

```java
while ((ze = zin.getNextEntry()) != null) 
```



**Java 解压缩 ZIP 示例**

用 Java 解压缩 ZIP 文件：

```java
public static void unZipFile(String file) throws Exception {
    Path outDir = Paths.get("src/resources/output/");
    byte[] buffer = new byte[2048];

    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
         ZipInputStream stream = new ZipInputStream(bis)
        ) {
        ZipEntry entry;
        while ((entry = stream.getNextEntry()) != null) {
            Path filePath = outDir.resolve(entry.getName());
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
                 BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length)) {
                int len;
                while ((len = stream.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
            }
        }
        stream.closeEntry();
    }
}
```

该示例使用 `ZipInputStream` 读取给定 ZIP 文件的内容，并使用 `FileOutputStream` 和 `BufferedOutputStream` 将该内容写入目录。



## 2.2、ZipOutputStream

**Java 将多个文件压缩打包成 ZIP 下载：**

步骤如下：

1. 设置下载文件名编码
2. 创建 ZIP 输出流 `ZipOutputStream`
3. 将需要下载的文件流循环写入 `ZipOutputStream`
4. 关闭各个流

```java
public void downloadFile(String ids, HttpServletResponse response) throws IOException {
    //获取文件对象
    List<SysFileInfo> sysFileInfos = selectSysFileInfoListByIds(ids);
    response.reset();
    response.setContentType("bin");
    String localPath = Global.getProfile();
    if (sysFileInfos.size() > 1) {
         try (//获取响应中的输出流
             ServletOutputStream outputStream = response.getOutputStream();
             //构建Zip流对象
             ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
             response.addHeader("Content-Disposition", "attachment;filename=" + FileUtils.setFileDownloadHeader(request, "附件" + System.currentTimeMillis() + ".zip"));
             for (SysFileInfo sysFileInfo : sysFileInfos) {
                 String filePath = sysFileInfo.getFilePath();
                 File file = new File(localPath + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX));
                 //获取文件的绝对路径
                 String absolutePath = file.getAbsolutePath();
                 //将处理过后的文件名替换为文件的原名
                 File newFile = new File(absolutePath.replace(file.getName(), sysFileInfo.getFileName()));
                 if (file.exists()) {
                     file.renameTo(newFile);
                     //定义ZipEntry对象
                     ZipEntry zipEntry = new ZipEntry(newFile.getName());
                     //赋予Zip流对象属性
                     zipOutputStream.putNextEntry(zipEntry);
                     int let;
                     byte[] bytes = new byte[100];
                     try (FileInputStream fileInputStream = new FileInputStream(newFile)) {
                         while ((let = fileInputStream.read(bytes)) > 0) {
                             zipOutputStream.write(bytes, 0, let);
                             zipOutputStream.flush();
                         }
                     }
                     //关闭closeEntry
                     zipOutputStream.closeEntry();
                     newFile.renameTo(file);
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    } else {
        ...
    }
}
```



# 2、Zip4j

Zip4j 默认采用的是 UTF-8 编码，所以本身支持中文（但是，还是建议在读取 ZIP 文件后，立即设置字符集），同时也支持密码，而且支持多种压缩算法，可以说功能强大，但使用起来却非常简单。如果有其他需求，需要自己从官网上看 API。

**官网**：[GitHub - srikanth-lingala/zip4j: A Java library for zip files and streams](https://github.com/srikanth-lingala/zip4j)

**特性**：

1. 支持 Zip 文件的创建、添加、解压、更新、移除
2. 可读写有密码保护的 ZIP文件
3. 支持 AES 128/256 算法加密
4. 支持标准 ZIP 算法加密
5. 支持 ZIP64 格式
6. 支持分块 ZIP 文件的创建和解压
7. 支持 Unicode 编码的文件名
8. 支持进度监控



**Maven依赖**：

```xml
<!-- https://mvnrepository.com/artifact/net.lingala.zip4j/zip4j -->
<dependency>
    <groupId>net.lingala.zip4j</groupId>
    <artifactId>zip4j</artifactId>
    <version>2.9.1</version>
</dependency>

```



## 2.1、解压

**解压文件：**

```java
// 获取Zip文件
ZipFile zipFile = new ZipFile(filePath);
// 指定文件名编码
zipFile.setCharset(Charset.forName("GBK"));

// 验证文件有效性
if (!zipFile.isValidZipFile()) {
    return;
}

// 解压目录
File fileDir = new File(destPath);

// 目录不存在则创建
if (fileDir.isDirectory() && !fileDir.exists()) {
    fileDir.mkdir();
}

// 解压
zipFile.extractAll(destPath);
```



**过滤文件：**

```java
private void filterZipFile(ZipFile zipFile, String destPath) throws ZipException {
    List fileHeaders = zipFile.getFileHeaders();

    for (Object objFileHeader : fileHeaders) {
        FileHeader fileHeader = (FileHeader) objFileHeader;
        String fileName = fileHeader.getFileName();

        // 匹配到是需要过滤的文件则跳过
        if (isIllegalFile(fileName)) {
            continue;
        }

        // 匹配到隐藏文件则跳过
        if (isHiddenFile(fileName)) {
            continue;
        }

        zipFile.extractFile(fileHeader, destPath);
    }
}
```



## 2.2、压缩

**压缩一个文件**

首先，我们将使用 `ZipFile addFile()` 方法将一个名为 `aFile.txt` 的文件压缩到一个名为 `compressed.zip` 的有密码保护的 Zip 文件：

```java
ZipParameters zipParameters = new ZipParameters();
zipParameters.setEncryptFiles(true);
zipParameters.setCompressionLevel(CompressionLevel.HIGHER);
zipParameters.setEncryptionMethod(EncryptionMethod.AES);

ZipFile zipFile = new ZipFile("compressed.zip", "password".toCharArray());
zipFile.addFile(new File("aFile.txt"), zipParameters);
```

在这个例子中 `setCompressionLevel()` 一行是可选的，我们可以从 `FASTEST` 到 `ULTRA` 级别中选择（默认是 `NORMAL`）。同时使用了 AES 加密，如果想使用 Zip 标准加密，只需用 `ZIP_STANDARD` 替换 `AES`。

注意，如果文件 "aFile.txt" 在磁盘上不存在，该方法将抛出一个异常：`net.lingala.zip4j.exception.ZipException File does not exist: …`，为了解决这个问题，必须确保该文件是手动创建并放置在项目文件夹中，或者必须从 Java 中创建它。

```java
File fileToAdd = new File("aFile.txt");
if (!fileToAdd.exists()) {
    fileToAdd.createNewFile();
}
```

另外，在我们完成了新的 `ZipFile` 之后，需要及时关闭资源：

```javas
zipFile.close();
```



**压缩多个文件**

修改一下代码，不使用 `addFile()` 方法，而是使用 `addFiles()` 并传入一个装有文件的 `List`，以便我们能够一次压缩多个文件：

```java
ZipParameters zipParameters = new ZipParameters();
zipParameters.setEncryptFiles(true);
zipParameters.setEncryptionMethod(EncryptionMethod.AES);

List<File> filesToAdd = Arrays.asList(
  new File("aFile.txt"),
  new File("bFile.txt")
);

ZipFile zipFile = new ZipFile("compressed.zip", "password".toCharArray());
zipFile.addFiles(filesToAdd, zipParameters);
```



**压缩一个目录**

我们可以简单地用 `addFolder()` 代替 `addFile()` 方法来压缩一个文件夹：

```java
ZipFile zipFile = new ZipFile("compressed.zip", "password".toCharArray());
zipFile.addFolder(new File("/users/folder_to_add"), zipParameters);
```



## 2.3、其他

**不解压 ZIP 文件的前提下，直接利用流（InuptStream）形式读取其中的文件：**

```java
public static void readZipFile(String file) throws Exception {
	ZipFile zFile = new ZipFile(file);
    // 此处最好立即设置字符集
    zipFile.setCharset(Charset.forName("GBK"));
    if (!zFile.isValidZipFile()) {
        return;
    }

    // 获取ZIP中所有文件的FileHeader，以便后面对ZIP中文件进行遍历
    List<FileHeader> list = zFile.getFileHeaders();
    // 此时list的size包括：文件夹、子文件夹、文件的个数
    System.out.println(list.size());
    // 遍历其中的文件
    for (FileHeader fileHeader : list) {
        String fileName = fileHeader.getFileName();
        // fileName会将目录单独读出来，而且带有路径分割符
        if (fileName.endsWith("/") || fileName.endsWith("\\\\") || fileName.endsWith("\\")) {
            System.out.println(fileName + " 这是一个文件夹。");
            continue;
        } else {
            ZipInputStream inputStream = zFile.getInputStream(fileHeader);
            操作...
            System.out.println(fileName + " 这是一个文件。");
        }
    }
}
```

