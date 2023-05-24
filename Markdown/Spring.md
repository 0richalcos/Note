---
typora-copy-images-to: upload
---

# 1、Spring

## 1.1、简介

- Spring：春天----->给软件行业带来了春天！
- 2002，首次推出了Spring框架的雏形：interface21框架！
- Spring框架即以interface21框架为基础，经过重新设计，并不断丰富其内涵，于2004年3月24日，发布了1.0正式版。
- **Rod Johnosn**，Spring Framework创始人，注明作者。很难想象Rod Johnosn的学历，真的让好多人大吃一惊，他是悉尼大学的博士，然而他的专业不是计算机，而是音乐学。
- Spring的理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架！



- SSH：Struct2 + Spring + Hibernate
- SSM：SpringMVC + Spring + Mybatis



官网：https://spring.io/

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.4.RELEASE</version>
</dependency>
```



## 1.2、优点

- Spring是一个开源的免费框架（容器）！
- Spring是一个轻量级的、非入侵式的框架！
- 控制反转（IoC），面向切面编程（AOP）！
- 支持事务处理，对框架整合的支持！

**轻量**　　

从大小与开销两方面而言 Spring 都是轻量的。完整的 Spring 框架可以在一个大小只有 1MB 多的 JAR 文件里发布。并且 Spring 所需的处理开销也是微不足道的。此外，Spring 是非侵入式的：典型地Spring应用中的对象不依赖于 Spring 的特定类。

**控制反转**　　

Spring 通过一种称作控制反转（IoC）的技术促进了松耦合。当应用了 IoC，一个对象依赖的其它对象会通过被动的方式传递进来，而不是这个对象自己创建或者查找依赖对象。你可以认为 IoC 与 JNDI 相反——不是对象从容器中查找依赖，而是容器在对象初始化时不等对象请求就主动将依赖传递给它。

**面向切面**　　

Spring 提供了面向切面编程的丰富支持，允许通过分离应用的业务逻辑与系统级服务（例如审计（auditing）和事务（transaction）管理）进行内聚性的开发。应用对象只实现它们应该做的——完成业务逻辑——仅此而已。它们并不负责（甚至是意识）其它的系统级关注点，例如日志或事务支持。

**容器**

Spring 包含并管理应用对象的配置和生命周期，在这个意义上它是一种容器，你可以配置你的每个 bean 如何被创建——基于一个可配置原型（prototype），你的 bean 可以创建一个单独的实例或者每次需要时都生成一个新的实例——以及它们是如何相互关联的。然而，Spring 不应该被混同于传统的重量级的 EJB 容器，它们经常是庞大与笨重的，难以使用。

**框架**

Spring 可以将简单的组件配置、组合成为复杂的应用。在 Spring 中，应用对象被声明式地组合，典型地是在一个 XML 文件里。Spring 也提供了很多基础功能（事务管理、持久化框架集成等等），将应用逻辑的开发留给了你。

总结：Spring就是一个轻量级的控制反转（IoC）和面向切面编程（AOP）的框架！



## 1.3、组成

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200316215023023.png" alt="image-20200316215616542" style="zoom:67%;" />

- Spring Core

	> Core封装包是框架的最基础部分，提供IoC和依赖注入特性。这里的基础概念是 BeanFactory，它提供对 Factory 模式的经典实现来消除对程序性单例模式的需要，并真正地允许你从程序逻辑中分离出依赖关系和配置。

- Spring Context

	> 构建于 Core 封装包基础上的 Context 封装包，提供了一种框架式的对象访问方法，有些像 JNDI 注册器。Context 封装包的特性得自于Beans 封装包，并添加了对国际化（I18N）的支持（例如资源绑定），事件传播，资源装载的方式和 Context 的透明创建，比如说通过Servlet 容器。

- Spring DAO

	> DAO（Data Access Object）提供 JDBC 的抽象层，它可消除冗长的 JDBC 编码和解析数据库厂商特有的错误代码。 并且，JDBC 封装包还提供了一种比编程性更好的声明性事务管理方法，不仅仅是实现了特定接口，而且对所有的 POJOs（plain old Java objects）都适用。

- Spring ORM

	> ORM 封装包提供了常用的 “对象/关系” 映射 APIs 的集成层。 其中包括 JPA、JDO、Hibernate 和 iBatis。利用 ORM 封装包，可以混合使用所有 Spring 提供的特性进行 “对象/关系” 映射，如前边提到的简单声明性事务管理。

- Spring AOP

	> Spring 的 AOP 封装包提供了符合 AOP Alliance 规范的面向切面的编程实现，让你可以定义，例如方法拦截器（method-interceptors）和切点（pointcuts），从逻辑上讲，从而减弱代码的功能耦合，清晰的被分离开。而且，利用 source-level 的元数据功能，还可以将各种行为信息合并到你的代码中。

- Spring Web

	> Spring 中的 Web 包提供了基础的针对 Web 开发的集成特性，例如多方文件上传，利用 Servlet listeners 进行 IOC 容器初始化和针对 Web 的 ApplicationContext。当与 WebWork 或 Struts 一起使用 Spring 时，这个包使 Spring 可与其他框架结合。

- Spring Web MVC

	> Spring 中的 MVC 封装包提供了 Web 应用的 Model-View-Controller（MVC）实现。Spring 的 MVC 框架并不是仅仅提供一种传统的实现，它提供了一种清晰的分离模型，在领域模型代码和 Web Form 之间。并且，还可以借助 Spring 框架的其他特性。



## 1.4、扩展

在Spring的官网有这个介绍：现代化的Java开发！说白了就是基于Spring的开发

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200316215808451.png" style="zoom:80%;" />



- Spring Boot
	- 一个快速开发的脚手架。
	- 基于SpringBoot可以快速的开发单个微服务。
	- 约定大于配置！
- Spring Cloud
	- Spring Cloud是基于SpringBoot实现的

现在很多公司都在使用SpringBoot进行快速开发，学习SpringBoot的前提，需要完全掌握Spring及SpringMVC！



**弊端：Spring发展了太久之后，违背了原来的理念，配置变得十分繁琐。**



# 2、IoC 理论推导（DI）

1. UserDao 接口

	```java
	public interface UserDao {
	    void getUser();
	}
	```

2. UserDaoImpl 实现类

	```java
	public class UserDaoImpl implements UserDao {
	    @Override
	    public void getUser() {
	        System.out.println("默认获取用户的数据");
	    }
	}
	```

3. UserService 业务接口

	```java
	public interface UserService {
	    void getUser();
	}
	```

4. UserServiceImpl 业务实现类

	```java
	public class UserServiceImpl implements UserService {
	    private UserDao userDao = new UserDaoImpl();
	
	    @Override
	    public void getUser() {
	        userDao.getUser();
	    }
	}
	```

5. 测试

	```java
	@Test
	public void test(){
	    UserService service = new UserServiceImpl();
	    service.getUser();
	}
	```



把 Userdao 的实现类增加一个

```java
public class UserDaoMySqlImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("MySql获取用户数据");
    }
}
```

紧接着我们要去使用 MySql 的话 ， 我们就需要去 service 实现类里面修改对应的实现 。

```java
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoMySqlImpl();

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
```



假设，再增加一个 Userdao 的实现类。

```java
public class UserDaoOracleImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("Oracle获取用户数据");
    }
}
```

那么我们要使用 Oracle，又需要去 service 实现类里面修改对应的实现。假设我们的这种需求非常大，这种方式就根本不适用了，每次变动都需要修改大量代码，这种设计的耦合性太高了，牵一发而动全身。



**如何解决？**

可以在需要用到它的地方，不去实现它，而是留出一个接口 ，利用 set，修改代码：

```java
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    // 利用set实现
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
```

现在去测试类里，进行测试：

```java
@Test
public void test(){
    UserServiceImpl service = new UserServiceImpl();
    service.setUserDao( new UserDaoMySqlImpl() );
    service.getUser();
    //那我们现在又想用Oracle去实现呢?
    service.setUserDao( new UserDaoOracleImpl() );
    service.getUser();
}
```



在之前的业务中，用户的需求可能会影响我们原本的代码，我们需要根据用户的需求去修改源代码！如果程序代码量十分大，修改一次的成本十分昂贵！

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200316231355857.png" alt="image-20200316231355857" style="zoom: 67%;" />



使用一个 Set 接口实现，已经发生了革命性的变化！

```java
private UserDao userDao;

