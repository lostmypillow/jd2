package com.lostmypillow.jd2.structure;

public record LinkStatus(
    String host,
    String linkCheckID,
    String name,
    Long size,
    AvailableLinkState status,
    String url
) {}
