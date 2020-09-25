package com.krizzscott.zedeliverychallenge.usecases;

import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARAM_DOCUMENT_CANNOT_BE_NULL;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerRepository;

@Component
public class ExistsPartnerWithTheSameDocumentNumberUseCase {
	
	@Autowired
	private PartnerRepository partnerRepository;

	public boolean execute(final String document) {
		if(StringUtils.isBlank(document)) {throw new DomainValidationException(PARAM_DOCUMENT_CANNOT_BE_NULL);}
		return partnerRepository.existsByDocument(document);
	}

}
