package com.lostmypillow.jd2.reconnect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ReconnectClientUnitTest {
    private ReconnectClient reconnectClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        reconnectClient = httpServiceProxyFactory.createClient(ReconnectClient.class);
    }

    @Test
    void doReconnect_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/reconnect/doReconnect")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        reconnectClient.doReconnect();

        mockServer.verify();
    }
}

