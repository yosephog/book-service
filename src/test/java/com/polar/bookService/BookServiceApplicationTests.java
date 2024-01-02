package com.polar.bookService;

import com.polar.bookService.data.Book;
import com.polar.bookService.data.Inventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class BookServiceApplicationTests {

   @Autowired
    private WebTestClient webTestClient;

   @Test
    void whenPostRequestThenBookCreated(){
       var expectedBook = Book.of("222", "The echo", "Minette walter","publisher", 789.50);
       webTestClient
               .post()
               .uri("/book")
               .bodyValue(expectedBook)
               .exchange()
               .expectStatus().isCreated()
               .expectBody(Book.class).value(actualBook -> {
                   assertThat(actualBook).isNotNull();
                   assertThat(actualBook.serialNumber())
                           .isEqualTo(expectedBook.serialNumber());
               });
   }
   @Test
    void whenPostRequestThenInventoryIsCreated(){
       var expectedInventory = Inventory.of("456", 30);
       webTestClient
               .post()
               .uri("/inventory")
               .bodyValue(expectedInventory)
               .exchange()
               .expectStatus().isCreated()
               .expectBody(Inventory.class).value(actualInventory -> {
                   assertThat(actualInventory).isNotNull();
                   assertThat(actualInventory.serialNumber()).isEqualTo(expectedInventory.serialNumber());
                       }
               );
   }

}
