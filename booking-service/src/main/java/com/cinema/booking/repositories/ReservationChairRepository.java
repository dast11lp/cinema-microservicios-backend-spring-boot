package com.cinema.booking.repositories;

import com.cinema.booking.entities.ReservationChair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationChairRepository extends JpaRepository<ReservationChair, Long> {
    List<ReservationChair> findByReservationId(Long reservationId);
}