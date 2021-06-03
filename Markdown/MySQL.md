# 1、SQL简介

SQL是用于访问和处理数据库的标准计算机语言。

## 1.1、SQL是什么？

- SQL，指结构化查询语言，全称是Structured Query Language
- SQL可以让我们访问和处理数据库
- SQL是一种ANSI（American National Standards Institute）标准的计算机语言



## 1.2、SQL能做什么？

- SQL面向数据库执行查询
- SQL可从数据库取回数据
- SQL可在数据库中插入新的记录
- SQL可更新数据库中的数据
- SQL可从数据库删除记录
- SQL可创建新的数据库
- SQL可在数据库中创建新表
- SQL可在数据库中创建存储过程
- SQL可在数据库中创建视图
- SQL可以设置表、存储过程和视图的权限



## 1.3、SQL分类

**DDL（数据定义语言）**

- 数据定义语言（Data Definition Language）
- 用来定义数据库的对象，如数据表、视图、索引等
- CREATFE DROP ALTER

**DML（数据操纵语言）**

- 数据处理语言（Data Manipulation Language）
- 在数据库中更新，增加和删除记录
- UPDATE DELETE INSERT

**DCL（数据控制语言）**

- 数据控制语言（Data Control Language）
- 指用于设置用户权限和控制事务语句

**DQL（数据查询语言）**

- 数据查询语言（Data Query Language）
- SELECT

# 2、数据库

MySQL是最流行的关系型数据库管理系统，在WEB应用方面MySQL是最好的RDBMS(Relational Database Management System：关系数据库管理系统)应用软件之一。

## 2.1、什么是数据库？

数据库（Database）是按照数据结构来组织、存储和管理数据的仓库。

每个数据库都有一个或多个不同的API用于创建，访问，管理，搜索和复制所保存的数据。

我们也可以将数据存储在文件中，但是在文件中读写数据速度相对较慢。

所以，现在我们使用关系型数据库管理系统（RDBMS）来存储和管理的大数据量。所谓的关系型数据库，是建立在关系模型基础上的数据库，借助于集合代数等数学概念和方法来处理数据库中的数据。

RDBMS即关系数据库管理系统（Relational Database Management System）的特点：

- 数据以表的的形式出现
- 每行为各种记录名称
- 每列为记录名称所对应的数据域
- 许多的行和列组成一张表单
- 若干的表单组成database



## 2.2、RDBMS术语

- 数据库：数据库是一些关联表的集合
- 数据表：表是数据库的矩阵。在一个数据库中的表看起来像一个简单的电子表格
- 列：一列（数据元素）包含了相同类型的数据，例如邮政编码的数据
- 行：一行（=元组，或记录）是一组相关的数据，例如一条用户订阅的数据
- 冗余：存储两倍数据，冗余降低了性能，但提高了数据的安全性
- 主键：主键是唯一的。一个数据表中只能包含一个主键。你可以使用主键来查询数据
- 外键：外键用于关联两个表
- 复合键：复合键（组合键）将多个列作为一个索引建，一般用于复合索引
- 索引：使用索引可快速访问数据库表中的特定信息。索引是数据库表中一列或多列的值进行排序的一种结构。类似于书籍的目录
- 参照完整性：参照的完整性要求关系中不允许引用不存在的实体。与实体完整性是关系模型必须满足的完整性约束条件，目的是保证数据的一致性

MySQL为关系型数据库（Relational Database Management System），这种所谓的“关系型”可以理解为“表格”的概念，一个关系型数据库由一个或数个表格组成，如下图所示的一个表格：

<img src="../Images/MySQL/image-20200528004923567.png" alt="image-20200528004923567" style="zoom:80%;" />

- 表头（header）：每一列的名称；
- 列（col）：具有相同数据类型的数据的集合；
- 行（row）：每一行用来描述某条记录的具体信息；
- 值（VALUE）：行的具体信息，每个值必须与该列的数据类型相同；
- 键（KEY）：键的值在当前列具有唯一性。



## 2.3、创建数据库

使用CREATE命令创建数据库，语法如下：

```mysql
CREATE DATABASE [IF NOT EXISTS] <数据库名>
[[DEFAULT] CHARACTER SET <字符集名>] 
[[DEFAULT] COLLATE <校对规则名>];
```

[ ]中的内容是可选的。语法说明如下：

- <数据库名>：创建数据库的名称。MySQL 的数据存储区将以目录方式表示 MySQL 数据库，因此数据库名称必须符合操作系统的文件夹命名规则，注意在 MySQL 中不区分大小写。
- IF NOT EXISTS：在创建数据库之前进行判断，只有该数据库目前尚不存在时才能执行操作。此选项可以用来避免数据库已经存在而重复创建的错误。
- [DEFAULT] CHARACTER SET：指定数据库的默认字符集。
- [DEFAULT] COLLATE：指定字符集的默认校对规则。

可以使用==SHOW CREATE DATABASE 数据库名称==查看数据库的定义声明

为防止字符混乱的情况发生，MySQL 有时需要在创建数据库时明确指定字符集；在中国大陆地区，常用的字符集有 utf8 和 gbk。

- utf8 能够存储全球的所有字符，在任何国家都可以使用，默认的校对规则为 utf8_general_ci，对于中文可以使用 utf8_general_ci。
- gbk只能存储汉语涉及到的字符，不具有全球通用性，默认的校对规则为 gbk_chinese_ci。



## 2.4、数据库操作

**查看数据库**

```mysql
SHOW DATABASES [LIKE '数据库名'];
```

语法说明如下：

- LIKE 从句是可选项，用于匹配指定的数据库名称。LIKE 从句可以部分匹配，也可以完全匹配。
- 数据库名由单引号' '包围。



**修改数据库**

在 MySQL 中，可以使用 ALTER DATABASE 或 ALTER SCHEMA 语句来修改已经被创建或者存在的数据库的相关参数。

修改数据库的语法格式为：

```mysql
ALTER DATABASE [数据库名] 
{[ DEFAULT ] CHARACTER SET <字符集名> |
[ DEFAULT ] COLLATE <校对规则名>}
```

语法说明如下：

- ALTER DATABASE 用于更改数据库的全局特性。这些特性存储在数据库目录的 db.opt 文件中。
- 使用 ALTER DATABASE 需要获得数据库 ALTER 权限。
- 数据库名称可以忽略，此时语句对应于默认数据库。
- CHARACTER SET 子句用于更改默认的数据库字符集。



**删除数据库**

```mysql
DROP DATABASE [ IF EXISTS ] <数据库名>
```

语法说明如下：

- <数据库名>：指定要删除的数据库名。
- IF EXISTS：用于防止当数据库不存在时发生错误。
- DROP DATABASE：删除数据库中的所有表格并同时删除数据库。使用此语句时要非常小心，以免错误删除。如果要使用 DROP DATABASE，需要获得数据库 DROP 权限。

注意：MySQL 安装后，系统会自动创建名为 information_schema 和 mysql 的两个系统数据库，系统数据库存放一些和数据库相关的信息，如果删除了这两个数据库，MySQL 将不能正常工作。



**选择数据库**

```mysql
USE <数据库名>;
```

# 3、存储引擎

## 3.1、什么是存储引擎？

数据库存储引擎是数据库底层软件组件，数据库管理系统使用数据引擎进行创建、查询、更新和删除数据操作。不同的存储引擎提供不同的存储机制、索引技巧、锁定水平等功能，使用不同的存储引擎还可以获得特定的功能。

现在许多数据库管理系统都支持多种不同的存储引擎。MySQL 的核心就是存储引擎。

提示：InnoDB 事务型数据库的首选引擎，支持事务安全表（ACID），支持行锁定和外键。MySQL 5.5.5 之后，InnoDB 作为默认存储引擎。

MyISAM 是基于 ISAM 的存储引擎，并对其进行扩展，是在 Web、数据仓储和其他应用环境下最常使用的存储引擎之一。MyISAM 拥有较高的插入、查询速度，但不支持事务。

MEMORY 存储引擎将表中的数据存储到内存中，为查询和引用其他数据提供快速访问。



## 3.2、支持的存储引擎

MySQL 支持多种类型的数据库引擎，可分别根据各个引擎的功能和特性为不同的数据库处理任务提供各自不同的适应性和灵活性。在 MySQL 中，可以利用 ==SHOW ENGINES== 语句来显示可用的数据库引擎和默认引擎。

MySQL 提供了多个不同的存储引擎，包括处理事务安全表的引擎和处理非事务安全表的引擎。在 MySQL 中，不需要在整个服务器中使用同一种存储引擎，针对具体的要求，可以对每一个表使用不同的存储引擎。

支持的存储引擎有 InnoDB、MyISAM、Memory、Merge、Archive、Federated、CSV、BLACKHOLE 等。可以使用SHOW ENGINES语句查看系统所支持的引擎类型，结果如图所示：

<img src="../Images/MySQL/image-20200528235456064.png" alt="image-20200528235456064" style="zoom: 67%;" />

Support 列的值表示某种引擎是否能使用，YES表示可以使用，NO表示不能使用，DEFAULT表示该引擎为当前默认的存储引擎。



## 3.3、如何选择存储引擎？

不同的存储引擎都有各自的特点，以适应不同的需求，如表所示。为了做出选择，首先要考虑每一个存储引擎提供了哪些不同的功能。

| 功能         | MyISAM | MEMORY | InnoDB | Archive |
| ------------ | ------ | ------ | ------ | ------- |
| 存储限制     | 256TB  | RAM    | 64TB   | None    |
| 支持事务     | No     | No     | Yes    | No      |
| 支持全文索引 | Yes    | No     | No     | No      |
| 支持树索引   | Yes    | Yes    | Yes    | No      |
| 支持哈希索引 | No     | Yes    | No     | No      |
| 支持数据缓存 | No     | N/A    | Yes    | No      |
| 支持外键     | No     | No     | Yes    | No      |

可以根据以下的原则来选择 MySQL 存储引擎：

- 如果要提供提交、回滚和恢复的事务安全（ACID 兼容）能力，并要求实现并发控制，InnoDB 是一个很好的选择。
- 如果数据表主要用来插入和查询记录，则 MyISAM 引擎提供较高的处理效率。
- 如果只是临时存放数据，数据量不大，并且不需要较高的数据安全性，可以选择将数据保存在内存的 MEMORY 引擎中，MySQL 中使用该引擎作为临时表，存放查询的中间结果。
- 如果只有 INSERT 和 SELECT 操作，可以选择Archive 引擎，Archive 存储引擎支持高并发的插入操作，但是本身并不是事务安全的。Archive 存储引擎非常适合存储归档数据，如记录日志信息可以使用 Archive 引擎。

提示：使用哪一种引擎要根据需要灵活选择，一个数据库中多个表可以使用不同的引擎以满足各种性能和实际需求。使用合适的存储引擎将会提高整个数据库的性能。



## 3.4、MySQL 默认存储引擎

InnoDB 是系统的默认引擎，支持可靠的事务处理。

使用下面的语句可以修改数据库临时的默认存储引擎

```mysql
SET DEFAULT_storage_engine=<存储引擎名>
```

但是当再次重启客户端时，默认存储引擎仍然是 InnoDB。

# 4、数据表

## 4.1、创建数据表

使用 CREATE TABLE 语句创建表。其语法格式为：

```mysql
CREATE TABLE <表名> ([表定义选项])[表选项][分区选项];
```

其中，[表定义选项]的格式为：<列名1> <类型1> [,…] <列名n> <类型n>

CREATE TABLE 命令语法比较多，其主要是由表创建定义（CREATE-definition）、表选项（TABLE-options）和分区选项（partition-options）所组成的。

CREATE TABLE 语句的主要语法及使用说明如下：

- CREATE TABLE：用于创建给定名称的表，必须拥有表CREATE的权限。
- <表名>：指定要创建表的名称，在 CREATE TABLE 之后给出，必须符合标识符命名规则。表名称被指定为 db_name.tbl_name，以便在特定的数据库中创建表。无论是否有当前数据库，都可以通过这种方式创建。在当前数据库中创建表时，可以省略 db-name。如果使用加引号的识别名，则应对数据库和表名称分别加引号。例如，'mydb'.'mytbl' 是合法的，但 'mydb.mytbl' 不合法。
- <表定义选项>：表创建定义，由列名（col_name）、列的定义（column_definition）以及可能的空值说明、完整性约束或表索引组成。
- 默认的情况是，表被创建到当前的数据库中。若表已存在、没有当前数据库或者数据库不存在，则会出现错误。

提示：使用 CREATE TABLE 创建表时，必须指定以下信息：

