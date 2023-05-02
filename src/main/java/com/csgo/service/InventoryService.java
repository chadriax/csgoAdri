package com.csgo.service;

import com.csgo.entity.InventoryEntity;
import com.csgo.entity.PlayerEntity;
import com.csgo.exceptions.NotEnoughBalanceException;
import com.csgo.exceptions.NotInSaleException;
import com.csgo.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService {

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

    public ResponseEntity<Map<String, Double>> sellSkinAndUpdateOnSaleStatus(int playerId, int gunId){

        Optional<InventoryEntity> optionalSkinToBuy = inventoryRepository.findById(gunId);
        if(optionalSkinToBuy.isEmpty()) {
            throw new EntityNotFoundException("There is no skin with that Id");
        }
        if(Boolean.TRUE.equals(canAffordIt(optionalSkinToBuy.get().getGunPrice(), playerId).block()) && isOnSale(optionalSkinToBuy)){
            optionalSkinToBuy.get().setOnSale(false);
            optionalSkinToBuy.get().setPlayerId(playerId);
            inventoryRepository.save(optionalSkinToBuy.get());
            Map<String, Double> response = new HashMap<>();
            response.put("gunPrice", optionalSkinToBuy.get().getGunPrice());
            return ResponseEntity.ok(response);
        }
        throw new NotEnoughBalanceException("There is not enough balance to complete the operation");

    }

    private boolean isOnSale(Optional<InventoryEntity> optionalSkinToBuy) {
        if(!optionalSkinToBuy.get().isOnSale()){
            throw new NotInSaleException("The gun selected is not on sale");
        }
        return true;
    }

    public Mono<Boolean> canAffordIt(double gunPrice, int playerId) {
        WebClient webClient = WebClient.create();

        Mono<PlayerEntity> response = webClient.get()
                .uri("http://localhost:8080/players/" + playerId)
                .retrieve()
                .bodyToMono(PlayerEntity.class);

        return response.map(result -> result.getMoney() >= gunPrice)
                .onErrorMap(error -> new EntityNotFoundException(error.getMessage()));
    }
}
