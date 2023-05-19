package com.it.academy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    @Value("${stripe.secret.key}")
    private String secretKey;
    @Value("${stripe.public.key}")
    private String publicKey;
}
