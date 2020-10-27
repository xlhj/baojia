package com.xlhj.baojia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xlhj.*.mapper", "com.xlhj.*.config"})
public class BaojiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaojiaApplication.class, args);
    }

}