//利用set进行动态实现值得注入
public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
}
```

- 之前，程序是主动创建对象！控制权在程序猿手上！
- 使用了 set 注入后，程序不再具有主动性，而是变成了被动的接受对象！

这种思想，从本质上解决了问题，程序猿不用再去管理对象的创建了。系统的耦合性大大降低~，可以更加地专注在业务的实现上！这是 IoC 的原型！

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200316231558028.png" alt="image-20200316231558028" style="zoom:67%;" />



## 2.1、依赖倒置和 DI

假设我们设计一辆汽车：先设计轮子，然后根据轮子大小设计底盘，接着根据底盘设计车身，最后根据车身设计好整个汽车。这里就出现了一个 “依赖” 关系：汽车依赖车身，车身依赖底盘，底盘依赖轮子。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a1e884dbb2b.webp" alt="img" style="zoom:80%;" />

这样的设计看起来没问题，但是可维护性却很低。假设设计完工之后，上司却突然说根据市场需求的变动，要我们把车子的轮子设计都改大一码。这下我们就蛋疼了：因为我们是根据轮子的尺寸设计的底盘，轮子的尺寸一改，底盘的设计就得修改；同样因为我们是根据底盘设计的车身，那么车身也得改，同理汽车设计也得改——整个设计几乎都得改！我们现在换一种思路。我们先设计汽车的大概样子，然后根据汽车的样子来设计车身，根据车身来设计底盘，最后根据底盘来设计轮子。这时候，依赖关系就倒置过来了：轮子依赖底盘， 底盘依赖车身， 车身依赖汽车。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a206b53e40b.png" alt="img" style="zoom:80%;" />

这时候，上司再说要改动轮子的设计，我们就只需要改动轮子的设计，而不需要动底盘，车身，汽车的设计了。这就是依赖倒置原则——把原本的高层建筑依赖底层建筑 “倒置” 过来，变成底层建筑依赖高层建筑。**高层建筑决定需要什么，底层去实现这样的需求，但是高层并不用管底层是怎么实现的**。这样就不会出现前面的 “牵一发动全身” 的情况。



先定义四个 Class，车，车身，底盘，轮胎。然后初始化这辆车，最后跑这辆车。代码结构如下：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a2528185a4f.webp" alt="img" style="zoom:80%;" />

这样，就相当于上面第一个例子，上层建筑依赖下层建筑——每一个类的构造函数都直接调用了底层代码的构造函数。假设我们需要改动一下轮胎（Tire）类，把它的尺寸变成动态的，而不是一直都是 30。我们需要这样改：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a290792f841.webp" alt="img" style="zoom:80%;" />

由于我们修改了轮胎的定义，为了让整个程序正常运行，我们需要做以下改动：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a2a736b0ee8.webp" alt="img" style="zoom:80%;" />

由此我们可以看到，仅仅是为了修改轮胎的构造函数，这种设计却需要修改整个上层所有类的构造函数！在软件工程中，这样的设计几乎是不可维护的——在实际工程项目中，有的类可能会是几千个类的底层，如果每次修改这个类，我们都要修改所有以它作为依赖的类，那软件的维护成本就太高了。所以我们需要把底层类作为参数传入上层类，实现上层类对下层类的 “控制”，用构造方法传递的依赖注入方式重新写车类的定义：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a2c1e041d1f.webp" alt="img" style="zoom:80%;" />

这里我们再把轮胎尺寸变成动态的，同样为了让整个系统顺利运行，我们需要做如下修改：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a2f3b9840d6.webp" alt="img" style="zoom:80%;" />

这里我只需要修改轮胎类就行了，不用修改其他任何上层类。这显然是更容易维护的代码。



## 2.2、IoC 本质和 IoC 容器

**控制反转 IoC（Inversion of Control），是一种设计思想，DI（依赖注入）是实现 IoC 的一种方法**，也有人认为 DI 只是 IoC 的另一种说法。没有IoC 的程序中，我们使用面向对象编程，对象的创建与对象中的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓的控制反转就是：获得依赖对象的方式反转了。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200316232856255.png" alt="image-20200316232856255" style="zoom: 67%;" />



采用 XML 方式配置 Bean 的时候，Bean 的定义信息是和实现分离的，而采用注解的方式可以把二者合为一体，Bean 的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

控制反转是一种通过描述（XML 或注解）并通过第三方去生产或获取特定对象的方式。在 Spring 中实现控制反转的是 IoC 容器，其实现方法是依赖注入（Dependency Injection，DI）。



其实上面的例子中，对车类进行初始化的那段代码发生的地方，就是 IoC 容器。

因为采用了依赖注入，在初始化的过程中就不可避免的会写大量的 new。这里 IoC 容器就解决了这个问题。这个容器可以自动对你的代码进行初始化，你只需要维护一个 Configuration（可以是 xml 可以是一段代码），而不用每次初始化一辆车都要亲手去写那一大段初始化的代码。这是引入 IoC Container 的第一个好处。IoC Container 的第二个好处是：我们在创建实例的时候不需要了解其中的细节。在上面的例子中，我们自己手动创建一个车 instance 时候，是从底层往上层 new 的：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a3a9c6d3ba9.webp" alt="img" style="zoom:80%;" />

这个过程中，我们需要了解整个 Car/Framework/Bottom/Tire 类构造函数是怎么定义的，才能一步一步 new 注入。而 IoC Container 在进行这个工作的时候是反过来的，它先从最上层开始往下找依赖关系，到达最底层之后再往上一步一步 new（有点像深度优先遍历）：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/165d5a3be0740718.webp" alt="img" style="zoom:80%;" />

IoC Container 可以直接隐藏具体的创建实例的细节.



# 3、HelloSpring

## 3.1、导入依赖

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.4.RELEASE</version>
</dependency>
```



## 3.2、编写代码

1. 编写一个Hello实体类

	```java
	public class Hello {
	    private String str;
	
	    public String getStr() {
	        return str;
	    }
	
	    public void setStr(String str) {
	        this.str = str;
	    }
	
	    @Override
	    public String toString() {
	        return "Hello{" +
	                "str='" + str + '\'' +
	                '}';
	    }
	}
	```

