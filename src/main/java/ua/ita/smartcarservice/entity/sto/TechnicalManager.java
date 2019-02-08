package ua.ita.smartcarservice.entity.sto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.ita.smartcarservice.entity.UserBaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "sto_manager")
public class TechnicalManager extends UserBaseEntity implements Serializable {

    @JsonIgnore
    @OneToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "sto_id", nullable = false)
    private TechnicalService technicalService;


    @JsonIgnore//ManagedReference
    @OneToMany(mappedBy = "technicalManager")
    Set<Worker> workers;
}