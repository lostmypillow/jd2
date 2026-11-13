package com.lostmypillow.jd2.captchaforward;

import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/captchaforward")
public interface CaptchaForwardClient {
    @PostExchange("/createJobRecaptchaV2")
    Jd2Response<Long> createJobRecaptchaV2(
            @RequestParam("param1") String param1,
            @RequestParam("param2") String param2,
            @RequestParam("param3") String param3,
            @RequestParam("param4") String param4
    );

    @GetExchange("/getResult")
    Jd2Response<String> getResult(@RequestParam("param1") long param1);
    // Possible Error(s) ResponseCode 404 (Not Found); Type:Not Found
    // ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
}