2. 编写Spring配置文件，这里命名为beans.xml

	```java
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	        https://www.springframework.org/schema/beans/spring-beans.xsd">
	
	    <!--使用Spring来创建对象，在Spring中这些都称为Bean-->
	    <bean id="hello" class="com.kuang.pojo.Hello">
	        <property name="str" value="Spring"/>
	    </bean>
	
	</beans>
	```

3. 测试

	```java
	public class MyTest {
	    public static void main(String[] args) {
	        //获取Spring的上下文对象！
	        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	        //对象现在都在Spring中管理了，如果要使用，直接去里面取出来就可以！
	        Hello hello = (Hello) context.getBean("hello");
	        System.out.println(hello.toString());
	    }
	}
	```



## 3.3、思考

- Hello对象是谁创建的？
	- 【hello对象是由Spring创建的】
- Hello对象的属性是怎么设置的？
	- 【hello对象的属性是由Spring容器设置的】

这个过程就叫做控制反转：

- 控制：谁来控制对象的创建，传统应用程序的对象由程序本身控制创建的，使用Spring后，对象是由Spring来创建的
- 反转：程序本身不创建对象，由主动的编程变成被动的接收。

依赖注入：就是利用set方法来进行注入的。

**IoC是一种编程思想，由主动的编程变成被动的接收。**



# 4、Ioc创建对象的方式

1. 使用无参构造创建对象，默认！

2. 如果使用有参构造创建对象

	1. 下标赋值

		```xml
		<!--第一种，下标赋值-->
		<bean id="user" class="com.kuang.pojo.User">
		    <constructor-arg index="0" value="琴江"/>
		</bean>
		```

	2. 类型

		```xml
		<!--第二种方式，通过类型创建-->
		<bean id="user" class="com.kuang.pojo.User">
		    <constructor-arg type="java.lang.String" value="qinjiang"/>
		</bean>
		```

	3. 参数名

		```xml
		<!--第三种，直接通过参数名来设置-->
		<bean id="user" class="com.kuang.pojo.User">
		    <constructor-arg name="name" value="琴江"/>
		</bean>
		```

总结：在配置文件加载的时候，容器中管理的对象就已经实例化了！



# 5、Spring配置

## 5.1、别名

```xml
<!--别名，如果添加了别名，就可以使用别名获取到这个对象-->
<alias name="user" alias="userNew"/>
```



## 5.2、Bean的配置

```xml
<!--
    id：bean的唯一标识符，也就是相当于以前学的对象名
    class：bean对象所对应的全限定名：包名+类型
    name：也是别名，而且name可以同时取多个别名
-->
<bean id="userT" class="com.kuang.pojo.UserT" name="user2,u2 u3;u4">
    <property name="name" value="hello"/>
</bean>
```



## 5.3、import

import一般用于团队开发，它可以将多个配置文件，导入合并为一个。

假设，现在项目中有多个人开发，这三个人负责不同的类的开发，不同的类需要注册在不同的bean中，可以利用import将所有人的beans.xml合并为一个总的！

- 张三

- 李四

- 王五

- applicationContext.xml

	```xml
	<import resource="beans.xml"/>
	<import resource="beans2.xml"/>
	<import resource="beans3.xml"/>
	```

使用的时候，直接使用总的就可以了！



# 6、依赖注入（DI）

## 6.1、构造器注入

见 <a href="#4、IoC创建对象的方式" style="text-decoration:none">4、 IoC创建对象的方式</a>



## 6.2、Set方式注入

- 依赖注入：Set注入！
	- 依赖：bean对象的创建依赖于容器！
	- 注入：bean对象中的所有属性，由容器来注入！



【环境搭建】

1. 复杂类型

	```java
	public class Address {
	    private String address;
	
	    public String getAddress() {
	        return address;
	    }
	
	    public void setAddress(String address) {
	        this.address = address;
	    }
	}
	```

2. 真实测试对象

	```java
	public class Student {
	    private String name;
	    private Address address;
	    private String[] books;
	    private List<String> hobbies;
	    private Map<String, String> card;
	    private Set<String> games;
	    private String wife;
	    private Properties info;
	}
	```

3. beans.xml

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	        https://www.springframework.org/schema/beans/spring-beans.xsd">
	
	    <bean id="address" class="com.kuang.pojo.Address">
	        <property name="address" value="西安"/>
	    </bean>
	
	    <bean id="student" class="com.kuang.pojo.Student">
	        <!--第一种，普通值注入，value-->
	        <property name="name" value="琴江"/>
	
	        <!--第二种，Bean注入，ref-->
	        <property name="address" ref="address"/>
	
	        <!--数组-->
	        <property name="books">
	            <array>
	                <value>红楼梦</value>
	                <value>水浒传</value>
	                <value>西游记</value>
	                <value>三国演义</value>
	            </array>
	        </property>
	
	        <!--List-->
	        <property name="hobbies">
	            <list>
	                <value>听歌</value>
	                <value>敲代码</value>
	                <value>看电影</value>
	            </list>
	        </property>
	
	        <!--Map-->
	        <property name="card">
	            <map>
	                <entry key="身份证" value="23424234232343"/>
	                <entry key="银行卡" value="34223424242243"/>
	            </map>
	        </property>
	
	        <!--Set-->
	        <property name="games">
	            <set>
	                <value>LOL</value>
	                <value>COC</value>
	                <value>BOB</value>
	            </set>
	        </property>
	
	        <!--null-->
	        <property name="wife">
	            <null/>
	        </property>
	
	        <!--Properties-->
	        <property name="info">
	            <props>
	                <prop key="学号">1412231</prop>
	                <prop key="性别">男</prop>
	                <prop key="姓名">小明</prop>
	            </props>
	        </property>
	    </bean>
	
	</beans>
	```

4. 测试类

	```java
	public class MyTest {
	    public static void main(String[] args) {
	        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	        Student student = (Student) context.getBean("student");
	        System.out.println(student.toString());
	    }
	}
	```

	

## 6.3、扩展方式注入

可以使用p命名空间和c命名空间进行注入

官方解释：
![image-20200317234553650](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200317234553650.png)

使用：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--p命名空间注入，可以直接注入属性的值：property-->
    <bean id="user" class="com.kuang.pojo.User" p:age="19" p:name="琴江"/>

    <!--c命名空间注入，通过构造器注入：constructor-args-->
    <bean id="user2" class="com.kuang.pojo.User" c:age="12" c:name="小红"/>

</beans>
```

测试：

```java
@Test
public void test2(){
    ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
    User user = context.getBean("user", User.class);
    User user = context.getBean("user2", User.class);
    System.out.println(user);
    System.out.println(user2);
}
```



## 6.4、bean的作用域

![image-20200318144358878](https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200318144358878.png)

1. 单例模式（Spring的默认机制）

	```java
	<bean id="user2" class="com.kuang.pojo.User" c:age="12" c:name="小红" scope="singleton"/>
	```

2. 原型模式：每次从容器中get的时候，都会产生一个新对象！

	```java
	<bean id="user2" class="com.kuang.pojo.User" c:age="12" c:name="小红" scope="prototype"/>
	```

3. 其余的request、session、application这些只能在web开发中使用到！



# 7、bean的装配方式

在Spring中有三种装配的方式

1. 在xml中显示地配置
2. 在java中显示地配置
3. 隐式地自动装配bean



Spring 中的 bean 默认是单例模式，Spring 框架并没有对单例 bean 进行多线程的封装处理。

