package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndAuthType(String email, String authType);
}
