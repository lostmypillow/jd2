package com.lostmypillow.jd2.events;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.PublisherResponse;
import com.lostmypillow.jd2.structure.SubscriptionResponse;
import com.lostmypillow.jd2.structure.SubscriptionStatusResponse;
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

public class EventsClientUnitTest {
    private EventsClient eventsClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:3128");
        mockServer = MockRestServiceServer.bindTo(builder).build();

        RestClient restClient = builder.build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        eventsClient = httpServiceProxyFactory.createClient(EventsClient.class);
    }

    @Test
    void addsubscription_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "subscriptionid": 100,
                        "subscribed": true
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/addsubscription")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<SubscriptionResponse> response = eventsClient.addsubscription(
                100L,
                Json.stringify(new String[]{"^downloads$"}),
                Json.stringify(new String[]{})
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().subscriptionid());
        assertEquals(true, response.data().subscribed());
        mockServer.verify();
    }

    @Test
    void changesubscriptiontimeouts_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "subscriptionid": 100,
                        "maxPolltimeout": 30000,
                        "maxKeepalive": 60000
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/changesubscriptiontimeouts")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<SubscriptionResponse> response = eventsClient.changesubscriptiontimeouts(100L, 30000L, 60000L);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().subscriptionid());
        assertEquals(30000L, response.data().maxPolltimeout());
        assertEquals(60000L, response.data().maxKeepalive());
        mockServer.verify();
    }

    @Test
    void getsubscription_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "subscriptionid": 100,
                        "subscribed": true
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/getsubscription")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<SubscriptionResponse> response = eventsClient.getsubscription(100L);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().subscriptionid());
        mockServer.verify();
    }

    @Test
    void getsubscriptionstatus_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "subscriptionid": 100,
                        "subscribed": true,
                        "queueSize": 0
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/getsubscriptionstatus")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<SubscriptionStatusResponse> response = eventsClient.getsubscriptionstatus(100L);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().subscriptionid());
        assertEquals(0, response.data().queueSize());
        mockServer.verify();
    }

    @Test
    void listen_succeeds() {
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/listen")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        assertDoesNotThrow(() -> eventsClient.listen(100L));
        mockServer.verify();
    }

    @Test
    void listpublisher_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": [
                        {
                            "publisher": "downloads",
                            "eventids": ["STATE_CHANGE"]
                        }
                    ]
                }
                """;
        mockServer.expect(requestTo("http://localhost:3128/events/listpublisher"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<List<PublisherResponse>> response = eventsClient.listpublisher();

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(1, response.data().size());
        assertEquals("downloads", response.data().getFirst().publisher());
        mockServer.verify();
    }

    @Test
    void removesubscription_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "subscriptionid": 100,
                        "subscribed": true
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/removesubscription")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<SubscriptionResponse> response = eventsClient.removesubscription(
                100L,
                Json.stringify(new String[]{"^downloads$"}),
                Json.stringify(new String[]{})
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().subscriptionid());
        mockServer.verify();
    }

    @Test
    void setsubscription_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "subscriptionid": 100,
                        "subscribed": true
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/setsubscription")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<SubscriptionResponse> response = eventsClient.setsubscription(
                100L,
                Json.stringify(new String[]{"^downloads$"}),
                Json.stringify(new String[]{})
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().subscriptionid());
        mockServer.verify();
    }

    @Test
    void subscribe_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "subscriptionid": 100,
                        "subscribed": true
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/subscribe")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<SubscriptionResponse> response = eventsClient.subscribe(
                Json.stringify(new String[]{"^downloads$"}),
                Json.stringify(new String[]{})
        );

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().subscriptionid());
        mockServer.verify();
    }

    @Test
    void unsubscribe_returnsValidJd2Response() {
        String mockJsonResponse = """
                {
                    "data": {
                        "subscriptionid": 100,
                        "subscribed": false
                    }
                }
                """;
        mockServer.expect(requestTo(startsWith("http://localhost:3128/events/unsubscribe")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        Jd2Response<SubscriptionResponse> response = eventsClient.unsubscribe(100L);

        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals(100L, response.data().subscriptionid());
        assertEquals(false, response.data().subscribed());
        mockServer.verify();
    }
}

