package com.casestudy.pension.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.casestudy.pension.entity.PensionDetail;
import com.casestudy.pension.entity.ProcessPensionInput;
import com.casestudy.pension.utils.exception.DetailsNotFoundException;

public class ProcessPensionControllerTest {

	//@Test
	public void testProcessPension() throws Exception {
		ProcessPensionController processPensionController = Mockito.mock(ProcessPensionController.class);
		PensionDetail pensionDetail = new PensionDetail();
		pensionDetail.setPensionAmount(34567.89);
		pensionDetail.setBankServiceCharge(500L);
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		ResponseEntity<? extends PensionDetail> expected = ResponseEntity.ok(pensionDetail);
		Mockito.doReturn(expected).when(processPensionController.processPension("xyz", "yui",processPensionInput));
		ResponseEntity<?> actual = processPensionController.processPension("xyz", "yui", processPensionInput);
		assertNull(expected);
	}

	@Test
	public void testProcessPensionEx() {
		boolean result = false;
		ProcessPensionController processPensionController = Mockito.mock(ProcessPensionController.class);
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		try {
			Mockito.doThrow(DetailsNotFoundException.class).when(processPensionController.processPension("xyz", "yui", processPensionInput));
			ResponseEntity<?> actual = processPensionController.processPension("xyz", "yui", processPensionInput);
			result = true;
		}catch(Exception e) {
			assertFalse(result);
		}
	}
	
}
