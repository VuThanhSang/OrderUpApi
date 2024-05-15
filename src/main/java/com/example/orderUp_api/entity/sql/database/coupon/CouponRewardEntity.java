package com.example.orderUp_api.entity.sql.database.coupon;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.entity.sql.database.coupon.reward.MoneyRewardEntity;
import com.example.orderUp_api.entity.sql.database.coupon.reward.ProductRewardEntity;
import com.example.orderUp_api.enums.CouponRewardType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "coupon_reward")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponRewardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CouponRewardType type;

    @OneToOne
    @JoinColumn(name = "coupon_id")
    @JsonBackReference
    private CouponEntity coupon;

    // =================================================================
    @OneToOne(mappedBy = "couponReward", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private MoneyRewardEntity moneyReward;

    @OneToMany(mappedBy = "couponReward", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<ProductRewardEntity> productRewardList;

}
