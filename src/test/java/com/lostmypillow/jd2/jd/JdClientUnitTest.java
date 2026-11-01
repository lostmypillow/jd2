package com.lostmypillow.jd2.jd;

import com.lostmypillow.jd2.jd.records.JdApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class JdClientUnitTest {
    private JdClient jdClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        jdClient = httpServiceProxyFactory.createClient(JdClient.class);
    }

    @Test
    void getCoreRevision_returnsValidJdApiResponse() {
        String mockJsonResponse = """
                {
                    "data": 42
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/jd/getCoreRevision")).andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        JdApiResponse<Integer> data = jdClient.getCoreRevision();

        assertNotNull(data);
        assertInstanceOf(JdApiResponse.class, data, "data should be an instance of JdApiResponse<Integer>");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
        assertEquals(42, data.data());

        mockServer.verify();
    }
}
