package com.krizzscott.zedeliverychallenge.gateway.cache.key;

import lombok.Getter;

enum KeyMarkup {
	SYSTEM_ENVIRONMENT("<system_environment>"), 
	PARTNER_ID("<partner_id>");

	@Getter
	private String key;

	private KeyMarkup(String key) {
		this.key = key;
	}

}
