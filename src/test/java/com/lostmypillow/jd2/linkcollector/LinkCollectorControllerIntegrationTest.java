package com.lostmypillow.jd2.linkcollector;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.CrawledLink;
import com.lostmypillow.jd2.structure.CrawledPackage;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkCollectorControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LinkCollectorController linkCollectorController;

    @Test
    void addContainer() {
        ResponseEntity<Void> response = linkCollectorController.addContainer("DLC", "");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addLinks_fourArgs() {
        ResponseEntity<Boolean> response = linkCollectorController.addLinks("https://example.com", "pkg", "archPass", "linkPass");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addLinks_fiveArgs() {
        ResponseEntity<Boolean> response = linkCollectorController.addLinks("https://example.com", "pkg", "extPass", "dlPass", "/downloads");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addLinksAndStartDownload() {
        ResponseEntity<Boolean> response = linkCollectorController.addLinksAndStartDownload("https://example.com", "pkg", "extPass", "dlPass");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void disableLinks_twoArgs() {
        ResponseEntity<Boolean> response = linkCollectorController.disableLinks(
                Json.stringify(List.of()),
                Json.stringify(List.of())
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void disableLinks_oneArg() {
        ResponseEntity<Boolean> response = linkCollectorController.disableLinks(Json.stringify(List.of()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void enableLinks_twoArgs() {
        ResponseEntity<Boolean> response = linkCollectorController.enableLinks(
                Json.stringify(List.of()),
                Json.stringify(List.of())
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void enableLinks_oneArg() {
        ResponseEntity<Boolean> response = linkCollectorController.enableLinks(Json.stringify(List.of()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void getChildrenChanged() {
        ResponseEntity<Long> response = linkCollectorController.getChildrenChanged(0L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Long.class, response.getBody());
    }

    @Test
    void getDownloadFolderHistorySelectionBase() {
        ResponseEntity<List<String>> response = linkCollectorController.getDownloadFolderHistorySelectionBase();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void moveLinks() {
        ResponseEntity<Boolean> response = linkCollectorController.moveLinks(Json.stringify(new APIQuery<>()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void movePackages() {
        ResponseEntity<Boolean> response = linkCollectorController.movePackages(Json.stringify(new APIQuery<>()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void packageCount() {
        ResponseEntity<Integer> response = linkCollectorController.packageCount();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Integer.class, response.getBody());
    }

    @Test
    void queryLinks() {
        ResponseEntity<List<CrawledLink>> response = linkCollectorController.queryLinks(Json.stringify(new APIQuery<>()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void queryPackages() {
        ResponseEntity<List<CrawledPackage>> response = linkCollectorController.queryPackages(Json.stringify(new APIQuery<>()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void removeLinks_twoArgs() {
        ResponseEntity<Boolean> response = linkCollectorController.removeLinks(
                Json.stringify(List.of()),
                Json.stringify(List.of())
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void removeLinks_oneArg() {
        ResponseEntity<Boolean> response = linkCollectorController.removeLinks(Json.stringify(List.of()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void renameLink() {
        ResponseEntity<Boolean> response = linkCollectorController.renameLink(1L, 1L, "newName");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void renamePackage() {
        ResponseEntity<Boolean> response = linkCollectorController.renamePackage(1L, "newPackageName");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void startDownloads_oneArg() {
        ResponseEntity<Boolean> response = linkCollectorController.startDownloads(Json.stringify(List.of()));
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void startDownloads_twoArgs() {
        ResponseEntity<Boolean> response = linkCollectorController.startDownloads(
                Json.stringify(List.of()),
                Json.stringify(List.of())
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

