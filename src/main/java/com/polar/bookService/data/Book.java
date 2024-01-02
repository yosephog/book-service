package com.polar.bookService.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

public record Book (
        @Id
         Long id,
         @NotBlank(message = "The serial number should be defined.")
         @Pattern(regexp = "^([0-9]{3}|[0-9]{4})$",
                 message = "The serial number must be formatted correctly.")
         String serialNumber,

         @NotBlank(message = "The book title must be defined.")
         String title,

         @NotBlank(message = "The book author must be defined.")
         String author,

         String publisher,

         @NotNull(message = "The book price must be defined")
         @Positive(message = "The book price must be greater than zero.")
         Double price,

         @CreatedDate
         Instant createdDate,

        @LastModifiedDate
        Instant lastModified,

        int version
){
    public static Book of(String serialNumber, String title, String author, String publisher, Double price) {
        return new Book(null, serialNumber, title, author, publisher, price, null, null, 0);
    }
}
