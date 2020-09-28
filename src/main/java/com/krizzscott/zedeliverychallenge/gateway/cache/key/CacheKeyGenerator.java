package com.krizzscott.zedeliverychallenge.gateway.cache.key;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @version 1.0.0
 * @author Christopher Rozario (ˇ෴ˇ) CREATE, TEST, COMPILE AND RUN.
 * @date 2020-02-15
 */
public class CacheKeyGenerator {
	
	static String mojo(String markup, String replaceWith, String patternKey) {
		Pattern p = Pattern.compile(markup);
		Matcher matcher = p.matcher(patternKey);
		return matcher.replaceAll(replaceWith);
	}
}
