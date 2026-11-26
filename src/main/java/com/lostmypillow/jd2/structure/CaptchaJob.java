package com.lostmypillow.jd2.structure;

public record CaptchaJob(
    String captchaCategory,
    Long created,
    String explain,
    String hoster,
    Long id,
    Long link,
    Integer timeout,
    String type
) {}
