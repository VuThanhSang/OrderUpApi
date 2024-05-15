package com.example.orderUp_api.entity.sql.database.coupon_used;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.entity.sql.database.coupon.CouponEntity;
import com.example.orderUp_api.entity.sql.database.order.OrderBillEntity;
import com.example.orderUp_api.enums.CouponType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "coupon_used")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponUsedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "type", nullable = false)
    private CouponType type;

    @ManyToOne
    @JoinColumn(name = "order_bill_id", nullable = false)
    @JsonBackReference
    private OrderBillEntity orderBill;

    @ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    @JsonBackReference
    private CouponEntity coupon;

    // =================================================
    @OneToOne(mappedBy = "couponUsed", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private CouponRewardReceivedEntity couponRewardReceived;

}
