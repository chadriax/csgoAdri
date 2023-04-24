package com.csgo.service;

import com.csgo.entity.InventoryEntity;
import com.csgo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public Optional<InventoryEntity> findByGunId(int id){
        return inventoryRepository.findById(id);
    }

    public List<InventoryEntity> getAllSkinsOnSale(){
        return inventoryRepository.findByIsOnSaleIsTrue();
    }

    public ResponseEntity<Void> sellSkinAndUpdateOnSaleStatus(int playerId, int gunId){
        Optional<InventoryEntity> optionalSkinToBuy = Optional.ofNullable(inventoryRepository.findById(gunId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no gun with this id: " + gunId)
        ));
        optionalSkinToBuy.get().setOnSale(false);
        optionalSkinToBuy.get().setPlayerId(playerId);
        inventoryRepository.save(optionalSkinToBuy.get());

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
