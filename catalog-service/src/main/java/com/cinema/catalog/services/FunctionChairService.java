package com.cinema.catalog.services;

import com.cinema.catalog.entities.Function;
import com.cinema.catalog.entities.FunctionChair;
import com.cinema.catalog.repositories.FunctionChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionChairService {
	
	@Autowired
	private FunctionChairRepository chairRepository;
	
	public List<FunctionChair> findAll() {
		return this.chairRepository.findAll();
	}
	
	public FunctionChair findById (Long id) {
		return this.chairRepository.findById(id).orElse(null);
	}
	
	public List<FunctionChair>  saveAll(List<FunctionChair> listFunctionChair) {
		return this.chairRepository.saveAll(listFunctionChair);
	}
	
	public FunctionChair findByNumberChair (Long id) {
		return this.chairRepository.findByNumberChair(id);
	}


	/*---------------------------------------------------------*/

	public List<FunctionChair> findByFunction (Function function) {
		return this.chairRepository.findByFunction(function);
	}

	public List<Long> reserveChairs(List<Long> chairsId) {
		List<FunctionChair> chairs = this.chairRepository.findAllById(chairsId);

		//validar sillas disponibles
		for (FunctionChair chair: chairs) {
			if(!chair.getAvailable()) {
				throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"La silla " + chair.getNumberChair() + " ya está ocupada");
			}
		}

		chairs.forEach(chair -> chair.setAvailable(false));
		this.chairRepository.saveAll(chairs);
		return chairs.stream().map(FunctionChair::getId).toList();
		// FunctionChair::getId equivale a chair -> chair.getId()
	}
}
