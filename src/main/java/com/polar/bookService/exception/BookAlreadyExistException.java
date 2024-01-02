package com.polar.bookService.exception;

public class BookAlreadyExistException extends RuntimeException{

    public BookAlreadyExistException(String value) {
        super("The book with the value " + value + " already exist.");
    }
}
