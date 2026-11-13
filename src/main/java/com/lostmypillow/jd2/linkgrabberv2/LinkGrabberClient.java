package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.Map;

@HttpExchange("/linkgrabberv2")
public interface LinkGrabberClient {
    /**
     * Adds a link
     *
     * @param query a stringified {@link AddLinksQuery} object
     * @return a {@link Jd2Response} containing the created {@link LinkCollectingJob}
     */
    @PostExchange("/addLinks")
    Jd2Response<LinkCollectingJob> addLinks(@RequestParam("query") String query);

    @GetExchange("/isCollecting")
    Jd2Response<Boolean> isCollecting();

    @PostExchange("/clearList")
    Jd2Response<Boolean> clearList();

    /**
     * Lists link crawler jobs
     *
     * @param query a stringified {@link LinkCrawlerJobsQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link JobLinkCrawler}
     */
    @PostExchange("/queryLinkCrawlerJobs")
    Jd2Response<List<JobLinkCrawler>> queryLinkCrawlerJobs(@RequestParam("query") String query);

    /**
     * Lists crawled links
     *
     * @param queryParams a stringified {@link CrawledLinkQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link CrawledLink}
     */
    @PostExchange("/queryLinks")
    Jd2Response<List<CrawledLink>> queryLinks(@RequestParam("queryParams") String queryParams);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Lists crawled packages
     *
     * @param queryParams a stringified {@link CrawledPackageQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link CrawledPackage}
     */
    @PostExchange("/queryPackages")
    Jd2Response<List<CrawledPackage>> queryPackages(@RequestParam("queryParams") String queryParams);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter


    @PostExchange("/abort")
    Jd2Response<Boolean> abort(@RequestParam(name = "jobId", required = false) long jobId);

    @PostExchange("/addContainer")
    Jd2Response<LinkCollectingJob> addContainer(
            @RequestParam("type") String type,
            @RequestParam("content") String content
    );


    @PostExchange("/addVariantCopy")
    void addVariantCopy(
            @RequestParam("linkid") long linkid,
            @RequestParam("destinationAfterLinkID") long destinationAfterLinkID,
            @RequestParam("destinationPackageID") long destinationPackageID,
            @RequestParam("variantID") String variantID

    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Cleans up links and packages
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     * @param action the {@link Action}
     * @param mode the {@link Mode}
     * @param selectionType the {@link SelectionType}
     */
    @PostExchange("/cleanup")
    void cleanup(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds,
            @RequestParam("action") Action action,
            @RequestParam("mode") Mode mode,
            @RequestParam("selectionType") SelectionType selectionType
            );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter



    @PostExchange("/getChildrenChanged")
    Jd2Response<Long> getChildrenChanged(@RequestParam("structureWatermark") long structureWatermark);

    @GetExchange("/getDownloadFolderHistorySelectionBase")
    Jd2Response<List<String>> getDownloadFolderHistorySelectionBase();

    /**
     * Gets download URLs
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     * @param urlDisplayTypes display types
     * @return a {@link Jd2Response} containing a map of download URLs
     */
    @GetExchange("/getDownloadUrls")
    Jd2Response<Map<String, List<Long>>> getDownloadUrls(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds,
            @RequestParam("urlDisplayTypes") String[] urlDisplayTypes
            // UrlDisplayTypeStorable not defined, we use string for now
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @GetExchange("/getPackageCount")
    Jd2Response<Integer> getPackageCount();

    @GetExchange("/getVariants")
    Jd2Response<List<LinkVariant>> getVariants(@RequestParam("linkid") long linkid);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter



    /**
     * Moves links
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param afterLinkID destination after link ID
     * @param destPackageID destination package ID
     */
    @PostExchange("/moveLinks")
    void moveLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("afterLinkID") long afterLinkID,
            @RequestParam("destPackageID") long destPackageID
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Moves packages
     *
     * @param packageIds a stringified {@code long[]} of package IDs
     * @param afterDestPackageId destination after package ID
     */
    @PostExchange("/movePackages")
    void movePackages(
            @RequestParam("packageIds") String packageIds,
            @RequestParam("afterDestPackageId") long afterDestPackageId
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Moves links and packages to download list
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/moveToDownloadlist")
    void moveToDownloadlist(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Moves links and packages to a new package
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param pkgIds a stringified {@code long[]} of package IDs
     * @param newPkgName new package name
     * @param downloadPath download path
     */
    @PostExchange("/movetoNewPackage")
    void movetoNewPackage(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("pkgIds") String pkgIds,
            @RequestParam("newPkgName") String newPkgName,
            @RequestParam("downloadPath") String downloadPath
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter


    /**
     * Removes links and packages
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/removeLinks")
    void removeLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/renameLink")
    void renameLink(
            @RequestParam("linkId") long linkId,
            @RequestParam("newName") String newName
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/renamePackage")
    void renamePackage(
            @RequestParam("packageId") long packageId,
            @RequestParam("newName") String newName
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Sets download directory for packages
     *
     * @param directory directory path
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/setDownloadDirectory")
    void setDownloadDirectory(
            @RequestParam("directory") String directory,
            @RequestParam("packageIds") String packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Sets download password
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     * @param pass password
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/setDownloadPassword")
    Jd2Response<Boolean> setDownloadPassword(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds,
            @RequestParam("pass") String pass
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Sets enabled status for links and packages
     *
     * @param enabled enabled flag
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/setEnabled")
    void setEnabled(
            @RequestParam("enabled") boolean enabled,
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Sets priority for links and packages
     *
     * @param priority the {@link Priority}
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/setPriority")
    void setPriority(
            @RequestParam("priority") Priority priority,
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/setVariant")
    void setVariant(
            @RequestParam("linkid") long linkid,
            @RequestParam("variantID") String variantID
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Splits package by hoster
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param pkgIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/splitPackageByHoster")
    void splitPackageByHoster(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("pkgIds") String pkgIds
    );

    /**
     * Starts online status check
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/startOnlineStatusCheck")
    void startOnlineStatusCheck(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter
}
