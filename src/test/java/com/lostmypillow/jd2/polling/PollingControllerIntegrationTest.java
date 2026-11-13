package com.lostmypillow.jd2.polling;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.PollingResult;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PollingControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PollingController pollingController;

    @Test
    void testPoll() {
        ResponseEntity<List<PollingResult>> response = pollingController.poll(Json.stringify(new APIQuery<>()));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}

