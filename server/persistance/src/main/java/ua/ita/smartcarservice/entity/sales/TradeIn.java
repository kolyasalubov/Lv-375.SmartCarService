package ua.ita.smartcarservice.entity.sales;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by 1 on 27.02.2019.
 */
@Data
@Entity
@Table(name = "TradeIn")
public class TradeIn {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String vinNewCar;

    @Column(length = 100, nullable = false, unique = true)
    private String vinUsedCar;

    @Column(length = 100, nullable = false, unique = true)
    private Long idUser;

    @Column(length = 100, nullable = false, unique = true)
    private Long idDealer;

    @Column(length = 100, nullable = false, unique = true)
    private String isactive;


//    @ManyToOne
//    @JoinColumn(name = "dealerEntity",nullable = false)
//    @JsonIgnore
//    private DealerEntity dealerEntity;


    public TradeIn(String vinNewCar, String vinUsedCar, Long idUser, Long idDealer, String isactive) {
        this.vinNewCar = vinNewCar;
        this.vinUsedCar = vinUsedCar;
        this.idUser = idUser;
        this.idDealer = idDealer;
        this.isactive = isactive;

    }
}

