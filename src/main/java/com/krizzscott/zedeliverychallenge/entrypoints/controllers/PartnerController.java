package com.krizzscott.zedeliverychallenge.entrypoints.controllers;

import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.ACCESS.HTTP_METHOD;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.ACCESS.ENDPOINT.GET_PARTNERS_BY_ID;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.ACCESS.ENDPOINT.GET_PARTNER_BY_GEOLOCATION;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.ACCESS.ENDPOINT.POST_PARTNERS;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.PARAMETERS.LATITUDE;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.PARAMETERS.LONGITUDE;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.PARAMETERS.PARTNER_ID;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.PARAMETERS.REQUEST_BODY;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogEvent.create;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.krizzscott.zedeliverychallenge.configurations.log.LogEvent;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.converters.PartnerDTOConverter;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.request.PartnerRequestDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.response.PartnerDTO;
import com.krizzscott.zedeliverychallenge.usecases.CreatePartnerUseCase;
import com.krizzscott.zedeliverychallenge.usecases.FindByNearGeoLocationUseCase;
import com.krizzscott.zedeliverychallenge.usecases.FindPartnerByIdUseCase;

@RestController
@RequestMapping(path = "/v1/partners")
public class PartnerController {

	private static final Logger LOG_ACCESS = LogEvent.logger("ACCESS");

	@Autowired
	private CreatePartnerUseCase createPartnerUseCase;
	@Autowired
	private FindPartnerByIdUseCase findPartnerByIdUseCase;
	@Autowired
	private FindByNearGeoLocationUseCase findByNearGeoLocation;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public PartnerDTO createPartner(@Valid @RequestBody PartnerRequestDTO request) {
		LOG_ACCESS.info(create(POST_PARTNERS).add(HTTP_METHOD, POST).add(REQUEST_BODY, request.toJSON()).build());

		return PartnerDTOConverter.toDTO(createPartnerUseCase.execute(PartnerDTOConverter.toDomain(request)));
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/{partnerId}")
	public PartnerDTO findByID(@PathVariable(value = "partnerId") final String partnerId) {

		LOG_ACCESS.info(create(GET_PARTNERS_BY_ID).add(HTTP_METHOD, GET).add(PARTNER_ID, partnerId).build());

		return PartnerDTOConverter.toDTO(findPartnerByIdUseCase.execute(partnerId));
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/nearest-location")
	public PartnerDTO findByNearGeoLocation(
			@RequestParam(value = "long", required = true) final Double longitude,
			@RequestParam(value = "lat", required = true) final Double latitude) {

		LOG_ACCESS.info(
				create(GET_PARTNER_BY_GEOLOCATION).add(HTTP_METHOD, GET).add(LONGITUDE, longitude).add(LATITUDE, latitude).build());

		return PartnerDTOConverter.toDTO(findByNearGeoLocation.execute(longitude, latitude));
	}

}
