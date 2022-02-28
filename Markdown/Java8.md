# 1、Effectively final

Java 中局部内部类和匿名内部类访问的局部变量必须由 `final` 修饰，以保证内部类和外部类的数据一致性。但从 Java 8 开始，我们可以不加 `final` 修饰符，由系统默认添加，当然这在 Java 8 以前的版本是不允许的。Java 将这个功能称为 Effectively final 功能。

编写同样的代码，分别在 Java 7 和 Java 8 下运行，代码如下：

```java
public class Test {
    public static void main(String[] args) {
        String name = "C语言中文网";
        new Runnable() {
            @Override
            public void run() {
                System.out.println(name);
            }
        }
    }
}
```

下图是 Java 7 的编译结果

<img src="../Images/Java8/5-191119163Sb30.png" alt="Java 7 运行结果" style="zoom:80%;" />

可以看到在 Java 7 中出现代码错误，提示我们必须显式的声明这个变量为 `final` 的（`run()` 方法中代码为输出 *name* 语句，即`System.out.println(name);`）。

<img src="../Images/Java8/5-191119164126217.png" alt="img" style="zoom:80%;" />

<img src="../Images/Java8/5-191119164142109.png" alt="img" style="zoom:80%;" />

因为系统会默认添加 `final` 修饰符，所以在图 2 和图 3 中可以在匿名内部类中直接使用非 `final` 变量，而 `final` 修饰的局部变量不能在被重新赋值，所以图 3 中出现编译错误。也就是说从 Java 8 开始，它不要求程序员必须将访问的局部变量显式的声明为 `final` 的。只要该变量不被重新赋值就可以。

一个非 `final` 的局部变量或方法参数，其值在初始化后就从未更改，那么该变量就是 effectively final。在 Lambda 表达式中，使用局部变量的时候，也要求该变量必须是 `final` 的，所以 effectively final 在 Lambda 表达式上下文中非常有用。

Lambda 表达式在编程中是经常使用的，而匿名内部类是很少使用的。那么，我们在 Lambda 编程中每一个被使用到的局部变量都去显示定义成 `final` 吗？显然这不是一个好方法。所以，Java 8 引入了 effectively final 新概念。

总结一下，规则没有改变，Lambda 表达式和匿名内部类访问的局部变量必须是 `final` 的，只是不需要程序员显式的声明变量为 `final` 的，从而节省时间。

<br>

# 2、Lambda 表达式

Lambda 表达式（Lambda expression）是一个匿名函数，基于数学中的 λ 演算得名，也可称为闭包（Closure）。现在很多语言都支持 Lambda 表达式，如 C++、C#、Java、 Python 和 JavaScript 等。

Lambda 表达式是推动 Java 8 发布的重要新特性，它允许把函数作为一个方法的参数（函数作为参数传递进方法中），下面通过例子来理解 Lambda 表达式的概念。

先定义一个计算数值的接口，代码如下：

```java
// 可计算接口
public interface Calculable {
    // 计算两个int数值
    int calculateInt(int a, int b);
}
```

Calculable 接口只有一个方法 `calculateInt()`，参数是两个 `int` 类型，返回值也是 `int` 类型。实现方法代码如下：

```java
public class Test{
    
    /**
     * 通过操作符，进行计算
     *
     * @param opr 操作符
     * @return 实现Calculable接口对象
     */
    public static Calculable calculate(char opr) {
        Calculable result;
        if (opr == '+') {
            // 匿名内部类实现Calcu	lable接口
            result = new Calculable() {
                // 实现加法运算
                @Override
                public int calculateInt(int a, int b) {
                    return a + b;
                }
            };
        } else {
            // 匿名内部类实现Calculable接口
            result = new Calculable() {
                // 实现减法运算
                @Override
                public int calculateInt(int a, int b) {
                    return a - b;
                }
            };
        }
        return result;
    }
}
```

方法 `calculate()` 中 *opr* 参数是运算符，返回值是实现 Calculable 接口对象。代码第 13 行和第 23 行都采用匿名内部类实现 Calculable 接口。代码第 16 行实现加法运算。代码第 26 行实现减法运算。

```java
public static void main(String[] args) {
    int n1 = 10;
    int n2 = 5;
    // 实现加法计算Calculable对象
    Calculable f1 = calculate('+');
    // 实现减法计算Calculable对象
    Calculable f2 = calculate('-');
    // 调用calculateInt方法进行加法计算
    System.out.println(n1 + "+" + n2 + "=" + f1.calculateInt(n1, n2));
    // System.out.printf("%d + %d = %d \n", n1, n2, f1.calculateInt(n1, n2));
    // 调用calculateInt方法进行减法计算
    System.out.println(n1 + "-" + n2 + "=" + f1.calculateInt(n1, n2));
    // System.out.printf("%d - %d = %d \n", n1, n2, f2.calculateInt(n1, n2));
}
```

代码第 5 行中 *f1* 是实现加法计算 Calculable 对象，代码第 7 行中 *f2* 是实现减法计算 Calculable 对象。代码第 9 行和第 12 行才进行方法调用。

上述代码中列出了两种输出方式，下面简单介绍一下 Java 中常见的输出函数：

1. `printf()` 主要继承了 C 语言中 `printf()` 的一些特性，可以进行格式化输出。
2. `print()` 就是一般的标准输出，但是不换行。
3. `println()` 和 `print()` 基本没什么差别，就是最后会换行。

输出结果如下：

```
10+5=15
10-5=5
```

使用匿名内部类的方法 `calculate()` 代码很臃肿，Java 8 采用 Lambda 表达式可以替代匿名内部类。修改之后的通用方法 `calculate()` 代码如下：

```java
/**
* 通过操作符，进行计算
* @param opr 操作符
* @return 实现Calculable接口对象
*/
public static Calculable calculate(char opr) {
    Calculable result;
    if (opr == '+') {
        // Lambda表达式实现Calculable接口
        result = (int a, int b) -> {
            return a + b;
        };
    } else {
        // Lambda表达式实现Calculable接口
        result = (int a, int b) -> {
            return a - b;
        };
    }
    return result;
}
```

