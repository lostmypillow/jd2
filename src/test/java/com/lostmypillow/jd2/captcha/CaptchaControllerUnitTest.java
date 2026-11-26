package com.lostmypillow.jd2.captcha;

import com.lostmypillow.jd2.constants.SkipRequest;
import com.lostmypillow.jd2.structure.CaptchaJob;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CaptchaControllerUnitTest {

    @Mock
    private CaptchaClient client;

    private CaptchaController controller;

    @BeforeEach
    void setup() {
        controller = new CaptchaController(client);
    }

    @Test
    void get_byId_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.get(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).get(100L);
    }

    @Test
    void get_byIdAndFormat_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.get(100L, "png");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).get(100L, "png");
    }

    @Test
    void getCaptchaJob_returnsCaptchaJob() {
        CaptchaJob job = new CaptchaJob("cat", 1L, "explain", "example.com", 100L, 200L, 60, "IMAGE");
        when(client.getCaptchaJob(100L)).thenReturn(new Jd2Response<>(job));

        ResponseEntity<CaptchaJob> response = controller.getCaptchaJob(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(job, response.getBody());
        verify(client).getCaptchaJob(100L);
    }

    @Test
    void list_returnsCaptchaJobs() {
        List<CaptchaJob> jobs = List.of(
                new CaptchaJob("cat", 1L, "explain", "example.com", 100L, 200L, 60, "IMAGE")
        );
        when(client.list()).thenReturn(new Jd2Response<>(jobs));

        ResponseEntity<List<CaptchaJob>> response = controller.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobs, response.getBody());
        verify(client).list();
    }

    @Test
    void skip_withIdAndType_returnsBoolean() {
        when(client.skip(100L, SkipRequest.SINGLE)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.skip(100L, SkipRequest.SINGLE);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).skip(100L, SkipRequest.SINGLE);
    }

    @Test
    @SuppressWarnings("deprecation")
    void skip_withId_returnsBoolean() {
        when(client.skip(100L)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.skip(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).skip(100L);
    }

    @Test
    void solve_withIdResultAndFormat_returnsBoolean() {
        when(client.solve(100L, "abcd", "STRING")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.solve(100L, "abcd", "STRING");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).solve(100L, "abcd", "STRING");
    }

    @Test
    void solve_withIdAndResult_returnsBoolean() {
        when(client.solve(100L, "abcd")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.solve(100L, "abcd");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).solve(100L, "abcd");
    }
}

