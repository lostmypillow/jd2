package com.lostmypillow.jd2.polling;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.PollingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/polling")
public interface PollingClient {
    /**
     * Polls for events matching the given query parameters
     *
     * @param queryParams a stringified {@link APIQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link PollingResult}
     */
    @PostExchange("/poll")
    Jd2Response<List<PollingResult>> poll(@RequestParam("queryParams") String queryParams);
}

