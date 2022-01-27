package com.casestudy.pension.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.pension.entities.PensionerDetail;
import com.casestudy.pension.repository.PensionerDetailRepository;
import com.casestudy.pension.service.PensionerDetailLoader;
import com.casestudy.pension.utils.exception.DetailsNotFoundException;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
public class PensionerDetailController {

	@Autowired
	private PensionerDetailLoader loader;
	
	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	public ResponseEntity<?> pensionerDetailByAadhaar(@PathVariable String aadhaarNumber) throws DetailsNotFoundException {
		
		log.info("Searching details for "+aadhaarNumber);
		Optional<PensionerDetail> pensionerDetail = loader.getPensionerDetail(aadhaarNumber);
		log.info(pensionerDetail);
		if(pensionerDetail.isEmpty()){
			ResponseEntity<?> negativeResponse = new ResponseEntity("User details not found",HttpStatus.NOT_FOUND);
			return negativeResponse;
		}
		return ResponseEntity.ok(pensionerDetail.get());
	}
}
