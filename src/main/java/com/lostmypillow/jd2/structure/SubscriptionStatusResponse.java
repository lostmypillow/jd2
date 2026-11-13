package com.lostmypillow.jd2.structure;

public record SubscriptionStatusResponse(
    Integer queueSize,
    Boolean subscribed,
    Long subscriptionid
) {}
