package com.polar.bookService.data;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidatorTest {
    private static Validator validator;

    @BeforeAll
    static void setValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();;
    }

    @Test
    void whenAllFieldsAreDefinedCorretlyThenValidationSucceeds(){
        var book = Book.of("1234","Lord of the rings","Tolkin",500.00);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenSerialNumberIsNotDefindPropertyThenValidationFail(){
        var book = Book.of("1","Lord of the rings","Tolkin",500.00);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The serial number must be formatted correctly.");
    }

    @Test
    void whenBookAuthorIsNotDefinedThenValidationFail() {
        var book = Book.of("1234","Lord of the rings"," ",500.00);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The book author must be defined.");
    }

    @Test
    void whenBookPriceIsNotDefinedCorrectlyThenValidationFail(){
        var book = Book.of("1234","Lord of the rings","Tolkin",0.00);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The book price must be greater than zero.");
    }

}
