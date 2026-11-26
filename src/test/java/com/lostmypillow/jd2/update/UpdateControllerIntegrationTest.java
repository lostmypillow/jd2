package com.lostmypillow.jd2.update;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UpdateController updateController;

    @Test
    void testIsUpdateAvailable() {
        ResponseEntity<Boolean> response = updateController.isUpdateAvailable();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testRestartAndUpdate() {
        ResponseEntity<Void> response = updateController.restartAndUpdate();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRunUpdateCheck() {
        ResponseEntity<Void> response = updateController.runUpdateCheck();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

