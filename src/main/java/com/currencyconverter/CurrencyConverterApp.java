package com.currencyconverter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyConverterApp {

    private static final String API_KEY = ("fb475756d58b2d50cfebfd8c");
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private static final Logger logger = Logger.getLogger(CurrencyConverterApp.class.getName());

    public static void main(String[] args) {

        String ratesJson = getExchangeRates("USD");  // Usar "USD" como base o la que prefieras
        if (ratesJson == null) {
            System.out.println("No se pudieron obtener las tasas de cambio. Inténtelo de nuevo más tarde.");
            return;
        }

        // Crear instancia de la clase ExchangeRates para obtener tasas de cambio
        ExchangeRatesResponse exchangeRatesResponse = new ExchangeRatesResponse(ratesJson);
        Map<String, Double> rates = exchangeRatesResponse.getRates();

        // Crear un objeto Scanner para capturar la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Mostrar el menú principal de opciones
            System.out.println("\n==========================================================================");
            System.out.println("Bienvenidos al mejor Convertidor de Monedas diseñado por Albx.Dev!");
            System.out.println("1. Realizar conversión personalizada");
            System.out.println("2. Realizar conversión con tasas de cambio");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción válida (1-3): ");
            System.out.println("\n==========================================================================");

            int option = scanner.nextInt();  // Capturar la opción seleccionada
            scanner.nextLine();  // Limpiar el buffer de la entrada

            switch (option) {
                case 1:
                    // Menú para conversión personalizada (opciones 1 a 10)
                    realizarConversionPersonalizada(scanner, rates);
                    break;
                case 2:
                    // Submenú para conversiones con tasas de cambio generales
                    mostrarConversionesGenerales(scanner, rates);
                    break;
                case 3:
                    // Salir de la aplicación
                    System.out.println("Gracias por usar el Convertidor de Monedas. ¡Adiós!");
                    scanner.close();
                    return;  // Terminar el programa
                default:
                    // Opción no válida
                    System.out.println("Opción no válida. Por favor intente nuevamente.");
            }
        }
    }

    // Método para realizar las conversiones personalizadas (opciones 1-10)
    private static void realizarConversionPersonalizada(Scanner scanner, Map<String, Double> rates) {
        System.out.println("\n==========================================================================");
        System.out.println("1. Dólares a Pesos Argentinos (USD a ARS)");
        System.out.println("2. Pesos Argentinos a Dólares (ARS a USD)");
        System.out.println("3. Dólares a Euros (USD a EUR)");
        System.out.println("4. Euros a Dólares (EUR a USD)");
        System.out.println("5. Dólares a Libras Esterlinas (USD a GBP)");
        System.out.println("6. Libras Esterlinas a Dólares (GBP a USD)");
        System.out.println("7. Dólares a Yenes Japoneses (USD a JPY)");
        System.out.println("8. Yenes Japoneses a Dólares (JPY a USD)");
        System.out.println("9. Dólares a Riyales Sauditas (USD a SAR)");
        System.out.println("10. Riyales Sauditas a Dólares (SAR a USD)");
        System.out.println("11. Volver al menú principal");
        System.out.println("12. Salir del programa");
        System.out.print("Seleccione una opción (1-12): ");
        System.out.println("\n==========================================================================");

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option >= 1 && option <= 10) {
            realizarConversionPredefinida(option, rates, scanner);
        } else if (option == 11) {
            System.out.println("Regresando al menú principal...");
        } else if (option == 12) {
            System.out.println("Gracias por usar mi Convertidor de Monedas. ¡Adiós!");
            System.exit(0);
        } else {
            System.out.println("Opción no válida. Por favor intente nuevamente.");
        }
    }

    // Método para realizar la conversión con tasas predefinidas según la opción seleccionada
    private static void realizarConversionPredefinida(int option, Map<String, Double> rates, Scanner scanner) {
        String fromCurrency, toCurrency;

        switch (option) {
            case 1 -> {
                fromCurrency = "USD";
                toCurrency = "ARS";
            }
            case 2 -> {
                fromCurrency = "ARS";
                toCurrency = "USD";
            }
            case 3 -> {
                fromCurrency = "USD";
                toCurrency = "EUR";
            }
            case 4 -> {
                fromCurrency = "EUR";
                toCurrency = "USD";
            }
            case 5 -> {
                fromCurrency = "USD";
                toCurrency = "GBP";
            }
            case 6 -> {
                fromCurrency = "GBP";
                toCurrency = "USD";
            }
            case 7 -> {
                fromCurrency = "USD";
                toCurrency = "JPY";
            }
            case 8 -> {
                fromCurrency = "JPY";
                toCurrency = "USD";
            }
            case 9 -> {
                fromCurrency = "USD";
                toCurrency = "SAR";
            }
            case 10 -> {
                fromCurrency = "SAR";
                toCurrency = "USD";
            }
            default -> {
                System.out.println("Opción no válida. Por favor elija una nuevamente.");
                return;
            }
        }

        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = scanner.nextDouble();

        double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency, rates);
        System.out.printf("El valor convertido de %.2f %s a %s es: %s%n", amount, fromCurrency, toCurrency, formatNumber(convertedAmount));
    }

    // Método para mostrar el submenú de conversiones generales
    private static void mostrarConversionesGenerales(Scanner scanner, Map<String, Double> rates) {
        while (true) {
            System.out.println("\n==========================================================================");
            System.out.println("1. Realizar conversión");
            System.out.println("2. Ver tasas de cambio");
            System.out.println("3. Volver al menú principal");
            System.out.println("4. Salir del programa");
            System.out.print("Seleccione una opción válida (1-4): ");
            System.out.println("\n==========================================================================");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> realizarConversion(scanner, rates);
                case 2 -> mostrarTasasDeCambio(rates);
                case 3 -> {
                    return;
                } // Salir al menú principal
                case 4 -> {
                    System.out.println("Gracias por usar mi Convertidor de Monedas. ¡Adiós!");
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida. Por favor elija una nuevamente.");
            }
        }
    }

    public static double convertCurrency(double amount, String fromCurrency, String toCurrency, Map<String, Double> rates) {
        if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Monedas no encontradas en las tasas de cambio.");
        }
        return (amount / rates.get(fromCurrency)) * rates.get(toCurrency);
    }

    public static String getExchangeRates(String baseCurrency) {
        try {
            URI uri = new URI(BASE_URL + baseCurrency);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                logger.log(Level.SEVERE, "Error al obtener tasas de cambio: " + response.statusCode());
                System.out.println("Response Body: " + response.body());  // Debugging line
                return null;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al hacer la solicitud HTTP", e);
            return null;
        }
    }


    // Método para realizar una conversión general entre dos monedas
    private static void realizarConversion(Scanner scanner, Map<String, Double> rates) {
        try {
            System.out.print("Ingrese la cantidad a convertir: ");
            double amount = scanner.nextDouble();

            System.out.print("Ingrese la moneda de origen (ej. USD): ");
            String fromCurrency = scanner.next().toUpperCase();

            System.out.print("Ingrese la moneda de destino (ej. EUR): ");
            String toCurrency = scanner.next().toUpperCase();

            double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency, rates);
            System.out.printf("El valor convertido de %.2f %s a %s es: %s%n", amount, fromCurrency, toCurrency, formatNumber(convertedAmount));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine(); // Limpiar el buffer en caso de error
        }
    }

    // Método para mostrar tasas de cambio disponibles
    private static void mostrarTasasDeCambio(Map<String, Double> rates) {
        System.out.println("Tasas de cambio actuales:");
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            System.out.printf("%s: %f%n", entry.getKey(), entry.getValue());
        }
    }

    // Método para dar formato a los números grandes (billones, millones, miles)
    private static String formatNumber(double number) {
        if (number >= 1_000_000_000) {
            return String.format("%.2fB", number / 1_000_000_000);
        } else if (number >= 1_000_000) {
            return String.format("%.2fM", number / 1_000_000);
        } else if (number >= 1_000) {
            return String.format("%.2fK", number / 1_000);
        } else {
            return String.format("%.2f", number);
        }
    }
}