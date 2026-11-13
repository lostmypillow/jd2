package com.lostmypillow.jd2.downloads;

public record FilePackage(
        String activeTask,
        long bytesLoaded,
        long bytesTotal,
        int childCount,
        String comment,
        String downloadPassword,
        boolean enabled,
        long eta,
        boolean finished,
        String[] hosts,
        String name,
        Priority priority,
        boolean running,
        String saveTo,
        long speed,
        String status,
        String statusIconKey,
        long uuid
) {
}
