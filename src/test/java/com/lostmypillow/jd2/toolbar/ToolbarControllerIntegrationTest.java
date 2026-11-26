package com.lostmypillow.jd2.toolbar;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ToolbarControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ToolbarController toolbarController;

    @Test
    void testAddLinksFromDOM() {
        assertDoesNotThrow(() -> toolbarController.addLinksFromDOM());
    }

    @Test
    void testCheckLinksFromDOM() {
        assertDoesNotThrow(() -> toolbarController.checkLinksFromDOM());
    }

    @Test
    void testGetStatus() {
        ResponseEntity<Object> response = toolbarController.getStatus();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testIsAvailable() {
        ResponseEntity<Boolean> response = toolbarController.isAvailable();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testPollCheckedLinksFromDOM() {
        assertDoesNotThrow(() -> toolbarController.pollCheckedLinksFromDOM("check-123"));
    }

    @Test
    void testSpecialURLHandling() {
        assertDoesNotThrow(() -> toolbarController.specialURLHandling("https://example.com"));
    }

    @Test
    void testStartDownloads() {
        ResponseEntity<Boolean> response = toolbarController.startDownloads();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testStopDownloads() {
        ResponseEntity<Boolean> response = toolbarController.stopDownloads();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testToggleAutomaticReconnect() {
        ResponseEntity<Boolean> response = toolbarController.toggleAutomaticReconnect();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testToggleClipboardMonitoring() {
        ResponseEntity<Boolean> response = toolbarController.toggleClipboardMonitoring();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testToggleDownloadSpeedLimit() {
        ResponseEntity<Boolean> response = toolbarController.toggleDownloadSpeedLimit();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testTogglePauseDownloads() {
        ResponseEntity<Boolean> response = toolbarController.togglePauseDownloads();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testTogglePremium() {
        ResponseEntity<Boolean> response = toolbarController.togglePremium();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testToggleStopAfterCurrentDownload() {
        ResponseEntity<Boolean> response = toolbarController.toggleStopAfterCurrentDownload();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testTriggerUpdate() {
        ResponseEntity<Boolean> response = toolbarController.triggerUpdate();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}

