package com.lostmypillow.jd2.downloadcontroller;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class DownloadControllerClientUnitTest {
    private DownloadControllerClient downloadControllerClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        downloadControllerClient = httpServiceProxyFactory.createClient(DownloadControllerClient.class);
    }

    @Test
    void forceDownload_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadcontroller/forceDownload")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadControllerClient.forceDownload(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void getCurrentState_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "IDLE"
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/downloadcontroller/getCurrentState"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<String> response = downloadControllerClient.getCurrentState();

        assertNotNull(response);
        assertEquals("IDLE", response.data());
        mockServer.verify();
    }

    @Test
    void getSpeedInBps_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 1024
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/downloadcontroller/getSpeedInBps"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Integer> response = downloadControllerClient.getSpeedInBps();

        assertNotNull(response);
        assertEquals(1024, response.data());
        mockServer.verify();
    }

    @Test
    void pause_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadcontroller/pause")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = downloadControllerClient.pause(true);

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void start_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/downloadcontroller/start"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = downloadControllerClient.start();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void stop_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/downloadcontroller/stop"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = downloadControllerClient.stop();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

