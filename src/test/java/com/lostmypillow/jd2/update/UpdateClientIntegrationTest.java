package com.lostmypillow.jd2.update;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UpdateClient updateClient;

    @Test
    void testIsUpdateAvailable() {
        Jd2Response<Boolean> response = updateClient.isUpdateAvailable();
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testRestartAndUpdate() {
        assertDoesNotThrow(() -> updateClient.restartAndUpdate());
    }

    @Test
    void testRunUpdateCheck() {
        assertDoesNotThrow(() -> updateClient.runUpdateCheck());
    }
}

