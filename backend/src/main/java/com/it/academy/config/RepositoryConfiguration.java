package com.it.academy.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.it.academy.models"})
@EnableJpaRepositories(basePackages = {"com.it.academy.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {

}
