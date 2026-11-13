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
     * @param queryParams an {@link CrawledLinkQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link CrawledLink}
     */
    @PostExchange("/queryLinks")
    Jd2Response<List<CrawledLink>> queryLinks(@RequestParam("queryParams") String queryParams);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Lists crawled packages
     *
     * @param queryParams an {@link CrawledPackageQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link CrawledPackage}
     */
    @PostExchange("/queryPackages")
    Jd2Response<List<CrawledPackage>> queryPackages(@RequestParam("queryParams") CrawledPackageQuery queryParams);
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

    @PostExchange("/cleanup")
    void cleanup(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("packageIds") long[] packageIds,
            @RequestParam("action") Action action,
            @RequestParam("mode") Mode mode,
            @RequestParam("selectionType") SelectionType selectionType
            );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter



    @PostExchange("/getChildrenChanged")
    Jd2Response<Long> getChildrenChanged(@RequestParam("structureWatermark") long structureWatermark);

    @GetExchange("/getDownloadFolderHistorySelectionBase")
    Jd2Response<List<String>> getDownloadFolderHistorySelectionBase();

    @GetExchange("/getDownloadUrls")
    Jd2Response<Map<String, List<Long>>> getDownloadUrls(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("packageIds") long[] packageIds,
            @RequestParam("urlDisplayTypes") String[] urlDisplayTypes
            // UrlDisplayTypeStorable not defined, we use string for now
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @GetExchange("/getPackageCount")
    Jd2Response<Integer> getPackageCount();

    @GetExchange("/getVariants")
    Jd2Response<List<LinkVariant>> getVariants(@RequestParam("linkid") long linkid);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter



    @PostExchange("/moveLinks")
    void moveLinks(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("afterLinkID") long afterLinkID,
            @RequestParam("destPackageID") long destPackageID
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/movePackages")
    void movePackages(
            @RequestParam("packageIds") long[] packageIds,
            @RequestParam("afterDestPackageId") long afterDestPackageId
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/moveToDownloadlist")
    void moveToDownloadlist(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("packageIds") long[] packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/movetoNewPackage")
    void movetoNewPackage(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("pkgIds") long[] pkgIds,
            @RequestParam("newPkgName") String newPkgName,
            @RequestParam("downloadPath") String downloadPath
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter


    @PostExchange("/removeLinks")
    void removeLinks(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("packageIds") long[] packageIds
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

    @PostExchange("/setDownloadDirectory")
    void setDownloadDirectory(
            @RequestParam("directory") String directory,
            @RequestParam("packageIds") long[] packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/setDownloadPassword")
    Jd2Response<Boolean> setDownloadPassword(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("packageIds") long[] packageIds,
            @RequestParam("pass") String pass
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/setEnabled")
    void setEnabled(
            @RequestParam("enabled") boolean enabled,
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("packageIds") long[] packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/setPriority")
    void setPriority(
            @RequestParam("priority") Priority priority,
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("packageIds") long[] packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/setVariant")
    void setVariant(
            @RequestParam("linkid") long linkid,
            @RequestParam("variantID") String variantID
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/splitPackageByHoster")
    void splitPackageByHoster(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("pkgIds") long[] pkgIds
    );

    @PostExchange("/startOnlineStatusCheck")
    void startOnlineStatusCheck(
            @RequestParam("linkIds") long[] linkIds,
            @RequestParam("packageIds") long[] packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter
}
