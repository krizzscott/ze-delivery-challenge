package com.krizzscott.zedeliverychallenge.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ErrorResponse {
	
	private String message;
	private int errorCode;


}
