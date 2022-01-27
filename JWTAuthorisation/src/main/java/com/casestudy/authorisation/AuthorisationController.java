package com.casestudy.authorisation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.authorisation.models.AuthenicationCheck;
import com.casestudy.authorisation.models.AuthenicationRequest;
import com.casestudy.authorisation.models.AuthenicationResponse;
import com.casestudy.authorisation.services.MyUserDetailsService;
import com.casestudy.authorisation.util.JwtUtil;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@CrossOrigin
public class AuthorisationController {

	@Autowired
	private AuthenticationManager authenicationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/validateToken")
	public boolean validateToken(@RequestBody AuthenicationCheck authenicationCheck) {
		
		if(null != request.getSession(false)) {
			return false;
		}else {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenicationCheck.getUsername());
			log.info(authenicationCheck);
			return jwtTokenUtil.validateToken(authenicationCheck.getToken(), userDetails);	
		}
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAnthenticationToken(@RequestBody AuthenicationRequest authenicationRequest)
			throws Exception {
		log.info(authenicationRequest);
		try {

			authenicationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenicationRequest.getUsername(), authenicationRequest.getPassword()));

		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().build();
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenicationRequest.getUsername());

		session.setAttribute("userDetails", userDetails);
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenicationResponse(jwt));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(){
		session.invalidate();
		return ResponseEntity.accepted().build();
	}

}
