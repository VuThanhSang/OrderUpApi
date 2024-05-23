package com.example.orderUp_api.entity.sql.database.coupon.reward;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.orderUp_api.entity.sql.database.coupon.CouponRewardEntity;
import com.example.orderUp_api.enums.MoneyRewardUnit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "money_reward")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyRewardEntity {
    @Id
    @GenericGenerator(name = "money_reward_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "money_reward_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
    private MoneyRewardUnit unit;

//    @Column(name = "target_reward", nullable = false)
//    private TargetReward targetReward;

    @Column(name = "value", nullable = false)
    private Integer value;

    @OneToOne
    @JoinColumn(name = "coupon_reward_id")
    @JsonBackReference
    private CouponRewardEntity couponReward;
}
