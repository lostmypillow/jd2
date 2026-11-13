package com.lostmypillow.jd2.device;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class DeviceClientUnitTest {
    private DeviceClient deviceClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        deviceClient = httpServiceProxyFactory.createClient(DeviceClient.class);
    }

    @Test
    void getDirectConnectionInfos_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "mode": "LAN",
                        "rebindProtectionDetected": false
                    }
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/device/getDirectConnectionInfos"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<JsonMap> response = deviceClient.getDirectConnectionInfos();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("LAN", response.data().get("mode"));
        assertEquals(false, response.data().get("rebindProtectionDetected"));
        mockServer.verify();
    }

    @Test
    void getSessionPublicKey_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "public-key-value"
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/device/getSessionPublicKey"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<String> response = deviceClient.getSessionPublicKey();

        assertNotNull(response);
        assertEquals("public-key-value", response.data());
        mockServer.verify();
    }

    @Test
    void ping_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/device/ping"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = deviceClient.ping();

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

