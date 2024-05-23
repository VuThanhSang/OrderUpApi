package com.example.orderUp_api.repository.database.coupon.reward;

import com.example.orderUp_api.entity.sql.database.coupon.reward.ProductRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRewardRepository extends JpaRepository<ProductRewardEntity, String> {
}
