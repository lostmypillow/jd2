package com.lostmypillow.jd2.accounts;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Account;
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
public class AccountsControllerUnitTest {

    @Mock
    private AccountsClient client;

    private AccountsController controller;

    @BeforeEach
    void setup() {
        controller = new AccountsController(client);
    }

    @Test
    void addAccount_returnsBoolean() {
        when(client.addAccount("example.com", "user", "pass")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.addAccount("example.com", "user", "pass");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).addAccount("example.com", "user", "pass");
    }

    @Test
    void disableAccounts_returnsBoolean() {
        String ids = Json.stringify(List.of(1L, 2L));
        when(client.disableAccounts(ids)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.disableAccounts(ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).disableAccounts(ids);
    }

    @Test
    void enableAccounts_returnsBoolean() {
        String ids = Json.stringify(List.of(1L, 2L));
        when(client.enableAccounts(ids)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.enableAccounts(ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).enableAccounts(ids);
    }

    @Test
    void getAccountInfo_returnsAccount() {
        Account account = new Account("example.com", new JsonMap(), 100L);
        when(client.getAccountInfo(100L)).thenReturn(new Jd2Response<>(account));

        ResponseEntity<Account> response = controller.getAccountInfo(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account, response.getBody());
        verify(client).getAccountInfo(100L);
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
    void listPremiumHoster_returnsHosters() {
        List<String> hosters = List.of("example.com");
        when(client.listPremiumHoster()).thenReturn(new Jd2Response<>(hosters));

        ResponseEntity<List<String>> response = controller.listPremiumHoster();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hosters, response.getBody());
        verify(client).listPremiumHoster();
    }

    @Test
    void listPremiumHosterUrls_returnsJsonMap() {
        JsonMap urls = new JsonMap(Map.of("example.com", "https://example.com/premium"));
        when(client.listPremiumHosterUrls()).thenReturn(new Jd2Response<>(urls));

        ResponseEntity<JsonMap> response = controller.listPremiumHosterUrls();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(urls, response.getBody());
        verify(client).listPremiumHosterUrls();
    }

    @Test
    void premiumHosterIcon_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.premiumHosterIcon("example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).premiumHosterIcon("example.com");
    }

    @Test
    void queryAccounts_returnsAccounts() {
        String query = Json.stringify(new APIQuery<>());
        List<Account> accounts = List.of(new Account("example.com", new JsonMap(), 100L));
        when(client.queryAccounts(query)).thenReturn(new Jd2Response<>(accounts));

        ResponseEntity<List<Account>> response = controller.queryAccounts(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
        verify(client).queryAccounts(query);
    }

    @Test
    void removeAccounts_returnsBoolean() {
        String ids = Json.stringify(new Long[]{1L, 2L});
        when(client.removeAccounts(ids)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.removeAccounts(ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).removeAccounts(ids);
    }

    @Test
    void setEnabledState_returnsBoolean() {
        String ids = Json.stringify(new Long[]{1L, 2L});
        when(client.setEnabledState(true, ids)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.setEnabledState(true, ids);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).setEnabledState(true, ids);
    }

    @Test
    void updateAccount_returnsBoolean() {
        when(client.updateAccount(100L, "user", "pass")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.updateAccount(100L, "user", "pass");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).updateAccount(100L, "user", "pass");
    }
}

