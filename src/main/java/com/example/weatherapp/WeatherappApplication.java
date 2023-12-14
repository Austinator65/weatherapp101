package com.example.weatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class WeatherappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherappApplication.class, args);
	}

}
