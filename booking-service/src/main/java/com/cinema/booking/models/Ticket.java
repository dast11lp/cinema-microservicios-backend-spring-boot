package com.cinema.booking.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(content = Include.NON_NULL)
public class Ticket {

	private Long userId;
	private String username;
	private String dateFunc;
	private LocalDateTime dateRes;
	private String room;
	private Long idFuncReservation;
	private String movieName;
	private Double totalMount;

	private List<Integer> chairs;

	public Ticket(List<Integer> chairs, Long userId, String username, String dateFunc, LocalDateTime dateRes, String room,
			Double mount, Long idFuncReservation, String movieName, Double totalMount) {
		this.chairs = chairs;
		this.userId = userId;
		this.username = username;
		this.dateFunc = dateFunc;
		this.dateRes = dateRes;
		this.room = room;
		this.idFuncReservation = idFuncReservation;
		this.movieName = movieName;
		this.totalMount = totalMount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDateFunc() {
		return dateFunc;
	}

	public void setDateFunc(String dateFunc) {
		this.dateFunc = dateFunc;
	}

	public LocalDateTime getDateRes() {
		return dateRes;
	}

	public void setDateRes(LocalDateTime dateRes) {
		this.dateRes = dateRes;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Long getIdFuncReservation() {
		return idFuncReservation;
	}

	public void setIdFuncReservation(Long idFuncReservation) {
		this.idFuncReservation = idFuncReservation;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public List<Integer> getChairs() {
		return chairs;
	}

	public void setChairs(List<Integer> chairs) {
		this.chairs = chairs;
	}

	public Double getTotalMount() {
		return totalMount;
	}

	public void setTotalMount(Double totalMount) {
		this.totalMount = totalMount;
	}
}
