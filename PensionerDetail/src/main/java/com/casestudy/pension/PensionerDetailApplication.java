package com.casestudy.pension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@EnableDiscoveryClient
@CommonsLog
public class PensionerDetailApplication {

	public static void main(String[] args) {
		log.info("In pensioner detail main method");
		SpringApplication.run(PensionerDetailApplication.class, args);
	}

}
