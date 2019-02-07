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

    @GetMapping("/salesmanagers/")
    @ResponseBody
    public List<SalesManager> getAllSalesManagers() {
        return salesManagerService.getAllSalesManagers();
    }

    @PostMapping("/salesmanagers")
    public void AddSalesManager(@RequestBody SalesManagerDto salesManagerDto) {
        salesManagerService.save(salesManagerDto);
    }

    @PutMapping("/salesmanagers/{name}")
    @ResponseBody
    public void updateSalesManager(
            @PathVariable String name,
            @RequestBody SalesManagerDto salesManagerDto) {
        SalesManager salesManager = salesManagerService.getSalesManagerByUserName(name);

        salesManagerService.updateSalesManager((Long) salesManager.getId(), salesManagerDto);
//        salesManagerService.save(salesManagerService.entityToDto(salesManager));
    }


    @DeleteMapping("/salesmanagers/{name}")
    public void deleteSalesManager(@PathVariable String name) {
        salesManagerService.deleteSalesManagerByUsername(name);
    }

    @DeleteMapping("/salesmanagers/")
    public void deleteAllSalesManagers() {
        salesManagerService.deleteAllSalesManagers();
    }


}
