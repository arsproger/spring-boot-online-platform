package com.it.academy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000") // Разрешенные источники (домены)
                .allowedOrigins("*") // Разрешенные источники (домены)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные HTTP-методы
                .allowedHeaders("Authorization", "Content-Type") // Разрешенные заголовки
                .allowCredentials(true) // Разрешить передачу учетных данных (например, куки)
                .maxAge(3600) // Максимальное время кэширования предопределенных ответов CORS
                .exposedHeaders("Authorization"); // Разрешить доступ к заголовку Authorization в ответе
    }

}

