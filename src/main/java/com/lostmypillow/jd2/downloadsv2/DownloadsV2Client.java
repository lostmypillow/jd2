package com.lostmypillow.jd2.downloadsv2;

import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.Reason;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.Map;

@HttpExchange("/downloadsV2")
public interface DownloadsV2Client {
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

    /**
     * Forces download of links and packages
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/forceDownload")
    Jd2Response<Boolean> forceDownload(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Gets download URLs
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     * @param urlDisplayType display types
     * @return a {@link Jd2Response} containing a map of download URLs
     */
    @GetExchange("/getDownloadUrls")
    Jd2Response<Map<String, List<Long>>> getDownloadUrls(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds,
            @RequestParam("urlDisplayType") String[] urlDisplayType
            // UrlDisplayTypeStorable not defined, we use string for now
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @GetExchange("/getStopMark")
    Jd2Response<Long> getStopMark();

    @GetExchange("/getStopMarkedLink")
    Jd2Response<DownloadLink> getStopMarkedLink();

    @PostExchange("/getStructureChangeCounter")
    Jd2Response<Long> getStructureChangeCounter(@RequestParam("oldCounterValue") long oldCounterValue);

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

    @GetExchange("/packageCount")
    Jd2Response<Integer> packageCount();

    /**
     * Lists download links
     *
     * @param queryParams a stringified {@link LinkQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link DownloadLink}
     */
    @PostExchange("/queryLinks")
    Jd2Response<List<DownloadLink>> queryLinks(@RequestParam("queryParams") String queryParams);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Lists file packages
     *
     * @param queryParams a stringified {@link PackageQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link FilePackage}
     */
    @PostExchange("/queryPackages")
    Jd2Response<List<FilePackage>> queryPackages(@RequestParam("queryParams") String queryParams);
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

    @PostExchange("/removeStopMark")
    void removeStopMark();

    @PostExchange("/renameLink")
    void renameLink(
            @RequestParam("linkId") Long linkId,
            @RequestParam("newName") String newName
    );

    @PostExchange("/renamePackage")
    void renamePackage(
            @RequestParam("packageId") Long packageId,
            @RequestParam("newName") String newName
    );

    /**
     * Resets links and packages
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/resetLinks")
    void resetLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );

    /**
     * Resumes links and packages
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/resumeLinks")
    void resumeLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
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

    @PostExchange("/setStopMark")
    void setStopMark(
            @RequestParam("linkId") long linkId,
            @RequestParam("packageId") long packageId
    );

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

    /**
     * Unskips links and packages
     *
     * @param packageIds a stringified {@code long[]} of package IDs
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param filterByReason the {@link Reason} filter
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/unskip")
    Jd2Response<Boolean> unskip(
            @RequestParam("packageIds") String packageIds,
            @RequestParam("linkIds") String linkIds,
            @RequestParam("filterByReason") Reason filterByReason
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter
}

