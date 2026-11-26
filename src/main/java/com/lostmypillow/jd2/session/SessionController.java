package com.lostmypillow.jd2.session;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/session")
public class SessionController {
    private final SessionClient sessionClient;

    public SessionController(SessionClient sessionClient) {
        this.sessionClient = sessionClient;
    }

    @PostMapping("/disconnect")
    public ResponseEntity<Boolean> disconnect() {
        return ResponseEntity.ok(sessionClient.disconnect().data());
    }

    @PostMapping("/handshake")
    public ResponseEntity<String> handshake(
            @RequestParam("user") String user,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(sessionClient.handshake(user, password).data());
    }
}

