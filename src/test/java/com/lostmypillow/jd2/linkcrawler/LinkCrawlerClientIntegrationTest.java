package com.lostmypillow.jd2.linkcrawler;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LinkCrawlerClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LinkCrawlerClient linkCrawlerClient;

    @Test
    void isCrawling() {
        Jd2Response<Boolean> data = linkCrawlerClient.isCrawling();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

