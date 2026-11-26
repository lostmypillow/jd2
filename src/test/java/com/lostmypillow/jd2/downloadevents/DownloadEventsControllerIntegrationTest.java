package com.lostmypillow.jd2.downloadevents;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.DownloadListDiff;
import com.lostmypillow.jd2.structure.LinkQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DownloadEventsControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DownloadEventsController downloadEventsController;

    @Test
    void queryLinks() {
        ResponseEntity<DownloadListDiff> response = downloadEventsController.queryLinks(
                Json.stringify(new LinkQuery()),
                0
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(DownloadListDiff.class, response.getBody());
    }

    @Test
    void setStatusEventInterval() {
        ResponseEntity<Boolean> response = downloadEventsController.setStatusEventInterval(1L, 1000L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

