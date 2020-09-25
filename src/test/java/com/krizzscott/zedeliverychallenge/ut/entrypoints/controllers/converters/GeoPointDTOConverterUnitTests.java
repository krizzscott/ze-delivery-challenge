package com.krizzscott.zedeliverychallenge.ut.entrypoints.controllers.converters;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.krizzscott.zedeliverychallenge.domains.GeoPoint;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.GeoAddressDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.converters.GeoPointDTOConverter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

class GeoPointDTOConverterUnitTests {

	@BeforeAll
	static void setUp() {
	    FixtureFactoryLoader.loadTemplates("com.krizzscott.zedeliverychallenge.fixtures");
	}
	
	@Test
	void shouldReturnDTO() {
		// GIVEN
		GeoPoint geoPoint = Fixture.from(GeoPoint.class).gimme("valid");

		// WHEN
		GeoAddressDTO actual = GeoPointDTOConverter.toDTO(geoPoint);

		// THEN
		assertNotNull(actual);
		assertEquals(geoPoint.getType(), actual.getType());
		assertEquals(geoPoint.getCoordinates(), actual.getCoordinates());
	}
	
	@Test
	void shouldReturnDomain() {
		// GIVEN
		GeoAddressDTO geoPoint = Fixture.from(GeoAddressDTO.class).gimme("valid");
		
		// WHEN
		GeoPoint actual = GeoPointDTOConverter.toDomain(geoPoint);
		
		// THEN
		assertNotNull(actual);
		assertEquals(geoPoint.getType(), actual.getType());
		assertEquals(geoPoint.getCoordinates(), actual.getCoordinates());
	
	}

}