实际上大部分时候 Spring bean 无状态的（比如 dao 类），所有某种程度上来说 bean 也是安全的，但如果 bean 有状态的话（比如 view model 对象），那就要开发者自己去保证线程安全了，最简单的就是改变 bean 的作用域，把 “singleton” 变更为 “prototype”，这样请求 bean 相当于 new Bean()了，所以就可以保证线程安全了。

- 有状态就是有数据存储功能。
- 无状态就是不会保存数据。



## 7.1、测试

环境搭建：一个人有两个宠物！

1. People类

	```java
	public class People {
	    private Dog dog;
	    private Cat cat;
	    private String name;
	}
	```

2. Dog类

	```java
	public class Dog {
	    public void shout() {
	        System.out.println("wang~");
	    }
	}
	```

3. Cat类

	```java
	public class Cat {
	    public void shout(){
	        System.out.println("miao~");
	    }
	}
	```

4. bean的配置

	```xml
	<bean id="dog" class="com.kuang.pojo.Dog"/>
	<bean id="cat" class="com.kuang.pojo.Cat"/>
	
	<bean id="people" class="com.kuang.pojo.People">
	    <property name="name" value="小明"/>
	    <property name="dog" ref="dog"/>
	    <property name="cat" ref="cat"/>
	</bean>
	```

5. 测试

	```java
	public class MyTest {
	    @Test
	    public void test1(){
	        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	
	        People people = context.getBean("people", People.class);
	        people.getDog().shout();
	        people.getCat().shout();
	    }
	}
	```



## 7.2、ByName

```xml
<bean id="dog" class="com.kuang.pojo.Dog"/>
<bean id="cat" class="com.kuang.pojo.Cat"/>

<!--
    byName：会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid！
    -->
<bean id="people" class="com.kuang.pojo.People" autowire="byName">
    <property name="name" value="小明"/>
</bean>
```

注意：byName的时候，需要保证所有的bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致！



## 7.3、ByType

```xml
<bean class="com.kuang.pojo.Dog"/>
<bean class="com.kuang.pojo.Cat"/>

<!--
    byType：会自动在容器上下文中查找，和自己对象属性类型相同的bean！
    -->
<bean id="people" class="com.kuang.pojo.People" autowire="byType">
    <property name="name" value="小明"/>
</bean>
```

注意：byType的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致！



## 7.4、注解实现

使用注解须要配置注解的支持：

```xml
<context:annotation-config/>
```

完整文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```



**@Autowired**

直接在属性上使用即可！也可以在set方式上使用！

使用@Autowired可以不用编写set方法，前提是这个自动装配的的属性在IoC（Spring）容器中存在。

测试：

```java
public class People {
    //如果显示地定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空！
    @Autowired(required = false)
    private Dog dog;
    @Autowired
    private Cat cat;
    private String name;
}
```

@Autowired默认按byType方式进行装配，当有多个相同的类型时按照byName方式装配（默认获取和属性名相等的id的bean），如果没有符合byName的bean，需要手动使用**@Qualifier(value = "xxx")**去指定唯一的bean对象注入！

```xml
<bean id="dog" class="com.kuang.pojo.Dog"/>
<bean id="cat1" class="com.kuang.pojo.Cat"/>
<bean id="cat2" class="com.kuang.pojo.Cat"/>
<bean id="people" class="com.kuang.pojo.People"/>
```

```java
public class People {
    @Autowired
    private Dog dog;
    @Autowired
    @Qualifier(value = "cat1")
    private Cat cat;
    private String name;
}
```



**@Resource**

和 @Autowired 类似

```xml
<bean id="dog" class="com.kuang.pojo.Dog"/>
<bean id="cat1" class="com.kuang.pojo.Cat"/>
<bean id="cat2" class="com.kuang.pojo.Cat"/>
<bean id="people" class="com.kuang.pojo.People"/>
```

```java
public class People {
    @Resource
    private Dog dog;
    @Resource(name = "cat1")
    private Cat cat;
    private String name;
}
```



关于@Autowired和@Resource：

- 都是用来自动装配的，都可以放在属性字段上。
- @Autowired默认通过byType方式实现，而且必须要求这个对象存在！
- @Resource默认通过byName方式实现，如果找不到名字，则通过byType方式实现。



扩展：

**@Nullable**：字段标记了这个注解，说明这个字段可以为空。

```java
public People(@Nullable String name) {
    this.name = name;
}
```



# 8、使用注解开发

在Spring4之后，要使用注解开发，必须要保证aop的包已经导入

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200318210404506.png" alt="image-20200318210404506" style="zoom:80%;" />



1. bean

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	        https://www.springframework.org/schema/beans/spring-beans.xsd
	        http://www.springframework.org/schema/context
	        https://www.springframework.org/schema/context/spring-context.xsd">
	
	    <!--指定要扫描的包，这个包下的注解就会生效-->
	    <context:component-scan base-package="com.kuang.pojo"/>
	
	</beans>
	```

	```java
	//等价于 <bean id="user" class="com.kuang.pojo.User"/>
	@Component
	public class User {
	    public String name = "琴江";
	}
	```

2. 属性如何注入

	```java
	@Component
	public class User {
	    //相当于 <property name="name" value="hello"/>
	    @Value("hello")
	    public String name;
	}
	```

3. 衍生的注解
	@component有几个衍生的注解，在web开发中，会按照MVC三层架构分层！

	- dao	【@Repository】
	- service	【@Service】 
	- controller	【@Controller】

	这四个注解功能都是一样的，都是代表将某个类注册到Spring中，装配Bean。

4. 自动装配
	<a href="#7.4、使用注解实现自动装配" style="text-decoration:none">见 7.4、使用注解实现自动装配</a>

5. 作用域

	```java
	//等价于 <bean id="user" class="com.kuang.pojo.User"/>
	@Component
	@Scope("singleton")
	public class User {
	    //相当于 <property name="name" value="hello"/>
	    @Value("hello")
	    public String name;
	}
	```

6. 小结
	xml与注解：

	- xml更加万能，适用于任何场合！维护简单方便
	- 注解不是自己类使用不了，维护相对复杂！

	xml与注解最佳实践：

	- xml用来管理bean
	- 注解只负责完成属性的注入



# 9、使用Java的方式配置Spring

可以不使用Spring的xml配置，全权交给Java来做！

JavaConfig是Spring的一个子项目，在Spring4之后，它成为了一个核心功能！



**测试类：**

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(KuangConfig.class);
        User user = context.getBean("user", User.class);

        System.out.println(user.getName());
    }
}
```



**方式1**：

实体类：

```java
public class User {
    @Value("hello")
    private String name;

    public String getName() {
        return name;
    }
}
```

配置文件

```java
@Configuration
public class KuangConfig {
    @Bean
    public User user(){
        return new User();
    }
}
```

此方法创建的Bean Id为方法名



**方式2**：

实体类

```java
@Component
public class User {
    @Value("hello")
    private String name;

