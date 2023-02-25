package com.mycalories.caloriesrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mycalories")
public class CaloriesRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaloriesRestApplication.class, args);
	}

}
