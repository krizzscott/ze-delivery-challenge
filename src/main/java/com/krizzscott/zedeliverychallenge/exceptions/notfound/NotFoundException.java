package com.krizzscott.zedeliverychallenge.exceptions.notfound;

/***
 * 
 * @author chrisrozario
 *	Exception somente para erros com retorno 404
 */
public abstract class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1;
	private final int errorCode;
	
	public NotFoundException(final String message, final int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

}
