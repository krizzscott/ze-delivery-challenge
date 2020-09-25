package com.krizzscott.zedeliverychallenge.exceptions.badrequest;

public abstract class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1;
	private final int errorCode;
	
	public BadRequestException(final String message, final int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

}
