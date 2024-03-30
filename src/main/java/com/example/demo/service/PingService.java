package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingService {
    private final RestTemplateService restTemplateService;

    @Autowired
    public PingService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    public String getPing(String httpClient, String url) {
        switch (httpClient) {
            case "rest_template":
                return restTemplateService.getPing(url);
            default:
                return restTemplateService.getPing(url);
        }
    }
}
