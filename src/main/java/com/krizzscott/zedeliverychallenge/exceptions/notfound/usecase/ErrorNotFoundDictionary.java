package com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase;

/***
 * 
 * @author chrisrozario
 *	Dicionario de erros SOMENTE para retorno 404
 */
public enum ErrorNotFoundDictionary {

	PARTNER_NOT_FOUND_BY_ID(404001, "Partner not found by ID"),
	PARTNER_NOT_FOUND_BY_GEOLOCATION(404002, "Partner not found by Geolocation"),
	;


	private int errorCode;
	private String message;

	private ErrorNotFoundDictionary(int code, String message) {
		this.errorCode = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
