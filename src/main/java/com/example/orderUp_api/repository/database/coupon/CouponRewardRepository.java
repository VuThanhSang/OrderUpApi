package com.example.orderUp_api.repository.database.coupon;

import com.example.orderUp_api.entity.sql.database.coupon.CouponRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRewardRepository extends JpaRepository<CouponRewardEntity, String> {
}
