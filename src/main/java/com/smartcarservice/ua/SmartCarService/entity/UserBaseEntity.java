package com.smartcarservice.ua.SmartCarService.entity;

import lombok.Data;

import javax.persistence.*;

//@Data
//@Entity
//@Table(name = "base_users")
@MappedSuperclass
public class UserBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String fullName;


}
