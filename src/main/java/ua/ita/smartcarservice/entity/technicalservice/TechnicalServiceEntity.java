package ua.ita.smartcarservice.entity.technicalservice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ua.ita.smartcarservice.entity.sales.DealerEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "technical_services")
public class TechnicalServiceEntity {

    public TechnicalServiceEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalServiceId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String address;


    @OneToMany(mappedBy = "technicalServiceId", orphanRemoval = true)
    private List<UserTechnicalService> userTechnicalServices;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true)
    private DealerEntity dealer;

    public TechnicalServiceEntity(String name, String address, DealerEntity dealerEntity) {
        this.name = name;
        this.address = address;
        this.dealer = dealerEntity;
    }

    public Long getTechnicalServiceId() {
        return technicalServiceId;
    }

    public void setTechnicalServiceId(Long technicalServiceId) {
        this.technicalServiceId = technicalServiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<UserTechnicalService> getUserTechnicalServices() {
        return userTechnicalServices;
    }

    public void setUserTechnicalServices(List<UserTechnicalService> userTechnicalServices) {
        this.userTechnicalServices = userTechnicalServices;
    }

    public DealerEntity getDealer() {
        return dealer;
    }

    public void setDealer(DealerEntity dealer) {
        this.dealer = dealer;
    }
}
