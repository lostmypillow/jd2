package com.lostmypillow.jd2.ui;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.Context;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.MyJDMenuItem;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UiClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UiClient uiClient;

    @Test
    void testGetMenu() {
        Jd2Response<MyJDMenuItem> response = uiClient.getMenu(Context.LGC);
        assertNotNull(response);
        assertNotNull(response.data());
    }

    @Test
    void testInvokeAction() {
        assertDoesNotThrow(() -> uiClient.invokeAction(
                Context.DLC,
                "action-1",
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }
}

