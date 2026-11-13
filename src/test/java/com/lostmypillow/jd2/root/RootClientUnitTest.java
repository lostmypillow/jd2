package com.lostmypillow.jd2.root;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class RootClientUnitTest {
    private RootClient rootClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        rootClient = httpServiceProxyFactory.createClient(RootClient.class);
    }

    @Test
    void flash_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/flash"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> rootClient.flash());
        mockServer.verify();
    }

    @Test
    void crossdomainXml_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/crossdomain.xml"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> rootClient.crossdomainXml());
        mockServer.verify();
    }

    @Test
    void faviconIco_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/favicon.ico"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> rootClient.faviconIco());
        mockServer.verify();
    }

    @Test
    void flashgot_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/flashgot"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> rootClient.flashgot());
        mockServer.verify();
    }

    @Test
    void jdcheckJs_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/jdcheck.js"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> rootClient.jdcheckJs());
        mockServer.verify();
    }

    @Test
    void jdcheckjson_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/jdcheckjson"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> rootClient.jdcheckjson());
        mockServer.verify();
    }
}

