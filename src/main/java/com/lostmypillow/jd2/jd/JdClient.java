package com.lostmypillow.jd2.jd;

import com.lostmypillow.jd2.jd.records.JdApiResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/jd")
public interface JdClient {
    @GetExchange("/getCoreRevision")
    JdApiResponse<Integer> getCoreRevision();

    @GetExchange("/uptime")
    JdApiResponse<Long> getUptime();

    @GetExchange("/version")
    JdApiResponse<Long> getVersion();

    @GetExchange("/refreshPlugins")
    JdApiResponse<Boolean> refreshPlugins();
}
