package com.polar.bookService.controller;

import com.polar.bookService.data.Inventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class InventoryJsonTest {
    @Autowired
    private JacksonTester<Inventory> json;

    @Test
    void testSerialization() throws IOException {
        var inventory = Inventory.of("123", 20);
        var jsonContent = json.write(inventory);
        assertThat(jsonContent).extractingJsonPathStringValue("@.serialNumber")
                .isEqualTo(inventory.serialNumber());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.countInStock")
                .isEqualTo(inventory.countInStock());
    }

    @Test
    void testDesiralization() throws IOException {
        var content = """
                {
                "serialNumber" : "123",
                "countInStock" : 20
                }
                """;
        assertThat(json.parse(content)).usingRecursiveComparison()
                .isEqualTo( Inventory.of("123",20));
    }
}
