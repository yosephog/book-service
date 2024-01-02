package com.polar.bookService.controller;

import com.polar.bookService.controllers.BookController;
import com.polar.bookService.exception.BookNotFoundException;
import com.polar.bookService.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGettingBookNotExsistingThenReturn404() throws Exception {
        String serialNumber = "234";
        given(bookService.findBookBySerialNumber(serialNumber))
                .willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/book/serialNumber").param("serialNumber", serialNumber))
                .andExpect(status().isNotFound());
    }
}
