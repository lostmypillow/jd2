package com.lostmypillow.jd2.downloadcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/downloadcontroller")
public class DownloadControllerController {
    public final DownloadControllerClient client;

    public DownloadControllerController(DownloadControllerClient client) {
        this.client = client;
    }

    @PostMapping("/forceDownload")
    public ResponseEntity<Void> forceDownload(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.forceDownload(linkIds, packageIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getCurrentState")
    public ResponseEntity<String> getCurrentState() {
        return ResponseEntity.ok(client.getCurrentState().data());
    }

    @GetMapping("/getSpeedInBps")
    public ResponseEntity<Integer> getSpeedInBps() {
        return ResponseEntity.ok(client.getSpeedInBps().data());
    }

    @PostMapping("/pause")
    public ResponseEntity<Boolean> pause(@RequestParam("value") boolean value) {
        return ResponseEntity.ok(client.pause(value).data());
    }

    @PostMapping("/start")
    public ResponseEntity<Boolean> start() {
        return ResponseEntity.ok(client.start().data());
    }

    @PostMapping("/stop")
    public ResponseEntity<Boolean> stop() {
        return ResponseEntity.ok(client.stop().data());
    }
}

