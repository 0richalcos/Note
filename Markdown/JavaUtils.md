---
typora-copy-images-to: upload
---

这里记录一些开发过程中遇到的好用的工具类。



# 1、RandomStringUtils

该工具类可以用于生成随机字符串。

全称为：`org.apache.commons.lang3.RandomStringUtils`，下面是一些方法：

| Modifier and Type | Method                        | Description                                                  |
| ----------------- | ----------------------------- | ------------------------------------------------------------ |
| static String     | random(int count)             | 创建一个随机字符串，其长度为指定的字符数。<br>将从所有字符的集合中选择字符。<br>参数：<br> - *count*：要创建的随机字符串的长度 |
| static String     | randomAlphabetic(int count)   | 创建一个随机字符串，其长度为指定的字符数。<br>字符将从拉丁字母字符集（a-z，A-Z）中选择。<br>参数：<br> - *count*：要创建的随机字符串的长度 |
| static String     | randomAlphanumeric(int count) | 创建一个随机字符串，其长度为指定的字符数。<br>字符将从拉丁字母字符a-z，A-Z）和数字 0-9 中选择。<br>参数：<br> - *count*：要创建的随机字符串的长度 |
| static String     | randomNumeric(int count)      | 创建一个随机字符串，其长度为指定的字符数。<br>将从数字字符集中选择字符。<br>参数：<br> - *count*：要创建的随机字符串的长度 |
| static String     | randomAscii(int count)        | 创建一个随机字符串，其长度为指定的字符数。<br>将从 ASCII 值介于 32 和 126 (含)。<br>参数：<br> - *count*：要创建的随机字符串的长度 |
| static String     | randomGraph(int count)        | 创建一个随机字符串，其长度为指定的字符数。<br>将从匹配 POSIX [:graph:] 正则表达式字符类的字符集中选择字符。此类包含所有可见的ASCII 字符 (即除空格和控制字符以外的任何字符)。<br>参数：<br> - *count*：要创建的随机字符串的长度 |
| static String     | randomPrint(int count)        | 创建一个随机字符串，其长度为指定的字符数。<br>将从匹配 POSIX [:print:] 正则表达式字符类的字符集中选择字符。此类包括所有可见的ASCII 字符和空格 (即除控制字符以外的任何内容)。<br>参数：<br> - *count*：要创建的随机字符串的长度 |



# 2、StringUtils.join()

将数组或集合以某拼接符拼接到一起形成新的字符串。

`StringUtils` 全称为 `org.apache.commons.lang3.StringUtils`，`join()` 方法如下图：

![image-20230801122834011](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230801122834011.png)

基本上此方法需传入 2 个参数，第一个参数是传入一个任意类型数组或集合，第二个参数是拼接符。

```java
List<String> list = new ArrayList<>();
list.add("Mxy");
list.add("StringUtils");
list.add("join");
String join = StringUtils.join(list,"-");//传入String类型的List集合，使用"-"号拼接
System.out.println(join);

String[] s = new String[]{"Yuan","Mxy"};//传入String类型的数组，使用"-"号拼接
String join2 = StringUtils.join(s,"-");
System.out.println(join2);
```

结果如下：

```
Mxy-StringUtils-join
Yuan-Mxy
```



**String.join()**

`String.join()` 是 JDK8 新增方法（无需引包）

![image-20230801123153856](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20230801123153856.png)

参数为 2 个，第一个参数为拼接符号，第二个参数为数组和集合。

这里和 `StringUtils.join()` 有区别，参数顺序不一样，另外，`StringUtils.join(）`可以传入 `Integer` 或者其他类型的集合或数组，而 `String.join()` 仅可以传入实现 `CharSequence` 接口类型的集合或数组。

如果是字符串类型的集合或数组推荐使用 `String.join()`。
