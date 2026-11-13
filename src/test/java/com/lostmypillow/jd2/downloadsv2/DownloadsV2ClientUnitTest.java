package com.lostmypillow.jd2.downloadsv2;

import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.Reason;
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

public class DownloadsV2ClientUnitTest {
    private DownloadsV2Client downloadsV2Client;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        downloadsV2Client = httpServiceProxyFactory.createClient(DownloadsV2Client.class);
    }

    @Test
    void cleanup_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/cleanup")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.cleanup(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L}),
                Action.DELETE_ALL,
                Mode.REMOVE_LINKS_ONLY,
                SelectionType.SELECTED
        ));
        mockServer.verify();
    }

    @Test
    void forceDownload_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/forceDownload")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = downloadsV2Client.forceDownload(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        );

        assertNotNull(response);
        assertEquals(true, response.data());
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
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/getDownloadUrls")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Map<String, List<Long>>> response = downloadsV2Client.getDownloadUrls(
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
    void getStopMark_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": -1
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/downloadsV2/getStopMark"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Long> response = downloadsV2Client.getStopMark();

        assertNotNull(response);
        assertEquals(-1L, response.data());
        mockServer.verify();
    }

    @Test
    void getStopMarkedLink_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "name": "example.zip",
                        "uuid": 111
                    }
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/downloadsV2/getStopMarkedLink"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<DownloadLink> response = downloadsV2Client.getStopMarkedLink();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("example.zip", response.data().name());
        assertEquals(111L, response.data().uuid());
        mockServer.verify();
    }

    @Test
    void getStructureChangeCounter_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 42
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/getStructureChangeCounter")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Long> response = downloadsV2Client.getStructureChangeCounter(0L);

        assertNotNull(response);
        assertEquals(42L, response.data());
        mockServer.verify();
    }

    @Test
    void moveLinks_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/moveLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.moveLinks(Json.stringify(new long[]{1L}), 2L, 3L));
        mockServer.verify();
    }

    @Test
    void movePackages_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/movePackages")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.movePackages(Json.stringify(new long[]{1L}), 2L));
        mockServer.verify();
    }

    @Test
    void movetoNewPackage_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/movetoNewPackage")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.movetoNewPackage(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L}),
                "newPkg",
                "/downloads"
        ));
        mockServer.verify();
    }

    @Test
    void packageCount_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 5
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/downloadsV2/packageCount"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Integer> response = downloadsV2Client.packageCount();

        assertNotNull(response);
        assertEquals(5, response.data());
        mockServer.verify();
    }

    @Test
    void queryLinks_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "name": "example.zip",
                            "uuid": 111
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/queryLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<DownloadLink>> response = downloadsV2Client.queryLinks(
                Json.stringify(new LinkQuery())
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
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/queryPackages")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<FilePackage>> response = downloadsV2Client.queryPackages(
                Json.stringify(new PackageQuery())
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("pkg1", response.data().getFirst().name());
        assertEquals(222L, response.data().getFirst().uuid());
        mockServer.verify();
    }

    @Test
    void removeLinks_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/removeLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.removeLinks(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void removeStopMark_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/downloadsV2/removeStopMark"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.removeStopMark());
        mockServer.verify();
    }

    @Test
    void renameLink_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/renameLink")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.renameLink(1L, "newLinkName"));
        mockServer.verify();
    }

    @Test
    void renamePackage_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/renamePackage")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.renamePackage(1L, "newPkgName"));
        mockServer.verify();
    }

    @Test
    void resetLinks_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/resetLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.resetLinks(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void resumeLinks_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/resumeLinks")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.resumeLinks(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void setDownloadDirectory_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/setDownloadDirectory")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.setDownloadDirectory("/downloads", Json.stringify(new long[]{1L})));
        mockServer.verify();
    }

    @Test
    void setDownloadPassword_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/setDownloadPassword")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = downloadsV2Client.setDownloadPassword(
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
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/setEnabled")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.setEnabled(
                true,
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void setPriority_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/setPriority")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.setPriority(
                Priority.HIGH,
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void setStopMark_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/setStopMark")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.setStopMark(1L, 2L));
        mockServer.verify();
    }

    @Test
    void splitPackageByHoster_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/splitPackageByHoster")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.splitPackageByHoster(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void startOnlineStatusCheck_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/startOnlineStatusCheck")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> downloadsV2Client.startOnlineStatusCheck(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        ));
        mockServer.verify();
    }

    @Test
    void unskip_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/downloadsV2/unskip")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = downloadsV2Client.unskip(
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L}),
                Reason.MANUAL
        );

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

