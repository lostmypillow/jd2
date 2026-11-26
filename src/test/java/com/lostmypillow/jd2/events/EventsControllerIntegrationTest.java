package com.lostmypillow.jd2.events;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.PublisherResponse;
import com.lostmypillow.jd2.structure.SubscriptionResponse;
import com.lostmypillow.jd2.structure.SubscriptionStatusResponse;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventsControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventsController eventsController;

    @Test
    void addsubscription() {
        ResponseEntity<SubscriptionResponse> response = eventsController.addsubscription(
                1L,
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(SubscriptionResponse.class, response.getBody());
    }

    @Test
    void changesubscriptiontimeouts() {
        ResponseEntity<SubscriptionResponse> response = eventsController.changesubscriptiontimeouts(1L, 30000L, 60000L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(SubscriptionResponse.class, response.getBody());
    }

    @Test
    void getsubscription() {
        ResponseEntity<SubscriptionResponse> response = eventsController.getsubscription(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(SubscriptionResponse.class, response.getBody());
    }

    @Test
    void getsubscriptionstatus() {
        ResponseEntity<SubscriptionStatusResponse> response = eventsController.getsubscriptionstatus(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(SubscriptionStatusResponse.class, response.getBody());
    }

    @Test
    void listen() {
        ResponseEntity<SubscriptionResponse> sub = eventsController.subscribe(
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        eventsController.changesubscriptiontimeouts(sub.getBody().subscriptionid(), 100L, 1000L);
        ResponseEntity<Void> response = eventsController.listen(sub.getBody().subscriptionid());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listpublisher() {
        ResponseEntity<List<PublisherResponse>> response = eventsController.listpublisher();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void removesubscription() {
        ResponseEntity<SubscriptionResponse> response = eventsController.removesubscription(
                1L,
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(SubscriptionResponse.class, response.getBody());
    }

    @Test
    void setsubscription() {
        ResponseEntity<SubscriptionResponse> response = eventsController.setsubscription(
                1L,
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(SubscriptionResponse.class, response.getBody());
    }

    @Test
    void subscribe() {
        ResponseEntity<SubscriptionResponse> response = eventsController.subscribe(
                Json.stringify(new String[]{}),
                Json.stringify(new String[]{})
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(SubscriptionResponse.class, response.getBody());
    }

    @Test
    void unsubscribe() {
        ResponseEntity<SubscriptionResponse> response = eventsController.unsubscribe(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(SubscriptionResponse.class, response.getBody());
    }
}

