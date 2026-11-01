package com.lostmypillow.jd2;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@SpringBootTest()
@ActiveProfiles(profiles = "local")
@Testcontainers
public class AbstractIntegrationTest {
    private static final String SERVICE_NAME = "jd2";
    private static final int SERVICE_PORT = 3128;

    @Container
    public static ComposeContainer environment = new ComposeContainer(
            new File("compose.yaml"))
            .withExposedService(
                    SERVICE_NAME,
                    SERVICE_PORT,
                    Wait.forHttp("/help").forStatusCode(200)
            );

}
