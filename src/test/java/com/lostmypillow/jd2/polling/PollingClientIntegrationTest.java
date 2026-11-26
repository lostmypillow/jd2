package com.lostmypillow.jd2.polling;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.PollingResult;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PollingClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PollingClient pollingClient;

    @Test
    void testPoll() {
        Jd2Response<List<PollingResult>> response = pollingClient.poll(Json.stringify(new APIQuery<>()));
        assertNotNull(response);
        assertNotNull(response.data());
    }
}

