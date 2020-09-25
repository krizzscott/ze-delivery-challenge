package com.krizzscott.zedeliverychallenge.ut.domains;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.krizzscott.zedeliverychallenge.domains.GeoPoint;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;

class GeoPointUnitTests {

	@Test
	void shouldThrowDomainExceptionWhenCoordinatesIsNull() {

		// GIVEN
		GeoPoint actual = new GeoPoint().toBuilder().coordinates(null).build();

		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});

		// THEN
		assertEquals("Parameter [geopoint.coordinates] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

	}
	
	@Test
	void shouldThrowDomainExceptionWhenCoordinatesIsEmpty() {
		
		// GIVEN
		GeoPoint actual = new GeoPoint().toBuilder().coordinates(Arrays.asList()).build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [geopoint.coordinates] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}

	@Test
	void shouldThrowDomainExceptionWhenCoordinatesIsInvalid() {

		// GIVEN
		GeoPoint actual = new GeoPoint().toBuilder().coordinates(Arrays.asList(1d)).build();

		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});

		// THEN
		assertEquals("Parameter [geopoint.coordinates] must contains 2 coordinates (longitude, latitude)", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

	}
	
	@Test
	void shouldVerifyIfDidMountWithSuccess() {
		
		// GIVEN
		GeoPoint actual = new GeoPoint().toBuilder().coordinates(Arrays.asList(1d, 2d)).build();
		
		// WHEN
		assertDoesNotThrow(() -> {
			actual.didMount();
		});
		
		// THEN
		
	}

}
