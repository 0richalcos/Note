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

<div align="center">
    <img src="../Images/Java8/5-191119163Sb30.png" alt="Java 7 运行结果" style="width:35%;" />
</div>


可以看到在 Java 7 中出现代码错误，提示我们必须显式的声明这个变量为 `final` 的（`run()` 方法中代码为输出 *name* 语句，即`System.out.println(name);`）。

<div align="center">
    <img src="../Images/Java8/5-191119164126217.png" alt="img" style="width:35%;" />
</div>


<div align="center">
    <img src="../Images/Java8/5-191119164142109.png" alt="img" style="width:35%;" />
</div>

因为从 Java 8 开始系统会默认添加 `final` 修饰符，所以在图 2 和图 3 中可以在匿名内部类中直接使用非 `final` 变量，而 `final` 修饰的局部变量不能在被重新赋值，所以图 3 中出现编译错误。

也就是说从 Java 8 开始，它不要求程序员必须将访问的局部变量显式的声明为 `final` 的。只要该变量不被重新赋值就可以。

一个非 `final` 的局部变量或方法参数，其值在初始化后就从未更改，那么该变量就是 effectively final。在 Lambda 表达式中，使用局部变量的时候，也要求该变量必须是 `final` 的，所以 effectively final 在 Lambda 表达式上下文中非常有用。

Lambda 表达式在编程中是经常使用的，而匿名内部类是很少使用的。那么，我们在 Lambda 编程中每一个被使用到的局部变量都去显示定义成 `final` 吗？显然这不是一个好方法。所以，Java 8 引入了 Effectively final 新概念。

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

## 2.1、Lambda 的简写方式

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

## 2.2、Lambda 的使用

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

## 2.3、Lambda 与匿名内部类的联系和区别

Java Lambda 表达式的一个重要用法是简化某些匿名内部类的写法，因此它可以部分取代匿名内部类的作用。

Lambda 表达式与匿名内部类的相同点如下：

- Lambda 表达式与匿名内部类一样，都可以直接访问 Effectively final 的局部变量，以及外部类的成员变量（包括实例变量和类变量）。
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

上面程序使用 Lambda 表达式创建了一个 Displayable 的对象，Lambda 表达式的代码块中的代码第 19、21 和 22 行分别示范了访问 Effectively final 的局部变量、外部类的实例变量和类变量。从这点来看， Lambda 表达式的代码块与匿名内部类的方法体是相同的。

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

# 3、方法引用

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

# 4、函数式接口

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

JDK 1.8 之前已有的函数式接口：

- java.lang.Runnable
- java.util.concurrent.Callable
- java.security.PrivilegedAction
- java.util.Comparator
- java.io.FileFilter
- java.nio.file.PathMatcher
- java.lang.reflect.InvocationHandler
- java.beans.PropertyChangeListener
- java.awt.event.ActionListener
- javax.swing.event.ChangeListener

JDK 1.8 新增加的函数接口：

- java.util.function

<br>

## 4.1、常用函数式接口

这里只介绍最基础的函数式接口，至于它的变体只要明白了基础自然就能够明白。

<br>

### 4.1.1、`Consumer<T>`

**`Consumer<T>`：消费型接口**

```java
/**
 * Represents an operation that accepts a single input argument and returns no
 * result. Unlike most other functional interfaces, {@code Consumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object)}.
 *
 * @param <T> the type of the input to the operation
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Consumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
```

<br>

**抽象方法：**`void accept(T t)`

接收一个参数进行消费，但无需返回结果。

**使用方式：**

```java
Consumer consumer = System.out::println;
consumer.accept("hello function");
```

**结果：**

```
hello function
```

<br>

**默认方法：** `andThen(Consumer<? super T> after)`

先消费然后再消费，先执行调用 `andThen` 接口的 `accept` 方法，然后再执行 `andThen` 方法参数 *after* 中的 `accept` 方法。

**使用方式：**

```java
Consumer<String> consumer1 = s -> System.out.print("车名：" + s.split(",")[0]);
Consumer<String> consumer2 = s -> System.out.println("-->颜色：" + s.split(",")[1]);
String[] strings = {"保时捷,白色", "法拉利,红色"};
for (String string : strings) {
    consumer1.andThen(consumer2).accept(string);
}
```

**结果：**

```
车名：保时捷-->颜色：白色
车名：法拉利-->颜色：红色
```

<br>

### 4.1.2、`Supplier<T>`

**`Supplier<T>`：供给型接口**

```java
/**
 * Represents a supplier of results.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #get()}.
 *
 * @param <T> the type of results supplied by this supplier
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
```

<br>

**抽象方法**：`T get()`

