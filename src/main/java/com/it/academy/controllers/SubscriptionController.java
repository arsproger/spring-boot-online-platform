package com.it.academy.controllers;

import com.it.academy.dto.SubscriptionDto;
import com.it.academy.mappers.SubscriptionMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
@AllArgsConstructor
@Tag(name = "Контроллер подписок пользователя на курсы")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        List<SubscriptionDto> subscriptions = subscriptionMapper.map(subscriptionService.getAll());
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable Long id) {
        SubscriptionDto dto = subscriptionMapper.map(subscriptionService.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/user")
    @Operation(summary = "Получение всех подписок текущего пользователя")
    public ResponseEntity<List<SubscriptionDto>> getSubscriptionsByUserId(@AuthenticationPrincipal DetailsUser detailsUser) {
        List<SubscriptionDto> subscriptions = subscriptionMapper.map(subscriptionService.getSubscriptionsByUserId(detailsUser.getUser().getId()));
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSubscriptionById(@PathVariable Long id) {
        Long deletedId = subscriptionService.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }
}
