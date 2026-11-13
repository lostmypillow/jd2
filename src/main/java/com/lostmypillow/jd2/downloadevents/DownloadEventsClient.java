package com.lostmypillow.jd2.downloadevents;

import com.lostmypillow.jd2.structure.DownloadListDiff;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.LinkQuery;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/downloadevents")
public interface DownloadEventsClient {
    /**
     * Queries links diff
     *
     * @param queryParams a stringified {@link LinkQuery} object
     * @param diffID diff ID
     * @return a {@link Jd2Response} containing the {@link DownloadListDiff}
     */
    @PostExchange("/queryLinks")
    Jd2Response<DownloadListDiff> queryLinks(
            @RequestParam("queryParams") String queryParams,
            @RequestParam("diffID") int diffID
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/setStatusEventInterval")
    Jd2Response<Boolean> setStatusEventInterval(
            @RequestParam("channelID") long channelID,
            @RequestParam("interval") long interval
    );
}

