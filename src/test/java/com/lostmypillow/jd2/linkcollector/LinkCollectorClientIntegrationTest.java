package com.lostmypillow.jd2.linkcollector;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.CrawledLink;
import com.lostmypillow.jd2.structure.CrawledPackage;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkCollectorClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LinkCollectorClient linkCollectorClient;

    @Test
    void addContainer() {
        assertDoesNotThrow(() -> linkCollectorClient.addContainer("DLC", ""));
    }

    @Test
    void addLinks_fourArgs() {
        Jd2Response<Boolean> data = linkCollectorClient.addLinks("https://example.com", "pkg", "archPass", "linkPass");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void addLinks_fiveArgs() {
        Jd2Response<Boolean> data = linkCollectorClient.addLinks("https://example.com", "pkg", "extPass", "dlPass", "/downloads");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void addLinksAndStartDownload() {
        Jd2Response<Boolean> data = linkCollectorClient.addLinksAndStartDownload("https://example.com", "pkg", "extPass", "dlPass");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void disableLinks_twoArgs() {
        Jd2Response<Boolean> data = linkCollectorClient.disableLinks(
                Json.stringify(List.of()),
                Json.stringify(List.of())
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void disableLinks_oneArg() {
        Jd2Response<Boolean> data = linkCollectorClient.disableLinks(Json.stringify(List.of()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void enableLinks_twoArgs() {
        Jd2Response<Boolean> data = linkCollectorClient.enableLinks(
                Json.stringify(List.of()),
                Json.stringify(List.of())
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void enableLinks_oneArg() {
        Jd2Response<Boolean> data = linkCollectorClient.enableLinks(Json.stringify(List.of()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void getChildrenChanged() {
        Jd2Response<Long> data = linkCollectorClient.getChildrenChanged(0L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Long.class, data.data(), "data.data() should be an instance of Long");
    }

    @Test
    void getDownloadFolderHistorySelectionBase() {
        Jd2Response<List<String>> data = linkCollectorClient.getDownloadFolderHistorySelectionBase();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void moveLinks() {
        Jd2Response<Boolean> data = linkCollectorClient.moveLinks(Json.stringify(new APIQuery<>()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void movePackages() {
        Jd2Response<Boolean> data = linkCollectorClient.movePackages(Json.stringify(new APIQuery<>()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void packageCount() {
        Jd2Response<Integer> data = linkCollectorClient.packageCount();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
    }

    @Test
    void queryLinks() {
        Jd2Response<List<CrawledLink>> data = linkCollectorClient.queryLinks(Json.stringify(new APIQuery<>()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void queryPackages() {
        Jd2Response<List<CrawledPackage>> data = linkCollectorClient.queryPackages(Json.stringify(new APIQuery<>()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void removeLinks_twoArgs() {
        Jd2Response<Boolean> data = linkCollectorClient.removeLinks(
                Json.stringify(List.of()),
                Json.stringify(List.of())
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void removeLinks_oneArg() {
        Jd2Response<Boolean> data = linkCollectorClient.removeLinks(Json.stringify(List.of()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void renameLink() {
        Jd2Response<Boolean> data = linkCollectorClient.renameLink(1L, 1L, "newName");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void renamePackage() {
        Jd2Response<Boolean> data = linkCollectorClient.renamePackage(1L, "newPackageName");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void startDownloads_oneArg() {
        Jd2Response<Boolean> data = linkCollectorClient.startDownloads(Json.stringify(List.of()));
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }

    @Test
    void startDownloads_twoArgs() {
        Jd2Response<Boolean> data = linkCollectorClient.startDownloads(
                Json.stringify(List.of()),
                Json.stringify(List.of())
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
    }
}

