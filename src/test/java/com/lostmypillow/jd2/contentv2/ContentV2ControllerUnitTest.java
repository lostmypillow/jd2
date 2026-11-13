package com.lostmypillow.jd2.contentv2;

import com.lostmypillow.jd2.structure.IconDescriptor;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContentV2ControllerUnitTest {

    @Mock
    private ContentV2Client client;

    private ContentV2Controller controller;

    @BeforeEach
    void setup() {
        controller = new ContentV2Controller(client);
    }

    @Test
    void getFavIcon_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.getFavIcon("example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).getFavIcon("example.com");
    }

    @Test
    void getFileIcon_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.getFileIcon("archive.zip");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).getFileIcon("archive.zip");
    }

    @Test
    void getIcon_callsClientAndReturnsOk() {
        ResponseEntity<Void> response = controller.getIcon("ok", 16);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).getIcon("ok", 16);
    }

    @Test
    void getIconDescription_returnsIconDescriptor() {
        IconDescriptor descriptor = new IconDescriptor("Icon", "ok", null, null);
        when(client.getIconDescription("ok")).thenReturn(new Jd2Response<>(descriptor));

        ResponseEntity<IconDescriptor> response = controller.getIconDescription("ok");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(descriptor, response.getBody());
        verify(client).getIconDescription("ok");
    }
}

