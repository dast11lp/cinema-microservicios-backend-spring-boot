package com.cinema.booking.entities;

import jakarta.persistence.*;

@Entity(name = "reservation_chairs")
public class ReservationChair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_res_cha")
    private Long id;

    @Column(name = "id_res")
    private Long reservationId;

    @Column(name = "id_chair")
    private Long chairId;

    @Column(name = "number_chair")
    private Integer numberChair;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getReservationId() { return reservationId; }
    public void setReservationId(Long reservationId) { this.reservationId = reservationId; }

    public Long getChairId() { return chairId; }
    public void setChairId(Long chairId) { this.chairId = chairId; }

    public Integer getNumberChair() { return numberChair; }
    public void setNumberChair(Integer numberChair) { this.numberChair = numberChair; }
}