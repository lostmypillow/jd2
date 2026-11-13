package com.lostmypillow.jd2.downloadsv2;

import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.Reason;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.DownloadLink;
import com.lostmypillow.jd2.structure.FilePackage;
import com.lostmypillow.jd2.structure.Priority;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/downloadsV2")
public class DownloadsV2Controller {
    public final DownloadsV2Client client;

    public DownloadsV2Controller(DownloadsV2Client client) {
        this.client = client;
    }

    @PostMapping("/cleanup")
    public ResponseEntity<Void> cleanup(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds,
            @RequestParam("action") Action action,
            @RequestParam("mode") Mode mode,
            @RequestParam("selectionType") SelectionType selectionType
    ) {
        client.cleanup(linkIds, packageIds, action, mode, selectionType);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/forceDownload")
    public ResponseEntity<Boolean> forceDownload(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        return ResponseEntity.ok(client.forceDownload(linkIds, packageIds).data());
    }

    @GetMapping("/getDownloadUrls")
    public ResponseEntity<Map<String, List<Long>>> getDownloadUrls(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds,
            @RequestParam("urlDisplayType") String[] urlDisplayType
    ) {
        return ResponseEntity.ok(client.getDownloadUrls(linkIds, packageIds, urlDisplayType).data());
    }

    @GetMapping("/getStopMark")
    public ResponseEntity<Long> getStopMark() {
        return ResponseEntity.ok(client.getStopMark().data());
    }

    @GetMapping("/getStopMarkedLink")
    public ResponseEntity<DownloadLink> getStopMarkedLink() {
        return ResponseEntity.ok(client.getStopMarkedLink().data());
    }

    @PostMapping("/getStructureChangeCounter")
    public ResponseEntity<Long> getStructureChangeCounter(@RequestParam("oldCounterValue") long oldCounterValue) {
        return ResponseEntity.ok(client.getStructureChangeCounter(oldCounterValue).data());
    }

    @PostMapping("/moveLinks")
    public ResponseEntity<Void> moveLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("afterLinkID") long afterLinkID,
            @RequestParam("destPackageID") long destPackageID
    ) {
        client.moveLinks(linkIds, afterLinkID, destPackageID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/movePackages")
    public ResponseEntity<Void> movePackages(
            @RequestParam("packageIds") String packageIds,
            @RequestParam("afterDestPackageId") long afterDestPackageId
    ) {
        client.movePackages(packageIds, afterDestPackageId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/movetoNewPackage")
    public ResponseEntity<Void> movetoNewPackage(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("pkgIds") String pkgIds,
            @RequestParam("newPkgName") String newPkgName,
            @RequestParam("downloadPath") String downloadPath
    ) {
        client.movetoNewPackage(linkIds, pkgIds, newPkgName, downloadPath);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/packageCount")
    public ResponseEntity<Integer> packageCount() {
        return ResponseEntity.ok(client.packageCount().data());
    }

    @PostMapping("/queryLinks")
    public ResponseEntity<List<DownloadLink>> queryLinks(@RequestParam("queryParams") String queryParams) {
        return ResponseEntity.ok(client.queryLinks(queryParams).data());
    }

    @PostMapping("/queryPackages")
    public ResponseEntity<List<FilePackage>> queryPackages(@RequestParam("queryParams") String queryParams) {
        return ResponseEntity.ok(client.queryPackages(queryParams).data());
    }

    @PostMapping("/removeLinks")
    public ResponseEntity<Void> removeLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.removeLinks(linkIds, packageIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/removeStopMark")
    public ResponseEntity<Void> removeStopMark() {
        client.removeStopMark();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/renameLink")
    public ResponseEntity<Void> renameLink(
            @RequestParam("linkId") Long linkId,
            @RequestParam("newName") String newName
    ) {
        client.renameLink(linkId, newName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/renamePackage")
    public ResponseEntity<Void> renamePackage(
            @RequestParam("packageId") Long packageId,
            @RequestParam("newName") String newName
    ) {
        client.renamePackage(packageId, newName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resetLinks")
    public ResponseEntity<Void> resetLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.resetLinks(linkIds, packageIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resumeLinks")
    public ResponseEntity<Void> resumeLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.resumeLinks(linkIds, packageIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/setDownloadDirectory")
    public ResponseEntity<Void> setDownloadDirectory(
            @RequestParam("directory") String directory,
            @RequestParam("packageIds") String packageIds
    ) {
        client.setDownloadDirectory(directory, packageIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/setDownloadPassword")
    public ResponseEntity<Boolean> setDownloadPassword(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds,
            @RequestParam("pass") String pass
    ) {
        return ResponseEntity.ok(client.setDownloadPassword(linkIds, packageIds, pass).data());
    }

    @PostMapping("/setEnabled")
    public ResponseEntity<Void> setEnabled(
            @RequestParam("enabled") boolean enabled,
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.setEnabled(enabled, linkIds, packageIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/setPriority")
    public ResponseEntity<Void> setPriority(
            @RequestParam("priority") Priority priority,
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.setPriority(priority, linkIds, packageIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/setStopMark")
    public ResponseEntity<Void> setStopMark(
            @RequestParam("linkId") long linkId,
            @RequestParam("packageId") long packageId
    ) {
        client.setStopMark(linkId, packageId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/splitPackageByHoster")
    public ResponseEntity<Void> splitPackageByHoster(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("pkgIds") String pkgIds
    ) {
        client.splitPackageByHoster(linkIds, pkgIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/startOnlineStatusCheck")
    public ResponseEntity<Void> startOnlineStatusCheck(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.startOnlineStatusCheck(linkIds, packageIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unskip")
    public ResponseEntity<Boolean> unskip(
            @RequestParam("packageIds") String packageIds,
            @RequestParam("linkIds") String linkIds,
            @RequestParam("filterByReason") Reason filterByReason
    ) {
        return ResponseEntity.ok(client.unskip(packageIds, linkIds, filterByReason).data());
    }
}

