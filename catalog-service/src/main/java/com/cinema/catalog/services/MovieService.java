package com.cinema.catalog.services;

import com.cinema.catalog.entities.Movie;
import com.cinema.catalog.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRep;
	
	
	public List<Movie> findAll(){
		return this.movieRep.findAll();
	}
	
	public Movie findByid(Long id){
		
		Movie movie = this.movieRep.findById(id).orElse(null);
		
		return movie;
	}
}