- 要创建的表的名称不区分大小写，不能使用SQL语言中的关键字，如DROP、ALTER、INSERT等。
- 数据表中每个列（字段）的名称和数据类型，如果创建多个列，要用逗号隔开。

实例：

```mysql
CREATE TABLE runoob_tb1(
	runoob_id INT NOT NULL AUTO_INCREMENT,
	runoob_title VARCHAR(100) NOT NULL,
	runoob_author VARCHAR(40) NOT NULL,
	submission_date DATE,
	PRIMARY KEY(runoob_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

实例解析：

- 如果你不想字段为NULL可以设置字段的属性为NOT NULL，在操作数据库时如果输入该字段的数据为NULL，就会报错
- AUTO_INCREMENT定义列为自增的属性，一般用于主键，数值会自动加1
- PRIMARY KEY关键字用于定义列为主键。可以使用多列来定义主键，列间以逗号分隔
- ENGINE设置存储引擎，CHARSET设置编码

创建MySQL的表时，表名和字段名外面的符号`不是单引号，而是英文输入法状态下的反单引号，也就是键盘左上角esc下面的那一个~按键。

反单引号是为了区分MySQL关键字与普通字符而引入的符号，一般的，表名与字段名都是用反单引号（也可以不用）。



## 4.2、查看表结构

```mysql
DESC <表名>;
```

或

```mysql
SHOW COLUMNS FROM <表名>;
```

其中，各个字段的含义如下：

- Null：表示该列是否可以存储 NULL 值。
- Key：表示该列是否已编制索引。PRI 表示该列是表主键的一部分，UNI 表示该列是 UNIQUE 索引的一部分，MUL 表示在列中某个给定值允许出现多次。
- Default：表示该列是否有默认值，如果有，值是多少。
- Extra：表示可以获取的与给定列有关的附加信息，如 AUTO_INCREMENT 等。

SHOW CREATE TABLE语句可以用来显示创建表时的CREATE TABLE语句，语法格式如下：

```mysql
SHOW CREATE TABLE <表名>\G；
```

提示：使用 SHOW CREATE TABLE 语句不仅可以查看创建表时的详细语句，而且可以查看存储引擎和字符编码。如果不加“\G”参数，显示的结果可能非常混乱，加上“\G”参数之后，可使显示的结果更加直观，易于查看。



## 4.3、修改数据表

当我们需要修改数据表名或者修改数据表字段时，就需要使用到MySQL ALERT命令。



**删除、添加字段**

使用ALERT命令及DROP子句来删除以上表的字段：

```mysql
ALTER TABLE <表名> DROP <字段名>；
```

如果数据表中只剩余一个字段则无法使用DROP来删除字段

添加字段的语法格式如下：

```mysql
ALTER TABLE <表名> ADD <新字段名> <数据类型> [约束条件] [FIRST|AFTER 已存在的字段名]；
```

新字段名为需要添加的字段的名称；FIRST 为可选参数，其作用是将新添加的字段设置为表的第一个字段；AFTER 为可选参数，其作用是将新添加的字段添加到指定的已存在的字段名的后面。



**修改字段类型及名称**

修改字段的数据类型就是把字段的数据类型转换成另一种数据类型。

在 MySQL 中修改字段数据类型的语法规则如下：

```mysql
ALTER TABLE <表名> MODIFY <字段名> <数据类型>;
```

使用CHANGE子句，语法有很大的不同。在CHANGE关键字之后，紧跟着的是你要修改的字段名，然后指定新字段名及类型。

```mysql
ALTER TABLE <表名> CHANGE <旧字段名> <新字段名> <新数据类型>；
```



**修改字段默认值**

可以使用ALERT来修改字段的默认值：

```mysql
ALTER TABLE <表名> ALTER <字段名> SET DEFAULT <默认值>;
```

也可以使用ALTER命令及DROP子句来删除字段的默认值：

```mysql
ALTER TABLE <表名> ALTER <字段名> DROP DEFAULT;
```

当修改字段时，可以指定是否包含值或者是否设置默认值。

以下实例：指定字段j为NOT NULL且默认值为100：

```mysql
ALTER TABLE <表名> MODIFY j BIGINT NOT NULL DEFAULT 100;
```

如果你不设置默认值，MySQL会自动设置该字段默认为NULL



**修改数据表引擎**

使用ALTER命令TYPE子句来完成：

将表的引擎修改为MYISAM：

```mysql
ALTER TABLE <表名> ENGINE=MYISAM;
```



**修改表名**

如果需要修改数据表的名称，可以在ALTER TABLE语句中使用RENAME子句来实现：

```mysql
ALTER TABLE <旧表名> RENAME [TO] <新表名>；
```



## 4.4、删除数据表

删除MySQL数据表的通用语法：

```mysql
DROP TABLE [IF EXISTS] <表名> [ , <表名1> , <表名2>] …
```

语法说明如下：

- <表名>：被删除的表名。DROP TABLE 语句可以同时删除多个表，用户必须拥有该命令的权限。
- 表被删除时，所有的表数据和表定义会被取消，所以使用本语句要小心。
- 表被删除时，用户在该表上的权限并不会自动被删除。
- 参数IF EXISTS用于在删除前判断删除的表是否存在，加上该参数后，在删除表的时候，如果表不存在，SQL 语句可以顺利执行，但会发出警告（warning）。

# 5、约束

SQL约束用于规定表中的规则。

如果存在违反约束的数据行为，行为会被约束终止。

约束可以在创建表时规定（通过CREATE TABLE语句），或者在表创建之后规定（通过ALTER TABLE语句）。

在SQL中，有如下约束：

- NOT NULL：指示某列不能存储NULL值。
- UNIQUE：保证某列的每行必须有唯一的值。
- PRIMARY KEY：NOT NULL和UNIQUE的结合。确保某列（或两个列多个列的结合）有唯一标识，有注意更容易更快速地找到表中的一个特定的记录。
- FOREIGN KEY：保证一个表中的数据匹配到另一个表中的值得参照完整性。
- CHECK：保证列中的值符合指定的条件。
- DEFAULT：规定没有给列赋值时的默认值。



## 5.1、NOT NULL

添加NOT NULL约束:

在一个已创建的表“Age”字段中添加NOT NULL约束：

```mysql
ALTER TABLE Persons MODIFY Age INT NOT NULL;
```

删除NOT NULL约束:

在一个已创建的表的“Age”字段中删除NOT NULL约束：

```mysql
ALTER TABLE Persons MODIFY Age INT NULL;
```



## 5.2、UNIQUE

UNIQUE约束唯一标识数据库表中的每条记录。

UNIQUE和PRIMARY KEY约束均为列或列集合提供了唯一性的保证。

PRIMARY KEY约束拥有自动定义的UNIQUE约束。

注意：每个表可以有多个UNIQUE约束，但是每个表只能有一个PRIMARY KEY约束（一个表不能有两个主键，但是允许用多个字段组成主键）。声明为 PRIMAY KEY 的列不允许有空值；声明为 UNIQUE 的字段允许空值的存在，但只能出现一个空值。



**添加UNIQUE约束**

实例：

在“Persons”表创建时在“P_id”列上创建UNIQUE约束

```mysql
CREATE TABLE Persons(
    P_Id INT NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    UNIQUE(P_Id)
);
```

如需命名UNIQUE约束，并定义多个UNIQUE约束，请使用下面的语法：

```mysql
CREATE TABLE Persons(
    P_Id INT NOT NULL,
    LastName VARCHAR(255),
    FirstName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    CONSTRAINT uc_PersonID UNIQUE (P_Id,LastName)
);
```



**修改UNIQUE约束**

```mysql
ALTER TABLE Persons ADD UNIQUE(P_Id);
```

如需命名UNIQUE约束，并定义多个列的UNIQUE约束：

```mysql
ALTER TABLE Persons ADD CONSTRAINT uc_PersonID UNIQUE(P_Id,LastName);
```

撤销UNIQUE约束：

```mysql
ALTER TABLE Persons DROP INDEX uc_PersonID;
```



## 5.3、PRIMARY KEY

PRIMARY KEY约束唯一标识数据库表中的没条记录。

主键必须包含唯一的值。

主键列不能包含NULL值。

每个表应该有一个主键，并且每个表只能有一个主键。



**添加PRIMARY KEY**

实例：

在“Persons”表创建时在“P_Id”列上创建PRIMARY KEY约束

```mysql
CREATE TABLE Persons(
    P_Id INT NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    PRIMARY KEY (P_Id)
);
```

如需命名PRIMARY KEY约束，并定义多个列的PRIMARY KEY约束：

```mysql
CREATE TABLE Persons(
    P_Id INT NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    CONSTRAINT pk_PersonID PRIMARY KEY (P_Id,LastName)
);
```

注释：在上面的实例中，只有一个主键PRIMARY KEY（pk_PersonID）。然而，pk_PersonID的值是由两个列（P_Id和LastName）组成的。



**修改PRIMARY KEY**

```mysql
ALTER TABLE Persons ADD PRIMARY KEY (P_Id);
```

如需命名PRIMARY KEY约束，并定义多个列的PRIMARY KEY约束：

```mysql
ALTER TABLE Persons ADD CONSTRAINT pk_PersonID PRIMARY KEY (P_Id,LastName);
```

注释：如果使用ALTER语句添加主键，必须把主键列声明为不包含NULL值，

如果未声明，先使用以下语句修改再添加主键：

```mysql
ALTER TABLE <表名> MODIFY <字段名> <数据类型> NOT NULL;
```

撤销PRIMARY KEY约束

```mysql
ALTER TABLE Persons DROP PRIMARY KEY;
```



## 5.4、FOREIGN KEY

一个表中的FOREIGN KEY指向另一个表的UNIQUE KEY（唯一约束的键）。

A表的a列指向B表的b列，则：

- A表的a列是B表的PRIMARY KEY；
- B表的b列是A表的FOREIGN KEY。

FOREIGN KEY约束用于预防破坏表之间连接的行为。

FOREIGN KEY约束也能防止非法数据插入外键列，因为它必须是它指向的那个表中的值之一。



**添加FOREIGN KEY**

实例：

```mysql
CREATE TABLE Orders(
    O_Id INT NOT NULL,
    OrderNo INT NOT NULL,
    P_Id INT,
    PRIMARY KEY (O_Id),
    FOREIGN KEY (P_Id) REFERENCES Persons(P_Id)
);
```

在“Orders”表创建时在“P_id”列上创建FOREIGN KEY约束

如需命名 FOREIGN KEY 约束，并定义多个列的 FOREIGN KEY 约束:

```mysql
CREATE TABLE Orders(
    O_Id INT NOT NULL,
    OrderNo INT NOT NULL,
    P_Id INT,
    PRIMARY KEY (O_Id),
    CONSTRAINT fk_PerOrders FOREIGN KEY (P_Id) REFERENCES Persons(P_Id)
);
```



**修改FOREIGN KEY**

```mysql
ALTER TABLE Orders ADD FOREIGN KEY (P_Id) REFERENCES Persons(P_Id);
```

如需命名 FOREIGN KEY约束，并定义多个列的 FOREIGN KEY 约束:

```mysql
ALTER TABLE Orders ADD CONSTRAINT fk_PerOrders FOREIGN KEY (P_Id) REFERENCES Persons (P_Id);
```

撤销FOREIGN KEY约束：

```mysql
ALTER TABLE Orders DROP FOREIGN KEY fk_PerOrders;
```



**级联**

这是数据库外键定义的一个可选项，用来设置当主键表中的被参考列的数据发生变化时，外键表中响应字段的变化规则。UPDATE则是主键表中被参考字段的值更新，DELETE是指在主键表中删除一条记录。

ON UPDATE和ON DELETE后面可以跟的词语有四个：

- NO ACTION：表示不做任何操作。
- SET NULL：表示在外键表中将相应的字段设置为NULL
- SET DEFAULT：表示设置为默认值
- CASCADE：表示级联操作，就是说，如果主键表中被参考字段更新，外检表也更新，主键表中的记录被删除，外检表中的该行也相应删除。



## 5.5、CHECK

CHECK约束用于限制列中的值得范围

如果对单个列定义CHECK约束，那么该列只允许特定的值。

如果对一个表定义CHECK约束，那么此约束会基于行中其他列的值在特定的列中对值进行限制。



**添加CHECK约束**

实例：

在“Persons”表创建时在“P_Id”列上创建CHECK约束。CHECK约束规定“P_Id”列必须只包含大于0的整数。

```mysql
CREATE TABLE Persons(
    P_Id INT NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    CHECK (P_Id>0)
);
```

如需命名 CHECK约束，并定义多个列的 CHECK约束：

