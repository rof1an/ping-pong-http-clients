package com.example.demo.service.clientService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;
    private final static String headerName = "server-port";

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getPing(String serviceUrl) {
        ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("non 200 status code from response");
        }

        return ResponseEntity.status(response.getStatusCode())
                .header(headerName, response.getHeaders().getFirst(headerName))
                .body(response.getBody());
    }
}

