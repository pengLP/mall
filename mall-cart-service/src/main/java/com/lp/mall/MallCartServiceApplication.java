package com.lp.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.lp.mall.cart.mapper")
public class MallCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCartServiceApplication.class, args);
    }

}
