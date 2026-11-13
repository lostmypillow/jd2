package com.lostmypillow.jd2.contentv2;

import com.lostmypillow.jd2.structure.IconDescriptor;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ContentV2ClientUnitTest {
    private ContentV2Client contentV2Client;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        contentV2Client = httpServiceProxyFactory.createClient(ContentV2Client.class);
    }

    @Test
    void getFavIcon_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/contentV2/getFavIcon")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> contentV2Client.getFavIcon("example.com"));
        mockServer.verify();
    }

    @Test
    void getFileIcon_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/contentV2/getFileIcon")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> contentV2Client.getFileIcon("archive.zip"));
        mockServer.verify();
    }

    @Test
    void getIcon_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/contentV2/getIcon")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> contentV2Client.getIcon("ok", 16));
        mockServer.verify();
    }

    @Test
    void getIconDescription_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "cls": "Icon",
                        "key": "ok"
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/contentV2/getIconDescription")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<IconDescriptor> response = contentV2Client.getIconDescription("ok");

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("Icon", response.data().cls());
        assertEquals("ok", response.data().key());
        mockServer.verify();
    }
}

