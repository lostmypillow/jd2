package com.lostmypillow.jd2.config;

import com.lostmypillow.jd2.structure.AdvancedConfigAPIEntry;
import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.EnumOption;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ConfigClientUnitTest {
    private ConfigClient configClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        configClient = httpServiceProxyFactory.createClient(ConfigClient.class);
    }

    @Test
    void get_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "configValue"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/config/get")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Object> response = configClient.get("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");

        assertNotNull(response);
        assertEquals("configValue", response.data());
        mockServer.verify();
    }

    @Test
    void getDefault_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "/downloads"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/config/getDefault")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Object> response = configClient.getDefault("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");

        assertNotNull(response);
        assertEquals("/downloads", response.data());
        mockServer.verify();
    }

    @Test
    void list_noArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "interfaceName": "org.jdownloader.settings.GeneralSettings",
                            "key": "DefaultDownloadFolder",
                            "storage": "default"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/config/list"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<AdvancedConfigAPIEntry>> response = configClient.list();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("DefaultDownloadFolder", response.data().getFirst().key());
        mockServer.verify();
    }

    @Test
    void list_fiveArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "interfaceName": "org.jdownloader.settings.GeneralSettings",
                            "key": "DefaultDownloadFolder",
                            "storage": "default"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/config/list")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<AdvancedConfigAPIEntry>> response = configClient.list(".*", true, true, true, true);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("DefaultDownloadFolder", response.data().getFirst().key());
        mockServer.verify();
    }

    @Test
    @SuppressWarnings("deprecation")
    void list_fourArgs_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "interfaceName": "org.jdownloader.settings.GeneralSettings",
                            "key": "DefaultDownloadFolder",
                            "storage": "default"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/config/list")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<AdvancedConfigAPIEntry>> response = configClient.list(".*", true, true, true);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("DefaultDownloadFolder", response.data().getFirst().key());
        mockServer.verify();
    }

    @Test
    void listEnum_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "label": "Default",
                            "name": "DEFAULT"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/config/listEnum")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<EnumOption>> response = configClient.listEnum("org.jdownloader.controlling.Priority");

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("DEFAULT", response.data().getFirst().name());
        mockServer.verify();
    }

    @Test
    void query_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "interfaceName": "org.jdownloader.settings.GeneralSettings",
                            "key": "DefaultDownloadFolder",
                            "storage": "default"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/config/query")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<AdvancedConfigAPIEntry>> response = configClient.query(Json.stringify(new AdvancedConfigQuery()));

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("DefaultDownloadFolder", response.data().getFirst().key());
        mockServer.verify();
    }

    @Test
    void reset_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/config/reset")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = configClient.reset("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void set_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/config/set")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = configClient.set("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder", "/downloads");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