代码第 10 行和第 15 行用 Lambda 表达式替代匿名内部类，可见代码变得简洁。通过以上示例我们发现，Lambda 表达式是一个匿名函数（方法）代码块，可以作为表达式、方法参数和方法返回值。

Lambda 表达式标准语法形式如下：

```java
(参数列表) -> {
    // Lambda表达式体
}
```

`->` 被称为箭头操作符或 Lambda 操作符，箭头操作符将 Lambda 表达式拆分成两部分：

- 左侧：Lambda 表达式的参数列表。
- 右侧：Lambda 表达式中所需执行的功能，用 `{}` 包起来，即 Lambda 体。

<br>

**Java Lambda 表达式的优缺点**

优点：

1. 代码简洁，开发迅速
2. 方便函数式编程
3. 非常容易进行并行计算
4. Java 引入 Lambda，改善了集合操作（引入 Stream API）

缺点：

1. 代码可读性变差
2. 在非并行计算中，很多计算未必有传统的 `for` 性能要高
3. 不容易进行调试

<br>

## 2.1、函数式接口

Lambda 表达式实现的接口不是普通的接口，而是**函数式接口**。如果一个接口中，有且只有一个抽象的方法（Object 类中的方法不包括在内），那这个接口就可以被看做是函数式接口。这种接口只能有一个方法。如果接口中声明多个抽象方法，那么 Lambda 表达式会发生编译错误：

```
The target type of this expression must be a functional interface
//此表达式的目标类型必须是函数接口
```

这说明该接口不是函数式接口，为了防止在函数式接口中声明多个抽象方法，Java 8 提供了一个声明函数式接口注解 `@FunctionalInterface`，示例代码如下：

```java
// 可计算接口
@FunctionalInterface
public interface Calculable {
    // 计算两个int数值
    int calculateInt(int a, int b);
}
```

在接口之前使用 `@FunctionalInterface` 注解修饰，那么试图增加一个抽象方法时会发生编译错误。但可以添加默认方法和静态方法。

`@FunctionalInterface` 注解与 `@Override` 注解的作用类似。Java 8 中专门为函数式接口引入了一个新的注解 `@FunctionalInterface`。该注解可用于一个接口的定义上，一旦使用该注解来定义接口，编译器将会强制检查该接口是否确实有且仅有一个抽象方法，否则将会报错。需要注意的是，即使不使用该注解，只要满足函数式接口的定义，这仍然是一个函数式接口，使用起来都一样。

> 提示：Lambda 表达式是一个匿名方法代码，Java 中的方法必须声明在类或接口中，那么 Lambda 表达式所实现的匿名方法是在函数式接口中声明的。

<br>

## 2.2、Lambda 的简写方式

使用 Lambda 表达式是为了简化程序代码，Lambda 表达式本身也提供了多种简化形式，这些简化形式虽然简化了代码，但客观上使得代码可读性变差。

<br>

**省略参数类型**

Lambda 表达式可以根据上下文环境推断出参数类型。`calculate()` 方法中 Lambda 表达式能推断出参数 *a* 和 *b* 是 `int` 类型，简化形式如下：

```java
public static Calculable calculate(char opr) {
    Calculable result;
    if (opr == '+') {
        // Lambda表达式实现Calculable接口
        result = (a, b) -> {
            return a + b;
        };
    } else {
        // Lambda表达式实现Calculable接口
        result = (a, b) -> {
            return a - b;
        };
    }
    return result;
}
```

<br>

**省略参数小括号**

如果 Lambda 表达式中的参数只有一个，可以省略参数小括号。修改 Calculable 接口中的 `calculateInt()` 方法，代码如下。

```java
// 可计算接口
@FunctionalInterface
public interface Calculable {
    // 计算一个int数值
    int calculateInt(int a);
}
```

其中 `calculateInt()` 方法只有一个 `int` 类型参数，返回值也是 `int` 类型。调用 `calculateInt()` 方法代码如下：

```java
public static void main(String[] args) {
    int n1 = 10;
    // 实现二次方计算Calculable对象
    Calculable f1 = calculate(2);
    // 实现三次方计算Calculable对象
    Calculable f2 = calculate(3);
    // 调用calculateInt方法进行加法计算
    System.out.printf("%d二次方 = %d \n", n1, f1.calculateInt(n1));
    // 调用calculateInt方法进行减法计算
    System.out.printf("%d三次方 = %d \n", n1, f2.calculateInt(n1));
}
/**
* 通过幂计算
*
* @param power 幂
* @return 实现Calculable接口对象
*/
public static Calculable calculate(int power) {
    Calculable result;
    if (power == 2) {
        // Lambda表达式实现Calculable接口
        // 标准形式
        result = (int a) -> {
            return a * a;
        };
    } else {
        // Lambda表达式实现Calculable接口
        // 省略形式
        result = a -> {
            return a * a * a;
        };
    }
    return result;
}
```

输出结果为：

```
10二次方 = 100
10三次方 = 1000 
```

<br>

**省略 return 和大括号**

如果 Lambda 表达式体中只有一条语句，那么可以省略 `return` 和大括号，代码如下：

```java
public static Calculable calculate(int power) {
    Calculable result;
    if (power == 2) {
        // Lambda表达式实现Calculable接口
        // 标准形式
        result = (int a) -> {
            return a * a;
        };
    } else {
        // Lambda表达式实现Calculable接口
        // 省略形式
        result = a -> a * a * a;
    }
    return result;
}
```

<br>

## 2.3、Lambda 的使用

**作为参数使用 Lambda 表达式**

Lambda 表达式一种常见的用途就是作为参数传递给方法，这需要声明参数的类型声明为函数式接口类型。示例代码如下：