无参数，有返回值。

**使用方式：**

```java
Supplier<String> supplier = () -> "我要变的很有钱";
System.out.println(supplier.get());
```

**输出：**

```java
我要变得很有钱
```

这类接口适合提供数据的场景。

<br>

### 4.1.3、`Function<T,R>`

**`Function<T,R>`: 函数型接口**

```java
/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Function)
     */
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     *
     * @see #compose(Function)
     */
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    static <T> Function<T, T> identity() {
        return t -> t;
    }
}

```

<br>

**抽象方法：** `R apply(T t)`

传入一个参数，返回想要的结果。

**使用方式：**

```java
Function<Integer, Integer> function = e -> e * 6;
System.out.println(function.apply(2));
```

**输出：**

```
12
```

<br>

**默认方法：**`compose(Function<? super V, ? extends T> before)`

先执行 `compose` 方法参数 *before* 中的 `apply` 方法，然后将执行结果传递给调用 `compose` 函数中的 `apply` 方法在执行。

**使用方式：**

```java
Function<Integer, Integer> function1 = e -> e * 2;
Function<Integer, Integer> function2 = e -> e * e;

Integer apply2 = function1.compose(function2).apply(3);
System.out.println(apply2);
```

**输出：**

```
18
```

还是举一个乘法的例子，`compose` 方法执行流程是先执行 `function2` 的表达式也就是 `3*3=9`，然后在将执行结果传给 `function1` 的表达式也就是 `9*2=18`，所以最终的结果是 `18`。

> `andThen(Function<? super R, ? extends V> after)` 先执行调用 `andThen` 函数的 `apply` 方法，然后再将执行结果传递给 `andThen` 方法 `after` 参数中的 `apply` 方法再执行。它和 `compose` 方法正好是相反的执行顺序。

<br>

**默认方法：**`andThen(Function<? super R, ? extends V> after)`

**使用方式：**

```java
Function<Integer, Integer> function1 = e -> e * 2;
Function<Integer, Integer> function2 = e -> e * e;

Integer apply3 = function1.andThen(function2).apply(3);
System.out.println(apply3);
```

**输出：**

```
36
```

这里我们和 `compose` 方法使用一个例子，所以是一模一样的例子，由于方法的不同，执行顺序也就不相同，那么结果是大大不同的。`andThen` 方法是先执行 `function1` 表达式，也就是 `3*2=6`，然后再执行 `function2` 表达式也就是 `6*6=36`。结果就是 `36`。

<br>

**静态方法：**`identity()`

获取一个输入参数和返回结果相同的 Function 实例。

**使用方式：**

```java
Function<Integer, Integer> identity = Function.identity();
Integer apply = identity.apply(3);
System.out.println(apply);
```

**输出：**

```
3
```

平常没有遇到过使用这个方法的场景，总之这个方法的作用就是输入什么返回结果就是什么。

<br>

### 4.1.4、`Predicate<T>` 

**`Predicate<T>` ： 断言型接口**

```java
/**
 * Represents a predicate (boolean-valued function) of one argument.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Object)}.
 *
 * @param <T> the type of the input to the predicate
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    /**
     * Returns a predicate that tests if two arguments are equal according
     * to {@link Objects#equals(Object, Object)}.
     *
     * @param <T> the type of arguments to the predicate
     * @param targetRef the object reference with which to compare for equality,
     *               which may be {@code null}
     * @return a predicate that tests if two arguments are equal according
     * to {@link Objects#equals(Object, Object)}
     */
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }
}
```

<br>

**抽象方法：** `boolean test(T t)`

传入一个参数，返回一个布尔值。

**使用方式：**

```java
Predicate<Integer> predicate = t -> t > 0;
boolean test = predicate.test(1);
System.out.println(test);
```

**输出：**

```
true
```

当 `predicate` 函数调用 `test` 方法的时候，就会执行拿 `test` 方法的参数进行 `t -> t > 0` 的条件判断，`1` 肯定是大于 `0` 的，最终结果为 `true`。

<br>

**默认方法：**`and(Predicate<? super T> other)`

相当于逻辑运算符中的 `&&`，当两个 Predicate 函数的返回结果都为 `true` 时才返回 `true`。

**使用方式：**

```java
Predicate<String> predicate1 = s -> s.length() > 0;
Predicate<String> predicate2 = Objects::nonNull;
boolean test = predicate1.and(predicate2).test("&&测试");
System.out.println(test);
```

**输出：**

```
true
```

<br>

**默认方法：**`or(Predicate<? super T> other)` 

相当于逻辑运算符中的 `||`，当两个 Predicate 函数的返回结果有一个为 `true` 则返回 true，否则返回 `false`。

