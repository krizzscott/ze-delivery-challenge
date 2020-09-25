package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.converters;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.request.PartnerRequestDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.response.PartnerDTO;

public final class PartnerDTOConverter {

	private PartnerDTOConverter() {}
	
	public static Partner toDomain(PartnerRequestDTO schema) {
		return new Partner().toBuilder()
				.ownerName(schema.getOwnerName())
				.tradingName(schema.getTradingName())
				.document(schema.getDocument())
				.address(GeoPointDTOConverter.toDomain(schema.getAddress()))
				.coverageArea(GeoMultiPolygonDTOConverter.toDomain(schema.getCoverageArea()))
				.build();
	}
	
	public static PartnerDTO toDTO(Partner domain) {
		return new PartnerDTO().toBuilder()
				.id(domain.getId())
				.ownerName(domain.getOwnerName())
				.tradingName(domain.getTradingName())
				.document(domain.getDocument())
				.address(GeoPointDTOConverter.toDTO(domain.getAddress()))
				.coverageArea(GeoMultiPolygonDTOConverter.toDTO(domain.getCoverageArea()))
				.build();	
	}

}
