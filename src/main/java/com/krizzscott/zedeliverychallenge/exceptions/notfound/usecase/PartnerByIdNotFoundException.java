package com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase;


import static com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.ErrorNotFoundDictionary.PARTNER_NOT_FOUND_BY_ID;

import com.krizzscott.zedeliverychallenge.exceptions.notfound.NotFoundException;

public class PartnerByIdNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;

	public PartnerByIdNotFoundException() {
		super(PARTNER_NOT_FOUND_BY_ID.getMessage(), PARTNER_NOT_FOUND_BY_ID.getErrorCode());
	}


}
