package com.polar.bookService.repository;

import com.polar.bookService.config.DataConfig;
import com.polar.bookService.data.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findBookSerialNumberWhenNotExisting() {
        var bookSerialNumber = "123";
        var book = Book.of(bookSerialNumber,"Title", "Author","publisher",200.00);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findBookBySerialNumber(bookSerialNumber);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().serialNumber()).isEqualTo(book.serialNumber());
    }
}
