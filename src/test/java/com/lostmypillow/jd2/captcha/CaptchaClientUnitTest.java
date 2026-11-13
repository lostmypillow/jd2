package com.lostmypillow.jd2.captcha;

import com.lostmypillow.jd2.constants.SkipRequest;
import com.lostmypillow.jd2.structure.CaptchaJob;
import com.lostmypillow.jd2.structure.Jd2Response;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class CaptchaClientUnitTest {
    private CaptchaClient captchaClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        captchaClient = httpServiceProxyFactory.createClient(CaptchaClient.class);
    }

    @Test
    void get_byId_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captcha/get")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> captchaClient.get(100L));
        mockServer.verify();
    }

    @Test
    void get_byIdAndFormat_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captcha/get")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> captchaClient.get(100L, "png"));
        mockServer.verify();
    }

    @Test
    void getCaptchaJob_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "id": 100,
                        "hoster": "example.com",
                        "type": "IMAGE"
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captcha/getCaptchaJob")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<CaptchaJob> response = captchaClient.getCaptchaJob(100L);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().id());
        assertEquals("example.com", response.data().hoster());
        assertEquals("IMAGE", response.data().type());
        mockServer.verify();
    }

    @Test
    void list_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "id": 100,
                            "hoster": "example.com",
                            "type": "IMAGE"
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/captcha/list"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<CaptchaJob>> response = captchaClient.list();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals(100L, response.data().getFirst().id());
        mockServer.verify();
    }

    @Test
    void skip_withIdAndType_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captcha/skip")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = captchaClient.skip(100L, SkipRequest.SINGLE);

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    @SuppressWarnings("deprecation")
    void skip_withId_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captcha/skip")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = captchaClient.skip(100L);

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void solve_withIdResultAndFormat_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captcha/solve")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = captchaClient.solve(100L, "abcd", "STRING");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void solve_withIdAndResult_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/captcha/solve")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = captchaClient.solve(100L, "abcd");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

