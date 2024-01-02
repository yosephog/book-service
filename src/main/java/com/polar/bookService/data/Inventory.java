package com.polar.bookService.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Inventory(
        @Id
        Long id,

        @NotBlank(message = "Serial number must be defined")
        @Pattern(regexp = "^([0-9]{3}|[0-9]{4})$",
        message = "The serial number must have correct format")
        String serialNumber,

        @PositiveOrZero(message = "count in stock must be greater or equal to zero")
        int countInStock,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModified,

        @Version
        int version
) {
    public static Inventory of(String serialNumber, int countInStock){
        return new Inventory(null, serialNumber, countInStock, null, null, 0);
    }
}
