package com.lostmypillow.jd2.downloadcontroller;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DownloadControllerClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DownloadControllerClient downloadControllerClient;

    @Test
    void forceDownload() {
        assertDoesNotThrow(() -> downloadControllerClient.forceDownload(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void getCurrentState() {
        Jd2Response<String> data = downloadControllerClient.getCurrentState();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(String.class, data.data(), "data.data() should be an instance of String");
    }

    @Test
    void getSpeedInBps() {
        Jd2Response<Integer> data = downloadControllerClient.getSpeedInBps();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
    }

    @Test
    void pause() {
        Jd2Response<Boolean> data = downloadControllerClient.pause(false);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void start() {
        Jd2Response<Boolean> data = downloadControllerClient.start();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void stop() {
        Jd2Response<Boolean> data = downloadControllerClient.stop();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

