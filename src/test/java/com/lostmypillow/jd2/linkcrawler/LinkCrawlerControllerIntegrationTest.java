package com.lostmypillow.jd2.linkcrawler;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class LinkCrawlerControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LinkCrawlerController linkCrawlerController;

    @Test
    void isCrawling() {
        ResponseEntity<Boolean> response = linkCrawlerController.isCrawling();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

