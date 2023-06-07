package com.it.academy.services.impl;

import com.it.academy.dao.SubscriptionDao;
import com.it.academy.entities.Subscription;
import com.it.academy.exceptions.AppException;
import com.it.academy.repositories.SubscriptionRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.SubscriptionService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final CourseService courseService;
    private final SubscriptionDao subscriptionDao;

    @Override
    public Subscription getById(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(
                () -> new AppException("Subscription not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Long save(Long userId, Long courseId) {
        Subscription subscription = Subscription.builder()
                .creationDate(LocalDate.now())
                .user(userService.getById(userId))
                .course(courseService.getById(courseId))
                .build();

        return subscriptionRepository.save(subscription).getId();
    }

    @Override
    public Long deleteById(Long id) {
        subscriptionRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionDao.getSubscriptionsByUserId(userId);
    }

}
