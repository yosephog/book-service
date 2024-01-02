package com.polar.bookService.demo;

import com.polar.bookService.data.Inventory;
import com.polar.bookService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testData")
@RequiredArgsConstructor
public class InventoryLoader {
    private final InventoryRepository inventoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadInventory(){
        inventoryRepository.deleteAll();
        inventoryRepository.saveAll(
                List.of(Inventory.of("123",10),
                        Inventory.of("321", 20))
        );
    }
}
