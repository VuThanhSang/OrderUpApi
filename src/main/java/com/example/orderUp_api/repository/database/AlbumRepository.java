package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.AlbumEntity;
import com.example.orderUp_api.enums.AlbumType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, String> {
    Page<AlbumEntity> findByCloudinaryImageIdIsNotNullAndType(AlbumType type, Pageable pageable);
    Optional<AlbumEntity> findByCloudinaryImageIdIsNotNullAndImageUrl(String imageUrl);
    Page<AlbumEntity> findByCloudinaryImageIdIsNotNull(Pageable pageable);
}
