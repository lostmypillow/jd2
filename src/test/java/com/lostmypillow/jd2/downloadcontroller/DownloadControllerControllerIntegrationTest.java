package com.lostmypillow.jd2.downloadcontroller;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DownloadControllerControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DownloadControllerController downloadControllerController;

    @Test
    void forceDownload() {
        ResponseEntity<Void> response = downloadControllerController.forceDownload(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getCurrentState() {
        ResponseEntity<String> response = downloadControllerController.getCurrentState();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(String.class, response.getBody());
    }

    @Test
    void getSpeedInBps() {
        ResponseEntity<Integer> response = downloadControllerController.getSpeedInBps();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Integer.class, response.getBody());
    }

    @Test
    void pause() {
        ResponseEntity<Boolean> response = downloadControllerController.pause(false);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void start() {
        ResponseEntity<Boolean> response = downloadControllerController.start();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void stop() {
        ResponseEntity<Boolean> response = downloadControllerController.stop();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

