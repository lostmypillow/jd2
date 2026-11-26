package com.lostmypillow.jd2.root;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RootClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private RootClient rootClient;

    @Test
    void flash() {
        assertDoesNotThrow(() -> rootClient.flash());
    }

    @Test
    void crossdomainXml() {
        assertDoesNotThrow(() -> rootClient.crossdomainXml());
    }

    @Test
    void faviconIco() {
        assertDoesNotThrow(() -> rootClient.faviconIco());
    }

    @Test
    void flashgot() {
        assertDoesNotThrow(() -> rootClient.flashgot());
    }

    @Test
    void jdcheckJs() {
        assertDoesNotThrow(() -> rootClient.jdcheckJs());
    }

    @Test
    void jdcheckjson() {
        assertDoesNotThrow(() -> rootClient.jdcheckjson());
    }
}

