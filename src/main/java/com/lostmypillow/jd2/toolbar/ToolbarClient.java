package com.lostmypillow.jd2.toolbar;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LinkCheckResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/toolbar")
public interface ToolbarClient {
    /**
     * Adds links from DOM
     *
     * @return a {@link Jd2Response} containing an {@link Object}
     */
    @PostExchange("/addLinksFromDOM")
    Jd2Response<Object> addLinksFromDOM();

    /**
     * Checks links from DOM
     *
     * @return a {@link Jd2Response} containing an {@link Object}
     */
    @PostExchange("/checkLinksFromDOM")
    Jd2Response<Object> checkLinksFromDOM();

    /**
     * Gets toolbar status
     *
     * @return a {@link Jd2Response} containing an {@link Object}
     */
    @GetExchange("/getStatus")
    Jd2Response<Object> getStatus();

    /**
     * Checks whether toolbar is available
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @GetExchange("/isAvailable")
    Jd2Response<Boolean> isAvailable();

    /**
     * Polls checked links from DOM by check ID
     *
     * @param checkID check identifier
     * @return a {@link Jd2Response} containing a {@link LinkCheckResult}
     */
    @PostExchange("/pollCheckedLinksFromDOM")
    Jd2Response<LinkCheckResult> pollCheckedLinksFromDOM(@RequestParam("checkID") String checkID);

    /**
     * Performs special URL handling
     *
     * @param url URL to handle
     * @return a {@link Jd2Response} containing a {@link String}
     */
    @PostExchange("/specialURLHandling")
    Jd2Response<String> specialURLHandling(@RequestParam("url") String url);

    /**
     * Starts downloads from toolbar
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/startDownloads")
    Jd2Response<Boolean> startDownloads();

    /**
     * Stops downloads from toolbar
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/stopDownloads")
    Jd2Response<Boolean> stopDownloads();

    /**
     * Toggles automatic reconnect
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/toggleAutomaticReconnect")
    Jd2Response<Boolean> toggleAutomaticReconnect();

    /**
     * Toggles clipboard monitoring
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/toggleClipboardMonitoring")
    Jd2Response<Boolean> toggleClipboardMonitoring();

    /**
     * Toggles download speed limit
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/toggleDownloadSpeedLimit")
    Jd2Response<Boolean> toggleDownloadSpeedLimit();

    /**
     * Toggles pause downloads
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/togglePauseDownloads")
    Jd2Response<Boolean> togglePauseDownloads();

    /**
     * Toggles premium usage
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/togglePremium")
    Jd2Response<Boolean> togglePremium();

    /**
     * Toggles stop after current download
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/toggleStopAfterCurrentDownload")
    Jd2Response<Boolean> toggleStopAfterCurrentDownload();

    /**
     * Triggers an update
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/triggerUpdate")
    Jd2Response<Boolean> triggerUpdate();
}

