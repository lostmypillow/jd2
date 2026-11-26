package com.lostmypillow.jd2.structure;

public record LinkCrawlerJobsQuery(
    Boolean collectorInfo,
    long[] jobIds
) {
    public LinkCrawlerJobsQuery(long[] jobIds) {
        this(true, jobIds);
    }

}
