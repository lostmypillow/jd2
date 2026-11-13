package com.lostmypillow.jd2.structure;

public record PackageQuery(
    Boolean bytesLoaded,
    Boolean bytesTotal,
    Boolean childCount,
    Boolean comment,
    Boolean enabled,
    Boolean eta,
    Boolean finished,
    Boolean hosts,
    Integer maxResults,
    long[] packageUUIDs,
    Boolean priority,
    Boolean running,
    Boolean saveTo,
    Boolean speed,
    Integer startAt,
    Boolean status
) {
    public PackageQuery() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
}
