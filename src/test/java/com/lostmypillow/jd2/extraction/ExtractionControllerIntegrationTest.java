package com.lostmypillow.jd2.extraction;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.ArchiveSettings;
import com.lostmypillow.jd2.structure.ArchiveStatus;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExtractionControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ExtractionController extractionController;

    @Test
    void addArchivePassword() {
        ResponseEntity<Void> response = extractionController.addArchivePassword("secret");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void cancelExtraction() {
        ResponseEntity<Boolean> response = extractionController.cancelExtraction(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void getArchiveInfo() {
        ResponseEntity<List<ArchiveStatus>> response = extractionController.getArchiveInfo(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void getArchiveSettings() {
        ResponseEntity<List<ArchiveSettings>> response = extractionController.getArchiveSettings(
                Json.stringify(new String[]{})
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void getQueue() {
        ResponseEntity<List<ArchiveStatus>> response = extractionController.getQueue();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void setArchiveSettings() {
        ResponseEntity<Boolean> response = extractionController.setArchiveSettings(
                "arch1",
                Json.stringify(new ArchiveSettings())
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void startExtractionNow() {
        ResponseEntity<Map<String, Boolean>> response = extractionController.startExtractionNow(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());
    }
}

