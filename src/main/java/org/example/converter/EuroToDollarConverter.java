package org.example.converter;

@ConverterName("Euro > Dollar")
public class EuroToDollarConverter implements CurrencyConverter {
    @Override
    public double convert(double amount) {
        return amount * 1.07; // value as of 2024-04-17
    }
}