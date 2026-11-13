package com.lostmypillow.jd2.captchaforward;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/captchaforward")
public class CaptchaForwardController {
    public final CaptchaForwardClient client;

    public CaptchaForwardController(CaptchaForwardClient client) {
        this.client = client;
    }

    @PostMapping("/createJobRecaptchaV2")
    public ResponseEntity<Long> createJobRecaptchaV2(
            @RequestParam("param1") String param1,
            @RequestParam("param2") String param2,
            @RequestParam("param3") String param3,
            @RequestParam("param4") String param4
    ) {
        return ResponseEntity.ok(client.createJobRecaptchaV2(param1, param2, param3, param4).data());
    }

    @GetMapping("/getResult")
    public ResponseEntity<String> getResult(@RequestParam("param1") long param1) {
        return ResponseEntity.ok(client.getResult(param1).data());
    }
}

