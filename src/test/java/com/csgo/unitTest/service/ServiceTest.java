package com.csgo.unitTest.service;

import com.csgo.entity.InventoryEntity;
import com.csgo.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    ServiceTest serviceTest;

    private List<InventoryEntity> actualInventories;
    private InventoryEntity inventoryModel;
    private Optional<InventoryEntity> optionalInventoryEntity;

    @BeforeEach
    public void createEntities(){
        inventoryModel = new InventoryEntity(1,2,"invented", "FN", true, 154.7, false);
        optionalInventoryEntity = Optional.of(inventoryModel);
        actualInventories = Arrays.asList(inventoryModel);
    }

    @Test
    public void getAllInventories_Test(){
        Mockito.when(inventoryRepository.findAll()).thenReturn(actualInventories);
        assertThat(actualInventories).isEqualTo(inventoryRepository.findAll());
    }

    @Test
    public void findByPlayerId_Test(){
        Mockito.when(inventoryRepository.findByPlayerId(anyInt())).thenReturn(actualInventories);
        assertThat(actualInventories).isEqualTo(inventoryRepository.findByPlayerId(4));
    }

    @Test
    public void fundByGunId_Test(){
        Mockito.when(inventoryRepository.findById(anyInt())).thenReturn(optionalInventoryEntity);
        assertThat(optionalInventoryEntity).isEqualTo(inventoryRepository.findById(3));
    }

    @Test
    public void getAllSkinsOnSaleWithoutSkinsOnSale_Test(){
        Mockito.when(inventoryRepository.findByIsOnSaleIsTrue()).thenReturn(Collections.emptyList());
        assertThat(0).isEqualTo(inventoryRepository.findByIsOnSaleIsTrue().size());
    }

    @Test
    public void getAllSkinsOnSaleWithSkinsOnSale_Test(){
        inventoryModel.setOnSale(true);
        Mockito.when(inventoryRepository.findByIsOnSaleIsTrue()).thenReturn(actualInventories);
        assertThat(1).isEqualTo(inventoryRepository.findByIsOnSaleIsTrue().size());
    }

}
