package com.cinema.catalog.repositories;

import com.cinema.catalog.entities.FunctionMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionMovieRepository extends JpaRepository<FunctionMovie, Long>{
	
}
