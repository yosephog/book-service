package com.polar.bookService.controllers;

import com.polar.bookService.data.Inventory;
import com.polar.bookService.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@Slf4j
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/serialNumber")
    Inventory getInventoryBySerialNumber(@RequestParam("serialNumber") String serialNumber) {
        return inventoryService.getInventoryBySerialNumber(serialNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Inventory registerInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryService.registerInventory(inventory);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Inventory updateInventory(@Valid @RequestBody Inventory inventory){
        return inventoryService.updateInventory(inventory);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteInventory(@RequestParam("serialNumber") String serialNumber){
        inventoryService.deleteInventory(serialNumber);
    }
}
