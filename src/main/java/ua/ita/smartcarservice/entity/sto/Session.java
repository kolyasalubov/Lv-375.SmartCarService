package ua.ita.smartcarservice.entity.sto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import ua.ita.smartcarservice.entity.car.Car;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @Column(nullable = false, length = 20, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startSession;

    @Column(nullable = false, length = 20, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endSession;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Car car;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public void setStartSession(LocalDateTime startSession) {
        this.startSession = startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public void setEndSession(LocalDateTime endSession) {
        this.endSession = endSession;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
