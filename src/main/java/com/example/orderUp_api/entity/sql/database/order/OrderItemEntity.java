package com.example.orderUp_api.entity.sql.database.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.entity.sql.database.review.ProductReviewEntity;
import com.example.orderUp_api.entity.sql.database.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "order_item")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private ProductEntity product;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "order_bill_id", nullable = false)
    @JsonBackReference
    private OrderBillEntity orderBill;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "product_review_id")
    @JsonBackReference
    private ProductReviewEntity productReview;

    // =================================================
    @OneToMany(mappedBy = "orderItem", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<ItemDetailEntity> itemDetailList;


//    @OneToMany(mappedBy = "orderItem")
//    @JsonManagedReference
//    private List<ProductRewardReceivedEntity> productRewardReceivedList;
//    public void fromOrderItemDto(OrderItemDto orderItemDto) {
//        this.setQuantity(orderItemDto.getQuantity());
//        this.setNote(orderItemDto.getNote());
//        this.setSize(orderItemDto.getSize());
//    }
}
