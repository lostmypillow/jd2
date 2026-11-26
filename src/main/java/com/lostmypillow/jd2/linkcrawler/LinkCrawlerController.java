package com.lostmypillow.jd2.linkcrawler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/linkcrawler")
public class LinkCrawlerController {
    public final LinkCrawlerClient client;

    public LinkCrawlerController(LinkCrawlerClient client) {
        this.client = client;
    }

    @GetMapping("/isCrawling")
    public ResponseEntity<Boolean> isCrawling() {
        return ResponseEntity.ok(client.isCrawling().data());
    }
}

