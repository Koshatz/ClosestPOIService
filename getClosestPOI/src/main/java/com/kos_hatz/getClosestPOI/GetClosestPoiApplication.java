package com.kos_hatz.getClosestPOI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GetClosestPoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetClosestPoiApplication.class, args);
	}

}
