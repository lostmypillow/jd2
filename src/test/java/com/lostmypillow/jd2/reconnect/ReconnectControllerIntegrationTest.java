package com.lostmypillow.jd2.reconnect;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReconnectControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ReconnectController reconnectController;

    @Test
    void testDoReconnect() {
        ResponseEntity<Void> response = reconnectController.doReconnect();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

