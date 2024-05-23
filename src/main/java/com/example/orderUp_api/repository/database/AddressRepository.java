package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.AddressEntity;
import com.example.orderUp_api.entity.sql.database.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    List<AddressEntity> findByUser(UserEntity user);
    List<AddressEntity> findByUserIdOrderByIsDefaultDesc(String userId);
}
