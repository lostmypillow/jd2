package com.lostmypillow.jd2.linkcollector;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.CrawledLink;
import com.lostmypillow.jd2.structure.CrawledPackage;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkCollectorControllerUnitTest {

    @Mock
    private LinkCollectorClient client;

    private LinkCollectorController controller;

    @BeforeEach
    void setup() {
        controller = new LinkCollectorController(client);
    }

    @Test
    void addContainer_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.addContainer("DLC", "content");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).addContainer("DLC", "content");
    }

    @Test
    void addLinks_fourArgs_returnsBoolean() {
        when(client.addLinks("https://example.com", "pkg", "archPass", "linkPass")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.addLinks("https://example.com", "pkg", "archPass", "linkPass");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).addLinks("https://example.com", "pkg", "archPass", "linkPass");
    }

    @Test
    void addLinks_fiveArgs_returnsBoolean() {
        when(client.addLinks("https://example.com", "pkg", "extPass", "dlPass", "/downloads")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.addLinks("https://example.com", "pkg", "extPass", "dlPass", "/downloads");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).addLinks("https://example.com", "pkg", "extPass", "dlPass", "/downloads");
    }

    @Test
    void addLinksAndStartDownload_returnsBoolean() {
        when(client.addLinksAndStartDownload("https://example.com", "pkg", "extPass", "dlPass")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.addLinksAndStartDownload("https://example.com", "pkg", "extPass", "dlPass");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).addLinksAndStartDownload("https://example.com", "pkg", "extPass", "dlPass");
    }

    @Test
    void disableLinks_twoArgs_returnsBoolean() {
        String linkIds = Json.stringify(List.of(1L));
        String packageIds = Json.stringify(List.of(2L));
        when(client.disableLinks(linkIds, packageIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.disableLinks(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).disableLinks(linkIds, packageIds);
    }

    @Test
    void disableLinks_oneArg_returnsBoolean() {
        String linkIds = Json.stringify(List.of(1L));
        when(client.disableLinks(linkIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.disableLinks(linkIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).disableLinks(linkIds);
    }

    @Test
    void enableLinks_twoArgs_returnsBoolean() {
        String linkIds = Json.stringify(List.of(1L));
        String packageIds = Json.stringify(List.of(2L));
        when(client.enableLinks(linkIds, packageIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.enableLinks(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).enableLinks(linkIds, packageIds);
    }

    @Test
    void enableLinks_oneArg_returnsBoolean() {
        String linkIds = Json.stringify(List.of(1L));
        when(client.enableLinks(linkIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.enableLinks(linkIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).enableLinks(linkIds);
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
    void moveLinks_returnsBoolean() {
        String query = Json.stringify(new APIQuery<>());
        when(client.moveLinks(query)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.moveLinks(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).moveLinks(query);
    }

    @Test
    void movePackages_returnsBoolean() {
        String query = Json.stringify(new APIQuery<>());
        when(client.movePackages(query)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.movePackages(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).movePackages(query);
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
    void queryLinks_returnsCrawledLinks() {
        String query = Json.stringify(new APIQuery<>());
        List<CrawledLink> links = List.of(new CrawledLink(null, "test.zip", 1L, 10L));
        when(client.queryLinks(query)).thenReturn(new Jd2Response<>(links));

        ResponseEntity<List<CrawledLink>> response = controller.queryLinks(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(links, response.getBody());
        verify(client).queryLinks(query);
    }

    @Test
    void queryPackages_returnsCrawledPackages() {
        String query = Json.stringify(new APIQuery<>());
        List<CrawledPackage> packages = List.of(new CrawledPackage(null, "pkg", 1L));
        when(client.queryPackages(query)).thenReturn(new Jd2Response<>(packages));

        ResponseEntity<List<CrawledPackage>> response = controller.queryPackages(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(packages, response.getBody());
        verify(client).queryPackages(query);
    }

    @Test
    void removeLinks_twoArgs_returnsBoolean() {
        String packageIds = Json.stringify(List.of(2L));
        String linkIds = Json.stringify(List.of(1L));
        when(client.removeLinks(packageIds, linkIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.removeLinks(packageIds, linkIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).removeLinks(packageIds, linkIds);
    }

    @Test
    void removeLinks_oneArg_returnsBoolean() {
        String linkIds = Json.stringify(List.of(1L));
        when(client.removeLinks(linkIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.removeLinks(linkIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).removeLinks(linkIds);
    }

    @Test
    void renameLink_returnsBoolean() {
        when(client.renameLink(2L, 1L, "newName")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.renameLink(2L, 1L, "newName");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).renameLink(2L, 1L, "newName");
    }

    @Test
    void renamePackage_returnsBoolean() {
        when(client.renamePackage(2L, "newPkgName")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.renamePackage(2L, "newPkgName");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).renamePackage(2L, "newPkgName");
    }

    @Test
    void startDownloads_oneArg_returnsBoolean() {
        String linkIds = Json.stringify(List.of(1L));
        when(client.startDownloads(linkIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.startDownloads(linkIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).startDownloads(linkIds);
    }

    @Test
    void startDownloads_twoArgs_returnsBoolean() {
        String linkIds = Json.stringify(List.of(1L));
        String packageIds = Json.stringify(List.of(2L));
        when(client.startDownloads(linkIds, packageIds)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.startDownloads(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).startDownloads(linkIds, packageIds);
    }
}

