package com.krizzscott.zedeliverychallenge.ut.entrypoints.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.PartnerController;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.request.PartnerRequestDTO;
import com.krizzscott.zedeliverychallenge.exceptions.ExceptionHandler;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.PartnerByGeoLocationNotFoundException;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.PartnerByIdNotFoundException;
import com.krizzscott.zedeliverychallenge.usecases.CreatePartnerUseCase;
import com.krizzscott.zedeliverychallenge.usecases.FindByNearGeoLocationUseCase;
import com.krizzscott.zedeliverychallenge.usecases.FindPartnerByIdUseCase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@ExtendWith(SpringExtension.class)
class PartnerControllerUnitTests{

	private MockMvc mockMvc;

	@InjectMocks
	private PartnerController partnerController;
	@Mock
	private CreatePartnerUseCase createPartnerUseCase;
	@Mock
	private FindPartnerByIdUseCase findPartnerByIdUseCase;
	@Mock
	private FindByNearGeoLocationUseCase findByNearGeoLocation;

	@BeforeEach
	void setUp() {
	    FixtureFactoryLoader.loadTemplates("com.krizzscott.zedeliverychallenge.fixtures");
		mockMvc = MockMvcBuilders.standaloneSetup(partnerController)
				.setControllerAdvice(ExceptionHandler.class)
				.setValidator(new LocalValidatorFactoryBean())
				.build();
	}
	
	/*********************************************
	 * Testes do endpoint de criacao de parceiro *
	 *********************************************/

	@Test
	void whenPostRequestBodyNotSendThenReturns400() throws Exception {
		//GIVEN

		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json"));

		//THEN
		resultActions.andExpect(status().isBadRequest());
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}

