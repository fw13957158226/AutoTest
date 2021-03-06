package com.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * Created by fengwei on 2020/2/20.
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.course.mapper"})
public class Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context= SpringApplication.run(Application.class);
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
