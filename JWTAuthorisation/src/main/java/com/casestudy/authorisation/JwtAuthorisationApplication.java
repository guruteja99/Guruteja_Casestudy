package com.casestudy.authorisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JwtAuthorisationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthorisationApplication.class, args);
	}

}
