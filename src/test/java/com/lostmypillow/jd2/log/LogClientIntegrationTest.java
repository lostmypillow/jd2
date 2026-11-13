package com.lostmypillow.jd2.log;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LogFolder;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LogClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LogClient logClient;

    @Test
    void testGetAvailableLogs() {
        Jd2Response<List<LogFolder>> response = logClient.getAvailableLogs();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testSendLogFile() {
        assertDoesNotThrow(() -> logClient.sendLogFile(Json.stringify(new LogFolder[]{})));
    }
}