```mysql
CREATE TABLE Persons(
    P_Id INT NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    CONSTRAINT chk_Person CHECK (P_Id>0 AND City='Sandnes')
);
```



**修改CHECK约束**

```mysql
ALTER TABLE Persons ADD CHECK (P_Id>0);
```

如需命名 CHECK约束，并定义多个列的 CHECK约束：

```mysql
ALTER TABLE Persons CONSTRAINT chk_Person ADD CHECK (P_Id>0 AND City='Sandnes');
```

撤销CHECK约束

```mysql
ALTER TABLE Persons DROP CHECK chk_Person;
```



## 5.6、DEFAULT

**添加DEFAULT约束**

```mysql
CREATE TABLE Persons(
    P_Id INT NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255) DEFAULT 'Sandnes'
);
```

通过使用类似 GETDATE() 这样的函数，DEFAULT 约束也可以用于插入系统值：

```mysql
CREATE TABLE Persons(
    P_Id INT NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255) DEFAULT GETDATE()
);
```



**修改DEFAULT约束**

```mysql
ALTER TABLE Persons ALTER City SET DEFAULT 'Sandnes';
```

撤销DEFAULT约束：

```mysql
ALTER TABLE Persons ALTER City DROP DEFAULT;
```



## 5.7、查看表中的约束

查看数据表中的约束语法格式如下：

```mysql
SHOW CREATE TABLE <数据表名>;
```

# 6、增、删、改

## 6.1、插入数据

语法：

```mysql
INSERT INTO <表名>
(field1, field2,…fieldN)
VALUES
(VALUE1, VALUE2,…VALUEN);
```

实例：

```mysql
INSERT INTO runoob_tb1
(runoob_title, runoob_author, submission_date)
VALUES
('学习PHP', '菜鸟教程', NOW());

INSERT INTO runoob_tb1
(runoob_title, runoob_author, submission_date)
VALUES
('学习MysQL', '菜鸟教程', NOW());

INSERT INTO runoob_tb1
(runoob_title, runoob_author, submission_date)
VALUES
('Java教程', 'runoob.com', '2016-05-06');
```

实例中NOW()是一个MySQL函数，该函数返回日期和时间



**INSERT多条数据**

```mysql
INSERT INTO <表名>(field1, field2,…fieldN)
VALUES
(VALUEA1,VALUEA2,…VALUEAN),
(VALUEB1,VALUEB2,…VALUEBN),
(VALUEC1,VALUEC2,…VALUECN),
…;
```

如果第一列是自增可以在添加数据的时候，写为0或NULL，这样添加数据可以自增，从而可以添加全部数据，而不用特意规定哪几列添加数据。



**复制表**

复制表结构：

```mysql
CREATE TABLE targetTable LIKE sourceTable;
```

或者

```mysql
CREATE TABLE targetTable AS SELECT * FROM sourceTable WHERE 1=2;
```

复制表数据：

```mysql
INSERT INTO targetTable SELECT * FROM sourceTable;
```

复制表结构及数据到新表：

```mysql
CREATE TABLE newTable AS SELECT * FROM oldTable;
```



## 6.2、删除数据

语法：

```mysql
DELETE FROM <表名> [WHERE Clause]
```

- 如果没有指定WHERE子句，MySQL表中所有记录将被删除
- 你可以在WHERE子句中指定任何条件
- 你可以在单个表中一次性删除记录



## 6.3、更新数据

语法：

```mysql
UPDATE <表名> SET field1=new_VALUE1, field2=new_VALUE2
[WHERE Clause]
```

- 你可以同时更新一个或多个字段
- 你可以在WHERE子句中指定任何条件
- 你可以在一个单独表中同时更新数据

# 7、查询数据

SELECT语句用于从数据库中选取数据，结果被存储在一个结果表中，称为结果集。

## 7.1、限制查询结果数和偏移

语法：

```mysql
SELECT column_name,column_name FROM tbale_name
[WHERE Clause]
[LIMIT N][OFFSET M]
```

- 查询语句中你可以使用一个或多个表，表之间使用逗号(,)分割，并使用WHERE语句来设定查询条件
- SELECT命令可以读取一条或多条记录
- 你可以使用星号(*)来代替其他字段，SELECT语句会返回表的所有字段数据
- 你可以使用WHERE语句来包含任何条件
- 你可以使用LIMIT属性来设定返回的记录数
- 你可以通过OFFSET指定SELECT语句开始查询的数据偏移量。默认情况下偏移量为0

**实现分页**

LIMIT N：返回N条记录

OFFSET M：跳过M条记录，默认M=0，单独使用似乎不起作用

LIMIT N,M：相当于LIMIT M OFFSET N，从第N条记录开始，返回M条记录

实现分页：

```mysql
SELECT * FROM _TABLE LIMIT (page_number-1)*lines_perpage, lines_perpage
```

或

```mysql
SELECT * FROM _TABLE LIMIT lines_perpage OFFSET (page_number-1)*lines_perpage
```



## 7.2、WHERE--条件查询

语法：

```mysql
SELECT field1, field2,…fieldN FROM TABLE_name1, TABLE_name2…
[WHERE condition1 [AND | OR] condition2…]
```

- 查询语句中你可以使用一个或多个表，表之间用逗号分隔，并使用WHERE语句来设定查询条件
- 你可以在WHERE子句中指定任何条件
- 你可以使用and或者or指定一个或多个条件
- WHERE子句也可以运用于SQL的DELETE或者UPDATE命令
- WHERE子句类似于程序语言中的if条件，根据MySQL表中的字段值来读取指定的数据

如果我们想在MySQL数据表中读取指定的数据，WHERE子句是非常有用的。

使用主键来作为WHERE子句的条件查询是非常快速的。

如果给定的条件在表中没有任何匹配的记录，那么查询不会返回任何数据。

MySQL的WHERE子句的字符串比较是不区分大小写的。可以使用BINARY关键字来设定WHERE子句的字符串比较是区分大小写的

```mysql
SELECT field1, field2,…fieldN FROM TABLE_name1, TABLE_name2…
[WHERE BINARY condition1 [AND | OR] condition2…]
```

### 7.2.1、WHERE的运算符

| 运算符  | 描述                                                |
| ------- | --------------------------------------------------- |
| =       | 等于                                                |
| <>      | 不等于。注释：在SQL的一些版本中，该操作符可被写成!= |
| >       | 大于                                                |
| <       | 小于                                                |
| >=      | 大于等于                                            |
| <=      | 小于等于                                            |
| BETWEEN | 在某个范围内。实例：BETWEEN 1  AND 3 => [1,3]       |
| LIKE    | 搜索某种模式                                        |
| IN      | 指定针对某个列的多个可能值。实例：IN(1,2,3)         |
| AND     | 与                                                  |
| OR      | 或                                                  |
| NOT     | 非                                                  |

逻辑运算的优先级：() ONT AND OR



**MySQL的整除和取余**

整除：DIV

```mysql
5 DIV 2 = 2;
```

取余：MOD

```mysql
5 MOD 2 = 1;
```

四舍五入：ROUND

```mysql
ROUND(1.5) = 2;
```



### 7.2.2、LIKE子句

LIKE子句中使用百分号%字符来表示任何字符，类似于UNIX或正则表达式中的星号*，如果没有使用百分号%，LIKE子句与等号=的效果是一样的

语法：

```mysql
SELECT field1, field2,…fieldN FROM <表名>
WHERE field1 LIKE condition1 [and | or] field2 = 'someVALUE'
```

- 你可以在WHERE子句中指定任何条件
- 你可以在WHERE子句中使用LIKE子句
- 你可以使用LIKE子句代替等号=
- LIKE通常与%一同使用，类似于一个元字符的搜索
- 你可以使用and或者or指定一个或多个条件
- 你可以在UPDATE或DELETE命令是使用WHERE…LIKE子句来指定条件 
- MYSQL不区分大小写，因此LIKE也是，如果想要区分大小写，在LIKE后面加上BINARY，表示区分大小写

在WHERE LIKE的条件查询中，MySQL有两种匹配方式

- %：表示任意0个或多个字符，可匹配任意类型和长度的字符，有些情况下若是中文，请使用两个百分号（%%）表示。
- _：表示任意单个字符，匹配单个任意字符，常用来限制表达式的字符长度语句。 



## 7.3、连接的使用

你可以在SELECT、UPDATE和DELETE语句中使用MySQL的JOIN来联合多表查询。

JOIN按照功能大致分为如下三类：

- INNER JOIN（内连接，或等值连接）：获取两个表中字段匹配关系的记录
- LEFT JOIN（左连接）：获取左表所有记录，即使右边没有对应匹配的记录
- RIGHT JOIN（右连接）：与LEFT JOIN相反，用于获取右表所有记录，即使左表没有相应匹配的记录 

实例：

<img src="../Images/MySQL/image-20200531154733044.png" alt="image-20200531154733044" style="zoom:80%;" />



**INNER JOIN**

INNER JOIN（也可以省略INNER 使用JOIN，效果一样）：

<img src="../Images/MySQL/image-20200531155043766.png" alt="image-20200531155043766" style="zoom:80%;" />

等价于：

<img src="../Images/MySQL/image-20200531155105933.png" alt="image-20200531155105933" style="zoom:80%;" />





**LEFT JOIN**

<img src="../Images/MySQL/image-20200531155241133.png" alt="image-20200531155241133" style="zoom:80%;" />





**RIGHT  JOIN**

<img src="../Images/MySQL/image-20200531155309994.png" alt="image-20200531155309994" style="zoom:80%;" />



## 7.4、排序

语法：

```mysql
SELECT field1,field2,…fieldN FROM TABLE_name1, TABLE_name2…
ORDER BY field1 [ASC | DESC][默认ASC], [field2…] [ASC | DESC][默认ASC];
```

- 你可以使用任何字段来作为排序的条件，从而返回排序后的查询结果
- 你可以设定多个字段来排序
- 你可以使用ASC或DESC关键字来设置查询结果是按升序或降序排列。默认情况下，它是按升序排列
- 你可以添加WHERE…LIKE子句来设置条件



**ORDER BY多列**

ORDER BY多列的时候，先按照第一个column_name排序，再按照第二个column_name排序。

- ORDER BY A,B    这个时候都是默认按升序排列
- ORDER BY A desc,B 这个时候A降序，B升序排列
- ORDER BY A, B desc 这个时候A升序，B降序排列



**MySQL拼音排序**

如果字符集采用的是gbk（汉字编码字符集），直接在查询语句后边添加ORDER BY：

```mysql
SELECT * FROM <表名> ORDER BY <字段名> ASC;
```

如果字符集采用的是utf8（万国码），需要先对字段进行转码然后排序：

```mysql
SELECT * FROM <表名> ORDER BY CONVERT(<字段名> USING gbk) ASC;
```



## 7.5、分组

GROUP BY语句根据一个或多个列对结果集进行分组，在分组上我们可以使用COUNT、SUM、AVG等函数

GROUP BY语法：

```mysql
SELECT column_name,FUNCTION(cloumns_name) FROM <表名>
WHERE columns_name operator VALUE
GROUP BY column_name;
```

### 7.5.1、WITH  ROLLUP

WITH  ROLLUP可以实现在分组数据基础上再进行相同的统计（SUM，AVG，COUNT…）

例如我们将以上数据表按名字进行分组，再统计每个人登陆的次数：

<img src="../Images/MySQL/image-20200531161241249.png" alt="image-20200531161241249" style="zoom:80%;" />

其中记录NULL表示所有人的登陆次数

可以使用COALESCE来设置一个可以取代NULL的名称

COALESCE语法：

```mysql
SELECT COALESCE(a,b,c);
```

参数说明：如果 a == NULL，则选择b；如果 b == NULL ，则选择c；如果 a != NULL ，则选择a；如果a b c都为NULL，则返回为NULL（没意义）

以下实例中如果name为空我们使用“总数”代替：

<img src="../Images/MySQL/image-20200531161426907.png" alt="image-20200531161426907" style="zoom:80%;" />



### 7.5.2、HAVING子句

增加HAVING子句的原因是，WHERE关键字无法与聚合函数一起使用。

HAVING子句可以筛选分组后的各组数据。

语法：

```mysql
HAVING <条件>
```

其中，<条件>指的是指定的过滤条件。

HAVING 子句和 WHERE 子句非常相似，HAVING 子句支持 WHERE 子句中所有的操作符和语法，但是两者存在几点差异：

- WHERE 子句主要用于过滤数据行，而 HAVING 子句主要用于过滤分组，即 HAVING 子句基于分组的聚合值而不是特定行的值来过滤数据。
- WHERE 子句不可以包含聚合函数，HAVING 子句中的条件可以包含聚合函数。
- HAVING 子句是在数据分组后进行过滤，WHERE 子句会在数据分组前进行过滤。WHERE 子句排除的行不包含在分组中，可能会影响 HAVING 子句基于这些值过滤掉的分组。



