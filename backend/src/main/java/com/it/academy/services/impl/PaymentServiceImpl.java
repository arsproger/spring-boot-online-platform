package com.it.academy.services.impl;

import com.it.academy.config.PaymentConfig;
import com.it.academy.models.Subscription;
import com.it.academy.models.User;
import com.it.academy.services.PaymentService;
import com.it.academy.services.SubscriptionService;
import com.it.academy.services.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.param.AccountCreateParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentConfig paymentConfig;
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @Override
    public void makePayment(Long subscriptionId, String cardNumber, String expMonth, String expYear, String cvc) throws StripeException {
        //Stripe.apiKey = paymentConfig.getSecretKey();
        Subscription subscription = subscriptionService.getById(subscriptionId);

        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);

        Map<String, Object> tokenParams = new HashMap<>();
        tokenParams.put("card", cardParams);

        Token token = Token.create(tokenParams);

        Map<String, Object> chargeParams = new HashMap<>();
        //chargeParams.put("amount", subscription.getCourse().getPrice().multiply(new BigDecimal(100)));
        chargeParams.put("amount", subscription.getCourse().getPrice().multiply(new BigDecimal(100)).intValue());
        chargeParams.put("currency", "USD");
        chargeParams.put("description", "Payment for subscription to the course " + subscription.getCourse().getName());
        chargeParams.put("source", token.getId());
        chargeParams.put("application_fee_amount", (subscription.getCourse().getPrice().multiply(new BigDecimal(0.1)).intValue()));
        //chargeParams.put("transfer_data", Collections.singletonMap("destination", subscription.getCourse().getAuthor().getStripeAccountId()));
        chargeParams.put("transfer_data", Collections.singletonMap("destination", "acct_1N7tFTDOKky9CwBJ")); //for testing

        Charge charge = Charge.create(chargeParams);
    }


    @Override
    public String createStripeAccount(Long userId) throws StripeException {
            User user = userService.getById(userId);
            String email = user.getEmail();
            String firstName = user.getName();
            String lastName = user.getSurname();

            //Stripe.apiKey = paymentConfig.getSecretKey();

            AccountCreateParams params = AccountCreateParams.builder()
                    .setType(AccountCreateParams.Type.EXPRESS)
                    .setEmail(email)
                    .setBusinessType(AccountCreateParams.BusinessType.INDIVIDUAL)
                    .setCapabilities(AccountCreateParams.Capabilities.builder()
                            .setTransfers(AccountCreateParams.Capabilities.Transfers.builder().build())
                            .build())
                    .setIndividual(
                            AccountCreateParams.Individual.builder()
                                    .setFirstName(firstName)
                                    .setLastName(lastName)
                                    .build()
                    )
                    .build();

            Account account = Account.create(params);

            user.setStripeAccountId(account.getId());
            userService.save(user);

            return account.getId();
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
