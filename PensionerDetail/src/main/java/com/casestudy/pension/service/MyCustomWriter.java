package com.casestudy.pension.service;

import java.sql.Date;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.pension.entities.BankDetail;
import com.casestudy.pension.entities.InputFormat;
import com.casestudy.pension.entities.PensionerDetail;
import com.casestudy.pension.repository.BankDetailRepository;
import com.casestudy.pension.repository.PensionerDetailRepository;

import lombok.extern.apachecommons.CommonsLog;

@Component
@CommonsLog
public class MyCustomWriter implements ItemWriter<InputFormat> {

	@Autowired
	PensionerDetailRepository pensionerRepository;
	
	@Autowired
	BankDetailRepository bankRepository;
	
	@Override
	public void write(List<? extends InputFormat> list) throws Exception {
		
		log.info("In write method to write details to Database");
		for (InputFormat data : list) {
		BankDetail bankDetail = new BankDetail(data.getBankName(),data.getAccountNumber(),data.getBankType());
		bankDetail = bankRepository.save(bankDetail);
		PensionerDetail pensionerDetail =	new PensionerDetail(data.getNameOfCandidate(),data.getAadhaarNumber(),Date.valueOf("1998-11-26"),data.getPan(),data.getSalaryEarned(),data.getAllowances(),data.getPensionType(),bankDetail);
		log.info(pensionerDetail);
		pensionerRepository.save(pensionerDetail);
		}
	}
}