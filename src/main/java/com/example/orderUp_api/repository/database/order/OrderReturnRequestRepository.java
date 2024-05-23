package com.example.orderUp_api.repository.database.order;

import com.example.orderUp_api.entity.sql.database.order.OrderRefundRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderReturnRequestRepository extends JpaRepository<OrderRefundRequestEntity, String> {
    Optional<OrderRefundRequestEntity> findByOrderBill_Id(String orderBillId);
}
