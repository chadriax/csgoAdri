package com.csgo.service;

import com.csgo.Connection.GetExternalInformation;
import com.csgo.entity.InventoryEntity;
import com.csgo.entity.PlayerEntity;
import com.csgo.exceptions.NotEnoughBalanceException;
import com.csgo.exceptions.NotInSaleException;
import com.csgo.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class InventoryService {

    InventoryRepository inventoryRepository;

    private GetExternalInformation getExternalInformation;

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

    public Mono<ResponseEntity<Map<String, Double>>> sellSkinAndUpdateOnSaleStatus(int playerId, int gunId) {
        Optional<InventoryEntity> optionalSkinToBuy = inventoryRepository.findById(gunId);
        if (optionalSkinToBuy.isEmpty()) {
            throw new EntityNotFoundException("There is no skin with that Id");
        }
        InventoryEntity skinToBuy = optionalSkinToBuy.get();

        Map<String, Double> response = new HashMap<>();

        return canAffordIt(skinToBuy.getGunPrice(), playerId)
                .flatMap(validateCanBuy -> {
                    if (Boolean.TRUE.equals(validateCanBuy) && isOnSale(skinToBuy)){
                        skinToBuy.setOnSale(false);
                        skinToBuy.setPlayerId(playerId);
                        inventoryRepository.save(skinToBuy);
                        response.put("gunPrice", skinToBuy.getGunPrice());
                    }else {
                        throw new NotEnoughBalanceException("There is not enough balance to complete the operation");
                    }
                    return Mono.just(ResponseEntity.ok(response));
                });
    }

    private boolean isOnSale(InventoryEntity skinToBuy) {
        if (!skinToBuy.isOnSale()) {
            throw new NotInSaleException("The gun selected is not on sale");
        }
        return true;
    }

    @Transactional
    public Mono<Boolean> canAffordIt(double gunPrice, int playerId) {
        return getExternalInformation.GetInfoMethod("http://localhost:8080/players/" + playerId,
                        new ParameterizedTypeReference<PlayerEntity>() {})
                .map(result -> result.getMoney() >= gunPrice);
    }
}
