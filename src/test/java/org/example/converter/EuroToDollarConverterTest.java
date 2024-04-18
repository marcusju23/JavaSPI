package org.example.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EuroToDollarConverterTest {

    private EuroToDollarConverter euroToDollarConverter;

    @BeforeEach
    void setUp() {
        euroToDollarConverter = new EuroToDollarConverter();
    }

    @Test
    @DisplayName("Should return positive value")
    void shouldReturnPositiveValue() {
        assertEquals(10.7, euroToDollarConverter.convert(10), 0.01);
    }

    @Test
    @DisplayName("Should return zero value")
    void shouldReturnZeroValue() {
        assertEquals(0.0, euroToDollarConverter.convert(0), 0.01);
    }

    @Test
    @DisplayName("Should return negative value")
    void shouldReturnNegativeValue() {
        assertEquals(-10.7, euroToDollarConverter.convert(-10), 0.01);
    }

}
