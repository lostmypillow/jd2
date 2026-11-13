package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LinkGrabberControllerUnitTest {

    @Mock
    private LinkGrabberService service;

    @Mock
    private LinkGrabberClient client;

    private LinkGrabberController controller;

    @BeforeEach
    void setup() {
        controller = new LinkGrabberController(service, client);
    }

    @Test
    void getLinkList_returnsCrawledLinks() {
        List<CrawledLink> links = List.of(new CrawledLink(null, "test.zip", 1L, 10L));
        when(client.queryLinks(Json.stringify(new CrawledLinkQuery()))).thenReturn(new Jd2Response<>(links));

        ResponseEntity<List<CrawledLink>> response = controller.getLinkList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(links, response.getBody());
        verify(client).queryLinks(Json.stringify(new CrawledLinkQuery()));
    }

    @Test
    void clearLinkList_returnsBoolean() {
        when(client.clearList()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.clearLinkList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).clearList();
    }

    @Test
    void addLink_callsServiceAndReturnsAccepted() {
        String link = "https://example.com/file.zip";

        ResponseEntity<Void> response = controller.addLink(link);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(service).addLinkAsync(link);
    }

    @Test
    void isCollecting_returnsBoolean() {
        when(client.isCollecting()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.isCollecting();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).isCollecting();
    }

    @Test
    void queryLinkCrawlerJobs_returnsJobLinkCrawlers() {
        String query = Json.stringify(new LinkCrawlerJobsQuery(new long[]{20L}));
        List<JobLinkCrawler> jobs = List.of(new JobLinkCrawler(0, false, 1, 10L, false, 0, 20L, 0));
        when(client.queryLinkCrawlerJobs(query)).thenReturn(new Jd2Response<>(jobs));

        ResponseEntity<List<JobLinkCrawler>> response = controller.queryLinkCrawlerJobs(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobs, response.getBody());
        verify(client).queryLinkCrawlerJobs(query);
    }

    @Test
    void queryPackages_returnsCrawledPackages() {
        String query = Json.stringify(new CrawledPackageQuery(new long[]{1L}));
        List<CrawledPackage> packages = List.of(new CrawledPackage(null, "pkg", 1L));
        when(client.queryPackages(query)).thenReturn(new Jd2Response<>(packages));

        ResponseEntity<List<CrawledPackage>> response = controller.queryPackages(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(packages, response.getBody());
        verify(client).queryPackages(query);
    }

    @Test
    void abort_returnsBoolean() {
        when(client.abort(42L)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.abort(42L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).abort(42L);
    }

    @Test
    void addContainer_returnsLinkCollectingJob() {
        LinkCollectingJob job = new LinkCollectingJob(99L);
        when(client.addContainer("DLC", "content")).thenReturn(new Jd2Response<>(job));

        ResponseEntity<LinkCollectingJob> response = controller.addContainer("DLC", "content");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(job, response.getBody());
        verify(client).addContainer("DLC", "content");
    }

    @Test
    void addVariantCopy_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.addVariantCopy(1L, 2L, 3L, "v1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).addVariantCopy(1L, 2L, 3L, "v1");
    }

    @Test
    void cleanup_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.cleanup(
                linkIds,
                packageIds,
                Action.DELETE_ALL,
                Mode.REMOVE_LINKS_ONLY,
                SelectionType.SELECTED
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).cleanup(linkIds, packageIds, Action.DELETE_ALL, Mode.REMOVE_LINKS_ONLY, SelectionType.SELECTED);
    }

    @Test
    void getChildrenChanged_returnsWatermark() {
        when(client.getChildrenChanged(10L)).thenReturn(new Jd2Response<>(20L));

        ResponseEntity<Long> response = controller.getChildrenChanged(10L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(20L, response.getBody());
        verify(client).getChildrenChanged(10L);
    }

    @Test
    void getDownloadFolderHistorySelectionBase_returnsFolders() {
        List<String> folders = List.of("/downloads");
        when(client.getDownloadFolderHistorySelectionBase()).thenReturn(new Jd2Response<>(folders));

        ResponseEntity<List<String>> response = controller.getDownloadFolderHistorySelectionBase();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(folders, response.getBody());
        verify(client).getDownloadFolderHistorySelectionBase();
    }

    @Test
    void getDownloadUrls_returnsUrlMap() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});
        String[] displayTypes = {"ORIGIN"};
        Map<String, List<Long>> urls = Map.of("https://example.com", List.of(1L));
        when(client.getDownloadUrls(linkIds, packageIds, displayTypes)).thenReturn(new Jd2Response<>(urls));

        ResponseEntity<Map<String, List<Long>>> response = controller.getDownloadUrls(linkIds, packageIds, displayTypes);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(urls, response.getBody());
        verify(client).getDownloadUrls(linkIds, packageIds, displayTypes);
    }

    @Test
    void getPackageCount_returnsCount() {
        when(client.getPackageCount()).thenReturn(new Jd2Response<>(5));

        ResponseEntity<Integer> response = controller.getPackageCount();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody());
        verify(client).getPackageCount();
    }

    @Test
    void getVariants_returnsVariants() {
        List<LinkVariant> variants = List.of(new LinkVariant("icon", "id1", "name1"));
        when(client.getVariants(1L)).thenReturn(new Jd2Response<>(variants));

        ResponseEntity<List<LinkVariant>> response = controller.getVariants(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(variants, response.getBody());
        verify(client).getVariants(1L);
    }

    @Test
    void moveLinks_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});

        ResponseEntity<Void> response = controller.moveLinks(linkIds, 2L, 3L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).moveLinks(linkIds, 2L, 3L);
    }

    @Test
    void movePackages_callsClientAndReturnsOk() {
        String packageIds = Json.stringify(new long[]{1L});

        ResponseEntity<Void> response = controller.movePackages(packageIds, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).movePackages(packageIds, 2L);
    }

    @Test
    void moveToDownloadlist_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.moveToDownloadlist(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).moveToDownloadlist(linkIds, packageIds);
    }

    @Test
    void movetoNewPackage_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String pkgIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.movetoNewPackage(linkIds, pkgIds, "newPkg", "/downloads");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).movetoNewPackage(linkIds, pkgIds, "newPkg", "/downloads");
    }

    @Test
    void removeLinks_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.removeLinks(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).removeLinks(linkIds, packageIds);
    }

    @Test
    void renameLink_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.renameLink(1L, "newName");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).renameLink(1L, "newName");
    }

    @Test
    void renamePackage_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.renamePackage(1L, "newPkgName");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).renamePackage(1L, "newPkgName");
    }

    @Test
    void setDownloadDirectory_callsClientAndReturnsOk() {
        String packageIds = Json.stringify(new long[]{1L});

        ResponseEntity<Void> response = controller.setDownloadDirectory("/downloads", packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).setDownloadDirectory("/downloads", packageIds);
    }

    @Test
    void setDownloadPassword_returnsBoolean() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});
        when(client.setDownloadPassword(linkIds, packageIds, "pass")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.setDownloadPassword(linkIds, packageIds, "pass");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).setDownloadPassword(linkIds, packageIds, "pass");
    }

    @Test
    void setEnabled_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.setEnabled(true, linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).setEnabled(true, linkIds, packageIds);
    }

    @Test
    void setPriority_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.setPriority(Priority.HIGH, linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).setPriority(Priority.HIGH, linkIds, packageIds);
    }

    @Test
    void setVariant_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.setVariant(1L, "v1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).setVariant(1L, "v1");
    }

    @Test
    void splitPackageByHoster_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String pkgIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.splitPackageByHoster(linkIds, pkgIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).splitPackageByHoster(linkIds, pkgIds);
    }

    @Test
    void startOnlineStatusCheck_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.startOnlineStatusCheck(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).startOnlineStatusCheck(linkIds, packageIds);
    }
}
