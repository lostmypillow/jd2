package com.lostmypillow.jd2.structure;

public record APIQuery<V>(
    Boolean empty,
    V forNullKey,
    Integer maxResults,
    Integer startAt
) {}
