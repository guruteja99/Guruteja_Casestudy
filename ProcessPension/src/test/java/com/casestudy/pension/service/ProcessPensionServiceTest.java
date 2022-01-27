package com.casestudy.pension.service;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.casestudy.pension.entity.Authorisation;
import com.casestudy.pension.entity.PensionerDetail;
import com.casestudy.pension.entity.ProcessPensionInput;

public class ProcessPensionServiceTest extends ProcessPensionService {

	//@Test
	public void testGetPensionerDetail() throws Exception {
		ProcessPensionService processPensionService = Mockito.mock(ProcessPensionService.class);
		Authorisation request = new Authorisation();
		ProcessPensionInput input = new ProcessPensionInput();
		PensionerDetail pensionerDetail = new PensionerDetail();
		ResponseEntity<? extends PensionerDetail> expected = ResponseEntity.of(Optional.of(pensionerDetail));
		Mockito.doReturn(expected).when(processPensionService.getPensionerDetail(request, input));
		ResponseEntity<?> actual = processPensionService.getPensionerDetail(request, input);
		assertEquals(expected,actual);
	}


}
