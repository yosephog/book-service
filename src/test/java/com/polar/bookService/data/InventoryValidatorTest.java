package com.polar.bookService.data;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.relational.core.sql.In;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryValidatorTest {
    static Validator validator;

    @BeforeAll
    public static void setValidator() {
       ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
       validator = factory.getValidator();
    }
    @Test
    void whenAllValusAreFilledCorrectly(){
        var inventory = Inventory.of("123",0);
        Set<ConstraintViolation<Inventory>> violations = validator.validate(inventory);
        assertThat(violations).isEmpty();
    }
    @Test
    void whenSerialNumberIsNotFormattedCorrectThenReturError(){
        var inventory = Inventory.of("1",0);
        Set<ConstraintViolation<Inventory>> violations = validator.validate(inventory);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The serial number must have correct format");
    }
    @Test
    void whenCountInStorckIsLessThanZeroThenReturnError(){
        var inventory = Inventory.of("1234",-20);
        Set<ConstraintViolation<Inventory>> violations = validator.validate(inventory);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("count in stock must be greater or equal to zero");
    }
}
