![](https://raw.githubusercontent.com/937447974/Blog/master/Resources/2016110201.png)

![Language: Java](https://img.shields.io/badge/language-Java-orange.svg?style=flat)
![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/com.github.937447974/yjcocoa/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.937447974/yjcocoa)
![QQ群](https://img.shields.io/badge/QQ群-557445088-blue.svg?style=flat)

YJ 系列 Maven 开源库

**Maven 安装**

```pom
<dependency>
    <groupId>com.github.937447974</groupId>
    <artifactId>yjcocoa</artifactId>
    <version>17.11.1</version>
</dependency>
```

# 1 MyBatis

mybatis 动态数据源，支持一主多从架构，包含自动事务管理。

默认连接从库，切换主库如下所示。

```java
DynamicDataSourceHolder.setMaster(true, () -> { // 主库切换
    System.out.println("切换到主库");
});
```

----------

# <a id="Appendix">Appendix

## Author

姓名：阳君

QQ：937447974

YJ技术支持群：557445088

职位：滴滴 iOS 高级工程师

如果你觉得这个框架很赞，请点击右上角的Star按钮；如果你对我的框架感兴趣，并想持续获得本人最新的框架源文件，欢迎点击右上角的Fork按钮。

如果你也想来和我们一起在滴滴从事iOS研发工作，欢迎投递简历到937447974@qq.com。

## Copyright

CSDN：http://blog.csdn.net/y550918116j

GitHub：https://github.com/937447974