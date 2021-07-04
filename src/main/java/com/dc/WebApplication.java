package com.dc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/25 17:18
 */
// 指定该路径下的所有接口、类，开启扫描，即可使用注解
@SpringBootApplication(scanBasePackages = "com.dc.*")
// 指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
// 可以指定要扫描的dao接口类的路径，可以在启动类中添加此注解，可替代@Mapper注解（此模块内dao接口类不用都添加@Mapper注解）
// 会生成一个：MapperFactoryBean
@MapperScan(basePackages = "com.dc.repository",annotationClass = Mapper.class)
// 添加事务控制。如果业务层有需要事务进行控制的，则在业务层添加@Transactional注解
@EnableTransactionManagement
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
