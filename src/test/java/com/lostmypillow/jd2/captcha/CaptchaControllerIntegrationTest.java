package com.lostmypillow.jd2.captcha;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.SkipRequest;
import com.lostmypillow.jd2.structure.CaptchaJob;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaptchaControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CaptchaController captchaController;

    @Test
    void get_byId() {
        ResponseEntity<Void> response = captchaController.get(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void get_byIdAndFormat() {
        ResponseEntity<Void> response = captchaController.get(1L, "png");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getCaptchaJob() {
        ResponseEntity<CaptchaJob> response = captchaController.getCaptchaJob(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void list() {
        ResponseEntity<List<CaptchaJob>> response = captchaController.list();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void skip_withIdAndType() {
        ResponseEntity<Boolean> response = captchaController.skip(1L, SkipRequest.SINGLE);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    @SuppressWarnings("deprecation")
    void skip_withId() {
        ResponseEntity<Boolean> response = captchaController.skip(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void solve_withIdResultAndFormat() {
        ResponseEntity<Boolean> response = captchaController.solve(1L, "abcd", "STRING");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void solve_withIdAndResult() {
        ResponseEntity<Boolean> response = captchaController.solve(1L, "abcd");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

