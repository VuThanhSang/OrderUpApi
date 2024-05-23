package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.CoinHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinHistoryRepository extends JpaRepository<CoinHistoryEntity, String> {
}
