package com.lostmypillow.jd2.accountsv2;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.Type;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.AccountQuery;
import com.lostmypillow.jd2.structure.BasicAuthentication;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountsV2ClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AccountsV2Client accountsV2Client;

    @Test
    void addAccount() {
        assertDoesNotThrow(() -> accountsV2Client.addAccount("example.com", "user", "pass"));
    }

    @Test
    void addBasicAuth() {
        Jd2Response<Long> data = accountsV2Client.addBasicAuth(Type.HTTP, "example.com", "user", "pass");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Long.class, data.data(), "data.data() should be an instance of Long");
    }

    @Test
    void disableAccounts() {
        assertDoesNotThrow(() -> accountsV2Client.disableAccounts(Json.stringify(new long[]{})));
    }

    @Test
    void enableAccounts() {
        assertDoesNotThrow(() -> accountsV2Client.enableAccounts(Json.stringify(new long[]{})));
    }

    @Test
    void getPremiumHosterUrl() {
        Jd2Response<String> data = accountsV2Client.getPremiumHosterUrl("example.com");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void listAccounts() {
        Jd2Response<List<Account>> data = accountsV2Client.listAccounts(Json.stringify(new AccountQuery()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void listBasicAuth() {
        Jd2Response<List<BasicAuthentication>> data = accountsV2Client.listBasicAuth();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void listPremiumHoster() {
        Jd2Response<List<String>> data = accountsV2Client.listPremiumHoster();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void listPremiumHosterUrls() {
        Jd2Response<Map<String, String>> data = accountsV2Client.listPremiumHosterUrls();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Map.class, data.data(), "data.data() should be an instance of Map");
    }

    @Test
    void refreshAccounts() {
        assertDoesNotThrow(() -> accountsV2Client.refreshAccounts(Json.stringify(new long[]{})));
    }

    @Test
    void removeAccounts() {
        assertDoesNotThrow(() -> accountsV2Client.removeAccounts(Json.stringify(new long[]{})));
    }

    @Test
    void removeBasicAuths() {
        Jd2Response<Boolean> data = accountsV2Client.removeBasicAuths(Json.stringify(new long[]{}));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void setUserNameAndPassword() {
        Jd2Response<Boolean> data = accountsV2Client.setUserNameAndPassword(1L, "user", "pass");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void updateBasicAuth() {
        BasicAuthentication entry = new BasicAuthentication(null, true, "example.com", 1L, null, "pass", BasicAuthentication.Type.BASIC, "user");
        Jd2Response<Boolean> data = accountsV2Client.updateBasicAuth(Json.stringify(entry));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

