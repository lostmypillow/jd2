package com.lostmypillow.jd2.events;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.PublisherResponse;
import com.lostmypillow.jd2.structure.SubscriptionResponse;
import com.lostmypillow.jd2.structure.SubscriptionStatusResponse;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventsClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventsClient eventsClient;

    @Test
    void addsubscription() {
        Jd2Response<SubscriptionResponse> data = eventsClient.addsubscription(
                1L,
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(SubscriptionResponse.class, data.data(), "data.data() should be an instance of SubscriptionResponse");
    }

    @Test
    void changesubscriptiontimeouts() {
        Jd2Response<SubscriptionResponse> data = eventsClient.changesubscriptiontimeouts(1L, 30000L, 60000L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(SubscriptionResponse.class, data.data(), "data.data() should be an instance of SubscriptionResponse");
    }

    @Test
    void getsubscription() {
        Jd2Response<SubscriptionResponse> data = eventsClient.getsubscription(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(SubscriptionResponse.class, data.data(), "data.data() should be an instance of SubscriptionResponse");
    }

    @Test
    void getsubscriptionstatus() {
        Jd2Response<SubscriptionStatusResponse> data = eventsClient.getsubscriptionstatus(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(SubscriptionStatusResponse.class, data.data(), "data.data() should be an instance of SubscriptionStatusResponse");
    }

    @Test
    void listen() {
        Jd2Response<SubscriptionResponse> sub = eventsClient.subscribe(
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        eventsClient.changesubscriptiontimeouts(sub.data().subscriptionid(), 100L, 1000L);
        assertDoesNotThrow(() -> eventsClient.listen(sub.data().subscriptionid()));
    }

    @Test
    void listpublisher() {
        Jd2Response<List<PublisherResponse>> data = eventsClient.listpublisher();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void removesubscription() {
        Jd2Response<SubscriptionResponse> data = eventsClient.removesubscription(
                1L,
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(SubscriptionResponse.class, data.data(), "data.data() should be an instance of SubscriptionResponse");
    }

    @Test
    void setsubscription() {
        Jd2Response<SubscriptionResponse> data = eventsClient.setsubscription(
                1L,
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(SubscriptionResponse.class, data.data(), "data.data() should be an instance of SubscriptionResponse");
    }

    @Test
    void subscribe() {
        Jd2Response<SubscriptionResponse> data = eventsClient.subscribe(
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(SubscriptionResponse.class, data.data(), "data.data() should be an instance of SubscriptionResponse");
    }

    @Test
    void unsubscribe() {
        Jd2Response<SubscriptionResponse> data = eventsClient.unsubscribe(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(SubscriptionResponse.class, data.data(), "data.data() should be an instance of SubscriptionResponse");
    }
}