**使用方式：**

```java
Predicate<String> predicate1 = s -> false;
Predicate<String> predicate2 = Objects::nonNull;
boolean test = predicate1.and(predicate2).test("||测试");
System.out.println(test);
```

**输出：**

```
false
```

<br>

**默认方法：**`negate()`

这个方法的意思就是取反。

**使用方式：**

```java
Predicate<String> predicate = s -> s.length() > 0;
boolean result = predicate.negate().test("取反");
System.out.println(result);
```

**输出：**

```
false
```

很明显正常执行 `test` 方法的话应该为 `true`，但是调用 `negate` 方法后就返回为 `false` 了。 

<br>

**静态方法：**`isEqual(Object targetRef)`

对当前操作进行 "=" 操作，即取等操作，可以理解为 A == B。

**使用方式:**

```java
boolean test1 = Predicate.isEqual("test").test("test");
boolean test2 = Predicate.isEqual("test").test("equal");
System.out.println(test1);
System.out.println(test2);
```

**输出：**

```
true
false
```

<br>

### 4.1.5、Operator

可以简单理解成算术中的各种运算操作，当然不仅仅是运算这么简单，因为它只定义了运算这个定义，但至于运算成什么样你说了算。由于没有最基础的 Operator，这里将通过 BinaryOperator、IntBinaryOperator 来理解 Operator 函数式接口，先从简单的 IntBinaryOperator 开始。

<br>

**IntBinaryOperator **

```java
/**
 * Represents an operation upon two {@code int}-valued operands and producing an
 * {@code int}-valued result.   This is the primitive type specialization of
 * {@link BinaryOperator} for {@code int}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #applyAsInt(int, int)}.
 *
 * @see BinaryOperator
 * @see IntUnaryOperator
 * @since 1.8
 */
@FunctionalInterface
public interface IntBinaryOperator {

    /**
     * Applies this operator to the given operands.
     *
     * @param left the first operand
     * @param right the second operand
     * @return the operator result
     */
    int applyAsInt(int left, int right);
}
```

IntBinaryOperator 接口内只有一个 `applyAsInt` 方法，其接收两个 int 类型的参数，并返回一个 int 类型的结果，其实这个跟 Function 接口的 `apply` 有点像，但是这里限定了，只能是 int 类型。

<br>

**BinaryOperator**

```java
/**
 * Represents an operation upon two operands of the same type, producing a result
 * of the same type as the operands.  This is a specialization of
 * {@link BiFunction} for the case where the operands and the result are all of
 * the same type.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> the type of the operands and result of the operator
 *
 * @see BiFunction
 * @see UnaryOperator
 * @since 1.8
 */
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
    /**
     * Returns a {@link BinaryOperator} which returns the lesser of two elements
     * according to the specified {@code Comparator}.
     *
     * @param <T> the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the two values
     * @return a {@code BinaryOperator} which returns the lesser of its operands,
     *         according to the supplied {@code Comparator}
     * @throws NullPointerException if the argument is null
     */
    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link BinaryOperator} which returns the greater of two elements
     * according to the specified {@code Comparator}.
     *
     * @param <T> the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the two values
     * @return a {@code BinaryOperator} which returns the greater of its operands,
     *         according to the supplied {@code Comparator}
     * @throws NullPointerException if the argument is null
     */
    public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}
```

BinaryOperator 是 BiFunction 生的，而 IntBinaryOperator 是从石头里蹦出来的，BinaryOperator 自身定义了`minBy`、`maxBy` 默认方法，并且参数都是 Comparator，就是根据传入的比较器的比较规则找出最小最大的数据。

<br>

## 4.2、function 包下所有接口

java.util.function 它包含了很多类，用来支持 Java 的函数式编程，该包中的函数式接口有：

