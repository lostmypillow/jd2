package com.lostmypillow.jd2.toolbar;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LinkCheckResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ToolbarClientUnitTest {
    private ToolbarClient toolbarClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        toolbarClient = httpServiceProxyFactory.createClient(ToolbarClient.class);
    }

    @Test
    void addLinksFromDOM_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "ok"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/addLinksFromDOM")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Object> response = toolbarClient.addLinksFromDOM();

        assertNotNull(response);
        assertEquals("ok", response.data());
        mockServer.verify();
    }

    @Test
    void checkLinksFromDOM_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "check-id-1"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/checkLinksFromDOM")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Object> response = toolbarClient.checkLinksFromDOM();

        assertNotNull(response);
        assertEquals("check-id-1", response.data());
        mockServer.verify();
    }

    @Test
    void getStatus_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "running"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/getStatus")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Object> response = toolbarClient.getStatus();

        assertNotNull(response);
        assertEquals("running", response.data());
        mockServer.verify();
    }

    @Test
    void isAvailable_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/isAvailable")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.isAvailable();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void pollCheckedLinksFromDOM_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "status": "COMPLETED",
                        "links": []
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/pollCheckedLinksFromDOM")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<LinkCheckResult> response = toolbarClient.pollCheckedLinksFromDOM("check-123");

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(LinkCheckResult.STATUS.COMPLETED, response.data().status());
        mockServer.verify();
    }

    @Test
    void specialURLHandling_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "handled"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/specialURLHandling")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<String> response = toolbarClient.specialURLHandling("https://example.com");

        assertNotNull(response);
        assertEquals("handled", response.data());
        mockServer.verify();
    }

    @Test
    void startDownloads_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/startDownloads")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.startDownloads();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void stopDownloads_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/stopDownloads")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.stopDownloads();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void toggleAutomaticReconnect_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/toggleAutomaticReconnect")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.toggleAutomaticReconnect();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void toggleClipboardMonitoring_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/toggleClipboardMonitoring")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.toggleClipboardMonitoring();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void toggleDownloadSpeedLimit_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/toggleDownloadSpeedLimit")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.toggleDownloadSpeedLimit();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void togglePauseDownloads_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/togglePauseDownloads")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.togglePauseDownloads();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void togglePremium_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/togglePremium")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.togglePremium();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void toggleStopAfterCurrentDownload_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/toggleStopAfterCurrentDownload")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.toggleStopAfterCurrentDownload();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void triggerUpdate_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/toolbar/triggerUpdate")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = toolbarClient.triggerUpdate();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

