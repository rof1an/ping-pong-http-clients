package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPing(String serviceUrl) {
        ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("non 200 status code from response");
        }

        return response.getBody();
    }
}

