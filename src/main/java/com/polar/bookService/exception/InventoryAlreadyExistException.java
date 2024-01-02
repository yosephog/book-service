package com.polar.bookService.exception;

public class InventoryAlreadyExistException extends RuntimeException{

    public InventoryAlreadyExistException(String value) {
        super("The inventory with the value " + value + " already exist.");
    }
}
