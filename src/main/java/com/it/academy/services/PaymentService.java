package com.it.academy.services;

import com.stripe.exception.StripeException;

public interface PaymentService {
    String createStripeAccount(Long userId) throws StripeException;

    String generateOnboardingLink(String accountId) throws StripeException;

    void makePayment(Long courseId, Long userId, String cardNumber, String expMonth, String expYear, String cvc) throws StripeException;

}
