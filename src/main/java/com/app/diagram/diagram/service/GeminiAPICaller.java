package com.app.diagram.diagram.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeminiAPICaller {
    public String geminiCall(String prompt){
        JsonNode response = callGeminiAPI(prompt);

        if (response != null) {
            // Navigate to the text field in the JSON structure
            JsonNode textNode = response.at("/candidates/0/content/parts/0/text");
            if (!textNode.isMissingNode()) {
                return textNode.asText().trim(); // Return the text without the trailing newline
            }
        }
        return null;
    }
    public JsonNode callGeminiAPI(String prompt) {
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyApmqXICSIr73ReFqXIAunuzSIZHmKUQhw";

        try {
            // Build the JSON request body
            String requestBody = "{ \"contents\": [ { \"parts\": [ { \"text\": \"" + prompt + "\" } ] } ] }";

            // Create the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the HTTP request and receive the response
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response body into a JsonNode
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.body());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
