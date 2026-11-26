package com.lostmypillow.jd2.config;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.AdvancedConfigAPIEntry;
import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.EnumOption;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ConfigController configController;

    @Test
    void get() {
        ResponseEntity<Object> response = configController.get("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getDefault() {
        ResponseEntity<Object> response = configController.getDefault("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void list_noArgs() {
        ResponseEntity<List<AdvancedConfigAPIEntry>> response = configController.list();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void list_fiveArgs() {
        ResponseEntity<List<AdvancedConfigAPIEntry>> response = configController.list(".*", true, true, true, true);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    @SuppressWarnings("deprecation")
    void list_fourArgs() {
        ResponseEntity<List<AdvancedConfigAPIEntry>> response = configController.list(".*", true, true, true);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void listEnum() {
        ResponseEntity<List<EnumOption>> response = configController.listEnum("org.jdownloader.controlling.Priority");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void query() {
        ResponseEntity<List<AdvancedConfigAPIEntry>> response = configController.query(Json.stringify(new AdvancedConfigQuery()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void reset() {
        ResponseEntity<Boolean> response = configController.reset("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void set() {
        ResponseEntity<Boolean> response = configController.set("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder", "\"/output\"");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

