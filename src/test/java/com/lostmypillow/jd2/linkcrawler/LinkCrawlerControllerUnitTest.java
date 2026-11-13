package com.lostmypillow.jd2.linkcrawler;

import com.lostmypillow.jd2.structure.Jd2Response;
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
public class LinkCrawlerControllerUnitTest {

    @Mock
    private LinkCrawlerClient client;

    private LinkCrawlerController controller;

    @BeforeEach
    void setup() {
        controller = new LinkCrawlerController(client);
    }

    @Test
    void isCrawling_returnsBoolean() {
        when(client.isCrawling()).thenReturn(new Jd2Response<>(false));

        ResponseEntity<Boolean> response = controller.isCrawling();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody());
        verify(client).isCrawling();
    }
}

