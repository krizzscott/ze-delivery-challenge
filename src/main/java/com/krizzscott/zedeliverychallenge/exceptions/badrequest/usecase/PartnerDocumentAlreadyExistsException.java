package com.krizzscott.zedeliverychallenge.exceptions.badrequest.usecase;

import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.usecase.ErrorUseCaseDictionary.DOCUMENT_ALREADY_EXISTS_IN_THIS_COLLECTION;

import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;

public class PartnerDocumentAlreadyExistsException extends BadRequestException {

	private static final long serialVersionUID = 1L;

	public PartnerDocumentAlreadyExistsException() {
		super(DOCUMENT_ALREADY_EXISTS_IN_THIS_COLLECTION.getMessage(),
				DOCUMENT_ALREADY_EXISTS_IN_THIS_COLLECTION.getErrorCode());
	}

}
