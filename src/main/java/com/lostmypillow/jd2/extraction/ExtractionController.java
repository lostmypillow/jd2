package com.lostmypillow.jd2.extraction;

import com.lostmypillow.jd2.structure.ArchiveSettings;
import com.lostmypillow.jd2.structure.ArchiveStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/extraction")
public class ExtractionController {
    public final ExtractionClient client;

    public ExtractionController(ExtractionClient client) {
        this.client = client;
    }

    @PostMapping("/addArchivePassword")
    public ResponseEntity<Void> addArchivePassword(@RequestParam("password") String password) {
        client.addArchivePassword(password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancelExtraction")
    public ResponseEntity<Boolean> cancelExtraction(@RequestParam("controllerID") long controllerID) {
        return ResponseEntity.ok(client.cancelExtraction(controllerID).data());
    }

    @PostMapping("/getArchiveInfo")
    public ResponseEntity<List<ArchiveStatus>> getArchiveInfo(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        return ResponseEntity.ok(client.getArchiveInfo(linkIds, packageIds).data());
    }

    @PostMapping("/getArchiveSettings")
    public ResponseEntity<List<ArchiveSettings>> getArchiveSettings(@RequestParam("archiveIds") String archiveIds) {
        return ResponseEntity.ok(client.getArchiveSettings(archiveIds).data());
    }

    @GetMapping("/getQueue")
    public ResponseEntity<List<ArchiveStatus>> getQueue() {
        return ResponseEntity.ok(client.getQueue().data());
    }

    @PostMapping("/setArchiveSettings")
    public ResponseEntity<Boolean> setArchiveSettings(
            @RequestParam("archiveId") String archiveId,
            @RequestParam("archiveSettings") String archiveSettings
    ) {
        return ResponseEntity.ok(client.setArchiveSettings(archiveId, archiveSettings).data());
    }

    @PostMapping("/startExtractionNow")
    public ResponseEntity<Map<String, Boolean>> startExtractionNow(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        return ResponseEntity.ok(client.startExtractionNow(linkIds, packageIds).data());
    }
}