## 7.6、正则表达式

MySQL同样支持其他正则表达式的匹配，MySQL中使用REGEXP或NOT REGEXP（或RLIKE和NOT RLIKE）运算符来进行正则表达式匹配

| 模式       | 描述                                                         |
| ---------- | ------------------------------------------------------------ |
| ^          | 匹配输入字符串的开始位置                                     |
| $          | 匹配输入字符串的结束位置                                     |
| .          | 匹配除 "\n" 之外的任何单个字符                               |
| […]        | 字符集合。匹配所包含的任意一个字符。例如， '[abc]' 可以匹配 "plain" 中的 'a'。 |
| [^…]       | 负值字符集合。匹配未包含的任意字符。例如， '[^abc]' 可以匹配 "plain" 中的'p'。 |
| 字符串     | 例如 'in' ,可以匹配 'china' , 'chinese'                      |
| p1\|p2\|p3 | 匹配 p1或 p2 或 p3。例如，'z\|foood' 能匹配 'z' 或 'food'。'(z\|f)ood' 则匹配 'zood' 或 'food' |
| *          | 匹配前面的子表达式零次或多次。例如，zo* 能匹配 'z' 以及 'zoo'。* 等价于{0,} |
| +          | 匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,}。 |
| {n}        | n 是一个非负整数。匹配确定的至少n次。例如，'o{2}' 不能匹配 'Bob' 中的 'o'，但是能匹配 "food" 中的两个 o。 |
| {n,m}      | m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。 |

实例：

查找name字段中以'st'为开头的所有数据：

```mysql
SELECT name FROM person_tbl WHERE name REGEXP '^st';
```

查找name字段中以'ok'为结尾的所有数据：

```mysql
SELECT name FROM person_tbl WHERE name REGEXP 'ok$';
```

查找name字段中包含'mar'字符串的所有数据：

```mysql
SELECT name FROM person_tbl WHERE name REGEXP 'mar';
```

查找name字段中以元音字符开头或以'ok'字符串结尾的所有数据：

```mysql
SELECT name FROM person_tbl WHERE name REGEXP '^[aeiou]|ok$';
```



## 7.7、UNION

描述：

UNION操作符用于连接两个以上的SELECT语句的结果组合到一个结果集中，多个SELECT语句会删除重复的数据

注意：UNION内部每个SELECT语句必须拥有相同数量的列。列也必须拥有相似的数据类型。同时，每个SELECT语句中的列的顺序必须相同。

语法：

```mysql
SELECT expression1, expression2, …expression_n
FROM <表名>
[WHERE conditions]
UNION [ALL | DISTINCT]
SELECT expression1, expression2,…expressoion_n
FROM <表名>
[WHERE conditions];
```

参数：

- expression：要检索的列
- WHERE conditions：可选，检索条件
- DISTINCT：可选，删除结果集中重复的数据。默认情况下UNION操作符已经删除了重复数据，所以DISTINCT修饰符对结果没啥影响
- ALL：可选，返回所有结果集，包含重复数据



## 7.8、NULL值处理

MySQL使用SELECT命令及WHERE子句来读取数据表中的数据，但是当提供的查询条件字段为NULL时，该命令就无法正常工作

为了处理这种情况，MySQL提供了三大运算符：

- IS NULL：当列的值是NULL，此运算符返回TRUE
- IS NOT NULL：当列的值不为NULL，运算符返回TRUE
- <=>：比较操作符（不同于=运算符），当比较的两个值为NULL时返回TRUE

关于NULL的条件比较运算是比较特殊的。不能使用=NULL或!=NULL在列中查找NULL值。

在MySQL中，NULL值与任何其他值比较（即使是NULL）永远返回false，即NULL=NULL返回FALSE。

实例：

<img src="../Images/MySQL/image-20200531163220282.png" alt="image-20200531163220282" style="zoom:80%;" />

当输出列中有NULL值时可以使用函数IFNULL()或COALESCE()

# 8、视图

视图是可视化的表。

视图隐藏了底层的表结构，简化了数据访问操作，客户端不再需要知道底层表结构及其之间的关系。

视图提供了一个统一访问数据的接口（即可以允许用户通过视图访问数据的安全机制，而不授予用户直接访问底层表的权限）。

从而加强了安全性，使用户只能看到视图所显示的数据。

视图还可以被嵌套，一个视图中可以嵌套另一个视图。

视图提供的是对查询操作的封装，本身不包含数据，所呈现的数据是根据视图定义从基础表中检索出来的，如果基础表的数据新增或删除，视图呈现的也是更新后的数据。视图定义后，编写完所需的查询，可以方便地重用该视图。



**创建视图**

语法格式如下：

```mysql
CREATE VIEW <视图名> AS <SELECT语句>
```

语法说明如下:

- <视图名>：指定视图的名称。该名称在数据库中必须是唯一的，不能与其他表或视图同名。
- <SELECT语句>：指定创建视图的 SELECT 语句，可用于查询多个基础表或源视图。 

默认情况下，创建的视图和基本表的字段是一样的，也可以通过指定视图字段的名称来创建视图。

<img src="../Images/MySQL/image-20200531164924587.png" alt="image-20200531164924587" style="zoom:80%;" />



**查看视图**

```mysql
SELECT column_name FROM view_name;
```

或

```mysql
DESCRIBE 视图名；
```

注意：DESCRIBE 一般情况下可以简写成 DESC，输入这个命令的执行结果和输入 DESCRIBE 是一样的。



**修改视图**

```mysql
CREATE OR REPLACE view_name AS 
SELECT column_name…
FROM <表名>
WHERE <条件>;
```



**删除视图**

```mysql
DROP VIEW view_name;
```

# 9、存储过程

存储过程（Stored Procedure）是一种在数据库中存储复杂程序，以便外部程序调用的一种数据库对象。

存储过程是为了完成特定功能的SQL语句集，经编译创建并保存在数据库中，用户可通过指定存储过程的名字并给定参数(需要时)来调用执行。

存储过程思想上很简单，就是数据库 SQL 语言层面的代码封装与重用。

优点

- 存储过程可封装，并隐藏复杂的商业逻辑。
- 存储过程可以回传值，并可以接受参数。
- 存储过程无法使用 SELECT 指令来运行，因为它是子程序，与查看表，数据表或用户定义函数不同。
- 存储过程可以用在数据检验，强制实行商业逻辑等。

缺点

- 存储过程，往往定制化于特定的数据库上，因为支持的编程语言不同。当切换到其他厂商的数据库系统时，需要重写原有的存储过程。
- 存储过程的性能调校与撰写，受限于各种数据库系统。



## 9.1、存储过程的创建和调用

- 存储过程就是具有名字的一段代码，用来完成一个特定的功能。
- 创建的存储过程保存在数据库的数据字典中。

语法：

**声明语句结束符，可以自定义**

```mysql
DELIMITER $$
```

或

```mysql
DELIMITER //
```

当使用 DELIMITER命令时，应该避免使用反斜杠“\”字符，因为它是 MySQL 的转义字符。



**声明存储过程**

```mysql
CREATE PROCEDURE p_name([IN,OUT,INOUT] data_name datatype)
```



**存储过程开始和结束符号**

BEGIN…END



**变量赋值**

```mysql
SET @p_in=1
```



**定义变量**

```mysql
DECLARE l_INT UNSIGNED DEFAULT 1;
```

可以使用 ==SHOW PROCEDURE STATUS== 命令查看数据库中存在哪些存储过程，若要查看某个存储过程的具体信息，则可以使用 ==SHOW CREATE PROCEDURE <存储过程名>==。



实例：

```mysql
DELIMITER $$		#将语句的结束符号从分号;临时改为两个$$(可以是自定义)
CREATE PROCEDURE DELETE_matches(IN p_playerno INTEGER)
BEGIN
	DELETE FROM matches
	WHERE playerno = p_playerno;
END$$
DELIMITER ; 　#将语句的结束符号恢复为分号
```

解析：默认情况下，存储过程和默认数据库相关联，如果想指定存储过程创建在某个特定的数据库下，那么在过程名前面加数据库名做前缀。 在定义过程时，使用 `DELIMITER $$` 命令将语句的结束符号从分号 `; `临时改为两个 `$$`，使得过程体中使用的分号被直接传递到服务器，而不会被客户端（如mysql）解释。



**调用存储过程：**

```mysql
CALL sp_name(传参);
```



**存储过程体**

存储过程体包含了在过程调用时必须执行的语句，例如：DML、DDL语句，IF-THEN-ELSE和WHILE-DO语句、声明变量的DECLARE语句等。



**过程体格式**：以BEFIN开始，以END结束(可嵌套)

```mysql
BEGIN
　　BEGIN
　　　　BEGIN
　　　　　　statements; 
　　　　END
　　END
END
```

注意：每个嵌套块及其中的每条语句，必须以分号结束，表示过程体结束的BEGIN-END块(又叫做复合语句compound statement)，则不需要分号。



**为语句块贴标签**:

```mysql
[begin_label:] BEGIN
　　[statement_list]
END [end_label]
```

例如：

```mysql
label1: BEGIN
　　label2: BEGIN
　　　　label3: BEGIN
　　　　　　statements; 
　　　　END label3 ;
　　END label2;
END label1
```

标签有两个作用：

- 增强代码的可读性
- 在某些语句(例如:LEAVE和ITERATE语句)，需要用到标签。



## 9.2、存储过程的参数

MySQL存储过程的参数用在存储过程的定义，共有三种参数类型,IN,OUT,INOUT,形式如：

```mysql
CREATEPROCEDURE 存储过程名([IN,OUT,INOUT] 参数名 数据类形...)
```

- IN 输入参数：表示调用者向过程传入值（传入值可以是字面量或变量）
- OUT 输出参数：表示过程向调用者传出值(可以返回多个值)（传出值只能是变量）
- INOUT 输入输出参数：既表示调用者向过程传入值，又表示过程向调用者传出值（值只能是变量） 

注意：

- 如果过程没有参数，也必须在过程名后面写上小括号
	例：

	```mysql
	CREATE PROCEDURE sp_name ([proc_parameter[,...]]) ……
	```

- 确保参数的名字不等于列的名字，否则在过程体中，参数名被当做列名来处理。

建议：

- 输入值使用IN参数。
- 返回值使用OUT参数。
- INOUT参数就尽量的少用。



## 9.3、变量

**变量定义**

```mysql
DECLARE variable_name datatype [DEFAULT VALUE];
```

其中，datatype 为 MySQL 的数据类型，如: INT, FLOAT, DATE,VARCHAR(length)



**变量赋值**

```mysql
SET 变量名 = 表达式值
```



**用户变量**

在MySQL客户端使用用户变量:

<img src="../Images/MySQL/image-20200531171439487.png" alt="image-20200531171439487" style="zoom:80%;" />

在存储过程中使用用户变量:

<img src="../Images/MySQL/image-20200531171451044.png" alt="image-20200531171451044" style="zoom:80%;" />

在存储过程间传递全局范围的用户变量:

<img src="../Images/MySQL/image-20200531171500385.png" alt="image-20200531171500385" style="zoom:80%;" />

注意:

- 用户变量名一般以@开头
- 滥用用户变量会导致程序难以理解及管理



## 9.4、存储过程的删除

```mysql
 DROP { PROCEDURE | FUNCTION } [ IF EXISTS ] <过程名>
```

注意：存储过程名称后面没有参数列表，也没有括号，在删除之前，必须确认该存储过程没有任何依赖关系，否则会导致其他与之关联的存储过程无法运行。

查看：

```mysql
SHOWCREATE PROCEDURE 数据库.存储过程名;
```



## 9.5、存储过程的控制语句

### 9.5.1、变量作用域

内部的变量在其作用域范围内享有更高的优先权，当执行到 end。变量时，内部变量消失，此时已经在其作用域外，变量不再可见了，应为在存储过程外再也不能找到这个申明的变量，但是你可以通过 out 参数或者将其值指派给会话变量来保存其值。

<img src="../Images/MySQL/image-20200531195043445.png" alt="image-20200531195043445" style="zoom:80%;" />



### 9.5.2、条件语句

**if-then-else**

<img src="../Images/MySQL/image-20200531195130654.png" alt="image-20200531195130654" style="zoom:80%;" />



**case语句**

<img src="../Images/MySQL/image-20200531195143678.png" alt="image-20200531195143678" style="zoom:80%;" />



### 9.5.3、循环语句

**while ···· end while**