```java
public static void main(String[] args) {
    int n1 = 10;
    int n2 = 5;
    // 打印加法计算结果
    display((a, b) -> {
        return a + b;
    }, n1, n2);
    // 打印减法计算结果
    display((a, b) -> a - b, n1, n2);
}

/**
* 打印计算结果
*
* @param calc Lambda表达式
* @param n1   操作数1
* @param n2   操作数2
*/
public static void display(Calculable calc, int n1, int n2) {
    System.out.println(calc.calculateInt(n1, n2));
}
```

上述代码第 19 行定义 `display()` 打印计算结果方法，其中参数 *calc* 类型是 Calculable，这个参数即可以接收实现 Calculable 接口的对象，也可以接收 Lambda 表达式，因为 Calculable 是函数式接口。 代码第 7 行和第 9 行两次调用 `display()` 方法，它们第一个参数都是 Lambda 表达式。

<br>

**访问变量**

Lambda 表达式可以访问所在外层作用域定义的变量，包括成员变量和局部变量。

成员变量包括实例成员变量和静态成员变量。在 Lambda 表达式中可以访问这些成员变量，此时的 Lambda 表达式与普通方法一样，可以读取成员变量，也可以修改成员变量。

```java
public class LambdaDemo {
    // 实例成员变量
    private int value = 10;
    // 静态成员变量
    private static int staticValue = 5;
    
    // 静态方法，进行加法运算
    public static Calculable add() {
        Calculable result = (int a, int b) -> {
            // 访问静态成员变量，不能访问实例成员变量
            staticValue++;
            int c = a + b + staticValue;
            // this.value;
            return c;
        };
        return result;
    }
    
    // 实例方法，进行减法运算
    public Calculable sub() {
        Calculable result = (int a, int b) -> {
            // 访问静态成员变量和实例成员变量
            staticValue++;
            this.value++;
            int c = a - b - staticValue - this.value;
            return c;
        };
        return result;
    }
}
```

LambdaDemo 类中声明一个实例成员变量 *value* 和一个静态成员变量 *staticValue*。此外，还声明了静态方法 `add()`（见代码第 8 行）和实例方法 `sub()`（见代码第 20 行）。`add()` 方法是静态方法，静态方法中不能访问实例成员变量，所以代码第 13 行的 Lambda 表达式中也不能访问实例成员变量，也不能访问实例成员方法。

`sub()` 方法是实例方法，实例方法中能够访问静态成员变量和实例成员变量，所以代码第 23 行的 Lambda 表达式中可以访问这些变量，当然实例方法和静态方法也可以访问。当访问实例成员变量或实例方法时可以使用 `this`，如果不与局部变量发生冲突情况下可以省略 `this`。

对于成员变量的访问 Lambda 表达式与普通方法没有区别，但是访问局部变量时，变量必须是 `final` 类型的（不可改变）。

```java
public class LambdaDemo {
    // 实例成员变量
    private int value = 10;
    // 静态成员变量
    private static int staticValue = 5;
    
    // 静态方法，进行加法运算
    public static Calculable add() {
        // 局部变量
        int localValue = 20;
        Calculable result = (int a, int b) -> {
            // localValue++;
            // 编译错误
            int c = a + b + localValue;
            return c;
        };
        return result;
    }
    
    // 实例方法，进行减法运算
    public Calculable sub() {
        // final局部变量
        final int localValue = 20;
        Calculable result = (int a, int b) -> {
            int c = a - b - staticValue - this.value;
            // localValue = c;
            // 编译错误
            return c;
        };
        return result;
    }
}
```

上述代码第 10 行和第 23 行都声明一个局部变量 *localValue*，Lambda 表达式中访问这个变量，如代码第 14 行和第 25 行。不管这个变量是否显式地使用 `final` 修饰，它都不能在 Lambda 表达式中修改变量，所以代码第 12 行和第 26 行如果去掉注释会发生编译错误。

Lambda 表达式只能访问局部变量而不能修改，否则会发生编译错误，但对静态变量和成员变量可读可写。

<br>

**方法引用**

方法引用可以理解为 Lambda 表达式的快捷写法，它比 Lambda 表达式更加的简洁，可读性更高，有很好的重用性。如果实现比较简单，复用的地方又不多，推荐使用 Lambda 表达式，否则应该使用方法引用。

Java 8 之后增加了双冒号 `::` 运算符，该运算符用于 “方法引用”，注意不是调用方法。“方法引用” 虽然没有直接使用 Lambda 表达式，但也与 Lambda 表达式有关，与函数式接口有关。 方法引用的语法格式如下：

```java
ObjectRef::methodName 
```

其中，*ObjectRef* 是类名或者实例名，*methodName* 是相应的方法名。

> 注意：被引用方法的参数列表和返回值类型，必须与函数式接口方法参数列表和方法返回值类型一致，示例代码如下。

```java
public class LambdaDemo {
    // 静态方法，进行加法运算
    // 参数列表要与函数式接口方法calculateInt(int a, int b)兼容
    public static int add(int a, int b) {
        return a + b;
    }
    
    // 实例方法，进行减法运算
    // 参数列表要与函数式接口方法calculateInt(int a, int b)兼容
    public int sub(int a, int b) {
        return a - b;
    }
}
```

LambdaDemo 类中提供了一个静态方法 `add()`，一个实例方法 `sub()`。这两个方法必须与函数式接口方法参数列表一致，方法返回值类型也要保持一致。

```java
public class HelloWorld {
    public static void main(String[] args) {
        int n1 = 10;
        int n2 = 5;
        // 打印加法计算结果
        display(LambdaDemo::add, n1, n2);
        LambdaDemo d = new LambdaDemo();
        // 打印减法计算结果 
        display(d::sub, n1, n2);
    }
    
    /**
     * 打印计算结果
     *
     * @param calc Lambda表达式
     * @param n1   操作数1
     * @param n2   操作数2
     */
    public static void display(Calculable calc, int n1, int n2) {
        System.out.println(calc.calculateInt(n1, n2));
    }
}
```

