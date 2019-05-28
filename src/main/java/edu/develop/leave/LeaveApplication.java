package edu.develop.leave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication()
@MapperScan("edu.develop.leave.dao.mapper")
@ComponentScan(basePackages = { "edu.develop.leave.component"
                                ,"edu.develop.leave.service"
                                ,"edu.develop.leave.controller"
                                ,"edu.develop.leave.config"})
@EnableTransactionManagement
public class LeaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaveApplication.class, args);
    }

}
