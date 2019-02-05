package ua.ita.smartcarservice.entity.sto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ua.ita.smartcarservice.entity.sales.Dealer;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "sto")
public class TechnicalService {

    public TechnicalService(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stoId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String address;


    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    @JsonIgnore
    @OneToOne(//fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "technicalService")
    private TechnicalManager technicalManager;

    //@JsonIgnore//not working
    @JsonManagedReference
    @OneToMany(mappedBy = "technicalService")
    private Set<Worker> workers;

}
