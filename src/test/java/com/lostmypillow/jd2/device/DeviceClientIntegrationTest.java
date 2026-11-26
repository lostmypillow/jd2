package com.lostmypillow.jd2.device;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeviceClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DeviceClient deviceClient;

    @Test
    void getDirectConnectionInfos() {
        Jd2Response<JsonMap> data = deviceClient.getDirectConnectionInfos();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(JsonMap.class, data.data(), "data.data() should be an instance of JsonMap");
    }

    @Test
    void getSessionPublicKey() {
        Jd2Response<String> data = deviceClient.getSessionPublicKey();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void ping() {
        Jd2Response<Boolean> data = deviceClient.ping();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

