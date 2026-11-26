package com.lostmypillow.jd2.structure;

public record CrawledPackage(
    Long bytesTotal,
    Integer childCount,
    String comment,
    String downloadPassword,
    Boolean enabled,
    String[] hosts,
    String name,
    Integer offlineCount,
    Integer onlineCount,
    Priority priority,
    String saveTo,
    Integer tempUnknownCount,
    Integer unknownCount,
    Long uuid,
    JsonMap infoMap
) {
    public CrawledPackage(JsonMap infoMap, String name, long uuid) {
        this(null, null, null, null, null, null, name, null, null, null, null, null, null, uuid, infoMap);
    }
}
