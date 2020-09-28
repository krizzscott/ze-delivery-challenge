package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.response;

import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.CoverageAreaDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.GeoAddressDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PartnerDTO {
	
	@ApiModelProperty(value = "Nome do Estabelecimento Comercial", 
			example = "Adega Ambev de barueri",
			required = true)
	private String id;
	
	@ApiModelProperty(value = "Nome do Estabelecimento Comercial", 
			example = "Adega Ambev de barueri",
			required = true)
	private String tradingName;
	
	@ApiModelProperty(value = "Nome do Proprietario do Estabelecimento Comercial", 
			example = "Adega Ambev de barueri",
			required = true)
	private String ownerName;
	
	@ApiModelProperty(value = "CNPJ do Estabelecimento Comercial (somente números)", 
			example = "12345678912345",
			required = true)
	private String document;
	
	@ApiModelProperty(value = "Dados da Localizacao do Estabelecimento Comercial", required = true)
	private CoverageAreaDTO coverageArea;

	@ApiModelProperty(value = "Dados da area de cobertura do Estabelecimento Comercial", required = true)
	private GeoAddressDTO address;
	
}
