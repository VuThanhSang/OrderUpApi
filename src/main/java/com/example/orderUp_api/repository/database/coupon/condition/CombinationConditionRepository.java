package com.example.orderUp_api.repository.database.coupon.condition;

import com.example.orderUp_api.entity.sql.database.coupon.condition.CombinationConditionEntity;
import com.example.orderUp_api.enums.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombinationConditionRepository extends JpaRepository<CombinationConditionEntity, String> {


    @Query(value = """
            select cc.`type`\s
            from combination_condition cc\s
            join coupon_condition cc2 on cc.coupon_condition_id = cc2.id\s
            join coupon c on cc2.coupon_id = c.id\s
            where c.code = ?1 and c.status = 'RELEASED'
            and c.is_deleted = false
            """, nativeQuery = true)
    List<CouponType> getCombinationConditionListByCouponCode(String couponCode);

    @Query(value = """
            select cc.`type`\s
            from combination_condition cc\s
            join coupon_condition cc2 on cc.coupon_condition_id = cc2.id\s
            join coupon c on cc2.coupon_id = c.id\s
            where (c.code = ?1 or c.code = ?2 or c.code = ?3) and c.status = 'RELEASED'
            """, nativeQuery = true)
    List<CouponType> getCombinationConditionByMoreCouponCode(String orderCouponCode, String shippingCouponCode, String productCouponCode);
}
