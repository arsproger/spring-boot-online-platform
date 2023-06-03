package com.it.academy.config;

import com.stripe.Stripe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StripeConfig {

    @Value("${stripe.secret.key}")
    private String secretKey;

    @Bean
    public String getKey() {
        return Stripe.apiKey = secretKey;
    }

}

