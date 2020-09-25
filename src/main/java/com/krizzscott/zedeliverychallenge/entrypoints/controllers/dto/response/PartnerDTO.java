package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.response;

import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.CoverageAreaDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.GeoAddressDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PartnerDTO {
	
	private String id;
	private String tradingName;
	private String ownerName;
	private String document;
	private CoverageAreaDTO coverageArea;
	private GeoAddressDTO address;
	
}
