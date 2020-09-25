package com.krizzscott.zedeliverychallenge.ut.domains;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.krizzscott.zedeliverychallenge.domains.GeoMultiPolygon;
import com.krizzscott.zedeliverychallenge.domains.GeoPoint;
import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;

class PartnerUnitTests {

	@Test
	void shouldThrowDomainExceptionWhenTradingNameIsNull() {
		// GIVEN
		Partner actual = new Partner();

		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});

		// THEN
		assertEquals("Parameter [tradingName] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

	}
	
	@Test
	void shouldThrowDomainExceptionWhenOwnerNameIsNull() {
		// GIVEN
		Partner actual = new Partner().toBuilder().tradingName("Trading Name Test").build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [ownerName] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldThrowDomainExceptionWhenDocumentIsNull() {
		// GIVEN
		Partner actual = new Partner().toBuilder()
				.tradingName("Trading Name Test")
				.ownerName("Owner Name Test")
				.build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [document] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldThrowDomainExceptionWhenDocumentContainsLengthDiffOf14Numbers() {
		// GIVEN
		Partner actual = new Partner().toBuilder()
				.tradingName("Trading Name Test")
				.ownerName("Owner Name Test")
				.document("212121212121")
				.build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [document] must contains 14 numbers", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldThrowDomainExceptionWhenAddressIsNull() {
		// GIVEN
		Partner actual = new Partner().toBuilder()
				.tradingName("Trading Name Test")
				.ownerName("Owner Name Test")
				.document("12345678912345")
				.build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [address] cannot be null", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldThrowDomainExceptionWhenCoverageAreaIsNull() {
		// GIVEN
		Partner actual = new Partner().toBuilder()
				.tradingName("Trading Name Test")
				.ownerName("Owner Name Test")
				.document("12345678912345")
				.address(GeoPoint.builder().coordinates(Arrays.asList(1d, 2d)).build())
				.build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [coverageArea] cannot be null", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}

	@Test
	void shouldVerifyIfDidMountWithSuccess() {
		// GIVEN
		Partner actual = new Partner().toBuilder()
				.tradingName("Trading Name Test")
				.ownerName("Owner Name Test")
				.document("12345678912345")
				.address(GeoPoint.builder().coordinates(Arrays.asList(1d, 2d)).build())
				.coverageArea(new GeoMultiPolygon().toBuilder().coordinates(Arrays.asList(Arrays.asList(Arrays.asList(Arrays.asList(1d, 2d, 3d, 4d, 5d))))).build())
				.build();
		
		// WHEN
		assertDoesNotThrow(() -> {
			actual.didMount();
		});
		
		// THEN
		
	}

}
