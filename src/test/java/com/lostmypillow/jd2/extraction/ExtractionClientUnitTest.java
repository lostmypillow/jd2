package com.lostmypillow.jd2.extraction;

import com.lostmypillow.jd2.structure.ArchiveSettings;
import com.lostmypillow.jd2.structure.ArchiveStatus;
import com.lostmypillow.jd2.structure.ControllerStatus;
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

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ExtractionClientUnitTest {
    private ExtractionClient extractionClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        extractionClient = httpServiceProxyFactory.createClient(ExtractionClient.class);
    }

    @Test
    void addArchivePassword_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/extraction/addArchivePassword")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> extractionClient.addArchivePassword("secret"));
        mockServer.verify();
    }

    @Test
    void cancelExtraction_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/extraction/cancelExtraction")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = extractionClient.cancelExtraction(100L);

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void getArchiveInfo_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "archiveId": "arch1",
                            "archiveName": "archive.rar",
                            "controllerId": 100,
                            "controllerStatus": "RUNNING"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/extraction/getArchiveInfo")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<ArchiveStatus>> response = extractionClient.getArchiveInfo(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("arch1", response.data().getFirst().archiveId());
        assertEquals(ControllerStatus.RUNNING, response.data().getFirst().controllerStatus());
        mockServer.verify();
    }

    @Test
    void getArchiveSettings_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "archiveId": "arch1",
                            "autoExtract": true
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/extraction/getArchiveSettings")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<ArchiveSettings>> response = extractionClient.getArchiveSettings(
                Json.stringify(new String[]{"arch1"})
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("arch1", response.data().getFirst().archiveId());
        assertEquals(true, response.data().getFirst().autoExtract());
        mockServer.verify();
    }

    @Test
    void getQueue_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "archiveId": "arch1",
                            "archiveName": "archive.rar",
                            "controllerId": 100,
                            "controllerStatus": "QUEUED"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/extraction/getQueue"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<ArchiveStatus>> response = extractionClient.getQueue();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("arch1", response.data().getFirst().archiveId());
        assertEquals(ControllerStatus.QUEUED, response.data().getFirst().controllerStatus());
        mockServer.verify();
    }

    @Test
    void setArchiveSettings_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/extraction/setArchiveSettings")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = extractionClient.setArchiveSettings(
                "arch1",
                Json.stringify(new ArchiveSettings())
        );

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void startExtractionNow_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "arch1": true
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/extraction/startExtractionNow")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Map<String, Boolean>> response = extractionClient.startExtractionNow(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(true, response.data().get("arch1"));
        mockServer.verify();
    }
}

