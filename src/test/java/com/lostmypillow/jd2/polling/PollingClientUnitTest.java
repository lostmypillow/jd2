package com.lostmypillow.jd2.polling;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.PollingResult;
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

public class PollingClientUnitTest {
    private PollingClient pollingClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        pollingClient = httpServiceProxyFactory.createClient(PollingClient.class);
    }

    @Test
    void poll_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "eventName": "downloadControllerState",
                            "eventData": {
                                "state": "RUNNING"
                            }
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/polling/poll")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<PollingResult>> response = pollingClient.poll(Json.stringify(new APIQuery<>()));

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("downloadControllerState", response.data().getFirst().eventName());
        assertEquals("RUNNING", response.data().getFirst().eventData().get("state"));
        mockServer.verify();
    }
}

