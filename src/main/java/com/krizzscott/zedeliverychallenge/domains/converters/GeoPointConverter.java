package com.krizzscott.zedeliverychallenge.domains.converters;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.krizzscott.zedeliverychallenge.domains.GeoPoint;
import com.krizzscott.zedeliverychallenge.domains.GeometryType;

public class GeoPointConverter {

	public static GeoPoint toDomain(GeoJsonPoint geoJsonPoint) {
		return new GeoPoint().toBuilder().type(GeometryType.findValue(geoJsonPoint.getType()))
				.coordinates(geoJsonPoint.getCoordinates()).build();
	}

}
