package com.smartcarservice.ua.SmartCarService.entity.sto;


import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startSession;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endSession;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

}
