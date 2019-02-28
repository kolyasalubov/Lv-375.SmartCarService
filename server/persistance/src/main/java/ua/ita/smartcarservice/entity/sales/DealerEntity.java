package ua.ita.smartcarservice.entity.sales;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1 on 27.02.2019.
 */
@Data
@Entity
@Table(name = "dealer")
public class DealerEntity {


    public DealerEntity() {
    }

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealerId;

    @Column(length = 100, nullable = false, unique = false)
    private String dealerName;

    @Column(length = 100, nullable = false, unique = false)
    private String dealerAddress;

    @Column(length = 100, nullable = false, unique = true)
    private String dealerEdr;

    @Column(length = 100, nullable = false, unique = true)
    private String dealerEmail;


    public DealerEntity(UserEntity userEntity, String dealerName, String dealerAddress, String dealerEdr, String dealerEmail) {
        this.userEntity = userEntity;
        this.dealerName = dealerName;
        this.dealerAddress = dealerAddress;
        this.dealerEdr = dealerEdr;
        this.dealerEmail = dealerEmail;
    }

    //    @OneToMany(mappedBy = "dealerEntity")
//    private List<SaleManagerEntity>saleManagerEntities;
//
//
//    @OneToMany(mappedBy = "dealerEntity")
//    private List<TradeIn> tradeIns;

//    @OneToMany(mappedBy = "dealerEntity")
//    private List<TechnicalServiceEntity>technicalServiceEntities;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public String getDealerEdr() {
        return dealerEdr;
    }

    public void setDealerEdr(String dealerEdr) {
        this.dealerEdr = dealerEdr;
    }

    public String getDealerEmail() {
        return dealerEmail;
    }

    public void setDealerEmail(String dealerEmail) {
        this.dealerEmail = dealerEmail;
    }

}