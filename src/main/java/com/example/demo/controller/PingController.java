package com.example.demo.controller;

import com.example.demo.service.PingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ping")
public class PingController {
    private final PingService pingService;
    private final static String serverPort = "server-port";

    @Autowired
    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping
    public ResponseEntity<String> getPong(HttpServletRequest request) {
        String requestServerPort = String.valueOf(request.getServerPort());

        return ResponseEntity.ok()
                .header(serverPort, requestServerPort)
                .body("ping");
    }

    @GetMapping(path = "/remote_ping")
    public ResponseEntity<String> getRemotePing(@RequestParam("httpClient") String httpClient, @RequestParam("url") String url) {
        ResponseEntity<String> pingResult = pingService.getPing(httpClient, url);

        return ResponseEntity.ok()
                .header(serverPort, pingResult.getHeaders().getFirst(serverPort))
                .body(pingResult.getBody());
    }
}
