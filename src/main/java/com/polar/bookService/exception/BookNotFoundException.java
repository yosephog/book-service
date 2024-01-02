package com.polar.bookService.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String value) {
        super("The Book with the value " + value + " is not found.");
    }
}
