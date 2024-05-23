package com.example.orderUp_api.repository.database.product;

import com.example.orderUp_api.entity.sql.database.product.ToppingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<ToppingEntity, String> {
}
