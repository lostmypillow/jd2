package com.lostmypillow.jd2.events;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.PublisherResponse;
import com.lostmypillow.jd2.structure.SubscriptionResponse;
import com.lostmypillow.jd2.structure.SubscriptionStatusResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/events")
public interface EventsClient {
    /**
     * Adds subscriptions and exclusions to an existing subscription
     *
     * @param subscriptionid subscription ID
     * @param subscriptions a stringified {@code String[]} of subscriptions
     * @param exclusions a stringified {@code String[]} of exclusions
     * @return a {@link Jd2Response} containing the {@link SubscriptionResponse}
     */
    @PostExchange("/addsubscription")
    Jd2Response<SubscriptionResponse> addsubscription(
            @RequestParam("subscriptionid") long subscriptionid,
            @RequestParam("subscriptions") String subscriptions,
            @RequestParam("exclusions") String exclusions
    );

    @PostExchange("/changesubscriptiontimeouts")
    Jd2Response<SubscriptionResponse> changesubscriptiontimeouts(
            @RequestParam("subscriptionid") long subscriptionid,
            @RequestParam("polltimeout") long polltimeout,
            @RequestParam("maxkeepalive") long maxkeepalive
    );

    @GetExchange("/getsubscription")
    Jd2Response<SubscriptionResponse> getsubscription(@RequestParam("subscriptionid") long subscriptionid);

    @GetExchange("/getsubscriptionstatus")
    Jd2Response<SubscriptionStatusResponse> getsubscriptionstatus(@RequestParam("subscriptionid") long subscriptionid);

    @GetExchange("/listen")
    void listen(@RequestParam("subscriptionid") long subscriptionid);
    // Possible Error(s) ResponseCode 404 (Not Found); Type:FILE_NOT_FOUND
    // ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @GetExchange("/listpublisher")
    Jd2Response<List<PublisherResponse>> listpublisher();

    /**
     * Removes subscriptions and exclusions from an existing subscription
     *
     * @param subscriptionid subscription ID
     * @param subscriptions a stringified {@code String[]} of subscriptions
     * @param exclusions a stringified {@code String[]} of exclusions
     * @return a {@link Jd2Response} containing the {@link SubscriptionResponse}
     */
    @PostExchange("/removesubscription")
    Jd2Response<SubscriptionResponse> removesubscription(
            @RequestParam("subscriptionid") long subscriptionid,
            @RequestParam("subscriptions") String subscriptions,
            @RequestParam("exclusions") String exclusions
    );

    /**
     * Sets subscriptions and exclusions on an existing subscription
     *
     * @param subscriptionid subscription ID
     * @param subscriptions a stringified {@code String[]} of subscriptions
     * @param exclusions a stringified {@code String[]} of exclusions
     * @return a {@link Jd2Response} containing the {@link SubscriptionResponse}
     */
    @PostExchange("/setsubscription")
    Jd2Response<SubscriptionResponse> setsubscription(
            @RequestParam("subscriptionid") long subscriptionid,
            @RequestParam("subscriptions") String subscriptions,
            @RequestParam("exclusions") String exclusions
    );

    /**
     * Subscribes to events
     *
     * @param subscriptions a stringified {@code String[]} of subscriptions
     * @param exclusions a stringified {@code String[]} of exclusions
     * @return a {@link Jd2Response} containing the {@link SubscriptionResponse}
     */
    @PostExchange("/subscribe")
    Jd2Response<SubscriptionResponse> subscribe(
            @RequestParam("subscriptions") String subscriptions,
            @RequestParam("exclusions") String exclusions
    );

    @PostExchange("/unsubscribe")
    Jd2Response<SubscriptionResponse> unsubscribe(@RequestParam("subscriptionid") long subscriptionid);
}

