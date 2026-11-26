package com.lostmypillow.jd2.update;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/update")
public class UpdateController {
    private final UpdateClient updateClient;

    public UpdateController(UpdateClient updateClient) {
        this.updateClient = updateClient;
    }

    @GetMapping("/isUpdateAvailable")
    public ResponseEntity<Boolean> isUpdateAvailable() {
        return ResponseEntity.ok(updateClient.isUpdateAvailable().data());
    }

    @PostMapping("/restartAndUpdate")
    public ResponseEntity<Void> restartAndUpdate() {
        updateClient.restartAndUpdate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/runUpdateCheck")
    public ResponseEntity<Void> runUpdateCheck() {
        updateClient.runUpdateCheck();
        return ResponseEntity.ok().build();
    }
}

