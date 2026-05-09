package com.cinema.booking.models;

import java.util.List;

public class FunctionDTO {
    private Long id;
    private String hourTime;
    private String date;
    private String room;
    private Double priceTicket;
    private List<FunctionMovieDTO> listFunctionMovie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHourTime() {
        return hourTime;
    }

    public void setHourTime(String hour) {
        this.hourTime = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double price) {
        this.priceTicket = price;
    }

    public List<FunctionMovieDTO> getListFunctionMovie() { return listFunctionMovie; }
    public void setListFunctionMovie(List<FunctionMovieDTO> list) { this.listFunctionMovie = list; }
}
