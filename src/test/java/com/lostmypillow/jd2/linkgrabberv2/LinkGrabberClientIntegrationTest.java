package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.AbstractIntegrationTest;
import com.lostmypillow.jd2.constants.Action;
import com.lostmypillow.jd2.constants.Mode;
import com.lostmypillow.jd2.constants.SelectionType;
import com.lostmypillow.jd2.structure.*;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LinkGrabberClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private LinkGrabberClient linkGrabberClient;
    @Autowired
    private ConfigurableEnvironment env;

    @Test
    void addLinks() {
        Jd2Response<LinkCollectingJob> data = linkGrabberClient.addLinks(
                Json.stringify(new AddLinksQuery("http://releases.ubuntu.com/26.04/ubuntu-26.04.1-desktop-amd64.iso"))
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(LinkCollectingJob.class, data.data(), "data.data() should be an instance of LinkCollectingJob");
    }

    @Test
    void isCollecting() {
        Jd2Response<Boolean> data = linkGrabberClient.isCollecting();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void clearList() {
        Jd2Response<Boolean> data = linkGrabberClient.clearList();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void queryLinkCrawlerJobs() {
        Jd2Response<List<JobLinkCrawler>> data = linkGrabberClient.queryLinkCrawlerJobs(
                Json.stringify(new LinkCrawlerJobsQuery(new long[]{}))
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void queryLinks() {
        Jd2Response<List<CrawledLink>> data = linkGrabberClient.queryLinks(
                Json.stringify(new CrawledLinkQuery())
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void queryPackages() {
        Jd2Response<List<CrawledPackage>> data = linkGrabberClient.queryPackages(
                Json.stringify(new CrawledPackageQuery(new long[]{}))
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void abort() {
        Jd2Response<Boolean> data = linkGrabberClient.abort(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void addContainer() {
        Jd2Response<LinkCollectingJob> data = linkGrabberClient.addContainer("DLC", "");
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(LinkCollectingJob.class, data.data(), "data.data() should be an instance of LinkCollectingJob");
    }

    @Test
    void addVariantCopy() {
        assertDoesNotThrow(() -> linkGrabberClient.addVariantCopy(1L, 2L, 3L, "variant1"));
    }

    @Test
    void cleanup() {
        assertDoesNotThrow(() -> linkGrabberClient.cleanup(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                Action.DELETE_ALL,
                Mode.REMOVE_LINKS_ONLY,
                SelectionType.NONE
        ));
    }

    @Test
    void getChildrenChanged() {
        Jd2Response<Long> data = linkGrabberClient.getChildrenChanged(0L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Long.class, data.data(), "data.data() should be an instance of Long");
    }

    @Test
    void getDownloadFolderHistorySelectionBase() {
        Jd2Response<List<String>> data = linkGrabberClient.getDownloadFolderHistorySelectionBase();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void getDownloadUrls() {
        Jd2Response<Map<String, List<Long>>> data = linkGrabberClient.getDownloadUrls(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                new String[]{"ORIGIN"}
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Map.class, data.data(), "data.data() should be an instance of Map");
    }

    @Test
    void getPackageCount() {
        Jd2Response<Integer> data = linkGrabberClient.getPackageCount();
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
    }

    @Test
    void getVariants() {
        Jd2Response<List<LinkVariant>> data = linkGrabberClient.getVariants(1L);
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(List.class, data.data(), "data.data() should be an instance of List");
    }

    @Test
    void moveLinks() {
        assertDoesNotThrow(() -> linkGrabberClient.moveLinks(Json.stringify(new long[]{}), -1L, -1L));
    }

    @Test
    void movePackages() {
        assertDoesNotThrow(() -> linkGrabberClient.movePackages(Json.stringify(new long[]{}), -1L));
    }

    @Test
    void moveToDownloadlist() {
        assertDoesNotThrow(() -> linkGrabberClient.moveToDownloadlist(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void movetoNewPackage() {
        assertDoesNotThrow(() -> linkGrabberClient.movetoNewPackage(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                "newPkg",
                "/downloads"
        ));
    }

    @Test
    void removeLinks() {
        assertDoesNotThrow(() -> linkGrabberClient.removeLinks(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void renameLink() {
        assertDoesNotThrow(() -> linkGrabberClient.renameLink(1L, "newName"));
    }

    @Test
    void renamePackage() {
        assertDoesNotThrow(() -> linkGrabberClient.renamePackage(1L, "newPackageName"));
    }

    @Test
    void setDownloadDirectory() {
        assertDoesNotThrow(() -> linkGrabberClient.setDownloadDirectory("/downloads", Json.stringify(new long[]{})));
    }

    @Test
    void setDownloadPassword() {
        Jd2Response<Boolean> data = linkGrabberClient.setDownloadPassword(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{}),
                "password"
        );
        IO.println("data variable is " + data);

        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Boolean.class, data.data(), "data.data() should be an instance of Boolean");
    }

    @Test
    void setEnabled() {
        assertDoesNotThrow(() -> linkGrabberClient.setEnabled(
                true,
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void setPriority() {
        assertDoesNotThrow(() -> linkGrabberClient.setPriority(
                Priority.DEFAULT,
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void setVariant() {
        assertDoesNotThrow(() -> linkGrabberClient.setVariant(1L, "variant1"));
    }

    @Test
    void splitPackageByHoster() {
        assertDoesNotThrow(() -> linkGrabberClient.splitPackageByHoster(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }

    @Test
    void startOnlineStatusCheck() {
        assertDoesNotThrow(() -> linkGrabberClient.startOnlineStatusCheck(
                Json.stringify(new long[]{}),
                Json.stringify(new long[]{})
        ));
    }
}
