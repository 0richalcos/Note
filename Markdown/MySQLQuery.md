# 【1】查询每门课程成绩都大于 80 分的学生的姓名

SQL 1：

```mysql
SELECT
    name
FROM
    stu
GROUP BY
    name
HAVING
    count(score) = sum(
        CASE
            WHEN score > 80 THEN 1
            ELSE 0
        END
    )
```

SQL 2：

```mysql
SELECT
    name
FROM
    stu
GROUP BY
    name
HAVING
    name NOT IN (
        SELECT
            name
        FROM
            stu
        WHERE
            score < 80
    )
```

SQL 3：

```mysql
SELECT
    name
FROM
    stu
GROUP BY
    name
HAVING
    min(score) >= 80
```