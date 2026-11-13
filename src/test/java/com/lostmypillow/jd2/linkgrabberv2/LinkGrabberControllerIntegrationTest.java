package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LinkGrabberControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LinkGrabberController linkGrabberController;
    @Autowired
    private ConfigurableEnvironment env;

    @Test
    void getLinkList() {
        ResponseEntity<List<CrawledLink>> response = linkGrabberController.getLinkList();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void clearLinkList() {
        ResponseEntity<Boolean> response = linkGrabberController.clearLinkList();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void addLink() {
        ResponseEntity<Void> response = linkGrabberController.addLink("http://releases.ubuntu.com/26.04/ubuntu-26.04.1-desktop-amd64.iso");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void isCollecting() {
        ResponseEntity<Boolean> response = linkGrabberController.isCollecting();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void queryLinkCrawlerJobs() {
        ResponseEntity<List<JobLinkCrawler>> response = linkGrabberController.queryLinkCrawlerJobs(
                Json.stringify(new LinkCrawlerJobsQuery(new long[]{}))
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void queryPackages() {
        ResponseEntity<List<CrawledPackage>> response = linkGrabberController.queryPackages(
                Json.stringify(new CrawledPackageQuery(new long[]{}))
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void abort() {
        ResponseEntity<Boolean> response = linkGrabberController.abort(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void addContainer() {
        ResponseEntity<LinkCollectingJob> response = linkGrabberController.addContainer("DLC", "");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(LinkCollectingJob.class, response.getBody());
    }

    @Test
    void addVariantCopy() {
        ResponseEntity<Void> response = linkGrabberController.addVariantCopy(1L, 2L, 3L, "variant1");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void cleanup() {
        ResponseEntity<Void> response = linkGrabberController.cleanup(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                Action.DELETE_ALL,
                Mode.REMOVE_LINKS_ONLY,
                SelectionType.NONE
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getChildrenChanged() {
        ResponseEntity<Long> response = linkGrabberController.getChildrenChanged(0L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Long.class, response.getBody());
    }

    @Test
    void getDownloadFolderHistorySelectionBase() {
        ResponseEntity<List<String>> response = linkGrabberController.getDownloadFolderHistorySelectionBase();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void getDownloadUrls() {
        ResponseEntity<Map<String, List<Long>>> response = linkGrabberController.getDownloadUrls(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                new String[]{"ORIGIN"}
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());
    }

    @Test
    void getPackageCount() {
        ResponseEntity<Integer> response = linkGrabberController.getPackageCount();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Integer.class, response.getBody());
    }

    @Test
    void getVariants() {
        ResponseEntity<List<LinkVariant>> response = linkGrabberController.getVariants(1L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void moveLinks() {
        ResponseEntity<Void> response = linkGrabberController.moveLinks(Json.stringify(new long[]{}), -1L, -1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void movePackages() {
        ResponseEntity<Void> response = linkGrabberController.movePackages(Json.stringify(new long[]{}), -1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void moveToDownloadlist() {
        ResponseEntity<Void> response = linkGrabberController.moveToDownloadlist(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void movetoNewPackage() {
        ResponseEntity<Void> response = linkGrabberController.movetoNewPackage(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                "newPkg",
                "/downloads"
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void removeLinks() {
        ResponseEntity<Void> response = linkGrabberController.removeLinks(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void renameLink() {
        ResponseEntity<Void> response = linkGrabberController.renameLink(1L, "newName");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void renamePackage() {
        ResponseEntity<Void> response = linkGrabberController.renamePackage(1L, "newPackageName");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void setDownloadDirectory() {
        ResponseEntity<Void> response = linkGrabberController.setDownloadDirectory("/downloads", Json.stringify(new long[]{}));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void setDownloadPassword() {
        ResponseEntity<Boolean> response = linkGrabberController.setDownloadPassword(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                "password"
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void setEnabled() {
        ResponseEntity<Void> response = linkGrabberController.setEnabled(
                true,
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void setPriority() {
        ResponseEntity<Void> response = linkGrabberController.setPriority(
                Priority.DEFAULT,
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void setVariant() {
        ResponseEntity<Void> response = linkGrabberController.setVariant(1L, "variant1");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void splitPackageByHoster() {
        ResponseEntity<Void> response = linkGrabberController.splitPackageByHoster(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void startOnlineStatusCheck() {
        ResponseEntity<Void> response = linkGrabberController.startOnlineStatusCheck(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
