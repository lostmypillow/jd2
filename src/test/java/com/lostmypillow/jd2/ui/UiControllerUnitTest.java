package com.lostmypillow.jd2.ui;

import com.lostmypillow.jd2.constants.Context;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.MenuStructure;
import com.lostmypillow.jd2.structure.MyJDMenuItem;
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
public class UiControllerUnitTest {

    @Mock
    private UiClient uiClient;

    @InjectMocks
    private UiController uiController;

    @Test
    void getMenu_returnsMenuItem() {
        MyJDMenuItem expected = new MyJDMenuItem(List.of(), "icon", "id-1", "Menu", MenuStructure.Type.CONTAINER);
        when(uiClient.getMenu(Context.LGC)).thenReturn(new Jd2Response<>(expected));

        ResponseEntity<MyJDMenuItem> response = uiController.getMenu(Context.LGC);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(uiClient).getMenu(Context.LGC);
    }

    @Test
    void invokeAction_returnsObject() {
        String linkIdsJson = Json.stringify(new long[]{1L});
        String packageIdsJson = Json.stringify(new long[]{2L});
        when(uiClient.invokeAction(Context.DLC, "act-1", linkIdsJson, packageIdsJson)).thenReturn(new Jd2Response<>("result"));

        ResponseEntity<Object> response = uiController.invokeAction(Context.DLC, "act-1", linkIdsJson, packageIdsJson);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("result", response.getBody());
        verify(uiClient).invokeAction(Context.DLC, "act-1", linkIdsJson, packageIdsJson);
    }
}

