package com.polar.bookService.repository;

import com.polar.bookService.data.Inventory;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    Optional<Inventory> findInventoryBySerialNumber(String serialNumber);

    @Modifying
    @Transactional
    @Query("delete  from inventory where serial_number = :serialNumber")
    void deleteBySerialNumber(String serialNumber);
}
