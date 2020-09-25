package com.krizzscott.zedeliverychallenge.exceptions.badrequest.usecase;

import lombok.Getter;

@Getter
public enum ErrorUseCaseDictionary {
	
	DOCUMENT_ALREADY_EXISTS_IN_THIS_COLLECTION(400100, "Document already exists in this collection"),
	;
	
	private int errorCode;
	private String message;

	private ErrorUseCaseDictionary(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

}
