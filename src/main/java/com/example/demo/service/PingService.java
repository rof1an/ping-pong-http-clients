package com.example.demo.service;

import com.example.demo.service.clientService.HttpClientService;
import com.example.demo.service.clientService.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PingService {
    private final RestTemplateService restTemplateService;
    private final HttpClientService httpClientService;

    @Autowired
    public PingService(RestTemplateService restTemplateService, HttpClientService httpClientService) {
        this.restTemplateService = restTemplateService;
        this.httpClientService = httpClientService;
    }

    public ResponseEntity<String> getPing(String httpClient, String url) {
        switch (httpClient) {
            case "rest_template":
                return restTemplateService.getPing(url);

            case "http_client":
                return httpClientService.getPing(url);

            default:
                return restTemplateService.getPing(url);
        }
    }
}
