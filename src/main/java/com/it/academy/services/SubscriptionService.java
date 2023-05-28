package com.it.academy.services;

import com.it.academy.entities.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription getById(Long id);
    List<Subscription> getAll();
    Long save(Long userId, Long courseId);
    Long deleteById(Long id);
    List<Subscription> getSubscriptionsByUserId(Long userId);

}
