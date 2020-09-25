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
		GeoMultiPolygon actual = new GeoMultiPolygon().toBuilder().coordinates(Arrays.asList()).build();

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
		assertEquals("Parameter [geomultipolygon.coordinates] must contains at least 1 polygon",
				exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

	}

	@Test
	void shouldThrowDomainExceptionWhenCoordinatesPolygonsPointsIsEmpty() {

		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon().toBuilder()
				.coordinates(Arrays.asList(Arrays.asList(Arrays.asList()))).build();

		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});

		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] must contains 4 or more positions",
				exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

	}

	@Test
	void shouldThrowDomainExceptionWhenCoordinatesPolygonsPointsContainsLessThan4Positions() {

		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon()
				.toBuilder().coordinates(Arrays.asList(Arrays.asList(Arrays.asList(
						Arrays.asList(1d, 2d),Arrays.asList(1d, 2d), Arrays.asList(1d, 2d)))))
				.build();
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});

		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] must contains 4 or more positions",
				exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

	}
	
	@Test
	void shouldThrowDomainExceptionWhenCoordinatesPolygonsPointContainsLessThan2Coordinates() {
		
		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon()
				.toBuilder().coordinates(Arrays.asList(Arrays.asList(Arrays.asList(
						Arrays.asList(1d, 2d), Arrays.asList(1d, 2d), Arrays.asList(1d, 2d), Arrays.asList(1d, 2d),
						Arrays.asList(1d)))))
				.build();
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] must contains 2 coordinates (longitude, latitude)",
				exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}
	
	@Test
	void shouldThrowDomainExceptionWhenCoordinatesPolygonsPointContainsLessThan2Coordinates_2() {
		
		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon()
				.toBuilder().coordinates(Arrays.asList(Arrays.asList(Arrays.asList(
						Arrays.asList(1d, 2d), Arrays.asList(1d, 2d), Arrays.asList(1d, 2d), Arrays.asList(1d, 2d),
						Arrays.asList()))))
				.build();
		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			actual.didMount();
		});
		
		// THEN
		assertEquals("Parameter [geomultipolygon.coordinates] must contains 2 coordinates (longitude, latitude)",
				exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
	}

	@Test
	void shouldVerifyIfDidMountWithSuccess() {

		// GIVEN
		GeoMultiPolygon actual = new GeoMultiPolygon()
				.toBuilder().coordinates(Arrays.asList(Arrays.asList(Arrays.asList(
						Arrays.asList(1d, 2d), Arrays.asList(1d, 2d), Arrays.asList(1d, 2d), Arrays.asList(1d, 2d),
						Arrays.asList(1d, 2d)))))
				.build();

		// WHEN
		assertDoesNotThrow(() -> {
			actual.didMount();
		});

		// THEN
	}

}
