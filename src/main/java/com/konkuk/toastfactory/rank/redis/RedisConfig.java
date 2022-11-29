package com.konkuk.toastfactory.rank.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis의 다섯가지 데이터 유형에 대한 Operation Interface를 제공
 *  메소드명            반환 오퍼레이션    Redis 자료구조
 * opsForValue()    ValueOperations     String
 * opsForList()     ListOperations      List
 * opsForSet()      SetOperations       Set
 * opsForZSet()     ZSetOperations      Sorted Set
 * opsForHash()     HashOperations	    Hash
 * */



@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.host}")
    private String host;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // RedisConnectionFactory 인터페이스 하위 클래스에 LettuceConnectionFactory
        // Lettuce와 Jedis 중 Lettuce의 성능이 더 좋다.
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer()); //key 깨짐 방지
        redisTemplate.setValueSerializer(new StringRedisSerializer()); //value 깨짐 방지
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}
