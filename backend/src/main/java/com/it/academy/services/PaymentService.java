package com.it.academy.services;

import com.it.academy.models.Subscription;
import com.stripe.exception.StripeException;

public interface PaymentService {
    void makePayment(Long subscriptionId, String cardNumber, String expMonth, String expYear, String cvc) throws StripeException;
    String createStripeAccount(Long userId) throws StripeException;
    void addBankAccount(Long userId, String bankAccountNumber, String routingNumber);
    void addDebitCardToAccount(String accountId, String cardNumber, int expMonth, int expYear, String cvc);
    String getAuthUrl();
    String generateOnboardingLink(String accountId) throws StripeException;

}
