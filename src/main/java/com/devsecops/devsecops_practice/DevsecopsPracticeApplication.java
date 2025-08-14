package com.devsecops.devsecops_practice;

import java.util.logging.Logger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevsecopsPracticeApplication {

	 public static  final String AWS_KEY = "AKIAIOSFODNN7EXAMPLE";

	public static void main(String[] args) {
		System.out.println("kdasdasdsd");
		System.out.println("AWS_KEY"+" "+ AWS_KEY);
		
		SpringApplication.run(DevsecopsPracticeApplication.class, args);
	}

}
