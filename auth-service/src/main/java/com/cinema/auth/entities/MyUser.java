package com.cinema.auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "users")
public class MyUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_use")
	private Long idUser;

	@Column(name = "username_use")
	private String username;

	@Column(name = "password_use")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "name_use")
	private String name;
	
	@Column(name = "surname_use")
	private String surname;
	
	@Column(name = "nid_type_use")
	private String typeNID;
	
	@Column(name = "nid_use")
	private String nid;
	
	@Column(name = "birthdate_use")
	private LocalDate birthdate;
	
	@Column(name = "department_use")
	private String department;
	
	@Column(name = "city_use")
	private String city;
	
	@Column(name = "addresss_use")
	private String addresss;
	
	@Column(name = "cellphone_number_use")
	private String cellphoneNumber;
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //porqué?? jajaja
	@JoinColumn(name = "id_use")
	private List<Role> roles;


	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTypeNID() {
		return typeNID;
	}

	public void setTypeNID(String typeNID) {
		this.typeNID = typeNID;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}

	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
