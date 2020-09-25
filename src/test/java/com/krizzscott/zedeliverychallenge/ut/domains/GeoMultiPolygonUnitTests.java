package com.krizzscott.zedeliverychallenge.ut.domains;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.krizzscott.zedeliverychallenge.domains.GeoMultiPolygon;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;

class GeoMultiPolygonUnitTests {

	@Test
	void shouldThrowDomainExceptionWhenCoordinatesMultiPolygonIsNull() {
		
		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon();

		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});

		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

	}
	
	@Test
	void shouldThrowDomainExceptionWhenCoordinatesMultiPolygonIsEmpty() {
		
		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon().toBuilder()
				.coordinates(Arrays.asList()).build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldThrowDomainExceptionWhenCoordinatesPolygonsIsEmpty() {
		
		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon().toBuilder().coordinates(Arrays.asList(Arrays.asList())).build();

		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] must contains at least 1 polygon", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldThrowDomainExceptionWhenCoordinatesPolygonsPointsIsEmpty() {
		
		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon().toBuilder().coordinates(Arrays.asList(Arrays.asList(Arrays.asList()))).build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] must contains 5 points of coordinates", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldThrowDomainExceptionWhenCoordinatesPolygonsPointsContainsLessThan5Points() {
		
		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon().toBuilder().coordinates(Arrays.asList(Arrays.asList(Arrays.asList(Arrays.asList(1d, 2d, 3d, 4d))))).build();
		
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] must contains 5 points of coordinates", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldVerifyIfDidMountWithSuccess() {
		
		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon().toBuilder().coordinates(Arrays.asList(Arrays.asList(Arrays.asList(Arrays.asList(1d, 2d, 3d, 4d, 5d))))).build();
		
		// WHEN
		assertDoesNotThrow(() -> {
			actual.didMount();
		});
		
		// THEN
	}
	

}
