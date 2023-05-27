package com.it.academy.services.impl;

import com.it.academy.exceptions.AppException;
import com.it.academy.entities.Subscription;
import com.it.academy.repositories.SubscriptionRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.SubscriptionService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository repo;
    private final UserService userService;
    private final CourseService courseService;

    @Override
    public Subscription getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new AppException("Subscription not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Subscription> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long userId, Long courseId) {
        Subscription subscription = Subscription.builder()
                .creationDate(LocalDate.now())
                .user(userService.getById(userId))
                .course(courseService.getById(courseId))
                .build();

        return repo.save(subscription).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Subscription updatedSubscription) {
        Subscription subscription = getById(id);

        subscription.setCreationDate(updatedSubscription.getCreationDate());

        return repo.save(subscription).getId();
    }

}
