package com.cinema.booking.repositories;

import com.cinema.booking.entities.FunctionReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FunctionReservationRepository extends JpaRepository<FunctionReservation, Long> {

	List<FunctionReservation> findByUserId(Long userId);

	Page<FunctionReservation> findByUserId(Long userId, Pageable pageable);
}


/*package com.cinema.booking.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FunctionReservationRepository extends JpaRepository<FunctionReservation, Long>{
	
	public List<FunctionReservation> findByMyUser(MyUser myUser);
	
	public Page<FunctionReservation> findByMyUser(MyUser myUser, Pageable pageable);

}*/
