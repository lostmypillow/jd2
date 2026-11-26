package com.lostmypillow.jd2.captcha;

import com.lostmypillow.jd2.constants.SkipRequest;
import com.lostmypillow.jd2.structure.CaptchaJob;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/captcha")
public class CaptchaController {
    public final CaptchaClient client;

    public CaptchaController(CaptchaClient client) {
        this.client = client;
    }

    @GetMapping(value = "/get", params = "!format")
    public ResponseEntity<Void> get(@RequestParam("id") long id) {
        client.get(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get", params = "format")
    public ResponseEntity<Void> get(
            @RequestParam("id") long id,
            @RequestParam("format") String format
    ) {
        client.get(id, format);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getCaptchaJob")
    public ResponseEntity<CaptchaJob> getCaptchaJob(@RequestParam("id") long id) {
        return ResponseEntity.ok(client.getCaptchaJob(id).data());
    }

    @GetMapping("/list")
    public ResponseEntity<List<CaptchaJob>> list() {
        return ResponseEntity.ok(client.list().data());
    }

    @PostMapping(value = "/skip", params = "type")
    public ResponseEntity<Boolean> skip(
            @RequestParam("id") long id,
            @RequestParam("type") SkipRequest type
    ) {
        return ResponseEntity.ok(client.skip(id, type).data());
    }

    @Deprecated
    @PostMapping(value = "/skip", params = "!type")
    public ResponseEntity<Boolean> skip(@RequestParam("id") long id) {
        return ResponseEntity.ok(client.skip(id).data());
    }

    @PostMapping(value = "/solve", params = "resultFormat")
    public ResponseEntity<Boolean> solve(
            @RequestParam("id") long id,
            @RequestParam("result") String result,
            @RequestParam("resultFormat") String resultFormat
    ) {
        return ResponseEntity.ok(client.solve(id, result, resultFormat).data());
    }

    @PostMapping(value = "/solve", params = "!resultFormat")
    public ResponseEntity<Boolean> solve(
            @RequestParam("id") long id,
            @RequestParam("result") String result
    ) {
        return ResponseEntity.ok(client.solve(id, result).data());
    }
}

