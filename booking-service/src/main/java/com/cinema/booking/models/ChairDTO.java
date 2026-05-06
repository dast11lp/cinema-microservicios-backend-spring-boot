package com.cinema.booking.models;

public class ChairDTO {

    private Long id;
    private Integer numberChair;
    private Boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberChair() {
        return numberChair;
    }

    public void setNumberChair(Integer numberChair) {
        this.numberChair = numberChair;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
