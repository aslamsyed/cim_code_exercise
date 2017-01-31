package com.comcast.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.comcast.model.Ad;

@Transactional
public interface AdRepository extends MongoRepository<Ad, String> {
	
	
}
