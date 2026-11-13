package com.lostmypillow.jd2.downloadevents;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.DownloadListDiff;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LinkQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DownloadEventsClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DownloadEventsClient downloadEventsClient;

    @Test
    void queryLinks() {
        Jd2Response<DownloadListDiff> data = downloadEventsClient.queryLinks(
                Json.stringify(new LinkQuery()),
                0
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(DownloadListDiff.class, data.data(), "data.data() should be an instance of DownloadListDiff");
    }

    @Test
    void setStatusEventInterval() {
        Jd2Response<Boolean> data = downloadEventsClient.setStatusEventInterval(1L, 1000L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

