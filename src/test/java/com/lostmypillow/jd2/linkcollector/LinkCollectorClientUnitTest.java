package com.lostmypillow.jd2.linkcollector;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.CrawledLink;
import com.lostmypillow.jd2.structure.CrawledPackage;
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

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class LinkCollectorClientUnitTest {
    private LinkCollectorClient linkCollectorClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        linkCollectorClient = httpServiceProxyFactory.createClient(LinkCollectorClient.class);
    }

    @Test
    void addContainer_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/addContainer")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkCollectorClient.addContainer("DLC", "content"));
        mockServer.verify();
    }

    @Test
    void addLinks_fourArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/addLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.addLinks("https://example.com", "pkg", "archPass", "linkPass");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void addLinks_fiveArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/addLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.addLinks("https://example.com", "pkg", "extPass", "dlPass", "/downloads");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void addLinksAndStartDownload_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/addLinksAndStartDownload")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.addLinksAndStartDownload("https://example.com", "pkg", "extPass", "dlPass");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void disableLinks_twoArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/disableLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.disableLinks(
                Json.stringify(List.of(1L)),
                Json.stringify(List.of(2L))
        );

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void disableLinks_oneArg_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/disableLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.disableLinks(Json.stringify(List.of(1L)));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void enableLinks_twoArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/enableLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.enableLinks(
                Json.stringify(List.of(1L)),
                Json.stringify(List.of(2L))
        );

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void enableLinks_oneArg_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/enableLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.enableLinks(Json.stringify(List.of(1L)));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void getChildrenChanged_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 555
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/getChildrenChanged")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Long> response = linkCollectorClient.getChildrenChanged(100L);

        assertNotNull(response);
        assertEquals(555L, response.data());
        mockServer.verify();
    }

    @Test
    void getDownloadFolderHistorySelectionBase_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": ["/downloads", "/tmp"]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/linkcollector/getDownloadFolderHistorySelectionBase"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<String>> response = linkCollectorClient.getDownloadFolderHistorySelectionBase();

        assertNotNull(response);
        assertEquals(List.of("/downloads", "/tmp"), response.data());
        mockServer.verify();
    }

    @Test
    void moveLinks_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/moveLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.moveLinks(Json.stringify(new APIQuery<>()));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void movePackages_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/movePackages")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.movePackages(Json.stringify(new APIQuery<>()));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void packageCount_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 3
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/linkcollector/packageCount"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Integer> response = linkCollectorClient.packageCount();

        assertNotNull(response);
        assertEquals(3, response.data());
        mockServer.verify();
    }

    @Test
    void queryLinks_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "name": "example.zip",
                            "uuid": 111,
                            "packageUUID": 222
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/queryLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<CrawledLink>> response = linkCollectorClient.queryLinks(Json.stringify(new APIQuery<>()));

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("example.zip", response.data().getFirst().name());
        assertEquals(111L, response.data().getFirst().uuid());
        mockServer.verify();
    }

    @Test
    void queryPackages_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "name": "pkg1",
                            "uuid": 222
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/queryPackages")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<CrawledPackage>> response = linkCollectorClient.queryPackages(Json.stringify(new APIQuery<>()));

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("pkg1", response.data().getFirst().name());
        assertEquals(222L, response.data().getFirst().uuid());
        mockServer.verify();
    }

    @Test
    void removeLinks_twoArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/removeLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.removeLinks(
                Json.stringify(List.of(2L)),
                Json.stringify(List.of(1L))
        );

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void removeLinks_oneArg_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/removeLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.removeLinks(Json.stringify(List.of(1L)));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void renameLink_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/renameLink")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.renameLink(2L, 1L, "newLinkName");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void renamePackage_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/renamePackage")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.renamePackage(2L, "newPkgName");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void startDownloads_oneArg_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/startDownloads")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.startDownloads(Json.stringify(List.of(1L)));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void startDownloads_twoArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkcollector/startDownloads")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkCollectorClient.startDownloads(
                Json.stringify(List.of(1L)),
                Json.stringify(List.of(2L))
        );

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

