package ua.ita.smartcarservice.entity.sto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @JsonManagedReference
    @OneToMany(mappedBy = "technicalManager")
    Set<Worker> workers;

    public TechnicalService getTechnicalService() {
        return technicalService;
    }

    public void setTechnicalService(TechnicalService technicalService) {
        this.technicalService = technicalService;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }
}