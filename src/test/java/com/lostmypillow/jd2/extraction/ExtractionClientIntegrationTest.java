package com.lostmypillow.jd2.extraction;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.ArchiveSettings;
import com.lostmypillow.jd2.structure.ArchiveStatus;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExtractionClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ExtractionClient extractionClient;

    @Test
    void addArchivePassword() {
        assertDoesNotThrow(() -> extractionClient.addArchivePassword("secret"));
    }

    @Test
    void cancelExtraction() {
        Jd2Response<Boolean> data = extractionClient.cancelExtraction(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void getArchiveInfo() {
        Jd2Response<List<ArchiveStatus>> data = extractionClient.getArchiveInfo(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void getArchiveSettings() {
        Jd2Response<List<ArchiveSettings>> data = extractionClient.getArchiveSettings(
                Json.stringify(new String[]{})
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void getQueue() {
        Jd2Response<List<ArchiveStatus>> data = extractionClient.getQueue();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void setArchiveSettings() {
        Jd2Response<Boolean> data = extractionClient.setArchiveSettings(
                "arch1",
                Json.stringify(new ArchiveSettings())
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void startExtractionNow() {
        Jd2Response<Map<String, Boolean>> data = extractionClient.startExtractionNow(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Map.class, data.data(), "data.data() should be an instance of Map");
    }
}

