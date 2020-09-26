package com.krizzscott.zedeliverychallenge.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.request.PartnerRequestDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.response.PartnerDTO;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PartnerControllerITests {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
	    FixtureFactoryLoader.loadTemplates("com.krizzscott.zedeliverychallenge.fixtures");
	}
	
	/*********************************************
	 * Testes do endpoint de criacao de parceiro *
	 *********************************************/
	void whenPostRequestBodyNotSendThenReturns400() throws Exception {
		//GIVEN

		//WHEN
		ResultActions resultActions = mockMvc.perform(post("/v1/partners")
				.contentType("application/json"));

		//THEN
		resultActions.andExpect(status().isBadRequest());
		
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

	}
	
	@Test
	void whenPostRequestBodyValidThenReturns201() throws Exception {
		
		//GIVEN
		PartnerRequestDTO partnerActual = Fixture.from(PartnerRequestDTO.class).gimme("valid");
		
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

	}
	
	/**************************************************
	 * Testes do endpoint de busca de parceiro por ID *
	 **************************************************/
	@Test
	void whenGetPartnerByIdIsNullThenReturns405() throws Exception {
		// GIVEN
		String partnerId = "";

		// WHEN
		ResultActions resultActions = mockMvc
				.perform(get("/v1/partners/".concat(partnerId)).contentType("application/json"));

		// THEN
		resultActions.andExpect(status().isMethodNotAllowed());

	}
	
	@Test
	void whenGetPartnerByIdNotFoundThenReturns404() throws Exception {
		//GIVEN
		String partnerId = UUID.randomUUID().toString();
		
		//WHEN
		ResultActions resultActions = mockMvc.perform(get("/v1/partners/".concat(partnerId))
				.contentType("application/json"));
		
		//THEN
		resultActions.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.errorCode").isNumber())
		.andExpect(jsonPath("$.errorCode").value(404001))
		.andExpect(jsonPath("$.message").isString())
		.andExpect(jsonPath("$.message").value("Partner not found by ID"));
		
	}

	@Test
	void whenGetPartnerByIdThenReturns200() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerActual = Fixture.from(PartnerRequestDTO.class).gimme("valid-with-document-random");
		ResultActions resultActionsCreate = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerActual)));
		PartnerDTO parterCreated = mapFromJson(resultActionsCreate.andReturn().getResponse().getContentAsString(), PartnerDTO.class);
		
		String partnerId = parterCreated.getId();

		
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
		
	}
	
	@Test
	void whenGetPartnerNearestByGeoPositionNotFoundThenReturns404() throws Exception {
		//GIVEN
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		queryParams.add("long", "-21.65454");
		queryParams.add("lat", "-43.232323232");
		
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
		
	}

	@Test
	void whenGetPartnerNearestByGeoPositionThenReturns200() throws Exception {
		//GIVEN
		PartnerRequestDTO partnerActual = Fixture.from(PartnerRequestDTO.class).gimme("valid-with-real-coordinates");
		ResultActions resultActionsCreate = mockMvc.perform(post("/v1/partners")
				.contentType("application/json")
				.content(mapToJson(partnerActual)));
		resultActionsCreate.andExpect(status().isCreated());
		
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		queryParams.add("long", "-46.86647415161133");
		queryParams.add("lat", "-23.505814784042073");
		
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
		
	}
	

	private String mapToJson(Object request) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(request);
	}
	
	private PartnerDTO mapFromJson(String responseAsString, Class<PartnerDTO> valueType) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(responseAsString, valueType);
	}

}
