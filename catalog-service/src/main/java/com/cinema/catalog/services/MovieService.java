package com.cinema.catalog.services;

import com.cinema.catalog.entities.Movie;
import com.cinema.catalog.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRep;

	public Page<Movie> findAll(int page, int size) {
		return this.movieRep.findAll(PageRequest.of(page, size));
	}
	
	public Movie findByid(Long id){
		
		Movie movie = this.movieRep.findById(id).orElse(null);
		
		return movie;
	}

	public Movie findById(Long id) {
		return movieRep.findById(id).orElse(null);
	}
}
