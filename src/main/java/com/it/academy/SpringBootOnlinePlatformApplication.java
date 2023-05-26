package com.it.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootOnlinePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOnlinePlatformApplication.class, args);
    }

}
