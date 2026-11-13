package com.lostmypillow.jd2.plugins;

import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.Plugin;
import com.lostmypillow.jd2.structure.PluginConfigEntry;
import com.lostmypillow.jd2.structure.PluginsQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PluginsControllerUnitTest {

    @Mock
    private PluginsClient pluginsClient;

    @InjectMocks
    private PluginsController pluginsController;

    @Test
    void get_returnsValue() {
        when(pluginsClient.get("iface", "display", "key")).thenReturn(new Jd2Response<>("val"));

        ResponseEntity<Object> response = pluginsController.get("iface", "display", "key");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("val", response.getBody());
        verify(pluginsClient).get("iface", "display", "key");
    }

    @Test
    void getAllPluginRegex_returnsMap() {
        Map<String, List<String>> expected = Map.of("youtube.com", List.of("regex1"));
        when(pluginsClient.getAllPluginRegex()).thenReturn(new Jd2Response<>(expected));

        ResponseEntity<Map<String, List<String>>> response = pluginsController.getAllPluginRegex();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(pluginsClient).getAllPluginRegex();
    }

    @Test
    void getPluginRegex_returnsList() {
        List<String> expected = List.of("regex1");
        when(pluginsClient.getPluginRegex("https://youtube.com")).thenReturn(new Jd2Response<>(expected));

        ResponseEntity<List<String>> response = pluginsController.getPluginRegex("https://youtube.com");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(pluginsClient).getPluginRegex("https://youtube.com");
    }

    @Test
    void list_returnsPlugins() {
        String queryJson = Json.stringify(new PluginsQuery());
        List<Plugin> expected = List.of(new Plugin(null, "cls", null, "youtube.com", null, null, null, null, null, null, null, null, null, "100"));
        when(pluginsClient.list(queryJson)).thenReturn(new Jd2Response<>(expected));

        ResponseEntity<List<Plugin>> response = pluginsController.list(queryJson);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(pluginsClient).list(queryJson);
    }

    @Test
    void query_returnsPluginConfigEntries() {
        String queryJson = Json.stringify(new AdvancedConfigQuery());
        List<PluginConfigEntry> expected = List.of(new PluginConfigEntry(null, null, null, null, null, "iface", "key", "storage", "BOOLEAN", true));
        when(pluginsClient.query(queryJson)).thenReturn(new Jd2Response<>(expected));

        ResponseEntity<List<PluginConfigEntry>> response = pluginsController.query(queryJson);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(pluginsClient).query(queryJson);
    }

    @Test
    void reset_returnsBoolean() {
        when(pluginsClient.reset("iface", "display", "key")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = pluginsController.reset("iface", "display", "key");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(pluginsClient).reset("iface", "display", "key");
    }

    @Test
    void set_returnsBoolean() {
        when(pluginsClient.set("iface", "display", "key", "newVal")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = pluginsController.set("iface", "display", "key", "newVal");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(pluginsClient).set("iface", "display", "key", "newVal");
    }
}