| 接口                      | 描述                                                         |
| ------------------------- | ------------------------------------------------------------ |
| `BiConsumer<T,U>`         | 接受两个参数，无返回值。                                     |
| `BiFunction<T,U,R>`       | 接受两个参数，返回一个结果。                                 |
| `BinaryOperator<T>`       | 代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果。 |
| `BiPredicate<T,U>`        | 接受一个参数，返回一个 boolean 类型结果。                    |
| `BooleanSupplier`         | 无参数，返回一个 boolean 类型结果。                          |
| `Consumer<T>`             | 接受一个参数，无返回值。                                     |
| `DoubleBinaryOperator`    | 代表了作用于两个 double 值操作符的操作，并且返回了一个 double 值的结果。 |
| `DoubleConsumer`          | 接受一个 double 类型参数，无返回值。                         |
| `DoubleFunction<R>`       | 接受一个 double 类型参数，返回一个结果。                     |
| `DoublePredicate`         | 接受一个 double 类型参数，返回一个 boolean 类型结果。        |
| `DoubleSupplier`          | 无参数，返回一个 double 类型结果。                           |
| `DoubleToIntFunction`     | 接受一个 double 类型参数，返回一个 int 类型结果。            |
| `DoubleToLongFunction`    | 接受一个 double 类型参数，返回一个 long 类型结果。           |
| `DoubleUnaryOperator`     | 接受一个参数同为类型 double，返回值类型也为 double 。        |
| `Function<T,R>`           | 接受一个输入参数，返回一个结果。                             |
| `IntBinaryOperator`       | 接受两个参数同为类型 int，返回值类型也为 int 。              |
| `IntConsumer`             | 接受一个 int 类型的参数，无返回值。                          |
| `IntFunction<R>`          | 接受一个 int 类型参数，返回一个结果 。                       |
| `IntPredicate`            | 接受一个 int 类型参数，返回一个 boolean 类型结果。           |
| `IntSupplier`             | 无参数，返回一个 int 类型结果。                              |
| `	IntToDoubleFunction` | 接受一个 int 类型参数，返回一个 double 类型结果 。           |
| `IntToLongFunction`       | 接受一个 int 类型参数，返回一个 long 类型结果。              |
| `IntUnaryOperator`        | 接受一个参数同为类型 int，返回值类型也为 int 。              |
| `LongBinaryOperator`      | 接受两个参数同为类型 long，返回值类型也为 long。             |
| `LongConsumer`            | 接受一个 long 类型的参数，不返回结果。                       |
| `LongFunction<R>`         | 接受一个 long 类型参数，返回一个结果。                       |
| `LongPredicate`           | 接受一个 long 类型参数，返回一个 boolean 类型结果。          |
| `LongSupplier`            | 无参数，返回一个 long 类型结果。                             |
| `LongToDoubleFunction`    | 接受一个 long 类型参数，返回一个 double 类型结果。           |
| `LongToIntFunction`       | 接受一个 long 类型参数，返回一个 int 类型结果。              |
| `LongUnaryOperator`       | 接受一个参数同为类型 long，返回值类型也为 long。             |
| `ObjDoubleConsumer<T>`    | 接受一个 object 类型和一个 double 类型的参数，无返回值。     |
| `	ObjIntConsumer<T>`   | 接受一个 object 类型和一个 int 类型的参数，无返回值。        |
| `ObjLongConsumer<T>`      | 接受一个 object 类型和一个 long 类型的参数，无返回值。       |
| `Predicate<T>`            | 接受一个参数，返回一个 boolean 类型结果。                    |
| `Supplier<T>`             | 无参数，返回一个结果。                                       |
| `ToDoubleBiFunction<T,U>` | 接受两个参数，返回一个 double 类型结果。                     |
| `ToDoubleFunction<T>`     | 接受一个参数，返回一个 double 类型结果。                     |
| `ToIntBiFunction<T,U>`    | 接受两个参数，返回一个 int 类型结果。                        |
| `ToIntFunction<T>`        | 接受一个参数，返回一个 int 类型结果。                        |
| `ToLongBiFunction<T,U>`   | 接受两个参数，返回一个 long 类型结果。                       |
| `ToLongFunction<T>`       | 接受一个参数，返回一个 long 类型结果。                       |
| `UnaryOperator<T>`        | 接受一个参数为类型 T，返回值类型也为 T。                     |

<br>

# 5、新的日期和时间 API

在 Java 8 之前，所有关于日期和时间的 API 都存在各种使用方面的缺陷，主要有：

1. Java的 `java.util.Date` 和 `java.util.Calendar` 类易用性差，不支持时区，而且他们都不是线程安全的
2. 用于格式化日期的类 `DateFormat` 被放在 `java.text` 包中，它是一个抽象类，所以我们需要实例化一个 `SimpleDateFormat` 对象来处理日期格式化，并且 `DateFormat` 也是非线程安全，你得把它用 `ThreadLocal` 包起来才能在多线程中使用
3. 对日期的计算方式繁琐，而且容易出错，因为月份是从 0 开始的，从 `Calendar` 中获取的月份需要加一才能表示当前月份

由于以上这些问题，出现了一些三方的日期处理框架，例如 Joda-Time，date4j 等开源项目。但是，Java 需要一套标准的用于处理时间和日期的框架，于是 Java 8 中引入了新的日期 API。新的日期 API 是 JSR-310 规范的实现，Joda-Time 框架的作者正是 JSR-310 的规范的倡导者，所以能从 Java 8 的日期 API 中看到很多 Joda-Time 的特性。

