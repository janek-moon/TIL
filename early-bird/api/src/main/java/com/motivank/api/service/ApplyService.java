package com.motivank.api.service;

import com.motivank.api.domain.Coupon;
import com.motivank.api.repository.CouponCountRepository;
import com.motivank.api.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    public ApplyService(
            CouponRepository couponRepository,
            CouponCountRepository couponCountRepository
    ) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void apply(Long userId) {
//        var count = couponRepository.count();
        var count = couponCountRepository.increment();

        if (count > 100) return;

        couponRepository.save(new Coupon(userId));
    }

}
