package com.cinema.booking.services;

import com.cinema.booking.entities.FunctionReservation;
import com.cinema.booking.repositories.FunctionReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionReservationService {

	@Autowired
	private FunctionReservationRepository funResRepo;

	public FunctionReservation findById(Long id) {
		return this.funResRepo.findById(id).orElse(null);
	}

	public List<FunctionReservation> findAll(){
		return this.funResRepo.findAll();
	}

	public void deleteById(Long id) {
		this.funResRepo.deleteById(id);
	}

	public void save(FunctionReservation funRes) {
		this.funResRepo.save(funRes);
	}

	public List<FunctionReservation> findByUserId(Long userId) {
		return this.funResRepo.findByUserId(userId);
	}

	public Page<FunctionReservation> findByUserId(Long userId, Pageable pageable) {
		return this.funResRepo.findByUserId(userId, pageable);
	}
}