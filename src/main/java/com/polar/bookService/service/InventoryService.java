package com.polar.bookService.service;

import com.polar.bookService.data.Inventory;
import com.polar.bookService.exception.BookNotFoundException;
import com.polar.bookService.exception.InventoryAlreadyExistException;
import com.polar.bookService.exception.InventoryNotFoundException;
import com.polar.bookService.repository.BookRepository;
import com.polar.bookService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BookRepository bookRepository;

    public Inventory getInventoryBySerialNumber(String serialNumber){
        return inventoryRepository.findInventoryBySerialNumber(serialNumber)
                .orElseThrow(() -> new InventoryNotFoundException(serialNumber));
    }
    public Inventory registerInventory(Inventory inventory) {
        if(inventoryRepository.findInventoryBySerialNumber(inventory.serialNumber()).isPresent()){
            throw new InventoryAlreadyExistException(inventory.serialNumber());
        } else if (bookRepository.findBookBySerialNumber(inventory.serialNumber()).isEmpty()){
            throw new BookNotFoundException(inventory.serialNumber());
        } else {
           return inventoryRepository.save(inventory);
        }
    }

    public Inventory updateInventory(Inventory inventory){
        return inventoryRepository.findInventoryBySerialNumber(inventory.serialNumber())
                .map(actualInventory -> {
                    var updatedInventory = new Inventory(
                            actualInventory.id(),
                            inventory.serialNumber(),
                            inventory.countInStock(),
                            actualInventory.createdDate(),
                            actualInventory.lastModified(),
                            actualInventory.version()
                    );
                    return inventoryRepository.save(updatedInventory);
                })
                .orElseThrow(() -> new InventoryNotFoundException(inventory.serialNumber()));
    }
    public void deleteInventory(String serialNumber) {
        inventoryRepository.deleteBySerialNumber(serialNumber);
    }
}
