package com.lostmypillow.jd2.extensions;

import com.lostmypillow.jd2.structure.Extension;
import com.lostmypillow.jd2.structure.ExtensionQuery;
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
public class ExtensionsControllerUnitTest {

    @Mock
    private ExtensionsClient client;

    private ExtensionsController controller;

    @BeforeEach
    void setup() {
        controller = new ExtensionsController(client);
    }

    @Test
    void install_returnsBoolean() {
        when(client.install("eventscripter")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.install("eventscripter");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).install("eventscripter");
    }

    @Test
    void isEnabled_returnsBoolean() {
        when(client.isEnabled("org.jdownloader.extensions.extraction.ExtractionExtension")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.isEnabled("org.jdownloader.extensions.extraction.ExtractionExtension");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).isEnabled("org.jdownloader.extensions.extraction.ExtractionExtension");
    }

    @Test
    void isInstalled_returnsBoolean() {
        when(client.isInstalled("extraction")).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.isInstalled("extraction");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).isInstalled("extraction");
    }

    @Test
    void list_returnsExtensions() {
        String query = Json.stringify(new ExtensionQuery());
        List<Extension> extensions = List.of(
                new Extension("iface", "desc", true, "icon", "extraction", true, "Archive Extractor")
        );
        when(client.list(query)).thenReturn(new Jd2Response<>(extensions));

        ResponseEntity<List<Extension>> response = controller.list(query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(extensions, response.getBody());
        verify(client).list(query);
    }

    @Test
    void setEnabled_returnsBoolean() {
        when(client.setEnabled("org.jdownloader.extensions.extraction.ExtractionExtension", true)).thenReturn(new Jd2Response<>(true));

        ResponseEntity<Boolean> response = controller.setEnabled("org.jdownloader.extensions.extraction.ExtractionExtension", true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(client).setEnabled("org.jdownloader.extensions.extraction.ExtractionExtension", true);
    }
}

