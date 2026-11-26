package com.lostmypillow.jd2.accountsv2;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.Type;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.AccountQuery;
import com.lostmypillow.jd2.structure.BasicAuthentication;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountsV2ControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AccountsV2Controller accountsV2Controller;

    @Test
    void addAccount() {
        ResponseEntity<Void> response = accountsV2Controller.addAccount("example.com", "user", "pass");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addBasicAuth() {
        ResponseEntity<Long> response = accountsV2Controller.addBasicAuth(Type.HTTP, "example.com", "user", "pass");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Long.class, response.getBody());
    }

    @Test
    void disableAccounts() {
        ResponseEntity<Void> response = accountsV2Controller.disableAccounts(Json.stringify(new long[]{}));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void enableAccounts() {
        ResponseEntity<Void> response = accountsV2Controller.enableAccounts(Json.stringify(new long[]{}));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getPremiumHosterUrl() {
        ResponseEntity<String> response = accountsV2Controller.getPremiumHosterUrl("example.com");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listAccounts() {
        ResponseEntity<List<Account>> response = accountsV2Controller.listAccounts(Json.stringify(new AccountQuery()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void listBasicAuth() {
        ResponseEntity<List<BasicAuthentication>> response = accountsV2Controller.listBasicAuth();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void listPremiumHoster() {
        ResponseEntity<List<String>> response = accountsV2Controller.listPremiumHoster();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void listPremiumHosterUrls() {
        ResponseEntity<Map<String, String>> response = accountsV2Controller.listPremiumHosterUrls();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());
    }

    @Test
    void refreshAccounts() {
        ResponseEntity<Void> response = accountsV2Controller.refreshAccounts(Json.stringify(new long[]{}));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void removeAccounts() {
        ResponseEntity<Void> response = accountsV2Controller.removeAccounts(Json.stringify(new long[]{}));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void removeBasicAuths() {
        ResponseEntity<Boolean> response = accountsV2Controller.removeBasicAuths(Json.stringify(new long[]{}));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void setUserNameAndPassword() {
        ResponseEntity<Boolean> response = accountsV2Controller.setUserNameAndPassword(1L, "user", "pass");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void updateBasicAuth() {
        BasicAuthentication entry = new BasicAuthentication(null, true, "example.com", 1L, null, "pass", BasicAuthentication.Type.BASIC, "user");
        ResponseEntity<Boolean> response = accountsV2Controller.updateBasicAuth(Json.stringify(entry));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

