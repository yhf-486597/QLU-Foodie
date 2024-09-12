package com.foodie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FoodieApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodieApplication.class, args);
        log.info("server start");
    }
}
