package com.example.orderUp_api.entity.sql.database.order;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "item_topping")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemToppingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;
    // =================================================================

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "item_detail_id", nullable = false)
    private ItemDetailEntity itemDetail;
}
