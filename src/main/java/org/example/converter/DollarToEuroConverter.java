package org.example.converter;

public class DollarToEuroConverter implements CurrencyConverter {
    @Override
    public double convert(double amount) {
        return amount * 0.94; // value as of 2024-04-17
    }
}