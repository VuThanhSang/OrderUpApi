package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.CategoryEntity;
import com.example.orderUp_api.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByName(String name);
    List<CategoryEntity> findByStatus(CategoryStatus status);
}
