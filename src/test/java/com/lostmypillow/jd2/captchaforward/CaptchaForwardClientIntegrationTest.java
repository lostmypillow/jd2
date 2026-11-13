package com.lostmypillow.jd2.captchaforward;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CaptchaForwardClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CaptchaForwardClient captchaForwardClient;

    @Test
    void createJobRecaptchaV2() {
        Jd2Response<Long> data = captchaForwardClient.createJobRecaptchaV2("siteKey", "stoken", "example.com", "reason");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Long.class, data.data(), "data.data() should be an instance of Long");
    }

    @Test
    void getResult() {
        Jd2Response<String> data = captchaForwardClient.getResult(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }
}

