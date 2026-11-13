package com.lostmypillow.jd2.log;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LogFolder;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogControllerUnitTest {

    @Mock
    private LogClient logClient;

    @InjectMocks
    private LogController logController;

    @Test
    void getAvailableLogs_returnsLogFolders() {
        List<LogFolder> expected = List.of(new LogFolder(1000L, true, 2000L));
        when(logClient.getAvailableLogs()).thenReturn(new Jd2Response<>(expected));

        ResponseEntity<List<LogFolder>> response = logController.getAvailableLogs();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(logClient).getAvailableLogs();
    }

    @Test
    void sendLogFile_returnsId() {
        String logFoldersJson = Json.stringify(new LogFolder[]{new LogFolder()});
        when(logClient.sendLogFile(logFoldersJson)).thenReturn(new Jd2Response<>("log-id-123"));

        ResponseEntity<String> response = logController.sendLogFile(logFoldersJson);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("log-id-123", response.getBody());
        verify(logClient).sendLogFile(logFoldersJson);
    }
}

