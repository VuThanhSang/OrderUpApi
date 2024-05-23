package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.RoleEntity;
import com.example.orderUp_api.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByRoleName(Role role);
}
