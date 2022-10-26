package com.example.account.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisRepositoryConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

//        config.useSingleServer().setAddress("redis://localhost:6379");
        // 아래를 선호하게 됨 Code 내 임의의 숫자, 문자는 오류가 가능하기 때문
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);

        // 위 설정을 통해 redisson을 생성해서 Bean으로 하나만 등록해줌 -> 이것만 불려가서 씀
        return Redisson.create(config);
    }
}
