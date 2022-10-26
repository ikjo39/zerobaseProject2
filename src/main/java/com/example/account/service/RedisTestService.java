package com.example.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTestService {
    private final RedissonClient redissonClient;

    public String getLock() {
//        기본 제공
        RLock lock = redissonClient.getLock("sampleLock");

        try {
            // 최대 1초동안 기다리면서 lock을 획득하는 거고
            // 성공하면 이 lock을 3초간 갖고있다 풀어줌
            // unlock을 안하면 무조건 3초간 갖고있음
            boolean isLock = lock.tryLock(1, 5, TimeUnit.SECONDS);
            if (!isLock) {
                log.error("=========Lock acquisition Failed========");
                return "Lock Failed";
            }
        } catch (Exception e) {
            log.error("Redis Lock failed.");
        }

        return "Lock Success";
    }
}
