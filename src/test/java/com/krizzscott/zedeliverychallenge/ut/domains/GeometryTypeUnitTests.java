package com.krizzscott.zedeliverychallenge.ut.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.krizzscott.zedeliverychallenge.domains.GeometryType;

class GeometryTypeUnitTests {

	@Test
	void shouldReturnNullWhenGeometryTypeNotFound() {

		// GIVEN
		String type = "teste";

		// WHEN
		String actual = GeometryType.findValue(type);
		
		// THEN
		assertNull(actual);
		
	}
	
	@Test
	void shouldReturnGeometryTypeFound() {
		
		// GIVEN
		String type = "Point";
		String expected = GeometryType.POINT.toString();
		
		// WHEN
		String actual = GeometryType.findValue(type);
		
		// THEN
		assertEquals(expected, actual);
		
	}

}
