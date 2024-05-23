package com.example.orderUp_api.entity.sql.database.coupon.condition;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.orderUp_api.entity.sql.database.coupon.CouponConditionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "min_purchase_order_condition")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MinPurchaseConditionEntity {
    @Id
    @GenericGenerator(name = "min_purchase_order_condition_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "min_purchase_order_condition_id")
    private String id;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "type", nullable = false)
//    private MiniPurchaseType type;

    @Column(name = "value")
    private Long value;

    @OneToOne
    @JoinColumn(name = "coupon_condition_id", nullable = false)
    @JsonBackReference
    private CouponConditionEntity couponCondition;
}
