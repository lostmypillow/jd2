package com.lostmypillow.jd2.flash;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.CnlQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FlashClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private FlashClient flashClient;

    @Test
    void add_threeArgs() {
        assertDoesNotThrow(() -> flashClient.add("pass", "source", "https://example.com"));
    }

    @Test
    void add_fourArgs() {
        assertDoesNotThrow(() -> flashClient.add("p1", "p2", "p3", "p4"));
    }

    @Test
    void add_noArgs() {
        assertDoesNotThrow(() -> flashClient.add());
    }

    @Test
    void addcnl() {
        assertDoesNotThrow(() -> flashClient.addcnl(Json.stringify(new CnlQuery())));
    }

    @Test
    void addcrypted() {
        assertDoesNotThrow(() -> flashClient.addcrypted());
    }

    @Test
    void addcrypted2() {
        assertDoesNotThrow(() -> flashClient.addcrypted2());
    }

    @Test
    void addcrypted2Remote() {
        assertDoesNotThrow(() -> flashClient.addcrypted2Remote("crypted", "jk", "k"));
    }

    @Test
    void flash() {
        assertDoesNotThrow(() -> flashClient.flash());
    }
}