<img src="../Images/MySQL/image-20200531195208679.png" alt="image-20200531195208679" style="zoom:80%;" />



**repeat···· end repea**

它在执行操作后检查结果，而 while 则是执行前进行检查。

<img src="../Images/MySQL/image-20200531195231262.png" alt="image-20200531195231262" style="zoom:80%;" />



**loop ·····endloop**

loop 循环不需要初始条件，这点和 while 循环相似，同时和 repeat 循环一样不需要结束条件, leave 语句的意义是离开循环。

<img src="../Images/MySQL/image-20200531195245319.png" alt="image-20200531195245319" style="zoom:80%;" />



**LABLES 标号**

标号可以用在 begin repeat while 或者 loop 语句前，语句标号只能在合法的语句前面使用。可以跳出循环，使运行指令达到复合语句的最后一步。



## 9.6、ITERATE迭代

ITERATE 通过引用复合语句的标号,来从新开始复合语句:

<img src="../Images/MySQL/image-20200531195350919.png" alt="image-20200531195350919" style="zoom:80%;" />

# 10、触发器

## 10.1、触发器简介

MySQL 数据库中触发器是一个特殊的存储过程，不同的是执行存储过程要使用 CALL 语句来调用，而触发器的执行不需要使用 CALL 语句来调用，也不需要手工启动，只要一个预定义的事件发生就会被 MySQL自动调用。

引发触发器执行的事件一般如下：

- 增加一条学生记录时，会自动检查年龄是否符合范围要求。
- 每当删除一条学生信息时，自动删除其成绩表上的对应记录。
- 每当删除一条数据时，在数据库存档表中保留一个备份副本。

触发程序的优点如下：

- 触发程序的执行是自动的，当对触发程序相关表的数据做出相应的修改后立即执行。
- 触发程序可以通过数据库中相关的表层叠修改另外的表。
- 触发程序可以实施比 FOREIGN KEY 约束、CHECK 约束更为复杂的检查和操作。 

触发器与表关系密切，主要用于保护表中的数据。特别是当有多个表具有一定的相互联系的时候，触发器能够让不同的表保持数据的一致性。

在 MySQL 中，只有执行 INSERT、UPDATE 和 DELETE 操作时才能激活触发器。

在实际使用中，MySQL 所支持的触发器有三种：INSERT 触发器、UPDATE 触发器和 DELETE 触发器。



**INSERT 触发器**

在 INSERT 语句执行之前或之后响应的触发器。

使用 INSERT 触发器需要注意以下几点：

- 在 INSERT 触发器代码内，可引用一个名为 NEW（不区分大小写）的虚拟表来访问被插入的行。
- 在 BEFORE INSERT 触发器中，NEW 中的值也可以被更新，即允许更改被插入的值（只要具有对应的操作权限）。
- 对于 AUTO_INCREMENT 列，NEW 在 INSERT 执行之前包含的值是 0，在 INSERT 执行之后将包含新的自动生成值。



**UPDATE 触发器**

在 UPDATE 语句执行之前或之后响应的触发器。

使用 UPDATE 触发器需要注意以下几点：

- 在 UPDATE 触发器代码内，可引用一个名为 NEW（不区分大小写）的虚拟表来访问更新的值。
- 在 UPDATE 触发器代码内，可引用一个名为 OLD（不区分大小写）的虚拟表来访问 UPDATE 语句执行前的值。
- 在 BEFORE UPDATE 触发器中，NEW 中的值可能也被更新，即允许更改将要用于 UPDATE 语句中的值（只要具有对应的操作权限）。
- OLD 中的值全部是只读的，不能被更新。

注意：当触发器设计对触发表自身的更新操作时，只能使用 BEFORE 类型的触发器，AFTER 类型的触发器将不被允许。



**DELETE 触发器**

在 DELETE 语句执行之前或之后响应的触发器。

使用 DELETE 触发器需要注意以下几点：

- 在 DELETE 触发器代码内，可以引用一个名为 OLD（不区分大小写）的虚拟表来访问被删除的行。
- OLD 中的值全部是只读的，不能被更新。



## 10.2、创建触发器

使用 CREATE TRIGGER 语句创建触发器。

语法格式如下：

```mysql
CREATE <触发器名> < BEFORE | AFTER >
<INSERT | UPDATE | DELETE >
ON <表名> FOR EACH ROW<触发器主体>
```

语法说明如下。

- 触发器名
	触发器的名称，触发器在当前数据库中必须具有唯一的名称。如果要在某个特定数据库中创建，名称前面应该加上数据库的名称。

- INSERT | UPDATE | DELETE
	触发事件，用于指定激活触发器的语句的种类。

	注意：三种触发器的执行时间如下。

	- INSERT：将新行插入表时激活触发器。例如，INSERT 的 BEFORE 触发器不仅能被 MySQL 的 INSERT 语句激活，也能被 LOAD DATA 语句激活。

	- DELETE： 从表中删除某一行数据时激活触发器，例如 DELETE 和 REPLACE 语句。

	- UPDATE：更改表中某一行数据时激活触发器，例如 UPDATE 语句。

- BEFORE | AFTER
	BEFORE 和 AFTER，触发器被触发的时刻，表示触发器是在激活它的语句之前或之后触发。若希望验证新数据是否满足条件，则使用 BEFORE 选项；若希望在激活触发器的语句执行之后完成几个或更多的改变，则通常使用 AFTER 选项。

- 表名

	与触发器相关联的表名，此表必须是永久性表，不能将触发器与临时表或视图关联起来。在该表上触发事件发生时才会激活触发器。同一个表不能拥有两个具有相同触发时刻和事件的触发器。例如，对于一张数据表，不能同时有两个 BEFORE UPDATE 触发器，但可以有一个 BEFORE UPDATE 触发器和一个 BEFORE INSERT 触发器，或一个 BEFORE UPDATE 触发器和一个 AFTER UPDATE 触发器。

- 触发器主体
	触发器动作主体，包含触发器激活时将要执行的 MySQL 语句。如果要执行多个语句，可使用 BEGIN…END 复合语句结构。

- FOR EACH ROW
	一般是指行级触发，对于受触发事件影响的每一行都要激活触发器的动作。例如，使用 INSERT 语句向某个表中插入多行数据时，触发器会对每一行数据的插入都执行相应的触发器动作。

注意：每个表都支持 INSERT、UPDATE 和 DELETE 的 BEFORE 与 AFTER，因此每个表最多支持 6 个触发器。每个表的每个事件每次只允许有一个触发器。单一触发器不能与多个事件或多个表关联。

另外，在 MySQL 中，若需要查看数据库中已有的触发器，则可以使用 ==SHOW TRIGGERS== 语句。



## 10.3、修改和删除触发器

与其他 MySQL 数据库对象一样，可以使用 DROP 语句将触发器从数据库中删除。

语法格式如下：

```mysql
DROP TRIGGER [ IF EXISTS ] [数据库名] <触发器名>
```

语法说明如下：

- 触发器名
	要删除的触发器名称。
- 数据库名
	可选项。指定触发器所在的数据库的名称。若没有指定，则为当前默认的数据库。
- 权限
	执行 DROP TRIGGER 语句需要 SUPER 权限。
- IF EXISTS
	可选项。避免在没有触发器的情况下删除触发器。

注意：删除一个表的同时，也会自动删除该表上的触发器。另外，触发器不能更新或覆盖，为了修改一个触发器，必须先删除它，再重新创建。

# 11、自定义函数

**创建并使用自定义函数**

可以使用 CREATE FUNCTION 语句创建自定义函数。

语法格式如下：

```mysql
CREATE FUNCTION <函数名> ([<参数1> <类型1>,<参数2> <类型2>] … )
RETURNS <类型>
<函数主体>
```

语法说明如下：

- <函数名>：指定自定义函数的名称。注意，自定义函数不能与存储过程具有相同的名称。
- <参数><类型>：用于指定自定义函数的参数。这里的参数只有名称和类型，不能指定关键字 IN、OUT 和 INOUT。
- RETURNS<类型>：用于声明自定义函数返回值的数据类型。其中，<类型>用于指定返回值的数据类型。
- <函数主体>：自定义函数的主体部分，也称函数体。所有在存储过程中使用的 SQL 语句在自定义函数中同样适用，包括前面所介绍的局部变量、SET 语句、流程控制语句、游标等。除此之外，自定义函数体还必须包含一个 RETURN<值> 语句，其中<值>用于指定自定义函数的返回值。

在 RETURN VALUE 语句中包含 SELECT 语句时，SELECT 语句的返回结果只能是一行且只能有一列值。

若要查看数据库中存在哪些自定义函数，可以使用 SHOW FUNCTION STATUS 语句；若要查看数据库中某个具体的自定义函数，可以使用 SHOW CREATE FUNCTION<函数名> 语句，其中<函数名>用于指定该自定义函数的名称。

<img src="../Images/MySQL/image-20200531205053160.png" alt="image-20200531205053160" style="zoom:80%;" />

成功创建自定义函数后，就可以如同调用系统内置函数一样，使用关键字 SELECT 调用用户自定义的函数，语法格式为：

```mysql
SELECT <自定义函数名> ([<参数> [,...]])
```



**修改自定义函数**

可以使用 ALTER FUNCTION 语句来修改自定义函数的某些相关特征。若要修改自定义函数的内容，则需要先删除该自定义函数，然后重新创建。



**删除自定义函数**

自定义函数被创建后，一直保存在数据库服务器上以供使用，直至被删除。删除自定义函数的方法与删除存储过程的方法基本一样，可以使用 DROP FUNCTION 语句来实现。

语法格式如下：

```mysql
DROP FUNCTION [ IF EXISTS ] <自定义函数名>
```

语法说明如下:

- <自定义函数名>：指定要删除的自定义函数的名称。
- IF EXISTS：指定关键字，用于防止因误删除不存在的自定义函数而引发错误。

# 12、事务

MySQL事务主要用于处理操作量大，复杂度高的数据。比如说，在人员管理系统中，你删除一个人员，需要删除人员的基本资料，也要删除该人员相关的信息，比如信息，文章等等，这样，这些数据库操作就构成一个事务！

- 在MySQL中只有使用了InnoDb数据库引擎的数据库或表才支持事务
- 事务处理可以用来维护数据库的完整性，保证成批的SQL语句要么全部执行，要么全部不执行
- 事务用来管理INSERT，UPDATE，DELETE语句

事务控制语句：

- BEGIN或START TRANSACTION显示地开启一个事务
- COMMIT也可以使用COMMIT WORD，不过二者是等价的。COMMIT会提交事务，并使已对数据库进行的所有修改成为永久性的
- ROLLBACK也可以使用ROLLBACK WORK，不过二者是等价的。回滚会结束用户的事务，并撤销正在进行的所有未提交的修改
- SAVEPOINT identifier，SAVEPOINT允许在事务中创建一个保存点，一个事务中可以有多个SAVEPOINT 
- RELEASE SAVEPOINT identifier 删除一个事务的保存点，当没有指定的保存点时，执行该语句会抛出一个异常
- ROLLBACK TO identifier把事务回滚到标记点
- SET TRANSACTION用来设置事务的隔离级别。InnoDB存储引擎提供事务的隔离级别有read uncommitted、read committed、repeatalbe read和serializable

MYSQL事务处理主要有两种方法：

1. 用BEGIN，ROLLBACK，COMMIT来实现
	- BEGIN开始一个事务
	- ROLLBACK事务回滚
	- COMMIT事务确认
2. 直接用SET来改变MySQL的自动提交模式
	- SET AUTOCOMMIT=0禁止自动提交
	- SET AUTOCOMMIT=1开始自动提交

实例：

<img src="../Images/MySQL/image-20200531205816289.png" alt="image-20200531205816289" style="zoom:80%;" />



## 12.1、事务的四大特性（ACID）

一般来说，事务必须满足4个条件：原子性（或称不可分割性）、一致性、隔离性（又称独立性）、持久性

- **原子性（Atomicity）**：一个事物中所有的操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被回滚到事务开始前的状态，就像这个事务从来没有执行过一样
- **一致性（Consistency）**：在事务开始之前和事务结束之后，数据库的完整性没有被破坏。这表示写入的资料必须符合所有的预设规则，这包含资料的的精确度、串联性以及后续数据库可以自发性地完成预定的工作
- **隔离性（Isolation）**：数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，包括读未提交、读已提交、可重复读和串行化
- **持久性（Durability）**：事务处理结束后，对数据的修改就是永久的，即使系统故障也不会丢失



## 12.2、事务的隔离级别

多个线程开启各自事务操作数据库中数据时，数据库系统要负责隔离操作，以保证各个线程在获取数据时的准确性。

如果不考虑隔离性，可能会引发如下问题：

