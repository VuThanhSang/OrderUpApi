package com.example.orderUp_api.entity.sql.database.order.shipping;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.orderUp_api.entity.sql.database.EmployeeEntity;
import com.example.orderUp_api.entity.sql.database.order.OrderBillEntity;
import com.example.orderUp_api.enums.ShippingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "shipping_task")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class ShippingTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShippingStatus status;


    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "order_bill_id", nullable = false)
    private OrderBillEntity orderBill;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}
