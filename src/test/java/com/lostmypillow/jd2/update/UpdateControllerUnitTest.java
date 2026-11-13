package com.lostmypillow.jd2.update;

import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateControllerUnitTest {

    @Mock
    private UpdateClient updateClient;

    @InjectMocks
    private UpdateController updateController;

    @Test
    void isUpdateAvailable_returnsBoolean() {
        when(updateClient.isUpdateAvailable()).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = updateController.isUpdateAvailable();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(updateClient).isUpdateAvailable();
    }

    @Test
    void restartAndUpdate_returnsOk() {
        ResponseEntity<Void> response = updateController.restartAndUpdate();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateClient).restartAndUpdate();
    }

    @Test
    void runUpdateCheck_returnsOk() {
        ResponseEntity<Void> response = updateController.runUpdateCheck();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateClient).runUpdateCheck();
    }
}

