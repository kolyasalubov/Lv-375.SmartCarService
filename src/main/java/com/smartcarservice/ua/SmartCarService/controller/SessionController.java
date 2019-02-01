package com.smartcarservice.ua.SmartCarService.controller;

import com.smartcarservice.ua.SmartCarService.dto.StoDto.SessionDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Session;
import com.smartcarservice.ua.SmartCarService.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/sessionbyid")
    public List<SessionDto> findAllByWorker_WorkerId(@RequestParam(value = "workerId", required = false)
                                                              String workerId){
        return sessionService.findAllByWorker_WorkerId(Long.valueOf(workerId));

    }

}
