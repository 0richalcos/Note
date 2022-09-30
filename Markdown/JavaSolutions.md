# 【1】Java 高效获取大文件的行数

**方式 1 : 利用 LineNumberReader**

```java
public static int getFileLineNum(String filePath) {
    try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath))){
        lineNumberReader.skip(Long.MAX_VALUE);
        int lineNumber = lineNumberReader.getLineNumber();
        return lineNumber + 1;//实际上是读取换行符数量 , 所以需要+1
    } catch (IOException e) {
        return -1;
    }
}
```

**方式 2 : Java 8 新的工具方法**

```java
public static long getFileLineNum(String filePath) {
    try {
        return Files.lines(Paths.get(filePath)).count();
    } catch (IOException e) {
        return -1;
    }
}
```

实际上 , Java 8 的新方法时间上并没有 LineNumberReader 快。经测试如下：

| 文件大小（行数） | LineNumberReader 耗时 | Java 8 方法耗时 |
| ---------------- | --------------------- | --------------- |
| 9656204          | 1098 ms               | 1385 ms         |
| 29691684         | 1512 ms               | 2237 ms         |

<br>

### 1、BufferedReader

BufferedReader 提供了下面两个功能：

1. 在普通 Reader 的基础上，提供了缓冲功能，可以更加高效的读取；
2. 提供了读取一行的功能：`readLine()`。

<br>

### 2、LineNumberReader

`Java.io.LineNumberReader` 类是一个缓冲的字符输入流，用于跟踪行号。行被视为由换行符 `\n`、回车符 `\r`或者回车后立即换行。

LineNumberReader 继承自 BufferedReader，并且增加了下面两个功能：

1. 获取行号：`getLineNumber()`
2. 设置行号：`setLineNumber()`

<br>

**构造函数**

| 序号 | 构造函数与说明                                               |
| ---- | ------------------------------------------------------------ |
| 1    | `LineNumberReader(Reader in)`<br>这将使用默认的输入缓冲区大小创建一个新的行号读取器。 |
| 2    | `LineNumberReader(Reader in, int sz)`<br>这将创建一个新的行号读取器，将字符读取到给定大小的缓冲区中。 |

<br>

**类方法**

| 序号 | 方法与说明                                                   |
| ---- | ------------------------------------------------------------ |
| 1    | `int getLineNumber()`<br>此方法获取当前行号。                |
| 2    | `void mark(int readAheadLimit)`<br/>此方法标记流中的当前位置。 |
| 3    | `int read()`<br/>此方法读取单个字符。                        |
| 4    | `int read(char[] cbuf, int off, int len)`<br/>此方法将字符读入数组的一部分。 |
| 5    | `String readLine()`<br/>此方法读取一行文本。                 |
| 6    | `void reset()`<br/>此方法将流重置为最新标记。                |
| 7    | `void setLineNumber(int lineNumber)`<br/>此方法设置当前行号。 |
| 8    | `long skip(long n)`<br/>此方法跳过 *n* 个字符，返回实际跳过的字符数。 |

