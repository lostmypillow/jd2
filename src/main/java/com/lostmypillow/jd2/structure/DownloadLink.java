package com.lostmypillow.jd2.structure;

public record DownloadLink(
    Long addedDate,
    Long bytesLoaded,
    Long bytesTotal,
    String comment,
    String downloadPassword,
    Boolean enabled,
    Long eta,
    String extractionStatus,
    Boolean finished,
    Long finishedDate,
    String host,
    String name,
    Long packageUUID,
    Priority priority,
    Boolean running,
    Boolean skipped,
    Long speed,
    String status,
    String statusIconKey,
    String url,
    Long uuid,
    JsonMap infoMap
) {
    public DownloadLink(JsonMap infoMap, String name, long uuid) {
        this(null, null, null, null, null, null, null, null, null, null, null, name, null, null, null, null, null, null, null, null, uuid, infoMap);
    }
}
