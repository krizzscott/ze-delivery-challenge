package com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain;

import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;

public class DomainValidationException extends BadRequestException {

	private static final long serialVersionUID = 1L;


	public DomainValidationException(ErrorDomainValidationDictionary exceptionDictionary) {
		super(exceptionDictionary.getMessage(), exceptionDictionary.getErrorCode());
	}

}
