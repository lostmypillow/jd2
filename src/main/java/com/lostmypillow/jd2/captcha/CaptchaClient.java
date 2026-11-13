package com.lostmypillow.jd2.captcha;

import com.lostmypillow.jd2.constants.SkipRequest;
import com.lostmypillow.jd2.structure.CaptchaJob;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/captcha")
public interface CaptchaClient {
    /**
     * Returns Captcha Image as Base64 encoded data url
     *
     * @param id captcha job ID
     */
    @GetExchange("/get")
    void get(@RequestParam("id") long id);
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
    // ResponseCode 404 (Not Found); Type:NOT_AVAILABLE

    /**
     * Returns Captcha Image as Base64 encoded data url
     *
     * @param id captcha job ID
     * @param format image format
     */
    @GetExchange("/get")
    void get(
            @RequestParam("id") long id,
            @RequestParam("format") String format
    );
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR
    // ResponseCode 404 (Not Found); Type:NOT_AVAILABLE

    /**
     * Returns CaptchaJob Object for the given id
     *
     * @param id captcha job ID
     * @return a {@link Jd2Response} containing the {@link CaptchaJob}
     */
    @GetExchange("/getCaptchaJob")
    Jd2Response<CaptchaJob> getCaptchaJob(@RequestParam("id") long id);
    // Possible Error(s) ResponseCode 404 (Not Found); Type:NOT_AVAILABLE

    /**
     * Returns a list of all available captcha jobs
     *
     * @return a {@link Jd2Response} containing a {@link List} of {@link CaptchaJob}
     */
    @GetExchange("/list")
    Jd2Response<List<CaptchaJob>> list();

    @PostExchange("/skip")
    Jd2Response<Boolean> skip(
            @RequestParam("id") long id,
            @RequestParam("type") SkipRequest type
    );
    // Possible Error(s) ResponseCode 404 (Not Found); Type:NOT_AVAILABLE

    /**
     * @deprecated This method will be removed soon. DO NOT USE IT!
     */
    @Deprecated
    @PostExchange("/skip")
    Jd2Response<Boolean> skip(@RequestParam("id") long id);
    // Possible Error(s) ResponseCode 404 (Not Found); Type:NOT_AVAILABLE

    @PostExchange("/solve")
    Jd2Response<Boolean> solve(
            @RequestParam("id") long id,
            @RequestParam("result") String result,
            @RequestParam("resultFormat") String resultFormat
    );
    // Possible Error(s) ResponseCode 404 (Not Found); Type:NOT_AVAILABLE
    // ResponseCode 404 (Not Found); Type:UNKNOWN_CHALLENGETYPE; Description:

    @PostExchange("/solve")
    Jd2Response<Boolean> solve(
            @RequestParam("id") long id,
            @RequestParam("result") String result
    );
    // Possible Error(s) ResponseCode 404 (Not Found); Type:NOT_AVAILABLE
    // ResponseCode 404 (Not Found); Type:UNKNOWN_CHALLENGETYPE; Description:
}

