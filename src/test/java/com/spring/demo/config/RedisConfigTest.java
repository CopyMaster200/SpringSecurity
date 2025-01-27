package com.spring.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
public class RedisConfigTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("suresh", "SriRama@200");
        log.info("REDIS TEST: " + redisTemplate.opsForValue().get("suresh"));
    }

}
