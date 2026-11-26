package com.lostmypillow.jd2.device;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.JsonMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DeviceControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DeviceController deviceController;

    @Test
    void getDirectConnectionInfos() {
        ResponseEntity<JsonMap> response = deviceController.getDirectConnectionInfos();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(JsonMap.class, response.getBody());
    }

    @Test
    void getSessionPublicKey() {
        ResponseEntity<String> response = deviceController.getSessionPublicKey();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void ping() {
        ResponseEntity<Boolean> response = deviceController.ping();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

