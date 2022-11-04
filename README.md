springcloud学习记录


# SpringCloud学习笔记1-10

# 1 项目地址 笔记地址

# 2 零基础微服务框架理论入门

## 2.1 什么是微服务

*   微服务是一种架构风格

*   一个应用拆分为一组小型服务

*   每个服务运行在自己的进程内，也就是可独立部署和升级

*   服务之间使用轻量级HTTP交互

*   服务围绕业务功能拆分

*   可以由全自动部署机制独立部署

*   去中心化，服务自治。服务可以使用不同的语言、不同的存储技术

## 2.2 **分布式微服务架构-落地维度**

*   服务调用

*   服务降级

*   服务注册与发先

*   服务熔断

*   负载均衡

*   服务消息队列

*   服务网关

*   配置中心管理

*   自动化构建部署

*   服务监控

*   全链路追踪

*   服务定时任务

*   调度操作

## 2.3 SpringCloud 简介

**SpringCloud=分布式微服务架构的站式解决方案，是多种微服务架构落地技术的集合体，俗称微服务全家桶**

[Spring Cloud中文网-官方文档中文版](https://www.springcloud.cc/ "Spring Cloud中文网-官方文档中文版") 包含的技术

![](image/image_jXlVO50yYy.png)

![](image/image_RfTB2mL8DW.png)

SpringCloud主流的技术栈

![](image/image_C5JFU5FO7V.png)

技术与解决内容对应关系

![](image/image_LL821UTnWp.png)

# 3.Boot和Cloud版本选型

boot的版本命名用的数字，cloud用的字母表示

*   Spring Boot 2.X 版

    *   源码地址（[https://github.com/spring-projects/spring-boot/releases/](https://github.com/spring-projects/spring-boot/releases/ "https://github.com/spring-projects/spring-boot/releases/")）

*   Spring Cloud H版

    *   源码地址([https://github.com/spring-projects/spring-cloud](https://github.com/spring-projects/spring-cloud "https://github.com/spring-projects/spring-cloud"))

    *   官网（[https://spring.io/projects/spring-cloud](https://spring.io/projects/spring-cloud "https://spring.io/projects/spring-cloud")）

*   Spring Boot 与 Spring Cloud 兼容性查看

![](image/image_ne85zG8v8Q.png)

*   json接口查看（[https://start.spring.io/actuator/info](https://start.spring.io/actuator/info "https://start.spring.io/actuator/info")）

*   接下来开发用到的组件版本

    *   Cloud - **Hoxton.SR12**（22年11月最新）

    *   Boot - **2.3.12.RELEASE**（22年11月最新）

    *   Cloud - Hoxton.SR1（本项目采用）

    *   Boot - 2.2.2.RELEASE（本项目采用）

    *   Cloud Alibaba - 2.1.0.RELEASE

    *   Java - Java 8

    *   Maven - 3.5及以上

    *   MySQL - 5.7及以上

# 4.Cloud组件停更说明

*   服务注册中心

    *   × Eureka（停更不停用）

    *   √ Zookeeper (见dubbo笔记)

    *   √ Consul

    *   √ Nacos（重点，百万级注册并发）

*   服务调用

    *   √ Ribbon

    *   √ LoadBalancer

*   服务调用2

    *   × Feign

    *   √ OpenFeign

*   服务降级

    *   × Hystrix

    *   √ resilience4j

    *   √ sentienl（重点）

*   服务网关

    *   × Zuul

    *   ! Zuul2

    *   √ gateway

*   服务配置

    *   × Config

    *   √ Nacos

*   服务总线

    *   × Bus

    *   √ Nacos

Spring Cloud中文文档（[https://www.bookstack.cn/read/spring-cloud-docs/docs-translate.md](https://www.bookstack.cn/read/spring-cloud-docs/docs-translate.md "https://www.bookstack.cn/read/spring-cloud-docs/docs-translate.md")）

# 5.父工程Project空间新建

约定>配置>编码

1.  字符编码 - Settings - File encoding

2.  注解生效激活 - Settings - Annotation Processors

3.  Java编译版本选8

4.  File Type过滤 - Settings - File Type

# 6父工程pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.yxz.springcloud</groupId>
    <artifactId>springcloud</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>cloud-provider-payment8001</module>
    </modules>

    <!-- 统一管理jar包版本 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
        <mysql.version>5.1.47</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>

    <!-- 子模块继承之后，提供作用：锁定版本+子modlue不用写groupId和version  -->
    <dependencyManagement>
        <dependencies>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR1-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud alibaba 2.1.0.RELEASE-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <optional>true</optional>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

# 7 DependencyManagement和Dependencies

Maven使用`dependencyManagement`元素来提供了一种管理依赖版本号的方式。

通常会在一个组织或者项目的最顶层的父POM中看到`dependencyManagement`元素。

使用pom.xml中的`dependencyManagement`元素能让所有在子项目中引用个依赖而不用显式的列出版本量。

Maven会沿着父子层次向上走，直到找到一个拥有`dependencyManagement`元素的项目，然后它就会使用这个`dependencyManagement`元素中指定的版本号。

这样做的**好处**就是：如果有多个子项目都引用同一样依赖，则可以避免在每个使用的子项目里都声明一个版本号，这样当想升级或切换到另一个版本时，只需要在顶层父容器里更新，而不需要一个一个子项目的修改；另外如果某个子项目需要另外的一个版本，只需要声明version就可。

*   `dependencyManagement`里只是声明依赖，**并不实现引入**，因此**子项目需要显示的声明需要用的依赖**。

*   如果不在子项目中声明依赖，是不会从父项目中继承下来的；只有在子项目中写了该依赖项,并且没有指定具体版本，才会从父项目中继承该项，并且version和scope都读取自父pom。

*   如果子项目中指定了版本号，那么会使用子项目中指定的jar版本。

***

IDEA右侧旁的Maven插件有`Toggle ' Skip Tests' Mode`按钮，这样maven可以跳过单元测试

***

父工程创建完成执行`mvn : install`将父工程发布到仓库方便子工程继承。

# 8 支付模块构建（上）

创建微服务模块套路

1.  建Module

2.  改POM

3.  写YML

4.  主启动

5.  业务类

**1.建名为cloud-provider-payment8001的Maven工程**

**2.改pom文件**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>com.yxz.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

**3.改yml文件**

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource            # 当前数据源操作类型
    driver-class-name: org.gjt.mm.mysql.Driver              # mysql驱动包 com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/db2019?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456

mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.atguigu.springcloud.entities    # 所有Entity别名类所在包


```

**4.主启动类**

```java
package com.yxz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}

```

# 9支付模块构建（中）

1.  建sql表&#x20;

2.  entities

3.  dao

4.  service

5.  controller

**1.sql语句**

```java
use db2019;
CREATE TABLE `payment` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
   `serial` varchar(200) DEFAULT '',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

insert into payment value (31,"aaabbb01")
```

**2.CommonResult返回类**

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>
{
    private Integer code;
    private String  message;
    private T data;

    public CommonResult(Integer code, String message)
    {
        this(code,message,null);
    }
}

```

**实体类Payment：**

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
```

**3.PaymentDao接口**

```java
import com.yxz.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}

```

**mybatis映射文件，路径为resources/mapper/PaymentMapper.xml**

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yxz.springcloud.dao.PaymentDao">

    <resultMap id="BaseResultMap" type="com.yxz.springcloud.entities.Payment">
            <id property="id" column="id" jdbcType="BIGINT"/>
            <result property="serial" column="serial" jdbcType="VARCHAR"/>
    </resultMap>

    <sql id="Base_Column_List">
        id,serial
    </sql>

    <!--    parameterType不写全类名会爆红，但是不影响使用-->
    <insert id="create" parameterType="com.yxz.springcloud.entities.Payment" useGeneratedKeys="true" keyProperty="id" >
        insert into payment(serial) values (#{serial});
    </insert>

    <select id="getPaymentById" parameterType="Long" resultMap="BaseResultMap">
        select  * from payment where id = #{id};
    </select>

</mapper>

```

**4.Service**

PaymentService接口

```java
import com.yxz.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}

```

PaymentServiceImpl实现类

```java
import com.yxz.springcloud.dao.PaymentDao;
import com.yxz.springcloud.entities.Payment;
import com.yxz.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}

```

**5.controller层** **（这里我用来restful风格）**

```java
import com.yxz.springcloud.entities.CommonResult;
import com.yxz.springcloud.entities.Payment;
import com.yxz.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentServiceImpl paymentService;

    @PostMapping()
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********插入结果:" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功", payment);
        } else {
            return new CommonResult(444, "没有ID = " + id + " 的对应记录", null);
        }
    }
}

```

# 10 支付模块构建（下）

postman测试

![](image/image_BjK2GNkA66.png)

![](image/image_6ApWmxp_c6.png)