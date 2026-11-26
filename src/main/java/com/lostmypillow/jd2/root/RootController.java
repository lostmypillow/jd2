package com.lostmypillow.jd2.root;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RootController {
    public final RootClient client;

    public RootController(RootClient client) {
        this.client = client;
    }

    @GetMapping("/flash")
    public ResponseEntity<Void> flash() {
        client.flash();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/crossdomain.xml")
    public ResponseEntity<Void> crossdomainXml() {
        client.crossdomainXml();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favicon.ico")
    public ResponseEntity<Void> faviconIco() {
        client.faviconIco();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/flashgot")
    public ResponseEntity<Void> flashgot() {
        client.flashgot();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/jdcheck.js")
    public ResponseEntity<Void> jdcheckJs() {
        client.jdcheckJs();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/jdcheckjson")
    public ResponseEntity<Void> jdcheckjson() {
        client.jdcheckjson();
        return ResponseEntity.ok().build();
    }
}

