//package com.it.academy.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//@Configuration
//@EnableWebMvc
//@EnableSwagger2
//public class SwaggerConfig {
//    @Bean
//    public Docket api() { // http://localhost:8080/swagger-ui/index.html
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.it.academy.controllers"))
//                .paths(PathSelectors.any())
//                .build()
//                        .apiInfo(new ApiInfoBuilder()
//                                .title("Online Platform")
//                                .description("API endpoints for Online Platform")
//                                .build());
//    }
//
//}
