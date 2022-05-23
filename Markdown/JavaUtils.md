这里记录一些开发过程中遇到的好用的工具类。

<br>

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

