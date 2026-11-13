package com.lostmypillow.jd2.reconnect;

import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/reconnect")
public interface ReconnectClient {
    /**
     * Triggers a network reconnect
     */
    @PostExchange("/doReconnect")
    void doReconnect();
}

