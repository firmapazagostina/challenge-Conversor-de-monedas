package com.alura.conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private static final String API_BASE = "https://v6.exchangerate-api.com/v6/ac9edde2a023854b0e768e0e/latest/";

    public JsonObject obtenerTasasDeCambio(String moneda) throws IOException, InterruptedException {
        try {
            String direccion = API_BASE + moneda;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            return json.get("conversion_rates").getAsJsonObject();
        } catch (IOException e) {
            throw new IOException("Error al conectar con el servicio de tasas de cambio", e);
        } catch (InterruptedException e) {
            throw new InterruptedException("Conexión interrumpida");
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al obtener tasas de cambio", e);
        }
    }
}