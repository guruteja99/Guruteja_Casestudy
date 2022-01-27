package com.casestudy.pension.entities;

import java.sql.Date;

import com.casestudy.pension.utils.BankType;
import com.casestudy.pension.utils.PensionType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputFormat {

	private Long id;
	
	private String nameOfCandidate;
	
	private String aadhaarNumber;
	
	private String dateOfBirth;
	
	private String pan;
	
	private Double salaryEarned;
	
	private Double allowances;
	
	private PensionType pensionType;
	
	private String bankName;
	
	private String accountNumber;
	
	private BankType bankType;
	
}
