package com.it.academy.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PaymentConfig {
    @Value("${stripe.secret.key}")
    private String secretKey;
    @Value("${stripe.public.key}")
    private String publicKey;
}
