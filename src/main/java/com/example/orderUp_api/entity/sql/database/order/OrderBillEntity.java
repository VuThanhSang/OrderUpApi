package com.example.orderUp_api.entity.sql.database.order;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.entity.sql.database.BranchEntity;
import com.example.orderUp_api.entity.sql.database.UserEntity;
import com.example.orderUp_api.entity.sql.database.coupon_used.CouponUsedEntity;
import com.example.orderUp_api.entity.sql.database.identifier.StringPrefixedSequenceGenerator;
import com.example.orderUp_api.entity.sql.database.order.shipping.ShippingTaskEntity;
import com.example.orderUp_api.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

import static com.example.orderUp_api.constant.EntityConstant.SEQUENCE_ID_GENERATOR;

@Entity
@Table(name = "order_bill")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderBillEntity {
    //    @GenericGenerator(name = "order_bill_id", strategy = TIME_ID_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @Column(name = "note")
    private String note;

    @Column(name = "shipping_fee")
    private Long shippingFee;

    @Column(name = "shipping_discount")
    private Long shippingDiscount;

    @Column(name = "total_item_price", nullable = false)
    private Long totalItemPrice;

    @Column(name = "order_discount")
    private Long orderDiscount;

    @Column(name = "coin")
    private Long coin;

    @Column(name = "total_payment", nullable = false)
    private Long totalPayment;


    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @JsonBackReference
    private BranchEntity branch;

//    @Column(name = "receive_time")
//    private Date receiveTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;
    // =================================================

    @OneToMany(mappedBy = "orderBill", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JsonManagedReference
    @OrderBy("createdAt DESC")
    private List<OrderEventEntity> orderEventList;

    @OneToMany(mappedBy = "orderBill", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<OrderItemEntity> orderItemList;

    @OneToMany(mappedBy = "orderBill", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<CouponUsedEntity> couponUsedList;

    @OneToOne(mappedBy = "orderBill", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private ReceiverInformationEntity receiverInformation;

    @OneToOne(mappedBy = "orderBill", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private TransactionEntity transaction;

    @OneToOne(mappedBy = "orderBill", cascade = {CascadeType.MERGE})
    @JsonManagedReference
    private CancellationRequestEntity cancellationRequest;

    @OneToOne(mappedBy = "orderBill", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private OrderRefundRequestEntity orderReturnRequest;

    @OneToOne(mappedBy = "orderBill")
    @JsonManagedReference
    private ShippingTaskEntity shippingTask;
}
