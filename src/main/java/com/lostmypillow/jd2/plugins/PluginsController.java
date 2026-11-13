package com.lostmypillow.jd2.plugins;

import com.lostmypillow.jd2.structure.Plugin;
import com.lostmypillow.jd2.structure.PluginConfigEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plugins")
public class PluginsController {
    private final PluginsClient pluginsClient;

    public PluginsController(PluginsClient pluginsClient) {
        this.pluginsClient = pluginsClient;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> get(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("displayName") String displayName,
            @RequestParam("key") String key
    ) {
        return ResponseEntity.ok(pluginsClient.get(interfaceName, displayName, key).data());
    }

    @GetMapping("/getAllPluginRegex")
    public ResponseEntity<Map<String, List<String>>> getAllPluginRegex() {
        return ResponseEntity.ok(pluginsClient.getAllPluginRegex().data());
    }

    @GetMapping("/getPluginRegex")
    public ResponseEntity<List<String>> getPluginRegex(@RequestParam("URL") String url) {
        return ResponseEntity.ok(pluginsClient.getPluginRegex(url).data());
    }

    @PostMapping("/list")
    public ResponseEntity<List<Plugin>> list(@RequestParam("query") String query) {
        return ResponseEntity.ok(pluginsClient.list(query).data());
    }

    @PostMapping("/query")
    public ResponseEntity<List<PluginConfigEntry>> query(@RequestParam("query") String query) {
        return ResponseEntity.ok(pluginsClient.query(query).data());
    }

    @PostMapping("/reset")
    public ResponseEntity<Boolean> reset(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("displayName") String displayName,
            @RequestParam("key") String key
    ) {
        return ResponseEntity.ok(pluginsClient.reset(interfaceName, displayName, key).data());
    }

    @PostMapping("/set")
    public ResponseEntity<Boolean> set(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("displayName") String displayName,
            @RequestParam("key") String key,
            @RequestParam("newValue") Object newValue
    ) {
        return ResponseEntity.ok(pluginsClient.set(interfaceName, displayName, key, newValue).data());
    }
}

