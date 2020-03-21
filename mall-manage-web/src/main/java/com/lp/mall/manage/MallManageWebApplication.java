package com.lp.mall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MallManageWebApplication {

    public static void main(String[] args) {
        Map<Integer , Integer> map = new HashMap<>();
        SpringApplication.run(MallManageWebApplication.class, args);
    }

}
