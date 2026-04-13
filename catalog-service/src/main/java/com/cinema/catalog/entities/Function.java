package com.cinema.catalog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity(name= "function_")
public class Function {
	
	@Id
	@Column(name="id_fun")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name="hour_time_mov")
	private String hourTime;
	
	@Column(name="date_fun")
	private String date;
	
	@Column(name="room_fun")
	private String room;
	
	@Column(name="price_ticket_fun") 
	private Double priceTicket;

	@JsonIgnore
	@JsonIgnoreProperties({"function", "hibernateLazyInitializer","handle"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "function")
	private List<FunctionMovie> listFunctionMovie;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "function")
	@OrderBy("number_cha ASC")
	private List<FunctionChair >functionChairs ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHourTime() {
		return hourTime;
	}

	public void setHourTime(String hourTime) {
		this.hourTime = hourTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Double getPriceTicket() {
		return priceTicket;
	}

	public void setPriceTicket(Double priceTicket) {
		this.priceTicket = priceTicket;
	}

	public List<FunctionMovie> getListFunctionMovie() {
		return listFunctionMovie;
	}

	public void setListFunctionMovie(List<FunctionMovie> listFunctionMovie) {
		this.listFunctionMovie = listFunctionMovie;
	}

	public List<FunctionChair> getFunctionChairs() {
		return functionChairs;
	}

	public void setFunctionChairs(List<FunctionChair> functionChairs) {
		this.functionChairs = functionChairs;
	}

	
}
