package com.casestudy.pension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@CommonsLog
public class ProcessPensionApplication {

	public static void main(String[] args) {
		log.info("In pension process main method");
		SpringApplication.run(ProcessPensionApplication.class, args);
	}

}
