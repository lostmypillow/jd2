package com.lostmypillow.jd2.config;

import com.lostmypillow.jd2.structure.AdvancedConfigAPIEntry;
import com.lostmypillow.jd2.structure.EnumOption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {
    public final ConfigClient client;

    public ConfigController(ConfigClient client) {
        this.client = client;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> get(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("storage") String storage,
            @RequestParam("key") String key
    ) {
        return ResponseEntity.ok(client.get(interfaceName, storage, key).data());
    }

    @GetMapping("/getDefault")
    public ResponseEntity<Object> getDefault(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("storage") String storage,
            @RequestParam("key") String key
    ) {
        return ResponseEntity.ok(client.getDefault(interfaceName, storage, key).data());
    }

    @GetMapping(value = "/list", params = "!pattern")
    public ResponseEntity<List<AdvancedConfigAPIEntry>> list() {
        return ResponseEntity.ok(client.list().data());
    }

    @GetMapping(value = "/list", params = {"pattern", "returnEnumInfo"})
    public ResponseEntity<List<AdvancedConfigAPIEntry>> list(
            @RequestParam("pattern") String pattern,
            @RequestParam("returnDescription") boolean returnDescription,
            @RequestParam("returnValues") boolean returnValues,
            @RequestParam("returnDefaultValues") boolean returnDefaultValues,
            @RequestParam("returnEnumInfo") boolean returnEnumInfo
    ) {
        return ResponseEntity.ok(client.list(pattern, returnDescription, returnValues, returnDefaultValues, returnEnumInfo).data());
    }

    @Deprecated
    @GetMapping(value = "/list", params = {"pattern", "!returnEnumInfo"})
    public ResponseEntity<List<AdvancedConfigAPIEntry>> list(
            @RequestParam("pattern") String pattern,
            @RequestParam("returnDescription") boolean returnDescription,
            @RequestParam("returnValues") boolean returnValues,
            @RequestParam("returnDefaultValues") boolean returnDefaultValues
    ) {
        return ResponseEntity.ok(client.list(pattern, returnDescription, returnValues, returnDefaultValues).data());
    }

    @GetMapping("/listEnum")
    public ResponseEntity<List<EnumOption>> listEnum(@RequestParam("type") String type) {
        return ResponseEntity.ok(client.listEnum(type).data());
    }

    @PostMapping("/query")
    public ResponseEntity<List<AdvancedConfigAPIEntry>> query(@RequestParam("query") String query) {
        return ResponseEntity.ok(client.query(query).data());
    }

    @PostMapping("/reset")
    public ResponseEntity<Boolean> reset(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("storage") String storage,
            @RequestParam("key") String key
    ) {
        return ResponseEntity.ok(client.reset(interfaceName, storage, key).data());
    }

    @PostMapping("/set")
    public ResponseEntity<Boolean> set(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("storage") String storage,
            @RequestParam("key") String key,
            @RequestParam("value") Object value
    ) {
        return ResponseEntity.ok(client.set(interfaceName, storage, key, value).data());
    }
}

