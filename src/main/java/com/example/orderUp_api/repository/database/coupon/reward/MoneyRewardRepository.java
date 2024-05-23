package com.example.orderUp_api.repository.database.coupon.reward;

import com.example.orderUp_api.entity.sql.database.coupon.reward.MoneyRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRewardRepository extends JpaRepository<MoneyRewardEntity, String> {
}
