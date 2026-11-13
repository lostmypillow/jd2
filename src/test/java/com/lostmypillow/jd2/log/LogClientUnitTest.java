package com.lostmypillow.jd2.log;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LogFolder;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class LogClientUnitTest {
    private LogClient logClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        logClient = httpServiceProxyFactory.createClient(LogClient.class);
    }

    @Test
    void getAvailableLogs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "created": 1000,
                            "current": true,
                            "lastModified": 2000
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/log/getAvailableLogs")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<LogFolder>> response = logClient.getAvailableLogs();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals(1000L, response.data().getFirst().created());
        assertEquals(true, response.data().getFirst().current());
        assertEquals(2000L, response.data().getFirst().lastModified());
        mockServer.verify();
    }

    @Test
    void sendLogFile_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "log-id-123"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/log/sendLogFile")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<String> response = logClient.sendLogFile(Json.stringify(new LogFolder[]{new LogFolder()}));

        assertNotNull(response);
        assertEquals("log-id-123", response.data());
        mockServer.verify();
    }
}

