package com.lostmypillow.jd2.plugins;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.Plugin;
import com.lostmypillow.jd2.structure.PluginConfigEntry;
import com.lostmypillow.jd2.structure.PluginsQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PluginsClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PluginsClient pluginsClient;

    @Test
    void testGet() {
        assertDoesNotThrow(() -> pluginsClient.get("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key"));
    }

    @Test
    void testGetAllPluginRegex() {
        Jd2Response<Map<String, List<String>>> response = pluginsClient.getAllPluginRegex();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testGetPluginRegex() {
        Jd2Response<List<String>> response = pluginsClient.getPluginRegex("https://www.youtube.com/watch?v=123");
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testList() {
        Jd2Response<List<Plugin>> response = pluginsClient.list(Json.stringify(new PluginsQuery()));
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testQuery() {
        Jd2Response<List<PluginConfigEntry>> response = pluginsClient.query(Json.stringify(new AdvancedConfigQuery()));
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testReset() {
        assertDoesNotThrow(() -> pluginsClient.reset("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key"));
    }

    @Test
    void testSet() {
        assertDoesNotThrow(() -> pluginsClient.set("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key", "value"));
    }
}