- 脏读：一个事务读取了另一个未提交的并行事务写的数据。 
- 不可重复读：一个事务重新读取前面读取过的数据， 发现该数据已经被另一个已提交的事务修改过。
- 虚读（幻读）：一个事务重新执行一个查询，返回一套符合查询条件的行， 发现这些行因为其他最近提交的事务而发生了改变。



**脏读**

指一个事务读取了另外一个事务未提交的数据。

这是非常危险的，假设Ａ向Ｂ转帐１００元，对应sql语句如下所示

```mysql
update account set money=money+100 while name='b'; 
update account set money=money-100 while name='a';
```

当第1条sql执行完，第2条还没执行(A未提交时)，如果此时Ｂ查询自己的帐户，就会发现自己多了100元钱。如果A等B走后再回滚，B就会损失100元。



**不可重复读**

在一个事务内读取表中的某一行数据，多次读取结果不同。

例如银行想查询A帐户余额，第一次查询A帐户为200元，此时A向帐户存了100元并提交了，银行接着又进行了一次查询，此时A帐户为300元了。银行两次查询不一致，可能就会很困惑，不知道哪次查询是准的。

和脏读的区别是，脏读是读取前一事务未提交的脏数据，不可重复读是重新读取了前一事务已提交的数据。

很多人认为这种情况就对了，无须困惑，当然是后面的为准。我们可以考虑这样一种情况，比如银行程序需要将查询结果分别输出到电脑屏幕和写到文件中，结果在一个事务中针对输出的目的地，进行的两次查询不一致，导致文件和屏幕中的结果不一致，银行工作人员就不知道以哪个为准了。



**虚读(幻读)**

是指在一个事务内读取到了别的事务插入的数据，导致前后读取不一致。
事务A开启并读取到只有一条记录，还未提交，此时事务B开启事务，添加一条记录并提交，事务A再次读取发现有两条记录了，仿佛产生幻觉，这就是幻读。

==**这里注意：**不可重复读针对的是修改操作，幻读针对的是新增记录的操作==



**事务隔离性的设置**

数据库共定义了四种隔离级别：

- serializable：可避免脏读、不可重复读、虚读情况的发生。（串行化）
- repeatable read：可避免脏读、不可重复读情况的发生。（可重复读）不可以避免虚读
- read committed：可避免脏读情况发生（读已提交）
- read uncommitted：最低级别，以上情况均无法保证。(读未提交)

```mysql
set session transaction isolation level XXX #（XXX代表隔离级别）设置事务隔离级别
select @@transaction_isolation;  #查询当前事务隔离级别
```



**演示脏读**

1. 同时开启两个窗口，A和B。A代表左窗口，B代表右窗口。

2. 先登陆MySLQ服务器，分别查看当前窗口的隔离级别：发现两个窗口的隔离级别都是： REPEATABLE-READ

3. 设置A窗口的隔离级别为：read uncommitted （有脏读发生的可能）

	```mysql
	set session transaction isolation level read uncommitted;
	```

4. 在A和B窗口中都开启事物。

	```mysql
	start transaction;
	```

5. 在B窗口中完成转账的操作，但是没有提交事物。

	```mysql
	update user set money = money - 1000 where name = '张三';
	update user set money = money + 1000 where name = '赵四';
	```

6. 在A窗口中查询账户的余额。

	```mysql
	select * from user;
	```

7. 在B窗口中回滚数据

	```mysql
	rollback;
	```



**演示不可重复读（避免脏读）**

1. 设置A窗口的隔离级别为：read committed （避免脏读，不可重复读会发生）

	```mysql
	set session transaction isolation level read committed;
	```

2. 在A和B窗口中都开启事物。

	```mysql
	start transaction;
	```

3. 在B窗口中完成转账的操作，但是没有提交事物。

	```mysql
	update user set money = money - 1000 where name = '张三';
	update user set money = money + 1000 where name = '赵四';
	```

4. 在A窗口中查询账户的余额，查询的结果没有变化，说明已经避免了脏读。

	```mysql
	select * from user;
	```

5. 在B窗口中提交数据数据

	```mysql
	commit;
	```

6. B窗口的数据已经提交了，在A窗口中查询数据，发现两次查询的结果发生了变化，这就导致了不可重复读的发生。



**避免不可重复读**

1. 设置A窗口的隔离级别为： repeatable read   （避免脏读和不可重复读，但是虚读有可能发生）

	```mysql
	set session transaction isolation level repeatable read ;
	```

2. 在A和B窗口中都开启事物。

	```mysql
	start transaction;
	```

3. 在B窗口中完成转账的操作，但是没有提交事物。

	```mysql
	update user set money = money - 1000 where name = '张三';
	update user set money = money + 1000 where name = '赵四';
	```

4. 在A窗口中查询账户的余额，查询的结果没有变化，说明已经避免了脏读。

	```mysql
	select * from user;
	```

5. 在B窗口中提交数据数据

	```mysql
	commit;
	```

6. B窗口的数据已经提交了，在A窗口中查询数据，发现两次查询的结果一致，说明已经避免了不可重复读的发生。



**避免虚读**

1. 设置A窗口的隔离级别serializable 

	```mysql
	set session transaction isolation level serializable ;
	```

2. 在A和B窗口中都开启事物。

	```mysql
	start transaction;
	```

3. 在B窗口中插入一条数据（没有提交）

	```mysql
	insert into user values (null,'小凤',10000);
	```

4. 在A窗口查询数据，如果B窗口没有提交或者回滚，在A窗口是 不能查询到结果的。

	```mysql
	select * from user;
	```



## 12.3、事务的传播属性

- **PROPAGATION_REQUIRED** – 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择
- **PROPAGATION_SUPPORTS** – 支持当前事务，如果当前没有事务，就以非事务方式执行
- **PROPAGATION_MANDATORY** – 支持当前事务，如果当前没有事务，就抛出异常
- **PROPAGATION_REQUIRES_NEW** – 新建事务，如果当前存在事务，把当前事务挂起
- **PROPAGATION_NOT_SUPPORTED** – 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
- **PROPAGATION_NEVER** – 以非事务方式执行，如果当前存在事务，则抛出异常
- **PROPAGATION_NESTED** – 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作



# 13、索引

为什么要使用索引？

索引是 MySQL 中一种十分重要的数据库对象。它是数据库性能调优技术的基础，常用于实现数据的快速检索。

索引就是根据表中的一列或若干列按照一定顺序建立的列值与记录行之间的对应关系表，实质上是一张描述索引列的列值与原表中记录行之间一一对应关系的有序表。

在 MySQL 中，通常有以下两种方式访问数据库表的行数据：

- 顺序访问
	顺序访问是在表中实行全表扫描，从头到尾逐行遍历，直到在无序的行数据中找到符合条件的目标数据。这种方式实现比较简单，但是当表中有大量数据的时候，效率非常低下。例如，在几千万条数据中查找少量的数据时，使用顺序访问方式将会遍历所有的数据，花费大量的时间，显然会影响数据库的处理性能。

- 索引访问

	索引访问是通过遍历索引来直接访问表中记录行的方式。使用这种方式的前提是对表建立一个索引，在列上创建了索引之后，查找数据时可以直接根据该列上的索引找到对应记录行的位置，从而快捷地查找到数据。索引存储了指定列数据值的指针，根据指定的排序顺序对这些指针排序。

例如，在学生基本信息表 students 中，如果基于 student_id 建立了索引，系统就建立了一张索引列到实际记录的映射表，当用户需要查找 student_id 为 12022 的数据的时候，系统先在 student_id 索引上找到该记录，然后通过映射表直接找到数据行，并且返回该行数据。因为扫描索引的速度一般远远大于扫描实际数据行的速度，所以采用索引的方式可以大大提高数据库的工作效率。



**创建索引**

可以使用专门用于创建索引的 CREATE INDEX 语句在一个已有的表上创建索引，但该语句不能创建主键。

语法格式：

```mysql
CREATE <索引名> ON <表名> (<列名> [<长度>] [ ASC | DESC])
```

语法说明如下：

- <索引名>：指定索引名。一个表可以创建多个索引，但每个索引在该表中的名称是唯一的。
- <表名>：指定要创建索引的表名。
- <列名>：指定要创建索引的列名。通常可以考虑将查询语句中在 JOIN 子句和 WHERE 子句里经常出现的列作为索引列。
- <长度>：可选项。指定使用列前的 length 个字符来创建索引。使用列的一部分创建索引有利于减小索引文件的大小，节省索引列所占的空间。在某些情况下，只能对列的前缀进行索引。索引列的长度有一个最大上限 255 个字节（MyISAM 和 InnoDB 表的最大上限为 1000 个字节），如果索引列的长度超过了这个上限，就只能用列的前缀进行索引。另外，BLOB 或 TEXT 类型的列也必须使用前缀索引。
- ASC|DESC：可选项。ASC指定索引按照升序来排列，DESC指定索引按照降序来排列，默认为ASC。



**删除索引**

```mysql
ALTER TABLE TABLE_name DROP index index_name;
```



**显示索引信息**

可以使用show index命令来列出表中的相关的索引信息，可以添加\G来格式化输出信息

```mysql
SHOW INDEX FROM <表名> [ FROM <数据库名>];\G
```

语法说明如下：

- <表名>：要显示索引的表。
- <数据库名>：要显示的表所在的数据库。

该语句会返回一张结果表，该表有如下几个字段，每个字段所显示的内容说明如下:

- Table：表的名称。
- Non_UNIQUE：用于显示该索引是否是唯一索引。若不是唯一索引，则该列的值显示为 1；若是唯一索引，则该列的值显示为 0。
- Key_name：索引的名称。
- Seq_in_index：索引中的列序列号，从 1 开始计数。
- Column_name：列名称。
- Collation：显示列以何种顺序存储在索引中。在 MySQL 中，升序显示值“A”（升序），若显示为 NULL，则表示无分类。
- Cardinality：显示索引中唯一值数目的估计值。基数根据被存储为整数的统计数据计数，所以即使对于小型表，该值也没有必要是精确的。基数越大，当进行联合时，MySQL 使用该索引的机会就越大。
- Sub_part：若列只是被部分编入索引，则为被编入索引的字符的数目。若整列被编入索引，则为 NULL。
- Packed：指示关键字如何被压缩。若没有被压缩，则为 NULL。
- Null：用于显示索引列中是否包含 NULL。若列含有 NULL，则显示为 YES。若没有，则该列显示为 NO。
- Index_type：显示索引使用的类型和方法（BTREE、FULLTEXT、HASH、RTREE）。
- Comment：显示评注。

# 14、临时表

MySQL临时表在需要保存一些临时数据时是非常有用的。临时表只在当前连接可见，当关闭连接时，MySQL会自动删除表并释放所有空间。

实例：

<img src="../Images/MySQL/image-20200531210628430.png" alt="image-20200531210628430" style="zoom:80%;" />

当使用SHOW TABLES命令显示数据表列表时，将无法看到SalesSummary表。

如果退出当前MySQL会话，再使用SELECT命令来读取原先创建的临时表数据，将会发现数据库中没有该表的存在，因为在退出时该临时表已经被销毁了



**删除MySQL临时表**

默认情况下，在断开与数据库的连接后，临时表就会自动销毁，当然也可以在当前MySQL会话使用DROP TABLE命令来手动删除临时表。

<img src="../Images/MySQL/image-20200531210706162.png" alt="image-20200531210706162" style="zoom:80%;" />

# 15、序列的使用

MySQL序列是一组整数：1,2,3,…,由于一张数据表只能有一个字段自增主键，如果想实现其他字段也实现自动增加，就可以使用MySQL序列来实现。



**使用ATUO_INCREMENT**

MySQL中最简单使用序列的方法就是使用MySQL auto_increment来定义列



**重置序列**

如果删除了数据表中的多条记录，并希望对剩下数据的AUTO_INCREMENT列进行重新排列，那么可以通过删除自增的列，然后重新添加来实现。不过该操作要非常小心，如果在删除的同时又有新纪录添加，有可能会出现数据混乱，操作如下所示：

<img src="../Images/MySQL/image-20200531210829143.png" alt="image-20200531210829143" style="zoom:80%;" />



**设置序列的开始值**

一般情况下序列的开始值为1，但如果需要指定一个开始值100，那么可以通过以下语句来实现：

<img src="../Images/MySQL/image-20200531210858494.png" alt="image-20200531210858494" style="zoom:80%;" />

或者也可以在表创建成功后，通过以下语句来实现：

```mysql
ALTER TABLE <表名> AUTO_INCREMENT=100;
```

# 16、处理重复数据

