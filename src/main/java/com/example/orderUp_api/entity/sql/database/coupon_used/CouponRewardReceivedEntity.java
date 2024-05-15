package com.example.orderUp_api.entity.sql.database.coupon_used;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.entity.sql.database.coupon_used.reward.MoneyRewardReceivedEntity;
import com.example.orderUp_api.entity.sql.database.coupon_used.reward.ProductRewardReceivedEntity;
import com.example.orderUp_api.enums.CouponRewardType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "coupon_reward_received")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponRewardReceivedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CouponRewardType type;

    @OneToOne
    @JoinColumn(name = "coupon_used_id")
    @JsonBackReference
    private CouponUsedEntity couponUsed;

    // =================================================================
    @OneToOne(mappedBy = "couponRewardReceived", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private MoneyRewardReceivedEntity moneyRewardReceived;

    @OneToMany(mappedBy = "couponRewardReceived", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<ProductRewardReceivedEntity> productRewardReceivedList;

}
