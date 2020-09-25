package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.CommomDTO;
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
public class PartnerRequestDTO implements CommomDTO {
	
	@ApiModelProperty(value = "Nome do Estabelecimento Comercial", 
			example = "Adega Ambev de barueri",
			required = true)
	@NotBlank(message = "Parameter [tradingName] cannot be null or empty")
	private String tradingName;
	
	@ApiModelProperty(value = "Nome do Proprietario do Estabelecimento Comercial", 
			example = "Adega Ambev de barueri",
			required = true)
	@NotBlank(message = "Parameter [ownerName] cannot be null or empty")
	private String ownerName;
	
	@ApiModelProperty(value = "CNPJ do Estabelecimento Comercial (somente números)", 
			example = "12345678912345",
			required = true)
	@NotNull(message = "Parameter [document] cannot be null")
	@Size(min = 14, max = 14, message = "Parameter [document] cannot be invalid size, please insert only 14 numbers for CNPJ document")
    @Pattern(regexp = "[0-9]+", message = "Parameter [document] cannot be invalid pattern, please insert only numbers for CNPJ document")
	private String document;

	@ApiModelProperty(value = "Dados da Localizacao do Estabelecimento Comercial", required = true)
	@NotNull(message = "Parameter [address] cannot be null")
	@Valid
	private GeoAddressDTO address;

	@ApiModelProperty(value = "Dados da area de cobertura do Estabelecimento Comercial", required = true)
	@NotNull(message = "Parameter [coverageArea] cannot be null or empty")
	@Valid
	private CoverageAreaDTO coverageArea;
	
	
}
