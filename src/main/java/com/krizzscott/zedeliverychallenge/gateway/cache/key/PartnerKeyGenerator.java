package com.krizzscott.zedeliverychallenge.gateway.cache.key;


import static com.krizzscott.zedeliverychallenge.gateway.cache.key.KeyConstants.SEPARATOR;
import static com.krizzscott.zedeliverychallenge.gateway.cache.key.KeyConstants.PARTNER;
import static com.krizzscott.zedeliverychallenge.gateway.cache.key.KeyMarkup.PARTNER_ID;
import static com.krizzscott.zedeliverychallenge.gateway.cache.key.KeyMarkup.SYSTEM_ENVIRONMENT;

import org.springframework.util.Assert;

public class PartnerKeyGenerator extends CacheKeyGenerator {

	private static final String PATTERN_PARTNER_ID_KEY;

	static {
		PATTERN_PARTNER_ID_KEY = 
				SYSTEM_ENVIRONMENT.getKey()
				+ SEPARATOR.getConstant()
				+ PARTNER.getConstant()
				+ SEPARATOR.getConstant()
				+ PARTNER_ID.getKey() 
				;

	}

	public static String makeMojo(String applicationEnvironmentPrefix, String partnerId) {
		Assert.notNull(applicationEnvironmentPrefix, "applicationEnvironmentPrefix must not be null");
		Assert.hasText(applicationEnvironmentPrefix, "applicationEnvironmentPrefix must not be empty");
		Assert.notNull(partnerId, "partnerId must not be null!");

		String withSystem = mojo(SYSTEM_ENVIRONMENT.getKey(), applicationEnvironmentPrefix, PATTERN_PARTNER_ID_KEY);
		return mojo(PARTNER_ID.getKey(), partnerId, withSystem);
	}
	
}
