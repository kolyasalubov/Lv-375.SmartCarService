package com.smartcarservice.ua.SmartCarService.entity.sto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long requiredTime;

    @JsonBackReference
    @OneToMany(mappedBy = "skill")
    Set<Worker> workers;

    @OneToMany(fetch = FetchType.LAZY,
    		  cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
    		  mappedBy = "skill")
    private List<FaultCode> faultCode;

    public Skill() {
    }

    public Skill(String name, Long requiredTime) {

        this.requiredTime = requiredTime;
        this.name = name;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return name.equals(skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
