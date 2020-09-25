package com.krizzscott.zedeliverychallenge.gateway.database.repositories.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerCriteriaRepository;

@Repository
public class PartnerCriteriaRepositoryImpl implements PartnerCriteriaRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<PartnerEntity> searchPartnerNearestAndInCoverageAreaByGeoPoint(final double longitude, final double latitude) {
		GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
		Criteria criteria = Criteria.where("address").near(point).and("coverageArea").intersects(point);
		Query query = new Query(criteria);
		return Optional.ofNullable(mongoTemplate.findOne(query, PartnerEntity.class));
	}

}
