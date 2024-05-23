package com.example.orderUp_api.repository.database.order;

import com.example.orderUp_api.entity.sql.database.order.ReceiverInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingInformationRepository extends JpaRepository<ReceiverInformationEntity, String> {
}
