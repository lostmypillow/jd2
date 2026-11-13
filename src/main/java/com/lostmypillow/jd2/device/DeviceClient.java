package com.lostmypillow.jd2.device;

import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/device")
public interface DeviceClient {
    @GetExchange("/getDirectConnectionInfos")
    // DirectConnectionInfos not defined, we use JsonMap for now
    Jd2Response<JsonMap> getDirectConnectionInfos();

    @GetExchange("/getSessionPublicKey")
    Jd2Response<String> getSessionPublicKey();

    @GetExchange("/ping")
    Jd2Response<Boolean> ping();
}

