package com.lostmypillow.jd2.log;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.LogFolder;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LogControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LogController logController;

    @Test
    void testGetAvailableLogs() {
        ResponseEntity<List<LogFolder>> response = logController.getAvailableLogs();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testSendLogFile() {
        assertDoesNotThrow(() -> logController.sendLogFile(Json.stringify(new LogFolder[]{})));
    }
}

