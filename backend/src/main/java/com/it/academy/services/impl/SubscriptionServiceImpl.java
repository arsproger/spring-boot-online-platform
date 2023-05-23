package com.it.academy.services.impl;

import com.it.academy.dao.SubscriptionDao;
import com.it.academy.models.Course;
import com.it.academy.models.Subscription;
import com.it.academy.models.User;
import com.it.academy.repositories.SubscriptionRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.SubscriptionService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final CourseService courseService;
    private final UserService userService;
    private final SubscriptionDao subscriptionDao;

    @Override
    public Subscription getById(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Subscription not found with id: " + id));
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Long save(Subscription subscription) {
        return subscriptionRepository.save(subscription).getId();
    }

    @Override
    public Subscription create(Long userId, Long courseId) {
        Course course = courseService.getById(courseId);
        User user = userService.getById(userId);
        Subscription subscription = new Subscription();
        subscription.setCourse(course);
        subscription.setUser(user);
        subscription.setCreationDate(LocalDate.now());
        subscriptionRepository.save(subscription);

        return subscription;
    }

    @Override
    public Long deleteById(Long id) {
        subscriptionRepository.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Subscription updatedSubscription) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Subscription not found with id: " + id));
        subscription.setCreationDate(updatedSubscription.getCreationDate());

        return subscriptionRepository.save(subscription).getId();
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionDao.getActiveSubscriptionsByUserId(userId);
    }

}
