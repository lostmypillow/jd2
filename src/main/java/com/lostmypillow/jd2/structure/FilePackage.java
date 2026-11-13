package com.lostmypillow.jd2.structure;

public record FilePackage(
    String activeTask,
    Long bytesLoaded,
    Long bytesTotal,
    Integer childCount,
    String comment,
    String downloadPassword,
    Boolean enabled,
    Long eta,
    Boolean finished,
    String[] hosts,
    String name,
    Priority priority,
    Boolean running,
    String saveTo,
    Long speed,
    String status,
    String statusIconKey,
    Long uuid,
    JsonMap infoMap
) {
    public FilePackage(JsonMap infoMap, String name, long uuid) {
        this(null, null, null, null, null, null, null, null, null, null, name, null, null, null, null, null, null, uuid, infoMap);
    }
}
