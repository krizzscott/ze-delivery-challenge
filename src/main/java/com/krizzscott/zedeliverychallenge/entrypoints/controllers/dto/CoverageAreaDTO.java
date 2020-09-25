package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CoverageAreaDTO {

	@NotNull(message = "Parameter [coverageArea.type] cannot be null or invalid")
	private String type;

	@NotNull(message = "Parameter [coverageArea.coordinates] cannot be null")
	private List<List<List<List<Double>>>> coordinates;

}
