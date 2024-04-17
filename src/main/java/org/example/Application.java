package org.example;

import org.example.converter.CurrencyConverter;
import org.example.converter.DollarToEuroConverter;
import org.example.converter.EuroToDollarConverter;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ServiceLoader;

public class Application {

    private static final String DOLLAR_SYMBOL = "$";
    private static final String EURO_SYMBOL = "€";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ServiceLoader<CurrencyConverter> loader = ServiceLoader.load(CurrencyConverter.class);

        boolean running = true;
        while (running) {
            printAvailableConverters(loader);
            int choice = userChoice(loader);

            if (choice == 0) {
                running = false;
            } else {
                CurrencyConverter chosenConverter = findConverter(loader, choice);
                if (chosenConverter != null) {
                    convertAmount(chosenConverter, scanner);
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        }

        scanner.close();
    }

    private static void printAvailableConverters(ServiceLoader<CurrencyConverter> loader) {
        System.out.println("\nAvailable converters:");
        int i = 1;
        for (CurrencyConverter converter : loader) {
            System.out.println(i + ". " + converter.getClass().getSimpleName());
            i++;
        }
        System.out.println("0. Exit");
    }

    private static int userChoice(ServiceLoader<CurrencyConverter> loader) {
        Scanner scanner = new Scanner(System.in);
        int maxChoice = loader.stream().mapToInt(c -> 1).sum();
        int choice = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print("\nSelect a converter (1-" + maxChoice + "): ");
                choice = scanner.nextInt();
                if (choice >= 0 && choice <= maxChoice) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        } while (!validInput);
        return choice;
    }

    private static CurrencyConverter findConverter(ServiceLoader<CurrencyConverter> loader, int choice) {
        int i = 1;
        for (CurrencyConverter converter : loader) {
            if (i == choice) {
                return converter;
            }
            i++;
        }
        return null;
    }

    private static void convertAmount(CurrencyConverter chosenConverter, Scanner scanner) {
        System.out.print("\nEnter amount to convert: ");
        double amount = scanner.nextDouble();
        double convertedAmount = chosenConverter.convert(amount);
        System.out.println("Converted amount: " + retrieveCurrencySymbol(chosenConverter) + convertedAmount);
        pressEnterToContinue();
    }

    private static String retrieveCurrencySymbol(CurrencyConverter converter) {
        if (converter instanceof EuroToDollarConverter) {
            return EURO_SYMBOL;
        } else if (converter instanceof DollarToEuroConverter) {
            return DOLLAR_SYMBOL;
        }
        return "";
    }

    private static void pressEnterToContinue() {
        System.out.println("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}