代码第 18 行声明 `display()` 方法，第一个参数 *calc* 是 Calculable 类型，它可以接受三种对象：Calculable 实现对象、Lambda 表达式和方法引用。代码第 6 行中第一个实际参数 `LambdaDemo::add` 是静态方法的方法引用。代码第 9 行中第一个实际参数`d::sub`，是实例方法的方法引用，*d* 是 LambdaDemo 实例。

提示：代码第 6 行的 `LambdaDemo::add` 和第 9 行的 `d::sub` 是方法引用，此时并没有调用方法，只是将引用传递给 `display()` 方法，在 `display()` 方法中才真正调用方法。

<br>

## 2.4、Lambda 与匿名内部类的联系和区别

Java Lambda 表达式的一个重要用法是简化某些匿名内部类的写法，因此它可以部分取代匿名内部类的作用。

Lambda 表达式与匿名内部类的相同点如下：

- Lambda 表达式与匿名内部类一样，都可以直接访问 effectively final 的局部变量，以及外部类的成员变量（包括实例变量和类变量）。
- Lambda 表达式创建的对象与匿名内部类生成的对象一样，都可以直接调用从接口中继承的默认方法。

下面程序示范了 Lambda 表达式与匿名内部类的相似之处。

```java
@FunctionalInterface
interface Displayable {
    // 定义一个抽象方法和默认方法
    void display();
    
    default int add(int a, int b) {
        return a + b;
    }
}

public class LambdaAndInner {
    private int age = 12;
    private static String name = "C语言中文网";
    
    public void test() {
        String url = "http://c.biancheng.net/";
        Displayable dis = () -> {
            // 访问的局部变量
            System.out.println("url 局部变量为:" + url);
            // 访问外部类的实例变量和类变量
            System.out.println("外部类的 age 实例变量为：" + age);
            System.out.println("外部类的 name 类变量为：" + name);
        };
        dis.display();
        // 调用dis对象从接口中继承的add()方法
        System.out.println(dis.add(3, 5)); 
    }
    
    public static void main(String[] args) {
        LambdaAndInner lambda = new LambdaAndInner();
        lambda.test();
    }
}
```

输出结果为：

```
url 局部变量为：http://c.biancheng.net/
外部类的 age 实例变量为：12
外部类的 name 类变量为：C语言中文网
8
```

上面程序使用 Lambda 表达式创建了一个 Displayable 的对象，Lambda 表达式的代码块中的代码第 19、21 和 22 行分别示范了访问 effectively final 的局部变量、外部类的实例变量和类变量。从这点来看， Lambda 表达式的代码块与匿名内部类的方法体是相同的。

与匿名内部类相似的是，由于 Lambda 表达式访问了 *url* 局部变量，因此该局部变量相当于有一个隐式的 `final` 修饰，因此同样不允许对 `url` 局部变量重新赋值。

当程序使用 Lambda 表达式创建了 Displayable 的对象之后，该对象不仅可调用接口中唯一的抽象方法，也可调用接口中的默认方法，如上面程序代码第 26 行所示。

Lambda 表达式与匿名内部类主要存在如下区别。

- 匿名内部类可以为任意接口创建实例——不管接口包含多少个抽象方法，只要匿名内部类实现所有的抽象方法即可；但 Lambda 表达式只能为函数式接口创建实例。
- 匿名内部类可以为抽象类甚至普通类创建实例；但 Lambda 表达式只能为函数式接口创建实例。
- 匿名内部类实现的抽象方法的方法体允许调用接口中定义的默认方法；但 Lambda 表达式的代码块不允许调用接口中定义的默认方法。

对于 Lambda 表达式的代码块不允许调用接口中定义的默认方法的限制，可以尝试对上面的 LambdaAndInner.java 程序稍做修改，在 Lambda 表达式的代码块中增加如下一行：

```java
// 尝试调用接口中的默认方法，编译器会报错
System.out.println(add(3, 5));
```

虽然 Lambda 表达式的目标类型 Displayable 中包含了 `add()` 方法，但 Lambda 表达式的代码块不允许调用这个方法；如果将上面的 Lambda 表达式改为匿名内部类的写法，当匿名内部类实现 `display()` 抽象方法时，则完全可以调用这个 `add()` 方法，如下面代码所示。

```java
public void test() {
    String url = "http://c.biancheng.net/";
    Displayable dis = new Displayable() {
        
        @Override
        public void display() {
            // 访问的局部变量
            System.out.println("url 局部变量为:" + url);
            // 访问外部类的实例变量和类变量
            System.out.println("外部类的 age 实例变量为：" + age);
            System.out.println("外部类的 name 类变量为：" + name);
            System.out.println(add(3, 5));
        }
    };
    dis.display();
}
```

<br>

# 3、新的日期和时间 API

在 Java 8 之前，所有关于日期和时间的 API 都存在各种使用方面的缺陷，主要有：

1. Java的 `java.util.Date` 和 `java.util.Calendar` 类易用性差，不支持时区，而且他们都不是线程安全的
2. 用于格式化日期的类 `DateFormat` 被放在 `java.text` 包中，它是一个抽象类，所以我们需要实例化一个 `SimpleDateFormat` 对象来处理日期格式化，并且 `DateFormat` 也是非线程安全，你得把它用 `ThreadLocal` 包起来才能在多线程中使用
3. 对日期的计算方式繁琐，而且容易出错，因为月份是从 0 开始的，从 `Calendar` 中获取的月份需要加一才能表示当前月份

由于以上这些问题，出现了一些三方的日期处理框架，例如 Joda-Time，date4j 等开源项目。但是，Java 需要一套标准的用于处理时间和日期的框架，于是 Java 8 中引入了新的日期 API。新的日期 API 是 JSR-310 规范的实现，Joda-Time 框架的作者正是 JSR-310 的规范的倡导者，所以能从 Java 8 的日期 API 中看到很多 Joda-Time 的特性。

<br>

## 3.1、Instant 

