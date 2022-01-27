package com.casestudy.pension.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.casestudy.pension.utils.BankType;
import com.casestudy.pension.utils.PensionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PensionerDetail {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nameOfCandidate;
	
	@Column(unique=true)
	private String aadhaarNumber;
	
	private Date dateOfBirth;
	
	private String pan;
	
	private Double salaryEarned;
	
	private Double allowances;
	
	private PensionType pensionType;
	
	@OneToOne
	private BankDetail bankDetail;

	public PensionerDetail(String nameOfCandidate, String aadhaarNumber, Date dateOfBirth, String pan,
			Double salaryEarned, Double allowances, PensionType pensionType, BankDetail bankDetail) {
		super();
		this.nameOfCandidate = nameOfCandidate;
		this.aadhaarNumber = aadhaarNumber;
		this.dateOfBirth = dateOfBirth;
		this.pan = pan;
		this.salaryEarned = salaryEarned;
		this.allowances = allowances;
		this.pensionType = pensionType;
		this.bankDetail = bankDetail;
	}

	
}
