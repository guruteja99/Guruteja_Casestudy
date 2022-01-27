package com.casestudy.pension.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.casestudy.pension.entities.PensionerDetail;
import com.casestudy.pension.utils.exception.DetailsNotFoundException;

public class PensionerDetailControllerTest {
	
	@InjectMocks
	PensionerDetailController pensionDetailController;
	
	@Test
	public void testPensionerDetailByAadhaar() throws DetailsNotFoundException {

	    PensionerDetailController pensionDetailController = Mockito.mock(PensionerDetailController.class);
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setId(1L);
		pensionerDetail.setAadhaarNumber("A96704");
		ResponseEntity<? extends PensionerDetail> expected = ResponseEntity.ok(pensionerDetail);
		Mockito.doReturn(expected).when(pensionDetailController.pensionerDetailByAadhaar("A96704"));
		ResponseEntity<?> actual = pensionDetailController.pensionerDetailByAadhaar("A96704");
		assertEquals(pensionerDetail,actual.getBody());
	}
	
	@Test
	public void testPensionerDetailByAadhaarException() {
		boolean result=false;
	    PensionerDetailController pensionDetailController = Mockito.mock(PensionerDetailController.class);
		try {
			Mockito.when(pensionDetailController.pensionerDetailByAadhaar("A96704")).thenThrow(DetailsNotFoundException.class);
			ResponseEntity<?> actual = pensionDetailController.pensionerDetailByAadhaar("A96704");
			result=true;
		} catch (DetailsNotFoundException e) {
			assertFalse(result);
		}
		
		
	}

}
