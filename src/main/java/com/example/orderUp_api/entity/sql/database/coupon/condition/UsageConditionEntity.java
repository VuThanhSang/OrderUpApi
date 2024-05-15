package com.example.orderUp_api.entity.sql.database.coupon.condition;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.orderUp_api.entity.sql.database.coupon.CouponConditionEntity;
import com.example.orderUp_api.enums.UsageConditionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "usage_condition")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsageConditionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UsageConditionType type;

//    @Column(name = "value")
//    private Integer remainAmount;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "coupon_condition_id", nullable = false)
    @JsonBackReference
    private CouponConditionEntity couponCondition;
}
