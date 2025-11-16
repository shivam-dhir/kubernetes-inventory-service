package com.shivam.inventoryservice.service;

import com.shivam.inventoryservice.model.Inventory;
import com.shivam.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity){
        // JPA repository will itself create this method and its implementation
        // we just have to put this method in the InventoryRepository interface
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

    public void addToInventory(String skuCode, Integer quantity) {

        Inventory inventory = inventoryRepository.findBySkuCode(skuCode);

        if(inventory != null){
            inventory.setQuantity(inventory.getQuantity() + quantity);
        } else {
            inventory = Inventory.builder().
                    skuCode(skuCode)
                    .quantity(quantity)
                    .build();
        }
        inventoryRepository.save(inventory);
    }
}