有些MySQL数据表中可能存在重复的数据，有些情况允许重复数据的存在，但有时候也需要删除这些重复的数据



**防止表中出现重复数据**

可以在MySQL数据表中设置指定的字段为PRIMARY KEY（主键）或者UNIQUE（唯一）索引来保证数据的唯一性。

比如想设置数据表中两个字段的数据不能重复，可以设置双主键模式来设置数据的唯一性。

如果设置了唯一索引，那么在插入重复数据时，SQL语句将无法执行成功，并抛出错误。

INSERT IGNORE INTO与INSERT INTO的区别就是INSERT IGNORE会忽略数据库中已经存在的数据，如果数据库没有数据，就插入新的数据，如果有数据的话就跳过这条数据。这样就可以保留数据库中已经存在数据，达到在间隙中插入数据的目的

INSERT IGNORE INTO当插入数据时，在设置了记录的唯一性后，如果插入重复数据，将不会返回错误，只以警告形式返回。而REPLACE INTO如果存在PARIMARY KEY或UNIQUE相同的记录，则先删除掉。再插入新数据。

另一种设置数据的唯一性方法是添加一个UNIQUE索引



**统计重复数据**

以下将统计表中first_name和last_name的重复记录数：

<img src="../Images/MySQL/image-20200531211027701.png" alt="image-20200531211027701" style="zoom:80%;" />

以上查询语句将返回persion_tbl表中重复的记录数。一般情况下，查询重复的值，请执行以下操作：

- 确定哪一列包含的值可能会重复
- 在列选择列表使用COUNT(*)列出的那些列
- 在GROUP BY组居中列出的列
- HAVING子句设置重复数大于1



**过滤重复数据**

如果需要读取不重复的数据可以在SELECT语句中使用DISTINCT关键字来过滤重复数据

<img src="../Images/MySQL/image-20200531211202605.png" alt="image-20200531211202605" style="zoom:80%;" />

也可以使用GROUP BY来读取数据表中不重复的数据

<img src="../Images/MySQL/image-20200531211213766.png" alt="image-20200531211213766" style="zoom:80%;" />



**删除重复数据**

如果想删除数据表中的重复数据，可以使用以下SQL语句：

<img src="../Images/MySQL/image-20200531211248727.png" alt="image-20200531211248727" style="zoom:80%;" />

当然也可以在数据表中添加INDEX（索引）和PRIMARY KEY（主键）这种简单的方法来删除表中的重复记录，方法如下：

<img src="../Images/MySQL/image-20200531211258198.png" alt="image-20200531211258198" style="zoom:80%;" />

# 17、函数

## 17.1、时间日期相关

### 17.1.1、日期差

**天数差**

```MYSQL
SELECT DATEDIFF(DATE_FORMAT(NOW(), '%Y-%m-%d'),DATE_FORMAT('2018-09-10','%Y-%m-%d'))
```

结果：

<img src="../Images/MySQL/20180917110456408370.png" alt="技术分享图片" style="zoom:80%;FLOAT:LEFT" />

```MYSQL
SELECT TIMESTAMPDIFF(DAY,'2017-05-01', DATE_FORMAT(NOW(), '%Y-%m-%d'))
```

 结果：

<img src="../Images/MySQL/20180917110456532385.png" alt="技术分享图片" style="zoom:80%;FLOAT:LEFT" />



**年数差**

```mysql
SELECT TIMESTAMPDIFF(YEAR,'2017-05-01', DATE_FORMAT(NOW(), '%Y-%m-%d'))
```

结果：

<img src="../Images/MySQL/20180917110456254083.png" alt="技术分享图片" style="zoom:80%;float:left" />



**月份差**

```mysql
SELECT TIMESTAMPDIFF(MONTH,'2017-05-01', DATE_FORMAT(NOW(), '%Y-%m-%d'))
```

结果：

<img src="../Images/MySQL/20180917110456254083.png" alt="技术分享图片" style="zoom:80%;FLOAT:LEFT" />



### 17.1.2、当前日期

```mysql
SELECT NOW(),CURDATE(),CURTIME();
```

结果类似：

<img src="../Images/MySQL/755765-20190227093739650-528895218.png" alt="img" style="zoom:80%;float:left" />



### 17.1.3、前一天和后一天

**获取前一天**

```mysql
SELECT DATE_SUB(CURDATE(),INTERVAL 1 DAY);
```

当前日期2018-09-17，结果：

<img src="../Images/MySQL/20180917110455670136.png" alt="技术分享图片" style="zoom:80%;float:left" />



**获取后一天**

```mysql
SELECT DATE_SUB(CURDATE(),INTERVAL -1 DAY);
```

当前日期2018-09-17，结果：

<img src="../Images/MySQL/20180917110455998240.png" alt="技术分享图片" style="zoom:80%;float:left" />



### 17.1.4、倒数第几天

LAST_DAY()返回某月最后一天

```mysql
LAST_DAY(<日期>) #最后一天
LAST_DAY(<日期>)-1 #倒数第二天
LAST_DAY(<日期>)-2 #倒数第三天
```



### 17.1.5、提取年月日

EXTRACT() 函数用于返回日期/时间的单独部分，比如年、月、日、小时、分钟等等。

语法：

```mysql
EXTRACT(unit FROM <日期>)
```

date 参数是合法的日期表达式。unit 参数可以是下列的值：

- MICROSECOND（微秒：一百万分之一秒）
- SECOND
- MINUTE
- HOUR
- DAY
- WEEK
- MONTH
- QUARTER
- YEAR
- SECOND_MICROSECOND
- MINUTE_MIRCROSECOND
- MINUTE_SECOND
- HOUR_MICROSECOND
- HOUR_SECOND
- HOUR_MINUTE



## 17.2、字符串相关

**SUBSTRING()**

SUBSTRING函数从特定位置开始的字符串返回一个给定长度的子字符串。 MySQL提供了各种形式的子串功能。

语法：

```mysql
SUBSTRING(string,position,length);
SUBSTRING(string FROM position FOR length);
```

参数：

- string：要提取的字符串
- position：起始下标，可以是负数，MySQL下标从1开始。
- length：长度



**CONCAT()**

返回连接参数产生的字符串，一个或多个待拼接的内容，任意一个为NULL则返回值为NULL。



## 17.3、数字相关

### 17.3.1、保留两位小数

ROUND(x,d)，四舍五入。ROUND(x) ，其实就是ROUND(x,0)，也就是默认d为0。

```sql
select round(110.35,1);
# 110.4
```



TRUNCATE(x,d)，直接截取需要保留的小数位 。

```sql
select TRUNCATE(110.35,1);
# 110.3
```



FORMAT（x,d），四舍五入，保留d位小数，返回string类型 

```sql
select FORMAT(110.35,1);
# 110.4
```



# 18、JDBC

## 18.1、第一个JDBC程序

**JDBC简介**

JDBC全称为：Java DataBase Connectivity（java数据库连接）。

SUN公司为了简化、统一对数据库的操作，定义了一套Java操作数据库的规范，称之为JDBC。



实例：从user表中读取数据，并打印在命令行窗口中。

1. 搭建实验环境 ：

	- 在mysql中创建一个库，并创建user表和插入表的数据。
	- 新建一个Java工程，并导入数据驱动。

2. 编写程序，在程序中加载数据库驱动

	```java
	DriverManager.registerDriver(Driver driver);
	```

3. 建立连接(Connection)

	```java
	Connection conn = DriverManager.getConnection(url,user,pass); 
	```

4. 创建用于向数据库发送SQL的Statement对象，并发送

	```java
	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery(sql);
	```

5. 从代表结果集的ResultSet中取出数据，打印到命令行窗口

6. 断开与数据库的连接，并释放相关资源Jdbc程序

运行完后，切记要释放程序在运行过程中，创建的那些与数据库进行交互的对象，这些对象通常是ResultSet, Statement和Connection对象。

特别是Connection对象，它是非常稀有的资源，用完后必须马上释放，如果Connection不能及时、正确的关闭，极易导致系统宕机。Connection的使用原则是尽量晚创建，尽量早的释放。

为确保资源释放代码能运行，资源释放代码也一定要放在finally语句中



### 18.1.1、DriverManager

DriverManager类的是管理一组 JDBC 驱动程序的基本服务。作用有两个，如下：

1. 注册驱动

	static void registerDriver(Driver driver)方法进行注册驱动
	但是这种方式不是很好，原因如下：

	让应用程序依赖具体的驱动包，如果是切换数据库还需要修改源代码才能完成。调用该方法导致驱动被注册两次，通过查看驱动的源代码能发现次问题。所以在实际开发中不会这么做。

	具体做法如下：

	```java
	Class.forName("com.mysql.jdbc.Driver");
	```

2. 获取链接对象

数据库URL

URL用于标识数据库的位置，程序员通过URL地址告诉JDBC程序连接哪个数据库，URL的写法为：

<img src="../Images/MySQL/image-20200603120231842.png" alt="image-20200603120231842" style="zoom:80%;" />



### 18.1.2、Connection

Jdbc程序中的Connection，它用于代表数据库的链接，Collection是数据库编程中最重要的一个对象，客户端与数据库所有交互都是通过connection对象完成的，

有两个作用：

1. 创建执行SQL语句的对象：
	- createStatement()：创建向数据库发送sql的statement对象
	- prepareStatement(sql) ：创建向数据库发送预编译sql的PreparedSatement对象
	- prepareCall(sql)：创建执行存储过程的CallableStatement对象。
2. 管理事物：
	- setAutoCommit(boolean autoCommit)：设置事务是否自动提交。
	- commit() ：在链接上提交事务。 ---与事务相关！！
	- rollback() ：在此链接上回滚事务。



### 18.1.3、Statement

Jdbc程序中的Statement对象用于向数据库发送SQL语句， Statement对象常用方法：

1. 执行SQL语句
	- executeQuery(String sql) ：用于向数据发送查询语句。
	- executeUpdate(String sql)：用于向数据库发送insert、update或delete语句
	- execute(String sql)：用于向数据库发送任意sql语句
2. 执行批处理
	- addBatch(String sql) ：把多条sql语句放到一个批处理中。
	- executeBatch()：向数据库发送一批sql语句执行。



**PreparedStatement**

PreparedStatement是Statement的子接口，它的实例对象可以通过调用Connection.preparedStatement(sql)方法获得，相对于Statement对象而言：

- PreperedStatement可以避免SQL注入的问题。
- Statement会使数据库频繁编译SQL，可能造成数据库缓冲区溢出。PreparedStatement 可对SQL进行预编译，从而提高数据库的执行效率。
- 并且PreperedStatement对于sql中的参数，允许使用占位符的形式进行替换，简化sql语句的编写。



### 18.1.4、ResultSet

Jdbc程序中的ResultSet用于代表Sql语句的执行结果。Resultset封装执行结果时，采用的类似于表格的方式。ResultSet 对象维护了一个指向表格数据行的游标cursor，初始的时候，游标在第一行之前，调用ResultSet.next() 方法，可以使游标指向具体的数据行，进而调用方法获取该行的数据。

ResultSet既然用于封装执行结果的，所以该对象提供的大部分方法都是用于获取数据的get方法：

1. 获取任意类型的数据
	- getObject(int index)
	- getObject(string columnName)
2. 获取指定类型的数据
	- getString(int index)
	- getString(String columnName)



**ResultSet滚动结果集**

使用`createStatement(int resultSetType, int resultSetConcurrency);`可以设置滚动结果集的策略：

- resultSetType   ：代表滚动结果集的类型
- resultSetConcurrency ：代表结果集的并发策略

常用的结果集类型

- TYPE_FORWARD_ONLY   ：结果集只能向下
- TYPE_SCROLL_INSENSITIVE  ：可以滚动，不能修改记录
- TYPE_SCROLL_SENSITIVE  ：可以滚动，可以修改记录

常用的并发策略

- CONCUR_READ_ONLY    ：只读的，不能修改
- CONCUR_UPDATABLE   ：结果集可以修改

ResultSet 滚动结果集方法

- next()：移动到下一行
- previous()：移动到前一行
- absolute(int row)：移动到指定行
- beforeFirst()：移动resultSet的最前面
- afterLast() ：移动到resultSet的最后面
- updateRow() ：更新行数据



## 18.2、JDBC进行CRUD

Jdbc中的statement对象用于向数据库发送SQL语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。

Statement对象的executeUpdate方法，用于向数据库发送增、删、改的sql语句，executeUpdate执行完后，将会返回一个整数(即增删改语句导致了数据库几行数据发生了变化)。

Statement.executeQuery方法用于向数据库发送查询语句，executeQuery方法返回代表查询结果的ResultSet对象。



**CRUD操作-create**

