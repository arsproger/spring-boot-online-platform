package com.it.academy.services.impl;

import com.it.academy.models.Subscription;
import com.it.academy.repositories.SubscriptionRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.SubscriptionService;
import com.it.academy.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
                () -> new EntityNotFoundException("Subscription not found with id: " + id));
    }

    @Override
    public List<Subscription> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long userId, Long courseId) {
        Subscription subscription = Subscription.builder()
                .dateStart(LocalDate.now())
                .user(userService.getById(userId))
                .course(courseService.getById(courseId))
                .build();

        return repo.save(subscription).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Subscription updatedSubscription) {
        Subscription subscription = getById(id);

        subscription.setDateStart(updatedSubscription.getDateStart());

        return repo.save(subscription).getId();
    }

}
