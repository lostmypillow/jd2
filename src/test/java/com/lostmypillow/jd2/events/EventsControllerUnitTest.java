package com.lostmypillow.jd2.events;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.PublisherResponse;
import com.lostmypillow.jd2.structure.SubscriptionResponse;
import com.lostmypillow.jd2.structure.SubscriptionStatusResponse;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventsControllerUnitTest {

    @Mock
    private EventsClient client;

    private EventsController controller;

    @BeforeEach
    void setup() {
        controller = new EventsController(client);
    }

    @Test
    void addsubscription_returnsSubscriptionResponse() {
        String subs = Json.stringify(new String[]{"^downloads$"});
        String excls = Json.stringify(new String[]{});
        SubscriptionResponse subResp = new SubscriptionResponse(new String[]{}, 60000L, 30000L, true, 100L, new String[]{"^downloads$"});
        when(client.addsubscription(100L, subs, excls)).thenReturn(new Jd2Response<>(subResp));

        ResponseEntity<SubscriptionResponse> response = controller.addsubscription(100L, subs, excls);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subResp, response.getBody());
        verify(client).addsubscription(100L, subs, excls);
    }

    @Test
    void changesubscriptiontimeouts_returnsSubscriptionResponse() {
        SubscriptionResponse subResp = new SubscriptionResponse(new String[]{}, 60000L, 30000L, true, 100L, new String[]{});
        when(client.changesubscriptiontimeouts(100L, 30000L, 60000L)).thenReturn(new Jd2Response<>(subResp));

        ResponseEntity<SubscriptionResponse> response = controller.changesubscriptiontimeouts(100L, 30000L, 60000L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subResp, response.getBody());
        verify(client).changesubscriptiontimeouts(100L, 30000L, 60000L);
    }

    @Test
    void getsubscription_returnsSubscriptionResponse() {
        SubscriptionResponse subResp = new SubscriptionResponse(new String[]{}, 60000L, 30000L, true, 100L, new String[]{});
        when(client.getsubscription(100L)).thenReturn(new Jd2Response<>(subResp));

        ResponseEntity<SubscriptionResponse> response = controller.getsubscription(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subResp, response.getBody());
        verify(client).getsubscription(100L);
    }

    @Test
    void getsubscriptionstatus_returnsSubscriptionStatusResponse() {
        SubscriptionStatusResponse statusResp = new SubscriptionStatusResponse(0, true, 100L);
        when(client.getsubscriptionstatus(100L)).thenReturn(new Jd2Response<>(statusResp));

        ResponseEntity<SubscriptionStatusResponse> response = controller.getsubscriptionstatus(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(statusResp, response.getBody());
        verify(client).getsubscriptionstatus(100L);
    }

    @Test
    void listen_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.listen(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).listen(100L);
    }

    @Test
    void listpublisher_returnsPublisherResponses() {
        List<PublisherResponse> publishers = List.of(new PublisherResponse(new String[]{"STATE_CHANGE"}, "downloads"));
        when(client.listpublisher()).thenReturn(new Jd2Response<>(publishers));

        ResponseEntity<List<PublisherResponse>> response = controller.listpublisher();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(publishers, response.getBody());
        verify(client).listpublisher();
    }

    @Test
    void removesubscription_returnsSubscriptionResponse() {
        String subs = Json.stringify(new String[]{"^downloads$"});
        String excls = Json.stringify(new String[]{});
        SubscriptionResponse subResp = new SubscriptionResponse(new String[]{}, 60000L, 30000L, true, 100L, new String[]{});
        when(client.removesubscription(100L, subs, excls)).thenReturn(new Jd2Response<>(subResp));

        ResponseEntity<SubscriptionResponse> response = controller.removesubscription(100L, subs, excls);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subResp, response.getBody());
        verify(client).removesubscription(100L, subs, excls);
    }

    @Test
    void setsubscription_returnsSubscriptionResponse() {
        String subs = Json.stringify(new String[]{"^downloads$"});
        String excls = Json.stringify(new String[]{});
        SubscriptionResponse subResp = new SubscriptionResponse(new String[]{}, 60000L, 30000L, true, 100L, new String[]{"^downloads$"});
        when(client.setsubscription(100L, subs, excls)).thenReturn(new Jd2Response<>(subResp));

        ResponseEntity<SubscriptionResponse> response = controller.setsubscription(100L, subs, excls);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subResp, response.getBody());
        verify(client).setsubscription(100L, subs, excls);
    }

    @Test
    void subscribe_returnsSubscriptionResponse() {
        String subs = Json.stringify(new String[]{"^downloads$"});
        String excls = Json.stringify(new String[]{});
        SubscriptionResponse subResp = new SubscriptionResponse(new String[]{}, 60000L, 30000L, true, 100L, new String[]{"^downloads$"});
        when(client.subscribe(subs, excls)).thenReturn(new Jd2Response<>(subResp));

        ResponseEntity<SubscriptionResponse> response = controller.subscribe(subs, excls);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subResp, response.getBody());
        verify(client).subscribe(subs, excls);
    }

    @Test
    void unsubscribe_returnsSubscriptionResponse() {
        SubscriptionResponse subResp = new SubscriptionResponse(null, null, null, false, 100L, null);
        when(client.unsubscribe(100L)).thenReturn(new Jd2Response<>(subResp));

        ResponseEntity<SubscriptionResponse> response = controller.unsubscribe(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subResp, response.getBody());
        verify(client).unsubscribe(100L);
    }
}

