package com.lostmypillow.jd2.toolbar;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LinkCheckResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ToolbarControllerUnitTest {

    @Mock
    private ToolbarClient toolbarClient;

    @InjectMocks
    private ToolbarController toolbarController;

    @Test
    void addLinksFromDOM_returnsObject() {
        when(toolbarClient.addLinksFromDOM()).thenReturn(new Jd2Response<>("added"));

        ResponseEntity<Object> response = toolbarController.addLinksFromDOM();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("added", response.getBody());
        verify(toolbarClient).addLinksFromDOM();
    }

    @Test
    void checkLinksFromDOM_returnsObject() {
        when(toolbarClient.checkLinksFromDOM()).thenReturn(new Jd2Response<>("check-1"));

        ResponseEntity<Object> response = toolbarController.checkLinksFromDOM();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("check-1", response.getBody());
        verify(toolbarClient).checkLinksFromDOM();
    }

    @Test
    void getStatus_returnsObject() {
        when(toolbarClient.getStatus()).thenReturn(new Jd2Response<>("idle"));

        ResponseEntity<Object> response = toolbarController.getStatus();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("idle", response.getBody());
        verify(toolbarClient).getStatus();
    }

    @Test
    void isAvailable_returnsBoolean() {
        when(toolbarClient.isAvailable()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.isAvailable();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).isAvailable();
    }

    @Test
    void pollCheckedLinksFromDOM_returnsLinkCheckResult() {
        LinkCheckResult expected = new LinkCheckResult(List.of(), LinkCheckResult.STATUS.COMPLETED);
        when(toolbarClient.pollCheckedLinksFromDOM("check-1")).thenReturn(new Jd2Response<>(expected));

        ResponseEntity<LinkCheckResult> response = toolbarController.pollCheckedLinksFromDOM("check-1");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(toolbarClient).pollCheckedLinksFromDOM("check-1");
    }

    @Test
    void specialURLHandling_returnsString() {
        when(toolbarClient.specialURLHandling("https://example.com")).thenReturn(new Jd2Response<>("handled"));

        ResponseEntity<String> response = toolbarController.specialURLHandling("https://example.com");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("handled", response.getBody());
        verify(toolbarClient).specialURLHandling("https://example.com");
    }

    @Test
    void startDownloads_returnsBoolean() {
        when(toolbarClient.startDownloads()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.startDownloads();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).startDownloads();
    }

    @Test
    void stopDownloads_returnsBoolean() {
        when(toolbarClient.stopDownloads()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.stopDownloads();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).stopDownloads();
    }

    @Test
    void toggleAutomaticReconnect_returnsBoolean() {
        when(toolbarClient.toggleAutomaticReconnect()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.toggleAutomaticReconnect();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).toggleAutomaticReconnect();
    }

    @Test
    void toggleClipboardMonitoring_returnsBoolean() {
        when(toolbarClient.toggleClipboardMonitoring()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.toggleClipboardMonitoring();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).toggleClipboardMonitoring();
    }

    @Test
    void toggleDownloadSpeedLimit_returnsBoolean() {
        when(toolbarClient.toggleDownloadSpeedLimit()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.toggleDownloadSpeedLimit();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).toggleDownloadSpeedLimit();
    }

    @Test
    void togglePauseDownloads_returnsBoolean() {
        when(toolbarClient.togglePauseDownloads()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.togglePauseDownloads();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).togglePauseDownloads();
    }

    @Test
    void togglePremium_returnsBoolean() {
        when(toolbarClient.togglePremium()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.togglePremium();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).togglePremium();
    }

    @Test
    void toggleStopAfterCurrentDownload_returnsBoolean() {
        when(toolbarClient.toggleStopAfterCurrentDownload()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.toggleStopAfterCurrentDownload();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).toggleStopAfterCurrentDownload();
    }

    @Test
    void triggerUpdate_returnsBoolean() {
        when(toolbarClient.triggerUpdate()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = toolbarController.triggerUpdate();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(toolbarClient).triggerUpdate();
    }
}

