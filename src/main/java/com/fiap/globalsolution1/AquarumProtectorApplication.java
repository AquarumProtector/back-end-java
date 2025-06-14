package com.fiap.globalsolution1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AquarumProtectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AquarumProtectorApplication.class, args);
	}

}
