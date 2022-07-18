package com.practice.service;

import com.practice.service.services.CurrencyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = new Date(0);
//		date = Date.valueOf("15.07.2001");
}
