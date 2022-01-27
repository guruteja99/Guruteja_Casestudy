package com.casestudy.pension.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.casestudy.pension.entities.InputFormat;
import com.casestudy.pension.service.PensionerDetailLoader;

import lombok.extern.apachecommons.CommonsLog;

@Configuration
@EnableBatchProcessing
@CommonsLog
public class BatchConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	PensionerDetailLoader myCustomReader;
	
	@Autowired
	MyCustomWriter myCustomWriter;

	@Bean
	public Job createJob() {
		log.info("Initiating job");
		return jobBuilderFactory.get("MyJob")
				.incrementer(new RunIdIncrementer())
				.flow(createStep()).end().build();
	}

	@Bean
	public Step createStep() {
		log.info("Invoking the writer");
		return stepBuilderFactory.get("MyStep")
				.<InputFormat, InputFormat> chunk(1)
				.reader(myCustomReader)
				.writer(myCustomWriter)
				.build();
	}	
}