<br>

## 5.1、Instant 

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

## 5.2、LocalDate

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

## 5.3、Period

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

## 5.4、LocalDateTime

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

## 5.5、Time Zones

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

## 5.6、ZonedDateTime

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

## 5.7、Duration

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

## 5.8、DateTimeFormatter

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

<br>

# 6、默认方法

Java 8 新增了接口的默认方法。

简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。只需在方法名前面加个 `default` 关键字即可实现默认方法。

> **为什么要有这个特性？**
>
> 首先，之前的接口是个双刃剑，好处是面向抽象而不是面向具体编程，缺陷是，当需要修改接口时候，需要修改全部实现该接口的类，目前的 java 8 之前的集合框架没有 `foreach` 方法，通常能想到的解决办法是在 JDK 里给相关的接口添加新的方法及实现。然而，对于已经发布的版本，是没法在给接口添加新方法的同时不影响已有的实现。所以引进的默认方法。他们的目的是为了解决接口的修改与现有的实现不兼容的问题。

**语法**

默认方法语法格式如下：

```java
public interface Vehicle {
   default void print(){
      System.out.println("我是一辆车!");
   }
}
```

**多个默认方法**

一个接口有默认方法，考虑这样的情况，一个类实现了多个接口，且这些接口有相同的默认方法，以下实例说明了这种情况的解决方法：

```java
public interface Vehicle {
   default void print(){
      System.out.println("我是一辆车!");
   }
}
 
public interface FourWheeler {
   default void print(){
      System.out.println("我是一辆四轮车!");
   }
}
```

第一个解决方案是创建自己的默认方法，来覆盖重写接口的默认方法：

```java
public class Car implements Vehicle, FourWheeler {
   default void print(){
      System.out.println("我是一辆四轮汽车!");
   }
}
```

第二种解决方案可以使用 `super` 来调用指定接口的默认方法：

```java
public class Car implements Vehicle, FourWheeler {
   public void print(){
      Vehicle.super.print();
   }
}
```

<br>

## 6.1、静态默认方法

Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法。例如：

```java
public interface Vehicle {
   default void print(){
      System.out.println("我是一辆车!");
   }
    // 静态方法
   static void blowHorn(){
      System.out.println("按喇叭!!!");
   }
}
```

<br>

## 6.2、默认方法实例

可以通过以下代码来了解关于默认方法的使用，可以将代码放入 Java8Tester.java 文件中：

```java
public class Java8Tester {
   public static void main(String args[]){
      Vehicle vehicle = new Car();
      vehicle.print();
   }
}
 
interface Vehicle {
   default void print(){
      System.out.println("我是一辆车!");
   }
    
   static void blowHorn(){
      System.out.println("按喇叭!!!");
   }
}
 
interface FourWheeler {
   default void print(){
      System.out.println("我是一辆四轮车!");
   }
}
 
class Car implements Vehicle, FourWheeler {
   public void print(){
      Vehicle.super.print();
      FourWheeler.super.print();
      Vehicle.blowHorn();
      System.out.println("我是一辆汽车!");
   }
}
```

执行以上脚本，输出结果为：

```shell
$ javac Java8Tester.java 
$ java Java8Tester
我是一辆车!
我是一辆四轮车!
按喇叭!!!
我是一辆汽车!
```

<br>

# 7、Base64

在 Java 8 中，Base64 编码已经成为 Java 类库的标准。

Java 8 内置了 Base64 编码的编码器和解码器。

Base64 工具类提供了一套静态方法获取下面三种 BASE64 编解码器：

- 基本：输出被映射到一组字符 `A-Za-z0-9+/`，编码不添加任何行标，输出的解码仅支持 `A-Za-z0-9+/`。
- URL：输出映射到一组字符 `A-Za-z0-9+_`，输出是 URL 和文件。
- MIME：输出隐射到 MIME 友好格式。输出每行不超过 76 字符，并且使用 `\r` 并跟随 `\n` 作为分割。编码输出最后没有行分割。

<br>

## 7.1、内嵌类

| 序号 | 内嵌类 & 描述                                                |
| :--- | :----------------------------------------------------------- |
| 1    | **static class Base64.Decoder**<br>该类实现一个解码器，使用 Base64 编码来解码字节数据。 |
| 2    | **static class Base64.Encoder**<br>该类实现一个编码器，使用 Base64 编码来编码字节数据。 |

<br>

## 7.2、方法

