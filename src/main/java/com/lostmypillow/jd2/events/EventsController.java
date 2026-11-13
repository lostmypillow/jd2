package com.lostmypillow.jd2.events;

import com.lostmypillow.jd2.structure.PublisherResponse;
import com.lostmypillow.jd2.structure.SubscriptionResponse;
import com.lostmypillow.jd2.structure.SubscriptionStatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {
    public final EventsClient client;

    public EventsController(EventsClient client) {
        this.client = client;
    }

    @PostMapping("/addsubscription")
    public ResponseEntity<SubscriptionResponse> addsubscription(
            @RequestParam("subscriptionid") long subscriptionid,
            @RequestParam("subscriptions") String subscriptions,
            @RequestParam("exclusions") String exclusions
    ) {
        return ResponseEntity.ok(client.addsubscription(subscriptionid, subscriptions, exclusions).data());
    }

    @PostMapping("/changesubscriptiontimeouts")
    public ResponseEntity<SubscriptionResponse> changesubscriptiontimeouts(
            @RequestParam("subscriptionid") long subscriptionid,
            @RequestParam("polltimeout") long polltimeout,
            @RequestParam("maxkeepalive") long maxkeepalive
    ) {
        return ResponseEntity.ok(client.changesubscriptiontimeouts(subscriptionid, polltimeout, maxkeepalive).data());
    }

    @GetMapping("/getsubscription")
    public ResponseEntity<SubscriptionResponse> getsubscription(@RequestParam("subscriptionid") long subscriptionid) {
        return ResponseEntity.ok(client.getsubscription(subscriptionid).data());
    }

    @GetMapping("/getsubscriptionstatus")
    public ResponseEntity<SubscriptionStatusResponse> getsubscriptionstatus(@RequestParam("subscriptionid") long subscriptionid) {
        return ResponseEntity.ok(client.getsubscriptionstatus(subscriptionid).data());
    }

    @GetMapping("/listen")
    public ResponseEntity<Void> listen(@RequestParam("subscriptionid") long subscriptionid) {
        client.listen(subscriptionid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listpublisher")
    public ResponseEntity<List<PublisherResponse>> listpublisher() {
        return ResponseEntity.ok(client.listpublisher().data());
    }

    @PostMapping("/removesubscription")
    public ResponseEntity<SubscriptionResponse> removesubscription(
            @RequestParam("subscriptionid") long subscriptionid,
            @RequestParam("subscriptions") String subscriptions,
            @RequestParam("exclusions") String exclusions
    ) {
        return ResponseEntity.ok(client.removesubscription(subscriptionid, subscriptions, exclusions).data());
    }

    @PostMapping("/setsubscription")
    public ResponseEntity<SubscriptionResponse> setsubscription(
            @RequestParam("subscriptionid") long subscriptionid,
            @RequestParam("subscriptions") String subscriptions,
            @RequestParam("exclusions") String exclusions
    ) {
        return ResponseEntity.ok(client.setsubscription(subscriptionid, subscriptions, exclusions).data());
    }

    @PostMapping("/subscribe")
    public ResponseEntity<SubscriptionResponse> subscribe(
            @RequestParam("subscriptions") String subscriptions,
            @RequestParam("exclusions") String exclusions
    ) {
        return ResponseEntity.ok(client.subscribe(subscriptions, exclusions).data());
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<SubscriptionResponse> unsubscribe(@RequestParam("subscriptionid") long subscriptionid) {
        return ResponseEntity.ok(client.unsubscribe(subscriptionid).data());
    }
}

