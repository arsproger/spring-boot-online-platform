package com.it.academy;

import com.it.academy.services.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class SpringBootOnlinePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOnlinePlatformApplication.class, args);
    }

}
