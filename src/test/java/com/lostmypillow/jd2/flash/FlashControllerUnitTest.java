package com.lostmypillow.jd2.flash;

import com.lostmypillow.jd2.structure.CnlQuery;
import com.lostmypillow.jd2.util.Json;
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
public class FlashControllerUnitTest {

    @Mock
    private FlashClient client;

    private FlashController controller;

    @BeforeEach
    void setup() {
        controller = new FlashController(client);
    }

    @Test
    void add_threeArgs_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.add("pass", "source", "https://example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).add("pass", "source", "https://example.com");
    }

    @Test
    void add_fourArgs_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.add("p1", "p2", "p3", "p4");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).add("p1", "p2", "p3", "p4");
    }

    @Test
    void add_noArgs_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.add();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).add();
    }

    @Test
    void addcnl_callsClientAndReturnsOk() {
        String cnl = Json.stringify(new CnlQuery());

        ResponseEntity<Void> response = controller.addcnl(cnl);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).addcnl(cnl);
    }

    @Test
    void addcrypted_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.addcrypted();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).addcrypted();
    }

    @Test
    void addcrypted2_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.addcrypted2();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).addcrypted2();
    }

    @Test
    void addcrypted2Remote_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.addcrypted2Remote("crypted", "jk", "k");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).addcrypted2Remote("crypted", "jk", "k");
    }

    @Test
    void flash_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.flash();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).flash();
    }
}

