package com.aluracursos.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConsultaChatGPT {

    // Método para cargar la API key desde el archivo de propiedades
    private static String obtenerApiKey() {
        Properties propiedades = new Properties();

        // Usamos ClassLoader para cargar el archivo desde resources
        try (InputStream input = ConsultaChatGPT.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("No se encontró el archivo de propiedades.");
                return null;
            }
            propiedades.load(input);
            return propiedades.getProperty("apiKey");
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de propiedades: " + e.getMessage());
            return null;
        }
    }

    public static String obtenerTraduccion(String texto) {
        String apiKey = obtenerApiKey();

        if (apiKey == null) {
            System.out.println("API Key no encontrada. Utilizando el texto original.");
            return texto; // Retorna el texto original si no se encuentra la clave de API
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest requisicion = CompletionRequest.builder()
                    .model("gpt-3.5-turbo-instruct")
                    .prompt("traduce a español el siguiente texto: " + texto)
                    .maxTokens(1000)
                    .temperature(0.7)
                    .build();

            var respuesta = service.createCompletion(requisicion);
            return respuesta.getChoices().get(0).getText();
        } catch (Exception e) {
            // Si ocurre un error con la API (por ejemplo, si la API key no funciona),
            // retornamos el texto original
            System.out.println("Error al llamar la API de OpenAI: " + e.getMessage());
            System.out.println("Mostrando el texto original...");
            return texto; // Retorna el texto original
        }
    }
}