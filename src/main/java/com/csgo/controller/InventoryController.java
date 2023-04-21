package com.csgo.controller;

import com.csgo.entity.InventoryEntity;
import com.csgo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventories")
    public List<InventoryEntity> getAllInventories(){
        return inventoryService.getAllInventories();
    }

}
