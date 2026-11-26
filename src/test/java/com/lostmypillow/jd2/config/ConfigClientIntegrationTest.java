package com.lostmypillow.jd2.config;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.AdvancedConfigAPIEntry;
import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.EnumOption;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ConfigClient configClient;

    @Test
    void get() {
        Jd2Response<Object> data = configClient.get("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void getDefault() {
        Jd2Response<Object> data = configClient.getDefault("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void list_noArgs() {
        Jd2Response<List<AdvancedConfigAPIEntry>> data = configClient.list();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void list_fiveArgs() {
        Jd2Response<List<AdvancedConfigAPIEntry>> data = configClient.list(".*", true, true, true, true);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    @SuppressWarnings("deprecation")
    void list_fourArgs() {
        Jd2Response<List<AdvancedConfigAPIEntry>> data = configClient.list(".*", true, true, true);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void listEnum() {
        Jd2Response<List<EnumOption>> data = configClient.listEnum("org.jdownloader.controlling.Priority");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void query() {
        Jd2Response<List<AdvancedConfigAPIEntry>> data = configClient.query(Json.stringify(new AdvancedConfigQuery()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void reset() {
        Jd2Response<Boolean> data = configClient.reset("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void set() {
        Jd2Response<Boolean> data = configClient.set("org.jdownloader.settings.GeneralSettings", "default", "DefaultDownloadFolder", "\"/output\"");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}