| 序号 | 方法名 & 描述                                                |
| :--- | :----------------------------------------------------------- |
| 1    | **static Base64.Decoder getDecoder()**<br>返回一个 Base64.Decoder ，解码使用基本型 base64 编码方案。 |
| 2    | **static Base64.Encoder getEncoder()**<br/>返回一个 Base64.Encoder ，编码使用基本型 base64 编码方案。 |
| 3    | **static Base64.Decoder getMimeDecoder()**<br/>返回一个 Base64.Decoder ，解码使用 MIME 型 base64 编码方案。 |
| 4    | **static Base64.Encoder getMimeEncoder()**<br/>返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案。 |
| 5    | **static Base64.Encoder getMimeEncoder(int lineLength, byte[] lineSeparator)**<br/>返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案，<br>可以通过参数指定每行的长度及行的分隔符。 |
| 6    | **static Base64.Decoder getUrlDecoder()**<br/>返回一个 Base64.Decoder ，解码使用 URL 和文件名安全型 base64 编码方案。 |
| 7    | **static Base64.Encoder getUrlEncoder()**<br/>返回一个 Base64.Encoder ，编码使用 URL 和文件名安全型 base64 编码方案。 |

> 注意：Base64 类的很多方法从 **java.lang.Object** 类继承。

<br>

## 7.3、Base64 实例

以下实例演示了 Base64 的使用：

```java
import java.util.Base64;
import java.util.UUID;
import java.io.UnsupportedEncodingException;
 
public class Java8Tester {
   public static void main(String args[]){
      try {
        
         // 使用基本编码
         String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
         System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
        
         // 解码
         byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
        
         System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));
         base64encodedString = Base64.getUrlEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
         System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);
        
         StringBuilder stringBuilder = new StringBuilder();
        
         for (int i = 0; i < 10; ++i) {
            stringBuilder.append(UUID.randomUUID().toString());
         }
        
         byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
         String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
         System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
         
      }catch(UnsupportedEncodingException e){
         System.out.println("Error :" + e.getMessage());
      }
   }
}
```

执行以上脚本，输出结果为：

```
$ javac Java8Tester.java 
$ java Java8Tester
原始字符串: runoob?java8
Base64 编码字符串 (URL) :VHV0b3JpYWxzUG9pbnQ_amF2YTg=
Base64 编码字符串 (MIME) :M2Q4YmUxMTEtYWRkZi00NzBlLTgyZDgtN2MwNjgzOGY2NGFlOTQ3NDYyMWEtZDM4ZS00YWVhLTkz
OTYtY2ZjMzZiMzFhNmZmOGJmOGI2OTYtMzkxZi00OTJiLWEyMTQtMjgwN2RjOGI0MTBmZWUwMGNk
NTktY2ZiZS00MTMxLTgzODctNDRjMjFkYmZmNGM4Njg1NDc3OGItNzNlMC00ZWM4LTgxNzAtNjY3
NTgyMGY3YzVhZWQyMmNiZGItOTIwZi00NGUzLTlkMjAtOTkzZTI1MjUwMDU5ZjdkYjg2M2UtZTJm
YS00Y2Y2LWIwNDYtNWQ2MGRiOWQyZjFiMzJhMzYxOWQtNDE0ZS00MmRiLTk3NDgtNmM4NTczYjMx
ZDIzNGRhOWU4NDAtNTBiMi00ZmE2LWE0M2ItZjU3MWFiNTI2NmQ2NTlmMTFmZjctYjg1NC00NmE1
LWEzMWItYjk3MmEwZTYyNTdk
```

<br>

# 8、Optional 类

Optional 类是一个可以为 `null` 的容器对象。如果值存在则 `isPresent()` 方法会返回 `true`，调用 `get()` 方法会返回该对象。

Optional 是个容器：它可以保存类型 T 的值，或者仅仅保存 `null`。Optional 提供很多有用的方法，这样我们就不用显式进行空值检测。

Optional 类的引入很好的解决空指针异常。

<br>

## 8.1、Optional 声明

以下是一个 `java.util.Optional<T>` 类的声明：

```java
public final class Optional<T> extends Object
```

<br>

## 8.2、Optional 方法

