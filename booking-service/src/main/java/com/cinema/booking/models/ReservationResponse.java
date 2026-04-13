package com.cinema.booking.models;

import java.util.List;

public class ReservationResponse {
    private Long reservationId;
    private Long userId;
    private Long functionMovieId;
    private Double totalMount;
    private List<Long> chairs;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFunctionMovieId() {
        return functionMovieId;
    }

    public void setFunctionMovieId(Long functionMovieId) {
        this.functionMovieId = functionMovieId;
    }

    public Double getTotalMount() {
        return totalMount;
    }

    public void setTotalMount(Double totalMount) {
        this.totalMount = totalMount;
    }

    public List<Long> getChairs() {
        return chairs;
    }

    public void setChairs(List<Long> chairs) {
        this.chairs = chairs;
    }
}
