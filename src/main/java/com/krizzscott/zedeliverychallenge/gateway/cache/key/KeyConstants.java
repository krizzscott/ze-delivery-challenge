package com.krizzscott.zedeliverychallenge.gateway.cache.key;

import lombok.Getter;

/**
 * 
 * @version 1.0.0
 * @author Christopher Rozario (ˇ෴ˇ) CREATE, TEST, COMPILE AND RUN.
 * @date 2020-02-15
 */
enum KeyConstants {
	SEPARATOR(":"),
	PARTNER("partner");

	@Getter
	private String constant;

	private KeyConstants(final String constant) {
		this.constant= constant;
	}
}
