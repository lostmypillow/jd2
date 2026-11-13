package com.lostmypillow.jd2.downloads;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.FilePackage;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.DownloadLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.service.registry.ImportHttpServices;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ImportHttpServices(DownloadsClient.class)
class DownloadsClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DownloadsClient downloadsClient;
    @Autowired
    private ConfigurableEnvironment env;

    @Test
    void testGetJDState() {
        Jd2Response<String> data = downloadsClient.getJDState();
        IO.println("getJDState data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(String.class, data.data(), "data.data() should be an instance of String");
    }

    @Test
    void testPackageCount() {
        Jd2Response<Integer> data = downloadsClient.packageCount();
        IO.println("packageCount data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
        assertTrue(data.data() >= 0, "packageCount should be greater than or equal to 0");
    }

    @Test
    void testSpeed() {
        Jd2Response<Integer> data = downloadsClient.speed();
        IO.println("speed data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
        assertTrue(data.data() >= 0, "speed should be greater than or equal to 0");
    }

    @Test
    void testGetChildrenChanged() {
        Jd2Response<Long> data = downloadsClient.getChildrenChanged(-1L);
        IO.println("getChildrenChanged data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Long.class, data.data(), "data.data() should be an instance of Long");
    }

    @Test
    void testQueryPackages() {
        Jd2Response<List<FilePackage>> data = downloadsClient.queryPackages("{}");
        IO.println("queryPackages data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void testQueryLinks() {
        Jd2Response<List<DownloadLink>> data = downloadsClient.queryLinks("{}");
        IO.println("queryLinks data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void testStart() {
        Jd2Response<Boolean> data = downloadsClient.start();
        IO.println("start data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testPause() {
        Jd2Response<Boolean> data = downloadsClient.pause(false);
        IO.println("pause data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testStop() {
        Jd2Response<Boolean> data = downloadsClient.stop();
        IO.println("stop data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testDisableLinks() {
        Jd2Response<Boolean> data = downloadsClient.disableLinks(List.of(), List.of());
        IO.println("disableLinks data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testEnableLinks() {
        Jd2Response<Boolean> data = downloadsClient.enableLinks(List.of(), List.of());
        IO.println("enableLinks data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testForceDownload() {
        Jd2Response<Boolean> data = downloadsClient.forceDownload(List.of(), List.of());
        IO.println("forceDownload data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testResetLinks() {
        Jd2Response<Boolean> data = downloadsClient.resetLinks(List.of(), List.of());
        IO.println("resetLinks data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testRemoveLinks() {
        Jd2Response<Boolean> data = downloadsClient.removeLinks(List.of(), List.of());
        IO.println("removeLinks data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testRenamePackage() {
        Jd2Response<Boolean> data = downloadsClient.renamePackage(-1L, "nonexistent");
        IO.println("renamePackage data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testMoveLinks() {
        Jd2Response<Boolean> data = downloadsClient.moveLinks("{}");
        IO.println("moveLinks data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void testMovePackages() {
        Jd2Response<Boolean> data = downloadsClient.movePackages("{}");
        IO.println("movePackages data is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }
}
