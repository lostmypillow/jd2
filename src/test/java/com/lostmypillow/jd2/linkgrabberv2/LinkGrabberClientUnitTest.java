package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
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

public class LinkGrabberClientUnitTest {
    private LinkGrabberClient linkGrabberClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        linkGrabberClient = httpServiceProxyFactory.createClient(LinkGrabberClient.class);
    }

    @Test
    void addLinks_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "id": 12345
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/addLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<LinkCollectingJob> response = linkGrabberClient.addLinks(
                Json.stringify(new AddLinksQuery("https://example.com"))
        );

        assertNotNull(response);
        assertInstanceOf(Jd2Response.class, response);
        assertNotNull(response.data());
        assertEquals(12345L, response.data().id());
        mockServer.verify();
    }

    @Test
    void isCollecting_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/linkgrabberv2/isCollecting"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkGrabberClient.isCollecting();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void clearList_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/linkgrabberv2/clearList"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkGrabberClient.clearList();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void queryLinkCrawlerJobs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "broken": 0,
                            "checking": false,
                            "crawled": 1,
                            "crawlerId": 100,
                            "crawling": false,
                            "filtered": 0,
                            "jobId": 12345,
                            "unhandled": 0
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/queryLinkCrawlerJobs")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<JobLinkCrawler>> response = linkGrabberClient.queryLinkCrawlerJobs(
                Json.stringify(new LinkCrawlerJobsQuery(new long[]{12345L}))
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals(12345L, response.data().getFirst().jobId());
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
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/queryLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<CrawledLink>> response = linkGrabberClient.queryLinks(
                Json.stringify(new CrawledLinkQuery())
        );

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
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/queryPackages")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<CrawledPackage>> response = linkGrabberClient.queryPackages(
                Json.stringify(new CrawledPackageQuery(new long[]{222L}))
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("pkg1", response.data().getFirst().name());
        assertEquals(222L, response.data().getFirst().uuid());
        mockServer.verify();
    }

    @Test
    void abort_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/abort")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkGrabberClient.abort(12345L);

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void addContainer_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "id": 999
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/addContainer")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<LinkCollectingJob> response = linkGrabberClient.addContainer("DLC", "content");

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(999L, response.data().id());
        mockServer.verify();
    }

    @Test
    void addVariantCopy_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/addVariantCopy")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.addVariantCopy(1L, 2L, 3L, "v1"));
        mockServer.verify();
    }

    @Test
    void cleanup_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/cleanup")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.cleanup(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L}),
                Action.DELETE_ALL,
                Mode.REMOVE_LINKS_ONLY,
                SelectionType.SELECTED
        ));
        mockServer.verify();
    }

    @Test
    void getChildrenChanged_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 555
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/getChildrenChanged")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Long> response = linkGrabberClient.getChildrenChanged(100L);

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
        mockServer.expect(requestTo("http://localhost:3128/linkgrabberv2/getDownloadFolderHistorySelectionBase"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<String>> response = linkGrabberClient.getDownloadFolderHistorySelectionBase();

        assertNotNull(response);
        assertEquals(List.of("/downloads", "/tmp"), response.data());
        mockServer.verify();
    }

    @Test
    void getDownloadUrls_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "https://example.com/file.zip": [1]
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/getDownloadUrls")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Map<String, List<Long>>> response = linkGrabberClient.getDownloadUrls(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L}),
                new String[]{"ORIGIN"}
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(List.of(1L), response.data().get("https://example.com/file.zip"));
        mockServer.verify();
    }

    @Test
    void getPackageCount_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 3
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/linkgrabberv2/getPackageCount"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Integer> response = linkGrabberClient.getPackageCount();

        assertNotNull(response);
        assertEquals(3, response.data());
        mockServer.verify();
    }

    @Test
    void getVariants_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "iconKey": "video",
                            "id": "1080p",
                            "name": "1080p MP4"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/getVariants")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<LinkVariant>> response = linkGrabberClient.getVariants(1L);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("1080p", response.data().getFirst().id());
        mockServer.verify();
    }

    @Test
    void moveLinks_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/moveLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.moveLinks(Json.stringify(new long[]{1L}), 2L, 3L));
        mockServer.verify();
    }

    @Test
    void movePackages_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/movePackages")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.movePackages(Json.stringify(new long[]{1L}), 2L));
        mockServer.verify();
    }

    @Test
    void moveToDownloadlist_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/moveToDownloadlist")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.moveToDownloadlist(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void movetoNewPackage_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/movetoNewPackage")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.movetoNewPackage(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L}),
                "newPkg",
                "/downloads"
        ));
        mockServer.verify();
    }

    @Test
    void removeLinks_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/removeLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.removeLinks(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void renameLink_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/renameLink")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.renameLink(1L, "newLinkName"));
        mockServer.verify();
    }

    @Test
    void renamePackage_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/renamePackage")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.renamePackage(1L, "newPkgName"));
        mockServer.verify();
    }

    @Test
    void setDownloadDirectory_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/setDownloadDirectory")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.setDownloadDirectory("/downloads", Json.stringify(new long[]{1L})));
        mockServer.verify();
    }

    @Test
    void setDownloadPassword_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/setDownloadPassword")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = linkGrabberClient.setDownloadPassword(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L}),
                "secret"
        );

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void setEnabled_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/setEnabled")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.setEnabled(
                true,
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void setPriority_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/setPriority")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.setPriority(
                Priority.HIGH,
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void setVariant_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/setVariant")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.setVariant(1L, "v1"));
        mockServer.verify();
    }

    @Test
    void splitPackageByHoster_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/splitPackageByHoster")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.splitPackageByHoster(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void startOnlineStatusCheck_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/linkgrabberv2/startOnlineStatusCheck")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> linkGrabberClient.startOnlineStatusCheck(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }
}
