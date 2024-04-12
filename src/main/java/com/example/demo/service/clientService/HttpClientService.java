package com.example.demo.service.clientService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Service
public class HttpClientService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public HttpClientService() {
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClient.newHttpClient();
    }

    public ResponseEntity<String> getPing(String serviceUrl) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serviceUrl))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ResponseEntity<String> resEntity = objectMapper.readValue(response.body(), new TypeReference<>() {
                });

                return ResponseEntity.status(resEntity.getStatusCode())
                        .header(Objects.requireNonNull(resEntity.getHeaders().getFirst("server-port")))
                        .body(resEntity.getBody());
            }
            throw new RuntimeException("non 200 status code from response");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
