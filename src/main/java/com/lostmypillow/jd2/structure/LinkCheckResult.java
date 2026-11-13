package com.lostmypillow.jd2.structure;

import java.util.List;

public record LinkCheckResult(
    List<LinkStatus> links,
    STATUS status
) {
    public enum STATUS {
        PENDING,
        RUNNING,
        COMPLETED,
        FAILED,
        UNKNOWN
    }
}
