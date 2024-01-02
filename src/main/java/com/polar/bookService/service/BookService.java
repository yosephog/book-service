package com.polar.bookService.service;

import com.polar.bookService.data.Book;
import com.polar.bookService.exception.BookAlreadyExistException;
import com.polar.bookService.exception.BookNotFoundException;
import com.polar.bookService.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book findBookBySerialNumber(String serialNumber){
        return bookRepository.findBookBySerialNumber(serialNumber)
                .orElseThrow(() -> new BookNotFoundException(serialNumber));
    }
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.valueOf(id)));
    }
    public Book registerNewBook(Book book) {
        if(bookRepository.findBookBySerialNumber(book.serialNumber()).isPresent()) {
            throw new BookAlreadyExistException(book.serialNumber());
        }
        return bookRepository.save(book);
    }
    public Book updateBook(Book book) {
        return bookRepository.findBookBySerialNumber(book.serialNumber())
                .map(existingBook -> {
                    var bookToUpdate = new Book(
                            existingBook.id(),
                            existingBook.serialNumber(),
                            book.title(),
                            book.author(),
                            book.publisher(),
                            book.price(),
                            existingBook.createdDate(),
                            existingBook.lastModified(),
                            existingBook.version()
                    );
                    return bookRepository.save(bookToUpdate);
                })
                .orElseThrow(() -> new BookAlreadyExistException(book.serialNumber()));
    }
    public void deleteBook(String serialNumber) {
         bookRepository.deleteBookBySerialNumber(serialNumber);
    }

}
