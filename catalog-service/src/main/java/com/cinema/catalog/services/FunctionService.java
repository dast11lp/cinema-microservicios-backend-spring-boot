package com.cinema.catalog.services;

import com.cinema.catalog.entities.Function;
import com.cinema.catalog.repositories.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionService{
	
	@Autowired 
	private FunctionRepository functionRepository;
	
	public List<Function> getFunctions () {
		return this.functionRepository.findAll();
	}
	
	public Function getFunction (Long id) {
		return this.functionRepository.findById(id).orElse(null);
	}

}
