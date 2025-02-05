package com.motivank.api.service;

import com.motivank.api.repository.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    ApplyService applyService;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @BeforeEach
    void setUp() {
        couponRepository.deleteAllInBatch();
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    @DisplayName("쿠폰 발급이 정상적으로 이루어져야 한다.")
    void applyOnce() throws InterruptedException {
        // given
        applyService.apply(1L);

        // expect
        Thread.sleep(5000);
        assertThat(couponRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("멀티 쓰레드 환경에서 쿠폰 발급이 정상적으로 이루어져야 한다.")
    void applyMany() throws InterruptedException {
        var threadCount = 1000;
        var executorService = Executors.newFixedThreadPool(32); // 쓰레드 풀 생성
        var latch = new CountDownLatch(threadCount); // 쓰레드가 모두 끝날 때까지 기다리기 위한 CountDownLatch

        for (int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.apply(userId);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        Thread.sleep(10000);

        assertThat(couponRepository.count()).isEqualTo(100);
    }

    @Test
    @DisplayName("쿠폰은 한 명당 한 번만 발급되어야 한다.")
    void applyOnlyOnce() throws InterruptedException {
        var threadCount = 1000;
        var executorService = Executors.newFixedThreadPool(32);
        var latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    applyService.apply(1L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        Thread.sleep(10000);

        assertThat(couponRepository.count()).isEqualTo(1);
    }

}