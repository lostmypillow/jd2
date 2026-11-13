package com.lostmypillow.jd2.jd;

import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/jd")
public interface JdClient {
    @GetExchange("/getCoreRevision")
    Jd2Response<Integer> getCoreRevision();

    @GetExchange("/uptime")
    Jd2Response<Long> getUptime();

    @GetExchange("/version")
    Jd2Response<Long> getVersion();

    @GetExchange("/refreshPlugins")
    Jd2Response<Boolean> refreshPlugins();
}
