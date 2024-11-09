package com.currencyconverter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.logging.Level;
import java.util.logging.Logger;


public class JsonProcessor {

    private static final Logger logger = Logger.getLogger(JsonProcessor.class.getName());

    public static void processResponse(String jsonResponse) {
        Gson gson = new Gson();
        ExchangeRatesResponse response = gson.fromJson(jsonResponse, ExchangeRatesResponse.class);

        if (response == null) {
            logger.log(Level.SEVERE, "Respuesta de la API no válida.");
            return;
        }

        JsonObject conversionRates = response.getConversionRates();
        System.out.println("Moneda base: " + response.getBaseCode());

        String[] monedasInteresadas = {"EUR", "GBP", "AUD", "JPY"};
        for (String moneda : monedasInteresadas) {
            double tasa = conversionRates.has(moneda) ? conversionRates.get(moneda).getAsDouble() : -1;

            if (tasa != -1) {
                System.out.println("Tasa de " + moneda + ": " + tasa);
            } else {
                System.out.println("Tasa de " + moneda + " no disponible.");
            }
        }
    }

    public static void main(String[] args) {
        String jsonResponse = """
                {
                    "result": "success",
                    "documentation": "https://www.exchangerate-api.com/docs",
                    "terms_of_use": "https://www.exchangerate-api.com/terms",
                    "time_last_update_unix": 1585267200,
                    "time_last_update_utc": "Fri, 27 Mar 2020 00:00:00 +0000",
                    "time_next_update_unix": 1583533700,
                    "time_next_update_utc": "Sat, 28 Mar 2020 00:00:00 +0000",
                    "base_code": "USD",
                    "conversion_rates": {
                        "USD": 1,
                        "AUD": 1.4817,
                        "BGN": 1.7741,
                        "CAD": 1.3168,
                        "CHF": 0.9774,
                        "CNY": 6.9454,
                        "EGP": 15.7361,
                        "EUR": 0.9013,
                        "GBP": 0.7679
                    }
                }
            """;
         processResponse(jsonResponse);
    }
}