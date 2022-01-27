package com.casestudy.pension.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PensionerDetail {

	private Long id;
	
	private String name;
	
	private String aadhaarNumber;
	
	private Date dateOfBirth;
	
	private String pan;
	
	private Double salaryEarned;
	
	private Double allowances;
	
	private PensionType pensionType;
	
	private BankDetail bankDetail;
}
