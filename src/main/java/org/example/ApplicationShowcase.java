package org.example;

import org.example.converter.CurrencyConverter;

import java.util.ServiceLoader;

public class ApplicationShowcase {

    public static void main(String[] args) {
        showcase(1, 1);
        showcase(2, 2);
        showcase(0, 0);
    }

    private static void showcase(int choice, int amount){
        ServiceLoader<CurrencyConverter> loader = ServiceLoader.load(CurrencyConverter.class);
        Application.printAvailableConverters(loader);
        System.out.print("\nSelect a converter (1-2): " + choice + "\n");
        if (choice > 0) {
            System.out.print("Enter amount to convert: " + amount + "\n");

            CurrencyConverter chosenConverter = Application.findConverter(loader, choice);
            double convertedAmount = chosenConverter.convert(amount);
            System.out.println("Converted amount: " + Application.retrieveCurrencySymbol(chosenConverter) + convertedAmount);
            System.out.println("Press Enter to continue...\n");
        } else if (choice == 0) {
            System.out.println("\nProcess finished with exit code 0");
        }
    }

}
