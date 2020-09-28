package com.krizzscott.zedeliverychallenge.gateway.cache.component;

import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.CACHE_SERIALIZATION;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.CLASS_METHOD;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.CLASS_NAME;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogConstants.REDIS_DOWN;
import static com.krizzscott.zedeliverychallenge.configurations.log.LogEvent.create;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krizzscott.zedeliverychallenge.configurations.log.LogEvent;
import com.krizzscott.zedeliverychallenge.gateway.cache.health.CachePacemaker;
import com.krizzscott.zedeliverychallenge.gateway.cache.key.PartnerKeyGenerator;
import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;

@Component
public class PartnerCache {

	private static final Logger LOG_METRIC = LogEvent.logger("METRIC");

	private static final String SEPARATOR = ":";
	
	private CachePacemaker cachePacemaker;
	private Cache cache;
	private String applicationEnvironmentPrefix;
	private ObjectMapper objectMapper;

	@Autowired
	public PartnerCache(CacheManager cacheManager, 
			CachePacemaker cachePacemaker,
			@Value("${spring.application.name}") String applicationName, 
			@Value("${spring.profiles}") String profile, 
			@Value("${spring.data.redis.cache-partner.name}") String cacheName, 
			ObjectMapper objectMapper) {
		
		this.cachePacemaker = cachePacemaker;
		this.applicationEnvironmentPrefix = applicationName;
		
		this.applicationEnvironmentPrefix = applicationName.concat(SEPARATOR).concat(profile);
        this.cache = cacheManager.getCache(this.applicationEnvironmentPrefix.concat(SEPARATOR).concat(cacheName));
		this.objectMapper = objectMapper;
		
	}

	public Optional<PartnerEntity> getById(String id) {
		PartnerEntity response = null;
		try {
			if (cachePacemaker.cacheIsOK()) {
				String key = PartnerKeyGenerator.makeMojo(applicationEnvironmentPrefix, id);
				String value = cache.get(key, String.class);

				if (Objects.nonNull(value)) {
					response = objectMapper.readValue(value, PartnerEntity.class);
				}
			}
		} catch (RedisConnectionFailureException e) {
			LOG_METRIC.error(create(REDIS_DOWN).add(CLASS_NAME, PartnerCache.class.getName())
					.add(CLASS_METHOD, "getById").add("id", id).add(e).build());
			cachePacemaker.freezeCache();
		} catch (IOException e) {
			LOG_METRIC.error(create(CACHE_SERIALIZATION).add(CLASS_NAME, PartnerCache.class.getName())
					.add(CLASS_METHOD, "getById").add("id", id).add(e).build());
		}

		return Objects.isNull(response) ? Optional.empty() : Optional.of(response);
	}

	public boolean putItem(String id, PartnerEntity transactionOriginModel) {
		boolean result = false;

		try {
			if (cachePacemaker.cacheIsOK()) {
				String key = PartnerKeyGenerator.makeMojo(applicationEnvironmentPrefix, id);
				String jsonMatch = objectMapper.writeValueAsString(transactionOriginModel);

				if (Objects.nonNull(jsonMatch)) {
					cache.put(key, jsonMatch);
					result = true;
				}
			}
		} catch (JsonProcessingException e) {
			LOG_METRIC.error(create(CACHE_SERIALIZATION).add(CLASS_NAME, PartnerCache.class.getName())
					.add(CLASS_METHOD, "putItem").add("id", id).add(e).build());
		} catch (RedisConnectionFailureException e) {
			LOG_METRIC.error(create(REDIS_DOWN).add(CLASS_NAME, PartnerCache.class.getName())
					.add(CLASS_METHOD, "putItem").add("id", id).add(e).build());
			cachePacemaker.freezeCache();
		}

		return result;
	}

}
