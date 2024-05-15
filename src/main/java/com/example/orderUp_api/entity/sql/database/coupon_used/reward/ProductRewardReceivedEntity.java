package com.example.orderUp_api.entity.sql.database.coupon_used.reward;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.orderUp_api.entity.sql.database.coupon_used.CouponRewardReceivedEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "product_reward_received")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRewardReceivedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "quantity", nullable = false)
    private Short quantity;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_size")
    private String productSize;

//    @ManyToOne()
//    @JoinColumn(name = "order_item_id", nullable = false)
//    private OrderItemEntity orderItem;

    @ManyToOne
    @JoinColumn(name = "coupon_reward_received_id")
    @JsonBackReference
    private CouponRewardReceivedEntity couponRewardReceived;
}
