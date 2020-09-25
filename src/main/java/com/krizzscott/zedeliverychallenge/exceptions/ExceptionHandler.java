package com.krizzscott.zedeliverychallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.NotFoundException;

@RestControllerAdvice
public class ExceptionHandler {

	@RequestMapping(produces = "application/json")
	@org.springframework.web.bind.annotation.ExceptionHandler({ BadRequestException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleAuthorizationException(BadRequestException ex) {
		return new ErrorResponse(ex.getMessage(), ex.getErrorCode());
	}
	
	@RequestMapping(produces = "application/json")
	@org.springframework.web.bind.annotation.ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleAuthorizationException(MethodArgumentNotValidException ex) {
		return new ErrorResponse(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), 400001);
	}
	
	@RequestMapping(produces = "application/json")
	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleAuthorizationException(NotFoundException ex) {
		return new ErrorResponse(ex.getMessage(), ex.getErrorCode());
	}


}