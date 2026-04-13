package com.cinema.booking.controllers;

import com.cinema.booking.entities.FunctionReservation;
import com.cinema.booking.models.FunctionDTO;
import com.cinema.booking.models.Reservation;
import com.cinema.booking.models.ReservationResponse;
import com.cinema.booking.models.UserDTO;
import com.cinema.booking.services.FunctionReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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

	@PostMapping("/reserve-function-movie")
	public ResponseEntity<?> reserveFunction(@RequestBody Reservation reservationReq, @PathVariable Long idUser) {

        //obtener usuario
        UserDTO user = authClient.get()
                .uri("/users/" + idUser)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();
        if (user == null)
            return ResponseEntity.ok("el usuario no se encuentra en nuestros registros");

        //obtener lista de de sillas por función


        List<Long> reservedChairs;
        try {
            reservedChairs = catalogClient.put()
                    .uri("/functionChair/occupy")
                    .bodyValue(reservationReq.getFunctionChairs())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Long>>() {
                    })
                    .block();
        } catch (WebClientResponseException e) {
            return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
        }


        if (reservedChairs == null || reservedChairs.isEmpty())
            return ResponseEntity.ok("no se pudieron reservar las sillas");

        FunctionReservation reservation = new FunctionReservation();
        reservation.setUserId(idUser);
        reservation.setFunctionMovieId(reservationReq.getIdFunMov());

        FunctionDTO function = this.catalogClient.get()
                .uri("/functions/" + reservationReq.getIdFunMov())
                .retrieve()
                .bodyToMono(FunctionDTO.class)
                .block();

        reservation.setTotalMount(function.getPriceTicket() * reservationReq.getFunctionChairs().size());

        this.funcResSer.save(reservation);

        ReservationResponse response = new ReservationResponse();
        response.setReservationId(reservation.getId());
        response.setUserId(idUser);
        response.setFunctionMovieId(reservationReq.getIdFunMov());
        response.setTotalMount(reservation.getTotalMount());
        response.setChairs(reservedChairs);

        return ResponseEntity.ok(response);
    }

}