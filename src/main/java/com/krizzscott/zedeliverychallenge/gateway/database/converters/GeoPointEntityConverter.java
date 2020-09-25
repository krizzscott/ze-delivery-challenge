package com.krizzscott.zedeliverychallenge.gateway.database.converters;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.krizzscott.zedeliverychallenge.domains.GeoPoint;

public final class GeoPointEntityConverter {

	private GeoPointEntityConverter() {
	}

	static GeoJsonPoint toEntity(GeoPoint domain) {
		return new GeoJsonPoint(domain.getCoordinates().get(0), domain.getCoordinates().get(1));
	}

}
