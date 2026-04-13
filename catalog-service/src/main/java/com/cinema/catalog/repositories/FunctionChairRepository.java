package com.cinema.catalog.repositories;

import com.cinema.catalog.entities.Function;
import com.cinema.catalog.entities.FunctionChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FunctionChairRepository extends JpaRepository<FunctionChair, Long>{
	@Query("SELECT fc FROM function_chair fc ORDER BY fc.id")
	List<FunctionChair> findAllOrder();
	
	@Query("SELECT fc from function_chair fc WHERE fc.numberChair = : numberChair ")
	FunctionChair findByNumberChair(@Param("numberChair") Long numberChair);

	List<FunctionChair> findByFunction (Function function);
	
}
