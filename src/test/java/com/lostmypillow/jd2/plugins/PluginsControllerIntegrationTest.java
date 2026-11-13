package com.lostmypillow.jd2.plugins;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.Plugin;
import com.lostmypillow.jd2.structure.PluginConfigEntry;
import com.lostmypillow.jd2.structure.PluginsQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PluginsControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PluginsController pluginsController;

    @Test
    void testGet() {
        assertDoesNotThrow(() -> pluginsController.get("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key"));
    }

    @Test
    void testGetAllPluginRegex() {
        ResponseEntity<Map<String, List<String>>> response = pluginsController.getAllPluginRegex();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetPluginRegex() {
        ResponseEntity<List<String>> response = pluginsController.getPluginRegex("https://www.youtube.com/watch?v=123");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testList() {
        ResponseEntity<List<Plugin>> response = pluginsController.list(Json.stringify(new PluginsQuery()));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testQuery() {
        ResponseEntity<List<PluginConfigEntry>> response = pluginsController.query(Json.stringify(new AdvancedConfigQuery()));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testReset() {
        assertDoesNotThrow(() -> pluginsController.reset("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key"));
    }

    @Test
    void testSet() {
        assertDoesNotThrow(() -> pluginsController.set("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key", "value"));
    }
}

