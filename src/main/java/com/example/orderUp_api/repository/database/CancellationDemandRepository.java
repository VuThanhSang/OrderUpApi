package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.order.CancellationRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancellationDemandRepository extends JpaRepository<CancellationRequestEntity, String> {
}
