package com.lostmypillow.jd2.flash;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.CnlQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FlashControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private FlashController flashController;

    @Test
    void add_threeArgs() {
        ResponseEntity<Void> response = flashController.add("pass", "source", "https://example.com");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void add_fourArgs() {
        ResponseEntity<Void> response = flashController.add("p1", "p2", "p3", "p4");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void add_noArgs() {
        ResponseEntity<Void> response = flashController.add();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addcnl() {
        ResponseEntity<Void> response = flashController.addcnl(Json.stringify(new CnlQuery()));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addcrypted() {
        ResponseEntity<Void> response = flashController.addcrypted();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addcrypted2() {
        ResponseEntity<Void> response = flashController.addcrypted2();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addcrypted2Remote() {
        ResponseEntity<Void> response = flashController.addcrypted2Remote("crypted", "jk", "k");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void flash() {
        ResponseEntity<Void> response = flashController.flash();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

