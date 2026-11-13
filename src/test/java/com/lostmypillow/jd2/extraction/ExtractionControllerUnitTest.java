package com.lostmypillow.jd2.extraction;

import com.lostmypillow.jd2.structure.ArchiveSettings;
import com.lostmypillow.jd2.structure.ArchiveStatus;
import com.lostmypillow.jd2.structure.ControllerStatus;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExtractionControllerUnitTest {

    @Mock
    private ExtractionClient client;

    private ExtractionController controller;

    @BeforeEach
    void setup() {
        controller = new ExtractionController(client);
    }

    @Test
    void addArchivePassword_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.addArchivePassword("secret");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).addArchivePassword("secret");
    }

    @Test
    void cancelExtraction_returnsBoolean() {
        when(client.cancelExtraction(100L)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.cancelExtraction(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).cancelExtraction(100L);
    }

    @Test
    void getArchiveInfo_returnsArchiveStatuses() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});
        List<ArchiveStatus> statuses = List.of(
                new ArchiveStatus("arch1", "archive.rar", 100L, ControllerStatus.RUNNING, null, "RAR_SINGLE")
        );
        when(client.getArchiveInfo(linkIds, packageIds)).thenReturn(new Jd2Response<>(statuses));

        ResponseEntity<List<ArchiveStatus>> response = controller.getArchiveInfo(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(statuses, response.getBody());
        verify(client).getArchiveInfo(linkIds, packageIds);
    }

    @Test
    void getArchiveSettings_returnsArchiveSettings() {
        String archiveIds = Json.stringify(new String[]{"arch1"});
        List<ArchiveSettings> settings = List.of(new ArchiveSettings("arch1", true, "/extract", null, null, null, null));
        when(client.getArchiveSettings(archiveIds)).thenReturn(new Jd2Response<>(settings));

        ResponseEntity<List<ArchiveSettings>> response = controller.getArchiveSettings(archiveIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(settings, response.getBody());
        verify(client).getArchiveSettings(archiveIds);
    }

    @Test
    void getQueue_returnsArchiveStatuses() {
        List<ArchiveStatus> statuses = List.of(
                new ArchiveStatus("arch1", "archive.rar", 100L, ControllerStatus.QUEUED, null, "RAR_SINGLE")
        );
        when(client.getQueue()).thenReturn(new Jd2Response<>(statuses));

        ResponseEntity<List<ArchiveStatus>> response = controller.getQueue();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(statuses, response.getBody());
        verify(client).getQueue();
    }

    @Test
    void setArchiveSettings_returnsBoolean() {
        String settings = Json.stringify(new ArchiveSettings());
        when(client.setArchiveSettings("arch1", settings)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.setArchiveSettings("arch1", settings);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).setArchiveSettings("arch1", settings);
    }

    @Test
    void startExtractionNow_returnsMap() {
        String linkIds = Json.stringify(new long[]{1L});
        String packageIds = Json.stringify(new long[]{2L});
        Map<String, Boolean> result = Map.of("arch1", true);
        when(client.startExtractionNow(linkIds, packageIds)).thenReturn(new Jd2Response<>(result));

        ResponseEntity<Map<String, Boolean>> response = controller.startExtractionNow(linkIds, packageIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(result, response.getBody());
        verify(client).startExtractionNow(linkIds, packageIds);
    }
}

