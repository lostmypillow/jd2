package com.lostmypillow.jd2.downloadsv2;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.Reason;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DownloadsV2ControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DownloadsV2Controller downloadsV2Controller;

    @Test
    void cleanup() {
        ResponseEntity<Void> response = downloadsV2Controller.cleanup(
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
    void forceDownload() {
        ResponseEntity<Boolean> response = downloadsV2Controller.forceDownload(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }

    @Test
    void getDownloadUrls() {
        ResponseEntity<Map<String, List<Long>>> response = downloadsV2Controller.getDownloadUrls(
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
    void getStopMark() {
        ResponseEntity<Long> response = downloadsV2Controller.getStopMark();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Long.class, response.getBody());
    }

    @Test
    void getStopMarkedLink() {
        ResponseEntity<DownloadLink> response = downloadsV2Controller.getStopMarkedLink();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getStructureChangeCounter() {
        ResponseEntity<Long> response = downloadsV2Controller.getStructureChangeCounter(0L);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Long.class, response.getBody());
    }

    @Test
    void moveLinks() {
        ResponseEntity<Void> response = downloadsV2Controller.moveLinks(Json.stringify(new long[]{}), -1L, -1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void movePackages() {
        ResponseEntity<Void> response = downloadsV2Controller.movePackages(Json.stringify(new long[]{}), -1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void movetoNewPackage() {
        ResponseEntity<Void> response = downloadsV2Controller.movetoNewPackage(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                "newPkg",
                "/downloads"
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void packageCount() {
        ResponseEntity<Integer> response = downloadsV2Controller.packageCount();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Integer.class, response.getBody());
    }

    @Test
    void queryLinks() {
        ResponseEntity<List<DownloadLink>> response = downloadsV2Controller.queryLinks(
                Json.stringify(new LinkQuery())
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void queryPackages() {
        ResponseEntity<List<FilePackage>> response = downloadsV2Controller.queryPackages(
                Json.stringify(new PackageQuery())
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void removeLinks() {
        ResponseEntity<Void> response = downloadsV2Controller.removeLinks(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void removeStopMark() {
        ResponseEntity<Void> response = downloadsV2Controller.removeStopMark();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void renameLink() {
        ResponseEntity<Void> response = downloadsV2Controller.renameLink(1L, "newName");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void renamePackage() {
        ResponseEntity<Void> response = downloadsV2Controller.renamePackage(1L, "newPackageName");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void resetLinks() {
        ResponseEntity<Void> response = downloadsV2Controller.resetLinks(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void resumeLinks() {
        ResponseEntity<Void> response = downloadsV2Controller.resumeLinks(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void setDownloadDirectory() {
        ResponseEntity<Void> response = downloadsV2Controller.setDownloadDirectory("/downloads", Json.stringify(new long[]{}));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void setDownloadPassword() {
        ResponseEntity<Boolean> response = downloadsV2Controller.setDownloadPassword(
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
        ResponseEntity<Void> response = downloadsV2Controller.setEnabled(
                true,
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void setPriority() {
        ResponseEntity<Void> response = downloadsV2Controller.setPriority(
                Priority.DEFAULT,
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void setStopMark() {
        ResponseEntity<Void> response = downloadsV2Controller.setStopMark(-1L, -1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void splitPackageByHoster() {
        ResponseEntity<Void> response = downloadsV2Controller.splitPackageByHoster(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void startOnlineStatusCheck() {
        ResponseEntity<Void> response = downloadsV2Controller.startOnlineStatusCheck(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void unskip() {
        ResponseEntity<Boolean> response = downloadsV2Controller.unskip(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                Reason.MANUAL
        );
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Boolean.class, response.getBody());
    }
}

