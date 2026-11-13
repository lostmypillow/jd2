package com.lostmypillow.jd2.jd;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
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
        Jd2Response<Integer> data = jdClient.getCoreRevision();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
    }

}