package com.smartcarservice.ua.SmartCarService.dto;

import javax.persistence.Column;

public class CarOwnerDto {

	private String email;
	private String password;
	private String fullName;
	private String userName;

	public CarOwnerDto(String email, String password, String fullName, String userName) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.userName = userName;
	}

//Getters & Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
