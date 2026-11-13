package com.lostmypillow.jd2.ui;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.Context;
import com.lostmypillow.jd2.structure.MyJDMenuItem;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UiControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UiController uiController;

    @Test
    void testGetMenu() {
        ResponseEntity<MyJDMenuItem> response = uiController.getMenu(Context.LGC);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testInvokeAction() {
        assertDoesNotThrow(() -> uiController.invokeAction(
                Context.DLC,
                "action-1",
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }
}

