package com.lostmypillow.jd2.extensions;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.Extension;
import com.lostmypillow.jd2.structure.ExtensionQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExtensionsClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ExtensionsClient extensionsClient;

    @Test
    void install() {
        Jd2Response<Boolean> data = extensionsClient.install("extraction");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void isEnabled() {
        Jd2Response<Boolean> data = extensionsClient.isEnabled("org.jdownloader.extensions.extraction.ExtractionExtension");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void isInstalled() {
        Jd2Response<Boolean> data = extensionsClient.isInstalled("extraction");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void list() {
        Jd2Response<List<Extension>> data = extensionsClient.list(Json.stringify(new ExtensionQuery()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void setEnabled() {
        Jd2Response<Boolean> data = extensionsClient.setEnabled("org.jdownloader.extensions.extraction.ExtractionExtension", true);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

