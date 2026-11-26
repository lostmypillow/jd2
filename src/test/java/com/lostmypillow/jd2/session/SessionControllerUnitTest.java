package com.lostmypillow.jd2.session;

import com.lostmypillow.jd2.structure.Jd2Response;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionControllerUnitTest {

    @Mock
    private SessionClient sessionClient;

    @InjectMocks
    private SessionController sessionController;

    @Test
    void disconnect_returnsBoolean() {
        when(sessionClient.disconnect()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = sessionController.disconnect();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(sessionClient).disconnect();
    }

    @Test
    void handshake_returnsToken() {
        when(sessionClient.handshake("user", "password")).thenReturn(new Jd2Response<>("token-abc-123"));

        ResponseEntity<String> response = sessionController.handshake("user", "password");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token-abc-123", response.getBody());
        verify(sessionClient).handshake("user", "password");
    }
}

