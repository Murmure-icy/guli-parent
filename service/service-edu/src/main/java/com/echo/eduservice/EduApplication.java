package com.echo.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/03 10:07
 * @Description: com.echo.eduservice
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.echo"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}