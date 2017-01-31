package com.comcast;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.comcast.controller.AdController;
import com.comcast.model.Ad;
import com.comcast.model.AdRepository;

@RunWith(MockitoJUnitRunner.class)
public class AdControllerTest extends TestCase {

	@InjectMocks
	private AdController adController;

	@Mock
	AdRepository adRepository;
	
	
	@Test
	public void testGetAd() {
		Ad expectedAd = new Ad();
		expectedAd.setStatus("Active");
		List <Ad> list = new ArrayList<Ad>();
		list.add(new Ad("100", 30, "BMW Ad", new Date().getTime()));
		list.add(new Ad("101", 30, "Audi Ad", new Date().getTime()));
		list.add(new Ad("102", 30, "Honda Ad", new Date().getTime()));
		
		when(adRepository.findAll()).thenReturn(list);
		Ad ad = adController.getAd("100");
		verify(adRepository).findAll();
		assertEquals(ad.getAd_content(), "BMW Ad");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidatePartnerId() {
		Ad expectedAd = new Ad();
		expectedAd.setStatus("Active");
		List <Ad> list = new ArrayList<Ad>();
		list.add(new Ad("100", 30, "BMW Ad", new Date().getTime()));
		list.add(new Ad("101", 30, "Audi Ad", new Date().getTime()));
		list.add(new Ad("102", 30, "Honda Ad", new Date().getTime()));
		
		when(adRepository.findAll()).thenReturn(list);
		String partnerId = null;
		adController.getAd(partnerId);
	}
	
	
	
	@Test
	public void testPostAd() {
		Ad ad = new Ad("100", 30, "BMW Ad", new Date().getTime());
		when(adRepository.save(Mockito.any(Ad.class))).thenReturn(ad);
		 Map<String,Object> response = adController.createAd(ad);
		verify(adRepository).findAll();
		assertEquals(response.get("status"), "1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validatePostAdMissingPartnerId() {
		Ad ad = new Ad(null, 30, "BMW Ad", new Date().getTime());
		when(adRepository.save(Mockito.any(Ad.class))).thenReturn(ad);
		adController.createAd(ad);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validatePostAdMissingAdContent() {
		Ad ad = new Ad("100", 30, null, new Date().getTime());
		when(adRepository.save(Mockito.any(Ad.class))).thenReturn(ad);
		adController.createAd(ad);
	}

	@Ignore
	@Test(expected = IllegalArgumentException.class)
	public void testExpiredAd() throws InterruptedException {
		Ad expectedAd = new Ad();
		expectedAd.setStatus("Active");
		List <Ad> list = new ArrayList<Ad>();
		list.add(new Ad("100", 1, "BMW Ad", new Date().getTime() - 60000));
		list.add(new Ad("101", 2, "Audi Ad", new Date().getTime()));
		list.add(new Ad("102", 30, "Honda Ad", new Date().getTime()));
		when(adRepository.findAll()).thenReturn(list);
		adController.getAd("100");
	}

}
