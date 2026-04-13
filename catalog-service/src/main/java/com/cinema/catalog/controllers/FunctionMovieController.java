package com.cinema.catalog.controllers;

import com.cinema.catalog.entities.FunctionChair;
import com.cinema.catalog.entities.FunctionMovie;
import com.cinema.catalog.entities.Movie;
import com.cinema.catalog.services.FunctionMovieService;
import com.cinema.catalog.services.MovieService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("function-movie")
public class FunctionMovieController {
	
	@Autowired
	private FunctionMovieService functionMovieService;
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("available-chairs/{idFunMov}")
	public ResponseEntity<List<FunctionChair>> availableChairs(@PathVariable Long idFunMov){
		
		FunctionMovie functionMovie = this.functionMovieService.findById(idFunMov);
		
		//return ResponseEntity.ok(functionMovie.getFunctionChairs());
		return null;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<MappingJacksonValue> listFunctionsPerMovie(@PathVariable Long id){
		
			 SimpleBeanPropertyFilter simpleBeanPropertyFilter =
		                SimpleBeanPropertyFilter.serializeAllExcept();
			 
			 FilterProvider filterProvider = new SimpleFilterProvider()
		                .addFilter("userFilter", simpleBeanPropertyFilter);
			 
			 	Movie movie = movieService.findByid(id);
			 	
			 	if (movie == null) return ResponseEntity.ok(null);
			 	
		        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(movie);
		        mappingJacksonValue.setFilters(filterProvider);
				
				return ResponseEntity.ok(mappingJacksonValue);
	}
	
	@GetMapping("list")
	public ResponseEntity<List<FunctionMovie>> listFunctios(){
		return ResponseEntity.ok(this.functionMovieService.findAll());
	}
	
	@GetMapping("test/{id}")
	public ResponseEntity<?> oneFunctionMovie(@PathVariable Long id) {
		return ResponseEntity.ok(this.functionMovieService.findById(id));
	}
	
}
