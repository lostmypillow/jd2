package com.lostmypillow.jd2.ui;

import com.lostmypillow.jd2.constants.Context;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.MenuStructure;
import com.lostmypillow.jd2.structure.MyJDMenuItem;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class UiClientUnitTest {
    private UiClient uiClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        uiClient = httpServiceProxyFactory.createClient(UiClient.class);
    }

    @Test
    void getMenu_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "id": "menu-root",
                        "name": "Root Menu",
                        "type": "CONTAINER"
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/ui/getMenu")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<MyJDMenuItem> response = uiClient.getMenu(Context.LGC);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("menu-root", response.data().id());
        assertEquals("Root Menu", response.data().name());
        assertEquals(MenuStructure.Type.CONTAINER, response.data().type());
        mockServer.verify();
    }

    @Test
    void invokeAction_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": "invoked"
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/ui/invokeAction")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<Object> response = uiClient.invokeAction(
                Context.DLC,
                "action-1",
                Json.stringify(new long[]{1L}),
                Json.stringify(new long[]{2L})
        );

        assertNotNull(response);
        assertEquals("invoked", response.data());
        mockServer.verify();
    }
}

