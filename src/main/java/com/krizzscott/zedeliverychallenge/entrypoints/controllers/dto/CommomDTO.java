package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto;

import java.io.UncheckedIOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface CommomDTO {

	default String toJSON() {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new UncheckedIOException(e);
		}
	}
}