`Instant` 实例表示时间线上的一个点。 参考点是标准的 Java 纪元（epoch），即1970-01-01 T00：00：00Z（1970 年 1 月 1 日 00:00 GMT）。 `Instant` 类的 `EPOCH` 属性返回表示 Java 纪元的 `Instant` 实例。 在纪元之后的时间是正值，而在此之前的时间即是负值。

`Instant` 的静态 `now()` 方法返回一个表示当前时间的 `Instant` 对象：

```java
Instant now = Instant.now();
```

`getEpochSecond()` 方法返回自纪元以来经过的秒数。 `getNano()` 方法返回自上一秒开始以来的纳秒数。

`Instant` 类的一个常用用途是用来操作时间，如以下代码所示：

```java
public static void main(String[] args) {
    Instant start = Instant.now();
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    Instant end = Instant.now();
    System.out.println(Duration.between(start, end).toMillis());
}
```

如上面代码所示，`Duration` 类用于返回两个 `Instant` 之间时间数量的差异。

<br>

## 3.2、LocalDate

`LocalDate` 类只包括日期没有时间的部分。 它也没有时区。下表显示了 `LocalDate` 中一些重要的方法：

| 方法                                              | 描述                                                  |
| ------------------------------------------------- | ----------------------------------------------------- |
| `now()`                                           | 静态方法，返回今天的日期                              |
| `of()`                                            | 从指定年份、月份和日期创建 `LocalDate` 的静态方法     |
| `getDayOfMonth()`、`getMonthValue()`、`getYear()` | 以 `int` 形式返回此 `LocalDate` 的日、月或年          |
| `getMonth()`                                      | 以 `Month` 枚举常量返回此 `LocalDate` 的月份          |
| `plusDays()`、`minusDays()`                       | 给 `LocalDate` 添加或减去指定的天数                   |
| `plusWeeks()`、`minusWeeks()`                     | 给 `LocalDate` 添加或减去指定的星期数                 |
| `plusMonths()`、`minusMonths()`                   | 给 `LocalDate` 添加或减去指定的月份数                 |
| `plusYears()`、`minusYears()`                     | 给 `LocalDate` 添加或减去指定的年数                   |
| `isLeapYear()`                                    | 检查 `LocalDate` 指定的年份是否为闰年                 |
| `isAfter()`、`isBefore()`                         | 检查此 `LocalDate` 是在给定日期之后还是之前           |
| `lengthOfMonth()`                                 | 返回此 `LocalDate` 中月份的天数                       |
| `withDayOfMonth()`                                | 返回此 `LocalDate` 的拷贝，将月份中的某天设置为给定值 |
| `withMonth()`                                     | 返回此 `LocalDate` 的拷贝，其月份设置为给定值         |
| `withYear()`                                      | 返回此 `LocalDate` 的拷贝，并将年份设置为给定值       |

<br>

`LocalDate` 提供了各种创建日期的方法。 例如，要创建代表今天日期的 `LocalDate`，使用静态 `now()` 方法：

```java
LocalDate today = LocalDate.now();
```

要创建代表特定年、月和日的 `LocalDate`，使用 `of()` 方法，该方法也是静态的。 例如，以下代码创建了一个代表 2018 年 3 月 7 日的 `LocalDate` 实例：

```bash
LocalDate date = LocalDate.of(2018, 3, 7);
```

还有一个接受 `java.time.Month` 枚举的常量作为第二个参数的 `of()` 方法。 例如，下面是使用第二种方法重载构造相同日期的代码：

```bash
LocalDate date = LocalDate.of(2018, Month.MARCH, 7);
```

<br>

还有获取 `LocalDate` 的日、月或年的方法，例如 `getDayOfMonth()`、`getMonth()`、`getMonthValue()` 和 `getYear()`。 他们都没有任何参数，并返回一个 `int` 或 `Month` 的枚举常量。 另外，还有一个 `get()` 方法，它接受一个 `TemporalField` 并返回这个 `LocalDate` 的一部分。 例如，传递 `ChronoField.YEAR` 以获取 `LocalDate` 的年份部分：

```csharp
int year = localDate.get(ChronoField.YEAR);
```

> `ChronoField` 是一个实现 `TemporalField` 接口的枚举，因此可以传递一个 `ChronoField` 常量来获取。 `TemporalField` 和 `ChronoField` 都是 `java.time.temporal` 包的一部分。 但是，并非 `ChronoField` 中的所有常量都可以 `get()` 获取，因为并非所有常量都受支持。 例如，传递 `ChronoField.SECOND_OF_DAY` 以引发异常。 因此，取而代之，最好使用 `getMonth()`、`getYear()` 或类似方法来获取`LocalDate` 的组件。

<br>

此外，还有拷贝 `LocalDate` 的方法，例如 `plusDays()`、`plusYears()`、`minusMonths()` 等等。 例如，要获取表示明天的 `LocalDate`，可以创建一个代表今天的 `LocalDate`，然后调用其 `plusDays()` 方法：

```java
LocalDate tomorrow = LocalDate.now().plusDays(1);
```

要获取昨天表示的 `LocalDate`，可以使用 `minusDays()` 方法：

```java
LocalDate yesterday = LocalDate.now().minusDays(1);
```

另外，还有 `plus()` 和 `minus()` 方法以更通用的方式获得 `LocalDate` 的拷贝。 两者都接受一个 `int` 参数和一个 `TemporalUnit` 参数。 这些方法的签名如下：

```java
public LocalDate plus(long amountToAdd, java.time.temporal.TemporalUnit unit)

public LocalDate minus(long amountToSubtract, java.time.temporal.TemporalUnit unit)
```

例如，获得一个从今天开始前 20 年的 `LocalDate`，可以使用这段代码：

```java
LocalDate pastDate = LocalDate.now().minus(2, ChronoUnit.DECADES);
```

> `ChronoUnit` 是一个实现 `TemporalUnit` 的枚举，因此可以将 `ChronoUnit` 常量传递给 `plus()` 和 `minus()` 方法。

