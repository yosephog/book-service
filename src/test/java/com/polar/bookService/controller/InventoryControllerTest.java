package com.polar.bookService.controller;

import com.polar.bookService.controllers.InventoryController;
import com.polar.bookService.exception.InventoryNotFoundException;
import com.polar.bookService.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    void whenInventoryWithSerialNumberIsSearchedReturnNotFound() throws Exception {
        var serialNumber = "123";
        given(inventoryService.getInventoryBySerialNumber(serialNumber))
                .willThrow(new InventoryNotFoundException((serialNumber)));
        mockMvc.perform(get("/inventory/serialNumber").param("serialNumber", serialNumber))
                .andExpect(status().isNotFound());
    }
}
