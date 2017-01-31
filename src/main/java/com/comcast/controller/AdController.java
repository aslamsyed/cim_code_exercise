/**
 * 
 */
package com.comcast.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.comcast.model.Ad;
import com.comcast.model.AdRepository;

/**
 * @author Aslam Syed
 *
 */
@RestController
@RequestMapping(value="/ad")
public class AdController {

	@Autowired
	AdRepository adRepository;

	/*
	 * Post Ad API
	 */
	@RequestMapping(value="/", method = RequestMethod.POST)
	public Map<String, Object> createAd(@RequestBody Ad ad) {
		if(StringUtils.isEmpty(ad.getPartner_id())){
			throw new IllegalArgumentException("Partner Id is mandatory");
		}
		if(StringUtils.isEmpty(ad.getAd_content())){
			throw new IllegalArgumentException("Ad Content is mandatory");
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List <Ad> adList = adRepository.findAll();
		for(Ad adFromDB: adList){
			if(ad.getPartner_id().equals(adFromDB.getPartner_id())){
				throw new IllegalArgumentException("Partner Id is already associated with the valid Ad in the system");
			}
		}
		ad.setSessionToken(new Date().getTime());
		ad = adRepository.save(ad);
		dataMap.put("message", "Ad created successfully");
		dataMap.put("status", "1");
		dataMap.put("ad", ad);
		return dataMap;

	}
	
	/*
	 * Get AD by Parnter ID API
	 */
	@RequestMapping(value="/{partner_id}", method = RequestMethod.GET)
	public Ad getAd(@PathVariable("partner_id") String partner_id) {
		if(StringUtils.isEmpty(partner_id)){
			throw new IllegalArgumentException("Partner Id is mandatory");
		}
		List <Ad> list = adRepository.findAll();
		for(Ad ad : list){
			if(ad.getPartner_id().equals(partner_id)){
				if(isTokenValid(ad.getSessionToken(), ad.getDuration())){
					ad.setStatus("Active");
				return ad;
				}else{
					throw new IllegalArgumentException("Your Ad is expired");
				}
			}
		}
		throw new IllegalArgumentException("Partner Id doesn't exist in our system");
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());

	}
	
    public static boolean isTokenValid(long token, int duration) {
    	duration = duration * 60000;
        Long todayTime = new Date().getTime();
        Long timeElapsed = todayTime - token;
       return timeElapsed <= duration;
    }	
	public long generateToken(){
        return new Date().getTime();

	}
	
}
