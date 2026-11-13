package com.lostmypillow.jd2.linkcrawler;

import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/linkcrawler")
public interface LinkCrawlerClient {
    @GetExchange("/isCrawling")
    Jd2Response<Boolean> isCrawling();
}

