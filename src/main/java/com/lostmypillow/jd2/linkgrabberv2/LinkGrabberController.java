package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.structure.CrawledLink;
import com.lostmypillow.jd2.structure.CrawledLinkQuery;
import com.lostmypillow.jd2.util.Json;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
public class LinkGrabberController {
    public final LinkGrabberService service;
    public final LinkGrabberClient client;

    public LinkGrabberController(LinkGrabberService service, LinkGrabberClient client) {
        this.service = service;
        this.client = client;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CrawledLink>> getLinkList() {
        return ResponseEntity.ok(client.queryLinks(Json.stringify(new CrawledLinkQuery())).data());
    }

    @PostMapping("/clear")
    public ResponseEntity<Boolean> clearLinkList() {
        return ResponseEntity.ok(client.clearList().data());
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addLink(@RequestBody String link) {
        service.addLinkAsync(link);
        return ResponseEntity.accepted().build();
    }
}
