package com.example.orderUp_api.repository.database.coupon.condition;

import com.example.orderUp_api.entity.sql.database.coupon.condition.UsageConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageConditionRepository extends JpaRepository<UsageConditionEntity, String> {
}
