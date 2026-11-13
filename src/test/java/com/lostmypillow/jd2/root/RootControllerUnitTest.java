package com.lostmypillow.jd2.root;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RootControllerUnitTest {

    @Mock
    private RootClient client;

    private RootController controller;

    @BeforeEach
    void setup() {
        controller = new RootController(client);
    }

    @Test
    void flash_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.flash();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).flash();
    }

    @Test
    void crossdomainXml_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.crossdomainXml();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).crossdomainXml();
    }

    @Test
    void faviconIco_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.faviconIco();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).faviconIco();
    }

    @Test
    void flashgot_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.flashgot();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).flashgot();
    }

    @Test
    void jdcheckJs_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.jdcheckJs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).jdcheckJs();
    }

    @Test
    void jdcheckjson_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.jdcheckjson();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).jdcheckjson();
    }
}

