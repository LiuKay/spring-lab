package com.kay.example.springcache;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
public class AppCacheConfig {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder.cacheDefaults(defaultCacheConfig()
                                                        .entryTtl(Duration.ofMinutes(5))
                                                        .serializeKeysWith(fromSerializer(RedisSerializer.string()))
                                                        .serializeValuesWith(fromSerializer(RedisSerializer.json())));
    }

}
