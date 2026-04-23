package com.dhikrplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DhikrPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(DhikrPlusApplication.class, args);
    }
}
