# 1、JDK 内置操作 Zip 文件

## 1.1、ZipInputStream

`ZipInputStream` 是 Java 类，实现用于读取 ZIP 文件格式的文件的输入流过滤器。 它支持压缩和未压缩的条目。

`ZipInputStream` 具有以下构造函数：

```java
ZipInputStream(InputStream in)
ZipInputStream(InputStream in, Charset charset)
```

`ZipInputStream.getNextEntry()` 读取下一个 ZIP 文件条目，并将流定位在条目数据的开头。

<br>

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

<br>

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

<br>

2、Zip4j