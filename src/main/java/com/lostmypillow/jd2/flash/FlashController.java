package com.lostmypillow.jd2.flash;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flash")
public class FlashController {
    public final FlashClient client;

    public FlashController(FlashClient client) {
        this.client = client;
    }

    @PostMapping(value = "/add", params = {"password", "source", "url"})
    public ResponseEntity<Void> add(
            @RequestParam("password") String password,
            @RequestParam("source") String source,
            @RequestParam("url") String url
    ) {
        client.add(password, source, url);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/add", params = {"param1", "param2", "param3", "param4"})
    public ResponseEntity<Void> add(
            @RequestParam("param1") String param1,
            @RequestParam("param2") String param2,
            @RequestParam("param3") String param3,
            @RequestParam("param4") String param4
    ) {
        client.add(param1, param2, param3, param4);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/add", params = {"!password", "!param1"})
    public ResponseEntity<Void> add() {
        client.add();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addcnl")
    public ResponseEntity<Void> addcnl(@RequestParam("cnl") String cnl) {
        client.addcnl(cnl);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addcrypted")
    public ResponseEntity<Void> addcrypted() {
        client.addcrypted();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addcrypted2")
    public ResponseEntity<Void> addcrypted2() {
        client.addcrypted2();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addcrypted2Remote")
    public ResponseEntity<Void> addcrypted2Remote(
            @RequestParam("crypted") String crypted,
            @RequestParam("jk") String jk,
            @RequestParam("k") String k
    ) {
        client.addcrypted2Remote(crypted, jk, k);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<Void> flash() {
        client.flash();
        return ResponseEntity.ok().build();
    }
}

