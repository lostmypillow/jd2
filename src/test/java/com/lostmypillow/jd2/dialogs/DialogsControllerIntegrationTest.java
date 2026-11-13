package com.lostmypillow.jd2.dialogs;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.DialogInfo;
import com.lostmypillow.jd2.structure.DialogTypeInfo;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DialogsControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DialogsController dialogsController;

    @Test
    void answer() {
        ResponseEntity<Void> response = dialogsController.answer(1L, Json.stringify(Map.of()));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void get() {
        ResponseEntity<DialogInfo> response = dialogsController.get(1L, true, true);
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getTypeInfo() {
        ResponseEntity<DialogTypeInfo> response = dialogsController.getTypeInfo("org.appwork.uio.ConfirmDialogInterface");
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(DialogTypeInfo.class, response.getBody());
    }

    @Test
    void list() {
        ResponseEntity<long[]> response = dialogsController.list();
        IO.println("response variable is " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(long[].class, response.getBody());
    }
}