> `LocalDate` 是不可变的，因此无法更改。 任何返回 `LocalDate` 的方法都返回 `LocalDate` 的新实例。

<br>

## 3.3、Period

`Period` 类基于日期的时间数量构建，例如五天、一周或三年。 下面列出了一些重要的方法：

| 方法                                               | 描述                                                         |
| -------------------------------------------------- | ------------------------------------------------------------ |
| `between()`                                        | 在两个 `LocalDate` 之间创建一个 `Period` 示例                |
| `ofDays()`、`ofWeeks()`、`ofMonths()`、`ofYears()` | 创建代表给定天数、周、月、年的 `Period` 实例                 |
| `of()`                                             | 根据给定的年数、月数和天数创建一个 `Period` 实例             |
| `getDays()`、`getMonths()`、`getYears()`           | 以 `int` 形式返回此 `Period` 的天数、月、年                  |
| `isNegative()`                                     | 如果此 `Period` 的三个部分中的任何一个为负数，则返回 `true`，否则返回 `false` |
| `isZero()`                                         | 如果此 `Period` 的所有三个部分均为零，则返回 `true`，否则返回 `false` |
| `plusDays()`、`minusDays()`                        | 在此 `Period` 上添加或减去给定的天数                         |
| `plusMonths()`、`minusMonths()`                    | 在此 `Period` 上增加或减去给定的月数                         |
| `plusYears()`、`minusYears()`                      | 在此 `Period` 增加或减去给定的年数                           |
| `withDays()`                                       | 以指定的天数返回此 `Period` 的拷贝                           |
| `withMonths()`                                     | 以指定的月数返回此 `Period` 的拷贝                           |
| `withYears()`                                      | 以指定的年数返回此 `Period` 的拷贝                           |

<br>

创建一个 `Period` 很简单，这要感谢`between()`、`of()`、`ofDays()`、`ofWeeks()`、`ofMonths()`、`ofYears()` 等静态工厂方法。 例如，以下是如何创建代表两周的 `Period` 实例：

```java
Period twoWeeks = Period.ofWeeks(2);
```

要创建代表一年两个月三天的 `Period` 实例，请使用 `of()` 方法：

```java
Period p = Period.of(1, 2, 3);
```

<br>

要获取某个期间的年、月、日组件，调用其`getYears()`、`getMonths()`、`getDays()`方法。 例如，以下代码中的 `howManyDays` 变量的值是14：

```java
Period twoWeeks = Period.ofWeeks(2);

int howManyDays = twoWeeks.getDays();
```

<br>

最后，可以使用 `plusXXX()` 或 `minusXXX()` 方法以及 `withXXX()` 方法来创建 `Period` 的拷贝。 `Period` 是不可变的，所以这些方法返回新的 `Period` 实例。

<br>

下面的代码显示了一个计算个人年龄的年龄计算器。 它从两个 `LocalDate` 创建一个 `Period` 并调用它的 `getDays()`、`getMonths()` 和 `getYears()` 方法：

```java
import java.time.LocalDate;
import java.time.Period;

public class PeriodDemo1 {
    public static void main(String[] args) {
        LocalDate dateA = LocalDate.of(1978, 8, 26);
        LocalDate dateB = LocalDate.of(1988, 9, 28);
        Period period = Period.between(dateA, dateB);
        System.out.printf("Between %s and %s"
                + " there are %d years, %d months"
                + " and %d days%n", dateA, dateB,
                period.getYears(),
                period.getMonths(),
                period.getDays());
    }
}
```

运行 `PeriodDemo1` 类打印下面字符串。

```mipsasm
Between 1978-08-26 and 1988-09-28 there are 10 years, 1 months and 2 days
```

<br>

## 3.4、LocalDateTime

`LocalDateTime` 类是一个没有时区的日期时间的构建。 下表显示了 `LocalDateTime` 中一些重要的方法。 这些方法类似于 `LocalDate` 的方法，以及用于修改时间部分的一些其他方法，例如在 `LocalDate` 中不可用的 `plusHours()`、`plusMinutes()` 和 `plusSeconds()`：

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `now()`                                                      | 返回当前日期和时间的静态方法                                 |
| `of()`                                                       | 从指定年份、月份、日期、小时、分钟、秒和毫秒创建`LocalDateTime` 的静态方法 |
| `getYear()`、`getMonthValue()`、`getDayOfMonth()`、`getHour()`、`getMinute()`、`getSecond()` | 以 `int` 形式返回此 `LocalDateTime` 的年、月、日、小时、分钟或秒部分 |
| `plusDays()`、`minusDays()`                                  | 给当前 LocalDateTime 添加或减去指定的天数。                  |
| `plusWeeks()`、`minusWeeks()`                                | 给当前 `LocalDateTime` 添加或减去指定的周数。                |
| `plusMonths()`、`minusMonths()`                              | 给当前 `LocalDateTime` 添加或减去指定的月数。                |
| `plusYears()`、`minusYears()`                                | 给当前 `LocalDateTime` 添加或减去指定的年数。                |
| `plusHours()`、`minusHours()`                                | 给当前 `LocalDateTime` 添加或减去指定的小时数                |
| `plusMinutes()`、`minusMinutes()`                            | 给当前 `LocalDateTime` 添加或减去指定的分钟数                |
| `plusSeconds()`、`minusSeconds()`                            | 给当前 `LocalDateTime` 添加或减去指定的秒数                  |
| `IsAfter()`、`isBefore()`                                    | 检查此 `LocalDateTime` 是否在指定的日期时间之后或之前        |
| `withDayOfMonth()`                                           | 返回此 `LocalDateTime` 的拷贝，并将月份中的某天设置为指定值  |
| `withMonth()`、`withYear()`                                  | 返回此 `LocalDateTime` 的拷贝，其月或年设置为指定值          |
| `withHour()`、`withMinute()`、`withSecond()`                 | 返回此 `LocalDateTime` 的拷贝，其小时、分钟、秒设置为指定值  |

