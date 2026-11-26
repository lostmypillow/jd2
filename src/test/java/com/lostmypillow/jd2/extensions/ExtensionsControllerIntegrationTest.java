package com.lostmypillow.jd2.extensions;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Extension;
import com.lostmypillow.jd2.structure.ExtensionQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExtensionsControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ExtensionsController extensionsController;

    @Test
    void install() {
        ResponseEntity<Boolean> response = extensionsController.install("extraction");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void isEnabled() {
        ResponseEntity<Boolean> response = extensionsController.isEnabled("org.jdownloader.extensions.extraction.ExtractionExtension");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void isInstalled() {
        ResponseEntity<Boolean> response = extensionsController.isInstalled("extraction");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void list() {
        ResponseEntity<List<Extension>> response = extensionsController.list(Json.stringify(new ExtensionQuery()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void setEnabled() {
        ResponseEntity<Boolean> response = extensionsController.setEnabled("org.jdownloader.extensions.extraction.ExtractionExtension", true);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

