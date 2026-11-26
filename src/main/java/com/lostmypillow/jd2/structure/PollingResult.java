package com.lostmypillow.jd2.structure;

public record PollingResult(
    JsonMap eventData,
    String eventName
) {}
