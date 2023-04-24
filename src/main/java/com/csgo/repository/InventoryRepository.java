package com.csgo.repository;

import com.csgo.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    List<InventoryEntity> findByPlayerId(int id);
    List<InventoryEntity> findByIsOnSaleIsTrue();

}
