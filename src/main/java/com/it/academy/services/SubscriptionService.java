package com.it.academy.services;

import com.it.academy.models.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription getById(Long id);

    List<Subscription> getAll();

    Long save(Long userId, Long courseId);

    Long deleteById(Long id);

    Long update(Long id, Subscription subscription);

}