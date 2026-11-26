package com.lostmypillow.jd2.toolbar;

import com.lostmypillow.jd2.structure.LinkCheckResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/toolbar")
public class ToolbarController {
    private final ToolbarClient toolbarClient;

    public ToolbarController(ToolbarClient toolbarClient) {
        this.toolbarClient = toolbarClient;
    }

    @PostMapping("/addLinksFromDOM")
    public ResponseEntity<Object> addLinksFromDOM() {
        return ResponseEntity.ok(toolbarClient.addLinksFromDOM().data());
    }

    @PostMapping("/checkLinksFromDOM")
    public ResponseEntity<Object> checkLinksFromDOM() {
        return ResponseEntity.ok(toolbarClient.checkLinksFromDOM().data());
    }

    @GetMapping("/getStatus")
    public ResponseEntity<Object> getStatus() {
        return ResponseEntity.ok(toolbarClient.getStatus().data());
    }

    @GetMapping("/isAvailable")
    public ResponseEntity<Boolean> isAvailable() {
        return ResponseEntity.ok(toolbarClient.isAvailable().data());
    }

    @PostMapping("/pollCheckedLinksFromDOM")
    public ResponseEntity<LinkCheckResult> pollCheckedLinksFromDOM(@RequestParam("checkID") String checkID) {
        return ResponseEntity.ok(toolbarClient.pollCheckedLinksFromDOM(checkID).data());
    }

    @PostMapping("/specialURLHandling")
    public ResponseEntity<String> specialURLHandling(@RequestParam("url") String url) {
        return ResponseEntity.ok(toolbarClient.specialURLHandling(url).data());
    }

    @PostMapping("/startDownloads")
    public ResponseEntity<Boolean> startDownloads() {
        return ResponseEntity.ok(toolbarClient.startDownloads().data());
    }

    @PostMapping("/stopDownloads")
    public ResponseEntity<Boolean> stopDownloads() {
        return ResponseEntity.ok(toolbarClient.stopDownloads().data());
    }

    @PostMapping("/toggleAutomaticReconnect")
    public ResponseEntity<Boolean> toggleAutomaticReconnect() {
        return ResponseEntity.ok(toolbarClient.toggleAutomaticReconnect().data());
    }

    @PostMapping("/toggleClipboardMonitoring")
    public ResponseEntity<Boolean> toggleClipboardMonitoring() {
        return ResponseEntity.ok(toolbarClient.toggleClipboardMonitoring().data());
    }

    @PostMapping("/toggleDownloadSpeedLimit")
    public ResponseEntity<Boolean> toggleDownloadSpeedLimit() {
        return ResponseEntity.ok(toolbarClient.toggleDownloadSpeedLimit().data());
    }

    @PostMapping("/togglePauseDownloads")
    public ResponseEntity<Boolean> togglePauseDownloads() {
        return ResponseEntity.ok(toolbarClient.togglePauseDownloads().data());
    }

    @PostMapping("/togglePremium")
    public ResponseEntity<Boolean> togglePremium() {
        return ResponseEntity.ok(toolbarClient.togglePremium().data());
    }

    @PostMapping("/toggleStopAfterCurrentDownload")
    public ResponseEntity<Boolean> toggleStopAfterCurrentDownload() {
        return ResponseEntity.ok(toolbarClient.toggleStopAfterCurrentDownload().data());
    }

    @PostMapping("/triggerUpdate")
    public ResponseEntity<Boolean> triggerUpdate() {
        return ResponseEntity.ok(toolbarClient.triggerUpdate().data());
    }
}

