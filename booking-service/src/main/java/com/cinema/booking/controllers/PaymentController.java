package com.cinema.booking.controllers;


import com.cinema.booking.models.PaymentRequest;
import com.cinema.booking.models.ReservationResponse;
import com.cinema.booking.services.PaymentService;
import com.mercadopago.exceptions.MPApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/")
    public ResponseEntity<?> processPayment (@RequestBody PaymentRequest paymentRequest)  {

        try {
            ReservationResponse payment = this.paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok(payment);
        } catch (MPApiException e) {
            return ResponseEntity.badRequest().body(e.getApiResponse().getContent());
        }
    }
}
