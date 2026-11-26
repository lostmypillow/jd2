package com.lostmypillow.jd2.structure;

/**
 * Query filter for crawled links.
 *
 * @param availability Whether to fetch link availability status. Defaults to true.
 * @param bytesTotal   Whether to include total bytes. Defaults to true.
 * @param comment      Optional comment filter.
 * @param enabled      Whether link is enabled. Defaults to true.
 * @param host         Whether to include host info. Defaults to true.
 * @param jobUUIDs     Target job UUIDs. Pass {@code null} to target all jobs.
 * @param maxResults   Maximum results to return. Pass {@code -1} or {@code null} for all results.
 * @param packageUUIDs Target package UUIDs.
 * @param password     Password requirement filter.
 * @param priority     Priority filter.
 * @param startAt      Pagination start offset. Defaults to 0.
 * @param status       Status filter. Defaults to true.
 * @param url          Whether to include URL. Defaults to true.
 * @param variantID    Variant ID filter.
 * @param variantIcon  Variant icon filter.
 * @param variantName  Variant name filter.
 * @param variants     Variants filter.
 */
public record CrawledLinkQuery(
        Boolean availability,
        Boolean bytesTotal,
        Boolean comment,
        Boolean enabled,
        Boolean host,
        long[] jobUUIDs,
        Integer maxResults,
        long[] packageUUIDs,
        Boolean password,
        Boolean priority,
        Integer startAt,
        Boolean status,
        Boolean url,
        Boolean variantID,
        Boolean variantIcon,
        Boolean variantName,
        Boolean variants
) {
    public CrawledLinkQuery() {
        this(
                true,
                true,
                null,
                true,
                true,
                null,
                -1,
                null,
                null,
                null,
                0,
                true,
                true,
                null,
                null,
                null,
                null
        );
    }
}