    public String getName() {
        return name;
    }
}
```

配置文件

```java
@ComponentScan("com.kuang.pojo")
public class KuangConfig {}
```

此方法创建的BeanId为类名，也可以在@Component("")中设置BeanId



# 10、代理模式

为什么要学代理模式？因为这就是SpringAOP的底层！

代理模式的分类：

- 静态代理
- 动态代理



<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200319123439283.png" alt="image-20200319123439283" style="zoom: 67%;" />



## 10.1、静态代理

角色分析：

- 抽象角色：一般会使用接口或抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，一般会做一些附属操作
- 客户：访问代理对象的人



有一个打印机的类

```java
public class Printer {
    public void print(){
        System.out.println("打印！");
    }
}
```

我想在打印之前先记录一下日志怎么做？最简单的方法：在打印的功能前面直接加上记录日志的功能。

```java
public class Printer {
    public void print(){
        System.out.println("记录日志！");
        System.out.println("打印！");
    }
}
```

看上去好像没有问题，但是我们修改了打印机的源代码，**破坏了面向对象的开闭原则**，有可能影响到其它功能。怎么解决呢？很容易可以想到，既然不能修改原来的代码，那我新建一个类吧。

```java
public class LogPrinter extends Printer {
    public void print(){
        System.out.println("记录日志！");
        System.out.println("打印！");
    }
}
```

这个类继承了打印机的类，**重写了打印机的print方法，提供了记录日志的功能**，以后需要打印机的时候使用这个类就好。问题似乎得到了解决，我们可以在这个解决方案的基础上进一步的优化：

先抽象出一个接口：

```java
public interface IPrinter {
    void print();
}
```

打印机类实现这个接口:

```java
public class Printer implements IPrinter {
       public void print(){
       System.out.println("打印！");
    }
}
```

**创建打印机代理类**也实现该接口，在构造函数中将打印机对象传进去，实现接口的打印方法时调用打印机对象的打印方法并在前面加上记录日志的功能:

```java
public class PrinterProxy implements IPrinter {
    private IPrinter printer;
    public PrinterProxy(){
        this.printer = new printer();
    }
    @Override
    public void print() {
        System.out.println("记录日志");
        printer.print();
    }
}
```

试一把吧：

```java
public class Test {
    public static void main(String[] args) {
        PrinterProxy proxy = new PrinterProxy();
        proxy.print();
    }
}
```

结果出来了：

```
记录日志
打印
```

以后就可以直接实例化PrinterProxy对象调用它的打印方法了，这就是静态代理模式，通过抽象出接口让程序的扩展性和灵活性更高了。

但是，如果打印机类中还有别的方法，也需要加上记录日志的功能，就不得不将记录日志的功能写n遍。进一步如果还有电视机，电冰箱的类里面的所有方法也需要加上记录日志的功能，那要重复的地方就更多了。



代理模式的好处：

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共业务就交给代理角色！实现了业务的分工！
- 公共业务发生扩展的时候，方便集中管理！

缺点：一个真实角色就会产生一个代理角色；代码量会翻倍，开发效率会变低~



## 10.2、动态代理

- 动态代理和静态代理角色一样
- 动态代理的代理类是动态生成的，不是直接写好的！
- 动态代理分为两大类：基于接口的动态代理；基于类的动态代理
	- 基于接口--- JDK 动态代理
	- 基于类：cglib
	- java字节码实现：javasist



要想不重复写记录日志的功能，针对每一个接口实现一个代理类的做法肯定不可行了，可不可以让这些代理类的对象**自动生成**呢？Jdk 提供了**invocationHandler **接口和 **Proxy** 类，借助这两个工具可以达到我们想要的效果。

invocationHandler 接口上场：

```java
//Object proxy:被代理的对象 
//Method method:要调用的方法 
//Object[] args:方法调用时所需要参数 
public interface InvocationHandler {
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
```

接口里只有一个方法 invoke，这个方法非常重要。

Proxy类上场，它里面有一个很重要的方法 newProxyInstance：

```java
//CLassLoader loader:被代理对象的类加载器 
//Class<?> interfaces:被代理类全部的接口 
//InvocationHandler h:实现InvocationHandler接口的对象 
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException {}
```

调用 Proxy 的 newProxyInstance 方法可以生成代理对象。



接口IPrinter 和 该接口的实现类 Printer的代码同前。

实现一个类，该类用来创建代理对象，它实现了InvocationHandler接口：

```java
public class PrintHandler implements InvocationHandler {
    //被代理的对象
    private Object object;

    public Object newProxyInstance(Object targetObject) {
        this.object = targetObject;
        //object.getClass().getClassLoader()：被代理对象的类加载器
        //object.getClass().getInterfaces()：被代理对象的实现接口
        //this：当前对象，该对象实现了 InvocationHandler 接口，所以有invoke方法，通过invoke方法可以调用被代理对象的方法
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    //该方法在代理对象调用方法时调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("记录日志！");
        return method.invoke(object, args);
    }
}
```

invoke() 方法就是生成的代理类中的方法被调用时会去自动调用的方法，可以看到在这个方法中调用了被代理对象的方法: method.invoke(targetObject, args)；可以在这里加上需要的业务逻辑，比如调用方法前记录日志功能。

测试：

```java
public class Test {
    public static void main(String[] args) {
        ProxyHandler proxyHandler = new ProxyHandler();
        IPrinter printer = (IPrinter) proxyHandler.newProxyInstance(new Printer());
        printer.print();
    }
}
```

打印结果 ：

```
记录日志
打印
```



# 11、AOP

## 11.1、什么是AOP

AOP（Aspect-Oriented Programming，面向方面编程），通过预编译方式和运行期间动态代理实现程序功能的统一维护的一种技术，可以说是OOP（Object-Oriented Programing，面向对象编程）的补充和完善。OOP引入封装、继承和多态性等概念来建立一种对象层次结构，用以模拟公共行为的一个集合。当我们需要为分散的对象引入公共行为的时候，OOP则显得无能为力。也就是说，OOP允许你定义从上到下的关系，但并不适合定义从左到右的关系。例如日志功能。日志代码往往水平地散布在所有对象层次中，而与它所散布到的对象的核心功能毫无关系。对于其他类型的代码，如安全性、异常处理和透明的持续性也是如此。这种散布在各处的无关的代码被称为横切（cross-cutting）代码，在OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。

而AOP技术则恰恰相反，它利用一种称为“横切”的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可重用模块，并将其名为“Aspect”，即切面。所谓“切面”，简单地说，就是将那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可操作性和可维护性。AOP代表的是一个横向的关系，如果说“对象”是一个空心的圆柱体，其中封装的是对象的属性和行为；那么面向方面编程的方法，就仿佛一把利刃，将这些空心圆柱体剖开，以获得其内部的消息。而剖开的切面，也就是所谓的“切面”了。然后它又以巧夺天功的妙手将这些剖开的切面复原，不留痕迹。

使用“横切”技术，AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多处，而各处都基本相似。比如权限认证、日志、事务处理。Aop 的作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。正如Avanade公司的高级方案构架师Adam Magee所说，AOP的核心思想就是 “将应用程序中的商业逻辑同对其提供支持的通用服务进行分离。”

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200320011106852.png" alt="image-20200320011106852" style="zoom: 67%;" />



## 11.2、AOP在Spring中的作用

==提供声明式事务；允许用户自定义切面==

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志、安全、缓存、事务等等...
- 切面（ASPECT）：横切关注点被模块化的特殊对象。即，它是一个类。
- 通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。
- 目标（Target）：被通知对象。
- 代理（Proxy）：向目标对象应用通知之后创建的对象。
- 切入点（PointCut）：切面通知执行的 “地点” 的定义。
- 连接点（JointPoint）：与切入点匹配的执行点。

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200320114822252.png" alt="image-20200320114822252" style="zoom: 67%;" />



SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice：

<img src="https://orichalcos-typora-img.oss-cn-shanghai.aliyuncs.com/typora-img/image-20200320115059707.png" alt="image-20200320115059707" style="zoom:67%;" />

即AOP在不改变原有代码的情况下，去增加新的功能。



## 11.3、使用Spring实现AOP

使用AOP织入，需要导入一个依赖包！

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.5</version>
</dependency>
```



**Execution**

在使用Spring框架配置AOP的时候，不管是通过XML配置文件还是注解的方式都需要定义pointcut “切入点” 
例如定义切入点表达式 `execution(* com.samp.service.impl..*.*(..))`
ecexution()是最常用的切点函数，其语法如下所示：
整个表达式可以分为五个部分：

- execution()：表达式主体
- 第一个 * 号：表示返回类型，* 号表示所有的类型。
- 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包。
- 第二个 * 号：表示类型，* 号表示所有的类。
- *(..)：最后这星号表示方法名， *号表示所有的方法，后面括号里面表示方法的参数，两个句点表示任何参数。



**准备：**

UserService：

```java
public interface UserService {
    public void add();

    public void delete();

    public void update();

    public void query();
}
```

UserServiceImpl:

```java
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除了一个用户");
    }

    @Override
    public void update() {
        System.out.println("修改了一个用户");
    }

    @Override
    public void query() {
        System.out.println("查询了一个用户");
    }
}
```

测试类：

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService useService = context.getBean("userService", UserService.class);
        useService.add();
    }
}
```



**方式一：Spring的接口**

Log:

```java
public class Log implements MethodBeforeAdvice {
    //method：要执行的目标对象的方法
    //args：参数
    //target：目标对象
    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "的" + method.getName() + "被执行了");
    }
}
```

AfterLog:

```java
public class AfterLog implements AfterReturningAdvice {
    //returnValue：返回值
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("执行了" + method.getName() + "方法，返回结果为：" + returnValue);
    }
}
```

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userService" class="com.kuang.service.UserServiceImpl"/>
    <bean id="log" class="com.kuang.log.Log"/>
    <bean id="afterLog" class="com.kuang.log.AfterLog"/>

    <!--方式一：使用原生Spring API-->
    <!--配置aop-->
    <aop:config>
        <aop:pointcut id="pointcut" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>

        <!--执行环绕增加-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>

</beans>
```



