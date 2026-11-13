package com.lostmypillow.jd2.session;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SessionClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private SessionClient sessionClient;

    @Test
    void testDisconnect() {
        Jd2Response<Boolean> response = sessionClient.disconnect();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testHandshake() {
        assertDoesNotThrow(() -> sessionClient.handshake("user", "password"));
    }
}

