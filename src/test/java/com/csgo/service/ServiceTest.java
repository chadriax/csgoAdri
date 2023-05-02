package com.csgo.service;

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
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    ServiceTest serviceTest;

    private List<InventoryEntity> actualInventories;

    @BeforeEach
    public void createEntities(){
        actualInventories = Arrays.asList(new InventoryEntity(1,2,"invented", "FN", true, 154.7, false));
    }

    @Test
    public void getAllInventoriesTest(){
        Mockito.when(inventoryRepository.findAll()).thenReturn(actualInventories);
        assertThat(actualInventories).isEqualTo(inventoryRepository.findAll());
    }

    @Test
    public void findByPlayerId(){
        Mockito.when(inventoryRepository.findByPlayerId(anyInt())).thenReturn(actualInventories);
        assertThat(actualInventories).isEqualTo(inventoryRepository.findByPlayerId(4));
    }

}
