package com.lostmypillow.jd2.device;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceControllerUnitTest {

    @Mock
    private DeviceClient client;

    private DeviceController controller;

    @BeforeEach
    void setup() {
        controller = new DeviceController(client);
    }

    @Test
    void getDirectConnectionInfos_returnsJsonMap() {
        JsonMap infos = new JsonMap(Map.of("mode", "LAN"));
        when(client.getDirectConnectionInfos()).thenReturn(new Jd2Response<>(infos));

        ResponseEntity<JsonMap> response = controller.getDirectConnectionInfos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(infos, response.getBody());
        verify(client).getDirectConnectionInfos();
    }

    @Test
    void getSessionPublicKey_returnsPublicKey() {
        when(client.getSessionPublicKey()).thenReturn(new Jd2Response<>("public-key-value"));

        ResponseEntity<String> response = controller.getSessionPublicKey();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("public-key-value", response.getBody());
        verify(client).getSessionPublicKey();
    }

    @Test
    void ping_returnsBoolean() {
        when(client.ping()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.ping();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).ping();
    }
}

