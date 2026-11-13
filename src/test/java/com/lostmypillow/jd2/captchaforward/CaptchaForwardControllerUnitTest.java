package com.lostmypillow.jd2.captchaforward;

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
public class CaptchaForwardControllerUnitTest {

    @Mock
    private CaptchaForwardClient client;

    private CaptchaForwardController controller;

    @BeforeEach
    void setup() {
        controller = new CaptchaForwardController(client);
    }

    @Test
    void createJobRecaptchaV2_returnsJobId() {
        when(client.createJobRecaptchaV2("siteKey", "stoken", "example.com", "reason"))
                .thenReturn(new Jd2Response<>(98765L));

        ResponseEntity<Long> response = controller.createJobRecaptchaV2("siteKey", "stoken", "example.com", "reason");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(98765L, response.getBody());
        verify(client).createJobRecaptchaV2("siteKey", "stoken", "example.com", "reason");
    }

    @Test
    void getResult_returnsResultString() {
        when(client.getResult(98765L)).thenReturn(new Jd2Response<>("token-result"));

        ResponseEntity<String> response = controller.getResult(98765L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token-result", response.getBody());
        verify(client).getResult(98765L);
    }
}

