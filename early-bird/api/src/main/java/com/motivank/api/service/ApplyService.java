package com.motivank.api.service;

import com.motivank.api.producer.CouponCreateProducer;
import com.motivank.api.repository.AppliedUserRepository;
import com.motivank.api.repository.CouponCountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplyService {

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    private final AppliedUserRepository appliedUserRepository;

    public ApplyService(
            CouponCountRepository couponCountRepository,
            CouponCreateProducer couponCreateProducer,
            AppliedUserRepository appliedUserRepository
    ) {
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
    }

    public void apply(Long userId) {
        Long apply = appliedUserRepository.add(userId);

        if (apply != 1) return; // 이미 쿠폰을 발급 받은 사용자

//        var count = couponRepository.count();
        var count = couponCountRepository.increment();

        if (count > 100) return;

        System.out.println("쿠폰 발급");
//        couponRepository.save(new Coupon(userId));
        couponCreateProducer.create(userId);
    }

}
