package com.casestudy.pension.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetail {

	private int id;
	
	private String bankName;
	
	private Long accountNumber;
	
	private BankType bankType;
}
