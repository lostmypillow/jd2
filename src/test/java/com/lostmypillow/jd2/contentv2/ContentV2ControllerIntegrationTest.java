package com.lostmypillow.jd2.contentv2;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.IconDescriptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ContentV2ControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ContentV2Controller contentV2Controller;

    @Test
    void getFavIcon() {
        ResponseEntity<Void> response = contentV2Controller.getFavIcon("jdownloader.org");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getFileIcon() {
        ResponseEntity<Void> response = contentV2Controller.getFileIcon("archive.zip");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getIcon() {
        ResponseEntity<Void> response = contentV2Controller.getIcon("ok", 16);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getIconDescription() {
        ResponseEntity<IconDescriptor> response = contentV2Controller.getIconDescription("ok");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(IconDescriptor.class, response.getBody());
    }
}

