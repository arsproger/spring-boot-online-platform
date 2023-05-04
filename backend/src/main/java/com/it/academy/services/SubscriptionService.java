package com.it.academy.services;

import com.it.academy.models.Subscription;
import com.it.academy.models.User;

import java.util.List;

public interface SubscriptionService {
    Subscription getById(Long id);

    List<Subscription> getAll();

    Long save(Subscription subscription);

    Long deleteById(Long id);

    Long update(Long id, Subscription subscription);

    List<User> getUserByCourseId(Long id);
}
