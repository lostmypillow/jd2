package com.lostmypillow.jd2.dialogs;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.DialogInfo;
import com.lostmypillow.jd2.structure.DialogTypeInfo;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DialogsClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DialogsClient dialogsClient;

    @Test
    void answer() {
        assertDoesNotThrow(() -> dialogsClient.answer(1L, Json.stringify(Map.of())));
    }

    @Test
    void get() {
        Jd2Response<DialogInfo> data = dialogsClient.get(1L, true, true);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void getTypeInfo() {
        Jd2Response<DialogTypeInfo> data = dialogsClient.getTypeInfo("org.appwork.uio.ConfirmDialogInterface");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(DialogTypeInfo.class, data.data(), "data.data() should be an instance of DialogTypeInfo");
    }

    @Test
    void list() {
        Jd2Response<long[]> data = dialogsClient.list();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(long[].class, data.data(), "data.data() should be an instance of long[]");
    }
}

