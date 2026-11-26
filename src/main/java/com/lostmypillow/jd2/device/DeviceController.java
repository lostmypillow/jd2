package com.lostmypillow.jd2.device;

import com.lostmypillow.jd2.structure.JsonMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/device")
public class DeviceController {
    public final DeviceClient client;

    public DeviceController(DeviceClient client) {
        this.client = client;
    }

    @GetMapping("/getDirectConnectionInfos")
    public ResponseEntity<JsonMap> getDirectConnectionInfos() {
        return ResponseEntity.ok(client.getDirectConnectionInfos().data());
    }

    @GetMapping("/getSessionPublicKey")
    public ResponseEntity<String> getSessionPublicKey() {
        return ResponseEntity.ok(client.getSessionPublicKey().data());
    }

    @GetMapping("/ping")
    public ResponseEntity<Boolean> ping() {
        return ResponseEntity.ok(client.ping().data());
    }
}

