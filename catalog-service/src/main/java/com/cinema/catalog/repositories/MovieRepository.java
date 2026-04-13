package com.cinema.catalog.repositories;

import com.cinema.catalog.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
