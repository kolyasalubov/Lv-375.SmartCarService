package com.smartcarservice.ua.SmartCarService.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
public class UserBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;
    //TODO Make ono-to-one with login
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String fullName;
    //TODO Make ono-to-one with login
    @Column(length = 100, nullable = false, unique = true)
    private String userName;
    
    //Getters & Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
