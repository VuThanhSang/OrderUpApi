package com.example.orderUp_api.repository.database.product;

import com.example.orderUp_api.entity.sql.database.product.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, String> {
}
