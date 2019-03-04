package ua.ita.smartcarservice.entity.technicalservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
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

    @JsonIgnore
    @OneToMany(mappedBy = "serviceId")
    Set<ServicesFeedback> servicesFeedback;



    @JsonIgnore
    @OneToMany(mappedBy = "technicalService")
    private List<ReportEntity> reports;

}
