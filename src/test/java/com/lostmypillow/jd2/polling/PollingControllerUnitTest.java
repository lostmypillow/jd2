package com.lostmypillow.jd2.polling;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
import com.lostmypillow.jd2.structure.PollingResult;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PollingControllerUnitTest {

    @Mock
    private PollingClient pollingClient;

    @InjectMocks
    private PollingController pollingController;

    @Test
    void poll_returnsPollingResults() {
        String queryParamsJson = Json.stringify(new APIQuery<>());
        List<PollingResult> expected = List.of(new PollingResult(new JsonMap(Map.of("state", "IDLE")), "downloadControllerState"));
        when(pollingClient.poll(queryParamsJson)).thenReturn(new Jd2Response<>(expected));

        ResponseEntity<List<PollingResult>> response = pollingController.poll(queryParamsJson);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(pollingClient).poll(queryParamsJson);
    }
}

