package com.cinema.booking.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationResponse {
    private Long reservationId;
    private Long userId;
    private Long functionMovieId;
    private BigDecimal totalMount;
    private List<ChairDTO> chairs;
    private LocalDateTime dateRes;
    private String username;
    private String room;
    private String dateFun;
    private String movieName;


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

    public BigDecimal getTotalMount() {
        return totalMount;
    }

    public void setTotalMount(BigDecimal totalMount) {
        this.totalMount = totalMount;
    }

    public List<ChairDTO> getChairs() {
        return chairs;
    }

    public void setChairs(List<ChairDTO> chairs) {
        this.chairs = chairs;
    }

    public LocalDateTime getDateRes() {
        return dateRes;
    }

    public void setDateRes(LocalDateTime dateRes) {
        this.dateRes = dateRes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDateFun() {
        return dateFun;
    }

    public void setDateFun(String dateFun) {
        this.dateFun = dateFun;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
