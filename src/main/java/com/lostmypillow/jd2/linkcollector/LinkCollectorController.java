package com.lostmypillow.jd2.linkcollector;

import com.lostmypillow.jd2.structure.CrawledLink;
import com.lostmypillow.jd2.structure.CrawledPackage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/linkcollector")
public class LinkCollectorController {
    public final LinkCollectorClient client;

    public LinkCollectorController(LinkCollectorClient client) {
        this.client = client;
    }

    @PostMapping("/addContainer")
    public ResponseEntity<Void> addContainer(
            @RequestParam("type") String type,
            @RequestParam("content") String content
    ) {
        client.addContainer(type, content);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addLinks", params = "link")
    public ResponseEntity<Boolean> addLinks(
            @RequestParam("link") String link,
            @RequestParam("packageName") String packageName,
            @RequestParam("archivePassword") String archivePassword,
            @RequestParam("linkPassword") String linkPassword
    ) {
        return ResponseEntity.ok(client.addLinks(link, packageName, archivePassword, linkPassword).data());
    }

    @PostMapping(value = "/addLinks", params = "links")
    public ResponseEntity<Boolean> addLinks(
            @RequestParam("links") String links,
            @RequestParam("packageName") String packageName,
            @RequestParam("extractPassword") String extractPassword,
            @RequestParam("downloadPassword") String downloadPassword,
            @RequestParam("destinationFolder") String destinationFolder
    ) {
        return ResponseEntity.ok(client.addLinks(links, packageName, extractPassword, downloadPassword, destinationFolder).data());
    }

    @PostMapping("/addLinksAndStartDownload")
    public ResponseEntity<Boolean> addLinksAndStartDownload(
            @RequestParam("links") String links,
            @RequestParam("packageName") String packageName,
            @RequestParam("extractPassword") String extractPassword,
            @RequestParam("downloadPassword") String downloadPassword
    ) {
        return ResponseEntity.ok(client.addLinksAndStartDownload(links, packageName, extractPassword, downloadPassword).data());
    }

    @PostMapping(value = "/disableLinks", params = "packageIds")
    public ResponseEntity<Boolean> disableLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        return ResponseEntity.ok(client.disableLinks(linkIds, packageIds).data());
    }

    @PostMapping(value = "/disableLinks", params = "!packageIds")
    public ResponseEntity<Boolean> disableLinks(@RequestParam("linkIds") String linkIds) {
        return ResponseEntity.ok(client.disableLinks(linkIds).data());
    }

    @PostMapping(value = "/enableLinks", params = "packageIds")
    public ResponseEntity<Boolean> enableLinks(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        return ResponseEntity.ok(client.enableLinks(linkIds, packageIds).data());
    }

    @PostMapping(value = "/enableLinks", params = "!packageIds")
    public ResponseEntity<Boolean> enableLinks(@RequestParam("linkIds") String linkIds) {
        return ResponseEntity.ok(client.enableLinks(linkIds).data());
    }

    @PostMapping("/getChildrenChanged")
    public ResponseEntity<Long> getChildrenChanged(@RequestParam("structureWatermark") Long structureWatermark) {
        return ResponseEntity.ok(client.getChildrenChanged(structureWatermark).data());
    }

    @GetMapping("/getDownloadFolderHistorySelectionBase")
    public ResponseEntity<List<String>> getDownloadFolderHistorySelectionBase() {
        return ResponseEntity.ok(client.getDownloadFolderHistorySelectionBase().data());
    }

    @PostMapping("/moveLinks")
    public ResponseEntity<Boolean> moveLinks(@RequestParam("query") String query) {
        return ResponseEntity.ok(client.moveLinks(query).data());
    }

    @PostMapping("/movePackages")
    public ResponseEntity<Boolean> movePackages(@RequestParam("query") String query) {
        return ResponseEntity.ok(client.movePackages(query).data());
    }

    @GetMapping("/packageCount")
    public ResponseEntity<Integer> packageCount() {
        return ResponseEntity.ok(client.packageCount().data());
    }

    @PostMapping("/queryLinks")
    public ResponseEntity<List<CrawledLink>> queryLinks(@RequestParam("queryParams") String queryParams) {
        return ResponseEntity.ok(client.queryLinks(queryParams).data());
    }

    @PostMapping("/queryPackages")
    public ResponseEntity<List<CrawledPackage>> queryPackages(@RequestParam("queryParams") String queryParams) {
        return ResponseEntity.ok(client.queryPackages(queryParams).data());
    }

    @PostMapping(value = "/removeLinks", params = "packageIds")
    public ResponseEntity<Boolean> removeLinks(
            @RequestParam("packageIds") String packageIds,
            @RequestParam("linkIds") String linkIds
    ) {
        return ResponseEntity.ok(client.removeLinks(packageIds, linkIds).data());
    }

    @PostMapping(value = "/removeLinks", params = "!packageIds")
    public ResponseEntity<Boolean> removeLinks(@RequestParam("linkIds") String linkIds) {
        return ResponseEntity.ok(client.removeLinks(linkIds).data());
    }

    @PostMapping("/renameLink")
    public ResponseEntity<Boolean> renameLink(
            @RequestParam("packageId") Long packageId,
            @RequestParam("linkId") Long linkId,
            @RequestParam("newName") String newName
    ) {
        return ResponseEntity.ok(client.renameLink(packageId, linkId, newName).data());
    }

    @PostMapping("/renamePackage")
    public ResponseEntity<Boolean> renamePackage(
            @RequestParam("packageId") Long packageId,
            @RequestParam("newName") String newName
    ) {
        return ResponseEntity.ok(client.renamePackage(packageId, newName).data());
    }

    @PostMapping(value = "/startDownloads", params = "!packageIds")
    public ResponseEntity<Boolean> startDownloads(@RequestParam("linkIds") String linkIds) {
        return ResponseEntity.ok(client.startDownloads(linkIds).data());
    }

    @PostMapping(value = "/startDownloads", params = "packageIds")
    public ResponseEntity<Boolean> startDownloads(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    ) {
        return ResponseEntity.ok(client.startDownloads(linkIds, packageIds).data());
    }
}

