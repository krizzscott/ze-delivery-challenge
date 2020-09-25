package com.krizzscott.zedeliverychallenge.gateway.database.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;

public interface PartnerRepository extends MongoRepository<PartnerEntity, String>{

	boolean existsByDocument(String document);
	PartnerEntity findByDocument(String document);

}