**方式二：自定义类**

DiyPointCut：

```java
public class DiyPointCut {
    public void before() {
        System.out.println("方法执行前");
    }

    public void after() {
        System.out.println("方法执行后");
    }
}
```

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userService" class="com.kuang.service.UserServiceImpl"/>

    <!--方式二：自定义类-->
    <bean id="diy" class="com.kuang.diy.DiyPointCut"/>

    <aop:config>
        <!--自定义切面， ref 要引用的类-->
        <aop:aspect ref="diy">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>
            <!--通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>

</beans>
```



**方式三：注解**

AnnotationPointCut：

```java
//方法三：使用注解方式实现AOP
@Aspect
public class AnnotationPointCut {
    @Before("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("===方法执行前===");
    }

    @After("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("===方法执行后===");
    }

    @Around("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前");
        proceedingJoinPoint.proceed();
        System.out.println("环绕后");
    }
}
```

applicationContext.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userService" class="com.kuang.service.UserServiceImpl"/>

    <!--方式三-->
    <bean id="annotationPointCut" class="com.kuang.diy.AnnotationPointCut"/>
    
    <!--开启注解支持-->
    <aop:aspectj-autoproxy/>

</beans>
```



# 12、整合Mybatis

步骤：

1. 导入相关jar
	- junit
	- mybatis
	- mysql-connector
	- spring-webmvc
	- spring-jdbc
	- aspectjweaver
	- mybatis-spring
	- lombok
2. 编写配置文件
3. 测试



## 12.1、回忆Mybatis

1. 编写实体类

	```java
	@Data
	public class User {
	    private int id;
	    private String name;
	    private String pwd;
	}
	```

2. 编写核心配置文件

	```xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration
	        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
	    <typeAliases>
	        <package name="com.kuang.pojo"/>
	    </typeAliases>
	
	    <environments default="development">
	        <environment id="development">
	            <transactionManager type="JDBC"/>
	            <dataSource type="POOLED">
	                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
	                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=true&amp;serverTimezone=Asia/Shanghai"/>
	                <property name="username" value="root"/>
	                <property name="password" value="root"/>
	            </dataSource>
	        </environment>
	    </environments>
	    
	    <mappers>
	        <mapper class="com.kuang.mapper.UserMapper"/>
	    </mappers>
	</configuration>
	```

3. 编写接口

	```java
	public interface UserMapper {
	    public List<User> selectUser();
	}
	```

4. 编写Mapper.xml

	```xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
	        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.kuang.mapper.UserMapper">
	    <select id="selectUser" resultType="user">
	        select * from mybatis.user;
	    </select>
	</mapper>
	```

5. 测试

	```java
	public class MyTest {
	    @Test
	    public void test() throws IOException {
	        String resources = "mybatis-config.xml";
	        InputStream inputStream = Resources.getResourceAsStream(resources);
	        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	        SqlSession sqlSession = sqlSessionFactory.openSession();
	
	        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
	        List<User> userList = mapper.selectUser();
	
	        for (User user : userList) {
	            System.out.println(user);
	        }
	    }
	}
	```



## 12.2、Mybatis-spring

**方式一：**

实体类：User.java

```java
@Data
public class User {
    private int id;
    private String name;
    private String pwd;
}
```

接口：UserMapper.java

```java
public interface UserMapper {
    public List<User> selectUser();
}
```

Mapper.xml：UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.kuang.mapper.UserMapper">
    <select id="selectUser" resultType="user">
        select * from mybatis.user;
    </select>
</mapper>
```

配置Mybatis：spring-dao.xml 和 mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        DataSource：使用Spring的数据源替换Mybatis的配置
        这里使用Spring提供的JDBC：
    -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url"
                  value="jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=true&amp;serverTimezone=Asia/Shanghai"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>

    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--绑定Mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>

    <!--SqlSessionTemplate：就是以前使用的sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能通过构造器注入sqlSessionFactory，因为它没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
</beans>
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <package name="com.kuang.pojo"/>
    </typeAliases>

    <mappers>
        <mapper class="com.kuang.mapper.UserMapper"/>
    </mappers>
</configuration>
```

编写实现类：UserMapperImpl.java

```java
public class UserMapperImpl implements UserMapper {
    //原来所有的操作，都使用sqlSession来执行，现在都使用sqlSessionTemplate
    private SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<User> selectUser() {
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

配置applicationContext.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <import resource="spring-dao.xml"/>

    <bean id="userMapper" class="com.kuang.mapper.UserMapperImpl">
        <property name="sqlSessionTemplate" ref="sqlSession"/>
    </bean>
</beans>
```

测试：

```java
public class MyTest {
    @Test
    public void test() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        for (User user : userMapper.selectUser()) {
            System.out.println(user);
        }
    }
}
```



**方式二**

修改实现类：UserMapperImpl.java

