package com.cinema.booking.controllers;

import com.cinema.booking.models.*;
import com.cinema.booking.services.FunctionReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("reservation/user/{idUser}")
@CrossOrigin({ "*" })
public class FuntionReservationController {

	@Autowired
	private FunctionReservationService funcResSer;

	@Autowired
	private WebClient authClient;

	@Autowired
	private WebClient catalogClient;

    @PostMapping("reserve-function-movie")
    public ResponseEntity<?> reserveFunction(@RequestBody Reservation reservationReq, @PathVariable Long idUser) {
        try {
            ReservationResponse response = funcResSer.createReservation(reservationReq, idUser);
            return ResponseEntity.ok(response);
        } catch (WebClientResponseException e) {
            return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
        }
    }

}