package com.krizzscott.zedeliverychallenge.exceptions;

import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.ACCESS.ERROR_CODE;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.ACCESS.ERROR_MESSAGE;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.ACCESS.HTTP_STATUS;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogEvent.create;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.krizzscott.zedeliverychallenge.configurations.log.LogEvent;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.NotFoundException;

@RestControllerAdvice
public class ExceptionHandler {

	private static final Logger LOG_ERROR = LogEvent.logger("ERROR");

	@RequestMapping(produces = "application/json")
	@org.springframework.web.bind.annotation.ExceptionHandler({ BadRequestException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleAuthorizationException(BadRequestException ex) {
		LOG_ERROR.info(create(Strings.EMPTY).add(HTTP_STATUS, HttpStatus.BAD_REQUEST)
				.add(ERROR_CODE, ex.getErrorCode())
				.add(ERROR_MESSAGE, ex.getMessage())
				.build());
		return new ErrorResponse(ex.getMessage(), ex.getErrorCode());
	}
	
	@RequestMapping(produces = "application/json")
	@org.springframework.web.bind.annotation.ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleAuthorizationException(MethodArgumentNotValidException ex) {
		int errorCode = 400001;
		LOG_ERROR.info(create(Strings.EMPTY).add(HTTP_STATUS, HttpStatus.BAD_REQUEST)
				.add(ERROR_CODE, errorCode)
				.add(ERROR_MESSAGE, ex.getMessage())
				.build());
		return new ErrorResponse(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), errorCode);
	}
	
	@RequestMapping(produces = "application/json")
	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleAuthorizationException(NotFoundException ex) {
		LOG_ERROR.info(create(Strings.EMPTY).add(HTTP_STATUS, HttpStatus.NOT_FOUND)
				.add(ERROR_CODE, ex.getErrorCode())
				.add(ERROR_MESSAGE, ex.getMessage())
				.build());
		return new ErrorResponse(ex.getMessage(), ex.getErrorCode());
	}


}