package com.cinema.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "function_reservation")
public class FunctionReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fun_res")
	private Long id;

	@Column(name = "date_res")
	private LocalDateTime dateRes;
	
	@Column (name ="total_mou_res")
	private Double totalMount;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "payment_id")
	private Long paymentId;

	@Column(name = "id_use")
	private Long userId;

	@Column(name = "id_fun_mov")
	private Long functionMovieId;


    @PrePersist
    public void prePersist() {
        if (dateRes == null) {
            dateRes = LocalDateTime.now();
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateRes() {
		return dateRes;
	}

	public void setDateRes(LocalDateTime dateRes) {
		this.dateRes = dateRes;
	}

	public Double getTotalMount() {
		return totalMount;
	}

	public void setTotalMount(Double totalMount) {
		this.totalMount = totalMount;
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

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
}
