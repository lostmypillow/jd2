package com.lostmypillow.jd2.dialogs;

import com.lostmypillow.jd2.structure.DialogInfo;
import com.lostmypillow.jd2.structure.DialogTypeInfo;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DialogsControllerUnitTest {

    @Mock
    private DialogsClient client;

    private DialogsController controller;

    @BeforeEach
    void setup() {
        controller = new DialogsController(client);
    }

    @Test
    void answer_callsClientAndReturnsOk() {
        String data = Json.stringify(Map.of("ok", true));

        ResponseEntity<Void> response = controller.answer(1L, data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(client).answer(1L, data);
    }

    @Test
    void get_returnsDialogInfo() {
        DialogInfo dialogInfo = new DialogInfo(Map.of("title", "Confirm"), "org.appwork.uio.ConfirmDialogInterface");
        when(client.get(1L, true, true)).thenReturn(new Jd2Response<>(dialogInfo));

        ResponseEntity<DialogInfo> response = controller.get(1L, true, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dialogInfo, response.getBody());
        verify(client).get(1L, true, true);
    }

    @Test
    void getTypeInfo_returnsDialogTypeInfo() {
        DialogTypeInfo typeInfo = new DialogTypeInfo(Map.of("title", "String"), Map.of("result", "Boolean"));
        when(client.getTypeInfo("org.appwork.uio.ConfirmDialogInterface")).thenReturn(new Jd2Response<>(typeInfo));

        ResponseEntity<DialogTypeInfo> response = controller.getTypeInfo("org.appwork.uio.ConfirmDialogInterface");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(typeInfo, response.getBody());
        verify(client).getTypeInfo("org.appwork.uio.ConfirmDialogInterface");
    }

    @Test
    void list_returnsIds() {
        long[] ids = {1L, 2L, 3L};
        when(client.list()).thenReturn(new Jd2Response<>(ids));

        ResponseEntity<long[]> response = controller.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(ids, response.getBody());
        verify(client).list();
    }
}

