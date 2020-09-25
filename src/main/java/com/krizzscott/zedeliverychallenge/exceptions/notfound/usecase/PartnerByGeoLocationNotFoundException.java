package com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase;


import static com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.ErrorNotFoundDictionary.PARTNER_NOT_FOUND_BY_GEOLOCATION;

import com.krizzscott.zedeliverychallenge.exceptions.notfound.NotFoundException;

public class PartnerByGeoLocationNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;

	public PartnerByGeoLocationNotFoundException() {
		super(PARTNER_NOT_FOUND_BY_GEOLOCATION.getMessage(), PARTNER_NOT_FOUND_BY_GEOLOCATION.getErrorCode());
	}


}
