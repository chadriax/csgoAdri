package com.csgo.service;

import com.csgo.entity.InventoryEntity;
import com.csgo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<InventoryEntity> getAllInventories(){
        return inventoryRepository.findAll();
    }

}
