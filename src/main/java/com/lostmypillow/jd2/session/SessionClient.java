package com.lostmypillow.jd2.session;

import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/session")
public interface SessionClient {
    /**
     * Invalidates the current token
     *
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/disconnect")
    Jd2Response<Boolean> disconnect();

    /**
     * Returns an un/authenticated token for given username and password or "error" in case login failed
     *
     * @param user username
     * @param password password
     * @return a {@link Jd2Response} containing the session token {@link String}
     */
    @PostExchange("/handshake")
    Jd2Response<String> handshake(
            @RequestParam("user") String user,
            @RequestParam("password") String password
    );
    // Possible Error(s) ResponseCode 403 (Forbidden); Type:AUTH_FAILED
}

