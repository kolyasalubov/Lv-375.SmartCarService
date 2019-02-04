package com.smartcarservice.ua.SmartCarService;

import com.smartcarservice.ua.SmartCarService.Repository.SalesManagerRepository;
import com.smartcarservice.ua.SmartCarService.Service.SalesManagerService;
import com.smartcarservice.ua.SmartCarService.ServiceImpl.SalesManagerServiceImpl;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManagerEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SmartCarServiceApplication {

	public static void main(String[] args) {
//        SalesManagerEntity salesManagerEntity = new SalesManagerEntity(6,"asd","sadd","dsadas","dsadsa",new HashSet<Dealer>());
        SalesManagerService salesManagerService=new SalesManagerServiceImpl();
        salesManagerService.save(new SalesManagerEntity("sdsds","asd","sadd","dsadas"));
//        salesManagerService.customSave("sss","sss","sss","sss");
        SpringApplication.run(SmartCarServiceApplication.class, args);
	}

}

