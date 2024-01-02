package com.polar.bookService.controllers;

import com.polar.bookService.data.Book;
import com.polar.bookService.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping("/serialNumber")
    public Book getBookBySerialNumber(@RequestParam("serialNumber") String serialNumber){
        return bookService.findBookBySerialNumber(serialNumber);
    }

    @GetMapping("/id")
    public Book getBookById(@RequestParam("id") Long id)  {
        return bookService.findBookById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book registerBook(@Valid @RequestBody Book book){
        return bookService.registerNewBook(book);
    }

    @PutMapping
    public Book editBookDetail(@Valid @RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookBySerialNumber(@RequestParam("serialNumber") String serialNumber){
        bookService.deleteBook(serialNumber);
    }
}
