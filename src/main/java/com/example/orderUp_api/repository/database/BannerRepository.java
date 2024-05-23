package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.BannerEntity;
import com.example.orderUp_api.enums.BannerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BannerRepository extends JpaRepository<BannerEntity, String> {
    List<BannerEntity> findByIsDeletedFalseAndStatus(BannerStatus status);
    Optional<BannerEntity> findByIdAndIsDeletedFalse(String bannerId);
    List<BannerEntity> findByIsDeletedFalse();

}
