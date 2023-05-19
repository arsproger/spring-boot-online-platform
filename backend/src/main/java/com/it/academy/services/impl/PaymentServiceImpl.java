package com.it.academy.services.impl;

import com.it.academy.config.PaymentConfig;
import com.it.academy.models.Subscription;
import com.it.academy.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentConfig paymentConfig;
    private final UserServiceImpl userService;
    @Override
    public void makePayment(Subscription subscription, String cardNumber, String expMonth, String expYear, String cvc) {

    }

    @Override
    public String createStripeAccount(Long userId) {
        return null;
    }

    @Override
    public void addBankAccount(Long userId, String bankAccountNumber, String routingNumber) {

    }

    @Override
    public void addDebitCardToAccount(String accountId, String cardNumber, int expMonth, int expYear, String cvc) {

    }

    @Override
    public String getAuthUrl() {
        return null;
    }

    @Override
    public String generateOnboardingLink(String accountId) {
        return null;
    }
}
