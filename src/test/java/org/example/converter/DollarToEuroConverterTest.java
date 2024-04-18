package org.example.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DollarToEuroConverterTest {

    private DollarToEuroConverter dollarToEuroConverter;

    @BeforeEach
    void setUp() {
        dollarToEuroConverter = new DollarToEuroConverter();
    }

    @Test
    @DisplayName("Should return positive value")
    void shouldReturnPositiveValue() {
        assertEquals(9.4, dollarToEuroConverter.convert(10), 0.01);
    }

    @Test
    @DisplayName("Should return zero value")
    void shouldReturnZeroValue() {
        assertEquals(0.0, dollarToEuroConverter.convert(0), 0.01);
    }

    @Test
    @DisplayName("Should return negative value")
    void shouldReturnNegativeValue() {
        assertEquals(-9.4, dollarToEuroConverter.convert(-10), 0.01);
    }

}
