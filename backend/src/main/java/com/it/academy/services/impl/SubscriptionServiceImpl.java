package com.it.academy.services.impl;

import com.it.academy.models.Subscription;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.repositories.SubscriptionRepository;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository repo;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

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
                .user(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId)))
                .course(courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId)))
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
        Subscription subscription = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Subscription not found with id: " + id));

        subscription.setDateFinish(updatedSubscription.getDateFinish());
        subscription.setDateStart(updatedSubscription.getDateStart());

        return repo.save(subscription).getId();
    }

}
