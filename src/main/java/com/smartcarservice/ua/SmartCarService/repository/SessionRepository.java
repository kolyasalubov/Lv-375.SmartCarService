package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.dto.StoDto.SessionDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findAllByWorker_WorkerId(Long workerId);

}
