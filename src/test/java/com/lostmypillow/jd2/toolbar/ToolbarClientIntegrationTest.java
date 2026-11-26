package com.lostmypillow.jd2.toolbar;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ToolbarClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ToolbarClient toolbarClient;

    @Test
    void testAddLinksFromDOM() {
        assertDoesNotThrow(() -> toolbarClient.addLinksFromDOM());
    }

    @Test
    void testCheckLinksFromDOM() {
        assertDoesNotThrow(() -> toolbarClient.checkLinksFromDOM());
    }

    @Test
    void testGetStatus() {
        Jd2Response<Object> response = toolbarClient.getStatus();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testIsAvailable() {
        Jd2Response<Boolean> response = toolbarClient.isAvailable();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testPollCheckedLinksFromDOM() {
        assertDoesNotThrow(() -> toolbarClient.pollCheckedLinksFromDOM("check-123"));
    }

    @Test
    void testSpecialURLHandling() {
        assertDoesNotThrow(() -> toolbarClient.specialURLHandling("https://example.com"));
    }

    @Test
    void testStartDownloads() {
        Jd2Response<Boolean> response = toolbarClient.startDownloads();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testStopDownloads() {
        Jd2Response<Boolean> response = toolbarClient.stopDownloads();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testToggleAutomaticReconnect() {
        Jd2Response<Boolean> response = toolbarClient.toggleAutomaticReconnect();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testToggleClipboardMonitoring() {
        Jd2Response<Boolean> response = toolbarClient.toggleClipboardMonitoring();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testToggleDownloadSpeedLimit() {
        Jd2Response<Boolean> response = toolbarClient.toggleDownloadSpeedLimit();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testTogglePauseDownloads() {
        Jd2Response<Boolean> response = toolbarClient.togglePauseDownloads();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testTogglePremium() {
        Jd2Response<Boolean> response = toolbarClient.togglePremium();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testToggleStopAfterCurrentDownload() {
        Jd2Response<Boolean> response = toolbarClient.toggleStopAfterCurrentDownload();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testTriggerUpdate() {
        Jd2Response<Boolean> response = toolbarClient.triggerUpdate();
        assertNotNull(response);
        assertNotNull(response.data());
    }
}

