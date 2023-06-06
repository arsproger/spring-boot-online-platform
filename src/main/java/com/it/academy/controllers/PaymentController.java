package com.it.academy.controllers;

import com.it.academy.security.DetailsUser;
import com.it.academy.services.PaymentService;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripe")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/pay")
    @Operation(summary = "Оплата для курса")
    public ResponseEntity<String> makePayment(
            @AuthenticationPrincipal DetailsUser detailsUser,
            @RequestParam Long courseId,
            @RequestParam String cardNumber,
            @RequestParam String expMonth,
            @RequestParam String expYear,
            @RequestParam String cvc) throws StripeException {

        paymentService.makePayment(courseId, detailsUser.getUser().getId(), cardNumber, expMonth, expYear, cvc);
        return ResponseEntity.ok("Payment successful");
    }
}
