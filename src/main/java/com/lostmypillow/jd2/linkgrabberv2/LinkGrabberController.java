package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
import com.lostmypillow.jd2.util.Json;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LinkGrabberController {
    public final LinkGrabberService service;
    public final LinkGrabberClient client;

    public LinkGrabberController(LinkGrabberService service, LinkGrabberClient client) {
        this.service = service;
        this.client = client;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CrawledLink>> getLinkList() {
        return ResponseEntity.ok(client.queryLinks(Json.stringify(new CrawledLinkQuery())).data());
    }

    @PostMapping("/clear")
    public ResponseEntity<Boolean> clearLinkList() {
        return ResponseEntity.ok(client.clearList().data());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addLink(@RequestBody String link) {
        service.addLinkAsync(link);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/isCollecting")
    public ResponseEntity<Boolean> isCollecting() {
        return ResponseEntity.ok(client.isCollecting().data());
    }

    @PostMapping("/queryLinkCrawlerJobs")
    public ResponseEntity<List<JobLinkCrawler>> queryLinkCrawlerJobs(@RequestParam("query") String query) {
        return ResponseEntity.ok(client.queryLinkCrawlerJobs(query).data());
    }

    @PostMapping("/queryPackages")
    public ResponseEntity<List<CrawledPackage>> queryPackages(@RequestParam("queryParams") String queryParams) {
        return ResponseEntity.ok(client.queryPackages(queryParams).data());
    }

    @PostMapping("/abort")
    public ResponseEntity<Boolean> abort(@RequestParam(name = "jobId", required = false) long jobId) {
        return ResponseEntity.ok(client.abort(jobId).data());
    }

    @PostMapping("/addContainer")
    public ResponseEntity<LinkCollectingJob> addContainer(
            @RequestParam("type") String type,
            @RequestParam("content") String content
    ) {
        return ResponseEntity.ok(client.addContainer(type, content).data());
    }

    @PostMapping("/addVariantCopy")
    public ResponseEntity<Void> addVariantCopy(
            @RequestParam("linkid") long linkid,
            @RequestParam("destinationAfterLinkID") long destinationAfterLinkID,
            @RequestParam("destinationPackageID") long destinationPackageID,
            @RequestParam("variantID") String variantID
    ) {
        client.addVariantCopy(linkid, destinationAfterLinkID, destinationPackageID, variantID);
        return ResponseEntity.ok().build();
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

    @PostMapping("/getChildrenChanged")
    public ResponseEntity<Long> getChildrenChanged(@RequestParam("structureWatermark") long structureWatermark) {
        return ResponseEntity.ok(client.getChildrenChanged(structureWatermark).data());
    }

    @GetMapping("/getDownloadFolderHistorySelectionBase")
    public ResponseEntity<List<String>> getDownloadFolderHistorySelectionBase() {
        return ResponseEntity.ok(client.getDownloadFolderHistorySelectionBase().data());
    }

    @GetMapping("/getDownloadUrls")
    public ResponseEntity<Map<String, List<Long>>> getDownloadUrls(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds,
            @RequestParam("urlDisplayTypes") String[] urlDisplayTypes
    ) {
        return ResponseEntity.ok(client.getDownloadUrls(linkIds, packageIds, urlDisplayTypes).data());
    }

    @GetMapping("/getPackageCount")
    public ResponseEntity<Integer> getPackageCount() {
        return ResponseEntity.ok(client.getPackageCount().data());
    }

    @GetMapping("/getVariants")
    public ResponseEntity<List<LinkVariant>> getVariants(@RequestParam("linkid") long linkid) {
        return ResponseEntity.ok(client.getVariants(linkid).data());
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

    @PostMapping("/moveToDownloadlist")
    public ResponseEntity<Void> moveToDownloadlist(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.moveToDownloadlist(linkIds, packageIds);
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

    @PostMapping("/removeLinks")
    public ResponseEntity<Void> removeLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        client.removeLinks(linkIds, packageIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/renameLink")
    public ResponseEntity<Void> renameLink(
            @RequestParam("linkId") long linkId,
            @RequestParam("newName") String newName
    ) {
        client.renameLink(linkId, newName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/renamePackage")
    public ResponseEntity<Void> renamePackage(
            @RequestParam("packageId") long packageId,
            @RequestParam("newName") String newName
    ) {
        client.renamePackage(packageId, newName);
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

    @PostMapping("/setVariant")
    public ResponseEntity<Void> setVariant(
            @RequestParam("linkid") long linkid,
            @RequestParam("variantID") String variantID
    ) {
        client.setVariant(linkid, variantID);
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
}