```java
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<User> selectUser() {
        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
```

修改spring-dao.xml：SqlSessionTemplate不需要，删了。

重新配置applicationContext.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <import resource="spring-dao.xml"/>
    
    <bean id="userMapper" class="com.kuang.mapper.UserMapperImpl">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>
</beans>
```



# 13、事务

- 编程式事务管理，在代码中调用 commit()、rollback()等事务管理相关的方法
- 基于 TransactionProxyFactoryBean 的声明式事务管理
- 基于注解 @Transactional 的声明式事务管理
- 基于 Aspectj AOP 配置（注解）事务



## 13.1、编程式事务 

**编程式事务管理，在代码中调用 commit()、rollback()等事务管理相关的方法**

maven pom.xml 文件

```xml
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-beans</artifactId>
	<version>4.2.4.RELEASE</version>
</dependency>
 
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context</artifactId>
	<version>4.2.4.RELEASE</version>
</dependency>
 
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-aop</artifactId>
	<version>4.2.4.RELEASE</version>
</dependency>
 
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-tx</artifactId>
	<version>4.2.4.RELEASE</version>
</dependency>
 
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-jdbc</artifactId>
	<version>4.2.4.RELEASE</version>
</dependency>
 
<!-- mysql驱动 -->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.18</version>
</dependency>
```

编程式事务管理，可以通过 java.sql.Connection 控制事务。spring 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans.xsd
	    http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
        
	<bean id="driver" class="com.mysql.jdbc.Driver"></bean>
 
	<bean id="datasource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
		<constructor-arg index="0" name="driver" ref="driver" />
		<constructor-arg index="1">
			<value>jdbc:mysql://localhost:3306/test</value>
		</constructor-arg>
		<constructor-arg index="2">
			<value>root</value>
		</constructor-arg>
		<constructor-arg index="3">
			<value>root</value>
		</constructor-arg>
	</bean>
</beans>
```

测试代码

```java
public class TransactionTest {
	public static void main(String[] args) throws Exception {
		testManualTransaction();//测试函数式控制事务
	}
	
	private static void testManualTransaction() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring_transaction.xml");
		DataSource ds = (DataSource)context.getBean("datasource");
		Connection conn = ds.getConnection();
		try {
			initTable(conn);//初始化表
			conn.setAutoCommit(false);//设置不自动提交事务
			queryUsers(conn);//查询打印用户表
			deleteUser(conn);//删除 id=1 用户
			conn.rollback();//回滚
			queryUsers(conn);//查询打印用户表
		} finally {
			conn.close();
		}
	}
 
	private static void initTable(Connection conn) throws SQLException {
		conn.createStatement().execute("drop table if exists user");
		conn.createStatement().execute("create table user(id int, username varchar(60)) ENGINE=InnoDB DEFAULT CHARSET=utf8 ");//是否支持事务与数据库引擎有关，此处删除 ENGINE=InnoDB DEFAULT CHARSET=utf8 可能不支持事务
		conn.createStatement().execute("insert into user values(1, 'user1')");
		conn.createStatement().execute("insert into user values(2, 'user2')");
	}
 
	private static void deleteUser(Connection conn) throws SQLException {
		conn.createStatement().execute("delete from user where id = 1");
	}
 
	private static void queryUsers(Connection conn) throws SQLException {
		Statement st = conn.createStatement();
		st.execute("select * from user");
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			System.out.print(rs.getString("id"));
			System.out.print(" ");
			System.out.print(rs.getString("username"));
			System.out.println();
		}
	}
}
```

删除用户语句回滚，打印出两个用户

```
1 user1
2 user2
1 user1
2 user2
```



## 13.2、声明式事务 1

**基于 TransactionProxyFactoryBean 的声明式事务管理**

新增 UserDao 接口

```java
public interface UserDao {
 
	/**
	 * 查询用户
	 * @return
	 */
	public List<Map<String, Object>> getUsers();
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int deleteUser(int id);	
}
```

新增 UserDao 实现

```java
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	/**
	 * 查询用户
	 * @return
	 */
	public List<Map<String, Object>> getUsers() {
		String sql = "select * from user";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int deleteUser(int id){
		String sql = "delete from user where id = " + id;
		int result = this.getJdbcTemplate().update(sql);
		if (id == 1) {
			throw new RuntimeException();
		}
		return result;
	}
}
```

修改 spring 配置文件，添加事务管理器 DataSourceTransactionManager 和事务代理类 TransactionProxyFactoryBean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans.xsd
	    http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
        
	<bean id="driver" class="com.mysql.jdbc.Driver"></bean>
	
	<bean id="datasource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
		<constructor-arg index="0" name="driver" ref="driver" />
		<constructor-arg index="1">
			<value>jdbc:mysql://localhost:3306/test</value>
		</constructor-arg>
		<constructor-arg index="2">
			<value>root</value>
		</constructor-arg>
		<constructor-arg index="3">
			<value>root</value>
		</constructor-arg>
	</bean>
	
	<bean id="userDao" class="constxiong.interview.transaction.UserDaoImpl">
		<property name="dataSource" ref="datasource"></property>
	</bean>
	
	<!-- 事务管理器 -->
	<bean id="tracnsactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="datasource"></property>
	</bean>
	
	<bean id="userProxy" class="org.springframework.transaction.interceptor.TransactionProxyFactoryBean">
		<property name="transactionManager" ref="tracnsactionManager"></property>
		<property name="target" ref="userDao"></property>
		<property name="transactionAttributes">
			<props>
				<!-- 主要 key 是方法   
					ISOLATION_DEFAULT  事务的隔离级别
					PROPAGATION_REQUIRED  传播行为
				-->
				<!-- -Exception 表示发生指定异常回滚，+Exception 表示发生指定异常提交 -->
				<prop key="deleteUser">-java.lang.RuntimeException</prop>
			</props>
		</property>
	</bean>
</beans>
```

测试代码

```java
public class TransactionTest {
	static ApplicationContext context = new ClassPathXmlApplicationContext("spring_transaction.xml");
	
	public static void main(String[] args) throws Exception {
		testUseTransactionProxy(); //测试使用 spring TransactionProxyFactoryBean
	}
	
	private static void testUseTransactionProxy() {
		final UserDao userDao = (UserDao)context.getBean("userProxy");
		printUsers(userDao);//打印用户
		userDao.deleteUser(1);//删除 id=1 用户
	}
 
	private static void printUsers(UserDao userDao) {
		for (Map<String, Object> user : userDao.getUsers()) {
			System.out.println(user);
		}
	}
}
```

结果输出

```
{id=1, username=user1}
{id=2, username=user2}
Exception in thread "main" java.lang.RuntimeException
	at constxiong.interview.transaction.UserDaoImpl.deleteUser(UserDaoImpl.java:28)...
