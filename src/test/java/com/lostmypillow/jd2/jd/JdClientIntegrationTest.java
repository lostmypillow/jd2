package com.lostmypillow.jd2.jd;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.jd.records.JdApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class JdClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private JdClient jdClient;
    @Autowired
    private ConfigurableEnvironment env;

    @Test
    void testAgainstRealJd() {
        JdApiResponse<Integer> data = jdClient.getCoreRevision();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(JdApiResponse.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
    }

}