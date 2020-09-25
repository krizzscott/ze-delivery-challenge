package com.krizzscott.zedeliverychallenge.usecases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.domains.converters.PartnerConverter;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.PartnerByGeoLocationNotFoundException;
import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerCriteriaRepository;

@Component
public class FindByNearGeoLocationUseCase {

	@Autowired
	private PartnerCriteriaRepository partnerCriteriaRepository;

	public Partner execute(final double longitude, final double latitude) {

		Optional<PartnerEntity> partnerNearestOpt = partnerCriteriaRepository
				.searchPartnerNearestAndInCoverageAreaByGeoPoint(longitude, latitude);
		
		if(!partnerNearestOpt.isPresent()) {
			throw new PartnerByGeoLocationNotFoundException();
		}

		return PartnerConverter.toDomain(partnerNearestOpt.get());
	}

}
