package com.lostmypillow.jd2.captchaforward;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class CaptchaForwardControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CaptchaForwardController captchaForwardController;

    @Test
    void createJobRecaptchaV2() {
        ResponseEntity<Long> response = captchaForwardController.createJobRecaptchaV2("siteKey", "stoken", "example.com", "reason");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Long.class, response.getBody());
    }

    @Test
    void getResult() {
        ResponseEntity<String> response = captchaForwardController.getResult(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

