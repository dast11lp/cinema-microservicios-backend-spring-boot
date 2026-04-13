package com.cinema.catalog.services;

import com.cinema.catalog.entities.FunctionMovie;
import com.cinema.catalog.repositories.FunctionMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionMovieService {
	
	@Autowired
	private FunctionMovieRepository functionMovieRep;
	
	@Transactional
	public List<FunctionMovie> list(){
		return this.functionMovieRep.findAll();
	}
	
	@Transactional
	public FunctionMovie findById(Long id) {
		return this.functionMovieRep.findById(id).orElse(null);
	}
	
	
	public List<FunctionMovie> findAll() {
		return this.functionMovieRep.findAll();
	}
	
	
	
}
