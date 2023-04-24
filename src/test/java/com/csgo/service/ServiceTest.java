package com.csgo.service;

import com.csgo.entity.InventoryEntity;
import com.csgo.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    ServiceTest serviceTest;

    @Test
    public void getAllInventoriesTest(){
        List<InventoryEntity> actualInventories = Arrays.asList(new InventoryEntity(1,2,"invented", "FN", true, 154.7));
        Mockito.when(inventoryRepository.findAll()).thenReturn(actualInventories);
        Assertions.assertEquals(0, actualInventories.size());
    }

}
