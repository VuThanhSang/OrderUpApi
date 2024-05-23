package com.example.orderUp_api.entity.sql.database.coupon;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.entity.sql.database.coupon.condition.CombinationConditionEntity;
import com.example.orderUp_api.entity.sql.database.coupon.condition.MinPurchaseConditionEntity;
import com.example.orderUp_api.entity.sql.database.coupon.condition.SubjectConditionEntity;
import com.example.orderUp_api.entity.sql.database.coupon.condition.UsageConditionEntity;
import com.example.orderUp_api.enums.ConditionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "coupon_condition")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponConditionEntity {
    @Id
    @GenericGenerator(name = "coupon_condition_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "coupon_condition_id")
    private String id;

//    @Column(name = "description", nullable = false)
//    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ConditionType type;

    @ManyToOne()
    @JoinColumn(name = "coupon_id", nullable = false)
    @JsonBackReference
    private CouponEntity coupon;

    // =================================================================

//    @OneToOne(mappedBy = "couponCondition", cascade = {CascadeType.PERSIST})
//    @JsonManagedReference
//    private EligibilityCustomerConditionEntity applicableCustomerCondition;

    @OneToMany(mappedBy = "couponCondition", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<CombinationConditionEntity> combinationConditionList;

    @OneToOne(mappedBy = "couponCondition", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private MinPurchaseConditionEntity minPurchaseCondition;

    @OneToMany(mappedBy = "couponCondition", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<SubjectConditionEntity> subjectConditionList;

    @OneToMany(mappedBy = "couponCondition", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<UsageConditionEntity> usageConditionList;

}
