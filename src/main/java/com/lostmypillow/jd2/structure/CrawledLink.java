package com.lostmypillow.jd2.structure;

public record CrawledLink(
    AvailableLinkState availability,
    Long bytesTotal,
    String comment,
    String downloadPassword,
    Boolean enabled,
    String host,
    String name,
    Long packageUUID,
    Priority priority,
    String url,
    Long uuid,
    LinkVariant variant,
    Boolean variants,
    JsonMap infoMap,
    Long uniqueID
) {
    public CrawledLink(JsonMap infoMap, String name, Long uniqueID, Long uuid) {
        this(null, null, null, null, null, null, name, null, null, null, uuid, null, null, infoMap, uniqueID);
    }
}
