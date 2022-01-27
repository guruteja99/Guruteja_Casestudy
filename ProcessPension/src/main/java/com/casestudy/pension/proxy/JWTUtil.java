package com.casestudy.pension.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.casestudy.pension.entity.Authorisation;

@Component
@FeignClient(name="Authorisation-Service",url="${authorisation.url}")
public interface JWTUtil {

	@PostMapping("/validateToken")
	public boolean validateToken(@RequestBody Authorisation jwt);
}
