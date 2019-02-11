package ua.ita.smartcarservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartCarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartCarServiceApplication.class, args);
	}

}