```



## 13.3、声明式事务 2

**基于注解 @Transactional 的声明式事务管理**

UserDaoImpl 删除用户方法添加注解 @Transactional(rollbackFor=RuntimeException.class) 出现 RuntimeException 回滚

```java
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {	
	/**
	 * 查询用户
	 * @return
	 */
	public List<Map<String, Object>> getUsers() {
		String sql = "select * from user";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@Transactional(rollbackFor=RuntimeException.class)
	public int deleteUser(int id){
		String sql = "delete from user where id = " + id;
		int result = this.getJdbcTemplate().update(sql);
		if (id == 1) {
			throw new RuntimeException();
		}
		return result;
	}
}
```

修改 spring 配置文件，开启 spring 的事务注解能力

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx-3.2.xsd
	    http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
        
	<bean id="driver" class="com.mysql.jdbc.Driver"></bean>
	
	<bean id="datasource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
		<constructor-arg index="0" name="driver" ref="driver" />
		<constructor-arg index="1">
			<value>jdbc:mysql://localhost:3306/test</value>
		</constructor-arg>
		<constructor-arg index="2">
			<value>root</value>
		</constructor-arg>
		<constructor-arg index="3">
			<value>root</value>
		</constructor-arg>
	</bean>
	
	<bean id="userDao" class="constxiong.interview.transaction.UserDaoImpl">
		<property name="dataSource" ref="datasource"></property>
	</bean>
	
	<!-- 事务管理器 -->
	<bean id="tracnsactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="datasource"></property>
	</bean>
	
	<!-- 启用事务注解 -->
	<tx:annotation-driven transaction-manager="tracnsactionManager"/>
	
</beans>
```

测试代码

```java
public class TransactionTest {
	static ApplicationContext context = new ClassPathXmlApplicationContext("spring_transaction.xml");
	
	public static void main(String[] args) throws Exception {
		testAnnotationTransaction();
	}
	
	
	private static void testAnnotationTransaction() {
		UserDao userDao = (UserDao)context.getBean("userDao");
		printUsers(userDao);
		userDao.deleteUser(1);
	}
 
 
	private static void printUsers(UserDao userDao) {
		for (Map<String, Object> user : userDao.getUsers()) {
			System.out.println(user);
		}
	}
}
```

输出结果

```
{id=1, username=user1}
{id=2, username=user2}
Exception in thread "main" java.lang.RuntimeException
	at constxiong.interview.transaction.UserDaoImpl.deleteUser(UserDaoImpl.java:30)...
```



## 13.4、基于注解的事务

**基于 Aspectj AOP 配置（注解）事务**

maven pom.xml 添加 Aspectj 的支持

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.8.13</version>
</dependency>
```

去除 UserDaoImpl 注解@Transactional(rollbackFor=RuntimeException.class)

```java
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {	
	/**
	 * 查询用户
	 * @return
	 */
	public List<Map<String, Object>> getUsers() {
		String sql = "select * from user";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int deleteUser(int id){
		String sql = "delete from user where id = " + id;
		int result = this.getJdbcTemplate().update(sql);
		if (id == 1) {
			throw new RuntimeException();
		}
		return result;
	}
}
```

修改 spring 配置文件，织入切面

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx-3.2.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd
	    http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
        
	<bean id="driver" class="com.mysql.jdbc.Driver"></bean>
	
	<bean id="datasource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
		<constructor-arg index="0" name="driver" ref="driver" />
		<constructor-arg index="1">
			<value>jdbc:mysql://localhost:3306/test</value>
		</constructor-arg>
		<constructor-arg index="2">
			<value>root</value>
		</constructor-arg>
		<constructor-arg index="3">
			<value>root</value>
		</constructor-arg>
	</bean>
	
	<bean id="userDao" class="constxiong.interview.transaction.UserDaoImpl">
		<property name="dataSource" ref="datasource"></property>
	</bean>
	
	<!-- 事务管理器 -->
	<bean id="tracnsactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="datasource"></property>
	</bean>
	
	<tx:advice id="txAdvice" transaction-manager="tracnsactionManager">
		<tx:attributes>
			<!-- 为连接点指定事务属性 -->
			<tx:method name="deleteUser" rollback-for="java.lang.RuntimeException"/>
		</tx:attributes>
	</tx:advice>
	
	<aop:config>
		<!-- 切入点配置 -->
		<aop:pointcut id="point" expression="execution(* *constxiong.interview.transaction.UserDao.deleteUser(..))" />
		<aop:advisor advice-ref="txAdvice" pointcut-ref="point"/>
	</aop:config>
	
</beans>
```

测试代码

```java
public class TransactionTest {
	static ApplicationContext context = new ClassPathXmlApplicationContext("spring_transaction.xml");
	
	public static void main(String[] args) throws Exception {
		testAspectjTransaction();
	}
	
	private static void testAspectjTransaction() {
		UserDao userDao = (UserDao)context.getBean("userDao");
		printUsers(userDao);
		userDao.deleteUser(1);
	}
 
	private static void printUsers(UserDao userDao) {
		for (Map<String, Object> user : userDao.getUsers()) {
			System.out.println(user);
		}
    } 
}
```

输出结果

```
{id=1, username=user1}
{id=2, username=user2}
Exception in thread "main" java.lang.RuntimeException
	at constxiong.interview.transaction.UserDaoImpl.deleteUser(UserDaoImpl.java:28)...
```



## 13.5、事务的传播属性

- **PROPAGATION_REQUIRED = 0**

  如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。

  - 无父事务时：子事务作为独立事务执行
  - 有父事务时：子事务中的操作串入父事务中执行，并且一起提交，一个操作失败全部回滚

- **PROPAGATION_SUPPORTS = 1**

  如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。

  - 无父事务时：以非事务方式执行
  - 有父事务时：加入父事务执行，等同于 PROPAGATION_REQUIRED

- **PROPAGATION_MANDATORY = 2**

  如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。

  - 无父事务时：抛出异常
  - 有父事务时：加入父事务执行，等同于 PROPAGATION_REQUIRED

- **PROPAGATION_REQUIRES_NEW = 3**

  创建一个新的事务，如果当前存在事务，则把当前事务挂起。

  - 挂起（Suspend）：通知 TransactionManager 不再检查某事务的状态，直到 Resume
    AbstractPlatformTransactionManager. handleExistingTransaction()
  - 无父事务时：子事务新建事务作为独立事务执行
  - 有父事务时：子事务新建事务作为独立事务执行，独立提交

  ```
  T1{
  　　O(A);
  　　T2{
  　　　　O(B);
  　　　　O(C);
  　　};
  　　O(D);
  };
  ```

  子事务 T2 不受父事务 T1 回滚的影响，但仍作为 T1 的子逻辑，O(D) 失败，O(A) 回滚，T2 中的事务不回滚；

  T2 失败回滚，T1 捕获异常后，可以选择提交或回滚，未捕获异常，同 T2 一起回滚。

- **PROPAGATION_NOT_SUPPORTED = 4**

  以非事务方式运行，如果当前存在事务，则把当前事务挂起。

  - 无父事务时：以非事务方式执行
  - 有父事务时：挂起父事务，自己按照无事务方式运行

  子事务自身无回滚，出现异常若向上抛，可能导致父事务回滚，父事务回滚时，不会影响子事务

- **PROPAGATION_NEVER = 5**

  以非事务方式运行，如果当前存在事务，则抛出异常。

  - 无父事务时：以非事务方式执行
  - 有父事务时：抛出异常（若不处理会导致父事务回滚）

- **PROPAGATION_NESTED = 6**

  如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行。

  - 无父事务时：创建独立事务，等同于PROPAGATION_REQUIRED
  - 有父事务时：嵌套在父事务之内
  - 子事务依赖父事务：子事务于父事务提交时提交；父事务回滚，子事务也回滚
  - Savepoint：子事务回滚时，父事务不回滚

