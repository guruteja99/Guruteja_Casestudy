package com.casestudy.pension.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.pension.entity.Authorisation;
import com.casestudy.pension.entity.PensionDetail;
import com.casestudy.pension.entity.PensionerDetail;
import com.casestudy.pension.entity.ProcessPensionInput;
import com.casestudy.pension.proxy.JWTUtil;
import com.casestudy.pension.service.ProcessPensionService;
import com.casestudy.pension.utils.exception.DetailsNotFoundException;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@CrossOrigin
public class ProcessPensionController {

	@Autowired
	ProcessPensionService processPensionService;
	
	@Autowired
	JWTUtil jwtUtil;

	@PostMapping("/ProcessPension")
	public ResponseEntity<?> processPension(@RequestHeader(value="Authorisation") String jwt,@RequestHeader(value="currentUser") String username,@RequestBody ProcessPensionInput processPensionInput) throws Exception {
		Authorisation request = new Authorisation(username,jwt);
		log.info("Validating JWT using authorisation");
		if (jwtUtil.validateToken(request)) {
			log.info("In pension process controller with parameters " + processPensionInput);
			ResponseEntity<?> pensionerResponse = processPensionService.getPensionerDetail(request,processPensionInput);
			if (pensionerResponse.getStatusCode() == HttpStatus.OK) {
				log.info("processing pension Details with parameters " + pensionerResponse.getBody());
				PensionerDetail pensioner = (PensionerDetail) pensionerResponse.getBody();
				PensionDetail pensionDetail = processPensionService.processPensionDetail(pensioner);
				return new ResponseEntity(pensionDetail, HttpStatus.OK);
			} else if(pensionerResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new DetailsNotFoundException("User with "+processPensionInput.getAadaarNumber()+" not found");
			}else {
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
			}
		} else {
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}
}
