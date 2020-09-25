package com.krizzscott.zedeliverychallenge.domains.converters;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;

public final class PartnerConverter {
	
	private PartnerConverter() {}

	public static Partner toDomain(PartnerEntity entity) {
		return new Partner().toBuilder().id(entity.getId()).ownerName(entity.getOwnerName())
				.tradingName(entity.getTradingName()).document(entity.getDocument())
				.address(GeoPointConverter.toDomain(entity.getAddress()))
				.coverageArea(GeoMultiPolygonConverter.toDomain(entity.getCoverageArea())).build();
	}
}
