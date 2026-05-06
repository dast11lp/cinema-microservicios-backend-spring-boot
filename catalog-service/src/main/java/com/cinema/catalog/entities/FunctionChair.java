package com.cinema.catalog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name="function_chair")
public class FunctionChair {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_fun_cha")
	private Long id;
	
	@Column(name="number_cha")
	private Integer numberChair;
	
	@Column(name="available_fun_cha")
	private Boolean available;

	
	@JsonIgnore
	@JoinColumn(name="id_fun_mov")
	@ManyToOne(fetch = FetchType.LAZY)
	private Function function;

	//-----------------

	@Column
	private String status; //AVAILABLE, BLOCKED, OCCUPIED

	@Column(name = "blocked_until")
	private LocalDateTime blockedUntil;

	//-----------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumberChair() {
		return numberChair;
	}

	public void setNumberChair(Integer numberChair) {
		this.numberChair = numberChair;
	}


	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getBlockedUntil() {
		return blockedUntil;
	}

	public void setBlockedUntil(LocalDateTime blockedUntil) {
		this.blockedUntil = blockedUntil;
	}
}
