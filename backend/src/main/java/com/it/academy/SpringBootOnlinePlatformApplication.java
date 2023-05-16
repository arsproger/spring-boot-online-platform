package com.it.academy;

import com.it.academy.services.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
@EnableCaching
public class SpringBootOnlinePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOnlinePlatformApplication.class, args);
    }

}
