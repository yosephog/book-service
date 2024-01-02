package com.polar.bookService.demo;

import com.polar.bookService.data.Book;
import com.polar.bookService.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testData")
@RequiredArgsConstructor
public class BookLoader {
    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadTestBook(){
        bookRepository.deleteAll();
        bookRepository.saveAll(List.of(
                Book.of("123","The lord of the ring", "Tolkin", "publisher",340.00),
                Book.of("321", "The outsider","Stephan kings", "publisher",560.02)
        ));
    }
}
