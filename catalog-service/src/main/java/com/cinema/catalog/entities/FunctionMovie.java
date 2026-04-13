package com.cinema.catalog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity(name = "function_movie")
public class FunctionMovie {
	
	@Id
	@Column(name="id_fun_mov")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@JsonIgnoreProperties({"listFunctionMovie", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "id_fun")
	private Function function;
	
	
	@JsonIgnoreProperties({"functionMovie","hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "id_mov")
	private Movie movie;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Function getFunction() {
		return function;
	}


	public void setFunction(Function function) {
		this.function = function;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
}
