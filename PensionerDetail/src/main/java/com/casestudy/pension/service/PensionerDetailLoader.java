package com.casestudy.pension.service;

import java.util.Optional;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.casestudy.pension.entities.InputFormat;
import com.casestudy.pension.entities.PensionerDetail;
import com.casestudy.pension.repository.PensionerDetailRepository;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class PensionerDetailLoader extends FlatFileItemReader<InputFormat>  implements ItemReader<InputFormat>{

	@Autowired
	PensionerDetailRepository pensionerDetailRepository;
	
	public PensionerDetailLoader() {
		setResource(new FileSystemResource("src/main/resources/data/input.csv"));
		setLinesToSkip(1); 
		setLineMapper(getDefaultLineMapper());
	}
	
	public DefaultLineMapper<InputFormat> getDefaultLineMapper() {
		
		log.info("In default line mapper method");
		DefaultLineMapper<InputFormat> defaultLineMapper = new DefaultLineMapper<InputFormat>();
		
		DelimitedLineTokenizer delimitedLineTokenizer =new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames(new String[] { "nameOfCandidate", "aadhaarNumber", "dateOfBirth", "pan", "salaryEarned", "allowances","pensionType","bankName","accountNumber","bankType"});
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		BeanWrapperFieldSetMapper<InputFormat> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<InputFormat>();
		beanWrapperFieldSetMapper.setTargetType(InputFormat.class);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return defaultLineMapper;
	}
	
	public Optional<PensionerDetail> getPensionerDetail(String aadhaarNumber){
		log.info("In pensioner detail loader");
		return pensionerDetailRepository.findByAadhaarNumber(aadhaarNumber);
	}
}
