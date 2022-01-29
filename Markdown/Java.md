



# 8、异常（Exception）

在程序设计和运行的过程中，发生错误是不可避免的。尽管 Java 语言的设计从根本上提供了便于写出整洁、安全代码的方法，并且程序员也尽量地减少错误的产生，但是使程序被迫停止的错误的存在仍然不可避免。为此，Java 提供了异常处理机制来帮助程序员检查可能出现的错误，以保证程序的可读性和可维护性。

Java 将异常封装到一个类中，出现错误时就会拋出异常。

## 8.1、异常处理及常见异常

很多事件并非总是按照人们自己设计意愿顺利发展的，经常出现这样那样的异常情况。例如： 你计划周末郊游，计划从家里出发→到达目的→游泳→烧烤→回家。但天有不测风云，当你准备烧烤时候突然天降大雨，只能终止郊游提前回家。“天降大雨”是一种异常情况，你的计划应该考虑到这样的情况，并且应该有处理这种异常的预案。

计算机程序的编写也需要考虑处理这些异常情况。==异常（exception）是在运行程序时产生的一种异常情况，==已经成为了衡量一门语言是否成熟的标准之一。目前的主流编程语言，如 C++、c#、Ruby 和 Python 等大都提供了异常处理机制。

### 8.1.1、异常简介

==Java 中的异常又称为例外，是一个在程序执行期间发生的事件，它中断正在执行程序的正常指令流。==为了能够及时有效地处理程序中的运行错误，必须使用异常类，这可以让程序具有极好的容错性且更加健壮。 

在 Java 中一个异常的产生，主要有如下三种原因：

1. Java 内部错误发生异常，Java 虚拟机产生的异常。
2. 编写的程序代码中的错误所产生的异常，例如空指针异常、数组越界异常等。
3. 通过 throw 语句手动生成的异常，一般用来告知该方法的调用者一些必要信息。

Java 通过面向对象的方法来处理异常。在一个方法的运行过程中，如果发生了异常，则这个方法会产生代表该异常的一个对象，并把它交给运行时的系统，运行时系统寻找相应的代码来处理这一异常。

我们把生成异常对象，并把它提交给运行时系统的过程称为==拋出（throw）异常。==运行时系统在方法的调用栈中查找，直到找到能够处理该类型异常的对象，这一个过程称为==捕获（catch）异常。==



### 8.1.2、异常类型

为了能够及时有效地处理程序中的运行错误，Java 专门引入了异常类。==在 Java 中所有异常类型都是内置类 java.lang.Throwable 类的子类，即 Throwable 位于异常类层次结构的顶层。==Throwable 类下有两个异常分支 Exception 和 Error，如下图所示。

<img src="../Images/Java/3-1Q0231H424V1.jpg" alt="img" style="zoom:80%;" />

==Throwable 类是所有异常和错误的超类，下面有 Error 和 Exception 两个子类分别表示错误和异常。==其中异常类 Exception 又分为==运行时异常==和==非运行时异常，==这两种异常有很大的区别，也称为==不检查异常（Unchecked Exception）==和==检查异常（Checked Exception）。==

- Exception 类用于用户程序可能出现的异常情况，它也是用来创建自定义异常类型类的类。
- Error 定义了在通常环境下不希望被程序捕获的异常。一般指的是 JVM 错误，如堆栈溢出。

运行时异常都是 RuntimeException 类及其子类异常，如 NullPointerException、IndexOutOfBoundsException 等，这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般由程序逻辑错误引起，程序应该从逻辑角度尽可能避免这类异常的发生。

非运行时异常是指 RuntimeException 以外的异常，类型上都属于 Exception 类及其子类。从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。如 IOException、ClassNotFoundException 等以及用户自定义的 Exception 异常（一般情况下不自定义检查异常）。

Java中常见运行时异常

| 异常类型                      | 说明                                                  |
| ----------------------------- | ----------------------------------------------------- |
| ArithmeticException           | 算术错误异常，如以零做除数                            |
| ArraylndexOutOfBoundException | 数组索引越界                                          |
| ArrayStoreException           | 向类型不兼容的数组元素赋值                            |
| ClassCastException            | 类型转换异常                                          |
| IllegalArgumentException      | 使用非法实参调用方法                                  |
| lIIegalStateException         | 环境或应用程序处于不正确的状态                        |
| lIIegalThreadStateException   | 被请求的操作与当前线程状态不兼容                      |
| IndexOutOfBoundsException     | 某种类型的索引越界                                    |
| NullPointerException          | 尝试访问 null 对象成员，空指针异常                    |
| NegativeArraySizeException    | 再负数范围内创建的数组                                |
| NumberFormatException         | 数字转化格式异常，比如字符串到 float 型数字的转换无效 |
| TypeNotPresentException       | 类型未找到                                            |

Java常见非运行时异常

| 异常类型                     | 说明                       |
| ---------------------------- | -------------------------- |
| ClassNotFoundException       | 没有找到类                 |
| IllegalAccessException       | 访问类被拒绝               |
| InstantiationException       | 试图创建抽象类或接口的对象 |
| InterruptedException         | 线程被另一个线程中断       |
| NoSuchFieldException         | 请求的域不存在             |
| NoSuchMethodException        | 请求的方法不存在           |
| ReflectiveOperationException | 与反射有关的异常的超类     |



## 8.2、Error、Exception 

Error（错误）和 Exception（异常）都是 java.lang.Throwable 类的子类，在 Java 代码中只有继承了 Throwable 类的实例才能被 throw 或者 catch。

Exception 和 Error 体现了 Java 平台设计者对不同异常情况的分类，Exception 是程序正常运行过程中可以预料到的意外情况，并且应该被开发者捕获，进行相应的处理。Error 是指正常情况下不大可能出现的情况，绝大部分的 Error 都会导致程序处于非正常、不可恢复状态。所以不需要被开发者捕获。

Error 错误是任何处理技术都无法恢复的情况，肯定会导致程序非正常终止。并且 Error 错误属于未检查类型，大多数发生在运行时。Exception 又分为可检查（checked）异常和不检查（unchecked）异常，可检查异常在源码里必须显示的进行捕获处理，这里是编译期检查的一部分。不检查异常就是所谓的运行时异常，通常是可以编码避免的逻辑错误，具体根据需要来判断是否需要捕获，并不会在编译器强制要求。

如下是常见的 Error 和 Exception：

1）运行时异常（RuntimeException）：

- NullPropagation：空指针异常；
- ClassCastException：类型强制转换异常
- IllegalArgumentException：传递非法参数异常
- IndexOutOfBoundsException：下标越界异常
- NumberFormatException：数字格式异常


2）非运行时异常：

- ClassNotFoundException：找不到指定 class 的异常
- IOException：IO 操作异常


3）错误（Error）：

- NoClassDefFoundError：找不到 class 定义异常
- StackOverflowError：深递归导致栈被耗尽而抛出的异常
- OutOfMemoryError：内存溢出异常

下面代码会导致 Java 堆栈溢出错误。

```java
// 通过无限递归演示堆栈溢出错误
class StackOverflow {
    public static void test(int i) {
        if (i == 0) {
            return;
        } else {
            test(i++);
        }
    }
}

public class ErrorEg {
    public static void main(String[] args) {
        // 执行StackOverflow方法
        StackOverflow.test(5);
    }
}
```

运行输出为：

```
Exception in thread "main" java.lang.StackOverflowError
    at ch11.StackOverflow.test(ErrorEg.java:9)
    at ch11.StackOverflow.test(ErrorEg.java:9)
    at ch11.StackOverflow.test(ErrorEg.java:9)
    at ch11.StackOverflow.test(ErrorEg.java:9)
```

上面代码通过无限递归调用最终引发了 java.lang.StackOverflowError 错误。



## 8.3、异常处理机制

Java 的异常处理通过 5 个关键字来实现：try、catch、throw、throws 和 finally。==try catch 语句用于捕获并处理异常，finally 语句用于在任何情况下（除特殊情况外）都必须执行的代码，throw 语句用于拋出异常，throws 语句用于声明可能会出现的异常。== 

Java 的异常处理机制提供了一种结构性和控制性的方式来处理程序执行期间发生的事件。异常处理的机制如下：

- 在方法中用 try catch 语句捕获并处理异常，catch 语句可以有多个，用来匹配多个异常。
- 对于处理不了的异常或者要转型的异常，在方法的声明处通过 throws 语句拋出异常，即由上层的调用方法来处理。

以下代码是异常处理程序的基本结构：

```java
try {
    逻辑程序块
} catch(ExceptionType1 e) {
    处理代码块1
} catch (ExceptionType2 e) {
    处理代码块2
    throw(e);    // 再抛出这个"异常"
} finally {
    释放资源代码块
}
```

**throw**

- 表示方法内抛出某种异常对象。
- 如果异常对象是非 RuntimeException 则需要在方法申明时加上该异常的抛出，即需要加上 throws 语句 或者 在方法体内 try catch 处理该异常，否则编译报错。
- 执行到 throw 语句则后面的语句块不再执行

**throws**

- 方法的定义上使用 throws 表示这个方法可能抛出某种异常
- 需要由方法的调用者进行异常处理。



## 8.4、try catch语句

在实际应用中，对于错误的处理是极其重要的，任何程序都很难做到百分百完美，程序中可能存在大量未知问题，所以程序开发时一定要对各种问题进行相应的处理，而 Java 提供的异常处理机制可以帮用户更好地解决这方面的问题。Java 的异常处理机制可以让程序具有极好的容错性，让程序更加健壮。

==在 Java 中通常采用 try catch 语句来捕获异常并处理。==语法格式如下：

```java
try {
    // 可能发生异常的语句
} catch(ExceptionType e) {
    // 处理异常语句
}
```

在以上语法中，把可能引发异常的语句封装在 try 语句块中，用以捕获可能发生的异常。catch 后的`( )`里放匹配的异常类，指明 catch 语句可以处理的异常类型，发生异常时产生异常类的实例化对象。

如果 try 语句块中发生异常，那么一个相应的异常对象就会被拋出，然后 catch 语句就会依据所拋出异常对象的类型进行捕获，并处理。处理之后，程序会跳过 try 语句块中剩余的语句，转到 catch 语句块后面的第一条语句开始执行。

如果 try 语句块中没有异常发生，那么 try 块正常结束，后面的 catch 语句块被跳过，程序将从 catch 语句块后的第一条语句开始执行。

注意：try...catch 与 if...else 不一样，try 后面的花括号`{ }`不可以省略，即使 try 块里只有一行代码，也不可省略这个花括号。与之类似的是，catch 块后的花括号`{ }`也不可以省略。另外，try 块里声明的变量只是代码块内的局部变量，它只在 try 块内有效，其它地方不能访问该变量。

可以使用以下 3 个方法输出相应的异常信息。

- printStackTrace() 方法：指出异常的类型、性质、栈层次及出现在程序中的位置。
- getMessage() 方法：输出错误的性质。
- toString() 方法：给出异常的类型与性质。

编写一个录入学生姓名、年龄和性别的程序，要求能捕捉年龄不为数字时的异常。在这里使用 try catch 语句来实现，具体代码如下：

```java
import java.util.Scanner;
public class Test02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------学生信息录入---------------");
        String name = ""; // 获取学生姓名
        int age = 0; // 获取学生年龄
        String sex = ""; // 获取学生性别
        try {
            System.out.println("请输入学生姓名：");
            name = scanner.next();
            System.out.println("请输入学生年龄：");
            age = scanner.nextInt();
            System.out.println("请输入学生性别：");
            sex = scanner.next();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("输入有误！");
        }
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
    }
}
```

述代码在 main() 方法中使用 try catch 语句来捕获异常，将可能发生异常的`age = scanner.nextlnt();`代码放在了 try 块中，在 catch 语句中指定捕获的异常类型为 Exception，并调用异常对象的 printStackTrace() 方法输出异常信息。运行结果如下所示。

```
---------学生信息录入---------------
请输入学生姓名：
徐白
请输入学生年龄：
110a
java.util.InputMismatchException
    at java.util.Scanner.throwFor(Unknown Source)
    at java.util.Scanner.next(Unknown Source)
    at java.util.Scanner.nextInt(Unknown Source)
    at java.util.Scanner.nextInt(Unknown Source)
输入有误！
姓名：徐白
年龄：0
    at text.text.main(text.java:19)
```

---

**多重catch语句**

如果 try 代码块中有很多语句会发生异常，而且发生的异常种类又很多。那么可以在 try 后面跟有多个 catch 代码块。多 catch 代码块语法如下：

```java
try {
    // 可能会发生异常的语句
} catch(ExceptionType e) {
    // 处理异常语句
} catch(ExceptionType e) {
    // 处理异常语句
} catch(ExceptionType e) {
    // 处理异常语句
...
}
```

在多个 catch 代码块的情况下，当一个 catch 代码块捕获到一个异常时，其它的 catch 代码块就不再进行匹配。

注意：当捕获的多个异常类之间存在父子关系时，捕获异常时一般先捕获子类，再捕获父类。所以子类异常必须在父类异常的前面，否则子类捕获不到。

```java
public class Test03 {
    public static void main(String[] args) {
        Date date = readDate();
        System.out.println("读取的日期 = " + date);
    }
    
    public static Date readDate() {
        FileInputStream readfile = null;
        InputStreamReader ir = null;
        BufferedReader in = null;
        try {
            readfile = new FileInputStream("readme.txt");
            ir = new InputStreamReader(readfile);
            in = new BufferedReader(ir);
            // 读取文件中的一行数据
            String str = in.readLine();
            if (str == null) {
                return null;
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(str);
            return date;
        } catch (FileNotFoundException e) {
            System.out.println("处理FileNotFoundException...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("处理IOException...");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("处理ParseException...");
            e.printStackTrace();
        }
        return null;
    }
}
```

上述代码通过 Java I/O（输入输出）流技术从文件 readme.txt 中读取字符串，然后解析成为日期。

在 try 代码块中第 12 行代码调用 FileInputStream 构造方法可能会发生 FileNotFoundException 异常。第 16 行代码调用 BufferedReader 输入流的 readLine() 方法可能会发生 IOException 异常。FileNotFoundException 异常是 IOException 异常的子类，应该先捕获 FileNotFoundException 异常，见代码第 23 行；后捕获 IOException 异常，见代码第 26 行。

如果将 FileNotFoundException 和 IOException 捕获顺序调换，那么捕获 FileNotFoundException 异常代码块将永远不会进入，FileNotFoundException 异常处理永远不会执行。 上述代码第 29 行 ParseException 异常与 IOException 和 FileNotFoundException 异常没有父子关系，所以捕获 ParseException 异常位置可以随意放置。



## 8.5、try catch finally语句

在实际开发中，根据 try catch 语句的执行过程，try 语句块和 catch 语句块有可能不被完全执行，而有些处理代码则要求必须执行。例如，程序在 try 块里打开了一些物理资源（如数据库连接、网络连接和磁盘文件等），这些物理资源都必须显式回收。

Java的垃圾回收机制不会回收任何物理资源，垃圾回收机制只回收堆内存中对象所占用的内存。

所以为了确保一定能回收 try 块中打开的物理资源，异常处理机制提供了 finally 代码块，并且 Java 7 之后提供了==自动资源管理（Automatic Resource Management）==技术。

finally 语句可以与前面介绍的 try catch 语句块匹配使用，语法格式如下：

```java
try {
    // 可能会发生异常的语句
} catch(ExceptionType e) {
    // 处理异常语句
} finally {
    // 清理代码块
}
```

对于以上格式，无论是否发生异常（除特殊情况外），finally 语句块中的代码都会被执行。此外，finally 语句也可以和 try 语句匹配使用，其语法格式如下：

```java
try {
    // 逻辑代码块
} finally {
    // 清理代码块
}
```

使用 try-catch-finally 语句时需注意以下几点：

1. 异常处理语法结构中只有 try 块是必需的，也就是说，如果没有 try 块，则不能有后面的 catch 块和 finally 块；
2. catch 块和 finally 块都是可选的，但 catch 块和 finally 块至少出现其中之一，也可以同时出现；
3. 可以有多个 catch 块，捕获父类异常的 catch 块必须位于捕获子类异常的后面；
4. 不能只有 try 块，既没有 catch 块，也没有 finally 块；
5. 多个 catch 块必须位于 try 块之后，finally 块必须位于所有的 catch 块之后。
6. finally 与 try 语句块匹配的语法格式，此种情况会导致异常丢失，所以不常见。

==一般情况下，无论是否有异常拋出，都会执行 finally 语句块中的语句，==执行流程如下图所示。

<img src="../Images/Java/3-1Q024110159364.jpg" alt="img" style="zoom:80%;" />

try catch finally 语句块的执行情况可以细分为以下 3 种情况：

1. 如果 try 代码块中没有拋出异常，则执行完 try 代码块之后直接执行 finally 代码块，然后执行 try catch finally 语句块之后的语句。
2. 如果 try 代码块中拋出异常，并被 catch 子句捕捉，那么在拋出异常的地方终止 try 代码块的执行，转而执行相匹配的 catch 代码块，之后执行 finally 代码块。如果 finally 代码块中没有拋出异常，则继续执行 try catch finally 语句块之后的语句；如果 finally 代码块中拋出异常，则把该异常传递给该方法的调用者。
3. 如果 try 代码块中拋出的异常没有被任何 catch 子句捕捉到，那么将直接执行 finally 代码块中的语句，并把该异常传递给该方法的调用者。

除非在 try 块、catch 块中调用了退出虚拟机的方法`System.exit(int status)`，否则不管在 try 块或者 catch 块中执行怎样的代码，出现怎样的情况，异常处理的 finally 块总会执行。

通常情况下==不在 finally 代码块中使用 return 或 throw 等导致方法终止的语句，==否则将会导致 try 和 catch 代码块中的 return 和 throw 语句失效。



## 8.6、finally和return执行顺序

**情况1：** try{} catch(){}finally{} return;
显然程序按顺序执行。

---

**情况2:** try{ return; }catch(){} finally{} return;

1. 先执行try块中return 语句（包括return语句中的表达式运算），但不返回；
2. 执行finally语句中全部代码
3. 最后执行try中return 返回
4. finally块之后的语句return不执行，因为程序在try中已经return。

---

**情况3:** try{ } catch(){return;} finally{} return;
程序先执行try，如果遇到异常执行catch块，

- 有异常：

	1. 执行catch中return语句，但不返回

	2. 执行finally语句中全部代码，

	3. 最后执行catch块中return返回。 finally块后的return语句不再执行。
- 无异常：执行完try再finally再return…

---

**情况4:** try{ return; }catch(){} finally{return;}

1. 执行try块return语句（包括return语句中的表达式运算），但不返回；
2. 再执行finally块，
3. 执行finally块，有return，从这里返回。

---

**情况5:** try{} catch(){return;}finally{return;}

1. 程序执行catch块中return语句（包括return语句中的表达式运算），但不返回；
2. 再执行finally块，
3. 执行finally块，有return，从这里返回。

---

**情况6:** try{ return;}catch(){return;} finally{return;}
程序执行try块中return语句（包括return语句中的表达式运算），但不返回；

- 有异常：

	1. 执行catch块中return语句（包括return语句中的表达式运算），但不返回；

	2. 再执行finally块

	3. 执行finally块，有return，从这里返回。

- 无异常：

	1. 再执行finally块
2. 执行finally块，有return，从这里返回。

---

**结论：**

- 不管有木有出现异常，finally块中代码都会执行；
- 当try和catch中有return时，finally仍然会执行；
- finally是在return语句执行之后，返回之前执行的（此时并没有返回运算后的值，而是先把要返回的值保存起来，不管finally中的代码怎么样，返回的值都不会改变，仍然是之前保存的值），所以函数返回值是在finally执行前就已经确定了；
- finally中如果包含return，那么程序将在这里返回，而不是try或catch中的return返回，返回值就不是try或catch中保存的返回值了。

---

**关于finally中修改返回值的问题：**

- 如果return的数据是基本数据类型或文本字符串（==字符串虽然为引用类型，但是在finally改变的时候，其实当于申请了一个新的字符串==），则在finally中对该基本数据的改变不起作用，try中的return语句依然会返回进入finally块之前保留的值。

	```java
	public class tryDemo {
	    public static int show() {
	        int result = 0;
	        try {
	            return result;
	        }finally{
	            System.out.println("finally模块被执行");
	            result = 1;
	        }
	    }
	    public static void main(String args[]) {
	        System.out.println(show());
	    }
	}
	```

	运行结果为：

	```
	finally模块被执行
	0
	```

- 如果return的数据是引用数据类型，而在finally中对该引用数据类型的属性值的改变起作用，try中的return语句返回的就是在finally中改变后的该属性的值。

	```java
	public class TTT {
	
	    public static RetDto show() {
	        RetDto dto = new RetDto();
	        try {
	            dto.name = "try";
	            dto.age = 1;
	            System.out.println("try:" + dto);
	            return dto;
	        } catch (Exception e) {
	            System.out.println("catch:" + dto);
	            return dto;
	        } finally {
	            dto.name = "finally";
	            dto.age = 19;
	            System.out.println("finally:" + dto);
	        }
	    }
	
	    public static void main(String[] args) {
	        System.out.println(show());
	    }
	}
	
	class RetDto {
	    String name;
	    int age;
	
	    @Override
	    public String toString() {
	        return "RetDto{" +
	                "name='" + name + '\'' +
	                ", age=" + age +
	                '}';
	    }
	}
	```

	运行结果为：

	```
	try:RetDto{name='try', age=1}
	finally:RetDto{name='finally', age=19}
	RetDto{name='finally', age=19}
	```




## 8.7、增强的自动资源管理

当程序使用 finally 块关闭资源时，程序会显得异常臃肿，例如以下代码。

```java
public static void main(String[] args) {
    FileInputStream fis = null;
    try {
        fis = new FileInputStream("a.txt");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } finally {
        // 关闭磁盘文件，回收资源
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

Java 7 以前，上面程序中的 finally 代码块是不得不写的“臃肿代码”，为了解决这种问题，Java 7 增加了一个新特性，该特性提供了另外一种管理资源的方式，这种方式能自动关闭文件，被称为自动资源管理（Automatic Resource Management）。该特性是在 try 语句上的扩展，主要释放不再需要的文件或其他资源。

自动资源管理替代了 finally 代码块，并优化了代码结构和提高程序可读性。语法如下：

```java
try (声明或初始化资源语句) {
    // 可能会生成异常语句
} catch(Throwable e1){
    // 处理异常e1
} catch(Throwable e2){
    // 处理异常e1
} catch(Throwable eN){
    // 处理异常eN
}
```

当 try 代码块结束时，自动释放资源。不再需要显式的调用 close() 方法，该形式也称为“带资源的 try 语句”。

注意：

1. try 语句中声明的资源被隐式声明为 final，资源的作用局限于带资源的 try 语句。
2. 可以在一条 try 语句中声明或初始化多个资源，每个资源以`;`隔开即可。
3. 需要关闭的资源必须实现了 AutoCloseable 或 Closeable 接口。

Closeable 是 AutoCloseable 的子接口，Closeable 接口里的 close() 方法声明抛出了 IOException，因此它的实现类在实现 close() 方法时只能声明抛出 IOException 或其子类；AutoCloseable 接口里的 close() 方法声明抛出了 Exception，因此它的实现类在实现 close() 方法时可以声明抛出任何异常。

下面示范如何使用自动关闭资源的 try 语句。

```java
public class AutoCloseTest {
    public static void main(String[] args) throws IOException {
        try (
                // 声明、初始化两个可关闭的资源
                // try语句会自动关闭这两个资源
                BufferedReader br = new BufferedReader(new FileReader("AutoCloseTest.java"));
                PrintStream ps = new PrintStream(new FileOutputStream("a.txt"))) {
            // 使用两个资源
            System.out.println(br.readLine());
            ps.println("Orichalcos");
        }
    }
}
```

上面程序中粗体字代码分别声明、初始化了两个 IO 流，BufferedReader 和 PrintStream 都实现了 Closeable 接口，并在 try 语句中进行了声明和初始化，所以 try 语句会自动关闭它们。

自动关闭资源的 try 语句相当于包含了隐式的 finally 块（这个 finally 块用于关闭资源），因此这个 try 语句可以既没有 catch 块，也没有 finally 块。

Java 7 几乎把所有的“资源类”（包括文件 IO 的各种类、JDBC 编程的 Connection 和 Statement 等接口）进行了改写，改写后的资源类都实现了 AutoCloseable 或 Closeable 接口。

如果程序需要，自动关闭资源的 try 语句后也可以带多个 catch 块和一个 finally 块。

Java 9 再次增强了这种 try 语句。Java 9 不要求在 try 后的圆括号内声明并创建资源，只需要自动关闭的资源有 final 修饰或者是有效的 final (effectively final)，Java 9 允许将资源变量放在 try 后的圆括号内。上面程序在 Java 9 中可改写为如下形式。

```java
public class AutoCloseTest {
    public static void main(String[] args) throws IOException {
        // 有final修饰的资源
        final BufferedReader br = new BufferedReader(new FileReader("AutoCloseTest.java"));
        // 没有显式使用final修饰，但只要不对该变量重新赋值，该变量就是有效的
        final PrintStream ps = new PrintStream(new FileOutputStream("a. txt"));
        // 只要将两个资源放在try后的圆括号内即可
        try (br; ps) {
            // 使用两个资源
            System.out.println(br.readLine());
            ps.println("Orichalcos");
        }
    }
}
```



## 8.8、声明和抛出异常

Java 中的异常处理除了捕获异常和处理异常之外，还包括声明异常和拋出异常。实现声明和抛出异常的关键字非常相似，它们是 throws 和 throw。==可以通过 throws 关键字在方法上声明该方法要拋出的异常，==然后==在方法内部通过 throw 拋出异常对象。==

### 8.8.1、throws 声明异常

当一个方法产生一个它不处理的异常时，那么就需要在该方法的头部声明这个异常，以便将该异常传递到方法的外部进行处理。使用 throws 声明的方法表示此方法不处理异常。throws 具体格式如下：

```java
returnType method_name(paramList) throws Exception 1,Exception2,…{…}
```

其中，returnType 表示返回值类型；method_name 表示方法名；paramList 表示参数列表；Exception 1，Exception2，… 表示异常类。

==如果有多个异常类，它们之间用逗号分隔。==这些异常类可以是方法中调用了可能拋出异常的方法而产生的异常，也可以是方法体中生成并拋出的异常。

使用 throws 声明抛出异常的思路是，当前方法不知道如何处理这种类型的异常，该异常应该由向上一级的调用者处理；如果 main 方法也不知道如何处理这种类型的异常，也可以使用 throws 声明抛出异常，该异常将交给 JVM 处理。JVM 对异常的处理方法是，打印异常的跟踪栈信息，并中止程序运行，这就是前面程序在遇到异常后自动结束的原因。

创建一个 readFile() 方法，该方法用于读取文件内容，在读取的过程中可能会产生 IOException 异常，但是在该方法中不做任何的处理，而将可能发生的异常交给调用者处理。在 main() 方法中使用 try catch 捕获异常，并输出异常信息。代码如下：

```java
public class Test04 {
    public void readFile() throws IOException {
        // 定义方法时声明异常
        FileInputStream file = new FileInputStream("read.txt"); // 创建 FileInputStream 实例对象
        int f;
        while ((f = file.read()) != -1) {
            System.out.println((char) f);
            f = file.read();
        }
        file.close();
    }
    public static void main(String[] args) {
        Throws t = new Test04();
        try {
            t.readFile(); // 调用 readFHe()方法
        } catch (IOException e) {
            // 捕获异常
            System.out.println(e);
        }
    }
}
```

以上代码，首先在定义 readFile() 方法时用 throws 关键字声明在该方法中可能产生的异常，然后在 main() 方法中调用 readFile() 方法，并使用 catch 语句捕获产生的异常。

---

**方法重写时声明抛出异常的限制**

使用 throws 声明抛出异常时有一个限制，是方法重写中的一条规则：==子类方法声明抛出的异常类型应该是父类方法声明抛出的异常类型的子类或相同，子类方法声明抛出的异常不允许比父类方法声明抛出的异常多。==看如下程序。

```java
public class OverrideThrows {
    public void test() throws IOException {
        FileInputStream fis = new FileInputStream("a.txt");
    }
}

class Sub extends OverrideThrows {
    // 子类方法声明抛出了比父类方法更大的异常
    // 所以下面方法出错
    public void test() throws Exception {
    }
}
```

上面程序中 Sub 子类中的 test() 方法声明抛出 Exception，该 Exception 是其父类声明抛出异常 IOException 类的父类，这将导致程序无法通过编译。

所以在编写类继承代码时要注意，子类在重写父类带 throws 子句的方法时，子类方法声明中的 throws 子句不能出现父类对应方法的 throws 子句中没有的异常类型，因此 throws 子句可以限制子类的行为。也就是说，子类方法拋出的异常不能超过父类定义的范围。



### 8.8.2、throw 拋出异常

与 throws 不同的是，==throw 语句用来直接拋出一个异常，==后接一个可拋出的异常类对象，其语法格式如下：

```java
throw ExceptionObject;
```

其中，ExceptionObject 必须是 Throwable 类或其子类的对象。如果是自定义异常类，也必须是 Throwable 的直接或间接子类。例如，以下语句在编译时将会产生语法错误：

```java
throw new String("拋出异常");    // String类不是Throwable类的子类
```

当 throw 语句执行时，它后面的语句将不执行，此时程序转向调用者程序，寻找与之相匹配的 catch 语句，执行相应的异常处理程序。如果没有找到相匹配的 catch 语句，则再转向上一层的调用程序。这样逐层向上，直到最外层的异常处理程序终止程序并打印出调用栈情况。

throw 关键字不会单独使用，它的使用完全符合异常的处理机制，但是，一般来讲用户都在避免异常的产生，所以不会手工抛出一个新的异常类的实例，而往往会抛出程序中已经产生的异常类的实例。

在某仓库管理系统中，要求管理员的用户名需要由 8 位以上的字母或者数字组成，不能含有其他的字符。当长度在 8 位以下时拋出异常，并显示异常信息；当字符含有非字母或者数字时，同样拋出异常，显示异常信息。代码如下：

```java
public class Test05 {
    public boolean validateUserName(String username) {
        boolean con = false;
        if (username.length() > 8) {
            // 判断用户名长度是否大于8位
            for (int i = 0; i < username.length(); i++) {
                char ch = username.charAt(i); // 获取每一位字符
                if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                    con = true;
                } else {
                    con = false;
                    throw new IllegalArgumentException("用户名只能由字母和数字组成！");
                }
            }
        } else {
            throw new IllegalArgumentException("用户名长度必须大于 8 位！");
        }
        return con;
    }
    
    public static void main(String[] args) {
        Test05 te = new Test05();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = input.next();
        try {
            boolean con = te.validateUserName(username);
            if (con) {
                System.out.println("用户名输入正确！");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
```

如上述代码，在 validateUserName() 方法中两处拋出了 IllegalArgumentException 异常，即当用户名字符含有非字母或者数字以及长度不够 8 位时。在 main() 方法中，调用了 validateUserName() 方法，并使用 catch 语句捕获该方法可能拋出的异常。

运行程序，当用户输入的用户名包含非字母或者数字的字符时，程序输出异常信息，如下所示。

```
请输入用户名：
administrator@#
java.lang.IllegalArgumentException: 用户名只能由字母和数字组成！
```

当用户输入的用户名长度不够 8 位时，程序同样会输出异常信息，如下所示。

```
请输入用户名：
admin
java.lang.IllegalArgumentException: 用户名长度必须大于 8 位！
```

---

**throws 关键字和 throw 关键字在使用上的几点区别如下**：

- throws 用来声明一个方法可能抛出的所有异常信息，表示出现异常的一种可能性，但并不一定会发生这些异常；throw 则是指拋出的一个具体的异常类型，执行 throw 则一定抛出了某种异常对象。
- 通常在一个方法（类）的声明处通过 throws 声明方法（类）可能拋出的异常信息，而在方法（类）内部通过 throw 声明一个具体的异常信息。
- throws 通常不用显示地捕获异常，可由系统自动将所有捕获的异常信息抛给上级方法； throw 则需要用户自己捕获相关的异常，而后再对其进行相关包装，最后将包装后的异常信息抛出。



## 8.9、多异常捕获

多 catch 代码块虽然客观上提高了程序的健壮性，但是也导致了程序代码量大大增加。如果有些异常种类不同，但捕获之后的处理是相同的，例如以下代码。

```java
try{
    // 可能会发生异常的语句
} catch (FileNotFoundException e) {
    // 调用方法methodA处理
} catch (IOException e) {
    // 调用方法methodA处理
} catch (ParseException e) {
    // 调用方法methodA处理
}
```

3 个不同类型的异常，要求捕获之后的处理都是调用 methodA 方法。为了解决这种问题，Java 7 推出了多异常捕获技术，可以把这些异常合并处理。上述代码修改如下：

```java
try{
    // 可能会发生异常的语句
} catch (IOException | ParseException e) {
    // 调用方法methodA处理
}
```

注意：由于 FileNotFoundException 属于 IOException 异常，IOException 异常可以捕获它的所有子类异常。所以不能写成 `FileNotFoundException | IOException | ParseException` 。

使用一个 catch 块捕获多种类型的异常时需要注意如下两个地方。

- 捕获多种类型的异常时，多种异常类型之间用竖线`|`隔开。
- 捕获多种类型的异常时，异常变量有隐式的 final 修饰，因此程序不能对异常变量重新赋值。

下面程序示范了 Java 7 提供的多异常捕获。

```java
public class ExceptionTest {
    public static void main(String[] args) {
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = a / b;
            System.out.println("您输入的两个数相除的结果是：" + c);
        } catch (IndexOutOfBoundsException | NumberFormatException | ArithmeticException e) {
            System.out.println("程序发生了数组越界、数字格式异常、算术异常之一");
            // 捕获多异常时，异常变量默认有final修饰
            // 所以下面代码有错
            e = new ArithmeticException("test");
        } catch (Exception e) {
            System.out.println("未知异常");
            // 捕获一种类型的异常时，异常变量没有final修饰
            // 所以下面代码完全正确
            e = new RuntimeException("test");
        }
    }
}
```

上面程序中第一行粗体字代码使用了`IndexOutOfBoundsException|NumberFormatException|ArithmeticException`来定义异常类型，这就表明该 catch 块可以同时捕获这 3 种类型的异常。捕获多种类型的异常时，异常变量使用隐式的 final 修饰，因此上面程序的第 12 行代码将产生编译错误；捕获一种类型的异常时，异常变量没有 final 修饰，因此上面程序的第 17 行代码完全正确。



## 8.10、自定义异常

如果 Java 提供的内置异常类型不能满足程序设计的需求，这时我们可以自己设计 Java 类库或框架，其中包括异常类型。实现自定义异常类需要继承 Exception 类或其子类，如果自定义运行时异常类需继承 RuntimeException 类或其子类。

自定义异常的语法形式为：

```java
<class><自定义异常名><extends><Exception>
```

在编码规范上，一般将自定义异常类的类名命名为 XXXException，其中 XXX 用来代表该异常的作用。

==自定义异常类一般包含两个构造方法：一个是无参的默认构造方法，另一个构造方法以字符串的形式接收一个定制的异常消息，并将该消息传递给超类的构造方法。==

例如，以下代码创建一个名称为 IntegerRangeException 的自定义异常类：

```java
class IntegerRangeException extends Exception {
    public IntegerRangeException() {
        super();
    }
    public IntegerRangeException(String s) {
        super(s);
    }
}
```

以上代码创建的自定义异常类 IntegerRangeException 类继承自 Exception 类，在该类中包含两个构造方法。

==提示：因为自定义异常继承自 Exception 类，因此自定义异常类中包含父类所有的属性和方法。==



## 8.11、异常处理规则

成功的异常处理应该实现如下 4 个目标。

1. 使程序代码混乱最小化。
2. 捕获并保留诊断信息。
3. 通知合适的人员。
4. 采用合适的方式结束异常活动。

---

**不要过度使用异常**

不可否认，Java 的异常机制确实方便，但滥用异常机制也会带来一些负面影响。过度使用异常主要有以下两个方面：

1. 把异常和普通错误混淆在一起，不再编写任何错误处理代码，而是以简单地抛出异常来代替所有的错误处理。
2. 使用异常处理来代替流程控制。

熟悉了异常使用方法后，程序员可能不再愿意编写烦琐的错误处理代码，而是简单地抛出异常。实际上这样做是不对的，对于完全已知和普通的错误，应该编写处理这种错误处理代码，增加程序的健壮性；只有对外部的、不能确定和预知的运行时错误才使用异常。

异常处理机制的效率比正常的流程控制效率差，所以不要使用异常处理来代替正常的程序流程控制。例如，对于以下代码：

```java
// 定义一个字符串数组
String[] arr = { "Hello", "Java", "Spring" };
// 使用异常处理来遍历arr数组的每个元素
try {
    int i = 0;
    while (true) {
        System.out.println(arr[i++]);
    }
} catch (ArrayIndexOutOfBoundsException ae) {
}
```

运行上面程序确实可以实现遍历 arr 数组元素的功能，但这种写法可读性较差，而且运行效率也不高。程序完全有能力避免产生 ArrayIndexOutOfBoundsException 异常，程序“故意”制造这种异常，然后使用 catch 块去捕获该异常，这是不应该的。将程序改为如下形式肯定要好得多。

```java
String arr[] = {"Hello","Java","Spring"};
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

异常只应该用于处理非正常的情况，不要使用异常处理来代替正常的流程控制。对于一些完全可预知，而且处理方式清楚的错误，程序应该提供相应的错误处理代码，而不是将其笼统地称为异常。

---

**不要使用过于庞大的try块**

在 try 块里放置大量的代码，这样看上去“很简单“，但这种”简单“只是一种假象，因为 try 块里的代码过于庞大，业务过于复杂，就会造成 try 块中出现异常的可能性大大增加，从而导致分析异常原因的难度也大大增加。而且当 try 块过于庞大时，就难免在 try 块后紧跟大量的 catch 块才可以针对不同的异常提供不同的处理逻辑。同一个 try 块后紧跟大量的 catch 块则需要分析它们之间的逻辑关系，反而增加了变成复杂度。

正确的做法是，把大块的 try 块分割成多个可能出现异常的程序段落，并把它们放在单独的 try 块中，从而分别捕获并处理异常。

---

**避免使用 Catch All 语句**

所谓 Catch All 语句指的是一种异常捕获模块，它可以处理程序发生的所有可能异常。例如，如下代码片段：

```java
try {
    // 可能引发Checked异常的代码
} catch (Throwsble t) {
    // 进行异常处理
    t.printStackTrace();
}
```

不可否认，每个程序员都曾经用过这种异常处理方式，但在编写关键程序时就应避免使用这种异常处理方式。这种处理方式有如下两点不足之处。

1. 所有的异常都采用相同的处理方式，这将导致无法对不同的异常分情况处理，如果要分情况处理，则需要在 catch 块中使用分支语句进行控制，这是得不偿失的做法。
2. 这种捕获方式可能将程序中的错误、Runtime 异常等可能导致程序终止的情况全部捕获到，从而“压制”了异常。如果出现了一些“关键”异常，那么此异常也会被“静悄悄”地忽略。

实际上，Catch All 语句不过是一种通过避免错误处理而加快编程进度的机制，应尽量避免在实际应用中使用这种语句。

---

**不要忽略捕获到的异常**

不要忽略异常，既然已捕获到异常，那 catch 块理应处理并修复这个错误。catch 块整个为空，或者仅仅打印出错信息都是不妥的。程序出了错误，所有的人都看不到任何异常，但整个应用可能已经彻底坏了，这是最可怕的事情。

对异常进行合适的修复，然后绕过异常发生的地方继续执行；或者用别的数据进行计算，以代替期望的方法返回值；或者提示用户重新操作等。总之，对于 Checked 异常，程序应该尽量修复。



## 8.12、异常跟踪栈

异常对象的 printStackTrace() 方法用于打印异常的跟踪栈信息，根据 printStackTrace() 方法的输出结果，开发者可以找到异常的源头，并跟踪到异常一路触发的过程。

看下面用于测试 printStackTrace 的例子程序。

```java
class SelfException extends RuntimeException {
    SelfException() {
    }
    
    SelfException(String msg) {
        super(msg);
    }
}

public class PrintStackTraceTest {
    public static void main(String[] args) {
        firstMethod();
    }
    
    public static void firstMethod() {
        secondMethod();
    }
    
    public static void secondMethod() {
        thirdMethod();
    }
    
    public static void thirdMethod() {
        throw new SelfException("自定义异常信息");
    }
}
```

上面程序中 main 方法调用 firstMethod，firstMethod 调用 secondMethod，secondMethod 调用 thirdMethod，thirdMethod 直接抛出一个 SelfException 异常。运行上面程序，会看到如下所示的结果。

```
Exception in thread "main" Test.SelfException: 自定义异常信息
        at Test.PrintStackTraceTest.thirdMethod(PrintStackTraceTest.java:26)
        at Test.PrintStackTraceTest.secondMethod(PrintStackTraceTest.java:22)
        at Test.PrintStackTraceTest.firstMethod(PrintStackTraceTest.java:18)
        at Test.PrintStackTraceTest.main(PrintStackTraceTest.java:14)
```

上面运行结果的第 2 行到第 5 行之间的内容是异常跟踪栈信息，从打印的异常信息我们可以看出，异常从 thirdMethod 方法开始触发，传到 secondMethod 方法，再传到 firstMethod 方法，最后传到 main 方法，在 main 方法终止，这个过程就是 Java 的异常跟踪栈。

在面向对象的编程中，大多数复杂操作都会被分解成一系列方法调用。这是因为实现更好的可重用性，将每个可重用的代码单元定义成方法，将复杂任务逐渐分解为更易管理的小型子任务。由于一个大的业务功能需要由多个对象来共同实现，在最终编程模型中，很多对象将通过一系列方法调用来实现通信，执行任务。

所以，面向对象的应用程序运行时，经常会发生一系列方法调用，从而形成“方法调用栈”，异常的传播则相反：只要异常没有被完全捕获（包括异常没有被捕获，或异常被处理后重新抛出了新异常），异常从发生异常的方法逐渐向外传播，首先传给该方法的调用者，该方法调用者再次传给其调用者……，直至最后传到 main 方法，如果 main 方法依然没有处理该异常，则 JVM 会中止该程序，并打印异常的跟踪栈信息。

很多初学者一看到上面运行结果的异常提示信息，就会惊慌失措，其实结果中的异常跟踪栈信息非常清晰，它记录了应用程序中执行停止的各个点。

异常跟踪栈信息的第一行一般详细显示异常的类型和异常的详细消息，接下来是所有异常的发生点，各行显示被调用方法中执行的停止位置，并标明类、类中的方法名、与故障点对应的文件的行。一行行地往下看，跟踪栈总是最内部的被调用方法逐渐上传，直到最外部业务操作的起点，通常就是程序的入口 main 方法或 Thread 类的 run 方法（多线程的情形）。

下面例子程序示范了多线程程序中发生异常的情形。

```java
public class ThreadExceptionTest implements Runnable {
    
    public void run() {
        firstMethod();
    }
    
    public void firstMethod() {
        secondMethod();
    }
    
    public void secondMethod() {
        int a = 5;
        int b = 0;
        int c = a / b;
    }
    
    public static void main(String[] args) {
        new Thread(new ThreadExceptionTest()).start();
    }
}
```

运行上面程序，会看到如下运行结果。

```
Exception in thread "Thread-0" java.lang.ArithmeticException: / by zero
            at Test.ThreadExceptionTest.secondMethod(ThreadExceptionTest.java:14)
            at Test.ThreadExceptionTest.firstMethod(ThreadExceptionTest.java:8)
            at Test.ThreadExceptionTest.run(ThreadExceptionTest.java:4)
            at java.lang.Thread.run(Unknown Source)
```

多线程异常的跟踪栈，从发生异常的方法开始，到线程的 run 方法结束。从上面的运行结果可以看出，程序在 Thread 的 run 方法中出现了 ArithmeticException 异常，这个异常的源头是 ThreadExcetpionTest 的 secondMethod 方法，位于 ThreadExcetpionTest.java 文件的 14 行。这个异常传播到 Thread 类的 run 方法就会结束（如果该异常没有得到处理，将会导致该线程中止运行）。

前面已经讲过，调用 Exception 的 printStackTrace() 方法就是打印该异常的跟踪栈信息，也就会看到上面两个示例运行结果中的信息。当然，如果方法调用的层次很深，将会看到更加复杂的异常跟踪栈。

提示：虽然 printStackTrace() 方法可以很方便地用于追踪异常的发生情况，可以用它来调试程序，但在最后发布的程序中，应该避免使用它。应该对捕获的异常进行适当的处理，而不是简单地将异常的跟踪栈信息打印出来。





# 10、多线程

Java 给多线程编程提供了内置的支持。 一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。

多线程是多任务的一种特别的形式，但多线程使用了更小的资源开销；多线程能满足程序员编写高效率的程序来达到充分利用 CPU 的目的。

---

这里定义和线程相关的另一个术语 - 进程，进程和线程有什么区别呢？

CPU+RAM+各种资源（比如显卡，光驱，键盘，GPS, 等等外设）构成我们的电脑，但是电脑的运行，实际就是CPU和相关寄存器以及RAM之间的事情。

一个最最基础的事实：CPU太快，太快，太快了，寄存器仅仅能够追的上他的脚步，RAM和别的挂在各总线上的设备完全是望尘莫及。那当多个任务要执行的时候怎么办呢？轮流着来?或者谁优先级高谁来？不管怎么样的策略，一句话就是在CPU看来就是轮流着来。

一个必须知道的事实：执行一段程序代码，实现一个功能的过程介绍 ，当得到CPU的时候，相关的资源必须也已经就位，就是显卡啊，GPS啊什么的必须就位，然后CPU开始执行。这里除了CPU以外所有的就构成了这个程序的执行环境，也就是我们所定义的程序上下文。当这个程序执行完了，或者分配给他的CPU执行时间用完了，那它就要被切换出去，等待下一次CPU的临幸。在被切换出去的最后一步工作就是保存程序上下文，因为这个是下次他被CPU临幸的运行环境，必须保存。

串联起来的事实：前面讲过在CPU看来所有的任务都是一个一个的轮流执行的，具体的轮流方法就是：先加载程序A的上下文，然后开始执行A，保存程序A的上下文，调入下一个要执行的程序B的程序上下文，然后开始执行B,保存程序B的上下文...

进程和线程就是这样的背景出来的，两个名词不过是对应的CPU时间段的描述，名词就是这样的功能。

**进程就是包换上下文切换的程序执行时间总和** = **CPU加载上下文+CPU执行+CPU保存上下文**

进程的颗粒度太大，每次都要有上下文的调入，保存，调出。如果我们把进程比喻为一个运行在电脑上的软件，那么一个软件的执行不可能是一条逻辑执行的，必定有多个分支和多个程序段，就好比要实现程序A，实际分成 a，b，c等多个块组合而成。那么这里具体的执行就可能变成：

程序A得到CPU => CPU加载上下文，开始执行程序A的a小段，然后执行A的b小段，然后再执行A的c小段，最后CPU保存A的上下文。

这里a，b，c的执行是共享了A的上下文，CPU在执行的时候没有进行上下文切换的。这里的a，b，c就是线程，也就是说线程是共享了进程的上下文环境的更为细小的CPU时间段。

做个简单的比喻：进程=火车，线程=车厢

- 线程在进程下行进（单纯的车厢无法运行）
- 一个进程可以包含多个线程（一辆火车可以有多个车厢）
- 不同进程间数据很难共享（一辆火车上的乘客很难换到另外一辆火车，比如站点换乘）
- 同一进程下不同线程间数据很易共享（A车厢换到B车厢很容易）
- 进程要比线程消耗更多的计算机资源（采用多列火车相比多个车厢更耗资源）
- 进程间不会相互影响，一个线程挂掉将导致整个进程挂掉（一列火车不会影响到另外一列火车，但是如果一列火车上中间的一节车厢着火了，将影响到所有车厢）
- 进程可以拓展到多机，进程最多适合多核（不同火车可以开在多个轨道上，同一火车的车厢不能在行进的不同的轨道上）
- 进程使用的内存地址可以上锁，即一个线程使用某些共享内存时，其他线程必须等它结束，才能使用这一块内存。（比如火车上的洗手间）－"互斥锁"
- 进程使用的内存地址可以限定使用量（比如火车上的餐厅，最多只允许多少人进入，如果满了需要在门口等，等有人出来了才能进去）－“信号量”

---

并行和并发：

- 并行是值两个或多个事件在同一时刻发生，并发是指两个或多个事件在同时时间间隔发生。
- 并行是不同实体上的多个事件，并发是同一实体上的多个事件。

在同一台机器上 “同时” 处理多个任务，在多台处理器上 “同时” 处理多个任务。如 Hadoop 分布式集群；所以并发编程的目标是充分利用处理器的每一个核，以达到最高的处理性能。



## 10.1、线程的生命周期

线程是一个动态执行的过程，它也有一个从产生到死亡的过程。下图显示了一个线程完整的生命周期。

<img src="../Images/Java/java-thread.jpg" alt="img" style="zoom: 50%;" />

- 新建状态:

	使用 **new** 关键字和 **Thread** 类或其子类建立一个线程对象后，该线程对象就处于新建状态。它保持这个状态直到程序 **start()** 这个线程。

- 就绪状态:

	当线程对象调用了start()方法之后，该线程就进入就绪状态。就绪状态的线程处于就绪队列中，要等待JVM里线程调度器的调度。

- 运行状态:

	如果就绪状态的线程获取 CPU 资源，就可以执行 **run()**，此时线程便处于运行状态。处于运行状态的线程最为复杂，它可以变为阻塞状态、就绪状态和死亡状态。

- 阻塞状态:

	如果一个线程执行了sleep（睡眠）、suspend（挂起）等方法，失去所占用资源之后，该线程就从运行状态进入阻塞状态。在睡眠时间已到或获得设备资源后可以重新进入就绪状态。可以分为三种：

	- 等待阻塞：运行状态中的线程执行 wait() 方法，使线程进入到等待阻塞状态。
	- 同步阻塞：线程在获取 synchronized 同步锁失败(因为同步锁被其他线程占用)。
	- 其他阻塞：通过调用线程的 sleep() 或 join() 发出了 I/O 请求时，线程就会进入到阻塞状态。当sleep() 状态超时，join() 等待线程终止或超时，或者 I/O 处理完毕，线程重新转入就绪状态。

- 死亡状态:

	一个运行状态的线程完成任务或者其他终止条件发生时，该线程就切换到终止状态。

---

**run() 和 start() 的区别**

每个线程都是通过某个特定 Thread 对象所对应的 run() 方法来完成其操作的，方法 run() 称为线程体，通过调用 Thread 类的 start() 方法来启动一个线程。

start() 方法来启动一个线程，真正实现了多线程运行。这时无需等待 run() 方法体代码执行完毕，可以继续执行下面的代码；这时线程处于就绪状态，并没有运行。当此 Thread 类获得 CPU 资源后调用方法 run() 来完成其运行状态，run() 方法包含了要执行的这个线程的内容，run() 方法执行结束，此线程终止。

run() 方法是在本线程里的，只是线程里的一个函数，而不是多线程的。如果直接调用 run()，其实就相当于是调用了一个普通的函数而已，直接调用 run() 方法必须等待 run() 方法执行完毕才能执行下面的代码，所以执行线路只有一条，根本就没有线程的特征，所以在多线程执行时要使用 start() 方法而不是 run() 方法。

一个线程对象的 start() 方法只能调用一次，多次调用会抛出 java.lang.IllegalThreadStateException 异常；run() 方法没有限制。

---

==守护线程（即daemon thread），是个服务线程，准确地来说就是服务其他的线程。==



## 10.2、线程的优先级

每一个 Java 线程都有一个优先级，这样有助于操作系统确定线程的调度顺序。

Java 线程的优先级是一个整数，其取值范围是 1 （Thread.MIN_PRIORITY ） - 10 （Thread.MAX_PRIORITY ）。

默认情况下，每一个线程都会分配一个优先级 NORM_PRIORITY（5）。

具有较高优先级的线程对程序更重要，并且应该在低优先级的线程之前分配处理器资源。但是，线程优先级不能保证线程执行的顺序，而且非常依赖于平台。



## 10.3、创建一个线程

Java 提供了三种创建线程的方法：

- 通过实现 Runnable 接口；
- 通过继承 Thread 类本身；
- 通过 Callable 和 Future 创建线程。



### 10.3.1、实现Runnable

创建一个线程，最简单的方法是创建一个实现 Runnable 接口的类。

为了实现 Runnable，一个类需要重写run()方法。

在创建一个实现 Runnable 接口的类之后，可以在类中实例化一个线程对象。

Thread 定义了几个构造方法，下面的这个是经常使用的：

```java
Thread(Runnable threadOb,String threadName);
```

这里，threadOb 是一个实现 Runnable 接口的类的实例，并且 threadName 指定新线程的名字。

新线程创建之后，调用它的 start() 方法它才会运行。

下面是一个创建线程并开始让它执行的实例：

```java
class RunnableDemo implements Runnable {
   private Thread t;
   private String threadName;
   
   RunnableDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() {
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // 让线程睡眠一会
            Thread.sleep(50);
         }
      }catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}
 
public class TestThread {
 
   public static void main(String args[]) {
      RunnableDemo R1 = new RunnableDemo( "Thread-1");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Thread-2");
      R2.start();
   }   
}
```

编译以上程序运行结果如下：

```
Creating Thread-1
Starting Thread-1
Creating Thread-2
Starting Thread-2
Running Thread-1
Thread: Thread-1, 4
Running Thread-2
Thread: Thread-2, 4
Thread: Thread-1, 3
Thread: Thread-2, 3
Thread: Thread-1, 2
Thread: Thread-2, 2
Thread: Thread-1, 1
Thread: Thread-2, 1
Thread Thread-1 exiting.
Thread Thread-2 exiting.
```



### 10.3.2、继承Thread

创建一个线程的第二种方法是创建一个新的类，该类继承 Thread 类，然后创建一个该类的实例。

继承类必须重写 run() 方法，该方法是新线程的入口点。它也必须调用 start() 方法才能执行。

该方法尽管被列为一种多线程实现方式，但是本质上也是实现了 Runnable 接口的一个实例。

```java
class ThreadDemo extends Thread {
   private Thread t;
   private String threadName;
   
   ThreadDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() {
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // 让线程睡眠一会
            Thread.sleep(50);
         }
      }catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}
 
public class TestThread {
 
   public static void main(String args[]) {
      ThreadDemo T1 = new ThreadDemo( "Thread-1");
      T1.start();
      
      ThreadDemo T2 = new ThreadDemo( "Thread-2");
      T2.start();
   }   
}
```

编译以上程序运行结果如下：

```
Creating Thread-1
Starting Thread-1
Creating Thread-2
Starting Thread-2
Running Thread-1
Thread: Thread-1, 4
Running Thread-2
Thread: Thread-2, 4
Thread: Thread-1, 3
Thread: Thread-2, 3
Thread: Thread-1, 2
Thread: Thread-2, 2
Thread: Thread-1, 1
Thread: Thread-2, 1
Thread Thread-1 exiting.
Thread Thread-2 exiting.
```



### 10.3.3、通过 Callable、Future

在java的多线程开发中Runnable一直以来都是多线程的核心，而Callable是java1.5添加进来的一个增强版本。

- Runnable接口中的run()方法的返回值是void，它做的事情只是纯粹地去执行run()方法中的代码而已；
- Callable接口中的call()方法是有返回值的，是一个泛型，和Future、FutureTask配合可以用来获取异步执行的结果。

---

使用 Callable 和 Future 创建线程：

1. 创建 Callable 接口的实现类，并实现 call() 方法，该 call() 方法将作为线程执行体，并且有返回值。
2. 创建 Callable 实现类的实例，使用 FutureTask 类来包装 Callable 对象，该 FutureTask 对象封装了该 Callable 对象的 call() 方法的返回值。
3. 使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程。
4. 调用 FutureTask 对象的 get() 方法来获得子线程执行结束后的返回值。

```java
public class CallableThreadTest implements Callable<Integer> {
    public static void main(String[] args)  
    {  
        CallableThreadTest ctt = new CallableThreadTest();  
        FutureTask<Integer> ft = new FutureTask<>(ctt);  
        for(int i = 0;i < 100;i++)  
        {  
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);  
            if(i==20)  
            {  
                new Thread(ft,"有返回值的线程").start();  
            }  
        }  
        try  
        {  
            System.out.println("子线程的返回值："+ft.get());  
        } catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        } catch (ExecutionException e)  
        {  
            e.printStackTrace();  
        }  
  
    }
    @Override  
    public Integer call() throws Exception  
    {  
        int i = 0;  
        for(;i<100;i++)  
        {  
            System.out.println(Thread.currentThread().getName()+" "+i);  
        }  
        return i;  
    }  
}
```



## 10.4、Thread 方法

| 方法                                 | 描述                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| void start()                         | 使该线程开始执行；**Java** 虚拟机调用该线程的 run 方法。     |
| void run()                           | 如果该线程是使用独立的 Runnable 运行对象构造的，则调用该 Runnable 对象的 run 方法；否则，该方法不执行任何操作并返回。 |
| final void setName(String name)      | 改变线程名称，使之与参数 name 相同。                         |
| final void setPriority(int priority) | 更改线程的优先级。                                           |
| final void setDaemon(boolean on)     | 将该线程标记为守护线程或用户线程。                           |
| final void join(long millisec)       | 等待该线程终止的时间最长为 millis 毫秒。                     |
| void interrupt()                     | 中断线程。                                                   |
| final boolean isAlive()              | 测试线程是否处于活动状态。                                   |
| static void yield()                  | 暂停当前正在执行的线程对象，并执行其他线程。                 |
| static void sleep(long millisec)     | 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响。 |
| static boolean holdsLock(Object x)   | 当且仅当当前线程在指定的对象上保持监视器锁时，才返回 true。  |
| static Thread currentThread()        | 返回对当前正在执行的线程对象的引用。                         |
| static void dumpStack()              | 将当前线程的堆栈跟踪打印至标准错误流。                       |



## 10.5、烧水泡茶

在日常生活中，我们喝茶之前，必须完成完成烧开水，清洗茶杯，以及泡茶过程才能喝到清香的茶水。如果一项项的去完成的话，就会浪费很多时间。我们知道，在烧水的过程中，我们完全可以一边清洗茶杯，这样就能节约更多的时间了。

```java
public class Test {
    public static void main(String[] args) {
        HeatUpWaterRunnable heatUpWaterRunnable = new HeatUpWaterRunnable();
        WashTheCupRunnable washTheCupRunnable = new WashTheCupRunnable();
        Thread heatWaterThread = new Thread(heatUpWaterRunnable);
        Thread washTheCupThread = new Thread(washTheCupRunnable);
        heatWaterThread.start();
        washTheCupThread.start();
        try {
            heatWaterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            washTheCupThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("泡茶");
    }
}

class HeatUpWaterRunnable extends Thread {
    @Override
    public void run() {
        System.out.println("开始烧水");
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("水烧开了");
    }
}

class WashTheCupRunnable extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("开始洗第" + i + "个杯子");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第" + i + "个杯子洗完了");
        }
        System.out.println("杯子洗完了");
    }
}
```

运行结果如下：

```
开始烧水
开始洗第1个杯子
第1个杯子洗完了
开始洗第2个杯子
第2个杯子洗完了
开始洗第3个杯子
第3个杯子洗完了
开始洗第4个杯子
水烧开了
第4个杯子洗完了
开始洗第5个杯子
第5个杯子洗完了
杯子洗完了
泡茶
```



## 10.6、join()、yield()、interrupt()

### 10.6.1、join()

线程的合并指的是：将指定的线程加入到当前的线程之中，可以将两个交替执行的线程合并为顺序执行的线程，如果在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。

```java
public class JoinTest {

    public static void main(String[] args) {
        try {
            ThreadA t1 = new ThreadA("t1"); // 新建“线程t1”

            t1.start();   // 启动“线程t1”
            t1.join();   // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”
            System.out.printf("%s finish\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.printf("%s start\n", this.getName());

            // 延时操作
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s finish\n", this.getName());
        }
    }
}
```

运行结果：

```
t1 start
t1 finish
main finish
```

流程图

<img src="../Images/Java/20190816110104907.png" alt="在这里插入图片描述" style="zoom:80%;" />



### 10.6.2、yield()

暂停当前线程，以便其他线程有机会执行，不过不能指定暂停的时间，并且也不能保证当前线程马上停止。

作用：

1. 使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。cpu会从众多的可执行态里选择，也就是说，当前也就是刚刚的那个线程还是有可能会被再次执行到的，并不是说一定会执行其他线程而该线程在下一次中不会执行到了。
2. 用了yield方法后，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
3. 通过yield方法来实现两个线程的交替执行。不过请注意：这种交替并不一定能得到保证。
4. yield()只是使当前线程重新回到可执行状态，所有执行yield()的线程有可能在进入到可执行状态后马上又被执行，所以yield()方法只能使同优先级的线程有执行的机会



### 10.6.3、interrupt()

首先，一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止。所以，Thread.stop, Thread.suspend, Thread.resume 都已经被废弃了。而 Thread.interrupt 的作用其实也不是中断线程，而是「通知线程应该中断了」，具体到底中断还是继续运行，应该由被通知的线程自己处理。

具体来说，当对一个线程，调用 interrupt() 时：

1. 如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。仅此而已。
2. 如果线程处于正常活动状态，那么会将该线程的中断标志设置为 true，仅此而已。被设置中断标志的线程将继续正常运行，不受影响。

interrupt() 并不能真正的中断线程，需要被调用的线程自己进行配合才行。也就是说，一个线程如果有被中断的需求，那么就可以这样做。

- 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程。
- 在调用阻塞方法时正确处理InterruptedException异常。（例如，catch异常后就结束线程。）

---

**中断的相关方法：**

- public void interrupt() 将调用者线程的中断状态设为true。
- public boolean isInterrupted() 判断调用者线程的中断状态。
- public static boolean interrupted 只能通过Thread.interrupted()调用。 它会做两步操作：返回当前线程的中断状态；将当前线程的中断状态设为false。

---

**如何使用中断？**

要使用中断，首先需要在可能会发生中断的线程中不断监听中断状态，一旦发生中断，就执行相应的中断处理代码。
当需要中断线程时，调用该线程对象的interrupt函数即可。

1. 设置中断监听

	```java
	Thread t1 = new Thread( new Runnable(){
	    public void run(){
	        // 若未发生中断，就正常执行任务
	        while(!Thread.currentThread.isInterrupted()){
	            // 正常任务代码……
	        }
	
	        // 中断的处理代码……
	        doSomething();
	    }
	} ).start();
	```

	正常的任务代码被封装在while循环中，每次执行完一遍任务代码就检查一下中断状态；一旦发生中断，则跳过while循环，直接执行后面的中断处理代码。

2. 触发中断

	```java
	t1.interrupt();
	```

	上述代码执行后会将t1对象的中断状态设为true，此时t1线程的正常任务代码执行完成后，进入下一次while循环前Thread.currentThread.isInterrupted()的结果为true，此时退出循环，执行循环后面的中断处理代码。

stop函数停止线程过于暴力，它会立即停止线程，不给任何资源释放的余地。而上述方法较为安全，因为一条线程发出终止信号后，接收线程并不会立即停止，而是将本次循环的任务执行完，再跳出循环停止线程。此外，程序员又可以在跳出循环后添加额外的代码进行收尾工作。



## 10.7、synchronized

当程序中可能出现并发的情况时，我们就需要通过一定的手段来保证在并发情况下数据的准确性，通过这种手段保证了当前用户和其他用户一起操作时，所得到的结果和他单独操作时的结果是一样的。这种手段就叫做并发控制。并发控制的目的是保证一个用户的工作不会对另一个用户的工作产生不合理的影响。

线程安全是并发编程中的重要关注点，应该注意到的是，造成线程安全问题的主要诱因有两点，一是存在共享数据(也称临界资源)，二是存在多条线程共同操作共享数据。

因此为了解决这个问题，我们可能需要这样一个方案，当存在多个线程操作共享数据时，需要保证同一时刻有且只有一个线程在操作共享数据，其他线程必须等到该线程处理完数据后再进行，这种方式有个高尚的名称叫互斥锁，即能达到互斥访问目的的锁，也就是说当一个共享数据被当前正在访问的线程加上互斥锁后，在同一个时刻，其他线程只能处于等待的状态，直到当前线程处理完毕释放该锁。

在 Java 中，关键字 synchronized可以保证在同一个时刻，只有一个线程可以执行某个方法或者某个代码块(主要是对方法或者代码块中存在共享数据的操作)，同时我们还应该注意到synchronized另外一个重要的作用，synchronized可保证一个线程的变化(主要是共享数据的变化)被其他线程所看到（保证可见性，完全可以替代Volatile功能），这点确实也是很重要的。

synchronized关键字最主要有以下3种应用方式：

- 修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
- 修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
- 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。

### 11.7.1、修饰实例方法

所谓的实例对象锁就是用synchronized修饰实例对象中的实例方法，注意是实例方法不包括静态方法，如下

```java
public class AccountingSync implements Runnable{
    //共享资源(临界资源)
    static int i=0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        AccountingSync instance=new AccountingSync();
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
    /**
     * 输出结果:
     * 2000000
     */
}
```

上述代码中，我们开启两个线程操作同一个共享资源即变量i，由于i++;操作并不具备原子性，该操作是先读取值，然后写回一个新值，相当于原来的值加上1，分两步完成，如果第二个线程在第一个线程读取旧值和写回新值期间读取i的域值，那么第二个线程就会与第一个线程一起看到同一个值，并执行相同值的加1操作，这也就造成了线程安全失败，因此对于increase方法必须使用synchronized修饰，以便保证线程安全。

此时我们应该注意到synchronized修饰的是实例方法increase，在这样的情况下，当前线程的锁便是实例对象instance，注意Java中的线程同步锁可以是任意对象。从代码执行结果来看确实是正确的，倘若我们没有使用synchronized关键字，其最终输出结果就很可能小于2000000，这便是synchronized关键字的作用。

这里我们还需要意识到，当一个线程正在访问一个对象的 synchronized 实例方法，那么其他线程不能访问该对象的其他 synchronized 方法，毕竟一个对象只有一把锁，当一个线程获取了该对象的锁之后，其他线程无法获取该对象的锁，所以无法访问该对象的其他synchronized实例方法，但是其他线程还是可以访问该实例对象的其他非synchronized方法，当然如果是一个线程 A 需要访问实例对象 obj1 的 synchronized 方法 f1(当前对象锁是obj1)，另一个线程 B 需要访问实例对象 obj2 的 synchronized 方法 f2(当前对象锁是obj2)，这样是允许的，因为两个实例对象锁并不同相同，此时如果两个线程操作数据并非共享的，线程安全是有保障的，遗憾的是如果两个线程操作的是共享数据，那么线程安全就有可能无法保证了，如下代码将演示出该现象：

```java
public class AccountingSyncBad implements Runnable{
    static int i=0;
    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1=new Thread(new AccountingSyncBad());
        //new新实例
        Thread t2=new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
```

上述代码与前面不同的是我们同时创建了两个新实例AccountingSyncBad，然后启动两个不同的线程对共享变量i进行操作，但很遗憾操作结果是1452317而不是期望结果2000000，因为上述代码犯了严重的错误，虽然我们使用synchronized修饰了increase方法，但却new了两个不同的实例对象，这也就意味着存在着两个不同的实例对象锁，因此t1和t2都会进入各自的对象锁，也就是说t1和t2线程使用的是不同的锁，因此线程安全是无法保证的。解决这种困境的的方式是将synchronized作用于静态的increase方法，这样的话，对象锁就当前类对象，由于无论创建多少个实例对象，但对于的类对象拥有只有一个，所有在这样的情况下对象锁就是唯一的。

下面我们看看如何使用将synchronized作用于静态的increase方法。



### 11.7.2、修饰静态方法

当synchronized作用于静态方法时，其锁就是当前类的class对象锁。由于静态成员不专属于任何一个实例对象，是类成员，因此通过class对象锁可以控制静态 成员的并发操作。需要注意的是如果一个线程A调用一个实例对象的非static synchronized方法，而线程B需要调用这个实例对象所属类的静态 synchronized方法，是允许的，不会发生互斥现象，因为访问静态 synchronized 方法占用的锁是当前类的class对象，而访问非静态 synchronized 方法占用的锁是当前实例对象锁，看如下代码：

```java
public class AccountingSyncClass implements Runnable{
    static int i=0;

    /**
     * 作用于静态方法,锁是当前class对象,也就是
     * AccountingSyncClass类对应的class对象
     */
    public static synchronized void increase(){
        i++;
    }

    /**
     * 非静态,访问时锁不一样不会发生互斥
     */
    public synchronized void increase4Obj(){
        i++;
    }

    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1=new Thread(new AccountingSyncClass());
        //new心事了
        Thread t2=new Thread(new AccountingSyncClass());
        //启动线程
        t1.start();t2.start();

        t1.join();t2.join();
        System.out.println(i);
    }
}
```

由于synchronized关键字修饰的是静态increase方法，与修饰实例方法不同的是，其锁对象是当前类的class对象。注意代码中的increase4Obj方法是实例方法，其对象锁是当前实例对象，如果别的线程调用该方法，将不会产生互斥现象，毕竟锁对象不同，但我们应该意识到这种情况下可能会发现线程安全问题(操作了共享静态变量i)。



### 11.7.3、修饰代码块

除了使用关键字修饰实例方法和静态方法外，还可以使用同步代码块，在某些情况下，我们编写的方法体可能比较大，同时存在一些比较耗时的操作，而需要同步的代码又只有一小部分，如果直接对整个方法进行同步操作，可能会得不偿失，此时我们可以使用同步代码块的方式对需要同步的代码进行包裹，这样就无需对整个方法进行同步操作了，同步代码块的使用示例如下：

```java
public class AccountingSync implements Runnable{
    static AccountingSync instance=new AccountingSync();
    static int i=0;
    @Override
    public void run() {
        //省略其他耗时操作....
        //使用同步代码块对变量i进行同步操作,锁对象为instance
        synchronized(instance){
            for(int j=0;j<1000000;j++){
                    i++;
              }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
```

从代码看出，将synchronized作用于一个给定的实例对象instance，即当前实例对象就是锁对象，每次当线程进入synchronized包裹的代码块时就会要求当前线程持有instance实例对象锁，如果当前有其他线程正持有该对象锁，那么新到的线程就必须等待，这样也就保证了每次只有一个线程执行i++;操作。当然除了instance作为对象外，我们还可以使用this对象(代表当前实例)或者当前类的class对象作为锁，如下代码：

```java
//this,当前实例对象锁
synchronized(this){
    for(int j=0;j<1000000;j++){
        i++;
    }
}

//class对象锁
synchronized(AccountingSync.class){
    for(int j=0;j<1000000;j++){
        i++;
    }
}

```



### 11.7.4、底层原理

Java 虚拟机中的同步(Synchronization)基于进入和退出管程(Monitor)对象实现， 无论是显式同步(有明确的 monitorenter 和 monitorexit 指令,即同步代码块)还是隐式同步都是如此。在 Java 语言中，同步用的最多的地方可能是被 synchronized 修饰的同步方法。

同步方法 并不是由 monitorenter 和 monitorexit 指令来实现同步的，而是由方法调用指令读取运行时常量池中方法的 ACC_SYNCHRONIZED 标志来隐式实现的，关于这点，稍后详细分析。下面先来了解一个概念Java对象头，这对深入理解synchronized实现原理非常关键。

---

**理解Java对象头与Monitor**

在JVM中，对象在内存中的布局分为三块区域：对象头、实例数据和对齐填充。如下：

<img src="../Images/Java/20170603163237166.png" alt="img" style="zoom:80%;" />

- 实例变量：存放类的属性数据信息，包括父类的属性信息，如果是数组的实例部分还包括数组的长度，这部分内存按4字节对齐。
- 填充数据：由于虚拟机要求对象起始地址必须是8字节的整数倍。填充数据不是必须存在的，仅仅是为了字节对齐。不知道大家有没有被问过一个空对象占多少个字节？就是8个字节，是因为对齐填充的关系哈，不到8个字节对其填充会帮我们自动补齐。如果是`Object o=new Object();`，那么空对象占8字节，对象的引用占4字节，加上填充数据即总共占用16字节，这点了解即可。

而对于顶部，则是Java头对象，它实现synchronized的锁对象的基础，这点我们重点分析它，一般而言，synchronized使用的锁对象是存储在Java对象头里的，jvm中采用2个字节来存储对象头(如果对象是数组则会分配3个字节，多出来的1个字节记录的是数组长度)，其主要结构是由Mark Word 和 Class Metadata Address 组成，其结构说明如下表：

| 虚拟机位数 | 头对象结构             | 说明                                                         |
| ---------- | ---------------------- | ------------------------------------------------------------ |
| 32/64bit   | Mark Word              | 存储对象的hashCode、锁信息或分代年龄或GC标志等信息           |
| 32/64bit   | Class Metadata Address | 类型指针指向对象的类元数据，JVM通过这个指针确定该对象是哪个类的实例。 |

其中Mark Word在默认情况下存储着对象的HashCode、分代年龄、锁标记位等以下是32位JVM的Mark Word默认存储结构

| 锁状态   | 25bit        | 4bit         | 1bit是否是偏向锁 | 2bit 锁标志位 |
| -------- | ------------ | ------------ | ---------------- | ------------- |
| 无锁状态 | 对象HashCode | 对象分代年龄 | 0                | 01            |

由于对象头的信息是与对象自身定义的数据没有关系的额外存储成本，因此考虑到JVM的空间效率，Mark Word 被设计成为一个非固定的数据结构，以便存储更多有效的数据，它会根据对象本身的状态复用自己的存储空间，如32位JVM下，除了上述列出的Mark Word默认存储结构外，还有如下可能变化的结构：

<img src="../Images/Java/20170603172215966.png" alt="img" style="zoom:80%;" />

其中轻量级锁和偏向锁是Java 6 对 synchronized 锁进行优化后新增加的，这里主要分析一下重量级锁也就是通常说synchronized的对象锁，锁标识位为10，其中指针指向的是monitor对象（也称为管程或监视器锁）的起始地址。

每个对象都存在着一个 monitor 与之关联，对象与其 monitor 之间的关系有存在多种实现方式，如monitor可以与对象一起创建销毁或当线程试图获取对象锁时自动生成，但当一个 monitor 被某个线程持有后，它便处于锁定状态。

在Java虚拟机(HotSpot)中，monitor是由ObjectMonitor实现的，其主要数据结构如下（位于HotSpot虚拟机源码ObjectMonitor.hpp文件，C++实现的）

```C++
ObjectMonitor() {
    _header       = NULL;
    _count        = 0; //记录个数
    _waiters      = 0,
    _recursions   = 0;
    _object       = NULL;
    _owner        = NULL;
    _WaitSet      = NULL; //处于wait状态的线程，会被加入到_WaitSet
    _WaitSetLock  = 0 ;
    _Responsible  = NULL ;
    _succ         = NULL ;
    _cxq          = NULL ;
    FreeNext      = NULL ;
    _EntryList    = NULL ; //处于等待锁block状态的线程，会被加入到该列表
    _SpinFreq     = 0 ;
    _SpinClock    = 0 ;
    OwnerIsThread = 0 ;
  }
```

ObjectMonitor中有两个队列，_ WaitSet 和 _ EntryList，用来保存ObjectWaiter对象列表( 每个等待锁的线程都会被封装成ObjectWaiter对象)，_owner指向持有ObjectMonitor对象的线程，当多个线程同时访问一段同步代码时，首先会进入 _EntryList 集合，当线程获取到对象的monitor 后进入 _Owner 区域并把monitor中的owner变量设置为当前线程同时monitor中的计数器count加1，若线程调用 wait() 方法，将释放当前持有的monitor，owner变量恢复为null，count自减1，同时该线程进入 WaitSe t集合中等待被唤醒。若当前线程执行完毕也将释放monitor(锁)并复位变量的值，以便其他线程进入获取monitor(锁)。如下图所示

![img](../Images/Java/20170604114223462.png)

由此看来，monitor对象存在于每个Java对象的对象头中(存储的指针的指向)，synchronized锁便是通过这种方式获取锁的，也是为什么Java中任意对象可以作为锁的原因，同时也是notify/notifyAll/wait等方法存在于顶级对象Object中的原因。

---

**synchronized代码块底层原理**

现在重新定义一个synchronized修饰的同步代码块，在代码块中操作共享变量i，如下

```java
public class SyncCodeBlock {

   public int i;

   public void syncTask(){
       //同步代码块
       synchronized (this){
           i++;
       }
   }
}
```

编译上述代码并使用javap反编译后得到字节码如下(这里我们一部分没有必要的信息)：

```java
Classfile /Users/zejian/Downloads/Java8_Action/src/main/java/com/zejian/concurrencys/SyncCodeBlock.class
  Last modified 2017-6-2; size 426 bytes
  MD5 checksum c80bc322c87b312de760942820b4fed5
  Compiled from "SyncCodeBlock.java"
public class com.zejian.concurrencys.SyncCodeBlock
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
  //........省略常量池中数据
  //构造函数
  public com.zejian.concurrencys.SyncCodeBlock();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 7: 0
  //===========主要看看syncTask方法实现================
  public void syncTask();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=3, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter  //注意此处，进入同步方法
         4: aload_0
         5: dup
         6: getfield      #2             // Field i:I
         9: iconst_1
        10: iadd
        11: putfield      #2            // Field i:I
        14: aload_1
        15: monitorexit   //注意此处，退出同步方法
        16: goto          24
        19: astore_2
        20: aload_1
        21: monitorexit //注意此处，退出同步方法
        22: aload_2
        23: athrow
        24: return
      Exception table:
      //省略其他字节码.......
}
SourceFile: "SyncCodeBlock.java"
```

主要关注字节码中的如下代码

```java
3: monitorenter  //进入同步方法
//..........省略其他  
15: monitorexit   //退出同步方法
16: goto          24
//省略其他.......
21: monitorexit //退出同步方法
```

从字节码中可知同步语句块的实现使用的是monitorenter 和 monitorexit 指令，其中monitorenter指令指向同步代码块的开始位置，monitorexit指令则指明同步代码块的结束位置，当执行monitorenter指令时，当前线程将试图获取 objectref(即对象锁) 所对应的 monitor 的持有权，当 objectref 的 monitor 的进入计数器为 0，那线程可以成功取得 monitor，并将计数器值设置为 1，取锁成功。如果当前线程已经拥有 objectref 的 monitor 的持有权，那它可以重入这个 monitor (关于重入性稍后会分析)，重入时计数器的值也会加 1。倘若其他线程已经拥有 objectref 的 monitor 的所有权，那当前线程将被阻塞，直到正在执行线程执行完毕，即monitorexit指令被执行，执行线程将释放 monitor(锁)并设置计数器值为0 ，其他线程将有机会持有 monitor 。值得注意的是编译器将会确保无论方法通过何种方式完成，方法中调用过的每条 monitorenter 指令都有执行其对应 monitorexit 指令，而无论这个方法是正常结束还是异常结束。为了保证在方法异常完成时 monitorenter 和 monitorexit 指令依然可以正确配对执行，编译器会自动产生一个异常处理器，这个异常处理器声明可处理所有的异常，它的目的就是用来执行 monitorexit 指令。从字节码中也可以看出多了一个monitorexit指令，它就是异常结束时被执行的释放monitor 的指令。

---

**synchronized方法底层原理**

方法级的同步是隐式，即无需通过字节码指令来控制的，它实现在方法调用和返回操作之中。JVM可以从方法常量池中的方法表结构(method_info Structure) 中的 ACC_SYNCHRONIZED 访问标志区分一个方法是否同步方法。当方法调用时，调用指令将会 检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，执行线程将先持有monitor（虚拟机规范中用的是管程一词）， 然后再执行方法，最后在方法完成(无论是正常完成还是非正常完成)时释放monitor。在方法执行期间，执行线程持有了monitor，其他任何线程都无法再获得同一个monitor。如果一个同步方法执行期间抛出了异常，并且在方法内部无法处理此异常，那这个同步方法所持有的monitor将在异常抛到同步方法之外时自动释放。下面我们看看字节码层面如何实现：

```java
public class SyncMethod {

   public int i;

   public synchronized void syncTask(){
           i++;
   }
}
```

使用javap反编译后的字节码如下：

```java
Classfile /Users/zejian/Downloads/Java8_Action/src/main/java/com/zejian/concurrencys/SyncMethod.class
  Last modified 2017-6-2; size 308 bytes
  MD5 checksum f34075a8c059ea65e4cc2fa610e0cd94
  Compiled from "SyncMethod.java"
public class com.zejian.concurrencys.SyncMethod
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool;

   //省略没必要的字节码
  //==================syncTask方法======================
  public synchronized void syncTask();
    descriptor: ()V
    //方法标识ACC_PUBLIC代表public修饰，ACC_SYNCHRONIZED指明该方法为同步方法
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: dup
         2: getfield      #2                  // Field i:I
         5: iconst_1
         6: iadd
         7: putfield      #2                  // Field i:I
        10: return
      LineNumberTable:
        line 12: 0
        line 13: 10
}
SourceFile: "SyncMethod.java"
```

从字节码中可以看出，synchronized修饰的方法并没有monitorenter指令和monitorexit指令，取得代之的确实是ACC_SYNCHRONIZED标识，该标识指明了该方法是一个同步方法，JVM通过该ACC_SYNCHRONIZED访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。这便是synchronized锁在同步代码块和同步方法上实现的基本原理。同时我们还必须注意到的是在Java早期版本中，synchronized属于重量级锁，效率低下，因为监视器锁（monitor）是依赖于底层的操作系统的Mutex Lock来实现的，而操作系统实现线程之间的切换时需要从用户态转换到核心态，这个状态之间的转换需要相对比较长的时间，时间成本相对较高，这也是为什么早期的synchronized效率低的原因。庆幸的是在Java 6之后Java官方对从JVM层面对synchronized较大优化，所以现在的synchronized锁效率也优化得很不错了，Java 6之后，为了减少获得锁和释放锁所带来的性能消耗，引入了轻量级锁和偏向锁。



### 11.7.5、锁升级

Java 虚拟机对 synchronized 的优化

锁的状态总共有四种，无锁状态、偏向锁、轻量级锁和重量级锁。随着锁的竞争，锁可以从偏向锁升级到轻量级锁，再升级的重量级锁，但是锁的升级是单向的，也就是说只能从低到高升级，不会出现锁的降级，关于重量级锁，前面已详细分析过，下面将介绍偏向锁和轻量级锁以及JVM的其他优化手段，这里并不打算深入到每个锁的实现和转换过程更多地是阐述Java虚拟机所提供的每个锁的核心优化思想，毕竟涉及到具体过程比较繁琐，如需了解详细过程可以查阅《深入理解Java虚拟机原理》。

---

**偏向锁**

偏向锁是Java 6之后加入的新锁，它是一种针对加锁操作的优化手段，经过研究发现，在大多数情况下，锁不仅不存在多线程竞争，而且总是由同一线程多次获得，因此为了减少同一线程获取锁(会涉及到一些CAS操作,耗时)的代价而引入偏向锁。偏向锁的核心思想是，如果一个线程获得了锁，那么锁就进入偏向模式，此时Mark Word 的结构也变为偏向锁结构，当这个线程再次请求锁时，无需再做任何同步操作，即获取锁的过程，这样就省去了大量有关锁申请的操作，从而也就提供程序的性能。所以，对于没有锁竞争的场合，偏向锁有很好的优化效果，毕竟极有可能连续多次是同一个线程申请相同的锁。但是对于锁竞争比较激烈的场合，偏向锁就失效了，因为这样场合极有可能每次申请锁的线程都是不相同的，因此这种场合下不应该使用偏向锁，否则会得不偿失，需要注意的是，偏向锁失败后，并不会立即膨胀为重量级锁，而是先升级为轻量级锁。下面接着了解轻量级锁。

---

**轻量级锁**

倘若偏向锁失败，虚拟机并不会立即升级为重量级锁，它还会尝试使用一种称为轻量级锁的优化手段(1.6之后加入的)，此时Mark Word 的结构也变为轻量级锁的结构。轻量级锁能够提升程序性能的依据是“对绝大部分的锁，在整个同步周期内都不存在竞争”，注意这是经验数据。需要了解的是，轻量级锁所适应的场景是线程交替执行同步块的场合，如果存在同一时间访问同一锁的场合，就会导致轻量级锁膨胀为重量级锁。

---

**自旋锁**

轻量级锁失败后，虚拟机为了避免线程真实地在操作系统层面挂起，还会进行一项称为自旋锁的优化手段。这是基于在大多数情况下，线程持有锁的时间都不会太长，如果直接挂起操作系统层面的线程可能会得不偿失，毕竟操作系统实现线程之间的切换时需要从用户态转换到核心态，这个状态之间的转换需要相对比较长的时间，时间成本相对较高，因此自旋锁会假设在不久将来，当前的线程可以获得锁，因此虚拟机会让当前想要获取锁的线程做几个空循环(这也是称为自旋的原因)，一般不会太久，可能是50个循环或100循环，在经过若干次循环后，如果得到锁，就顺利进入临界区。如果还不能获得锁，那就会将线程在操作系统层面挂起，这就是自旋锁的优化方式，这种方式确实也是可以提升效率的。最后没办法也就只能升级为重量级锁了。



## 10.8、sleep、wait、notify、notifyAll

**sleep 和 wait**

sleep() 和 wait() 都是线程暂停执行的方法。

- 这两个方法来自不同的类，分别是 Thread 和 Object，sleep() 属于 Thread 类中的静态方法，wait() 属于Object 成员方法。
- sleep() 是线程类（Thread）的方法，不涉及线程通信，调用时会在指定的时间内暂停该线程，但监控依然保持，**不会释放对象锁**，到时间自动恢复；wait() 是 Object 的方法，用于线程间的通信，调用时会放弃对象锁，进入等待队列，调用 notify()、notifyAll() 唤醒指定的线程或者所有的线程，才进入对象锁池准备获得对象所进入运行状态。
- ==wait()、notify() 和 notifyAll() 只能在同步控制方法或者同步控制块里面使用，而 sleep() 可以在任何地方使用（使用范围）。==
- sleep() 方法必须捕获异常 InterruptedException，而 wait() \ notify() 以及 notifyAll() 不需要捕获异常。

注意：

- sleep() 方法只让出了CPU，并不会释放同步资源锁。
- sleep() 方法指定的时间为线程不会运行的最短时间。因此，sleep() 方法不能保证该线程睡眠到期后就开始执行。
- notify() 的作用相当于叫醒睡着的人，而并不会给他分配任务，就是说 notify() 只是让之前调用 wait() 的线程有权利重新参与线程的调度。

wait() 会释放锁，noitfy() 和 notify() 将要释放锁，他们都需要线程持有对象的锁，这样就只能通过同步来实现，所以他们只能在同步方法或者同步块中被调用。

---

**wait、notify / notifyAll **

由于 wait()、notify/notifyAll() 方法是 Object 的本地 final 方法，无法被重写。

==当线程执行wait()方法时候，会释放当前的锁，然后让出CPU，进入等待状态。只有当 notify/notifyAll() 被执行时候，才会唤醒一个或多个正处于等待状态的线程，然后继续往下执行，直到执行完synchronized 代码块的代码或是中途遇到wait() ，再次释放锁。==也就是说，notify / notifyAll() 的执行只是唤醒沉睡的线程，而不会立即释放锁，锁的释放要看代码块的具体执行情况。所以在编程中，尽量在使用了 notify / notifyAll() 后立即退出临界区，以唤醒其他线程让其获得锁。

notify 和 wait 的顺序不能错，如果 A 线程先执行 notify 方法，B 线程再执行 wait 方法，那么 B 线程是无法被唤醒的。

notify 和 notifyAll 的区别：notify 方法只唤醒一个等待（对象的）线程并使该线程开始执行。所以如果有多个线程等待一个对象，这个方法只会唤醒其中一个线程，选择哪个线程取决于操作系统对多线程管理的实现。notifyAll 会唤醒所有等待(对象的)线程，尽管哪一个线程将会第一个处理取决于操作系统的实现。如果当前情况下有多个线程需要被唤醒，推荐使用 notifyAll 方法。比如在生产者-消费者里面的使用，每次都需要唤醒所有的消费者或是生产者，以判断程序是否可以继续往下执行。

---

**生产者消费者**

这个问题是一个多线程同步问题的经典案例，生产者负责生产对象，消费者负责将生成者产生的对象取出，两者不断重复此过程。这过程需要注意几个问题：

不论生产者和消费者有几个，必须保证：

1. 生产者每次产出的对象必须不一样，产生的对象有且仅有出现一次；

2. 消费者每次取出的对象必须不一样，取出的对象有且仅有出现一次；

3. 一定是先产生该对象，然后对象才能被取出，顺序不能乱；

定义一个汽车类：

```java
public class Car {
	// 定义一个属性存储当前汽车的数量
	int count = 0;

	// 定义生产汽车的方法
	public synchronized void createCar() {
		// 判断当前汽车的数量是否大于0
		if (count > 0) {
			// 将当前的线程进行等待
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 汽车数量为0的时候就开始执行生产操作
		System.out.println("汽车开始进行生产");
		count++;
		// 唤醒消费者线程开始进行销售
		this.notify();
	}

	// 定义消费者的方法
	public synchronized void sell() {
		if (count == 0) {
			// 执行等待操作
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("正在进行汽车销售");
		count--;
		// 唤醒生产者线程
		this.notify();
	}
}

```

定义生产者：

```java
public class Producter implements Runnable {
	// 定义一个汽车属性接收传入的汽车对象
	Car car;

	public Producter(Car car) {
		this.car = car;
	}

	@Override
	public void run() {
		while(true) {
			car.createCar();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```

定义消费者：

```java
public class Customer implements Runnable {
	Car car;

	public Customer(Car car) {
		this.car = car;
	}

	@Override
	public void run() {
		while(true) {
			car.sell();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```

测试：

```java
public class Test {
	public static void main(String[] args) {
		// 创建汽车核心类对象
		Car car = new Car();

		// 创建消费者功能
		Customer c = new Customer(car);

		// 创建生产者功能
		Producter p = new Producter(car);

		// 创建两个线程完成功能操作
		Thread tc = new Thread(c);
		Thread tp = new Thread(p);

		tc.start();
		tp.start();
	}
}
```



## 10.9、BIO、NIO、AIO

**同步与异步**

- **同步：** 同步就是发起一个调用后，被调用者未处理完请求之前，调用不返回。
- **异步：** 异步就是发起一个调用后，立刻得到被调用者的回应表示已接收到请求，但是被调用者并没有返回结果，此时我们可以处理其他的请求，被调用者通常依靠事件，回调等机制来通知调用者其返回结果。

同步和异步的区别最大在于异步的话调用者不需要等待处理结果，被调用者会通过回调等机制来通知调用者其返回结果。

---

**阻塞和非阻塞**

- **阻塞：** 阻塞就是发起一个请求，调用者一直等待请求结果返回，也就是当前线程会被挂起，无法从事其他任务，只有当条件就绪才能继续。
- **非阻塞：** 非阻塞就是发起一个请求，调用者不用一直等着结果返回，可以先去干其他事情。

举个生活中简单的例子，你妈妈让你烧水，小时候你比较笨啊，在哪里傻等着水开（**同步阻塞**）。等你稍微再长大一点，你知道每次烧水的空隙可以去干点其他事，然后只需要时不时来看看水开了没有（**同步非阻塞**）。后来，你们家用上了水开了会发出声音的壶，这样你就只需要听到响声后就知道水开了，在这期间你可以随便干自己的事情，你需要去倒水了（**异步非阻塞**）。



### 10.9.1、BIO (Blocking I/O)

同步阻塞 I/O 模式，数据的读取写入必须阻塞在一个线程内等待其完成。

![img](../Images/Java/2163864-20201022174532077-2087464332-16282184549795.png)

采用 **BIO 通信模型** 的服务端，通常由一个独立的 Acceptor 线程负责监听客户端的连接。我们一般通过在 `while(true)` 循环中服务端会调用 `accept()` 方法等待接收客户端的连接的方式监听请求，请求一旦接收到一个连接请求，就可以建立通信套接字在这个通信套接字上进行读写操作，此时不能再接收其他客户端连接请求，只能等待同当前连接的客户端的操作执行完成， 不过可以通过多线程来支持多个客户端的连接，如上图所示。

如果要让 **BIO 通信模型** 能够同时处理多个客户端请求，就必须使用多线程（主要原因是 `socket.accept()`、 `socket.read()`、 `socket.write()` 涉及的三个主要函数都是同步阻塞的），也就是说它在接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理，处理完成之后，通过输出流返回应答给客户端，线程销毁。这就是典型的 **一请求一应答通信模型** 。

在 Java 虚拟机中，线程是宝贵的资源，线程的创建和销毁成本很高，除此之外，线程的切换成本也是很高的。尤其在 Linux 这样的操作系统中，线程本质上就是一个进程，创建和销毁线程都是重量级的系统函数。如果并发访问量增加会导致线程数急剧膨胀可能会导致线程堆栈溢出、创建新线程失败等问题，最终导致进程宕机或者僵死，不能对外提供服务。同时如果这个连接不做任何事情的话会造成不必要的线程开销，不过可以通过 **线程池机制** 改善，线程池还可以让线程的创建和回收成本相对较低。使用`FixedThreadPool` 可以有效的控制了线程的最大数量，保证了系统有限的资源的控制，实现了N(客户端请求数量):M(处理客户端请求的线程数量)的伪异步I/O模型（N 可以远远大于 M）。

![img](../Images/Java/2163864-20201022175636286-539148404.png)

采用线程池和任务队列可以实现一种叫做伪异步的 I/O 通信框架，它的模型图如上图所示。当有新的客户端接入时，将客户端的 Socket 封装成一个Task（该任务实现java.lang.Runnable接口）投递到后端的线程池中进行处理，JDK 的线程池维护一个消息队列和 N 个活跃线程，对消息队列中的任务进行处理。由于线程池可以设置消息队列的大小和最大线程数，因此，它的资源占用是可控的，无论多少个客户端并发访问，都不会导致资源的耗尽和宕机。

伪异步I/O通信框架采用了线程池实现，因此避免了为每个请求都创建一个独立线程造成的线程资源耗尽问题。不过因为它的底层任然是同步阻塞的BIO模型，因此无法从根本上解决问题。



### 10.9.2、NIO (New I/O)

NIO 是一种同步非阻塞的 I/O 模型，在Java 1.4 中引入了 NIO 框架，对应 java.nio 包，提供了 Channel , Selector，Buffer等抽象。

NIO中的 N 可以理解为 Non-blocking，不单纯是 New。它支持面向缓冲的，基于通道的 I/O 操作方法。 NIO 提供了与传统 BIO 模型中的 `Socket` 和 `ServerSocket` 相对应的 `SocketChannel` 和 `ServerSocketChannel` 两种不同的套接字通道实现,两种通道都支持阻塞和非阻塞两种模式。阻塞模式使用就像传统中的支持一样，比较简单，但是性能和可靠性都不好；非阻塞模式正好与之相反。对于低负载、低并发的应用程序，可以使用同步阻塞 I/O 来提升开发速率和更好的维护性；对于高负载、高并发的（网络）应用，应使用 NIO 的非阻塞模式来开发。

---

**NIO 的特性 / NIO 与 IO 区别**

- Non-blocking IO（非阻塞IO）

	> IO 流是阻塞的，NIO 流是不阻塞的。
	>
	> Java NIO 使我们可以进行非阻塞 IO 操作。比如说，单线程中从通道读取数据到 buffer，同时可以继续做别的事情，当数据读取到 buffer 中后，线程再继续处理数据。写数据也是一样的。另外，非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。
	>
	> Java IO 的各种流是阻塞的。这意味着，当一个线程调用 `read()` 或 `write()` 时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情了。

- Buffer（缓冲区）

	> IO 面向流(Stream oriented)，而 NIO 面向缓冲区(Buffer oriented)。
	>
	> Buffer 是一个对象，它包含一些要写入或者要读出的数据。在 NIO 类库中加入 Buffer 对象，体现了新库与原 I/O 的一个重要区别。在面向流的 I/O 中，可以将数据直接写入或者将数据直接读到 Stream 对象中。虽然 Stream 中也有 Buffer 开头的扩展类，但只是流的包装类，还是从流读到缓冲区，而 NIO 却是直接读到 Buffer 中进行操作。
	>
	> 在 NIO 厍中，所有数据都是用缓冲区处理的。在读取数据时，它是直接读到缓冲区中的；在写入数据时，写入到缓冲区中。任何时候访问NIO 中的数据，都是通过缓冲区进行操作。
	>
	> 最常用的缓冲区是 ByteBuffer，一个 ByteBuffer 提供了一组功能用于操作 byte 数组。除了 ByteBuffer，还有其他的一些缓冲区，事实上，每一种 Java 基本类型（除了Boolean类型）都对应有一种缓冲区。

- Channel（通道）

	> NIO 通过Channel（通道） 进行读写。
	>
	> 通道是双向的，可读也可写，而流的读写是单向的。无论读写，通道只能和 Buffer 交互。因为 Buffer，通道可以异步地读写。

- Selectors(选择器)

	> NIO 有选择器，而 IO 没有。
	>
	> 选择器用于使用单个线程处理多个通道。因此，它需要较少的线程来处理这些通道。线程之间的切换对于操作系统来说是昂贵的。 因此，为了提高系统效率选择器是有用的。



### 10.9.3、AIO (Asynchronous I/O)

AIO 也就是 NIO 2。在 Java 7 中引入了 NIO 的改进版 NIO 2,它是异步非阻塞的IO模型。异步 IO 是基于事件和回调机制实现的，也就是应用操作之后会直接返回，不会堵塞在那里，当后台处理完成，操作系统会通知相应的线程进行后续的操作。



## 10.10、ExecutorService

我们之前使用线程的时候都是使用 new Thread 来进行线程的创建，但是这样会有一些问题。如：

- 每次 new Thread 新建对象性能差。
- 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机。
- 缺乏更多功能，如定时执行、定期执行、线程中断。

相比 new Thread，Java 提供的四种线程池的好处在于：

- 重用存在的线程，减少对象创建、消亡的开销，性能佳。
- 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
- 提供定时执行、定期执行、单线程、并发数控制等功能。



### 10.10.1、内置线程池

Java 通过 Executors 提供四种线程池，分别为：

- newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
- newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
- newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
- newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。



**newCachedThreadPool（）**

它是用来处理大量短时间工作任务的线程池，具有几个鲜明特点：它会试图缓存线程并重用，当无缓存线程可用时，就会创建新的工作线程；如果线程闲置时间超过60秒，则被终止并移除缓存；长时间闲置时，这种线程池，不会消耗什么资源。其内部使用SynchronousQueue作为工作队列。



**newFixedThreadPool（int nThreads）**

重用指定数目（nThreads）的线程，其背后使用的是无界的工作队列，任何时候最多有nThreads个工作线程是活动的。这意味着，如果任务数量超过了活动线程数目，将在工作队列中等待空闲线程出现；如果工作线程退出，将会有新的工作线程被创建，以补足指定数目nThreads。



**newSingleThreadExecutor()**

它的特点在于工作线程数目限制为1，操作一个无界的工作队列，所以它保证了所有的任务都是被顺序执行，最多会有一个任务处于活动状态，并且不予许使用者改动线程池实例，因此可以避免改变线程数目。



**newSingleThreadScheduledExecutor()**和**newScheduledThreadPool(int corePoolSize)**

创建的是个ScheduledExecutorService，可以进行定时或周期性的工作调度，区别在于单一工作线程还是多个工作线程。



### 10.10.2、ThreadPoolExecutor

在阿里巴巴代码规范中，建议我们自己指定线程池的相关参数，为的是让开发人员能够自行理解线程池创建中的每个参数，根据实际情况，创建出合理的线程池。接下来，我们来剖析下`java.util.concurrent.ThreadPoolExecutor`的构造方法参数。

`java.util.concurrent.ThreadPoolExecutor`有多个构造方法，我们拿参数最多的构造方法来举例，以下是阿里巴巴代码规范中给出的创建线程池的范例：

```java
ThreadPoolExecutor service = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), 
                new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(), 
                new ThreadPoolExecutor.AbortPolicy());
```

首先最重要的几个参数，可能就是：`corePoolSize`，`maximumPoolSize`，`workQueue`了，先看下这几个参数的解释：

- corePoolSize
	用于设定 thread pool 需要时刻保持的最小 core threads 的数量，即便这些 core threads 处于空闲状态啥事都不做也不会将它们回收掉，当然前提是你没有设置 allowCoreThreadTimeOut 为 true。至于 pool 是如何做到保持这些个 threads 不死的，我们稍后再说。
- maximumPoolSize
	用于限定 pool 中线程数的最大值。如果你自己构造了 pool 且传入了一个 Unbounded 的 queue 且没有设置它的 capacity，那么不好意思，最大线程数会永远 <= corePoolSize，maximumPoolSize 变成了无效的。
- workQueue
	该线程池中的任务队列：维护着等待执行的 Runnable 对象。当所有的核心线程都在干活时，新添加的任务会被添加到这个队列中等待处理，如果队列满了，则新建非核心线程执行任务。

```java
// 创建线程池
ThreadPoolExecutor service = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());
// 等待执行的runnable   
Runnable runnable = () -> {
    try {
        TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
};

// 启动的任务数量
int counts = 1224;
for (int i = 0; i < counts; i++) {
    service.execute(runnable);
}

// 监控线程池执行情况的代码 
ThreadPoolExecutor tpe = ((ThreadPoolExecutor) service);
while (true) {
    System.out.println();

    int queueSize = tpe.getQueue().size();
    System.out.println("当前排队线程数：" + queueSize);

    int activeCount = tpe.getActiveCount();
    System.out.println("当前活动线程数：" + activeCount);

    long completedTaskCount = tpe.getCompletedTaskCount();
    System.out.println("执行完成线程数：" + completedTaskCount);

    long taskCount = tpe.getTaskCount();
    System.out.println("总线程数：" + taskCount);

    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
} 
```

线程池的容量与我们启动的任务数量息息相关。

已知：

- corePoolSize = 5
- maximumPoolSize = 200
- workQueue.size() = 1024

我们修改同时 execute 添加到线程池的 Runnable 数量 counts：

- counts <= corePoolSize：所有的任务均为核心线程执行，没有任何 Runnable 被添加到 workQueue中。

	```
	当前排队线程数：0
	当前活动线程数：3
	执行完成线程数：0
	总线程数：3
	```

- corePoolSize < counts <= corePoolSize + workQueue.size()：所有任务均为核心线程执行，当核心线程处于繁忙状态，则将任务添加到 workQueue 中等待。

	```
	当前排队线程数：15
	当前活动线程数：5
	执行完成线程数：0
	总线程数：20
	```

- corePoolSize + workQueue.size() < counts <= maximumPoolSize + workQueue.size()：corePoolSize 个线程由核心线程执行，超出队列长度 workQueue.size() 的任务，将另启动非核心线程执行。

	```
	当前排队线程数：1024
	当前活动线程数：105
	执行完成线程数：0
	总线程数：1129
	```

- counts > maximumPoolSize + workQueue.size()：将会报异常`java.util.concurrent.RejectedExecutionException`

	```java
	java.util.concurrent.RejectedExecutionException: Task com.bwjava.util.ExecutorServiceUtilTest$$Lambda$1/314265080@725bef66 rejected from java.util.concurrent.ThreadPoolExecutor@2aaf7cc2[Running, pool size = 200, active threads = 200, queued tasks = 1024, completed tasks = 0]
	```



### 10.10.3、ExecutorService

通常来说有两种方法来创建 ExecutorService。

- 第一种方式是使用 Executors 中的工厂类方法，例如：

	```java
	ExecutorService executor = Executors.newFixedThreadPool(10);
	```

	除了 newFixedThreadPool 方法之外，Executors 还包含了很多创建 ExecutorService 的方法。

- 第二种方法是直接创建一个 ExecutorService， 因为 ExecutorService 是一个 interface，需要实例化 ExecutorService 的一个实现。

	这里使用 ThreadPoolExecutor 来举例：

	```java
	ExecutorService executorService =
	            new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
	                    new LinkedBlockingQueue<Runnable>());
	```

---

**为 ExecutorService 分配 Tasks**

ExecutorService 可以执行 Runnable 和 Callable 的 task。其中 Runnable 是没有返回值的，而 Callable 是有返回值的。以下为两种情况的使用：

```java
Runnable runnableTask = () -> {
    try {
        TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
};
 
Callable<String> callableTask = () -> {
    TimeUnit.MILLISECONDS.sleep(300);
    return "Task's execution";
};
```

将 task 分配给 ExecutorService，可以通过调用 `execute()`, `submit()`, `invokeAny()`, `invokeAll()` 这几个方法来实现。



execute() 返回值是 void，他用来提交一个Runnable task。

```java
executorService.execute(runnableTask);
```



submit() 返回值是 Future，它可以提交 Runnable task, 也可以提交 Callable task，同时submit() 可以处理异常，如果在task里会抛出 checked 或者 unchecked exception，而又希望外面的调用者能够感知这些 exception 并做出及时的处理，那么就需要用到 submit，通过捕获 Future.get 抛出的异常。

提交 Runnable 的有两个方法：

```java
<T> Future<T> submit(Runnable task, T result);

Future<?> submit(Runnable task);
```

第一个方法在返回传入的 result。第二个方法返回 null。



再看一下callable的使用：

```java
Future<String> future = executorService.submit(callableTask);
```



invokeAny() 将一个 task 列表传递给 executorService，并返回其中的一个成功返回的结果。

```java
String result = executorService.invokeAny(callableTasks);
```



invokeAll() 将一个task列表传递给executorService，并返回所有成功执行的结果：

```java
List<Future<String>> futures = executorService.invokeAll(callableTasks);
```

---

**关闭 ExecutorService**

如果 ExecutorService 中的任务运行完毕之后，ExecutorService 不会自动关闭。它会等待接收新的任务。如果需要关闭 ExecutorService， 需要调用 shutdown() 或者 shutdownNow() 方法。

shutdown() 会立即销毁 ExecutorService，它会让 ExecutorServic 停止接收新的任务，并等待现有任务全部执行完毕再销毁。

```java
executorService.shutdown();
```

shutdownNow() 并不保证所有的任务都被执行完毕，它会返回一个未执行任务的列表：

```java
List<Runnable> notExecutedTasks = executorService.shutdownNow();
```



### 10.10.4、线程池的状态 

线程池有5种状态：Running、ShutDown、Stop、Tidying、Terminated。

![线程池各个状态切换框架图：](https://img-blog.csdnimg.cn/20200629150312492.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMDEzNTkw,size_16,color_FFFFFF,t_70)

- RUNNING：线程池一旦被创建，就处于 RUNNING 状态，任务数为 0，能够接收新任务，对已排队的任务进行处理。

- SHUTDOWN：不接收新任务，但能处理已排队的任务。

	调用线程池的 shutdown() 方法，线程池由 RUNNING 转变为 SHUTDOWN 状态。 

- STOP：不接收新任务，不处理已排队的任务，并且会中断正在处理的任务。

	调用线程池的 shutdownNow() 方法，线程池由(RUNNING 或 SHUTDOWN ) 转变为 STOP 状态。

- TIDYING：会执行 terminated() 方法。线程池中的 terminated() 方法时空实现，可以重写给方法进行相应的处理。

	线程池在 SHUTDOWN 状态，任务队列为空且执行中任务为空，线程池就会由 SHUTDOWN 转变为 TIDYING 状态。 

	线程池在 STOP 状态，线程池中执行中任务为空时，就会由 STOP 转变为 TIDYING 状态。 

- TERMINATED：线程池彻底终止。

	线程池在 TIDYING 状态执行完 terminated() 方法就会由 TIDYING 转变为 TERMINATED 状态。 



## 10.11、线程安全

### 10.11.1、内存模型的相关概念

计算机在执行程序时，每条指令都是在CPU中执行的，而执行指令过程中，势必涉及到数据的读取和写入。由于程序运行过程中的临时数据是存放在主存（物理内存）当中的，这时就存在一个问题，CPU执行速度很快，而从内存读取数据和向内存写入数据的过程跟CPU执行指令的速度比起来要慢的多，因此如果任何时候对数据的操作都要通过和内存的交互来进行，会大大降低指令执行的速度。

因此在CPU里面就有了高速缓存：当程序在运行过程中，会将运算需要的数据从主存复制一份到CPU的高速缓存当中，那么CPU进行计算时就可以直接从它的高速缓存读取数据和向其中写入数据，当运算结束之后，再将高速缓存中的数据刷新到主存当中。举个简单的例子，比如下面的这段代码：

```java
i = i + 1;
```

当线程执行这个语句时，会先从主存当中读取i的值，然后复制一份到高速缓存当中，然后CPU执行指令对i进行加1操作，然后将数据写入高速缓存，最后将高速缓存中i最新的值刷新到主存当中。

这个代码在单线程中运行是没有任何问题的，但是在多线程中运行就会有问题了。在多核CPU中，每条线程可能运行于不同的CPU中，因此每个线程运行时有自己的高速缓存（对单核CPU来说，其实也会出现这种问题，只不过是以线程调度的形式来分别执行的）。

可能存在下面一种情况：初始时，两个线程分别读取 i 的值存入各自所在的CPU的高速缓存当中，然后线程1进行加1操作，然后把i的最新值1写入到内存。此时线程2的高速缓存当中i的值还是0，进行加1操作之后，i的值为1，然后线程2把i的值写入内存。最终结果i的值是1，而不是2。这就是著名的缓存一致性问题。通常称这种被多个线程访问的变量为共享变量。也就是说，如果一个变量在多个CPU中都存在缓存（一般在多线程编程时才会出现），那么就可能存在缓存不一致的问题。

为了解决缓存不一致性问题，通常来说有以下2种解决方法：

- 通过在总线加LOCK#锁的方式
- 通过缓存一致性协议

这2种方式都是硬件层面上提供的方式。

在早期的 CPU 当中，是通过在总线上加 LOCK# 锁的形式来解决缓存不一致的问题。因为 CPU 和其他部件进行通信都是通过总线来进行的，如果对总线加 LOCK# 锁的话，也就是说阻塞了其他 CPU 对其他部件访问（如内存），从而使得只能有一个 CPU 能使用这个变量的内存。比如上面例子中 如果一个线程在执行 i = i +1，如果在执行这段代码的过程中，在总线上发出了 LCOK# 锁的信号，那么只有等待这段代码完全执行完毕之后，其他CPU才能从变量i所在的内存读取变量，然后进行相应的操作。这样就解决了缓存不一致的问题。

但是上面的方式会有一个问题，由于在锁住总线期间，其他CPU无法访问内存，导致效率低下。

所以就出现了缓存一致性协议。最出名的就是Intel 的MESI协议，MESI协议保证了每个缓存中使用的共享变量的副本是一致的。它核心的思想是：当CPU写数据时，如果发现操作的变量是共享变量，即在其他CPU中也存在该变量的副本，会发出信号通知其他CPU将该变量的缓存行置为无效状态，因此当其他CPU需要读取这个变量时，发现自己缓存中缓存该变量的缓存行是无效的，那么它就会从内存重新读取。



### 10.11.2、并发编程中的三个概念

在并发编程中，通常会遇到以下三个问题：原子性问题，可见性问题，有序性问题：

**原子性**：
即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
若一个线程执行到这个语句时，暂且假设为一个32位的变量赋值包括两个过程：为低16位赋值，为高16位赋值。
那么就可能发生一种情况：当将低16位数值写入之后，突然被中断，而此时又有一个线程去读取i的值，那么读取到的就是错误的数据。

**可见性**：
当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。

```java
//线程1执行的代码
int i = 0;
i = 10;
 
//线程2执行的代码
j = i;
```

假若执行线程1的是CPU1，执行线程2的是CPU2。由上面的分析可知，当线程1执行 i =10这句时，会先把i的初始值加载到CPU1的高速缓存中，然后赋值为10，那么在CPU1的高速缓存当中i的值变为10了，却没有立即写入到主存当中。

此时线程2执行 j = i，它会先去主存读取i的值并加载到CPU2的缓存当中，注意此时内存当中i的值还是0，那么就会使得j的值为0，而不是10。这就是可见性问题，线程1对变量i修改了之后，线程2没有立即看到线程1修改的值。

**有序性**：
即程序执行的顺序按照代码的先后顺序执行。

```java
int i = 0;
boolean flag = false;
i = 1;			//语句1  
flag = true;     //语句2
```

上面代码定义了一个int型变量，定义了一个boolean类型变量，然后分别对两个变量进行赋值操作。从代码顺序上看，语句1是在语句2前面的，那么JVM在真正执行这段代码的时候会保证语句1一定会在语句2前面执行吗？不一定，为什么呢？这里可能会发生指令重排序（Instruction Reorder）。
	
一般来说，处理器为了提高程序运行效率，可能会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序一致，但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。比如上面的代码中，语句1和语句2谁先执行对最终的程序结果并没有影响，那么就有可能在执行过程中，语句2先执行而语句1后执行。
	
但是要注意，虽然处理器会对指令进行重排序，但是它会保证程序最终结果会和代码顺序执行结果相同，那么它靠什么保证的呢？再看下面一个例子：

```java
int a = 10;    //语句1
int r = 2;    //语句2
a = a + 3;    //语句3
r = a*a;     //语句4
```

这段代码有4个语句，那么可能的一个执行顺序是： 语句2  语句1  语句3  语句4；那么可不可能是这个执行顺序呢： 语句2  语句1  语句4  语句3。

不可能，因为处理器在进行重排序时是会考虑指令之间的数据依赖性，如果一个指令Instruction 2 必须用到Instruction 1 的结果，那么处理器会保证Instruction 1会在Instruction 2之前执行。

虽然重排序不会影响单个线程内程序执行的结果，但是多线程呢？下面看一个例子：

```java
//线程1:
context = loadContext();   //语句1
inited = true;             //语句2
 
//线程2:
while(!inited ){
  sleep()
}
doSomethingwithconfig(context);
```

上面代码中，由于语句1和语句2没有数据依赖性，因此可能会被重排序。假如发生了重排序，在线程1执行过程中先执行语句2，而此是线程2会以为初始化工作已经完成，那么就会跳出 while 循环，去执行 doSomethingwithconfig(context) 方法，而此时context并没有被初始化，就会导致程序出错。
	
从上面可以看出，指令重排序不会影响单个线程的执行，但是会影响到线程并发执行的正确性。也就是说，要想并发程序正确地执行，必须要保证原子性、可见性以及有序性。只要有一个没有被保证，就有可能会导致程序运行不正确。



### 10.11.3、Java内存模型

在Java虚拟机规范中试图定义一种Java内存模型（Java Memory Model，JMM）来屏蔽各个硬件平台和操作系统的内存访问差异，以实现让Java程序在各种平台下都能达到一致的内存访问效果。那么Java内存模型规定了哪些东西呢，它定义了程序中变量的访问规则，往大一点说是定义了程序执行的次序。注意，为了获得较好的执行性能，Java内存模型并没有限制执行引擎使用处理器的寄存器或者高速缓存来提升指令执行速度，也没有限制编译器对指令进行重排序。也就是说，在java内存模型中，也会存在缓存一致性问题和指令重排序的问题。

Java内存模型规定所有的变量都是存在主存当中（类似于前面说的物理内存），每个线程都有自己的工作内存（类似于前面的高速缓存）。线程对变量的所有操作都必须在工作内存中进行，而不能直接对主存进行操作。并且每个线程不能访问其他线程的工作内存。

那么Java语言本身对原子性、可见性以及有序性提供了哪些保证呢？

**原子性**：
在Java中，对基本数据类型的变量的读取和赋值操作是原子性操作，即这些操作是不可被中断的，要么执行，要么不执行。
上面一句话虽然看起来简单，但是理解起来并不是那么容易。看下面一个例子：

```java
// 请分析以下哪些操作是原子性操作
x = 10;         //语句1
y = x;         //语句2
x++;           //语句3
x = x + 1;     //语句4
```

语句1是直接将数值10赋值给x，也就是说线程执行这个语句的会直接将数值10写入到工作内存中。语句2实际上包含2个操作，它先要去读取x的值，再将x的值写入工作内存，虽然读取x的值以及 将x的值写入工作内存 这2个操作都是原子性操作，但是合起来就不是原子性操作了。同样的，x++和 x = x+1包括3个操作：读取x的值，进行加1操作，写入新的值。

所以上面4个语句只有语句1的操作具备原子性。

也就是说，只有简单的读取、赋值（而且必须是将数字赋值给某个变量，变量之间的相互赋值不是原子操作）才是原子操作。从上面可以看出，Java内存模型只保证了基本读取和赋值是原子性操作，如果要实现更大范围操作的原子性，可以通过 synchronized 和Lock来实现。由于 synchronized 和 Lock 能够保证任一时刻只有一个线程执行该代码块，那么自然就不存在原子性问题了，从而保证了原子性。

**可见性**：
对于可见性，Java提供了volatile关键字来保证可见性。

当一个共享变量被volatile修饰时，它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，它会去内存中读取新值。

而普通的共享变量不能保证可见性，因为普通共享变量被修改之后，什么时候被写入主存是不确定的，当其他线程去读取时，此时内存中可能还是原来的旧值，因此无法保证可见性。

另外，通过synchronized和Lock也能够保证可见性，synchronized和Lock能保证同一时刻只有一个线程获取锁然后执行同步代码，并且在释放锁之前会将对变量的修改刷新到主存当中。因此可以保证可见性。

**有序性**：
在Java内存模型中，允许编译器和处理器对指令进行重排序，但是重排序过程不会影响到单线程程序的执行，却会影响到多线程并发执行的正确性。

在Java里面，可以通过volatile关键字来保证一定的“有序性”（具体原理在下一节讲述）。另外可以通过synchronized和Lock来保证有序性，很显然，synchronized和Lock保证每个时刻是有一个线程执行同步代码，相当于是让线程顺序执行同步代码，自然就保证了有序性。

另外，Java内存模型具备一些先天的“有序性”，即不需要通过任何手段就能够得到保证的有序性，这个通常也称为 happens-before 原则。如果两个操作的执行次序无法从happens-before原则推导出来，那么它们就不能保证它们的有序性，虚拟机可以随意地对它们进行重排序。

Happens-Before 规则如下：

- 程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作
- 锁定规则：一个unLock操作先行发生于后面对同一个锁额lock操作
- volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
- 传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C
- 线程启动规则：Thread对象的start()方法先行发生于此线程的每个一个动作
- 线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
- 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行
- 对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始

这8条规则中，前4条规则是比较重要的，后4条规则都是显而易见的。



## 10.12、死锁

死锁是指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。此时称系统处于死锁状态或系统产生了死锁，这些永远在互相等待的进程称为死锁进程。是操作系统层面的一个错误，是进程死锁的简称，最早在 1965 年由 Dijkstra 在研究银行家算法时提出的，它是计算机操作系统乃至整个并发程序设计领域最难处理的问题之一。

死锁的四个必要条件：

- 互斥条件：进程对所分配到的资源不允许其他进程进行访问，若其他进程访问该资源，只能等待，直至占有该资源的进程使用完成后释放该资源。
- 请求和保持条件：进程获得一定的资源之后，又对其他资源发出请求，但是该资源可能被其他进程占有，此时请求阻塞，但又对自己获得的资源保持不放。
- 不可剥夺条件：是指进程已获得的资源，在未完成使用之前，不可被剥夺，只能在使用完后自己释放。
- 环路等待条件：是指进程发生死锁后，若干进程之间形成一种头尾相接的循环等待资源关系。

只要打破四个必要条件之一就能有效预防死锁的发生：

- 打破互斥条件：改造独占性资源为虚拟资源，大部分资源已无法改造。
- 打破不可抢占条件：当一进程占有一独占性资源后又申请一独占性资源而无法满足，则退出原占有的资源。
- 打破占有且申请条件：采用资源预先分配策略，即进程运行前申请全部资源，满足则运行，不然就等待，这样就不会占有且申请。
- 打破循环等待条件：实现资源有序分配策略，对所有设备实现分类编号，所有进程只能采用按序号递增的形式申请资源。


所以，在系统设计、进程调度等方面注意如何不让这四个必要条件成立，如何确定资源的合理分配算法，避免进程永久占据系统资源。此外，也要防止进程在处于等待状态的情况下占用资源,在系统运行过程中，对进程发出的每一个系统能够满足的资源申请进行动态检查，并根据检查结果决定是否分配资源，若分配后系统可能发生死锁，则不予分配，否则予以分配。因此，对资源的分配要给予合理的规划。



## 10.13、ThreadLocal

ThreadLocal 是线程本地存储，在每个线程中都创建了一个 ThreadLocalMap 对象，每个线程可以访问自己内部 ThreadLocalMap 对象内的 value。

经典的使用场景是为每个线程分配一个 JDBC 连接 Connection。这样就可以保证每个线程的都在各自的 Connection 上进行数据库的操作，不会出现 A 线程关了 B线程正在使用的 Connection； 还有 Session 管理等问题。

ThreadLocal 使用例子：

```java
public class TestThreadLocal {
	//线程本地存储变量
	private static final ThreadLocal<Integer> THREAD_LOCAL_NUM = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
 
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {//启动三个线程
			Thread t = new Thread() {
				@Override
				public void run() {
					add10ByThreadLocal();
				}
			};
			t.start();
		}
	}
	
	/**
	 * 线程本地存储变量加 5
	 */
	private static void add10ByThreadLocal() {
		for (int i = 0; i < 5; i++) {
			Integer n = THREAD_LOCAL_NUM.get();
			n += 1;
			THREAD_LOCAL_NUM.set(n);
			System.out.println(Thread.currentThread().getName() + " : ThreadLocal num=" + n);
		}
	}
}
```

```
Thread-0 : ThreadLocal num=1
Thread-1 : ThreadLocal num=1
Thread-0 : ThreadLocal num=2
Thread-0 : ThreadLocal num=3
Thread-1 : ThreadLocal num=2
Thread-2 : ThreadLocal num=1
Thread-0 : ThreadLocal num=4
Thread-2 : ThreadLocal num=2
Thread-1 : ThreadLocal num=3
Thread-1 : ThreadLocal num=4
Thread-2 : ThreadLocal num=3
Thread-0 : ThreadLocal num=5
Thread-2 : ThreadLocal num=4
Thread-2 : ThreadLocal num=5
Thread-1 : ThreadLocal num=5
```

启动了 3 个线程，每个线程最后都打印到 "ThreadLocal num=5"，而不是 num 一直在累加直到值等于 15。

---

**实现原理：**

按照我们第一直觉，感觉 ThreadLocal 内部肯定是有个 Map 结构，key 存了 Thread，value 存了 本地变量 V 的值。每次通过 ThreadLocal 对象的 get() 和 set(T value) 方法获取当前线程里存的本地变量、设置当前线程里的本地变量。

![img](../Images/Java/20190523105529871-162821864050610.png)

而 JDK 的实现里面这个 Map 是属于 Thread，而非属于 ThreadLocal。ThreadLocal 仅是一个代理工具类，内部并不持有任何与线程相关的数据，所有和线程相关的数据都存储在 Thread 里面。ThreadLocalMap 属于 Thread 也更加合理。

![img](../Images/Java/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21laXNtNQ==,size_16,color_FFFFFF,t_70)

还有一个更加深层次的原因，这样设计不容易产生内存泄露。

ThreadLocal 持有的 Map 会持有 Thread 对象的引用，只要 ThreadLocal 对象存在，那么 Map 中的 Thread 对象就永远不会被回收。ThreadLocal 的生命周期往往比线程要长，所以这种设计方案很容易导致内存泄露。


JDK 的实现中 Thread 持有 ThreadLocalMap，而且 ThreadLocalMap 里对 ThreadLocal 的引用还是弱引用（WeakReference），所以只要 Thread 对象可以被回收，那么 ThreadLocalMap 就能被回收。JDK 的这种实现方案复杂但更安全。

----

**在线程池中使用 ThreadLocal 为什么可能导致内存泄露呢？**

在线程池中线程的存活时间太长，往往都是和程序同生共死的，这样 Thread 持有的 ThreadLocalMap 一直都不会被回收，再加上 ThreadLocalMap 中的 Entry 对 ThreadLocal 是弱引用（WeakReference），所以只要 ThreadLocal 结束了自己的生命周期是可以被回收掉的。

Entry 中的 Value 是被 Entry 强引用的，即便 value 的生命周期结束了，value 也是无法被回收的，导致内存泄露。

---

**线程池中，如何正确使用 ThreadLocal？**

在 finally 代码块中手动清理 ThreadLocal 中的 value，调用 ThreadLocal 的 remove()方法。

----

**ThreadLocal 核心方法**

- 设置 Thread 对应的 Value 值，首次会创建一个 ThreadLocalMap ，添加 ThreadLocal - Value 到 ThreadLocalMap 中，并且绑定 ThreadLocalMap 到当前线程。

	```java
	public void set(T value) {
		Thread t = Thread.currentThread();
		ThreadLocalMap map = getMap(t);
		if (map != null)
			map.set(this, value);
		else
			createMap(t, value);
	}
	```

- 创建 ThreadLocalMap，绑定到当前线程。 

	```java
	void createMap(Thread t, T firstValue) {
		t.threadLocals = new ThreadLocalMap(this, firstValue);
	}
	```

- 通过 ThreadLocalMap 获取当前线程的存储的 Value 值。

	```java
	public T get() {
		Thread t = Thread.currentThread();
		ThreadLocalMap map = getMap(t);
		if (map != null) {
			ThreadLocalMap.Entry e = map.getEntry(this);
			if (e != null) {
				@SuppressWarnings("unchecked")
				T result = (T)e.value;
				return result;
			}
		}
		return setInitialValue();
	}
	```

- 设置 ThreadLocal 的初始化值，未 set(T value) 初次获取 Thread 对应的 Value 值时会调用，即被 setInitialValue 方法调用。需要重写该方法。

	```java
	protected T initialValue() {
		return null;
	}
	```

- 移除当前线程存储的 Value 值。当 ThreadLocal 不在使用，最好在 finally 语句块中，调用 remove() 方法，释放去 Value 的引用，避免内存泄露。

	```java
	public void remove() {
		ThreadLocalMap m = getMap(Thread.currentThread());
		if (m != null)
			m.remove(this);
	}
	```



## 10.14、volatile

**volatile 关键字的两层语义**

一旦一个共享变量（类的成员变量、类的静态成员变量）被 volatile 修饰之后，那么就具备了两层语义：

- 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
- 禁止进行指令重排序。

先看一段代码，假如线程1先执行，线程2后执行：

```JAVA
//线程1
boolean stop = false;
while(!stop){
    doSomething();
}
 
//线程2
stop = true;
```

也许在大多数时候，这段代码能够把线程中断，但是也有可能会导致无法中断线程（虽然这个可能性很小，但是只要一旦发生这种情况就会造成死循环了）。

每个线程在运行过程中都有自己的工作内存，那么线程 1 在运行的时候，会将 stop 变量的值拷贝一份放在自己的工作内存当中。那么当线程 2 更改了stop 变量的值之后，但是还没来得及写入主存当中，线程 2 转去做其他事情了，那么线程 1 由于不知道线程 2 对 stop 变量的更改，因此还会一直循环下去。

但是用 volatile 修饰之后就变得不一样了：

- 使用 volatile 关键字会强制将修改的值立即写入主存；
- 使用 volatile 关键字的话，当线程 2 进行修改时（当然这里包括 2 个操作，修改线程 2 工作内存中的值，然后将修改后的值写入内存），会导致线程 1 的工作内存中缓存变量 stop 的缓存行无效（反映到硬件层的话，就是 CPU 的 L1 或者 L2 缓存中对应的缓存行无效）；
- 由于线程 1 的工作内存中缓存变量 stop 的缓存行无效，所以线程 1 再次读取变量 stop 的值时会去主存读取。

那么线程 1 读取到的就是最新的正确的值。

---

**volatile保证原子性吗？**

```java
public class Test {
    public volatile int inc = 0;

    public void increase() { 
        inc++;
    }
 
    public static void main(String[] args) {
        final Test test = new Test(); 
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                }; 
            }.start();
        }
 
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
```

大家想一下这段程序的输出结果是多少？也许有些朋友认为是 10000。但是事实上运行它会发现每次运行结果都不一致，都是一个小于 10000 的数。

可能有的朋友就会有疑问，不对啊，上面是对变量 inc 进行自增操作，由于 volatile 保证了可见性，那么在每个线程中对 inc 自增完之后，在其他线程中都能看到修改后的值啊，所以有 10 个线程分别进行了 1000 次操作，那么最终 inc 的值应该是 1000 * 10 =  10000。

这里面就有一个误区了，volatile 关键字能保证可见性没有错，但是上面的程序错在没能保证原子性。可见性只能保证每次读取的是最新的值，但是volatile 没办法保证对变量的操作的原子性。

在前面已经提到过，自增操作是不具备原子性的，它包括读取变量的原始值、进行加 1 操作、写入工作内存。那么就是说自增操作的三个子操作可能会分割开执行，就有可能导致下面这种情况出现：假如某个时刻变量 inc 的值为 10，线程 1 对变量进行自增操作，线程 1 先读取了变量 inc 的原始值，然后线程 1 被阻塞了；然后线程 2 对变量进行自增操作，线程 2 也去读取变量 inc 的原始值，由于线程 1 只是对变量 inc 进行读取操作，而没有对变量进行修改操作，所以不会导致线程 2 的工作内存中缓存变量 inc 的缓存行无效，所以线程 2 会直接去主存读取 inc 的值，发现 inc 的值时 10，然后进行加 1 操作，并把 11 写入工作内存，最后写入主存。然后线程 1 接着进行加 1 操作，由于已经读取了 inc 的值，注意此时在线程 1 的工作内存中 inc 的值仍然为 10，所以线程 1 对 inc 进行加 1 操作后 inc 的值为 11，然后将 11 写入工作内存，最后写入主存。那么两个线程分别进行了一次自增操作后，inc 只增加了 1。

解释到这里，可能有朋友会有疑问，不对啊，前面不是保证一个变量在修改volatile变量时，会让缓存行无效吗？然后其他线程去读就会读到新的值，对，这个没错。这个就是上面的happens-before规则中的volatile变量规则，但是要注意，线程1对变量进行读取操作之后，被阻塞了的话，并没有对inc值进行修改。然后虽然volatile能保证线程2对变量inc的值读取是从内存中读取的，但是线程1没有进行修改，所以线程2根本就不会看到修改的值。根源就在这里，自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的。

在java 1.5的java.util.concurrent.atomic包下提供了一些原子操作类，即对基本数据类型的自增（加1操作），自减（减1操作）、以及加法操作（加一个数），减法操作（减一个数）进行了封装，保证这些操作是原子性操作。atomic是利用CAS来实现原子性操作的（Compare And Swap），CAS实际上是利用处理器提供的CMPXCHG指令实现的，而处理器执行CMPXCHG指令是一个原子性操作。

---

**volatile能保证有序性吗？**

在前面提到volatile关键字能禁止指令重排序，所以volatile能在一定程度上保证有序性。

volatile关键字禁止指令重排序有两层意思：

- 当程序执行到volatile变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经对后面的操作可见；在其后面的操作肯定还没有进行；
- 在进行指令优化时，不能将在对volatile变量访问的语句放在其后面执行，也不能把volatile变量后面的语句放到其前面执行。

可能上面说的比较绕，举个简单的例子：

```java
//x、y为非volatile变量
//flag为volatile变量
x = 2;        //语句1
y = 0;        //语句2
flag = true;  //语句3
x = 4;         //语句4
y = -1;       //语句5
```

由于 flag 变量为 volatile 变量，那么在进行指令重排序的过程的时候，不会将语句3放到语句1、语句2前面，也不会讲语句3放到语句4、语句5后面。但是要注意语句1和语句2的顺序、语句4和语句5的顺序是不作任何保证的。并且volatile关键字能保证，执行到语句3时，语句1和语句2必定是执行完毕了的，且语句1和语句2的执行结果对语句3、语句4、语句5是可见的。

```java
//线程1:
context = loadContext();   //语句1
inited = true;             //语句2
 
//线程2:
while(!inited ){
  sleep()
}
doSomethingwithconfig(context);
```

前面举这个例子的时候，提到有可能语句2会在语句1之前执行，那么久可能导致context还没被初始化，而线程2中就使用未初始化的context去进行操作，导致程序出错。这里如果用volatile关键字对inited变量进行修饰，就不会出现这种问题了，因为当执行到语句2时，必定能保证context已经初始化完毕。

---

**volatile 的原理和实现机制**

前面讲述了源于 volatile 关键字的一些使用，下面我们来探讨一下 volatile 到底如何保证可见性和禁止指令重排序的。

下面这段话摘自《深入理解Java虚拟机》：

“观察加入 volatile 关键字和没有加入 volatile 关键字时所生成的汇编代码发现，加入 volatile 关键字时，会多出一个 lock 前缀指令”

lock 前缀指令实际上相当于一个内存屏障（也成内存栅栏），内存屏障会提供 3 个功能：

- 它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；即在执行到内存屏障这句指令时，在它前面的操作已经全部完成；
- 它会强制将对缓存的修改操作立即写入主存；
- 如果是写操作，它会导致其他 CPU 中对应的缓存行无效。

---

**使用 volatile 关键字的场景**

synchronized 关键字是防止多个线程同时执行一段代码，那么就会很影响程序执行效率，而 volatile 关键字在某些情况下性能要优于 synchronized，但是要注意 volatile 关键字是无法替代 synchronized 关键字的，因为 volatile 关键字无法保证操作的原子性。通常来说，使用 volatile 必须具备以下 2个条件：

- 对变量的写操作不依赖于当前值
- 该变量没有包含在具有其他变量的不变式中

实际上，这些条件表明，可以被写入 volatile 变量的这些有效值独立于任何程序的状态，包括变量的当前状态。

事实上，我的理解就是上面的 2 个条件需要保证操作是原子性操作，才能保证使用 volatile 关键字的程序在并发时能够正确执行。

---

**synchronized 和 volatile 的区别是什么？**

- volatile 本质是在告诉 jvm 当前变量在寄存器（工作内存）中的值是不确定的，需要从主存中读取； synchronized 则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住。
- volatile 仅能使用在变量级别；synchronized 则可以使用在变量、方法、和类级别的。
- volatile 仅能实现变量的修改可见性，不能保证原子性；而 synchronized 则可以保证变量的修改可见性和原子性。
- volatile 不会造成线程的阻塞；synchronized 可能会造成线程的阻塞。
- volatile 标记的变量不会被编译器优化；synchronized 标记的变量可以被编译器优化。



## 10.15、Lock

Lock 是 JKD 1.5后新增的。

Lock和ReadWriteLock是两大锁的根接口，Lock代表实现类是ReentrantLock（可重入锁），ReadWriteLock（读写锁）的代表实现类是ReentrantReadWriteLock。

Lock 接口支持那些语义不同（重入、公平等）的锁规则，可以在非阻塞式结构的上下文（包括 hand-over-hand 和锁重排算法）中使用这些规则。主要的实现是 ReentrantLock。

ReadWriteLock 接口以类似方式定义了一些读取者可以共享而写入者独占的锁。此包只提供了一个实现，即 ReentrantReadWriteLock，因为它适用于大部分的标准用法上下文。但程序员可以创建自己的、适用于非标准要求的实现。



### 10.15.1、为什么使用 Lock？

**synchronized 的局限性 与 Lock 的优点**　

- Lock 不是 Java 语言内置的，synchronized 是 Java 语言的关键字，因此是内置特性。Lock 是一个类，通过这个类可以实现同步访问；
- Lock 和 synchronized 有一点非常大的不同，当 synchronized 方法或者 synchronized 代码块执行完之后，系统会自动让线程释放对锁的占用；而 Lock 则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。

如果一个代码块被 synchronized 关键字修饰，当一个线程获取了对应的锁，并执行该代码块时，其他线程便只能一直等待直至占有锁的线程释放锁。事实上，占有锁的线程释放锁一般会是以下三种情况之一：

- 占有锁的线程执行完了该代码块，然后释放对锁的占有；
- 占有锁线程执行发生异常，此时JVM会让线程自动释放锁；
- 占有锁线程进入 WAITING 状态从而释放锁，例如在该线程中调用 wait() 方法等。

试考虑以下三种情况：　

- 在使用 synchronized 关键字的情形下，假如占有锁的线程由于要等待 IO 或者其他原因（比如调用 sleep 方法）被阻塞了，但是又没有释放锁，那么其他线程就只能一直等待，别无他法。这会极大影响程序执行效率。因此，就需要有一种机制可以不让等待的线程一直无期限地等待下去（比如只等待一定的时间 (解决方案：tryLock(long time, TimeUnit unit)) 或者 能够响应中断 (解决方案：lockInterruptibly())），这种情况可以通过 Lock 解决。
- 当多个线程读写文件时，读操作和写操作会发生冲突现象，写操作和写操作也会发生冲突现象，但是读操作和读操作不会发生冲突现象。但是如果采用 synchronized 关键字实现同步的话，就会导致一个问题，即当多个线程都只是进行读操作时，也只有一个线程在可以进行读操作，其他线程只能等待锁的释放而无法进行读操作。因此，需要一种机制来使得当多个线程都只是进行读操作时，线程之间不会发生冲突。同样地，Lock 也可以解决这种情况 (解决方案：ReentrantReadWriteLock) 。
- 可以通过 Lock 得知线程有没有成功获取到锁 (解决方案：ReentrantLock) ，但这个是 synchronized 无法办到的。

上面提到的三种情形，都可以通过 Lock 来解决，但 synchronized 关键字却无能为力。Lock 是 java.util.concurrent.locks 包下的接口，提供了比 synchronized 关键字更广泛的锁操作，它能以更优雅的方式处理线程同步问题。也就是说，Lock 提供了比 synchronized 更多的功能。



### 10.15.2、Lock 接口实现类

Lock 接口有6个方法：

```java
// 获取锁  
void lock()   

// 如果当前线程未被中断，则获取锁，可以响应中断  
void lockInterruptibly()   

// 返回绑定到此 Lock 实例的新 Condition 实例  
Condition newCondition()   

// 仅在调用时锁为空闲状态才获取该锁，可以响应中断  
boolean tryLock()   

// 如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁  
boolean tryLock(long time, TimeUnit unit)   

// 释放锁  
void unlock() 
```

lock()、tryLock()、tryLock(long time, TimeUnit unit) 和 lockInterruptibly() 都是用来获取锁的。unLock() 方法是用来释放锁的。newCondition() 返回绑定到此 Lock 的新的 Condition 实例 ，用于线程间的协作，详细内容请查找关键词：线程间通信与协作。

---

**lock()**

lock() 方法是平常使用得最多的一个方法，就是用来获取锁。如果锁已被其他线程获取，则进行等待。由于使用 Lock 必须主动去释放锁，因此，使用 Lock 最好在 try…catch… 块中进行，并且将释放锁的操作放在 finally 块中，以保证锁一定被被释放，防止死锁的发生。通常使用 Lock 来进行同步的话，是以下面这种形式去使用的：

```java
Lock lock = ...;
lock.lock();
try{
    //处理任务
}catch(Exception ex){

}finally{
    lock.unlock();   //释放锁
}
```

---

**tryLock() & tryLock(long time, TimeUnit unit)**

tryLock() 方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回 true；如果获取失败（即锁已被其他线程获取），则返回 false，也就是说，这个方法无论如何都会立即返回（在拿不到锁时不会一直在那等待）。

tryLock(long time, TimeUnit unit) 方法和 tryLock() 方法是类似的，只不过区别在于这个方法在拿不到锁时会等待一定的时间，在时间期限之内如果还拿不到锁，就返回 false，同时可以响应中断。如果一开始拿到锁或者在等待期间内拿到了锁，则返回 true。

一般情况下，通过 tryLock 来获取锁时是这样使用的：

```java
Lock lock = ...;
if(lock.tryLock()) {
     try{
         //处理任务
     }catch(Exception ex){

     }finally{
         lock.unlock();   //释放锁
     } 
}else {
    //如果不能获取锁，则直接做其他事情
}
```

----

**lockInterruptibly()　**

lockInterruptibly() 方法比较特殊，当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。例如，当两个线程同时通过 lock.lockInterruptibly() 想获取某个锁时，假若此时线程 A 获取到了锁，而线程 B 只有在等待，那么对线程 B 调用threadB.interrupt() 方法能够中断线程 B 的等待过程。

由于 lockInterruptibly() 的声明中抛出了异常，所以lock.lockInterruptibly() 必须放在try块中或者在调用 lockInterruptibly() 的方法外声明抛出 InterruptedException，但推荐使用后者，原因稍后阐述。因此，lockInterruptibly()一般的使用形式如下：

```java
public void method() throws InterruptedException {
    lock.lockInterruptibly();
    try {  
     //.....
    }
    finally {
        lock.unlock();
    }  
}
```

注意，当一个线程获取了锁之后，是不会被 interrupt() 方法中断的。因为 interrupt() 方法只能中断阻塞过程中的线程而不能中断正在运行过程中的线程。与 synchronized 相比，当一个线程处于等待某个锁的状态，是无法被中断的，只有一直等待下去。



### 10.15.3、ReentrantLock

ReentrantLock，即可重入锁。ReentrantLock 是唯一实现了 Lock 接口的类，并且 ReentrantLock 提供了更多的方法。

构造方法（不带参数和带参数 true： 公平锁； false: 非公平锁）：

```java
/**
  * Creates an instance of {@code ReentrantLock}.
  * This is equivalent to using {@code ReentrantLock(false)}.
  */
public ReentrantLock() {
    sync = new NonfairSync();
}

/**
  * Creates an instance of {@code ReentrantLock} with the
  * given fairness policy.
  *
  * @param fair {@code true} if this lock should use a fair ordering policy
  */
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```

```java
import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReentrantLock;
public class LockThread {
    Lock lock = new ReentrantLock(); 
    public void lock(String name) {  
        // 获取锁  
        lock.lock();  
        try {  
            System.out.println(name + " get the lock");  
            // 访问此锁保护的资源  
        } finally {  
            // 释放锁  
            lock.unlock();  
            System.out.println(name + " release the lock");  
        }  
    }  

    public static void main(String[] args) {
        LockThread lt = new LockThread();  
        new Thread(() -> lt.lock("A")).start();  
        new Thread(() -> lt.lock("B")).start();  
    }
}
```

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9pbWcyMDE4LmNuYmxvZ3MuY29tL2Jsb2cvODg1ODU5LzIwMTkwNC84ODU4NTktMjAxOTA0MjgxNDQxMDY5MDQtMjIzNDAyODMxLnBuZw?x-oss-process=image/format,png)

从执行结果可以看出，A 线程和 B 线程同时对资源加锁，A 线程获取锁之后，B 线程只好等待，直到 A 线程释放锁 B 线程才获得锁。

总结一下，也就是说 **Lock** 提供了比 **synchronized** 更多的功能。但是要注意以下几点：

- Synchronized 是 Java 语言的关键字，因此是内置特性，而 Lock 不是 Java 语言内置的，在 JVM 层面，它是一个接口，通过实现类可以实现同步访问。
- Synchronized 是在 JVM 层面上实现的，不但可以通过一些监控工具监控 Synchronized 的锁定，而且在代码执行时出现异常，JVM 会自动释放锁定，但是使用 Lock 则不行，lock是通过代码实现的，要保证锁定一定会被释放，就必须将 unLock() 放到 finally{} 中，如果为及时释放，可能会造成死锁。
- 在资源竞争不是很激烈的情况下，Synchronized 的性能要优于 ReetrantLock，但是在资源竞争很激烈的情况下，Synchronized 的性能会下降几十倍，但是 ReetrantLock 的性能能维持常态，所以 Lock 锁适合大量同步的代码的同步问题，synchronized 锁适合代码少量的同步问题。



### 10.15.4、ReadWriteLock

读写锁，可以分别获取读锁或写锁。也就是说将数据的读写操作分开，分成 2 个锁来分配给线程，从而使得多个线程可以同时进行读操作。读锁使用共享模式；写锁使用独占模式；读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的。当有读锁时，写锁就不能获得；而当有写锁时，除了获得写锁的这个线程可以获得读锁外，其他线程不能获得读锁。

ReadWriteLock 接口只有两个方法：

```java
//返回用于读取操作的锁  
Lock readLock()   
//返回用于写入操作的锁  
Lock writeLock() 
```

三个线程同时对一个共享数据进行读写:

```java
class Queue {
    //共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
    private Object data = null;

    ReadWriteLock lock = new ReentrantReadWriteLock();

    // 读数据
    public void get() {
        // 加读锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to read data!");
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + " have read data :" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放读锁
            lock.readLock().unlock();
        }
    }

    // 写数据
    public void put(Object data) {
        // 加写锁
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to write data!");
            Thread.sleep((long) (Math.random() * 1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " have write data: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放写锁
            lock.writeLock().unlock();
        }

    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        final Queue queue = new Queue();
        //一共启动6个线程，3个读线程，3个写线程
        for (int i = 0; i < 3; i++) {
            //启动1个读线程
            new Thread() {
                public void run() {
                    while (true) {
                        queue.get();
                    }
                }

            }.start();
            //启动1个写线程
            new Thread() {
                public void run() {
                    while (true) {
                        queue.put(new Random().nextInt(10000));
                    }
                }
            }.start();
        }
    }
}
```

执行结果

<img src="Interview.assets/885859-20190428144431395-1738884484.png" alt="img" style="zoom:80%;float:left" />



### 10.15.5、锁的相关概念介绍

**可重入锁**

如果锁具备可重入性，则称作为可重入锁 。像 synchronized 和 ReentrantLock 都是可重入锁。举个简单的例子，当一个线程执行到某个synchronized 方法时，比如说method1，而在method1 中会调用另外一个 synchronized 方法 method2，此时线程不必重新去申请锁，而是可以直接执行方法 method2。

```java
class MyClass {
    public synchronized void method1() {
        method2();
    }

    public synchronized void method2() {}
}
```

上述代码中的两个方法 method1 和 method2 都用 synchronized 修饰了。假如某一时刻，线程 A 执行到了 method1，此时线程 A 获取了这个对象的锁，而由于 method2 也是 synchronized 方法，假如 synchronized 不具备可重入性，此时线程 A 需要重新申请锁。但是，这就会造成死锁，因为线程 A 已经持有了该对象的锁，而又在申请获取该对象的锁，这样就会线程 A 一直等待永远不会获取到的锁。而由于 synchronized 和 Lock 都具备可重入性，所以不会发生上述现象。

---

**不可重入锁**

与可重入锁相反：

```java
public class NonLockDemo implements Runnable {
    Lock lock = new NonReentrantLock();
    @Override
    public void run() {
        set();
    }
    public  void set() {
        try {
            lock.lock();
            System.out.println("set 方法");
            get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();// 必须在finally中释放
        }
    }

    public void get() {
        try {
            boolean b = lock.tryLock();
            if (b) {
                System.out.println("get 方法");
            } else {
                System.out.println("get 方法获取锁失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        NonLockDemo nonLockDemo = new NonLockDemo();
        new Thread(nonLockDemo).start();
    }
}
```

同一个线程先调用set方法并获取到锁后继续调用get方法，此时set方法还未执行所得释放，在get方法中尝试获取锁时返回false。
![在这里插入图片描述](../Images/Java/20200708112550464-162821912389915.png)

---

**可中断锁**

顾名思义，可中断锁就是可以响应中断的锁。在 Java 中，synchronized 就不是可中断锁，而 Lock 是可中断锁。

如果某一线程 A 正在执行锁中的代码，另一线程 B 正在等待获取该锁，可能由于等待时间过长，线程 B 不想等待了，想先处理其他事情，我们可以让它中断自己或者在别的线程中中断它，这种就是可中断锁。在前面演示 tryLock(long time, TimeUnit unit) 和 lockInterruptibly() 的用法时已经体现了Lock 的可中断性。

---

**公平锁**

公平锁即尽量以请求锁的顺序来获取锁。比如，同是有多个线程在等待一个锁，当这个锁被释放时，等待时间最久的线程（最先请求的线程）会获得该所，这种就是公平锁。而非公平锁则无法保证锁的获取是按照请求锁的顺序进行的，这样就可能导致某个或者一些线程永远获取不到锁。

非公平锁性能高于公平锁性能。首先，在恢复一个被挂起的线程与该线程真正运行之间存在着严重的延迟。而且，非公平锁能更充分的利用cpu的时间片，尽量的减少 cpu 空闲的状态时间。

在Java中，synchronized 就是非公平锁，它无法保证等待的线程获取锁的顺序。而对于 ReentrantLock 和 ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为公平锁。



## 10.16、Atomic

Atomic 包是 java.util.concurrent 下的另一个专门为线程安全设计的 Java 包，包含多个原子操作类。这个包里面提供了一组原子变量类。其基本的特性就是在多线程环境下，当有多个线程同时执行这些类的实例包含的方法时，具有排他性，即当某个线程进入方法，执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，一直等到该方法执行完成，才由 JVM 从等待队列中选择一个另一个线程进入，这只是一种逻辑上的理解。实际上是借助硬件的相关指令来实现的，不会阻塞线程(或者说只是在硬件级别上阻塞了)。可以对基本数据、数组中的基本数据、对类中的基本数据进行操作。原子变量类相当于一种泛化的 volatile 变量，能够支持原子的和有条件的读-改-写操作。



### 10.16.1、传统锁的问题

先来看一个例子：计数器（Counter），采用Java里比较方便的锁机制synchronized关键字，初步的代码如下：

```java
class Counter {
	private int value;
 
	public synchronized int getValue() {
		return value;
	}
 
	public synchronized int increment() {
		return ++value;
	}
 
	public synchronized int decrement() {
		return --value;
	}
} 
```

synchronized关键字是基于阻塞的锁机制，也就是说当一个线程拥有锁的时候，访问同一资源的其它线程需要等待，直到该线程释放锁，这里会有些问题：首先，如果被阻塞的线程优先级很高很重要怎么办？其次，如果获得锁的线程一直不释放锁怎么办？（这种情况是非常糟糕的）。还有一种情况，如果有大量的线程来竞争资源，那CPU将会花费大量的时间和资源来处理这些竞争（事实上CPU的主要工作并非这些），同时，还有可能出现一些例如死锁之类的情况，最后，其实锁机制是一种比较粗糙，粒度比较大的机制，相对于像计数器这样的需求有点儿过于笨重，因此，对于这种需求我们期待一种更合适、更高效的线程安全机制。



### 10.16.2、CAS

CAS的英文单词是 Compare and Swap，即比较并替换，是乐观锁的一种实现方式，是一种轻量级锁，JUC 中很多工具类的实现就是基于 CAS 的。

CAS 有三个操作数，内存值 V，旧的预期值 E，要修改的新值 U。当且仅当预期值 E 和内存值 V 相等时，将内存值V修改为U，否则什么都不做。操作的流程如下图所示，线程在读取数据时不进行加锁，在准备写回数据时，比较原值是否修改，若未被其他线程修改则写回，若已被修改，则重新执行读取流程。这是一种乐观策略，认为并发操作并不总会发生。

<img src="../Images/Java/640" alt="img" style="zoom: 67%;" />

看一个例子，解释CAS的实现过程（并非真实的CAS实现）：

```java
class SimulatedCAS {
	private int value;
 
	public synchronized int getValue() {
		return value;
	}
	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if (value == expectedValue)
			value = newValue;
		return oldValue;
	}
}
```

下面是一个用CAS实现的Counter

```java
public class CasCounter {
    private SimulatedCAS value;
 
    public int getValue() {
        return value.getValue();
    }
 
    public int increment() {
        int oldValue = value.getValue();
        while (value.compareAndSwap(oldValue, oldValue + 1) != oldValue)
            oldValue = value.getValue();
        return oldValue + 1;
    }
}
```

但是 CAS 不一定能保证数据没被别的线程修改过，比如很经典的 ABA 问题。

所谓的 ABA 就是说来了一个线程把值改回了 B，又来了一个线程把值又改回了 A，对于这个时候判断的线程，就发现他的值还是 A，所以他就不知道这个值到底有没有被人改过，其实很多场景如果只追求最后结果正确，这是没关系的。

但是实际过程中还是需要记录修改过程的，比如资金修改什么的，你每次修改的都应该有记录，方便回溯。

解决方法：用版本号去保证，比如说，在修改前去查询他原来的值的时候再带一个版本号，每次判断就连值和版本号一起判断，判断成功就给版本号加1。时间戳也可以，查询的时候把时间戳一起查出来，对的上才修改并且更新值的时候一起修改更新时间，这样也能保证。



### 10.16.3、Atomic 类

在 JDK5.0 之前，想要实现无锁无等待的算法是不可能的，除非用本地库，自从有了 Atomic 变量类后，这成为可能。下面这张图是java.util.concurrent.atomic 包下的类结构。

![img](../Images/Java/Center-162821933664920)

- 标量类：AtomicBoolean，AtomicInteger，AtomicLong，AtomicReference
- 数组类：AtomicIntegerArray，AtomicLongArray，AtomicReferenceArray
- 更新器类：AtomicLongFieldUpdater，AtomicIntegerFieldUpdater，AtomicReferenceFieldUpdater
- 复合变量类：AtomicMarkableReference，AtomicStampedReference

第一组AtomicBoolean，AtomicInteger，AtomicLong，AtomicReference这四种基本类型用来处理布尔，整数，长整数，对象四种数据，其内部实现不是简单的使用 synchronized，而是一个更为高效的方式 CAS (compare and swap) + volatile 和 native方法，从而避免了synchronized 的高开销，执行效率大为提升。我们来看个例子，与我们平时 i++ 所对应的原子操作为：getAndIncrement()

```java
public static void main(String[] args) {
	AtomicInteger ai = new AtomicInteger();
	System.out.println(ai);
	ai.getAndIncrement();
	System.out.println(ai);
}
```

我们可以看一下AtomicInteger的实现：

```java
/**
  * Atomically increments by one the current value.
  *
  * @return the previous value
  */
public final int getAndIncrement() {
    return unsafe.getAndAddInt(this, valueOffset, 1);
}
```

这里直接调用一个叫 Unsafe 的类去处理，这个类是用于执行低级别、不安全操作的方法集合。尽管这个类和所有的方法都是公开的（public），但是这个类的使用仍然受限，你无法在自己的 Java 程序中直接使用该类，因为只有授信的代码才能获得该类的实例。所以我们平时的代码是无法使用这个类的，因为其设计的操作过于偏底层，如若操作不慎可能会带来很大的灾难，所以直接禁止普通代码的访问，当然JDK使用是没有问题的。



**Atomic中的CAS**

从前面的解释得知，CAS的原理是拿期望的值和原本的一个值作比较，如果相同则更新成新的值，此处这个“原本的一个值”怎么来，看看AtomicInteger里的实现：

```java
// setup to use Unsafe.compareAndSwapInt for updates
private static final Unsafe unsafe = Unsafe.getUnsafe();
private static final long valueOffset;

static {
    try {
        valueOffset = unsafe.objectFieldOffset
            (AtomicInteger.class.getDeclaredField("value"));
    } catch (Exception ex) { throw new Error(ex); }
}
```

这里用到UnSafe的一个方法objectFieldOffset()，查看源码：

```java
/**
  * Report the location of a given static field, in conjunction with {@link
  * #staticFieldBase}.
  * <p>Do not expect to perform any sort of arithmetic on this offset;
  * it is just a cookie which is passed to the unsafe heap memory accessors.
  *
  * <p>Any given field will always have the same offset, and no two distinct
  * fields of the same class will ever have the same offset.
  *
  * <p>As of 1.4.1, offsets for fields are represented as long values,
  * although the Sun JVM does not use the most significant 32 bits.
  * It is hard to imagine a JVM technology which needs more than
  * a few bits to encode an offset within a non-array object,
  * However, for consistency with other methods in this class,
  * this method reports its result as a long value.
  * @see #getInt(Object, long)
  */
public native long objectFieldOffset(Field f);
```

这个方法是用来拿到我们上文提到的这个“原来的值”的内存地址。是一个本地方法，返回值是 valueOffset。它的参数 field 就是AtomicInteger 里定义的 value 属性：

```java
private volatile int value;
 
/**
  * Creates a new AtomicInteger with the given initial value.
  *
  * @param initialValue the initial value
  */
public AtomicInteger(int initialValue) {
    value = initialValue;
}
 
/**
  * Creates a new AtomicInteger with initial value {@code 0}.
  */
public AtomicInteger() {}
```

value 是一个 volatile 变量，在内存中可见，任何线程都不允许对其进行拷贝，因此 JVM 可以保证任何时刻任何线程总能拿到该变量的最新值。此处 value 的值，可以在 AtomicInteger 类初始化的时候传入，也可以留空，留空则自动赋值为0。

我们再回到CAS，看看getAndIncrement()方法是怎么利用CAS实现的。

```java
/**
  * Atomically increments by one the current value.
  *
  * @return the previous value
  */
public final int getAndIncrement() {
    return unsafe.getAndAddInt(this, valueOffset, 1);
}
```

```java
public final int getAndAddInt(Object o, long offset, int delta) {
    int v;
    do {
        v = getIntVolatile(o, offset);//------------0---------------
    } while (!compareAndSwapInt(o, offset, v, v + delta));//-------------1-------------
    return v;
}
```

```java
/**
  * Atomically update Java variable to <tt>x</tt> if it is currently
  * holding <tt>expected</tt>.
  * @return <tt>true</tt> if successful
  */
public final native boolean compareAndSwapInt(Object o, long offset,//---------------2--------------
                                              int expected,
                                              int x);
```

我稍微解释一下，其实compareAndSwapInt的注释解释的很明确，原子的将变量的值更新为x，如果成功了返回true，我们知道，如果我们创建AtomicInteger实例时不传入参数，则原始变量的值即为0，所以上面//----------0-----------处得到的v的值即为0,1处的代码为：

```java
while(!compareAndSwapInt(o, offset, 0, 1))
```

我们知道offset指向的地址对应的值就是原始变量的初值0，所以与期望的值0相同，所以将初值赋值为1，返回true，取反后为false，循环结束，返回v即更新之前的值0. 这就是类似于i++操作的原子操作的实现，当然最终CAS的实现都是native的，用C语言实现的，我们这里看不到源码.



**CAS线程安全**

说了半天，我们要回归到最原始的问题了：这样怎么实现线程安全呢？请大家自己先考虑一下这个问题，其实我们在语言层面是没有做任何同步的操作的，大家也可以看到源码没有任何锁加在上面，可它为什么是线程安全的呢？这就是Atomic包下这些类的奥秘：语言层面不做处理，我们将其交给硬件—CPU和内存，利用CPU的多处理能力，实现硬件层面的阻塞，再加上volatile变量的特性即可实现基于原子操作的线程安全。所以说，CAS并不是无阻塞，只是阻塞并非在语言、线程方面，而是在硬件层面，所以无疑这样的操作会更快更高效！



# 11、反射机制

## 11.1、反射机制的基本概念

先了解两个概念，编译期和运行期。

==编译期==是指把源码交给编译器编译成计算机可以执行的文件的过程。在 Java 中也就是把 Java 代码编成 class 文件的过程。编译期只是做了一些翻译功能，并没有把代码放在内存中运行起来，而只是把代码当成文本进行操作，比如检查错误。

==运行期==是把编译后的文件交给计算机执行，直到程序运行结束。所谓运行期就把在磁盘中的代码放到内存中执行起来。

Java 反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；这种动态获取信息以及动态调用对象方法的功能称为 Java 语言的反射机制。简单来说，反射机制指的是程序在运行时能够获取自身的信息。在 Java 中，只要给定类的名字，就可以通过反射机制来获得类的所有信息。

Java 反射机制在服务器程序和中间件程序中得到了广泛运用。在服务器端，往往需要根据客户的请求，动态调用某一个对象的特定方法。此外，==在 ORM 中间件的实现中，运用 Java 反射机制可以读取任意一个 JavaBean 的所有属性，或者给这些属性赋值。==

<img src="../Images/Java/5-19121314235A02.png" alt="img" style="zoom:80%;" />

Java 反射机制主要提供了以下功能，这些功能都位于`java.lang.reflect`包。

- 在运行时判断任意一个对象所属的类。
- 在运行时构造任意一个类的对象。
- 在运行时判断任意一个类所具有的成员变量和方法。
- 在运行时调用任意一个对象的方法。
- 生成动态代理。

要想知道一个类的属性和方法，必须先获取到该类的字节码文件对象。获取类的信息时，使用的就是 Class 类中的方法。所以先要获取到每一个字节码文件（.class）对应的 Class 类型的对象。

众所周知，所有 Java 类均继承了 Object 类，在 Object 类中定义了一个 getClass() 方法，该方法返回同一个类型为 Class 的对象。例如，下面的示例代码：

```java
Class labelCls = label1.getClass();    // label1为 JLabel 类的对象
```

利用 Class 类的对象 labelCls 可以访问 labelCls 对象的描述信息、JLabel 类的信息以及基类 Object 的信息。下表列出了通过反射可以访问的信息。

| 类型           | 访问方法                  | 返回值类型         | 说明                                              |
| -------------- | ------------------------- | ------------------ | ------------------------------------------------- |
| 包路径         | getPackage()              | Package 对象       | 获取该类的存放路径                                |
| 类名称         | getName()                 | String 对象        | 获取该类的名称                                    |
| 继承类         | getSuperclass()           | Class 对象         | 获取该类继承的类                                  |
| 实现接口       | getlnterfaces()           | Class 型数组       | 获取该类实现的所有接口                            |
| 构造方法       | getConstructors()         | Constructor 型数组 | 获取所有权限为 public 的构造方法                  |
|                | getDeclaredContruectors() | Constructor 对象   | 获取当前对象的所有构造方法                        |
| 方法           | getMethods()              | Methods 型数组     | 获取所有权限为 public 的方法                      |
|                | getDeclaredMethods()      | Methods 对象       | 获取当前对象的所有方法                            |
| 成员变量       | getFields()               | Field 型数组       | 获取所有权限为 public 的成员变量                  |
|                | getDeclareFileds()        | Field 对象         | 获取当前对象的所有成员变量                        |
| 内部类         | getClasses()              | Class 型数组       | 获取所有权限为 public 的内部类                    |
|                | getDeclaredClasses()      | Class 型数组       | 获取所有内部类                                    |
| 内部类的声明类 | getDeclaringClass()       | Class 对象         | 如果该类为内部类，则返回它的成员类，否则返回 null |

在调用 getFields() 和 getMethods() 方法时将会依次获取权限为 public 的字段和变量，然后将包含从超类中继承到的成员变量和方法。而通过 getDeclareFields() 和 getDeclareMethod() 只是获取在本类中定义的成员变量和方法。

---

**Java 反射机制的优缺点**

优点：

- 能够运行时动态获取类的实例，大大提高系统的灵活性和扩展性。
- 与 Java 动态编译相结合，可以实现无比强大的功能。
- 对于 Java 这种先编译再运行的语言，能够让我们很方便的创建灵活的代码，这些代码可以在运行时装配，无需在组件之间进行源代码的链接，更加容易实现面向对象。

缺点：

- 反射会消耗一定的系统资源，因此，如果不需要动态地创建一个对象，那么就不需要用反射；
- 反射调用方法时可以忽略权限检查，获取这个类的私有方法和属性，因此可能会破坏类的封装性而导致安全问题。



## 11.2、反射机制API

### 11.2.1、java.lang.Class 类

java.lang.Class 类是实现反射的关键所在，Class 类的一个实例表示 Java 的一种数据类型，包括类、接口、枚举、注解（Annotation）、数组、基本数据类型和 void。Class 没有公有的构造方法，Class 实例是由 JVM 在类加载时自动创建的。

在程序代码中获得 Class 实例可以通过如下代码实现：

```java
// 1. 通过类型class静态变量
Class clz1 = String.class;
String str = "Hello";
// 2. 通过对象的getClass()方法
Class clz2 = str.getClass();
// 3. 通过Class的forName()方法
Class clz3 = Class.forName(java.lang.String);
```

每一种类型包括类和接口等，都有一个 class 静态变量可以获得 Class 实例。另外，每一个对象都有 getClass() 方法可以获得 Class 实例，该方法是由 Object 类提供的实例方法。

Class 类提供了很多方法可以获得运行时对象的相关信息，下面的程序代码展示了其中一些方法。

```java
public class ReflectionTest01 {
    public static void main(String[] args) {
        // 获得Class实例
        // 1.通过类型class静态变量
        Class clz1 = String.class;
        String str = "Hello";
        // 2.通过对象的getClass()方法
        Class clz2 = str.getClass();
        // 获得int类型Class实例
        Class clz3 = int.class;
        // 获得Integer类型Class实例
        Class clz4 = Integer.class;
        System.out.println("clz2类名称：" + clz2.getName());
        System.out.println("clz2是否为接口：" + clz2.isInterface());
        System.out.println("clz2是否为数组对象：" + clz2.isArray());
        System.out.println("clz2父类名称：" + clz2.getSuperclass().getName());
        System.out.println("clz2是否为基本类型：" + clz2.isPrimitive());
        System.out.println("clz3是否为基本类型：" + clz3.isPrimitive());
        System.out.println("clz4是否为基本类型：" + clz4.isPrimitive());
    }
}
```

运行结果如下：

```java
clz2类名称：java.lang.String
clz2是否为接口：false
clz2是否为数组对象：false
clz2父类名称：java.lang.Object
clz2是否为基本类型：false
clz3是否为基本类型：true
clz4是否为基本类型：false
```

注意上述代码第 10 行和第 12 行的区别。int 是基本数据类型，所以输出结果为 true；Integer 是类，是引用数据类型，所以输出结果为 false。



### 11.2.2、java.lang.reflect 包

java.lang.reflect 包提供了反射中用到类，主要的类说明如下：

- Constructor 类：提供类的构造方法信息。

- Field 类：提供类或接口中成员变量信息。

- Method 类：提供类或接口成员方法信息。

- Array 类：提供了动态创建和访问 Java 数组的方法。

	- static Object newInstance(Class<?> componentType, int…length)：创建一个具有指定的元素类型、指定维度的新数组。
	- static xxx getXxx(Object array, int index)：返回 array 数组中第 index 个元素。其中 xxx 是各种基本数据类型，如果数组元素是引用类型，则该方法变为 get(Object array, int index)。
	- static void setXxx(Object array, int index, xxx val)：将 array 数组中第 index 个元素的值设为 val。
	
	其中 xxx 是各种基本数据类型，如果数组元素是引用类型，则该方法变成 set(Object array, int index, Object val)。
	
- Modifier 类：提供类和成员访问修饰符信息。

示例代码如下：

```java
public class ReflectionTest02 {
    public static void main(String[] args) {
        try {
            // 动态加载xx类的运行时对象
            Class c = Class.forName("java.lang.String");
            // 获取成员方法集合
            Method[] methods = c.getDeclaredMethods();
            // 遍历成员方法集合
            for (Method method : methods) {
                // 打印权限修饰符，如public、protected、private
                System.out.print(Modifier.toString(method.getModifiers()));
                // 打印返回值类型名称
                System.out.print(" " + method.getReturnType().getName() + " ");
                // 打印方法名称
                System.out.println(method.getName() + "();");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定类");
        }
    }
}
```

上述代码第 5 行是通过 Class 的静态方法`forName(String)`创建某个类的运行时对象，其中的参数是类全名字符串，如果在类路径中找不到这个类则抛出 ClassNotFoundException 异常，见代码第 17 行。

代码第 7 行是通过 Class 的实例方法 getDeclaredMethods() 返回某个类的成员方法对象数组。代码第 9 行是遍历成员方法集合，其中的元素是 Method 类型。

代码第 11 行的`method.getModifiers()`方法返回访问权限修饰符常量代码，是 int 类型，例如 1 代表 public，这些数字代表的含义可以通过`Modifier.toString(int)`方法转换为字符串。代码第 13 行通过 Method 的 getReturnType() 方法获得方法返回值类型，然后再调用 getName() 方法返回该类型的名称。代码第 15 行`method.getName()`返回方法名称。



## 11.3、通过反射访问构造方法

为了能够**动态获取**对象构造方法的信息，首先需要通过下列方法之一创建一个 `Constructor` 类型的对象或者数组。

- getConstructors()
- getConstructor(Class<?>…parameterTypes)
- getDeclaredConstructors()
- getDeclaredConstructor(Class<?>...parameterTypes)

==如果是访问指定的构造方法，需要根据该构造方法的入口参数的类型来访问。==例如，访问一个入口参数类型依次为 int 和 String 类型的构造方法，下面的两种方式均可以实现。

```java
objectClass.getDeclaredConstructor(int.class,String.class);
objectClass.getDeclaredConstructor(new Class[]{int.class,String.class});
```

创建的每个 Constructor 对象表示一个构造方法，然后利用 Constructor 对象的方法操作构造方法。Constructor 类的常用方法如下表所示。

| 方法名称                       | 说明                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| isVarArgs()                    | 查看该构造方法是否允许带可变数量的参数，如果允许，返回 true，否则返回 false |
| getParameterTypes()            | 按照声明顺序以 Class 数组的形式获取该构造方法各个参数的类型  |
| getExceptionTypes()            | 以 Class 数组的形式获取该构造方法可能抛出的异常类型          |
| newInstance(Object … initargs) | 通过该构造方法利用指定参数创建一个该类型的对象，如果未设置参数则表示 采用默认无参的构造方法 |
| setAccessiable(boolean flag)   | 如果该构造方法的权限为 private，默认为不允许通过反射利用 netlnstance() 方法创建对象。如果先执行该方法，并将入口参数设置为 true，则允许创建对象 |
| getModifiers()                 | 获得可以解析出该构造方法所采用修饰符的整数                   |

通过 java.lang.reflect.Modifier 类可以解析出方法的返回值所表示的修饰符信息。在该类中提供了一系列用来解析的静态方法，既可以查看是否被指定的修饰符修饰，还可以字符串的形式获得所有修饰符。下表列出了 Modifier 类的常用静态方法。

| 静态方法名称         | 说明                                                     |
| -------------------- | -------------------------------------------------------- |
| isStatic(int mod)    | 如果使用 static 修饰符修饰则返回 true，否则返回 false    |
| isPublic(int mod)    | 如果使用 public 修饰符修饰则返回 true，否则返回 false    |
| isProtected(int mod) | 如果使用 protected 修饰符修饰则返回 true，否则返回 false |
| isPrivate(int mod)   | 如果使用 private 修饰符修饰则返回 true，否则返回 false   |
| isFinal(int mod)     | 如果使用 final 修饰符修饰则返回 true，否则返回 false     |
| toString(int mod)    | 以字符串形式返回所有修饰符                               |

例如，下列代码判断对象 con 所代表的构造方法是否被 public 修饰，以及以字符串形式获取该构造方法的所有修饰符。

```java
int modifiers = con.getModifiers();    // 获取构造方法的修饰符整数
boolean isPublic = Modifier.isPublic(modifiers);    // 判断修饰符整数是否为public 
string allModifiers = Modifier.toString(modifiers);
```



## 11.4、通过反射执行方法

要**动态获取**一个对象方法的信息，首先需要通过下列方法之一创建一个 `Method` 类型的对象或者数组。

- getMethods()
- getMethods(String name,Class<?> …parameterTypes)
- getDeclaredMethods()
- getDeclaredMethods(String name,Class<?>...parameterTypes)

==如果是访问指定的构造方法，需要根据该方法的入口参数的类型来访问。==例如，访问一个名称为 max，入口参数类型依次为 int 和 String 类型的方法。

下面的两种方式均可以实现：

```java
objectClass.getDeclaredConstructor("max",int.class,String.class);
objectClass.getDeclaredConstructor("max",new Class[]{int.class,String.class});
```

Method类的常用方法

| 方法名称                         | 说明                                                         |
| -------------------------------- | ------------------------------------------------------------ |
| getName()                        | 获取该方法的名称                                             |
| getParameterType()               | 按照声明顺序以 Class 数组的形式返回该方法各个参数的类型      |
| getReturnType()                  | 以 Class 对象的形式获得该方法的返回值类型                    |
| getExceptionTypes()              | 以 Class 数组的形式获得该方法可能抛出的异常类型              |
| invoke(Object obj,Object...args) | 利用 args 参数执行指定对象 obj 中的该方法，返回值为 Object 类型 |
| isVarArgs()                      | 查看该方法是否允许带有可变数量的参数，如果允许返回 true，否则返回 false |
| getModifiers()                   | 获得可以解析出该方法所采用修饰符的整数                       |
| setAccessible(boolean flag)      | 此方法可以设置是否忽略权限直接运行 private 等私有权限的方法  |



## 11.5、通过反射访问成员变量

通过下列任意一个方法访问成员变量时将返回 Field 类型的对象或数组。

- getFields()
- getField(String name)
- getDeclaredFields()
- getDeclaredField(String name)

上述方法返回的 Field 对象代表一个成员变量。例如，要访问一个名称为 price 的成员变量，示例代码如下：

```java
object.getDeciaredField("price");
```

Field 类的常用方法如下表所示：

| 方法名称                          | 说明                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| getName()                         | 获得该成员变量的名称                                         |
| getType()                         | 获取表示该成员变量的 Class 对象                              |
| get(Object obj)                   | 获得指定对象 obj 中成员变量的值，返回值为 Object 类型        |
| set(Object obj, Object value)     | 将指定对象 obj 中成员变量的值设置为 value                    |
| getlnt(0bject obj)                | 获得指定对象 obj 中成员类型为 int 的成员变量的值             |
| setlnt(0bject obj, int i)         | 将指定对象 obj 中成员变量的值设置为 i                        |
| getFloat(Object obj)              | 获得指定对象 obj 中成员类型为 float 的成员变量的值           |
| setFloat(Object obj, float f)     | 将指定对象 obj 中成员变量的值设置为 f                        |
| getBoolean(Object obj)            | 获得指定对象 obj 中成员类型为 boolean 的成员变量的值         |
| setBoolean(Object obj, boolean b) | 将指定对象 obj 中成员变量的值设置为 b                        |
| setAccessible(boolean flag)       | 此方法可以设置是否忽略权限直接访问 private 等私有权限的成员变量 |
| getModifiers()                    | 获得可以解析出该方法所采用修饰符的整数                       |



## 11.6、通过反射操作泛型

从Java 1.5 版本开始，Java 的 Class 类增加了泛型功能，从而允许使用泛型来限制 Class 类。例如，String.class 的类型实际上是 Class<String>。如果 Class 对应的类暂时未知，则使用 Class<?>。通过在反射中使用泛型，可以避免使用反射生成的对象需要强制类型转换。

### 11.6.1、泛型和 Class 类

使用 Class<T> 泛型可以避免强制类型转换。例如，下面提供一个简单的对象工厂，该对象工厂可以根据指定类来提供该类的实例。

```java
public class ObjectFactory {
    public static Object getInstance(String clsName) {
        try {
            // 创建指定类对应的Class对象
            Class cls = Class.forName(clsName);
            // 返回使用该Class对象创建的实例
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

上面程序中第 5 、7 行代码根据指定的字符串类型创建了一个新对象，但这个对象的类型是 Object，因此当需要使用 ObjectFactory 的 getInstance() 方法来创建对象时，代码如下：

```java
// 获取实例后需要强制类型转换
Date d = (Date)ObjectFactory.getInstance("java.util.Date");
```

或

```java
JFrame f = (JFrame)ObjectFactory .getInstance("java.util.Date");
```

上面代码在编译时不会有任何问题，但运行时将抛出 ClassCastException（强制类型转换异常），因为程序试图将一个 Date 对象转换成 JFrame 对象。

如果将上面的 ObjectFactory 工厂类改写成使用泛型后的 Class，就可以避免这种情况。

```java
public class ObjectFactory2 {
    public static <T> T getInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        // 获取实例后无须类型转换
        Date d = CrazyitObjectFactory2.getInstance(Date.class);
        JFrame f = CrazyitObjectFactory2.getInstance(JFrame.class);
    }
}
```

在上面程序的 getInstance() 方法中传入一个 Class<T> 参数，这是一个泛型化的 Class 对象，调用该 Class 对象的 newInstance() 方法将返回一个 T 对象，如程序中第 4 行代码所示。接下来当使用 ObjectFactory2 工厂类的 getInstance() 方法来产生对象时，无须使用强制类型转换，系统会执行更严格的检查，不会出现 ClassCastException 运行时异常。

使用 Array 类来创建数组时，曾经看到如下代码：

```java
// 使用Array的newInstance方法来创建一个数组
Object arr = Array.newInstance(String.class, 10);
```

对于上面的代码其实使用并不是非常方便，因为 newInstance() 方法返回的确实是一个 String[] 数组，而不是简单的 Object 对象。如果需要将 arr 对象当成 String[] 数组使用，则必须使用强制类型转换，但是这是不安全的操作。

奇怪的是，Array 的 newInstance() 方法签名（方法签名由方法名称和参数列表组成）为如下形式：

```java
public static Object newInstance(Class<?> componentType, int... dimensions)
```

在这个方法签名中使用了 Class<?> 泛型，但并没有真正利用这个泛型。如果将该方法签名改为如下形式：

```java
public static <T> T[] newInstance(Class<T> componentType, int length)
```

这样就可以在调用该方法后无需强制类型转换了。

为了示范泛型的优势，可以对 Array 的 newInstance() 方法进行包装。

```java
public class CrazyitArray {
    // 对Array的newInstance方法进行包装
    @SuppressWarnings("unchecked")
    public static <T> T[] newInstance(Class<T> componentType, int length) {
        return (T[]) Array.newInstance(componentType, length); 
    }
    
    public static void main(String[] args) {
        // 使用 CrazyitArray 的 newInstance()创建一维数组
        String[] arr = CrazyitArray.newInstance(String.class, 10);
        // 使用 CrazyitArray 的 newInstance()创建二维数组
        // 在这种情况下，只要设置数组元素的类型是int[]即可
        int[][] intArr = CrazyitArray.newInstance(int[].class, 5);
        arr[5] = "C语言中文网Java教程";
        // intArr是二维数组，初始化该数组的第二个数组元素
        // 二维数组的元素必须是一维数组
        intArr[1] = new int[]{ 23, 12 };
        System.out.println(arr[5]);
        System.out.println(intArr[1][1]);
    }
}
```

上面程序中第 4、5、6、10 和 13 定义的 newInstance() 方法对 Array 类提供的 newInstance() 方法进行了包装，将方法签名改成了 `public static  T[] newInstance(Class componentType, int length)`，这就保证程序通过该 newInstance() 方法创建数组时的返回值就是数组对象，而不是 Object 对象，从而避免了强制类型转换。

提示：@SuppressWarnings("unchecked") 告诉编译器忽略 unchecked 警告信息，如使用 List，ArrayList 等未进行参数化产生的警告信息。程序在第 5 行代码处将会有一个 unchecked 编译警告，所以程序使用了 @SuppressWarnings 来抑制这个警告信息。



### 11.6.2、使用反射来获取泛型信息

通过指定类对应的 Class 对象，可以获得该类里包含的所有成员变量，不管该成员变量是使用 private 修饰，还是使用 public 修饰。获得了成员变量对应的 Field 对象后，就可以很容易地获得该成员变量的数据类型，即使用如下代码即可获得指定成员变量的类型。

```java
// 获取成员变量 f 的类型
Class<?> a = f.getType();
```

但这种方式只对普通类型的成员变量有效。如果该成员变量的类型是有泛型类型的类型，如 Map<String, Integer>类型，则不能准确地得到该成员变量的泛型参数。

为了获得指定成员变量的泛型类型，应先使用如下方法来获取该成员变量的泛型类型。

```java
// 获得成员变量f的泛型类型
Type gType = f.getGenericType();
```

然后将 Type 对象强制类型转换为 ParameterizedType 对象，ParameterizedType 代表被参数化的类型，也就是增加了泛型限制的类型。ParameterizedType 类提供了如下两个方法。

- getRawType()：返回没有泛型信息的原始类型。
- getActualTypeArguments()：返回泛型参数的类型。

下面是一个获取泛型类型的完整程序。

```java
public class GenericTest {
    private Map<String, Integer> score;
    
    public static void main(String[] args) throws Exception {
        Class<GenericTest> clazz = GenericTest.class;
        Field f = clazz.getDeclaredField("score");
        // 直接使用getType()取出类型只对普通类型的成员变量有效
        Class<?> a = f.getType();
        // 下面将看到仅输出java.util.Map
        System.out.println("score 的类型是：" + a);
        // 获得成员变量f的泛型类型
        Type gType = f.getGenericType();
        // 如果 gType 类型是 ParameterizedType对象
        if (gType instanceof ParameterizedType) {
            // 强制类型转换
            ParameterizedType pType = (ParameterizedType) gType;
            // 获取原始类型
            Type rType = pType.getRawType();
            System.out.println("原始类型是：" + rType);
            // 取得泛型类型的泛型参数
            Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("泛型信息是:");
            for (int i = 0; i < tArgs.length; i++) {
                System.out.println("第" + i + "个泛型类型是：" + tArgs[i]);
            }
        } else {
            System.out.println("获取泛型类型出错!");
        }
    }
}
```

上面程序中的第 12、16、18 和 21 行代码就是取得泛型类型的关键代码。运行上面程序，将看到如下运行结果：

```
score 的类型是：interface java.util.Map
原始类型是：interface java.util.Map
泛型信息是:
第0个泛型类型是：class java.lang.String
第1个泛型类型是：class java.lang.Integer
```

从上面的运行结果可以看出，使用 getType() 方法只能获取普通类型的成员变量的数据类型。对于增加了泛型的成员变量，应该使用 getGenericType() 方法来取得其类型。

提示：Type 也是 java.lang.reflect 包下的一个接口，该接口代表所有类型的公共高级接口，Class 是 Type 接口的实现类。Type 包括原始类型、参数化类型、数组类型、类型变量和基本类型等。



# 12、输入/输出（I/O）流

在变量、数组、对象和集合中存储的数据是暂时存在的，一旦程序结束它们就会丢失。为了能够永久地保存程序创建的数据，需要将其保存到磁盘文件中，这样就可以在其他程序中使用它们。Java 的 I/O（输入/输出）技术可以将数据保存到文本文件和二进制文件中， 以达到永久保存数据的要求。

## 12.1、流的概念

在 Java 中所有数据都是使用流读写的。流是一组有序的数据序列，将数据从一个地方带到另一个地方。==根据数据流向的不同，可以分为输入（Input）流和输出（Output）流两种。==

在学习输入和输出流之前，我们要明白为什么应用程序需要输入和输出流。例如，我们平时用的 Office 软件，对于 Word、Excel 和 PPT 文件，我们需要打开文件并读取这些文本，和编辑输入一些文本，这都需要利用输入和输出的功能。在现实生活中，输入和输出的例子比比皆是。

### 12.1.1、什么是输入/输出流

Java 程序通过流来完成输入/输出，所有的输入/输出以流的形式处理。因此要了解 I/O 系统，首先要理解输入/输出流的概念。

输入就是将数据从各种输入设备（包括文件、键盘等）中读取到内存中，输出则正好相反，是将数据写入到各种输出设备（比如文件、显示器、磁盘等）。例如键盘就是一个标准的输入设备，而显示器就是一个标准的输出设备，但是文件既可以作为输入设备，又可以作为输出设备。

数据流是 Java 进行 I/O 操作的对象，它按照不同的标准可以分为不同的类别。

- 按照流的方向主要分为输入流和输出流两大类。
- 数据流按照数据单位的不同分为字节流和字符流。
- 按照功能可以划分为节点流和处理流。

数据流的处理只能按照数据序列的顺序来进行，即前一个数据处理完之后才能处理后一个数据。数据流以输入流的形式被程序获取，再以输出流的形式将数据输出到其它设备。

<img src="../Images/Java/5-200115142HWK.png" alt="输入流模式" style="zoom:80%;" />

<img src="../Images/Java/5-200115142K1644.png" alt="输出流模式" style="zoom:80%;" />



### 12.1.2、输入流

Java 流相关的类都封装在 java.io 包中，而且每个数据流都是一个对象。所有输入流类都是 InputStream 抽象类（字节输入流）和 Reader 抽象类（字符输入流）的子类。其中 InputStream 类是字节输入流的抽象类，是所有字节输入流的父类，其层次结构如下图所示。

<img src="../Images/Java/5-200115145253550.png" alt="InputStream类的层次结构图" style="zoom:80%;" />

InputStream 类中所有方法遇到错误时都会引发 IOException 异常。如下是该类中包含的常用方法。

| 名称                               | 作用                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| int read()                         | 从输入流读入一字节的数据，将它转换成一个 0~ 255 的整数，返回一个整数，如果遇到输入流的结尾返回 -1 |
| int read(byte[] b)                 | 从输入流读取若干字节的数据保存到参数 b 指定的字节数组中，返回的字节数表示读取的字节数，如果遇到输入流的结尾返回 -1 |
| int read(byte[] b,int off,int len) | 从输入流读取若干字节的数据保存到参数 b 指定的字节数组中，其中 off 是指在数组中开始保存数据位置的起始下标，len 是指读取字节的位数。返回的是实际读取的字节数，如果遇到输入流的结尾则返回 -1 |
| void close()                       | 关闭数据流，当完成对数据流的操作之后需要关闭数据流           |
| int available()                    | 返回可以从数据源读取的数据流的位数。                         |
| skip(long n)                       | 从输入流跳过参数 n 指定的字节数目                            |
| boolean markSupported()            | 判断输入流是否可以重复读取（是否可以使用mark和reset），如果可以就返回 true |
| void mark(int readLimit)           | 如果输入流可以被重复读取，从流的当前位置开始设置标记，readLimit 指定可以设置标记的字节数 |
| void reset()                       | 使输入流重新定位到刚才被标记的位置，这样可以重新读取标记过的数据 |

上述最后 3 个方法一般会结合在一起使用，首先使用 markSupported() 判断，如果可以重复读取，则使用 mark(int readLimit) 方法进行标记，标记完成之后可以使用 read() 方法读取标记范围内的字节数，最后使用 reset() 方法使输入流重新定位到标记的位置，继而完成重复读取操作。

Java 中的字符是 Unicode 编码，即双字节的，而 InputerStream 是用来处理单字节的，在处理字符文本时不是很方便。这时可以使用 Java 的文本输入流 Reader 类，该类是字符输入流的抽象类，即所有字符输入流的实现都是它的子类，该类的方法与 InputerSteam 类的方法类似。



### 12.1.3、输出流

在 Java 中所有输出流类都是 OutputStream 抽象类（字节输出流）和 Writer 抽象类（字符输出流）的子类。其中 OutputStream 类是字节输出流的抽象类，是所有字节输出流的父类，其层次结构如下图所示。

<img src="../Images/Java/5-200115151G3J0.png" alt="OutputStream类的层次结构图" style="zoom:80%;" />

OutputStream 类是所有字节输出流的超类，用于以二进制的形式将数据写入目标设备，该类是抽象类，不能被实例化。OutputStream 类提供了一系列跟数据输出有关的方法，如下所示。

| 名称                                 | 作用                                                     |
| ------------------------------------ | -------------------------------------------------------- |
| int write(b)                         | 将指定字节的数据写入到输出流                             |
| int write (byte[] b)                 | 将指定字节数组的内容写入输出流                           |
| int write (byte[] b,int off,int len) | 将指定字节数组从 off 位置开始的 len 字节的内容写入输出流 |
| close()                              | 关闭数据流，当完成对数据流的操作之后需要关闭数据流       |
| flush()                              | 刷新输出流，强行将缓冲区的内容写入输出流                 |



## 12.2、系统流

每个 Java 程序运行时都带有一个系统流，系统流对应的类为 java.lang.System。Sytem 类封装了 Java 程序运行时的 3 个系统流，分别通过 in、out 和 err 变量来引用。这 3 个系统流如下所示：

- System.in：标准输入流，默认设备是键盘。
- System.out：标准输出流，默认设备是控制台。
- System.err：标准错误流，默认设备是控制台。

以上变量的作用域为 public 和 static，因此在程序的任何部分都不需引用 System 对象就可以使用它们。

下面的程序演示了如何使用 System.in 读取字节数组，使用 System.out 输出字节数组。

```java
public class Test01 {
    public static void main(String[] args) {
        byte[] byteData = new byte[100]; // 声明一个字节数组
        System.out.println("请输入英文：");
        try {
            System.in.read(byteData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("您输入的内容如下：");
        for (int i = 0; i < byteData.length; i++) {
            System.out.print((char) byteData[i]);
        }
    }
}
```

该程序的运行结果如下所示：

```
请输入英文：
abcdefg hijklmn opqrst uvwxyz
您输入的内容如下：
abcdefg hijklmn opqrst uvwxyz
```

==System.in 是 InputStream 类的一个对象，==因此上述代码的 System.in.read() 方法实际是访问 InputStream 类定义的 read() 方法。该方法可以从键盘读取一个或多个字符。==对于 System.out 输出流主要用于将指定内容输出到控制台。==

==System.out 和 System.error 是 PrintStream 类的对象。==因为 PrintStream 是一个从 OutputStream 派生的输出流，所以它还执行低级别的 write() 方法。因此，除了 print() 和 println() 方法可以完成控制台输出以外，System.out 还可以调用 write() 方法实现控制台输出。

write() 方法的简单形式如下：

```java
void write(int byteval) throws IOException
```

该方法通过 byteval 参数向文件写入指定的字节。在实际操作中，print() 方法和 println() 方法比 write() 方法更常用。

注意：尽管它们通常用于对控制台进行读取和写入字符，但是这些都是字节流。因为预定义流是没有引入字符流的 Java 原始规范的一部分，所以它们不是字符流而是字节流，但是在 Java 中可以将它们打包到基于字符的流中使用。



## 12.3、字符编码介绍

计算机中，任何的文字都是以指定的编码方式存在的，在 [Java](http://c.biancheng.net/java/) 程序的开发中最常见的是 ISO8859-1、GBK/GB2312、Unicode、 UTF 编码。

Java 中常见编码说明如下：

- ISO8859-1：属于单字节编码，最多只能表示 0~255 的字符范围。
- GBK/GB2312：中文的国标编码，用来表示汉字，属于双字节编码。GBK 可以表示简体中文和繁体中文，而 GB2312 只能表示简体中文。GBK 兼容 GB2312。
- Unicode：是一种编码规范，是为解决全球字符通用编码而设计的。UTF-8 和 UTF-16 是这种规范的一种实现，此编码不兼容 ISO8859-1 编码。Java 内部采用此编码。
- UTF：UTF 编码兼容了 ISO8859-1 编码，同时也可以用来表示所有的语言字符，不过 UTF 编码是不定长编码，每一个字符的长度为 1~6 个字节不等。一般在中文网页中使用此编码，可以节省空间。

在程序中如果处理不好字符编码，就有可能出现乱码问题。例如现在本机的默认编码是 GBK，但在程序中使用了 ISO8859-1 编码，则就会出现字符的乱码问题。就像两个人交谈，一个人说中文，另外一个人说英语，语言不同就无法沟通。为了避免产生乱码，程序编码应与本地的默认编码保持一致。

本地的默认编码可以使用 System 类查看。Java 中 System 类可以取得与系统有关的信息，所以直接使用此类可以找到系统的默认编码。方法如下所示：

```java
public static Properties getProperty()
```

使用上述方法可以查看 JVM 的默认编码，代码如下：

```java
public static void main(String[] args) {
    // 获取当前系统编码
    System.out.println("系统默认编码：" + System.getProperty("file.encoding"));
}
```

运行结果如下：

```
系统默认编码：GBK
```

可以看出，现在操作系统的默认编码是 GBK。

下面通过一个示例讲解乱码的产生。现在本地的默认编码是 GBK，下面通过 ISO8859-1 编码对文字进行编码转换。如果要实现编码的转换可以使用 String 类中的 getBytes(String charset) 方法，此方法可以设置指定的编码，该方法的格式如下：

```java
public byte[] getBytes(String charset);
```

示例代码如下：

```java
public class Test {
    public static void main(String[] args) throws Exception {
        File f = new File("D:" + File.separator + "test.txt");
        // 实例化输出流
        OutputStream out = new FileOutputStream(f);
        // 指定ISO8859-1编码
        byte b[] = "C语言是世界上最好的语言！".getBytes("ISO8859-1");
        // 保存转码之后的数据
        out.write(b);
        // 关闭输出流
        out.close();
    }
}
```

运行结果如下：

<img src="../Images/Java/5-191223141433509.png" alt="img" style="zoom:80%;" />

可以发现，因为编码不一致，所以在保存时出现了乱码。在 Java 的开发中，乱码是一个比较常见的问题，乱码的产生就有一个原因，即输出内容的编码与接收内容的编码不一致。



## 12.4、File类

在 Java 中，==File 类是 java.io 包中唯一代表磁盘文件本身的对象，==也就是说，如果希望在程序中操作文件和目录，则都可以通过 File 类来完成。File 类定义了一些方法来操作文件，如新建、删除、重命名文件和目录等。

File 类不能访问文件内容本身，如果需要访问文件内容本身，则需要使用输入/输出流。

File 类提供了如下三种形式构造方法。

1. File(String path)：如果 path 是实际存在的路径，则该 File 对象表示的是目录；如果 path 是文件名，则该 File 对象表示的是文件。
2. File(String path, String name)：path 是路径名，name 是文件名。
3. File(File dir, String name)：dir 是路径对象，name 是文件名。

使用任意一个构造方法都可以创建一个 File 对象，然后调用其提供的方法对文件进行操作。在下表中列出了 File 类的常用方法及说明。

| 方法名称                      | 说明                                                         |
| ----------------------------- | ------------------------------------------------------------ |
| boolean canRead()             | 测试应用程序是否能从指定的文件中进行读取                     |
| boolean canWrite()            | 测试应用程序是否能写当前文件                                 |
| boolean createNewFile()       | 创建一个文件                                                 |
| boolean delete()              | 删除当前对象指定的文件                                       |
| boolean exists()              | 测试当前 File 是否存在                                       |
| String getAbsolutePath()      | 返回由该对象表示的文件的绝对路径名                           |
| String getName()              | 返回表示当前对象的文件名或路径名（如果是路径，则返回最后一级子路径名） |
| String getParent()            | 返回当前 File 对象所对应目录（最后一级子目录）的父目录名     |
| boolean isAbsolute()          | 测试当前 File 对象表示的文件是否为一个绝对路径名。该方法消除了不同平台的差异，可以直接判断 file 对象是否为绝对路径。在 UNIX/Linux/BSD 等系统上，如果路径名开头是一条斜线`/`，则表明该 File 对象对应一个绝对路径；在 Windows 等系统上，如果路径开头是盘符，则说明它是一个绝对路径。 |
| boolean isDirectory()         | 测试当前 File 对象表示的文件是否为一个路径                   |
| boolean isFile()              | 测试当前 File 对象表示的文件是否为一个“普通”文件             |
| long lastModified()           | 返回当前 File 对象表示的文件最后修改的时间                   |
| long length()                 | 返回当前 File 对象表示的文件长度                             |
| String[] list()               | 返回当前 File 对象的子级目录下的所有文件的文件名             |
| File[] listFiles()            | 返回当前 File 对象的子级目录下的所有文件                     |
| String[] list(FilenameFilter) | 返回当前 File 对象的子级目录中满足指定过滤器的文件列表       |
| boolean mkdir()               | 创建一个目录，它的路径名由当前 File 对象指定                 |
| boolean mkdirs()              | 创建一个目录，它的路径名由当前 File 对象指定                 |
| boolean renameTo(File)        | 将当前 File 对象指定的文件更名为给定参数 File 指定的路径名   |

File 类中有以下两个常用常量：

- public static final String pathSeparator：指的是分隔连续多个路径字符串的分隔符，Windows 下指`;`。例如 `java -cp test.jar;abc.jar HelloWorld`。

- public static final String separator：用来分隔同一个路径字符串中的目录的，Windows 下指`/`。例如 `C:/Program Files/Common Files`。
	比如说要在temp目录下建立一个test.txt文件，在Windows下应该这么写：

	```java   
	File file1 = new File ("C:\tmp\test.txt");
	```

	在Linux下则是这样的：

	```java
	File file2 = new File ("/tmp/test.txt");
	```

	如果要考虑跨平台，则最好是这么写：

	```java
	File myFile = new File("C:" + File.separator + "tmp" + File.separator, "test.txt");
	```

注意：可以看到 File 类的常量定义的命名规则不符合标准命名规则，常量名没有全部大写，这是因为 Java 的发展经过了一段相当长的时间，而命名规范也是逐步形成的，File 类出现较早，所以当时并没有对命名规范有严格的要求，这些都属于 Java 的历史遗留问题。

Windows 的路径分隔符使用反斜线“\”，而 Java 程序中的反斜线表示转义字符，所以如果需要在 Windows 的路径下包括反斜线，则应该使用两条反斜线或直接使用斜线“/”（两条斜线也行）也可以。Java 程序支持将斜线当成平台无关的路径分隔符。

假设在 Windows 操作系统中有一文件 `D:\javaspace\hello.java`，在 Java 中使用的时候，其路径的写法应该为 `D:/javaspace/hello.java` 或者 `D:\\javaspace\\hello.java`。

注意：==在操作文件时一定要使用 File.separator 表示分隔符。==在程序的开发中，往往会使用 Windows 开发环境，因为在 Windows 操作系统中支持的开发工具较多，使用方便，而在程序发布时往往是直接在 Linux 或其它操作系统上部署，所以这时如果不使用 File.separator，则程序运行就有可能存在问题。关于这一点我们在以后的开发中一定要有所警惕。



## 12.5、遍历目录

通过遍历目录可以在指定的目录中查找文件，或者显示所有的文件列表。==File 类的 list() 方法提供了遍历目录功能，==该方法有如下两种重载形式。

- String[] list()该方法表示返回由 File 对象表示目录中所有文件和子目录名称组成的字符串数组，如果调用的 File 对象不是目录，则返回 null。

	提示：==list() 方法返回的数组中仅包含文件名称，而不包含路径。==但不保证所得数组中的相同字符串将以特定顺序出现，特别是不保证它们按字母顺序出现。

- String[] list(FilenameFilter filter)
	该方法的作用与 list() 方法相同，不同的是返回数组中仅包含符合 filter 过滤器的文件和目录，如果 filter 为 null，则接受所有名称。

假设要遍历 D:/Program Files下的所有文件和目录。使用 listFiles() 方法的实现代码如下：

```java
public class Test05 {
    public static void main(String[] args) {
        File f = new File("C:/"); // 建立File变量,并设定由f变量变数引用
        System.out.println("文件名称\t\t文件类型\t\t文件大小");
        System.out.println("===================================================");
        String fileList[] = f.list(); // 调用不带参数的list()方法
        for (int i = 0; i < fileList.length; i++) { // 遍历返回的字符数组
            System.out.print(fileList[i] + "\t\t");
            System.out.print((new File("C:/", fileList[i])).isFile() ? "文件" + "\t\t" : "文件夹" + "\t\t");
            System.out.println((new File("C:/", fileList[i])).length() + "字节");
        }
    }
}
```

运行结果为：

```
文件名称		文件类型		文件大小
===================================================
$Recycle.Bin		文件夹		0字节
$WINDOWS.~BT		文件夹		0字节
$Windows.~WS		文件夹		0字节
$WINRE_BACKUP_PARTITION.MARKER		文件		0字节
47938B4DB7DF		文件		40字节
AMTAG.BIN		文件		1024字节
Config.Msi		文件夹		57344字节
Documents and Settings		文件夹		4096字节
Fraps		文件夹		0字节
inetpub		文件夹		4096字节
Intel		文件夹		0字节
offline_FtnInfo.txt		文件		348字节
OldNewExplorer		文件夹		4096字节
Program Files		文件夹		12288字节
Program Files (x86)		文件夹		8192字节
ProgramData		文件夹		12288字节
Recovery		文件夹		0字节
swapfile.sys		文件		268435456字节
System Volume Information		文件夹		4096字节
Temp		文件夹		0字节
Users		文件夹		4096字节
Windows		文件夹		65536字节
```

假设希望只列出目录下的某些文件，这就需要调用带过滤器参数的 list() 方法。首先需要创建文件过滤器，该过滤器必须实现 `java.io.FilenameFilter` 接口，并在 accept() 方法中指定允许的文件类型。

如下所示为允许 SYS、TXT 和 BAK 格式文件的过滤器实现代码：

```java
public class MyFileFilter implements FilenameFilter {
    // 实现 FilenameFilter 接口
    @Override
    public boolean accept(File dir, String name) {
        // 指定允许的文件类型
        return name.endsWith(".sys") || name.endsWith(".txt") || name.endsWith(".bak");
    }
}
```

上述代码创建的过滤器名称为 MyFileFilter，接下来只需要将该名称传递给 list() 方法即可实现筛选文件。

```java
String fileList[] = f.list(new MyFileFilter());
```

再次运行程序，遍历结果如下所示：

```
文件名称		文件类型		文件大小
===================================================
offline_FtnInfo.txt		文件		348字节
swapfile.sys		文件		268435456字节
```



## 12.6、字节流的使用

==InputStream 是 Java 所有字节输入流类的父类，OutputStream 是 Java 所有字节输出流类的父类，==它们都是一个抽象类，因此继承它们的子类要重新定义父类中的抽象方法。

### 12.6.1、字节输入流

InputStream 类及其子类的对象表示字节输入流，InputStream 类的常用子类如下。

- ByteArrayInputStream 类：将字节数组转换为字节输入流，从中读取字节。
- FileInputStream 类：从文件中读取数据。
- PipedInputStream 类：连接到一个 PipedOutputStream（管道输出流）。
- SequenceInputStream 类：将多个字节输入流串联成一个字节输入流。
- ObjectInputStream 类：将对象反序列化。

==使用 InputStream 类的方法可以从流中读取一个或一批字节。==下表列出了 InputStream 类的常用方法。

| 方法名及返回值类型                   | 说明                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| int read()                           | 从输入流中读取一个 8 位的字节，并把它转换为 0~255 的整数，最后返回整数。 如果返回 -1，则表示已经到了输入流的末尾。为了提高 I/O 操作的效率，建议尽量 使用 read() 方法的另外两种形式 |
| int read(byte[] b)                   | 从输入流中读取若干字节，并把它们保存到参数 b 指定的字节数组中。 该方法返回 读取的字节数。如果返回 -1，则表示已经到了输入流的末尾 |
| int read(byte[] b, int off, int len) | 从输入流中读取若干字节，并把它们保存到参数 b 指定的字节数组中。其中，off 指 定在字节数组中开始保存数据的起始下标；len 指定读取的字节数。该方法返回实际 读取的字节数。如果返回 -1，则表示已经到了输入流的末尾 |
| void close()                         | 关闭输入流。在读操作完成后，应该关闭输入流，系统将会释放与这个输入流相关 的资源。注意，InputStream 类本身的 close() 方法不执行任何操作，但是它的许多 子类重写了 close() 方法 |
| int available()                      | 返回可以从输入流中读取的字节数                               |
| long skip(long n)                    | 从输入流中跳过参数 n 指定数目的字节。该方法返回跳过的字节数  |
| void mark(int readLimit)             | 在输入流的当前位置开始设置标记，参数 readLimit 则指定了最多被设置标记的字 节数 |
| boolean markSupported()              | 判断当前输入流是否允许设置标记，是则返回 true，否则返回 false |
| void reset()                         | 将输入流的指针返回到设置标记的起始处                         |

==注意：在使用 mark() 方法和 reset() 方法之前，需要判断该文件系统是否支持这两个方法，以避免对程序造成影响。==



### 12.6.2、字节输出流

==OutputStream 类及其子类的对象表示一个字节输出流。==OutputStream 类的常用子类如下。

- ByteArrayOutputStream 类：向内存缓冲区的字节数组中写数据。
- FileOutputStream 类：向文件中写数据。
- PipedOutputStream 类：连接到一个 PipedlntputStream（管道输入流）。
- ObjectOutputStream 类：将对象序列化。

利用 OutputStream 类的方法可以从流中写入一个或一批字节。下表列出了 OutputStream 类的常用方法。

| 方法名及返回值类型                   | 说明                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| void write(int b)                    | 向输出流写入一个字节。这里的参数是 int 类型，但是它允许使用表达式， 而不用强制转换成 byte 类型。为了提高 I/O 操作的效率，建议尽量使用 write() 方法的另外两种形式 |
| void write(byte[] b)                 | 把参数 b 指定的字节数组中的所有字节写到输出流中              |
| void write(byte[] b,int off,int len) | 把参数 b 指定的字节数组中的若干字节写到输出流中。其中，off 指定字节 数组中的起始下标，len 表示元素个数 |
| void close()                         | 关闭输出流。写操作完成后，应该关闭输出流。系统将会释放与这个输出 流相关的资源。注意，OutputStream 类本身的 close() 方法不执行任何操作，但是它的许多子类重写了 close() 方法 |
| void flush()                         | 为了提高效率，在向输出流中写入数据时，数据一般会先保存到内存缓冲 区中，只有当缓冲区中的数据达到一定程度时，缓冲区中的数据才会被写 入输出流中。使用 flush() 方法则可以强制将缓冲区中的数据写入输出流， 并清空缓冲区 |



### 12.6.3、文件输入流

FileInputStream 是 Java 流中比较常用的一种，它表示从文件系统的某个文件中获取输入字节。通过使用 FileInputStream 可以访问文件中的一个字节、一批字节或整个文件。

==在创建 FileInputStream 类的对象时，如果找不到指定的文件将拋出 FileNotFoundException 异常，该异常必须捕获或声明拋出。==

FileInputStream 常用的构造方法主要有如下两种重载形式。

1. FileInputStream(File file)：通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定。
2. FileInputStream(String name)：通过打开一个到实际文件的链接来创建一个 FileInputStream，该文件通过文件系统中的路径名 name 指定。

下面的示例演示了 FileInputStream() 两个构造方法的使用。

```java
try {
    // 以File对象作为参数创建FileInputStream对象
    FileInputStream fis1 = new FileInputStream(new File("F:/mxl.txt"));
    // 以字符串值作为参数创建FilelnputStream对象
    FileInputStream fis2 = new FileInputStream("F:/mxl.txt");
} catch(FileNotFoundException e) {
    System.out.println("指定的文件找不到!");
}
```

假设有一个 `D:\myJava\HelloJava.java` 文件，下面使用 FileInputStream 类读取并输出该文件的内容。具体代码如下：

```java
public class Test10 {
    public static void main(String[] args) {
        File f = new File("D:/myJava/HelloJava.java");
        FileInputStream fis = null;
        try {
            // 因为File没有读写的能力,所以需要有个InputStream
            fis = new FileInputStream(f);
            // 定义一个字节数组
            byte[] bytes = new byte[1024];
            int n = 0; // 得到实际读取到的字节数
            System.out.println("D:\\myJava\\HelloJava.java文件内容如下：");
            // 循环读取
            while ((n = fis.read(bytes)) != -1) {
                String s = new String(bytes, 0, n); // 将数组中从下标0到n的内容给s
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

如上述代码，在 FileInputDemo 类的 main() 方法中首先创建了一个 File 对象 f，该对象指向 D:\myJava\HelloJava.java 文件。接着使用 FileInputStream 类的构造方法创建了一个 FileInputStream 对象 fis，并声明一个长度为 1024 的 byte 类型的数组，然后使用 FileInputStream 类中的 read() 方法将 HelloJava.java 文件中的数据读取到字节数组 bytes 中，并输出该数据。最后在 finally 语句中关闭 FileInputStream 输入流。

下面所示为 HelloJava.java 文件的原始内容，如下所示的是运行程序后的输出内容。

```
D:\myJava\HelloJava.java文件内容如下：
/*
*第一个java程序
*/
public class HelloJava {
    // 这里是程序入口
    public static void main(String[] args) {
        // 输出字符串
        System.out.println("你好 Java");
    }
}
```

<img src="../Images/Java/5-19121Q25Z21J.png" alt="HelloJava.java文件内容" style="zoom:80%;" />

==注意：FileInputStream 类重写了父类 InputStream 中的 read() 方法、skip() 方法、available() 方法和 close() 方法，不支持 mark() 方法和 reset() 方法。==



### 12.6.4、文件输出流

==FileOutputStream 类继承自 OutputStream 类，重写和实现了父类中的所有方法。==FileOutputStream 类的对象表示一个文件字节输出流，可以向流中写入一个字节或一批字节。==在创建 FileOutputStream 类的对象时，如果指定的文件不存在，则创建一个新文件；如果文件已存在，则清除原文件的内容重新写入。==

FileOutputStream 类的构造方法主要有如下 4 种重载形式。

1. FileOutputStream(File file)：创建一个文件输出流，参数 file 指定目标文件。
2. FileOutputStream(File file,boolean append)：创建一个文件输出流，参数 file 指定目标文件，append 指定是否将数据添加到目标文件的内容末尾，如果为 true，则在末尾添加；如果为 false，则覆盖原有内容；其默认值为 false。
3. FileOutputStream(String name)：创建一个文件输出流，参数 name 指定目标文件的文件路径信息。
4. FileOutputStream(String name,boolean append)：创建一个文件输出流，参数 name 和 append 的含义同上。

注意：使用构造方法 FileOutputStream(String name,boolean append) 创建一个文件输出流对象，它将数据附加在现有文件的末尾。该字符串 name 指明了原文件，如果只是为了附加数据而不是重写任何已有的数据，布尔类型参数 append 的值应为 true。

对文件输出流有如下四点说明：

1. 在 FileOutputStream 类的构造方法中指定目标文件时，目标文件可以不存在。
2. 目标文件的名称可以是任意的，例如 D:\\abc、D:\\abc.de 和 D:\\abc.de.fg 等都可以，可以使用记事本等工具打开并浏览这些文件中的内容。
3. 目标文件所在目录必须存在，否则会拋出 java.io.FileNotFoundException 异常。
4. 目标文件的名称不能是已存在的目录。例如 D 盘下已存在 Java 文件夹，那么就不能使用 Java 作为文件名，即不能使用 D:\\Java，否则抛出 java.io.FileNotFoundException 异常。

同样是读取 D:\myJava\HelloJava.java 文件的内容，在这里使用 FileInputStream 类实现，然后再将内容写入新的文件 D:\myJava\HelloJava.txt 中。具体的代码如下：

```java
public class Test11 {
    public static void main(String[] args) {
        FileInputStream fis = null; // 声明FileInputStream对象fis
        FileOutputStream fos = null; // 声明FileOutputStream对象fos
        try {
            File srcFile = new File("D:/myJava/HelloJava.java");
            fis = new FileInputStream(srcFile); // 实例化FileInputStream对象
            File targetFile = new File("D:/myJava/HelloJava.txt"); // 创建目标文件对象，该文件不存在
            fos = new FileOutputStream(targetFile); // 实例化FileOutputStream对象
            byte[] bytes = new byte[1024]; // 每次读取1024字节
            int i = fis.read(bytes);
            while (i != -1) {
                fos.write(bytes, 0, i); // 向D:\HelloJava.txt文件中写入内容
                i = fis.read(bytes);
            }
            System.out.println("写入结束！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close(); // 关闭FileInputStream对象
                fos.close(); // 关闭FileOutputStream对象
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

如上述代码，将 D:\myJava\HelloJava.java 文件中的内容通过文件输入/输出流写入到了 D:\myJava\HelloJava.txt 文件中。由于 HelloJava.txt 文件并不存在，所以在执行程序时将新建此文件，并写入相应内容。

运行程序，成功后会在控制台输出“写入结束！”。此时，打开 D:\myJava\HelloJava.txt 文件会发现，其内容与 HelloJava.java 文件的内容相同，如下图所示。

<img src="../Images/Java/5-1912191A322507.png" alt="img" style="zoom:80%;" />

==技巧：在创建 FileOutputStream 对象时，如果将 append 参数设置为 true，则可以在目标文件的内容末尾添加数据，此时目标文件仍然可以暂不存在。==



## 12.7、字符流的使用

尽管 Java 中字节流的功能十分强大，几乎可以直接或间接地处理任何类型的输入/输出操作，但利用它却不能直接操作 16 位的 Unicode 字符。这就要用到字符流。

### 12.7.1、字符输入流

Reader 类是所有字符流输入类的父类，该类定义了许多方法，这些方法对所有子类都是有效的。

Reader 类的常用子类如下。

- CharArrayReader 类：将字符数组转换为字符输入流，从中读取字符。
- StringReader 类：将字符串转换为字符输入流，从中读取字符。
- BufferedReader 类：为其他字符输入流提供读缓冲区。
- PipedReader 类：连接到一个 PipedWriter。
- InputStreamReader 类：将字节输入流转换为字符输入流，可以指定字符编码。

与 InputStream 类相同，在 Reader 类中也包含 close()、mark()、skip() 和 reset() 等方法，这些方法可以参考 InputStream 类的方法。下面主要介绍 Reader 类中的 read() 方法，如下表所示。

| 方法名及返回值类型                    | 说明                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| int read()                            | 从输入流中读取一个字符，并把它转换为 0~65535 的整数。如果返回 -1， 则表示 已经到了输入流的末尾。为了提高 I/O 操作的效率，建议尽量使用下面两种 read() 方法 |
| int read(char[] cbuf)                 | 从输入流中读取若干个字符，并把它们保存到参数 cbuf 指定的字符数组中。 该方 法返回读取的字符数，如果返回 -1，则表示已经到了输入流的末尾 |
| int read(char[] cbuf,int off,int len) | 从输入流中读取若干个字符，并把它们保存到参数 cbuf 指定的字符数组中。其中， off 指定在字符数组中开始保存数据的起始下标，len 指定读取的字符数。该方法返 回实际读取的字符数，如果返回 -1，则表示已经到了输入流的末尾 |



### 12.7.2、字符输出流

与 Reader 类相反，==Writer 类是所有字符输出流的父类，==该类中有许多方法，这些方法对继承该类的所有子类都是有效的。

Writer 类的常用子类如下。

- CharArrayWriter 类：向内存缓冲区的字符数组写数据。
- StringWriter 类：向内存缓冲区的字符串（StringBuffer）写数据。
- BufferedWriter 类：为其他字符输出流提供写缓冲区。
- PipedWriter 类：连接到一个 PipedReader。
- OutputStreamReader 类：将字节输出流转换为字符输出流，可以指定字符编码。

与 OutputStream 类相同，Writer 类也包含 close()、flush() 等方法，这些方法可以参考 OutputStream 类的方法。下面主要介绍 Writer 类中的 write() 方法和 append() 方法，如下表所示。

| 方法名及返回值类型                         | 说明                                                         |
| ------------------------------------------ | ------------------------------------------------------------ |
| void write(int c)                          | 向输出流中写入一个字符                                       |
| void write(char[] cbuf)                    | 把参数 cbuf 指定的字符数组中的所有字符写到输出流中           |
| void write(char[] cbuf,int off,int len)    | 把参数 cbuf 指定的字符数组中的若干字符写到输出流中。其中，off 指定 字符数组中的起始下标，len 表示元素个数 |
| void write(String str)                     | 向输出流中写入一个字符串                                     |
| void write(String str, int off,int len)    | 向输出流中写入一个字符串中的部分字符。其中，off 指定字符串中的起 始偏移量，len 表示字符个数 |
| append(char c)                             | 将参数 c 指定的字符添加到输出流中                            |
| append(charSequence esq)                   | 将参数 esq 指定的字符序列添加到输出流中                      |
| append(charSequence esq,int start,int end) | 将参数 esq 指定的字符序列的子序列添加到输出流中。其中，start 指定 子序列的第一个字符的索引，end 指定子序列中最后一个字符后面的字符 的索引，也就是说子序列的内容包含 start 索引处的字符，但不包括 end 索引处的字符 |

==注意：Writer 类所有的方法在出错的情况下都会引发 IOException 异常。关闭一个流后，再对其进行任何操作都会产生错误。==



### 12.7.3、字符文件输入流

为了读取方便，Java 提供了用来读取字符文件的便捷类——FileReader。该类的构造方法有如下两种重载形式。

1. FileReader(File file)：在给定要读取数据的文件的情况下创建一个新的 FileReader 对象。其中，file 表示要从中读取数据的文件。
2. FileReader(String fileName)：在给定从中读取数据的文件名的情况下创建一个新 FileReader 对象。其中，fileName 表示要从中读取数据的文件的名称，表示的是一个文件的完整路径。

在用该类的构造方法创建 FileReader 读取对象时，默认的字符编码及字节缓冲区大小都是由系统设定的。要自己指定这些值，可以在 FilelnputStream 上构造一个 InputStreamReader。

注意：在创建 FileReader 对象时可能会引发一个 FileNotFoundException 异常，因此需要使用 try catch 语句捕获该异常。

字符流和字节流的操作步骤相同，都是首先创建输入流或输出流对象，即建立连接管道，建立完成后进行读或写操作，最后关闭输入/输出流通道。

要将 D:\myJava\HelloJava.java 文件中的内容读取并输出到控制台，使用 FileReader 类的实现代码如下：

```java
public class Test12 {
    public static void main(String[] args) {
        FileReader fr = null;
        try {
            fr = new FileReader("D:/myJava/HelloJava.java"); // 创建FileReader对象
            int i = 0;
            System.out.println("D:\\myJava\\HelloJava.java文件内容如下：");
            while ((i = fr.read()) != -1) { // 循环读取
                System.out.print((char) i); // 将读取的内容强制转换为char类型
            }
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            try {
                fr.close(); // 关闭对象
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

如上述代码，首先创建了 FileReader 字符输入流对象 fr，该对象指向 D:\myJava\HelloJava.java 文件，然后定义变量 i 来接收调用 read() 方法的返回值，即读取的字符。在 while 循环中，每次读取一个字符赋给整型变量 i，直到读取到文件末尾时退出循环（当输入流读取到文件末尾时，会返回值 -1）。



### 12.7.4、字符文件输出流

Java 提供了写入字符文件的便捷类——FileWriter，该类的构造方法有如下 4 种重载形式。

1. FileWriter(File file)：在指定 File 对象的情况下构造一个 FileWriter 对象。其中，file 表示要写入数据的 File 对象。
2. FileWriter(File file,boolean append)：在指定 File 对象的情况下构造一个 FileWriter 对象，如果 append 的值为 true，则将字节写入文件末尾，而不是写入文件开始处。
3. FileWriter(String fileName)：在指定文件名的情况下构造一个 FileWriter 对象。其中，fileName 表示要写入字符的文件名，表示的是完整路径。
4. FileWriter(String fileName,boolean append)：在指定文件名以及要写入文件的位置的情况下构造 FileWriter 对象。其中，append 是一个 boolean 值，如果为 true，则将数据写入文件末尾，而不是文件开始处。

在创建 FileWriter 对象时，默认字符编码和默认字节缓冲区大小都是由系统设定的。要自己指定这些值，可以在 FileOutputStream 上构造一个 OutputStreamWriter 对象。

==FileWriter 类的创建不依赖于文件存在与否，如果关联文件不存在，则会自动生成一个新的文件。在创建文件之前，FileWriter 将在创建对象时打开它作为输出。如果试图打开一个只读文件，将引发一个 IOException 异常。==

注意：在创建 FileWriter 对象时可能会引发 IOException 或 SecurityException 异常，因此需要使用 try catch 语句捕获该异常。

编写一个程序，将用户输入的 4 个字符串保存到 D:\myJava\book.txt 文件中。在这里使用 FileWriter 类中的 write() 方法循环向指定文件中写入数据，实现代码如下：

```java
public class Test13 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FileWriter fw = null;
        try {
            fw = new FileWriter("D:\\myJava\\book.txt"); // 创建FileWriter对象
            for (int i = 0; i < 4; i++) {
                System.out.println("请输入第" + (i + 1) + "个字符串：");
                String name = input.next(); // 读取输入的名称
                fw.write(name + "\r\n"); // 循环写入文件
            }
            System.out.println("录入完成！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fw.close(); // 关闭对象
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

如上述代码，首先创建了一个指向 D:\myJava\book.txt 文件的字符文件输出流对象 fw，然后使用 for 循环录入 4 个字符串，并调用 write() 方法将字符串写入到指定的文件中。最后在 finally 语句中关闭字符文件输出流。

运行该程序，根据提示输入 4 个字符串，如下所示。接着打开 D:\myJava\book.txt 文件，将看到写入的内容，如下所示。

```
请输入第1个字符串：
热点要闻
请输入第2个字符串：
个性推荐
请输入第3个字符串：
热搜新闻词
请输入第4个字符串：
本地看点
录入完成！
```

<img src="../Images/Java/5-1912191H959510.png" alt="img" style="zoom:80%;" />



### 12.7.5、字符缓冲区输入流

==BufferedReader 类主要用于辅助其他字符输入流，它带有缓冲区，可以先将一批数据读到内存缓冲区。==接下来的读操作就可以直接从缓冲区中获取数据，而不需要每次都从数据源读取数据并进行字符编码转换，这样就可以提高数据的读取效率。

BufferedReader 类的构造方法有如下两种重载形式。

1. BufferedReader(Reader in)：创建一个 BufferedReader 来修饰参数 in 指定的字符输入流。
2. BufferedReader(Reader in,int size)：创建一个 BufferedReader 来修饰参数 in 指定的字符输入流，参数 size 则用于指定缓冲区的大小，单位为字符。

除了可以为字符输入流提供缓冲区以外，BufferedReader 还提供了 `readLine()` 方法，该方法返回包含该行内容的字符串，但该字符串中不包含任何终止符，如果已到达流末尾，则返回 null。readLine() 方法表示每次读取一行文本内容，当遇到换行（\n）、回车（\r）或回车后直接跟着换行标记符即可认为某行已终止。

使用 BufferedReader 类中的 readLine() 方法逐行读取 D:\myJava\Book.txt 文件中的内容，并将读取的内容在控制台中打印输出，代码如下：

```java
public class Test13 {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("D:\\myJava\\book.txt"); // 创建 FileReader 对象
            br = new BufferedReader(fr); // 创建 BufferedReader 对象
            System.out.println("D:\\myJava\\book.txt 文件中的内容如下：");
            String strLine = "";
            while ((strLine = br.readLine()) != null) { // 循环读取每行数据
                System.out.println(strLine);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close(); // 关闭 FileReader 对象
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

如上述代码，首先分别创建了名称为 fr 的 FileReader 对象和名称为 br 的 BufferedReader 对象，然后调用 BufferedReader 对象的 readLine() 方法逐行读取文件中的内容。如果读取的文件内容为 Null，即表明已经读取到文件尾部，此时退出循环不再进行读取操作。最后将字符文件输入流和带缓冲的字符输入流关闭。

运行该程序，输出结果如下所示：

```
D:\myJava\book.txt 文件中的内容如下：
热点要闻
个性推荐
热搜新闻词
本地看点
```



### 12.7.6、字符缓冲区输出流

==BufferedWriter 类主要用于辅助其他字符输出流，==它同样带有缓冲区，可以先将一批数据写入缓冲区，当缓冲区满了以后，再将缓冲区的数据一次性写到字符输出流，其目的是为了提高数据的写效率。

BufferedWriter 类的构造方法有如下两种重载形式。

1. BufferedWriter(Writer out)：创建一个 BufferedWriter 来修饰参数 out 指定的字符输出流。
2. BufferedWriter(Writer out,int size)：创建一个 BufferedWriter 来修饰参数 out 指定的字符输出流，参数 size 则用于指定缓冲区的大小，单位为字符。

该类除了可以给字符输出流提供缓冲区之外，还提供了一个新的方法 newLine()，该方法用于写入一个行分隔符。行分隔符字符串由系统属性 line.separator 定义，并且不一定是单个新行（\n）符。

提示：BufferedWriter 类的使用与 FileWriter 类相同，这里不再重述。



## 12.8、转换流

正常情况下，字节流可以对所有的数据进行操作，但是有些时候在处理一些文本时我们要用到字符流，比如，查看文本的中文时就是需要采用字符流更为方便。所以 Java IO 流中提供了两种用于将字节流转换为字符流的转换流。

==InputStreamReader 用于将字节输入流转换为字符输入流，==其中 ==OutputStreamWriter 用于将字节输出流转换为字符输出流。==使用转换流可以在一定程度上避免乱码，还可以指定输入输出所使用的字符集。

在 java.txt 中输入“C语言中文网”这 6 个字，将 java.txt 保存为“UTF-8”的格式，然后通过字节流的方式读取，代码如下：

```java
public static void main(String[] args) {
    try {
        FileInputStream fis = new FileInputStream("D://java.txt");
        int b = 0;
        while ((b = fis.read()) != -1) {
            System.out.print((char) b);
        }
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
```

输出结果为 `C??????????`，我们发现中文都是乱码。下面用字节数组，并通过字符串设定编码格式来显式内容，代码如下：

```java
public static void main(String[] args) {
    try {
        FileInputStream fis = new FileInputStream("D://java.txt");
        byte b[] = new byte[1024];
        int len = 0;
        while ((len = fis.read(b)) != -1) {
            System.out.print(new String(b, 0, len, "UTF-8"));
        }
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
```

这时输出结果为 `C语言中文网`，但是当存储的文字较多时，会出现解码不正确的问题，且字节长度无法根据解码内容自动设定，此时就需要转换流来完成。代码如下：

```java
public static void main(String[] args) {
    try {
        FileInputStream fis = new FileInputStream("D://java.txt");
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        int b = 0;
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);    // 输出结果为“C语言中文网”
        }
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
```

下面以获取键盘输入为例来介绍转换流的用法。Java 使用 System.in 代表标准输出，即键盘输入，但这个标准输入流是 InputStream 类的实例，使用不太方便，而且键盘输入内容都是文本内容，所以可以使用 InputStreamReader 将其转换成字符输入流，普通的 Reader 读取输入内容时依然不太方便，可以将普通的 Reader 再次包装成 BufferedReader，利用 BufferedReader 的 readLine() 方法可以一次读取一行内容。程序如下所示。

```java
public static void main(String[] args) {
    try {
        // 将 System.in 对象转换成 Reader 对象
        InputStreamReader reader = new InputStreamReader(System.in);
        // 将普通的Reader 包装成 BufferedReader
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        // 利用循环方式来逐行的读取
        while ((line = br.readLine()) != null) {
            // 如果读取的字符串为“exit”，则程序退出
            if (line.equals("exit")) {
                System.exit(1);
            }
            // 打印读取的内容
            System.out.println("输入内容为：" + line);
        }
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
```

上面代码第 4 行和第 6 行将 System.in 包装成 BufferedReader，BufferReader 流具有缓冲功能，它可以一次读取一行文本，以换行符为标志，如果它没有读到换行符，则程序堵塞，等到读到换行符为止。运行上面程序可以发现这个特征，在控制台执行输入时，只有按下回车键，程序才会打印出刚刚输入的内容。

提示：Sytem.exit(0)是正常退出，表示程序正常执行结束退出；system.exit(1)是非正常退出，就是说无论程序正在执行与否，都退出，大致理解为电脑点关机和拔电源。

由于 BufferedReader 具有一个 readLine() 方法，可以非常方便地进行一次读入一行内容，所以经常把读入文本内容地输入流包装成 BufferedReader，用来方便地读取输入流的文本内容。

既然有字节流转字符流的转换流，那么为什么没有字符流转字节流的转换流呢？

这个问题一语指出了 Java 设计的遗漏之处，想一想字符流和字节流的差别。字节流比字符流的使用范围要更广，但字符流比字节流操作方便。如果有一个流已经是字符流了，也就是说，是一个用起来更方便的流，为什么要转换成字节流呢？反之，如果现在有一个字节流，但可以确定这个字节流的内容都是文本内容，那么把它转换成字符流来处理就会更方便一些，所以 Java 只提供了将字节流转换成字符流的转换流，没有提供将字符流转换成字节流的转换流。



## 12.9、对象序列化输入输出

==对象序列化（Serialize）==指将一个 Java 对象写入 IO 流中，与此对应的是，对象的反序列化（Deserialize）则指从 IO 流中恢复该 Java 对象。如果想让某个 Java 对象能够序列化，则必须让它的类实现 java.io.Serializable 接口，接口定义如下：

```java
public interface Serializable {
}
```

Serializable 接口是一个空接口，实现该接口无须实现任何方法，它只是告诉 JVM 该类可以被序列化机制处理。通常建议程序创建的每个 JavaBean 类都实现 Serializable。

ObjectInput 接口与 ObjectOutput 接口分别继承了 DataInput 和 DataOutput 接口，主要提供用于读写基本数据和对象数据的方法。 ObjectInput 接口提供了 readObject() 方法，此方法用于将对象从流中读出。ObjectOutput 提供了 writeObject() 方法，此方法用于将对象写入流中。因为 ObjectInput 与 ObjectOutput 都是接口，所以不能创建对象，只能使用分别实现了这两个接口的 ObjectInputStream 类和 ObjectOutputStream 类来创建对象。

==凡是被`static`修饰的字段是不会被序列化的，因为序列化保存的是**对象的状态**而非类的状态，所以会忽略`static`静态域也是理所应当的。==

### 12.9.1、序列化

ObjectOutputStream 类继承了 OutputStream 类，同时实现了 ObjectOutput 接口，提供将对象序列化并写入流中的功能，该类的构造方法如下：

```java
public ObjectOutputStream (OutputStream out)
```

该构造方法需要传入一个 OutputStream 对象，用来表示将对象二进制流写入到指定的 OutputStream 中。

程序通过以下两个步骤来序列化对象：

1. 创建一个 ObjectOutputStream 对象，如下代码所示。

	```java
	// 创建个 ObjectOutputStream 输出流
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
	```

2. 调用 ObjectOutputStream 对象的 writeObject() 方法输出可序列化对象，如下代码所示。

	```java
	// 将一个 Person 对象输出到输出流中
	oos.writerObject(per);
	```

下面程序定义了一个 Person 类，这个 Person 类就是一个普通的 Java 类，只是实现了 Serializable 接口，该接口表示该类的对象是可序列化的。

```java
import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;
    // 注意此处没有提供无参数的构造器
    public Person(String name, int age) {
        System.out.println("有参数的构造器");
        this.name = name;
        this.age = age;
    }
    // 省略 name 和 age的setter和getter方法
    ...
}
```

注意：Person 类的两个成员变量分别是 String 类型和 int 类型的。如果某个类的成员变量的类型不是基本类型或 String 类型，而是另一个引用类型，那么这个引用类型必须是可序列化的，否则拥有该类型成员变量的类也是不可序列化的。

下面程序使用 ObjectOutputStream 将一个 Person 对象写入到磁盘文件。

```java
public class WriteObject {
    public static void main(String[] args) throws Exception {
        // 创建一个 ObjectOutputStream 输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
        Person per = new Person("C语言中文网", 7);
        // 将 Per对象写入输出流
        oos.writeObject(per);
    }
```

上面程序中的第 4 行代码创建了一个 ObjectOutputStream 输出流，这个 ObjectOutputStream 输出流建立在一个文件输出流的基础之上。程序第 7 行代码使用 writeObject() 方法将一个 Person 对象写入输出流。运行上面程序，将会看到生成了一个 object.txt 文件，该文件的内容就是 Person 对象。



### 12.9.2、反序列化

ObjectInputStream 类继承了 InputStream 类，同时实现了 ObjectInput 接口，提供了将对象序列化并从流中读取出来的功能。该类的构造方法如下：

```java
public ObjectInputStream(InputStream out)
```

该构造方法需要传入一个 InputStream 对象，用来创建从指定 InputStream 读取的 ObjectInputStream。

反序列化的步骤如下所示：

1. 创建一个 ObjectInputStream 输入流，这个输入流是一个处理流，所以必须建立在其他节点流的基础之上。如下代码所示。

	```java
	// 创建一个ObjectInputStream输入流
	ObjectInputStream ois = new ObjectInputStream (new FileInputStream ("object. txt"));
	```

2. 调用 ObjectInputStream 对象的 readObject() 方法读取流中的对象，该方法返回一个 Object 类型的 Java 对象，如果程序知道该 Java 对象的类型，则可以将该对象强制类型转换成其真实的类型。如下代码所示。

	```java
	// 从输入流中读取一个Java对象，并将其强制类型转换为Person类
	Person P = (Person)ois.readObject();
	```

下面程序是从例 1 中生成的 object.txt 文件来读取 Person 对象的步骤。

```java
public class ReadObject {
    public static void main(String[] args) throws Exception {
        // 创建一个ObjectInputStream输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"));
        // 从输入流中读取一个 Java对象，并将其强制类型转换为Person类
        Person p = (Person) ois.readObject();
        System.out.println("名字为：" + p.getName() + "\n年龄为：" + p.getAge());
    }
}
```

上面程序中第 4 行粗体字代码将一个文件输入流包装成 ObjectInputStream 输入流，第 6 行代码使用 readObject() 读取了文件中的 Java 对象，这就完成了反序列化过程。

反序列化读取的仅仅是 Java 对象的数据，而不是 Java 类，因此采用反序列化恢复 Java 对象时，必须提供该 Java 对象所属类的 class 文件，否则将会引发 ClassNotFoundException 异常。

Person 类只有一个有参数的构造器，没有无参数的构造器，而且该构造器内有一个普通的打印语句。当反序列化读取 Java 对象时，并没有看到程序调用该构造器，这表明反序列化机制无须通过构造器来初始化 Java 对象。

如果使用序列化机制向文件中写入了多个 Java 对象，使用反序列化机制恢复对象时必须按实际写入的顺序读取。

当一个可序列化类有多个父类时（包括直接父类和间接父类），这些父类要么有无参数的构造方法，要么也是可序列化的，否则反序列化时将抛出 InvalidClassException 异常。如果父类是不可序列化的，只是带有无参数的构造方法，则该父类中定义的成员变量值不会序列化到 IO 流中。

---

**Java序列化编号**

Java 序列化机制是通过类的序列化编号（serialVersionUID）来验证版本一致性的。在反序列化时，JVM 会把传来字节流中的序列化编号和本地相应实体类的序列化编号进行比较，如果相同就认为一致，可以进行反序列化，否则会抛出 InvalidCastException 异常。

序列化编号有两种显式生成方式：

1. 默认的1L，比如：private static final long serialVersionUID = 1L。
2. 根据类名、接口名、成员方法及属性等来生成一个 64 位的哈希字段。

当实现 Serializable 接口的对象没有显式定义一个序列化编号时，Java 序列化会根据编译的 Class 自动生成一个序列化编号，这种情况下只要 class 文件发生变化，序列化号就会改变，否则一直不变。

---

**transient关键字**

如果不希望类中的属性被序列化，可以在声明属性之前加上**transient**关键字。如下所示，下面的代码修改自前面所用到的Person.java程序，在声明属性时，前面多加了一个transient关键字。

```java
private transient String name;
private transient int age;
```

---

**序列化的受控和加强**

序列化和反序列化的过程其实是**有漏洞的**，因为从序列化到反序列化是有中间过程的，如果被别人拿到了中间字节流，然后加以伪造或者篡改，那反序列化出来的对象就会有一定风险了。

毕竟反序列化也相当于一种 **“隐式的”对象构造** ，因此我们希望在反序列化时，进行**受控的**对象反序列化动作。

那怎么个受控法呢？

**答案就是：** 自行编写**`readObject()`**函数，用于对象的反序列化构造，从而提供约束性。既然自行编写`readObject()`函数，那就可以做很多可控的事情：比如各种判断工作。



## 12.10、Properties文件解析

编写一个解析Properties文件的类：

```java
public class PropertiesDemo {
	public static void main(String[] args) throws IOException {
		Properties p = new Properties();
		FileInputStream file = new FileInputStream("jdbc.properties");
		p.load(file);
		String driver = p.getProperty("driver");
		System.out.println(driver);
		file.close();
	}
}

```

在该工程根目录下创建一个jdbc.properties文件

```properties
driver=com.mysql.cj.jdbc.Drvier
```

运行结果为：

```
com.mysql.cj.jdbc.Drvier
```



### 12.10.1、使用class.getResourceAsStream(file)获取properties文件

```java
public class PropertiesDemo {
	public static void main(String[] args) throws IOException {
		Properties p = new Properties();
		// properties文件在PropertiesDemo.java的包下
		p.load(PropertiesDemo.class.getResourceAsStream("jdbc.properties"));
		String driver = p.getProperty("driver");
		System.out.println(driver);
	}
}
```



### 12.10.2、class.getClassLoader().getResourceAsStream(file)和class.getResourceAsStream(file)区别

共同点：都是实现获取在classpath路径下的资源文件的输入流。

为什么是classpath而不是src，因为当web项目运行时,IDE编译器会把src下的一些资源文件移至WEB-INF/classes，classPath目录其实就是这个classes目录。这个目录下放的一般是web项目运行时的class文件、资源文件(xml,properties...)；

---

**class.getClassLoader().getResourceAsStream(file)**

path 不以 '/' 开头时默认是从此类所在的包下取资源，以 '/' 开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。

---

**Class.getClassLoader.getResourceAsStream(String path)**

默认则是从ClassPath根下获取，path不能以 '/' 开头，最终是由ClassLoader获取资源。



## 12.11、使用printf 格式化输出 

```java
import java.util.Date;
 
/**
 * 使用printf输出
 */
/**关键技术点
 * 使用java.io.PrintStream的printf方法实现C风格的输出
 * printf 方法的第一个参数为输出的格式,第二个参数是可变长的,表示待输出的数据对象
 */
public class Printf {
 
       public static void main(String[] args) {
              /*** 输出字符串 ***/
              // %s表示输出字符串，也就是将后面的字符串替换模式中的%s
              System.out.printf("%s", new Integer(1212));
              // %n表示换行
              System.out.printf("%s%n", "end line");
              // 还可以支持多个参数
              System.out.printf("%s = %s%n", "Name", "Zhangsan");
              // %S将字符串以大写形式输出
              System.out.printf("%S = %s%n", "Name", "Zhangsan");
              // 支持多个参数时，可以在%s之间插入变量编号，1$表示第一个字符串，3$表示第3个字符串
              System.out.printf("%1$s = %3$s %2$s%n", "Name", "san", "Zhang");
             
              /*** 输出boolean类型 ***/
              System.out.printf("true = %b; false = ", true);
              System.out.printf("%b%n", false);
 
              /*** 输出整数类型***/
              Integer iObj = 342;
              // %d表示将整数格式化为10进制整数
              System.out.printf("%d; %d; %d%n", -500, 2343L, iObj);
              // %o表示将整数格式化为8进制整数
              System.out.printf("%o; %o; %o%n", -500, 2343L, iObj);
              // %x表示将整数格式化为16进制整数
              System.out.printf("%x; %x; %x%n", -500, 2343L, iObj);
              // %X表示将整数格式化为16进制整数，并且字母变成大写形式
              System.out.printf("%X; %X; %X%n", -500, 2343L, iObj);
             
              /*** 输出浮点类型***/
              Double dObj = 45.6d;
              // %e表示以科学技术法输出浮点数
              System.out.printf("%e; %e; %e%n", -756.403f, 7464.232641d, dObj);
              // %E表示以科学技术法输出浮点数，并且为大写形式            
              System.out.printf("%E; %E; %E%n", -756.403f, 7464.232641d, dObj);
              // %f表示以十进制格式化输出浮点数
              System.out.printf("%f; %f; %f%n", -756.403f, 7464.232641d, dObj);
              // 还可以限制小数点后的位数
              System.out.printf("%.1f; %.3f; %f%n", -756.403f, 7464.232641d, dObj);
             
              /*** 输出日期类型***/
              // %t表示格式化日期时间类型，%T是时间日期的大写形式，在%t之后用特定的字母表示不同的输出格式
              Date date = new Date();
              long dataL = date.getTime();
              // 格式化年月日
              // %t之后用y表示输出日期的年份（2位数的年，如99）
              // %t之后用m表示输出日期的月份，%t之后用d表示输出日期的日号
              System.out.printf("%1$ty-%1$tm-%1$td; %2$ty-%2$tm-%2$td%n", date, dataL);
              // %t之后用Y表示输出日期的年份（4位数的年），
              // %t之后用B表示输出日期的月份的完整名， %t之后用b表示输出日期的月份的简称
              System.out.printf("%1$tY-%1$tB-%1$td; %2$tY-%2$tb-%2$td%n", date, dataL);
             
              // 以下是常见的日期组合
              // %t之后用D表示以 "%tm/%td/%ty"格式化日期
              System.out.printf("%1$tD%n", date);
              //%t之后用F表示以"%tY-%tm-%td"格式化日期
              System.out.printf("%1$tF%n", date);
             
              /*** 输出时间类型***/
              // 输出时分秒
              // %t之后用H表示输出时间的时（24进制），%t之后用I表示输出时间的时（12进制），
              // %t之后用M表示输出时间的分，%t之后用S表示输出时间的秒
              System.out.printf("%1$tH:%1$tM:%1$tS; %2$tI:%2$tM:%2$tS%n", date, dataL);
              // %t之后用L表示输出时间的秒中的毫秒
              System.out.printf("%1$tH:%1$tM:%1$tS %1$tL%n", date);
              // %t之后p表示输出时间的上午或下午信息
              System.out.printf("%1$tH:%1$tM:%1$tS %1$tL %1$tp%n", date);
             
              // 以下是常见的时间组合
              // %t之后用R表示以"%tH:%tM"格式化时间
              System.out.printf("%1$tR%n", date);
              // %t之后用T表示以"%tH:%tM:%tS"格式化时间
              System.out.printf("%1$tT%n", date);
              // %t之后用r表示以"%tI:%tM:%tS %Tp"格式化时间
              System.out.printf("%1$tr%n", date);
             
              /*** 输出星期***/
              // %t之后用A表示得到星期几的全称
              System.out.printf("%1$tF %1$tA%n", date);
              // %t之后用a表示得到星期几的简称
              System.out.printf("%1$tF %1$ta%n", date);
             
              // 输出时间日期的完整信息
              System.out.printf("%1$tc%n", date);
       }
}
/**
 *printf方法中,格式为"%s"表示以字符串的形式输出第二个可变长参数的第一个参数值;
 *格式为"%n"表示换行;格式为"%S"表示将字符串以大写形式输出;在"%s"之间用"n$"表示
 *输出可变长参数的第n个参数值.格式为"%b"表示以布尔值的形式输出第二个可变长参数
 *的第一个参数值.
 */
/**
 * 格式为"%d"表示以十进制整数形式输出;"%o"表示以八进制形式输出;"%x"表示以十六进制
 * 输出;"%X"表示以十六进制输出,并且将字母(A、B、C、D、E、F)换成大写.格式为"%e"表
 * 以科学计数法输出浮点数;格式为"%E"表示以科学计数法输出浮点数,而且将e大写;格式为
 * "%f"表示以十进制浮点数输出,在"%f"之间加上".n"表示输出时保留小数点后面n位.
 */
/**
 * 格式为"%t"表示输出时间日期类型."%t"之后用y表示输出日期的二位数的年份(如99)、用m
 * 表示输出日期的月份,用d表示输出日期的日号;"%t"之后用Y表示输出日期的四位数的年份
 * (如1999)、用B表示输出日期的月份的完整名,用b表示输出日期的月份的简称."%t"之后用D
 * 表示以"%tm/%td/%ty"的格式输出日期、用F表示以"%tY-%tm-%td"的格式输出日期.
 */
/**
 * "%t"之后用H表示输出时间的时(24进制),用I表示输出时间的时(12进制),用M表示输出时间
 * 分,用S表示输出时间的秒,用L表示输出时间的秒中的毫秒数、用 p 表示输出时间的是上午还是
 * 下午."%t"之后用R表示以"%tH:%tM"的格式输出时间、用T表示以"%tH:%tM:%tS"的格式输出
 * 时间、用r表示以"%tI:%tM:%tS %Tp"的格式输出时间.
 */
/**
 * "%t"之后用A表示输出日期的全称,用a表示输出日期的星期简称.
 */
```



## 12.12、内存流

除了文件之外，IO 操作也可以发生在内存中，发生在内存中的操作流也成为内存流。

内存流也分为两类：

- 字节内存流
	- ByteArrayOutputStream
	- ByteArrayInputStream (Byte buf[])
- 字符内存流
	- CharArrayReader
	- CharArrayWriter



**内存流实现字母的转换**

```java
public class Test {
    public static void main(String[] args) {
        //1.内存流实现字母的转换
        String msg = "hello";
        //2.取得内存的输入输出流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(msg.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        while((len = inputStream.read())!=-1){
            outputStream.write(Character.toUpperCase(len));
        }
        //直接将内存输出流输出
        System.out.println(outputStream);
    }
}
```



**使用 toString() 方法时出现乱码的解决办法**

可以使用`toString(java.nio.charset.Charset charset)`的方式设置编码：`toString(StandardCharsets.UTF_8)`;

或者`new String(outputStream.toByteArray(), StandardCharsets.UTF_8)`;



# 13、网络编程

计算机网络是通过传输介质、通信设施和网络通信协议，把分散在不同地点的计算机设备互连起来的，实现资源共享和数据传输的系统。网络编程就是编写程序使互联网的两个（或多个）设备（如计算机）之间进行数据传输。Java语言对网络编程提供了良好的支持。通过其提供的接口我们可以很方便地进行网络编程。

计算机网络分为网络协议和网络体系结构。



## 13.1、网络体系结构

通过网络发送数据是一项复杂的操作，必须仔细地协调网络的物理特性以及所发送数据的逻辑特征。通过网络将数据从一台主机发送到另外的主机，这个过程是通过计算机网络通信来完成。

网络通信的不同方面被分解为多个层，层与层之间用接口连接。通信的双方具有相同的层次，层次实现的功能由协议数据单元（PDU）来描述。不同系统中的同一层构成对等层，对等层之间通过对等层协议进行通信。每一层表示为物理硬件（即线缆和电流）与所传输信息之间的不同抽象层次。在理论上，每一层只与紧挨其上和其下的层对话。将网络分层，这样就可以修改甚至替换某一层的软件，只要层与层之间的接口保持不变，就不会影响到其他层。

计算机网络体系结构是计算机网络层次和协议的集合，网络体系结构对计算机网络实现的功能，以及网络协议、层次、接口和服务进行了描述，但并不涉及具体的实现。接口是同一节点内相邻层之间交换信息的连接处，也叫服务访问点（SAP）。

计算机网络层次模型：

<img src="../Images/Java/1217276-20190503122042374-56172361.png" alt="img" style="zoom:80%;" />

世界上第一个网络体系结构由IBM公司提出（1974年，SNA），以后其他公司也相继提出自己的网络体系结构。为了促进计算机网络的发展，国际标准化组织ISO在现有网络的基础上，提出了不基于具体机型、操作系统或公司的网络体系结构，称为开放系统互连参考模型，即OSI/RM（Open System Interconnection Reference Model）。

ISO制定的OSI参考模型过于庞大、复杂招致了许多批评。与此相对，美国国防部提出了TCP/IP协议栈参考模型，简化了OSI参考模型，由于TCP/IP协议栈的简单，获得了广泛的应用，并成为后续因特网使用的参考模型。



### 13.1.1、OSI 参考模型

OSI 模型把网络通信的工作分为 7 层，分别是物理层、数据链路层、网络层、传输层、会话层、表示层和应用层。

<img src="../Images/Java/1217276-20190503140552307-1306184849.png" alt="img" style="zoom:80%;" />

- 物理层
	
	> 物理层处于OSI的最底层，是整个开放系统的基础。物理层涉及通信信道上传输的原始比特流（bits），它的功能主要是为数据端设备提供传送数据的通路以及传输数据。
- 数据链路层
	
	> 数据链路层的主要任务是实现计算机网络中相邻节点之间的可靠传输，把原始的、有差错的物理传输线加上数据链路协议以后，构成逻辑上可靠的数据链路。需要完成的功能有链路管理、成帧、差错控制以及流量控制等。其中成帧是对物理层的原始比特流进行界定，数据链路层也能够对帧的丢失进行处理。
- 网络层
	
	> 网络层涉及源主机节点到目的主机节点之间可靠的网络传输，它需要完成的功能主要包括路由选择、网络寻址、流量控制、拥塞控制、网络互连等。
- 传输层
	
	> 传输层起着承上启下的作用，涉及源端节点到目的端节点之间可靠的信息传输。传输层需要解决跨越网络连接的建立和释放，对底层不可靠的网络，建立连接时需要三次握手，释放连接时需要四次挥手。　
- 会话层和表示层
	
	> 会话层的主要功能是负责应用程序之间建立、维持和中断会话，同时也提供对设备和结点之间的会话控制，协调系统和服务之间的交流，并通过提供单工、半双工和全双工3种不同的通信方式，使系统和服务之间有序地进行通信。
	> 表示层关心所传输数据信息的格式定义，其主要功能是把应用层提供的信息变换为能够共同理解的形式，提供字符代码、数据格式、控制信息格式、加密等的统一表示。
- 应用层
	
	> 应用层为OSI的最高层，是直接为应用进程提供服务的。其作用是在实现多个系统应用进程相互通信的同时，完成一系列业务处理所需的服务。



### 13.1.2、TCP/IP参考模型

TCP/IP，即Transmission Control Protocol/Internet Protocol的简写，中译名为传输控制协议/因特网互联协议，是Internet最基本的协议，Internet国际互联网络的基础。

TCP/IP协议是一个开放的网络协议簇，它的名字主要取自最重要的网络层IP协议和传输层TCP协议。TCP/IP协议定义了电子设备如何连入因特网，以及数据如何在它们之间传输的标准。TCP/IP参考模型采用4层的层级结构，每一层都呼叫它的下一层所提供的协议来完成自己的需求，这4个层次分别是：网络接口层、网络层（IP层）、传输层（TCP层）、应用层。

<img src="../Images/Java/1217276-20190503162526581-1315509304.gif" alt="img" style="zoom:80%;" />

- 网络接口层
	TCP/IP协议对网络接口层没有给出具体的描述，网络接口层对应着OSI参考模型的物理层和数据链路层。
- 网络层（IP层）
	网络层是整个TCP/IP协议栈的核心。它的功能是把分组发往目标网络或主机。同时，为了尽快地发送分组，可能需要沿不同的路径同时进行分组传递。因此，分组到达的顺序和发送的顺序可能不同，这就需要上层必须对分组进行排序。网络层除了需要完成路由的功能外，也可以完成将不同类型的网络（异构网）互连的任务。除此之外，互联网层还需要完成拥塞控制的功能。
- 传输层（TCP层）
	TCP层负责在应用进程之间建立端到端的连接和可靠通信，它只存在与端节点中。TCP层涉及两个协议，TCP和UDP。其中，TCP协议提供面向连接的服务，提供按字节流的有序、可靠传输，可以实现连接管理、差错控制、流量控制、拥塞控制等。UDP协议提供无连接的服务，用于不需要或无法实现面向连接的网络应用中。
- 应用层
	应用层为Internet中的各种网络应用提供服务。



## 13.2、网络协议

如同人与人之间相互交流是需要遵循一定的规则（如语言）一样，计算机之间能够进行相互通信是因为它们都共同遵守一定的规则，即网络协议。

OSI参考模型和TCP/IP模型在不同的层次中有许多不同的网络协议，如图所示：

<img src="../Images/Java/1217276-20190503164338008-348345936.png" alt="img" style="zoom:80%;" />

网络协议之间的关系图如下：

<img src="../Images/Java/1217276-20190503165942538-1220277464.png" alt="img" style="zoom:80%;" />

### 13.2.1、IP协议

IP协议（Internet protocol）的作用在于把各种数据包准备无误的传递给对方，其中两个重要的条件是IP地址和MAC地址。由于IP地址是稀有资源，不可能每个人都拥有一个IP地址，所以我们通常的IP地址是路由器给我们生成的IP地址，路由器里面会记录我们的MAC地址。而MAC地址是全球唯一的。举例，IP地址就如同是我们居住小区的地址，而MAC地址就是我们住的那栋楼那个房间那个人。IP地址采用的IPv4格式，目前正在向IPv6过渡。



### 13.2.2、TCP协议

TCP（Transmission Control Protocol）（传输控制协议）是面向连接的传输层协议。TCP 层是位于 IP 层之上，应用层之下的中间层。不同主机的应用层之间经常需要可靠的、像管道一样的连接，但是 IP 层不提供这样的流机制，而是提供不可靠的包交换。TCP 协议采用字节流传输数据。



#### TCP的报文格式

TCP 报文段包括协议首部和数据两部分，协议首部的固定部分是 20 个字节，首部的固定部分后面是选项部分。

<img src="../Images/Java/1217276-20190504001611594-373661106.jpg" alt="img" style="zoom:80%;" />

下面是报文段首部各个字段的含义：

1. **源端口号以及目的端口号**：各占2个字节，端口是传输层和应用层的服务接口，用于寻找发送端和接收端的进程，一般来讲，通过端口号和IP地址，可以唯一确定一个TCP连接，在网络编程中，通常被称为一个socket接口。
2. **序号**：Seq序号，占4个字节、32位。用来标识从TCP发送端向TCP接收端发送的数据字节流。发起方发送数据时对此进行标记。
3. **确认序号**：Ack序号，占4个字节、32位。包含发送确认的一端所期望收到的下一个序号。只有ACK标记位为1时，确认序号字段才有效，因此，确认序号应该是上次已经成功收到数据字节序号加1，即Ack=Seq + 1。
4. **数据偏移**：占4个字节，用于指出TCP首部长度，若不存在选项，则这个值为20字节，数据偏移的最大值为60字节。
5. **保留字段**占6位，暂时可忽略，值全为0。
6. **标志位，6个**
	- URG(紧急)：为1时表明紧急指针字段有效
	- ACK(确认)：为1时表明确认号字段有效
	- PSH(推送)：为1时接收方应尽快将这个报文段交给应用层
	- RST(复位)：为1时表明TCP连接出现故障必须重建连接
	- SYN(同步)：在连接建立时用来同步序号
	- FIN(终止)：为1时表明发送端数据发送完毕要求释放连接
7. **接收窗口：**占2个字节，用于流量控制和拥塞控制，表示当前接收缓冲区的大小。在计算机网络中，通常是用接收方的接收能力的大小来控制发送方的数据发送量。TCP连接的一端根据缓冲区大小确定自己的接收窗口值，告诉对方，使对方可以确定发送数据的字节数。
8. **校验和：**占2个字节，范围包括首部和数据两部分。
9. 选项是可选的，默认情况是不选。



#### 三次握手与四次挥手

==TCP是面向连接的协议，因此每个TCP连接都有3个阶段：连接建立、数据传送和连接释放。连接建立经历三个步骤，通常称为“三次握手”。==

TCP三次握手过程如下：

<img src="../Images/Java/1217276-20190503210424740-1764008697.jpg" alt="img" style="zoom:80%;" />

1. 第一次握手（客户端发送请求）
	客户机发送连接请求报文段到服务器，并进入SYN_SENT状态，等待服务器确认。发送连接请求报文段内容：SYN=1，seq=x；SYN=1意思是一个TCP的SYN标志位置为1的包，指明客户端打算连接的服务器的端口；seq=x表示客户端初始序号x，保存在包头的序列号（Sequence Number）字段里。
2. 第二次握手（服务端回传确认）
	服务器收到客户端连接请求报文，如果同意建立连接，向客户机发回确认报文段（ACK）应答，并为该TCP连接分配TCP缓存和变量。服务器发回确认报文段内容：SYN=1，ACK=1，seq=y，ack=x+1；SYN标志位和ACK标志位均为1，同时将确认序号（Acknowledgement Number）设置为客户的ISN加1，即x+1；seq=y为服务端初始序号y。
3. 第三次握手（客户端回传确认）
	客户机收到服务器的确认报文段后，向服务器给出确认报文段（ACK），并且也要给该连接分配缓存和变量。此包发送完毕，客户端和服务器进入ESTABLISHED（TCP连接成功）状态，完成三次握手。客户端发回确认报文段内容：ACK=1，seq=x+1，ack=y+1；ACK=1为确认报文段；seq=x+1为客户端序号加1；ack=y+1,为服务器发来的ACK的初始序号字段+1。

==注意：握手过程中传送的包里不包含数据，三次握手完毕后，客户端与服务器才正式开始传送数据==

---

TCP四次挥手过程如下：

<img src="../Images/Java/1217276-20190503231436105-1355677452.png" alt="img" style="zoom:80%;" />

由于TCP连接是全双工的，因此每个方向都必须单独进行关闭。这原则是当一方完成它的数据发送任务后就能发送一个FIN来终止这个方向的连接。收到一个FIN只意味着这一方向上没有数据流动，一个TCP连接在收到一个FIN后仍能发送数据。首先进行关闭的一方将执行主动关闭，而另一方执行被动关闭。

1. TCP客户端发送一个FIN，用来关闭客户端到服务端的数据传送，客户端进入FIN_WAIT_1状态。发送报文段内容：FIN=1，seq=u；FIN=1表示请求切断连接；seq=u为客户端请求初始序号。
2. 服务端收到这个FIN，它发回一个ACK给客户端，确认序号为收到的序号加1。和SYN一样，一个FIN将占用一个序号；服务端进入CLOSE_WAIT状态。发送报文段内容：ACK=1，seq=v，ack=u+1；ACK=1为确认报文；seq=v为服务器确认初始序号；ack=u+1为客户端初始序号加1。
3. 服务器关闭客户端的连接后，发送一个FIN给客户端，服务端进入LAST_ACK状态。发送报文段内容：FIN=1，ACK=1，seq=w，ack=u+1；FIN=1为请求切断连接，ACK=1为确认报文，seq=w为服务端请求切断初始序号。
4. 客户端收到FIN后，客户端进入TIME_WAIT状态，接着发回一个ACK报文给服务端确认，并将确认序号设置为收到序号加1，服务端进入CLOSED状态，完成四次挥手。发送报文内容：ACK=1，seq=u+1，ack=w+1；ACK=1为确认报文，seq=u+1为客户端初始序号加1，ack=w+1为服务器初始序号加1。

==注意：为什么连接的时候是三次握手，关闭的时候却是四次挥手？==

因为当服务端收到客户端的SYN连接请求报文后，可以直接发送SYN+ACK报文。其中ACK报文是用来应答的，SYN报文是用来同步的。但是关闭连接时，当服务端收到FIN报文时，很可能并不会立即关闭socket，所以只能先回复一个ACK报文，告诉客户端，“你发的FIN报文，我收到了”。只有等到服务端所有的报文都发送完了，我才能发送FIN报文，因此不能一起发送，故需要四步挥手。



#### TCP粘包

TCP协议是网络通信协议中十分重要的协议，相比于UDP协议来说，它是一个可靠的传输协议，并且是一个面向数据流的协议；所谓面向数据流，其实是指数据传输是以流式的方式传输，这些传输的数据就像一条河里的水，他们之间是没有缝隙的，也就是说TCP协议传输的数据是无边界的；而UDP是面向数据包的，收发数据包要么全收要么不收，数据包与数据包之间是有明显的边界的。

---

**TCP 粘包**

粘包发生在发送或接收缓冲区中；应用程序从缓冲区中取数据是整个缓冲区中有多少取多少；那么就有可能第一个数据的尾部和第二个数据的头部同时存在缓冲区，而TCP是流式的，数据无边界，这时发生粘包。

<img src="../Images/Java/SouthEast" alt="这里写图片描述" style="zoom: 67%;" />



**发送方产生粘包**

采用TCP协议传输数据的客户端与服务器经常是保持一个长连接的状态（一次连接发一次数据不存在粘包），双方在连接不断开的情况下，可以一直传输数据；但当发送的数据包过于的小时，那么TCP协议默认的会启用Nagle算法，将这些较小的数据包进行合并发送（缓冲区数据发送是一个堆压的过程）；这个合并过程就是在发送缓冲区中进行的，也就是说数据发送出来它已经是粘包的状态了。

<img src="../Images/Java/640-162821934986822" alt="img" style="zoom: 67%;" />



**接收方产生粘包**

接收方采用TCP协议接收数据时的过程是这样的：从网络模型的下方传递至传输层，传输层的TCP协议处理是将其放置接收缓冲区，然后由应用层来主动获取（C语言用recv、read等函数）；这时会出现一个问题，就是我们在程序中调用的读取数据函数不能及时的把缓冲区中的数据拿出来，而下一个数据又到来并有一部分放入的缓冲区末尾，等我们读取数据时就是一个粘包；（放数据的速度 > 应用层拿数据速度）

<img src="../Images/Java/SouthEast-162821939066325" alt="这里写图片描述" style="zoom: 50%;" />

---

**TCP粘包解决方案**

在消息的头部添加数据包长度，接收方根据消息长度进行接收。在一条TCP连接上，数据的流式传输**在接收缓冲区里**是有序的，其主要的问题就是第一个包的包尾与第二个包的包头共存接收缓冲区，所以根据长度读取是十分合适的。

也可以使用应用程序在每条消息头前面加协议头、尾部加校验码， 即使粘包了，应用程序可以先解析第一条消息，然后把剩余的数据跟后面的读取的数据先拼接，然后找出一条完整的消息数据，依次类推。

- 解决发送方粘包

	> 发送产生是因为Nagle算法合并小数据包，那么可以禁用掉该算法；
	>
	> TCP提供了强制数据立即传送的操作指令push，当填入数据后调用操作指令就可以立即将数据发送，而不必等待发送缓冲区填充自动发送；
	>
	> 数据包中加头，头部信息为整个数据的长度（最广泛最常用）。

- 解决接收方粘包

	> 解析数据包头部信息，根据长度来接收；
	>
	> 短连接传输，建立一次连接只传输一次数据就关闭；（不推荐）



### 13.2.3、UDP协议

UDP（User Datagram Protocol），用户数据报协议，它是 TCP/IP 协议簇中无连接的运输层协议。

1. UDP 是一个非连接的协议，传输数据之前源端和终端不建立连接，当它想传送时就简单地去抓取来自应用程序的数据，并尽可能快地把它扔到网络上。在发送端，UDP 传送数据的速度仅仅是受应用程序生成数据的速度、计算机的能力和传输带宽的限制；在接收端，UDP 把每个消息段放在队列中，应用程序每次从队列中读一个消息段。
2. 由于传输数据不建立连接，因此也就不需要维护连接状态，包括收发状态等，因此一台服务器可同时向多个客户端传输相同的消息。
3. UDP 信息包的标题很短，只有 8 个字节，相对于 TCP 的 20 个字节信息包的额外开销很小。
4. 吞吐量不受拥挤控制算法的调节，只受应用软件生成数据的速率、传输带宽、源端和终端主机性能的限制。
5. UDP 使用尽量最大努力交付，即不保证可靠交付，因此主机不需要维持复杂的链接状态表。
6. UDP 是面向报文的。发送方的 UDP 对应用程序交下来的报文，在添加首部受就向下交付给 IP 层。既不拆分，也不合并，而是保留这些报文的边界，因此，应用程序需要选择合适的报文大小。

---

**UDP协议格式**

<img src="../Images/Java/1217276-20190504154900038-1234898266.png" alt="img" style="zoom:80%;" />

UDP协议由两部分组成：首部和数据。其中，首部仅有8个字节，包括源端口和目的端口、长度（UDP用于数据报的长度）、校验和。

---

**TCP与UDP的区别**

1. TCP基于连接，UDP是无连接的；
2. 对系统资源的要求，TCP较多，UDP较少；
3. UDP程序结构较简单；
4. TCP是流模式，而UDP是数据报模式；
5. TCP保证数据正确性，而UDP可能丢包；TCP保证数据顺序，而UDP不保证。



### 13.2.4、HTTP协议

HTTP（Hypertext Transfer Protocol），超文本传输协议，它是互联网上应用最为广泛的一种网络协议。HTTP是一种应用层协议，它是基于TCP协议之上的请求/响应式的协议。HTTP协议是Web浏览器和Web服务器之间通信的标准协议。HTTP指定客户端与服务器如何建立连接、客户端如何从服务器请求数据，服务器如何响应请求，以及最后如何关闭连接。HTTP连接使用TCP/IP来传输数据。

对于从客户端到服务器的每一个请求，都有4个步骤：

1. 默认情况下，客户端在端口80打开与服务器的一个TCP连接，URL中还可以指定其他端口。
2. 客户端向服务器发送消息，请求指定路径上的资源。这个资源包括一个首部，可选地（取决于请求的性质）还可以有一个空行，后面是这个请求的数据。
3. 服务器向客户端发送响应。响应以响应码开头，后面是包含数据的首部、一个空行以及所请求的文档或错误消息。
4. 服务器关闭连接。

现在使用的HTTP协议是HTTP/1.1版本，1997年之前采用的是HTTP1.0版本。HTTP连接在1.0版本中采用非持续连接工作方式，1.1版本采用的是持续连接工作方式，持续连接是指服务器在发送响应后仍然在一段时间内保持这条由TCP运输层协议建立起来的连接，使客户端和服务器可以继续在这条连接上传输HTTP报文。

是否采用持续连接工作方式，1.0中默认是关闭的，需要在HTTP头加入“Connection:Keep-Alive”，才能启用Keep-Alive。HTTP1.1中默认启用Keep-Alive，如果加入“Connection:close”，才关闭。目前大部分浏览器都是用HTTP1.1协议，也就是说默认都会发起Keep-Alive的连接请求了，所以是否能完成一个完整的Keep-Alive连接就看服务器设置情况。

---

**HTTP报文**

HTTP协议是基于TCP协议之上的请求/响应式协议，下面主要介绍HTTP报文的格式，HTTP报文主要有请求报文和响应报文两种。

HTTP请求报文的格式：

<img src="../Images/Java/1217276-20190504230113716-231823746.png" alt="img" style="zoom:80%;" />

HTTP请求报文由**请求行、首部行和实体主体**组成，由浏览器发送给服务器。上面这张图中SP表示空格，cr lf表示回车和换行。下图是谷歌浏览器内访问服务器查看的HTTP请求例子：

<img src="../Images/Java/1217276-20190505002716130-1447539444.png" alt="img" style="zoom:80%;" />

HTTP响应报文格式：

<img src="../Images/Java/1217276-20190505001517048-1489396333.png" alt="img" style="zoom:80%;" />

上面这张图是HTTP响应报文，它由**状态行、首部行和实体主体**组成。下图为HTTP响应报文例子：

<img src="../Images/Java/1217276-20190505003116790-1900233646.png" alt="img" style="zoom:80%;" />

---

**HTTP请求方法和响应状态码**

在上面的HTTP请求报文例子中，可以看到请求方法是GET，这表示请求读取由URL所标志的信息，除了GET，还有其他几种常用的方法。

<img src="../Images/Java/1217276-20190505003337425-1910585386.png" alt="img" style="zoom:80%;" />

在HTTP响应报文的例子中，可以看到状态码是200，表示响应成功。下表是其他状态码，总共5大类，33种。

<img src="../Images/Java/1217276-20190505004242552-665354194.png" alt="img" style="zoom:80%;" />

---

**HTTP和HTTPS的区别**

HTTPS（全称：Hyper Text Transfer Protocol over Secure Socket Layer），是以安全为目标的HTTP通道，简单来说就是HTTP的安全版。即HTTP下加入SSL层，HTTPS的安全基础是SSL，因此加密的详细内容就需要SSL。它是一个URL scheme（抽象标识符体系），句法类同http:体系，用于安全的HTTP数据传输。https:URL表明它使用了HTTP，但HTTPS存在不同于HTTP的默认端口及一个加密/身份验证层（在HTTP与TCP之间）。

HTTP协议被用于在Web浏览器和网站服务器之间传递信息。HTTP协议以明文方式发送内容，不提供任何方式的数据加密，如果攻击者截取了Web浏览器和网站服务器之间的传输报文，就可以直接读懂其中的信息，因此HTTP协议不适合传输一些敏感信息，比如信用开号、密码等。

为了解决HTTP协议的这一缺陷，需要使用另一种协议：安全套接字层超文本传输协议HTTPS。为了数据传输的安全，HTTPS在HTTP的基础上加入了SSL协议，SSL依靠证书来验证服务器的身份，并为浏览器和服务器之间的通信加密。

HTTPS和HTTP的区别主要为以下四点：

- https协议需要到ca申请证书，一般免费证书很少，需要缴费。
- http是超文本传输协议，信息是明文传输，https则是具有安全性的ssl加密传输协议。
- http和https使用的是完全不同的连接方式，用的端口也不一样，前者是80，后者是443。
- http的连接很简单，是无状态的；https协议是有ssl+http协议构建的可进行加密传输、身份认证的网络协议，比http协议安全。

---

**HTTP和TCP/IP协议的关系**

网络中有一段比较容易理解的介绍：

“我们在传输数据时，可以只使用（传输层）TCP/IP协议，但是那样的话，如果没有应用层，便无法识别数据内容，如果想要使传输的数据有意义，则必须使用到应用层协议，应用层协议有很多，比如HTTP、FTP、TELNET等，也可以自己定义应用层协议。WEB使用HTTP协议作应用层协议，以封装HTTP文本信息，然后使用TCP/IP做传输层协议将它发到网络上。”



## 13.3、Socket

### 13.3.1、Socket概述

Java的网络编程主要涉及到的内容是Socket编程。Socket，套接字，就是两台主机之间逻辑连接的端点。TCP/IP协议是传输层协议，主要解决数据如何在网络中传输，而HTTP是应用层协议，主要解决如何包装数据。Socket是通信的基石，是支持TCP/IP协议的网络通信的基本操作单元。它是网络通信过程中端点的抽象表示，包含进行网络通信必须的五种信息：连接使用的协议、本地主机的IP地址、本地进程的协议端口、远程主机的IP地址、远程进程的协议端口。

应用层通过传输层进行数据通信时，TCP会遇到同时为多个应用程序进程提供并发服务的问题。多个TCP连接或多个应用程序进程可能需要通过同一个TCP协议端口传输数据。为了区别不同的应用程序进程和连接，许多计算机操作系统为应用程序与TCP/IP协议交互提供了套接字（Socket）接口。应用层可以和传输层通过Socket接口，区分来自不同应用程序进程或网络连接的通信，实现数据传输的并发服务。

Socket，实际上是对TCP/IP协议的封装，Socket本身并不是协议，而是一个调用接口（API），通过Socket，我们才能使用TCP/IP协议。实际上，Socket跟TCP/IP协议没有必然的关系，Socket编程接口在设计的时候，就希望也能适应其他的网络协议。所以说，Socket的出现，只是使得程序员更方便地使用TCP/IP协议栈而已，是对TCP/IP协议的抽象，从而形成了我们知道的一些最基本的函数接口，比如create、listen、accept、send、read和write等等。网络有一段关于socket和TCP/IP协议关系的说法比较容易理解：

“TCP/IP只是一个协议栈，就像操作系统的运行机制一样，必须要具体实现，同时还要提供对外的操作接口。这个就像操作系统会提供标准的编程接口，比如win32编程接口一样，TCP/IP也要提供可供程序员做网络开发所用的接口，这就是Socket编程接口。” 

实际上，传输层的TCP是基于网络层的IP协议的，而应用层的HTTP协议又是基于传输层的TCP协议的，而Socket本身不算是协议，就像上面所说，它只是提供了一个针对TCP或者UDP编程的接口。socket是对端口通信开发的工具,它要更底层一些。



### 13.3.2、套接字有哪些类型

这个世界上有很多种套接字（socket），比如 DARPA Internet 地址（Internet 套接字）、本地节点的路径名（Unix套接字）、CCITT X.25地址（X.25 套接字）等。但本教程只讲第一种套接字——Internet 套接字，它是最具代表性的，也是最经典最常用的。

根据数据的传输方式，可以将 Internet 套接字分成两种类型。通过 socket() 函数创建连接时，必须告诉它使用哪种数据传输方式。

---

**流格式套接字（SOCK_STREAM）**

流格式套接字（Stream Sockets）也叫“面向连接的套接字”，在代码中使用 SOCK_STREAM 表示。

==SOCK_STREAM 是一种可靠的、双向的通信数据流，数据可以准确无误地到达另一台计算机，如果损坏或丢失，可以重新发送。==

SOCK_STREAM 有以下几个特征：

- 数据在传输过程中不会消失；
- 数据是按照顺序传输的；
- 数据的发送和接收不是同步的（有的教程也称“不存在数据边界”）。

可以将 SOCK_STREAM 比喻成一条传送带，只要传送带本身没有问题（不会断网），就能保证数据不丢失；同时，较晚传送的数据不会先到达，较早传送的数据不会晚到达，这就保证了数据是按照顺序传递的。

<img src="../Images/Java/1-1Z1232154153L.gif" alt="将面向连接的套接字比喻成传送带" style="zoom:80%;" />

为什么流格式套接字可以达到高质量的数据传输呢？这是因为它使用了 TCP 协议（The Transmission Control Protocol，传输控制协议），TCP 协议会控制你的数据按照顺序到达并且没有错误。

你也许见过 TCP，是因为你经常听说“TCP/IP”。TCP 用来确保数据的正确性，IP（Internet Protocol，网络协议）用来控制数据如何从源头到达目的地，也就是常说的“路由”。

假设传送带传送的是水果，接收者需要凑齐 100 个后才能装袋，但是传送带可能把这 100 个水果分批传送，比如第一批传送 20 个，第二批传送 50 个，第三批传送 30 个。接收者不需要和传送带保持同步，只要根据自己的节奏来装袋即可，不用管传送带传送了几批，也不用每到一批就装袋一次，可以等到凑够了 100 个水果再装袋。

流格式套接字的内部有一个缓冲区（也就是字符数组），通过 socket 传输的数据将保存到这个缓冲区。接收端在收到数据后并不一定立即读取，只要数据不超过缓冲区的容量，接收端有可能在缓冲区被填满以后一次性地读取，也可能分成好几次读取。

也就是说，不管数据分几次传送过来，接收端只需要根据自己的要求读取，不用非得在数据到达时立即读取。传送端有自己的节奏，接收端也有自己的节奏，它们是不一致的。

流格式套接字有什么实际的应用场景吗？浏览器所使用的 http 协议就基于面向连接的套接字，因为必须要确保数据准确无误，否则加载的 HTML 将无法解析。

---

**数据报格式套接字（SOCK_DGRAM）**

数据报格式套接字（Datagram Sockets）也叫“无连接的套接字”，在代码中使用 SOCK_DGRAM 表示。

计算机只管传输数据，不作数据校验，如果数据在传输中损坏，或者没有到达另一台计算机，是没有办法补救的。也就是说，数据错了就错了，无法重传。

因为数据报套接字所做的校验工作少，所以在传输效率方面比流格式套接字要高。

可以将 SOCK_DGRAM 比喻成高速移动的摩托车快递，它有以下特征：

- 强调快速传输而非传输顺序；
- 传输的数据可能丢失也可能损毁；
- 限制每次传输的数据大小；
- 数据的发送和接收是同步的（有的教程也称“存在数据边界”）。

众所周知，速度是快递行业的生命。用摩托车发往同一地点的两件包裹无需保证顺序，只要以最快的速度交给客户就行。这种方式存在损坏或丢失的风险，而且包裹大小有一定限制。因此，想要传递大量包裹，就得分配发送。

<img src="../Images/Java/1-1Z123222015527.gif" alt="将无连接套接字比喻成摩托车快递" style="zoom:80%;" />

另外，用两辆摩托车分别发送两件包裹，那么接收者也需要分两次接收，所以“数据的发送和接收是同步的”；换句话说，接收次数应该和发送次数相同。

==总之，数据报套接字是一种不可靠的、不按顺序传递的、以追求速度为目的的套接字。==

数据报套接字也使用 IP 协议作路由，但是它不使用 TCP 协议，而是使用 UDP 协议（User Datagram Protocol，用户数据报协议）。

QQ 视频聊天和语音聊天就使用 SOCK_DGRAM 来传输数据，因为首先要保证通信的效率，尽量减小延迟，而数据的正确性是次要的，即使丢失很小的一部分数据，视频和音频也可以正常解析，最多出现噪点或杂音，不会对通信质量有实质的影响。



## 13.4、TCP/IP 通讯的实现

套接字使用TCP提供了两台计算机之间的通信机制。 客户端程序创建一个套接字，并尝试连接服务器的套接字。

当连接建立时，服务器会创建一个 Socket 对象。客户端和服务器现在可以通过对 Socket 对象的写入和读取来进行通信。

java.net.Socket 类代表一个套接字，并且 java.net.ServerSocket 类为服务器程序提供了一种来监听客户端，并与他们建立连接的机制。

以下步骤在两台计算机之间使用套接字建立TCP连接时会出现：

- 服务器实例化一个 ServerSocket 对象，表示通过服务器上的端口通信。
- 服务器调用 ServerSocket 类的 accept() 方法，该方法将一直等待，直到客户端连接到服务器上给定的端口。
- 服务器正在等待时，一个客户端实例化一个 Socket 对象，指定服务器名称和端口号来请求连接。
- Socket 类的构造函数试图将客户端连接到指定的服务器和端口号。如果通信被建立，则在客户端创建一个 Socket 对象能够与服务器进行通信。
- 在服务器端，accept() 方法返回服务器上一个新的 socket 引用，该 socket 连接到客户端的 socket。

连接建立后，通过使用 I/O 流在进行通信，每一个socket都有一个输出流和一个输入流，客户端的输出流连接到服务器端的输入流，而客户端的输入流连接到服务器端的输出流。

TCP 是一个双向的通信协议，因此数据可以通过两个数据流在同一时间发送。以下是一些类提供的一套完整的有用的方法来实现 socket。

### 13.4.1、ServerSocket类

服务器应用程序通过使用 java.net.ServerSocket 类以获取一个端口,并且侦听客户端请求。

ServerSocket 类有四个构造方法：

| 方法                                                         | 方法描述                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public ServerSocket(int port) throws IOException             | 创建绑定到特定端口的服务器套接字                             |
| public ServerSocket(int port, int backlog) throws IOException | 利用指定的 backlog 创建服务器套接字并将其绑定到指定的本地端口号 |
| public ServerSocket(int port, int backlog, InetAddress address) throws IOException | 使用指定的端口、侦听 backlog 和要绑定到的本地 IP 地址创建服务器 |
| public ServerSocket() throws IOException                     | 创建非绑定服务器套接字                                       |

 如果 ServerSocket 构造方法没有抛出异常，就意味着你的应用程序已经成功绑定到指定的端口，并且侦听客户端请求。

ServerSocket 类的常用方法：

| 方法                                       | 方法描述                                          |
| ------------------------------------------ | ------------------------------------------------- |
| int getLocalPort()                         | 返回此套接字在其上侦听的端口                      |
| Socket accept() throws IOException         | 侦听并接受到此套接字的连接                        |
| void setSoTimeout(int timeout)             | 通过指定超时值启用/禁用 SO_TIMEOUT，以毫秒为单位  |
| void bind(SocketAddress host, int backlog) | 将 ServerSocket 绑定到特定地址（IP 地址和端口号） |



### 13.4.2、Socket类

java.net.Socket 类代表客户端和服务器都用来互相沟通的套接字。客户端要获取一个 Socket 对象通过实例化 ，而服务器获得一个 Socket 对象则通过 accept() 方法的返回值。

Socket 类有五个构造方法：

| 方法                                                         | 方法描述                                               |
| ------------------------------------------------------------ | ------------------------------------------------------ |
| public Socket(String host, int port) throws UnknownHostException, IOException | 创建一个流套接字并将其连接到指定主机上的指定端口号     |
| public Socket(InetAddress host, int port) throws IOException | 创建一个流套接字并将其连接到指定 IP 地址的指定端口号   |
| public Socket(String host, int port, InetAddress localAddress, int localPort) throws IOException | 创建一个套接字并将其连接到指定远程主机上的指定远程端口 |
| public Socket(InetAddress host, int port, InetAddress localAddress, int localPort) throws IOException | 创建一个套接字并将其连接到指定远程地址上的指定远程端口 |
| public Socket()                                              | 通过系统默认类型的 SocketImpl 创建未连接套接字         |

当 Socket 构造方法返回，并没有简单的实例化了一个 Socket 对象，它实际上会尝试连接到指定的服务器和端口。

下面列出了一些感兴趣的方法，注意客户端和服务器端都有一个 Socket 对象，所以无论客户端还是服务端都能够调用这些方法。

| 方法                                                         | 方法描述                                            |
| ------------------------------------------------------------ | --------------------------------------------------- |
| void connect(SocketAddress host, int timeout) throws IOException | 将此套接字连接到服务器，并指定一个超时值            |
| InetAddress getInetAddress()                                 | 返回套接字连接的地址                                |
| int getPort()                                                | 返回此套接字连接到的远程端口                        |
| int getLocalPort()                                           | 返回此套接字绑定到的本地端口                        |
| SocketAddress getRemoteSocketAddress()                       | 返回此套接字连接的端点的地址，如果未连接则返回 null |
| InputStream getInputStream() throws IOException              | 返回此套接字的输入流                                |
| OutputStream getOutputStream() throws IOException            | 返回此套接字的输出流                                |
| void close() throws IOException                              | 关闭此套接字                                        |



### 13.4.3、InetAddress类

这个类表示互联网协议(IP)地址。下面列出了 Socket 编程时比较有用的方法：

| 方法                                                      | 方法描述                                          |
| --------------------------------------------------------- | ------------------------------------------------- |
| static InetAddress getByAddress(byte[] addr)              | 在给定原始 IP 地址的情况下，返回 InetAddress 对象 |
| static InetAddress getByAddress(String host, byte[] addr) | 根据提供的主机名和 IP 地址创建 InetAddress        |
| static InetAddress getByName(String host)                 | 在给定主机名的情况下确定主机的 IP 地址            |
| String getHostAddress()                                   | 返回 IP 地址字符串（以文本表现形式）              |
| String getHostName()                                      | 获取此 IP 地址的主机名                            |
| static InetAddress getLocalHost()                         | 返回本地主机                                      |
| String toString()                                         | 将此 IP 地址转换为 String                         |



### 13.4.4、实现聊天室聊天

服务处理器：

```java
public class ServerHandler extends Thread {
    Socket socket;
    List<Socket> list;

    public ServerHandler(List<Socket> list, Socket socket) {
        this.list = list;
        this.socket = socket;
    }

    public void run() {
        InetAddress inetAddress = socket.getInetAddress();
        System.out.println(inetAddress.getHostAddress() + "上线了...");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String string = "";
            while ((string = bufferedReader.readLine()) != null) {
                string = inetAddress.getHostAddress() + ":" + string;
                send(string);
            }
        } catch (IOException e) {
            System.out.println(inetAddress.getHostAddress() + "下线...");
            list.remove(socket);
        }
    }

    public synchronized void send(String message) throws IOException {
        for (Socket sk : list) {
            if (sk != socket) {
                PrintStream printStream = new PrintStream(sk.getOutputStream());
                printStream.println(message);
            }
        }
    }
}

```

服务器：

```java
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8898);
        System.out.println("服务器启动...");

        List<Socket> list = new ArrayList<>();

        while (true) {
            Socket socket = serverSocket.accept();
            synchronized (list) {
                list.add(socket);
            }
            new ServerHandler(list, socket).start();
        }
    }
}
```

客户端发送信息线程：

```java
public class ClientSend extends Thread {
    Socket socket;

    public ClientSend(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            Scanner in = new Scanner(System.in);
            System.out.println("请输入信息");
            while (true) {
                printStream.println(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("当前客户端离线");
            System.exit(0);
        }
    }
}
```

客户端接收信息线程：

```java
public class ClientReader extends Thread {
    Socket socket;

    public ClientReader(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            String string = "";
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println(string);
            }
        } catch (IOException e) {
            System.out.println("服务器已关闭");
            System.exit(0);
        }
    }
}
```

客户端：

```java
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.42.165", 8898);

        new ClientSend(socket).start();

        new ClientReader(socket).start();
    }
}
```



## 13.5、UDP 通讯的实现

与TCP相比，UDP是一种无连接的传输层协议，提供面向事务的简单不可靠信息传送服务。UDP通讯主要用到两个类DatagramPacket和DatagramSocket。

### 13.5.1、DatagramSocke

此类表示用来发送和接收数据报包的套接字。

数据报套接字是包投递服务的发送或接收点。每个在数据报套接字上发送或接收的包都是单独编址和路由的。从一台机器发送到另一台机器的多个包可能选择不同的路由，也可能按不同的顺序到达。

构造方法:

| 方法                                        | 方法描述                                                 |
| ------------------------------------------- | -------------------------------------------------------- |
| DatagramSocket()                            | 构造数据报套接字并将其绑定到本地主机上的任何可用端口     |
| DatagramSocket(DatagramSocketImpl impl      | 使用指定的DatagramSocketImpl创建一个未绑定的数据报套接字 |
| DatagramSocket(int port)                    | 构造数据报套接字并将其绑定到本地主机上的指定端口         |
| DatagramSocket(int port, InetAddress laddr) | 创建一个数据报套接字，绑定到指定的本地地址               |
| DatagramSocket(SocketAddress bindaddr)      | 创建一个数据报套接字，绑定到指定的本地套接字地址         |

常用的方法：

| 方法                                        | 方法描述                                          |
| ------------------------------------------- | ------------------------------------------------- |
| void bind(SocketAddress add)                | 将此DatagramSocket绑定到特定的地址和端口          |
| boolean isBound()                           | 返回套接字的绑定状态                              |
| void close()                                | 关闭此数据报套接字                                |
| void isClosed()                             | 返回套接字是否关闭                                |
| void connect(InetAddress address, int port) | 将套接字连接到此套接字的远程地址                  |
| void connect(SocketAddress addr)            | 将此套接字连接到远程套接字地址（IP地址+端口号）   |
| boolean isConnected()                       | 返回套接字的连接状态                              |
| InetAddress getInetAddress()                | 返回此套接字连接到的地址                          |
| InetAddress getLocalAddress()               | 获取套接字所绑定的本地地址                        |
| int getLocalPort()                          | 返回此套接字绑定到的本地主机上的端口号            |
| SocketAddress getLocalSocketAddress()       | 返回此套接字绑定到的端点的地址                    |
| int getPort()                               | 返回此套接字连接到的端口号                        |
| SocketAddress getRemoteSocketAddress()      | 返回此套接字连接，或端点的地址 `null`如果是未连接 |
| void receive(DatagramPacket p)              | 从此套接字接收数据报包                            |
| void send(DatagramPacket)                   | 从此套接字发送数据报包                            |



### 13.5.2、DatagramPacket

该类表示数据报包。

数据报包用于实现无连接分组传送服务。 仅基于该数据包中包含的信息，每个消息从一台机器路由到另一台机器。 从一台机器发送到另一台机器的多个分组可能会有不同的路由，并且可能以任何顺序到达。 包传送不能保证。 

| 方法                                                         | 方法描述                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| DatagramPacket(byte[] buf, int length)                       | 构造一个长度为lendth用于接收的数据包 DatagramPacket          |
| DatagramPacket(byte[] buf, int length, InetAddress address, int port) | 构造用于发送长度的分组的数据报包，length为长度，address指定主机，port指定端口号 |



### 13.5.3、实现聊天室聊天

服务控制器：

```java
public class ServerHandler extends Thread {
    Socket socket;
    List<Socket> list;

    public ServerHandler(List<Socket> list, Socket socket) {
        this.list = list;
        this.socket = socket;
    }

    public void run() {
        InetAddress inetAddress = socket.getInetAddress();
        System.out.println(inetAddress.getHostAddress() + "上线了...");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String string = "";
            while ((string = bufferedReader.readLine()) != null) {
                string = inetAddress.getHostAddress() + ":" + string;
                send(string);
            }
        } catch (IOException e) {
            System.out.println(inetAddress.getHostAddress() + "下线...");
            list.remove(socket);
        }
    }

    public synchronized void send(String message) throws IOException {
        for (Socket sk : list) {
            if (sk != socket) {
                PrintStream printStream = new PrintStream(sk.getOutputStream());
                printStream.println(message);
            }
        }
    }
}

```

服务器：

```java
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8898);
        System.out.println("服务器启动...");

        List<Socket> list = new ArrayList<>();

        while (true) {
            Socket socket = serverSocket.accept();
            synchronized (list) {
                list.add(socket);
            }
            new ServerHandler(list, socket).start();
        }
    }
}
```

客户端接收信息线程：

```java
public class ClientSend extends Thread {
    Socket socket;

    public ClientSend(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            Scanner in = new Scanner(System.in);
            System.out.println("请输入信息");
            while (true) {
                printStream.println(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("当前客户端离线");
            System.exit(0);
        }
    }
}
```

客户端发送信息线程：

```java
public class ClientReader extends Thread {
    Socket socket;

    public ClientReader(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            String string = "";
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println(string);
            }
        } catch (IOException e) {
            System.out.println("服务器已关闭");
            System.exit(0);
        }
    }
}
```

客户端：

```java
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.42.165", 8898);

        new ClientSend(socket).start();

        new ClientReader(socket).start();
    }
}
```

---

**利用广播IP实现群聊**

广播地址(Broadcast Address)是专门用于同时向网络中所有工作站进行发送的一个地址。在使用TCP/IP 协议的网络中，主机标识段host ID 为全1 的IP 地址为广播地址，广播的分组传送给host ID段所涉及的所有计算机。例如，对于10.1.1.0 （255.255.255.0 ）网段，其广播地址为10.1.1.255 （255 即为2 进制的11111111 ），当发出一个目的地址为10.1.1.255 的分组（封包）时，它将被分发给该网段上的所有计算机。

```java
/**
 * 发送消息
 *
 * @author LIU
 */
class Send implements Runnable {
    DatagramSocket ds;

    public Send(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            Scanner s = new Scanner(System.in);
            while (true) {
                String sendBuf = s.nextLine();
                // 发送的 内容，长度，IP，端口
                DatagramPacket dpSend = new DatagramPacket(sendBuf.getBytes(), sendBuf.getBytes().length, InetAddress.getByName("192.168.8.255"), 9988);
                ds.send(dpSend);

                System.out.println("我说：" + sendBuf);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}


/**
 * 接收消息
 *
 * @author LIU
 */
class Recv implements Runnable {
    DatagramSocket ds;

    public Recv(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while (true) {
                byte[] recvBuf = new byte[1024];
                //接收的 内容，长度
                DatagramPacket dpRecv = new DatagramPacket(recvBuf, 1024);
                ds.receive(dpRecv);
                String str = new String(dpRecv.getData(), 0, dpRecv.getLength());

                System.out.println(dpRecv.getAddress().getHostAddress() + "说：" + str);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}


/**
 * 开始执行对话
 *
 * @author LIU
 */
public class UDP {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            DatagramSocket ds = new DatagramSocket(9988);

            new Thread(new Send(ds)).start();
            new Thread(new Recv(ds)).start();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
```



## 13.6、基于 HTTP 协议编程

http（Hypertext transfer protocol）超文本传输协议，通过浏览器和服务器进行数据交互，进行超文本（文本、图片、视频等）传输的规定。也就是说，http 协议规定了超文本传输所要遵守的规则。那么网页在输入 URL 到加载，http 究竟做了哪些工作呢？（见下图）

<img src="../Images/Java/17230018-9fe0c3c244864b36b22ce0eb7ec74842.png" alt="img" style="zoom:80%;float:left" />

浏览器负责发起请求和最后的响应请求，服务器接收请求后，处理请求。我们一步一步来看这个过程，http是如何设定步骤，设置规范的。

1. 输入URL。不管是链接还是地址栏的输入，情况都是一样的。http 协议已经规定了 URL 的格式，通过 http 协议中的域名或IP找到服务器。

2. 找到服务器的同时，会有 http 的请求发送过来，告诉服务器我求你做什么？http 协议规定了发送请求的格式**，这个格式有三部分组成请求行、请求头、请求体。

	请求行包括请求的方式（get、post或其他）、要求响应的文件、http 版本。

	请求头包括本机信息、浏览器信息等等，当然，也包括URL中 ？后面的参数。如图：

	<img src="../Images/Java/17231440-44a1e2aa088b40e9b444361c4a5548ae.png" alt="img" style="zoom: 80%;" />
	请求体包括 POST 传递数据的相关信息，Get 方式传值时，请求体为空。

3. 请求信息发送至服务器以后，服务器会获取传递过来的相关信息进行后端程序的处理。一般通过 request.querystring 获取URL传递过来的值、通过 request.form 获取 POST 传递过来的值，当然，也是可以获取到所有的其他请求过来的信息，如浏览器信息、cookie信息、操作系统信息等。获取相关的数据以后，服务器就会根据程序进行处理。

4. 处理完成以后，服务器会做出响应，向浏览器输出相关信息。http 对响应的格式也做出了规定，响应的信息主要包括，响应码、响应头、响应体。
	响应码用来标识服务器响应的结果，如我们常看到的200、404等。大致的分类如下：1开头的表示消息，2开头表示成功，3开头表示重定向，4开头表示失败，5开头表示服务器异常。

	响应头记录服务器相关信息如服务器是否启用压缩、服务器为 IIS 或 Ngnix、程序所用服务端语言等等。当然，缓存也是在这里设置的，通过修改响应头可以修改 html 在本地缓存的情况，如设置浏览器缓存过期的时间。

	<img src="../Images/Java/17233401-2eb186f25c0d4780b0d31111b6ce941f.png" alt="img" style="zoom:80%;" />
	响应体主要是看到的html的相关内容了。

 完成以上四部操作以后，浏览器就断开了与服务器的数据连接，不能再进行数据传输，如果需要再次进行数据传输，那么一切就要从输入URL开始。

如此，便是一个完整的网页流程，http 从中的作用就是对整个流程进行规定，包括执行步骤，每一步的数据格式。只有了解 http 协议以及网页是如何产生的以后，才能对网页进行更好的控制，例如控制浏览器缓存、通过非浏览器发送 http 请求、get 和 post 传值的选择，甚至是建立长连接，这些都是以 http 协议为基础。

----

HTTP 的底层是 TCP/IP。所以 GET 和 POST 的底层也是 TCP/IP，也就是说，GET/POST 都是 TCP 链接。GET 和 POST 能做的事情是一样一样的。你要给 GET 加上 request body，给 POST 带上url参数，技术上是完全行的通的。

 在大万维网世界中，TCP 就像汽车，我们用 TCP 来运输数据，它很可靠，从来不会发生丢件少件的现象。但是如果路上跑的全是看起来一模一样的汽车，那这个世界看起来是一团混乱，送急件的汽车可能被前面满载货物的汽车拦堵在路上，整个交通系统一定会瘫痪。为了避免这种情况发生，交通规则 HTTP 诞生了。HTTP 给汽车运输设定了好几个服务类别，有 GET, POST, PUT, DELETE 等等，HTTP 规定，当执行 GET 请求的时候，要给汽车贴上GET 的标签（设置method为GET），而且要求把传送的数据放在车顶上（url中）以方便记录。如果是 POST请求，就要在车上贴上 POST 的标签，并把货物放在车厢里。当然，你也可以在 GET 的时候往车厢内偷偷藏点货物，但是这是很不光彩；也可以在POST的时候在车顶上也放一些数据，让人觉得傻乎乎的。HTTP只是个行为准则，而 TCP 才是 GET 和 POST 怎么实现的基本。

还有另一个重要的角色：运输公司。不同的浏览器（发起http请求）和服务器（接受http请求）就是不同的运输公司。 虽然理论上，你可以在车顶上无限的堆货物（url中无限加参数）。但是运输公司可不傻，装货和卸货也是有很大成本的，他们会限制单次运输量来控制风险，数据量太大对浏览器和服务器都是很大负担。业界不成文的规定是，（大多数）浏览器通常都会限制 url 长度在 2K 个字节，而（大多数）服务器最多处理 64K 大小的 url。超过的部分，恕不处理。如果你用 GET 服务，在 request body 偷偷藏了数据，不同服务器的处理方式也是不同的，有些服务器会帮你卸货，读出数据，有些服务器直接忽略，所以，虽然 GET 可以带 request body，也不能保证一定能被接收到哦。

---

```java
public class HttpDemo {
	public static void main(String[] args) throws Exception {
		// 使用HTTP协议方式获取网络资源地址
		URL url = new URL("http://www.mtpiao.com");
		// 通过url对象提供的 openConnection 方法获取资源链接
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		// 通过字符缓冲流获取网络信息
		BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
		// 读取
		String str = "";
		while ((str = br.readLine()) != null) {
			System.out.println(str);
		}
		br.close();
	}
}
```



# 14、XML 编程

XML，即可扩展标记语言（Extensible Markup Language），标准通用标记语言的子集，一种用于标记电子文件使其具有结构性的标记语言。它可以用来标记数据、定义数据类型，是一种允许用户对自己的标记语言进行定义的源语言。它非常适合万维网传输，提供统一的方法描述和交换独立于应用程序或供应商的结构化数据。

- XML是一种标记语言，类似HTML
- XML设计宗旨是传输数据，而非显示数据
- XML标签没有被预定义，需要自行定义标签
- XML被设计为具有自我描述性
- XML是W3C的推荐标准

XML解析方法分为5种：1、DOM解析；2、SAX解析；3、STAX解析；4、JDOM解析；5、DOM4J解析。其中DOM解析和SAX解析属于基础方法，是官方提供的平台无关的解析方式；JDOM解析和DOM4J属于扩展方法，它们是在基础的方法上扩展出来的，只适用于java平台。

## 14.1、DOM 解析

DOM的全称是Document Object Model，即文档对象模型。在应用程序中，基于DOM的XML分析器将一个XML文档转换成一个对象模型的集合（通常称DOM树），应用程序正是通过对这个对象模型的操作，来实现对XML文档数据的操作。通过DOM接口，应用程序可以在任何时候访问XML文档中的任何一部分数据，因此，这种利用DOM接口的机制也被称作随机访问机制。

DOM接口提供了一种通过分层对象模型来访问XML文档信息的方式，这些分层对象模型依据XML的文档结构形成了一棵节点树。无论XML文档中所描述的是什么类型的信息，即便是制表数据、项目列表或一个文档，利用DOM所生成的模型都是节点树的形式。也就是说，DOM强制使用树模型来访问XML文档中的信息。由于XML本质上就是一种分层结构，所以这种描述方法是相当有效的。

DOM树所提供的随机访问方式给应用程序的开发带来了很大的灵活性，它可以任意地控制整个XML文档中的内容。然而，由于DOM分析器把整个XML文档转化成DOM树放在了内存中，因此，当文档比较大或者结构比较复杂时，对内存的需求就比较高。而且，对于结构复杂的树的遍历也是一项耗时的操作。所以，DOM分析器对机器性能的要求比较高，实现效率不十分理想。不过，由于DOM分析器所采用的树结构的思想与XML文档的结构相吻合，同时鉴于随机访问所带来的方便，因此，DOM分析器还是有很广泛的使用价值的。

优点：

- 形成了树结构，有助于更好的理解、掌握，且代码容易编写
- 解析过程中，树结构保存在内存中，方便修改
- 可通过节点名来获取节点

缺点：

- 由于文件是一次性读取，所以对内存的耗费比较大，容易引发内存溢出等问题

下面是DOM解析与创建XML文件的完整代码：

```java
package com.xiaobaizhiqian;
 
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class DOMTest {
 
	public static void main(String[] args) {
		Date string = new Date();
		long str1 = string.getTime();
		readeXMLByDOM();
		Date string1 = new Date();
		long str2 = string1.getTime();
		long str = str2 - str1;
		System.out.println(str);
	}
 
	public static void readeXMLByDOM() {
		// 创建一个xml文件解析工厂
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
 
		try {
			// 创建一个解析工厂基本接口
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			// 通过DocumentBuilder的parse方法加载文件
			Document document = documentBuilder.parse("languages.xml");
			// 获取根目录
			Element element = document.getDocumentElement();
			document.getElementsByTagName("");
			// 通过根目录，根据节点名称，获取所有该名称的节点
			NodeList nodeList = element.getElementsByTagName("lan");
			// 也可通过document直接获取
			NodeList nodeList2 = document.getElementsByTagName("lan");
			for (int i = 0; i < nodeList.getLength(); i++) {
				// 获取单独的一个节点
				Node node = nodeList.item(i);
				// 获取该节点的名字
				String nodeName = node.getNodeName();
				// 获取该节点的属性集合
				NamedNodeMap attrs = node.getAttributes();
				// 根据属性名获得该节点的指定属性名的属性
				Node nodeAttr = attrs.getNamedItem("id");
				// 也可获取属性集合中指定下标的属性
				Node nodeattr2 = attrs.item(0);// 这里item中使用的是具体值，在实际应用中可使用变量
				// 获取属性名
				String attrName = nodeAttr.getNodeName();
				// 获取属性值
				String attrValue = nodeAttr.getNodeValue();
				// 获取节点的值。若该节点下还有节点，那么该节点的值则为空，须继续获取节点
				String nodeValue = node.getNodeValue();
				NodeList nodeList3 = node.getChildNodes();
				for(int j = 0; j < nodeList3.getLength(); j++) {
					Node node2 = nodeList3.item(j);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						String nodeName1 = node2.getNodeName();
						String nodeValue2 = node2.getTextContent();
//						String nodeValue2 = node2.getFirstChild().getNodeValue();
						System.out.println(nodeName1 + "：" + nodeValue2);
					}		
				}
				System.out.println(nodeName + "：" + attrName + "：" + attrValue + "：" + nodeValue);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public static void writeXMLByDom() {
		// 创建一个xml文件解析工厂
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			// 创建一个解析工厂基本接口
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			// 通过解析工厂接口创建document
			Document document = documentBuilder.newDocument();
			// 创建根节点
			Element rootElement = document.createElement("languagesTest");
			// 给根节点设置属性
			rootElement.setAttribute("category", "it");
			
			// 创建子节点
			Element lanElement = document.createElement("lan");
			// 为子节点设置属性
			lanElement.setAttribute("id", "1");
			// 创建子节点
			Element nameElement = document.createElement("name");
			// 为子节点设置值
			nameElement.setTextContent("java");
			Element ideElement = document.createElement("ide");
			ideElement.setTextContent("eclipse");
			
			// 将二级子节点放入一级子节点中
			lanElement.appendChild(nameElement);
			lanElement.appendChild(ideElement);
			
			// 将一级子节点放入根节点中
			rootElement.appendChild(lanElement);
			
			// 将根节点放入document中
			document.appendChild(rootElement);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			/* 
			 * 在将源xml输出时，有两个参数，第一个为源xml数据，固定不变的。
			 * 第二个参数，则可将源xml输出为字符串，也可将源xml输出为xml文件格式的文件，具体实现方式如下
			 * 通过StringWriter将源xml输出为字符串，通过File将源xml输出为文件
			 */
			// 输出为字符串格式
			StringWriter stringWriter = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
			System.out.println(stringWriter.toString());
			
			// 输出为文件格式
			transformer.transform(new DOMSource(document), new StreamResult(new File("languagesTest.xml")));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}	
	}
}
```



## 14.2、SAX 解析

SAX的全称是Simple APIs for XML，即简单应用程序接口。与DOM不同，SAX提供的访问模式是一种顺序模式，这是一种快速读写XML数据的方式。当使用SAX分析器对XML文档进行分析时，会触发一系列事件，并激活相应的事件处理函数，应用程序通过这些事件处理函数实现对XML文档的访问，因而SAX接口也被称作事件驱动接口。

优点：

- 采用事件驱动模式，对内存耗费比较小，解析速度快
- 适用于只处理XML文件中的数据时

缺点：

- 编码比较麻烦
- 很难同时访问XML文件中的多处不同数据
- 无法知道当前解析标签（节点）的上层标签，及其嵌套结构，仅仅知道当前解析的标签的名字和属性，要知道其他信息需要程序猿自己编码
- 只能读取XML，无法修改XML
- 无法随机访问某个标签（节点）

适用场合：

- 对于CPU资源宝贵的设备，如Android等移动设备
- 对于只需从xml读取信息而无需修改xml

SAX解析XML步骤：

1. 创建一个与XML对应的实体类（JavaBean）:

	```java
	public class Languages {
		private String id;
		private String name;
		private String ide;
		// getter和setter已省略
	}
	```

2. 新建一个类XmlParseHandler.java，该类需要继承`DefaultHandler`或者实现`ContentHandler`接口，这里通过继承`DefaultHandler`（实现了`ContentHandler`接口）的方式，该类是SAX解析的核心所在，生命周期如下：

	<img src="../Images/Java/20181101171449337.jpeg" alt="XmlParseHandler生命周期" style="zoom:80%;" />

	代码：

	```java
	package com.xiaobaizhiqian;
	 
	import java.util.ArrayList;
	 
	import org.xml.sax.Attributes;
	import org.xml.sax.SAXException;
	import org.xml.sax.helpers.DefaultHandler;
	 
	public class SAXParserHandler extends DefaultHandler {
		// 解析标签内容时存放数据，便于endElement使用
		private String value = null;
	    private Languages languages = null;
	    private ArrayList<Languages> languagesList = null;
	    
	    /** 直接调用便可获取从xml中读取的数据
	     * @return 返回值可自行设置（这里是返回的ArrayList<Languages>）
	     */
	    public ArrayList<Languages> getLanguages() {
	        return languagesList;
	    }
	    
	    /** 用来标识解析开始，只会执行一次
	     */
	    @Override
	    public void startDocument() throws SAXException {
	    	super.startDocument();
	    	System.out.println("解析开始");
	    	languagesList = new ArrayList<Languages>();
	    }
	    
	    /** 开始解析节点时调用
	     * @param uri xml文档的命名空间
	     * @param localName
	     * @param qName 标签的名字
	     * @param attributes 标签属性集
	     */
	    @Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	    	// 调用父类的方法
	    	super.startElement(uri, localName, qName, attributes);
	    	if (qName.equals("languages")) {
	           
	            //开始解析book元素的属性
	            System.out.println("======================开始遍历某一本书的内容=================");
	            //不知道book元素下属性的名称以及个数，如何获取属性名以及属性值
	            int num = attributes.getLength();
	            for(int i = 0; i < num; i++){
	                System.out.print("语言元素的第" + (i + 1) +  "个属性名是："
	                        + attributes.getQName(i));
	                System.out.println("---属性值是：" + attributes.getValue(i));
	            }
			}else if (!qName.equals("lan")) {
	            System.out.print("节点名是：" + qName + "---");
	        }else {
	        	 //创建一个book对象
	            languages = new Languages();
	        	int num = attributes.getLength();
	            for(int i = 0; i < num; i++){
	                System.out.print("语言元素的第" + (i + 1) +  "个属性名是："
	                        + attributes.getQName(i));
	                System.out.println("---属性值是：" + attributes.getValue(i));
	                if (attributes.getQName(i).equals("id")) {
	                    languages.setId(attributes.getValue(i));
	                }
	            }
			}
	    }
	    
	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	    	//调用DefaultHandler类的endElement方法
	        super.endElement(uri, localName, qName);
	        //判断是否针对一本书已经遍历结束
	        if (qName.equals("languages")) {
	            System.out.println(languagesList.size());
	            System.out.println("======================结束遍历某一本书的内容=================");
	        }else if(qName.equals("lan")){
	        	languagesList.add(languages);
	        }else if(qName.equals("name")){
	        	languages.setName(value);
	        }else if (qName.equals("ide")) {
	        	languages.setIde(value);
	        }
	    }
	    
	    /** 解析标签内容时调用
	     * @param ch 当前读取到的TextNode(文本节点)的字节数组
	     * @param start 字节开始的位置，为0则读取全部
	     * @param length 当前TextNode的长度
	     */
	    @Override
	    public void characters(char[] ch, int start, int length) throws SAXException {
	    	super.characters(ch, start, length);
	    	value = new String(ch, start, length);
	        if (!value.trim().equals("")) {
	            System.out.println("节点值是：" + value);
	        }
	    }
	    
	    /** 用来标识解析结束，只会执行一次
	     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	     */
	    @Override
	    public void endDocument() throws SAXException {
	    	super.endDocument();
	    	System.out.println("解析结束");
	    }
	}
	```

3. 使用XmlParseHandler通过getLanguages直接获得数据，代码：

	```java
	package com.xiaobaizhiqian;
	 
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.Date;
	 
	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.parsers.SAXParser;
	import javax.xml.parsers.SAXParserFactory;
	import javax.xml.transform.OutputKeys;
	import javax.xml.transform.Result;
	import javax.xml.transform.Transformer;
	import javax.xml.transform.TransformerConfigurationException;
	import javax.xml.transform.sax.SAXTransformerFactory;
	import javax.xml.transform.sax.TransformerHandler;
	import javax.xml.transform.stream.StreamResult;
	 
	import org.xml.sax.SAXException;
	 
	import com.sun.xml.internal.bind.util.AttributesImpl;
	 
	public class SAXTest {
		public static void main(String[] args) {
			Date string = new Date();
			long str1 = string.getTime();
			readXMLBySAX();
			Date string1 = new Date();
			long str2 = string1.getTime();
			long str = str2 - str1;
			System.out.println(str);
		}
	 
		public static void readXMLBySAX() {
			// 创建一个xml文件解析工厂
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			try {
				// 创建一个解析工厂基本接口
				SAXParser saxParser = saxParserFactory.newSAXParser();
				// 创建一个解析工厂管理
				SAXParserHandler saxParserHandler = new SAXParserHandler();
				// 通过SAXParserHandler的parse方法加载文件
				saxParser.parse("languages.xml", saxParserHandler);
				System.out.println("~！~！~！共有" + saxParserHandler.getLanguages().size() + "本书");
				for (Languages languages : saxParserHandler.getLanguages()) {
					System.out.println(languages.getId());
					System.out.println(languages.getName());
					System.out.println(languages.getIde());
					System.out.println("----finish----");
				}
	 
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 
		public static void writeXMLBySAX() {
			SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
			try {
				TransformerHandler transformerHandler = saxTransformerFactory.newTransformerHandler();
				Transformer transformer = transformerHandler.getTransformer();
				// 设置生成的XML文件格式
				transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
				// 设置生成的 XML 文件自动换行
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				// 如果不存在，就创建一个新的 XML 文件
				File file = new File("languagesTest.xml");
				if (!file.exists()) {
					file.createNewFile();
				}
				// 创建一个Result 对象,并且使其与 TransformerHandler 对象关联
				Result result = null;
				result = new StreamResult(new FileOutputStream(file));
				transformerHandler.setResult(result);
				
				//利用 handler 对象进行 XML 文件内容的编写
	             //打开 document
				transformerHandler.startDocument();
	             //为了创建节点属性和属性值
	             AttributesImpl atts = new AttributesImpl();
	             //设置属性和属性值
	             atts.addAttribute("", "", "cat", "", "it");
	             //根节点开始标签
	             transformerHandler.startElement("", "", "languages", atts);
	             atts.clear();
	             atts.addAttribute("", "", "id", "", "1");
	             //子节点开始标签
	             transformerHandler.startElement("", "", "lan", atts);
	            
	             atts.clear();  //清空子节点设的值
	            
	             //子节点下name节点开始标签
	             transformerHandler.startElement("", "", "name", atts);
	             String name="java";
	             transformerHandler.characters(name.toCharArray(), 0, name.length());
	             //字节点下name节点结束标签
	             transformerHandler.endElement("", "", "name");
	             //子节点结束标签
	             transformerHandler.endElement("", "", "lan");
	            
	             //根节点结束标签
	             transformerHandler.endElement("", "", "languages");
	            
	             //关闭 document
	             transformerHandler.endDocument();
	 
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
	}
	```



## 14.3、JDOM 解析

特征：

- 可直接通过节点名获取节点
- 仅使用具体类，而不使用接口
- API大量使用了Collections类
- Jdom专用于java技术，比Dom应用占用更少内存，但内存消耗仍然较大
- Jdom提供更加简单和逻辑性访问xml信息的基础方法

JDOM解析XML步骤

1. 导入jdom.jar包。jar包下载地址http://www.jdom.org/downloads/
2. 创建一个SAXBuilder的对象
3. 创建一个输入流，将xml文件加载到输入流中
4. 通过saxBuilder的build方法，将输入流加载到saxBuilder中
5. 获取节点信息

下面是完整代码

```java
package com.xiaobaizhiqian;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
 
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
 
public class JDomTest {
	public static void main(String[] args) {
		Date string = new Date();
		long str1 = string.getTime();
		readXMLByJdom();
		Date string1 = new Date();
		long str2 = string1.getTime();
		long str = str2 - str1;
		System.out.println(str);
	}
 
	public static void readXMLByJdom() {
		// 1.创建一个SAXBuilder的对象
		SAXBuilder saxBuilder = new SAXBuilder();
		InputStream inputStream;
		try {
			// 2.创建一个输入流，将xml文件加载到输入流中
			inputStream = new FileInputStream("languages.xml");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			// 3.通过saxBuilder的build方法，将输入流加载到saxBuilder中
			Document document = saxBuilder.build(inputStreamReader);
			// 获取根节点
			Element rootElement = document.getRootElement();
			List<Element> elements = rootElement.getChildren();
			for (Element element : elements) {
				// 获取节点的属性
				List<Attribute> attributes = element.getAttributes();
				// 获取指定名称的节点
				element.getChild("");
				for (Attribute attribute : attributes) {
					// 获取属性名
					String attrName = attribute.getName();
					// 获取属性值
					String attrValue = attribute.getValue();
					System.out.println(element.getName() + "：" + attrName + "：" + attrValue);
				}
				// 获取子节点
				List<Element> elementsChilds = element.getChildren();
				for (Element elementChild : elementsChilds) {
					String elementName = elementChild.getName();
					String elementValue = elementChild.getValue();
					System.out.println(elementName + "：" + elementValue);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public static void writeXMLByJdom() {
		// 创建根节点
		Element rootElement = new Element("languages");
		rootElement.setAttribute("cat", "it");
		// 将根节点放到document
		Document document = new Document(rootElement);
		Element element = new Element("lan");
		element.setAttribute("id", "1");
		Element nameElement = new Element("name");
		nameElement.addContent("java");
		rootElement.addContent(element);
		element.addContent(nameElement);
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getCompactFormat().setEncoding("UTF-8").setIndent("	"));
		// 设置xml文件编码格式可封装成方法进行
//		XMLOutputter xmlOutputter1 = new XMLOutputter(getFormat());
		try {
			xmlOutputter.output(document, new FileOutputStream("languagesTest.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Format getFormat() {
		Format format = Format.getCompactFormat();
		format.setEncoding("UTF-8");
		format.setIndent("	");
		return format;
	}
}
```



## 14.4、DOM4J 解析

特征：

- JDOM的一种智能分支，它合并了许多超出基本XML文档表示的功能。
- 它使用接口和抽象基本类方法。
- 具有性能优异、灵活性好、功能强大和极端易用的特点。
- 是一个开放源码的文件
- 可移植性差
- 不可通过节点名获取节点

以下是完整测试代码

```java
package com.xiaobaizhiqian;
 
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
 
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
 
 
public class Dom4jTest {
 
	public static void main(String[] args) {
		Date string = new Date();
		long str1 = string.getTime();
		readeXMLByDom4j();
		Date string1 = new Date();
		long str2 = string1.getTime();
		long str = str2 - str1;
		System.out.println(str);
	}
 
	public static void readeXMLByDom4j() {
		SAXReader saxReader = new SAXReader();
		try {
			// 通过reader对象的read方法加载xml文件,获取docuemnt对象。
			Document document = saxReader.read("languages.xml");
			// 通过document对象获取根节点bookstore
			Element rootElement = document.getRootElement();
			// 通过根节点获取迭代器
			Iterator<Element> elementIterator = rootElement.elementIterator();
			// 遍历迭代器，获取根节点中的信息
			while (elementIterator.hasNext()) {
				Element element = elementIterator.next();
				List<Attribute> attributes = element.attributes();
				for (Attribute attribute : attributes) {
					String attrName = attribute.getName();
					String attrValue = attribute.getValue();
					System.out.println(element.getName() + "：" + attrName + "：" + attrValue);
				}
				String elementName = element.getName();
				String elementValue = element.getStringValue();
				Iterator<Element> iterator = element.elementIterator();
				while (iterator.hasNext()) {
					Element element1 = iterator.next();
					String elementName1 = element1.getName();
					String elementValue1 = element1.getStringValue();
					System.out.println(elementName1 +"："+ elementValue1);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
 
	public static void writeXMLByDom4j() {
		// 创建一个xml文档
		Document doc = DocumentHelper.createDocument();
		// 向xml文件中添加注释
		doc.addComment("这里是注释");
		// 创建languages子节点
		Element root = doc.addElement("languages");
		// 在root节点下创建一个名为student的节点
		Element stuEle = root.addElement("student");
		// 给student节点添加属性
		stuEle.addAttribute("id", "101");
		// 给student节点添加一个子节点
		Element nameEle = stuEle.addElement("name");
		// 设置子节点的文本
		nameEle.setText("张三");
		// 用于格式化xml内容和设置头部标签
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置xml文档的编码为utf-8
		format.setEncoding("utf-8");
		Writer out;
		try {
			// 创建一个输出流对象
			out = new FileWriter("languagesTest.xml");
			// 创建一个dom4j创建xml的对象
			XMLWriter writer = new XMLWriter(out, format);
			// 调用write方法将doc文档写到指定路径
			writer.write(doc);
			writer.close();
			System.out.print("生成XML文件成功");
		} catch (IOException e) {
			System.out.print("生成XML文件失败");
			e.printStackTrace();
		}
	}
}
```



# 15、注解（Annotation）

## 15.1、注解简介

Java 注解用于为 Java 代码提供元数据。作为元数据，注解不直接影响你的代码执行，但也有一些类型的注解实际上可以用于这一目的。Java 注解是从 Java5 开始添加到 Java 的。

其实同 classs 和 interface 一样，注解也属于一种类型。

---

**注解的定义**

注解通过 `@interface`关键字进行定义。

```java
public @interface TestAnnotation {
}
```

它的形式跟接口很类似，不过前面多了一个 @ 符号。上面的代码就创建了一个名字为 TestAnnotaion 的注解。

---

**注解的应用**

上面创建了一个注解，那么注解的的使用方法是什么呢。

```java
@TestAnnotation
public class Test {
}
```

创建一个类 Test,然后在类定义的地方加上 @TestAnnotation 就可以用 TestAnnotation 注解这个类了。

不过，要想注解能够正常工作，还需要介绍一下一个新的概念那就是元注解。



## 15.2、元注解

元注解是可以注解到注解上的注解，或者说元注解是一种基本注解，但是它能够应用到其它的注解上面。

如果难于理解的话，可以这样理解。元注解也是一张标签，但是它是一张特殊的标签，它的作用和目的就是给其他普通的标签进行解释说明的。

元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种。

---

**@Retention**

Retention 的英文意为保留期的意思。当 @Retention 应用到一个注解上的时候，它解释说明了这个注解的的存活时间。

它的取值如下：

- RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
- RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
- RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。

加深理解：@Retention 去给一张标签解释的时候，它指定了这张标签张贴的时间。@Retention 相当于给一张标签上面盖了一张时间戳，时间戳指明了标签张贴的时间周期。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
}
```

上面的代码中，指定 TestAnnotation 可以在程序运行周期被获取到，因此它的生命周期非常的长。

---

**@Documented**

顾名思义，这个元注解肯定是和文档有关。它的作用是能够将注解中的元素包含到 Javadoc 中去。

---

**@Target**

Target 是目标的意思，@Target 指定了注解运用的地方；当一个注解被 @Target 注解时，这个注解就被限定了运用的场景。

类比到标签，原本标签是你想张贴到哪个地方就到哪个地方，但是因为 @Target 的存在，它张贴的地方就非常具体了，比如只能张贴到方法上、类上、方法参数上等等。@Target 有下面的取值：

- ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
- ElementType.CONSTRUCTOR 可以给构造方法进行注解
- ElementType.FIELD 可以给属性进行注解
- ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
- ElementType.METHOD 可以给方法进行注解
- ElementType.PACKAGE 可以给一个包进行注解
- ElementType.PARAMETER 可以给一个方法内的参数进行注解
- ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举

---

**@Inherited**

Inherited 是继承的意思，但是它并不是说注解本身可以继承，而是说如果一个超类被 @Inherited 注解过的注解进行注解的话，那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。

```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Test {}

@Test
public class A {}

public class B extends A {}
```

注解 Test 被 @Inherited 修饰，之后类 A 被 Test 注解，类 B 继承 A,类 B 也拥有 Test 这个注解。

老子非常有钱，所以人们给他贴了一张标签叫做富豪。老子的儿子长大后，只要没有和老子断绝父子关系，虽然别人没有给他贴标签，但是他自然也是富豪。老子的孙子长大了，自然也是富豪。

---

**@Repeatable**

Repeatable 自然是可重复的意思。@Repeatable 是 Java 1.8 才加进来的，所以算是一个新的特性。

什么样的注解会多次应用呢？通常是注解的值可以同时取多个。

举个例子，一个人他既是程序员又是产品经理,同时他还是个画家。

```java
@interface Persons {
	Person[]  value();
}

@Repeatable(Persons.class)
@interface Person{
	String role() default "";
}

@Person(role="artist")
@Person(role="coder")
@Person(role="PM")
public class SuperMan{
}
```

上面的代码，@Repeatable 注解了 Person。而 @Repeatable 后面括号中的类相当于一个容器注解。

什么是容器注解呢？就是用来存放其它注解的地方。它本身也是一个注解。按照规定，它里面必须要有一个 value 的属性，属性类型是一个被 @Repeatable 注解过的注解数组，注意它是数组。



## 15.3、注解的属性

注解的属性也叫做成员变量。注解只有成员变量，没有方法。注解的成员变量在注解的定义中以“无形参的方法”形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
	int id();
	String msg();
}
```

上面代码定义了 TestAnnotation 这个注解中拥有 id 和 msg 两个属性。在使用的时候，应该给它们进行赋值。

赋值的方式是在注解的括号内以 `value=""` 形式，多个属性之间用 `,`隔开。

```java
@TestAnnotation(id=3,msg="hello annotation")
public class Test {
}
```

注解中属性可以有默认值，默认值需要用 default 关键值指定。比如：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
	public int id() default -1;
	public String msg() default "Hi";
}
```

TestAnnotation 中 id 属性默认值为 -1，msg 属性默认值为 Hi。

它可以这样应用。

```java
@TestAnnotation()
public class Test {}
```

因为有默认值，所以无需要再在 @TestAnnotation 后面的括号里面进行赋值了，这一步可以省略。

另外，还有一种情况。如果一个注解内仅仅只有一个名字为 value 的属性时，应用这个注解时可以直接接属性值填写到括号内。

```java
public @interface Check {
	String value();
}
```

上面代码中，Check 这个注解只有 value 这个属性。所以可以这样应用。

```java
@Check("hi")
int a;
```

这和下面的效果是一样的

```java
@Check(value="hi")
int a;
```

最后，还需要注意的一种情况是一个注解没有任何属性。比如

```java
public @interface Perform {}
```

那么在应用这个注解的时候，括号都可以省略。

```java
@Perform
public void testMethod(){}
```



## 15.4、Java 预置的注解

**@Deprecated**

这个元素是用来标记过时的元素。编译器在编译阶段遇到这个注解时会发出提醒警告，告诉开发者正在调用一个过时的元素比如过时的方法、过时的类、过时的成员变量。

```java
public class Hero {
	@Deprecated
	public void say(){
		System.out.println("Noting has to say!");
	}
	
	public void speak(){
		System.out.println("I have a dream!");
	}
}
```

定义了一个 Hero 类，它有两个方法 say() 和 speak() ，其中 say() 被 @Deprecated 注解。然后我们在 IDE 中分别调用它们。

<img src="JavaWeb.assets/aHR0cDovL2ltZy5ibG9nLmNzZG4ubmV0LzIwMTcwNjI3MjE0MDA4NTg4" alt="这里写图片描述" style="zoom:80%;float:left" />

可以看到，say() 方法上面被一条直线划了一条，这其实就是编译器识别后的提醒效果。

---

**@Override**

提示子类要复写父类中被 @Override 修饰的方法

---

**@SuppressWarnings**

阻止警告的意思。之前说过调用被 @Deprecated 注解的方法后，编译器会警告提醒，而有时候开发者会忽略这种警告，他们可以在调用的地方通过 @SuppressWarnings 达到目的。

```java
@SuppressWarnings("deprecation")
public void test1(){
	Hero hero = new Hero();
	hero.say();
	hero.speak();
}
```

---

**@FunctionalInterface**

函数式接口注解，这个是 Java 1.8 版本引入的新特性。函数式编程很火，所以 Java 8 也及时添加了这个特性。



## 15.5、注解的提取

注解通过反射获取。首先可以通过 Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解。

```java
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {}
```

然后通过 getAnnotation() 方法来获取 Annotation 对象。

```java
public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {}
```

或者是 getAnnotations() 方法。

```java
public Annotation[] getAnnotations() {}
```

前一种方法返回指定类型的注解，后一种方法返回注解到这个元素上的所有注解。如果获取到的 Annotation 如果不为 null，则就可以调用它们的属性方法了。比如:

```java
@TestAnnotation()
public class Test {	
	public static void main(String[] args) {		
		boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
		
		if ( hasAnnotation ) {
			TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
			
			System.out.println("id:"+testAnnotation.id());
			System.out.println("msg:"+testAnnotation.msg());
		}
	}
}
```

程序的运行结果是：

```
id:-1
msg:
```

这个正是 TestAnnotation 中 id 和 msg 的默认值。

上面的例子中，只是检阅出了注解在类上的注解，其实属性、方法上的注解照样是可以的。同样还是要假手于反射。

```java
@TestAnnotation(msg="hello")
public class Test {	
	@Check(value="hi")
	int a;
	
	@Perform
	public void testMethod(){}
	
	@SuppressWarnings("deprecation")
	public void test1(){
		Hero hero = new Hero();
		hero.say();
		hero.speak();
	}

	public static void main(String[] args) {
		boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
		
		if ( hasAnnotation ) {
			TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
			//获取类的注解
			System.out.println("id:"+testAnnotation.id());
			System.out.println("msg:"+testAnnotation.msg());
		}
		
		try {
			Field a = Test.class.getDeclaredField("a");
			a.setAccessible(true);
			//获取一个成员变量上的注解
			Check check = a.getAnnotation(Check.class);
			
			if ( check != null ) {
				System.out.println("check value:"+check.value());
			}
			
			Method testMethod = Test.class.getDeclaredMethod("testMethod");
			
			if ( testMethod != null ) {
				// 获取方法中的注解
				Annotation[] ans = testMethod.getAnnotations();
				for( int i = 0;i < ans.length;i++) {
					System.out.println("method testMethod annotation:"+ans[i].annotationType().getSimpleName());
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (SecurityException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
```

它们的结果如下：

```
id:-1
msg:hello
check value:hi
method testMethod annotation:Perform
```

需要注意的是，如果一个注解要在运行时被成功提取，那么 @Retention(RetentionPolicy.RUNTIME) 是必须的。



## 15.6、自定义注解

写一个测试框架，测试程序员的代码有无明显的异常。

—— 程序员 A : 我写了一个类，它的名字叫做 NoBug，因为它所有的方法都没有错误。
—— 我：自信是好事，不过为了防止意外，让我测试一下如何？
—— 程序员 A: 怎么测试？
—— 我：把你写的代码的方法都加上 @Jiecha 这个注解就好了。
—— 程序员 A: 好的。

```java
public class NoBug {
	@Jiecha
	public void suanShu(){
		System.out.println("1234567890");
	}
    
	@Jiecha
	public void jiafa(){
		System.out.println("1+1="+1+1);
	}
    
	@Jiecha
	public void jiefa(){
		System.out.println("1-1="+(1-1));
	}
    
	@Jiecha
	public void chengfa(){
		System.out.println("3 x 5="+ 3*5);
	}
    
	@Jiecha
	public void chufa(){
		System.out.println("6 / 0="+ 6 / 0);
	}
	
	public void ziwojieshao(){
		System.out.println("我写的程序没有 bug!");
	}
}
```

上面的代码，有些方法上面运用了 @Jiecha 注解。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface Jiecha {
}
```

编写一个测试类 TestTool 就可以测试 NoBug 相应的方法:

```java
public class TestTool {
	public static void main(String[] args) {	
		NoBug testobj = new NoBug();
		
		Class clazz = testobj.getClass();
		
		Method[] method = clazz.getDeclaredMethods();
		//用来记录测试产生的 log 信息
		StringBuilder log = new StringBuilder();
		// 记录异常的次数
		int errornum = 0;
		
		for ( Method m: method ) {
			// 只有被 @Jiecha 标注过的方法才进行测试
			if ( m.isAnnotationPresent( Jiecha.class )) {
				try {
					m.setAccessible(true);
					m.invoke(testobj, null);
				} catch (Exception e) {
					errornum++;
					log.append(m.getName());
					log.append(" ");
					log.append("has error:");
					log.append("\n\r  caused by ");
					//记录测试过程中，发生的异常的名称
					log.append(e.getCause().getClass().getSimpleName());
					log.append("\n\r");
					//记录测试过程中，发生的异常的具体信息
					log.append(e.getCause().getMessage());
					log.append("\n\r");
				} 
			}
		}	
		
		log.append(clazz.getSimpleName());
		log.append(" has  ");
		log.append(errornum);
		log.append(" error.");
		
		// 生成测试报告
		System.out.println(log.toString());
	}
}
```

测试结果为：

```
1234567890
1+1=11
1-1=0
3 x 5=15
chufa has error:

  caused by ArithmeticException

/ by zero

NoBug has  1 error.
```

提示 NoBug 类中的 chufa() 这个方法有异常，这个异常名称叫做 ArithmeticException，原因是运算过程中进行了除 0 的操作。

