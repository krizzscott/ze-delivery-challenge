package com.krizzscott.zedeliverychallenge.ut.gateway.database.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.impl.PartnerCriteriaRepositoryImpl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@ExtendWith(SpringExtension.class)
public class PartnerCriteriaRepositoryImplUnitTests {
	
	@InjectMocks
	private PartnerCriteriaRepositoryImpl partnerCriteriaRepository;

	@Mock
	private MongoTemplate mongoTemplate;
	
	@BeforeEach
	public void setUp() {
	    FixtureFactoryLoader.loadTemplates("com.krizzscott.zedeliverychallenge.fixtures");
	}

	@Test
	public void shouldReturnPartnerMostNearestByGeoPoint() {
		
		//GIVEN
		final double longitude = 1d;
		final double latitude = 2d;

		PartnerEntity partnerExpected = Fixture.from(PartnerEntity.class).gimme("valid");
		
		GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
		Criteria criteria = Criteria.where("address").near(point).and("coverageArea").intersects(point);
		Query query = new Query(criteria);

		when(mongoTemplate.findOne(any(Query.class), any())).thenReturn(partnerExpected);

		//WHEN
		Optional<PartnerEntity> coverageAreaByGeoPoint = partnerCriteriaRepository.searchPartnerNearestAndInCoverageAreaByGeoPoint(longitude, latitude);
		
		//THEN
		assertTrue(coverageAreaByGeoPoint.isPresent());
		
		verify(mongoTemplate, times(1)).findOne(Mockito.eq(query), any());
		verifyNoMoreInteractions(mongoTemplate);
		
		InOrder inOrder = inOrder(mongoTemplate);
		inOrder.verify(mongoTemplate, times(1)).findOne(Mockito.eq(query), any());
		
	}
	
	@Test
	public void shouldReturnOptionalPartnerEmpty() {
		
		//GIVEN
		final double longitude = 1d;
		final double latitude = 2d;

		GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
		Criteria criteria = Criteria.where("address").near(point).and("coverageArea").intersects(point);
		Query query = new Query(criteria);

		when(mongoTemplate.findOne(any(Query.class), any())).thenReturn(null);
		
		//WHEN
		Optional<PartnerEntity> coverageAreaByGeoPoint = partnerCriteriaRepository.searchPartnerNearestAndInCoverageAreaByGeoPoint(longitude, latitude);
		
		//THEN
		assertFalse(coverageAreaByGeoPoint.isPresent());

		verify(mongoTemplate, times(1)).findOne(Mockito.eq(query), any());
		verifyNoMoreInteractions(mongoTemplate);
		
		InOrder inOrder = inOrder(mongoTemplate);
		inOrder.verify(mongoTemplate, times(1)).findOne(Mockito.eq(query), any());
		
	}
}

