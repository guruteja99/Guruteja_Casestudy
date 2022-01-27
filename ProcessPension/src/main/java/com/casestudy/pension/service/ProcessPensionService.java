package com.casestudy.pension.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.pension.entity.Authorisation;
import com.casestudy.pension.entity.BankType;
import com.casestudy.pension.entity.PensionDetail;
import com.casestudy.pension.entity.PensionType;
import com.casestudy.pension.entity.PensionerDetail;
import com.casestudy.pension.entity.ProcessPensionInput;
import com.casestudy.pension.proxy.JWTUtil;
import com.casestudy.pension.proxy.PensionerDetailProxy;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class ProcessPensionService {

	@Autowired
	private PensionerDetailProxy proxy;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public ResponseEntity<?> getPensionerDetail(Authorisation request,ProcessPensionInput pensionInput) throws Exception {
		log.info("Making rest call for "+pensionInput.getAadaarNumber());
		ResponseEntity<PensionerDetail> pensioner = proxy.getPensionerDetails(pensionInput.getAadaarNumber());
		return pensioner;
	}
	
	public PensionDetail processPensionDetail(PensionerDetail pensionerDetail) {
		log.info("In processPensionDetail method of ProcessPensionService");
		PensionDetail pensionDetail = new PensionDetail();
		pensionDetail.setPensionAmount(getPensionAmount(pensionerDetail));
		pensionDetail.setBankServiceCharge(getBankServiceCharge(pensionerDetail.getBankDetail().getBankType()));
		log.info("In processPensionDetail method of ProcessPensionService -- completed");
		return pensionDetail;
	}
	
	private static Double getPensionAmount(PensionerDetail pensionerDetail) {
		log.info("processing pension amount in getPensionAmount method");
		Double pensionAmount;
		if(pensionerDetail.getPensionType().equals(PensionType.SELF)) {
			pensionAmount = (pensionerDetail.getSalaryEarned()*0.8)+pensionerDetail.getAllowances();
		}
		else{
			pensionAmount = (pensionerDetail.getSalaryEarned()*0.5)+pensionerDetail.getAllowances();
		}
		log.info("processing pension amount in getPensionAmount method -- completed");
		return pensionAmount;
	}
	
	private static Long getBankServiceCharge(BankType bankType) {
		log.info("processing service charge in getBankServiceCharge method");
		if(bankType.equals(BankType.PUBLIC)) {
			return 500L;
		}else {
			return 550L;
		}
	}
}
