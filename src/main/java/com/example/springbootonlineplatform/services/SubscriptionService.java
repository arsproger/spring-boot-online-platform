package com.example.springbootonlineplatform.services;

import com.example.springbootonlineplatform.models.Subscription;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SubscriptionService {
    Subscription getById(Long id);
    List<Subscription> getAll();
    Long save(Subscription subscription);
    Long deleteById(Long id);
    Long update(Long id, Subscription subscription);
}
