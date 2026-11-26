package com.lostmypillow.jd2.accounts;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class AccountsClientUnitTest {
    private AccountsClient accountsClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        accountsClient = httpServiceProxyFactory.createClient(AccountsClient.class);
    }

    @Test
    void addAccount_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/addAccount")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = accountsClient.addAccount("example.com", "user", "pass");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void disableAccounts_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/disableAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = accountsClient.disableAccounts(Json.stringify(List.of(1L, 2L)));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void enableAccounts_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/enableAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = accountsClient.enableAccounts(Json.stringify(List.of(1L, 2L)));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void getAccountInfo_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "hostname": "example.com",
                        "username": "user1",
                        "uuid": 100
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/getAccountInfo")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Account> response = accountsClient.getAccountInfo(100L);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("example.com", response.data().hostname());
        assertEquals("user1", response.data().username());
        assertEquals(100L, response.data().uuid());
        mockServer.verify();
    }

    @Test
    void getPremiumHosterUrl_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "https://example.com/premium"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/getPremiumHosterUrl")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<String> response = accountsClient.getPremiumHosterUrl("example.com");

        assertNotNull(response);
        assertEquals("https://example.com/premium", response.data());
        mockServer.verify();
    }

    @Test
    void listPremiumHoster_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": ["example.com", "hoster2.com"]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/accounts/listPremiumHoster"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<String>> response = accountsClient.listPremiumHoster();

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
        mockServer.expect(requestTo("http://localhost:3128/accounts/listPremiumHosterUrls"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<JsonMap> response = accountsClient.listPremiumHosterUrls();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("https://example.com/premium", response.data().get("example.com"));
        mockServer.verify();
    }

    @Test
    void premiumHosterIcon_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/premiumHosterIcon")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> accountsClient.premiumHosterIcon("example.com"));
        mockServer.verify();
    }

    @Test
    void queryAccounts_returnsValidJd2Response() {
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
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/queryAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<Account>> response = accountsClient.queryAccounts(Json.stringify(new APIQuery<>()));

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("example.com", response.data().getFirst().hostname());
        assertEquals(100L, response.data().getFirst().uuid());
        mockServer.verify();
    }

    @Test
    void removeAccounts_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/removeAccounts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = accountsClient.removeAccounts(Json.stringify(new Long[]{1L, 2L}));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void setEnabledState_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/setEnabledState")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = accountsClient.setEnabledState(true, Json.stringify(new Long[]{1L, 2L}));

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }

    @Test
    void updateAccount_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": true
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/accounts/updateAccount")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Boolean> response = accountsClient.updateAccount(100L, "newUser", "newPass");

        assertNotNull(response);
        assertEquals(true, response.data());
        mockServer.verify();
    }
}

