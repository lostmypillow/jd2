package com.lostmypillow.jd2.update;

import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class UpdateClientUnitTest {
    private UpdateClient updateClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        updateClient = httpServiceProxyFactory.createClient(UpdateClient.class);
    }

    @Test
    void isUpdateAvailable_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": false
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/update/isUpdateAvailable")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = updateClient.isUpdateAvailable();

        assertNotNull(response);
        assertEquals(false, response.data());
        mockServer.verify();
    }

    @Test
    void restartAndUpdate_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/update/restartAndUpdate")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        updateClient.restartAndUpdate();

        mockServer.verify();
    }

    @Test
    void runUpdateCheck_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/update/runUpdateCheck")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        updateClient.runUpdateCheck();

        mockServer.verify();
    }
}

