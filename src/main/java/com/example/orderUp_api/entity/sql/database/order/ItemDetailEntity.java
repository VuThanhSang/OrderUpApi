package com.example.orderUp_api.entity.sql.database.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.enums.ProductSize;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Table(name = "item_detail")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private ProductSize size;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "product_discount")
    private Long productDiscount;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "order_item_id", nullable = false)
    @JsonBackReference
    private OrderItemEntity orderItem;

    // =================================================================
    @OneToMany(mappedBy = "itemDetail", cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<ItemToppingEntity> itemToppingList;
}
