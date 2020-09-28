package com.krizzscott.zedeliverychallenge.usecases;

import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARAM_PARTNERID_CANNOT_BE_NULL;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.domains.converters.PartnerConverter;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.PartnerByIdNotFoundException;
import com.krizzscott.zedeliverychallenge.gateway.cache.component.PartnerCache;
import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerRepository;

@Component
public class FindPartnerByIdUseCase {
	
	@Autowired
	private PartnerRepository partnerRepository;
	@Autowired
	private PartnerCache partnerCache;

	public Partner execute(final String partnerId) {
		if(StringUtils.isBlank(partnerId)) {throw new DomainValidationException(PARAM_PARTNERID_CANNOT_BE_NULL);}
		
		Optional<PartnerEntity> partnerEntityCache = partnerCache.getById(partnerId);
		
		if(partnerEntityCache.isPresent()) {
			return PartnerConverter.toDomain(partnerEntityCache.get());
		}
		
		PartnerEntity partnerEntity = partnerRepository.findById(partnerId).orElseThrow(PartnerByIdNotFoundException::new);
		partnerCache.putItem(partnerId, partnerEntity);

		return PartnerConverter.toDomain(partnerEntity);
	}


}
