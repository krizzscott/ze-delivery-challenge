package com.krizzscott.zedeliverychallenge.configurations.log;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @since 1.0.0
 * @author Christopher Rozario (ˇ෴ˇ) CREATE, TEST, COMPILE AND RUN.
 * @date 2018-jan-08
 */
public final class LogEvent {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogEvent.class);
	public static final Charset UTF_8 = StandardCharsets.UTF_8;
	private final ObjectMapper mapper;

	private final Map<String, Object> params;

	/**
	 *
	 */
	private LogEvent() {
		params = new LinkedHashMap<>();
		mapper = new ObjectMapper();
	}

	/**
	 * @param clazz
	 * @return
	 */
	public static Logger logger(@SuppressWarnings("rawtypes") Class clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	/**
	 * @param appender
	 * @return
	 */
	public static Logger logger(String appender) {
		return LoggerFactory.getLogger(appender);
	}

	/**
	 * @param message
	 * @param objects
	 * @return
	 */
	public static LogEvent create(String message, Object... objects) {
		return create(MessageFormat.format(message, objects));
	}

	/**
	 * @param message
	 * @return
	 */
	public static LogEvent create(String message) {
		LogEvent result = new LogEvent();
		result.params.put("message", new StringBuilder(message));
		return result;
	}

	/**
	 * @param param
	 * @param value
	 * @return
	 */
	public LogEvent add(String param, Object value) {
		this.params.put(param, value);
		return this;
	}

	/**
	 * @param message
	 * @param objects
	 * @return
	 */
	public LogEvent add(String message, Object... objects) {
		return add(MessageFormat.format(message, objects));
	}

	/**
	 * @param e
	 * @return
	 */
	public LogEvent add(Throwable e) {
		params.put("stackTrace", ExceptionUtils.getMessage(e));
		return this;
	}

	/**
	 * @param message
	 * @return
	 */
	public LogEvent add(String message) {
		((StringBuilder) params.get("message")).append(message);
		return this;
	}

	/**
	 * @return
	 */
	public String build() {
		try {
			return new String(mapper.writeValueAsString(params).getBytes(UTF_8));
		} catch (JsonProcessingException e) {
			LOGGER.error("Erro no component de log", e);
		}
		return "Could not write log, sorry :'(";
	}

}