package com.lostmypillow.jd2.reconnect;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReconnectControllerUnitTest {

    @Mock
    private ReconnectClient reconnectClient;

    @InjectMocks
    private ReconnectController reconnectController;

    @Test
    void doReconnect_returnsOk() {
        ResponseEntity<Void> response = reconnectController.doReconnect();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reconnectClient).doReconnect();
    }
}

