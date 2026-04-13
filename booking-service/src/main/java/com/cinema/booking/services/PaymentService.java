package com.cinema.booking.services;

import com.cinema.booking.entities.FunctionReservation;
import com.cinema.booking.models.PaymentRequest;
import com.cinema.booking.repositories.FunctionReservationRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private FunctionReservationRepository functionReservationRepository;

    @Value("${mercadopago.access-token}")
    private String accessToken; // el accestoken hacia la api

    public PaymentService (FunctionReservationRepository functionReservationRepository) {
        this.functionReservationRepository = functionReservationRepository;
    }

    public Payment processPayment (PaymentRequest paymentRequest) throws MPApiException {
        MercadoPagoConfig.setAccessToken(accessToken);

        //Crear Requiest para mercadoPago

        PaymentCreateRequest mpPaymentCreateRequest = PaymentCreateRequest.builder()
                .transactionAmount(paymentRequest.getPrice())
                .token(paymentRequest.getToken()) //este token lo genera mercadopago cuando el usuario ingresa los datos de su targeta, el token es devuelto
                .installments(1) // numero de pagos.
                .paymentMethodId(paymentRequest.getPaymentMethodId())
                .payer(
                        PaymentPayerRequest.builder()
                                .email(paymentRequest.getEmail())
                                .identification(
                                        IdentificationRequest.builder()
                                                .type("CC")
                                                .number(paymentRequest.getCc())
                                                .build()
                                ).build()
                )
                .build();

        //enviar request a mercado pago

        try {
            PaymentClient client = new PaymentClient();
            Payment payment = client.create(mpPaymentCreateRequest);

            Optional<FunctionReservation> functionReservationOpt = this.functionReservationRepository.findById(paymentRequest.getReservationId());

            if(functionReservationOpt.isPresent()) {
                FunctionReservation reservation = functionReservationOpt.get();
                reservation.setPaymentStatus(payment.getStatus());
                reservation.setPaymentId(payment.getId());
                this.functionReservationRepository.save(reservation);
            }

            return payment;
        } catch (MPApiException e) {
            System.out.println("MPApiException: " + e.getApiResponse().getContent());
            throw e;
        } catch (MPException e) {
            System.out.println("MPException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
