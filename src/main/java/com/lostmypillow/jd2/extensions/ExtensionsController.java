package com.lostmypillow.jd2.extensions;

import com.lostmypillow.jd2.structure.Extension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/extensions")
public class ExtensionsController {
    public final ExtensionsClient client;

    public ExtensionsController(ExtensionsClient client) {
        this.client = client;
    }

    @PostMapping("/install")
    public ResponseEntity<Boolean> install(@RequestParam("id") String id) {
        return ResponseEntity.ok(client.install(id).data());
    }

    @GetMapping("/isEnabled")
    public ResponseEntity<Boolean> isEnabled(@RequestParam("classname") String classname) {
        return ResponseEntity.ok(client.isEnabled(classname).data());
    }

    @GetMapping("/isInstalled")
    public ResponseEntity<Boolean> isInstalled(@RequestParam("id") String id) {
        return ResponseEntity.ok(client.isInstalled(id).data());
    }

    @PostMapping("/list")
    public ResponseEntity<List<Extension>> list(@RequestParam("query") String query) {
        return ResponseEntity.ok(client.list(query).data());
    }

    @PostMapping("/setEnabled")
    public ResponseEntity<Boolean> setEnabled(
            @RequestParam("classname") String classname,
            @RequestParam("b") boolean b
    ) {
        return ResponseEntity.ok(client.setEnabled(classname, b).data());
    }
}

