package com.smartcarservice.ua.SmartCarService.controller;


import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import com.smartcarservice.ua.SmartCarService.service.SalesManagerService;
import com.smartcarservice.ua.SmartCarService.dto.sales.SalesManagerDto;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
public class SalesManagerController {

    @Autowired
    private SalesManagerService salesManagerService;

    @GetMapping("/salesmanagers/{name}")
    @ResponseBody
    public SalesManagerDto getSalesManagerInfo(@PathVariable(value = "name") String name) {
        return salesManagerService.getSalesManagerDto(name);
    }

    @PostMapping("/salesmanagers")
    public void AddSalesManager(@RequestBody SalesManagerDto salesManagerDto) {
        salesManagerService.save(salesManagerDto);
    }

    @GetMapping("/salesmanagers/")
    @ResponseBody
    public List<SalesManager> getAllSalesManagers() {
        return salesManagerService.getAllSalesManagers();
    }


    @PutMapping("/salesmanagers/{id}")
    @ResponseBody
    public void updateSalesManager(@PathVariable Long id,
                                      @RequestParam(value = "email") String email,
                                      @RequestParam(value = "fullname", required = false) String fullname,
                                      @RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "password", required = false) String password) {
        SalesManager salesManager = salesManagerService.getSalesManagerById(id);
        salesManager.setUserName(username);
        salesManager.setFullName(fullname);
        salesManager.setPassword(password);
        salesManager.setEmail(email);
        salesManagerService.save(salesManagerService.entityToDto(salesManager));
    }

}