使用executeUpdate(String sql)方法完成数据添加操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "insert into user(...) values(...)";
int num = st.executeUpdate(sql);
if (num > 0) {
    System.out.println("插入成功！");
}
```



**CRUD操作-updata**

使用executeUpdate(String sql)方法完成数据修改操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "update user set name='' where name=''";
int num = st.executeUpdate(sql);
if (num > 0) {
    System.out.println("修改成功！");
}
```



**CRUD操作-delete**

使用executeUpdate(String sql)方法完成数据删除操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "delete from user where id=1";
int num = st.executeUpdate(sql);
if (num > 0) {
    System.out.println("删除成功！");
}
```



**CRUD操作-Retrieve/Read**

使用executeQuery(String sql)方法完成数据查询操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "select * from user where id=1";
ResultSet rs = st.executeQuery(sql);
while (rs.next()) {
    // 根据获取列的数据类型，分别调用ts的响应方法
    // 映射到java对象中
}
```



==使用JDBC完成数据的CURD操作具有很多重复性的代码，像驱动加载、获取链接、释放资源等，所以可以封装成工具类方便使用。==



## 18.3、JDBC处理大数据

在实际开发中，程序需要把大文本 TEXT 或二进制数据 BLOB保存到数据库。TEXT是mysql叫法，Oracle中叫CLOB

基本概念：大数据也称之为LOB(Large Objects)，LOB又分为：CLOB和BLOB

- CLOB用于存储大文本。
- BLOB用于存储二进制数据，例如图像、声音、二进制文等。

对MySQL而言只有BLOB，而没有CLOB，MySQL存储大文本采用的是TEXT，Text和BLOB分别又分为：

- TINYTEXT(255)、TEXT(64k)、MEDIUMTEXT(16M)和LONGTEXT(4G)
- TINYBLOB(255)、BLOB(64k)、MEDIUMBLOB(16M)和LONGBLOB(4G)

对于MySQL中的Text类型，可调用如下方法设置：

```java
PreparedStatement.setCharacterStream(index, reader, length);
// 注意length长度需设置，并且设置为int型
// 当包过大时修改配置：[mysqld] max_allowed_packet-64M
```

对MySQL中的Text类型，可调用如下方法获取：

```java
reader = resultSet.getCharacterStream(i);
// 等价于
reader = resultSet.getClob(i).getCharacterStream();
```

对于MySQL中的BLOB类型，可调用如下方法设置：

```java
PreparedStatement.setBinaryStream(i, inputStream, length);
```

对MySQL中的BLOB类型，可调用如下方法获取：

```java
InputStream in = resultSet.getBinaryStream(i);
InputStream in = resultSet.getBlob(i).getBinaryStream();
```



**使用JDBC进行批处理**

业务场景：当需要向数据库发送一批SQL语句执行时，应避免向数据库一条条的发送执行，而应采用JDBC的批处理机制，以提升执行效率。

- Statement.addBatch(sql) ：添加批处理命令
- executeBatch()方法：执行批处理命令
- clearBatch()方法：清除批处理命令

```java
conn = jdbcUtil.getConnection();
String sql = "insert into person(name,password,email,birthday) values(?,?,?,?,?)";
st = conn.prepareStatement(sql);
for (int i = 0; i < 50000; i++) {
    st.setString(1, "aaa" + i);
    st.setString(2, "123" + i);
    st.setString(3, "aaa" + i + "@sina.com");
    st.setDate(4, new Date(1980, 10, 10));

    st.addBatch();
    if (i % 1000 == 0) {
        st.executeBatch();
        st.clearBatch();
    }
}
st.executeBatch();
```



## 18.4、JDBC事务控制管理

当Jdbc程序向数据库获得一个Connection对象时，默认情况下这个Connection对象会自动向数据库提交在它上面发送的SQL语句。若想关闭这种默认提交方式，让多条SQL在一个事务中执行，可使用下列语句：

```java
Connection.setAutoCommit(false); //  相当于start transaction
Connection.rollback();  		//	rollback
Connection.commit();  			//	commit
```



**JDBC中设置事务的隔离级别**

Connection接口中定义事务隔离级别四个常量：

- static int TRANSACTION_READ_COMMITTED 
	指示不可以发生脏读的常量；不可重复读和虚读可以发生。 

- static int TRANSACTION_READ_UNCOMMITTED 
	指示可以发生脏读 (dirty read)、不可重复读和虚读 (phantom read) 的常量。 

- static int TRANSACTION_REPEATABLE_READ 
	指示不可以发生脏读和不可重复读的常量；虚读可以发生。 

- static int TRANSACTION_SERIALIZABLE 

	指示不可以发生脏读、不可重复读和虚读的常量。

通过 void setTransactionIsolation(int level) 设置数据库隔离级别



# 19、数据库设计表关系 

**一对一**

一张表的一条记录一定只能与另外一张表的一条记录进行对应，反之亦然。

学生表：姓名，性别，年龄，身高，体重，籍贯，家庭住址，紧急联系人
其中姓名、性别、年龄、身高，体重属于常用数据，但是籍贯、住址和联系人为不常用数据
如果每次查询都是查询所有数据，不常用的数据就会影响效率，实际又不用
常用信息表：ID(P)，姓名，性别，年龄，身高，体重
不常用信息表：ID(P)，籍贯，家庭住址，紧急联系人

解决方案：将常用的和不常用的信息分享存储，分成两张表
不常用信息表和常用信息表，保证不常用信息表与常用信息表能够对应上：找一个具有唯一性的字段来共同连接两张表。
一个常用表中的一条记录永远只能在一张不常用表中匹配一条记录，反之亦然。



**一对多**

一张表中有一条记录可以对应另外一张表中的多条记录；但是反过来，另外一张表的一条记录只能对应第一张表的一条记录，这种关系就是一对多或多对一

母亲表：ID(P),名字，年龄，性别
孩子表：ID(P),名字，年龄，性别
以上关系：一个妈妈可以在孩子表中找到多条记录（也可能是一条），但是一个孩子只能找到一个妈妈，这是是一种典型的一对多的关系。

解决方案：在某一张表中增加一个字段，能够找到另外一张表中的记录:在孩子表中增加一个字段指向母亲表，因为孩子表的记录只能匹配到一条母亲表的记录。
母亲表：ID(P),名字，年龄，性别
孩子表：ID(P),名字，年龄，性别，母亲表ID（母亲表主键）



**多对多**

一对表中（A）的一条记录能够对应另外一张表（B）中的多条记录；同时B表中的一条记录也能对应A表中的多条记录

老师和学生
老师表 T_ID(P),姓名，性别
学生表 S_ID(P),姓名，性别
一个老师教过多个学生，一个学生也被多个老师教过

解决方案：增加一张中间关系表
老师与学生的关系表：ID(P),T_ID,S_ID
老师表与中间表形成一对多的关系，而中间表是多表；维护了能够唯一找到一表的关系；
同样的学生表与中间表也是一个一对多的关系;
学生找老师：找出学生ID--->中间表寻找匹配记录（多条）--->老师表匹配（一条）
老师找学生：找出老师ID--->中间表寻找匹配记录（多条）--->学生表匹配（一条）



# 20、连接阿里云数据库

## 20.1、安装MySQL 8.0

**系统 Ubuntu  20.04 64位**

**安装数据库**

1.  查看有没有安装 MySQL：

	```shell
	dpkg -l | grep mysql
	```

2. 安装MySQL：

	```shell
	apt install mysql-server
	```

3. 安装完成之后可以使用如下命令来检查是否安装成功：

	```shell
	netstat -tap | grep mysql
	```

	通过上述命令检查之后，如果看到有 mysql 的 socket 处于 LISTEN 状态则表示安装成功

4. 登录 mysql 数据库可以通过如下命令：

	```shell
	mysql -u root -p
	```

	现在是 mysql 数据库是没有密码的，Enter password: 处直接回车，就能够进入 mysql 数据库。



**初始化数据库**

接下来，为了确保数据库的安全性和正常运转，对数据库进行初始化操作。这个初始化操作涉及下面5个步骤

1. 安装验证密码插件。
2. 设置root管理员在数据库中的专有密码。
3. 随后删除匿名账户，并使用root管理员从远程登录数据库，以确保数据库上运行的业务的安全性。
4. 删除默认的测试数据库，取消测试数据库的一系列访问权限。
5. 刷新授权列表，让初始化的设定立即生效。

输入`mysql_secure_installation`开始

```shell
Securing the MySQL server deployment.

Connecting to MySQL using a blank password.

VALIDATE PASSWORD COMPONENT can be used to test passwords
and improve security. It checks the strength of password
and allows the users to set only those passwords which are
secure enough. Would you like to setup VALIDATE PASSWORD component? #要安装验证密码插件吗？

Press y|Y for Yes, any other key for No: N #我选了N
Please set the password for root here.

New password: #输入root密码

Re-enter new password: #再次输入
By default, a MySQL installation has an anonymous user,
allowing anyone to log into MySQL without having to have
a user account created for them. This is intended only for
testing, and to make the installation go a bit smoother.
You should remove them before moving into a production
environment.

Remove anonymous users? (Press y|Y for Yes, any other key for No) : y #是否删除匿名用户
Success.


Normally, root should only be allowed to connect from
'localhost'. This ensures that someone cannot guess at
the root password from the network.

Disallow root login remotely? (Press y|Y for Yes, any other key for No) : n # 是否禁止root管理员从远程登录

 ... skipping.
By default, MySQL comes with a database named 'test' that
anyone can access. This is also intended only for testing,
and should be removed before moving into a production
environment.


Remove test database and access to it? (Press y|Y for Yes, any other key for No) : y #是否删除test数据库并取消它的访问权限
 - Dropping test database...
Success.

 - Removing privileges on test database...
Success.

Reloading the privilege tables will ensure that all changes
made so far will take effect immediately.

Reload privilege tables now? (Press y|Y for Yes, any other key for No) : y # 是否刷新授权表，让初始化后的设定立即生效
Success.

All done!
```



## 20.2、远程访问

在阿里云控制台连接 MySQL

```shell
mysql> use mysql;

mysql> select user,host,plugin from user;
+------------------+-----------+-----------------------+
| user             | host      | plugin                |
+------------------+-----------+-----------------------+
| debian-sys-maint | localhost | caching_sha2_password |
| mysql.infoschema | localhost | caching_sha2_password |
| mysql.session    | localhost | caching_sha2_password |
| mysql.sys        | localhost | caching_sha2_password |
| root             | localhost | auth_socket           |
+------------------+-----------+-----------------------+

mysql> update user set host='%',plugin='mysql_native_password' where user='root';

mysql> flush privileges;

# 重新设置密码
mysql> alter user'root'@'%' IDENTIFIED BY 'root';
```

> auth_socket：首先，这种验证方式不要求输入密码，即使输入了密码也不验证。这个特点让很多人觉得很不安全，实际仔细研究一下这种方式，发现还是相当安全的，因为它有另外两个限制；
>
> - 只能用 UNIX 的 socket 方式登陆，这就保证了只能本地登陆，用户在使用这种登陆方式时已经通过了操作系统的安全验证；
> - 操作系统的用户和 MySQL 数据库的用户名必须一致，例如你要登陆 MySQL 的 root 用户，必须用操作系统的 root 用户登陆。
>
> mysql8.0 引入了新特性 caching_sha2_password；这种密码加密方式客户端不支持；客户端支持的是 mysql_native_password 这种加密方式；

编辑 /etc/mysql/mysql.conf.d/mysqld.cnf 配置文件：

```shell
vim /etc/mysql/mysql.conf.d/mysqld.cnf
```

注释掉 `bind-address`：

![image-20210420120254515](../Images/MySQL/image-20210420120254515.png)

重启服务：

```bash
service mysql restart
```

测试远程访问



## 20.3、卸载

1. 首先停止 MySQL 服务

	```shell
	service mysql stop;
	service mysql status;
	```

2. 在终端中查看MySQL的依赖项：

	```shell
	dpkg --list|grep mysql
	```

3. 卸载：

	```shell
	sudo apt-get remove mysql-common
	udo apt-get autoremove --purge mysql-server-8.0
	```

4. 如果看到没有删除的文件记得手动删除：

	![image-20210420112319964](../Images/MySQL/image-20210420112319964.png)

5. 清除残留数据：

	```shell
	dpkg -l|grep ^rc|awk '{print$2}'|sudo xargs dpkg -P
	```

6. 再次查看MySQL的剩余依赖项：

	```shell
	dpkg --list|grep mysql
	```

7. 删除 MySQL 的数据库信息，位置：`/var/lib/mysql/`：

	![image-20210420112705768](../Images/MySQL/image-20210420112705768.png)

