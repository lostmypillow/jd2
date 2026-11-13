package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.structure.*;
import com.lostmypillow.jd2.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.service.registry.ImportHttpServices;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles(profiles = "local")
@ImportHttpServices(LinkGrabberClient.class)
class LinkGrabberClientTest {

    @Autowired
    LinkGrabberClient linkGrabberClient;
    @Autowired
    private ConfigurableEnvironment env;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getPackageCount() {
        var data = linkGrabberClient.getPackageCount();
        IO.println("getPackageCount data is " + data);
        assertNotNull(data);
        assertInstanceOf(Jd2Response.class, data, "data should be an instance of JdApiResponse");
        assertInstanceOf(Integer.class, data.data(), "data.data() should be an instance of Integer");
    }

    @Test
    void addLinkAndClearList() throws InterruptedException {
        var addLinkResponse = linkGrabberClient.addLinks(new AddLinksQuery("http://releases.ubuntu.com/26.04/ubuntu-26.04.1-desktop-amd64.iso"));

        TimeUnit.SECONDS.sleep(5);
        var jobId = Optional.ofNullable(addLinkResponse.data()).map(LinkCollectingJob::id).orElseThrow();
        IO.println("Created job ID " + jobId);
        int maxWaitSeconds = 15;
        while (maxWaitSeconds > 0) {
            var collecting = linkGrabberClient.isCollecting().data();
            var crawlerJobs = linkGrabberClient.queryLinkCrawlerJobs(Json.stringify(new LinkCrawlerJobsQuery(new long[]{jobId})));
            if (Boolean.TRUE.equals(collecting)) {
                IO.println("Collecting");
                IO.println(crawlerJobs);
                break;
            }
            TimeUnit.SECONDS.sleep(1);
            maxWaitSeconds--;
        }

        var crawledLinks = linkGrabberClient.queryLinks(new CrawledLinkQuery()).data();
        IO.println("Queried links = " + crawledLinks);
        long[] crawledPackageUuids = crawledLinks.stream().mapToLong(CrawledLink::packageUUID).toArray();
        var crawledPackages = linkGrabberClient.queryPackages(new CrawledPackageQuery(crawledPackageUuids)).data();
        IO.println("Crawled packages = " + crawledPackages);
        var packageCount = linkGrabberClient.getPackageCount().data();

        // Assert link was captured before clearing
        IO.println("Package count before clear = " + packageCount);
        assertFalse(crawledLinks.isEmpty(), "Crawled links should not be empty");
        assertTrue(packageCount > 0, "Package count should be > 0");

        var clearListResponse = linkGrabberClient.clearList();
        IO.println("Cleared download list. Response = " + clearListResponse.data());

        var data2 = linkGrabberClient.getPackageCount();
        IO.println("Package count after clear = " + data2.data());

        assertEquals(0, data2.data(), "Package count should be 0 after clearList()");

    }
}