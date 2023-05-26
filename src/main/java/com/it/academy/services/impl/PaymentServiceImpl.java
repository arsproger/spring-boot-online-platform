package com.it.academy.services.impl;

import com.it.academy.models.Subscription;
import com.it.academy.models.User;
import com.it.academy.services.PaymentService;
import com.it.academy.services.SubscriptionService;
import com.it.academy.services.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.secret.key}")
    private String secretKey;
    @Value("${stripe.public.key}")
    private String publicKey;
    private UserService userService;
    private SubscriptionService subscriptionService;

    @Override
    public void makePayment(Long subscriptionId, String cardNumber, String expMonth, String expYear, String cvc) throws StripeException {
        Stripe.apiKey = secretKey;
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
        chargeParams.put("amount", subscription.getCourse().getPrice().multiply(new BigDecimal(100)).intValue());
        chargeParams.put("currency", "USD");
        chargeParams.put("description", "Payment for subscription to the course " + subscription.getCourse().getName());
        chargeParams.put("source", token.getId());
        chargeParams.put("application_fee_amount", (subscription.getCourse().getPrice().multiply(new BigDecimal(0.1)).intValue()));
        chargeParams.put("transfer_data", Collections.singletonMap("destination", subscription.getCourse().getAuthor().getStripeAccountId()));
        //chargeParams.put("transfer_data", Collections.singletonMap("destination", "acct_1N7tFTDOKky9CwBJ")); //for testing

        Charge charge = Charge.create(chargeParams);
    }


    @Override
    public String createStripeAccount(Long userId) throws StripeException {
            User user = userService.getById(userId);
            String email = user.getEmail();
            String fullName = user.getFullName();


            Stripe.apiKey = secretKey;

            AccountCreateParams params = AccountCreateParams.builder()
                    .setType(AccountCreateParams.Type.EXPRESS)
                    .setEmail(email)
                    .setBusinessType(AccountCreateParams.BusinessType.INDIVIDUAL)
                    .setCapabilities(AccountCreateParams.Capabilities.builder()
                            .setTransfers(AccountCreateParams.Capabilities.Transfers.builder().build())
                            .build())
                    .setIndividual(
                            AccountCreateParams.Individual.builder()
                                    .setFirstName(fullName)
                                    .build()
                    )
                    .build();

            Account account = Account.create(params);

            user.setStripeAccountId(account.getId());
            userService.save(user);

            return account.getId();
        }

    @Override
    public String generateOnboardingLink(String accountId) throws StripeException {
        AccountLinkCreateParams params = AccountLinkCreateParams.builder()
                .setAccount(accountId)
                .setRefreshUrl("http://localhost:8080/course/create")
                .setReturnUrl("http://localhost:8080/course/create")
                .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                .build();

        AccountLink accountLink = AccountLink.create(params);

        return accountLink.getUrl();
    }
}
