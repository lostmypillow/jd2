package com.lostmypillow.jd2.accountsv2;

import com.lostmypillow.jd2.constants.Type;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.AccountQuery;
import com.lostmypillow.jd2.structure.BasicAuthentication;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountsV2ControllerUnitTest {

    @Mock
    private AccountsV2Client client;

    private AccountsV2Controller controller;

    @BeforeEach
    void setup() {
        controller = new AccountsV2Controller(client);
    }

    @Test
    void addAccount_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.addAccount("example.com", "user", "pass");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).addAccount("example.com", "user", "pass");
    }

    @Test
    void addBasicAuth_returnsId() {
        when(client.addBasicAuth(Type.HTTP, "example.com", "user", "pass")).thenReturn(new Jd2Response<>(555L));

        ResponseEntity<Long> response = controller.addBasicAuth(Type.HTTP, "example.com", "user", "pass");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(555L, response.getBody());
        verify(client).addBasicAuth(Type.HTTP, "example.com", "user", "pass");
    }

    @Test
    void disableAccounts_callsClientAndReturnsOk() {
        String ids = Json.stringify(new long[]{1L, 2L});

        ResponseEntity<Void> response = controller.disableAccounts(ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).disableAccounts(ids);
    }

    @Test
    void enableAccounts_callsClientAndReturnsOk() {
        String ids = Json.stringify(new long[]{1L, 2L});

        ResponseEntity<Void> response = controller.enableAccounts(ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).enableAccounts(ids);
    }

    @Test
    void getPremiumHosterUrl_returnsUrl() {
        when(client.getPremiumHosterUrl("example.com")).thenReturn(new Jd2Response<>("https://example.com/premium"));

        ResponseEntity<String> response = controller.getPremiumHosterUrl("example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("https://example.com/premium", response.getBody());
        verify(client).getPremiumHosterUrl("example.com");
    }

    @Test
    void listAccounts_returnsAccounts() {
        String query = Json.stringify(new AccountQuery());
        List<Account> accounts = List.of(new Account("example.com", new JsonMap(), 100L));
        when(client.listAccounts(query)).thenReturn(new Jd2Response<>(accounts));

        ResponseEntity<List<Account>> response = controller.listAccounts(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
        verify(client).listAccounts(query);
    }

    @Test
    void listBasicAuth_returnsBasicAuthentications() {
        List<BasicAuthentication> auths = List.of(
                new BasicAuthentication(null, true, "example.com", 10L, null, "pass", BasicAuthentication.Type.BASIC, "user")
        );
        when(client.listBasicAuth()).thenReturn(new Jd2Response<>(auths));

        ResponseEntity<List<BasicAuthentication>> response = controller.listBasicAuth();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(auths, response.getBody());
        verify(client).listBasicAuth();
    }

    @Test
    void listPremiumHoster_returnsHosters() {
        List<String> hosters = List.of("example.com");
        when(client.listPremiumHoster()).thenReturn(new Jd2Response<>(hosters));

        ResponseEntity<List<String>> response = controller.listPremiumHoster();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hosters, response.getBody());
        verify(client).listPremiumHoster();
    }

    @Test
    void listPremiumHosterUrls_returnsUrlMap() {
        Map<String, String> urls = Map.of("example.com", "https://example.com/premium");
        when(client.listPremiumHosterUrls()).thenReturn(new Jd2Response<>(urls));

        ResponseEntity<Map<String, String>> response = controller.listPremiumHosterUrls();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(urls, response.getBody());
        verify(client).listPremiumHosterUrls();
    }

    @Test
    void refreshAccounts_callsClientAndReturnsOk() {
        String ids = Json.stringify(new long[]{1L, 2L});

        ResponseEntity<Void> response = controller.refreshAccounts(ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).refreshAccounts(ids);
    }

    @Test
    void removeAccounts_callsClientAndReturnsOk() {
        String ids = Json.stringify(new long[]{1L, 2L});

        ResponseEntity<Void> response = controller.removeAccounts(ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).removeAccounts(ids);
    }

    @Test
    void removeBasicAuths_returnsBoolean() {
        String ids = Json.stringify(new long[]{1L, 2L});
        when(client.removeBasicAuths(ids)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.removeBasicAuths(ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).removeBasicAuths(ids);
    }

    @Test
    void setUserNameAndPassword_returnsBoolean() {
        when(client.setUserNameAndPassword(100L, "user", "pass")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.setUserNameAndPassword(100L, "user", "pass");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).setUserNameAndPassword(100L, "user", "pass");
    }

    @Test
    void updateBasicAuth_returnsBoolean() {
        BasicAuthentication entry = new BasicAuthentication(null, true, "example.com", 10L, null, "pass", BasicAuthentication.Type.BASIC, "user");
        String entryJson = Json.stringify(entry);
        when(client.updateBasicAuth(entryJson)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.updateBasicAuth(entryJson);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).updateBasicAuth(entryJson);
    }
}

