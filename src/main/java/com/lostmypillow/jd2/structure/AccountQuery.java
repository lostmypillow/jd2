package com.lostmypillow.jd2.structure;

import java.util.List;

public record AccountQuery(
    Boolean enabled,
    Boolean error,
    Integer maxResults,
    Integer startAt,
    Boolean trafficLeft,
    Boolean trafficMax,
    Boolean userName,
    UnorderedList<Long> uuidlist,
    Boolean valid,
    Boolean validUntil
) {
    public AccountQuery(
        Boolean enabled,
        Boolean error,
        Integer maxResults,
        Integer startAt,
        Boolean trafficLeft,
        Boolean trafficMax,
        Boolean userName,
        List<Long> uuidlist,
        Boolean valid,
        Boolean validUntil
    ) {
        this(
            enabled,
            error,
            maxResults,
            startAt,
            trafficLeft,
            trafficMax,
            userName,
            uuidlist != null ? (uuidlist instanceof UnorderedList<Long> ul ? ul : new UnorderedList<>(uuidlist)) : null,
            valid,
            validUntil
        );
    }

    public AccountQuery() {
        this(null, null, null, null, null, null, null, (UnorderedList<Long>) null, null, null);
    }
}
