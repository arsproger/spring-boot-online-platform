package com.it.academy.services;

import com.it.academy.models.Subscription;

public interface PaymentService {
    void makePayment(Subscription subscription, String cardNumber, String expMonth, String expYear, String cvc);
    String createStripeAccount(Long userId);
    void addBankAccount(Long userId, String bankAccountNumber, String routingNumber);
    void addDebitCardToAccount(String accountId, String cardNumber, int expMonth, int expYear, String cvc);
    String getAuthUrl();
    String generateOnboardingLink(String accountId);

}
