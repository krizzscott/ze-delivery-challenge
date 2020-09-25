package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.converters;

import com.krizzscott.zedeliverychallenge.domains.GeoPoint;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.GeoAddressDTO;

public final class GeoPointDTOConverter {
	
	private GeoPointDTOConverter() {}

	public static GeoPoint toDomain(GeoAddressDTO schema) {
		return new GeoPoint().toBuilder().coordinates(schema.getCoordinates()).build();
	}

	public static GeoAddressDTO toDTO(GeoPoint domain) {
		return new GeoAddressDTO().toBuilder().type(domain.getType()).coordinates(domain.getCoordinates())
				.build();
	}

}
