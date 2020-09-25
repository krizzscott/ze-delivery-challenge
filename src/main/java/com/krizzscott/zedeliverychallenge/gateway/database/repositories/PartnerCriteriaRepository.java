package com.krizzscott.zedeliverychallenge.gateway.database.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;

@Repository
public interface PartnerCriteriaRepository {
	
	Optional<PartnerEntity> searchPartnerNearestAndInCoverageAreaByGeoPoint(final double longitude, final double latitude);

}
