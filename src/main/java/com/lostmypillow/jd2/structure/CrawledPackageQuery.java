package com.lostmypillow.jd2.structure;

public record CrawledPackageQuery(
    Boolean availableOfflineCount,
    Boolean availableOnlineCount,
    Boolean availableTempUnknownCount,
    Boolean availableUnknownCount,
    Boolean bytesTotal,
    Boolean childCount,
    Boolean comment,
    Boolean enabled,
    Boolean hosts,
    Integer maxResults,
    long[] packageUUIDs,
    Boolean priority,
    Boolean saveTo,
    Integer startAt,
    Boolean status
) {
    public CrawledPackageQuery(long[] packageUUIDs) {
        this(
                true,
               true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                -1,
                packageUUIDs,
        true,
        true,
        -1,
        true
        );
    }
}
