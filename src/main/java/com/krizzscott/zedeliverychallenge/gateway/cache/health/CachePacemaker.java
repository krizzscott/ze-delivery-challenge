package com.krizzscott.zedeliverychallenge.gateway.cache.health;

import static com.krizzscott.zedeliverychallenge.configurations.log.LogEvent.create;
import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

import com.krizzscott.zedeliverychallenge.configurations.log.LogEvent;

@Component
public class CachePacemaker {

	private static final Logger LOG_METRIC = LogEvent.logger("METRIC");
    private final JedisConnectionFactory jedisConnectionFactory;

    private long timeToWaitInSeconds;

    private boolean frozen = false;
    private LocalDateTime waitLimit;

    /**
     * @param jedisConnectionFactory
     * @param cacheHitWait
     */
    @Autowired
    public CachePacemaker(JedisConnectionFactory jedisConnectionFactory, @Value("${spring.data.redis.hit-wait}") Long cacheHitWait) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.timeToWaitInSeconds = cacheHitWait;
    }

    /**
     * @return
     */
    public boolean cacheIsOK() {
        if (isPastTheWaitingLimit()) {
            try {
                RedisConnection con = jedisConnectionFactory.getConnection();
                if (frozen) {
                    Properties p = con.info();
                    LOG_METRIC.info(
                            create("REDIS UP")
                                    .add("connected_clients", p.getProperty("connected_clients"))
                                    .add("used_memory_human", p.getProperty("used_memory_human"))
                                    .add("total_connections_received", p.getProperty("total_connections_received"))
                                    .add("total_commands_processed", p.getProperty("total_commands_processed"))
                                    .add("rejected_connections", p.getProperty("rejected_connections"))
                                    .build());
                }
                this.warmCache();
                con.close();
            } catch (Exception e) {
                LOG_METRIC.error(create("REDIS DOWN").add(e).build());
                this.freezeCache();
            }
        }

        return !frozen;
    }

    /**
     *
     */
    private void warmCache() {
        frozen = false;
        waitLimit = now().plusSeconds(timeToWaitInSeconds);
    }

    /**
     * @return
     */
    private boolean isPastTheWaitingLimit() {
        if (isNull(waitLimit)) {
            return true;
        }

        return now().isAfter(waitLimit);

    }

    /**
     * Trava a verificaçãoo do cache pelo periodo estipulado no construtor
     */
    public void freezeCache() {
        if (frozen && nonNull(waitLimit) && now().isBefore(waitLimit)) {
            return;
        }
        frozen = true;
        waitLimit = now().plusSeconds(timeToWaitInSeconds);
    }

    
}
