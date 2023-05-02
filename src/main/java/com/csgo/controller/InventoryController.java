package com.csgo.controller;

import com.csgo.entity.InventoryEntity;
import com.csgo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventories")
    public List<InventoryEntity> getAllInventories(){
        return inventoryService.getAllInventories();
    }

    @GetMapping("/inventories/player/{id}")
    public List<InventoryEntity> getAllInventoryByPlayerId(@PathVariable int id){
        return inventoryService.findByPlayerId(id);
    }

    @GetMapping("/marketplace")
    public List<InventoryEntity> getAllSkinsOnSale(){
        return inventoryService.getAllSkinsOnSale();
    }

    @GetMapping("/marketplace/{gunId}")
    public Optional<InventoryEntity> findByGunId(@PathVariable int gunId){
        return inventoryService.findByGunId(gunId);
    }

    @PutMapping("marketplace/{playerId}/{gunId}")
    public ResponseEntity<Map<String, Double>> sellSkinAndUpdateOnSaleStatus(@PathVariable int playerId, int gunId){
        return inventoryService.sellSkinAndUpdateOnSaleStatus(playerId, gunId);
    }

}
