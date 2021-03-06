package com.krizzscott.zedeliverychallenge.configurations.log;

/**
 *
 * @since 1.0.0
 * @author Christopher Rozario (ˇ෴ˇ) CREATE, TEST, COMPILE AND RUN.
 * @date 2018-jan-08
 */
public class LogConstants {

	public static final String CLASS_NAME = "className";
	public static final String CLASS_METHOD = "classMethod";
	public static final String REDIS_UP = "REDIS UP";
	public static final String REDIS_DOWN = "REDIS DOWN";
	public static final String CACHE_SERIALIZATION = "Cache Serialization";
	
	public class ERROR_MESSAGES {
		public static final String ERROR_ON_SERIALIZE_JSON = "Erro ao serializar o JSON";
		public static final String ERROR_ON_DESERIALIZE_JSON = "Erro ao deserializar o JSON";

		private ERROR_MESSAGES() {
		}
	}
	
	public class ACTION_MESSAGES {
		public static final String VALIDATING_REQUEST_BODY = "Validando request_body da requisicao";

		
		private ACTION_MESSAGES() {
		}
	}

	public class ACCESS {

		public static final String HTTP_METHOD = "method";
		public static final String HTTP_STATUS = "httpStatus";
		public static final String ERROR_CODE = "erroCode";
		public static final String ERROR_MESSAGE = "errorMessage";
		
		private ACCESS() {
		}
		
		public class ENDPOINT {

			public static final String POST_PARTNERS = "/v1/partners";
			public static final String GET_PARTNERS_BY_ID = "/v1/partners/{partnerId}";
			public static final String GET_PARTNER_BY_GEOLOCATION = "/v1/partners/nearest-location";
			
			private ENDPOINT() {
			}
		}
	}

	/**
	 *
	 */
	public class PARAMETERS {

		public static final String REQUEST_BODY = "requestBody";
		public static final String PARTNER_ID = "partnerId";
		public static final String DOCUMENT = "document";
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
		
		private PARAMETERS() {
		}
	}


}
