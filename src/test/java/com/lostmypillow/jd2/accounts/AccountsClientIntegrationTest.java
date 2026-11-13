package com.lostmypillow.jd2.accounts;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountsClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AccountsClient accountsClient;

    @Test
    void addAccount() {
        Jd2Response<Boolean> data = accountsClient.addAccount("example.com", "user", "pass");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void disableAccounts() {
        Jd2Response<Boolean> data = accountsClient.disableAccounts(Json.stringify(List.of()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void enableAccounts() {
        Jd2Response<Boolean> data = accountsClient.enableAccounts(Json.stringify(List.of()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void getAccountInfo() {
        Jd2Response<Account> data = accountsClient.getAccountInfo(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void getPremiumHosterUrl() {
        Jd2Response<String> data = accountsClient.getPremiumHosterUrl("example.com");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void listPremiumHoster() {
        Jd2Response<List<String>> data = accountsClient.listPremiumHoster();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void listPremiumHosterUrls() {
        Jd2Response<JsonMap> data = accountsClient.listPremiumHosterUrls();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(JsonMap.class, data.data(), "data.data() should be an instance of JsonMap");
    }

    @Test
    void premiumHosterIcon() {
        assertDoesNotThrow(() -> accountsClient.premiumHosterIcon("example.com"));
    }

    @Test
    void queryAccounts() {
        Jd2Response<List<Account>> data = accountsClient.queryAccounts(Json.stringify(new APIQuery<>()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void removeAccounts() {
        Jd2Response<Boolean> data = accountsClient.removeAccounts(Json.stringify(new Long[]{}));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void setEnabledState() {
        Jd2Response<Boolean> data = accountsClient.setEnabledState(true, Json.stringify(new Long[]{}));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void updateAccount() {
        Jd2Response<Boolean> data = accountsClient.updateAccount(1L, "user", "pass");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

