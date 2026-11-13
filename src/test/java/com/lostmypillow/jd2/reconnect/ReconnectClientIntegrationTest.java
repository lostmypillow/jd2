package com.lostmypillow.jd2.reconnect;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ReconnectClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ReconnectClient reconnectClient;

    @Test
    void testDoReconnect() {
        assertDoesNotThrow(() -> reconnectClient.doReconnect());
    }
}

