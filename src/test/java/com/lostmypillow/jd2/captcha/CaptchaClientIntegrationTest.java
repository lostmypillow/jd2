package com.lostmypillow.jd2.captcha;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.SkipRequest;
import com.lostmypillow.jd2.structure.CaptchaJob;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaptchaClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CaptchaClient captchaClient;

    @Test
    void get_byId() {
        assertDoesNotThrow(() -> captchaClient.get(1L));
    }

    @Test
    void get_byIdAndFormat() {
        assertDoesNotThrow(() -> captchaClient.get(1L, "png"));
    }

    @Test
    void getCaptchaJob() {
        Jd2Response<CaptchaJob> data = captchaClient.getCaptchaJob(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void list() {
        Jd2Response<List<CaptchaJob>> data = captchaClient.list();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void skip_withIdAndType() {
        Jd2Response<Boolean> data = captchaClient.skip(1L, SkipRequest.SINGLE);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    @SuppressWarnings("deprecation")
    void skip_withId() {
        Jd2Response<Boolean> data = captchaClient.skip(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void solve_withIdResultAndFormat() {
        Jd2Response<Boolean> data = captchaClient.solve(1L, "abcd", "STRING");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void solve_withIdAndResult() {
        Jd2Response<Boolean> data = captchaClient.solve(1L, "abcd");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

