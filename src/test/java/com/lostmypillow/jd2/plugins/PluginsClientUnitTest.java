package com.lostmypillow.jd2.plugins;

import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.Plugin;
import com.lostmypillow.jd2.structure.PluginConfigEntry;
import com.lostmypillow.jd2.structure.PluginsQuery;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class PluginsClientUnitTest {
    private PluginsClient pluginsClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        pluginsClient = httpServiceProxyFactory.createClient(PluginsClient.class);
    }

    @Test
    void get_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "pluginValue"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/plugins/get")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Object> response = pluginsClient.get("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key");

        assertNotNull(response);
        assertEquals("pluginValue", response.data());
        mockServer.verify();
    }

    @Test
    void getAllPluginRegex_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "youtube.com": [
                            "https?://(www\\\\.)?youtube\\\\.com/watch\\\\?v=.*"
                        ]
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/plugins/getAllPluginRegex")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Map<String, List<String>>> response = pluginsClient.getAllPluginRegex();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals(List.of("https?://(www\\.)?youtube\\.com/watch\\?v=.*"), response.data().get("youtube.com"));
        mockServer.verify();
    }

    @Test
    void getPluginRegex_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        "https?://(www\\\\.)?youtube\\\\.com/watch\\\\?v=.*"
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/plugins/getPluginRegex")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<String>> response = pluginsClient.getPluginRegex("https://www.youtube.com/watch?v=123");

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("https?://(www\\.)?youtube\\.com/watch\\?v=.*", response.data().getFirst());
        mockServer.verify();
    }

    @Test
    void list_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "className": "jd.plugins.hoster.YoutubeDashV2",
                            "displayName": "youtube.com",
                            "version": "100"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/plugins/list")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<Plugin>> response = pluginsClient.list(Json.stringify(new PluginsQuery()));

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("jd.plugins.hoster.YoutubeDashV2", response.data().getFirst().className());
        assertEquals("youtube.com", response.data().getFirst().displayName());
        assertEquals("100", response.data().getFirst().version());
        mockServer.verify();
    }

    @Test
    void query_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "interfaceName": "jd.plugins.hoster.YoutubeDashV2",
                            "key": "SubtitlesEnabled",
                            "value": true
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/plugins/query")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<PluginConfigEntry>> response = pluginsClient.query(Json.stringify(new AdvancedConfigQuery()));

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("jd.plugins.hoster.YoutubeDashV2", response.data().getFirst().interfaceName());
        assertEquals("SubtitlesEnabled", response.data().getFirst().key());
        assertEquals(true, response.data().getFirst().value());
        mockServer.verify();
    }

    @Test
    void reset_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/plugins/reset")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = pluginsClient.reset("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key");

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
        mockServer.expect(requestTo(startsWith("http://localhost:3128/plugins/set")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = pluginsClient.set("jd.plugins.hoster.YoutubeDashV2", "youtube.com", "key", "newVal");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

