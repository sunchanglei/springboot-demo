package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 *	 SpringBootApplication 是 Spring Boot 的核心注解，它是一个组合注解;
 *	 该注解组合了：@Configuration、@EnableAutoConfiguration、@ComponentScan； 若不是用 @SpringBootApplication 注解也可以使用这三个注解代替。
 *	 @EnableAutoConfiguration 让 Spring Boot 根据类路径中的 jar 包依赖为当前项目进行自动配置
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.boot"})
public class SpringbootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}
}