<br>

`LocalDateTime` 提供了各种静态方法来创建日期时间。 该方法现在带有三个重载方法返回当前的日期时间。 无参的方法是最容易使用的：

```java
LocalDateTime now = LocalDateTime.now();
```

要创建具有特定日期和时间的 `LocalDateTime`，请使用 `of()` 方法。 此方法有多个重载，并允许传递日期时间或 `LocalDate` 和 `LocalTime` 的单个部分。 以下是一些方法的签名：

```java
public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute)

public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute)

public static LocalDateTime of(LocalDate date, LocalTime time)
```

例如，下面的代码段创建一个 `LocalDateTime`，代表 2015 年 12 月 31 日早上八点：

```java
LocalDateTime endOfYear = LocalDateTime.of(2015, 12, 31, 8, 0);
```

可以使用 `plusXXX()` 或 `minusXXX()` 方法创建 `LocalDateTime` 的拷贝。 例如，此代码创建一个 `LocalDateTime`，它表示明天的同一时间：

```java
LocalDateTime now = LocalDateTime.now();

LocalDateTime sameTimeTomorrow = now.plusHours(24);
```

<br>

## 3.5、Time Zones

互联网数字分配机构（IANA）维护一个可从此网页下载的[时区数据库](http://www.iana.org/time-zones)。

Java 日期和时间 API 也适用于时区。 抽象类 `ZoneId`（在 `java.time` 包中）表示一个区域标识符。 它有一个名为 `getAvailableZoneIds()` 的静态方法，它返回所有区域标识符。 下面展示了如何使用这种方法打印所有时区的排序列表：

```java
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TimeZoneDemo1 {
    public static void main(String[] args) {
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        List<String> zoneList = new ArrayList<>(allZoneIds);
        Collections.sort(zoneList);      
        for (String zoneId : zoneList) {
            System.out.println(zoneId);
        }
        // alternatively, you can use this line of code to
        // print a sorted list of zone ids
        // ZoneId.getAvailableZoneIds().stream().sorted().
        //        forEach(System.out::println);
    }
}
```

`getAvailableZoneIds()` 返回字符串的 `Set` 集合。 可以使用 `Collections.sort()` 或更优雅地通过调用它的 `stream()` 方法对 `Set` 进行排序。 可以编写此代码对区域标识符进行排序：

```java
ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);
```

`getAvailableZoneIds()` 返回 586 个区域标识符的 `Set` 集合。 以下是上述代码中的一部分区域标识符：

```
Africa/Cairo
Africa/Johannesburg
America/Chicago
America/Los_Angeles
America/Mexico_City
America/New_York
America/Toronto
Antarctica/South_Pole
Asia/Hong_Kong
Asia/Shanghai
Asia/Tokyo
Australia/Melbourne
Australia/Sydney
Canada/Atlantic
Europe/Amsterdam
Europe/London
Europe/Paris
US/Central
US/Eastern
US/Pacific
```

<br>

## 3.6、ZonedDateTime

`ZonedDateTime` 类以一个时区的日期时间的构建。例如，以下是一个时区的日期时间:

```makefile
2015-12-31T10:59:59+01:00 Europe/Paris
```

`ZonedDateTime` 始终是不可变的，时间分量的存储精度为纳秒。

`ZonedDateTIme` 中一些重要方法的使用与 `LocalDateTime` 类似，只是多了一个时区的概念。可自行查阅 API。

<br>

像 `LocalDateTime` 一样，`ZonedDateTime `类现在提供静态 `now()` 和 `of()` 方法，并构造一个 `ZonedDateTime` 实例。 `now()` 方法创建一个 `ZonedDateTime` 代表执行的日期和时间。 无参 `now()` 方法会使用计算机的默认时区创建 `ZonedDateTime`：

```java
ZonedDateTime now = ZonedDateTime.now();
```

`now()` 的另一个重载方法允许传递区域标识符：

```java
ZonedDateTime parisTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
```

`of()` 方法也有好几个重载的方法。在所有情况下，都需要传递区域标识符：

```java
public static ZonedDateTime of(int year, int month, int dayOfMonth,
        int hour, int minute, int second, int nanosecond,
        ZoneId zone)
    
public static ZonedDateTime of(LocalDate date, LocalTime time, ZoneId zone)
    
public static ZonedDateTime of(LocalDateTime datetime, ZoneId zone)
```

<br>

像 `LocalDate` 和 `LocalDateTime` 一样，`ZonedDateTime` 提供了使用 `plusXXX()`、`minusXXX()` 和 `withXXX()` 方法创建实例拷贝的方法。

例如，下面代码行创建一个带默认时区的 `ZonedDateTime`，并调用它的 `minusDays()` 方法以在三天前创建相同的 `ZonedDateTime`：

```java
ZonedDateTime now = ZonedDateTime.now();
ZonedDateTime threeDaysEarlier = now.minusDays(3);
```

<br>

## 3.7、Duration

`Duration` 类是基于时间的持续时间的构建。 它与 `Period` 类似，不同之处在于 `Duration` 的时间分量为纳秒精度，并考虑了`ZonedDateTime` 实例之间的时区。 下表显示了 `Duration` 中重要的方法：

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `between()`                                                  | 在两个时差的对象之间创建一个 `Duration` 实例，例如在两个 `LocalDateTime` 或两个 `ZonedDateTime` 之间。 |
| `ofYears()`、`ofMonths()`、`ofWeeks()`、`ofDays()`、`ofHours()`、`ofMinutes()`、`ofSeconds()`、`ofNano()` | 创建给定年数、月、周、天、小时、分、秒、纳秒的 `Duration` 实例 |
| `of()`                                                       | 根据指定数量的时间单位创建 `Duration` 实例                   |
| `toDays()`、`toHours()`、`toMinutes()`                       | 以 `int` 形式返回此 `Duration` 的天数、小时、分钟数          |
| `isNegative()`                                               | 如果此 `Duration` 为负则返回 `true`，否则返回 `false`        |
| `isZero()`                                                   | 如果此 `Duration` 长度为零则返回 `true`，否则返回 `false`    |
| `plusDays()`、`minusDays()`                                  | 在此 `Duration` 内添加或减去指定的天数                       |
| `plusMonths()`、`minusMonths()`                              | 在此 `Duration` 内添加或减去指定的月数                       |
| `plusYears()`、`minusYears()`                                | 在 `Duration` 内添加或减去指定的年数                         |
| `withSeconds()`                                              | 以指定的秒数返回此 `Duration` 的拷贝                         |

<br>

可以通过调用静态方法 `between()` 或 `of()` 来创建 `Duration`。 下面的代码会在 2015 年 1 月 26 日 11:10 至 2015 年 1 月 26 日 12:40 之间创建两个 `LocalDateTime` 的 `Duration`：

```java
import java.time.Duration;
import java.time.LocalDateTime;

public class DurationDemo1 {
    public static void main(String[] args) {
        LocalDateTime dateTimeA = LocalDateTime.of(2015, 1, 26, 8, 10, 0, 0);
        LocalDateTime dateTimeB = LocalDateTime.of(2015, 1, 26, 11, 40, 0, 0);
        Duration duration = Duration.between(dateTimeA, dateTimeB);

        System.out.printf("There are %d hours and %d minutes.%n",
                duration.toHours(),
                duration.toMinutes() % 60);
    }
}
```

运行 `DurationDemo1` 类的结果是这样的：

```sql
There are 3 hours and 30 minutes.
```

<br>

下面的代码在两个 `ZoneDateTime` 之间创建一个 `Duration`，具有相同的日期和时间，但时区不同：

```java
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DurationDemo2 {
    public static void main(String[] args) {
        ZonedDateTime zdt1 = ZonedDateTime.of(LocalDateTime.of(2015, Month.JANUARY, 1, 8, 0),
                ZoneId.of("America/Denver"));
        ZonedDateTime zdt2 = ZonedDateTime.of(LocalDateTime.of(2015, Month.JANUARY, 1, 8, 0),
                ZoneId.of("America/Toronto"));

        Duration duration = Duration.between(zdt1, zdt2);
        System.out.printf("There are %d hours and %d minutes.%n",
                duration.toHours(),
                duration.toMinutes() % 60);
    }
}
```

运行 `DurationDemo2` 类在控制台上打印如下结果：

```sql
There are -2 hours and 0 minutes.
```

这是预料之中的，因为时区 `America/Denver`  和 `America/Toronto` 之间有两个小时的差异。

<br>

作为一个更复杂的例子，下面的代码显示了一个公交车旅行时间计算器。 它有一个方法 `calculateTravelTime()`，它需要一个离开的 `ZonedDateTime` 实例和一个到达的 `ZonedDateTime` 实例。 该代码调用 `calculateTravelTime()` 方法两次。 这两次公交车都在丹佛早上 8 点从科罗拉多州丹佛出发，并于多伦多时间第二天早上 8 点抵达多伦多。 公交车首次于 2014 年 3 月 8 日启程，第二次于 2014 年 3 月 18 日启程。

两种情况下的旅行时间是多少?

```java
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TravelTimeCalculator {
    public Duration calculateTravelTime(ZonedDateTime departure, ZonedDateTime arrival) {
        return Duration.between(departure, arrival);
    }

    public static void main(String[] args) {
        TravelTimeCalculator calculator = new TravelTimeCalculator();
        ZonedDateTime departure1 = ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH, 8, 8, 0),
                ZoneId.of("America/Denver"));
        ZonedDateTime arrival1 = ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH, 9, 8, 0),
                ZoneId.of("America/Toronto"));
        Duration travelTime1 = calculator.calculateTravelTime(departure1, arrival1);
        System.out.println("Travel time 1: " + travelTime1.toHours() + " hours");

        ZonedDateTime departure2 = ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH, 18,8, 0),
                ZoneId.of("America/Denver"));
        ZonedDateTime arrival2 = ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH, 19, 8, 0),
                ZoneId.of("America/Toronto"));
        Duration travelTime2 = calculator.calculateTravelTime(departure2, arrival2);
        System.out.println("Travel time 2: " + travelTime2.toHours() + " hours");
    }
}
```

运行结果为：

```less
Travel time 1: 21 hours

Travel time 2: 22 hours
```

为什么有这个区别？ 因为 2014 年的夏令时从 3 月 9 日星期日凌晨 2 点开始。 因此，在 2014 年 3 月 8 日至 2014 年 3 月 9 日之间 “失去” 了一小时。

<br>

## 3.8、DateTimeFormatter

新的日期 API 中提供了一个`DateTimeFormatter` 类用于处理日期格式化操作，它被包含在 `java.time.format` 包中，Java 8 的日期类有一个 `format()` 方法用于将日期格式化为字符串，该方法接收一个 `DateTimeFormatter` 类型参数：

```java
LocalDateTime dateTime = LocalDateTime.now();
String strDate1 = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);    // 20170105
String strDate2 = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);    // 2017-01-05
String strDate3 = dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);    // 14:20:16.998
String strDate4 = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));   // 2017-01-05
// 今天是：2017年 一月 05日 星期四
String strDate5 = dateTime.format(DateTimeFormatter.ofPattern("今天是：YYYY年 MMMM DD日 E", Locale.CHINESE)); 
```

同样，日期类也支持将一个字符串解析成一个日期对象，例如：

```java
String strDate6 = "2017-01-05";
String strDate7 = "2017-01-05 12:30:05";

LocalDate date = LocalDate.parse(strDate6, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
LocalDateTime dateTime1 = LocalDateTime.parse(strDate7, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
```

<br>

**Date 转换为 LocalDateTime**

方法 1：

```java
LocalDateTime localDateTime = new Date().toInstant().atZone(zoneId).toLocalDateTime();
```

方法 2：

```java
LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), zoneId);
```

