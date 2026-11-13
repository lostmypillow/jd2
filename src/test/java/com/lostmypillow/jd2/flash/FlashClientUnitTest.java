package com.lostmypillow.jd2.flash;

import com.lostmypillow.jd2.structure.CnlQuery;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class FlashClientUnitTest {
    private FlashClient flashClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        flashClient = httpServiceProxyFactory.createClient(FlashClient.class);
    }

    @Test
    void add_threeArgs_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/flash/add")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> flashClient.add("pass", "source", "https://example.com"));
        mockServer.verify();
    }

    @Test
    void add_fourArgs_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/flash/add")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> flashClient.add("p1", "p2", "p3", "p4"));
        mockServer.verify();
    }

    @Test
    void add_noArgs_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/flash/add"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> flashClient.add());
        mockServer.verify();
    }

    @Test
    void addcnl_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/flash/addcnl")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> flashClient.addcnl(Json.stringify(new CnlQuery())));
        mockServer.verify();
    }

    @Test
    void addcrypted_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/flash/addcrypted"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> flashClient.addcrypted());
        mockServer.verify();
    }

    @Test
    void addcrypted2_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/flash/addcrypted2"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> flashClient.addcrypted2());
        mockServer.verify();
    }

    @Test
    void addcrypted2Remote_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/flash/addcrypted2Remote")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> flashClient.addcrypted2Remote("crypted", "jk", "k"));
        mockServer.verify();
    }

    @Test
    void flash_succeeds() {
        mockServer.expect(requestTo("http://localhost:3128/flash/"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> flashClient.flash());
        mockServer.verify();
    }
}

