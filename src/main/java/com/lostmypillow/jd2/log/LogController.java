package com.lostmypillow.jd2.log;

import com.lostmypillow.jd2.structure.LogFolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    public final LogClient client;

    public LogController(LogClient client) {
        this.client = client;
    }

    @GetMapping("/getAvailableLogs")
    public ResponseEntity<List<LogFolder>> getAvailableLogs() {
        return ResponseEntity.ok(client.getAvailableLogs().data());
    }

    @PostMapping("/sendLogFile")
    public ResponseEntity<String> sendLogFile(@RequestParam("logFolders") String logFolders) {
        return ResponseEntity.ok(client.sendLogFile(logFolders).data());
    }
}

