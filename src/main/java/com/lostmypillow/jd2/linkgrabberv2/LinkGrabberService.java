package com.lostmypillow.jd2.linkgrabberv2;

import com.lostmypillow.jd2.sse.SseDomainEvent;
import com.lostmypillow.jd2.structure.*;
import com.lostmypillow.jd2.util.Json;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class LinkGrabberService {
    private final LinkGrabberClient linkGrabberClient;
    private final ApplicationEventPublisher eventPublisher;


    public LinkGrabberService(LinkGrabberClient linkGrabberClient, ApplicationEventPublisher eventPublisher) {
        this.linkGrabberClient = linkGrabberClient;
        this.eventPublisher = eventPublisher;
    }

    @Async
    public void addLinkAsync(String link) {
        var maxWaitSeconds = 15;
        eventPublisher.publishEvent(new SseDomainEvent("linkgrabber-progress", "Sending link to JD2"));
        try {
//            var linkCollectingJob =
            linkGrabberClient.addLinks(Json.stringify(new AddLinksQuery(link)));
//            var jobId = Optional.ofNullable(linkCollectingJob.data()).map(LinkCollectingJob::id).orElseThrow(() -> new IllegalStateException("JD2 replied with job ID of null"));
            eventPublisher.publishEvent(new SseDomainEvent("linkgrabber-progress", "Link sent to JD2"));
            while (maxWaitSeconds > 0) {
                var collecting = linkGrabberClient.isCollecting().data();
                if (Boolean.TRUE.equals(collecting)) {
                    eventPublisher.publishEvent(new SseDomainEvent("linkgrabber-progress", "Collecting link data"));
                    break;
                }
                TimeUnit.SECONDS.sleep(1);
                maxWaitSeconds--;
            }
            eventPublisher.publishEvent(new SseDomainEvent("linkgrabber-list", Json.stringify(linkGrabberClient.queryLinks(Json.stringify(new CrawledLinkQuery())))));
        } catch (Exception e) {
            eventPublisher.publishEvent(new SseDomainEvent("linkgrabber-error", "Failed: " + e.getMessage()));
        }
    }
}
