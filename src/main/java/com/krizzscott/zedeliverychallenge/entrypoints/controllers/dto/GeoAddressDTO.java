package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GeoAddressDTO {

	@ApiModelProperty(value = "Tipo de GeoJson que será informado na coordenada (informar = Point)", 
			example = "Point", 
			allowableValues = "Point",
			required = true)
	@NotNull(message = "Parameter [address.type] cannot be null")
	private String type;

	@ApiModelProperty(value = "Coordenadas da Localizacao do Estabelecimento Comercial", 
			example = "[125.6, 10.1]",
			required = true)
	@NotNull(message = "Parameter [address.coordinates] cannot be null")
	@Size(min = 2, max = 2, message = "Parameter [address.coordinates] must contains 2 values, longitude and latitude")
	private List<Double> coordinates;

}
