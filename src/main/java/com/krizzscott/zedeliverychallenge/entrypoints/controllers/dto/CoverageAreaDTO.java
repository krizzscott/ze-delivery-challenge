package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CoverageAreaDTO {
	
	@ApiModelProperty(value = "Tipo de GeoJson que será informado na coordenada (informar = MultiPolygon)", 
			example = "MultiPolygon", 
			allowableValues = "MultiPolygon",
			required = true)
	@NotNull(message = "Parameter [coverageArea.type] cannot be null or invalid")
	private String type;

	@ApiModelProperty(value = "Coordenadas da Localizacao do Estabelecimento Comercial", 
			example = "[\n" + 
					"        [\n" + 
					"            [[40, 40], [20, 45], [45, 30], [40, 40]]\n" + 
					"        ], \n" + 
					"        [\n" + 
					"            [[20, 35], [10, 30], [10, 10], [30, 5], [45, 20], [20, 35]], \n" + 
					"            [[30, 20], [20, 15], [20, 25], [30, 20]]\n" + 
					"        ]\n" + 
					"    ]",
			required = true)
	@NotNull(message = "Parameter [coverageArea.coordinates] cannot be null")
	private List<List<List<List<Double>>>> coordinates;

}
