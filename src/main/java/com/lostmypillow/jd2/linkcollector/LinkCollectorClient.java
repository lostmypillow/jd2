package com.lostmypillow.jd2.linkcollector;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.CrawledLink;
import com.lostmypillow.jd2.structure.CrawledPackage;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/linkcollector")
public interface LinkCollectorClient {
    @PostExchange("/addContainer")
    void addContainer(
            @RequestParam("type") String type,
            @RequestParam("content") String content
    );

    @PostExchange("/addLinks")
    Jd2Response<Boolean> addLinks(
            @RequestParam("link") String link,
            @RequestParam("packageName") String packageName,
            @RequestParam("archivePassword") String archivePassword,
            @RequestParam("linkPassword") String linkPassword
    );

    @PostExchange("/addLinks")
    Jd2Response<Boolean> addLinks(
            @RequestParam("links") String links,
            @RequestParam("packageName") String packageName,
            @RequestParam("extractPassword") String extractPassword,
            @RequestParam("downloadPassword") String downloadPassword,
            @RequestParam("destinationFolder") String destinationFolder
    );

    @PostExchange("/addLinksAndStartDownload")
    Jd2Response<Boolean> addLinksAndStartDownload(
            @RequestParam("links") String links,
            @RequestParam("packageName") String packageName,
            @RequestParam("extractPassword") String extractPassword,
            @RequestParam("downloadPassword") String downloadPassword
    );

    /**
     * Disables links and packages
     *
     * @param linkIds a stringified {@link List} of link IDs
     * @param packageIds a stringified {@link List} of package IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/disableLinks")
    Jd2Response<Boolean> disableLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );

    /**
     * Disables links
     *
     * @param linkIds a stringified {@link List} of link IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/disableLinks")
    Jd2Response<Boolean> disableLinks(@RequestParam("linkIds") String linkIds);

    /**
     * Enables links and packages
     *
     * @param linkIds a stringified {@link List} of link IDs
     * @param packageIds a stringified {@link List} of package IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/enableLinks")
    Jd2Response<Boolean> enableLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );

    /**
     * Enables links
     *
     * @param linkIds a stringified {@link List} of link IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/enableLinks")
    Jd2Response<Boolean> enableLinks(@RequestParam("linkIds") String linkIds);

    @PostExchange("/getChildrenChanged")
    Jd2Response<Long> getChildrenChanged(@RequestParam("structureWatermark") Long structureWatermark);

    @GetExchange("/getDownloadFolderHistorySelectionBase")
    Jd2Response<List<String>> getDownloadFolderHistorySelectionBase();

    /**
     * Moves links
     *
     * @param query a stringified {@link APIQuery} object
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/moveLinks")
    Jd2Response<Boolean> moveLinks(@RequestParam("query") String query);

    /**
     * Moves packages
     *
     * @param query a stringified {@link APIQuery} object
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/movePackages")
    Jd2Response<Boolean> movePackages(@RequestParam("query") String query);

    @GetExchange("/packageCount")
    Jd2Response<Integer> packageCount();

    /**
     * Queries crawled links
     *
     * @param queryParams a stringified {@link APIQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link CrawledLink}
     */
    @PostExchange("/queryLinks")
    Jd2Response<List<CrawledLink>> queryLinks(@RequestParam("queryParams") String queryParams);

    /**
     * Queries crawled packages
     *
     * @param queryParams a stringified {@link APIQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link CrawledPackage}
     */
    @PostExchange("/queryPackages")
    Jd2Response<List<CrawledPackage>> queryPackages(@RequestParam("queryParams") String queryParams);

    /**
     * Removes packages and links
     *
     * @param packageIds a stringified {@link List} of package IDs
     * @param linkIds a stringified {@link List} of link IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/removeLinks")
    Jd2Response<Boolean> removeLinks(
            @RequestParam("packageIds") String packageIds,
            @RequestParam("linkIds") String linkIds
    );

    /**
     * Removes links
     *
     * @param linkIds a stringified {@link List} of link IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/removeLinks")
    Jd2Response<Boolean> removeLinks(@RequestParam("linkIds") String linkIds);

    @PostExchange("/renameLink")
    Jd2Response<Boolean> renameLink(
            @RequestParam("packageId") Long packageId,
            @RequestParam("linkId") Long linkId,
            @RequestParam("newName") String newName
    );

    @PostExchange("/renamePackage")
    Jd2Response<Boolean> renamePackage(
            @RequestParam("packageId") Long packageId,
            @RequestParam("newName") String newName
    );

    /**
     * Starts downloads for links
     *
     * @param linkIds a stringified {@link List} of link IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/startDownloads")
    Jd2Response<Boolean> startDownloads(@RequestParam("linkIds") String linkIds);

    /**
     * Starts downloads for links and packages
     *
     * @param linkIds a stringified {@link List} of link IDs
     * @param packageIds a stringified {@link List} of package IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/startDownloads")
    Jd2Response<Boolean> startDownloads(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
}

