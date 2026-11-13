package com.lostmypillow.jd2.contentv2;

import com.lostmypillow.jd2.structure.IconDescriptor;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/contentV2")
public interface ContentV2Client {
    @GetExchange("/getFavIcon")
    void getFavIcon(@RequestParam("hostername") String hostername);
    // Possible Error(s) ResponseCode 404 (Not Found); Type:FILE_NOT_FOUND
    // ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @GetExchange("/getFileIcon")
    void getFileIcon(@RequestParam("filename") String filename);
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    @GetExchange("/getIcon")
    void getIcon(
            @RequestParam("key") String key,
            @RequestParam("size") int size
    );
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
    // ResponseCode 404 (Not Found); Type:FILE_NOT_FOUND
    // ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS

    @GetExchange("/getIconDescription")
    Jd2Response<IconDescriptor> getIconDescription(@RequestParam("key") String key);
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
}

