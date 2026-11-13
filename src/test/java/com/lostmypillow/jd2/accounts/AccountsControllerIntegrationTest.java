package com.lostmypillow.jd2.accounts;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.JsonMap;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountsControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AccountsController accountsController;

    @Test
    void addAccount() {
        ResponseEntity<Boolean> response = accountsController.addAccount("example.com", "user", "pass");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void disableAccounts() {
        ResponseEntity<Boolean> response = accountsController.disableAccounts(Json.stringify(List.of()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void enableAccounts() {
        ResponseEntity<Boolean> response = accountsController.enableAccounts(Json.stringify(List.of()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void getAccountInfo() {
        ResponseEntity<Account> response = accountsController.getAccountInfo(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getPremiumHosterUrl() {
        ResponseEntity<String> response = accountsController.getPremiumHosterUrl("example.com");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listPremiumHoster() {
        ResponseEntity<List<String>> response = accountsController.listPremiumHoster();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void listPremiumHosterUrls() {
        ResponseEntity<JsonMap> response = accountsController.listPremiumHosterUrls();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(JsonMap.class, response.getBody());
    }

    @Test
    void premiumHosterIcon() {
        ResponseEntity<Void> response = accountsController.premiumHosterIcon("example.com");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void queryAccounts() {
        ResponseEntity<List<Account>> response = accountsController.queryAccounts(Json.stringify(new APIQuery<>()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void removeAccounts() {
        ResponseEntity<Boolean> response = accountsController.removeAccounts(Json.stringify(new Long[]{}));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void setEnabledState() {
        ResponseEntity<Boolean> response = accountsController.setEnabledState(true, Json.stringify(new Long[]{}));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void updateAccount() {
        ResponseEntity<Boolean> response = accountsController.updateAccount(1L, "user", "pass");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

