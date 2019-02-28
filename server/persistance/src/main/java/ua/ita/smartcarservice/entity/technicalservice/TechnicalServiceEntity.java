package ua.ita.smartcarservice.entity.technicalservice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.feedback.ServicesFeedback;
import ua.ita.smartcarservice.entity.sales.DealerEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "technical_services")
public class TechnicalServiceEntity {

    public TechnicalServiceEntity() {
    }

    public TechnicalServiceEntity(String name, String address, DealerEntity dealerEntity) {
        this.name = name;
        this.address = address;
        this.dealer = dealerEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalServiceId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String address;

        @ManyToOne
        @JoinColumn(name = "dealer_id", nullable = true)
        private DealerEntity dealer;

    @OneToMany(mappedBy = "technicalServiceId", orphanRemoval = true)
    private List<UserTechnicalService> userTechnicalServices;

//    @ManyToOne
//    @JoinColumn(name = "dealerEntity")
//    private DealerEntity dealerEntity;

//    @ManyToOne
//    @JoinColumn(name = "dealer", nullable = true)
//    private DealerEntity dealer;



    @JsonIgnore
    @OneToMany(mappedBy = "serviceId")
    Set<ServicesFeedback> servicesFeedback;
//}
//
//    public DealerEntity getDealer() {
//        return dealer;
//    }
//
//    public void setDealer(DealerEntity dealer) {
//        this.dealer = dealer;
//    }

}
