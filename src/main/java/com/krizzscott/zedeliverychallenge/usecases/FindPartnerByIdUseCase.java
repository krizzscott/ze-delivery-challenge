package com.krizzscott.zedeliverychallenge.usecases;

import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARAM_PARTNERID_CANNOT_BE_NULL;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.domains.converters.PartnerConverter;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.PartnerByIdNotFoundException;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerRepository;

@Component
public class FindPartnerByIdUseCase {
	
	@Autowired
	private PartnerRepository partnerRepository;

	public Partner execute(final String partnerId) {
		if(StringUtils.isBlank(partnerId)) {throw new DomainValidationException(PARAM_PARTNERID_CANNOT_BE_NULL);}
		return PartnerConverter.toDomain(partnerRepository.findById(partnerId).orElseThrow(PartnerByIdNotFoundException::new));
	}


}
