package com.csgo.repository;

import com.csgo.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {
}
