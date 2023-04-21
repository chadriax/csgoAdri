package com.csgo.service;

import com.csgo.entity.InventoryEntity;
import com.csgo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<InventoryEntity> getAllInventories(){
        return inventoryRepository.findAll();
    }

    public List<InventoryEntity> findByPlayerId(int id){
        return inventoryRepository.findByPlayerId(id);
    }

}
