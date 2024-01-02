package com.polar.bookService.repository;

import com.polar.bookService.config.DataConfig;
import com.polar.bookService.data.Inventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.relational.core.sql.In;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class InventoryRepositoryTest {
    @Autowired
    private JdbcAggregateTemplate template;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    void whenRegisterdInventoryIsFoundThenSuccess(){
        var inventory = Inventory.of("123",3);
        template.insert(inventory);
        Optional<Inventory> inventory1 = inventoryRepository.findInventoryBySerialNumber(inventory.serialNumber());
        assertThat(inventory1).isPresent();
        assertThat(inventory1.get().serialNumber())
                .isEqualTo(inventory.serialNumber());
    }
}
