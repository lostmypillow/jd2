package com.lostmypillow.jd2.structure;

public record JobLinkCrawler(
    Integer broken,
    Boolean checking,
    Integer crawled,
    Long crawlerId,
    Boolean crawling,
    Integer filtered,
    Long jobId,
    Integer unhandled
) {}
