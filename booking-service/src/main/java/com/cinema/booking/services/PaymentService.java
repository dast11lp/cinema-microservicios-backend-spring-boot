package com.cinema.booking.services;

import com.cinema.booking.entities.FunctionReservation;
import com.cinema.booking.entities.ReservationChair;
import com.cinema.booking.models.ChairDTO;
import com.cinema.booking.models.PaymentRequest;
import com.cinema.booking.models.ReservationResponse;
import com.cinema.booking.repositories.FunctionReservationRepository;
import com.cinema.booking.repositories.ReservationChairRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class PaymentService {

    private final FunctionReservationRepository functionReservationRepository;

    @Value("${mercadopago.access-token}")
    private String accessToken; // el access token hacia la api

    private final WebClient catalogClient;

    private final ReservationChairRepository reservationChairRepository;

    public PaymentService (
            FunctionReservationRepository functionReservationRepository,
            WebClient catalogClient,
            ReservationChairRepository reservationChairRepository
    ) {
        this.functionReservationRepository = functionReservationRepository;
        this.catalogClient = catalogClient;
        this.reservationChairRepository = reservationChairRepository;
    }

    public ReservationResponse processPayment (PaymentRequest paymentRequest) throws MPApiException {
        MercadoPagoConfig.setAccessToken(accessToken);


        // 1. validar sillas.
        List<ChairDTO> reservedChairs;

        try {
            reservedChairs = this.catalogClient.put()
                    .uri("/functionChair/occupy")
                    .bodyValue(paymentRequest.getListChairs())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<ChairDTO>>() {})
                    .block();
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error al reservar sillas: " + e.getResponseBodyAsString());
        }

        if (reservedChairs == null || reservedChairs.isEmpty()) {
            throw new RuntimeException("No se pudieron reservar las sillas");
        }

        // 2. validar y reservar función

        FunctionReservation reservation = this.functionReservationRepository
                .findById(paymentRequest.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        //3. Crear Requiest para mercadoPago

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

        //4. enviar request a mercado pago
        Payment payment;
        try {
            PaymentClient client = new PaymentClient();
            payment = client.create(mpPaymentCreateRequest);

        } catch (MPApiException e) {
            System.out.println("MPApiException: " + e.getApiResponse().getContent());
            // liberar sillas bloqueadas
            this.catalogClient.put()
                    .uri("/functionChair/release")
                    .bodyValue(paymentRequest.getListChairs())
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            throw e;
        } catch (MPException e) {
            System.out.println("MPException: " + e.getMessage());
            throw new RuntimeException(e);
        }

        // 5. validar pago

        if (!payment.getStatus().equals("approved")) {
            throw new RuntimeException("Pago no aprobado. Estado: " + payment.getStatus());
        }

        // 6. respuesta

        ReservationResponse response = new ReservationResponse();
        response.setReservationId(reservation.getId());
        response.setUserId(reservation.getUserId());
        response.setFunctionMovieId(reservation.getFunctionMovieId());
        response.setTotalMount(reservation.getTotalMount());
        response.setChairs(reservedChairs);

        reservation.setPaymentStatus(payment.getStatus());
        reservation.setPaymentId(payment.getId());
        this.functionReservationRepository.save(reservation);

        // 7. guardar sillas de la reserva
        for (ChairDTO chair : reservedChairs) {
            ReservationChair rc = new ReservationChair();
            rc.setReservationId(reservation.getId());
            rc.setChairId(chair.getId());
            rc.setNumberChair(chair.getNumberChair());
            reservationChairRepository.save(rc);
        }

        return response;
    }
}
