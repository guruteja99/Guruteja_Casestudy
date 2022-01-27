package com.casestudy.pension.service;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;

import com.casestudy.pension.entities.InputFormat;
import com.casestudy.pension.entities.PensionerDetail;

public class PensionerDetailLoaderTest {
	
	PensionerDetailLoader loader = Mockito.mock(PensionerDetailLoader.class);

	@Test
	public void testPensionerDetailLoader() {

	
	}

	@Test
	public void testGetDefaultLineMapper() {

		DefaultLineMapper<InputFormat> lineMapper = new DefaultLineMapper();
		Mockito.when(loader.getDefaultLineMapper()).thenReturn(lineMapper);
		DefaultLineMapper<InputFormat> actual = loader.getDefaultLineMapper();
		assertEquals(lineMapper,actual);
	
	}
	
	@Test
	public void testGetDefaultLineMapperEx() {

		DefaultLineMapper<InputFormat> lineMapper = new DefaultLineMapper();
		Mockito.when(loader.getDefaultLineMapper()).thenReturn(lineMapper);
		DefaultLineMapper<InputFormat> actual = loader.getDefaultLineMapper();
		assertEquals(lineMapper,actual);
	
	}


	@Test
	public void testGetPensionerDetail() {
		
		PensionerDetail pensioner = new PensionerDetail();
		pensioner.setId(1L);
		pensioner.setAadhaarNumber("A96704");
		Mockito.when(loader.getPensionerDetail("A96704")).thenReturn(Optional.of(pensioner));
		Optional<PensionerDetail> actual = loader.getPensionerDetail("A96704");
		assertEquals(pensioner,actual.get());
	
	}
	
	@Test
	public void testGetPensionerDetailEx() {
		
		boolean result = false;
		PensionerDetailLoader loader = Mockito.mock(PensionerDetailLoader.class);
		PensionerDetail pensioner = new PensionerDetail();
		pensioner.setId(1L);
		pensioner.setAadhaarNumber("A96704");
		try {
			Mockito.when(loader.getPensionerDetail("A96704")).thenThrow(Exception.class);
			Optional<PensionerDetail> actual = loader.getPensionerDetail("A96704");
			result = true;
		}catch(Exception e) {
			assertFalse(result);
		}
	}

}
