package com.lostmypillow.jd2.root;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RootControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private RootController rootController;

    @Test
    void flash() {
        ResponseEntity<Void> response = rootController.flash();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void crossdomainXml() {
        ResponseEntity<Void> response = rootController.crossdomainXml();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void faviconIco() {
        ResponseEntity<Void> response = rootController.faviconIco();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void flashgot() {
        ResponseEntity<Void> response = rootController.flashgot();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void jdcheckJs() {
        ResponseEntity<Void> response = rootController.jdcheckJs();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void jdcheckjson() {
        ResponseEntity<Void> response = rootController.jdcheckjson();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

