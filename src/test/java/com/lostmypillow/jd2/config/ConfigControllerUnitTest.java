package com.lostmypillow.jd2.config;

import com.lostmypillow.jd2.structure.AbstractType;
import com.lostmypillow.jd2.structure.AdvancedConfigAPIEntry;
import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.EnumOption;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConfigControllerUnitTest {

    @Mock
    private ConfigClient client;

    private ConfigController controller;

    @BeforeEach
    void setup() {
        controller = new ConfigController(client);
    }

    @Test
    void get_returnsValue() {
        when(client.get("iface", "storage", "key")).thenReturn(new Jd2Response<>("val"));

        ResponseEntity<Object> response = controller.get("iface", "storage", "key");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("val", response.getBody());
        verify(client).get("iface", "storage", "key");
    }

    @Test
    void getDefault_returnsDefaultValue() {
        when(client.getDefault("iface", "storage", "key")).thenReturn(new Jd2Response<>("defaultVal"));

        ResponseEntity<Object> response = controller.getDefault("iface", "storage", "key");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("defaultVal", response.getBody());
        verify(client).getDefault("iface", "storage", "key");
    }

    @Test
    void list_noArgs_returnsEntries() {
        List<AdvancedConfigAPIEntry> entries = List.of(
                new AdvancedConfigAPIEntry(AbstractType.STRING, "def", "docs", null, null, "iface", "key", "storage", "String", "val")
        );
        when(client.list()).thenReturn(new Jd2Response<>(entries));

        ResponseEntity<List<AdvancedConfigAPIEntry>> response = controller.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entries, response.getBody());
        verify(client).list();
    }

    @Test
    void list_fiveArgs_returnsEntries() {
        List<AdvancedConfigAPIEntry> entries = List.of(
                new AdvancedConfigAPIEntry(AbstractType.STRING, "def", "docs", null, null, "iface", "key", "storage", "String", "val")
        );
        when(client.list(".*", true, true, true, true)).thenReturn(new Jd2Response<>(entries));

        ResponseEntity<List<AdvancedConfigAPIEntry>> response = controller.list(".*", true, true, true, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entries, response.getBody());
        verify(client).list(".*", true, true, true, true);
    }

    @Test
    @SuppressWarnings("deprecation")
    void list_fourArgs_returnsEntries() {
        List<AdvancedConfigAPIEntry> entries = List.of(
                new AdvancedConfigAPIEntry(AbstractType.STRING, "def", "docs", null, null, "iface", "key", "storage", "String", "val")
        );
        when(client.list(".*", true, true, true)).thenReturn(new Jd2Response<>(entries));

        ResponseEntity<List<AdvancedConfigAPIEntry>> response = controller.list(".*", true, true, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entries, response.getBody());
        verify(client).list(".*", true, true, true);
    }

    @Test
    void listEnum_returnsEnumOptions() {
        List<EnumOption> options = List.of(new EnumOption("Default", "DEFAULT"));
        when(client.listEnum("type")).thenReturn(new Jd2Response<>(options));

        ResponseEntity<List<EnumOption>> response = controller.listEnum("type");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(options, response.getBody());
        verify(client).listEnum("type");
    }

    @Test
    void query_returnsEntries() {
        String query = Json.stringify(new AdvancedConfigQuery());
        List<AdvancedConfigAPIEntry> entries = List.of(
                new AdvancedConfigAPIEntry(AbstractType.STRING, "def", "docs", null, null, "iface", "key", "storage", "String", "val")
        );
        when(client.query(query)).thenReturn(new Jd2Response<>(entries));

        ResponseEntity<List<AdvancedConfigAPIEntry>> response = controller.query(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entries, response.getBody());
        verify(client).query(query);
    }

    @Test
    void reset_returnsBoolean() {
        when(client.reset("iface", "storage", "key")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.reset("iface", "storage", "key");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).reset("iface", "storage", "key");
    }

    @Test
    void set_returnsBoolean() {
        when(client.set("iface", "storage", "key", "val")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.set("iface", "storage", "key", "val");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).set("iface", "storage", "key", "val");
    }
}

