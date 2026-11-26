package com.lostmypillow.jd2.contentv2;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.IconDescriptor;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ContentV2ClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ContentV2Client contentV2Client;

    @Test
    void getFavIcon() {
        assertDoesNotThrow(() -> contentV2Client.getFavIcon("jdownloader.org"));
    }

    @Test
    void getFileIcon() {
        assertDoesNotThrow(() -> contentV2Client.getFileIcon("archive.zip"));
    }

    @Test
    void getIcon() {
        assertDoesNotThrow(() -> contentV2Client.getIcon("ok", 16));
    }

    @Test
    void getIconDescription() {
        Jd2Response<IconDescriptor> data = contentV2Client.getIconDescription("ok");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(IconDescriptor.class, data.data(), "data.data() should be an instance of IconDescriptor");
    }
}

