package com.lostmypillow.jd2.captchaforward;

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

public class CaptchaForwardClientUnitTest {
    private CaptchaForwardClient captchaForwardClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        captchaForwardClient = httpServiceProxyFactory.createClient(CaptchaForwardClient.class);
    }

    @Test
    void createJobRecaptchaV2_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 98765
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captchaforward/createJobRecaptchaV2")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Long> response = captchaForwardClient.createJobRecaptchaV2("siteKey", "stoken", "example.com", "reason");

        assertNotNull(response);
        assertEquals(98765L, response.data());
        mockServer.verify();
    }

    @Test
    void getResult_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "token-result"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captchaforward/getResult")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<String> response = captchaForwardClient.getResult(98765L);

        assertNotNull(response);
        assertEquals("token-result", response.data());
        mockServer.verify();
    }
}

