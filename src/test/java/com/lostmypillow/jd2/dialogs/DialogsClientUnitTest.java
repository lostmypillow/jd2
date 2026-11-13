package com.lostmypillow.jd2.dialogs;

import com.lostmypillow.jd2.structure.DialogInfo;
import com.lostmypillow.jd2.structure.DialogTypeInfo;
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

import java.util.Map;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class DialogsClientUnitTest {
    private DialogsClient dialogsClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        dialogsClient = httpServiceProxyFactory.createClient(DialogsClient.class);
    }

    @Test
    void answer_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/dialogs/answer")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> dialogsClient.answer(1L, Json.stringify(Map.of("ok", true))));
        mockServer.verify();
    }

    @Test
    void get_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "type": "org.appwork.uio.ConfirmDialogInterface",
                        "properties": {
                            "title": "Confirm"
                        }
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/dialogs/get")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<DialogInfo> response = dialogsClient.get(1L, true, true);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("org.appwork.uio.ConfirmDialogInterface", response.data().type());
        assertEquals("Confirm", response.data().properties().get("title"));
        mockServer.verify();
    }

    @Test
    void getTypeInfo_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "in": {
                            "title": "String"
                        },
                        "out": {
                            "result": "Boolean"
                        }
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/dialogs/getTypeInfo")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<DialogTypeInfo> response = dialogsClient.getTypeInfo("org.appwork.uio.ConfirmDialogInterface");

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("String", response.data().in().get("title"));
        assertEquals("Boolean", response.data().out().get("result"));
        mockServer.verify();
    }

    @Test
    void list_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [1, 2, 3]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/dialogs/list"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<long[]> response = dialogsClient.list();

        assertNotNull(response);
        assertArrayEquals(new long[]{1L, 2L, 3L}, response.data());
        mockServer.verify();
    }
}

