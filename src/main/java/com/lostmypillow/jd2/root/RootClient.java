package com.lostmypillow.jd2.root;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface RootClient {
    @GetExchange("/flash")
    void flash();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @GetExchange("/crossdomain.xml")
    void crossdomainXml();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @GetExchange("/favicon.ico")
    void faviconIco();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @GetExchange("/flashgot")
    void flashgot();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @GetExchange("/jdcheck.js")
    void jdcheckJs();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @GetExchange("/jdcheckjson")
    void jdcheckjson();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
}

