package com.it.academy.services.impl;

import com.it.academy.models.Subscription;
import com.it.academy.repositories.SubscriptionRepository;
import com.it.academy.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository repo;

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
    public Long save(Subscription subscription) {
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

        subscription.setCourse(updatedSubscription.getCourse());
        subscription.setUser(updatedSubscription.getUser());
        subscription.setDateFinish(updatedSubscription.getDateFinish());
        subscription.setDateStart(updatedSubscription.getDateStart());
        return repo.save(subscription).getId();
    }
}
