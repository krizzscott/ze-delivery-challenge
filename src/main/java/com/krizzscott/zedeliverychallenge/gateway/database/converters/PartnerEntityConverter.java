package com.krizzscott.zedeliverychallenge.gateway.database.converters;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;

public final class PartnerEntityConverter {

	public static PartnerEntity toEntity(Partner domain) {
		return new PartnerEntity().toBuilder()
				.id(domain.getId())
				.ownerName(domain.getOwnerName())
				.tradingName(domain.getTradingName())
				.document(domain.getDocument())
				.address(GeoPointEntityConverter.toEntity(domain.getAddress()))
				.coverageArea(GeoMultiPolygonEntityConverter.toEntity(domain.getCoverageArea()))
				.build();
	}
	
}
