package com.example.orderUp_api.entity.sql.database.review;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.entity.sql.database.order.OrderItemEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "product_review")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProductReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "star", nullable = false, columnDefinition = "INT CHECK(star > 0 and star <= 5)")
    private Integer star;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;


//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private Date updatedAt;

    // =================================================
//    @OneToMany(mappedBy = "productReview")
//    @JsonManagedReference
//    private List<UserReviewInteractionEntity> userReviewInteractionList;

    @OneToOne(mappedBy = "productReview")
    @JsonManagedReference
    private OrderItemEntity orderItem;
}
