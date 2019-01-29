package com.smartcarservice.ua.SmartCarService.entity.sensors.data;

import com.smartcarservice.ua.SmartCarService.entity.sensors.DateEntity;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class BaseRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "car_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private CarEntity carEntity;  TODO add later

//    TODO fix foreign key & add to Car
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carEntity")
//    private List<RecordEntity> records;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "date_id", nullable = false)
    private DateEntity dateEntity;

}
