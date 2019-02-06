package com.smartcarservice.ua.SmartCarService.controller;


import com.smartcarservice.ua.SmartCarService.service.SalesManagerService;
import com.smartcarservice.ua.SmartCarService.dto.sales.SalesManagerDto;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan
public class SalesManagerController {

    @Autowired
    private SalesManagerService salesManagerService;
//    @Autowired
//    SalesManagerService salesManagerService;
//    @RequestMapping("/")
//    private String toMainPage(Model model){
 //       model.addAttribute("smAll",salesManagerService.findAll());
//        return "";
//    }
    @GetMapping("/salesmanagers/{name}")
    @ResponseBody
//    @RequestMapping (value = "/salesManagers/{name}",method = RequestMethod.GET)
    public SalesManagerDto getSalesManagerInfo(@PathVariable(value = "name") String name) {
        return salesManagerService.getSalesManagerDto(name);
    }
    @PostMapping("/salesmanagers/add")
    public void AddSalesManager(@RequestBody SalesManagerDto salesManagerDto){
        salesManagerService.save(salesManagerDto);
    }
}
