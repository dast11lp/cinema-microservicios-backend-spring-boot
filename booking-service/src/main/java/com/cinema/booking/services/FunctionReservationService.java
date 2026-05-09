package com.cinema.booking.services;

import com.cinema.booking.entities.FunctionReservation;
import com.cinema.booking.models.*;
import com.cinema.booking.repositories.FunctionReservationRepository;
import com.cinema.booking.repositories.ReservationChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FunctionReservationService {

	@Autowired
	private FunctionReservationRepository funResRepo;

	@Autowired
	private WebClient authClient;

    @Autowired
    private WebClient catalogClient;

	@Autowired
	private ReservationChairRepository reservationChairRepository;

	public FunctionReservation findById(Long id) {
		return this.funResRepo.findById(id).orElse(null);
	}

	public List<FunctionReservation> findAll(){
		return this.funResRepo.findAll();
	}

	public void deleteById(Long id) {
		this.funResRepo.deleteById(id);
	}

	public void save(FunctionReservation funRes) {
		this.funResRepo.save(funRes);
	}

	public List<FunctionReservation> findByUserId(Long userId) {
		return this.funResRepo.findByUserId(userId);
	}

	public Page<FunctionReservation> findByUserId(Long userId, Pageable pageable) {
		return this.funResRepo.findByUserId(userId, pageable);
	}

	public ReservationResponse createReservation(Reservation reservationReq, Long idUser) {

		// 1. Verificar usuario
		UserDTO user = authClient.get()
				.uri("/users/" + idUser)
				.retrieve()
				.bodyToMono(UserDTO.class)
				.block();
		if (user == null)
			throw new RuntimeException("El usuario no se encuentra en nuestros registros");

		// 2. Bloquear sillas temporalmente
		List<ChairDTO> blockedChairs = catalogClient.put()
				.uri("/functionChair/block")
				.bodyValue(reservationReq.getFunctionChairs())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<ChairDTO>>() {})
				.block();

		if (blockedChairs == null || blockedChairs.isEmpty())
			throw new RuntimeException("No se pudieron bloquear las sillas");

		// 3. Obtener función
		FunctionDTO function = catalogClient.get()
				.uri("/functions/" + reservationReq.getIdFunMov())
				.retrieve()
				.bodyToMono(FunctionDTO.class)
				.block();

		// 4. Guardar reserva
		FunctionReservation reservation = new FunctionReservation();
		reservation.setUserId(idUser);
		reservation.setFunctionMovieId(reservationReq.getIdFunMov());
		reservation.setTotalMount(BigDecimal.valueOf(function.getPriceTicket() * reservationReq.getFunctionChairs().size()));
		this.funResRepo.save(reservation);

		// 5. Armar respuesta
		ReservationResponse response = new ReservationResponse();
		response.setReservationId(reservation.getId());
		response.setUserId(idUser);
		response.setFunctionMovieId(reservationReq.getIdFunMov());
		response.setTotalMount(reservation.getTotalMount());
		response.setChairs(blockedChairs);
		response.setDateRes(reservation.getDateRes());
		response.setUsername(user.getUsername());
		response.setRoom(function.getRoom());
		response.setDateFun(function.getDate());

		if (function.getListFunctionMovie() != null && !function.getListFunctionMovie().isEmpty()) {
			response.setMovieName(function.getListFunctionMovie().get(0).getMovie().getMovieName());
		}

		return response;
	}

	public Page<ReservationResponse> findByUserIdEnriched(Long userId, Pageable pageable) {
		Page<FunctionReservation> reservations = funResRepo.findByUserId(userId, pageable);

		return reservations.map(reservation -> {

			UserDTO user = authClient.get()
					.uri("/users/" + userId)
					.retrieve()
					.bodyToMono(UserDTO.class)
					.block();

			FunctionDTO function = catalogClient.get()
					.uri("/functions/" + reservation.getFunctionMovieId())
					.retrieve()
					.bodyToMono(FunctionDTO.class)
					.block();

			List<ChairDTO> chairs = reservationChairRepository
					.findByReservationId(reservation.getId())
					.stream()
					.map(rc -> {
						ChairDTO chair = new ChairDTO();
						chair.setId(rc.getChairId());
						chair.setNumberChair(rc.getNumberChair());
						return chair;
					})
					.toList();

			ReservationResponse response = new ReservationResponse();
			response.setReservationId(reservation.getId());
			response.setUserId(userId);
			response.setFunctionMovieId(reservation.getFunctionMovieId());
			response.setTotalMount(reservation.getTotalMount());
			response.setDateRes(reservation.getDateRes());
			response.setChairs(chairs);

			if (user != null) response.setUsername(user.getUsername());

			if (function != null) {
				response.setRoom(function.getRoom());
				response.setDateFun(function.getDate());
				if (function.getListFunctionMovie() != null && !function.getListFunctionMovie().isEmpty()) {
					response.setMovieName(function.getListFunctionMovie().get(0).getMovie().getMovieName());
				}
			}

			return response;
		});
	}
}