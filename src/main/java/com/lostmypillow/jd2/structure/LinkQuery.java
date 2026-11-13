package com.lostmypillow.jd2.structure;

public record LinkQuery(
    Boolean addedDate,
    Boolean bytesLoaded,
    Boolean bytesTotal,
    Boolean comment,
    Boolean enabled,
    Boolean eta,
    Boolean extractionStatus,
    Boolean finished,
    Boolean finishedDate,
    Boolean host,
    long[] jobUUIDs,
    Integer maxResults,
    long[] packageUUIDs,
    Boolean password,
    Boolean priority,
    Boolean running,
    Boolean skipped,
    Boolean speed,
    Integer startAt,
    Boolean status,
    Boolean url
) {
    public LinkQuery() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
}
