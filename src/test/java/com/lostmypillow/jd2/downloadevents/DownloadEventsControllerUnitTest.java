package com.lostmypillow.jd2.downloadevents;

import com.lostmypillow.jd2.structure.DownloadListDiff;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LinkQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DownloadEventsControllerUnitTest {

    @Mock
    private DownloadEventsClient client;

    private DownloadEventsController controller;

    @BeforeEach
    void setup() {
        controller = new DownloadEventsController(client);
    }

    @Test
    void queryLinks_returnsDownloadListDiff() {
        String queryParams = Json.stringify(new LinkQuery());
        DownloadListDiff diff = new DownloadListDiff();
        when(client.queryLinks(queryParams, 1)).thenReturn(new Jd2Response<>(diff));

        ResponseEntity<DownloadListDiff> response = controller.queryLinks(queryParams, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(diff, response.getBody());
        verify(client).queryLinks(queryParams, 1);
    }

    @Test
    void setStatusEventInterval_returnsBoolean() {
        when(client.setStatusEventInterval(1L, 1000L)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.setStatusEventInterval(1L, 1000L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).setStatusEventInterval(1L, 1000L);
    }
}

