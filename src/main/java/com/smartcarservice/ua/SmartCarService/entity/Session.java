package com.smartcarservice.ua.SmartCarService.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @Column(nullable = false, unique = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startSession;

    @Column(nullable = false, unique = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endSession;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

}
