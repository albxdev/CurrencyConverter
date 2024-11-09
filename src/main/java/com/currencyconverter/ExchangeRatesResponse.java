package com.currencyconverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeRatesResponse {

    private String base_code;
    private JsonObject conversion_rates;

    // Constructor that accepts JSON string and initializes fields
    public ExchangeRatesResponse(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        this.base_code = jsonObject.get("base_code").getAsString();
        this.conversion_rates = jsonObject.getAsJsonObject("conversion_rates");
    }

    // Getters para base_code y conversion_rates
    public String getBaseCode() {
        return base_code;
    }

    public JsonObject getConversionRates() {
        return conversion_rates;
    }

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/fb475756d58b2d50cfebfd8c/latest/USD";
    private static final Logger logger = Logger.getLogger(ExchangeRatesResponse.class.getName());

    // Método para obtener las tasas de cambio
    public Map<String, Double> getRates() {
        Map<String, Double> rates = new HashMap<>();
        try {
            // Realizar la solicitud HTTP y obtener la respuesta en JSON
            String jsonResponse = makeHttpRequest();

            // Procesar la respuesta JSON
            rates = parseJsonResponse(jsonResponse);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener las tasas de cambio", e);
        }
        return rates;
    }

    // Método para hacer la solicitud HTTP a la API
    private String makeHttpRequest() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);  // Tiempo de espera para la conexión
        connection.setReadTimeout(10000);     // Tiempo de espera para leer la respuesta

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    // Método para parsear la respuesta JSON y extraer las tasas de cambio
    private Map<String, Double> parseJsonResponse(String jsonResponse) {
        Map<String, Double> rates = new HashMap<>();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        // Asignar base_code y conversion_rates desde la respuesta JSON
        base_code = jsonObject.get("base_code").getAsString();
        conversion_rates = jsonObject.getAsJsonObject("conversion_rates");

        // Iterar sobre las tasas de cambio y agregarlas al mapa
        for (Map.Entry<String, JsonElement> entry : conversion_rates.entrySet()) {
            String currency = entry.getKey();
            double rate = entry.getValue().getAsDouble();
            rates.put(currency, rate);
        }
        return rates;
    }
}