package com.krizzscott.zedeliverychallenge.ut.entrypoints.controllers.converters;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.converters.PartnerDTOConverter;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.request.PartnerRequestDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.response.PartnerDTO;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

class PartnerDTOConverterUnitTests {
	
	@BeforeAll
	static void setUp() {
	    FixtureFactoryLoader.loadTemplates("com.krizzscott.zedeliverychallenge.fixtures");
	}

	@Test
	void shouldReturnDTO() {
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-valid-with-id");

		//WHEN
		PartnerDTO actual = PartnerDTOConverter.toDTO(partner);
		
		//THEN
		assertNotNull(actual);
		assertEquals(partner.getId(), actual.getId());
		assertEquals(partner.getTradingName(), actual.getTradingName());
		assertEquals(partner.getOwnerName(), actual.getOwnerName());
		assertEquals(partner.getDocument(), actual.getDocument());
		
		assertNotNull(actual.getAddress());
		assertEquals(partner.getAddress().getType(), actual.getAddress().getType());
		assertEquals(partner.getAddress().getCoordinates(), actual.getAddress().getCoordinates());
		
		assertNotNull(actual.getCoverageArea());
		assertEquals(partner.getCoverageArea().getType(), actual.getCoverageArea().getType());
		assertEquals(partner.getCoverageArea().getCoordinates(), actual.getCoverageArea().getCoordinates());
		
	}
	
	@Test
	void shouldReturnDomainWithoutID() {
		//GIVEN
		PartnerRequestDTO partner = Fixture.from(PartnerRequestDTO.class).gimme("valid");
		
		//WHEN
		Partner actual = PartnerDTOConverter.toDomain(partner);
		
		//THEN
		assertNotNull(actual);
		assertEquals(partner.getTradingName(), actual.getTradingName());
		assertEquals(partner.getOwnerName(), actual.getOwnerName());
		assertEquals(partner.getDocument(), actual.getDocument());
		
		assertNotNull(actual.getAddress());
		assertEquals(partner.getAddress().getType(), actual.getAddress().getType());
		assertEquals(partner.getAddress().getCoordinates(), actual.getAddress().getCoordinates());
		
		assertNotNull(actual.getCoverageArea());
		assertEquals(partner.getCoverageArea().getType(), actual.getCoverageArea().getType());
		assertEquals(partner.getCoverageArea().getCoordinates(), actual.getCoverageArea().getCoordinates());
		
	}
}
