package com.lostmypillow.jd2.downloadcontroller;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DownloadControllerControllerUnitTest {

    @Mock
    private DownloadControllerClient client;

    private DownloadControllerController controller;

    @BeforeEach
    void setup() {
        controller = new DownloadControllerController(client);
    }

    @Test
    void forceDownload_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.forceDownload(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).forceDownload(linkIds, packageIds);
    }

    @Test
    void getCurrentState_returnsState() {
        when(client.getCurrentState()).thenReturn(new Jd2Response<>("IDLE"));

        ResponseEntity<String> response = controller.getCurrentState();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("IDLE", response.getBody());
        verify(client).getCurrentState();
    }

    @Test
    void getSpeedInBps_returnsSpeed() {
        when(client.getSpeedInBps()).thenReturn(new Jd2Response<>(1024));

        ResponseEntity<Integer> response = controller.getSpeedInBps();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1024, response.getBody());
        verify(client).getSpeedInBps();
    }

    @Test
    void pause_returnsBoolean() {
        when(client.pause(true)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.pause(true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).pause(true);
    }

    @Test
    void start_returnsBoolean() {
        when(client.start()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.start();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).start();
    }

    @Test
    void stop_returnsBoolean() {
        when(client.stop()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.stop();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).stop();
    }
}

