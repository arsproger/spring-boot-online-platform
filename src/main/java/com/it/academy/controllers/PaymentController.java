package com.it.academy.controllers;

import com.it.academy.services.PaymentService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> makePayment(
            @RequestParam("courseId") Long courseId,
            @RequestParam("userId") Long userId,
            @RequestParam("cardNumber") String cardNumber,
            @RequestParam("expMonth") String expMonth,
            @RequestParam("expYear") String expYear,
            @RequestParam("cvc") String cvc) throws StripeException {

        paymentService.makePayment(courseId, userId, cardNumber, expMonth, expYear, cvc);
        return ResponseEntity.ok("Payment successful");
    }
}
