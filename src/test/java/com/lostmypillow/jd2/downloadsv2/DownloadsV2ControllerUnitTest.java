package com.lostmypillow.jd2.downloadsv2;

import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.Reason;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DownloadsV2ControllerUnitTest {

    @Mock
    private DownloadsV2Client client;

    private DownloadsV2Controller controller;

    @BeforeEach
    void setup() {
        controller = new DownloadsV2Controller(client);
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
    void forceDownload_returnsBoolean() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});
        when(client.forceDownload(linkIds, packageIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.forceDownload(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).forceDownload(linkIds, packageIds);
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
    void getStopMark_returnsMark() {
        when(client.getStopMark()).thenReturn(new Jd2Response<>(-1L));

        ResponseEntity<Long> response = controller.getStopMark();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(-1L, response.getBody());
        verify(client).getStopMark();
    }

    @Test
    void getStopMarkedLink_returnsDownloadLink() {
        DownloadLink link = new DownloadLink(null, "test.zip", 1L);
        when(client.getStopMarkedLink()).thenReturn(new Jd2Response<>(link));

        ResponseEntity<DownloadLink> response = controller.getStopMarkedLink();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(link, response.getBody());
        verify(client).getStopMarkedLink();
    }

    @Test
    void getStructureChangeCounter_returnsCounter() {
        when(client.getStructureChangeCounter(10L)).thenReturn(new Jd2Response<>(20L));

        ResponseEntity<Long> response = controller.getStructureChangeCounter(10L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(20L, response.getBody());
        verify(client).getStructureChangeCounter(10L);
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
    void movetoNewPackage_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String pkgIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.movetoNewPackage(linkIds, pkgIds, "newPkg", "/downloads");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).movetoNewPackage(linkIds, pkgIds, "newPkg", "/downloads");
    }

    @Test
    void packageCount_returnsCount() {
        when(client.packageCount()).thenReturn(new Jd2Response<>(5));

        ResponseEntity<Integer> response = controller.packageCount();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody());
        verify(client).packageCount();
    }

    @Test
    void queryLinks_returnsDownloadLinks() {
        String query = Json.stringify(new LinkQuery());
        List<DownloadLink> links = List.of(new DownloadLink(null, "test.zip", 1L));
        when(client.queryLinks(query)).thenReturn(new Jd2Response<>(links));

        ResponseEntity<List<DownloadLink>> response = controller.queryLinks(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(links, response.getBody());
        verify(client).queryLinks(query);
    }

    @Test
    void queryPackages_returnsFilePackages() {
        String query = Json.stringify(new PackageQuery());
        List<FilePackage> packages = List.of(new FilePackage(null, "pkg1", 1L));
        when(client.queryPackages(query)).thenReturn(new Jd2Response<>(packages));

        ResponseEntity<List<FilePackage>> response = controller.queryPackages(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(packages, response.getBody());
        verify(client).queryPackages(query);
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
    void removeStopMark_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.removeStopMark();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).removeStopMark();
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
    void resetLinks_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.resetLinks(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).resetLinks(linkIds, packageIds);
    }

    @Test
    void resumeLinks_callsClientAndReturnsOk() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});

        ResponseEntity<Void> response = controller.resumeLinks(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).resumeLinks(linkIds, packageIds);
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
    void setStopMark_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.setStopMark(1L, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).setStopMark(1L, 2L);
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

    @Test
    void unskip_returnsBoolean() {
        String packageIds = Json.stringify(new long[]{1L});
        String linkIds = Json.stringify(new long[]{2L});
        when(client.unskip(packageIds, linkIds, Reason.MANUAL)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.unskip(packageIds, linkIds, Reason.MANUAL);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).unskip(packageIds, linkIds, Reason.MANUAL);
    }
}

