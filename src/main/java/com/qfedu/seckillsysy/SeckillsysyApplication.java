package com.qfedu.seckillsysy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qfedu.seckillsysy.dao")
public class SeckillsysyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillsysyApplication.class, args);
    }

}
