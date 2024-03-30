package com.example.demo.controller;

import com.example.demo.service.PingService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping(path = "/ping")
public class PingController {
    private final PingService pingService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping
    public String getPong() {
        return "pong";
    }

    @GetMapping(path = "/remote_ping")
    public String getRemotePing(@RequestParam("httpClient") String httpClient, @RequestParam("url") String url, HttpServletResponse response) {
        response.addHeader("server-rort", serverPort);
        return pingService.getPing(httpClient, url);
    }
}
