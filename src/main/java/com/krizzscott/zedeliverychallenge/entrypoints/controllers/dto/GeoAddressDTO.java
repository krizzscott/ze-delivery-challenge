package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GeoAddressDTO {

	@NotNull(message = "Parameter [address.type] cannot be null")
	private String type;

	@NotNull(message = "Parameter [address.coordinates] cannot be null")
	@Size(min = 2, max = 2, message = "Parameter [address.coordinates] must contains 2 values, longitude and latitude")
	private List<Double> coordinates;

}
