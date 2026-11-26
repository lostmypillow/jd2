package com.lostmypillow.jd2.flash;

import com.lostmypillow.jd2.structure.CnlQuery;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/flash")
public interface FlashClient {
    @PostExchange("/add")
    void add(
            @RequestParam("password") String password,
            @RequestParam("source") String source,
            @RequestParam("url") String url
    );
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @PostExchange("/add")
    void add(
            @RequestParam("param1") String param1,
            @RequestParam("param2") String param2,
            @RequestParam("param3") String param3,
            @RequestParam("param4") String param4
    );
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @PostExchange("/add")
    void add();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    /**
     * Adds Click'n'Load links
     *
     * @param cnl a stringified {@link CnlQuery} object
     */
    @PostExchange("/addcnl")
    void addcnl(@RequestParam("cnl") String cnl);
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @PostExchange("/addcrypted")
    void addcrypted();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @PostExchange("/addcrypted2")
    void addcrypted2();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @PostExchange("/addcrypted2Remote")
    void addcrypted2Remote(
            @RequestParam("crypted") String crypted,
            @RequestParam("jk") String jk,
            @RequestParam("k") String k
    );

    @GetExchange("/")
    void flash();
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
}