	@Test
	void whenPostRequestBodyNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO request = null;
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(request)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest());
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
		
	}
	
	@Test
	void whenPostRequestBodyEmptyThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO request = new PartnerRequestDTO();
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(request)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest());
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
		
	}

	@Test
	void whenPostRequestBodyTradingNameNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-tradingName-null");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [tradingName] cannot be null or empty"));	
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyTradingNameEmptyThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-tradingName-empty");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [tradingName] cannot be null or empty"));	
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyOwnerNameNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-ownerName-null");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [ownerName] cannot be null or empty"));		
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyOwnerNameEmptyThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-ownerName-empty");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [ownerName] cannot be null or empty"));	
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	@Test
	void whenPostRequestBodyDocumentNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-document-null");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [document] cannot be null"));	
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyDocumentEmptyThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-document-empty");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString());
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyDocumentInvalidSizeThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-document-invalid_size");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [document] cannot be invalid size, please insert only 14 numbers for CNPJ document"));	
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyDocumentPatternInvalidThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-document-invalid_pattern");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [document] cannot be invalid pattern, please insert only numbers for CNPJ document"));
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyAddressIsNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-address-null");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [address] cannot be null"));		
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyAddressTypeIsNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-address_type-null");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [address.type] cannot be null"));	
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyAddressCoordinatesIsNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-address_coordinates-null");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [address.coordinates] cannot be null"));		
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}

	@Test
	void whenPostRequestBodyAddressCoordinatesinvalidSizeThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-address_coordinates-invalid_size");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [address.coordinates] must contains 2 values, longitude and latitude"));	
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyCoverageAreaTypeNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-coveragearea_type-null");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [coverageArea.type] cannot be null or invalid"));		
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
	}
	
	@Test
	void whenPostRequestBodyCoverageAreaTypeNotEqualsToPointThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-coveragearea_type-diff-to-multipolygon");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [coverageArea.type] cannot be null or invalid"));	
		
		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
		
	}
	
	@Test
	void whenPostRequestBodyCoverageAreaCoordinatesIsNullThenReturns400() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerRequest = Fixture.from(PartnerRequestDTO.class).gimme("with-coveragearea_coordinates-null");
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerRequest)));
		
		//THEN
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(400001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Parameter [coverageArea.coordinates] cannot be null"));	

		verify(createPartnerUseCase, times(0)).execute(any(Partner.class));
		verifyNoInteractions(createPartnerUseCase);
		
	}
	
	@Test
	void whenPostRequestBodyValidThenReturns200() throws Exception {
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-valid-with-id");
		PartnerRequestDTO partnerActual = Fixture.from(PartnerRequestDTO.class).gimme("valid");
		
		when(createPartnerUseCase.execute(any(Partner.class))).thenReturn(partner);

		
		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerActual)));
		
		//THEN
		resultActions.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").isString())
		.andExpect(jsonPath("$.tradingName").isString())
		.andExpect(jsonPath("$.ownerName").isString())
		.andExpect(jsonPath("$.document").isString())
		.andExpect(jsonPath("$.address").isMap())
		.andExpect(jsonPath("$.address.type").isString())
		.andExpect(jsonPath("$.address.type").value("Point"))
		.andExpect(jsonPath("$.address.coordinates").isArray())
		.andExpect(jsonPath("$.address.coordinates[0]").isNumber())
		.andExpect(jsonPath("$.coverageArea").isMap())
		.andExpect(jsonPath("$.coverageArea.type").isString())
		.andExpect(jsonPath("$.coverageArea.type").value("MultiPolygon"))
		.andExpect(jsonPath("$.coverageArea.coordinates").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0][0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0][0][0]").isNumber());	

		verify(createPartnerUseCase, times(1)).execute(any(Partner.class));
		verifyNoMoreInteractions(createPartnerUseCase);
		
		InOrder inOrder = inOrder(createPartnerUseCase);
		inOrder.verify(createPartnerUseCase, times(1)).execute(any(Partner.class));

	}
	
	/**************************************************
	 * Testes do endpoint de busca de parceiro por ID *
	 **************************************************/

	@Test
	void whenGetPartnerByIdIsNullThenReturns405() throws Exception {
		//GIVEN
		String partnerId = "";
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(get("/v1/partners/".concat(partnerId))
				.contentType("application/json"));
		
		//THEN
		resultActions.andExpect(status().isMethodNotAllowed());
		
		verify(findPartnerByIdUseCase, times(0)).execute(anyString());
		verifyNoInteractions(findPartnerByIdUseCase);
	}
	
	@Test
	void whenGetPartnerByIdNotFoundThenReturns404() throws Exception {
		//GIVEN
		String partnerId = UUID.randomUUID().toString();
		
		when(findPartnerByIdUseCase.execute(anyString())).thenThrow(new PartnerByIdNotFoundException());
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(get("/v1/partners/".concat(partnerId))
				.contentType("application/json"));
		
		//THEN
		resultActions.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(404001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Partner not found by ID"));
		
		verify(findPartnerByIdUseCase, times(1)).execute(anyString());
		verifyNoMoreInteractions(findPartnerByIdUseCase);
		
		InOrder inOrder = inOrder(findPartnerByIdUseCase);
		inOrder.verify(findPartnerByIdUseCase, times(1)).execute(anyString());
		
	}

	@Test
	void whenGetPartnerByIdThenReturns200() throws Exception {
		//GIVEN
		String partnerId = UUID.randomUUID().toString();
		Partner partnerExpected = Fixture.from(Partner.class).gimme("create-partner-valid-with-id");

		when(findPartnerByIdUseCase.execute(anyString())).thenReturn(partnerExpected);
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(get("/v1/partners/".concat(partnerId))
				.contentType("application/json"));
		
		//THEN
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").isString())
		.andExpect(jsonPath("$.tradingName").isString())
		.andExpect(jsonPath("$.ownerName").isString())
		.andExpect(jsonPath("$.document").isString())
		.andExpect(jsonPath("$.address").isMap())
		.andExpect(jsonPath("$.address.type").isString())
		.andExpect(jsonPath("$.address.type").value("Point"))
		.andExpect(jsonPath("$.address.coordinates").isArray())
		.andExpect(jsonPath("$.address.coordinates[0]").isNumber())
		.andExpect(jsonPath("$.coverageArea").isMap())
		.andExpect(jsonPath("$.coverageArea.type").isString())
		.andExpect(jsonPath("$.coverageArea.type").value("MultiPolygon"))
		.andExpect(jsonPath("$.coverageArea.coordinates").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0][0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0][0][0]").isNumber());
		
		verify(findPartnerByIdUseCase, times(1)).execute(anyString());
		verifyNoMoreInteractions(findPartnerByIdUseCase);
		
		InOrder inOrder = inOrder(findPartnerByIdUseCase);
		inOrder.verify(findPartnerByIdUseCase, times(1)).execute(anyString());
		
	}
	
	/********************************************************
	 * Testes do endpoint de busca de parceiro mais proximo *
	 ********************************************************/

	@Test
	void whenGetPartnerNearestByGeoPositionIsNullThenReturns400() throws Exception {
		//GIVEN
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(get("/v1/partners/nearest-location?long=lat=")
				.contentType("application/json"));
		
		//THEN
		resultActions.andExpect(status().isBadRequest());
		
		verify(findPartnerByIdUseCase, times(0)).execute(anyString());
		verifyNoInteractions(findPartnerByIdUseCase);
	}
	
	@Test
	void whenGetPartnerNearestByGeoPositionNotFoundThenReturns404() throws Exception {
		//GIVEN
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		queryParams.add("long", "-21.65454");
		queryParams.add("lat", "-43.232323232");
		
		when(findByNearGeoLocation.execute(anyDouble(), anyDouble())).thenThrow(new PartnerByGeoLocationNotFoundException());
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(get("/v1/partners/nearest-location")
				.queryParams(queryParams)
				.contentType("application/json"));
		
		//THEN
		resultActions.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(404002))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Partner not found by Geolocation"));
		
		verify(findByNearGeoLocation, times(1)).execute(anyDouble(), anyDouble());
		verifyNoMoreInteractions(findByNearGeoLocation);
		
		InOrder inOrder = inOrder(findByNearGeoLocation);
		inOrder.verify(findByNearGeoLocation, times(1)).execute(anyDouble(), anyDouble());
		
	}

	@Test
	void whenGetPartnerNearestByGeoPositionThenReturns200() throws Exception {
		//GIVEN
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		queryParams.add("long", "-21.65454");
		queryParams.add("lat", "-43.232323232");
		
		Partner partnerExpected = Fixture.from(Partner.class).gimme("create-partner-valid-with-id");

		when(findByNearGeoLocation.execute(anyDouble(), anyDouble())).thenReturn(partnerExpected);

		//WHEN
		ResultActions resultActions = mockMvc.perform(get("/v1/partners/nearest-location")
				.queryParams(queryParams)
				.contentType("application/json"));
		
		//THEN
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").isString())
		.andExpect(jsonPath("$.tradingName").isString())
		.andExpect(jsonPath("$.ownerName").isString())
		.andExpect(jsonPath("$.document").isString())
		.andExpect(jsonPath("$.address").isMap())
		.andExpect(jsonPath("$.address.type").isString())
		.andExpect(jsonPath("$.address.type").value("Point"))
		.andExpect(jsonPath("$.address.coordinates").isArray())
		.andExpect(jsonPath("$.address.coordinates[0]").isNumber())
		.andExpect(jsonPath("$.coverageArea").isMap())
		.andExpect(jsonPath("$.coverageArea.type").isString())
		.andExpect(jsonPath("$.coverageArea.type").value("MultiPolygon"))
		.andExpect(jsonPath("$.coverageArea.coordinates").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0][0]").isArray())
		.andExpect(jsonPath("$.coverageArea.coordinates[0][0][0][0]").isNumber());
		
		verify(findByNearGeoLocation, times(1)).execute(anyDouble(), anyDouble());
		verifyNoMoreInteractions(findByNearGeoLocation);
		
		InOrder inOrder = inOrder(findByNearGeoLocation);
		inOrder.verify(findByNearGeoLocation, times(1)).execute(anyDouble(), anyDouble());
		
	}

	private String mapToJson(Object request) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(request);
	}

}
