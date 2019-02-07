package ua.ita.smartcarservice.entity.sales;

/**
 * Created by 1 on 07.02.2019.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TradeIn")
public class TradeIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesManager_id", nullable = true)
    private SalesManager salesManager;

    @Column(length = 100, nullable = false, unique = true)
    private String vinNewCar;


    @Column(length = 100, nullable = false, unique = true)
    private String vinUserCar;

    @Column(length = 100, nullable = false, unique = true)
    private String idUser;

    @Column(length = 100, nullable = false, unique = true)
    private String isactive;

    public TradeIn() {
    }

    public TradeIn(SalesManager salesManager, String vinNewCar, String vinUserCar, String idUser, String isactive) {
        this.salesManager = salesManager;
        this.vinNewCar = vinNewCar;
        this.vinUserCar = vinUserCar;
        this.idUser = idUser;
        this.isactive = isactive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(SalesManager salesManager) {
        this.salesManager = salesManager;
    }

    public String getVinNewCar() {
        return vinNewCar;
    }

    public void setVinNewCar(String vinNewCar) {
        this.vinNewCar = vinNewCar;
    }

    public String getVinUserCar() {
        return vinUserCar;
    }

    public void setVinUserCar(String vinUserCar) {
        this.vinUserCar = vinUserCar;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
}
