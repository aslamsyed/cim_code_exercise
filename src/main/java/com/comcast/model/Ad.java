/**
 * 
 */
package com.comcast.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Aslam Syed
 *
 */
@Document
public class Ad implements Serializable{
	
	public Ad(){
		
	}

	public Ad(String partner_id, int duration, String ad_content, long sessionToken){
		this.partner_id = partner_id;
		this.duration = duration;
		this.ad_content = ad_content;
		this.sessionToken = sessionToken;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	String partner_id;
	int duration;
	String ad_content;
	long sessionToken;
	String status;
	
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	public long getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(long sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
}
