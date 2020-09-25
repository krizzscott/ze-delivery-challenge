package com.krizzscott.zedeliverychallenge.usecases;

import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARTNER_OBJECT_CANNOT_BE_NULL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.domains.converters.PartnerConverter;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.usecase.PartnerDocumentAlreadyExistsException;
import com.krizzscott.zedeliverychallenge.gateway.database.converters.PartnerEntityConverter;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerRepository;

@Component
public class CreatePartnerUseCase {
	
	@Autowired
	private ExistsPartnerWithTheSameDocumentNumberUseCase existsPartnerWithTheSameDocumentNumberUseCase;
	@Autowired
	private PartnerRepository partnerRepository;

	public Partner execute(final Partner partner) {
		
		validateDomain(partner);
		
		if(existsPartnerWithTheSameDocumentNumberUseCase.execute(partner.getDocument())) {
			throw new PartnerDocumentAlreadyExistsException();
		}       
		
		return PartnerConverter.toDomain(partnerRepository.save(PartnerEntityConverter.toEntity(partner)));
	}
	
	private void validateDomain(final Partner partner) {
		if(ObjectUtils.isEmpty(partner)) { 
			throw new DomainValidationException(PARTNER_OBJECT_CANNOT_BE_NULL);
		}
		partner.didMount();
	}
	
}
