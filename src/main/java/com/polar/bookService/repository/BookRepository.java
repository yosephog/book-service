package com.polar.bookService.repository;

import com.polar.bookService.data.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findBookBySerialNumber(String serialNumber);

    @Modifying
    @Transactional
    @Query("delete from Book where serial_number = :serialNumber")
    void deleteBookBySerialNumber(String serialNumber);
}
