package com.lostmypillow.jd2.reconnect;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reconnect")
public class ReconnectController {
    private final ReconnectClient reconnectClient;

    public ReconnectController(ReconnectClient reconnectClient) {
        this.reconnectClient = reconnectClient;
    }

    @PostMapping("/doReconnect")
    public ResponseEntity<Void> doReconnect() {
        reconnectClient.doReconnect();
        return ResponseEntity.ok().build();
    }
}