| 方法                                                         | 描述                                                         |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| `static <T> Optional<T> empty()`                             | 返回空的 Optional 实例。                                     |
| `boolean equals(Object obj)`                                 | 判断其他对象是否等于 Optional。                              |
| `Optional<T> filter(Predicate<? super T> predicate)`         | 如果值存在，并且这个值匹配给定的 *predicate*，返回一个Optional 用以描述这个值，否则返回一个空的 Optional。 |
| `<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)` | 如果值存在，返回基于 Optional 包含的映射方法的值，否则返回一个空的 Optional。 |
| `T get()`                                                    | 如果在这个 Optional 中包含这个值，返回值，否则抛出异常：NoSuchElementException。 |
| `int hashCode()`                                             | 返回存在值的哈希码，如果值不存在则返回 `0`。                 |
| `void ifPresent(Consumer<? super T> consumer)`               | 如果值存在则使用该值调用 *consumer*，否则不做任何事情。      |
| `boolean isPresent()`                                        | 如果值存在则方法会返回 `true`，否则返回 `false`。            |
| `<U>Optional<U> map(Function<? super T, ? extends U> mapper)` | 如果有值，则对其执行调用映射函数得到返回值。如果返回值不为 `null`，则创建包含映射返回值的 Optional 作为 `map` 方法返回值，否则返回空 Optional。 |
| `static <T> Optional<T> of(T value)`                         | 返回一个指定非 `null` 值的 Optional。                        |
| `static <T> Optional<T> ofNullable(T value)`                 | 如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional。 |
| `T orElse(T other)`                                          | 如果存在该值，返回值， 否则返回 *other*。                    |
| `T orElseGet(Supplier<? extends T> other)`                   | 如果存在该值，返回值， 否则触发 *other*，并返回 *other* 调用的结果。 |
| `<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)` | 如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常   |
| `String toString()`                                          | 返回一个 Optional 的非空字符串，用来调试                     |

> 注意： 这些方法是从 `java.lang.Object` 类继承来的。

<br>

## 8.3、Optional 实例

```java
import java.util.Optional;
 
public class Java8Tester {
   public static void main(String args[]){
   
      Java8Tester java8Tester = new Java8Tester();
      Integer value1 = null;
      Integer value2 = new Integer(10);
        
      // Optional.ofNullable - 允许传递为 null 参数
      Optional<Integer> a = Optional.ofNullable(value1);
        
      // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
      Optional<Integer> b = Optional.of(value2);
      System.out.println(java8Tester.sum(a,b));
   }
    
   public Integer sum(Optional<Integer> a, Optional<Integer> b){
    
      // Optional.isPresent - 判断值是否存在
        
      System.out.println("第一个参数值存在: " + a.isPresent());
      System.out.println("第二个参数值存在: " + b.isPresent());
        
      // Optional.orElse - 如果值存在，返回它，否则返回默认值
      Integer value1 = a.orElse(new Integer(0));
        
      //Optional.get - 获取值，值需要存在
      Integer value2 = b.get();
      return value1 + value2;
   }
}
```

执行以上脚本，输出结果为：

```
$ javac Java8Tester.java 
$ java Java8Tester
第一个参数值存在: false
第二个参数值存在: true
10
```

<br>

# 9、Stream

## 9.1、Stream 流是如何工作的？

流表示包含着一系列元素的集合，我们可以对其做不同类型的操作，用来对这些元素执行计算：

```java
List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

myList.stream() // 创建流
        .filter(s -> s.startsWith("c")) // 执行过滤，过滤出以 c 为前缀的字符串
        .map(String::toUpperCase) // 转换成大写
        .sorted() // 排序
        .forEach(System.out::println); // for 循环打印
```

输出：

```
C1
C2
```

我们可以对流进行中间操作或者终端操作：

<div align="center">
    <img src="../Images/Java8/image-20220812142807269.png" alt="image-20220812142807269" style="width:80%;" />
</div>

1. 中间操作会再次返回一个流，所以，我们可以链接多个中间操作，注意这里是不用加分号的。上图中的 `filter` 过滤，`map` 对象转换，`sorted` 排序，就属于中间操作。
2. 终端操作是对流操作的一个结束动作，一般返回 `void` 或者一个非流的结果。上图中的 `forEach` 循环就是一个终止操作。

看完上面的操作，感觉是不是很像一个流水线式操作呢。

实际上，大部分流操作都支持 lambda 表达式作为参数，正确理解，应该说是接受一个函数式接口的实现作为参数。

<br>

## 9.2、不同类型的 Stream 流

我们可以从各种数据源中创建 Stream 流，其中以 Collection 集合最为常见。如 `List` 和 `Set` 均支持 `stream()` 方法来创建顺序流或者是并行流。

并行流是通过多线程的方式来执行的，它能够充分发挥多核 CPU 的优势来提升性能。

```java
Arrays.asList("a1", "a2", "a3")
        .stream() // 创建流
        .findFirst() // 找到第一个元素
        .ifPresent(System.out::println);  // 如果存在，即输出
```

输出：

```
a1
```

在集合上调用 `stream()` 方法会返回一个普通的 Stream 流。但是大可不必刻意地创建一个集合，再通过集合来获取 Stream 流，还可以通过如下这种方式：

```java
Stream.of("a1", "a2", "a3")
    .findFirst()
    .ifPresent(System.out::println);
```

