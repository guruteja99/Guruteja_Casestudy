package com.casestudy.pension.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.casestudy.pension.entity.PensionerDetail;

@Component
@FeignClient(name="pensioner-detail", url= "${pensionerdetail.url}")
public interface PensionerDetailProxy {

	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	public ResponseEntity<PensionerDetail> getPensionerDetails(@PathVariable String aadhaarNumber);
}
