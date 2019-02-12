package ua.ita.smartcarservice.entity.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.sales.DealerEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "technical_services")
public class TechnicalServiceEntity {

    public TechnicalServiceEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalServiceId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String address;


    @OneToMany(mappedBy = "technicalServiceId")
    private List<UserTechnicalService> userTechnicalServices;



    @ManyToOne
    @JoinColumn(name = "dealerEntity",nullable = false)
    private DealerEntity dealerEntity;


/*
    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private UserEntity dealer;




    @JsonBackReference
    @OneToMany(mappedBy = "technicalService")
    private Set<UserEntity> workers;
*/
}