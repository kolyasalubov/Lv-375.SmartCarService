package com.smartcarservice.ua.SmartCarService.entity;

import lombok.Data;

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
    @Column(length = 100, nullable = false)
    private String userName;

}