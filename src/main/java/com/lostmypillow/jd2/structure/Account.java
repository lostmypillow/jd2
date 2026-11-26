package com.lostmypillow.jd2.structure;

public record Account(
    Boolean enabled,
    String errorString,
    String errorType,
    String hostname,
    JsonMap infoMap,
    Long trafficLeft,
    Long trafficMax,
    String username,
    Long uuid,
    Boolean valid,
    Long validUntil
) {
    public Account(String hostname, JsonMap infoMap, long uuid) {
        this(null, null, null, hostname, infoMap, null, null, null, uuid, null, null);
    }
}
