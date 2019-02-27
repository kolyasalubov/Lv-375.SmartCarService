package ua.ita.smartcarservice.entity.technicalservice;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalServiceId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String address;


    @OneToMany(mappedBy = "technicalServiceId", orphanRemoval = true)
    private List<UserTechnicalService> userTechnicalServices;

    @ManyToOne
    @JoinColumn(name = "dealerEntity")
    private DealerEntity dealerEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "serviceId")
    Set<ServicesFeedback> servicesFeedback;
}
