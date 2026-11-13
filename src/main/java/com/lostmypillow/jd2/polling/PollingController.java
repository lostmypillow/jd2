package com.lostmypillow.jd2.polling;

import com.lostmypillow.jd2.structure.PollingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/polling")
public class PollingController {
    private final PollingClient pollingClient;

    public PollingController(PollingClient pollingClient) {
        this.pollingClient = pollingClient;
    }

    @PostMapping("/poll")
    public ResponseEntity<List<PollingResult>> poll(@RequestParam("queryParams") String queryParams) {
        return ResponseEntity.ok(pollingClient.poll(queryParams).data());
    }
}

