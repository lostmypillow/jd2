package com.lostmypillow.jd2.structure;

public record SubscriptionResponse(
    String[] exclusions,
    Long maxKeepalive,
    Long maxPolltimeout,
    Boolean subscribed,
    Long subscriptionid,
    String[] subscriptions
) {}
