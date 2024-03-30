package com.example.demo.controller;

import com.example.demo.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ping")
public class PongController {
    private final PingService pingService;

    @Autowired
    public PongController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping
    public String getPong() {
        return "pong";
    }

    @GetMapping(path = "/remote_ping")
    public String getRemotePing(@RequestParam("httpClient") String httpClient, @RequestParam("url") String url) {
        return pingService.getPing(httpClient, url);
    }
}
