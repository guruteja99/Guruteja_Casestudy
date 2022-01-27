package com.casestudy.pension.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.casestudy.pension.utils.BankType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankDetail {

	@Id
	@GeneratedValue
	private int Id;
	
	private String bankName;
	
	private String accountNumber;
	
	private BankType bankType;

	public BankDetail(String bankName, String accountNumber, BankType bankType) {
		super();
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.bankType = bankType;
	}
}
