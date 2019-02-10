package ua.ita.smartcarservice.entity.sales;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */
@Data
@Entity
@Table(name = "dealer")

public class DealerEntity {

    public DealerEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealerId;


    @Column(length = 100, nullable = false, unique = false)
    private String address;

    @Column(length = 100, nullable = false, unique = true)
    private String edr;

    @OneToMany(mappedBy = "dealerEntity")
    private List<UserDealer>userDealers;




//    @OneToMany(mappedBy = "dealerEntity")
//    private List<UserEntity>technical_services;
//







}