输出：

```
a1
```

例如上面这样，我们可以通过 `Stream.of()` 从一堆对象中创建 Stream 流。

除了常规对象流之外，Java 8 还附带了一些特殊类型的流，用于处理原始数据类型 `int`、`long` 以及 `double`，它们就是 `IntStream`、`LongStream` 还有 `DoubleStream`。

其中，`IntStreams.range()` 方法还可以被用来取代常规的 `for` 循环, 如下所示：

```go
IntStream.range(1, 4)
		.forEach(System.out::println);// 相当于 for (int i = 1; i < 4; i++) {}
```

输出：

```
1
2
3
```

上面这些原始类型流的工作方式与常规对象流基本是一样的，但还是略微存在一些区别：

- 原始类型流使用其独有的函数式接口，例如 `IntFunction` 代替 `Function`、`IntPredicate` 代替 `Predicate`。
- 原始类型流支持额外的终端聚合操作，比如 `sum()` 以及 `average()`，如下所示：

```go
IntStream.range(1, 4).map(n -> 2 * n + 1) // 对数值中的每个对象执行 2*n + 1 操作
        .average() // 求平均值
        .ifPresent(System.out::println);  // 如果值不为空，则输出
```

输出：

```
5.0
```

但是，偶尔我们也有这种需求，需要将常规对象流转换为原始类型流，这时中间操作 `mapToInt()`、`mapToLong()` 以及 `mapToDouble` 就派上用场了：

```java
Stream.of("a1", "a2", "a3")
    .map(s -> s.substring(1)) // 对每个字符串元素从下标1位置开始截取
    .mapToInt(Integer::parseInt) // 转成 int 基础类型类型流
    .max() // 取最大值
    .ifPresent(System.out::println);  // 不为空则输出
```

输出：

```
3
```

如果需要将原始类型流装换成对象流，可以使用 `mapToObj()` 来达到目的：

```java
IntStream.range(1, 4)
    .mapToObj(i -> "a" + i) // for 循环 1->4, 拼接前缀 a
    .forEach(System.out::println); // for 循环打印
```

输出：

```
a1
a2
a3
```

下面是一个组合示例，我们将双精度流首先转换成 int 类型流，然后再将其装换成对象流：

```rust
Stream.of(1.0, 2.0, 3.0)
        .mapToInt(Double::intValue) // double 类型转 int
        .mapToObj(i -> "a" + i) // 对值拼接前缀 a
        .forEach(System.out::println); // for 循环打印
```

输出：

```
a1
a2
a3
```

<br>

## 9.3、Stream 流的处理顺序

Stream 流的中间操作的有个重要特性 —— **延迟性**。观察下面这个没有终端操作的示例代码：

```kotlin
Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
            System.out.println("filter: " + s);
            return true;
        });
```

执行此代码段它不会打印任何内容。因为当且仅当存在终端操作时，中间操作操作才会被执行。

接下来，对上面的代码添加 `forEach`终端操作：

```java
Stream.of("d2", "a2", "b1", "b3", "c")
    .filter(s -> {
        System.out.println("filter: " + s);
        return true;
    })
    .forEach(s -> System.out.println("forEach: " + s));
```

输出如下：

```
filter:  d2
forEach: d2
filter:  a2
forEach: a2
filter:  b1
forEach: b1
filter:  b3
forEach: b3
filter:  c
forEach: c
```

代码并不是先将所有 `filter` 前缀的字符串打印出来，接着才会打印 `forEach` 前缀的字符串。事实上，输出的结果是随着链条垂直移动的。比如说，当 Stream 开始处理 `d2` 元素时，它实际上会在执行完 `filter` 操作后，再执行 `forEach` 操作，接着才会处理第二个元素。

因为出于性能的考虑。这样设计可以减少对每个元素的实际操作数，看完下面代码你就明白了：

```kotlin
Stream.of("d2", "a2", "b1", "b3", "c")
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase(); // 转大写
        })
        .anyMatch(s -> {
            System.out.println("anyMatch: " + s);
            return s.startsWith("A"); // 过滤出以 A 为前缀的元素
        });
```

输出：

```
map: d2
anyMatch: D2
map: a2
anyMatch: A2
```

终端操作 `anyMatch()` 表示任何一个元素以 A 为前缀，返回为 `true`，就停止循环。所以它会从 `d2` 开始匹配，接着循环到 `a2` 的时候，返回为 `true` ，于是停止循环。

由于数据流的链式调用是垂直执行的，`map` 这里只需要执行两次。相对于水平执行来说，`map` 会执行尽可能少的次数，而不是把所有元素都 `map` 转换一遍。
