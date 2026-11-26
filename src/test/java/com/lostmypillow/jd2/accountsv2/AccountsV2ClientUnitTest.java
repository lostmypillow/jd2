package com.lostmypillow.jd2.accountsv2;

import com.lostmypillow.jd2.constants.Type;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.AccountQuery;
import com.lostmypillow.jd2.structure.BasicAuthentication;
import com.lostmypillow.jd2.structure.Jd2Response;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class AccountsV2ClientUnitTest {
    private AccountsV2Client accountsV2Client;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        accountsV2Client = httpServiceProxyFactory.createClient(AccountsV2Client.class);
    }

    @Test
    void addAccount_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/addAccount")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> accountsV2Client.addAccount("example.com", "user", "pass"));
        mockServer.verify();
    }

    @Test
    void addBasicAuth_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": 555
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/addBasicAuth")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Long> response = accountsV2Client.addBasicAuth(Type.HTTP, "example.com", "user", "pass");

        assertNotNull(response);
        assertEquals(555L, response.data());
        mockServer.verify();
    }

    @Test
    void disableAccounts_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/disableAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> accountsV2Client.disableAccounts(Json.stringify(new long[]{1L, 2L})));
        mockServer.verify();
    }

    @Test
    void enableAccounts_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/enableAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> accountsV2Client.enableAccounts(Json.stringify(new long[]{1L, 2L})));
        mockServer.verify();
    }

    @Test
    void getPremiumHosterUrl_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "https://example.com/premium"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/getPremiumHosterUrl")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<String> response = accountsV2Client.getPremiumHosterUrl("example.com");

        assertNotNull(response);
        assertEquals("https://example.com/premium", response.data());
        mockServer.verify();
    }

    @Test
    void listAccounts_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "hostname": "example.com",
                            "username": "user1",
                            "uuid": 100
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/listAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<Account>> response = accountsV2Client.listAccounts(Json.stringify(new AccountQuery()));

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("example.com", response.data().getFirst().hostname());
        assertEquals(100L, response.data().getFirst().uuid());
        mockServer.verify();
    }

    @Test
    void listBasicAuth_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "id": 10,
                            "hostmask": "example.com",
                            "username": "user1",
                            "enabled": true
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/accountsV2/listBasicAuth"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<BasicAuthentication>> response = accountsV2Client.listBasicAuth();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals(10L, response.data().getFirst().id());
        assertEquals("example.com", response.data().getFirst().hostmask());
        mockServer.verify();
    }

    @Test
    void listPremiumHoster_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": ["example.com", "hoster2.com"]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/accountsV2/listPremiumHoster"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<String>> response = accountsV2Client.listPremiumHoster();

        assertNotNull(response);
        assertEquals(List.of("example.com", "hoster2.com"), response.data());
        mockServer.verify();
    }

    @Test
    void listPremiumHosterUrls_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "example.com": "https://example.com/premium"
                    }
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/accountsV2/listPremiumHosterUrls"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Map<String, String>> response = accountsV2Client.listPremiumHosterUrls();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("https://example.com/premium", response.data().get("example.com"));
        mockServer.verify();
    }

    @Test
    void refreshAccounts_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/refreshAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> accountsV2Client.refreshAccounts(Json.stringify(new long[]{1L, 2L})));
        mockServer.verify();
    }

    @Test
    void removeAccounts_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/removeAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> accountsV2Client.removeAccounts(Json.stringify(new long[]{1L, 2L})));
        mockServer.verify();
    }

    @Test
    void removeBasicAuths_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/removeBasicAuths")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = accountsV2Client.removeBasicAuths(Json.stringify(new long[]{1L, 2L}));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void setUserNameAndPassword_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/setUserNameAndPassword")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = accountsV2Client.setUserNameAndPassword(100L, "newUser", "newPass");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void updateBasicAuth_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accountsV2/updateBasicAuth")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        BasicAuthentication entry = new BasicAuthentication(null, true, "example.com", 10L, null, "pass", BasicAuthentication.Type.BASIC, "user");
        Jd2Response<Boolean> response = accountsV2Client.updateBasicAuth(Json.stringify(entry));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

