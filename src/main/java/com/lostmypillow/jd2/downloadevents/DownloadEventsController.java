package com.lostmypillow.jd2.downloadevents;

import com.lostmypillow.jd2.structure.DownloadListDiff;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/downloadevents")
public class DownloadEventsController {
    public final DownloadEventsClient client;

    public DownloadEventsController(DownloadEventsClient client) {
        this.client = client;
    }

    @PostMapping("/queryLinks")
    public ResponseEntity<DownloadListDiff> queryLinks(
            @RequestParam("queryParams") String queryParams,
            @RequestParam("diffID") int diffID
    ) {
        return ResponseEntity.ok(client.queryLinks(queryParams, diffID).data());
    }

    @PostMapping("/setStatusEventInterval")
    public ResponseEntity<Boolean> setStatusEventInterval(
            @RequestParam("channelID") long channelID,
            @RequestParam("interval") long interval
    ) {
        return ResponseEntity.ok(client.setStatusEventInterval(channelID, interval).data());
    }
}

