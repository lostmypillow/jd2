package com.lostmypillow.jd2.downloadcontroller;

import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/downloadcontroller")
public interface DownloadControllerClient {
    /**
     * Forces download of links and packages
     *
     * @param linkIds a stringified {@code long[]} of link IDs
     * @param packageIds a stringified {@code long[]} of package IDs
     */
    @PostExchange("/forceDownload")
    void forceDownload(
            @RequestParam("linkIds") String linkIds,
            @RequestParam("packageIds") String packageIds
    );

    @GetExchange("/getCurrentState")
    Jd2Response<String> getCurrentState();

    @GetExchange("/getSpeedInBps")
    Jd2Response<Integer> getSpeedInBps();

    @PostExchange("/pause")
    Jd2Response<Boolean> pause(@RequestParam("value") boolean value);

    @PostExchange("/start")
    Jd2Response<Boolean> start();

    @PostExchange("/stop")
    Jd2Response<Boolean> stop();
}

